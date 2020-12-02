package espaciosFisicosTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;

public class TestZona {
	ArrayList<PuntoDeVenta> listaPuntoDeVenta = new ArrayList<PuntoDeVenta>();
	PuntoDeVenta puntoDeVenta;
	Zona zona;
	
	@Before
	public void setUp() throws Exception {
		zona = new Zona (listaPuntoDeVenta, "F1997");
		puntoDeVenta = new PuntoDeVenta(null, null, null);
	}
	
	@Test
	public void registrarPuntoDeVenta() {
		zona.registrarPuntoDeVenta(puntoDeVenta);
		assertEquals(puntoDeVenta, zona.getPuntosDeVentaDisponibles().get(0));
	}

	@Test
	public void getInspectorAsigando() {
		assertEquals("F1997", zona.getInspectorAsigando());
	}

	@Test
	public void getPuntosDeVentaDisponibles() {
		zona.getPuntosDeVentaDisponibles().add(puntoDeVenta);
		assertEquals(1, zona.getPuntosDeVentaDisponibles().size());
	}	
	
	@Test
	public void contieneAlInspector() {
		assertTrue(zona.contieneAlInspector("F1997"));
	}
	
	@Test
	public void noContieneAlInspector() {
		assertFalse(zona.contieneAlInspector("B-29"));
	}
}