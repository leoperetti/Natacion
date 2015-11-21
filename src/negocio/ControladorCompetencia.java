package negocio;
import datos.*;
import entidades.Carrera;
import entidades.Nadador;
import entidades.Programa;
import entidades.Torneo;

import java.sql.Time;
import java.util.ArrayList;

public class ControladorCompetencia {
	
	private CatalogoNadador cn = new CatalogoNadador();
	private CatalogoSerie cs = new CatalogoSerie();
	private CatalogoCarrera cc = new CatalogoCarrera();
	private CnadadoresPorCarrera cnpc = new CnadadoresPorCarrera();
	private CatalogoPrograma cp = new CatalogoPrograma();
	private CatalogoTorneo ct = new CatalogoTorneo();

	public void cargarNadador(int dni, String nombre, String apellido, String club, int edad, Time tiempo) {
		
		 cn.cargarNadador(dni, nombre, apellido, club, edad, tiempo);
		
	}

	

/*	public boolean cargarNadadoresPorCarrera(int nro) {
		
		boolean resp = false;
		ArrayList<Integer> dniNadadores = new ArrayList<Integer>();
		int tipoCarrera = cc.buscarCarrera(nro);
		if(tipoCarrera != 0)
		{
				dniNadadores = cn.buscarDniNadadores(tipoCarrera);
				
				if(dniNadadores != null)
				{
					System.out.println(tipoCarrera);
					for(int i = 0; i <dniNadadores.size();i++)
					{
					System.out.println(dniNadadores.get(i));
					}
					cnpc.cargar(dniNadadores, nro);
					resp = true;
					return resp;
				}
				else
				{
					System.out.println("No hay nadadores en esa carrera");
					return resp;
				}
				
		}
		else
		{
			return resp;
		}
	} */
	
	public void generarSerie(int nro) {

	}

	public ArrayList<Torneo> buscarTorneosPorPrograma(int nroPrograma)
	{
		return ct.buscarTorneosPorPrograma(nroPrograma);
	}

	public ArrayList<Nadador> buscarNadadoresPorCarrera(int nroCarrera)
	{
		return cnpc.buscarNadadoresPorCarrera(nroCarrera);
	}

	public ArrayList<Nadador> traerTodosNadadores() 
	{
		return cn.buscarNadadores();
	}



	public ArrayList<Programa> traerLosProgramas() {
		
		return cp.buscarProgramas();
	}



	public ArrayList<Carrera> traerCarrerasPorTorneo(int nroTorneo) {
		
		return cc.traerCarrerasPorTorneo(nroTorneo);
	}

}
