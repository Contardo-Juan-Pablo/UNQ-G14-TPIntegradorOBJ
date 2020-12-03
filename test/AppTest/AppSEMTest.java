package AppTest;
import estacionamiento.EstacionamientoGeneral;
import estacionamiento.EstacionamientoViaApp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import java.util.AbstractMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import app.Estado;
import app.Modo;
import sem.SEM;

public class AppSEMTest {
	private UpdateHourTestClass updateHour;
	private AppSEMTestClass appSEM;
	private int numeroCelular;
	private SEM semMock;
	private String patente;

	
	@Before
	public void setUp() throws Exception {
		semMock = mock(SEM.class);
		updateHour = new UpdateHourTestClass();
		appSEM = new AppSEMTestClass(Estado.CAMINANDO, semMock, updateHour);
		patente = "HDT";
		numeroCelular = 1234;
		
	}
	
	@Test
	public void testIniciarEstacionamientoViaApp() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular); 		
		appSEM.actualizarSaldo(9999);
		appSEM.iniciarEstacionamientoViaApp(estacionamiento);
		when(semMock.getHoraActual()).thenReturn(10);

		verify(semMock).guardarEstacionamiento(estacionamiento);
	}
	
	@Test
	public void testIniciarEstacionamientoViaAppCasoNegativo() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular); 		
		appSEM.actualizarSaldo(0);
		appSEM.iniciarEstacionamientoViaApp(estacionamiento);

		verifyNoInteractions(semMock);
	}
	
	@Test
	public void testFinalizarEstacionamientoViaApp() {
		appSEM.finalizarEstacionamientoViaApp(numeroCelular);
		
		verify(semMock).terminarEstacionamiento(numeroCelular);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomatico() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular);	
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(false);
		appSEM.walking();
		appSEM.actualizarSaldo(99999);
		appSEM.iniciarEstacionamientoAutomatico(estacionamiento);

		verify(semMock, atLeast(1)).guardarEstacionamiento(estacionamiento);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomaticoCasoManejando() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular);	
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(false);
		appSEM.driving();
		appSEM.actualizarSaldo(99999);
		appSEM.iniciarEstacionamientoAutomatico(estacionamiento);

		verifyNoInteractions(semMock);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomaticoCasoFalsoConEstacionamientoExistente() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular);	
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.driving();
		appSEM.actualizarSaldo(99999);
		appSEM.iniciarEstacionamientoAutomatico(estacionamiento);

		verifyNoInteractions(semMock);
	}
	
	@Test
	public void testIniciarEstacionamientoAutomaticoCasoFalsoCaminando() {
		EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, numeroCelular);	
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.walking();
		appSEM.actualizarSaldo(99999);
		appSEM.iniciarEstacionamientoAutomatico(estacionamiento);

		verify(semMock, atLeast(0)).guardarEstacionamiento(estacionamiento);
	}
	
	
	@Test
	public void finalizarEstacionamientoAutomaticoSegundaCondicion() {
		AbstractMap.SimpleEntry<Integer, Integer> cantidadDeHoras = new AbstractMap.SimpleEntry<Integer, Integer>(6, 6);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(cantidadDeHoras);
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(false);
		appSEM.driving();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);

		verify(semMock, atLeast(1)).terminarEstacionamiento(numeroCelular);
	}
	
	@Test
	public void finalizarEstacionamientoAutomaticoSegundaCondicionFalsa() {
		AbstractMap.SimpleEntry<Integer, Integer> cantidadDeHoras = new AbstractMap.SimpleEntry<Integer, Integer>(1, 6);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(cantidadDeHoras);
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);

		verifyNoMoreInteractions(semMock);
	}
	
	@Test
	public void finalizarEstacionamientoAutomaticoPrimeraConcion() {
		AbstractMap.SimpleEntry<Integer, Integer> cantidadDeHoras = new AbstractMap.SimpleEntry<Integer, Integer>(1, 6);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(cantidadDeHoras);
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.driving();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);

		verify(semMock, atLeast(1)).terminarEstacionamiento(numeroCelular);
	}
	
	@Test
	public void finalizarEstacionamientoAutomaticoPrimeraConcionCasoCaminando() {
		AbstractMap.SimpleEntry<Integer, Integer> cantidadDeHoras = new AbstractMap.SimpleEntry<Integer, Integer>(1, 6);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(cantidadDeHoras);
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		appSEM.walking();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);

		verifyNoInteractions(semMock);
	}
	
	@Test
	public void finalizarEstacionamientoAutomaticoPrimeraConcionCasoTodoFalso() {
		AbstractMap.SimpleEntry<Integer, Integer> cantidadDeHoras = new AbstractMap.SimpleEntry<Integer, Integer>(1, 6);
		updateHour.setCantidadDeHorasEstacionamientoAutomatico(cantidadDeHoras);
		when(semMock.hayEstacionamientoVigenteConPatente(patente)).thenReturn(false);
		appSEM.walking();
		appSEM.finalizarEstacionamientoAutomatico(semMock, numeroCelular, patente);

		verifyNoInteractions(semMock);
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
		
		assertEquals(128, appSEM.getSaldoActual());
	}
	
	@Test 
	public void getSaldoActual() {

		assertEquals(0, appSEM.getSaldoActual());
	}
	
	@Test 
	public void finalizarEstacionamientoHoraMaximaSEM() {
		appSEM.actualizarSaldo(40);
		appSEM.finalizarEstacionamientoHoraMaximaSEM();
		
		verify(semMock).realizarDescuentoDeSaldo(40, appSEM);
		assertEquals(1, appSEM.getNotificationHistory().size());
	}
	
	@Test
	public void cambiarModoAutomaticoActivado() {
		appSEM.cambiarModoAutomatico();
		assertEquals(Modo.ACTIVADO, appSEM.getModoAutomatico());
	}
	
	@Test
	public void cambiarModoAutomaticoDesactivado() {
		appSEM.setModo(Modo.ACTIVADO);
		appSEM.cambiarModoAutomatico();
		assertEquals(Modo.DESACTIVADO, appSEM.getModoAutomatico());
	}
	
	@Test
	public void costoActual() {
		  MockedStatic<EstacionamientoGeneral> theMock = Mockito.mockStatic(EstacionamientoGeneral.class);
		  theMock.when(() -> EstacionamientoGeneral.getHoraActual()).thenReturn(6);
	      theMock.when(() -> EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(1)).thenCallRealMethod();
	      
		  assertEquals(40, appSEM.costoActual(1));
		
	}
}