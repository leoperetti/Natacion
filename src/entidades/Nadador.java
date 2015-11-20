package entidades;

import java.sql.Time;;

public class Nadador {

	private String nombre, apellido, nombreClub;
	private int dni, edad;
	private Time tiempoPreCompetencia;
	
	@Override
	public String toString(){
		return dni + " - " + nombre + " " + apellido;
	}
	
	public Nadador(){
		
		
	}
	
	public Nadador(String nom, String ape, String nomC, int edad, Time tiempo){
		
		this.setNombre(nom);
		this.setApellido(ape);
		this.setNombreClub(nomC);
		this.setEdad(edad);
		this.setTiempoPreCompetencia(tiempo);
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombreClub() {
		return nombreClub;
	}
	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Time getTiempoPreCompetencia() {
		return tiempoPreCompetencia;
	}
	public void setTiempoPreCompetencia(Time tiempoPreCompetencia) {
		this.tiempoPreCompetencia = tiempoPreCompetencia;
	}
		
	
}
