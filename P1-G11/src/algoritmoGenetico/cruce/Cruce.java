package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public abstract class Cruce {
	
	public abstract IndividuoBool[] cruzar(IndividuoBool i1, IndividuoBool i2);
	
	public abstract IndividuoDouble[] cruzar(IndividuoDouble i1, IndividuoDouble i2);
}
