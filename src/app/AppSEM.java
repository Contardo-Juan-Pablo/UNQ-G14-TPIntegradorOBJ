package app;
import estacionamiento.EstacionamientoGeneral;
import estacionamiento.EstacionamientoViaApp;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import sem.SEM;

public class AppSEM implements MovementSensor, ObserverAppSEM { 
	protected Estado estadoDelUsuario;
	protected Modo modoAutomatico = Modo.DESACTIVADO;
	protected SEM semAsociado;
	protected UpdateHour updateHour;
	protected ArrayList<String> notificationHistory = new ArrayList<String>();
	protected Integer saldoActual = 0;
	
	
	/**             CONSTRUCTOR DE CLASE                  **/
	public AppSEM(Estado estadoDelUsuario, SEM semAsociado, UpdateHour updateHour) {
		this.estadoDelUsuario = estadoDelUsuario;
		this.semAsociado = semAsociado;
		this.updateHour = updateHour;
	} 
 
	/**             GETTERS AND SETTERS                  **/
	public void iniciarEstacionamientoViaApp(EstacionamientoGeneral estacionamiento) {
		int costoMaximo = cantidadDeHorasConSaldoDispónible(40);
		if(saldoActual > costoMaximo) {
			semAsociado.guardarEstacionamiento(estacionamiento);
			lanzarAlerta("Se ha registrado un estacionamiento, el número de horas disponible con su saldo es de:" + cantidadDeHorasConSaldoDispónible(costoMaximo));
		} else {
			lanzarAlerta("Saldo insuficiente. Estacionamiento no permitido");
		}
	}
	
	public void finalizarEstacionamientoViaApp(Integer numeroCelular) {		
		semAsociado.realizarDescuentoDeSaldo(costoActual(updateHour.getHoraDeEstacionamientoActual()) ,this);
		semAsociado.terminarEstacionamiento(numeroCelular);
		updateHour.resetearContadorDeHorasEstacionamientoAutomatico();
		lanzarAlerta("Se finalizó el estacionamiento. Costo total:" + costoActual(updateHour.getHoraDeEstacionamientoActual()));
	}
	
	public void iniciarEstacionamientoAutomatico(EstacionamientoViaApp estacionamiento) {
		if(estadoDelUsuario == Estado.CAMINANDO && !semAsociado.hayEstacionamientoVigenteConPatente(estacionamiento.getPatente())) {
			iniciarEstacionamientoViaApp(estacionamiento);
			lanzarAlerta("La solicitud de inicio de estacionamiento fue enviada");
			updateHour.emepezarServicio();
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		
		if((estadoDelUsuario == Estado.MANEJANDO && sem.hayEstacionamientoVigenteConPatente(patente))) {
			finalizarEstacionamientoViaApp(numeroCelular);
			lanzarAlerta("La solicitud de finalización de estacionamiento fue enviada");
		} 
		
		if(updateHour.excedioLaCantidadDeHorasMaxima()) {
			finalizarEstacionamientoViaApp(numeroCelular);
			lanzarAlerta("Su saldo se agotó, por favor vuelva a su vehículo");
		}
	}
	
	public void lanzarAlerta(String mensaje) {
		notificationHistory.add(mensaje + "Hora actual :" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()).toString());
	}
	
	@Override
	public void driving() {
		estadoDelUsuario = Estado.MANEJANDO;
	}

	@Override
	public void walking() {
		estadoDelUsuario = Estado.CAMINANDO;	
	}

	public void actualizarSaldo(int nuevoSaldo) {
		saldoActual = nuevoSaldo;
	}
	
	public int costoActual(int horasReservadas) {
		return EstacionamientoGeneral.costoActualPorHoraEnFranjaHorario(horasReservadas);
	}

	public int cantidadDeHorasConSaldoDispónible(int valorDelEstacionamiento) {
		return saldoActual / valorDelEstacionamiento;
	}

	public void finalizarEstacionamientoHoraMaximaSEM() {
		semAsociado.realizarDescuentoDeSaldo(costoActual(updateHour.getHoraDeEstacionamientoActual()) ,this);
		updateHour.resetearContadorDeHorasEstacionamientoAutomatico();
		lanzarAlerta("Finalizo el servicio SEM");
	}

	public void cambiarModoAutomatico() {
		modoAutomatico = modoAutomatico.siguienteEstado();
	}
	
	public int getSaldoActual() {
		return saldoActual;
	}
}