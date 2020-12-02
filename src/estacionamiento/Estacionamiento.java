package estacionamiento;
import java.time.LocalDateTime;
import app.Modo;

public class Estacionamiento {	
	private String patente;
	@SuppressWarnings("unused")
	private int cantidadDeHorasReservadas;
	private Modo estadoDelEstacionamiento = Modo.ACTIVADO;
	
	public Estacionamiento(String patente, Integer cantidadDeHorasReservadas) {
		this.patente = patente;
		this.cantidadDeHorasReservadas = cantidadDeHorasReservadas; 
	}
	
	public Boolean estaActivo() {
		return estadoDelEstacionamiento == Modo.ACTIVADO;
	}
	
	public int getNumeroCelularOrigen() {
		return -1;
	}
	
	public Boolean estaDentroDeLaFranjaHoraria(Integer hora) { 
		return hora >= 7 && hora <= 20;
	}

	public Boolean esNumeroCelularBuscado(Integer numeroCelular) {
		return false;
	}
	
	public String getPatente() {
		return patente;	
	}
	
	public static int costoActualPorHoraEnFranjaHorario(int horasReservadas) {
		if(getHoraActual() <= 7) {															
			return 40 * (getHoraActual() + horasReservadas - 7);	
		} 
		
		if(getHoraActual() + horasReservadas >= 20) {										
			return 40 * (20 - getHoraActual());	
		} 	
		return 40 * horasReservadas;
	}

	public void finalizar() {
		estadoDelEstacionamiento = Modo.DESACTIVADO;
	}
	
	public static int getHoraActual() {
		return LocalDateTime.now().getHour();
	}

	public int getHorasReservadas() {
		return cantidadDeHorasReservadas;
	}
}