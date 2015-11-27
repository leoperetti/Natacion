package entidades;

import java.sql.Time;

public class Inscripcion 
{
	private int nroNadador, nroAndarivel, nroSerie;
	private Time tiempoCompetencia;
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
	public Time getTiempoCompetencia() {
		return tiempoCompetencia;
	}
	public void setTiempoCompetencia(Time tiempoCompetencia) {
		this.tiempoCompetencia = tiempoCompetencia;
	}

}
