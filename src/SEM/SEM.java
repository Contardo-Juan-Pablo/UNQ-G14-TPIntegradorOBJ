package SEM;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import app.AppSEM;
import compra.CargaVirtual;
import compra.Compra;
import EspaciosFisicos.Zona;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoViaApp;

public class SEM {
	private ArrayList<Compra> comprasRealizadas = new ArrayList<Compra>();
	private ArrayList<CargaVirtual> cargasRealizadas = new ArrayList<CargaVirtual>();
	private ArrayList<Zona> zonasConSEM = new ArrayList<Zona>();
	private Map<Integer, Integer> creditoAsociado = new HashMap<>(); //Decidimos utilizar un Map, porque creemos que es la mejor manera de mantener asociado el celular con su carga
	private ArrayList<Infraccion> infraccionesLabradas = new ArrayList<Infraccion>();
	private ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private ArrayList<Entidad> entidadesParticipantes = new ArrayList<Entidad>();
	 
	/** 				SECCIÓN INSPECTOR 				**/
	public void cargarInfraccion(Infraccion infraccion) {
		infraccionesLabradas.add(infraccion);
	}
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		return this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
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
		return getHoraActual() >= 20;
	}
	
	/** 				GETTERS AND SETTERS 				**/
	public void realizarDescuentoDeSaldo(Integer numeroDeCelular, Integer costo) {
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) - costo;
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
	}
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(Estacionamiento estacionamiento : getEstacionamientos()) {
			getEstacionamientos().remove(estacionamiento);
			this.enviarNotificaciones(estacionamiento.getPatente() + "Finalizo estacionamiento el" + LocalDateTime.now().toString());
		}
	}
	
	public void guardarEstacionamiento(Estacionamiento estacionamiento) {
		if(this.getHoraActual() >= 7 && this.getHoraActual() <= 20) {
			this.almancenarNuevoEstacionamiento(estacionamiento);
			this.enviarNotificaciones(estacionamiento.getPatente() + "Inicio estacionamiento el" + LocalDateTime.now().toString());
		}
	}
	
	public void registrarCarga(CargaVirtual carga, AppSEM appSEM) {
		int numeroDeCelular = carga.getCelular();
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) + carga.getCarga();
		appSEM.actualizarSaldo(nuevoSaldo);
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
		getCargasRealizadas().add(carga);
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

	public void registrarEntidad(Entidad entidad) {
		entidadesParticipantes.remove(entidad);
		
	}

	public void retirarEntidad(Entidad entidad) {
		entidadesParticipantes.add(entidad);
		
	}
}