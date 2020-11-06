package Estacionamiento;
import java.util.Calendar;

class EstacionamientoCompraFisica extends Estacionamiento {
	private int cantidadDeHorasCompradas;
	
	public EstacionamientoCompraFisica(String patente, Calendar horaDeInicio,Calendar horaDeFinalizacion, int cantidadDeHorasCompradas) {
		super(patente, horaDeInicio, horaDeFinalizacion);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}