package algoritmoGenetico.multiplexores;

public class Multiplexor6 extends Multiplexor {
	private static final int NUM_CODONES = 10;
	private static final int NUM_ENTRADAS = 2;
	private static final int NUM_SALIDAS = 4;
	
	public Multiplexor6(int wraps) {
		super(NUM_CODONES, wraps, NUM_ENTRADAS, NUM_SALIDAS);
	}

	public boolean esValida(boolean[] parametros) {
		if (!parametros[0] && !parametros[1] && parametros[2]) return true;
		else if (!parametros[0] && parametros[1] && parametros[3]) return true;
		else if (parametros[0] && !parametros[1] && parametros[4]) return true;
		else if (parametros[0] && parametros[1] && parametros[5]) return true;
		else return false;
	}
	
}
