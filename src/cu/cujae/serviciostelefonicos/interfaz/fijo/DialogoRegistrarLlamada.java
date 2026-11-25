package cu.cujae.serviciostelefonicos.interfaz.fijo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import cu.cujae.serviciostelefonicos.interfaz.Formulario;

/**
 * Registro de una llamada: número de origen, número de destino, duración y mes.
 * La capa lógica decide dónde se carga: en la factura del fijo (local o larga
 * distancia) o en el registro del móvil si sale de un fijo hacia un móvil.
 *
 * <p>Corte 2 · Tarea 12 · Inti. En el Corte 3 Aceptar llamará a
 * {@code Etecsa.registrarLlamada(origen, destino, segundos, mes)}.</p>
 */
public class DialogoRegistrarLlamada extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JTextField txtOrigen = new JTextField(12);
	private final JTextField txtDestino = new JTextField(12);
	private final JSpinner spMinutos = new JSpinner(new SpinnerNumberModel(0, 0, 600, 1));
	private final JSpinner spSegundos = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
	private final JComboBox<String> cbMes = new JComboBox<String>(DialogoFacturaFijo.MESES);

	public DialogoRegistrarLlamada(Window owner) {
		super(owner, "Registrar llamada", ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel contenido = new JPanel(new BorderLayout(0, 8));
		contenido.setBorder(new EmptyBorder(12, 12, 12, 12));
		setContentPane(contenido);

		JPanel datos = new JPanel(new GridBagLayout());
		datos.setBorder(BorderFactory.createTitledBorder("Datos de la llamada"));
		Formulario.agregarFila(datos, 0, "Número de origen:", txtOrigen);
		Formulario.agregarFila(datos, 1, "Número de destino:", txtDestino);
		JPanel duracion = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
		duracion.add(spMinutos);
		duracion.add(new JLabel("min"));
		duracion.add(spSegundos);
		duracion.add(new JLabel("s"));
		Formulario.agregarFila(datos, 2, "Duración:", duracion);
		Formulario.agregarFila(datos, 3, "Mes:", cbMes);
		contenido.add(datos, BorderLayout.CENTER);

		JLabel lblNota = new JLabel("<html><small>Si la llamada sale de un fijo hacia un móvil, se carga al móvil.<br>"
				+ "Entre fijos de distinto municipio se factura como larga distancia.</small></html>");
		contenido.add(lblNota, BorderLayout.NORTH);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnAceptar = new JButton("Registrar");
		JButton btnCancelar = new JButton("Cancelar");
		botones.add(btnAceptar);
		botones.add(btnCancelar);
		contenido.add(botones, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(DialogoRegistrarLlamada.this,
						"El registro se conectará con la capa lógica en el Corte 3.", "Diseño de pantalla",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pack();
		setResizable(false);
		setLocationRelativeTo(owner);
	}
}
