package visualizacion;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.*;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruce.CruceMonopunto;
import algoritmoGenetico.funciones.Funcion1;
import algoritmoGenetico.mutar.Mutacion;
import algoritmoGenetico.seleccion.Ruleta;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	private Plot2DPanel plot;
	private double[] generaciones;
	private double[] mejor;
	private double[] media;
	private int nGen;
	
	
	public Ventana(){
		super("Algoritmo genetico");
		this.nGen = 100;
		initGUI();
		new AlgoritmoGenetico(100, 100, new Ruleta(), new CruceMonopunto(), new Mutacion(0.5), 0.7, 2, new Funcion1(0.001), this);
	}

	private void initGUI() {
		super.setSize(new Dimension(600, 600));
		super.setVisible(true);
		
		plot = new Plot2DPanel();
		generaciones = new double[nGen];
		mejor = new double[nGen];
		media = new double[nGen];
		
		// Falta el array que calcula el mejor absoluto
		for (int i = 0; i < nGen; i++) {
			generaciones[i] = (i + 1);
			mejor[i] = 0;
			media[i] = 0;
		}

		super.add(plot);
	}
	
	public void actualizar(int n, double bst, double avg) {
		plot.removeAllPlots();
		mejor[n] = bst;
		media[n] = avg;
		// plot.addLegend("SOUTH");
 		plot.addLinePlot("mejor", Color.cyan, generaciones, mejor);
		plot.addLinePlot("media", Color.magenta, generaciones, media);
	}
	
}
