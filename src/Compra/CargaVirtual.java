package Compra;

public class CargaVirtual {
	private int montoDeRecarga;
	private int celular;
	
	public CargaVirtual(int montoDeRecarga, int numeroDeCelular) {
		this.montoDeRecarga = montoDeRecarga;
		this.celular = numeroDeCelular;
	}
	
	public int getCarga() {
		return montoDeRecarga;
	}

	public int getCelular() {
		return celular;
	}
}