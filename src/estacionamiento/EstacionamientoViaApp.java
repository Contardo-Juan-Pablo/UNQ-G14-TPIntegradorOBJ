package estacionamiento;

import java.time.LocalDateTime;

public class EstacionamientoViaApp extends EstacionamientoGeneral {
	private int numeroCelularOrigen;
	
	public EstacionamientoViaApp(String patente, Integer numeroCelularOrigen) {
		this.patente = patente;
		this.numeroCelularOrigen = numeroCelularOrigen;
		this.fechaYHoraInicio = LocalDateTime.now();
	}

	public int getNumeroCelularOrigen() {
		return numeroCelularOrigen;
	}
	
	public Boolean esNumeroCelularBuscado(Integer numeroCelular) {
		return this.getNumeroCelularOrigen() == numeroCelular;
	}

	@Override
	public boolean estaDentroDeFranjaHorariaComprada() {
		return true;
	}

	@Override
	public void finalizarSiCumple(Boolean condicion) {}
}