package EspaciosFisicos;

import java.util.ArrayList;

import Compra.CargaVirtual;
import Compra.CompraFisica;
import SEM.SEM;

public class PuntoDeVenta {
	ArrayList<Integer> n�meroDeControl;
	Integer idPuntoDeVenta;
	SEM sem;
	
	public Integer lastControl() {
		return new Integer(n�meroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}

	public void IngresarCompra(CompraFisica compra) {
		sem.registrarCompra(compra);
	}

	public void IngresarCarga(CargaVirtual carga) {
		sem.registrarCarga(carga);
	}
}