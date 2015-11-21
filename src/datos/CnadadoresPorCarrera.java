package datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.DataConnection;
import entidades.Nadador;

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
	public void cargarNadadorEnCarrera(Integer dniNadador, int nroCarrera) {
		
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		String sql = "INSERT INTO `natacion`.`nadadorporcarrera` (`nroCarrera`,`dniNadador`) VALUES(?,?);";
		
		try{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			sentencia.setInt(2, dniNadador);
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
	
	
	public ArrayList<Nadador> buscarNadadoresPorCarrera(int nroCarrera) 
	{		
		ArrayList<Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql = "SELECT n.* FROM nadadores n inner join nadadorporcarrera nc on n.dni = nc.dniNadador "
				+ "where nc.nroCarrera = ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCarrera);
			rs = sentencia.executeQuery();
			
				while(rs.next())
				{
					Nadador nadador = new Nadador();
					nadador.setDni(rs.getInt("dni"));
					nadador.setNombre(rs.getString("nombre"));
					nadador.setApellido(rs.getString("apellido"));
					nadador.setEdad(rs.getInt("edad"));
					nadador.setNombreClub(rs.getString("nombreClub"));
					nadador.setTiempoPreCompetencia(rs.getTime("tiempoPreCompetencia"));
					listaNadadores.add(nadador);
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
		
		return listaNadadores;
	}
	
	   
	   
	   
}
