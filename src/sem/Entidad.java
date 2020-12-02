package sem;
import java.util.ArrayList;

public class Entidad implements Observer {
	protected ArrayList<String> informes;

	
	public Entidad(ArrayList<String> informes) {
		this.informes = informes;
	}

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