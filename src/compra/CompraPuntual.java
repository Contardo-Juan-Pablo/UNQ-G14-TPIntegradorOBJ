package compra;

import java.util.Calendar;
import espaciosFisicos.PuntoDeVenta;

public class CompraPuntual extends CompraGeneral{
	private int cantidadDeHorasCompradas;
	
	
	public CompraPuntual(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, int cantidadDeHorasCompradas) {
		this.c�digoDeControl = c�digoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}