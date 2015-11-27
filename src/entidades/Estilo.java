package entidades;

public class Estilo {
	
	private int nroEstilo;
	private String nombreEstilo;

	@Override
	public String toString(){
		return nroEstilo + " - " + nombreEstilo;
	}
	
	public Estilo(){
		
	}
	public int getNroEstilo() {
		return nroEstilo;
	}

	public void setNroEstilo(int nroEstilo) {
		this.nroEstilo = nroEstilo;
	}

	public String getNombreEstilo() {
		return nombreEstilo;
	}

	public void setNombreEstilo(String nombreEstilo) {
		this.nombreEstilo = nombreEstilo;
	}
	
	

}
