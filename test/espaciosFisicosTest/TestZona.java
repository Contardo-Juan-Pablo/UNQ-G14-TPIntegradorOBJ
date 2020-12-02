package espaciosFisicosTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;

public class TestZona {
	
	@Test
	public void registrarPuntoDeVenta() {
		ArrayList<PuntoDeVenta> listaPuntoDeVenta = new ArrayList<PuntoDeVenta>();
		Zona zonaZ = new Zona (listaPuntoDeVenta, "F1997");
		PuntoDeVenta puntoDeVentaX = new PuntoDeVenta(null, null, null);
		zonaZ.getPuntosDeVentaDisponibles().add(puntoDeVentaX);
		assertEquals(puntoDeVentaX, zonaZ.getPuntosDeVentaDisponibles().get(0));
	}

	@Test
	public void getInspectorAsigando() {
		Zona zonaX = new Zona (null, "F1997");
		assertEquals("F1997", zonaX.getInspectorAsigando());
	}

	@Test
	public void getPuntosDeVentaDisponibles() {
		ArrayList<PuntoDeVenta> listaPuntoDeVenta = new ArrayList<PuntoDeVenta>();
		Zona zonaZ = new Zona (listaPuntoDeVenta, "F1997");
		PuntoDeVenta puntoDeVentaX = new PuntoDeVenta(null, null, null);
		zonaZ.getPuntosDeVentaDisponibles().add(puntoDeVentaX);
		assertEquals(1, zonaZ.getPuntosDeVentaDisponibles().size());
	}	
	
	@Test
	public void contieneAlInspector() {
		Zona zona = new Zona(null, "F1997");
		assertTrue(zona.contieneAlInspector("F1997"));
	}	

}
