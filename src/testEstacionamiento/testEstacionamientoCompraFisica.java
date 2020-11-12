package testEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Estacionamiento.EstacionamientoCompraFisica;
import SEM.SEM;

public class testEstacionamientoCompraFisica {
	
	private EstacionamientoCompraFisica estacionamientoCompraFisica;
	
	@BeforeEach
	public void setUp() throws Exception {
		estacionamientoCompraFisica = new EstacionamientoCompraFisica("AA-12-ZZ", 10, 15, 5);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testEsNumeroCelularBuscado(int numeroCelular) {
		assertEquals(false, estacionamientoCompraFisica.esNumeroCelularBuscado(numeroCelular));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void mainTestGetPatente() {
		testEsNumeroCelularBuscado(11111111);
	}
}