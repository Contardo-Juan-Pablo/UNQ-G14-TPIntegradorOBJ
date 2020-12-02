package sem;
import java.time.LocalDateTime;
import espaciosFisicos.Zona;

public class Infraccion  {
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