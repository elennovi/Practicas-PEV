package algoritmoGenetico.gramatica;

import algoritmoGenetico.individuos.IndividuoInt;

public class Gramatica {
	private IndividuoInt individuo;
	
	public Gramatica(IndividuoInt indi) {
		this.individuo = indi;
	}
	
	public String getExpresion() {
		// Generamos un estado inicial con todos los wraps y la primera posicion
		Estado inicial = new Estado(individuo.getMultiplexor().getNWraps(), individuo.getNCodones());
		return (new Expresion(individuo, inicial)).getExpresion();
	}
}
