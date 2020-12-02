package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import estacionamiento.Estacionamiento;
import sem.SEM;

public class AppSEM implements MovementSensor { 
	private Estado estadoDelUsuario;
	private Modo modoAutomatico = Modo.DESACTIVADO;
	private SEM semAsociado;
	private ArrayList<String> notificationHistory = new ArrayList<String>();
	private Integer saldoActual;
	
	/**             CONSTRUCTOR DE CLASE                  **/
	public AppSEM(Estado estadoDelUsuario, SEM semAsociado) {
		this.estadoDelUsuario = estadoDelUsuario;
		this.semAsociado = semAsociado;
	} 
 
	/**             GETTERS AND SETTERS                  **/
	public void iniciarEstacionamientoViaApp(String patente, Integer horasReservadas, Integer numeroCelular) {
		if(this.getSaldoActual() >= this.costoActual(horasReservadas)) {
			semAsociado.realizarDescuentoDeSaldo(numeroCelular, this.costoActual(horasReservadas));
			semAsociado.guardarEstacionamiento(new Estacionamiento(patente, horasReservadas));
		}
	}
	
	public void finalizarEstacionamientoViaApp(Integer numeroCelular) {		
		semAsociado.terminarEstacionamiento(numeroCelular);
	}
	
	public void iniciarEstacionamientoAutomatico(int numeroCelular,String patente) {
		if(getEstadoDelUsuario() == Estado.CAMINANDO && !semAsociado.hayEstacionamientoVigenteConPatente(patente)) {
			iniciarEstacionamientoViaApp(patente,1, numeroCelular);
			lanzarAlerta("La solicitud de inicio de estacionamiento fue enviada");
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		if(getEstadoDelUsuario() == Estado.MANEJANDO && sem.hayEstacionamientoVigenteConPatente(patente)) {
			finalizarEstacionamientoViaApp(numeroCelular);
			lanzarAlerta("La solicitud de finalizacion de estacionamiento fue enviada");
		}
	}
	
	
	public void lanzarAlerta(String mensaje) {
		notificationHistory.add(mensaje + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()).toString());
	}
	
	@Override
	public void driving() {
		estadoDelUsuario = Estado.MANEJANDO;
	}

	@Override
	public void walking() {
		estadoDelUsuario = Estado.CAMINANDO;	
	}
	
	public Modo getModoAutomatico() {
		return modoAutomatico;
	}

	public Estado getEstadoDelUsuario() {
		return estadoDelUsuario;
	}
	
	public ArrayList<String> getNotificationHistory() {
		return notificationHistory;
	}
	
	public Integer getSaldoActual() {
		return saldoActual;
	}

	public void actualizarSaldo(int nuevoSaldo) {
		saldoActual = nuevoSaldo;
	}
	
	public int costoActual(int horasReservadas) {
		return Estacionamiento.costoActualPorHoraEnFranjaHorario(horasReservadas);
	}
}