package App;
import SEM.SEM;

public class CelularUser extends Celular implements MovementSensor {
	protected AppSEM appSEMInstalada;
	private GPS gps;
	
	public CelularUser(int numeroCelular, AppSEM appSEMInstalada, GPS gps) {
		super(numeroCelular);
		this.appSEMInstalada = appSEMInstalada;
		this.gps = gps;
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
	
	public void driving() {
		appSEMInstalada.setEstadoActual(true);
		
	}
	
	public void walking() {
		appSEMInstalada.setEstadoActual(false);
	}

	public void cambiarModoViaApp() {
		appSEMInstalada.cambiarModoApp();
		
	}
}