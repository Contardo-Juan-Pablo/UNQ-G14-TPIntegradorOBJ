package App;
import java.time.LocalDateTime;

import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;
import SEM.SEM;

public class AppSEM implements MovementSensor { 
	private Estado estadoDelUsuario;
	private Modo modoAutomatico = Modo.DESACTIVADO;
	private SEM semAsociado;
	private Integer saldoActual;
	
	/**             CONSTRUCTOR DE CLASE                  **/
	public AppSEM(Estado estadoDelUsuario, SEM semAsociado) {
		this.estadoDelUsuario = estadoDelUsuario;
		this.semAsociado = semAsociado;
	} 
 
	
	/**             GETTERS AND SETTERS                  **/
	
	public void iniciarEstacionamientoViaApp(String patente, Integer horasReservadas, Integer numeroCelular) {
		Integer horaActual = LocalDateTime.now().getHour();
		Integer costoActual = Estacionamiento.costoActualPorHoraEnFranjaHorario(horaActual,horasReservadas);
		
		if(getSaldoActual() >= costoActual) {
			semAsociado.realizarDescuentoDeSaldo(numeroCelular, costoActual);
			EstacionamientoViaApp estacionamiento = new EstacionamientoViaApp(patente, horasReservadas, numeroCelular); 
			semAsociado.guardarEstacionamiento(estacionamiento);
		}
	}
	
	public void finalizarEstacionamientoViaApp(Integer numeroCelular) {		
		semAsociado.terminarEstacionamiento(numeroCelular);
	}
	
	
	public void iniciarEstacionamientoAutomatico(int numeroCelular,String patente) {
		if(getEstadoDelUsuario() == Estado.CAMINANDO && !semAsociado.hayEstacionamientoVigenteConPatente(patente)) {
			iniciarEstacionamientoViaApp(patente,1, numeroCelular);
			lanzarAlerta("El estacionamiento a inciado");
		}
	}
	
	public void finalizarEstacionamientoAutomatico(SEM sem, int numeroCelular,String patente) {
		if(getEstadoDelUsuario() == Estado.MANEJANDO && sem.hayEstacionamientoVigenteConPatente(patente)) {
			finalizarEstacionamientoViaApp(numeroCelular);
			lanzarAlerta("El estacionamiento a finalizado");
		}
	}
	
	/** Cuando se tenga de manera precisa como mostrar el mensaje el usuario, el metodo ya esta definido, solo resta agregarle comprtamiento **/
	public void lanzarAlerta(String mensaje) {}
	

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
	
	private Integer getSaldoActual() {
		return saldoActual;
	}


	

	

	

}