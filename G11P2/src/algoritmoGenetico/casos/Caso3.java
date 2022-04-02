package algoritmoGenetico.casos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Caso3 extends Caso {
	private static final int NUM_PISTAS = 4;
	private static final int MAX_VUELOS = 70;
	private static final int MIN_VUELOS = 50;
	private static final int MIN_TEL = 5;
	private static final int MAX_TEL = 15;
	private Random rand; // El generador de números aleatorios
	
	// Para la generación aleatoria de ids de vuelos utilizaremos un abecedario
	private static final String[] ABECEDARIO = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
												"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z"};
	
	public Caso3(int semilla) {
		this.rand = new Random(semilla); // Generamos el aleatorio con la semilla seleccionada
		super.nPistas = NUM_PISTAS; // El numero de pistas está fijado
		super.nVuelos = MIN_VUELOS + rand.nextInt(MAX_VUELOS - MIN_VUELOS); // Un numero entre
		// el numero minimo y maximo de vuelos
		super.rellenarListaVuelos(this.generaIDs(), this.generaTipos());;
		super.matrizTEL = generaTEL();
	}
	
	private String[] generaIDs() {
		String[] ids = new String[super.nVuelos];
		List<Integer> lOrdenada = new ArrayList<>();
		
		// Los números serán del 1 hasta el nVuelos generado pero se repartiran aleatoriamente
		for (int i = 0; i < super.nVuelos; i++)
			lOrdenada.add(i + 1);
		
		// Para cada uno de los vuelos:
		for (int i = 0; i < super.nVuelos; i++) {
			// Generamos 2 letras para cada uno de los vuelos
			String id = "";
			// Las generamos al azar del abecedario
			id += ABECEDARIO[rand.nextInt(ABECEDARIO.length)];
			id += ABECEDARIO[rand.nextInt(ABECEDARIO.length)];
			// Generamos un número aleatorio
			int aleat = rand.nextInt(lOrdenada.size());
			id += lOrdenada.get(aleat);
			lOrdenada.remove(aleat); // Lo eliminamos de la lista para que no vuelva a aparecer
			
			// Ya tenemos construida la id para ese vuelo
			ids[i] = id;
		}		
		return ids;
	}
	
	private int[] generaTipos() {
		int[] tipos = new int[super.nVuelos];
		// Generamos aleatoriamente todos los tipos de los vuelos 
		for (int i = 0; i < super.nVuelos; i++)
			tipos[i] = rand.nextInt(3);
		return tipos;
	}

	private int[][] generaTEL(){
		int[][] TELs = new int[super.nPistas][super.nVuelos];
		// Para cada una de las pistas
		for (int i = 0; i < super.nPistas; i++)
			// Para cada uno de los vuelos
			for (int j = 0; j < super.nVuelos; j++) {
				// Generamos un número aleatorio que se corresponderá con el TEL en el intervalo
				// [MIN_TEL, MAX_TEL] que viene dado como constante
				TELs[i][j] = MIN_TEL+ rand.nextInt(MAX_TEL - MIN_TEL);
			}
		return TELs;
	}
	
}
