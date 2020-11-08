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
		if(sem.saldoCelular(numeroCelular) > sem.costoActualPorHora(horaActual, horasReservadas)) {
			sem.actualizarSaldo(numeroCelular, sem.costoActualPorHora(horaActual, horasReservadas));
			EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horaActual, horaActual + horasReservadas, numeroCelular);
			sem.guardarEstacionamiento(estacionamiento);
		}
	}
	
	public void finalizarEstacionamiento(SEM sem, int numeroCelular) {
		sem.terminarEstacionamiento(numeroCelular);
	}

}