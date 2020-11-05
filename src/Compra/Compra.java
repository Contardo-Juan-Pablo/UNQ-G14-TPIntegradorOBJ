package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public abstract class Compra {
	private String c�digoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Date fechaYHoraCompra;
	
	public Compra (String c�digoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra) {
		this.c�digoDeControl = c�digoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
	}
}