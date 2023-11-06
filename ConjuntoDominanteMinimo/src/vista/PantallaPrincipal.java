package vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import controlador.Controlador;
import grafo.Vertice;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.VerticeConNombre;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PantallaPrincipal {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	JList<VerticeConNombre> listVertices;
	DefaultListModel<VerticeConNombre> listModelVertices;

	JList<VerticeConNombre> listCDM;
	DefaultListModel<VerticeConNombre> listModelCDM;

	Controlador controlador;

	public PantallaPrincipal(Controlador controlador) {
		this.controlador = controlador;

		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 532, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Generador de conjunto dominante mínimo");

		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(tabbedPane);

		frame.setLocationRelativeTo(null); // Centrar en pantalla.

		JPanel panelVertices = new JPanel();
		tabbedPane.addTab("Vértices", null, panelVertices, "Gestionar vértices");
		panelVertices.setLayout(null);

		JPanel panelCDM = new JPanel();
		tabbedPane.addTab("Resultado", null, panelCDM, "Conjunto Dominante Mínimo generado");
		panelCDM.setLayout(null);

		listCDM = new JList<VerticeConNombre>();
		listCDM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCDM.setBounds(10, 79, 489, 243);

		listModelCDM = new DefaultListModel<>();
		listCDM.setModel(listModelCDM);

		JLabel lblCDM = new JLabel("Conjunto Dominante Mínimo");
		lblCDM.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblCDM.setHorizontalAlignment(SwingConstants.CENTER);
		lblCDM.setBounds(130, 20, 228, 43);
		panelCDM.add(lblCDM);

		listVertices = new JList<>();
		listVertices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVertices.setBounds(10, 11, 491, 210);

		listModelVertices = new DefaultListModel<>();
		listVertices.setModel(listModelVertices);

		JButton btnAgregarVertice = new JButton("Agregar vértice");
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

		JButton btnGenerar = new JButton("Generar conjunto dominante mínimo");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarConjuntoDominanteMinimo();
			}
		});

		btnGenerar.setBounds(10, 325, 491, 71);
		panelVertices.add(btnGenerar);

		JScrollPane scrollPaneListVertices = new JScrollPane();
		scrollPaneListVertices.setBounds(10, 11, 491, 210);
		scrollPaneListVertices.setViewportView(listVertices);
		panelVertices.add(scrollPaneListVertices);

		JScrollPane scrollPaneListCDM = new JScrollPane();
		scrollPaneListCDM.setBounds(10, 79, 491, 210);
		scrollPaneListCDM.setViewportView(listCDM);
		panelCDM.add(scrollPaneListCDM);
	}

	private void generarConjuntoDominanteMinimo() {
		listModelCDM.clear();

		if (hayVertices()) {
			Set<VerticeConNombre> conjuntoDominanteMinimo = obtenerConjuntoDominanteMinimo();

			for (VerticeConNombre verticeConNombre : conjuntoDominanteMinimo) {
				listModelCDM.addElement(verticeConNombre);
			}

			tabbedPane.setSelectedIndex(1);
		} else {
			JOptionPane.showMessageDialog(frame, "Primero agregá, por lo menos, un vértice.");
		}
	}

	private boolean hayVertices() {
		boolean hayVertices = obtenerVertices().size() > 0;

		return hayVertices;
	}

	private Set<VerticeConNombre> obtenerConjuntoDominanteMinimo() {
		Set<VerticeConNombre> conjuntoDominanteMinimo = new HashSet<>();

		for (Vertice<String> verticeActual : controlador.obtenerConjuntoDominanteMinimo()) {
			conjuntoDominanteMinimo.add((VerticeConNombre) verticeActual);
		}

		return conjuntoDominanteMinimo;
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
		if (nombreVertice == null)
			return false;

		Pattern pattern = Pattern.compile("\\S"); // Existe por lo menos un non-whitespace character.
		Matcher matcher = pattern.matcher(nombreVertice);

		boolean verticeValido = !nombreVertice.isEmpty() && matcher.find();

		if (!verticeValido) {
			JOptionPane.showMessageDialog(frame, "Nombre inválido. El nombre no puede estar en blanco.");

			return false;
		}

		return true;
	}

	private boolean nombreVerticeExiste(String nombreVertice) {
		List<VerticeConNombre> vertices = obtenerVertices();

		for (VerticeConNombre vertice : vertices)
			if (vertice.getCarga().equals(nombreVertice)) {
				JOptionPane.showMessageDialog(frame, "Nombre inválido. No puede haber nombres repetidos.");

				return true;
			}

		return false;
	}

	private List<VerticeConNombre> obtenerVertices() {
		return controlador.obtenerVertices();
	}

	private VerticeConNombre crearVertice(String nombreVertice) {
		VerticeConNombre vertice = new VerticeConNombre(nombreVertice);

		controlador.agregarVertice(vertice);

		return vertice;
	}

	private void agregarVerticeALista(VerticeConNombre vertice) {
		listModelVertices.addElement(vertice);
	}

	private void modificarVecinos() {
		VerticeConNombre verticeSeleccionado = obtenerVerticeSeleccionado();

		if (verticeSeleccionado != null) {
			PantallaGestionDeVecinos pantallaGestionDeVecinos = new PantallaGestionDeVecinos(controlador,
					verticeSeleccionado);

			pantallaGestionDeVecinos.setVertice(verticeSeleccionado);

			pantallaGestionDeVecinos.mostrar();
		} else {
			JOptionPane.showMessageDialog(frame, "Primero seleccioná un vértice.");
		}

	}

	private VerticeConNombre obtenerVerticeSeleccionado() {
		VerticeConNombre verticeSeleccionado = listVertices.getSelectedValue();

		return verticeSeleccionado;
	}

	public void mostrar() {
		this.frame.setVisible(true);
	}
}
