package entidades;

public class Programa {

	private int nroPrograma;
	private String estilo1, estilo2;
	
	@Override
	public String toString(){
		return "Programa nro: "+this.nroPrograma+"  Estilos:"+this.estilo1+","+this.estilo2;
	}
	
	public Programa(){
		
		
	}
	
	
	public int getNroPrograma() {
		return nroPrograma;
	}
	public void setNroPrograma(int nroPrograma) {
		this.nroPrograma = nroPrograma;
	}
	public String getEstilo1() {
		return estilo1;
	}
	public void setEstilo1(String estilo1) {
		this.estilo1 = estilo1;
	}
	public String getEstilo2() {
		return estilo2;
	}
	public void setEstilo2(String estilo2) {
		this.estilo2 = estilo2;
	}
	
	
	
}
