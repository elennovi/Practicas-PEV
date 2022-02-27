package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public class CruceMonopunto extends Cruce{
	
	public IndividuoBool[] cruzar(IndividuoBool papa, IndividuoBool mama) {
		// Se van a crear dos hijos
		IndividuoBool[] hijos = new IndividuoBool[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = new IndividuoBool(papa.getF());

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = new IndividuoBool(mama.getF());;

		// Generamos un número entero y por ese punto dividimos a los progenitores
		Random r = new Random();
		int corte = r.nextInt(papa.getL());
		
		
		for (int i = 0; i < papa.getL(); i++) {
			// El hijo 0 lleva la primera parte del padre y la segunda de la madre y viceversa
			if (i <= corte) {
				hijos[0].setAt(i, papa.getAt(i));
				hijos[1].setAt(i, mama.getAt(i));
			}
			else {
				hijos[0].setAt(i, mama.getAt(i));
				hijos[1].setAt(i, papa.getAt(i));
			}
		}
		
		return hijos;
	}

	public IndividuoDouble[] cruzar(IndividuoDouble i1, IndividuoDouble i2) {
		// PARA HACER!!!!!!!!!!
		return null;
	}

}
