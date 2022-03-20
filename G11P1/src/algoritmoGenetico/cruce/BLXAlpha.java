package algoritmoGenetico.cruce;

import java.util.Random;

import algoritmoGenetico.funciones.Funcion;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

public class BLXAlpha extends Cruce {

	private static final double alpha = 0.3;
	public Individuo[] cruzar(IndividuoDouble papa, IndividuoDouble mama) {
		
		double[] fenoPapa = papa.getGenesReales();
		double[] fenoMama = mama.getGenesReales();
		
		// Calculamos los valores importantes
		double Cmax = fenoPapa[0], Cmin = fenoPapa[0];
		if (fenoMama[0] > Cmax)
			Cmax = fenoMama[0];
		if (fenoMama[0] < Cmin)
			Cmin = fenoMama[0];
		
		for(int i = 1; i < fenoPapa.length; i++) {
			if (fenoPapa[i] > Cmax)
				Cmax = fenoPapa[i];
			if (fenoPapa[i] < Cmin)
				Cmin = fenoPapa[i];
			if (fenoMama[i] > Cmax)
				Cmax = fenoMama[i];
			if (fenoMama[i] < Cmin)
				Cmin = fenoMama[i];
		}
		
		// Generamos valores aleatorios entre un intervalo que va desde
		double I = Cmax - Cmin;
		double Imin = Cmin - I * alpha;
		double Imax = Cmax + I * alpha;
		Random r = new Random();
		
		Funcion f = papa.getF();
		
		double[] fenoh1 = new double[fenoPapa.length];
		double[] fenoh2 = new double[fenoMama.length];
		
		for (int i = 0; i < fenoPapa.length; i++) {
			double dRandom1 = r.nextDouble();
			int iRandom1 = r.nextInt((int)(Imax - Imin + 1));
			double fRandom1 = dRandom1 + Imin + iRandom1;
			fenoh1[i] = fRandom1;
			
			if (fenoh1[i] > f.getMaximoAt(i) || fenoh1[i] < f.getMinimoAt(i)) {
				Individuo[] hijos = {papa, mama};
				return hijos;
			}
			
			double dRandom2 = r.nextDouble();
			int iRandom2 = r.nextInt((int)(Imax - Imin + 1));
			double fRandom2= dRandom2 + Imin + iRandom2;
			fenoh2[i] = fRandom2;
			
			if (fenoh2[i] > f.getMaximoAt(i) || fenoh2[i] < f.getMinimoAt(i)) {
				Individuo[] hijos = {papa, mama};
				return hijos;
			}
		}
		
		IndividuoDouble h1 = new IndividuoDouble(f);
		IndividuoDouble h2 = new IndividuoDouble(f);
		
		h1.setGenesReales(fenoh1);
		h2.setGenesReales(fenoh2);
		
		IndividuoDouble[] hijos = {h1, h2};
		
		return hijos;
	}
	@Override
	public Individuo[] cruzar(IndividuoBool i1, IndividuoBool i2) {return null;}

}
