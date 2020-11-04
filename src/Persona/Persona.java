package Persona;
import SEM.*;

 abstract class Persona {
	private String nombre;
	private Celular celular;
	
	
	public Persona (String nombre, Celular celular) {
		this.nombre = nombre;
		this.celular = celular;
	}
 
}

class Usuario extends Persona {
	
	public Usuario (String nombre, Celular celular) {
		super(nombre, celular);
	}
}

