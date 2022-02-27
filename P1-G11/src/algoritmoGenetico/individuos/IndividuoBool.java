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
		// Conseguimos los valores de las variables en su forma real
		
		// Si alguno de ellos es superior al maximo (porque en su valor binario quepan
		// mas numeros) debemos penalizarlo para que no se tenga en cuenta
		
		// A continuacion evaluamos con la funcion que queremos maximizar/minimizar
		
		// Devolvemos el valor de la evaluacion
		
		return 0;
	}
}
