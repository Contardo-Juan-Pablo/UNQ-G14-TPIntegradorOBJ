package testEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class testEstacionamientoViaApp {
	
	private EstacionamientoViaApp estacionamientoViaApp;
	private SEM semTest;
	
	@BeforeEach
	public void setUp() throws Exception {
		/** Seteo las 8 horas en el SEM para el estacionamiento este vigente. **/
		estacionamientoViaApp = new EstacionamientoViaApp("ABC", 7, 20, 123456789);		
		semTest = new SEM();
		semTest.setHoraActual(8);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testGetPatente() {
		String patente = estacionamientoViaApp.getPatente();
		assertEquals(patente,"ABC");
	}
	
	public void testHoraDeInicio() {
		int horaInicio = estacionamientoViaApp.getHoraDeInicio();
		assertEquals(horaInicio,7);
	}

	public void testGetHoraDeFinalizacion() {
		int horaFin = estacionamientoViaApp.getHoraDeFinalizacion();
		assertEquals(horaFin, 20);
	}
	
	public void testEstaVigenteVerdadero() {
		Boolean bool = estacionamientoViaApp.estaVigente(semTest);
		assertEquals(true, bool);
	}
	
	public void testEstaVigenteFalso() {
		/** Seteo las 22 horas en el SEM para el estacionamiento no este mas vigente. **/
		semTest.setHoraActual(22);
		Boolean bool = estacionamientoViaApp.estaVigente(semTest);
		assertEquals(false, bool);
	}
	
	public void testGetNumeroCelularOrigen() {
		int celOrigen = estacionamientoViaApp.getNumeroCelularOrigen();
		assertEquals(123456789, celOrigen);
	}
	
	public void testEsNumeroCelularBuscado() {
		Boolean celBuscado = estacionamientoViaApp.esNumeroCelularBuscado(123456789);
		assertEquals(true, celBuscado);
	}
	
	public void testEsNumeroCelularBuscadoVerdadero(int numeroCelular) {
		assertEquals(estacionamientoViaApp.getNumeroCelularOrigen(), numeroCelular); 
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void mainTestGetPatente() {
		testGetPatente();
	}
	
	@Test
	void mainTestHoraDeInicio() {
		testHoraDeInicio();
	}
	
	@Test
	void mainTestGetHoraDeFinalizacion() {
		testGetHoraDeFinalizacion();
	}
	
	@Test
	void maintestEstaVigenteVerdadero() {
		testEstaVigenteVerdadero();
	}
	
	@Test
	void maintestEstaVigenteFalso() {
		testEstaVigenteFalso();
	}
	
	@Test
	void mainTestGetNumeroCelularOrigen() {
		testGetNumeroCelularOrigen();
	}
	
	@Test
	void mainTestEsNumeroCelularBuscado() {
		testEsNumeroCelularBuscado();
	}
	
	@Test
	void maintestEsNumeroCelularBuscadoVerdadero() {
		testEsNumeroCelularBuscadoVerdadero(123456789);
	}
	
}