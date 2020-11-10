package App;
import Persona.Inspector;
import SEM.SEM;


public class AppSEMInspector {
	int codigoDeApp;
	
	public AppSEMInspector(int codigoDeApp) {
		this.codigoDeApp = codigoDeApp;
	}
	
	public Boolean consultarPatenteSEM(String patente, SEM semActual) {
		return semActual.consultarPatenteSEM(patente);
	}

	public void gestionarInfraccion(String patente, Inspector inspector, SEM semActual) {
		semActual.cargarInfraccion(patente, inspector);	
	} 
	
}