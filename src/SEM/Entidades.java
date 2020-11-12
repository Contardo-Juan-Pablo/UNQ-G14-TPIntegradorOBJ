package SEM;

import java.util.ArrayList;

public class Entidades {
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