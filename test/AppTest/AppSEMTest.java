package AppTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import App.AppSEM;
import App.Estado;
import App.Modo;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class AppSEMTest {
	private int numeroCelular;
	private int horasReservadas;
	private SEM semMock;
	private String patente;
	private AppSEM appSEM;
	
	@Before
	public void setUp() throws Exception {
		semMock = mock(SEM.class);
		semMock = new SEM();
		appSEM = new AppSEM(Estado.CAMINANDO, semMock);
		patente = "HDT";
		numeroCelular = 1234;
		horasReservadas = 3;
	}
	
	@Test
	public void testIniciarEstacionamientoViaApp() {
		appSEM.iniciarEstacionamientoViaApp(patente, horasReservadas, numeroCelular);
		appSEM.actualizarSaldo(9999);
		
		verify(semMock).realizarDescuentoDeSaldo(numeroCelular, Estacionamiento.costoActualPorHoraEnFranjaHorario(horasReservadas));
		verify(semMock).guardarEstacionamiento(new EstacionamientoViaApp(patente, horasReservadas, numeroCelular));
	}
	
	@Test
	public void testFinalizarEstacionamientoViaApp() {
		appSEM.finalizarEstacionamientoViaApp(numeroCelular);
		
		verify(semMock).terminarEstacionamiento(numeroCelular);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomatico() {
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.iniciarEstacionamientoAutomatico(numeroCelular, patente);
		
		verify(appSEM).iniciarEstacionamientoViaApp(patente, 1, numeroCelular);
		verify(appSEM).lanzarAlerta("El estacionamiento a inciado");
	}
	
	@Test
	public void testFinalizarEstacionamientoAutomatico() {
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.driving();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);
		
		verify(appSEM).finalizarEstacionamientoViaApp(numeroCelular);
		verify(appSEM).lanzarAlerta("El estacionamiento a inciado");
	}
	
	@Test
	public void testCambiarEstadoAManejando() {
		appSEM.driving();
		
		assertTrue(appSEM.getEstadoDelUsuario() == Estado.MANEJANDO);
	}
	
	@Test
	public void testCambiarEstadoAManejandoYLuegoACaminando() {
		appSEM.driving();
		appSEM.walking();
		
		assertTrue(appSEM.getEstadoDelUsuario() == Estado.CAMINANDO);
	}
	
	@Test
	public void testGetModoAutomatico() {
		assertTrue(appSEM.getModoAutomatico() == Modo.DESACTIVADO);
	}
	
	@Test
	public void testActualizarSaldo() {
		appSEM.actualizarSaldo(128);
		
		assertEquals(Integer.valueOf(128), appSEM.getSaldoActual());
	}
}