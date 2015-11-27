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
	public ArrayList<Carrera> traerCarrerasPorTorneo(int nroTorneo) {
		
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>();
		String sql = "SELECT * FROM carreras WHERE nroTorneo = ?;";
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
				car.setTipoCarrera(rs.getInt("tipoCarrera"));
				car.setMetros(rs.getInt("metros"));
				car.setSexo(String.valueOf(rs.getString("sexo")).charAt(0));
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
	
	
	public void cargarCarrera(int nroEst, int nro, int tipo, int metros, char sexo, int nroTor) {
		
		String sql = "INSERT INTO `natacion`.`carreras` (`nroEstilo`,`nroCarrera`,`tipoCarrera`,`metros`,`sexo`,`nroTorneo`) VALUES(?,?,?,?,?,?);";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroEst);
			sentencia.setInt(2, nro);
			sentencia.setInt(3, tipo);
			sentencia.setInt(4, metros);
			sentencia.setString(5,String.valueOf(sexo));
			sentencia.setInt(6, nroTor);
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
	   
	   
	 
	

