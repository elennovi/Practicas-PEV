package visualizacion;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.math.plot.*;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruce.CruceMonopunto;
import algoritmoGenetico.funciones.Funcion1;
import algoritmoGenetico.funciones.Funcion2;
import algoritmoGenetico.mutar.Mutacion;
import algoritmoGenetico.seleccion.Estocastico;
import algoritmoGenetico.seleccion.Ruleta;
import algoritmoGenetico.seleccion.Truncamiento;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private AlgoritmoGenetico AG;
	private Plot2DPanel plot;
	
	
	public Ventana(){
		super("Algoritmo genetico");
		this.AG = new AlgoritmoGenetico(100, 100, new Truncamiento(), new CruceMonopunto(), new Mutacion(0.05), 0.7, 3, new Funcion1(0.001), this);
		initGUI();
	}

	private void initGUI() {
		super.setSize(new Dimension(600, 600));
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		plot = new Plot2DPanel();
		plot.setBounds(0, 0, 500, 500);
		super.add(plot);
		AG.run();
	}
	
	public void actualizar(double[] mejores, double[] medias) {
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					plot.removeAllPlots();
					plot.removeLegend();
					double [] generaciones = new double[mejores.length];
					for(int i = 0; i < generaciones.length; i++)
						generaciones[i] = i+1;
					plot.addLegend("SOUTH");
					plot.addLinePlot("media", Color.green, generaciones, medias);
					plot.addLinePlot("mejor", Color.red, generaciones, mejores);
				}
		});
	}
	
}
