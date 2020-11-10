package App;
import java.util.Calendar;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import Persona.Inspector;
import SEM.SEM;


public class AppSEM { 
	private boolean estadoActual;
	private boolean estadoAnterior;
	private boolean modoAutomatico = false;
	
	public AppSEM(boolean estadoActual, boolean estadoAnterior, boolean modoAutomatico) {
		super();
		this.estadoActual = estadoActual;
		this.estadoAnterior = estadoAnterior;
		this.modoAutomatico = modoAutomatico;
	}

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
				if(saldoActual >= costoActual && horaActual+horasReservadas>=20) {
					horaSalida = 20;
				}
				else if(saldoActual >= costoActual) {
					horaSalida = horaActual+horasReservadas;
				}
				else {
					horaSalida = horaActual + (saldoActual/40);
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
		Estacionamiento estacionamiento = sem.buscarEstacionamiento(numeroCelular);
		int horaInicial = estacionamiento.getHoraDeInicio();
		int horaFinal = estacionamiento.getHoraDeFinalizacion();
		int horasReservadas = horaFinal - horaInicial;
		System.out.println("Hora inicial: " + horaInicial);
		System.out.println("Hora final: " + horaFinal);
		System.out.println("Duraciòn de horas estacionado: " + (sem.costoActualPorHoraEnFranjaHorario(horaInicial,horasReservadas)/40));
		System.out.println("Costo de horas estacionado: " + sem.costoActualPorHoraEnFranjaHorario(horaInicial,horasReservadas));
		sem.guardarEstacionamiento(estacionamiento);
	}
	
	public void alertaDeInicioEstacionamiento(String patente, SEM sem) {
		if(this.estadoAnterior && ! this.estadoActual && ! sem.consultarPatenteSEM(patente)) {
			System.out.println("No inicio un estacionamiento");
		}
	}
	
	public void alertaDeFinEstacionamiento(String patente, SEM sem) {
		if(!this.estadoAnterior && this.estadoActual && sem.consultarPatenteSEM(patente)) {
			System.out.println("No finalizo el estacionamiento");
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		// Cada hora se actualiza el estacionamiento. Es decir, se comprueba si el estado actual la persona cambio.
		if(!this.estadoAnterior && this.estadoActual && sem.consultarPatenteSEM(patente)) {
			this.finalizarEstacionamiento(sem, numeroCelular);
			System.out.println("El estacionamiento a finalizado");
		}
	}
	
	public void iniciarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		// Cada hora se actualiza el estacionamiento. Es decir, se comprueba si el estado actual la persona cambio.
		if(this.estadoAnterior && ! this.estadoActual && ! sem.consultarPatenteSEM(patente)) {
			this.IniciarEstacionamiento(patente,1, sem, numeroCelular);
			System.out.println("El estacionamiento a iniciado");
		}
	}

	
	public void setEstadoActual(boolean b) {
		estadoActual = b;
		estadoAnterior = !b;
	}

	public void cambiarModoApp() {
		this.modoAutomatico = !modoAutomatico;
	}	
}