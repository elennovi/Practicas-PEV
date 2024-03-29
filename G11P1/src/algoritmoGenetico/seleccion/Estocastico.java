package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Estocastico extends Seleccion {

	@Override
	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion, boolean maxim) {
		// Creamos una lista de individuos que vamos a devolver (teniendo en cuenta que tenemos que generar
		// tantos individuos como se indique en el valor seleccionados)
		Individuo[] ind = new Individuo[seleccionados];
		
		// Ahora tenemos que evaluar todos los individuos mientras nos quedamos
		// con el fitness total conseguido
		double[] evaluaciones = new double[poblacion.length];
		int cont = 0;
		double max = poblacion[0].evaluar();
		double min = max;
		for (int i = 1; i < poblacion.length; i++) {
			double ev = poblacion[i].evaluar();
			// Evaluamos al individuo
			evaluaciones[cont] = ev;
			cont++;		
			// Comprobamos si es el nuevo maximo
			if (max < ev)
				max = ev;
			if (min > ev)
				min = ev;
		}
		
		// Desplazamiento de la aptitud utilizando el valor maximo
		double total = 0;
		for (int i = 0; i < poblacion.length; i++) {
			if (!maxim)
				evaluaciones[i] = max * 1.05 - evaluaciones[i];
			else 
				evaluaciones[i] = evaluaciones[i] + Math.abs(min);
			total += evaluaciones[i];
		}
		
		
		// Ahora calculamos la proporcion (como de bueno es en comparacion
		// con los demas).
		double[] proporciones = new double[poblacion.length];
		cont = 0;
		for (double i: evaluaciones) {
			// El mejor fitness es el mas grande si estabamos minimizando
			proporciones[cont] = i / total;
			cont++;
		}
		
		// Ahora calculamos la proporcion acumulada
		double[] acumuladas = new double[poblacion.length];
		acumuladas[0] = proporciones[0];
		for (cont = 1; cont < poblacion.length; cont++)
			acumuladas[cont] = acumuladas[cont - 1] + proporciones[cont];
		
		// Calculamos la distancia entre las marcas
		double dist = (double) (1) / (double) (seleccionados);
		// Calculamos el n�mero como un entero multiplicandolo por 100
		int maxSobre100 = (int) (dist * 100);
		// Calculamos ahora un n�mero aleatorio entre 0 y el numero maximo sobre 100
		Random rand = new Random();
		int randSobre100 = rand.nextInt(maxSobre100);
		// Lo convertimos a un valor sobre 100
		double r =  (double) (randSobre100) / (double) (100);
		
		// Calculamos las marcas
		double[] marcas = new double[seleccionados];
		for (int i = 0; i < seleccionados; i++)
			marcas[i] = r + dist * i;
		
		// Encontramos el intervalo en el que se encuentra cada marca
		for (int i = 0; i < seleccionados; i++) {
			double ant = 0;
			int seleccionado = poblacion.length - 1;
			for (int j = 0; j < poblacion.length; j++) {
				// Si es mayor o igual que el acumulado anterior y estrictamente menor
				// que su acumulado, lo seleccionamos
				if (ant <= marcas[i] && marcas[i] < acumuladas[j]) {
					seleccionado = j;
					break;
				}
				// El anterior es el acumulado actual
				ant = acumuladas[j];
			}
			
			// Ya tenemos al seleccionado, lo almacenamos como individuo
			// seleccionado
			ind[i] = poblacion[seleccionado].copia();
		}
		
		return ind;
	}

}
