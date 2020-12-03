package semTest;
import estacionamiento.EstacionamientoGeneral;
import estacionamiento.EstacionamientoViaApp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import estacionamiento.Estacionamiento;
import espaciosFisicos.PuntoDeVenta;
import AppTest.UpdateHourTestClass;
import AppTest.AppSEMTestClass;
import java.time.LocalDateTime;
import espaciosFisicos.Zona;
import java.util.ArrayList;
import compra.CargaVirtual;
import org.junit.Before;
import org.junit.Test;
import sem.Infraccion;
import compra.CompraPuntual;
import app.AppSEM;
import app.Estado;
import sem.Entidad;
import sem.SEM;

import static org.mockito.Mockito.*;

public class TestSEM {
	private ArrayList<EstacionamientoGeneral> estacionamientos;
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Entidad> entidadesParticipantes;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<CompraPuntual> comprasRealizadas;
	private ArrayList<AppSEM> appSEMAsociadas;
	private Estacionamiento estacionamiento;
	private UpdateHourTestClass updateHour;
	private ArrayList<PuntoDeVenta> puntos;
	private ArrayList<Zona> zonasConSEM;
	private ArrayList<String> informes;
	private AppSEMTestClass appSem;
	private SEMTestClass sem;
	private Zona zona;
	
	@Before
	public void setup() {
		updateHour = new UpdateHourTestClass();
		informes = new ArrayList<String>();
		comprasRealizadas = new ArrayList<CompraPuntual>();
		cargasRealizadas = new ArrayList<CargaVirtual>();
		zonasConSEM = new ArrayList<Zona>();
		infraccionesLabradas = new ArrayList<Infraccion>(); 
		estacionamientos = new ArrayList<EstacionamientoGeneral>();
		entidadesParticipantes = new ArrayList<Entidad>();
		appSEMAsociadas = new ArrayList<AppSEM>();
		zona = new Zona(puntos, "AA44");
		sem = new SEMTestClass(comprasRealizadas, cargasRealizadas, zonasConSEM, appSEMAsociadas, infraccionesLabradas, estacionamientos, entidadesParticipantes);
		sem.setHoraActual(10);
		appSem = new AppSEMTestClass(Estado.CAMINANDO, sem, updateHour);
		estacionamiento = new Estacionamiento("AA-33-CC", 2);
	}
	
	@Test 	
	public void cargarInfraccion() {
		Infraccion infraccion = new Infraccion("AA-12-CC", LocalDateTime.now(), zona, "AA44");
		sem.cargarInfraccion(infraccion);
		
		assertEquals(1, sem.getInfraccionesLabradas().size());
	}
	
