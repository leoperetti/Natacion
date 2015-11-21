package entidades;

import java.util.Date;

public class Torneo 
{
	private Date fecha;
	private int nroTorneo, nroPrograma;
	
	@Override
	public String toString(){
		return "Torneo nro: "+this.nroTorneo+" - Fecha: "+this.fecha;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNroTorneo() {
		return nroTorneo;
	}
	public void setNroTorneo(int nroTorneo) {
		this.nroTorneo = nroTorneo;
	}
	public int getNroPrograma() {
		return nroPrograma;
	}
	public void setNroPrograma(int nroPrograma) {
		this.nroPrograma = nroPrograma;
	}

}
