package algoritmoGenetico;
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
	private double pMutacion;
	private double pCruce;
	private double elitismo;
	private int tamPoblacion;
	private double prec;
	private int numGen;
	private Funcion f;
	private Individuo[] poblacion;
	
	// Falta añadir los atributos de cada funcion algoritmo genetico
	
  /*
   * 1. Evaluacion ?
   * 2. Seleccion
   * 3. Cruce
   * 4. Mutacion

   * */
	
	public AlgoritmoGenetico(int tamPoblacion, double prec, int numGen, Seleccion fSelec, Cruce fCruce, Mutacion fMutacion, 
			double pMutacion, double pCruce, double elitismo, Funcion f) {
		this.tamPoblacion = tamPoblacion;
		this.prec = prec;
		this.numGen = numGen;
		this.fSelec = fSelec;
		this.fCruce = fCruce;
		this.fMutacion = fMutacion;
		this.pMutacion = pMutacion;
		this.pCruce = pCruce;
		this.elitismo = elitismo;
		this.f = f;
		this.poblacion = new Individuo[tamPoblacion];
		run();
	}
	
	public void run() {
		// Generamos una poblacion inicial
		for (int i = 0; i < tamPoblacion; i++) {
			// Generamos un individuo booleano
			Individuo ind = new IndividuoBool(f);
			// LLamamos a la función que lo genera aleatoriamente
			ind.initGenesAleatorio();
			poblacion[i] = ind;
		}
		
		// Una vez ya tenemos la poblacion inicial la evaluamos
		double total = poblacion[0].evaluar();
		double mejor = total;
		for (int i = 1; i < tamPoblacion; i++) {
			// Nos ayudará a calcular la media de la poblacion actual
			total += poblacion[i].evaluar();
		}
		
		// Mostramos el valor de la poblacion actual
		System.out.println("El mejor valor es: " + mejor + " y la media es de: " + (total / tamPoblacion));
		
		// Ahora debemos seleccionar a los individuos
		Individuo[] seleccionados = fSelec.seleccionar(tamPoblacion, elitismo, poblacion, f.maximiza());
		
		// Recorremos los seleccionados y guardamos en un array los que hayan pasado
		// la probabilidad de cruce
		IndividuoBool[] cruzan = new IndividuoBool[tamPoblacion];
		int numCruzan = 0;
		for (int i = 0; i < tamPoblacion; i++) {
			double cruza = Math.random();
			// Si debe cruzarse
			if (cruza < pCruce) {
				cruzan[numCruzan] = (IndividuoBool) seleccionados[i];
				numCruzan++;
			}
		}
		
		// Si los que cruzan son impares debemos dejar a uno sin cruzar
		if (numCruzan % 2 != 0)
			numCruzan--;
		
		// Recorremos a los que se van a cruzar de dos en dos y aplicamos la funcion de cruce
		for (int i = 0; i < numCruzan; i += 2) {
			IndividuoBool[] hijos = fCruce.cruzar(cruzan[i], cruzan[i + 1]);
			// Nos quedamos con los hijos siempre independientemente de si son peores
			// que sus padres 
		}
		
		// Mutamos
	}
}