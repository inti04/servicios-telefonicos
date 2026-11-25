package cu.cujae.serviciostelefonicos.interfaz.clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cu.cujae.serviciostelefonicos.interfaz.Formulario;

/**
 * Listado de clientes con filtros por tipo, provincia y texto (CI o nombre).
 * Debajo se muestran los servicios del cliente seleccionado.
 *
 * <p>Corte 2 · Tarea 12 · Inti. Las filas son datos de ejemplo para el diseño;
 * en el Corte 3 la tabla se llena con lo que devuelva {@code Etecsa}.</p>
 */
public class VentanaListadoClientes extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMNAS_CLIENTES = { "Tipo", "Nombre", "CI / Representante", "Provincia",
			"Municipio", "Fijos", "Móviles", "Nauta" };
	private static final String[] COLUMNAS_SERVICIOS = { "Servicio", "Número o nick", "Monto del mes actual" };

	private final JTable tablaClientes = new JTable();
	private final JTable tablaServicios = new JTable();

	public VentanaListadoClientes(Window owner) {
		super(owner, "Listado de clientes", ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(Formulario.tamano(940, 520));
		setLocationRelativeTo(owner);

		JPanel contenido = new JPanel(new BorderLayout(0, 8));
		contenido.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contenido);

		contenido.add(crearPanelFiltros(), BorderLayout.NORTH);

		tablaClientes.setModel(new ModeloSoloLectura(datosEjemploClientes(), COLUMNAS_CLIENTES));
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaClientes.setAutoCreateRowSorter(true);
		tablaClientes.setRowSelectionInterval(0, 0);

		tablaServicios.setModel(new ModeloSoloLectura(datosEjemploServicios(), COLUMNAS_SERVICIOS));
		Formulario.ajustarFilas(tablaClientes);
		Formulario.ajustarFilas(tablaServicios);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(160);
		for (int i = 5; i < 8; i++) {
			tablaClientes.getColumnModel().getColumn(i).setPreferredWidth(50);
		}

		JScrollPane spServicios = new JScrollPane(tablaServicios);
		spServicios.setBorder(BorderFactory.createTitledBorder("Servicios del cliente seleccionado"));

		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(tablaClientes), spServicios);
		split.setResizeWeight(0.5);
		contenido.add(split, BorderLayout.CENTER);

		contenido.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel crearPanelFiltros() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
		p.add(new JLabel("Tipo:"));
		p.add(new JComboBox<String>(new String[] { "Todos", "Persona natural", "Persona jurídica",
				"Entidad no estatal" }));
		p.add(new JLabel("Provincia:"));
		p.add(new JComboBox<String>(new String[] { "Todas", "La Habana", "Artemisa", "Mayabeque", "..." }));
		p.add(new JLabel("Buscar (CI o nombre):"));
		p.add(new JTextField(16));
		p.add(new JButton("Buscar"));
		return p;
	}

	private JPanel crearPanelBotones() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnNuevo = new JButton("Nuevo...");
		JButton btnModificar = new JButton("Modificar...");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnCerrar = new JButton("Cerrar");
		p.add(btnNuevo);
		p.add(btnModificar);
		p.add(btnEliminar);
		p.add(btnCerrar);

		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogoCliente(VentanaListadoClientes.this).setVisible(true);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(VentanaListadoClientes.this,
						"¿Eliminar el cliente seleccionado y todos sus servicios?", "Confirmar",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		});
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		return p;
	}

	private static Object[][] datosEjemploClientes() {
		return new Object[][] {
				{ "Persona natural", "Ana María Pérez Gómez", "95031512345", "La Habana", "Plaza de la Revolución", 1, 2, 1 },
				{ "Persona jurídica", "Empresa de Correos de Cuba", "Luis Díaz Rey", "La Habana", "Cerro", 4, 6, 0 },
				{ "Entidad no estatal", "Mipyme SoftCuba", "Carmen Ruiz Soto", "Artemisa", "Bauta", 2, 3, 2 },
				{ "Persona natural", "Jorge Luis Martínez", "88112009876", "La Habana", "Playa", 1, 1, 0 } };
	}

	private static Object[][] datosEjemploServicios() {
		return new Object[][] { { "Teléfono fijo", "7 830 1234", "$ 42.50" },
				{ "Teléfono móvil", "5 312 3456", "$ 118.00" }, { "Teléfono móvil", "5 498 7654", "$ 36.25" },
				{ "Cuenta Nauta", "ana.perez", "$ 75.40" } };
	}

	/** Modelo de tabla que no deja editar las celdas. */
	private static class ModeloSoloLectura extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		ModeloSoloLectura(Object[][] datos, String[] columnas) {
			super(datos, columnas);
		}

		@Override
		public boolean isCellEditable(int fila, int columna) {
			return false;
		}
	}
}
