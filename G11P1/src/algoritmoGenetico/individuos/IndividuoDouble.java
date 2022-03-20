package algoritmoGenetico.individuos;

import java.util.concurrent.ThreadLocalRandom;

import algoritmoGenetico.funciones.Funcion;

public class IndividuoDouble extends Individuo{
	private double[] genesR;
	
	public IndividuoDouble(Funcion f){
		super(f);
		genesR = new double[f.getnVar()];
	}
	
	// Getters y setters para los valores de los genes
	public void setAt(int i, double b) {
		genesR[i] = b;
	}
	
	public double getAt(int i) {
		return genesR[i];
	}
	
	public double[] getGenesReales() {
		return genesR;
	}
	
	public void setGenesReales(double[] genesR) {
		this.genesR = genesR;
	}
	
	public double evaluar() {
		return super.getF().evaluar(genesR);
	}
	
	
	public void initGenesAleatorio() {
		Funcion f = super.getF();
		for (int i = 0; i < genesR.length; i++) {
			double random = ThreadLocalRandom.current().nextDouble(f.getMinimoAt(i), f.getMaximoAt(i));
			genesR[i] = random;
		}
	}

	@Override
	public Individuo copia() {
		IndividuoDouble nuevo = new IndividuoDouble(super.getF());
		for (int i = 0; i < genesR.length; i++)
			nuevo.setAt(i, genesR[i]);
		return nuevo;
	}

	@Override
	public double evaluaFunc() {
		return super.getF().evaluar(genesR);
	}
}
