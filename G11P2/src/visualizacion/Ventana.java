package visualizacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.math.plot.*;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruce.*;
import algoritmoGenetico.casos.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private AlgoritmoGenetico AG;
	private Plot2DPanel plot;
	private JButton run;
	private JPanel mainPanel;
	private JPanel seleccionPanel;
	
	private JTextArea textTam, textNGen, textCruce, textMutacion, textElitismo, textSemilla;
	private JComboBox<String> comboSelec, comboCruce, comboCaso, comboMut;
	
	private static final String TAM_POBLACION = "100", NUM_GENERACIONES = "100", SEMILLA = "0";
	private static final String PORCENTAJE_CRUCE = "60", PORCENTAJE_MUTACION = "5", PORCENTAJE_ELITISMO = "0";
	private static final String[] metodSel =  {"Ruleta", "Torneo deterministico", "Torneo probabilistico", "Truncamiento", "Estocastico", "Restos", "Ranking"};
	private static final String[] metodCruceInt =  {"CX", "PMX", "OX basico", "OX posiciones prioritarias", "OX orden prioritario", "CO"};
	private static final String[] metodMut = {"Inversion", "Intercambio", "Heuristica", "Insercion"};
	private static final String[] cs =  {"Caso 1", "Caso 2", "Caso aleatorio"};
	
	
	public Ventana(){
		super("Algoritmo genetico");
		initGUI();
	}

	private void initGUI() {
		super.setSize(new Dimension(1100, 700));
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Asignar el BorderLayout al panel principal
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//mainPanel.add(Box.createHorizontalStrut(50));
		
		// Ahora creamos el panel con todos las variables que el usuario debe seleccionar
		seleccionPanel = new JPanel();
		seleccionPanel.setLayout(new BoxLayout(seleccionPanel, BoxLayout.Y_AXIS));
		seleccionPanel.setPreferredSize(new Dimension(290, 650));
		seleccionPanel.setSize(new Dimension(290, 650));
		seleccionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), 
				"Seleccion de parametros", TitledBorder.LEFT, TitledBorder.TOP));
		
		// A�adimos los JTextArea con los valores predeterminados
		
		// Tama�o de la poblacion
		JLabel labelTam = new JLabel("Tama�o de la poblacion: ", SwingConstants.LEFT);
		labelTam.setPreferredSize(new Dimension(250, 25));
		labelTam.setMaximumSize(new Dimension(250, 25));
		labelTam.setMinimumSize(new Dimension(250, 25));
		labelTam.setAlignmentX( Component.LEFT_ALIGNMENT);
		textTam = new JTextArea(TAM_POBLACION);
		textTam.setPreferredSize(new Dimension(250, 25));
		textTam.setMaximumSize(new Dimension(250, 25));
		textTam.setMinimumSize(new Dimension(250, 25));
		textTam.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelTam);
		seleccionPanel.add(textTam);
		
		// Numero de generaciones
		JLabel labelNGen = new JLabel("Numeros de generaciones: ", SwingConstants.LEFT);
		labelNGen.setPreferredSize(new Dimension(250, 25));
		labelNGen.setMaximumSize(new Dimension(250, 25));
		labelNGen.setMinimumSize(new Dimension(250, 25));
		labelNGen.setAlignmentX( Component.LEFT_ALIGNMENT);
		textNGen = new JTextArea(NUM_GENERACIONES);
		textNGen.setPreferredSize(new Dimension(250, 25));
		textNGen.setMaximumSize(new Dimension(250, 25));
		textNGen.setMinimumSize(new Dimension(250, 25));
		textNGen.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelNGen);
		seleccionPanel.add(textNGen);
		
		// Porcentaje de cruce
		JLabel labelpcruce = new JLabel("Porcentaje de cruce (sobre 100): ", SwingConstants.LEFT);
		labelpcruce.setPreferredSize(new Dimension(250, 25));
		labelpcruce.setMaximumSize(new Dimension(250, 25));
		labelpcruce.setMinimumSize(new Dimension(250, 25));
		labelpcruce.setAlignmentX( Component.LEFT_ALIGNMENT);
		textCruce = new JTextArea(PORCENTAJE_CRUCE);
		textCruce.setPreferredSize(new Dimension(250, 25));
		textCruce.setMaximumSize(new Dimension(250, 25));
		textCruce.setMinimumSize(new Dimension(250, 25));
		textCruce.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelpcruce);
		seleccionPanel.add(textCruce);
		
		// Porcentaje de mutacion
		JLabel labelpmut = new JLabel("Porcentaje de mutacion (sobre 100): ", SwingConstants.LEFT);
		labelpmut.setPreferredSize(new Dimension(250, 25));
		labelpmut.setMaximumSize(new Dimension(250, 25));
		labelpmut.setMinimumSize(new Dimension(250, 25));
		labelpmut.setAlignmentX( Component.LEFT_ALIGNMENT);
		textMutacion = new JTextArea(PORCENTAJE_MUTACION);
		textMutacion.setPreferredSize(new Dimension(250, 25));
		textMutacion.setMaximumSize(new Dimension(250, 25));
		textMutacion.setMinimumSize(new Dimension(250, 25));
		textMutacion.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelpmut);
		seleccionPanel.add(textMutacion);
		
		// Porcentaje de elitismo
		JLabel labelelit = new JLabel("Porcentaje de elitismo (sobre 100): ", SwingConstants.LEFT);
		labelelit.setPreferredSize(new Dimension(250, 25));
		labelelit.setMaximumSize(new Dimension(250, 25));
		labelelit.setMinimumSize(new Dimension(250, 25));
		labelelit.setAlignmentX( Component.LEFT_ALIGNMENT);
		textElitismo = new JTextArea(PORCENTAJE_ELITISMO);
		textElitismo.setPreferredSize(new Dimension(250, 25));
		textElitismo.setMaximumSize(new Dimension(250, 25));
		textElitismo.setMinimumSize(new Dimension(250, 25));
		textElitismo.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelelit);
		seleccionPanel.add(textElitismo);
		
		// Combobox para el metodo de seleccion
		JLabel labelfsel = new JLabel("Metodo de seleccion: ", SwingConstants.LEFT);
		labelfsel.setPreferredSize(new Dimension(250, 25));
		labelfsel.setMaximumSize(new Dimension(250, 25));
		labelfsel.setMinimumSize(new Dimension(250, 25));
		labelfsel.setAlignmentX( Component.LEFT_ALIGNMENT);
		comboSelec = new JComboBox<String>(metodSel);
		comboSelec.setPreferredSize(new Dimension(250, 25));
		comboSelec.setMaximumSize(new Dimension(250, 25));
		comboSelec.setMinimumSize(new Dimension(250, 25));
		comboSelec.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelfsel);
		seleccionPanel.add(comboSelec);
		
		// Combobox para el metodo de cruce
		JLabel labelfcruce = new JLabel("Metodo de cruce: ", SwingConstants.LEFT);
		labelfcruce.setPreferredSize(new Dimension(250, 25));
		labelfcruce.setMaximumSize(new Dimension(250, 25));
		labelfcruce.setMinimumSize(new Dimension(250, 25));
		labelfcruce.setAlignmentX( Component.LEFT_ALIGNMENT);
		comboCruce = new JComboBox<String>(metodCruceInt);
		comboCruce.setPreferredSize(new Dimension(250, 25));
		comboCruce.setMaximumSize(new Dimension(250, 25));
		comboCruce.setMinimumSize(new Dimension(250, 25));
		comboCruce.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelfcruce);
		seleccionPanel.add(comboCruce);
		
		// Combobox para la funcion:
		JLabel labelc = new JLabel("Funcion que se pretende optimizar: ", SwingConstants.LEFT);
		labelc.setPreferredSize(new Dimension(250, 25));
		labelc.setMaximumSize(new Dimension(250, 25));
		labelc.setMinimumSize(new Dimension(250, 25));
		labelc.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboCaso = new JComboBox<String>(cs);
		comboCaso.setPreferredSize(new Dimension(250, 25));
		comboCaso.setMaximumSize(new Dimension(250, 25));
		comboCaso.setMinimumSize(new Dimension(250, 25));
		comboCaso.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboCaso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si seleccionamos el caso 3 hay que habilitar el texto para que se pueda cambiar la semilla
				if (comboCaso.getSelectedIndex() == 2)
					textSemilla.setEditable(true);
				else
					textSemilla.setEditable(false);
			}
		});
		
		seleccionPanel.add(labelc);
		seleccionPanel.add(comboCaso);
		
		// Un area de texto para la seleccion de la semilla
		JLabel labelSem = new JLabel("Semilla para el Caso aleatorio: ", SwingConstants.LEFT);
		labelSem.setPreferredSize(new Dimension(250, 25));
		labelSem.setMaximumSize(new Dimension(250, 25));
		labelSem.setMinimumSize(new Dimension(250, 25));
		textSemilla = new JTextArea(SEMILLA);
		textSemilla.setPreferredSize(new Dimension(250, 25));
		textSemilla.setMaximumSize(new Dimension(250, 25));
		textSemilla.setMinimumSize(new Dimension(250, 25));
		textSemilla.setAlignmentX(Component.LEFT_ALIGNMENT);
		textSemilla.setEditable(false); // Al principio no se puede editar
		
		seleccionPanel.add(labelSem);
		seleccionPanel.add(textSemilla);
		
		// Combobox para la mutacion:
		JLabel labelMut= new JLabel("Metodo de mutacion: ", SwingConstants.LEFT);
		labelMut.setPreferredSize(new Dimension(250, 25));
		labelMut.setMaximumSize(new Dimension(250, 25));
		labelMut.setMinimumSize(new Dimension(250, 25));
		labelMut.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboMut = new JComboBox<String>(metodMut);
		comboMut.setPreferredSize(new Dimension(250, 25));
		comboMut.setMaximumSize(new Dimension(250, 25));
		comboMut.setMinimumSize(new Dimension(250, 25));
		comboMut.setAlignmentX( Component.LEFT_ALIGNMENT);
				
		
		seleccionPanel.add(labelMut);
		seleccionPanel.add(comboMut);
		
		// Creamos el boton de run que ejecuta el algoritmo genetico con los parametros especificados
		run = new JButton("Run");
		run.setBackground(Color.green);
		run.setPreferredSize(new Dimension(250, 50));
		run.setMaximumSize(new Dimension(250, 50));
		run.setMinimumSize(new Dimension(250, 50));
		run.setAlignmentX(Component.LEFT_ALIGNMENT);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disableButtons();
				ejecutaAlgoritmo();
				enableButtons();
				
			}
		});
		
		seleccionPanel.add(run);
		
		// A�adimos el panel de seleccion a la izquierda
		mainPanel.add(seleccionPanel);
		
		// La grafica que muestra los valores de fitness
		plot = new Plot2DPanel();
		plot.setPreferredSize(new Dimension(690, 650));
		plot.setSize(new Dimension(690, 650));
		plot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), 
				"Graficas", TitledBorder.LEFT, TitledBorder.TOP));
		
		// A�adimo la grafica a la derecha
		mainPanel.add(plot);
		
		// A�adimos el panel principal a la ventana
		super.add(mainPanel);
		super.revalidate();
	}
	
	private void ejecutaAlgoritmo() {
		// Obtenemos los valores de las text areas
		
		// Tam poblacion
		int tamPoblacion = Integer.parseInt(textTam.getText());
		
		// Num Generaciones
		int numGen = Integer.parseInt(textNGen.getText());
		
		// Porcentaje de cruce
		double pCruce = Double.parseDouble(textCruce.getText()) / 100.0;
		
		// Porcentaje de mutacion
		double pMut = Double.parseDouble(textMutacion.getText()) / 100.0;
		
		// Porcentaje de elitismo
		double pElit = Double.parseDouble(textElitismo.getText()) / 100.0;
		
		// Funcion de seleccion:
		int posSelec = comboSelec.getSelectedIndex();
		Seleccion fSelec;
		if (posSelec == 0)
			fSelec = new Ruleta();
		else if (posSelec == 1)
			fSelec = new TorneoDeterministico();
		else if (posSelec == 2)
			fSelec = new TorneoProbabilistico();
		else if (posSelec == 3)
			fSelec = new Truncamiento();
		else if (posSelec == 4)
			fSelec = new Estocastico();
		else if (posSelec == 5)
			fSelec = new Restos();
		else
			fSelec = new Ranking();
		
		// Funcion de seleccion:
		int sCruce = comboCruce.getSelectedIndex();
		Cruce fCruce;
		if (sCruce == 0)
			fCruce = new CiclosCX();
		else if (sCruce == 1)
			fCruce = new PMX();
		else if (sCruce == 2)
			fCruce = new OXBasico();
		else if (sCruce == 3)
			fCruce = new OXPosPrio();
		else if (sCruce == 4)
			fCruce = new OXOrdPrio();
		else
			fCruce = new CO();
		
		
		// Funcion que se pretende optimizar
		int posC = comboCaso.getSelectedIndex();
		Caso c;
		if (posC == 0)
			c = new Caso1();
		else if (posC == 1)
			c = new Caso2();
		else {
			// Como es el caso aleatorio hay que sacar el valor de la semilla
			int sem = Integer.parseInt(textSemilla.getText());
			c = new Caso3(sem);
		}
		
		// M�todo de mutacion
		int posMut = comboMut.getSelectedIndex();
		Mutacion fMut;
		if (posMut == 0)
			fMut = new Inversion();
		else if (posMut == 1)
			fMut = new Intercambio();
		else if (posMut == 2)
			fMut = new Heuristica();
		else 
			fMut = new Insercion();
		
		AG = new AlgoritmoGenetico(tamPoblacion, numGen, fSelec, fCruce, fMut, pCruce, pMut, pElit, c, this);
		AG.run();
	}
	
	private void disableButtons() {
		run.setEnabled(false);
	}
	
	private void enableButtons() {
		run.setEnabled(true);
	}
	
	public void actualizar(double[] mejores, double[] medias, double[] mejoresAbs) {
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
					plot.addLinePlot("mejor absoluto", Color.blue , generaciones, mejoresAbs);
				}
		});
	}
}
