package algoritmoGenetico.mutar;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class Insercion extends Mutacion {

	public Insercion(double p) {
		super(p);
	}

	public void mutar(Individuo i) {
		IndividuoInt ind = (IndividuoInt) i;
		
		// Generador de numeros aleatorios
		Random rand = new Random();
		
		// Seleccionamos una posición aleatoria
		int pos1 = rand.nextInt(ind.getL());
		
		// Si elegimos la primera posicion no hay desplazamiento de ningun tipo
		if (pos1 != 0) {
			// Guardamos el valor en esa posición
			int valIns = ind.getAt(pos1);
			
			// Seleccionamos otra posición aleatoria (previa a la seleccionada anteriormente)
			int pos2 = rand.nextInt(pos1);
			
			// Desplazamos los elementos una posición a la derecha entre pos2 y pos1
			int ant = ind.getAt(pos2);
			for (int p = pos2 + 1; p <= pos1; p++) {
				int aux = ind.getAt(p);
				ind.setAt(p, ant);
				ant = aux;
			}
			
			// Insertamos el valor en pos2
			ind.setAt(pos2, valIns);
		}
	}

}
