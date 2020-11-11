package testApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import App.AppSEM;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class testAppSEM {
	
	SEM sem;
	AppSEM appSEM;
	
	@BeforeEach
	public void setUp() throws Exception {
		sem = new SEM();
		appSEM = new AppSEM(true, true, true);
	}
	
	public void testSolicitarSaldoSEM(){
		sem.setCelulares(123456789, 80);
		int saldo = appSEM.solicitarSaldoSEM(sem, 123456789);
		assertEquals(saldo,80);
	}
	
	public void testIniciarEstacionamiento(String patente, int horasReservadas, int numeroCelular, int saldoInicial, int horaActual) {
		sem.setCelulares(numeroCelular, saldoInicial);
		int saldoActual = appSEM.solicitarSaldoSEM(sem,numeroCelular);
		int costoActual = sem.costoActualPorHoraEnFranjaHorario(horaActual, horasReservadas);
		int horaSalida = 0;
		
		try {
			if(saldoActual>0) {
				if(saldoActual >= costoActual && horaActual+horasReservadas>=20) {
					horaSalida = 20;
					
					assertEquals(horaSalida,20);
				}
				else if(saldoActual >= costoActual) {
					horaSalida = horaActual+horasReservadas;
					
					assertEquals(horaSalida,15);
				}
				else {
					horaSalida = horaActual + (saldoActual/40);
					
					assertEquals(horaSalida,10);
				}
				
				sem.actualizarSaldo(numeroCelular, sem.costoActualPorHora(horaActual, horasReservadas));
				EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horaActual, horaActual + horasReservadas, numeroCelular);
				sem.guardarEstacionamiento(estacionamiento);
			}
			else {
				assertEquals(saldoActual,-1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testFinalizarEstacionamiento(int numeroCelular, int horaInicio, int ultimaHora) {		
		Estacionamiento estacionamiento = sem.buscarEstacionamiento(numeroCelular);
		EstacionamientoViaApp estacionamientoViaApp = new EstacionamientoViaApp("ABC", horaInicio, ultimaHora, numeroCelular);
		int horaInicial = estacionamientoViaApp.getHoraDeInicio();
		int horaFinal = estacionamientoViaApp.getHoraDeFinalizacion();
		int horasReservadas = horaFinal - horaInicial;
		/*System.out.println("Hora inicial: " + horaInicial);
		System.out.println("Hora final: " + horaFinal);
		System.out.println("Duraciòn de horas estacionado: " + (sem.costoActualPorHoraEnFranjaHorario(horaInicial,horasReservadas)/40));
		System.out.println("Costo de horas estacionado: " + sem.costoActualPorHoraEnFranjaHorario(horaInicial,horasReservadas));*/
		sem.guardarEstacionamiento(estacionamiento);
		
		int cantidadDeElementos = sem.getEstacionamiento().size();
		if(cantidadDeElementos>0) {
			assertEquals(sem.getEstacionamiento().size(),1);
		}
		else {
			assertEquals(sem.getEstacionamiento().size(),0);
		}
	}

	
	
	@Test
	void mainTestSolicitarSaldoSEM() {
		testSolicitarSaldoSEM();
	}
	
	@Test
	void mainTestIniciarEstacionamiento_SinSaldo(){
		testIniciarEstacionamiento("ABC",1,123456789,-1,8);
	}
	
	@Test
	void mainTestIniciarEstacionamiento_ConSaldoMayorAVeinteHoras(){
		testIniciarEstacionamiento("ABC",3,123456789,120,19);
	}
	
	@Test
	void mainTestIniciarEstacionamiento_ConSaldo(){
		testIniciarEstacionamiento("ABC",2,123456789,120,13);
	}
	
	@Test
	void mainTestIniciarEstacionamiento_ConSaldoFaltante(){
		testIniciarEstacionamiento("ABC",3,123456789,40,9);
	}
	@Test
	void mainTestFinalizarEstacionamiento() {
		testFinalizarEstacionamiento(123456789,7,9);
	}
}
