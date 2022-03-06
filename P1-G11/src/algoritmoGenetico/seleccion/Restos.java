package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public class Restos extends Seleccion{

	@Override
	public Individuo[] seleccionar(int seleccionados, double pSelec, Individuo[] poblacion, boolean maxim) {
		
		// Lista de individuos
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
		Individuo[] aux = ruleta.seleccionar(seleccionados - cont, pSelec, poblacion, maxim);
		
		//Copiamos esta subseleccion en la lista de seleccionados
		for(Individuo in : aux) {
			ind [cont] = in.copia();
			cont++;
		}
		
		return ind;
	}

}
