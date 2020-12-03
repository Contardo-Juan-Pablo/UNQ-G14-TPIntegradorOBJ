package app;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

public abstract class UpdateHour {
	protected  SimpleEntry<Integer, Integer> cantidadDeHorasEstacionamientoAutomatico = new AbstractMap.SimpleEntry<Integer, Integer>(1, 1);
	
	public UpdateHour() {}
	
	public  void actualizarHoraEstacionamientoAutomatico() {
		AbstractMap.SimpleEntry<Integer, Integer> nuevo = new AbstractMap.SimpleEntry<Integer, Integer>(cantidadDeHorasEstacionamientoAutomatico.getKey() + 1, cantidadDeHorasEstacionamientoAutomatico.getValue());
		cantidadDeHorasEstacionamientoAutomatico = nuevo;
	}
	
	public  void resetearContadorDeHorasEstacionamientoAutomatico() {
		cantidadDeHorasEstacionamientoAutomatico = new AbstractMap.SimpleEntry<Integer, Integer>(1,1);
	}
	
	public  boolean excedioLaCantidadDeHorasMaxima() {
		return cantidadDeHorasEstacionamientoAutomatico.getKey() == cantidadDeHorasEstacionamientoAutomatico.getValue();
	}
	
	public  int getHoraDeEstacionamientoActual() {
		return cantidadDeHorasEstacionamientoAutomatico.getKey();
	}

	/** PARA FUTURAS IMPLEMENTACIONES ESTO SE ACTUALIZARIA CADA UNA HORA UNA VEZ QUE ES LLAMADO **/
	protected void emepezarServicio() {
		actualizarHoraEstacionamientoAutomatico();
	};
}