	@Test
	public void zonaAlQuePerteneceInspector() {
		sem.añadirZona(zona);
		
		assertEquals(zona, sem.zonaAlQuePerteneceInspector("AA44"));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void zonaAlQuePerteneceInspectorInspectorFallido() throws IndexOutOfBoundsException {
		sem.añadirZona(zona);
		sem.zonaAlQuePerteneceInspector("AA");
	}
	
	@Test
	public void registrarCompra()  {
		CompraPuntual compra = mock(CompraPuntual.class);
		sem.registrarCompra(compra);
		assertEquals(1, sem.getComprasRealizadas().size());
	}
	
	@Test
	public void hayEstacionamientoVigenteConPatente() {
		sem.setHoraActual(10);
		sem.guardarEstacionamiento(estacionamiento);
		
		assertTrue(sem.hayEstacionamientoVigenteConPatente("AA-33-CC"));
	}
	
	@Test
	public void hayEstacionamientoVigenteConPatenteFinalizado() {
		sem.setHoraActual(10);
		estacionamiento.finalizar();
		sem.guardarEstacionamiento(estacionamiento);
		
		assertFalse(sem.hayEstacionamientoVigenteConPatente("AA-33-CC"));
	}
	
	
	@Test
	public void noHayEstacionamientoVigenteConPatente() {
		sem.setHoraActual(10);
		
		assertFalse(sem.hayEstacionamientoVigenteConPatente("AA-33-XX"));
	}
	
	@Test
	public void finalizarEstacionamientos() {
		AppSEM appSEMX = mock(AppSEM.class);
		sem.setHoraActual(20);
		sem.addAppSem(appSEMX);
		Estacionamiento estacionamiento = new Estacionamiento("AA-33-CC", 2);
		Estacionamiento estacionamientoDos = new Estacionamiento("AA-33-XX", 4);
		sem.guardarEstacionamiento(estacionamiento);
		sem.guardarEstacionamiento(estacionamientoDos);
		sem.finalizarEstacionamientos();
		
		
		assertFalse(sem.getEstacionamientos().get(0).estaActivo());
		assertFalse(sem.getEstacionamientos().get(1).estaActivo());
		verify(appSEMX).finalizarEstacionamientoHoraMaximaSEM();
	}
	
	@Test
	public void finalizarEstacionamientosHorarioMenorAlPermitido() {
		sem.setHoraActual(10);
		Estacionamiento estacionamiento = new Estacionamiento("AA-33-CC", 2);
		Estacionamiento estacionamientoDos = new Estacionamiento("AA-33-XX", 4);
		sem.guardarEstacionamiento(estacionamiento);
		sem.guardarEstacionamiento(estacionamientoDos);
		sem.finalizarEstacionamientos();
		
		assertTrue(sem.getEstacionamientos().get(0).estaActivo());
		assertTrue(sem.getEstacionamientos().get(1).estaActivo());
	}
	
	@Test
	public void terminarEstacionamiento() {
		sem.setHoraActual(10);
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp("AA-33-XX", 1111);
		sem.guardarEstacionamiento(estacionamiento);
		sem.terminarEstacionamiento(1111);
		assertFalse(sem.getEstacionamientos().get(0).estaActivo());
	}
	
	@Test
	public void terminarEstacionamientoNoEsta() {
		sem.setHoraActual(10);
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp("AA-33-DD", 3333);
		sem.guardarEstacionamiento(estacionamiento);
		sem.terminarEstacionamiento(1111);
		assertTrue(sem.getEstacionamientos().get(0).estaActivo());
	}
	
	@Test
	public void realizarDescuentoDeSaldo() {
		sem.realizarDescuentoDeSaldo(444, appSem);
		assertEquals(444, appSem.getSaldoActual());
	}
	
	@Test
	public void realizarDescuentoDeSaldoExistePreviamente() {
		CargaVirtual carga = new CargaVirtual(1000, 11111);
		sem.registrarCarga(carga, appSem);
		sem.realizarDescuentoDeSaldo(500, appSem);
		assertEquals(500, appSem.getSaldoActual());
	}
	
	@Test
	public void fueraDeHorario() {
		sem.setHoraActual(21);
		
		assertTrue(sem.fueraDeHorario(20));
	}
	
	@Test
	public void fueraDeHorarioFalso() {
		sem.setHoraActual(10);
		
		assertFalse(sem.fueraDeHorario(20));
	}
	
	@Test
	public void getHoraActual() {
		SEM semX =  mock(SEM.class);
		when(semX.getHoraActual()).thenCallRealMethod();
		assertEquals(LocalDateTime.now().getHour(), semX.getHoraActual());
	}
	
	@Test
	public void registrarEntidad() {
		Entidad entidad = new Entidad(informes);
		sem.registrarEntidad(entidad);
		
		assertEquals(1, sem.getEntidadesParticipantes().size());
	}
	
	@Test
	public void retirarEntidad() {
		Entidad entidad = new Entidad(informes);
		sem.registrarEntidad(entidad);
		sem.retirarEntidad(entidad);
		
		assertEquals(0, sem.getEntidadesParticipantes().size());
	}
	
	@Test
	public void enviarNotificaciones() {
		Entidad entidad = mock(Entidad.class);
		sem.registrarEntidad(entidad);
		sem.enviarNotificaciones("HH");
		
		verify(entidad).update("HH");
	}
	
	@Test
	public void guardarEstacionamientoMenorA7() {
		sem.setHoraActual(5);
		sem.guardarEstacionamiento(estacionamiento);
		
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	@Test
	public void guardarEstacionamientoMayorA20() {
		sem.setHoraActual(22);
		sem.guardarEstacionamiento(estacionamiento);
		
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
