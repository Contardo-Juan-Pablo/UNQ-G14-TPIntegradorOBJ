package estacionamiento;

import java.time.LocalDateTime;

import app.Modo;

public abstract class EstacionamientoGeneral {
	protected String patente;
	protected LocalDateTime fechaYHoraInicio;
	protected Modo estadoDelEstacionamiento = Modo.ACTIVADO;
	
	public LocalDateTime getFechaMax(int cantidadDeHorasReservadas) {
		return fechaYHoraInicio.plusHours(cantidadDeHorasReservadas);
	}
	
	public LocalDateTime getFechaActual() {
		return LocalDateTime.now();
	}
	
	
	public void finalizar() {
		estadoDelEstacionamiento = Modo.DESACTIVADO;
	}
	
	public abstract void finalizarSiCumple(Boolean condicion);
	
	public Boolean estaActivo() {
		return estadoDelEstacionamiento == Modo.ACTIVADO;
	}
	
	public abstract boolean estaDentroDeFranjaHorariaComprada();
	
	public Boolean esPatenteBuscada(String patente) {
		return this.patente.equals(patente);
	}
	
	public String getPatente() {
		return patente;	
	}
	
	public static final int costoActualPorHoraEnFranjaHorario(int horasReservadas) {
		if(getHoraActual() <= 7) {															
			return 40 * Math.abs(getHoraActual() + horasReservadas - 7);	
		} 
		if(getHoraActual() + horasReservadas >= 20) {										
			return 40 * Math.abs(20 - getHoraActual());	
		} 	
		return 40 * horasReservadas;
	}
	
	public static int getHoraActual() {
		return LocalDateTime.now().getHour();
	}
	
	public abstract int getNumeroCelularOrigen();
	
	public abstract Boolean esNumeroCelularBuscado(Integer numeroCelular);
}
