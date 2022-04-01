package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.casos.Caso;
import algoritmoGenetico.casos.Vuelo;

public class IndividuoInt extends Individuo {
	// El array que representa al individuo (de enteros)
	int[] valuesI;
	// Una matriz que se corresponda con el reparto de vuelos en
	// cada una de las pistas (de tamaño pistas x vuelos)
	int[][] vuelosPista;
	int[] lenPistas;
	double[] TLAs;
	
	public IndividuoInt(Caso c) {
		super(c);
		// Generamos un array de la longitud pertinente
		valuesI = new int[c.getNVuelos()];
	}

	public void evaluar() {
		// Generamos un array con la longitud de las pistas
		vuelosPista = new int[c.getNPistas()][c.getNVuelos()];
		lenPistas = new int[c.getNPistas()];
		for(int i = 0; i < c.getNPistas(); i++)
			lenPistas[i] = 0;
		
		
		
		// Necesitamos saber de cada pista el TLA del último vuelo asignado y el tipo
		// de avion 
		double[] ultimoTLAPista = new double[c.getNPistas()];
		int[] tipoUltimoPista = new int[c.getNPistas()];
		for (int i = 0; i < ultimoTLAPista.length; i++) {
			ultimoTLAPista[i] = 0;
			tipoUltimoPista[i] = 0;
		}
		
		// La lista en la que almacenamos los TLAs de todos los vuelos
		TLAs = new double[c.getNVuelos()];
		// La pista en la que aterriza cada vuelo
		int[] pistaVuelos = new int[c.getNVuelos()];
		
		// Inicializamos todos los valores iniciales
		for (int i = 0; i < valuesI.length; i++) {
			// Obtenemos el id de vuelo del que queremos encontrar el i
			Vuelo act = c.getVueloAt(valuesI[i] - 1);
			// Para todas  pistas calculamos el TLA si le asignamos ese vuelo 
			// a cada pista y nos quedamos con el mejor (menor) de todas las pistas
			double mejorTLA = 999999.0;
			// La pista en la que finalmente se dirige el vuelo
			int pista = 0;
			for (int j = 0; j < c.getNPistas(); j++) {
				double TLAPistaJ = max(ultimoTLAPista[j] + c.getSEPAt(tipoUltimoPista[j], act.getTipoAvion()), c.getTELAt(j, act.getPosVuelo() - 1));
				if (mejorTLA > TLAPistaJ) {
					mejorTLA = TLAPistaJ;
					pista = j;
				}
			}
			// Actualizamos los valores en esa pista
			ultimoTLAPista[pista] = mejorTLA;
			tipoUltimoPista[pista] = act.getTipoAvion();
			// Ya hemos calculado su TLA
			TLAs[valuesI[i] - 1] = mejorTLA;
			pistaVuelos[valuesI[i] - 1] = pista;
			vuelosPista[pista][lenPistas[pista]] = valuesI[i];
			lenPistas[pista]++;
		}
		
		// Ahora calculamos el fitness como la suma de la diferencia de TEL y TLA al cuadrado
		// para todos vuelos
		fitness = fitnessCuadradoDiferencia(TLAs, pistaVuelos);
	}
	
	private double fitnessCuadradoDiferencia(double[] TLAs, int[] pistaVuelo) {
		double sum = 0;
		for (int i = 0; i < TLAs.length; i++)
			sum += Math.pow((TLAs[i] - c.getTELAt(pistaVuelo[i], i)), 2);
		return sum;
	}

	public void initGenesAleatorio() {
		// Generamos una array con todos los numeros
		ArrayList<Integer> l = new ArrayList<Integer>(c.getNVuelos());
		
		// Rellenamos con los numeros enteros en el intervalo 1-nVuelos
		for (int i = 1; i <= c.getNVuelos(); i++)
			l.add(i);
		
		// Para la generacion aleatoria
		Random rand = new Random();
		
		// Ahora vamos seleccionando posiciones aleatorias hasta que se vacie la lista
		int cont = 0;
		while(!l.isEmpty()) {
			// Seleccionamos un numero aleatorio entre 0-tamaño del array
			int pos = rand.nextInt(l.size());
			valuesI[cont] = l.get(pos);
			l.remove(pos);
			cont++;
		}
	}

	public Individuo copia() {
		IndividuoInt nuevo = new IndividuoInt(c);
		for (int i = 0; i < valuesI.length; i++)
			nuevo.setAt(i, valuesI[i]);
		nuevo.setVuelosPista(vuelosPista, lenPistas);
		nuevo.setFitness(fitness);
		nuevo.setFitDesplazado(fitDesplazado);
		nuevo.setTLAs(TLAs);
		return nuevo;
	}

	private void setTLAs(double[] tLAs2) {
		double[] auxTLAs = new double[c.getNVuelos()];
		for(int i = 0; i < c.getNVuelos(); i++)
			auxTLAs[i] = tLAs2[i];
		this.TLAs = auxTLAs;
	}

	private void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setVuelosPista(int[][] vuelosPista, int[] lenPistas) {
		int [][] auxVuelos = new int[c.getNPistas()][c.getNVuelos()];
		for(int i = 0; i < c.getNPistas(); i++)
			for(int j = 0; j < lenPistas[i]; j++)
				auxVuelos[i][j] = vuelosPista[i][j];
		int[] auxLen = new int[c.getNPistas()];
		for(int i = 0; i < c.getNPistas(); i++)
			auxLen[i] = lenPistas[i];
		this.vuelosPista = auxVuelos;
		this.lenPistas = lenPistas;
	}

	public int getAt(int pos) {
		return valuesI[pos];
	}
	
	public void setAt(int pos, int newValue) {
		valuesI[pos] = newValue;
	}

	public int getPosOf(int at) {
		for (int i = 0; i < valuesI.length; i++)
			if (valuesI[i] == at)
				return i;
		return valuesI.length - 1;
	}
	
	private double max(double a, double b) {
		if (a > b)
			return a;
		return b;
	}
	
	public String toString() {
		String s = "(";
		for (int i = 0; i < valuesI.length - 1; i++)
			s+= valuesI[i] + ", ";
		s += valuesI[valuesI.length - 1] + ")";
		return s;
	}
	
	public int[][] getVuelosPista(){
		return vuelosPista;
	}
	
	public int[] getLenPista(){
		return lenPistas;
	}
	
	
	public double[] getTLAS(){
		return TLAs;
	}
	
}
