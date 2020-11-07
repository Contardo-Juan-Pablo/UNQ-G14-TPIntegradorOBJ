package SEM;

import java.util.Calendar;
import EspaciosFisicos.Zona;
import Persona.Inspector;

public class Infraccion {
	String patenteInfraccion;
	Calendar horaYFechaInfraccion;
	Zona zonaInfraccion;
	Inspector inspector;
	
	public Infraccion(String patenteInfraccion, Calendar horaYFechaInfraccion, Zona zonaInfraccion, Inspector inspector) {
		this.patenteInfraccion = patenteInfraccion;
		this.horaYFechaInfraccion = horaYFechaInfraccion;
		this.zonaInfraccion = zonaInfraccion;
		this.inspector = inspector;
	}
}