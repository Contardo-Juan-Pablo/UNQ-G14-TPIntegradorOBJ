package app;
import sem.Infraccion;
import sem.SEM;

public class AppSEMInspector {
	private SEM semAsociado;
	
	/**             CONSTRUCTOR DE CLASE                  **/
	public AppSEMInspector(SEM semAsociado) {
		this.semAsociado = semAsociado;
	}

	/**             GETTERS AND SETTERS                  **/
	public Boolean consultarPatenteSEM(String patente) {
		return semAsociado.hayEstacionamientoVigenteConPatente(patente);
	}

	public void gestionarInfraccion(Infraccion infraccion) {
		semAsociado.cargarInfraccion(infraccion);	
	}
}