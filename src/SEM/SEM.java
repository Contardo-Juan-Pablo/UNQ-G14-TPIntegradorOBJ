package SEM;
import java.util.ArrayList;

import EspaciosFisicos.Estacionamiento;
import EspaciosFisicos.Zona;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private ArrayList<Celular> celularesRegistrados;
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> Estacionamientos;
	
	
	
	public void dispararBajaDeEstacionamientos() {
		
	}
	
	public boolean consultarPatenteSEM(String patente) {
		Boolean patenteEncontrada = false;
		for(int i=0; i < Estacionamientos.size(); i++){
			if(Estacionamientos.get(i).getAutomovilEstacionado().patente() == patente) {
				patenteEncontrada = patenteEncontrada || true;
			}
			else { patenteEncontrada= patenteEncontrada || false; 
			}
		}
		return patenteEncontrada;
	}
	
}
