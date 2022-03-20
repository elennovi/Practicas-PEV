package algoritmoGenetico.individuos;

import algoritmoGenetico.funciones.Funcion;

public abstract class Individuo {
	private Funcion f;
	
	Individuo(Funcion f){
		this.f = f;
	}
	
	// La funci�n que evalua al fitness del individuo
	public abstract double evaluar();
	
	// La funcion que inicializa aleatoriamente todos los genes del individuo
	public abstract void initGenesAleatorio();
	
	// La funci�n que devuelve la copia del individuo
	public abstract Individuo copia();
	
	public int getL() {
		return f.getLTotal();
	}
	
	public Funcion getF() {
		return f;
	}

	public abstract double evaluaFunc();
}

