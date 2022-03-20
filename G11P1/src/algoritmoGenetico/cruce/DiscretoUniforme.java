package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public class DiscretoUniforme extends Cruce {
	
	private static final double P = 0.4;
	
	public Individuo[] cruzar(IndividuoBool papa, IndividuoBool mama) {
		
		// Se van a crear dos hijos
		IndividuoBool[] hijos = new IndividuoBool[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = (IndividuoBool) papa.copia();

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = (IndividuoBool) mama.copia();
		
		for (int i = 0; i < papa.getL(); i++) {
			double aleat = Math.random();
			// El hijo 0 lleva la primera parte del padre y la segunda de la madre y viceversa
			if (aleat <= P) {
				hijos[0].setAt(i, mama.getAt(i));
				hijos[1].setAt(i, papa.getAt(i));
			}
		}
		return hijos;
	}
	
	@Override
	public Individuo[] cruzar(IndividuoDouble papa, IndividuoDouble mama) {
		// Se van a crear dos hijos
		IndividuoDouble[] hijos = new IndividuoDouble[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = (IndividuoDouble) papa.copia();

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = (IndividuoDouble) mama.copia();
		
		for (int i = 0; i < papa.getL(); i++) {
			double aleat = Math.random();
			// El hijo 0 lleva la primera parte del padre y la segunda de la madre y viceversa
			if (aleat <= P) {
				hijos[0].setAt(i, mama.getAt(i));
				hijos[1].setAt(i, papa.getAt(i));
			}
		}
		return hijos;
	}

}
