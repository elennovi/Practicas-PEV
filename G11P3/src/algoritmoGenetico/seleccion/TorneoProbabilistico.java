package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class TorneoProbabilistico extends Seleccion{
	private static final double  pSelec = 0.4;
	@Override
	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion) {
		//Lista de individuos seleccionados
		Individuo[] ind = new Individuo[seleccionados];

		//Se relizan tantos torneos como elementos a seleccionar
		for (int i = 0; i < seleccionados; i++) {
			Random r = new Random();
			//Se realiza una seleccion entre 3 individuos aleatorios
			ind[i] = torneo3(poblacion[r.nextInt(poblacion.length)], poblacion[r.nextInt(poblacion.length)], 
					poblacion[r.nextInt(poblacion.length)], pSelec);
		}
		return ind;
	}
	
	
	//Torneo de 3 individuos
	private Individuo torneo3(Individuo uno, Individuo dos, Individuo tres, double p) {
		double ev1 = uno.getFitness(), ev2 = dos.getFitness(), ev3 = tres.getFitness();
		double d = Math.random();

		//Si nosquedamos con el mejor
		if( d > p) {
			//El mejor depende de si se quiere maximizar o minimizar
			if(ev1 < ev2 && ev1 < ev3)
				return uno;
			else if(ev2 < ev1 && ev2 < ev3)
				return dos;
			return tres;
		}
		
		//Si nos quedamos con el peor
		else {
			//El peor depende de si se quiere maximizar o minimizar
			if(ev1 > ev2 && ev1 > ev3)
				return uno;
			else if(ev2 > ev1 && ev2 > ev3)
				return dos;
			return tres;
		}
		
	}

}
