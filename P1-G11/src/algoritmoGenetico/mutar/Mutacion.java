package algoritmoGenetico.mutar;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;

public class Mutacion {
	private double probMutar;
	
	public Mutacion(double p){
		probMutar = p;
	}
	
	// El Mutacion basica
	public void mutar(IndividuoBool i) {
		// Recorremos todos los genes del individuo
		Random r = new Random();
		for (int nGen = 0; nGen < i.getL(); nGen++) {
			double aleat = r.nextDouble();
			// Si toca mutar ese gen
			if (aleat < probMutar) {
				// Modificamos el gen con un boolean aleatorio
				i.setAt(nGen, r.nextBoolean());
			}
		}
	}
}
