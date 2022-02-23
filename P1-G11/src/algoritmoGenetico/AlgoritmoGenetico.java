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
			// CALCULAR LA LONGITUD TOTAL DE LOS GENES
			// Individuo i = new IndividuoBool();
		}
		
	}
}