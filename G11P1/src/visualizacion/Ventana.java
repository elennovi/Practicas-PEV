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
import algoritmoGenetico.funciones.*;
import algoritmoGenetico.mutar.*;
import algoritmoGenetico.seleccion.*;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private AlgoritmoGenetico AG;
	private Plot2DPanel plot;
	private JButton run;
	private JLabel mut;
	private JPanel mainPanel;
	private JPanel seleccionPanel;
	
	private JTextArea textTam, textNGen, textCruce, textMutacion, textElitismo, textPrecision, textNF4;
	private JComboBox<String> comboSelec, comboCruce, comboFun;
	
	private static final String TAM_POBLACION = "100", NUM_GENERACIONES = "100", NF4 = "4";
	private static final String PORCENTAJE_CRUCE = "60", PORCENTAJE_MUTACION = "5", PRECISION = "0.001", PORCENTAJE_ELITISMO = "0";
	private static final String[] metodSel =  {"Ruleta", "Torneo deterministico", "Torneo probabilistico", "Truncamiento", "Estocastico", "Restos"};
	private static final String[] metodCruceBool =  {"Monopunto", "Discreto uniforme"};
	private static final String[] metodCruceDouble = {"Monopunto", "Discreto uniforme", "Aritmetico", "BLX-alpha"};
	private static final String[] fs =  {"Funcion 1", "Funcion 2", "Funcion 3", "Funcion 4 booleana", "Funcion 4 real"};
	
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
		
		// Añadimos los JTextArea con los valores predeterminados
		
		// Tamaño de la poblacion
		JLabel labelTam = new JLabel("Tamaño de la poblacion: ", SwingConstants.LEFT);
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
		
		// Precision
		JLabel labelprec = new JLabel("Precision numerica: ", SwingConstants.LEFT);
		labelprec.setPreferredSize(new Dimension(250, 25));
		labelprec.setMaximumSize(new Dimension(250, 25));
		labelprec.setMinimumSize(new Dimension(250, 25));
		labelprec.setAlignmentX( Component.LEFT_ALIGNMENT);
		textPrecision = new JTextArea(PRECISION);
		textPrecision.setPreferredSize(new Dimension(250, 25));
		textPrecision.setMaximumSize(new Dimension(250, 25));
		textPrecision.setMinimumSize(new Dimension(250, 25));
		textPrecision.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelprec);
		seleccionPanel.add(textPrecision);
		
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
		comboCruce = new JComboBox<String>(metodCruceBool);
		comboCruce.setPreferredSize(new Dimension(250, 25));
		comboCruce.setMaximumSize(new Dimension(250, 25));
		comboCruce.setMinimumSize(new Dimension(250, 25));
		comboCruce.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelfcruce);
		seleccionPanel.add(comboCruce);
		
		// Combobox para la funcion:
		JLabel labelf= new JLabel("Funcion que se pretende optimizar: ", SwingConstants.LEFT);
		labelf.setPreferredSize(new Dimension(250, 25));
		labelf.setMaximumSize(new Dimension(250, 25));
		labelf.setMinimumSize(new Dimension(250, 25));
		labelf.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboFun = new JComboBox<String>(fs);
		comboFun.setPreferredSize(new Dimension(250, 25));
		comboFun.setMaximumSize(new Dimension(250, 25));
		comboFun.setMinimumSize(new Dimension(250, 25));
		comboFun.setAlignmentX( Component.LEFT_ALIGNMENT);
		
		ActionListener funcAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (comboFun.getSelectedIndex() == 4) {
            		comboCruce.removeAllItems();
            		for (int i = 0 ; i < metodCruceDouble.length; i++)
            			comboCruce.addItem(metodCruceDouble[i]);
            		mut.setText("Mutacion uniforme");
            		textNF4.setEditable(true);
            	}
            	else {
            		comboCruce.removeAllItems();
            		for (int i = 0 ; i < metodCruceBool.length; i++)
            			comboCruce.addItem(metodCruceBool[i]);
            		mut.setText("Mutacion basica");
            		textNF4.setEditable(false);
            	}
            	
            	if (comboFun.getSelectedIndex() == 3)
            		textNF4.setEditable(true);
            }
		};
		
		comboFun.addActionListener(funcAL);

		
		seleccionPanel.add(labelf);
		seleccionPanel.add(comboFun);
		
		// El texto para decidir el numero de variables de F4
		JLabel labelnf4 = new JLabel("Numero de variables para la funcion 4: ", SwingConstants.LEFT);
		labelnf4.setPreferredSize(new Dimension(250, 25));
		labelnf4.setMaximumSize(new Dimension(250, 25));
		labelnf4.setMinimumSize(new Dimension(250, 25));
		labelnf4.setAlignmentX(Component.LEFT_ALIGNMENT);
		textNF4 = new JTextArea(NF4);
		textNF4.setPreferredSize(new Dimension(250, 25));
		textNF4.setMaximumSize(new Dimension(250, 25));
		textNF4.setMinimumSize(new Dimension(250, 25));
		textNF4.setAlignmentX( Component.LEFT_ALIGNMENT);
		textNF4.setEditable(false);
		
		seleccionPanel.add(labelnf4);
		seleccionPanel.add(textNF4);
		
		// Añadimos una etiqueta
		JLabel labelMut = new JLabel("La mutacion predeterminada es:", SwingConstants.LEFT);
		labelMut.setPreferredSize(new Dimension(250, 25));
		labelMut.setMaximumSize(new Dimension(250, 25));
		labelMut.setMinimumSize(new Dimension(250, 25));
		labelMut.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		mut = new JLabel("Mutacion basica", SwingConstants.LEFT);
		mut.setPreferredSize(new Dimension(250, 25));
		mut.setMaximumSize(new Dimension(250, 25));
		mut.setMinimumSize(new Dimension(250, 25));
		mut.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		seleccionPanel.add(labelMut);
		seleccionPanel.add(mut);
		
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
		
		// Añadimos el panel de seleccion a la izquierda
		mainPanel.add(seleccionPanel);
		
		// La grafica que muestra los valores de fitness
		plot = new Plot2DPanel();
		plot.setPreferredSize(new Dimension(690, 650));
		plot.setSize(new Dimension(690, 650));
		plot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), 
				"Graficas", TitledBorder.LEFT, TitledBorder.TOP));
		
		// Añadimo la grafica a la derecha
		mainPanel.add(plot);
		
		// Añadimos el panel principal a la ventana
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
		
		// Precision
		double prec = Double.parseDouble(textPrecision.getText()) / 100.0;
		
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
		else
			fSelec = new Restos();
		
		// Funcion de seleccion:
		String sCruce = (String) comboCruce.getSelectedItem();
		Cruce fCruce;
		if (sCruce == "Monopunto")
			fCruce = new Monopunto();
		else if (sCruce == "Discreto uniforme")
			fCruce = new DiscretoUniforme();
		else if (sCruce == "Aritmetico")
			fCruce = new Aritmetico();
		else 
			fCruce = new BLXAlpha();
		
		
		boolean indBool = true;
		// Funcion que se pretende optimizar
		int posF = comboFun.getSelectedIndex();
		Funcion f;
		Mutacion mutar;
		if (posF == 0) {
			f = new Funcion1(prec);
			mutar = new Basica(pMut);
		}
		else if (posF == 1) {
			f = new Funcion2(prec);
			mutar = new Basica(pMut);
		}
		else if (posF == 2) {
			f = new Funcion3(prec);
			mutar = new Basica(pMut);
		}
		else if (posF == 3) {
			// El numero de variables
			int n = Integer.parseInt(textNF4.getText());
			f = new Funcion4Bool(prec, n);
			mutar = new Basica(pMut);
		}
		else {
			int n = Integer.parseInt(textNF4.getText());
			f = new Funcion4Real(prec, n);
			mutar = new Uniforme(pMut);
			indBool = false;
		}
		
		AG = new AlgoritmoGenetico(tamPoblacion, numGen, fSelec, fCruce, mutar, pCruce, pElit, f, this, indBool);
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
