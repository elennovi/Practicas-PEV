package algoritmoGenetico.seleccion;
import algoritmoGenetico.individuos.Individuo;

public class Ruleta extends Seleccion{

	public Individuo[] seleccionar(int seleccionados, double elitismo, Individuo[] poblacion, boolean maxim) {
		// Creamos una lista de individuos que vamos a devolver (teniendo en cuenta que tenemos que generar
		// tantos individuos como se indique en el valor seleccionados)
		Individuo[] ind = new Individuo[seleccionados];
		
		// Ahora tenemos que evaluar todos los individuos mientras nos quedamos
		// con el fitness total conseguido
		double[] evaluaciones = new double[poblacion.length];
		int cont = 0;
		double total = 0;
		for (Individuo i: poblacion) {
			// Evaluamos al individuo
			evaluaciones[cont] = i.evaluar();
			// Lo acumulamos al total
			total += evaluaciones[cont];
			cont++;
		}
		
		// Ahora calculamos la proporcion (como de bueno es en comparacion
		// con los demas).
		double[] proporciones = new double[poblacion.length];
		cont = 0;
		for (double i: evaluaciones) {
			// Si es maximizar entonces el mejor fitness es el mas grande
			if (maxim)
				proporciones[cont] = i/total;
			// Si es minimizar entonces el mejor fitness es el mas bajo
			// por lo que ponemos las proporciones al reves
			else 
				proporciones[cont] = 1 - i/total;
			cont++;
		}
		
		// Ahora calculamos la proporcion acumulada
		double[] acumuladas = new double[poblacion.length];
		acumuladas[0] = proporciones[0];
		for (cont = 1; cont < poblacion.length; cont++)
			acumuladas[cont] = acumuladas[cont - 1] + proporciones[cont];
		
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
			ind[i] = poblacion[seleccionado];
		}
		
		return ind;
	}

}
