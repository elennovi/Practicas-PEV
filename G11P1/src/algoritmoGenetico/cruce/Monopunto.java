package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public class Monopunto extends Cruce{
	
	public Individuo[] cruzar(IndividuoBool papa, IndividuoBool mama) {
		
		// Se van a crear dos hijos
		IndividuoBool[] hijos = new IndividuoBool[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = (IndividuoBool) papa.copia();

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = (IndividuoBool) mama.copia();

		// Generamos un número entero y por ese punto dividimos a los progenitores
		Random r = new Random();
		int corte = r.nextInt(papa.getL());
		
		
		for (int i = corte + 1; i < papa.getL(); i++) {
			// El hijo 0 lleva la primera parte del padre y la segunda de la madre y viceversa
			hijos[0].setAt(i, mama.getAt(i));
			hijos[1].setAt(i, papa.getAt(i));
		}
		
		return hijos;
	}

	public Individuo[] cruzar(IndividuoDouble papa, IndividuoDouble mama) {

		// Se van a crear dos hijos
		IndividuoDouble[] hijos = new IndividuoDouble[2];
		
		// Creamos un individuo con genes booleanos del tamaño del padre
		hijos[0] = (IndividuoDouble) papa.copia();

		// Creamos un individuo con genes booleanos del tamaño de la madre
		hijos[1] = (IndividuoDouble) mama.copia();

		// Generamos un número entero y por ese punto dividimos a los progenitores
		Random r = new Random();
		int corte = r.nextInt(papa.getL());
		
		for (int i = corte + 1; i < papa.getL(); i++) {
			// El hijo 0 lleva la primera parte del padre y la segunda de la madre y viceversa
			hijos[0].setAt(i, mama.getAt(i));
			hijos[1].setAt(i, papa.getAt(i));
		}
		
		return hijos;
	}

}
