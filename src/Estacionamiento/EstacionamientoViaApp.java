package Estacionamiento;

public class EstacionamientoViaApp extends Estacionamiento {
	private int numeroCelularOrigen;
	
	public EstacionamientoViaApp(String automovilEstacionado, int horaDeInicio, int horaDeFinalizacion, int numeroCelularOrigen) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}