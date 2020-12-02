package semTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import AppTest.AppSEMTestClass;
import app.AppSEMInspector;
import app.Estado;
import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoViaApp;
import sem.Entidad;
import sem.Infraccion;
import sem.SEM;

import static org.mockito.Mockito.*;

public class TestSEM {
	AppSEMTestClass appSem;
	AppSEMInspector appInspector;
	SEMTestClass sem;
	Zona zona;
	ArrayList<Compra> comprasRealizadas;
	ArrayList<CargaVirtual> cargasRealizadas;
	ArrayList<Zona> zonasConSEM;
	ArrayList<Infraccion> infraccionesLabradas; 
	ArrayList<Estacionamiento> estacionamientos;
	ArrayList<Entidad> entidadesParticipantes;
	ArrayList<PuntoDeVenta> puntos;
	HashMap<Integer, Integer> creditoAsociado;
	ArrayList<String> informes;
	
	
	@Before
	public void setup() {
		informes = new ArrayList<String>();
		comprasRealizadas = new ArrayList<Compra>();
		cargasRealizadas = new ArrayList<CargaVirtual>();
		zonasConSEM = new ArrayList<Zona>();
		infraccionesLabradas = new ArrayList<Infraccion>(); 
		estacionamientos = new ArrayList<Estacionamiento>();
		entidadesParticipantes = new ArrayList<Entidad>();
		creditoAsociado = new HashMap<Integer, Integer>();
		zona = new Zona(puntos, "AA44");
		sem = new SEMTestClass(comprasRealizadas, cargasRealizadas, zonasConSEM, creditoAsociado, infraccionesLabradas, estacionamientos, entidadesParticipantes);
		sem.setHoraActual(10);
		appInspector = new AppSEMInspector(sem);
		appSem = new AppSEMTestClass(Estado.CAMINANDO, sem);
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
	public void hayEstacionamientoVigenteConPatente() {
		sem.setHoraActual(10);
		Estacionamiento estacionamiento = new Estacionamiento("AA-33-CC", 2);
		sem.guardarEstacionamiento(estacionamiento);
		
		assertTrue(sem.hayEstacionamientoVigenteConPatente("AA-33-CC"));
	}
	
	@Test
	public void hayEstacionamientoVigenteConPatenteFinalizado() {
		sem.setHoraActual(10);
		Estacionamiento estacionamiento = new Estacionamiento("AA-33-CC", 2);
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
		sem.setHoraActual(10);
		Estacionamiento estacionamiento = new Estacionamiento("AA-33-CC", 2);
		Estacionamiento estacionamientoDos = new Estacionamiento("AA-33-XX", 4);
		sem.guardarEstacionamiento(estacionamiento);
		sem.guardarEstacionamiento(estacionamientoDos);
		sem.setHoraActual(20);
		sem.finalizarEstacionamientos();
		
		assertFalse(sem.getEstacionamientos().get(0).estaActivo());
		assertFalse(sem.getEstacionamientos().get(1).estaActivo());
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
		sem.realizarDescuentoDeSaldo(11111, 444, appSem);
		assertEquals(444, appSem.getSaldoActual());
	}
	
	@Test
	public void realizarDescuentoDeSaldoExistePreviamente() {
		CargaVirtual carga = new CargaVirtual(1000, 11111);
		sem.registrarCarga(carga, appSem);
		sem.realizarDescuentoDeSaldo(11111, 500, appSem);
		assertEquals(500, appSem.getSaldoActual());
	}
	
	@Test
	public void fueraDeHorario() {
		sem.setHoraActual(25);
		
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
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
