package testPersona;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import App.AppSEM;
import App.CelularUser;
import App.GPS;
import EspaciosFisicos.PuntoDeVenta;
import Persona.Comerciante;
import Persona.User;
import SEM.SEM;
import javafx.util.Pair;

public class testUser {
	private SEM semTest;
	private User usuario;
	private AppSEM appSEM;
	private PuntoDeVenta puntoDeVenta;
	private Comerciante comerciante;
	
	@BeforeEach
	public void setUp() { 
		semTest = new SEM();
		semTest.setHoraActual(9);
		ArrayList<Integer> list = new ArrayList<Integer>();
		puntoDeVenta = new PuntoDeVenta(list, 12, semTest);
		comerciante = new Comerciante(puntoDeVenta, "C-1");
		Pair<Integer, Integer> posicion = new Pair<Integer, Integer>(1, 2);
		GPS gps = new GPS(posicion);
		appSEM = new AppSEM(true, false);
		CelularUser celular = new CelularUser(1122334455, appSEM, gps);
		usuario = new User(celular);

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void testComprar(String patente, byte horas, Comerciante comerciante) {
		usuario.comprar(patente, horas, comerciante);
		assertEquals(1, semTest.getComprasRealizadas().size());
	}
	
	public void testCargarCelularNoCarga(int monto, Comerciante comerciante) {
		assertEquals(0, semTest.getCargasRealizadas().size());
	}
	
	public void testCargarCelular(int monto, Comerciante comerciante) {
		usuario.cargarCelular(100, comerciante);
		assertEquals(1, semTest.getCargasRealizadas().size());
	}
	
	public void testCargarCelularCorrecta(int monto, Comerciante comerciante) {
		usuario.cargarCelular(100, comerciante);
		assertEquals(100, semTest.getCargasRealizadas().get(0).getCarga());
	}
	
	public void testCambiarModo() {
		usuario.cambiarModo();
		assertEquals(true,appSEM.getModoAutomatico());
	}
	
	public void testEstacionarFalso(String patente, int horasReservadas, SEM sem) {
		assertEquals(0,semTest.getEstacionamientos().size());
	}
	
	public void testEstacionarVerdadero(String patente, int horasReservadas, SEM sem) {
		usuario.estacionar(patente, horasReservadas, sem);
		assertEquals(1,semTest.getEstacionamientos().size());
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	void maintestCambiarModo() {
		testCambiarModo();
	}
	
	@Test
	void maintestComprar() {
		testComprar("AB - 12 - CC",(byte) 4, comerciante);
	}
	
	@Test
	void maintestCargarCelularNoCarga() {
		testCargarCelularNoCarga(100, comerciante);
	}
	
	@Test
	void maintestCargarCelular() {
		testCargarCelular(100, comerciante);
	}
	
	@Test
	void maintestCargarCelularCorrecta() {
		testCargarCelularCorrecta(100, comerciante);
	}
	
	@Test
	void maintestEstacionarFalso() {
		testEstacionarFalso("AB - 12 - CC", 4, semTest);
	}
	
	@Test
	void maintestEstacionarVerdadero() {
		testEstacionarVerdadero("AB - 12 - CC", 4, semTest);
	}
}
	