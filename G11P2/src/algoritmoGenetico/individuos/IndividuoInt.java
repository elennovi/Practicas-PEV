package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.casos.Caso;

public class IndividuoInt extends Individuo {
	// El array que representa al individuo (de enteros)
	int[] valuesI;
	
	public IndividuoInt(Caso c) {
		super(c);
		// Generamos un array de la longitud pertinente
		valuesI = new int[c.getNVuelos()];
	}

	public void evaluar() {
		double fit = 0;
		// Actualizamos 
		fitness = fit;
	}
	
	public void initGenesAleatorio() {
		// Generamos una array con todos los numeros
		List<Integer> l = new ArrayList<Integer>(c.getNVuelos());
		
		// Rellenamos con los numeros enteros en el intervalo 1-nVuelos
		for (int i = 1; i <= c.getNVuelos(); i++)
			l.add(i);
		
		// Para la generacion aleatoria
		Random rand = new Random();
		
		// Ahora vamos seleccionando posiciones aleatorias hasta que se vacie la lista
		int cont = 0;
		while(!l.isEmpty()) {
			// Seleccionamos un numero aleatorio entre 0-tamaño del array
			int pos = rand.nextInt(l.size());
			valuesI[cont] = l.get(pos);
			l.remove(pos);
			cont++;
		}
	}

	public Individuo copia() {
		IndividuoInt nuevo = new IndividuoInt(c);
		for (int i = 0; i < valuesI.length; i++)
			nuevo.setAt(i, valuesI[i]);
		return nuevo;
	}
	
	public int getAt(int pos) {
		return valuesI[pos];
	}
	
	public void setAt(int pos, int newValue) {
		valuesI[pos] = newValue;
	}

	public int getPosOf(int at) {
		for (int i = 0; i < valuesI.length; i++)
			if (valuesI[i] == at)
				return i;
		return valuesI.length - 1;
	}

}
