package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class OXOrdPrio extends Cruce {
	private static final int NUM_POS = 4;
	@Override
	public Individuo[] cruzar(IndividuoInt i1, IndividuoInt i2) {
		
		// Conseguimos los cromosomas cruzados
		int[] crom1 = generaCromosomaOX(i1, i2);
		int[] crom2 = generaCromosomaOX(i2, i1);
		
		// Generamos los hijos
		IndividuoInt hijo1 = (IndividuoInt) i1.copia();
		IndividuoInt hijo2 = (IndividuoInt) i2.copia();
		
		// Copiamos los cromosomas en los nuevos hijos
		for (int i = 0; i < crom1.length; i++) {
			hijo1.setAt(i, crom1[i]);
			hijo2.setAt(i, crom2[i]);
		}
		
		// El array de los hijos actualizado
		Individuo[] hijos = { hijo1, hijo2 };
		return hijos;
	}
	
	private int[] generaCromosomaOX(IndividuoInt main, IndividuoInt other) {
		// Generamos una serie de posiciones aleatoriamente
		int[] posisAleat = generaPosicionesAleat(main.getL());
		
		// Un array que lleva el cromosoma del nuevo individuo que estamos generando
		int[] crom = new int[other.getL()];
		
		// El cromosoma tendrá los valores del cromosoma other y luego modificaremos ciertas posiciones
		for (int i = 0; i < crom.length; i++)
			crom[i] = other.getAt(i);
		
		// Primero obtenemos los valores que se encunetran en las posiciones dadas por el array
		// que nos llega de entrada
		int[] valoresPosis = new int[NUM_POS];
		for (int i = 0; i < NUM_POS; i++)
			valoresPosis[i] = main.getAt(posisAleat[i]);
		
		// Ahora buscamos esos valores en el cromosoma del individuo other
		for (int i = 0; i < NUM_POS; i++) {
			int pos = -1;
			int j = 0;
			while(pos == -1) {
				if (other.getAt(j) == valoresPosis[i])
					pos = j; // Hemos encontrado la posicion
				else
					j++;
			}
			// Asignamos a esa posicion un -1 indicando que esa posicion se deberá rellenar
			crom[pos] = -1;
		}
		
		// Ahora rellenamos las posiciones con -1 con los valoresPosis
		int contPosis = 0;
		for (int i = 0; i < other.getL(); i++) {
			// Si estaba marcado
			if (crom[i] == -1) {
				crom[i] = valoresPosis[contPosis];
				contPosis++;
			}
		}
		
		return crom;
	}
	
	// Es una funcion que calcula un número dado (NUM_POS) de posiciones DIFERENTES
	private int[] generaPosicionesAleat(int L) {
		// El array con las posiciones diferentes
		int[] posiciones = new int[NUM_POS];
		
		// Ahora generamos una lista ordenada de todos los posibles valores de las posiciones
		ArrayList<Integer> lOrdenada = new ArrayList<Integer>(L);
		
		// Rellenamos con los numeros enteros en el intervalo 0-(nVuelos - 1)
		for (int i = 0; i < L; i++)
			lOrdenada.add(i);
		
		// Ahora seleccionamos las posiciones aleatoriamente
		Random rand = new Random();
		for (int i = 0; i < NUM_POS; i++) {
			int aleat = rand.nextInt(lOrdenada.size());
			posiciones[i] = lOrdenada.get(aleat);
			lOrdenada.remove(aleat); // Eliminamos esa posicion
		}
		
		// Devolvemos la lista de las posiciones diferentes ya seleccionadas
		return posiciones;
	}
}
