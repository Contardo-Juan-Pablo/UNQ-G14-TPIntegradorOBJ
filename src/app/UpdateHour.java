package app;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

public abstract class UpdateHour {
	protected  SimpleEntry<Integer, Integer> cantidadDeHorasEstacionamientoAutomatico = new AbstractMap.SimpleEntry<Integer, Integer>(1, 1);
	protected boolean estacionamientoActivo = false;
	
	public UpdateHour() {}
	
	public  void actualizarHoraEstacionamientoAutomatico() {
		AbstractMap.SimpleEntry<Integer, Integer> nuevo = new AbstractMap.SimpleEntry<Integer, Integer>(cantidadDeHorasEstacionamientoAutomatico.getKey() + 1, cantidadDeHorasEstacionamientoAutomatico.getValue());
		cantidadDeHorasEstacionamientoAutomatico = nuevo;
	}
	
	public  void resetearContadorDeHorasEstacionamientoAutomatico() {
		cantidadDeHorasEstacionamientoAutomatico = new AbstractMap.SimpleEntry<Integer, Integer>(1,1);
	}
	
	public boolean excedioLaCantidadDeHorasMaxima() {
		if(estacionamientoActivo) {
			return cantidadDeHorasEstacionamientoAutomatico.getKey() == cantidadDeHorasEstacionamientoAutomatico.getValue();
		} else {
			return false;
		}
	}
	
	public  int getHoraDeEstacionamientoActual() {
		return cantidadDeHorasEstacionamientoAutomatico.getKey();
	}
	
	/* PARA FUTURAS IMPLEMENTACIONES ESTO SE ACTUALIZARIA CADA 5 MINUTOS UNA VEZ QUE ES LLAMADO */
	protected void emepezarServicio() {
		estacionamientoActivo = true;
		actualizarHoraEstacionamientoAutomatico();
	};
	
	public void finalizarServicio() {
		estacionamientoActivo = false;
		actualizarHoraEstacionamientoAutomatico();
	};
	
}
