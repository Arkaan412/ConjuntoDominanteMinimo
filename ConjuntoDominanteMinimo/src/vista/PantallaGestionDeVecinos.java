package vista;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.VerticeConNombre;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PantallaGestionDeVecinos extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel panel = new JPanel();

	private JList<VerticeConNombre> listVerticesDisponibles;
	private JList<VerticeConNombre> listVecinosActuales;

	private DefaultListModel<VerticeConNombre> listModelVerticesDisponibles;
	private DefaultListModel<VerticeConNombre> listModelVecinosActuales;

	private Controlador controlador;

	private VerticeConNombre verticeEnGestion;

	public PantallaGestionDeVecinos(Controlador controlador, VerticeConNombre verticeEnGestion) {
		this.controlador = controlador;
		this.verticeEnGestion = verticeEnGestion;
		
		setTitle("Gestionar vecinos de " + this.verticeEnGestion);		

		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		setLocationRelativeTo(null); // Centrar en pantalla.

		setModal(true);

		listVerticesDisponibles = new JList<>();
		listVerticesDisponibles.setBounds(10, 36, 169, 314);

		listVecinosActuales = new JList<>();
		listVecinosActuales.setBounds(305, 36, 169, 314);

		listModelVecinosActuales = new DefaultListModel<>();
		listVecinosActuales.setModel(listModelVecinosActuales);

		listModelVerticesDisponibles = new DefaultListModel<>();
		listVerticesDisponibles.setModel(listModelVerticesDisponibles);

		cargarVerticesDisponiblesEnList();
		cargarVecinosActualesEnList();

		JLabel lblVerticesDisponibles = new JLabel("Vértices disponibles");
		lblVerticesDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerticesDisponibles.setBounds(32, 11, 119, 14);
		panel.add(lblVerticesDisponibles);

		JLabel lblVecinosActuales = new JLabel("Vecinos actuales");
		lblVecinosActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblVecinosActuales.setBounds(330, 11, 119, 14);
		panel.add(lblVecinosActuales);

		JButton btnAgregarVecinos = new JButton("Agregar ->");
		btnAgregarVecinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarVecinos();
			}
		});

		btnAgregarVecinos.setBounds(189, 123, 108, 28);
		panel.add(btnAgregarVecinos);

		JButton btnEliminarVecinos = new JButton("<- Eliminar");
		btnEliminarVecinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarVecinos();
			}
		});

		btnEliminarVecinos.setBounds(189, 196, 108, 28);
		panel.add(btnEliminarVecinos);

		JScrollPane scrollPaneListVerticesDisponibles = new JScrollPane();
		scrollPaneListVerticesDisponibles.setBounds(10, 36, 169, 314);
		scrollPaneListVerticesDisponibles.setViewportView(listVerticesDisponibles);
		panel.add(scrollPaneListVerticesDisponibles);

		JScrollPane scrollPaneListVecinosActuales = new JScrollPane();
		scrollPaneListVecinosActuales.setBounds(305, 36, 169, 314);
		scrollPaneListVecinosActuales.setViewportView(listVecinosActuales);
		panel.add(scrollPaneListVecinosActuales);

		JButton btnListo = new JButton("Listo");
		btnListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnListo.setBounds(189, 294, 108, 56);
		panel.add(btnListo);
	}

	private void cargarVerticesDisponiblesEnList() {
		List<VerticeConNombre> verticesDisponibles = controlador.obtenerVerticesDisponibles(verticeEnGestion);

		for (VerticeConNombre verticeActual : verticesDisponibles) {
			listModelVerticesDisponibles.addElement(verticeActual);
		}
	}

	private void cargarVecinosActualesEnList() {
		List<VerticeConNombre> vecinosActuales = controlador.obtenerVecinosActuales(verticeEnGestion);

		for (VerticeConNombre verticeActual : vecinosActuales) {
			listModelVecinosActuales.addElement(verticeActual);
		}
	}

	public void setVertice(VerticeConNombre verticeSeleccionado) {
		this.verticeEnGestion = verticeSeleccionado;
	}

	private void agregarVecinos() {
		List<VerticeConNombre> verticesSeleccionados = listVerticesDisponibles.getSelectedValuesList();

		if (verticesSeleccionados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Primero seleccioná un vértice.");
			return;
		}
		
		for (VerticeConNombre verticeActual : verticesSeleccionados) {
			controlador.agregarVecino(verticeEnGestion, verticeActual);

			listModelVerticesDisponibles.removeElement(verticeActual);

			listModelVecinosActuales.addElement(verticeActual);
		}
	}

	private void eliminarVecinos() {
		List<VerticeConNombre> verticesSeleccionados = listVecinosActuales.getSelectedValuesList();

		if (verticesSeleccionados.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Primero seleccioná un vértice.");
			return;
		}
		
		for (VerticeConNombre verticeActual : verticesSeleccionados) {
			controlador.eliminarVecino(verticeEnGestion, verticeActual);

			listModelVerticesDisponibles.addElement(verticeActual);

			listModelVecinosActuales.removeElement(verticeActual);
		}
	}

	public void mostrar() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}