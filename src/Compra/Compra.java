package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public abstract class Compra {
	private String c�digoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Calendar fechaYHoraCompra;
	private byte cantidadDeHorasCompradas;
	
	public Compra(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, byte cantidadDeHorasCompradas) {
		this.c�digoDeControl = c�digoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}