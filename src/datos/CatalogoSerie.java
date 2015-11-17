package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.DataConnection;

public class CatalogoSerie {

	private static CatalogoSerie instance = null;
	  public CatalogoSerie() {
	   
	  }
	   public static CatalogoSerie getInstance() {
	      if(instance == null) {
	         instance = new CatalogoSerie();
	      }
	      return instance;
	   }
	/*public void generarSerie(int nro) {
		
		
		String sql;
		PreparedStatement sentencia=null;
		Connection con = DataConnection.getInstancia().getConn();
		
	try{
		sql = "INSERT INTO `natacion`.`nadadores` (`dni`,`nombre`,`apellido`,`nombreClub`,`edad`,`tiempoPreCompetencia`) VALUES(?,?,?,?,?,?)";
		sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		sentencia.setInt(1, dni);
		sentencia.setString(2, nombre);
		sentencia.setString(3, apellido);
		sentencia.setString(4, club);
		sentencia.setInt(5, edad);
		sentencia.setTime(6, tiempo);
		
		
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
		
	}*/
	
}
