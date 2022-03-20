package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public abstract class Cruce {
	
	public abstract Individuo[] cruzar(IndividuoBool i1, IndividuoBool i2);
	public abstract Individuo[] cruzar(IndividuoDouble i1, IndividuoDouble i2);
}
