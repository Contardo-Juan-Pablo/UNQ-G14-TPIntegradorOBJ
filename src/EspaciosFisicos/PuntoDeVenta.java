package EspaciosFisicos;

import java.util.ArrayList;
import Compra.CargaVirtual;
import Compra.Compra;
import Estacionamiento.Estacionamiento;
import SEM.SEM;

public class PuntoDeVenta {
	private ArrayList<Integer> númeroDeControl;
	private Integer idPuntoDeVenta;
	private SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> númeroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.númeroDeControl = númeroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	} 

	public Integer lastControl() {
		return Integer.valueOf(númeroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}

	public void IngresarCompra(Compra compra, Estacionamiento estacionamiento) {
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