package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Torneo 
{
	private int nroTorneo, nroPrograma;
	private String clubAnfitrion, localidad, fecha;
	
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString()
	{
		return "Nro: "+this.nroTorneo+"  Se realiza en: "+this.clubAnfitrion+" - Fecha: "+ fecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
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

	public String getClubAnfitrion() {
		return clubAnfitrion;
	}

	public void setClubAnfitrion(String clubAnfitrion) {
		this.clubAnfitrion = clubAnfitrion;
	}
	
}
