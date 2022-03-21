package algoritmoGenetico.mutar;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class Intercambio extends Mutacion {

	public Intercambio(double p) {
		super(p);
	}

	public void mutar(Individuo i) {
		IndividuoInt ind = (IndividuoInt) i;
		
		// Generador de numeros aleatorios
		Random rand = new Random();
		
		// Seleccionamos dos posiciones al azar y las intercambiamos
		int pos1 = rand.nextInt(ind.getL());
		int pos2 = rand.nextInt(ind.getL());
		
		// Intercambiamos los valores de esas posiciones
		int val2 =  ind.getAt(pos2);
		ind.setAt(pos2, ind.getAt(pos1));
		ind.setAt(pos1, val2);
	}

}
