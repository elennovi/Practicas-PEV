package algoritmoGenetico.seleccion;
import algoritmoGenetico.individuos.*;

public abstract class Seleccion {
	
	// El método abstracto que tienen que redefinir todas las 
	// clases que extienden de seleccion, para ejecutar la 
	// selección
	public abstract Individuo[] seleccionar(int seleccionados, double elitismo, Individuo[] poblacion, 
			boolean maxim);
}
