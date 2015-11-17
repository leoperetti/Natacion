package entidades;

public class Carrera {

	private int nroCarrera, tipoCarrera, metros;
	private char sexo;
	private Programa prg;
	
	//Cuando traigo la carrera desde la db, pongo un atributo nro programa? o tengo q instanciar un programa?
	@Override
	public String toString(){
		return "Carrera nro: "+this.nroCarrera+"  Edad: ("+this.tipoCarrera+")";
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

	public Programa getPrg() {
		return prg;
	}

	public void setPrg(Programa prg) {
		this.prg = prg;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
	
	
	
}
