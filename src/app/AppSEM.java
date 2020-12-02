package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import estacionamiento.Estacionamiento;
import sem.SEM;

public class AppSEM implements MovementSensor { 
	protected Estado estadoDelUsuario;
	protected Modo modoAutomatico = Modo.DESACTIVADO;
	protected SEM semAsociado;
	protected ArrayList<String> notificationHistory = new ArrayList<String>();
	protected Integer saldoActual;
	
	/**             CONSTRUCTOR DE CLASE                  **/
	public AppSEM(Estado estadoDelUsuario, SEM semAsociado) {
		this.estadoDelUsuario = estadoDelUsuario;
		this.semAsociado = semAsociado;
	} 
 
	/**             GETTERS AND SETTERS                  **/
	public void iniciarEstacionamientoViaApp(Estacionamiento estacionamiento) {
		if(saldoActual >= this.costoActual(estacionamiento.getHorasReservadas())) {
			semAsociado.realizarDescuentoDeSaldo(estacionamiento.getNumeroCelularOrigen(),  this.costoActual(estacionamiento.getHorasReservadas()), this);
			semAsociado.guardarEstacionamiento(estacionamiento);
		} 
	}
	
	public void finalizarEstacionamientoViaApp(Integer numeroCelular) {		
		semAsociado.terminarEstacionamiento(numeroCelular);
	}
	
	public void iniciarEstacionamientoAutomatico(Estacionamiento estacionamiento) {
		if(estadoDelUsuario == Estado.CAMINANDO && !semAsociado.hayEstacionamientoVigenteConPatente(estacionamiento.getPatente())) {
			iniciarEstacionamientoViaApp(estacionamiento);
			lanzarAlerta("La solicitud de inicio de estacionamiento fue enviada");
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		if(estadoDelUsuario == Estado.MANEJANDO && sem.hayEstacionamientoVigenteConPatente(patente)) {
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

	public void actualizarSaldo(int nuevoSaldo) {
		saldoActual = nuevoSaldo;
	}
	
	public int costoActual(int horasReservadas) {
		return Estacionamiento.costoActualPorHoraEnFranjaHorario(horasReservadas);
	}
}