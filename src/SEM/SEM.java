package SEM;
import java.util.ArrayList;
import java.util.Calendar;

import Compra.CargaVirtual;
import Compra.Compra;
import Compra.CompraFisica;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Persona.Inspector;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	// private ArrayList<???> celularesRegistrados;
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> estacionamientos;
	
	public void RegistrarZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public void cargarInfraccion(String patente, Inspector inspector) {
		Calendar fechaYHoraActual = Calendar.getInstance();
		Zona zonaDeInfraccion = inspector.zonaDeTrabajo();
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, inspector);
		
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public boolean consultarPatenteSEM(String patente) {
		Boolean patenteVigenteEncontrada = false;
		
		for(int i=0; i < estacionamientos.size(); i++){
			if(estacionamientos.get(i).getPatente() == patente) {
				patenteVigenteEncontrada = patenteVigenteEncontrada || true && estacionamientos.get(i).estaVigente();
			}
			else { patenteVigenteEncontrada = patenteVigenteEncontrada || false; 
			}
		}
		return patenteVigenteEncontrada;
	}
	
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario()) {
			estacionamientos.clear();
		}
	}
	
	public Boolean fueraDeHorario() {
		Calendar fechaActual = Calendar.getInstance();
		return fechaActual.get(Calendar.HOUR_OF_DAY) >= 20;
	}
	
	public void registrarCompra(CompraFisica compra) {
		comprasRealizadas.add(compra);
	}

	public void registrarCarga(CargaVirtual carga) {
		cargasRealizadas.add(carga);
		// Cuando se tenga que actualizar el saldo para futuro this.ActualizarDatos(celularesRegistrados);
	}

}









