package espaciosFisicos;

import java.util.ArrayList;
import app.AppSEM;
import compra.CargaVirtual;
import compra.Compra;
import estacionamiento.Estacionamiento;
import sem.SEM;

public class PuntoDeVenta {
	private ArrayList<Integer> númeroDeControl;
	private Integer idPuntoDeVenta;
	private SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> númeroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.númeroDeControl = númeroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	} 

	public void ingresarCompra(Compra compra, Estacionamiento estacionamiento) {
		sem.registrarCompra(compra);
		sem.guardarEstacionamiento(estacionamiento);
	}

	public void ingresarCarga(int numeroDeCelular, int montoDeRecarga, AppSEM appSEM) {
		sem.registrarCarga(new CargaVirtual(montoDeRecarga, numeroDeCelular), appSEM);
	}
	
	public String getCodigoDeControl() {
		return this.getId().toString() + this.lastControl().toString();
	}
	
	public Integer lastControl() {
		return Integer.valueOf(númeroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}
}