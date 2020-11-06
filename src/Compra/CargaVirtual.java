package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class CargaVirtual extends Compra {
	private int montoDeRecarga;
	private int numeroCelularOrigen;
	
	public CargaVirtual(String códigoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraDeCarga,int montoDeRecarga, int numeroCelular) {
		super(códigoDeControl, puntoDeVenta, fechaYHoraDeCarga);
		this.montoDeRecarga = montoDeRecarga;
		this.numeroCelularOrigen = numeroCelular;
	}
}