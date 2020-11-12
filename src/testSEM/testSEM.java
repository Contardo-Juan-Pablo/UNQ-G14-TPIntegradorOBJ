package testSEM;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import App.AppSEMInspector;
import App.CelularInspector;
import Compra.CargaVirtual;
import EspaciosFisicos.PuntoDeVenta;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import Persona.Inspector;
import Persona.Operador;
import SEM.Infraccion;
import SEM.SEM;

public class testSEM {
	private SEM sem;
	private EstacionamientoViaApp estacionamientoViaApp;
	private EstacionamientoViaApp estacionamientoViaApp2;
	private PuntoDeVenta puntoDeVenta;
	private ArrayList<PuntoDeVenta> puntosDeVentaDisponibles;
	private AppSEMInspector appSEMInspector;
	private CelularInspector celuInspector;
	private Inspector inspector;
	private Zona zona;
	private Calendar calendario;
	private CargaVirtual cargaVirtual;
	private Inspector inspectorDos;
	private AppSEMInspector appSEMInspectorDos;
	private CelularInspector celuInspectorDos;
	private Operador operadorUno;
	private Operador operadorDos;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<Zona> zonas;
	private Infraccion infraccion;
	private Map<Integer, Integer> celulares; 
	private ArrayList<Estacionamiento> estacionamientos;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		puntoDeVenta = new PuntoDeVenta(null, 1, sem);		
		puntosDeVentaDisponibles = new ArrayList<PuntoDeVenta>();
		puntosDeVentaDisponibles.add(puntoDeVenta);
		
		appSEMInspector = new AppSEMInspector(12);
		celuInspector = new CelularInspector(123456789, appSEMInspector);
		inspector = new Inspector(celuInspector, 1);
		
		appSEMInspectorDos = new AppSEMInspector(11);
		celuInspectorDos = new CelularInspector(1122112211, appSEMInspectorDos);
		inspectorDos = new Inspector(celuInspector, 2);
		
		operadorUno = new Operador(1);
		operadorDos = new Operador(2);
		cargasRealizadas = new ArrayList<CargaVirtual>();
		cargasRealizadas.add(cargaVirtual);
		zona = new Zona(puntosDeVentaDisponibles,inspector);
		zonas = new ArrayList<Zona>();
		zonas.add(zona);
		
		estacionamientoViaApp = new EstacionamientoViaApp("AA-12-ZZ", 7, 20, 123456789);
		estacionamientoViaApp2 = new EstacionamientoViaApp("AA-13-ZZ", 5, 12, 1122334455);
		
		calendario = new GregorianCalendar(2013,1,28,13,24,56); //2013 FEB 28 13:24:56		
		cargaVirtual = new CargaVirtual("A1", puntoDeVenta, calendario, 40, 12345678);
		infraccion = new Infraccion("AA-13-ZZ", calendario, zona, inspector);
		
		estacionamientos = new ArrayList<Estacionamiento>();
		estacionamientos.add(estacionamientoViaApp);
		
		sem = new SEM();
		sem.setHoraActual(7);
		sem.setOperadorAsociado(operadorUno);
		sem.registrarZona(zona);

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testCargarInfraccionVacio(String patente, Inspector inspector) {
		assertEquals(0, sem.getInfraccionesLabradas().size());
	}
	
	
	public void testCargarInfraccionCargado(String patente, Inspector inspector) {
		sem.cargarInfraccion(patente, inspector);
		assertEquals(1, sem.getInfraccionesLabradas().size());
	}
	
	public void testZonaAlQuePerteneceInspectorCasoPositivo(Inspector inspector) {
		assertEquals(zona, sem.zonaAlQuePerteneceInspector(inspector));
	}	
	
	public void testZonaAlQuePerteneceInspectorCasoNegativo(Inspector inspector) {
		assertNull(sem.zonaAlQuePerteneceInspector(inspector));
	}	
	
	public void testconsultarPatenteSEMCasoVerdadero(String patente) {
		sem.setEstacionamiento(estacionamientoViaApp);
		assertEquals(true, sem.consultarPatenteSEM(patente));
	}
	
	public void testconsultarPatenteSEMCasoFalso(String patente) {
		assertEquals(false, sem.consultarPatenteSEM(patente));
	}
	
