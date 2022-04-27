package algoritmoGenetico.gramatica;

public class Estado {
	private int posCodon;
	private int nCodones;
	private int wrapsRestantes;
	
	public Estado(int wraps, int nCodones) {
		this.posCodon = 0;
		this.wrapsRestantes = wraps;
		this.nCodones = nCodones;
	}
	
	public void siguientePos() {
		posCodon++;
		if (nCodones <= posCodon) {
			if (siguienteWrap())
				posCodon = 0;
			else
				posCodon = -1;
		}

	}
	
	private boolean siguienteWrap() {
		if (wrapsRestantes < 0)
			return false;
		wrapsRestantes--;
		return true;
	}
	
	public int getPosCodon() {
		return posCodon;
	}
}
