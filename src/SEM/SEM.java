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
	private ArrayList<Compra> comprasRealizadas = new ArrayList<Compra>();
	private ArrayList<CargaVirtual> cargasRealizadas = new ArrayList<CargaVirtual>();
	private ArrayList<Zona> zonasConSEM = new ArrayList<Zona>();
	private Map<Integer, Integer> celulares = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas = new ArrayList<Infraccion>();
	private ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private ArrayList<Entidades> entidadesParticipantes = new ArrayList<Entidades>();
	private Operador operadorAsociado;
	private int horaActual;
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// SECCIÓN INSPECTOR 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void cargarInfraccion(String patente, Inspector inspector) {
		Calendar fechaYHoraActual = Calendar.getInstance();
		Zona zonaDeInfraccion = this.zonaAlQuePerteneceInspector(inspector);
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, inspector);
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public Zona zonaAlQuePerteneceInspector(Inspector inspector) {
		/** El inspector siempre pertenece a alguna zona. No puede haber inspector sin ella. */
		Zona zona = null;
		for(int i = 0; i < zonasConSEM.size(); i++) {
			if(zonasConSEM.get(i).contieneAlInspector(inspector)) {
					zona = zonasConSEM.get(i);
			}
		}
		return zona;
	}

	public boolean consultarPatenteSEM(String patente) {
		Boolean patenteVigenteEncontrada = false;
		for(int i=0; i < this.getEstacionamientos().size(); i++){
			
			if(this.getEstacionamientos().get(i).getPatente() == patente) {
				patenteVigenteEncontrada = this.getEstacionamientos().get(i).estaVigente(this);
			}
		}
		return patenteVigenteEncontrada;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// SECCIÓN OPERADOR 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void finalizarEstacionamientos() {
		
		if(this.fueraDeHorario()) {
			estacionamientos.clear();
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// SEM FUNCIONES 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void registrarCarga(CargaVirtual carga) {
		cargasRealizadas.add(carga);
		int celular = carga.getCelular();
		
		if(celulares.containsKey(celular)) {
			this.actualizarSaldo(celular, carga.getCarga());
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
	public int costoActualPorHora(int horaActual, int horasReservadas) {//Consultar
		horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int costo = (horaActual > 7 && horaActual < 20) ? horasReservadas * 40 : 0;
		return costo;
	}
	
	public int costoActualPorHoraEnFranjaHorario(int horaActual, int horasReservadas) {
		horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int contador = 0;
		for(int i = horaActual; i<=horaActual+horasReservadas; i++) {
			if(i > 7 && i < 20) {
				contador++;
			}			
		}
		return 40 * contador;
	}
	
	public Boolean fueraDeHorario() {
		return this.getHoraActual() >= 20;
	}
	
	public void actualizarSaldo(int celular, int carga) {
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
	
	public void setCelulares(Integer celular, Integer saldo) {
		celulares.put(celular, saldo);
	}
	
	public int saldoCelular(int celular) {
		return celulares.get(celular);
	}

	public Operador getOperadorAsociado() {
		return operadorAsociado;
	}

	public void setOperadorAsociado(Operador operadorAsociado) {
		this.operadorAsociado = operadorAsociado;
	}
	
	public Map<Integer, Integer> getCelulares() {
		return celulares;
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
