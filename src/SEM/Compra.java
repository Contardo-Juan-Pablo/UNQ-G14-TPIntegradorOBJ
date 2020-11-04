package SEM;

import java.util.Date;

import EspaciosFisicos.PuntoDeVenta;

abstract class Compra {
	private int numeroDeControl;
	private PuntoDeVenta puntoDeVenta;
	private Date fechaYHoraCompra;
	
	public Compra (int numeroDeControl,PuntoDeVenta puntoDeVenta,Date fechaYHoraCompra) {
		this.numeroDeControl = numeroDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
	}
}

class CompraMedianteCelular extends Compra {
	private int montoDeRecarga;
	private int numeroDeCelularCarga;
	
	
	public CompraMedianteCelular(int numeroDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra) {
		super(numeroDeControl, puntoDeVenta, fechaYHoraCompra);
	}
}

class CompraPuntual extends Compra {
	private int cantidadDeHorasCompradas;
	
	
	public CompraPuntual(int numeroDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra) {
		super(numeroDeControl, puntoDeVenta, fechaYHoraCompra);
	}

	
	
}
