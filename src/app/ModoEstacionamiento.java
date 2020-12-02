package app;

public enum ModoEstacionamiento {
	ACTIVO, INACTIVO;
	
	public ModoEstacionamiento siguienteEstado() {
		return (ModoEstacionamiento.values()[((this.ordinal()+1) % (Estado.values().length))]);
	}
}