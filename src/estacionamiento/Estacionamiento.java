package estacionamiento;
import java.time.LocalDateTime;

public class Estacionamiento extends EstacionamientoGeneral {	
	protected int cantidadDeHorasReservadas;
	
	public Estacionamiento(String patente, Integer cantidadDeHorasReservadas) {
		this.patente = patente;
		this.cantidadDeHorasReservadas = cantidadDeHorasReservadas; 
		this.fechaYHoraInicio = LocalDateTime.now();
	}

	public int getNumeroCelularOrigen() {
		return -1;
	}
	
	public Boolean esNumeroCelularBuscado(Integer numeroCelular) {
		return false;
	}

	@Override
	public boolean estaDentroDeFranjaHorariaComprada() {
		LocalDateTime fechaMax = fechaYHoraInicio.plusHours(cantidadDeHorasReservadas);
		LocalDateTime fechaActual = LocalDateTime.now();
		if(fechaMax.getHour() == fechaActual.getHour()) {
			return fechaMax.getMinute() < fechaActual.getMinute();
		} else {
			return false;
		}	
	}

	@Override
	public void finalizarSiCumple(Boolean condicion) {
		if(condicion) {
			finalizar();
		}
	}
}