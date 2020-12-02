package semTest;
import java.util.ArrayList;
import sem.Entidad;

public class EntidadTestClass extends Entidad {

	public EntidadTestClass(ArrayList<String> informes) {
		super(informes);
	}

	public ArrayList<String> getInformes() {
		return this.informes;
	}
}
