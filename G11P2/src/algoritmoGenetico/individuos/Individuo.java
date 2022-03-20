package algoritmoGenetico.individuos;

import algoritmoGenetico.casos.*;

public abstract class Individuo {
	private Caso c;
	
	public Individuo(Caso c){
		this.c = c;
	}
	
	// La funci�n que evalua al fitness del individuo
	public abstract double evaluar();
	
	// La funcion que inicializa aleatoriamente todos los genes del individuo
	public abstract void initGenesAleatorio();
	
	// La funci�n que devuelve la copia del individuo
	public abstract Individuo copia();
	
	public int getL() {
		return c.getNVuelos();
	}
	
	public Caso getF() {
		return c;
	}

	public abstract double evaluaFunc();
}

