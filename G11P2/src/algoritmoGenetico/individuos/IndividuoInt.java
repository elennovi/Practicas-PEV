package algoritmoGenetico.individuos;

import algoritmoGenetico.casos.Caso;

public class IndividuoInt extends Individuo {
	// El array que representa al individuo (de enteros)
	int[] valuesI;
	
	
	public IndividuoInt(Caso c) {
		super(c);
		// Generamos un array de la longitud pertinente
		valuesI = new int[c.getNVuelos()];
	}

	public void evaluar() {
		double fit = 0;
		// TODO Auto-generated method stub
		
		// Actualizamos 
		fitness = fit;
	}
	
	public void initGenesAleatorio() {
		// TODO Auto-generated method stub
		
	}

	public Individuo copia() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double evaluaFunc() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getAt(int pos) {
		return valuesI[pos];
	}
	
	public void setAt(int pos, int newValue) {
		valuesI[pos] = newValue;
	}

	public int getPosOf(int at) {
		for (int i = 0; i < valuesI.length; i++)
			if (valuesI[i] == at)
				return i;
		return valuesI.length - 1;
	}

}
