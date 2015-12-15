package entidades;

public class Inscripcion 
{
	private int nroNadador, nroAndarivel, nroSerie;
	private String tiempoCompetencia, motivoDescalificacion;
	public String getTiempoCompetencia() {
		return tiempoCompetencia;
	}
	public void setTiempoCompetencia(String tiempoCompetencia) {
		this.tiempoCompetencia = tiempoCompetencia;
	}
	public String getMotivoDescalificacion() {
		return motivoDescalificacion;
	}
	public void setMotivoDescalificacion(String motivoDescalificacion) {
		this.motivoDescalificacion = motivoDescalificacion;
	}
	public int getNroNadador() {
		return nroNadador;
	}
	public void setNroNadador(int nroNadador) {
		this.nroNadador = nroNadador;
	}
	public int getNroAndarivel() {
		return nroAndarivel;
	}
	public void setNroAndarivel(int nroAndarivel) {
		this.nroAndarivel = nroAndarivel;
	}
	public int getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(int nroSerie) {
		this.nroSerie = nroSerie;
	}


}
