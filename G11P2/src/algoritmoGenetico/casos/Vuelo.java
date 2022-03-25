package algoritmoGenetico.casos;

public class Vuelo {
	private String idVuelo; // El identificador de vuelo
	private int posVuelo; // La posición del vuelo en el array 
	private int tipoAvion; // W = 0, G = 1, P = 2

	public Vuelo(String idVuelo, int posVuelo, int tipoAvion) {
		this.idVuelo = idVuelo;
		this.posVuelo = posVuelo;
		this.tipoAvion = tipoAvion;
	}

	public String getIdVuelo() {
		return idVuelo;
	}
	
	public int getPosVuelo() {
		return posVuelo;
	}

	public int getTipoAvion() {
		return tipoAvion;
	}
	
	
	
}
