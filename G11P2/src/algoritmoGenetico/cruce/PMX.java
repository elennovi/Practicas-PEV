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
		
		
		//Ponemos a 0 los valores que no están en el intervalo
		for(int i = 0; i < madre.getL(); i++) {
			if(i <= menor || i > mayor) {
				hijos[0].setAt(i, 0);
				hijos[1].setAt(i, 0);
			}
		}
		
		//Si los 2 puntos de cruce son el mismo, los hijos son los mismos que los padres
		if(mayor != menor || mayor -1 != menor) {
			for (int i = 0; i < padre.getL(); i++) {
				//Si no está en el intervalo hay que cambiarlo. Si está, no hacemos nada
				if(isNotInRange(mayor, menor, i)) {
					
					//El hijo 0 tiene los valores centrales de la madre. Si el valor de la posición i del
					//padre no está en el intervalo de la madre, se pone el del padre. Si no, se pone el valor de 
					//la madre en que estuviera en la posición donde está el valor repetido. SI ese valor está repettido, 
					//se sigue iterando
					int pos = valueInPos(padre.getAt(i), hijos[0]);
					int posFinal = i;					
					
					while(pos != -1) {
						posFinal = pos;
						pos = valueInPos(padre.getAt(posFinal), hijos[0]);
					}
					hijos[0].setAt(i, padre.getAt(posFinal));
					
					//El hijo 1 tiene los valores centrales del padre. Si el valor de la posición i de la
					//madre no está en el intervalo del padre, se pone la de la madre. Si no, se pone el valor del
					//padre en que estuviera en la posición donde está el valor repetido.
					int pos1 = valueInPos(madre.getAt(i), hijos[1]);
					int posFinal1 = i;
					while(pos1 != -1) {
						posFinal1 = pos1;
						pos1 = valueInPos(madre.getAt(posFinal1), hijos[1]);
					}
					hijos[1].setAt(i, madre.getAt(posFinal1));
				}
			}
		}
		
		
		for(int j = 0; j < madre.getL(); j++)
			if(hijos[0].getAt(j) == 0 || hijos[1].getAt(j) == 0) {
				System.out.println("La has cagado");
				System.out.print("hijo 1: ");
				System.out.print("[ ");
				for (int i = 0; i<12; i++) {
					System.out.print(hijos[0].getAt(i) + " ");
				}
				System.out.println("]");
				System.out.print("hijo 2: ");
				System.out.print("[ ");
				for (int i = 0; i<12; i++) {
					System.out.print(hijos[1].getAt(i) + " ");
				}
				System.out.println("]");
			}
		
		
		return hijos;
	}
	
	//Mira si un valor está en el intervalo de genes y devuelve la posición en la que lo ha encontrado
	// (-1 si no está)
	private int valueInPos(int value, IndividuoInt ind) {
		int pos = -1;
		for (int i = 0; i < ind.getL(); i++)
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
