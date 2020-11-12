package testApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import App.AppSEM;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class testAppSEM {
	
	SEM sem;
	EstacionamientoViaApp estacionamiento;
	AppSEM appSEM;
	
	@BeforeEach
	public void setUp() throws Exception {
		estacionamiento = new EstacionamientoViaApp("AA-12-BB", 9, 15, 6);
		
		sem = new SEM();
		sem.setHoraActual(10);
		appSEM = new AppSEM(true, true);
		sem.setCelulares(123456789, 4000);
		sem.setCelulares(111111111, 0); 
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testSolicitarSaldoSEM(){
		int saldo = appSEM.solicitarSaldoSEM(sem, 123456789);
		assertEquals(saldo,4000);
	}

	public void testIniciarEstacionamientoNotificacionNegativa(String patente, int horasReservadas, SEM sem, int numeroCelular) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sem.guardarEstacionamiento(estacionamiento);
        appSEM.IniciarEstacionamiento(patente, horasReservadas, sem , numeroCelular);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertTrue(allWrittenLines.contains("Saldo insuficiente. Estacionamiento no permitido.")); 
	}

	public void testAlertaDeInicioEstacionamiento_ConEstadoActualFalse() throws Exception {
        appSEM.setEstadoActual(false);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sem.guardarEstacionamiento(estacionamiento);
        appSEM.alertaDeInicioEstacionamiento("ABC", sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertTrue(allWrittenLines.contains("No inicio un estacionamiento")); 
    }
	
	public void testAlertaDeInicioEstacionamiento_ConEstadoActualTrue() throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        appSEM.alertaDeInicioEstacionamiento("ABC", sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertFalse(allWrittenLines.contains("No inicio un estacionamiento")); 
    }
	
	public void testAlertaDeInicioEstacionamiento_ConEstadoActualFalseYHayUnEstacionamiento() throws Exception {
		appSEM.setEstadoActual(false);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sem.guardarEstacionamiento(estacionamiento);
        appSEM.alertaDeInicioEstacionamiento("AA-12-BB", sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertFalse(allWrittenLines.contains("No inicio un estacionamiento")); 
    }
	
	public void testSetEstadoActual_estadoAnteriorTrue() {
		appSEM.setEstadoActual(false);
		assertEquals(true, appSEM.getEstadoAnterior());
	}
	
	public void testSetEstadoActual_estadoActualTrue() {
		appSEM.setEstadoActual(false);
		appSEM.setEstadoActual(true);
		assertEquals(false, appSEM.getEstadoAnterior());
	}
	
	public void testcambiarModoAppCasoVerdadero() {
		/** La app comienza con el modo automático en false. Esto también testea el método "getModoAutomatico()"*/
		appSEM.cambiarModoApp();
		assertEquals(true, appSEM.getModoAutomatico());
	}
	
	public void testcambiarModoAppCasoFalso() {
		/** La app comienza con el modo automático en false. Esto también testea el método "getModoAutomatico()"*/
		appSEM.cambiarModoApp();
		appSEM.cambiarModoApp();
		assertEquals(false, appSEM.getModoAutomatico());
	}
	
	public void testalertaDeFinEstacionamientoEstadoActualFalsoYHayEstacionamiento(String patente, SEM sem) throws Exception {
		appSEM.setEstadoActual(false);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sem.guardarEstacionamiento(estacionamiento);
        appSEM.alertaDeInicioEstacionamiento(patente, sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertFalse(allWrittenLines.contains("No finalizo el estacionamiento"));
	}
	
	public void testalertaDeFinEstacionamientoEstadoActualVerdaderoYNoHayEstacionamiento(String patente, SEM sem) throws Exception {
		appSEM.setEstadoActual(false);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        appSEM.alertaDeInicioEstacionamiento(patente, sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertFalse(allWrittenLines.contains("No finalizo el estacionamiento"));
	}
	
	public void testalertaDeFinEstacionamientoEstadoActualFalsoYNoHayEstacionamiento(String patente, SEM sem) throws Exception {
		appSEM.setEstadoActual(false);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        appSEM.alertaDeInicioEstacionamiento(patente, sem);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertFalse(allWrittenLines.contains("No finalizo el estacionamiento"));
	}

	public void testIniciarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		appSEM.setEstadoActual(false);
		appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testIniciarEstacionamientoAutomaticoNotificacion(SEM sem, int numeroCelular,String patente) throws Exception {
		appSEM.setEstadoActual(false);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray()); 
        assertTrue(allWrittenLines.contains("El estacionamiento a iniciado"));
	}

	public void testIniciarEstacionamientoAutomaticoConEstacionamineto(SEM sem, int numeroCelular,String patente) {
		appSEM.setEstadoActual(false);
		sem.setEstacionamiento(estacionamiento);
		appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testIniciarEstacionamientoAutomaticoCasoFalsoConEstacionamineto(SEM sem, int numeroCelular,String patente) {
		appSEM.setEstadoActual(true);
		sem.setEstacionamiento(estacionamiento);
		appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	

	public void testFinalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
		appSEM.setEstadoActual(true);
		appSEM.finalizarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(0, sem.getEstacionamientos().size());
	}
	
	public void testFinalizarEstacionamientoAutomaticoConEstacionamineto(SEM sem, int numeroCelular,String patente) {
		appSEM.setEstadoActual(false);
		sem.setEstacionamiento(estacionamiento);
		appSEM.finalizarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	
	public void testFinalizarEstacionamientoAutomaticoCasoFalsoConEstacionamineto(SEM sem, int numeroCelular,String patente) {
		appSEM.setEstadoActual(true);
		sem.setEstacionamiento(estacionamiento);
		appSEM.iniciarEstacionamientoAutomatico(sem, numeroCelular, patente);
		assertEquals(1, sem.getEstacionamientos().size());
	}
	

	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test
	void mainTestIniciarEstacionamientoAutomatico() {
		testIniciarEstacionamientoAutomatico(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestIniciarEstacionamientoAutomaticoNotificacion() throws Exception {
		testIniciarEstacionamientoAutomaticoNotificacion(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestIniciarEstacionamientoAutomaticoConEstacionamineto() throws Exception {
		testIniciarEstacionamientoAutomaticoConEstacionamineto(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestIniciarEstacionamientoAutomaticoCasoFalsoConEstacionamineto() throws Exception {
		testIniciarEstacionamientoAutomaticoCasoFalsoConEstacionamineto(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestFinalizarEstacionamientoAutomatico() {
		testFinalizarEstacionamientoAutomatico(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestFinalizarEstacionamientoAutomaticoConEstacionamineto() throws Exception {
		testFinalizarEstacionamientoAutomaticoConEstacionamineto(sem, 123456789, "AA-12-BB");
	}
	
	@Test
	void mainTestFinalizarEstacionamientoAutomaticoCasoFalsoConEstacionamineto() throws Exception {
		testFinalizarEstacionamientoAutomaticoCasoFalsoConEstacionamineto(sem, 123456789, "AA-12-BB");
	}
	

	@Test
	void maintestalertaDeFinEstacionamientoEstadoActualFalsoYHayEstacionamiento() throws Exception {
		testalertaDeFinEstacionamientoEstadoActualFalsoYHayEstacionamiento("AA-12-BB", sem);
	}
	
	@Test
	void maintestalertaDeFinEstacionamientoEstadoActualVerdaderoYNoHayEstacionamiento() throws Exception {
		testalertaDeFinEstacionamientoEstadoActualVerdaderoYNoHayEstacionamiento("AA-12-BB", sem);
	}
	
	@Test
	void maintestalertaDeFinEstacionamientoEstadoActualFalsoYNoHayEstacionamiento() throws Exception {
		testalertaDeFinEstacionamientoEstadoActualFalsoYNoHayEstacionamiento("AA-12-BB", sem);
	}
	
	@Test
	void maintestAlertaDeInicioEstacionamiento_ConEstadoActualFalse() throws Exception {
		testAlertaDeInicioEstacionamiento_ConEstadoActualFalse();
	}
	
	@Test
	void maintestAlertaDeInicioEstacionamiento_ConEstadoActualTrue() throws Exception {
		testAlertaDeInicioEstacionamiento_ConEstadoActualTrue();
	}
	
	@Test
	void maintestAlertaDeInicioEstacionamiento_ConEstadoActualFalseYHayUnEstacionamiento() throws Exception {
		testAlertaDeInicioEstacionamiento_ConEstadoActualFalseYHayUnEstacionamiento();
	}
	
	@Test
	void mainTestSolicitarSaldoSEM() {
		testSolicitarSaldoSEM();
	}
	
	@Test
	void mainTestSetEstadoActual_estadoAnteriorTrue() {
		testSetEstadoActual_estadoAnteriorTrue();
	}
	
	void maintestSetEstadoActual_estadoActualTrue() {
		testSetEstadoActual_estadoActualTrue();
	}
	
	@Test
	void maintestcambiarModoAppCasoFalso() {
		testcambiarModoAppCasoFalso();
	}
	
	@Test
	void maintestcambiarModoAppCasoVerdadero() {
		testcambiarModoAppCasoVerdadero();
	}
	
	@Test
	void mainTestIniciarEstacionamientoNotificacionNegativa() throws Exception {
		testIniciarEstacionamientoNotificacionNegativa("AA-12-BB",8, sem,111111111);
	}
	
	
}
