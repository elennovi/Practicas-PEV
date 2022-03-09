package algoritmoGenetico.funciones;
import java.lang.Math;

public abstract class Funcion {
	private int nVar; // El número de variables que recibe la funcion
	private int lTotal; // La longitud total en bits de todas las variables
	private int[] l; // Las longitudes en bits de todas las variables
	
	private double[] maximos; // Los valores maximos que toman todas las variables
	private double[] minimos; // Los valores minimos que toman todas las variables
	
	private boolean maximiza; // Indica si es una funcion que maximiza
	
	public abstract double evaluar(double[] vars);
	
	// Calcula la longitud en bits que deberia tener una variable double
	public int calculaL(double max, double min, double PREC) {
		return (int) (log(1 + (max - min)/ PREC, 2));
	}
	
	// Calcula el logaritmo en base 2
	private double log(double num, int base) {
		return (Math.log10(num) / Math.log10(base));
	}
	
	// Calcula la suma de las longitudes
	public int sumaL() {
		int e = 0;
		// Recorremos todas las longitudes de las variables y las sumamos
		for (int i: l)
			e += i;
		return e;
	}
	
	public double getMaximoAt(int i) {
		return maximos[i];
	}
	public void setMaximos(double[] maximos) {
		this.maximos = maximos;
	}
	
	public int getnVar() {
		return nVar;
	}
	public void setnVar(int nVar) {
		this.nVar = nVar;
	}

	public double getMinimoAt(int i) {
		return minimos[i];
	}
	public void setMinimos(double[] minimos) {
		this.minimos = minimos;
	}

	public int[] getL() {
		return l;
	}
	public void setL(int[] l) {
		this.l = l;
	}
	
	public int getLAt(int pos) {
		return l[pos];
	}
	
	public int getLTotal() {
		return lTotal;
	}
	
	public void setLTotal(int lTotal) {
		this.lTotal = lTotal;
	}

	public void setMaximiza(boolean value) {
		maximiza = value;
	}

	public boolean maximiza() {
		return maximiza;
	}

	public double[] valorRealVariables(boolean[][] valuesB) {
		double[] valuesR = new double[nVar];
		for (int i = 0; i < nVar; i++) {
			valuesR[i] = minimos[i] + bin2dec(valuesB[i]) * ((maximos[i] - minimos[i]) / (Math.pow(2, l[i]) - 1));
		}
		return valuesR;
	}
	
	public boolean[][] valorBoolVariables(double[] valuesR){
		boolean[][] valuesB = new boolean[nVar][];
		for (int i = 0; i < nVar; i++)
			valuesB[i] = dec2bin((int) (((valuesR[i] - minimos[i]) * (Math.pow(2, l[i]) - 1) / (maximos[i] - minimos[i]))), l[i]);
		return valuesB;
	}
	
	private int bin2dec(boolean[] bin) {
		String s = "";
		for (boolean b: bin) {
			if (b) s+= "1";
			else s+= "0";
		}
		return Integer.parseInt(s,2);
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
}
