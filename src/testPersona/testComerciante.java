package testPersona;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers.*;
import org.mockito.internal.util.reflection.FieldSetter;

import Compra.CargaVirtual;
import EspaciosFisicos.PuntoDeVenta;
import Estacionamiento.EstacionamientoCompraFisica;
import Persona.Comerciante;
import Persona.Operador;
import SEM.SEM;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Calendar;

public class testComerciante {
	private Comerciante comerciante;
	private SEM sem;
	private PuntoDeVenta puntoDeVenta;
	
	@BeforeEach
	public void setUp() { 
		ArrayList<Integer> list = new ArrayList<Integer>();
		sem = new SEM();
		sem.setHoraActual(12);
		puntoDeVenta = new PuntoDeVenta(list, 12, sem);
		comerciante = new Comerciante(puntoDeVenta, "C-1");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// ZONA TEST 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void testGetCodigoComerciante() {
		assertEquals(comerciante.getCodigoComerciante(),"C-1");
	}

	public void testRegistrarCompraEstacionamiento(String patente, byte horas) {
		comerciante.RegistrarCompraEstacionamiento(patente, horas);
		assertEquals(true, sem.consultarPatenteSEM(patente));
	}

	public void testRegistrarCarga(int numeroDeCelular, int monto) {
		assertEquals(0, sem.getCargasRealizadas().size());
		comerciante.RegistrarCarga(numeroDeCelular, monto);
		assertEquals(1, sem.getCargasRealizadas().size());
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// MAIN TESTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	@Test
	void mainGetCodigoComerciante() {
		testGetCodigoComerciante();
	}
	
	@Test
	void mainRegistrarCompraEstacionamiento() {
		testRegistrarCompraEstacionamiento("AA-123-CC", (byte) 4);
	}
	
	@Test
	void mainRegistrarCarga() {
		testRegistrarCarga(1122334455, 400);
	}
	
}
