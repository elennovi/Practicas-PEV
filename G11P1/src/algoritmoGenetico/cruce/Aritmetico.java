package algoritmoGenetico.cruce;

import algoritmoGenetico.funciones.Funcion;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public class Aritmetico extends Cruce {
	
	private static final double alpha = 0.6;
	
	public Individuo[] cruzar(IndividuoDouble papa, IndividuoDouble mama) {
		// Obtenemos el valor real de las variables
		double[] fenoPapa = papa.getGenesReales();
		double[] fenoMama = mama.getGenesReales();
		
		// Las nuevas variables para los hijos
		double[] fenoh1 = new double[fenoPapa.length];
		double[] fenoh2 = new double[fenoMama.length];
		
		// Hacemos una combinacion lineal de cada una de las variables de los dos
		for (int i = 0; i < fenoPapa.length; i++) {
			// Combinacion lineal del h1
			fenoh1[i] = fenoPapa[i] * alpha + fenoMama[i] * (1 - alpha);
			// Combinacion lineal del h2
			fenoh2[i] = fenoMama[i] * alpha + fenoPapa[i] * (1 - alpha);
		}
		
		// Transformamos de real a binario
		Funcion f = papa.getF();
		
		// Concatenamos para formar un único array de boolean
		IndividuoDouble h1 = new IndividuoDouble(f);
		IndividuoDouble h2 = new IndividuoDouble(f);
		
		h1.setGenesReales(fenoh1);
		h2.setGenesReales(fenoh2);
		
		IndividuoDouble[] hijos = {h1, h2};
		return hijos;
	}

	@Override
	public Individuo[] cruzar(IndividuoBool i1, IndividuoBool i2) {return null;}

}
