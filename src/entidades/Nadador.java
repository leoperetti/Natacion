package entidades;

import java.sql.Time;;

public class Nadador {

	private String nombre, apellido, nombreClub;
	private int dni, edad;
	private Time tiempoPreCompetencia1, tiempoPreCompetencia2;
	
	@Override
	public String toString(){
		return dni + " - " + nombre + " " + apellido;
	}
	
	public Nadador(){
		
		
	}
	
	public Nadador(String nom, String ape, String nomC, int edad, Time tiempo1, Time tiempo2){
		
		this.setNombre(nom);
		this.setApellido(ape);
		this.setNombreClub(nomC);
		this.setEdad(edad);
		this.setTiempoPreCompetencia1(tiempo1);
		this.setTiempoPreCompetencia2(tiempo2);
		
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

	public Time getTiempoPreCompetencia1() {
		return tiempoPreCompetencia1;
	}

	public void setTiempoPreCompetencia1(Time tiempoPreCompetencia1) {
		this.tiempoPreCompetencia1 = tiempoPreCompetencia1;
	}

	public Time getTiempoPreCompetencia2() {
		return tiempoPreCompetencia2;
	}

	public void setTiempoPreCompetencia2(Time tiempoPreCompetencia2) {
		this.tiempoPreCompetencia2 = tiempoPreCompetencia2;
	}
	
		
	
}
