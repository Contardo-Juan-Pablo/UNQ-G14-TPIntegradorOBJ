package Estacionamiento;
import java.util.Calendar;

import EspaciosFisicos.Auto;

public abstract class Estacionamiento {
	private Auto automovilEstacionado;
	private Calendar horaDeInicio;
	private Calendar horaDeFinalizacion;
	
	public Boolean estaVigente() {
		Calendar localDate = Calendar.getInstance();
		return localDate.after(horaDeFinalizacion);
	}
	
	public Auto getAutomovilEstacionado() {
		return automovilEstacionado;
	}

	public Estacionamiento(Auto automovilEstacionado, Calendar horaDeInicio, Calendar horaDeFinalizacion) {
		this.automovilEstacionado = automovilEstacionado;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFinalizacion = horaDeFinalizacion;
	}
}