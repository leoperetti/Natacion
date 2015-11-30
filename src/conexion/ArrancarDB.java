package conexion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrancarDB {

	public static void main(String[] args) 
	{
		Connection con = DataConnection.getInstancia().getConn();
		Statement sentencia = null;
		ResultSet res = null;
		String sql = ("select * from inscripcion");		
		try
		{
			sentencia = con.createStatement();
			res = sentencia.executeQuery(sql);
			if(res.next())
			{
				System.out.println(res.getInt(1));
				System.out.println(res.getInt(2));
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
	}

}
