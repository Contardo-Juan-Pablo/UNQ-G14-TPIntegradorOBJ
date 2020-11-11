package testPersona;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import App.AppSEMInspector;
import App.CelularInspector;
import Estacionamiento.EstacionamientoCompraFisica;
import Persona.Inspector;
import SEM.SEM;

public class testInspector {
	private SEM semTest;
	private Inspector inspector;
	
	@BeforeEach
	public void setUp() { 
		semTest = new SEM();
		semTest.setHoraActual(9);
		AppSEMInspector appInspector = new AppSEMInspector(11);
		CelularInspector celular = new CelularInspector(1122334455, appInspector);
		inspector = new Inspector(celular, 1);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testEnviarInfraccionViaAppVacio(String patente,SEM semActual) {
		assertEquals(0,semActual.getInfraccionesLabradas().size());
	}
	
	public void testEnviarInfraccionViaAppCargado(String patente,SEM semActual) {
		EstacionamientoCompraFisica estacionamiento = new EstacionamientoCompraFisica(patente, 9, 13, 4);
		semActual.setEstacionamiento(estacionamiento);
		inspector.enviarInfraccionViaApp(patente, semActual);
		assertEquals(1,semActual.getInfraccionesLabradas().size());
	}
	
	public void testEnviarInfraccionViaAppVerdadero(String patente,SEM semActual) {
		EstacionamientoCompraFisica estacionamiento = new EstacionamientoCompraFisica(patente, 9, 13, 4);
		semActual.setEstacionamiento(estacionamiento);
		assertEquals(true,inspector.verificarPatenteEstacionamientoViaApp(patente, semActual));
	}
	
	public void testEnviarInfraccionViaAppVerdaderoFalso(String patente,SEM semActual) {
		assertEquals(false,inspector.verificarPatenteEstacionamientoViaApp(patente, semActual));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void maintestEnviarInfraccionViaAppVacio() {
		testEnviarInfraccionViaAppVacio("AA - 123 - CC", semTest);
	}
	
	@Test
	void maintestEnviarInfraccionViaAppCargado() {
		testEnviarInfraccionViaAppCargado("AA - 123 - CC", semTest);
	}
	
	@Test
	void maintestEnviarInfraccionViaAppVerdadero() {
		testEnviarInfraccionViaAppVerdadero("AA - 123 - CC", semTest);
	}
	
	@Test
	void maintestEnviarInfraccionViaAppVerdaderoFalso() {
		testEnviarInfraccionViaAppVerdaderoFalso("AA - 123 - CC", semTest);
	}
	
}
