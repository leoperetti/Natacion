package datos;

import conexion.DataConnection;
import entidades.Nadador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class CatalogoNadador {
	
	private static CatalogoNadador instance = null;
	public CatalogoNadador() 
	{
	   
	}
	public static CatalogoNadador getInstance() 
	{
		if(instance == null)
		{
			instance = new CatalogoNadador();
		}
		return instance;
	}
	

	   
	   public ArrayList<Nadador> buscarTodosNadadores()
	   {
		   ArrayList<Nadador> listaNadadores = new ArrayList<Nadador>();
		   String sql = "select * from nadador" ;
		   Statement sentencia=null;
		   ResultSet rs=null;
	
		try
		{
			sentencia=DataConnection.getInstancia().getConn().createStatement();
			rs = sentencia.executeQuery(sql);
			while(rs.next())
			{					
				Nadador nadador = new Nadador();
				nadador.setDni(rs.getInt("dni"));
				nadador.setNombre(rs.getString("nombre"));
				nadador.setApellido(rs.getString("apellido"));
				nadador.setEdad(rs.getInt("edad"));
				nadador.setNroClub(rs.getInt("nroClub"));
				nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
				nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
				nadador.setSexo(rs.getString("sexo").charAt(0));
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
	
	public void eliminarNadador(int dni) 
	{	
		String sql = "DELETE FROM nadador WHERE dni=?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, dni);
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
	   
	public void cargarNadador(int dni, String nombre, String apellido, String club, int edad, String tiempo1, String tiempo2, char sexo) {
		
	
		String sql;
		PreparedStatement sentencia=null;
		Connection con = DataConnection.getInstancia().getConn();
		
	try{
		sql = "INSERT INTO nadador (`dni`,`nombre`,`apellido`,`nombreClub`,`edad`,`tiempoPreCompeticion1`,`tiempoPreCompeticion2`, `sexo`) VALUES(?,?,?,?,?,?,?,?)";
		sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		sentencia.setInt(1, dni);
		sentencia.setString(2, nombre);
		sentencia.setString(3, apellido);
		sentencia.setString(4, club);
		sentencia.setInt(5, edad);
		sentencia.setString(6, tiempo1);
		sentencia.setString(7, tiempo2);
		sentencia.setString(8, String.valueOf(sexo));
		
		
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
			sql = "SELECT * FROM nadador "
					+ "where edad = ? and dni not in "
					+ "(SELECT n.dni FROM preinscripcion nc  "
					+ "inner join nadador n "
					+ "on n.dni = nc.dni "
					+ "where nroCarrera = ?) and sexo = (select genero from carrera c where nroCarrera = ?)";
		}
		else
		{
			sql = "SELECT * FROM nadador "
					+ "where edad > 18 and dni not in "
					+ "(SELECT n.dni FROM preinscripcion nc  "
					+ "inner join nadador n "
					+ "on n.dni = nc.dni "
					+ "where nroCarrera = ?) and sexo = (select genero from carrera c where nroCarrera = ?)";
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
				sentencia.setInt(3, nroCarrera);
			}
			else
			{
				sentencia.setInt(1, nroCarrera);
				sentencia.setInt(2, nroCarrera);
			}
			rs = sentencia.executeQuery();
			
				while(rs.next())
				{					
					Nadador nadador = new Nadador();
					nadador.setDni(rs.getInt("dni"));
					nadador.setNombre(rs.getString("nombre"));
					nadador.setApellido(rs.getString("apellido"));
					nadador.setEdad(rs.getInt("edad"));
					nadador.setNroClub(rs.getInt("nroClub"));
					nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
					nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
					nadador.setSexo(rs.getString("sexo").charAt(0));
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
	
	public Nadador buscarNadadorPorDni(int dni)
	{
		Nadador nadador = new Nadador();
		String sql = "select * from nadador where dni like ?" ;// like '?%'";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, Integer.toString(dni) + "%");
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{					
				nadador.setDni(rs.getInt("dni"));
				nadador.setNombre(rs.getString("nombre"));
				nadador.setApellido(rs.getString("apellido"));
				nadador.setEdad(rs.getInt("edad"));
				nadador.setNroClub(rs.getInt("nroClub"));
				nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
				nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
				nadador.setSexo(rs.getString("sexo").charAt(0));
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
		return nadador;
	}
	
	public ArrayList<Nadador> buscarMuchosNadadorPorDni(int dni)
	{
		ArrayList <Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql = "select * from nadador where dni like ?" ;// like '?%'";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, Integer.toString(dni) + "%");
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{				
				Nadador nadador = new Nadador();
				nadador.setDni(rs.getInt("dni"));
				nadador.setNombre(rs.getString("nombre"));
				nadador.setApellido(rs.getString("apellido"));
				nadador.setEdad(rs.getInt("edad"));
				nadador.setNroClub(rs.getInt("nroClub"));
				nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
				nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
				nadador.setSexo(rs.getString("sexo").charAt(0));
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
	public ArrayList<Nadador> buscarMuchosNadadoresPorNombreYApellido(String text) 
	{
		ArrayList <Nadador> listaNadadores = new ArrayList<Nadador>();
		String sql = "select * from nadador where (nombre || ' ' || apellido) like ?";
		String sql2 = "select * from nadador where (apellido || ' ' || nombre) like ?";
		PreparedStatement sentencia=null, sentencia2=null;
		ResultSet rs=null, rs2=null;
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia2=con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, text + "%");
			sentencia2.setString(1, text + "%");
			rs = sentencia.executeQuery();
			rs2 = sentencia2.executeQuery();
			
			while(rs.next())
			{				
				Nadador nadador = new Nadador();
				nadador.setDni(rs.getInt("dni"));
				nadador.setNombre(rs.getString("nombre"));
				nadador.setApellido(rs.getString("apellido"));
				nadador.setEdad(rs.getInt("edad"));
				nadador.setNroClub(rs.getInt("nroClub"));
				nadador.setTiempoPreCompetencia1(rs.getString("tiempoPreCompeticion1"));
				nadador.setTiempoPreCompetencia2(rs.getString("tiempoPreCompeticion2"));
				nadador.setSexo(rs.getString("sexo").charAt(0));
				listaNadadores.add(nadador);
			}
			while(rs2.next())
			{		
				Nadador nadador = new Nadador();
				nadador.setDni(rs2.getInt("dni"));
				nadador.setNombre(rs2.getString("nombre"));
				nadador.setApellido(rs2.getString("apellido"));
				nadador.setEdad(rs2.getInt("edad"));
				nadador.setNroClub(rs.getInt("nroClub"));
				nadador.setTiempoPreCompetencia1(rs2.getString("tiempoPreCompeticion1"));
				nadador.setTiempoPreCompetencia2(rs2.getString("tiempoPreCompeticion2"));
				nadador.setSexo(rs2.getString("sexo").charAt(0));
				boolean repetido = false;
				for(Nadador n : listaNadadores)
				{
					if (n.getDni() == nadador.getDni())
					{
						repetido = true;
					}
				}
				if (!repetido)
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
	
	public void modificarNadador(String nombre, String apellido, String club, int edad, String tiempo1, String tiempo2, char sexo , int dni)
	{
		String sql = "UPDATE Nadador set nombre = ?, apellido = ?, nombreClub = ?, edad = ?, tiempoPreCompeticion1 = ?, tiempoPreCompeticion2 = ?, sexo = ? where dni = ?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellido);
			sentencia.setString(3, club);
			sentencia.setInt(4, edad);
			sentencia.setString(5, tiempo1);
			sentencia.setString(6, tiempo2);
			sentencia.setString(7, Character.toString(sexo));
			sentencia.setInt(8, dni);
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
