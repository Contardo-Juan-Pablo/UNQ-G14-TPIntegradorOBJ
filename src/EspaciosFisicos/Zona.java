package EspaciosFisicos;

import java.util.ArrayList;
import Persona.Inspector;

public class Zona {
	private ArrayList<PuntoDeVenta> PuntosDeVentaDisponibles;
	private Inspector InspectorAsigando;
	
	public void registrarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		PuntosDeVentaDisponibles.add(puntoDeVenta);
	}
}
