package EspaciosFisicos;

import java.util.ArrayList;
import java.util.Calendar;

import App.AppSEM;
import Compra.CargaVirtual;
import Compra.Compra;
import Estacionamiento.Estacionamiento;
import SEM.SEM;

public class PuntoDeVenta {
	private ArrayList<Integer> n�meroDeControl;
	private Integer idPuntoDeVenta;
	private SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> n�meroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.n�meroDeControl = n�meroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	} 

	public void IngresarCompra(int cantidadDeHorasReservadas, String patente) {
		sem.registrarCompra(new Compra(this.getCodigoDeControl(),this, Calendar.getInstance(), cantidadDeHorasReservadas));
		sem.guardarEstacionamiento(new Estacionamiento(patente, cantidadDeHorasReservadas));
	}

	public void IngresarCarga(int numeroDeCelular, int montoDeRecarga, AppSEM appSEM) {
		sem.registrarCarga(new CargaVirtual(montoDeRecarga, numeroDeCelular), appSEM);
	}
	
	public String getCodigoDeControl() {
		return this.getId().toString() + this.lastControl().toString();
	}
	
	public Integer lastControl() {
		return Integer.valueOf(n�meroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}
}