package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class PMX extends Cruce {

	@Override
	public Individuo[] cruzar(IndividuoInt padre, IndividuoInt madre) {
		IndividuoInt[] hijos = new IndividuoInt[2];
		//Creamos copias de los padres para mantener el intervalo 
		hijos[0] = (IndividuoInt)madre.copia();
		hijos[1] = (IndividuoInt)padre.copia();
		
		//Generamos los 2 puntos de cruce aleatorios y vemos cual es mayor y cual es menor
		Random rand = new Random();
		int r1 = rand.nextInt(madre.getL()), r2 = rand.nextInt(madre.getL());
		int mayor = max(r1, r2), menor= min(r1, r2);
		System.out.println(menor + ", " + mayor);
		
		//Si los 2 puntos de cruce son el mismo, los hijos son los mismos que los padres
		if(mayor != menor) {
			for (int i = 0; i < padre.getL(); i++) {
				//Si no está en el intervalo hay que cambiarlo. Si está, no hacemos nada
				if(isNotInRange(mayor, menor, i)) {
					
					//El hijo 0 tiene los valores centrales de la madre. Si el valor de la posición i del
					//padre no está en el intervalo de la madre, se pone el del padre. Si no, se pone el valor de 
					//la madre en que estuviera en la posición donde está el valor repetido.
					int pos = valueInPos(padre.getAt(i), hijos[0], mayor, menor);
					if(pos == -1) hijos[0].setAt(i, padre.getAt(i));
					else hijos[0].setAt(i, padre.getAt(pos));
					
					//El hijo 1 tiene los valores centrales del padre. Si el valor de la posición i de la
					//madre no está en el intervalo del padre, se pone la de la madre. Si no, se pone el valor del
					//padre en que estuviera en la posición donde está el valor repetido.
					int pos1 = valueInPos(madre.getAt(i), hijos[1], mayor, menor);
					if(pos1 == -1) hijos[1].setAt(i, madre.getAt(i));
					else hijos[1].setAt(i, madre.getAt(pos1));
				}
			}
		}
		
		
		return hijos;
	}
	
	//Mira si un valor está en el intervalo de genes y devuelve la posición en la que lo ha encontrado
	// (-1 si no está)
	private int valueInPos(int value, IndividuoInt ind, int mayor, int menor) {
		int pos = -1;
		for (int i = menor + 1; i <= mayor; i++)
			if(ind.getAt(i) == value) {
				pos = i;
				break;
			}
		return pos;
	}
	
	
	//Un numero no está en un rango. Un número está en el rango si es > que el menor o <= mayor
	private boolean isNotInRange(int mayor, int menor, int i) {
		if(i > mayor || i <= menor)
			return true;
		return false;
	}

	//Menor de 2 numeros
	private int min(int r1, int r2) {
		if(r1 < r2)
			return r1;
		return r2;
	}

	//Mayor de 2 numeros
	private int max(int r1, int r2) {
		if(r1 > r2)
			return r1;
		return r2;
	}


}
