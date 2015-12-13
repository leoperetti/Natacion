package entidades;

public class Carrera {

	private int nroCarrera, tipoCarrera, metros, nroTorneo, nroEstilo;
	public int getNroEstilo() {
		return nroEstilo;
	}

	public void setNroEstilo(int nroEstilo) {
		this.nroEstilo = nroEstilo;
	}

	private char sexo;
	
	//Cuando traigo la carrera desde la db, pongo un atributo nro programa? o tengo q instanciar un programa?
	@Override
	public String toString(){
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


	public int getNroTorneo() {
		return nroTorneo;
	}

	public void setNroTorneo(int nroTorneo) {
		this.nroTorneo = nroTorneo;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
	
	
	
}
