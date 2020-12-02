package AppTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.Modo;

public class TestModo {	
	@Test
	public void siguienteModoInactivo() {
		assertEquals(Modo.ACTIVADO, Modo.DESACTIVADO.siguienteEstado());
	}
	
	@Test
	public void siguienteModoActivo() {
		assertEquals(Modo.DESACTIVADO, Modo.ACTIVADO.siguienteEstado());
	}
}
