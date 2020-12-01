package Estacionamiento;
import java.util.Calendar;
import App.Modo;

public class Estacionamiento {	
	private String patente;
	private Integer cantidadDeHorasReservadas;
	private Modo estadoDelEstacionamiento;
	
	public Estacionamiento(String patente, Integer cantidadDeHorasReservadas) {
		this.patente = patente;
		this.cantidadDeHorasReservadas = cantidadDeHorasReservadas;
		this.estadoDelEstacionamiento = Modo.ACTIVADO; 
	}

	
	public Boolean estaActivo() {
		return estadoDelEstacionamiento.equals(Modo.ACTIVADO);
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
	
	public static Integer costoActualPorHoraEnFranjaHorario(int horaActual, int horasReservadas) {
		horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int contador = 0;
		for(int i = horaActual; i<=horaActual + horasReservadas; i++) {
			if(i > 7 && i < 20) {
				contador++;
			}			
		}
		return 40 * contador;
	}
}