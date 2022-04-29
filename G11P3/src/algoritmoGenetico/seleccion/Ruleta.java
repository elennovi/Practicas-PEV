package algoritmoGenetico.seleccion;
import algoritmoGenetico.individuos.Individuo;

public class Ruleta extends Seleccion{

	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion) {
		// Creamos una lista de individuos que vamos a devolver (teniendo en cuenta que tenemos que generar
		// tantos individuos como se indique en el valor seleccionados)
		Individuo[] ind = new Individuo[seleccionados];
		
		// Calculamos el fitness total
		double total = 0;
		for (Individuo i: poblacion)
			total += i.getFitDesplazado();

		
		// Ahora calculamos la proporcion (como de bueno es en comparacion
		// con los demas).
		double[] proporciones = new double[poblacion.length];
		int cont = 0;
		for (Individuo i: poblacion) {
			// El mejor fitness es el mas grande
			proporciones[cont] = i.getFitDesplazado() / total;
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
			ind[i] = poblacion[seleccionado].copia();
		}
		
		return ind;
	}

}
