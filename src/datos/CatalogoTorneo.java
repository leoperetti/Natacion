package datos;

import java.util.ArrayList;

import entidades.Torneo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.DataConnection;

public class CatalogoTorneo 
{

	private static CatalogoTorneo instance = null;
	public CatalogoTorneo() 
	{
   
	}
	public static CatalogoTorneo getInstance()
	{
		if(instance == null) 
		{
			instance = new CatalogoTorneo();
		}
		return instance;
	}
	
	public ArrayList<Torneo> buscarTorneosPorPrograma(int nroPrograma)
	{
		ArrayList<Torneo> listaTorneos = new ArrayList<Torneo>();
		
		String sql="select * from torneo where nroPrograma = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try 
		{			
			sentencia= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroPrograma);
			rs= sentencia.executeQuery();
			
			while(rs.next())
			{
				Torneo t = new Torneo();
				t.setNroTorneo(rs.getInt("nroTorneo"));
				t.setNroClub(rs.getInt("nroClub"));
				t.setFecha(rs.getString("fechaTorneo"));
				t.setNroPrograma(rs.getInt("nroPrograma"));
				listaTorneos.add(t);
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
	
	return listaTorneos;
	}

	public ArrayList<Torneo> buscarTorneos() {
		
		ArrayList<Torneo> listaTor = new ArrayList<Torneo>();
		String sql = "SELECT * FROM torneo;";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try{
			
		sentencia = DataConnection.getInstancia().getConn().createStatement();
		rs = sentencia.executeQuery(sql);
		
		while(rs.next())
		{
			Torneo t = new Torneo();
			t.setNroTorneo(rs.getInt("nroTorneo"));
			t.setNroClub(rs.getInt("nroClub"));
			t.setFecha(rs.getString("fechaTorneo"));
			t.setNroPrograma(rs.getInt("nroPrograma"));
			listaTor.add(t);
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
		
		return listaTor;
		
	}
	
	
	public int buscarUltimoNumeroDeTorneo() 
	{
		int nroTorneoMaximo = 0;
		String sql = "SELECT MAX(nroTorneo) FROM torneo;";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try
		{
			
			sentencia = DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
		
		if(rs.next())
		{
			nroTorneoMaximo = rs.getInt(1);
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
		
		return nroTorneoMaximo;
		
	}
	
	public void cargarTorneo(int nroTorneo, int nroPrograma, int club, String fecha)
	{
			
		String sql = "INSERT INTO Torneo (nroTorneo, nroPrograma, clubAnfitrion, fechaTorneo, localidad) VALUES(?,?,?,?,?);";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroTorneo);
			sentencia.setInt(2, nroPrograma);
			sentencia.setInt(3, club);
			sentencia.setString(4, fecha);
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
	public void eliminarTorneo(int nroTorneo) 
	{
		String sql = "DELETE FROM Torneo WHERE nroTorneo = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroTorneo);
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
	public Torneo buscarTorneoPorNroTorneo(int nroTorneo) 
	{
		Torneo t = new Torneo();
		String sql="select * from torneo where nroTorneo = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try 
		{			
			sentencia= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroTorneo);
			rs= sentencia.executeQuery();
			
			if(rs.next())
			{
				t.setNroTorneo(rs.getInt("nroTorneo"));
				t.setNroClub(rs.getInt("nroClub"));
				t.setFecha(rs.getString("fechaTorneo"));
				t.setNroPrograma(rs.getInt("nroPrograma"));
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
	
	return t;
	}
	public void modificarTorneo(int nroPrograma, String fechaTorneo, String clubAnfitrion, String localidad, int nroTorneo) 
	{
		String sql = "UPDATE Torneo set nroPrograma = ?, fechaTorneo = ? , clubAnfitrion = ?, localidad = ? where nroTorneo = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroPrograma);
			sentencia.setString(2, fechaTorneo);
			sentencia.setString(3, clubAnfitrion);
			sentencia.setString(4, localidad);
			sentencia.setInt(5, nroTorneo);
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
