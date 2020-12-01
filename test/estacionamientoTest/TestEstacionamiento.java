package estacionamientoTest;

import org.junit.Test;
import Estacionamiento.Estacionamiento;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestEstacionamiento {
	
	
	@Test
	public void getPatente() {
		Estacionamiento estacionamiento = new Estacionamiento("AA-12-BB", 4);
		
		assertEquals("AA-12-BB", estacionamiento.getPatente());
	}
	
	
	
	@Test
	public void esUnEstacionamientoDentroDeFranjaHoraria() {
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		when(estacionamiento.estaDentroDeLaFranjaHoraria(10)).thenCallRealMethod();
		
		assertTrue(estacionamiento.estaDentroDeLaFranjaHoraria(10));
	}
	
	@Test
	public void esUnEstacionamientoFueraDeFranjaHoraria() {
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		when(estacionamiento.estaDentroDeLaFranjaHoraria(1)).thenCallRealMethod();
		
		assertFalse(estacionamiento.estaDentroDeLaFranjaHoraria(1));
	}
	
	@Test
	public void esUnEstacionamientoFueraDeFranjaHorariaExtra() {
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		when(estacionamiento.estaDentroDeLaFranjaHoraria(23)).thenCallRealMethod();
		
		assertFalse(estacionamiento.estaDentroDeLaFranjaHoraria(23));
	}
	
	
	
	@Test
	public void preguntarPorCelular() {
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		when(estacionamiento.esNumeroCelularBuscado(11111)).thenCallRealMethod();
		
		assertFalse(estacionamiento.esNumeroCelularBuscado(11111));
	}
}
