package datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import conexion.DataConnection;

public class CnadadoresPorCarrera {

	
	 private static CnadadoresPorCarrera instance = null;
	  public CnadadoresPorCarrera() {
	   
	  }
	   public static CnadadoresPorCarrera getInstance() {
	      if(instance == null) {
	         instance = new CnadadoresPorCarrera();
	      }
	      return instance;
	   }
	public void cargar(ArrayList<Integer> dniNadadores, int nro) {
		
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "INSERT INTO `natacion`.`nadadorporcarrera` (`nroCarrera`,`dniNadador`) VALUES(?,?);";
		
		try{
			for(int i = 0; i<dniNadadores.size();i++)
			{	
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nro);
			sentencia.setInt(2, dniNadadores.get(i));
			sentencia.executeUpdate();
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
