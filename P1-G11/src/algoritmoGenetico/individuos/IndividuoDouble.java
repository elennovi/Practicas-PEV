package algoritmoGenetico.individuos;

import algoritmoGenetico.funciones.Funcion;

public class IndividuoDouble extends Individuo{
	private double[] genesR;
	
	public IndividuoDouble(Funcion f){
		super(f);
		genesR = new double[f.getLTotal()];
	}
	
	// Getters y setters para los valores de los genes
	public void setAt(int i, double b) {
		genesR[i] = b;
	}
	
	public double getAt(int i) {
		return genesR[i];
	}
	
	public double evaluar() {
		return super.getF().evaluar(genesR);
	}
	
	
	public void initGenesAleatorio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Individuo copia() {
		// TODO Auto-generated method stub
		return null;
	}
}
