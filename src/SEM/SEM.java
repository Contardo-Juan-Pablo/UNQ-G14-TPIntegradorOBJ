package SEM;
import java.util.ArrayList;

import Compra.Compra;
import Compra.CompraFisica;
import EspaciosFisicos.Zona;
import Estacionamiento.Estacionamiento;

public class SEM {
	private ArrayList<Compra> comprasRealizadas;
	private ArrayList<Zona> zonasConSEM;
	private ArrayList<String> celularesRegistrados;
	private ArrayList<Infraccion> infraccionesLabradas;
	private ArrayList<Estacionamiento> Estacionamientos;
	
	public void RegistrarZona(Zona zona) {
		zonasConSEM.add(zona);
	}
	
	public boolean consultarPatenteSEM(String patente) {
		Boolean patenteEncontrada = false;
		for(int i=0; i < Estacionamientos.size(); i++){
			if(Estacionamientos.get(i).getAutomovilEstacionado().patente() == patente) {
				patenteEncontrada = patenteEncontrada || true;
			}
			else { patenteEncontrada = patenteEncontrada || false; 
			}
		}
		return patenteEncontrada;
	}

	public void registrarCompra(CompraFisica compra) {
		comprasRealizadas.add(compra);
	}
}