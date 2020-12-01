package App;
import SEM.SEM;

public class AppSEMInspector {
	private SEM semAsociado;
	
	public Boolean consultarPatenteSEM(String patente) {
		return semAsociado.hayEstacionamientoVigenteConPatente(patente);
	}

	public void gestionarInfraccion(String patente, String codigoDeInspector) {
		semAsociado.cargarInfraccion(patente, codigoDeInspector);	
	}
}