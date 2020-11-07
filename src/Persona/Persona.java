package Persona;
import SEM.*;

abstract class Persona {
	private Celular celular;
	
	public Persona (Celular celular) {
		this.celular = celular;
	}
	
	public void Comprar(String patente, byte horas, Comerciante comerciante) {
		comerciante.RegistrarCompraEstacionamiento(patente, horas);
	}
	
	public void CargarCelular(int monto, Comerciante comerciante) {
		comerciante.RegistrarCarga(celular.getNumero(), monto);
	}
	
	public void estacionar(String patente, int horasReservadas, SEM sem) {
		celular.IniciarEstacionamiento(patente, horasReservadas, sem);
	}
}