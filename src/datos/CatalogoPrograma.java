package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.DataConnection;
import entidades.Programa;
public class CatalogoPrograma {

	private static CatalogoPrograma instance = null;
	public CatalogoPrograma() 
	{
   
	}
	public static CatalogoPrograma getInstance()
	{
		if(instance == null) 
		{
			instance = new CatalogoPrograma();
		}
		return instance;
	}
	public ArrayList<Programa> buscarProgramas() {
		
		ArrayList<Programa> listaProgramas = new ArrayList<Programa>();
		String sql = "SELECT * FROM programa;";
		ResultSet rs = null;
		Connection con = DataConnection.getInstancia().getConn();
		Statement sentencia = null;
		
		try{
			sentencia = con.createStatement();
			rs = sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Programa prg = new Programa();
				prg.setNroPrograma(rs.getInt("nroPrograma"));
				prg.setDescripcion(rs.getString("descripcion"));
				listaProgramas.add(prg);
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
		
		return listaProgramas;
	}
	public int buscarUltimoNumeroPrograma() 
	{
		int nroProgramaMaximo = 0;
		String sql = "SELECT MAX(nroPrograma) FROM Programa";
		Statement sentencia = null;
		ResultSet rs = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			if(rs.next())
			{
				nroProgramaMaximo = rs.getInt(1);
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
		
		return nroProgramaMaximo;
	}
	
	public void cargarPrograma(int nroPrograma, String descripcion)
	{
		String sql = "INSERT INTO Programa VALUES(?,?)";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroPrograma);
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
	public void eliminarPrograma(int nroProgramaActual) 
	{
		String sql = "DELETE FROM Programa WHERE nroPrograma = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroProgramaActual);
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
	public Programa buscarProgramaPorNroPrograma(int nroProgramaActual)
	{
		Programa p = new Programa();
		String sql="select * from programa where nroPrograma = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try 
		{			
			sentencia= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroProgramaActual);
			rs= sentencia.executeQuery();
			
			if(rs.next())
			{
				p.setDescripcion(rs.getString("descripcion"));
				p.setNroPrograma(rs.getInt("nroPrograma"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
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
	
	return p;
	}
	public void modificarPrograma(String descripcion, int nroPrograma) 
	{
		String sql = "UPDATE Programa set descripcion = ? where nroPrograma = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, descripcion);
			sentencia.setInt(2, nroPrograma);
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
