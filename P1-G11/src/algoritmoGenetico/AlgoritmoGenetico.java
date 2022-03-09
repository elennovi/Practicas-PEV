package algoritmoGenetico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algoritmoGenetico.cruce.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;
import visualizacion.Ventana;
import algoritmoGenetico.funciones.*;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoBool;
import algoritmoGenetico.individuos.IndividuoDouble;

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
	private double[] generaciones;
	private double[] mejores;
	private double[] medias;
	private double[] mejoresAbs;
	private double mejorAbsoluto;
	private boolean indBool;
	
	public AlgoritmoGenetico(int tamPoblacion, int numGen, Seleccion fSelec, Cruce fCruce, Mutacion fMutacion, 
			double pCruce, double elitismo, Funcion f, Ventana window, boolean indBool) {
		this.tamPoblacion = tamPoblacion;
		this.numGen = numGen;
		this.fSelec = fSelec;
		this.fCruce = fCruce;
		this.fMutacion = fMutacion;
		this.pCruce = pCruce;
		this.elitismo = elitismo;
		// Calculamos el número de individuos que van a formar parte de la élite
		numElite = (int) (this.tamPoblacion * this.elitismo);
		generaciones = new double[this.numGen];
		mejores = new double[this.numGen];
		medias = new double[this.numGen];
		mejoresAbs = new double[this.numGen];
		// Falta el array que calcula el mejor absoluto
		for (int i = 0; i < this.numGen; i++) {
			generaciones[i] = (i + 1);
			mejores[i] = 0;
			medias[i] = 0;
			mejoresAbs[i] = 0;
		}
		this.f = f;
		this.poblacion = new Individuo[tamPoblacion];
		this.window = window;
		this.indBool = indBool;
	}
	
	public void run() {
		// Generamos la poblacion inicial aleatoriamente
		generaPoblacionInicial();
		
		// Ahora ejecutamos el algoritmo genetico tantas veces como numGeneraciones se pidan
		new Thread(new Runnable() {
            @Override 
            public void run() 
            {

            	for (int i = 0; i < numGen; i++) {
                    ejecutaAlgoritmoGenetico(i);
                }

            }   
        }).start();
	}
	
	public void generaPoblacionInicial() {
		// Generamos una poblacion inicial
		for (int i = 0; i < tamPoblacion; i++) {
			Individuo ind;
			// Generamos un individuo del tipo que corresponda
			if (indBool)
				ind = new IndividuoBool(f);
			else
				ind = new IndividuoDouble(f);
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
		
		// Comprobamos si ha cambiado el mejor absoluto
		if (nGen == 0)
			mejorAbsoluto = mejor;
		else if ((mejorAbsoluto < mejor && f.maximiza()) || (mejorAbsoluto > mejor && !f.maximiza()))
			mejorAbsoluto = mejor;
		
		// Ordenamos el vector en funcion del fitness
		posF.sort(new Comparator<Par>() {
			public int compare(Par o1, Par o2) {
				if (f.maximiza()) {
					if (o1.greaterThan(o2))
						return -1;
					else if (o1.equals(o2))
						return 0;
					else 
						return 1;
				}
				else {
					if (o1.greaterThan(o2))
						return 1;
					else if (o1.equals(o2))
						return 0;
					else 
						return -1;
				}
				
			}
	    });
		
		// Creamos un array aque contenga a los individos de la élite (los mejores, aquellos que no queremos
		// perder)
		Individuo[] elite = new Individuo[numElite];
		
		// Nos quedamos con tantos individuos como el porcentaje elite nos indique
		for (int i = 0; i < numElite; i++)
			elite[i] = poblacion[posF.get(i).getPos()].copia();
		
		// Indicamos a la ventana que se han evaluado la nueva poblacion
		mejores[nGen] = mejor;
		medias[nGen] = (total / tamPoblacion);
		mejoresAbs[nGen] = mejorAbsoluto;
		
		// Esperamos unos segundos para retrasar la ejecución
		try {
		    Thread.sleep(10);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		// Le decimos a la ventana que se actualice
		window.actualizar(mejores, medias, mejoresAbs);
		
		// Mostramos el valor de la poblacion actual
		System.out.println("El mejor valor de la generacion " + nGen  + " es: " + mejor + " y la media es de: " + (total / tamPoblacion));
		
		// Ahora debemos seleccionar a los individuos
		Individuo[] seleccionados = fSelec.seleccionar(tamPoblacion, poblacion, f.maximiza());
		
		
		// Recorremos los seleccionados y guardamos en un array los que hayan pasado
		// la probabilidad de cruce
		Individuo[] cruzan = new Individuo[tamPoblacion];
		// Almacena las posiciones de los individuos que seleccionamos
		int[] posiciones = new int[tamPoblacion];

		int numCruzan = 0;
		
		for (int i = 0; i < tamPoblacion; i++) {
			double cruza = Math.random();
			// Si debe cruzarse
			if (cruza < pCruce) {
				cruzan[numCruzan] = seleccionados[i];
				posiciones[numCruzan] = i;
				numCruzan++;
			}
		}
		
		// Si los que cruzan son impares debemos dejar a uno sin cruzar
		if (numCruzan % 2 != 0)
			numCruzan--;
		
		// Recorremos a los que se van a cruzar de dos en dos y aplicamos la funcion de cruce
		for (int i = 0; i < numCruzan; i += 2) {
			Individuo[] hijos = fCruce.cruzar(cruzan[i], cruzan[i + 1]);
			// Nos quedamos con los hijos siempre independientemente de si son peores
			// que sus padres 
			seleccionados[posiciones[i]] = hijos[0];
			seleccionados[posiciones[i + 1]] = hijos[1];
		}
		
		// Mutamos sobre el array de seleccionados en funcion de la probabilidad de mutacion
		for (int i = 0; i < tamPoblacion;i++) {
			// Si debe mutar
			fMutacion.mutar(seleccionados[i]);
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
				if (f.maximiza()) {
					if (o1.greaterThan(o2))
						return 1;
					else if (o1.equals(o2))
						return 0;
					else 
						return -1;
				}
				else {
					if (o1.greaterThan(o2))
						return -1;
					else if (o1.equals(o2))
						return 0;
					else 
						return 1;
				}
				
			}
	    });
						
		// Sustituimos a los mejores por los seleccionados peores
		for (int i = 0; i < numElite; i++) {
			seleccionados[selecOrden.get(i).getPos()] = elite[i];
		}
		
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