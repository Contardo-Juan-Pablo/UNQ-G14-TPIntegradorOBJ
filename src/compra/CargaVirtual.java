package compra;

import java.util.Calendar;

import espaciosFisicos.PuntoDeVenta;

public class CargaVirtual extends CompraGeneral{
	
	private int montoDeRecarga;
	private int celular;
	
	public CargaVirtual(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraCompra, int montoDeRecarga, int numeroDeCelular) {
		this.códigoDeControl = códigoDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
		this.montoDeRecarga = montoDeRecarga;
		this.celular = numeroDeCelular;
	}
	
	public int getCarga() {
		return montoDeRecarga;
	}

	public int getCelular() {
		return celular;
	}
}