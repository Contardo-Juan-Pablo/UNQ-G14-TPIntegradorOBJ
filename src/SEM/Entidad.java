package SEM;

import java.util.ArrayList;

public class Entidad implements Observer{
	private ArrayList<String> informes;
	
	public void update(String informe) {
		informes.add(informe);
	}
	
	public void suscribirseA(SEM sem) {
		sem.a�adirEntidad(this);
	}
	
	public void desuscribirse(SEM sem) {
		sem.retirar(this);
	}
}