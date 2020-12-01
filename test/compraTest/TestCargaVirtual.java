package compraTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import Compra.CargaVirtual;

public class TestCargaVirtual {

	@Test
	public void getCarga() {
		CargaVirtual cargaV = new CargaVirtual(50, 11111);
		assertEquals(50, cargaV.getCarga());
	}
	
	@Test
	public void getCelular() {
		CargaVirtual cargaV = new CargaVirtual(50, 11111);
		assertEquals(11111, cargaV.getCelular());
	}
	
}