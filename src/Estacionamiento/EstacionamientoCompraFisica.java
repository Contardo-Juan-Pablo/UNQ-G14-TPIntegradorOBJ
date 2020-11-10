package Estacionamiento;

public class EstacionamientoCompraFisica extends Estacionamiento {
	private int cantidadDeHorasCompradas;
	
	public EstacionamientoCompraFisica(String patente, int horaDeInicio, int horaDeFinalizacion, int cantidadDeHorasCompradas) {
		super(patente, horaDeInicio, horaDeFinalizacion);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}