package cu.cujae.serviciostelefonicos.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import cu.cujae.serviciostelefonicos.interfaz.clientes.DialogoCliente;
import cu.cujae.serviciostelefonicos.interfaz.clientes.VentanaListadoClientes;
import cu.cujae.serviciostelefonicos.interfaz.fijo.DialogoFacturaFijo;
import cu.cujae.serviciostelefonicos.interfaz.fijo.DialogoRegistrarLlamada;

/**
 * Ventana principal de la aplicación. Contiene todos los menús y unos botones
 * de acceso rápido. Corte 2 · Tarea 12 (diseño de pantallas) · Inti.
 *
 * <p>Las opciones de móvil, Nauta, reportes y ayuda están creadas para que el
 * menú quede completo; sus ventanas las conecta Carli.</p>
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// Si falla, se queda el aspecto por defecto de Swing.
				}
				new VentanaPrincipal().setVisible(true);
			}
		});
	}

	public VentanaPrincipal() {
		setTitle("ETECSA - Control de clientes y servicios telefónicos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(760, 480));
		setSize(Formulario.tamano(820, 460));
		setLocationRelativeTo(null);

		setJMenuBar(crearMenus());

		JPanel contenido = new JPanel(new BorderLayout());
		contenido.setBorder(new EmptyBorder(16, 16, 8, 16));
		setContentPane(contenido);

		JPanel encabezado = new JPanel(new GridLayout(2, 1));
		JLabel lblTitulo = new JLabel("Sistema de control de clientes y servicios telefónicos", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		JLabel lblSubtitulo = new JLabel("Telefonía fija · Telefonía móvil · Cuentas Nauta", SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
		encabezado.add(lblTitulo);
		encabezado.add(lblSubtitulo);
		contenido.add(encabezado, BorderLayout.NORTH);

		JPanel accesos = new JPanel(new GridLayout(2, 3, 12, 12));
		accesos.setBorder(new EmptyBorder(24, 40, 24, 40));
		JButton btnClientes = crearBotonAcceso("Clientes", "Alta, búsqueda y listado");
		JButton btnNuevoCliente = crearBotonAcceso("Nuevo cliente", "Natural, jurídica o no estatal");
		JButton btnFactura = crearBotonAcceso("Factura de fijo", "Ver la factura de un mes");
		JButton btnLlamada = crearBotonAcceso("Registrar llamada", "Llamada desde un fijo o un móvil");
		JButton btnMovil = crearBotonAcceso("Teléfono móvil", "Registro mensual de llamadas");
		JButton btnNauta = crearBotonAcceso("Cuenta Nauta", "Consumo de correo y navegación");
		accesos.add(btnClientes);
		accesos.add(btnNuevoCliente);
		accesos.add(btnFactura);
		accesos.add(btnLlamada);
		accesos.add(btnMovil);
		accesos.add(btnNauta);
		contenido.add(accesos, BorderLayout.CENTER);

		JLabel lblEstado = new JLabel(" Clientes registrados: 0   ·   Servicios: 0");
		lblEstado.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
		contenido.add(lblEstado, BorderLayout.SOUTH);

		btnClientes.addActionListener(abrirListadoClientes());
		btnNuevoCliente.addActionListener(abrirNuevoCliente());
		btnFactura.addActionListener(abrirFacturaFijo());
		btnLlamada.addActionListener(abrirRegistrarLlamada());
	}

	private JMenuBar crearMenus() {
		JMenuBar barra = new JMenuBar();

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic(KeyEvent.VK_A);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnArchivo.add(mntmSalir);
		barra.add(mnArchivo);

		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setMnemonic(KeyEvent.VK_C);
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo cliente...");
		mntmNuevoCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevoCliente.addActionListener(abrirNuevoCliente());
		JMenuItem mntmListado = new JMenuItem("Listado de clientes");
		mntmListado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmListado.addActionListener(abrirListadoClientes());
		mnClientes.add(mntmNuevoCliente);
		mnClientes.add(mntmListado);
		barra.add(mnClientes);

		JMenu mnFijo = new JMenu("Teléfono fijo");
		mnFijo.setMnemonic(KeyEvent.VK_F);
		JMenuItem mntmNuevoFijo = new JMenuItem("Asignar teléfono fijo...");
		JMenuItem mntmFactura = new JMenuItem("Factura del mes");
		mntmFactura.addActionListener(abrirFacturaFijo());
		JMenuItem mntmLlamada = new JMenuItem("Registrar llamada...");
		mntmLlamada.addActionListener(abrirRegistrarLlamada());
		mnFijo.add(mntmNuevoFijo);
		mnFijo.add(mntmFactura);
		mnFijo.add(new JSeparator());
		mnFijo.add(mntmLlamada);
		barra.add(mnFijo);

		JMenu mnMovil = new JMenu("Teléfono móvil");
		mnMovil.setMnemonic(KeyEvent.VK_M);
		mnMovil.add(new JMenuItem("Asignar teléfono móvil..."));
		mnMovil.add(new JMenuItem("Registro del mes"));
		barra.add(mnMovil);

		JMenu mnNauta = new JMenu("Nauta");
		mnNauta.setMnemonic(KeyEvent.VK_U);
		mnNauta.add(new JMenuItem("Crear cuenta Nauta..."));
		mnNauta.add(new JMenuItem("Registrar consumo del mes..."));
		mnNauta.add(new JMenuItem("Consumo del mes"));
		barra.add(mnNauta);

		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setMnemonic(KeyEvent.VK_R);
		mnReportes.add(new JMenuItem("R1. Estado de cuenta de un teléfono fijo"));
		mnReportes.add(new JMenuItem("R2. Estado de cuenta de una cuenta Nauta"));
		mnReportes.add(new JMenuItem("R3. Llamadas de larga distancia por municipio"));
		mnReportes.add(new JMenuItem("R4. Móviles con mayor gasto en un mes"));
		barra.add(mnReportes);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic(KeyEvent.VK_Y);
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAyuda.add(mntmAyuda);
		mnAyuda.add(new JMenuItem("Créditos"));
		barra.add(mnAyuda);

		return barra;
	}

	private JButton crearBotonAcceso(String titulo, String descripcion) {
		JButton boton = new JButton("<html><center><b>" + titulo + "</b><br><small>" + descripcion
				+ "</small></center></html>");
		boton.setFocusPainted(false);
		return boton;
	}

	private ActionListener abrirListadoClientes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaListadoClientes(VentanaPrincipal.this).setVisible(true);
			}
		};
	}

	private ActionListener abrirNuevoCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogoCliente(VentanaPrincipal.this).setVisible(true);
			}
		};
	}

	private ActionListener abrirFacturaFijo() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogoFacturaFijo(VentanaPrincipal.this).setVisible(true);
			}
		};
	}

	private ActionListener abrirRegistrarLlamada() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogoRegistrarLlamada(VentanaPrincipal.this).setVisible(true);
			}
		};
	}
}
