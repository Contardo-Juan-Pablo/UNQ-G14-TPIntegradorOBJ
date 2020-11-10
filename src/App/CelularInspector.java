package App;
import Persona.Inspector;
import SEM.SEM;

public class CelularInspector extends Celular {
	protected AppSEMInspector appSEMInstalada;
	
	public CelularInspector(int numeroCelular, AppSEMInspector appSEMInstalada) {
		super(numeroCelular);
		this.appSEMInstalada = appSEMInstalada;
	}

	public Boolean consultarPatenteApp(SEM semActual, String patente) {
		return appSEMInstalada.consultarPatenteSEM(patente,semActual);
	}

	public void gestionarInfraccion(String patente, Inspector inspector, SEM semActual) {
		appSEMInstalada.gestionarInfraccion(patente, inspector,semActual);
		
	}
}



