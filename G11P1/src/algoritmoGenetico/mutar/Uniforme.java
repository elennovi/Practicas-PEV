package algoritmoGenetico.mutar;

import java.util.concurrent.ThreadLocalRandom;

import algoritmoGenetico.funciones.Funcion;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoDouble;

public class Uniforme extends Mutacion{

	public Uniforme(double p) {
		super(p);
	}
	
	public void mutar(Individuo i) {
		IndividuoDouble ib = (IndividuoDouble) i;	
		
		Funcion f = ib.getF();
		
		for (int j = 0; j < f.getnVar(); j++) {
			if (super.getProbMutar() < Math.random()) {
				double random = ThreadLocalRandom.current().nextDouble(f.getMinimoAt(j), f.getMaximoAt(j));
				ib.setAt(j, random);
			}
		}
	}

}
