package testApp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import App.AppSEM;
import App.CelularUser;
import App.GPS;
import Compra.CargaVirtual;
import EspaciosFisicos.PuntoDeVenta;
import SEM.SEM;
import javafx.util.Pair;

public class testCelularUser {
	CelularUser celular;
	AppSEM appSEM;
	SEM sem;
	GPS gps;
	Pair<Integer, Integer> posicion;
	CargaVirtual carga;
	ArrayList<Integer> lcontrol;
	PuntoDeVenta puntoDeVenta;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		lcontrol = new ArrayList<Integer>();
		puntoDeVenta = new PuntoDeVenta(lcontrol, 1, sem);
		carga = new CargaVirtual("aaaa" , puntoDeVenta, Calendar.getInstance(), 4000, 1543);
		posicion = new Pair<>(1, 2);
		gps = new GPS(posicion);
		appSEM = new AppSEM(true, true);
		sem = new SEM();
		sem.setHoraActual(9);
		celular = new CelularUser(1543, appSEM, gps);
		sem.registrarCarga(carga);
	}
	
	public void testGetNumero() {
		assertEquals(1543, celular.getNumero());
	}
	
	public void testSolicitarSaldoSEMViaApp() {
		assertEquals(4000, celular.solicitarSaldoSEMViaApp(sem));
	}
	
	public void testSolicitarInicioEstacionamientoSEMViaApp() throws Exception {
		celular.solicitarInicioEstacionamientoSEMViaApp("HDT", 1, sem);
        assertNotNull(sem.buscarEstacionamiento(1543));     
	}
	
	public void testSolicitarFinalizacionEstacionamientoSEMViaApp() {
		celular.solicitarInicioEstacionamientoSEMViaApp("HDT", 1, sem);
		celular.solicitarFinalizacionEstacionamientoSEMViaApp(sem);
		assertNull(sem.buscarEstacionamiento(1543));
	}
	
	public void testDriving() {
		celular.driving();
		AppSEM appInstalada = celular.getAppSEMInstalada();
		assertTrue(appInstalada.getEstadoActual());
	}
	
	public void testWalking() {
		celular.walking();
		AppSEM appInstalada = celular.getAppSEMInstalada();
		assertFalse(appInstalada.getEstadoActual());
	}
	
	public void testCambiarModoViaAppATrue() {
		celular.cambiarModoViaApp();
		AppSEM appInstalada = celular.getAppSEMInstalada();
		assertTrue(appInstalada.getModoAutomatico());
	}
	
	@Test
	void mainTestGetNumero() {
		testGetNumero();
	}
	
	@Test
	void mainTestSolicitarSaldoSEMViaApp() {
		testSolicitarSaldoSEMViaApp();
	}
	
	@Test
	void maintestSolicitarInicioEstacionamientoSEMViaApp() throws Exception {
		testSolicitarInicioEstacionamientoSEMViaApp();
	}
	
	@Test
	void mainTestSolicitarFinalizacionEstacionamientoSEMViaApp() {
		testSolicitarFinalizacionEstacionamientoSEMViaApp();
	}
	
	@Test
	void mainTestDriving() {
		testDriving();
	}
	
	@Test
	void mainTestWalking() {
		testWalking();
	}
	
	@Test
	void mainTestCambiarModoViaAppATrue() {
		testCambiarModoViaAppATrue();
	}
}
