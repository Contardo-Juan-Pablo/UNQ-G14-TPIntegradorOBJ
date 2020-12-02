package espaciosFisicosTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.HashMap;
import app.AppSEM;
import app.AppSEMInspector;
import app.Estado;
import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import sem.Entidad;
import sem.Infraccion;
import sem.SEM;
import semTest.SEMTestClass;

public class TestPuntoDeVenta {
	AppSEM appSem;
	AppSEMInspector appInspector;
	SEMTestClass sem;
	ArrayList<Compra> comprasRealizadas;
	ArrayList<CargaVirtual> cargasRealizadas;
	ArrayList<Zona> zonasConSEM;
	ArrayList<Infraccion> infraccionesLabradas; 
	ArrayList<Estacionamiento> estacionamientos;
	ArrayList<Entidad> entidadesParticipantes;
	ArrayList<PuntoDeVenta> puntos;
	HashMap<Integer, Integer> creditoAsociado;
	Zona zona;
	
	@Before
	public void setup() {
		comprasRealizadas = new ArrayList<Compra>();
		cargasRealizadas = new ArrayList<CargaVirtual>();
		zonasConSEM = new ArrayList<Zona>();
		infraccionesLabradas = new ArrayList<Infraccion>(); 
		estacionamientos = new ArrayList<Estacionamiento>();
		entidadesParticipantes = new ArrayList<Entidad>();
		creditoAsociado = new HashMap<Integer, Integer>();
		zona = new Zona(puntos, "AA44");
		sem = new SEMTestClass(comprasRealizadas, cargasRealizadas, zonasConSEM, creditoAsociado, infraccionesLabradas, estacionamientos, entidadesParticipantes);
		appInspector = new AppSEMInspector(sem);
		appSem = new AppSEM(Estado.CAMINANDO, sem);
	}
	
	
	@Test
	public void ingresarCompra() {
		Compra compra = mock(Compra.class);
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		
		sem.setHoraActual(10);
		ArrayList<Integer> numeroDeControl = new ArrayList<Integer>(1);
		PuntoDeVenta puntoDeVenta = new PuntoDeVenta(numeroDeControl, 1, sem);
		puntoDeVenta.ingresarCompra(compra, estacionamiento);
		
		assertEquals(1, sem.getComprasRealizadas().size());
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