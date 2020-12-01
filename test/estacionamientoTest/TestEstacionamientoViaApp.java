package estacionamientoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import Estacionamiento.EstacionamientoViaApp;

public class TestEstacionamientoViaApp {
	EstacionamientoViaApp estacionamiento;
	
	@Before
	public void setup() {
		estacionamiento = new EstacionamientoViaApp("AA-12-BB", 3, 1111111);
	}
	
	@Test
	public void getPatente() {
		assertEquals("AA-12-BB", estacionamiento.getPatente());
	}
	
	@Test
	public void gerNumeroDeCelular() {
		assertEquals(1111111, estacionamiento.getNumeroCelularOrigen());
	}
	
	@Test
	public void esCelularBuscado() {
		assertTrue(estacionamiento.esNumeroCelularBuscado(1111111));
		assertFalse(estacionamiento.esNumeroCelularBuscado(12));
	}
}
