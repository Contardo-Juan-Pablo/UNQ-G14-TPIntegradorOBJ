package testEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Estacionamiento.EstacionamientoViaApp;

public class testEstacionamientoViaApp {
	
	private EstacionamientoViaApp estacionamientoViaApp;

	@BeforeEach
	public void setUp() throws Exception {
		estacionamientoViaApp = new EstacionamientoViaApp("ABC", 7, 20, 123456789);		
	}
	
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
	
	public void testEstaVigente() {
		Boolean bool = estacionamientoViaApp.estaVigente();
		assertEquals(bool, false);
	}
	
	public void testGetNumeroCelularOrigen() {
		int celOrigen = estacionamientoViaApp.getNumeroCelularOrigen();
		assertEquals(celOrigen, 123456789);
	}
	
	public void testEsNumeroCelularBuscado() {
		Boolean celBuscado = estacionamientoViaApp.esNumeroCelularBuscado(123456789);
		assertEquals(celBuscado, true);
	}
	
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
	void mainTestEstaVigente() {
		testEstaVigente();
	}
	@Test
	void mainTestGetNumeroCelularOrigen() {
		testGetNumeroCelularOrigen();
	}
	
	@Test
	void mainTestEsNumeroCelularBuscado() {
		testEsNumeroCelularBuscado();
	}
	
}