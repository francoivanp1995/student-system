package presentacion;

import datos.Excepcion.ErrorOperacionException;
import datos.Excepcion.ValidacionException;
import datos.interfaz.VistaLogin;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelInicio extends PanelBase implements VistaLogin {

	private JTextField loginCampo;
	private JPasswordField passwordCampo;
	protected final String btnIniciar = "Iniciar sesion";
	protected final String btnCancelar = "Cancelar";
	protected final String iniciarCommand = "INICIAR";
	protected final String cancelarCommand = "CANCELAR";

	public PanelInicio(PanelManager panelManager) {
		super(panelManager);
		crearBotonera();
		setUIComponentes();
	}

	private void crearBotonera() {
		panelBotonera = new PanelBotonera();
		panelBotonera.agregarBoton(btnIniciar,iniciarCommand);
		panelBotonera.agregarBoton(btnCancelar,cancelarCommand);
		setBotonera(panelBotonera);
	}

	@Override
	protected void setUIComponentes() {
		add(crearTitulo(), BorderLayout.NORTH);
		add(construirPanel(), BorderLayout.CENTER);
		System.out.println("Estoy en el setUI Componentes");
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

//		System.out.println("Aca llego en PanelInicio antes de agregar loginCampo al formulario");
		gbc.gridx = 1;
		loginCampo = new JTextField(15);
//		System.out.println("loginCampo en construirPanel: " + System.identityHashCode(loginCampo));

		formularioPanel.add(loginCampo, gbc);
//		System.out.println("Aca llego en PanelInicio despues de agregar loginCampo al formulario");
		// Contraseña
		gbc.gridx = 0;
		gbc.gridy = 1;
		formularioPanel.add(new JLabel("Contrasenia:"), gbc);

//		System.out.println("Aca llego en PanelInicio antes de agregar passwordCampo al formulario");
		gbc.gridx = 1;
		passwordCampo = new JPasswordField(15);
//		System.out.println("passwordCampo en construirPanel: " + System.identityHashCode(passwordCampo));

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
		System.out.println("loginCampo en limpiar: " + System.identityHashCode(loginCampo));
		System.out.println("passwordCampo en limpiar: " + System.identityHashCode(passwordCampo));
		loginCampo.setText("");
		passwordCampo.setText("");
	}

	@Override
	public void setListener(ActionListener listener) {
		System.out.println("Estoy en el ActionListener");
		panelBotonera.agregarListener(listener);
	}
}
