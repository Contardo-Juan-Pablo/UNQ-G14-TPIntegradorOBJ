package App;
import javafx.util.Pair;

public class GPS {
	private Pair<Integer,Integer> posicion;
	
	
	public GPS(Pair<Integer, Integer> posicion) {
		super();
		this.posicion = posicion;
	}

	public Pair<Integer, Integer> getPosicion() {
		return posicion;
	}

	public void setPosicion(Pair<Integer, Integer> posicion) {
		this.posicion = posicion;
	}
}
