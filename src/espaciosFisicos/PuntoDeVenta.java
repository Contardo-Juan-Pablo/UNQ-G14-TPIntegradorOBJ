package espaciosFisicos;
import estacionamiento.Estacionamiento;
import java.util.ArrayList;
import compra.CargaVirtual;
import compra.CompraPuntual;
import app.AppSEM;


import sem.SEM;

public class PuntoDeVenta {
	private ArrayList<Integer> númeroDeControl;
	private Integer idPuntoDeVenta;
	private SEM sem;
	
	public PuntoDeVenta(ArrayList<Integer> númeroDeControl, Integer idPuntoDeVenta, SEM sem) {
		this.númeroDeControl = númeroDeControl;
		this.idPuntoDeVenta = idPuntoDeVenta;
		this.sem = sem;
	} 

	public void ingresarCompra(CompraPuntual compra, Estacionamiento estacionamiento) {
		sem.registrarCompra(compra);
		sem.guardarEstacionamiento(estacionamiento);
	}

	public void ingresarCarga(CargaVirtual cargaVirtual, AppSEM appSEM) {
		sem.registrarCarga(cargaVirtual, appSEM);
	}
	
	public String getCodigoDeControl() {
		return this.getId().toString() + this.lastControl().toString();
	}
	
	public Integer lastControl() {
		return Integer.valueOf(númeroDeControl.size());
	}

	public Integer getId() {
		return idPuntoDeVenta;
	}
}