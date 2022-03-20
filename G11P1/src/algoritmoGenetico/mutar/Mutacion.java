package algoritmoGenetico.mutar;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	private double probMutar;
	
	public Mutacion(double p){
		probMutar = p;
	}

	public double getProbMutar() {
		return probMutar;
	}
	
	public abstract void mutar(Individuo i);
}
