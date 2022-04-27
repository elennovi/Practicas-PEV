package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algoritmoGenetico.Par;
import algoritmoGenetico.individuos.Individuo;

public class Truncamiento extends Seleccion {
	// El umbral de truncamiento
	private static final int trunc = 50;
	
	public Individuo[] seleccionar(int seleccionados, Individuo[] poblacion) {
		List<Par> posFit = new ArrayList<Par>();
		// Evaluamos a los individuos y los almacenamos dentro de un array con el indice que ocupan en la poblacion
		for (int i = 1; i < poblacion.length; i++) {
			// Nos ayudará a calcular la media de la poblacion actual
			double evaluacion = poblacion[i].getFitDesplazado();
			// Guardamos la posicion que ocupa en la poblacion junto con su fitness para ordenarlo posteriormente
			posFit.add(new Par(i, evaluacion));
		}
		
		// Ordenamos el vector en funcion del fitness
		posFit.sort(new Comparator<Par>() {
			public int compare(Par o1, Par o2) {
				if (o1.greaterThan(o2))
					return -1;
				else if (o1.equals(o2))
					return 0;
				else 
					return 1;
				
			}
	    });
		
		// Ahora nos quedamos con los <trunc> elementos mejores por su fitness y los individuos restantes
		// los rellenamos en función de ese truncamiento
		Individuo[] selec = new Individuo[seleccionados];
		
		// Calculamos el número de individuos que tenemos que seleccionar de la poblacion y las veces que tiene que
		// aparecer cada individuo en los seleccionados al final
		double porcentajeD = (double) (trunc )/ (double) (100);
		int nSelec = (int) (seleccionados * porcentajeD);
		int nVeces = (int) (1 / porcentajeD);
		
		// Los individuos se distribuiran: i1 i2... in i1 i2... in i1...
		int individuosRestantes = seleccionados;
		for (int v = 0; v < nVeces; v++) {
			for (int i = 0; i < nSelec; i++) {
				selec[i + nSelec * v] = poblacion[posFit.get(i).getPos()].copia();
				individuosRestantes--;
			}
		}
		
		// Si el número de individuos es impar puede haber un error y que falten individuos, en cuyo caso, 
		// añadiremos tantos individuos como falten
		for (int i = 0; i < individuosRestantes; i++)
			selec[nSelec * nVeces + i] = poblacion[posFit.get(i).getPos()].copia();

		return selec;
	}

}
