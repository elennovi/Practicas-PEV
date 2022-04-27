package algoritmoGenetico.multiplexores;

public class Multiplexor11  extends Multiplexor {
	private static final int NUM_CODONES = 15;
	private static final int NUM_ENTRADAS = 3;
	private static final int NUM_SALIDAS = 8;
	
	public Multiplexor11(int wraps) {
		super(NUM_CODONES, wraps, NUM_ENTRADAS, NUM_SALIDAS);
	}
	public boolean esValida(boolean[] parametros) {
		if (!parametros[0] && !parametros[1] && !parametros[2] && parametros[3]) return true;
		else if (!parametros[0] && !parametros[1] && parametros[2] && parametros[4]) return true;
		else if (!parametros[0] && parametros[1] && !parametros[2] && parametros[5]) return true;
		else if (!parametros[0] && parametros[1] && parametros[2] && parametros[6]) return true;
		else if (parametros[0] && !parametros[1] && !parametros[2] && parametros[7]) return true;
		else if (parametros[0] && !parametros[1] && parametros[2] && parametros[8]) return true;
		else if (parametros[0] && parametros[1] && !parametros[2] && parametros[9]) return true;
		else if (parametros[0] && parametros[1] && parametros[2] && parametros[10]) return true;
		else return false;
	}

}
