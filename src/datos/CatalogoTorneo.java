package datos;

import java.util.ArrayList;

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
				SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");	
				Torneo t = new Torneo();
				t.setNroTorneo(rs.getInt("nroTorneo"));
				t.setClubAnfitrion(rs.getString("clubAnfitrion"));
				t.setFecha(fecha.parse(rs.getString("fechaTorneo")));
				t.setNroPrograma(rs.getInt("nroPrograma"));
				listaTorneos.add(t);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (ParseException e)
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
			SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");	
			Torneo t = new Torneo();
			t.setNroTorneo(rs.getInt("nroTorneo"));
			t.setClubAnfitrion(rs.getString("clubAnfitrion"));
			t.setFecha(fecha.parse(rs.getString("fechaTorneo")));
			t.setNroPrograma(rs.getInt("nroPrograma"));
			listaTor.add(t);
		}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
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
}
