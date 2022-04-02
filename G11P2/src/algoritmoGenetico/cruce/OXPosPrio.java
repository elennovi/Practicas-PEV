package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class OXPosPrio extends Cruce {
	
	private static final int n = 3;

	@Override
	public Individuo[] cruzar(IndividuoInt madre, IndividuoInt padre) {
		
		IndividuoInt[] hijos = new IndividuoInt[2];
		//Creamos copias de los padres para mantener el intervalo 
		hijos[0] = (IndividuoInt)madre.copia();
		hijos[1] = (IndividuoInt)padre.copia();
		
		//Generamos los 2 puntos de cruce aleatorios y vemos cual es mayor y cual es menor
		Random rand = new Random();
		int[] posPrio = new int[n];
		for(int i = 0; i < n; i++)
			posPrio[i] = rand.nextInt(madre.getL());
		int mayor = max(posPrio);
		
		//Recorremos las posiciones vacías y las completamos con las correspondientes
		
		int contPadre = mayor + 1, contHijo = mayor + 1;
		//Si nos pasamos de la longitud del array
		if(contPadre >= padre.getL())
			contPadre = 0;
		if(contHijo >= hijos[0].getL())
			contHijo = 0;
		
		while(contHijo != mayor) {
			if(isNotPosPrio(contHijo, posPrio)) {
				if(isNotInRange(posPrio, padre.getAt(contPadre), hijos[0])) {
					//Si se puede poner
					hijos[0].setAt(contHijo, padre.getAt(contPadre));
					contHijo++;
					contPadre++;
				}
				else 
					contPadre++;
			}
			else//Si es posicion prioritaria, pasamos a cambiar la siguiente
				contHijo++;
				
			//Si nos pasamos de la longitud del array
			if(contPadre >= padre.getL())
				contPadre = 0;
			if(contHijo >= hijos[0].getL())
				contHijo = 0;
		}
		
		int contMadre = mayor + 1;
		contHijo = mayor + 1;
		//Si nos pasamos de la longitud del array
		if(contMadre >= madre.getL())
			contMadre = 0;
		if(contHijo >= hijos[1].getL())
			contHijo = 0;
		
		while(contHijo != mayor) {
			if(isNotPosPrio(contHijo, posPrio)) {
				if(isNotInRange(posPrio, madre.getAt(contMadre), hijos[1])) {
					//Si se puede poner
					hijos[1].setAt(contHijo, madre.getAt(contMadre));
					contHijo++;
					contMadre++;
				}
				else 
					contMadre++;
			}
			else//Si es posicion prioritaria, pasamos a cambiar la siguiente
				contHijo++;
				
	
			//Si nos pasamos de la longitud del array
			if(contMadre >= madre.getL())
				contMadre = 0;
			if(contHijo >= hijos[1].getL())
				contHijo = 0;
		}
		
		
		return hijos;
	}
	
	private boolean isNotPosPrio(int contHijo, int[] posPrio) {
		for(int i = 0; i < posPrio.length; i++)
			if(posPrio[i] == contHijo)
				return false;
		return true;
	}

	//Un elemento no está en un array en ciertas posiciones. 
	private boolean isNotInRange(int[] lpos, int n, IndividuoInt indi) {
		for(int i = 0; i < lpos.length; i++) 
			if(indi.getAt(lpos[i]) == n) 
				return false;
		return true;
	}

	//Mayor de 2 numeros
	private int max(int[] list) {
		int maxim = 0;
		for(int i : list)
			if(i > maxim)
				maxim = i;
		return maxim;
	}

}