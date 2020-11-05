package Persona;
import SEM.Celular;
import SEM.SEM;

public class Inspector extends Persona {
	
	public Inspector (Celular celular) {
		super(celular);
	}
	
	public Boolean verificarPatenteEstacionamiento(String patente, SEM semActual) {
		return semActual.consultarPatenteSEM(patente);
	}

	public void enviarInfraccionSEM(String patente,SEM semActual) { 
		if(this.verificarPatenteEstacionamiento(patente, semActual)) {
			semActual.cargarInfraccion(patente);
		}	
	}
}