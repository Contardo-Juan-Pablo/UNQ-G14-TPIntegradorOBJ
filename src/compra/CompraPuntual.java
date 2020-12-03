package compra;

import java.util.Calendar;
import espaciosFisicos.PuntoDeVenta;

public class CompraPuntual extends CompraGeneral{
	private int cantidadDeHorasCompradas;
	
	
	public CompraPuntual(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, int cantidadDeHorasCompradas) {
		this.códigoDeControl = códigoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}