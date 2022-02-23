package algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo {
	private int lTotal;
	
	Individuo(int l){
		lTotal = l;
	}
	
	// La función que evalua al fitness del individuo
	public abstract int evaluar();
	
	// La funcion que inicializa aleatoriamente todos los genes del individuo
	public abstract void initGenesAleatorio();
	
	public int getL() {
		return lTotal;
	}
}

