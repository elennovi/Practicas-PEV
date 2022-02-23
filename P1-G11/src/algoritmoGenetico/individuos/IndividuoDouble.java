package algoritmoGenetico.individuos;

public class IndividuoDouble extends Individuo{
	private double[] genesR;
	
	public IndividuoDouble(int l){
		super(l);
		genesR = new double[l];
	}
	
	// Getters y setters para los valores de los genes
	public void setAt(int i, double b) {
		genesR[i] = b;
	}
	
	public double getAt(int i) {
		return genesR[i];
	}
	
	public int evaluar() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public void initGenesAleatorio() {
		// TODO Auto-generated method stub
		
	}
}
