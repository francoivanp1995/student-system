package presentacion.abstracto;

import javax.swing.*;

public abstract class PanelFormularioBase<T> extends JPanel {
    public abstract T construirEntidad();
}
