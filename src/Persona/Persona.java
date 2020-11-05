package Persona;
import EspaciosFisicos.PuntoDeVenta;
import SEM.*;

abstract class Persona {
	private Celular celular;
	private PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
	private Comerciante comerciante = new Comerciante(puntoDeVenta);
	
	public Persona (Celular celular) {
		this.celular = celular;
	}
	
	public void Comprar(String patente, byte horas) {
		comerciante.RegistrarPersona(patente, horas);
	}
}