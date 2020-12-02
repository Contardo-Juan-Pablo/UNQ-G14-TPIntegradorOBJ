package semTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import sem.SEM;

public class TestEntidad {
	EntidadTestClass entidad;
	ArrayList<String> informes;
	
	@Before
	public void setup() {
		informes = new ArrayList<String>();
		entidad = new EntidadTestClass(informes);
	}
	
	@Test
	public void update() {
		entidad.update("HH");
		assertEquals(1, entidad.getInformes().size());
	}
	
	@Test
	public void suscribirse() {
		SEM sem = mock(SEM.class);
		entidad.suscribirse(sem);
		
		verify(sem).registrarEntidad(entidad);
	}
	
	@Test
	public void desuscribirse() {
		SEM sem = mock(SEM.class);
		entidad.desuscribirse(sem);
		
		verify(sem).retirarEntidad(entidad);
	}	
}
