package Estacionamiento;
import java.util.Calendar;

public abstract class Estacionamiento {
	private String patente;
	private int horaDeInicio;
	private int horaDeFinalizacion;
	
	public Boolean estaVigente() {
		Calendar localDate = Calendar.getInstance();
		return localDate.after(horaDeFinalizacion);
	}
	
	public String getPatente() {
		return patente;
	}

	public Estacionamiento(String patente, int horaDeInicio, int horaDeFinalizacion) {
		this.patente = patente;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFinalizacion = horaDeFinalizacion;
	}
	
	//CONSULTAR CON LOS CHICOS
	
	public Boolean esNumeroCelularBuscado(int numeroCelular) {
		return false;
	}
}