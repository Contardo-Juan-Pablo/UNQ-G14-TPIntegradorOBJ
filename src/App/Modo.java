package App;

public enum Modo {
	ACTIVADO, DESACTIVADO;
	
	public Modo siguienteEstado() {
		return (Modo.values()[((this.ordinal()+1) % (Estado.values().length))]);
	}
}
