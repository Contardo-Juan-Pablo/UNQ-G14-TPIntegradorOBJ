package App;

public enum ModoEstacionamiento {
	ACTIVO, INACTIVO;
	
	public Estado siguienteEstado() {
		return (Estado.values()[((this.ordinal()+1) % (Estado.values().length))]);
	}
}
