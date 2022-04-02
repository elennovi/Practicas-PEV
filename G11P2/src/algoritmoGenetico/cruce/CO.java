package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class CO extends Cruce {

	@Override
	public Individuo[] cruzar(IndividuoInt i1, IndividuoInt i2) {
		// Obtenemos una lista coordinal con los elementos de cada uno de los individuos
		// que queremos cruzar
		int[] coi1 = generaListaCoordinal(i1);
		int[] coi2 = generaListaCoordinal(i2);
		
		// Ahora los cruzamos de forma monopunto
		int[][] cruzadas = cruceMonopunto(coi1, coi2);
		
		// Obtenemos los cromosomas ya cruzados
		int[] crom1 = reversoCoordinal(cruzadas[0]);
		int[] crom2 = reversoCoordinal(cruzadas[1]);
		
		// Asignamos los cromosomas ya cruzados a los dos nuevos individuos
		IndividuoInt hijo1 = (IndividuoInt) i1.copia();
		IndividuoInt hijo2 = (IndividuoInt) i2.copia();
		
		for (int i = 0; i < crom1.length; i++) {
			hijo1.setAt(i, crom1[i]);
			hijo2.setAt(i, crom2[i]);
		}
		
		// La lista con los individuos que se corresponden con los cruces de los dos padres
		Individuo[] hijos = {hijo1, hijo2};
		
		return hijos;
	}
	
	// Genera las listas coordinales
	private int[] generaListaCoordinal(IndividuoInt ind) {
		// Obtenemos una lista coordinal con los elementos del individuo
		int[] co = new int[ind.getL()];
		
		// Ahora generamos una lista ordenada de todos los posibles valores que toman los genes
		ArrayList<Integer> lOrdenada = new ArrayList<Integer>(ind.getL());
		
		// Rellenamos con los numeros enteros en el intervalo 1-nVuelos
		for (int i = 1; i <= ind.getL(); i++)
			lOrdenada.add(i);
		
		// Obtenemos ahora la lista coordinal con el arraylist
		for (int i = 0; i < co.length; i++) {
			int pos = -1; // El -1 indica que no se ha encontrado todavia la posicion en la que
			// se encuentra 
			int j = 0; // Para recorrer la lista
			while (pos == -1) {
				if (lOrdenada.get(j) == ind.getAt(i))
					pos = j;
				else
					j++; // Si no lo hemos encontrado nos salimos del bucle
			}
			// Lo eliminamos del arraylist
			lOrdenada.remove(pos);
			// Ponemos la posicion en el array coordinal
			co[i] = pos;
		}
		
		return co;
	}
	
	// Cruza dos listas utilizando el cruce monopunto
	private int[][] cruceMonopunto(int[] coi1, int[] coi2) {
		// Las listas cruzadas irán aquí
		int[][] listas = new int[2][coi1.length];
		
		// Generamos un número entero y por ese punto dividimos a los progenitores
		Random r = new Random();
		int corte = r.nextInt(coi1.length);
		
		for (int i = 0; i < coi1.length; i++) {
			// Hasta el corte, incluido, las listas son iguales
			if (i <= corte) {
				listas[0][i] = coi1[i];
				listas[1][i] = coi2[i];
			}
			// A partir del corte, las listas son las otras
			else {
				listas[0][i] = coi2[i];
				listas[1][i] = coi1[i];
			}
		}
		return listas;
	}
	
	// Sirve para transformar una lista coordinal a un cromosoma entero como teníamos
	// anteriormente
	private int[] reversoCoordinal(int[] co) {
		int[] crom = new int[co.length];
		
		// Ahora generamos una lista ordenada de todos los posibles valores que toman los genes
		ArrayList<Integer> lOrdenada = new ArrayList<Integer>(co.length);
		
		// Rellenamos con los numeros enteros en el intervalo 1-nVuelos
		for (int i = 1; i <= co.length; i++)
			lOrdenada.add(i);
		
		// Recorremos el cromosoma coordinal y extraemos el valor de las posiciones
		for(int i = 0; i < co.length; i++) {
			crom[i] = lOrdenada.get(co[i]);
			lOrdenada.remove(co[i]);
		}
		
		return crom;
	}
		
}
