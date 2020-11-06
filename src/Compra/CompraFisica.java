package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class CompraFisica extends Compra {
	private byte cantidadDeHorasCompradas;
	
	public CompraFisica(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		super(códigoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}