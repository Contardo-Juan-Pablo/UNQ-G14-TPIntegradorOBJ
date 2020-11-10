package Persona;
import java.util.Calendar;

import Compra.CargaVirtual;
import Compra.CompraFisica;
import EspaciosFisicos.PuntoDeVenta;

public class Comerciante {
	private PuntoDeVenta puntoDeVenta;
	private String codigoComerciante;
	
	public Comerciante(PuntoDeVenta puntoDeVenta, String codigoComerciante) {
		this.puntoDeVenta = puntoDeVenta;
		this.codigoComerciante = codigoComerciante;
	}
	
	public String getCodigoComerciante() {
		return codigoComerciante;
	}

	public void RegistrarCompraEstacionamiento(String patente, byte horas) {
		String c�digoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Calendar fechaYHoraCompra = Calendar.getInstance();
		CompraFisica compra = new CompraFisica(c�digoDeControl, puntoDeVenta, fechaYHoraCompra, horas);
		puntoDeVenta.IngresarCompra(compra);
	}

	public void RegistrarCarga(int numeroDeCelular, int monto) {
		String c�digoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Calendar fechaYHoraDeCarga = Calendar.getInstance();
		CargaVirtual carga = new CargaVirtual(c�digoDeControl, puntoDeVenta, fechaYHoraDeCarga, monto, numeroDeCelular);
		puntoDeVenta.IngresarCarga(carga);
	}
}