package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce {
	
	public abstract Individuo[] cruzar(Individuo i1, Individuo i2);
}
