import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		int[] main = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] other = {4, 5, 2, 1, 8, 7, 6, 9, 3};
		// El array con las posiciones diferentes
		int[] posisAleat = new int[4];
		
		// Ahora generamos una lista ordenada de todos los posibles valores de las posiciones
		ArrayList<Integer> lOrdenada = new ArrayList<Integer>(9);
		
		// Rellenamos con los numeros enteros en el intervalo 0-(nVuelos - 1)
		for (int i = 0; i < 9; i++)
			lOrdenada.add(i);
		
		// Ahora seleccionamos las posiciones aleatoriamente
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			int aleat = rand.nextInt(lOrdenada.size());
			posisAleat[i] = lOrdenada.get(aleat);
			lOrdenada.remove(aleat); // Eliminamos esa posicion
		}
		
		System.out.println(toString(posisAleat));
		
		// Un array que lleva el cromosoma del nuevo individuo que estamos generando
		int[] crom = new int[other.length];
		
		// El cromosoma tendrá los valores del cromosoma other y luego modificaremos ciertas posiciones
		for (int i = 0; i < crom.length; i++)
			crom[i] = other[i];
		
		// Primero obtenemos los valores que se encunetran en las posiciones dadas por el array
		// que nos llega de entrada
		int[] valoresPosis = new int[4];
		for (int i = 0; i < 4; i++)
			valoresPosis[i] = main[posisAleat[i]];
		
		System.out.println(toString(valoresPosis));
		
		// Ahora buscamos esos valores en el cromosoma del individuo other
		for (int i = 0; i < 4; i++) {
			int pos = -1;
			int j = 0;
			while(pos == -1) {
				if (other[j] == valoresPosis[i])
					pos = j; // Hemos encontrado la posicion
				else
					j++;
			}
			// Asignamos a esa posicion un -1 indicando que esa posicion se deberá rellenar
			crom[pos] = -1;
		}
		
		// Ahora rellenamos las posiciones con -1 con los valoresPosis
		int contPosis = 0;
		for (int i = 0; i < other.length; i++) {
			// Si estaba marcado
			if (crom[i] == -1) {
				crom[i] = valoresPosis[contPosis];
				contPosis++;
			}
		}
		
		System.out.println(toString(crom));
	}
	
	public static String toString(int[] arr) {
		String s = "";
		for (int e: arr)
			s += e + " ";
		return s;
	}
}
