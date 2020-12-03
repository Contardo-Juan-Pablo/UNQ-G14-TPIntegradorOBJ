package AppTest;

import java.util.AbstractMap;

import app.UpdateHour;

public class UpdateHourTestClass extends UpdateHour {
	
	public UpdateHourTestClass() {
		
	}
	
	public void setCantidadDeHorasEstacionamientoAutomatico(AbstractMap.SimpleEntry<Integer, Integer> x) {
		cantidadDeHorasEstacionamientoAutomatico = x;
	}

	public void setEstacionamientoActivo(boolean bool) {
		estacionamientoActivo = bool;
	}

	public boolean getEstacionamientoActivo() {
		return estacionamientoActivo;
	}
}
