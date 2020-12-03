package estacionamientoTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import estacionamiento.Estacionamiento;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void getHoraActual() {
		int horaActual = LocalDateTime.now().getHour();
		try (MockedStatic<Estacionamiento> theMock = Mockito.mockStatic(Estacionamiento.class)) {
			  theMock.when(() -> Estacionamiento.getHoraActual()).thenCallRealMethod();
			  
			  assertEquals(horaActual, Estacionamiento.getHoraActual());
		}
	}
	
	@Test
	public void testCelularOrigen() {
		
		assertEquals(-1, estacionamientoX.getNumeroCelularOrigen());
	}
	
}
