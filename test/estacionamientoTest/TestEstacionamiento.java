package estacionamientoTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import app.Modo;
import estacionamiento.Estacionamiento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;


public class TestEstacionamiento {
	EstacionamientoTestClass estacionamiento;
	
	@Before
	public void setup() {
		estacionamiento = new EstacionamientoTestClass("AA-12-BB", 4);
	}
	
	@Test
	public void getPatente() {
		assertEquals("AA-12-BB", estacionamiento.getPatente());
	}
	
	
	@Test
	public void preguntarPorCelular() {
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		when(estacionamiento.esNumeroCelularBuscado(11111)).thenCallRealMethod();
		
		assertFalse(estacionamiento.esNumeroCelularBuscado(11111));
	}
	
	@Test
	public void setFinalizar() {
		estacionamiento.finalizar();
		assertFalse(estacionamiento.estaActivo());
	}
	
	@Test
	public void getHoraActual() {
		int horaActual = LocalDateTime.now().getHour();
		try (MockedStatic<Estacionamiento> theMock = Mockito.mockStatic(Estacionamiento.class)) {
			  theMock.when(() -> Estacionamiento.getHoraActual()).thenCallRealMethod();
			  
			  assertEquals(horaActual, Estacionamiento.getHoraActual());
		}
	}
	
	@Test
	public void testCelularOrigen() {
		assertEquals(-1, estacionamiento.getNumeroCelularOrigen());
	}
	
	@Test
	public void testFinalizarCuandoEsVerdadero() {
		estacionamiento.finalizarSiCumple(true);
		assertTrue(estacionamiento.getEstadoDelEstacionamiento() == Modo.DESACTIVADO);
	}
	
	@Test
	public void testFinalizarCuandoEsFalso() {
		estacionamiento.finalizarSiCumple(false);
		assertTrue(estacionamiento.getEstadoDelEstacionamiento() != Modo.DESACTIVADO);
	}
	
	@Test
	public void testEstaDentroDeFranjaHorariaComprada() {
		assertTrue(estacionamiento.estaDentroDeFranjaHorariaComprada());
	}
	
	@Test
	public void testNoEstaDentroDeFranjaHorariaComprada() {
		estacionamiento.setHorasReservadas(1);
		assertFalse(estacionamiento.estaDentroDeFranjaHorariaComprada());
	}
	
	@Test
	public void testGetFechaActual() {
		Estacionamiento estacionamiento = new Estacionamiento("AA-12-BB", 0);
		assertEquals(LocalDateTime.now(), estacionamiento.getFechaActual());
	}
	
	@Test
	public void testGetFechaMax() {
		assertEquals(LocalDateTime.now(), estacionamiento.getFechaActual());
	}
}
