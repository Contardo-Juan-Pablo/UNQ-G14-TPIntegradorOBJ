package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public class CompraFisica extends Compra {
	private int cantidadDeHorasCompradas;
	
	public CompraFisica(String códigoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		super(códigoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}