package cu.cujae.serviciostelefonicos.interfaz.clientes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cu.cujae.serviciostelefonicos.interfaz.Formulario;

/**
 * Formulario para registrar o modificar un cliente. El tipo de cliente elegido
 * arriba cambia la parte central: persona natural (nombres, apellidos y CI),
 * persona jurídica (entidad, organismo y representante) o entidad no estatal
 * (nombre y representante). Dirección, provincia y municipio son comunes.
 *
 * <p>Corte 2 · Tarea 12 · Inti. En el Corte 3 el botón Aceptar llamará a
 * {@code Etecsa.agregarCliente(...)} y mostrará las excepciones de validación.</p>
 */
public class DialogoCliente extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String NATURAL = "Persona natural";
	private static final String JURIDICA = "Persona jurídica";
	private static final String NO_ESTATAL = "Entidad no estatal";

	// TODO Corte 3: tomar las listas cerradas de utiles.Provincias (Carli).
	private static final String[] PROVINCIAS = { "Pinar del Río", "Artemisa", "La Habana", "Mayabeque",
			"Matanzas", "Cienfuegos", "Villa Clara", "Sancti Spíritus", "Ciego de Ávila", "Camagüey", "Las Tunas",
			"Holguín", "Granma", "Santiago de Cuba", "Guantánamo", "Isla de la Juventud" };
	private static final String[] MUNICIPIOS_LA_HABANA = { "Playa", "Plaza de la Revolución", "Centro Habana",
			"La Habana Vieja", "Regla", "La Habana del Este", "Guanabacoa", "San Miguel del Padrón", "Diez de Octubre",
			"Cerro", "Marianao", "La Lisa", "Boyeros", "Arroyo Naranjo", "Cotorro" };

	private final JComboBox<String> cbTipo = new JComboBox<String>(new String[] { NATURAL, JURIDICA, NO_ESTATAL });
	private final JPanel panelTipos = new JPanel(new CardLayout());

	// Persona natural
	private final JTextField txtNombres = new JTextField(20);
	private final JTextField txtApellidos = new JTextField(20);
	private final JTextField txtCi = new JTextField(11);

	// Persona jurídica
	private final JTextField txtEntidad = new JTextField(20);
	private final JTextField txtOrganismo = new JTextField(20);
	private final PanelRepresentante repJuridica = new PanelRepresentante();

	// Entidad no estatal
	private final JTextField txtNombreNoEstatal = new JTextField(20);
	private final PanelRepresentante repNoEstatal = new PanelRepresentante();

	// Datos comunes
	private final JTextField txtDireccion = new JTextField(20);
	private final JComboBox<String> cbProvincia = new JComboBox<String>(PROVINCIAS);
	private final JComboBox<String> cbMunicipio = new JComboBox<String>();

	public DialogoCliente(Window owner) {
		super(owner, "Nuevo cliente", ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel contenido = new JPanel(new BorderLayout(0, 8));
		contenido.setBorder(new EmptyBorder(12, 12, 12, 12));
		setContentPane(contenido);

		JPanel panelTipo = new JPanel(new GridBagLayout());
		Formulario.agregarFila(panelTipo, 0, "Tipo de cliente:", cbTipo);
		contenido.add(panelTipo, BorderLayout.NORTH);

		panelTipos.add(crearPanelNatural(), NATURAL);
		panelTipos.add(crearPanelJuridica(), JURIDICA);
		panelTipos.add(crearPanelNoEstatal(), NO_ESTATAL);

		JPanel centro = new JPanel(new BorderLayout(0, 8));
		centro.add(panelTipos, BorderLayout.CENTER);
		centro.add(crearPanelDireccion(), BorderLayout.SOUTH);
		contenido.add(centro, BorderLayout.CENTER);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");
		botones.add(btnAceptar);
		botones.add(btnCancelar);
		contenido.add(botones, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(btnAceptar);

		cbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				((CardLayout) panelTipos.getLayout()).show(panelTipos, (String) cbTipo.getSelectedItem());
			}
		});
		cbProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				actualizarMunicipios();
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(DialogoCliente.this,
						"El registro se conectará con la capa lógica en el Corte 3.", "Diseño de pantalla",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cbProvincia.setSelectedItem("La Habana");
		actualizarMunicipios();
		pack();
		setResizable(false);
		setLocationRelativeTo(owner);
	}

	/** Muestra directamente el formulario de un tipo de cliente (se usa al modificar). */
	public void mostrarTipo(int indice) {
		cbTipo.setSelectedIndex(indice);
	}

	private JPanel crearPanelNatural() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder("Datos de la persona"));
		Formulario.agregarFila(p, 0, "Nombre(s):", txtNombres);
		Formulario.agregarFila(p, 1, "Apellidos:", txtApellidos);
		Formulario.agregarFila(p, 2, "Carné de identidad:", txtCi);
		Formulario.agregarRelleno(p, 3);
		txtCi.setToolTipText("11 dígitos; los 6 primeros son la fecha de nacimiento (AAMMDD)");
		return p;
	}

	private JPanel crearPanelJuridica() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder("Datos de la entidad"));
		Formulario.agregarFila(p, 0, "Nombre de la entidad:", txtEntidad);
		Formulario.agregarFila(p, 1, "Organismo:", txtOrganismo);
		Formulario.agregarFilaCompleta(p, 2, repJuridica);
		return p;
	}

	private JPanel crearPanelNoEstatal() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder("Datos de la entidad no estatal"));
		Formulario.agregarFila(p, 0, "Nombre:", txtNombreNoEstatal);
		Formulario.agregarFilaCompleta(p, 1, repNoEstatal);
		Formulario.agregarRelleno(p, 2);
		return p;
	}

	private JPanel crearPanelDireccion() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder("Dirección"));
		Formulario.agregarFila(p, 0, "Dirección:", txtDireccion);
		Formulario.agregarFila(p, 1, "Provincia:", cbProvincia);
		Formulario.agregarFila(p, 2, "Municipio:", cbMunicipio);
		return p;
	}

	private void actualizarMunicipios() {
		// TODO Corte 3: pedir los municipios de la provincia a utiles.Provincias.
		String[] municipios = "La Habana".equals(cbProvincia.getSelectedItem()) ? MUNICIPIOS_LA_HABANA
				: new String[0];
		cbMunicipio.setModel(new DefaultComboBoxModel<String>(municipios));
	}

	/** Campos del representante, iguales en persona jurídica y entidad no estatal. */
	private static class PanelRepresentante extends JPanel {

		private static final long serialVersionUID = 1L;

		PanelRepresentante() {
			super(new GridBagLayout());
			setBorder(BorderFactory.createTitledBorder("Representante"));
			Formulario.agregarFila(this, 0, "Nombre(s):", new JTextField(20));
			Formulario.agregarFila(this, 1, "Apellidos:", new JTextField(20));
			Formulario.agregarFila(this, 2, "Carné de identidad:", new JTextField(11));
		}
	}
}
