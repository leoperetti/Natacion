package entidades;

public class Nadador {

	private String nombre, apellido;
	private int dni, edad, nroClub;
	private String tiempoPreCompetencia1, tiempoPreCompetencia2;
	private char sexo;
	
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString(){
		return dni + " - " + nombre + " " + apellido;
	}
	
	public Nadador(){
		
		
	}
	
	public Nadador(String nom, String ape, int nroC, int edad, String tiempo1, String tiempo2){
		
		this.setNombre(nom);
		this.setApellido(ape);
		this.setNroClub(nroC);
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
	public int getNroClub() {
		return nroClub;
	}
	public void setNroClub(int nroClub) {
		this.nroClub = nroClub;
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

	public String getTiempoPreCompetencia1() {
		return tiempoPreCompetencia1;
	}

	public void setTiempoPreCompetencia1(String tiempoPreCompetencia1) {
		this.tiempoPreCompetencia1 = tiempoPreCompetencia1;
	}

	public String getTiempoPreCompetencia2() {
		return tiempoPreCompetencia2;
	}

	public void setTiempoPreCompetencia2(String tiempoPreCompetencia2) {
		this.tiempoPreCompetencia2 = tiempoPreCompetencia2;
	}
	
		
	
}
