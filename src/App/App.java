package App;
import java.util.Calendar;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class App {
	
	public int solicitarSaldoSEM(SEM sem, int numeroCelular) {
		return sem.saldoCelular(numeroCelular);
	}
	
	public void IniciarEstacionamiento(String patente, int horasReservadas, SEM sem, int numeroCelular) {
		int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int saldoActual = solicitarSaldoSEM(sem,numeroCelular);
		int costoActual = sem.costoActualPorHoraEnFranjaHorario(horaActual, horasReservadas);
		int horaSalida = 0;
		try {
			if(saldoActual>0) {
				if(saldoActual >= costoActual) {
					if(horaActual+horasReservadas>=20) {
						horaSalida = 20;
					}
					else {
						horaSalida = horaActual+horasReservadas;
					}
				}
				else {
					int horaPermitidas = (costoActual - saldoActual)/40;
					horaSalida = horaActual+(horasReservadas - horaPermitidas);
				}
				
				sem.actualizarSaldo(numeroCelular, sem.costoActualPorHora(horaActual, horasReservadas));
				EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horaActual, horaActual + horasReservadas, numeroCelular);
				sem.guardarEstacionamiento(estacionamiento);
				
				System.out.printf("Hora inicial: %02d:%02d:%02d %n", horaActual, Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND));
				System.out.printf("Hora final: %02d %n", horaSalida);
			}
			else {
				System.out.println("Saldo insuficiente. Estacionamiento no permitido.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarEstacionamiento(SEM sem, int numeroCelular) {
		sem.terminarEstacionamiento(numeroCelular);
	}

}