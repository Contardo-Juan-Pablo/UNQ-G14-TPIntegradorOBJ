package EspaciosFisicos;
import java.time.LocalDateTime;

public abstract class Estacionamiento {
	private Auto automovilEstacionado;
	private LocalDateTime horaDeInicio;
	private LocalDateTime horaDeFinalizacion;
	
	public Boolean estaVigente() {
		LocalDateTime locaDate = LocalDateTime.now();
		return locaDate.getHour() < horaDeFinalizacion.getHour();
	}
	
	public Auto getAutomovilEstacionado() {
		return automovilEstacionado;
	}

	public Estacionamiento(Auto automovilEstacionado, LocalDateTime horaDeInicio, LocalDateTime horaDeFinalizacion) {
		this.automovilEstacionado = automovilEstacionado;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFinalizacion = horaDeFinalizacion;
	}
	
}

class EstacionamientoViaApp extends Estacionamiento {
	private char numeroCelularOrigen;
	
	public EstacionamientoViaApp(Auto automovilEstacionado, LocalDateTime horaDeInicio,LocalDateTime horaDeFinalizacion, char numeroCelularOrigen) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.numeroCelularOrigen = numeroCelularOrigen;
	}


}

class EstacionamientoCompraPuntual extends Estacionamiento {
	private int cantidadDeHorasCompradas;
	
	public EstacionamientoCompraPuntual(Auto automovilEstacionado, LocalDateTime horaDeInicio,LocalDateTime horaDeFinalizacion, int cantidadDeHorasCompradas) {
		super(automovilEstacionado, horaDeInicio, horaDeFinalizacion);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}