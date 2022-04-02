package algoritmoGenetico.mutar;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoInt;

public class Inversion extends Mutacion {

	public Inversion(double p) {
		super(p);
	}

	@Override
	public void mutar(Individuo i) {
		IndividuoInt ind = (IndividuoInt) i;
		
		//Si probabilisticamente toca mutar
		if(Math.random() <= super.getProbMutar()) {
			// Generamos los 2 puntos de inverión
			Random rand = new Random();
			int r1 = rand.nextInt(i.getL()), r2 = rand.nextInt(i.getL());
			int mayor = max(r1, r2), menor= min(r1, r2);
			
			//Creamos un array para copiar los elementos que hay que invertir (los copiaremos del último al primero.
			int [] copia = new int[mayor - menor];
			int cont = 0;
			for(int j = mayor; j > menor; j--) {
				copia[cont] = ind.getAt(j);
				cont++;
			}
			
			//Invertimos el orden en el individuo
			cont = 0;
			for(int j = menor + 1; j <= mayor; j++) {
				ind.setAt(j, copia[cont]);
				cont ++;
			}
			
		}		
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
