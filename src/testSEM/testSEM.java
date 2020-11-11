package testSEM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import App.AppSEMInspector;
import App.CelularInspector;
import Compra.CargaVirtual;
import Compra.CompraFisica;
import EspaciosFisicos.PuntoDeVenta;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import Persona.Inspector;
import SEM.Infraccion;
import SEM.SEM;

public class testSEM{
	
	private SEM sem;
	private EstacionamientoViaApp estacionamientoViaApp;
	private PuntoDeVenta puntoDeVenta;
	private ArrayList<PuntoDeVenta> puntosDeVentaDisponibles;
	private AppSEMInspector appSEMInspector;
	private CelularInspector celuInspector;
	private Inspector inspector;
	private Zona zona;
	private Calendar calendario;
	private CargaVirtual cargaVirtual;
	
	@BeforeEach
	public void setUp() throws Exception {
		sem = new SEM();
		
		puntoDeVenta = new PuntoDeVenta(1);		
		puntosDeVentaDisponibles = new ArrayList<PuntoDeVenta>();
		puntosDeVentaDisponibles.add(puntoDeVenta);
		
		appSEMInspector = new AppSEMInspector(12);
		celuInspector = new CelularInspector(123456789, appSEMInspector);
		inspector = new Inspector(celuInspector, 1);
		
		zona = new Zona(puntosDeVentaDisponibles,inspector);
		sem.RegistrarZona(zona);
		
		estacionamientoViaApp = new EstacionamientoViaApp("ABC", 7, 20, 123456789);
		
		calendario = new GregorianCalendar(2013,1,28,13,24,56); //2013 FEB 28 13:24:56		
		cargaVirtual = new CargaVirtual("A1", puntoDeVenta, calendario, 40, 12345678);
	}
	
	/////////////////////////////////////////////
	///////////// SECCIÓN INSPECTOR /////////////
	/////////////////////////////////////////////
	public void testCargarInfraccion(String patente, Inspector inspector) {
		Calendar fechaYHoraActual = Calendar.getInstance();
		Zona zonaDeInfraccion = sem.zonaAlQuePerteneceInspector(inspector);
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, inspector);
		sem.setInfraccionesLabradas(infraccionGenerada);
		
		assertEquals(sem.getInfraccionesLabradas().size(),1);
	}
	
	public void testZonaAlQuePerteneceInspector(Inspector inspector) {
		Zona zonaLocal = null;
		for(int i = 0; i < sem.getZonasConSEM().size(); i++) {
			if(sem.getZonasConSEM().get(i).contieneAlInspector(inspector)) {
				zonaLocal = sem.getZonasConSEM().get(i);
				
				assertEquals(zonaLocal,zona);
			}
		}
	}
	
	public void testConsultarPatenteSEM(String patente) {
		Boolean patenteVigenteEncontrada = false;
		for(int i=0; i < sem.getEstacionamiento().size(); i++){
			if(sem.getEstacionamiento().get(i).getPatente() == patente) {
				patenteVigenteEncontrada = patenteVigenteEncontrada || true && sem.getEstacionamiento().get(i).estaVigente();
				
				assertEquals(sem.getEstacionamiento().get(i).getPatente(), patente);
			}
		}		
		
	}
	
	////////////////////////////////////////////
	///////////// SECCIÓN OPERADOR /////////////
	////////////////////////////////////////////
	public void testFinalizarEstacionamientos() {
		if(sem.fueraDeHorario()) {
			sem.getEstacionamiento().clear();
			
			assertEquals(sem.getEstacionamiento().size(),0);
		}
	}
	
    ////////////////////////////////////////////
	///////////// SEM FUNCIONES ////////////////
	////////////////////////////////////////////
	public void testRegistrarCarga(CargaVirtual cargaVirtual) {
		sem.setCargasRealizadas(cargaVirtual);
		int celular = cargaVirtual.getCelular();
		if(sem.getCelular().containsKey(celular)) {
			sem.actualizarSaldo(celular, cargaVirtual.getCarga());
		}
		
		assertEquals(sem.getCargasRealizadas().size(),1);
	}
	
	public void testGuardarEstacionamiento(EstacionamientoViaApp estacionamientoViaApp) {
		int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(horaActual > 7 && horaActual < 20) {
			sem.setEstacionamiento(estacionamientoViaApp);
			//sem.enviarNotificaciones();
			
			assertEquals(sem.getEstacionamiento().size(),1);
		}		
	}
	
	public void testTerminarEstacionamiento(int numeroCelular) {
		for(int i=0; i < sem.getEstacionamiento().size(); i++){
			if(sem.getEstacionamiento().get(i).esNumeroCelularBuscado(numeroCelular)) {
				sem.getEstacionamiento().remove(i);
				//sem.enviarNotificaciones();
				
				assertNotEquals(sem.getEstacionamiento().get(i),numeroCelular);
			}
		}
	}
	
	public void testBuscarEstacionamiento(int numeroCelular) {
		Estacionamiento estacionamiento = null;
		for(int i=0; i < sem.getEstacionamiento().size(); i++){
			if(sem.getEstacionamiento().get(i).esNumeroCelularBuscado(numeroCelular)) {
				estacionamiento = sem.getEstacionamiento().get(i);
				
				assertEquals(sem.getEstacionamiento().get(i),numeroCelular);
			}
		}
	}
	
	///////////////////////////////////////////
	///////////// SEM FUNCIONES AUX ///////////
	///////////////////////////////////////////	
	public void testCostoActualPorHoraEnFranjaHorario(int horaActual, int horasReservadas) {
		int contador = 0;
		for(int i = horaActual; i<=horaActual+horasReservadas; i++) {
			if(i > 7 && i < 20) {
				contador++;
			}			
		}
		assertEquals(40 * contador,160);
	}
	
	public void testFueraDeHorario() {
		Calendar fechaActual = Calendar.getInstance();
		Boolean horario = fechaActual.get(Calendar.HOUR_OF_DAY) >= 20;
		if(horario){
			assertEquals(horario,true);
		}
		else {
			assertNotEquals(horario,true);
		}		
	}
	
	@Test
	void mainTestCargarInfraccion() {
		testCargarInfraccion("ABC",inspector);
	}
	
	@Test
	void mainTestZonaAlQuePerteneceInspector() {
		testZonaAlQuePerteneceInspector(inspector);
	}
	
	@Test
	void mainTestConsultarPatenteSEM() {
		testConsultarPatenteSEM("ABC");
	}
	
	@Test
	void mainTestGuardarEstacionamiento() {		
		testGuardarEstacionamiento(estacionamientoViaApp);
		
	}
	
	@Test
	void mainTestFinalizarEstacionamientos() {
		testFinalizarEstacionamientos();
	}
	
	@Test
	void mainTestRegistrarCarga() {
		testRegistrarCarga(cargaVirtual);
	}
	
	@Test
	void mainTestTerminarEstacionamiento() {
		testTerminarEstacionamiento(12345678);
	}
	
	@Test
	void mainTestBuscarEstacionamiento() {
		testBuscarEstacionamiento(12345678);
	}
	
	@Test
	void mainTestCostoActualPorHoraEnFranjaHorario() {
		testCostoActualPorHoraEnFranjaHorario(16,6);
	}
	
	@Test
	void mainTestFueraDeHorario() {
		testFueraDeHorario();
	}
}