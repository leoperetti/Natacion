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
				t.setFecha(rs.getDate("fechaTorneo"));
				t.setNroPrograma(rs.getInt("nroPrograma"));
				t.setNroTorneo(rs.getInt("nroTorneo"));
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
}
