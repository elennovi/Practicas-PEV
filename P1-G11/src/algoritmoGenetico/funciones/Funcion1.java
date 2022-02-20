package algoritmoGenetico.funciones;

import algoritmoGenetico.individuos.Individuo;

public class Funcion1 extends Funcion {
	private static final int NUM_VAR = 2;
	private static final double[] MAXIMOS = {12.1, 5.8}, MINIMOS = {-3.0, 4.1};
	
	// El constructor que crea la funcion 1
	Funcion1(double prec) {
		// Inicializamos el número de variables
		super.setnVar(NUM_VAR);
		// Inicializamos los valores maximos y minimos de las dos variables para este tipo de individuo
		super.setMaximos(MAXIMOS);
		super.setMinimos(MINIMOS);
		// Generamos un array con las longitudes que tiene que tener cada una de las dos variables
		int[] aux = {super.calculaL(super.getMaximoAt(0), super.getMinimoAt(0), prec), super.calculaL(super.getMaximoAt(1), super.getMinimoAt(1), prec)}; 
		super.setL(aux);
		// Calculamos la longitud total como la suma de las longitudes de las variables que lo componen:
		super.setLTotal(super.sumaL());
	}
	
	// Funcion que representa la formula de dicha funcion
	public double evaluar(Individuo i) {
		// A COMPLETAR
		return 0.0;
	}
}
