package Estacionamiento;
import SEM.SEM;

public abstract class Estacionamiento {	
	private String patente;
	private int horaDeInicio;
	private int horaDeFinalizacion;
	
	public Estacionamiento(String patente, int horaDeInicio, int horaDeFinalizacion) {
		this.patente = patente;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFinalizacion = horaDeFinalizacion;
	}
	
	public Boolean estaVigente(SEM sem) { 
		return sem.getHoraActual() >= 7 && sem.getHoraActual() <= 20;
	}
	
	public Boolean esNumeroCelularBuscado(int numeroCelular) {
		return false;
	}
	
	public int getHoraDeInicio() {
		return horaDeInicio;
	}
	
	public int getHoraDeFinalizacion() {
		return horaDeFinalizacion;
	}
	
	public String getPatente() {
		return patente;	
	}
}