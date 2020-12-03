package AppTest;

import static org.junit.Assert.assertEquals;
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
}
