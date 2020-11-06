package Persona;

import EspaciosFisicos.Zona;
import SEM.Celular;
import SEM.SEM;

public class Inspector extends Persona {
	Zona zonaDeInspeccion;
	int legajo;
	
	public Inspector (Celular celular, Zona zonaDeInspeccion, int legajo) {
		super(celular);
		this.zonaDeInspeccion = zonaDeInspeccion;
		this.legajo = legajo;
	}
	
	public Boolean verificarPatenteEstacionamiento(String patente, SEM semActual) {
		return semActual.consultarPatenteSEM(patente);
	}

	public void enviarInfraccionSEM(String patente,SEM semActual) { 
		if(this.verificarPatenteEstacionamiento(patente, semActual)) {
			semActual.cargarInfraccion(patente, this);
		}	
	}

	public Zona zonaDeTrabajo() {
		return zonaDeInspeccion;
	}
}