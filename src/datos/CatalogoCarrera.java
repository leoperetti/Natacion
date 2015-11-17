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
		String sql = "select c.tipoCarrera from natacion.carreras c where c.nroCarrera ="+nro+";" ;
		Statement sentencia=null;
		ResultSet rs=null;
		int tipoCarr = 0; 
		
			
		try{
			sentencia=DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			
				if(rs.next())
				{
					tipoCarr = rs.getInt("c.tipoCarrera");
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
	public ArrayList<Carrera> traerCarrerasPorPrograma(int nroProg) {
		
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>();
		String sql = "SELECT * FROM carreras WHERE carreras.nroPrograma = "+nroProg+";";
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Carrera car = new Carrera();
				car.setNroCarrera(rs.getInt("nroCarrera"));
				car.setTipoCarrera(rs.getInt("tipoCarrera"));
				car.setMetros(rs.getInt("metros"));
				car.setSexo(String.valueOf(rs.getString("sexo")).charAt(0));
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
		
	 	
	}
	   
	   
	 
	

