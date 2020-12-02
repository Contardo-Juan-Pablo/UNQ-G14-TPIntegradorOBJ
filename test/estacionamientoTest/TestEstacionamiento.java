package estacionamientoTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import estacionamiento.Estacionamiento;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;


public class TestEstacionamiento {
	Estacionamiento estacionamientoX;
	
	@Before
	public void setup() {
		estacionamientoX = new Estacionamiento("AA-12-BB", 4);
	}
	
	@Test
	public void getPatente() {
		assertEquals("AA-12-BB", estacionamientoX.getPatente());
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
	
	@Test
	public void setFinalizar() {
		estacionamientoX.finalizar();
		assertFalse(estacionamientoX.estaActivo());
	}
	
	
	@Test
	public void costoActualPorHoraEnFranjaHorarioMenorA7() {
		try (MockedStatic<Estacionamiento> theMock = Mockito.mockStatic(Estacionamiento.class)) {
			  theMock.when(() -> Estacionamiento.getHoraActual()).thenReturn(6);
			  theMock.when(() -> Estacionamiento.costoActualPorHoraEnFranjaHorario(3)).thenCallRealMethod();
			   
			  assertEquals(80, Estacionamiento.costoActualPorHoraEnFranjaHorario(3));
		}
	}
	
	@Test
	public void costoActualPorHoraEnFranjaHorarioMayorA20() {
		try (MockedStatic<Estacionamiento> theMock = Mockito.mockStatic(Estacionamiento.class)) {
			  theMock.when(() -> Estacionamiento.getHoraActual()).thenReturn(19);
			  theMock.when(() -> Estacionamiento.costoActualPorHoraEnFranjaHorario(5)).thenCallRealMethod();
			   
			  assertEquals(40, Estacionamiento.costoActualPorHoraEnFranjaHorario(5));
		}
	}
	
	@Test
	public void costoActualPorHoraEnFranjaHorario() {
		try (MockedStatic<Estacionamiento> theMock = Mockito.mockStatic(Estacionamiento.class)) {
			  theMock.when(() -> Estacionamiento.getHoraActual()).thenReturn(9);
			  theMock.when(() -> Estacionamiento.costoActualPorHoraEnFranjaHorario(5)).thenCallRealMethod();
			   
			  assertEquals(200, Estacionamiento.costoActualPorHoraEnFranjaHorario(5));
		}
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
	public void testGetHorasReservadas() {
		assertTrue(estacionamientoX.getHorasReservadas() == 4);
	}
	
	@Test
	public void testCelularOrigen() {
		
		assertEquals(-1, estacionamientoX.getNumeroCelularOrigen());
	}
	
}
