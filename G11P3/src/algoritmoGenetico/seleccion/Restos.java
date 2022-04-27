package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public class Restos extends Seleccion{

	@Override
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
			// El mejor fitness es el mas grande si estabamos minimizando
			proporciones[cont] = i.getFitDesplazado() / total;
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
		Individuo[] aux = ruleta.seleccionar(seleccionados - cont, poblacion);
		
		//Copiamos esta subseleccion en la lista de seleccionados
		for(Individuo in : aux) {
			ind [cont] = in.copia();
			cont++;
		}
		
		return ind;
	}

}
