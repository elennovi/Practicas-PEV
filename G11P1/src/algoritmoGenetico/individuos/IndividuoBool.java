package algoritmoGenetico.individuos;

import java.util.Random;

import algoritmoGenetico.funciones.Funcion;

public class IndividuoBool extends Individuo {
	private boolean[] genesB;
	
	
	public IndividuoBool(Funcion f){
		super(f);
		genesB = new boolean[f.getLTotal()];
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
	
	public double evaluar() {
		return this.evaluaFunc();
	}
	
	public double evaluaFunc() {
		// Conseguimos el fenotipo
		double[] valuesR = getFenotipo();
		
		Funcion f = super.getF();
		
		// Si alguno de ellos es superior al maximo es el propio maximo de la variable
		for (int i = 0; i < f.getnVar(); i++) {
			double v = valuesR[i];
			// Si es mas grande que el maximo suponemos que es el maximo
			if (v > f.getMaximoAt(i))
				valuesR[i] = f.getMaximoAt(i);
		}
		return f.evaluar(valuesR);
	}

	
	public Individuo copia() {
		IndividuoBool nuevo = new IndividuoBool(super.getF());
		for (int i = 0; i < genesB.length; i++)
			nuevo.setAt(i, genesB[i]);
		return nuevo;
	}
	
	public double[] getFenotipo() {
		Funcion f = super.getF();
		// Conseguimos arrays de booleanos que se corresponden con los valores en binario
		// de cada variable
		boolean[][] valuesB = new boolean[f.getnVar()][];
		int posCromosoma = 0;
		for (int i = 0; i < f.getnVar(); i++) {
			valuesB[i] = new boolean[f.getLAt(i)];
			for (int j = 0; j < f.getLAt(i); j++) {
				valuesB[i][j] = genesB[posCromosoma];
				posCromosoma++;
			}
		}
		
		// Ahora conseguimos los valores en valor real
		return f.valorRealVariables(valuesB);
	}
}
