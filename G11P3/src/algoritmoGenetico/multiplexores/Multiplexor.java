package algoritmoGenetico.multiplexores;

public abstract class Multiplexor {
	private static final int MAX_NUM_CODONES = 256;
	
	private int nCodones;
	private int nWraps;
	private int entradas;
	private int salidas;
	
	public Multiplexor(int nCodones, int nWraps, int entradas, int salidas) {
		this.nCodones = nCodones;
		this.nWraps = nWraps;
		this.entradas = entradas;
		this.salidas = salidas;
	}
	
	public int getNCodones() {
		return nCodones;
	}
	
	public int getNWraps() {
		return nWraps;
	}
	
	public int getEntradas() {
		return entradas;
	}
	
	public int getSalidas() {
		return salidas;
	}
	
	// Devuelve true si la combinacion de parametros es correct
	public abstract boolean esValida(boolean[] parametros);
	
	// El valor máximo que pueden tener los codones
	public int getMaxValueCodon() {
		return MAX_NUM_CODONES;
	}
}
