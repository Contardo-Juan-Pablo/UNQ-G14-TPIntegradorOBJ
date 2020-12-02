package AppTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import app.AppSEM;
import app.Estado;
import app.Modo;
import estacionamiento.Estacionamiento;
import sem.SEM;

public class AppSEMTest {
	private int numeroCelular;
	private int horasReservadas;
	private SEM semMock;
	private String patente;
	private AppSEM appSEM;
	
	@Before
	public void setUp() throws Exception {
		semMock = mock(SEM.class);
		appSEM = new AppSEM(Estado.CAMINANDO, semMock);
		patente = "HDT";
		numeroCelular = 1234;
		horasReservadas = 3;
	}
	
	@Test
	public void testIniciarEstacionamientoViaApp() {
		appSEM.actualizarSaldo(9999);
		appSEM.iniciarEstacionamientoViaApp(patente, horasReservadas, numeroCelular);
		when(semMock.getHoraActual()).thenReturn(10);

		verify(semMock).realizarDescuentoDeSaldo(numeroCelular, Estacionamiento.costoActualPorHoraEnFranjaHorario(horasReservadas));
	}
	
	@Test
	public void testFinalizarEstacionamientoViaApp() {
		appSEM.finalizarEstacionamientoViaApp(numeroCelular);
		
		verify(semMock).terminarEstacionamiento(numeroCelular);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomatico() {
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(false);
		appSEM.walking();
		appSEM.actualizarSaldo(99999);
		appSEM.iniciarEstacionamientoAutomatico(numeroCelular, patente);

		assertEquals(1, appSEM.getNotificationHistory().size());
	}
	
	@Test
	public void testFinalizarEstacionamientoAutomatico() {
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.driving();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);
		
		assertEquals(1, appSEM.getNotificationHistory().size());
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