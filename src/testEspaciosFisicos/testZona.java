package testEspaciosFisicos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import App.AppSEMInspector;
import App.CelularInspector;
import EspaciosFisicos.PuntoDeVenta;
import EspaciosFisicos.Zona;
import Persona.Inspector;
import SEM.SEM;
import javafx.util.Pair;

public class testZona {
	private SEM sem;
	private Zona zona;
	private AppSEMInspector appInspector;
	private CelularInspector celular;
	private Inspector inspector;
	private PuntoDeVenta puntoDeVenta;
	private ArrayList<PuntoDeVenta> puntosDeVenta;
	
	@BeforeEach
	public void setUp() throws Exception {
		sem = new SEM();
		appInspector = new AppSEMInspector(11);
		celular = new CelularInspector(1122334455, appInspector);
		inspector = new Inspector(celular, 36072);
		puntoDeVenta = new PuntoDeVenta(new ArrayList<Integer>(1), 4, sem);
		puntosDeVenta = new ArrayList<PuntoDeVenta>();
		zona = new Zona(puntosDeVenta, inspector);
	}
	
	public void testRegistrarPuntoDeVenta() {
		zona.registrarPuntoDeVenta(puntoDeVenta);
		assertEquals(1, zona.getPuntosDeVentaDisponibles().size());
	}
	
	public void testPerteneceAZona() {
		assertTrue(zona.perteneceAZona(new Pair<Integer, Integer>(4, 7)));
	}
	
	@Test
	void mainTestRegistrarPuntoDeVenta() {
		testRegistrarPuntoDeVenta();
	}
	
	@Test
	void mainTestPerteneceAZona() {
		testPerteneceAZona();
	}
}