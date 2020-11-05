package Compra;

import java.util.Date;
import EspaciosFisicos.PuntoDeVenta;

public class CargaVirtual extends Compra {
	private int montoDeRecarga;
	private char numeroCelularOrigen;
	
	public CargaVirtual(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Date fechaYHoraCompra,int montoDeRecarga, char numeroCelularOrigen) {
		super(c�digoDeControl, puntoDeVenta, fechaYHoraCompra);
		this.montoDeRecarga = montoDeRecarga;
		this.numeroCelularOrigen = numeroCelularOrigen;
	}
}