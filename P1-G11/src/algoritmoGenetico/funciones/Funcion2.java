package algoritmoGenetico.funciones;

public class Funcion2 extends Funcion {
	private static final int NUM_VAR = 2;
	private static final double[] MAXIMOS = {10.0, 10.0}, MINIMOS = {-10.0, -10.0};
	private static final boolean maximiza = false;
	
	// El constructor que crea la funcion 2
	public Funcion2(double prec) {
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
		// Indicamos si es una funcion que se pretende maximizar o no
		super.setMaximiza(maximiza);
	}
	
	public double evaluar(double[] vars) {
		double sol1 = 0;
		// El primer sumatorio
		for (int i = 1; i <= 5; i++) 
			sol1 += i * Math.cos((i + 1) * vars[0] + i);
		double sol2 = 0;
		// El segundo sumatorio
		for (int i = 1; i <= 5; i++)
			sol2 += i * Math.cos((i + 1) * vars[1] + i);
		return sol1 * sol2;
	}

}
