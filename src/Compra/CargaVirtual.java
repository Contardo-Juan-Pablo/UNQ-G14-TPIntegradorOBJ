package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public class CargaVirtual extends Compra {
	private int montoDeRecarga;
	private char numeroCelularOrigen;
	
	public CargaVirtual(String códigoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra,int montoDeRecarga, char numeroCelularOrigen) {
		super(códigoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.montoDeRecarga = montoDeRecarga;
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}