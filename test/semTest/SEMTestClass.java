package semTest;
import java.util.ArrayList;
import java.util.Map;

import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import sem.Entidad;
import sem.Infraccion;
import sem.SEM;

public class SEMTestClass extends SEM {
	private int horaActual = 0;
	
	public SEMTestClass(ArrayList<Compra> comprasRealizadas, ArrayList<CargaVirtual> cargasRealizadas,
			ArrayList<Zona> zonasConSEM, Map<Integer, Integer> creditoAsociado,
			ArrayList<Infraccion> infraccionesLabradas, ArrayList<Estacionamiento> estacionamientos,
			ArrayList<Entidad> entidadesParticipantes) {
		super(comprasRealizadas, cargasRealizadas, zonasConSEM, creditoAsociado, infraccionesLabradas, estacionamientos,
				entidadesParticipantes);
	}

	public ArrayList<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public ArrayList<Compra> getComprasRealizadas() {
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
}
