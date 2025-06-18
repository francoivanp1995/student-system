package presentacion;

import datos.Excepcion.ErrorOperacionException;
import datos.Excepcion.ValidacionException;
import datos.interfaz.VistaLogin;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelInicio extends PanelBase implements VistaLogin {

	private JTextField loginCampo = new JTextField(15);
	private JPasswordField passwordCampo = new JPasswordField(15);
	private PanelBotonera panelBotonera;

	public PanelInicio(PanelManager panelManager) {
		super(panelManager);
		crearBotonera();
	}

	private void crearBotonera() {
		panelBotonera = new PanelBotonera();
		panelBotonera.agregarBoton("Iniciar sesión");
		panelBotonera.agregarBoton("Cancelar");
		setBotonera(panelBotonera);
	}

	@Override
	protected void setUIComponentes() {
		add(crearTitulo(), BorderLayout.NORTH);
		add(construirPanel(), BorderLayout.CENTER);
	}

	@Override
	protected JPanel crearTitulo() {
		JPanel tituloPanel = new JPanel();
		JLabel nombreTitulo = new JLabel("SISTEMA DE ALUMNOS");
		tituloPanel.add(nombreTitulo);
		return tituloPanel;
	}

	@Override
	protected JPanel construirPanel() {

		JPanel formularioPanel = new JPanel(new GridBagLayout());
		formularioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		// Usuario
		gbc.gridx = 0;
		gbc.gridy = 0;
		formularioPanel.add(new JLabel("Usuario:"), gbc);

		gbc.gridx = 1;
		loginCampo = new JTextField(15);
		formularioPanel.add(loginCampo, gbc);

		// Contraseña
		gbc.gridx = 0;
		gbc.gridy = 1;
		formularioPanel.add(new JLabel("Contraseña:"), gbc);

		gbc.gridx = 1;
		passwordCampo = new JPasswordField(15);
		formularioPanel.add(passwordCampo, gbc);

		return formularioPanel;
	}

	@Override
	public String getUsuario() {
		return loginCampo.getText();
	}

	@Override
	public char[] getContrasena() {
		return passwordCampo.getPassword();
	}

	@Override
	public void limpiar() {
		loginCampo.setText("");
		passwordCampo.setText("");
	}

	@Override
	public void setListener(ActionListener listener) {
		panelBotonera.agregarListener(listener);
	}
}
