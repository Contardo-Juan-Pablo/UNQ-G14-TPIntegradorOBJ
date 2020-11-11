package testPersona;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Estacionamiento.EstacionamientoCompraFisica;
import Persona.Operador;
import SEM.SEM;

public class testOperador {
	private SEM semTest;
	private Operador operador;
	
	@BeforeEach
	public void setUp() { 
		/** Se eligio las 21 horas, ya que el operador solo puede dar la baja de todos los estacionamientos siendo las 20 horas. **/
		   
		semTest = new SEM();
		operador = new Operador(123);
		semTest.setOperadorAsociado(operador);
		semTest.setHoraActual(21);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void testDarBajaTotalEstacionamientosCargado(SEM sem) {
		/** Se seteo directamente sobre la lista de estacionamientos y no usando guardarEstacionamiento(), porque siendo las 21 horas los estacionamientos ya no se guardían mas. **/
		EstacionamientoCompraFisica estacionamiento = new EstacionamientoCompraFisica("AAA - 123 - CC", 8, 9, 1);
		sem.setEstacionamiento(estacionamiento);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testDarBajaTotalEstacionamientosElimanado(SEM sem) {
		/** Se seteo directamente sobre la lista de estacionamientos y no usando guardarEstacionamiento(), porque siendo las 21 horas los estacionamientos ya no se guardían mas. **/
		EstacionamientoCompraFisica estacionamiento = new EstacionamientoCompraFisica("AAA - 123 - CC", 8, 9, 1);
		sem.setEstacionamiento(estacionamiento);
		operador.darBajaTotalEstacionamientos(sem);
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	void maintestDarBajaTotalEstacionamientosCargado() {
		testDarBajaTotalEstacionamientosCargado(semTest);
	}
	
	@Test
	void maintestDarBajaTotalEstacionamientosElimanado() {
		testDarBajaTotalEstacionamientosElimanado(semTest);
	}
}
