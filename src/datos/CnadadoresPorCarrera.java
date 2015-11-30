package datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.DataConnection;
import entidades.Nadador;

public class CnadadoresPorCarrera {

	
	 private static CnadadoresPorCarrera instance = null;
	  public CnadadoresPorCarrera() {
	   
	  }
	   public static CnadadoresPorCarrera getInstance() {
	      if(instance == null) {
	         instance = new CnadadoresPorCarrera();
	      }
	      return instance;
	   }
	public void cargarNadadorEnCarrera(Integer dniNadador, int nroCarrera) {
		
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "INSERT INTO preinscripcion (`nroCarrera`,`dni`) VALUES(?,?);";
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			sentencia.setInt(2, dniNadador);
			sentencia.executeUpdate();
			}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
	}
	
	
	public ArrayList<Nadador> buscarNadadoresPorCarrera(int nroCarrera) 
	{		
		ArrayList<Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql = "SELECT n.* FROM nadador n inner join preinscripcion nc on n.dni = nc.dni "
				+ "where nc.nroCarrera = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			rs = sentencia.executeQuery();
			
				while(rs.next())
				{					
					Nadador nadador = new Nadador();
					nadador.setDni(rs.getInt("dni"));
					nadador.setNombre(rs.getString("nombre"));
					nadador.setApellido(rs.getString("apellido"));
					nadador.setEdad(rs.getInt("edad"));
					nadador.setNombreClub(rs.getString("nombreClub"));
					nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
					nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
					nadador.setSexo(rs.getString("sexo").charAt(0));
					listaNadadores.add(nadador);
				}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
		
		return listaNadadores;
	}
	
	public int contarNadadoresEnCarrera(int nroCarrera) 
	{		
		int numeroNadadores = 0;
		String sql = "SELECT count(*) FROM preinscripcion where nroCarrera = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			rs = sentencia.executeQuery();
			
				if(rs.next())
				{
					numeroNadadores = rs.getInt(1);
				}
				
		}
		catch(SQLException e)
			{
			e.printStackTrace();
			}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
		
		return numeroNadadores;
	}
	public ArrayList<Nadador> buscarNadadoresPorCarreraOrdenadosPorTiempo(int nroCarrera) 
	{
		ArrayList<Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql;
		if (nroCarrera <=20)
		{
			sql = "SELECT n.* FROM nadador n inner join preinscripcion nc on n.dni = nc.dni "
					+ "where nc.nroCarrera = ? order by tiempoPreCompeticion1";
		}
		else
		{
			sql = "SELECT n.* FROM nadador n inner join preinscripcion nc on n.dni = nc.dni "
					+ "where nc.nroCarrera = ? order by tiempoPreCompeticion2";
		}
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			rs = sentencia.executeQuery();
			
				while(rs.next())
				{
					
					Nadador nadador = new Nadador();
					nadador.setDni(rs.getInt("dni"));
					nadador.setNombre(rs.getString("nombre"));
					nadador.setApellido(rs.getString("apellido"));
					nadador.setEdad(rs.getInt("edad"));
					nadador.setNombreClub(rs.getString("nombreClub"));
					nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
					nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
					nadador.setSexo(rs.getString("sexo").charAt(0));

					listaNadadores.add(nadador);
				}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
		
		return listaNadadores;
	}
	public void quitarNadadorDeCarrera(int dni, int nroCarrera) 
	{
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "delete from preinscripcion where nroCarrera = ? and dni = ?";
		
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			sentencia.setInt(2, dni);
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
	}	   
	   
	   
}
