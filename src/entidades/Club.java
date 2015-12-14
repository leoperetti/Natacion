package entidades;

public class Club {
	private int NroClub;
	private String nombre, localidad;
	
	@Override
	public String toString()
	{
		return nombre + " - " + localidad; 
	}
	
	public int getNroClub() {
		return NroClub;
	}
	public void setNroClub(int nroClub) {
		NroClub = nroClub;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
