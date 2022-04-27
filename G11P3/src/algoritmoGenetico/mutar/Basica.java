package algoritmoGenetico.mutar;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class Basica extends Mutacion {

	public Basica(double p) {
		super(p);
	}
	
	public void mutar(Individuo i) {
		IndividuoInt ib = (IndividuoInt) i;
		// Recorremos todos los genes del individuo
		Random r = new Random();
		for (int nGen = 0; nGen < ib.getNCodones(); nGen++) {
			double aleat = r.nextDouble();
			// Si toca mutar ese gen
			if (aleat < super.getProbMutar()) {
				// Modificamos el gen con un boolean aleatorio
				ib.setAt(nGen, r.nextInt(i.getMultiplexor().getMaxValueCodon()));
			}
		}
	}

}
