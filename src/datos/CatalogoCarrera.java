package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.*;



public class CatalogoCarrera {

	 private static CatalogoCarrera instance = null;
	  public CatalogoCarrera() {
	   
	  }
	   public static CatalogoCarrera getInstance() {
	      if(instance == null) {
	         instance = new CatalogoCarrera();
	      }
	      return instance;
	   }
	
	 //Traigo desde la base de datos la carrera que ingreso el usuario  
	 public int buscarCarrera(int nro) {
		String sql = "select c.edadCarrera from Carrera c where c.nroCarrera ="+nro+";" ;
		Statement sentencia=null;
		ResultSet rs=null;
		int tipoCarr = 0; 
			
		try{
			sentencia=DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			
				if(rs.next())
				{
					tipoCarr = rs.getInt("c.edadCarrera");
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
		
		return tipoCarr;
		
		}
	public ArrayList<Carrera> traerCarrerasPorTorneo(int nroTorneo) {
		
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>();
		String sql = "SELECT * FROM carrera WHERE nroTorneo = ?;";
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroTorneo);
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				Carrera car = new Carrera();
				car.setNroCarrera(rs.getInt("nroCarrera"));
				car.setTipoCarrera(rs.getInt("edadCarrera"));
				car.setMetros(rs.getInt("metros"));
				car.setSexo(String.valueOf(rs.getString("genero")).charAt(0));
				car.setNroTorneo(rs.getInt("nroTorneo"));
				listaCarreras.add(car);
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
		return listaCarreras;
	}
	
	
	public void cargarCarrera(int nroEstilo, int nroCarrera, int edadCarrera, int metros, char sexo, int nroTorneo) 
	{
		
		String sql = "INSERT INTO carrera (nroEstilo, nroCarrera, edadCarrera ,metros , genero, nroTorneo) VALUES(?,?,?,?,?,?);";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroEstilo);
			sentencia.setInt(2, nroCarrera);
			sentencia.setInt(3, edadCarrera);
			sentencia.setInt(4, metros);
			sentencia.setString(5,String.valueOf(sexo));
			sentencia.setInt(6, nroTorneo);
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
	
	public int buscarUltimoNroCarrera() 
	{
		int nroCarreraMaximo = 0;
		String sql = "SELECT MAX(nroCarrera) FROM Carrera";
		Statement sentencia = null;
		ResultSet rs = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			if(rs.next())
			{
				nroCarreraMaximo = rs.getInt(1);
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
		
		return nroCarreraMaximo;
	}
	public ArrayList<Carrera> buscarCarreras() 
	{
		ArrayList<Carrera> listaCarrera = new ArrayList<Carrera>();
		String sql = "SELECT * FROM Carrera;";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try
		{
			sentencia = DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Carrera c = new Carrera();
				c.setNroCarrera(rs.getInt("nroCarrera"));
				c.setNroEstilo(rs.getInt("nroEstilo"));
				c.setMetros(rs.getInt("metros"));
				c.setNroTorneo(rs.getInt("nroTorneo"));
				c.setSexo(rs.getString("genero").charAt(0));
				c.setTipoCarrera(rs.getInt("edadCarrera"));
				listaCarrera.add(c);
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
		return listaCarrera;
	}
	 	
}
	   
	   
	 
	

