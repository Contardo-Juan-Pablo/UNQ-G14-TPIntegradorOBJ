package Estacionamiento;
import java.util.Calendar;

class EstacionamientoViaApp extends Estacionamiento {
	private char numeroCelularOrigen;
	
	public EstacionamientoViaApp(String automovilEstacionado, Calendar horaDeInicio,Calendar horaDeFinalizacion, char numeroCelularOrigen) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}