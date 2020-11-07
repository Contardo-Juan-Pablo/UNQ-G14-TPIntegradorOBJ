package SEM;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
	private Map<Integer, Integer> celulares = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
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

	public int costoActualPorHora(int horaActual, int horasReservadas) {
		int costo = (horaActual > 7 && horaActual < 20) ? horasReservadas * 40 : 0;
		return costo;
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(horaActual < 7 && horaActual > 20) {
			estacionamientos.add(estacionamiento);
		}
	}
	
	public void registrarCarga(CargaVirtual carga) {
		cargasRealizadas.add(carga);
		int celular = carga.getCelular();
		if(celulares.containsKey(celular)) {
			this.actualizarSaldo(celular, carga.getCarga());
		}
	}

	public void actualizarSaldo(int celular, int carga) {
		int nuevoSaldo = celulares.get(celular) + carga;
		celulares.replace(celular, nuevoSaldo);
	}
	
	public int saldoCelular(int celular) {
		return celulares.get(celular);
	}
}
