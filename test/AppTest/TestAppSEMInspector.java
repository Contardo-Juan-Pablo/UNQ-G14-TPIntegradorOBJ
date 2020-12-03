package AppTest;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import app.AppSEMInspector;
import compra.CargaVirtual;
import compra.CompraPuntual;
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
	ArrayList<CompraPuntual> comprasRealizadas;
	ArrayList<CargaVirtual> cargasRealizadas;
	ArrayList<Zona> zonasConSEM;
	ArrayList<Infraccion> infraccionesLabradas; 
	ArrayList<Estacionamiento> estacionamientos;
	ArrayList<Entidad> entidadesParticipantes;
	ArrayList<PuntoDeVenta> puntos;
	HashMap<Integer, Integer> creditoAsociado;
	Zona zona;
	SEM semMock;
	
	@Before
	public void setup() {
		comprasRealizadas = new ArrayList<CompraPuntual>();
		cargasRealizadas = new ArrayList<CargaVirtual>();
		zonasConSEM = new ArrayList<Zona>();
		infraccionesLabradas = new ArrayList<Infraccion>(); 
		estacionamientos = new ArrayList<Estacionamiento>();
		entidadesParticipantes = new ArrayList<Entidad>();
		creditoAsociado = new HashMap<Integer, Integer>();
		zona = new Zona(puntos, "AA44");
		semMock = mock(SEM.class);
		appInspector = new AppSEMInspector(semMock);
	}
	
	@Test
	public void consultarPatenteSEM() {
		appInspector.consultarPatenteSEM("AA-12-CC");
		
		verify(semMock).hayEstacionamientoVigenteConPatente("AA-12-CC");
	}
	
	@Test
	public void gestionarInfraccion() {
		Infraccion infraccion = new Infraccion("AA-12-CC", LocalDateTime.now(), zona, "AA44");
		appInspector.gestionarInfraccion(infraccion);
		
		verify(semMock).cargarInfraccion(infraccion);
	}
}
