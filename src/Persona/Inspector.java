package Persona;
import App.CelularInspector;
import EspaciosFisicos.Zona;
import SEM.SEM;

public class Inspector {
	protected CelularInspector celular;
	Zona zonaDeInspeccion;
	int legajo;
	
	public Inspector (CelularInspector celular, Zona zonaDeInspeccion, int legajo) {
		this.celular = celular;
		this.zonaDeInspeccion = zonaDeInspeccion;
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

	public Zona zonaDeTrabajo() {
		return zonaDeInspeccion;
	}
	
}