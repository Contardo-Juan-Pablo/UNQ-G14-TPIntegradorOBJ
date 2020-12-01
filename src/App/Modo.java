package App;

public enum Modo {
	ACTIVADO, DESACTIVADO;
	
	public Estado siguienteEstado() {
		return (Estado.values()[((this.ordinal()+1) % (Estado.values().length))]);
	}
}
