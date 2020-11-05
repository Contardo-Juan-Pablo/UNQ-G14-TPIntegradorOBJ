package Persona;

import java.util.Date;
import Compra.CompraFisica;
import EspaciosFisicos.PuntoDeVenta;

public class Comerciante {
	private PuntoDeVenta puntoDeVenta;
	
	public Comerciante(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}
	
	public void RegistrarPersona(String patente, byte horas) {
		String c�digoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Date fechaYHoraCompra = new Date();
		CompraFisica compra = new CompraFisica(c�digoDeControl, puntoDeVenta, fechaYHoraCompra, horas);
		puntoDeVenta.IngresarCompra(compra);
	}
}