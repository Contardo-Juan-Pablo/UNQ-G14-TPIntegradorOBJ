package sem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import app.AppSEM;
import compra.CargaVirtual;
import compra.Compra;
import espaciosFisicos.Zona;
import estacionamiento.Estacionamiento;

public class SEM {
	protected ArrayList<Compra> comprasRealizadas;
	protected ArrayList<CargaVirtual> cargasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private Map<Integer, Integer> creditoAsociado;
	protected ArrayList<Infraccion> infraccionesLabradas;
	protected ArrayList<Estacionamiento> estacionamientos;
	private ArrayList<Entidad> entidadesParticipantes;
	 
	/** 				SECCIÓN CONSTRUCTOR 				**/
	public SEM(ArrayList<Compra> comprasRealizadas, ArrayList<CargaVirtual> cargasRealizadas,
			ArrayList<Zona> zonasConSEM, Map<Integer, Integer> creditoAsociado,
			ArrayList<Infraccion> infraccionesLabradas, ArrayList<Estacionamiento> estacionamientos,
			ArrayList<Entidad> entidadesParticipantes) {
		this.comprasRealizadas = comprasRealizadas;
		this.cargasRealizadas = cargasRealizadas;
		this.zonasConSEM = zonasConSEM;
		this.creditoAsociado = creditoAsociado;
		this.infraccionesLabradas = infraccionesLabradas;
		this.estacionamientos = estacionamientos;
		this.entidadesParticipantes = entidadesParticipantes;
	}
	
	
	/** 				SECCIÓN INSPECTOR 				**/
	public void cargarInfraccion(Infraccion infraccion) {
		infraccionesLabradas.add(infraccion);
	}
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		return this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
	}

	public boolean hayEstacionamientoVigenteConPatente(String patenteBuscada) {	
		return this.estacionamientos.stream().filter(estacionamiento -> (estacionamiento.getPatente() == patenteBuscada) && estacionamiento.estaActivo()).collect(Collectors.toList()).size() > 1;
	}
	
	/** 				SECCIÓN OPERADOR 				**/
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario(20)) {
			this.estacionamientos.stream().forEach(estacionamiento -> estacionamiento.finalizar());
		}
	}
	
	public Boolean fueraDeHorario(int horaLimite) {
		return getHoraActual() >= horaLimite;
	}
	
	/** 				GETTERS AND SETTERS 				**/
	public void realizarDescuentoDeSaldo(Integer numeroDeCelular, Integer costo) {
		int nuevoSaldo = Optional.ofNullable(creditoAsociado.get(numeroDeCelular)).orElse(0) - costo;
		creditoAsociado.replace(numeroDeCelular, nuevoSaldo);
	}
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(Estacionamiento estacionamiento : estacionamientos) {
			estacionamientos.remove(estacionamiento);
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
		cargasRealizadas.add(carga);
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