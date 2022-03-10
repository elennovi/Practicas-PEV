package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public class Restos extends Seleccion{

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
		
		//Añadimos los individuos que tiene una proporcion*ind a seleccionar mayor o igual que 1
		cont = 0;
		int i = 0;
		while (i < poblacion.length && cont < seleccionados) {
			if(seleccionados * proporciones[i] >= 1) {
				ind[cont] = poblacion[i].copia();
				cont++;
			}
			i++;
		}
		
		//Llamamos a otro metodo de seleccion (ruleta)
		Seleccion ruleta = new Ruleta();
		Individuo[] aux = ruleta.seleccionar(seleccionados - cont, poblacion, maxim);
		
		//Copiamos esta subseleccion en la lista de seleccionados
		for(Individuo in : aux) {
			ind [cont] = in.copia();
			cont++;
		}
		
		return ind;
	}

}
