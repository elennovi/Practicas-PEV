package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algoritmoGenetico.Par;
import algoritmoGenetico.individuos.Individuo;

public class Ranking extends Seleccion {
	
	// Una constante que representa la beta de la formula aplicada a la hora de calcular 
	// la nueva probabilidad de ser escogido
	private static final double BETA = 2.0;
	
	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion) {
		// El array en el que se encontrarán los individuos seleccionados
		Individuo[] elegidos = new Individuo[seleccionados];
		
		// Una lista sobre la que aplicaremos un orden (decreciente)
		List<Par> lOrdenada = new ArrayList<>();
		for (int i = 0; i < poblacion.length; i++)
			lOrdenada.add(new Par(i, poblacion[i].getFitDesplazado()));
		
		// Ordenamos la lista para generar el ranking que ordena de mayor a menor
		lOrdenada.sort(new Comparator<Par>() {
			public int compare(Par o1, Par o2) {
				if (o1.greaterThan(o2))
					return -1;
				else if (o1.equals(o2))
					return 0;
				else 
					return 1;
				
			}
	    });
		
		// Ahora obtenemos las nuevas puntuaciones
		double[] puntuacion = new double[poblacion.length];
		for (int i = 0; i < poblacion.length; i++)
			puntuacion[i] = nuevaPuntuacion(i, poblacion.length);
		
		// Ahora calculamos la puntuacion acumulada
		double[] acumuladas = new double[poblacion.length];
		acumuladas[0] = puntuacion[0];
		for (int cont = 1; cont < poblacion.length; cont++)
			acumuladas[cont] = acumuladas[cont - 1] + puntuacion[cont];
		
		// Ahora obtenemos numeros aleatorios
		for (int i = 0; i < seleccionados; i++) {
			// Un número aleatorio entre 0 y 1 incluidos
			double aleat = Math.random();
			// Ahora recorremos las acumuladas hasta que encontramos una 
			// que cumpla las caracteristicas
			double ant = 0;
			int seleccionado = poblacion.length - 1;
			for (int j = 0; j < poblacion.length; j++) {
				// Si es mayor o igual que el acumulado anterior y estrictamente menor
				// que su acumulado, lo seleccionamos
				if (ant <= aleat && aleat < acumuladas[j]) {
					seleccionado = j;
					break;
				}
				// El anterior es el acumulado actual
				ant = acumuladas[j];
			}
			// Ya tenemos al seleccionado, lo almacenamos como individuo
			// seleccionado
			elegidos[i] = poblacion[lOrdenada.get(seleccionado).getPos()].copia();
		}
		
		return elegidos;
	}
	
	// La fórmula para el calculo de la posicion en funcion de la posicion que toma 
	// un individuo en el ranking
	private double nuevaPuntuacion(int iMenos1, int n) {
		return (1.0 / (double) n) * (BETA - 2.0 * (BETA - 1.0) * ((double) iMenos1 / (double) (n - 1)));
	}

}
