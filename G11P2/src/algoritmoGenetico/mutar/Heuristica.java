package algoritmoGenetico.mutar;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class Heuristica extends Mutacion {
	
	private static final int n = 3; //Numero de genes a mutar

	@Override
	public void mutar(Individuo i) {
		IndividuoInt indi = (IndividuoInt) i;
		
		// Generamos las 3 posiciones a mutar y guardamos el número de vuelo correspondiente
		int [] pos = new int[n];//Posiciones aleatorias
		int [] elem = new int[n];//Elemenetos en esas posiciones
		int[] orden = new int[n];//Orden final, inicializado al actual.
		
		// Ahora generamos una lista ordenada de todos los posibles valores de las posiciones
		ArrayList<Integer> lOrdenada = new ArrayList<Integer>(indi.getL());
		
		// Rellenamos con los numeros enteros en el intervalo 0-(nVuelos - 1)
		for (int h = 0; h < indi.getL(); h++)
			lOrdenada.add(h);
		
		// Ahora seleccionamos las posiciones aleatoriamente
		Random rand = new Random();
		for (int h = 0; h < n; h++) {
			int aleat = rand.nextInt(lOrdenada.size());
			pos[h] = lOrdenada.get(aleat);
			lOrdenada.remove(aleat); // Eliminamos esa posicion
		}
		
		// Los valores que se encunetran en las posiciones dadas 
		for (int h = 0; h < n; h++) {
			elem[h] = indi.getAt(pos[h]);
			orden[h] = elem[h];
		}
		
		
		IndividuoInt auxiliar = (IndividuoInt) indi.copia();
		double mejor = auxiliar.getFitness();
		
		//Generamos permutaciones
		for(int j = 1; j < n; j++) {
			for(int k = 0; k < n && k != j; k++) {
				for(int l = 0; l < n && l != j && l != k; l++) {
					//cambiamos el individuo auxiliar para que tenga ese orden y lo evaluamos
					auxiliar.setAt(pos[0], elem[j]);
					auxiliar.setAt(pos[1], elem[k]);
					auxiliar.setAt(pos[2], elem[l]);
					auxiliar.evaluar();
					//Si el nuevo individuo es mejor, nos quedamos con él
					if(auxiliar.getFitness() < mejor) {
						mejor = auxiliar.getFitness();
						orden[0] = elem[j];
						orden[1] = elem[k];
						orden[2] = elem[l];
					}
				}
			}
		}
		
		
		//Colocamos el mejor orden 
		for(int j = 0; j < n; j++)
			indi.setAt(pos[j], orden[j]);
	}

}
