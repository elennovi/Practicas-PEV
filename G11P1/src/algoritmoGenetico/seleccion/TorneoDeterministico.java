package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class TorneoDeterministico extends Seleccion{

	@Override
	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion, boolean maxim) {
		//Lista de individuos seleccionados
		Individuo[] ind = new Individuo[seleccionados];

		//Se relizan tantos torneos como elementos a seleccionar
		for (int i = 0; i < seleccionados; i++) {
			Random r = new Random();
			//Se realiza una seleccion entre 3 individuos aleatorios
			ind[i] = torneo3(poblacion[r.nextInt(poblacion.length)], poblacion[r.nextInt(poblacion.length)], 
					poblacion[r.nextInt(poblacion.length)], maxim);
		}
		return ind;
	}
	
	
	//Torneo de 3 individuos
	private Individuo torneo3(Individuo uno, Individuo dos, Individuo tres, boolean maxim) {
		double ev1 = uno.evaluar(), ev2 = dos.evaluar(), ev3 = tres.evaluar();

		//Nos quedamos siempre con el mejor
		//El mejor depende de si se quiere maximizar o minimizar
		if((ev1 > ev2 && ev1 > ev3 && maxim) || (ev1 < ev2 && ev1 < ev3 && !maxim))
			return uno;
		else if((ev2 > ev1 && ev2 > ev3 && maxim) || (ev2 < ev1 && ev2 < ev3 && !maxim))
			return dos;
		return tres;
	}

}
