package Persona;
import SEM.SEM;

public class Operador {
	int legajoSEM;

	public Operador(int legajoSEM) {
		this.legajoSEM = legajoSEM;
	}	
	
	public void darBajaTotalEstacionamientos(SEM sem) {
		if(sem.confirmarOperador(this)) {
			sem.finalizarEstacionamientos();
		}	
	}
}