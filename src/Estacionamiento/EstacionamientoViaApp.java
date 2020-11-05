package Estacionamiento;

import java.util.Calendar;

import EspaciosFisicos.Auto;

class EstacionamientoViaApp extends Estacionamiento {
	private char numeroCelularOrigen;
	
	public EstacionamientoViaApp(Auto automovilEstacionado, Calendar horaDeInicio,Calendar horaDeFinalizacion, char numeroCelularOrigen) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}