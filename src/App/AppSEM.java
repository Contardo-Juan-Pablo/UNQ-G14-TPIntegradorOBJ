package App;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class AppSEM { 
	private boolean estadoActual;
	private boolean estadoAnterior;
	private boolean modoAutomatico = false;
	
	public AppSEM(boolean estadoActual, boolean estadoAnterior) {
		this.estadoActual = estadoActual;
		this.estadoAnterior = estadoAnterior;
	}
 
	public int solicitarSaldoSEM(SEM sem, int numeroCelular) {
		return sem.saldoCelular(numeroCelular);
	}
    
	public void IniciarEstacionamiento(String patente, int horasReservadas, SEM sem, int numeroCelular) {
		int horaActual = sem.getHoraActual();
		int saldoActual = solicitarSaldoSEM(sem,numeroCelular);
		int costoActual = sem.costoActualPorHoraEnFranjaHorario(horaActual, horasReservadas);
		int horaSalida = 0;
		
		if(saldoActual >= costoActual) {
			sem.actualizarSaldo(numeroCelular, sem.costoActualPorHora(horaActual, horasReservadas));
			EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horaActual, horaActual + horasReservadas, numeroCelular);
			sem.guardarEstacionamiento(estacionamiento);
				if (horaActual+horasReservadas>=20) {
						System.out.printf("Hora inicial: " + horaActual);
						System.out.printf("Hora final: " + 20);
			    } else {
						System.out.printf("Hora inicial: " + horaActual);
						System.out.printf("Hora final: " + horaActual+horasReservadas);
				}
		} else { System.out.println("Saldo insuficiente. Estacionamiento no permitido."); }
	}
	 
	public void finalizarEstacionamiento(SEM sem, int numeroCelular) {		
		if(sem.buscarEstacionamiento(numeroCelular) == null) {
			System.out.printf("No hay un estacionamiento");
		}
		else { sem.terminarEstacionamiento(numeroCelular); }
	}
	
	public void alertaDeInicioEstacionamiento(String patente, SEM sem) {
		if(!this.estadoActual && !sem.consultarPatenteSEM(patente)) {
			System.out.println("No inicio un estacionamiento");
		}
	}

	public void alertaDeFinEstacionamiento(String patente, SEM sem) {
		if(this.estadoActual && sem.consultarPatenteSEM(patente)) {
			System.out.println("No finalizo el estacionamiento");
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		// Cada hora se actualiza el estacionamiento. Es decir, se comprueba si el estado actual la persona cambio.
		if(this.estadoActual && sem.consultarPatenteSEM(patente)) {
			this.finalizarEstacionamiento(sem, numeroCelular);
			System.out.println("El estacionamiento a finalizado");
		}
	}
	
	public void iniciarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		// Cada hora se actualiza el estacionamiento. Es decir, se comprueba si el estado actual la persona cambio.
		if(!this.estadoActual && !sem.consultarPatenteSEM(patente)) {
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
	
	public Boolean getModoAutomatico() {
		return modoAutomatico;
	}

	public Object getEstadoAnterior() {
		return estadoAnterior;
	}

	public boolean getEstadoActual() {
		return estadoActual;
	}
}