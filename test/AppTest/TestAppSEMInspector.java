package AppTest;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import app.AppSEMInspector;
import sem.SEM;

public class TestAppSEMInspector {
	AppSEMInspector appInspector;
	SEM sem;
	
	@Before
	public void setup() {
		sem = mock(SEM.class);
		appInspector = new AppSEMInspector(sem);
	}
	
	
	@Test
	public void consultarPatenteSEM() {
		appInspector.consultarPatenteSEM("AA-12-CC");
		
		verify(sem).hayEstacionamientoVigenteConPatente("AA-12-CC");
	}
	
	@Test
	public void gestionarInfraccion() {
		appInspector.gestionarInfraccion("AA-12-CC", "AA44");
		
		verify(sem).cargarInfraccion("AA-12-CC", "AA44");
	}
}
