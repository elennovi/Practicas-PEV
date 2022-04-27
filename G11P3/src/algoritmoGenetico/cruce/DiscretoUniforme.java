package algoritmoGenetico.cruce;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class DiscretoUniforme extends Cruce {
	
	private static final double P = 0.4;
	
	public Individuo[] cruzar(IndividuoInt papa, IndividuoInt mama) {
		
		// Se van a crear dos hijos
		IndividuoInt[] hijos = new IndividuoInt[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = (IndividuoInt) papa.copia();

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = (IndividuoInt) mama.copia();
		
		for (int i = 0; i < papa.getNCodones(); i++) {
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
