package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.gramatica.Gramatica;
import algoritmoGenetico.multiplexores.Multiplexor;

public class IndividuoInt extends Individuo {
	// El array que representa al individuo (de enteros)
	int[] valuesI;
	
	public IndividuoInt(Multiplexor m) {
		super(m);
		// Generamos un array de la longitud pertinente
		valuesI = new int[m.getNCodones()];
	}

	public void evaluar() {
		double nuevoFit = 0.0;
		String[] aPalabras = (this.toString()).split(" ");
		List<String> lPalabras = new ArrayList<String>(); 
		
		// Recorremos todas las posibilidades de valores de entrada y contamos los aciertos o fallos
		for (int i = 0; i < Math.pow(2, m.getEntradas() + m.getSalidas()); i++) {
			// Copiamos las palabras a una lista dinamica
			for (String p: aPalabras)
				lPalabras.add(p);
			boolean[] inOut = this.dec2bin(i, m.getEntradas() + m.getSalidas());
			boolean sol = this.getValueFor(inOut, lPalabras);
			// Solo si la solucion real es la misma que la obtenida por la gramatica decodificada
			if (sol == m.esValida(inOut)) 
				nuevoFit++;
		}
		this.setFitness(nuevoFit);
	}
	
	private boolean[] dec2bin(int dec, int longit) {
		String binS = Integer.toBinaryString(dec);
		boolean[] b = new boolean[longit];
		// Calculamos los que serán 0's
		int ultCero = longit - binS.length();
		for (int i = 0; i < ultCero; i++)
			b[i] = false;
		for (int i = ultCero; i < binS.length(); i++) {
			if (binS.charAt(i) == '1')
				b[i] = true;
			else 
				b[i] = false;
		}
		return b;
	}
	
	public void initGenesAleatorio() {
		Random rand = new Random();
		// Inicializamos cada codon con un número aleatorio entre 0 y el máximo
		for (int i = 0; i < valuesI.length; i++)
			valuesI[i] = rand.nextInt(m.getMaxValueCodon());
	}

	public Individuo copia() {
		IndividuoInt nuevo = new IndividuoInt(m);
		for (int i = 0; i < valuesI.length; i++)
			nuevo.setAt(i, valuesI[i]);
		return nuevo;
	}

	private void setFitness(double fitness) {
		this.fitness = fitness;
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

	public String toString() {
		Gramatica gram = new Gramatica(this); // Obtenemos la gramatica
		return gram.getExpresion();
	}
	
	public boolean getValueFor(boolean[] parametros, List<String> palabras) {
		boolean result = false;
		// Leemos la primera palabra
		String p = palabras.get(0);
		palabras.remove(0); // Eliminamos el primero
		if (p.equals("(AND")) { 
			boolean prim = getValueFor(parametros, palabras);
			boolean sec = getValueFor(parametros, palabras);
			result = prim && sec;
		}
		else if(p.equals("(OR")) {
			boolean prim = getValueFor(parametros, palabras);
			boolean sec = getValueFor(parametros, palabras);
			result = prim || sec;
		}
		else if (p.equals("(NOT"))
			result = !getValueFor(parametros, palabras);
		else if (p.equals("(IF")) {
			boolean condicion = getValueFor(parametros, palabras);
			boolean prim = getValueFor(parametros, palabras);
			boolean sec = getValueFor(parametros, palabras);
			if (condicion)
				result = prim;
			else
				result = sec;
		}
		else if (p.charAt(0) == 'A')
			result = parametros[Integer.parseInt(p.charAt(1) + "")];
		else if (p.charAt(0) == 'D')
			result = parametros[m.getEntradas() + Integer.parseInt(p.charAt(1) + "")];
		else
			result = getValueFor(parametros, palabras); // Por si leemos un ")", que no cuente como true o false
		return result;
	}
	
}
