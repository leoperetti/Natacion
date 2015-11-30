package conexion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrancarDB 
{

	public static void main(String[] args) 
	{			
			String sql = "INSERT INTO Programa VALUES (6, 'Programa6')";
			PreparedStatement sentencia = null;
			Connection con = DataConnection.getInstancia().getConn();
			try
			{
				sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
