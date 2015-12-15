package datos;

import java.util.ArrayList;
import java.util.Date;

import entidades.Torneo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		
		String sql="select t.nroTorneo, t.nroClub, t.fechaTorneo, t.nroPrograma , c.nombre from torneo as t inner join Club as c on t.nroClub = c.nroClub where t.nroPrograma = ?";
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
				t.setNroTorneo(rs.getInt(1));
				t.setNroClub(rs.getInt(2));
				t.setFecha(rs.getString(3));
				t.setNroPrograma(rs.getInt(4));
				t.setNombreClub(rs.getString(5));
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
		String sql = "select t.nroTorneo, t.nroClub, t.fechaTorneo, t.nroPrograma , c.nombre from torneo as t inner join Club as c on t.nroClub = c.nroClub";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try{
			
		sentencia = DataConnection.getInstancia().getConn().createStatement();
		rs = sentencia.executeQuery(sql);
		
		while(rs.next())
		{
			Torneo t = new Torneo();
			t.setNroTorneo(rs.getInt(1));
			t.setNroClub(rs.getInt(2));
			t.setFecha(rs.getString(3));
			t.setNroPrograma(rs.getInt(4));
			t.setNombreClub(rs.getString(5));
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
			
		String sql = "INSERT INTO Torneo (nroTorneo, nroPrograma, nroClub, fechaTorneo) VALUES(?,?,?,?);";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
			String fechaLarga = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroTorneo);
			sentencia.setInt(2, nroPrograma);
			sentencia.setInt(3, club);
			sentencia.setString(4, fechaLarga);
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		String sql="SELECT t.nroTorneo, t.nroClub, t.fechaTorneo, t.nroPrograma, c.nombre FROM torneo as t inner join Club as c on t.nroClub = c.nroClub where t.nroTorneo = ?";
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
				t.setNroTorneo(rs.getInt(1));
				t.setNroClub(rs.getInt(2));
				t.setFecha(rs.getString(3));
				t.setNroPrograma(rs.getInt(4));
				t.setNombreClub(rs.getString(5));
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
	public void modificarTorneo(int nroPrograma, String fechaTorneo, int nroClub, int nroTorneo) 
	{
		String sql = "UPDATE Torneo set nroPrograma = ?, fechaTorneo = ? , nroClub = ? where nroTorneo = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTorneo);
			String fechaLarga = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroPrograma);
			sentencia.setString(2, fechaLarga);
			sentencia.setInt(3, nroClub);
			sentencia.setInt(4, nroTorneo);
			sentencia.executeUpdate();
		}
		catch(SQLException | ParseException e)
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
