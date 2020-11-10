package SEM;
import App.App;


public class Celular {
	private int numeroCelular;
	private App appSEMInstalada;
	
	public Celular (int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
	public int getNumero() {
		return numeroCelular;
	}
	
	public int solicitarSaldoSEMViaApp(SEM sem) {
		return appSEMInstalada.solicitarSaldoSEM(sem, numeroCelular);
	}
	
	public void solicitarInicioEstacionamientoSEMViaApp(String patente, int horasReservadas, SEM sem) {
		appSEMInstalada.IniciarEstacionamiento(patente, horasReservadas, sem, numeroCelular);
	}
	 
	public void solicitarFinalizacionEstacionamientoSEMViaApp(SEM sem) {
		appSEMInstalada.finalizarEstacionamiento(sem, numeroCelular);
	}
}