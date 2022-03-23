package algoritmoGenetico.individuos;

import algoritmoGenetico.casos.*;

public abstract class Individuo {
	private Caso c;
	protected double fitness, fitDesplazado;
	
	public Individuo(Caso c){
		this.c = c;
	}
	
	// La función que evalua al fitness del individuo
	public abstract void evaluar();
	
	// La funcion que inicializa aleatoriamente todos los genes del individuo
	public abstract void initGenesAleatorio();
	
	// La función que devuelve la copia del individuo
	public abstract Individuo copia();
	
	public int getL() {
		return c.getNVuelos();
	}
	
	public Caso getF() {
		return c;
	}

	public abstract double evaluaFunc();
	
	public double getFitness() {
		return fitness;
	}
	public double getFitDesplazado() {
		return fitDesplazado;
	}

	public void setFitDesplazado(double d) {
		fitDesplazado = d;
	}
}

