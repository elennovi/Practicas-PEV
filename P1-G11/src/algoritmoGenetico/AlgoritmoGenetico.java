package algoritmoGenetico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.SwingUtilities;

import algoritmoGenetico.cruce.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;
import visualizacion.Ventana;
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
	private int numElite;
	private Ventana window;
	
	// Falta añadir los atributos de cada funcion algoritmo genetico
	
  /*
   * 1. Evaluacion ?
   * 2. Seleccion
   * 3. Cruce
   * 4. Mutacion

   * */
	
	public AlgoritmoGenetico(int tamPoblacion, int numGen, Seleccion fSelec, Cruce fCruce, Mutacion fMutacion, 
			double pCruce, double elitismo, Funcion f, Ventana window) {
		this.tamPoblacion = tamPoblacion;
		this.numGen = numGen;
		this.fSelec = fSelec;
		this.fCruce = fCruce;
		this.fMutacion = fMutacion;
		this.pCruce = pCruce;
		this.elitismo = elitismo;
		// Calculamos el número de individuos que van a formar parte de la élite
		numElite = (int) (this.tamPoblacion * (this.elitismo / 100));
		// PROVISIONAL
		System.out.println(numElite);
		this.f = f;
		this.poblacion = new Individuo[tamPoblacion];
		this.window = window;
		run();
	}
	
	public void run() {
		// Generamos la poblacion inicial aleatoriamente
		generaPoblacionInicial();
		
		// Ahora ejecutamos el algoritmo genetico tantas veces como numGeneraciones se pidan
		for (int i = 0; i < numGen; i++)
			ejecutaAlgoritmoGenetico(i);
		
//		boolean[] var1 = new boolean[f.getLAt(0)];
//		for (int i = 0; i < f.getLAt(0); i++) 
//			var1[i] = true && (i % 2 == 0);
//		boolean[] var2 = new boolean[f.getLAt(1)];
//		for (int i = 0; i < f.getLAt(1); i++) 
//			var2[i] = true && (i % 2 != 0);
//		boolean[][] arrB = new boolean[f.getnVar()][];
//		arrB[0] = var1;
//		arrB[1] = var2;
//		
//		double[] varsR = f.valorRealVariables(arrB);
//		for (int i = 0; i < f.getnVar(); i++)
//			System.out.println("Variable " + (i + 1) + ": " + varsR[i]);
//		
//		System.out.println("El fitness es: " + f.evaluar(varsR));	
	}
	
	public void generaPoblacionInicial() {
		// Generamos una poblacion inicial
		for (int i = 0; i < tamPoblacion; i++) {
			// Generamos un individuo booleano
			Individuo ind = new IndividuoBool(f);
			// LLamamos a la función que lo genera aleatoriamente
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
	
	public void ejecutaAlgoritmoGenetico(int nGen) {
		// Para almacenar el fitness de los individuos junto consigo mismos
		List<Par> posF = new ArrayList<Par>();
		
		// Una vez ya tenemos la poblacion inicial la evaluamos
		double total = poblacion[0].evaluar();
		posF.add(new Par(0, total));
		double mejor = total;
		for (int i = 1; i < tamPoblacion; i++) {
			// Nos ayudará a calcular la media de la poblacion actual
			double evaluacion = poblacion[i].evaluar();
			// Guardamos el individuo para comprobar posteriormente si pertenece
			// a la élite
			posF.add(new Par(i, evaluacion));
			total += evaluacion;
			// Si es mejor que la que teniamos lo actualizamos
			if (f.maximiza() && evaluacion > mejor)
				mejor = evaluacion;
			else if (!f.maximiza() && evaluacion < mejor)
				mejor = evaluacion;
		}
		
		// Ordenamos el vector en funcion del fitness
		posF.sort(new Comparator<Par>() {

			public int compare(Par o1, Par o2) {
				if (o1.greaterThan(o2))
					return -1;
				else if (o1.equals(o2))
					return 0;
				else 
					return 1;
			}
	    });
		
		// Creamos un array aque contenga a los individos de la élite (los mejores, aquellos que no queremos
		// perder)
		Individuo[] elite = new IndividuoBool[numElite];
		
		// Nos quedamos con tantos individuos como el porcentaje elite nos indique
		for (int i = 0; i < numElite; i++)
			elite[i] = poblacion[posF.get(i).getPos()];
		
		// Indicamos a la ventana que se han evaluado la nueva poblacion
		window.actualizar(nGen, mejor, (total / tamPoblacion));
		
		// Mostramos el valor de la poblacion actual
		System.out.println("El mejor valor de la generacion " + nGen  + " es: " + mejor + " y la media es de: " + (total / tamPoblacion));
		
		// Ahora debemos seleccionar a los individuos
		Individuo[] seleccionados = fSelec.seleccionar(tamPoblacion, elitismo, poblacion, f.maximiza());
		
		// PROVISIONAL:
		// Mostramos todos los individuos con su genotipo y su fenotipo
//		for (int i = 0; i < tamPoblacion; i++)
//			System.out.println("Individuo " + (i + 1) + " con genotipo: " + toString((IndividuoBool) seleccionados[i]) + " y  fitness: " + seleccionados[i].evaluar());
//		
		
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
//			System.out.println(toString((IndividuoBool)cruzan[i]) + " + " + toString((IndividuoBool)cruzan[i + 1]) 
//			+ " = " + toString((IndividuoBool)hijos[0]) + " , " + toString((IndividuoBool)hijos[1]));
			// Nos quedamos con los hijos siempre independientemente de si son peores
			// que sus padres 
			seleccionados[posiciones[i]] = hijos[0];
			seleccionados[posiciones[i + 1]] = hijos[1];
		}
		
		// Mutamos sobre el array de seleccionados en funcion de la probabilidad de mutacion
		for (int i = 0; i < tamPoblacion;i++) {
			// Si debe mutar
//			System.out.println("A: " + toString((IndividuoBool) seleccionados[i]));
			fMutacion.mutar((IndividuoBool) seleccionados[i]);
//			System.out.println("D: " + toString((IndividuoBool) seleccionados[i]));
		}
		
		// Creamos un array de pares para ordenarlos de menos a mayor
		List<Par> selecOrden = new ArrayList<Par>();
		
		// Recorremos los seleccionados y los evaluamos
		for (int i = 0; i < tamPoblacion; i++) {
			double evaluacion = seleccionados[i].evaluar();
			selecOrden.add(new Par(i, evaluacion));
		}
		
		// Los ordenamos de menor a mayor
		// Ordenamos el vector en funcion del fitness
		selecOrden.sort(new Comparator<Par>() {

			public int compare(Par o1, Par o2) {
				if (o1.greaterThan(o2))
					return 1;
				else if (o1.equals(o2))
					return 0;
				else 
					return -1;
			}
	    });
		
		// Sustituimos a los mejores por los seleccionados peores
		for (int i = 0; i < numElite; i++)
			seleccionados[selecOrden.get(i).getPos()] = elite[i];
		
		// Ahora tenemos la nueva poblacion en seleccionados
		poblacion = seleccionados;
		
	}

	public int getNumGen() {
		return numGen;
	}

	public void setNumGen(int numGen) {
		this.numGen = numGen;
	}
}