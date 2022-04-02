package algoritmoGenetico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import algoritmoGenetico.cruce.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;
import visualizacion.Ventana;
import visualizacion.VentanaSolucion;
import algoritmoGenetico.casos.*;
import algoritmoGenetico.individuos.*;

public class AlgoritmoGenetico {
	private Individuo[] poblacion;
	private Seleccion fSelec;
	private Cruce fCruce;
	private Mutacion fMutacion;
	private Caso c;
	
	private Individuo mejor;
	private double pCruce, elitismo, mejorAbsoluto;
	private double[] generaciones, mejores, medias, mejoresAbs;
	
	private int tamPoblacion, numGen, numElite;
	
	private Ventana window;
	
	
	public AlgoritmoGenetico(int tamPoblacion, int numGen, Seleccion fSelec, Cruce fCruce, Mutacion fMutacion, 
			double pCruce, double elitismo, Caso c, Ventana window) {
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
		this.c = c;
		this.poblacion = new Individuo[tamPoblacion];
		this.window = window;
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
                    ejecutar(i);
                }
            	IndividuoInt indi = (IndividuoInt) mejor.copia();
            	new VentanaSolucion(indi.getTLAS(), indi.getLenPista(), indi.getVuelosPista(), c, indi.getFitness());

            }   
        }).start();
	}	
	
	public void generaPoblacionInicial() {
		// Generamos una poblacion inicial
		for (int i = 0; i < tamPoblacion; i++) {
			Individuo ind;
			ind = new IndividuoInt(c);
			// LLamamos a la función que lo genera aleatoriamente
			ind.initGenesAleatorio();
			poblacion[i] = ind;
		}
	}
	
	public void ejecutar(int nGen) {
		// Primero evaluamos a los individuos para actualizar su fitness
		evaluar();
		
		// Desplazamiento de la aptitud para los métodos que lo requieran (ruleta, restos...)
		calculoDesplazamiento();
				
		// Obtener los valores para la representación gráfica (mejorGeneracion, mejorAbsoluto y la media)
		valoresGeneracion(nGen);
		
		//IMPORTANTE QUITAR
		IndividuoInt solParcial = (IndividuoInt) mejor.copia();
		solParcial.getVuelosPista();
		
		// Nos quedamos con un array de los mejores individuos (en función del elitismo seleccionado)
		Individuo[] elite = calculaElite();
		
		// Actualizamos la ventana
		actualizaVentana();
		
		// Seleccionamos a los individuos según su fitness
		Individuo[] seleccionados = seleccionar();
		
		// Cruzamos a los individuos entre los seleccionados
		cruzar(seleccionados);
		
		// Mutamos a los individuos
		mutar(seleccionados);
		
		// Reintroducimos la elite en los seleccionados
		introducirElite(seleccionados, elite);
		
		// Actualizamos la poblacion con los individuos seleccionados despues de haber pasado por todas las
		// fases del algoritmo genetico
		poblacion = seleccionados;
	}
	
	private void introducirElite(Individuo[] seleccionados, Individuo[] elite) {
		// Creamos un array de pares para ordenarlos de menos a mayor
		List<Par> ordenados = new ArrayList<Par>();
		
		// Recorremos los seleccionados y los guardamos para ordenarlos
		for (int i = 0; i < tamPoblacion; i++) 
			ordenados.add(new Par(i, seleccionados[i].getFitness()));
		
		// Los ordenamos de peor a mejor (mayor a menor, porque estamos minimizando)
		ordenados.sort(new Comparator<Par>() {
			public int compare(Par o1, Par o2) {
				if (o1.greaterThan(o2))
					return -1;
				else if (o1.equals(o2))
					return 0;
				else 
					return 1;
			}
	    });
						
		// Sustituimos a los mejores por los seleccionados peores
		for (int i = 0; i < numElite; i++) {
			seleccionados[ordenados.get(i).getPos()] = elite[i];
		}
	}

	private void mutar(Individuo[] seleccionados) {
		// Mutamos sobre el array de seleccionados en funcion de la probabilidad de mutacion
		for (int i = 0; i < tamPoblacion;i++) {
			// Si debe mutar
			fMutacion.mutar(seleccionados[i]);
			seleccionados[i].evaluar();
		}
	}

	private void cruzar(Individuo[] seleccionados) {
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
			Individuo[] hijos;
			hijos = fCruce.cruzar((IndividuoInt) cruzan[i], (IndividuoInt) cruzan[i + 1]);
			// Ahora evaluamos a los hijos de nuevo para que recalculen su fitness
			hijos[0].evaluar();
			hijos[1].evaluar();
			// Nos quedamos con los hijos siempre independientemente de si son peores
			// que sus padres 
			seleccionados[posiciones[i]] = (Individuo) hijos[0];
			seleccionados[posiciones[i + 1]] = (Individuo) hijos[1];
		}
	}

	private Individuo[] seleccionar() {
		return fSelec.seleccionar(tamPoblacion, poblacion);
	}

	// Espera un momento y repinta las graficas con los nuevos valores
	private void actualizaVentana() {
		// Esperamos unos segundos para retrasar la ejecución
		try {
		    Thread.sleep(10);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		
		// Le decimos a la ventana que se actualice
		window.actualizar(mejores, medias, mejoresAbs);
	}
	
	

	private Individuo[] calculaElite() {
		List<Individuo> ordenados = new ArrayList<Individuo>();
		// Guardamos a los individuos en una lista que posteriormente se ordenara
		for (int i = 0; i < tamPoblacion; i++)
			ordenados.add(poblacion[i]);
		
		// Ordenamos la lista de menor a mayor (los menores son los mejores)
		ordenados.sort(new Comparator<Individuo>() {
			public int compare(Individuo o1, Individuo o2) {
				if (o1.getFitness() > o2.getFitness())
					return 1;
				else if (o1.getFitness() == o2.getFitness())
					return 0;
				else 
					return -1;
			}
	    });
		
		// Cogemos tantos individuos como nos indique la variable numElite (en funcion del porcentaje
		// de elitismo seleccionado)
		Individuo[] elite = new Individuo[numElite];
		for (int i = 0; i < numElite; i++)
			elite[i] = ordenados.get(i).copia();
		return elite;
	}

	private void valoresGeneracion(int nGen) {
		double mejor = poblacion[0].getFitness();
		double total = mejor;
		Individuo mejorPob = poblacion[0].copia();
		for (int i = 1; i < tamPoblacion; i++) {
			double act = poblacion[i].getFitness();
			total += act;
			if (act < mejor) {
				mejor = act;
				mejorPob = poblacion[i].copia();
			}
		}
		if (mejor < mejorAbsoluto || nGen == 0) {
			mejorAbsoluto = mejor;
			this.mejor = mejorPob.copia();
		}
		double media = total / (double) tamPoblacion;
		medias[nGen] = media;
		mejores[nGen] = mejor;
		mejoresAbs[nGen] = mejorAbsoluto;
	}
	
	// Funcion que evalua todos los individuos
	private void evaluar() {
		for (Individuo i: poblacion)
			i.evaluar();
	}
	
	// Funcion que calcula el fitness adaptado
	private void calculoDesplazamiento() {
		// Ahora tenemos que evaluar todos los individuos mientras nos quedamos
		// con el fitness total conseguido
		double max = poblacion[0].getFitness();
		double min = max;
		for (int i = 1; i < poblacion.length; i++) {
			double ev = poblacion[i].getFitness();
			// Comprobamos si es el nuevo maximo
			if (max < ev)
				max = ev;
			if (min > ev)
				min = ev;
		}
		
		// Desplazamiento de la aptitud utilizando el valor maximo
		for (int i = 0; i < poblacion.length; i++)
			poblacion[i].setFitDesplazado(max * 1.05 - poblacion[i].getFitness());
	}
	

	
	public int getNumGen() {
		return numGen;
	}

	public void setNumGen(int numGen) {
		this.numGen = numGen;
	}
}