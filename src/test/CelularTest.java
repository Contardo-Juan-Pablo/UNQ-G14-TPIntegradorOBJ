package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SEM.Celular;

public class CelularTest {
	
	private Celular celular;
	
	@BeforeEach
	public void setUp() throws Exception {
		celular = new Celular(123456789);
	}
	
	public void testGetNumero() {
		int intAmount = celular.getNumero();
		assertEquals(intAmount,123456789);
	}
		
	@Test
	void test() throws Exception {
		testGetNumero();
	}

}