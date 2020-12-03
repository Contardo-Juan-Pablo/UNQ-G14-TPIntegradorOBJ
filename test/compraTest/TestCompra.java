package compraTest;
import static org.mockito.Mockito.*;
import java.util.Calendar;
import org.junit.Test;

import compra.CompraPuntual;
import espaciosFisicos.PuntoDeVenta;

public class TestCompra {
	CompraPuntual carga;
	
	@Test
	public void getCarga() {
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		Calendar fechaYHoraCompra = mock(Calendar.class);
		carga = new CompraPuntual("11-A4", puntoDeVenta, fechaYHoraCompra, 12);
	}
}
