package Estacionamiento;

import java.util.Calendar;

import EspaciosFisicos.Auto;

class EstacionamientoCompraFisica extends Estacionamiento {
	private int cantidadDeHorasCompradas;
	
	public EstacionamientoCompraFisica(Auto automovilEstacionado, Calendar horaDeInicio,Calendar horaDeFinalizacion, int cantidadDeHorasCompradas) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}