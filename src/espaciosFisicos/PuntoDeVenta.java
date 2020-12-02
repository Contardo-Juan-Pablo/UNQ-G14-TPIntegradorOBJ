package espaciosFisicos;

import java.util.ArrayList;
import java.util.Calendar;

import app.AppSEM;
import compra.CargaVirtual;
import compra.Compra;
import estacionamiento.Estacionamiento;
import sem.SEM;

public class PuntoDeVenta {
	private ArrayList<Integer> n�meroDeControl;
	private Integer idPuntoDeVenta;
	private SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> n�meroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.n�meroDeControl = n�meroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	} 

	public void ingresarCompra(int cantidadDeHorasReservadas, String patente) {
		sem.registrarCompra(new Compra(this.getCodigoDeControl(),this, Calendar.getInstance(), cantidadDeHorasReservadas));
		sem.guardarEstacionamiento(new Estacionamiento(patente, cantidadDeHorasReservadas));
	}

	public void ingresarCarga(int numeroDeCelular, int montoDeRecarga, AppSEM appSEM) {
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