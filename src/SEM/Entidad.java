package sem;

import java.util.ArrayList;

public class Entidad {
	private boolean estadoSubscripto = false;
	private ArrayList<Informe> informes;
	
	public void serNotificado() {
		Informe informe = new Informe();
		informes.add(informe);
	}

	public boolean isEstadoSubscripto() {
		return estadoSubscripto;
	}
}