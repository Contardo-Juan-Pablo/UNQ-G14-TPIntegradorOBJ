package SEM;

import java.time.LocalDateTime;
import EspaciosFisicos.Zona;

public class Infraccion {
	String patenteInfraccion;
	LocalDateTime horaYFechaInfraccion;
	Zona zonaInfraccion;
	String codigoInspector;
	
	public Infraccion(String patenteInfraccion, LocalDateTime horaYFechaInfraccion, Zona zonaInfraccion, String codigoInspector) {
		this.patenteInfraccion = patenteInfraccion;
		this.horaYFechaInfraccion = horaYFechaInfraccion;
		this.zonaInfraccion = zonaInfraccion;
		this.codigoInspector = codigoInspector;
	}
}