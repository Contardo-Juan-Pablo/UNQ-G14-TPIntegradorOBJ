package SEM;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import App.AppSEM;
import Compra.CargaVirtual;
import Compra.Compra;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Estacionamiento.EstacionamientoViaApp;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private Map<Integer, Integer> creditoAsociado; //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> estacionamientos;
	private ArrayList<Entidad> entidadesParticipantes;
	 
	/**             CONSTRUCTOR DE CLASE                  **/
	public SEM(ArrayList<Compra> comprasRealizadas, ArrayList<CargaVirtual> cargasRealizadas, ArrayList<Zona> zonasConSEM, Map<Integer, Integer> creditoAsociado,
			ArrayList<Infraccion> infraccionesLabradas, ArrayList<Estacionamiento> estacionamientos, ArrayList<Entidad> entidadesParticipantes) {
		this.comprasRealizadas = comprasRealizadas;
		this.cargasRealizadas = cargasRealizadas;
		this.zonasConSEM = zonasConSEM;
		this.creditoAsociado = creditoAsociado;
		this.infraccionesLabradas = infraccionesLabradas;
		this.estacionamientos = estacionamientos;
		this.entidadesParticipantes = entidadesParticipantes;
	}
	
	/** 				SECCIÓN INSPECTOR 				**/
	public void cargarInfraccion(String patente, String codigoDeInspector) {
		Calendar fechaYHoraActual = Calendar.getInstance();
		Zona zonaDeInfraccion = this.zonaAlQuePerteneceInspector(codigoDeInspector);
		Infraccion infraccionGenerada = new Infraccion(patente, fechaYHoraActual, zonaDeInfraccion, codigoDeInspector);
		infraccionesLabradas.add(infraccionGenerada);
	}
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		try {
			return this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
		} 
		catch (NullPointerException e) {
			throw e;
		}
	}

	public boolean hayEstacionamientoVigenteConPatente(String patenteBuscada) {	
		return this.getEstacionamientos().stream().filter(estacionamiento -> (estacionamiento.getPatente() == patenteBuscada) && estacionamiento.estaActivo()).collect(Collectors.toList()).size() > 1;
	}
	
	/** 				SECCIÓN OPERADOR 				**/
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario()) {
			this.getEstacionamientos().stream().forEach(estacionamiento -> estacionamiento.finalizar());
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
		for(Estacionamiento estacionamiento : getEstacionamientos()) {
			getEstacionamientos().remove(estacionamiento);
			this.enviarNotificaciones(estacionamiento.getPatente().toString() + "Finalizo un estacionamiento");
		}
	}
	
	public void registrarCarga(CargaVirtual carga, AppSEM appSEM) {
		int numeroDeCelular = carga.getCelular();
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) + carga.getCarga();
		appSEM.actualizarSaldo(nuevoSaldo);
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
		getCargasRealizadas().add(carga);
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		if(this.getHoraActual() >= 7 && this.getHoraActual() <= 20) {
			this.almancenarNuevoEstacionamiento(estacionamiento);
			this.enviarNotificaciones(estacionamiento.getPatente().toString() + "Inicio un estacionamiento");
		}
	}
	
	public void enviarNotificaciones(String informe) { 
		this.entidadesParticipantes.stream().forEach(entidad -> entidad.update(informe));
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
	
	public int getHoraActual() {
		return LocalDateTime.now().getHour();
	}

	public void retirar(Entidad entidad) {
		entidadesParticipantes.add(entidad);
		
	}
	
	public void añadirEntidad(Entidad entidad) {
		entidadesParticipantes.remove(entidad);
		
	}
}