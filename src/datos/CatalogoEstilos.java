package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.DataConnection;
import entidades.Estilo;

public class CatalogoEstilos {

	 private static CatalogoEstilos instance = null;
	  public CatalogoEstilos() {
	   
	  }
	   public static CatalogoEstilos getInstance() {
	      if(instance == null) {
	         instance = new CatalogoEstilos();
	      }
	      return instance;
	   }
	public ArrayList<Estilo> traerLosEstilos() {
		
		ArrayList<Estilo> listaEstilos = new ArrayList<Estilo>();
		String sql = "SELECT * FROM estilo;";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try{
			
		sentencia = DataConnection.getInstancia().getConn().createStatement();
		rs = sentencia.executeQuery(sql);
		
		while(rs.next())
		{
			Estilo e = new Estilo();
			e.setNombreEstilo(rs.getString("descripcion"));
			e.setNroEstilo(rs.getInt("nroEstilo"));
			listaEstilos.add(e);
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
		
		
		
		return listaEstilos;
	}
	public int buscarUltimoNumeroEstilo()
	{
		int nroEstiloMaximo = 0;
		String sql = "SELECT MAX(nroEstilo) FROM Estilo";
		Statement sentencia = null;
		ResultSet rs = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			if(rs.next())
			{
				nroEstiloMaximo = rs.getInt(1);
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
		
		return nroEstiloMaximo;
	}
	public void cargarEstilo(int nroEstilo, String descripcion) 
	{
		String sql = "INSERT INTO Estilo VALUES(?,?)";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroEstilo);
			sentencia.setString(2, descripcion);
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
