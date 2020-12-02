package SEM;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import Compra.CargaVirtual;
import Compra.Compra;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;

public class SEM {
	private ArrayList<Compra> comprasRealizadas = new ArrayList<Compra>();
	private ArrayList<CargaVirtual> cargasRealizadas = new ArrayList<CargaVirtual>();
	private ArrayList<Zona> zonasConSEM = new ArrayList<Zona>();
	private Map<Integer, Integer> creditoAsociado = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas = new ArrayList<Infraccion>();
	private ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private ArrayList<Entidad> entidadesParticipantes = new ArrayList<Entidad>();
	 
	/** 				SECCIÓN INSPECTOR 				**/
	public void cargarInfraccion(String patente, String codigoDeInspector) {
		Calendar fechaYHoraActual = Calendar.getInstance();
		Zona zonaDeInfraccion = this.zonaAlQuePerteneceInspector(codigoDeInspector);
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, codigoDeInspector);
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		/** El inspector siempre pertenece a alguna zona. No puede haber inspector sin ella. */
		return this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
	}

	public boolean hayEstacionamientoVigenteConPatente(String patenteBuscada) {	
		return this.estacionamientos.stream().filter(estacionamiento -> (estacionamiento.getPatente() == patenteBuscada) && estacionamiento.estaActivo()).collect(Collectors.toList()).size() > 1;
	}
	
	/** 				SECCIÓN OPERADOR 				**/
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario()) {
			this.estacionamientos.stream().forEach(estacionamiento -> estacionamiento.finalizar());
		}
	}
	
	public Boolean fueraDeHorario() {
		int horaActual = Calendar.HOUR_OF_DAY;
		return horaActual >= 20;
	}
	
	/** 				GETTERS AND SETTERS 				**/
	public void realizarDescuentoDeSaldo(Integer numeroDeCelular, Integer costo) {
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) - costo;
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
	}
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(Estacionamiento estacionamiento : estacionamientos) {
			estacionamientos.remove(estacionamiento);
			this.enviarNotificaciones();
		}
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
		int numeroDeCelular = carga.getCelular();
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) + carga.getCarga();
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
		cargasRealizadas.add(carga);
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		int horaActual = Calendar.HOUR_OF_DAY;
		if(horaActual >= 7 && horaActual <= 20) {
			this.almancenarNuevoEstacionamiento(estacionamiento);
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
	
	public void enviarNotificaciones() { //Consultar
		for(int i=0; i < entidadesParticipantes.size(); i++){
			if(entidadesParticipantes.get(i).isEstadoSubscripto())
			entidadesParticipantes.get(i).serNotificado();
		}
	}
	
	public void registrarCompra(Compra compra) {
		comprasRealizadas.add(compra);
	}
	
	public void almancenarNuevoEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientos.add(estacionamiento);
	}
	
	public ArrayList<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	
	public ArrayList<Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}	
	
	public ArrayList<CargaVirtual> getCargasRealizadas(){
		return cargasRealizadas;
	}
}