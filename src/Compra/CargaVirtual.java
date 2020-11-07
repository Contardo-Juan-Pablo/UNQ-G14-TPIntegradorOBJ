package Compra;

import java.util.Calendar;
import EspaciosFisicos.PuntoDeVenta;

public class CargaVirtual extends Compra {
	private int montoDeRecarga;
	private int celular;
	
	public CargaVirtual(String c�digoDeControl, PuntoDeVenta puntoDeVenta, Calendar fechaYHoraDeCarga,int montoDeRecarga, int numeroDeCelular) {
		super(c�digoDeControl, puntoDeVenta, fechaYHoraDeCarga);
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