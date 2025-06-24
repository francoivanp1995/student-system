	package presentacion;

	import datos.interfaz.VistaLogin;
	import presentacion.abstracto.PanelBase;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionListener;

	import static datos.Comandos.CANCELAR;
	import static datos.Comandos.INICIAR;

	public class PanelInicio extends PanelBase implements VistaLogin {

		private JTextField loginCampo;
		private JPasswordField passwordCampo;
		private static final String labelPanel = "SISTEMA DE ALUMNOS";

		public PanelInicio(PanelManager panelManager) {
			super(panelManager);
			setUIComponentesBase();
		}

		@Override
		protected void agregarBotonABotonera() {
			panelBotonera.agregarBoton(INICIAR, INICIAR);
			panelBotonera.agregarBoton(CANCELAR, CANCELAR);
		}

		@Override
		protected void setUIComponentesBase() {
			panelBotonera = new PanelBotonera();
			agregarBotonABotonera();
			add(panelSuperior(), BorderLayout.NORTH);
			add(panelCentral(), BorderLayout.CENTER);
			add(panelBotonera, BorderLayout.SOUTH);
		}

		@Override
		protected JPanel panelSuperior() {
			JPanel tituloPanel = new JPanel();
			JLabel nombreTitulo = new JLabel(labelPanel);
			tituloPanel.add(nombreTitulo);
			return tituloPanel;
		}

		@Override
		protected JPanel panelCentral() {
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
			formularioPanel.add(new JLabel("Contrasenia:"), gbc);

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
			System.out.println("Estoy en el ActionListener");
			panelBotonera.agregarListener(listener);
		}
	}
