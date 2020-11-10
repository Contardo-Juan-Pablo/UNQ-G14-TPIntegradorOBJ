package Persona;
import App.CelularUser;
import SEM.SEM;

public class User {
	protected CelularUser celular;
	
	public User(CelularUser celular) {
		this.celular = celular;
	}

	public void Comprar(String patente, byte horas, Comerciante comerciante) {
		comerciante.RegistrarCompraEstacionamiento(patente, horas);
	}
	
	public void CargarCelular(int monto, Comerciante comerciante) {
		comerciante.RegistrarCarga(celular.getNumero(), monto);
	}
	
	public void estacionar(String patente, int horasReservadas, SEM sem) {
		celular.solicitarInicioEstacionamientoSEMViaApp(patente, horasReservadas, sem);
	}
	
	public void cambiarModo() {
		celular.cambiarModoViaApp();
	}
}