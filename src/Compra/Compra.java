package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class Compra {
	private String códigoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Calendar fechaYHoraCompra;
	private int cantidadDeHorasCompradas;
	
	
	public Compra(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, int cantidadDeHorasCompradas) {
		this.códigoDeControl = códigoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}