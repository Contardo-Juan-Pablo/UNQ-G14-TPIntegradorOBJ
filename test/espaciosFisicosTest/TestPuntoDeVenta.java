package espaciosFisicosTest;
import estacionamiento.EstacionamientoGeneral;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import estacionamiento.Estacionamiento;
import static org.mockito.Mockito.*;
import espaciosFisicos.PuntoDeVenta;
import AppTest.UpdateHourTestClass;
import semTest.SEMTestClass;
import espaciosFisicos.Zona;
import java.util.ArrayList;
import compra.CargaVirtual;
import org.junit.Before;
import sem.Infraccion;
import compra.CompraPuntual;
import org.junit.Test;
import sem.Entidad;
import app.AppSEM;
import app.Estado;

public class TestPuntoDeVenta {
	private ArrayList<EstacionamientoGeneral> estacionamientos;
	private ArrayList<Infraccion> infraccionesLabradas; 
	private ArrayList<Entidad> entidadesParticipantes;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<CompraPuntual> comprasRealizadas;
	private ArrayList<AppSEM> appSEMAsociadas;
	private UpdateHourTestClass updateHour;
	private ArrayList<Zona> zonasConSEM;
	private SEMTestClass sem;
	private AppSEM appSem;
	
	@Before
	public void setup() {
		estacionamientos = new ArrayList<EstacionamientoGeneral>();
		infraccionesLabradas = new ArrayList<Infraccion>(); 
		entidadesParticipantes = new ArrayList<Entidad>();
		cargasRealizadas = new ArrayList<CargaVirtual>();
		comprasRealizadas = new ArrayList<CompraPuntual>();
		appSEMAsociadas = new ArrayList<AppSEM>();
		updateHour = new UpdateHourTestClass();
		zonasConSEM = new ArrayList<Zona>();
		sem = new SEMTestClass(comprasRealizadas, cargasRealizadas, zonasConSEM, appSEMAsociadas, infraccionesLabradas, estacionamientos, entidadesParticipantes);
		appSem = new AppSEM(Estado.CAMINANDO, sem, updateHour);
	}
	
	@Test
	public void ingresarCompra() {
		CompraPuntual compra = mock(CompraPuntual.class);
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