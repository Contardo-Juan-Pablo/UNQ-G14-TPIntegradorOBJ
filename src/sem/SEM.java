package sem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import app.AppSEM;
import compra.CargaVirtual;
import compra.CompraPuntual;
import espaciosFisicos.Zona;
import estacionamiento.EstacionamientoGeneral;

public class SEM {
	protected ArrayList<CompraPuntual> comprasRealizadas;
	protected ArrayList<CargaVirtual> cargasRealizadas;
	protected ArrayList<Zona> zonasConSEM;
	protected ArrayList<AppSEM> appSEMAsociadas;
	protected ArrayList<Infraccion> infraccionesLabradas;
	protected ArrayList<EstacionamientoGeneral> estacionamientos;
	protected ArrayList<Entidad> entidadesParticipantes;
	 
	/** 				SECCIÓN CONSTRUCTOR 				**/
	public SEM(ArrayList<CompraPuntual> comprasRealizadas, ArrayList<CargaVirtual> cargasRealizadas,
			ArrayList<Zona> zonasConSEM, ArrayList<AppSEM> appSEMAsociadas,
			ArrayList<Infraccion> infraccionesLabradas, ArrayList<EstacionamientoGeneral> estacionamientos,
			ArrayList<Entidad> entidadesParticipantes) {
		this.comprasRealizadas = comprasRealizadas;
		this.cargasRealizadas = cargasRealizadas;
		this.zonasConSEM = zonasConSEM;
		this.appSEMAsociadas = appSEMAsociadas;
		this.infraccionesLabradas = infraccionesLabradas;
		this.estacionamientos = estacionamientos;
		this.entidadesParticipantes = entidadesParticipantes;
	}
	
	/** 				SECCIÓN INSPECTOR 				**/
	public void cargarInfraccion(Infraccion infraccion) {
		infraccionesLabradas.add(infraccion);
	}
	
	
	public Zona zonaAlQuePerteneceInspector(String codigoDeInspector) {
		try {
			Zona zonaBuscada = this.zonasConSEM.stream().filter(zona -> zona.contieneAlInspector(codigoDeInspector)).collect(Collectors.toList()).get(0);
			return zonaBuscada;
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No hay una zona con ese Inspector");
			throw e;
		}	
	}

	public boolean hayEstacionamientoVigenteConPatente(String patenteBuscada) {	
		return this.estacionamientos.stream().filter(estacionamiento -> estacionamiento.esPatenteBuscada(patenteBuscada) && estacionamiento.estaActivo()).collect(Collectors.toList()).size() > 0;
	}
	
	/** 				SECCIÓN OPERADOR 				**/
	public void finalizarEstacionamientos() {
		if(this.fueraDeHorario(20)) {
			this.estacionamientos.stream().forEach(estacionamiento -> estacionamiento.finalizar());
			this.appSEMAsociadas.stream().forEach(appSEM -> appSEM.finalizarEstacionamientoHoraMaximaSEM());
		}
	}
	
	public Boolean fueraDeHorario(int horaLimite) {
		return getHoraActual() >= horaLimite;
	}
	
	/** 				GETTERS AND SETTERS 				
	 * @param appSEM **/
	public void realizarDescuentoDeSaldo(Integer costo, AppSEM appSEM) {
		int nuevoSaldo = Math.abs(appSEM.getSaldoActual() - costo);
		appSEM.actualizarSaldo(nuevoSaldo);
	}

	public void actualizacionDeEstacionamientos() {
		this.estacionamientos.stream().forEach(estacionamiento -> estacionamiento.finalizarSiCumple(estacionamiento.estaDentroDeFranjaHorariaComprada()));
	}
	
	public void terminarEstacionamiento(int numeroCelular) {
		for(EstacionamientoGeneral estacionamiento : estacionamientos) {
			if(estacionamiento.esNumeroCelularBuscado(numeroCelular)) {
				estacionamiento.finalizar();
			}
			this.enviarNotificaciones(estacionamiento.getPatente() + "Finalizó estacionamiento el" + LocalDateTime.now().toString());
		}
	}
	
	public void guardarEstacionamiento(EstacionamientoGeneral estacionamiento) {
		if(this.getHoraActual() >= 7 && this.getHoraActual() <= 20) {
			this.almancenarNuevoEstacionamiento(estacionamiento);
			this.enviarNotificaciones(estacionamiento.getPatente() + "Inició estacionamiento el" + LocalDateTime.now().toString());
		} 
	}
	
	public void registrarCarga(CargaVirtual carga, AppSEM appSEM) {
		int nuevoSaldo =  appSEM.getSaldoActual() + carga.getCarga();
		appSEM.actualizarSaldo(nuevoSaldo);
		cargasRealizadas.add(carga);
	}
	
	
	public void enviarNotificaciones(String informe) {
		this.entidadesParticipantes.stream().forEach(entidad -> entidad.update(informe));
	}
	
	public void registrarCompra(CompraPuntual compra) {
		comprasRealizadas.add(compra);
	}
	
	public void almancenarNuevoEstacionamiento(EstacionamientoGeneral estacionamiento) {
		estacionamientos.add(estacionamiento);
	}
	
	public int getHoraActual() {
		return LocalDateTime.now().getHour();
	}

	public void registrarEntidad(Entidad entidad) {
		entidadesParticipantes.add(entidad);
	}

	public void retirarEntidad(Entidad entidad) {
		entidadesParticipantes.remove(entidad);
	}
}