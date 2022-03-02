package algoritmoGenetico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algoritmoGenetico.cruce.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;
import algoritmoGenetico.funciones.*;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;

public class AlgoritmoGenetico {
	private Seleccion fSelec;
	private Cruce fCruce;
	private Mutacion fMutacion;
	private double pCruce;
	private double elitismo;
	private int tamPoblacion;
	private int numGen;
	private Funcion f;
	private Individuo[] poblacion;
	
	// Falta a�adir los atributos de cada funcion algoritmo genetico
	
  /*
   * 1. Evaluacion ?
   * 2. Seleccion
   * 3. Cruce
   * 4. Mutacion

   * */
	
	public AlgoritmoGenetico(int tamPoblacion, int numGen, Seleccion fSelec, Cruce fCruce, Mutacion fMutacion, 
			double pCruce, double elitismo, Funcion f) {
		this.tamPoblacion = tamPoblacion;
		this.numGen = numGen;
		this.fSelec = fSelec;
		this.fCruce = fCruce;
		this.fMutacion = fMutacion;
		this.pCruce = pCruce;
		this.elitismo = elitismo;
		this.f = f;
		this.poblacion = new Individuo[tamPoblacion];
		run();
	}
	
	public void run() {
		// Generamos la poblacion inicial aleatoriamente
		generaPoblacionInicial();
		
		// Ahora ejecutamos el algoritmo genetico tantas veces como numGeneraciones se pidan
		for (int i = 0; i < numGen; i++)
			ejecutaAlgoritmoGenetico();
		
//		boolean[] var1 = new boolean[f.getLAt(0)];
//		for (int i = 0; i < f.getLAt(0); i++) 
//			var1[i] = true;
//		boolean[] var2 = new boolean[f.getLAt(1)];
//		for (int i = 0; i < f.getLAt(1); i++) 
//			var2[i] = true;
//		boolean[][] arrB = new boolean[f.getnVar()][];
//		arrB[0] = var1;
//		arrB[1] = var2;
//		double[] varsR = f.valorRealVariables(arrB);
//		for (int i = 0; i < f.getnVar(); i++)
//			System.out.println("Variable " + (i + 1) + ": " + varsR[i]);
	}
	
	public void generaPoblacionInicial() {
		// Generamos una poblacion inicial
		for (int i = 0; i < tamPoblacion; i++) {
			// Generamos un individuo booleano
			Individuo ind = new IndividuoBool(f);
			// LLamamos a la funci�n que lo genera aleatoriamente
			ind.initGenesAleatorio();
			poblacion[i] = ind;
		}
	}
	
	public String toString(IndividuoBool i) {
		String s = "";
		for (int j = 0; j < i.getL(); j++) {
			if (i.getAt(j))
				s += "1";
			else
				s += "0";
		}
		return s;
	}
	
	public void ejecutaAlgoritmoGenetico() {
		// Para almacenar el fitness de los individuos junto consigo mismos
		List<Pair<String, Integer>> words = new ArrayList<Pair<String, Integer>>();
		
		// Una vez ya tenemos la poblacion inicial la evaluamos
		double total = poblacion[0].evaluar();
		double mejor = total;
		for (int i = 1; i < tamPoblacion; i++) {
			// Nos ayudar� a calcular la media de la poblacion actual
			double evaluacion = poblacion[i].evaluar();
			total += evaluacion;
			// Si es mejor que la que teniamos lo actualizamos
			if (f.maximiza() && evaluacion > mejor)
				mejor = evaluacion;
			else if (!f.maximiza() && evaluacion < mejor)
				mejor = evaluacion;
		}
		
		// Nos quedamos con la �lite para que no pierda calidad
		words.sort(new Comparator<Pair>() {
	        @Override
	        public int compare(Pair o1, Pair o2) {
	            if (o1.getValue() > o2.getValue()) {
	                return -1;
	            } else if (o1.getValue().equals(o2.getValue())) {
	                return 0;
	            } else {
	                return 1;
	            }
	        }
	    });
		
		// Mostramos el valor de la poblacion actual
		System.out.println("El mejor valor es: " + mejor + " y la media es de: " + (total / tamPoblacion));
		
		// Ahora debemos seleccionar a los individuos
		Individuo[] seleccionados = fSelec.seleccionar(tamPoblacion, elitismo, poblacion, f.maximiza());
		
		// PROVISIONAL:
		// Mostramos todos los individuos con su genotipo y su fenotipo
		for (int i = 0; i < tamPoblacion; i++)
			System.out.println("Individuo " + (i + 1) + " con genotipo: " + toString((IndividuoBool) seleccionados[i]) + " y  fitness: " + seleccionados[i].evaluar());
		
		
		// Recorremos los seleccionados y guardamos en un array los que hayan pasado
		// la probabilidad de cruce
		IndividuoBool[] cruzan = new IndividuoBool[tamPoblacion];
		// Almacena las posiciones de los individuos que seleccionamos
		int[] posiciones = new int[tamPoblacion];

		int numCruzan = 0;
		for (int i = 0; i < tamPoblacion; i++) {
			double cruza = Math.random();
			// Si debe cruzarse
			if (cruza < pCruce) {
				cruzan[numCruzan] = (IndividuoBool) seleccionados[i];
				posiciones[numCruzan] = i;
				numCruzan++;
			}
		}
		
		// Si los que cruzan son impares debemos dejar a uno sin cruzar
		if (numCruzan % 2 != 0)
			numCruzan--;
		
		// Recorremos a los que se van a cruzar de dos en dos y aplicamos la funcion de cruce
		for (int i = 0; i < numCruzan; i += 2) {
			IndividuoBool[] hijos = fCruce.cruzar(cruzan[i], cruzan[i + 1]);
			// PROVISIONAL:
			System.out.println(toString((IndividuoBool)cruzan[i]) + " + " + toString((IndividuoBool)cruzan[i + 1]) 
			+ " = " + toString((IndividuoBool)hijos[0]) + " , " + toString((IndividuoBool)hijos[1]));
			// Nos quedamos con los hijos siempre independientemente de si son peores
			// que sus padres 
			seleccionados[posiciones[i]] = hijos[0];
			seleccionados[posiciones[i + 1]] = hijos[1];
		}
		
		// Mutamos sobre el array de seleccionados en funcion de la probabilidad de mutacion
		for (int i = 0; i < tamPoblacion;i++) {
			// Si debe mutar
			System.out.println("A: " + toString((IndividuoBool) seleccionados[i]));
			fMutacion.mutar((IndividuoBool) seleccionados[i]);
			System.out.println("D: " + toString((IndividuoBool) seleccionados[i]));
		}
		
		
		// Ahora tenemos la nueva poblacion en seleccionados
		poblacion = seleccionados;
		
	}
}