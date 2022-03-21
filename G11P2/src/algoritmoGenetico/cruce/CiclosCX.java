package algoritmoGenetico.cruce;

import algoritmoGenetico.casos.Caso;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class CiclosCX extends Cruce {

	public Individuo[] cruzar(IndividuoInt i1, IndividuoInt i2) {
		// Generamos dos hijos
		IndividuoInt[] hijos = new IndividuoInt[2];
		
		// Para el primer hijo el main es el primer padre
		hijos[0] = obtieneIndividuo(i1, i2);
		
		// Para el segundo hijo el main es el otro padre
		hijos[1] = obtieneIndividuo(i2, i1);
		
		// Devolvemos a los hijos
		return hijos;
	}
	
	private IndividuoInt obtieneIndividuo(IndividuoInt main, IndividuoInt other) {
		// Obtenemos el caso para crear el nuevo individuo
		Caso c = main.getF();
		
		// Creamos al individuo que vamos a generar
		IndividuoInt ind = new IndividuoInt(c);
		
		// Inicializamos todo su array a -1 (servirá para identificar las posiciones todavía no completadas)
		for (int i = 0; i < ind.getL(); i++) 
			ind.setAt(i, -1);
		
		// Un array de booleanos que indica que el número en cada posición ya ha aparecido en el nuevo individuo
		boolean[] haAparecido = new boolean[ind.getL()];
		for (boolean b: haAparecido)
			b = false;
		
		// Hacemos el ciclo
		int pos = 0;
		int elem = main.getAt(0);
		while (!haAparecido[elem - 1]) {
			// Lo ponemos en el individuo que estamos creando
			ind.setAt(pos, elem);
			// Ya ha aparecido
			haAparecido[elem - 1] = true;
			// Conseguimos el elemento la posición que corresponda de other
			elem = other.getAt(pos);
			// Ahora conseguimos la posición
			pos = main.getPosOf(other.getAt(pos));
		}
		
		// Rellenamos con el otro individuo
		for (int i = 0; i < ind.getL(); i++)
			if (ind.getAt(i) == -1)
				ind.setAt(i, other.getAt(i));
		
		// Devolvemos al individuo generado
		return ind;
		
	}
}
