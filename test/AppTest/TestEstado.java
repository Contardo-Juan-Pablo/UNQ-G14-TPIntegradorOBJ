package AppTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import app.Estado;

public class TestEstado {
	@Test
	public void siguienteModoInactivo() {
		assertEquals(Estado.CAMINANDO, Estado.MANEJANDO.siguienteEstado());
	}
	
	@Test
	public void siguienteModoActivo() {
		assertEquals(Estado.MANEJANDO, Estado.CAMINANDO.siguienteEstado());
	}
}
