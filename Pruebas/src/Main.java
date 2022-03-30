import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		int[] ind = new int[12];
		
		// Generamos una array con todos los numeros
		List<Integer> l = new ArrayList<Integer>(12);
		
		// Rellenamos con los numeros enteros en el intervalo 1-nVuelos
		for (int i = 1; i <= 12; i++)
			l.add(i);
		
		// Para la generacion aleatoria
		Random rand = new Random();
		
		// Ahora vamos seleccionando posiciones aleatorias hasta que se vacie la lista
		int cont = 0;
		while(!l.isEmpty()) {
			// Seleccionamos un numero aleatorio entre 0-tamaño del array
			int pos = rand.nextInt(l.size());
			ind[cont] = l.get(pos);
			l.remove(pos);
			cont++;
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
