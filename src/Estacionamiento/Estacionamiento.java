package Estacionamiento;
import java.util.Calendar;

public abstract class Estacionamiento {
	private String patente;
	private Calendar horaDeInicio;
	private Calendar horaDeFinalizacion;
	
	public Boolean estaVigente() {
		Calendar localDate = Calendar.getInstance();
		return localDate.after(horaDeFinalizacion);
	}
	
	public String getPatente() {
		return patente;
	}

	public Estacionamiento(String patente, Calendar horaDeInicio, Calendar horaDeFinalizacion) {
		this.patente = patente;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFinalizacion = horaDeFinalizacion;
	}
}