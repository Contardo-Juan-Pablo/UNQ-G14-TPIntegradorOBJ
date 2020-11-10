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
import Persona.Operador;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private Map<Integer, Integer> celulares = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> estacionamientos;
	private ArrayList<Entidades> entidadesParticipantes;
	private Operador operadorAsociado;
	
    /////////////////////////////////////////////
    ///////////// SECCIÓN INSPECTOR /////////////
    /////////////////////////////////////////////
	
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
		}
		return patenteVigenteEncontrada;
	}
	
    ////////////////////////////////////////////
	///////////// SECCIÓN OPERADOR /////////////
    ////////////////////////////////////////////
	
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario()) {
			estacionamientos.clear();
		}
	}
	
    ////////////////////////////////////////////
	///////////// SEM FUNCIONES ////////////////
	////////////////////////////////////////////
	
	public void registrarCompra(CompraFisica compra) {
		comprasRealizadas.add(compra);
	}
	
	public void registrarCarga(CargaVirtual carga) {
		cargasRealizadas.add(carga);
		int celular = carga.getCelular();
		if(celulares.containsKey(celular)) {
			this.actualizarSaldo(celular, carga.getCarga());
		}
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(horaActual < 7 && horaActual > 20) {
			estacionamientos.add(estacionamiento);
				this.enviarNotificaciones();
		}
	}
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(int i=0; i < estacionamientos.size(); i++){
			if(estacionamientos.get(i).esNumeroCelularBuscado(numeroCelular)) {
				estacionamientos.remove(i);
				this.enviarNotificaciones();
			}
		}
	}
	
	public Estacionamiento buscarEstacionamiento(int numeroCelular) {
		Estacionamiento estacionamiento = null;
		for(int i=0; i < estacionamientos.size(); i++){
			if(estacionamientos.get(i).esNumeroCelularBuscado(numeroCelular)) {
				estacionamiento = estacionamientos.get(i);
			}
		}
		return estacionamiento;
	}
	
	///////////////////////////////////////////
	///////////// SEM FUNCIONES AUX ///////////
	///////////////////////////////////////////

	public int costoActualPorHora(int horaActual, int horasReservadas) {
		int costo = (horaActual > 7 && horaActual < 20) ? horasReservadas * 40 : 0;
		return costo;
	}
	
	public int costoActualPorHoraEnFranjaHorario(int horaActual, int horasReservadas) {
		int contador = 0;
		for(int i = horaActual; i<=horaActual+horasReservadas; i++) {
			if(i > 7 && i < 20) {
				contador++;
			}			
		}
		return 40 * contador;
	}
	
	public Boolean fueraDeHorario() {
		Calendar fechaActual = Calendar.getInstance();
		return fechaActual.get(Calendar.HOUR_OF_DAY) >= 20;
	}
	
	public void actualizarSaldo(int celular, int carga) {
		int nuevoSaldo = celulares.get(celular) + carga;
		celulares.replace(celular, nuevoSaldo);
	}
	
	public int saldoCelular(int celular) {
		return celulares.get(celular);
	}
	
	public Boolean confirmarOperador(Operador operador) {
		return operador == this.operadorAsociado;
	}
	
	public void RegistrarZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public void enviarNotificaciones() {
		for(int i=0; i < entidadesParticipantes.size(); i++){
			if(entidadesParticipantes.get(i).isEstadoSubscripto())
			entidadesParticipantes.get(i).notificar();
		}
	}
	
	
}
