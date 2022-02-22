package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public class Torneo extends Seleccion{

	@Override
	public Individuo[] seleccionar(int seleccionados, double elitismo, Individuo[] poblacion, boolean maxim) {
		//Lista de individuos
		Individuo[] ind = new Individuo[seleccionados];
		int cont = 0;
		//Se relizan tantos torneos como elementos a seleccionar
		for (int i = 0; i < seleccionados; i++) {
			if (cont + 1 >= seleccionados)
				cont = 0;
			//Las pajejas que entran al torneo se cogeran sucesivamente del array
			ind[i] = torneo2(poblacion[cont], poblacion[cont + 1], maxim, elitismo);
			cont += 2;
		}
		return ind;
	}
	
	Individuo torneo2(Individuo uno, Individuo dos, boolean maxim, double elitismo) {
		int ev1 = uno.evaluar(), ev2 = dos.evaluar();
		double d = Math.random();
		//Si se coge el mejor
		if(d > elitismo) {
			//Segun si se maximiza o minimiza
			if((ev1 > ev2 && maxim) || (ev1 < ev2 && !maxim))
				return uno;
			return dos;
		}
		//Si se coge el peor
		else {
			//Segun si se maximiza o minimiza
			if((ev1 < ev2 && maxim) || (ev1 > ev2 && !maxim))
				return uno;
			return dos;
		}
	}

}
