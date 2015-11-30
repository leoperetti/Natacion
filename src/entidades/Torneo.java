package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Torneo 
{
	private Date fecha;
	private int nroTorneo, nroPrograma;
	private String clubAnfitrion;
	
	@Override
	public String toString()
	{
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");	
		fecha.applyPattern("dd/MM/yyyy");
		return "Nro: "+this.nroTorneo+"  Se realiza en: "+this.clubAnfitrion+" - Fecha: "+fecha.format(this.fecha);
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

	public String getClubAnfitrion() {
		return clubAnfitrion;
	}

	public void setClubAnfitrion(String clubAnfitrion) {
		this.clubAnfitrion = clubAnfitrion;
	}
	
}
