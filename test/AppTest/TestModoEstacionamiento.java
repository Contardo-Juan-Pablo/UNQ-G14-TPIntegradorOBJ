package AppTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.ModoEstacionamiento;

public class TestModoEstacionamiento {	
	@Test
	public void siguienteModoInactivo() {
		assertEquals(ModoEstacionamiento.ACTIVO, ModoEstacionamiento.INACTIVO.siguienteEstado());
	}
	
	@Test
	public void siguienteModoActivo() {
		assertEquals(ModoEstacionamiento.INACTIVO, ModoEstacionamiento.ACTIVO.siguienteEstado());
	}
}
