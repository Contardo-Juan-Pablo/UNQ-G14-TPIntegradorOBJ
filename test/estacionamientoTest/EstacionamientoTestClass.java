package estacionamientoTest;

import java.time.LocalDateTime;

import app.Modo;
import estacionamiento.Estacionamiento;

public class EstacionamientoTestClass extends Estacionamiento {

	public EstacionamientoTestClass(String patente, Integer cantidadDeHorasReservadas) {
		super(patente, cantidadDeHorasReservadas);
	}

	public Modo getEstadoDelEstacionamiento() {
		return estadoDelEstacionamiento;
	}
	
	@Override
	public LocalDateTime getFechaMax(int cantidadDeHorasReservadas) {
		LocalDateTime.now();
		return LocalDateTime.of(2020, 04, 07, 8 + cantidadDeHorasReservadas, 12);
	}
	
	@Override
	public LocalDateTime getFechaActual() {
		LocalDateTime.now();
		return LocalDateTime.of(2020, 04, 07, 12, 13);
	}

	public void setHorasReservadas(int cantidadDeHorasReservadas) {
		this.cantidadDeHorasReservadas = cantidadDeHorasReservadas;
	}
}