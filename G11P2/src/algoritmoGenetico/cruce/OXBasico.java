package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class OXBasico extends Cruce{

	
	public Individuo[] cruzar(IndividuoInt madre, IndividuoInt padre) {
		
		IndividuoInt[] hijos = new IndividuoInt[2];
		//Creamos copias de los padres para mantener el intervalo 
		hijos[0] = (IndividuoInt)madre.copia();
		hijos[1] = (IndividuoInt)padre.copia();
		
		//Generamos los 2 puntos de cruce aleatorios y vemos cual es mayor y cual es menor
		Random rand = new Random();
		int r1 = rand.nextInt(madre.getL()), r2 = rand.nextInt(madre.getL());
		int mayor = max(r1, r2), menor= min(r1, r2);
		
		//Ponemos un limite del bucle(cambiara desde el mayor hasta el menor+1. Si el menor coincide con el mayor, se hara hasta el mayor
		int limit = menor + 1;
		if(menor == padre.getL() - 1)
			limit = mayor;
		//Recorremos las posiciones vacías y las completamos con las correspondientes
		int contPadre = mayor + 1, contHijo = mayor + 1;
		//Si nos pasamos de la longitud del array
		if(contPadre >= padre.getL())
			contPadre = 0;
		if(contHijo >= hijos[0].getL())
			contHijo = 0;
		

		System.out.println(menor+" "+mayor);
		
		if(mayor != menor) {
			while(contHijo != limit) {
	
				if(isNotInRange(mayor, menor, padre.getAt(contPadre), hijos[0])) {
					//Si se puede poner
					hijos[0].setAt(contHijo, padre.getAt(contPadre));
					contHijo++;
					contPadre++;
				}
				else 
					contPadre++;
				
				
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
			
			while(contHijo != limit) {
				
				if(isNotInRange(mayor, menor, madre.getAt(contMadre), hijos[1])) {
					//Si se puede poner
					hijos[1].setAt(contHijo, madre.getAt(contMadre));
					contHijo++;
					contMadre++;
				}
				else 
					contMadre++;
		
				//Si nos pasamos de la longitud del array
				if(contMadre >= madre.getL())
					contMadre = 0;
				if(contHijo >= hijos[1].getL())
					contHijo = 0;
			}
		}
		
		return hijos;
	}
	
	//Un elemento no está en un array en un rango. 
	private boolean isNotInRange(int mayor, int menor, int n, IndividuoInt indi) {
		for(int i = menor + 1; i <= mayor; i++) 
			if(indi.getAt(i) == n) 
				return false;
		return true;
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
