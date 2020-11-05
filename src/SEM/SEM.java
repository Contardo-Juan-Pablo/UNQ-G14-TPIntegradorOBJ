package SEM;
import java.util.ArrayList;

import Compra.Compra;
import Compra.CompraFisica;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;
import Persona.Inspector;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private ArrayList<String> celularesRegistrados;
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> estacionamientos;
	private ArrayList<Inspector> inspectores;
	
	
	public void RegistrarZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public static void cargarInfraccion(Infraccion infraccion) {
		infraccionesLabradas.add(infraccion);
	}
	
	public boolean consultarPatenteSEM(String patente) {
		Boolean patenteVigenteEncontrada = false;
		
		for(int i=0; i < Estacionamientos.size(); i++){
			if(Estacionamientos.get(i).getPatente() == patente) {
				patenteVigenteEncontrada = patenteVigenteEncontrada || true && 
						Estacionamientos.get(i).estaVigente();
			}
			else { patenteVigenteEncontrada = patenteVigenteEncontrada || false; 
			}
		}
		return patenteVigenteEncontrada;
	}

	public void registrarCompra(CompraFisica compra) {
		comprasRealizadas.add(compra);
	}
	
	public void labrarInfraccion(Inspector inspect, String patente) {
		inspect.labrarInf
	}
	
	

}