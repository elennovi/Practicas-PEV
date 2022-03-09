package algoritmoGenetico.funciones;

public class Funcion4Real extends Funcion {
	
	private static final double MAXIMO = Math.PI, MINIMO = 0.0;
	private static final boolean maximiza = false;
	
	public Funcion4Real(double prec, int n) {
		// Inicializamos el número de variables
		super.setnVar(n);
		// Inicializamos los valores maximos y minimos de las dos variables para este tipo de individuo
		double[] maximos = new double[n];
		double[] minimos = new double[n];
		for (int i = 0; i < n; i++) {
			maximos[i] = MAXIMO;
			minimos[i] = MINIMO;
		}
		super.setMaximos(maximos);
		super.setMinimos(minimos);
		// Indicamos si es una funcion que se pretende maximizar o no
		super.setMaximiza(maximiza);
	}

	@Override
	public double evaluar(double[] vars) {
		double ev = 0;
		for (int i = 1; i <= super.getnVar(); i++) {
			ev += Math.sin(vars[i - 1]) * Math.pow(Math.sin(((i + 1) * Math.pow(vars[i - 1], 2)) / Math.PI), 20);
		}
		return -ev;
	}

}
