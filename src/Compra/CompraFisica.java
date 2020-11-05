package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public class CompraFisica extends Compra {
	private int cantidadDeHorasCompradas;
	
	public CompraFisica(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		super(c�digoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}