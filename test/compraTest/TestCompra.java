package compraTest;
import static org.mockito.Mockito.*;
import java.util.Calendar;
import org.junit.Test;

import compra.Compra;
import espaciosFisicos.PuntoDeVenta;

public class TestCompra {
	
	@Test
	public void getCarga() {
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		Calendar fechaYHoraCompra = mock(Calendar.class);
		Compra cargaV = new Compra("11-A4", puntoDeVenta, fechaYHoraCompra, 12);
	}
}
