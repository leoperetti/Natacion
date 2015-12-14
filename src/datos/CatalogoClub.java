package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.Club;

public class CatalogoClub {
	private static CatalogoClub instance = null;
	  public CatalogoClub() {
	   
	  }
	   public static CatalogoClub getInstance() {
	      if(instance == null) {
	         instance = new CatalogoClub();
	      }
	      return instance;
	   }
	   
	   public ArrayList<Club> buscarClubes() {
			
			ArrayList<Club> listaClubes = new ArrayList<Club>();
			String sql = "SELECT * FROM Club;";
			ResultSet rs = null;
			Connection con = DataConnection.getInstancia().getConn();
			Statement sentencia = null;
			
			try{
				sentencia = con.createStatement();
				rs = sentencia.executeQuery(sql);
				
				while(rs.next())
				{
					Club club = new Club();
					club.setNroClub(rs.getInt("nroClub"));
					club.setNombre(rs.getString("nombre"));
					club.setLocalidad(rs.getString("localidad"));
					listaClubes.add(club);
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
			
			return listaClubes;
		}
	   
	   
	   
	   
	   
	   
}
