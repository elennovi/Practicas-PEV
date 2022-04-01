package visualizacion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import algoritmoGenetico.casos.Caso;

public class VentanaSolucion extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel fitLabel;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton boton;
	private JPanel mainPanel;
	
	private double[] TLAs;
	private double fitness;
	
	private int[] lenPistas;
	private int[][] vuelosPistas;
	
	private Caso c;
	
	public VentanaSolucion(double[] TLAs, int[] lenPistas, int[][] vuelosPistas, Caso c, double fit) {
		// Actualizamos las variables de la clase
		this.TLAs = TLAs;
		this.lenPistas = lenPistas;
		this.vuelosPistas = vuelosPistas;
		this.c = c;
		this.fitness = fit;
		
		// La configuracion de la ventana
		super.setTitle("Distribucion de los vuelos");
		super.setPreferredSize(new Dimension(550, 600));
		super.setMaximumSize(new Dimension(550, 600));
		super.setMinimumSize(new Dimension(550, 600));
		super.setSize(new Dimension(550, 600));
		super.setVisible(true);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Queremos que se cierre 
		
		// El panel principal sobre el que se añade la información de la pista
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setPreferredSize(new Dimension(550, 600));
		mainPanel.setMaximumSize(new Dimension(550, 600));
		mainPanel.setMinimumSize(new Dimension(550, 600));
		
		// El boton para cerrar la ventana con la solucion
	    boton = new JButton("ACEPTAR");
	    boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerramos(); // Cerramos la ventana
			}
		});
	    
	    // La tabla que representa la organizacion de los aviones en las pistas
	    tabla = new JTable();
	    tabla.setMinimumSize(new Dimension(550, 400));
	    tabla.setMaximumSize(new Dimension(550, 400));
	    tabla.setPreferredSize(new Dimension(550, 400));
	    modelo = new DefaultTableModel();
	    modelo.setColumnIdentifiers(generateColumnNames());
	    addData();
	    tabla.setModel(modelo);
	    
	    // El botón para cerrar la ventana
	    boton.setPreferredSize(new Dimension(100, 50));
	    boton.setMaximumSize(new Dimension(100, 50));
	    boton.setMinimumSize(new Dimension(100, 50));
	    
	    // La etiqueta para poder mostrar el fitness
	    fitLabel = new JLabel("Mejor fitness encontrado:" + fitness);
	    fitLabel.setMaximumSize(new Dimension(500, 50));
	    fitLabel.setMinimumSize(new Dimension(500, 50));
	    fitLabel.setPreferredSize(new Dimension(500, 50));
	    
	    // Ahora agregamos elementos al main panel
	    agregarElementos();
	    
	    // Añadimos el panel a la ventana principal
	    super.add(mainPanel);
	    super.revalidate();
	}
	
	// Elimina la ventana cuando se pulsa el boton
	public void cerramos() {
		super.dispose();
	}
	
	// Las pistas son las columnas de la tabla de organizacion
	private String[] generateColumnNames(){
		String[] names = new String[lenPistas.length];
		for (int i = 0; i < lenPistas.length; i++)
			names[i] = "Pista " + (i + 1);
		return names;
	}
	
	// Añade los principales elementos al main panel
	private void agregarElementos() {
        mainPanel.add(fitLabel);
        mainPanel.add(new JScrollPane(tabla));
        mainPanel.add(boton);
    }
	
	// Generamos la informacion de la tabla por filas
	private void addData() {
		for (int i = 0; i < max(lenPistas); i++) {
			String[] row = new String[lenPistas.length];
			for(int j = 0 ; j < lenPistas.length;j++) {
				String aux= " ";
				if(lenPistas[j] > i)
					aux = vuelosPistas[j][i] + ", " + c.getVueloAt(vuelosPistas[j][i] - 1).getIdVuelo() + ", " + TLAs[vuelosPistas[j][i] -1]; 
				row[j] = aux;
			}
			modelo.addRow(row);   
		}
	}
	
	//maximo de una lista
	private int max(int[] l) {
		int maxim = 0;
		for (int i = 0; i < l.length; i++)
			if (l[i] > maxim)
				maxim = l[i];
		return maxim;
		
	}
	
}
