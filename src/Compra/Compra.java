package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public abstract class Compra {
	private String códigoDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Date fechaYHoraCompra;
	
	public Compra (String códigoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra) {
		this.códigoDeControl = códigoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
	}
}