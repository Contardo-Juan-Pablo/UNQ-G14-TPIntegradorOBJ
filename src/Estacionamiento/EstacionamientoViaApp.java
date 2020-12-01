package Estacionamiento;

public class EstacionamientoViaApp extends Estacionamiento {
	private int numeroCelularOrigen;
	
	public EstacionamientoViaApp(String patente, Integer cantidadDeHorasReservadas, Integer numeroCelularOrigen) {
		super(patente, cantidadDeHorasReservadas);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}

	public Integer getNumeroCelularOrigen() {
		return numeroCelularOrigen;
	}
	
	public Boolean esNumeroCelularBuscado(Integer numeroCelular) {
		return numeroCelularOrigen == numeroCelular;
	}
	
}