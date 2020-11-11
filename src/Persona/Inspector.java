package Persona;
import App.CelularInspector;
import EspaciosFisicos.Zona;
import SEM.SEM;

public class Inspector {
	protected CelularInspector celular;
	int legajo;
	
	public Inspector (CelularInspector celular, int legajo) {
		this.celular = celular;
		this.legajo = legajo;
	}
	
	public Boolean verificarPatenteEstacionamientoViaApp(String patente, SEM semActual) {
		return celular.consultarPatenteApp(semActual, patente);
		
	}
	
	public void enviarInfraccionViaApp(String patente,SEM semActual) {
		if(this.verificarPatenteEstacionamientoViaApp(patente, semActual)) {
			celular.gestionarInfraccion(patente, this, semActual);
		}	
	}
	
}