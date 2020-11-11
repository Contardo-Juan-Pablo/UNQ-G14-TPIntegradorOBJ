package EspaciosFisicos;

import java.util.ArrayList;
import Persona.Inspector;
import javafx.util.Pair;

public class Zona {
	private ArrayList<PuntoDeVenta> PuntosDeVentaDisponibles;
	private Inspector InspectorAsigando;
	
	public Zona(ArrayList<PuntoDeVenta> puntosDeVentaDisponibles, Inspector inspectorAsigando) {
		this.PuntosDeVentaDisponibles = puntosDeVentaDisponibles;
		this.InspectorAsigando = inspectorAsigando;
	}
	
	public void registrarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		PuntosDeVentaDisponibles.add(puntoDeVenta);
	}

	public Inspector getInspectorAsigando() {
		return InspectorAsigando;
	}
	
	public Boolean perteneceAZona(Pair<Integer,Integer> posicion) {
		return true;
	}
	
	public boolean contieneAlInspector(Inspector inspector) {
		return inspector == InspectorAsigando;
	}
	
}
