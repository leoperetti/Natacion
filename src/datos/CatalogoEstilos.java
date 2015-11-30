package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.DataConnection;
import entidades.Estilo;

public class CatalogoEstilos {

	 private static CatalogoEstilos instance = null;
	  public CatalogoEstilos() {
	   
	  }
	   public static CatalogoEstilos getInstance() {
	      if(instance == null) {
	         instance = new CatalogoEstilos();
	      }
	      return instance;
	   }
	public ArrayList<Estilo> traerLosEstilos() {
		
		ArrayList<Estilo> listaEstilos = new ArrayList<Estilo>();
		String sql = "SELECT * FROM estilo;";
		Statement sentencia = null;
		ResultSet rs = null;
		
		try{
			
		sentencia = DataConnection.getInstancia().getConn().createStatement();
		rs = sentencia.executeQuery(sql);
		
		while(rs.next())
		{
			Estilo e = new Estilo();
			e.setNombreEstilo(rs.getString("descripcion"));
			e.setNroEstilo(rs.getInt("nroEstilo"));
			listaEstilos.add(e);
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
		
		
		
		return listaEstilos;
	}
	
}
