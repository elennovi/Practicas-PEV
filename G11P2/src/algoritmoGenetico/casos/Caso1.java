package algoritmoGenetico.casos;

public class Caso1 extends Caso {
	private static final int[][] MATRIZ_TEL = {{11, 15, 6, 6, 9, 7, 15, 6, 6, 9, 7, 9},
											   {10, 17, 7, 7, 12, 6, 17, 7, 7, 12, 6, 7},
	                                           {9, 19, 8, 8, 15, 5, 19, 8, 8, 15, 5, 5}};
	private static final int NUM_VUELOS = 12;
	private static final int NUM_PISTAS = 3;
	private static final String[] ID_VUELOS = {"UA138", "UA532", "UA599", "NW358", "UA2987", "AA128", "UA1482", "NW357", "AA129", "UA2408", "UA805", "AA309"};
	private static final int[] TIPO_AVIONES = {W, G, W, W, P, W, G, W, W, P, W, G};
	
	
	public Caso1() {
		// Rellenamos la información relativa a los vuelos en las variables de la superclase
		super.nVuelos = NUM_VUELOS;
		super.nPistas = NUM_PISTAS;
		super.matrizTEL = MATRIZ_TEL;
		super.rellenarListaVuelos(ID_VUELOS, TIPO_AVIONES);
	}
	
}