	public void testconsultarPatenteSEMFueraDeServicio(String patente) {
		sem.setHoraActual(22);
		sem.setEstacionamiento(estacionamientoViaApp);
		assertEquals(false, sem.consultarPatenteSEM(patente));
	}
	
	public void testFinalizarEstacionamientosCasoVerdadero() {
		sem.finalizarEstacionamientos();
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	public void testFinalizarEstacionamientosCasoFalso() {
		/** Elegimos las 9 AM ya que no se podria dar la baja de todos los estacionamientos. **/
		sem.setHoraActual(9);
		sem.setEstacionamiento(estacionamientoViaApp);
		sem.finalizarEstacionamientos();
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testRegistrarCarga(CargaVirtual carga) {
		sem.registrarCarga(cargaVirtual);
		assertEquals(1, sem.getCargasRealizadas().size());
		
	}
	
	public void testRegistrarCargaNuevo(CargaVirtual carga) {
		assertEquals(false, sem.getCelulares().containsKey(carga.getCelular()));
		
	}
	
	public void testRegistrarCargaActualizar(CargaVirtual carga) {
		sem.setCelulares(carga.getCelular(), carga.getCarga());
		assertEquals(true, sem.getCelulares().containsKey(carga.getCelular()));
		
	}
	
	public void testRegistrarCargaActualizarVerdadero(CargaVirtual carga) {
		sem.setCelulares(carga.getCelular(), 100);
		sem.registrarCarga(carga);
		assertEquals(100 + carga.getCarga(), sem.getCelulares().get(carga.getCelular()));
		
	}
	
	/**public void testGuardarEstacionamientoHoraCorrecta(Estacionamiento estacionamiento) {
		sem.setHoraActual(13);
		sem.guardarEstacionamiento(estacionamiento);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testGuardarEstacionamientoFueraDeHora(Estacionamiento estacionamiento) {
		sem.setHoraActual(5);
		assertEquals(0, sem.getEstacionamientos().size());
	} **/
	
	public void testTerminarEstacionamientoBorradoUno(int numeroCelular) {
		sem.setEstacionamiento(estacionamientoViaApp2);
		sem.terminarEstacionamiento(numeroCelular);
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	public void testTerminarEstacionamientoSinBorrados(int numeroCelular) {
		sem.setEstacionamiento(estacionamientoViaApp);
		sem.setEstacionamiento(estacionamientoViaApp2);
		sem.terminarEstacionamiento(numeroCelular);
		assertEquals(2, sem.getEstacionamientos().size());
	}
	
	public void testBuscarEstacionamientoCasoExistente(int numeroCelular) {
		sem.setEstacionamiento(estacionamientoViaApp2);
		assertEquals(estacionamientoViaApp2, sem.buscarEstacionamiento(numeroCelular));
	}
	
	public void testBuscarEstacionamientoCasoNoExistente(int numeroCelular) {
		sem.setEstacionamiento(estacionamientoViaApp2);
		assertNull(sem.buscarEstacionamiento(numeroCelular));
	}	
	
	public void testConfirmarOperadorCasoVerdadero(Operador operador) {
		assertEquals(true, sem.confirmarOperador(operador));
	}
	
	public void testConfirmarOperadorCasoFalso(Operador operador) {
		assertEquals(false, sem.confirmarOperador(operador));
	}
	
	public void testSetCargasRealizadas(ArrayList<CargaVirtual> cargasRealizadas) {
		sem.setCargasRealizadas(cargasRealizadas);
		assertEquals(1, sem.getCargasRealizadas().size());
	}
	
	public void testSetCargasRealizada (CargaVirtual cargaVirtual) {
		sem.setCargasRealizada(cargaVirtual);
		assertEquals(1, sem.getCargasRealizadas().size());
	}
	
	public void testGetZonasConSEM() {
		assertEquals(zonas, sem.getZonasConSEM());
	}

	public void testSetInfraccionLabrada(Infraccion infraccionGenerada) {
		sem.setInfraccionLabrada(infraccionGenerada);
		assertEquals(1, sem.getInfraccionesLabradas().size());
		
	}
	
	public void testSaldoCelular(int celular) {
		sem.registrarCarga(cargaVirtual);;
		assertEquals(40, sem.saldoCelular(celular));
	}

	public void testGetOperadorAsociado() {
		assertEquals(operadorUno, sem.getOperadorAsociado());
	}

	public void testSetEstacionamientos(ArrayList<Estacionamiento> estacionamientos) {
		sem.setEstacionamientos(estacionamientos);
		assertEquals(1, sem.getEstacionamientos().size());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void maintestCargarInfraccionVacio() {
		testCargarInfraccionVacio("AA-12-ZZ", inspector);
	}

	@Test
	void maintestCargarInfraccionCargado() {
		testCargarInfraccionCargado("AA-12-ZZ", inspector);
	}
	
	@Test
	void maintestZonaAlQuePerteneceInspectorCasoPositivo() {
		testZonaAlQuePerteneceInspectorCasoPositivo(inspector);
	}
	
	@Test
	void maintestZonaAlQuePerteneceInspectorCasoNegativo() {
		testZonaAlQuePerteneceInspectorCasoNegativo(inspectorDos);
	}
	
	@Test
	void maintestconsultarPatenteSEMCasoVerdadero() {
		testconsultarPatenteSEMCasoVerdadero("AA-12-ZZ");
	}
	
	@Test
	void maintestconsultarPatenteSEMCasoFalso() {
		testconsultarPatenteSEMCasoFalso("AA-12-ZZ");
	}
	
	@Test
	void maintestconsultarPatenteSEMFueraDeServicio() {
		testconsultarPatenteSEMFueraDeServicio("AA-12-ZZ");
	}
	
	@Test
	void maintestconsultarPatenteSEMFueraDeServicioDos() {
		testconsultarPatenteSEMFueraDeServicio("AA-13-ZZ");
	}
	
	@Test
	void maintestFinalizarEstacionamientosCasoFalso() {
		testFinalizarEstacionamientosCasoFalso();
	}
	
	@Test
	void maintestFinalizarEstacionamientosCasoVerdadero() {
		testFinalizarEstacionamientosCasoVerdadero();
	}
	
	/////////////////////////////////////////////////////////////////////////
	@Test
	void maintestRegistrarCargaNuevo() {
		testRegistrarCargaNuevo(cargaVirtual);
	}
	
	@Test
	void maintestRegistrarCargaActualizar() {
		testRegistrarCargaActualizar(cargaVirtual);
	}
	
	@Test
	void maintestRegistrarCargaActualizarVerdadero() {
		testRegistrarCargaActualizarVerdadero(cargaVirtual);
	}
	/////////////////////////////////////////////////////////////////////////
	
	@Test
	void maintestTerminarEstacionamientoBorradoUno() {
		testTerminarEstacionamientoBorradoUno(1122334455);
	}
	
	@Test
	void maintestTerminarEstacionamientoSinBorrados() {
		testTerminarEstacionamientoSinBorrados(1111444422);
	}
	
	@Test
	void maintestBuscarEstacionamientoCasoExistente() {
		testBuscarEstacionamientoCasoExistente(1122334455);
	}
	
	@Test
	void maintestBuscarEstacionamientoCasoNoExistente() {
		testBuscarEstacionamientoCasoNoExistente(1111444422);
	}
	
	@Test
	void maintestConfirmarOperadorCasoVerdadero() {
		testConfirmarOperadorCasoVerdadero(operadorUno);
	}
	
	@Test
	void maintestConfirmarOperadorCasoFalso() {
		testConfirmarOperadorCasoFalso(operadorDos);
	}
	
	@Test
	void maintestSetCargasRealizadas() {
		testSetCargasRealizadas(cargasRealizadas);
	}
	
	@Test
	void maintestSetCargasRealizada() {
		testSetCargasRealizada(cargaVirtual);
	}
	
	@Test
	void maintestGetZonasConSEM() {
		testGetZonasConSEM();
	}
	
	@Test
	void maintestSetInfraccionLabrada() {
		testSetInfraccionLabrada(infraccion);
	}
	
	@Test
	void maintestSaldoCelular() {
		testSaldoCelular(12345678);
	}
	
	@Test
	void maintestGetOperadorAsociado() {
		testGetOperadorAsociado();
	}
	
	@Test
	void maintestSetEstacionamientos() {
		testSetEstacionamientos(estacionamientos);
	}
}



	

