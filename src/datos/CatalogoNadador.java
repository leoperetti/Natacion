package datos;

import conexion.DataConnection;
import entidades.Nadador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;

public class CatalogoNadador {
	
	 private static CatalogoNadador instance = null;
	  public CatalogoNadador() {
	   
	  }
	   public static CatalogoNadador getInstance() {
	      if(instance == null) {
	         instance = new CatalogoNadador();
	      }
	      return instance;
	   }

	public void cargarNadador(int dni, String nombre, String apellido, String club, int edad, Time tiempo) {
		
		System.out.println("El tiempo es" + tiempo);
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
	public ArrayList<Nadador> buscarNadadores(int edad, int nroCarrera) {
		
		//CON ESTE CRITERIO:
		//TipoCarrera 6 -> Edad == 6
		//TipoCarrera 7 -> Edad == 7
		//...
		//...
		//TipoCarrera 17 -> Edad == 17
		//TipoCarrera 18 -> Edad >= 18
		
		ArrayList<Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql;
		if (edad < 18)
		{
			sql = "SELECT * FROM nadadores "
					+ "where edad = ? and dni not in "
					+ "(SELECT dni FROM nadadorporcarrera nc  "
					+ "inner join nadadores n "
					+ "on n.dni = nc.dniNadador "
					+ "where nroCarrera = ?)";
		}
		else
		{
			sql = "SELECT * FROM nadadores "
					+ "where edad > 18 and dni not in "
					+ "(SELECT dni FROM nadadorporcarrera nc  "
					+ "inner join nadadores n "
					+ "on n.dni = nc.dniNadador "
					+ "where nroCarrera = ?)";
		}
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (edad < 18)
			{
				sentencia.setInt(1, edad);
				sentencia.setInt(2, nroCarrera);
			}
			else
			{
				sentencia.setInt(1, nroCarrera);
			}
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
