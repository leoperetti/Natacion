package entidades;

public class Serie {

	public int getNroCarrera() {
		return nroCarrera;
	}

	public void setNroCarrera(int nroCarrera) {
		this.nroCarrera = nroCarrera;
	}

	private int nroSerie, nroCarrera;
	
	public Serie(){
		
		
	}

	public Serie(int nroS){
		
		this.setNroSerie(nroS);
	}
	
	public int getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(int nroSerie) {
		this.nroSerie = nroSerie;
	}
	
	
}

