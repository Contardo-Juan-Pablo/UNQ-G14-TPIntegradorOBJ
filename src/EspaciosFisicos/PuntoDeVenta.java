package EspaciosFisicos;

import java.util.ArrayList;
import Compra.CargaVirtual;
import Compra.CompraFisica;
import SEM.SEM;

public class PuntoDeVenta {
	ArrayList<Integer> n�meroDeControl;
	Integer id;
	
	public Integer lastControl() {
		return new Integer(n�meroDeControl.size());
	}

	public Integer getId() {
		return id;
	}

	public void IngresarCompra(CompraFisica compra) {
		SEM.registrarCompra(compra);
	}
}