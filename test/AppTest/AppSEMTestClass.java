package AppTest;
import java.util.ArrayList;
import app.AppSEM;
import app.Estado;
import app.Modo;
import sem.SEM;

public class AppSEMTestClass extends AppSEM {
	
	public AppSEMTestClass(Estado estadoDelUsuario, SEM semAsociado) {
		super(estadoDelUsuario, semAsociado);
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
	
	public int getSaldoActual() {
		return saldoActual;
	}

}
