package Persona;

import java.util.Calendar;
import Compra.CargaVirtual;
import Compra.CompraFisica;
import EspaciosFisicos.PuntoDeVenta;

public class Comerciante {
	private PuntoDeVenta puntoDeVenta;
	
	
	public Comerciante(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}
	
	public void RegistrarCompraEstacionamiento(String patente, byte horas) {
		String códigoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Calendar fechaYHoraCompra = Calendar.getInstance();
		CompraFisica compra = new CompraFisica(códigoDeControl, puntoDeVenta, fechaYHoraCompra, horas);
		puntoDeVenta.IngresarCompra(compra);
	}

	public void RegistrarCarga(int numeroDeCelular, int monto) {
		String códigoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Calendar fechaYHoraDeCarga = Calendar.getInstance();
		CargaVirtual carga = new CargaVirtual(códigoDeControl, puntoDeVenta, fechaYHoraDeCarga, monto, numeroDeCelular);
		puntoDeVenta.IngresarCarga(carga);
	}
}