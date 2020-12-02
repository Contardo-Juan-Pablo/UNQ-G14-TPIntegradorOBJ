package sem;
import java.util.ArrayList;

public class Entidad implements Observer {
	private ArrayList<String> informes;

	@Override
	public void update(String informe) {
		informes.add(informe);		
	}
	
	public void suscribirse(SEM sem) {
		sem.registrarEntidad(this);
	}
	
	public void desuscribirse(SEM sem) {
		sem.retirarEntidad(this);
	}
}