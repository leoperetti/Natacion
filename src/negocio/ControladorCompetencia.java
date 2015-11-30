package negocio;
import datos.*;
import entidades.Carrera;
import entidades.Estilo;
import entidades.Nadador;
import entidades.Programa;
import entidades.Serie;
import entidades.Torneo;
import java.util.ArrayList;

public class ControladorCompetencia {
	
	private CatalogoNadador cn = new CatalogoNadador();
	private CatalogoSerie cs = new CatalogoSerie();
	private CatalogoCarrera cc = new CatalogoCarrera();
	private CnadadoresPorCarrera cnpc = new CnadadoresPorCarrera();
	private CatalogoPrograma cp = new CatalogoPrograma();
	private CatalogoTorneo ct = new CatalogoTorneo();
	private CatalogoEstilos ce = new CatalogoEstilos();
	private CatalogoInscripcion ci = new CatalogoInscripcion();

	public void cargarNadador(int dni, String nombre, String apellido, String club, int edad, String tiempo1, String tiempo2, char sexo) {
		
		 cn.cargarNadador(dni, nombre, apellido, club, edad, tiempo1, tiempo2, sexo);
		
	}

	public void generarSerie(int nro) 
	{

	}

	public ArrayList<Nadador> buscarTodosNadadores()
	{
		return cn.buscarTodosNadadores();
	}
	
	public ArrayList<Torneo> buscarTorneosPorPrograma(int nroPrograma)
	{
		return ct.buscarTorneosPorPrograma(nroPrograma);
	}

	public ArrayList<Nadador> buscarNadadoresPorCarrera(int nroCarrera)
	{
		return cnpc.buscarNadadoresPorCarrera(nroCarrera);
	}

	public ArrayList<Nadador> traerTodosNadadores(int edad, int nroCarrera) 
	{
		return cn.buscarNadadores(edad, nroCarrera);
	}

	
	public void cargarNadadorEnCarrera(int dni, int nroCarrera)
	{
		cnpc.cargarNadadorEnCarrera(dni, nroCarrera);
	}


	public ArrayList<Programa> traerLosProgramas() {
		
		return cp.buscarProgramas();
	}



	public ArrayList<Carrera> traerCarrerasPorTorneo(int nroTorneo) {
		
		return cc.traerCarrerasPorTorneo(nroTorneo);
	}



	public ArrayList<Estilo> traerLosEstilos() {
		
		return ce.traerLosEstilos();
	}



	public ArrayList<Torneo> buscarTorneos() {

		return ct.buscarTorneos();
	}



	public void cargarCarrera(int nroEst, int nro, int tipo, int metros, char sexo, int nroTor) {
		
		cc.cargarCarrera(nroEst, nro, tipo, metros, sexo, nroTor);
		
	}
	
	public boolean generarSeriesPorCarrera(int nroCarrera)
	{
		int numeroNadador = cnpc.contarNadadoresEnCarrera(nroCarrera);
		if (numeroNadador > 1)
		{
			if (numeroNadador % 6 != 0)
			{
				for (int i=1; i <= (int)(numeroNadador/6)+1; i++)
				{
					cs.insertarSerie(nroCarrera);
				}
			}
			else
			{
				for (int i=1; i <= (numeroNadador/6); i++)
				{
					cs.insertarSerie(nroCarrera);
				}
			}
			
			this.distribuirNadadoresEnSeries(cnpc.buscarNadadoresPorCarreraOrdenadosPorTiempo(nroCarrera), cs.buscarSeriesPorCarrera(nroCarrera), numeroNadador);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void distribuirNadadoresEnSeries(ArrayList<Nadador> nadadoresEnCarreraOrdenadosPorTiempo, ArrayList<Serie> seriesEnCarrera, int numeroNadadores)
	{
		int cantidadSeries;
		if (numeroNadadores % 6 != 0)
		{
			cantidadSeries = (int)(numeroNadadores/6) + 1;
		}
		else
		{
			cantidadSeries = numeroNadadores/6;
		}
		
		int nroNadadoresSinResto = (int) numeroNadadores/cantidadSeries;
		int restoNroNadadores = numeroNadadores % cantidadSeries;
		
		int cont = 0;
		int contSeries = 0;
		
		for (int i = 1; i <= cantidadSeries; i++)
		{
			ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(i-1).getNroSerie(), 2);
			cont += 1;
		}
		
		for (int i = 1; i <= cantidadSeries; i++)
		{
			ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(i-1).getNroSerie(), 3);
			cont += 1;
		}
		
		for (int i = 1; i<=restoNroNadadores; i++)
		{
			for (int j = 1; j<=nroNadadoresSinResto - 1; j ++)
			{
				if (j == 1)
					ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j);
				if (j > 1)
					ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j + 2);
				
				cont += 1;

			}
			contSeries += 1;
		}
		
		for (int i = 1; i<=cantidadSeries - restoNroNadadores; i++)
		{
			for (int j = 1; j<=nroNadadoresSinResto - 2; j ++)
			{
				if (j == 1)
					ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j);
				if (j > 1)
					ci.cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j + 2);
				
				cont += 1;
			}
			contSeries += 1;
		}
	}

	public void quitarNadadorDeCarrera(int dni, int nroCarrera) 
	{
		cnpc.quitarNadadorDeCarrera(dni, nroCarrera);
	}
	
	public Nadador buscarNadadorPorDni (int dni)
	{
		return cn.buscarNadadorPorDni(dni);
	}
	
	public ArrayList<Nadador> buscarMuchosNadadoresPorDni(int dni)
	{
		return cn.buscarMuchosNadadorPorDni(dni);
	}
	
	public int buscarUltimoNumeroTorneo()
	{
		return ct.buscarUltimoNumeroDeTorneo();
	}
	
	public void cargarTorneo(int nroTorneo, int nroPrograma, String club, String fecha, String localidad)
	{
		ct.cargarTorneo(nroTorneo, nroPrograma, club, fecha, localidad);
	}
	

}
