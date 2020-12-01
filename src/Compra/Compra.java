package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class Compra {
	private String c�digoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Calendar fechaYHoraCompra;
	private int cantidadDeHorasCompradas;
	
	
	public Compra(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, int cantidadDeHorasCompradas) {
		this.c�digoDeControl = c�digoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}