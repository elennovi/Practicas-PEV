package algoritmoGenetico.individuos;

import java.util.Random;

public class IndividuoBool extends Individuo {
	private boolean[] genesB;
	
	
	public IndividuoBool(int l){
		super(l);
		genesB = new boolean[l];
	}
	
	// Genera unos genes booleanos aleatoriamente
	public void initGenesAleatorio() {
		// Creamos el array con los genes (numeros 0 o 1)
		genesB = new boolean[super.getL()];
		Random random = new Random();
		for (int i = 0; i < super.getL(); i++)
			genesB[i] = random.nextBoolean();
	}
	
	// Getters y setters para los valores de los genes
	public void setAt(int i, boolean b) {
		genesB[i] = b;
	}
	
	public boolean getAt(int i) {
		return genesB[i];
	}
	
	public int evaluar() {
		// TODO Auto-generated method stub
		return 0;
	}
}
