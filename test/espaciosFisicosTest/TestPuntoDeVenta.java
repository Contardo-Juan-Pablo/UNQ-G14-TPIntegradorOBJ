package espaciosFisicosTest;

import org.junit.Test;

import Compra.CargaVirtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import EspaciosFisicos.PuntoDeVenta;
import SEM.SEM;

public class TestPuntoDeVenta {
	
	@Test
	public void ingresarCompra() {
		ArrayList<Integer> numeroDeControl = new ArrayList<Integer>();
		numeroDeControl.add(1);
		SEM sem = new SEM();
		
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(numeroDeControl, 1, sem);
		puntoDeVenta.IngresarCompra(2,"AA-12-BB");
		
		assertEquals(sem.getComprasRealizadas().size(),1);
		assertEquals(sem.getEstacionamientos().size(),1);
	}
	
	@Test
	public void IngresarCarga() {
		ArrayList<CargaVirtual> cargas = new ArrayList<CargaVirtual>();
		SEM sem = new SEM();
		
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(null, null, sem);
		puntoDeVenta.IngresarCarga(123456, 200);
		
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
