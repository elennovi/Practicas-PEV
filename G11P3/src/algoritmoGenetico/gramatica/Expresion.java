package algoritmoGenetico.gramatica;

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
		if(actual.getPosCodon() != -1)
			pos = individuo.getAt(actual.getPosCodon()) % (NUM_EXPRESIONES + numTerminales);
		else
			pos = -1;
		if (pos == AND) { // Si ha tocado poner un and lo añadimos y continuamos
			str = "(AND ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " )";
		}
		else if (pos == OR) {
			str = "(OR ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " )";
		}
		else if (pos == NOT) {
			str = "(NOT ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " )";
		}
		else if (pos == IF) {
			str = "(IF ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " ";
			str += (new Expresion(individuo, actual)).getExpresion();
			str += " )";
		}
		else if (pos == -1)
			str += "A0";
		else if (pos < NUM_EXPRESIONES + numEntradas)
			str += "A" + (pos - NUM_EXPRESIONES);
		else 
			str += "D" + (pos - NUM_EXPRESIONES - numEntradas);
		
		return str;
	}
}
