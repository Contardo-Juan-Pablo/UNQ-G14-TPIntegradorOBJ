package EspaciosFisicos;

import java.util.ArrayList;

import Compra.CargaVirtual;
import Compra.CompraFisica;
import SEM.SEM;

public class PuntoDeVenta {
	ArrayList<Integer> númeroDeControl;
	Integer id;
	SEM sem;
	
	public Integer lastControl() {
		return new Integer(númeroDeControl.size());
	}

	public Integer getId() {
		return id;
	}

	public void IngresarCompra(CompraFisica compra) {
		sem.registrarCompra(compra);
	}

	public void IngresarCarga(CargaVirtual carga) {
		sem.registrarCarga(carga);
		
	}
	
}