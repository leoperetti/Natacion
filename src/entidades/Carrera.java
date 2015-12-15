package entidades;

public class Carrera
{

	private int nroCarrera, tipoCarrera, metros, nroPrograma;
	String estilo;
	private char sexo;
	
	@Override
	public String toString()
	{
		return "Número: "+this.nroCarrera+"  Edad: ("+this.tipoCarrera+")" + " Sexo: "+this.sexo;
	}
	
	public Carrera(){
		
		
	}
	
	public int getNroCarrera() {
		return nroCarrera;
	}

	public void setNroCarrera(int nroCarrera) {
		this.nroCarrera = nroCarrera;
	}

	public int getTipoCarrera() {
		return tipoCarrera;
	}

	public void setTipoCarrera(int tipoCarrera) {
		this.tipoCarrera = tipoCarrera;
	}

	public int getMetros() {
		return metros;
	}

	public void setMetros(int metros) {
		this.metros = metros;
	}


	public int getNroPrograma() {
		return nroPrograma;
	}

	public void setNroPrograma(int nroPrograma) {
		this.nroPrograma = nroPrograma;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
	
	
	
}
