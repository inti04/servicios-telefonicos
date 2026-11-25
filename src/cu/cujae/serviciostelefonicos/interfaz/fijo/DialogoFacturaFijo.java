package cu.cujae.serviciostelefonicos.interfaz.fijo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cu.cujae.serviciostelefonicos.interfaz.Formulario;

/**
 * Factura de un teléfono fijo en un mes: tarifa fija, llamadas locales, larga
 * distancia (con su desglose por municipio), atraso y total. Desde aquí también
 * se registra el atraso del mes.
 *
 * <p>Corte 2 · Tarea 12 · Inti. Los importes son datos de ejemplo; en el Corte 3
 * se obtienen con {@code Etecsa.buscarServicio(numero)} y
 * {@code TelefonoFijo.getFactura(mes)}.</p>
 */
public class DialogoFacturaFijo extends JDialog {

	private static final long serialVersionUID = 1L;

	public static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
			"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private final JTextField txtNumero = new JTextField("78301234", 10);
	private final JComboBox<String> cbMes = new JComboBox<String>(MESES);

	private final JTextField txtTarifaFija = crearCampoImporte("10.00");
	private final JTextField txtLocales = crearCampoImporte("8.40");
	private final JTextField txtLargaDistancia = crearCampoImporte("23.30");
	private final JTextField txtAtraso = crearCampoImporte("0.80");
	private final JTextField txtTotal = crearCampoImporte("42.50");

	public DialogoFacturaFijo(Window owner) {
		super(owner, "Factura del teléfono fijo", ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(Formulario.tamano(620, 470));
		setLocationRelativeTo(owner);

		JPanel contenido = new JPanel(new BorderLayout(0, 8));
		contenido.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contenido);

		contenido.add(crearPanelBusqueda(), BorderLayout.NORTH);

		JPanel centro = new JPanel(new BorderLayout(0, 8));
		centro.add(crearPanelResumen(), BorderLayout.NORTH);
		centro.add(crearPanelDesglose(), BorderLayout.CENTER);
		contenido.add(centro, BorderLayout.CENTER);

		contenido.add(crearPanelBotones(), BorderLayout.SOUTH);
		cbMes.setSelectedIndex(9);
	}

	private JPanel crearPanelBusqueda() {
		JPanel p = new JPanel(new BorderLayout());
		JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
		fila.add(new JLabel("Número del fijo:"));
		fila.add(txtNumero);
		fila.add(new JLabel("Mes:"));
		fila.add(cbMes);
		fila.add(new JButton("Mostrar"));
		p.add(fila, BorderLayout.NORTH);

		JLabel lblTitular = new JLabel("Titular: Ana María Pérez Gómez  ·  Plaza de la Revolución, La Habana");
		lblTitular.setBorder(new EmptyBorder(4, 8, 0, 0));
		p.add(lblTitular, BorderLayout.SOUTH);
		return p;
	}

	private JPanel crearPanelResumen() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder("Factura del mes"));
		Formulario.agregarFila(p, 0, "Tarifa fija:", txtTarifaFija);
		Formulario.agregarFila(p, 1, "Llamadas locales:", txtLocales);
		Formulario.agregarFila(p, 2, "Larga distancia:", txtLargaDistancia);
		Formulario.agregarFila(p, 3, "Atraso:", txtAtraso);
		txtTotal.setFont(txtTotal.getFont().deriveFont(Font.BOLD));
		Formulario.agregarFila(p, 4, "Total a pagar:", txtTotal);
		return p;
	}

	private JScrollPane crearPanelDesglose() {
		String[] columnas = { "Provincia", "Municipio", "Minutos", "Monto" };
		Object[][] datos = { { "Santiago de Cuba", "Santiago de Cuba", "12:30", "$ 12.50" },
				{ "Villa Clara", "Santa Clara", "6:00", "$ 6.00" }, { "Matanzas", "Cárdenas", "4:48", "$ 4.80" } };
		JTable tabla = new JTable(new DefaultTableModel(datos, columnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		});
		Formulario.ajustarFilas(tabla);
		JScrollPane sp = new JScrollPane(tabla);
		sp.setBorder(BorderFactory.createTitledBorder("Desglose de llamadas de larga distancia"));
		return sp;
	}

	private JPanel crearPanelBotones() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnAtraso = new JButton("Registrar atraso...");
		JButton btnCerrar = new JButton("Cerrar");
		p.add(btnAtraso);
		p.add(btnCerrar);

		btnAtraso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(DialogoFacturaFijo.this,
						"Atraso del mes (lo que quedó sin pagar del mes anterior):", "Registrar atraso",
						JOptionPane.QUESTION_MESSAGE);
			}
		});
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		return p;
	}

	private static JTextField crearCampoImporte(String valor) {
		JTextField campo = new JTextField("$ " + valor, 10);
		campo.setEditable(false);
		campo.setHorizontalAlignment(SwingConstants.RIGHT);
		return campo;
	}
}
