package semTest;
import java.util.ArrayList;
import app.AppSEM;
import compra.CargaVirtual;
import compra.CompraPuntual;
import espaciosFisicos.Zona;
import estacionamiento.EstacionamientoGeneral;
import sem.Entidad;
import sem.Infraccion;
import sem.SEM;

public class SEMTestClass extends SEM {
	private int horaActual = 0;
	
	public SEMTestClass(ArrayList<CompraPuntual> comprasRealizadas, ArrayList<CargaVirtual> cargasRealizadas,
			ArrayList<Zona> zonasConSEM, ArrayList<AppSEM> appSEMAsociadas,
			ArrayList<Infraccion> infraccionesLabradas, ArrayList<EstacionamientoGeneral> estacionamientos,
			ArrayList<Entidad> entidadesParticipantes) {
		super(comprasRealizadas, cargasRealizadas, zonasConSEM, appSEMAsociadas, infraccionesLabradas, estacionamientos,
				entidadesParticipantes);
	}

	public ArrayList<EstacionamientoGeneral> getEstacionamientos() {
		return estacionamientos;
	}
	
	public ArrayList<CompraPuntual> getComprasRealizadas() {
		return comprasRealizadas;
	}
	
	public ArrayList<CargaVirtual> getCargasRealizadas() {
		return cargasRealizadas;
	}
	public int getHoraActual() {
		return horaActual;
	}
	
	public ArrayList<Infraccion> getInfraccionesLabradas() {
		return infraccionesLabradas;
	}

	public void setHoraActual(int horaActual) {
		this.horaActual = horaActual;
	}
	
	public void añadirZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public ArrayList<Entidad> getEntidadesParticipantes() {
		return entidadesParticipantes;
	}
	
	public void addAppSem(AppSEM appSem) {
		appSEMAsociadas.add(appSem);
	}
}
