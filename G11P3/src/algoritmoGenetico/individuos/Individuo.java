package algoritmoGenetico.individuos;

import algoritmoGenetico.multiplexores.*;

public abstract class Individuo {
	protected Multiplexor m;
	protected double fitness, fitDesplazado;
	
	public Individuo(Multiplexor m){
		this.m = m;
	}
	
	// La función que evalua al fitness del individuo
	public abstract void evaluar();
	
	// La funcion que inicializa aleatoriamente todos los genes del individuo
	public abstract void initGenesAleatorio();
	
	// La función que devuelve la copia del individuo
	public abstract Individuo copia();
	
	public Multiplexor getMultiplexor() {
		return m;
	}
	
	public double getFitness() {
		return fitness;
	}
	public double getFitDesplazado() {
		return fitDesplazado;
	}

	public void setFitDesplazado(double d) {
		fitDesplazado = d;
	}
	
	public String toString() {
		return "";
	}
	
	public int getNCodones() {
		return m.getNCodones();
	}
}

