package EspaciosFisicos;

import java.util.ArrayList;

import Compra.CargaVirtual;
import Compra.CompraFisica;
import Estacionamiento.EstacionamientoCompraFisica;
import SEM.SEM;

public class PuntoDeVenta {
	ArrayList<Integer> n�meroDeControl;
	Integer idPuntoDeVenta;
	SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> n�meroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.n�meroDeControl = n�meroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	}

	public Integer lastControl() {
		return new Integer(n�meroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}

	public void IngresarCompra(CompraFisica compra, EstacionamientoCompraFisica estacionamiento) {
		sem.registrarCompra(compra);
		sem.guardarEstacionamiento(estacionamiento);
	}

	public void IngresarCarga(CargaVirtual carga) {
		sem.registrarCarga(carga);
	}
	
	public String getCodigoDeControl() {
		return this.getId().toString() + this.lastControl().toString();
	}
}