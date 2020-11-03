package Persona;

abstract class Persona {
	String nombre;
	String celular;
	
	public Persona (String nombre, String celular) {
		this.nombre = nombre;
		this.celular = celular;
	}
 
}

class Usuario extends Persona {
	public Usuario (String nombre, String celular) {
		super(nombre, celular);
	
	}
}

class Inspector extends Persona {
	
	public Inspector (String nombre, String celular) {
		super(nombre, celular);
	
	}

}
