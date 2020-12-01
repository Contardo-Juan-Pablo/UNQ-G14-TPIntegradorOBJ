package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public abstract class Compra {
	private String códigoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Calendar fechaYHoraCompra;
	private byte cantidadDeHorasCompradas;
	
	public Compra(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		this.códigoDeControl = códigoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}