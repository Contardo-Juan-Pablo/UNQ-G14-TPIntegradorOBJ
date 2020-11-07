package SEM;

import java.util.Calendar;
import Estacionamiento.EstacionamientoViaApp;

public class Celular {
	private int numeroCelular;

	public int getNumero() {
		return numeroCelular;
	}

	public void IniciarEstacionamiento(String patente, int horasReservadas, SEM sem) {
		int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(sem.saldoCelular(numeroCelular) > sem.costoActualPorHora(horaActual, horasReservadas)) {
			sem.actualizarSaldo(numeroCelular, sem.costoActualPorHora(horaActual, horasReservadas));
			EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horaActual, horaActual + horasReservadas, numeroCelular);
			sem.guardarEstacionamiento(estacionamiento);
		}
	}
}