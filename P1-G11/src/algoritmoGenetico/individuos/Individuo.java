package algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo {
	private boolean[] genes;
	private int lTotal;
	
	// Genera unos genes booleanos aleatoriamente
	public void initGenesAleatorio() {
		// Creamos el array con los genes (numeros 0 o 1)
		genes = new boolean[lTotal];
		Random random = new Random();
		for (int i = 0; i < lTotal; i++)
			genes[i] = random.nextBoolean();
	}
	
	
	
}

