import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		int[] ind = new int[8];
		
		for (int i = 0; i < 8; i++) 
			ind[i] = i + 1;
		
		System.out.println(toString(ind));
		
		// Generador de numeros aleatorios
		Random rand = new Random();
		
		// Seleccionamos una posición aleatoria
		int pos1 = rand.nextInt(ind.length);
		
		System.out.println("Hemos seleccionado la posicion: " + pos1);
		
		// Si elegimos la primera posicion no hay desplazamiento de ningun tipo
		if (pos1 != 0) {
			// Guardamos el valor en esa posición
			int valIns = ind[pos1];
			
			// Seleccionamos otra posición aleatoria (previa a la seleccionada anteriormente)
			int pos2 = rand.nextInt(pos1);
			
			System.out.println("Insertamos en la posicion: " + pos2);
			
			// Desplazamos los elementos una posición a la derecha entre pos2 y pos1
			int ant = ind[pos2];
			for (int p = pos2 + 1; p <= pos1; p++) {
				int aux = ind[p];
				ind[p] = ant;
				ant = aux;
			}
			
			// Insertamos el valor en pos2
			ind[pos2] = valIns;
		}
		
		System.out.println(toString(ind));
	}
	
	public static String toString(int[] arr) {
		String s = "";
		for (int e: arr)
			s += e + " ";
		return s;
	}
}
