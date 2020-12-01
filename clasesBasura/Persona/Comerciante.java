package Persona;
import java.util.Calendar;
import Compra.CargaVirtual;
import Compra.CompraFisica;
import EspaciosFisicos.PuntoDeVenta;
import Estacionamiento.EstacionamientoCompraFisica;

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
		String c�digoDeControl = puntoDeVenta.getCodigoDeControl();
		Calendar fechaYHoraCompra = Calendar.getInstance();
		CompraFisica compra = new CompraFisica(c�digoDeControl, puntoDeVenta, fechaYHoraCompra, horas);
		EstacionamientoCompraFisica estacionamiento = new EstacionamientoCompraFisica(patente, fechaYHoraCompra.get(Calendar.HOUR_OF_DAY), fechaYHoraCompra.get(Calendar.HOUR_OF_DAY) + horas, horas);
				puntoDeVenta.IngresarCompra(compra, estacionamiento);
	}

	public void RegistrarCarga(int numeroDeCelular, int monto) {
		String c�digoDeControl = puntoDeVenta.getId().toString() + puntoDeVenta.lastControl().toString();
		Calendar fechaYHoraDeCarga = Calendar.getInstance();
		CargaVirtual carga = new CargaVirtual(c�digoDeControl, puntoDeVenta, fechaYHoraDeCarga, monto, numeroDeCelular);
		puntoDeVenta.IngresarCarga(carga);
	}
}