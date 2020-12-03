package AppTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.AbstractMap.SimpleEntry;

import org.junit.Before;
import org.junit.Test;

public class TestUpdateHour {
	private UpdateHourTestClass updateHour;
	
	@Before
	public void setup() {
		updateHour = new UpdateHourTestClass();
	}
	
	@Test
	public void actualizarHoraEstacionamientoAutomatico() {
		updateHour.actualizarHoraEstacionamientoAutomatico();
		assertEquals(2, updateHour.getHoraDeEstacionamientoActual());
	}
	
	@Test
	public void testExcedioLaCantidadDeHorasMaximaEnCasoFalso() {
		updateHour.setEstacionamientoActivo(true);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(new SimpleEntry<>(1, 2));
		assertFalse(updateHour.excedioLaCantidadDeHorasMaxima());
	}
	
	@Test
	public void testFinalizarServicio() {
		updateHour.setEstacionamientoActivo(true);
		updateHour.finalizarServicio();
		assertFalse(updateHour.getEstacionamientoActivo());
	}
}
