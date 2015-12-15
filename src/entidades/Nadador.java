package entidades;

public class Nadador 
{

	private String nombre, apellido, fechaNacimiento;
	private int dni, nroClub;
	private char sexo;
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
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
	
	public Nadador(String nom, String ape, int nroC, String fecha, String tiempo1, String tiempo2){
		
		this.setNombre(nom);
		this.setApellido(ape);
		this.setNroClub(nroC);
		this.setFechaNacimiento(fecha);
		
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

		
	
}
