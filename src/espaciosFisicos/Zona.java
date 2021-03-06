package espaciosFisicos;

import java.util.ArrayList;

public class Zona {
	private ArrayList<PuntoDeVenta> puntosDeVentaDisponibles;
	private String codigoInspector;
	
	public Zona(ArrayList<PuntoDeVenta> puntosDeVentaDisponibles, String codigoInspector) {
		this.puntosDeVentaDisponibles = puntosDeVentaDisponibles;
		this.codigoInspector = codigoInspector;
	}
	
	public void registrarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		puntosDeVentaDisponibles.add(puntoDeVenta);
	}

	public String getInspectorAsigando() {
		return codigoInspector;
	}
	
	public boolean contieneAlInspector(String inspector) {
		return inspector == getInspectorAsigando();
	}

	public ArrayList<PuntoDeVenta> getPuntosDeVentaDisponibles() {
		return puntosDeVentaDisponibles;
	}
	
}
