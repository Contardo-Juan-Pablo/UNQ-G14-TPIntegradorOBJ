package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class CompraFisica extends Compra {
	private byte cantidadDeHorasCompradas;
	
	public CompraFisica(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		super(c�digoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}