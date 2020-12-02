package espaciosFisicosTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import app.AppSEM;
import app.Estado;
import compra.CargaVirtual;
import espaciosFisicos.PuntoDeVenta;
import sem.SEM;

public class TestPuntoDeVenta {
	SEM sem;
	AppSEM appSem;
	
	@Before
	public void setup() {
		sem = mock(SEM.class);
		appSem = new AppSEM(Estado.CAMINANDO, sem);
	}
	
	@Test
	public void ingresarCompra() {
		when(sem.getHoraActual()).thenReturn(10);
		ArrayList<Integer> numeroDeControl = new ArrayList<Integer>(1);
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(numeroDeControl, 1, sem);
		puntoDeVenta.ingresarCompra(2,"AA-12-BB");
		
		
	}
	
	@Test
	public void ingresarCarga() {
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(null, null, sem);
		puntoDeVenta.ingresarCarga(123456, 200, appSem);
		
		assertEquals(1, sem.getCargasRealizadas().size());
	}
	
	@Test
	public void getCodigoDeControl() {
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		when(puntoDeVenta.getCodigoDeControl()).thenCallRealMethod();
		
		assertEquals(puntoDeVenta.getCodigoDeControl(),"00");
	}
	
	@Test
	public void lastControl() {
		ArrayList<Integer> listaControl = new ArrayList<Integer>();
		PuntoDeVenta puntoDeControl = new PuntoDeVenta(listaControl, null, null);
		
		listaControl.add(3);
		listaControl.add(4);
		
		assertTrue(puntoDeControl.lastControl() == 2);
	}
	
	@Test
	public void getId() {
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(null, 123456, null);
		assertTrue(123456 == puntoDeVenta.getId());
	}

}
