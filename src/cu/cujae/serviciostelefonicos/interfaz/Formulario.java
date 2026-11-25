package cu.cujae.serviciostelefonicos.interfaz;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * Ayuda a colocar etiquetas y campos en dos columnas con GridBagLayout, para
 * que todos los formularios tengan el mismo aspecto. Solo se usa en la capa de
 * presentación.
 */
public final class Formulario {

	private Formulario() {
	}

	/** Añade en la fila indicada una etiqueta a la izquierda y el campo a la derecha. */
	public static void agregarFila(JPanel panel, int fila, String etiqueta, JComponent campo) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = fila;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(4, 4, 4, 8);
		JLabel lbl = new JLabel(etiqueta);
		lbl.setLabelFor(campo);
		panel.add(lbl, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = fila;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(4, 0, 4, 4);
		panel.add(campo, c);
	}

	/** Añade un componente que ocupa las dos columnas de la fila. */
	public static void agregarFilaCompleta(JPanel panel, int fila, JComponent componente) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = fila;
		c.gridwidth = 2;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(6, 4, 4, 4);
		panel.add(componente, c);
	}

	/**
	 * Tamaño en píxeles ajustado al tamaño de letra del sistema, para que las
	 * ventanas no queden apretadas en pantallas con escalado alto.
	 */
	public static Dimension tamano(int ancho, int alto) {
		Font letra = UIManager.getFont("Label.font");
		float factor = letra == null ? 1f : Math.max(1f, letra.getSize2D() / 12f);
		return new Dimension(Math.round(ancho * factor), Math.round(alto * factor));
	}

	/** Ajusta la altura de las filas de una tabla a la letra que usa. */
	public static void ajustarFilas(JTable tabla) {
		tabla.setRowHeight(tabla.getFontMetrics(tabla.getFont()).getHeight() + 6);
	}

	/** Empuja el contenido hacia arriba ocupando el espacio sobrante. */
	public static void agregarRelleno(JPanel panel, int fila) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = fila;
		c.weighty = 1.0;
		panel.add(new JLabel(), c);
	}
}
