package SEM;
import java.util.Calendar;
import EspaciosFisicos.Zona;

public class Infraccion  {
	String patenteInfraccion;
	Calendar horaYFechaInfraccion;
	Zona zonaInfraccion;
	String codigoInspector;
	
	public Infraccion(String patenteInfraccion, Calendar horaYFechaInfraccion, Zona zonaInfraccion, String codigoInspector) {
		this.patenteInfraccion = patenteInfraccion;
		this.horaYFechaInfraccion = horaYFechaInfraccion;
		this.zonaInfraccion = zonaInfraccion;
		this.codigoInspector = codigoInspector;
	}
}