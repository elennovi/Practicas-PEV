package algoritmoGenetico.mutar;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;

public class Basica extends Mutacion {

	public Basica(double p) {
		super(p);
	}
	
	public void mutar(Individuo i) {
		IndividuoBool ib = (IndividuoBool) i;
		// Recorremos todos los genes del individuo
		Random r = new Random();
		for (int nGen = 0; nGen < ib.getL(); nGen++) {
			double aleat = r.nextDouble();
			// Si toca mutar ese gen
			if (aleat < super.getProbMutar()) {
				// Modificamos el gen con un boolean aleatorio
				ib.setAt(nGen, r.nextBoolean());
			}
		}
	}

}
