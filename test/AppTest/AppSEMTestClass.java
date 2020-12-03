package AppTest;
import java.util.ArrayList;
import app.AppSEM;
import app.Estado;
import app.Modo;
import sem.SEM;

public class AppSEMTestClass extends AppSEM {
	
	public AppSEMTestClass(Estado estadoDelUsuario, SEM semAsociado, UpdateHourTestClass updateHour) {
		super(estadoDelUsuario, semAsociado, updateHour);
	}

	public int costoActual(int horasReservadas) {
		return 40 * horasReservadas;
	}
	
	public Modo getModoAutomatico() {
		return modoAutomatico;
	}
	
	public void setModo(Modo modo) {
		modoAutomatico = modo;
	}
	
	public Estado getEstadoDelUsuario() {
		return estadoDelUsuario;
	}
	
	public ArrayList<String> getNotificationHistory() {
		return notificationHistory;
	}
	
	

}
