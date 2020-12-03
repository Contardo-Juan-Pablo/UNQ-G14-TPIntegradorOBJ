package estacionamientoTest;

import estacionamiento.EstacionamientoGeneral;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.mockito.MockedStatic;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEstacionamientoGeneral {
	
	@Test
	public void costoActualPorHoraEnFranjaHorarioMenorA7() {
		try (MockedStatic<EstacionamientoGeneral> theMock = Mockito.mockStatic(EstacionamientoGeneral.class)) {
			  theMock.when(() -> EstacionamientoGeneral.getHoraActual()).thenReturn(6);
			  theMock.when(() -> EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(3)).thenCallRealMethod();
			   
			  assertEquals(80, EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(3));
		}
	}
	
	@Test
	public void costoActualPorHoraEnFranjaHorarioMayorA20() {
		try (MockedStatic<EstacionamientoGeneral> theMock = Mockito.mockStatic(EstacionamientoGeneral.class)) {
			  theMock.when(() -> EstacionamientoGeneral.getHoraActual()).thenReturn(19);
			  theMock.when(() -> EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(5)).thenCallRealMethod();
			   
			  assertEquals(40, EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(5));
		}
	}
	
	@Test
	public void costoActualPorHoraEnFranjaHorario() {
		try (MockedStatic<EstacionamientoGeneral> theMock = Mockito.mockStatic(EstacionamientoGeneral.class)) {
			  theMock.when(() -> EstacionamientoGeneral.getHoraActual()).thenReturn(9);
			  theMock.when(() -> EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(5)).thenCallRealMethod();
			   
			  assertEquals(200, EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(5));
		}
	}
	
}
