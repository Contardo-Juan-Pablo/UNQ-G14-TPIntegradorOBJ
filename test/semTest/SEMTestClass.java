package semTest;
import java.util.ArrayList;
import java.util.Map;
import Compra.CargaVirtual;
import Compra.Compra;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import SEM.Entidad;
import SEM.Infraccion;
import SEM.SEM;

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
	
	public int getHoraActual() {
		return horaActual;
	}
	
	public ArrayList<Infraccion> getInfraccionesLabradas() {
		return infraccionesLabradas;
	}
	
	
}
