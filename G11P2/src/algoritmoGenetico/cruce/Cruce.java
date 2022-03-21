package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.*;

public abstract class Cruce {
	
	public abstract Individuo[] cruzar(IndividuoInt i1, IndividuoInt i2);
}
