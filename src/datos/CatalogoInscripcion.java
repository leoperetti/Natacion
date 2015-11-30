package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.DataConnection;

public class CatalogoInscripcion 
{

	
	public void cargarInscripciones(int dniNadador, int nroSerie, int nroAndarivel) 
	{
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "INSERT INTO inscripcion (dni, nroSerie, nroAndarivel) VALUES(?, ?, ?)";
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, dniNadador);
			sentencia.setInt(2, nroSerie);
			sentencia.setInt(3, nroAndarivel);
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
