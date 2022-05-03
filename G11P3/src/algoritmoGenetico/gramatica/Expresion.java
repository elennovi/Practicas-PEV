package algoritmoGenetico.gramatica;

import java.util.Random;

import algoritmoGenetico.individuos.IndividuoInt;

public class Expresion {
	private static final int NUM_EXPRESIONES = 4;
	private static final int AND = 0, OR = 1, NOT = 2, IF = 3;
	private int numTerminales;
	private int numEntradas;
	private Estado actual;
	private IndividuoInt individuo;
	
	public Expresion(IndividuoInt individuo, Estado actual) {
		this.numTerminales = individuo.getMultiplexor().getSalidas() + individuo.getMultiplexor().getEntradas();
		this.numEntradas = individuo.getMultiplexor().getEntradas();
		this.actual = actual;
		this.individuo = individuo;
	}
	
	public String getExpresion() {
		String str = "";
		int pos;
		actual.siguientePos();
		if(actual.getPosCodon() != -1) // Conseguimos la posicion de la expresion que vamos a ejecutar
			pos = individuo.getAt(actual.getPosCodon()) % (NUM_EXPRESIONES + numTerminales);
		else // Tenemos que poner terminales
			pos = -1;
		// Expresiones compuestas
		if (pos == AND) 
			str = this.getAND();
		else if (pos == OR)
			str = this.getOR();
		else if (pos == NOT) 
			str = this.getNOT();
		else if (pos == IF)
			str = this.getIF();
		// Un terminal aleatorio
		else if (pos == -1)
			str = this.getTerminal();
		// Terminales de entrada
		else if (pos < NUM_EXPRESIONES + numEntradas) 
			str += "A" + (pos - NUM_EXPRESIONES);
		// Terminales de salida
		else 
			str += "D" + (pos - NUM_EXPRESIONES - numEntradas);
		
		return str;
	}
	
	private String getIF() {
		String str = "(IF ";
		str += this.getExpresion();
		str += " ";
		str += this.getExpresion();
		str += " ";
		str += this.getExpresion();
		str += " )";
		return str;
	}
	
	private String getOR() {
		String str = "(OR ";
		str += this.getExpresion();
		str += " ";
		str += this.getExpresion();
		str += " )";
		return str;
	}
	
	private String getAND() {
		String str = "(AND ";
		str += this.getExpresion();
		str += " ";
		str += this.getExpresion();
		str += " )";
		return str;
	}
	
	
	private String getNOT() {
		String str = "(NOT ";
		str += this.getExpresion();
		str += " )";
		return str;
	}
	
	private String getTerminal() {
		String str;
		// Obtenemos un numero aleatorio en el intervalo 0-(numTerminales - 1)
		Random rand = new Random(0);
		int aleat = rand.nextInt(numTerminales);
		// Si es un terminal de entrada
		if (aleat < numEntradas)
			str = "A" + aleat;
		// Si es un terminal de salida
		else
			str = "D" + (aleat - numEntradas);
		return str;
	}
}
