package SEM;

import java.time.LocalDateTime;
import EspaciosFisicos.PuntoDeVenta;

abstract class Compra {
	private int numeroDeControl;
	private PuntoDeVenta puntoDeVenta;
	private LocalDateTime fechaYHoraCompra;
	
	public Compra (int numeroDeControl,PuntoDeVenta puntoDeVenta,LocalDateTime fechaYHoraCompra) {
		this.numeroDeControl = numeroDeControl;
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHoraCompra = fechaYHoraCompra;
	}
}

class CompraMedianteCelular extends Compra {
	private int montoDeRecarga;
	private char numeroCelularOrigen;
	
	public CompraMedianteCelular(int numeroDeControl, PuntoDeVenta puntoDeVenta, LocalDateTime fechaYHoraCompra,int montoDeRecarga, char numeroCelularOrigens) {
		super(numeroDeControl, puntoDeVenta, fechaYHoraCompra);
		this.montoDeRecarga = montoDeRecarga;
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}

class CompraPuntual extends Compra {
	private int cantidadDeHorasCompradas;
	
	public CompraPuntual(int numeroDeControl, PuntoDeVenta puntoDeVenta, LocalDateTime fechaYHoraCompra, int cantidadDeHorasCompradas) {
		super(numeroDeControl, puntoDeVenta, fechaYHoraCompra);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
	}
}
