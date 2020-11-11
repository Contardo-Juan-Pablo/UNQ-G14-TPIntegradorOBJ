package testApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import App.AppSEM;
import SEM.SEM;

public class testAppSEM {
	
	SEM Sem;
	AppSEM appSEM;
	
	@BeforeEach
	public void setUp() throws Exception {
		Sem = new SEM();
		appSEM = new AppSEM(true, true, true);
	}
	public void testSolicitarSaldoSEM(){
		Sem.setCelulares(123456789, 80);
		int saldo = appSEM.solicitarSaldoSEM(Sem, 123456789);
		assertEquals(saldo,80);
	}
	
	public void IniciarEstacionamiento() {
		
	}
	
	@Test
	void test() {
		testSolicitarSaldoSEM();
	}

}
