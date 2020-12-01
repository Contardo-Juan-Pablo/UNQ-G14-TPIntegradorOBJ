package App;

public enum Estado {
	CAMINANDO, MANEJANDO;
	
	public Estado siguienteEstado() {
		return (Estado.values()[((this.ordinal()+1) % (Estado.values().length))]);
	}
}