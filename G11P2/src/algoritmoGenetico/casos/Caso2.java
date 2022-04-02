package algoritmoGenetico.casos;

public class Caso2 extends Caso {
	private static final int[][] MATRIZ_TEL = {{11, 15, 6, 6, 9, 7, 15, 6, 6, 9, 7, 9, 15, 6, 7, 4, 11, 20, 9, 7, 5, 4, 7, 5, 6, 6, 7, 9, 9, 10},
											   {10, 17, 7, 7, 12, 6, 17, 7, 7, 12, 6, 7, 8, 8, 6, 16, 17, 8, 5, 9, 8, 5, 9, 10, 13, 21, 14, 5, 6, 6},
											   {9, 19, 8, 8, 15, 5, 19, 8, 8, 15, 5, 5, 4, 7, 7, 9, 9, 10, 13, 21, 6, 7, 7, 8, 7, 9, 9, 9, 7, 6}, 
											   {15, 17, 7, 7, 8, 12, 17, 7, 9, 12, 6, 6, 8, 8, 6, 11, 17, 8, 8, 9, 5, 5, 9, 13, 13, 21, 17, 5, 19, 6},
											   {6, 19, 8, 8, 15, 5, 16, 8, 8, 15, 7, 5, 4, 7, 7, 9, 6, 10, 13, 21, 22, 7, 7, 9, 7, 6, 8, 9, 7, 15},};
	private static final int NUM_VUELOS = 30;
	private static final int NUM_PISTAS = 5;
	private static final String[] ID_VUELOS = {"UA138", "UA532", "UA599", "NW358", "UA2987", "AA128", "UA1482", "NW357", "AA129", "UA2408", "UA805", "AA309", "BB345", "BA678", "WA128",
											   "AU139", "AU532", "AU599", "NN358", "AU2987", "NN128", "NN1482", "BN357", "BB129", "AU2408", "BA805", "BA309", "AA456", "BB723", "BW548"};
	private static final int[] TIPO_AVIONES = {W, G, W, W, P, W, G, W, W, P, W, G, G, P, P, W, P, W, W, G, W, W, P, W, G, W, W, P, W, G};
	
	
	public Caso2() {
	// Rellenamos la información relativa a los vuelos en las variables de la superclase
		super.nVuelos = NUM_VUELOS;
		super.nPistas = NUM_PISTAS;
		super.matrizTEL = MATRIZ_TEL;
		super.rellenarListaVuelos(ID_VUELOS, TIPO_AVIONES);
	}
}
