package algoritmoGenetico.funciones;

public class Funcion4Bool extends Funcion {
	
	private static final double MAXIMO = Math.PI, MINIMO = 0.0;
	private static final boolean maximiza = false;
	
	public Funcion4Bool(double prec, int n) {
		// Inicializamos el número de variables
		super.setnVar(n);
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
		// Generamos un array con las longitudes que tiene que tener cada una de las dos variables
		int[] aux = new int[n];
		int v = super.calculaL(MAXIMO, MINIMO, prec);
		for (int i = 0; i < n; i++)
			aux[i] = v;
		super.setL(aux);
		// Calculamos la longitud total como la suma de las longitudes de las variables que lo componen:
		super.setLTotal(super.sumaL());
	}

	@Override
	public double evaluar(double[] vars) {
		double ev = 0;
		for (int i = 0; i < super.getnVar(); i++) {
			ev += Math.sin(vars[i]) * Math.pow(Math.sin(((i + 1) * Math.pow(vars[i], 2)) / Math.PI), 20);
		}
		return -ev;
	}

}
