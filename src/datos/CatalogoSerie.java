package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.Serie;

public class CatalogoSerie {

	private static CatalogoSerie instance = null;
	  public CatalogoSerie() {
	   
	  }
	   public static CatalogoSerie getInstance() {
	      if(instance == null) {
	         instance = new CatalogoSerie();
	      }
	      return instance;
	   }
	/*public void generarSerie(int nro) {
		
		
		String sql;
		PreparedStatement sentencia=null;
		Connection con = DataConnection.getInstancia().getConn();
		
	try{
		sql = "INSERT INTO `natacion`.`nadadores` (`dni`,`nombre`,`apellido`,`nombreClub`,`edad`,`tiempoPreCompetencia`) VALUES(?,?,?,?,?,?)";
		sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		sentencia.setInt(1, dni);
		sentencia.setString(2, nombre);
		sentencia.setString(3, apellido);
		sentencia.setString(4, club);
		sentencia.setInt(5, edad);
		sentencia.setTime(6, tiempo);
		
		
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
		
	}*/
	public void insertarSerie(int nroCarrera) 
	{
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "INSERT INTO series (carrera) VALUES(?)";
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
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
	public ArrayList<Serie> buscarSeriesPorCarrera(int nroCarrera) 
	{
		ArrayList<Serie> listaSeries = new ArrayList<Serie>();
		String sql = "SELECT * FROM series where carrera = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			rs = sentencia.executeQuery();
			
				while(rs.next())
				{
					Serie s = new Serie();
					s.setNroSerie(rs.getInt("nroSerie"));
					s.setNroCarrera(rs.getInt("carrera"));
					listaSeries.add(s);
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
		
		return listaSeries;	
	}
	
}
