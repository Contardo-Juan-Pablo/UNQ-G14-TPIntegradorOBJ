package estacionamiento;

public class EstacionamientoViaApp extends Estacionamiento {
	private int numeroCelularOrigen;
	
	public EstacionamientoViaApp(String patente, Integer numeroCelularOrigen) {
		super(patente, 1);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}

	public int getNumeroCelularOrigen() {
		return numeroCelularOrigen;
	}
	
	public Boolean esNumeroCelularBuscado(Integer numeroCelular) {
		return this.getNumeroCelularOrigen() == numeroCelular;
	}
}