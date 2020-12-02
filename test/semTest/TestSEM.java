package semTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.PuntoDeVenta;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import sem.Entidad;
import sem.Infraccion;

public class TestSEM {
	SEMTestClass sem;
	ArrayList<Compra> comprasRealizadas;
	ArrayList<CargaVirtual> cargasRealizadas;
	ArrayList<Zona> zonasConSEM; 
	Map<Integer, Integer> creditoAsociado;
	ArrayList<Infraccion> infraccionesLabradas; 
	ArrayList<Estacionamiento> estacionamientos;
	ArrayList<PuntoDeVenta> puntosDeVenta;
	ArrayList<Entidad> entidadesParticipantes;
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
		puntosDeVenta = new ArrayList<PuntoDeVenta>();
		zona = new Zona(puntosDeVenta, "AA44");
		sem = new SEMTestClass(comprasRealizadas, cargasRealizadas, zonasConSEM, creditoAsociado, infraccionesLabradas, estacionamientos, entidadesParticipantes);
	}
	
	@Test
	public void cargarInfraccion() {
		Infraccion infraccion = new Infraccion("AA-12-BB", LocalDateTime.now(), zona, "AA44");
		sem.cargarInfraccion(infraccion);
		
		assertEquals(1, sem.getInfraccionesLabradas().size());
	}
}