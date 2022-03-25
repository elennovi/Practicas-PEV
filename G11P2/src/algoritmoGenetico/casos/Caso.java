package algoritmoGenetico.casos;

public abstract class Caso {
	// Una matriz SEP (que indica la separacion que debe haber entre cada
	// vuelo en funcion del tipo de aviones que se pongan juntos en una misma
	// pista
	protected static final double[][] matrizSEP = {{1, 1.5, 2}, 
												   {1, 1.5, 1.5},
												   {1, 1, 1}};
	protected static final int W = 0; // El valor entero de cada uno de los tipos de aviones
	protected static final int G = 1;
	protected static final int P = 2;
	protected int[][] matrizTEL; // Una matriz TEL que lleve para cada pista el tiempo estimado de llegada
	protected Vuelo[] infoVuelos; // Un array con los vuelos del caso en cuestion
	protected int nVuelos;
	protected int nPistas;

	// Dadas las listas con los IDs y el tipo de avion de cada vuelo rellena la información de
	// todos los vuelos
	protected void rellenarListaVuelos(String[] idVuelos, int[] tipoAviones) {
		infoVuelos = new Vuelo[nVuelos];
		for (int i = 0; i < nVuelos; i++)
			infoVuelos[i] = new Vuelo(idVuelos[i], i + 1, tipoAviones[i]);
	}
	
	public double getSEPAt(int i, int j) {
		return matrizSEP[i][j];
	}
	
	public int getTELAt(int i, int j) {
		return matrizTEL[i][j];
	}
	
	public Vuelo getVueloAt(int i) {
		return infoVuelos[i];
	}

	public int getNVuelos() {
		return nVuelos;
	}
	
	public int getNPistas() {
		return nPistas;
	}
}
