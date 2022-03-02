package main;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruce.CruceMonopunto;
import algoritmoGenetico.funciones.Funcion1;
import algoritmoGenetico.mutar.Mutacion;
import algoritmoGenetico.seleccion.Ruleta;
import algoritmoGenetico.seleccion.Torneo;

public class Main {
	
	public static void main(String args[]) {
		
		AlgoritmoGenetico gen = new AlgoritmoGenetico(4, 2, new Ruleta(), new CruceMonopunto(), new Mutacion(0.5), 0.7, 0, new Funcion1(0.001));
	}
}
