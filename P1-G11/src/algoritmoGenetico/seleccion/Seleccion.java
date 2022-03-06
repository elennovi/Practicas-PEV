package algoritmoGenetico.seleccion;
import algoritmoGenetico.individuos.*;

public abstract class Seleccion {
	
	// El método abstracto que tienen que redefinir todas las 
	// clases que extienden de seleccion, para ejecutar la 
	// selección
	public abstract Individuo[] seleccionar(int seleccionados, double pSelec, Individuo[] poblacion, 
			boolean maxim);
	
	//IMPORTANTE:
	//La probabilidad de seleccion NO es el elitismo (suele estar entre 0,5-1)
	//¿Es constante o variable?¿Como se la pasamos al individuo?¿Que hacemos con nuestra vida?
}
