package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.HashMap;
import java.util.HashSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.VerticeConNombre;

public class Pantalla {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JList<VerticeConNombre> listVertices;
	DefaultListModel<VerticeConNombre> listModelVertices;

	private HashMap<VerticeConNombre, HashSet<VerticeConNombre>> vertices;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla window = new Pantalla();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pantalla() {
		vertices = new HashMap<>();

		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(tabbedPane);
		frame.setLocationRelativeTo(null); // Centrar en pantalla.

		JPanel panelVertices = new JPanel();
		tabbedPane.addTab("Vértices", null, panelVertices, "Gestionar vértices");
		panelVertices.setLayout(null);

		listVertices = new JList<>();
		listVertices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVertices.setBounds(10, 11, 491, 210);
		panelVertices.add(listVertices);

		listModelVertices = new DefaultListModel<>();
		listVertices.setModel(listModelVertices);

		JButton btnAgregarVertice = new JButton("Agregar v\u00E9rtice");
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarVertice();
			}
		});

		btnAgregarVertice.setBounds(10, 232, 242, 82);
		panelVertices.add(btnAgregarVertice);

		JButton btnVecinos = new JButton("Agregar/eliminar vecinos");
		btnVecinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarVecinos();
			}
		});

		btnVecinos.setBounds(262, 232, 239, 82);
		panelVertices.add(btnVecinos);

		JButton btnGenerar = new JButton("Generar conjunto dominante m\u00EDnimo");
		btnGenerar.setBounds(10, 325, 491, 71);
		panelVertices.add(btnGenerar);

		JScrollBar scrollBarListVertices = new JScrollBar();
		scrollBarListVertices.setBounds(484, 11, 17, 210);
		
		panelVertices.add(scrollBarListVertices);

//		tabbedPane.addTab("Vértices", null, frame, null);
	}

	private void agregarVertice() {
		String nombreVertice = recibirNombreDeVertice();

		boolean nombreDeVerticeValido = validarNombreDeVertice(nombreVertice);

		if (nombreDeVerticeValido) {
			VerticeConNombre vertice = crearVertice(nombreVertice);
			agregarVerticeALista(vertice);
		}
	}

	private String recibirNombreDeVertice() {
		String nombreVertice = JOptionPane.showInputDialog(tabbedPane, "Ingresá el nombre del vértice:");

		return nombreVertice;
	}

	private boolean validarNombreDeVertice(String nombreVertice) {
		boolean nombreDeVerticeEsValido = nombreDeVerticeEsValido(nombreVertice) && !nombreVerticeExiste(nombreVertice);

		return nombreDeVerticeEsValido;
	}

	private boolean nombreDeVerticeEsValido(String nombreVertice) {
		Pattern pattern = Pattern.compile("\\S"); // Existe por lo menos un non-whitespace character.
		Matcher matcher = pattern.matcher(nombreVertice);

		boolean verticeValido = nombreVertice != null && !nombreVertice.isEmpty() && matcher.find();

		return verticeValido;
	}

	private boolean nombreVerticeExiste(String nombreVertice) {
		for (VerticeConNombre vertice : vertices.keySet())
			if (vertice.getCarga().equals(nombreVertice))
				return true;

		return false;
	}

	private VerticeConNombre crearVertice(String nombreVertice) {
		VerticeConNombre vertice = new VerticeConNombre(nombreVertice);
		HashSet<VerticeConNombre> listaVecinos = new HashSet<>();

		vertices.putIfAbsent(vertice, listaVecinos);

		return vertice;
	}

	private void agregarVerticeALista(VerticeConNombre vertice) {
		listModelVertices.addElement(vertice);
	}

	private void modificarVecinos() {
		VerticeConNombre verticeSeleccionado = listVertices.getSelectedValue();
	}

}
