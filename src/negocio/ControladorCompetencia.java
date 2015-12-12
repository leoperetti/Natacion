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
	
	//CatálogoDeNadador
	public void cargarNadador(int dni, String nombre, String apellido, String club, int edad, String tiempo1, String tiempo2, char sexo) 
	{
		 CatalogoNadador.getInstance().cargarNadador(dni, nombre, apellido, club, edad, tiempo1, tiempo2, sexo);
	}
	
	public void eliminarNadador(int dni)
	{
		CatalogoNadador.getInstance().eliminarNadador(dni);
	}

	public ArrayList<Nadador> buscarTodosNadadores()
	{
		return CatalogoNadador.getInstance().buscarTodosNadadores();
	}
	
	public ArrayList<Nadador> traerTodosNadadores(int edad, int nroCarrera) 
	{
		return CatalogoNadador.getInstance().buscarNadadores(edad, nroCarrera);
	}

	public Nadador buscarNadadorPorDni(int dni)
	{
		return CatalogoNadador.getInstance().buscarNadadorPorDni(dni);
	}
	
	public ArrayList<Nadador> buscarMuchosNadadoresPorDni(int dni)
	{
		return CatalogoNadador.getInstance().buscarMuchosNadadorPorDni(dni);
	}
	
	//CatálogoDeTorneos
	public ArrayList<Torneo> buscarTorneosPorPrograma(int nroPrograma)
	{
		return CatalogoTorneo.getInstance().buscarTorneosPorPrograma(nroPrograma);
	}
	
	public ArrayList<Torneo> buscarTorneos() 
	{
		return CatalogoTorneo.getInstance().buscarTorneos();
	}
	
	public int buscarUltimoNumeroTorneo()
	{
		return CatalogoTorneo.getInstance().buscarUltimoNumeroDeTorneo();
	}
	
	public void cargarTorneo(int nroTorneo, int nroPrograma, String club, String fecha, String localidad)
	{
		CatalogoTorneo.getInstance().cargarTorneo(nroTorneo, nroPrograma, club, fecha, localidad);
	}
	
	public void eliminarTorneo(int nroTorneo) 
	{
		CatalogoTorneo.getInstance().eliminarTorneo(nroTorneo);
	}
	
	public Torneo buscarTorneosPorNroTorneo(int nroTorneo) 
	{
		return CatalogoTorneo.getInstance().buscarTorneoPorNroTorneo(nroTorneo);
	}
	
	public void modificarTorneo(int nroPrograma, String fechaTorneo, String clubAnfitrion, String localidad, int nroTorneo) 
	{
		CatalogoTorneo.getInstance().modificarTorneo(nroPrograma, fechaTorneo, clubAnfitrion, localidad, nroTorneo);
	}

	//CatálogoDeNadadoresPorCarrera
	public ArrayList<Nadador> buscarNadadoresPorCarrera(int nroCarrera)
	{
		return CnadadoresPorCarrera.getInstance().buscarNadadoresPorCarrera(nroCarrera);
	}

	public void cargarNadadorEnCarrera(int dni, int nroCarrera)
	{
		CnadadoresPorCarrera.getInstance().cargarNadadorEnCarrera(dni, nroCarrera);
	}
	
	public void quitarNadadorDeCarrera(int dni, int nroCarrera) 
	{
		CnadadoresPorCarrera.getInstance().quitarNadadorDeCarrera(dni, nroCarrera);
	}

	//CatálogoDeProgramas
	public ArrayList<Programa> traerLosProgramas() 
	{
		return CatalogoPrograma.getInstance().buscarProgramas();
	}

	//CatálogoDeCarreras
	public ArrayList<Carrera> traerCarrerasPorTorneo(int nroTorneo) 
	{
		return CatalogoCarrera.getInstance().traerCarrerasPorTorneo(nroTorneo);
	}
	
	public void cargarCarrera(int nroEst, int nro, int tipo, int metros, char sexo, int nroTor) 
	{
		CatalogoCarrera.getInstance().cargarCarrera(nroEst, nro, tipo, metros, sexo, nroTor);
	}
	
	//CatálogoDeEstilos
	public ArrayList<Estilo> traerLosEstilos() 
	{
		return CatalogoEstilos.getInstance().traerLosEstilos();
	}

	//Series e Inscripciones
	public boolean generarSeriesPorCarrera(int nroCarrera)
	{
		int numeroNadador = CnadadoresPorCarrera.getInstance().contarNadadoresEnCarrera(nroCarrera);
		if (numeroNadador > 1)
		{
			if (numeroNadador % 6 != 0)
			{
				for (int i=1; i <= (int)(numeroNadador/6)+1; i++)
				{
					CatalogoSerie.getInstance().insertarSerie(nroCarrera);
				}
			}
			else
			{
				for (int i=1; i <= (numeroNadador/6); i++)
				{
					CatalogoSerie.getInstance().insertarSerie(nroCarrera);
				}
			}
			
			this.distribuirNadadoresEnSeries(CnadadoresPorCarrera.getInstance().buscarNadadoresPorCarreraOrdenadosPorTiempo(nroCarrera), CatalogoSerie.getInstance().buscarSeriesPorCarrera(nroCarrera), numeroNadador);
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
			CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(i-1).getNroSerie(), 2);
			cont += 1;
		}
		
		for (int i = 1; i <= cantidadSeries; i++)
		{
			CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(i-1).getNroSerie(), 3);
			cont += 1;
		}
		
		for (int i = 1; i<=restoNroNadadores; i++)
		{
			for (int j = 1; j<=nroNadadoresSinResto - 1; j ++)
			{
				if (j == 1)
					CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j);
				if (j > 1)
					CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j + 2);
				cont += 1;
			}
			contSeries += 1;
		}
		
		for (int i = 1; i<=cantidadSeries - restoNroNadadores; i++)
		{
			for (int j = 1; j<=nroNadadoresSinResto - 2; j ++)
			{
				if (j == 1)
					CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j);
				if (j > 1)
					CatalogoInscripcion.getInstance().cargarInscripciones(nadadoresEnCarreraOrdenadosPorTiempo.get(cont).getDni(), seriesEnCarrera.get(contSeries).getNroSerie(), j + 2);
				cont += 1;
			}
			contSeries += 1;
		}
	}
	//Fin de series e inscripciones




}
