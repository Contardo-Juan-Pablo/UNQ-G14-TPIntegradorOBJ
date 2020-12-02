package AppTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import app.AppSEMInspector;
import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import sem.Entidad;
import sem.Infraccion;
import sem.SEM;
import semTest.SEMTestClass;

public class TestAppSEMInspector {
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
	}
	
	@Test
	public void consultarPatenteSEM() {
		appInspector.consultarPatenteSEM("AA-12-CC");
		
		verify(sem).hayEstacionamientoVigenteConPatente("AA-12-CC");
	}
	
	@Test
	public void gestionarInfraccion() {
		Infraccion infraccion = new Infraccion("AA-12-CC", LocalDateTime.now(), zona, "AA44");
		appInspector.gestionarInfraccion(infraccion);
		assertEquals(1, sem.getInfraccionesLabradas().size());
	}
}
