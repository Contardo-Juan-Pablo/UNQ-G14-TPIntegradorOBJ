package EspaciosFisicos;

import java.util.ArrayList;
import Persona.Inspector;

public class Zona {
	private ArrayList<PuntoDeVenta> PuntosDeVentaDisponibles;
	private Inspector InspectorAsigando;
	
	public Zona(ArrayList<PuntoDeVenta> puntosDeVentaDisponibles, Inspector inspectorAsigando) {
		PuntosDeVentaDisponibles = puntosDeVentaDisponibles;
		InspectorAsigando = inspectorAsigando;
	}
	
	public void registrarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		PuntosDeVentaDisponibles.add(puntoDeVenta);
	}

	public Inspector getInspectorAsigando() {
		return InspectorAsigando;
	}
	
	
}
