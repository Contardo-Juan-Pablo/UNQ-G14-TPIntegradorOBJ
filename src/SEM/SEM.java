package SEM;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Compra.CargaVirtual;
import Compra.Compra;
import Compra.CompraFisica;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Persona.Inspector;
import Persona.Operador;

public class SEM {
	private ArrayList<Compra> comprasRealizadas = new ArrayList<Compra>();
	private ArrayList<CargaVirtual> cargasRealizadas = new ArrayList<CargaVirtual>();
	private ArrayList<Zona> zonasConSEM = new ArrayList<Zona>();
	private Map<Integer, Integer> creditoAsociado = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas = new ArrayList<Infraccion>();
	private ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private ArrayList<Entidad> entidadesParticipantes = new ArrayList<Entidad>();
	private Operador operadorAsociado;
	private int horaActual;
	 
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// SECCIÓN INSPECTOR 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void cargarInfraccion(String patente, String codigoDeInspector) {
		LocalDateTime fechaYHoraActual = LocalDateTime.now();
		Zona zonaDeInfraccion = this.zonaAlQuePerteneceInspector(codigoDeInspector);
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, codigoDeInspector);
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		/** El inspector siempre pertenece a alguna zona. No puede haber inspector sin ella. */
		return this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
	}


	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// SECCIÓN OPERADOR 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void finalizarEstacionamientos() {
		
		if(this.fueraDeHorario()) {
			estacionamientos.clear();
		}
	}
	
	/** 				GETTERS AND SETTERS 				**/
	public void realizarDescuentoDeSaldo(Integer numeroDeCelular, Integer costo) {
		int nuevoSaldo = creditoAsociado.get(numeroDeCelular) - costo;
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);	
	}
		
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(Estacionamiento estacionamiento : estacionamientos) {
			estacionamientos.remove(estacionamiento);
			this.enviarNotificaciones();
		}
	}
	
	public boolean hayEstacionamientoVigenteConPatente(String patenteBuscada) {	
		return this.estacionamientos.stream().filter(estacionamiento -> (estacionamiento.getPatente() == patenteBuscada) && estacionamiento.estaActivo()).collect(Collectors.toList()).size() > 1;
	}
	
	
	
	
	// PARA POSIBLE ELIMINACION 
	public ArrayList<String> getPatentesDeEstacionamientos() {
		ArrayList<String> listaDePatentes = new ArrayList<String>();
		this.estacionamientos.stream().forEach(estacionamiento -> listaDePatentes.add(estacionamiento.getPatente()));
		return listaDePatentes;
		
	}
	
	public Boolean estaLaPatenteBuscada(String patenteBuscada) {
		return this.getPatentesDeEstacionamientos().stream().filter(patente -> (patente == patenteBuscada)).collect(Collectors.toList()).contains(patenteBuscada);
	}
	///////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void registrarCarga(CargaVirtual carga) {
		cargasRealizadas.add(carga);
		int celular = carga.getCelular();
		
		if(celulares.containsKey(celular)) {
			this.realizarCompraEstacionamiento(celular, carga.getCarga());
		} else {
			this.setCelulares(celular, carga.getCarga());
		}
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(this.getHoraActual() >= 7 && this.getHoraActual() <= 20) {
			this.setEstacionamiento(estacionamiento);
			this.enviarNotificaciones();
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
	
	public Boolean fueraDeHorario() {
		return this.getHoraActual() >= 20;
	}
	
	public void x(int celular, int carga) {
		int nuevoSaldo = celulares.get(celular) + carga;
		celulares.replace(celular, nuevoSaldo);
	}
	

	
	public void enviarNotificaciones() { //Consultar
		for(int i=0; i < entidadesParticipantes.size(); i++){
			if(entidadesParticipantes.get(i).isEstadoSubscripto())
			entidadesParticipantes.get(i).serNotificado();
		}
	}
	
	public Boolean confirmarOperador(Operador operador) {
		return operador == this.operadorAsociado;
	}
	
	///////////////////////////////////////////
	//////////////// GET Y SET ////////////////
	///////////////////////////////////////////
	
	public void registrarCompra(CompraFisica compra) {
		comprasRealizadas.add(compra);
	}
	
	public void setCargasRealizadas(ArrayList<CargaVirtual> cargasRealizadas) {
		this.cargasRealizadas = cargasRealizadas;
	}

	public ArrayList<Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}

	public void setCargasRealizada (CargaVirtual cargaVirtual) {
		cargasRealizadas.add(cargaVirtual);
	}
	
	public ArrayList<CargaVirtual> getCargasRealizadas() {
		return cargasRealizadas;
	}
	
	public void registrarZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public ArrayList<Zona> getZonasConSEM(){
		return zonasConSEM;
	}
		
	public void setEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
	}
	
	public void setInfraccionLabrada(Infraccion infraccionGenerada) {
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public ArrayList<Infraccion> getInfraccionesLabradas(){
	    return infraccionesLabradas;
	}
	

	public Operador getOperadorAsociado() {
		return operadorAsociado;
	}

	public void setOperadorAsociado(Operador operadorAsociado) {
		this.operadorAsociado = operadorAsociado;
	}
	

	public ArrayList<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public int getHoraActual() {
		return horaActual;
	}

	public void setHoraActual(int hora) {
		horaActual = hora;
	}

	public void setEstacionamientos(ArrayList<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	
}
