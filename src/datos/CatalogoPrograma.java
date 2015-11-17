package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import conexion.DataConnection;
import entidades.Nadador;
import entidades.Programa;

public class CatalogoPrograma {

	 private static CatalogoPrograma instance = null;
	  public CatalogoPrograma() {
	   
	  }
	   public static CatalogoPrograma getInstance() {
	      if(instance == null) {
	         instance = new CatalogoPrograma();
	      }
	      return instance;
	   }
	public ArrayList<Programa> buscarProgramas() {
		
		ArrayList<Programa> listaProgramas = new ArrayList<Programa>();
		String sql = "SELECT * FROM programas;";
		ResultSet rs = null;
		Connection con = DataConnection.getInstancia().getConn();
		PreparedStatement sentencia = null;
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = sentencia.executeQuery(sql);
			
			while(rs.next())
			{
				Programa prg = new Programa();
				prg.setNroPrograma(rs.getInt("nroPrograma"));
				prg.setEstilo1(rs.getString("estilo1"));
				prg.setEstilo2(rs.getString("estilo2"));
				listaProgramas.add(prg);
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
		
		return listaProgramas;
	}
	
}
