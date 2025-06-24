package presentacion.admin;

import datos.*;
import presentacion.PanelManager;
import Servicios.ServicioAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static datos.Comandos.*;

public class ControladorPanelPrincipalGestionar implements ActionListener {

    private final PanelPrincipalGestionar panel;
    private final ServicioAdmin servicioAdmin;
    private final Gestionar tipo;

    private RolUsuario rol;

    //chequear si esto funciona con panelManager.
    private PanelManager panelManager;


    public ControladorPanelPrincipalGestionar(PanelPrincipalGestionar panel, ServicioAdmin servicioAdmin, Gestionar tipo, RolUsuario rol) {
        System.out.println(">> Creando ControladorGestionar para: " + tipo);
        this.panel = panel;
        this.servicioAdmin = servicioAdmin;
        this.tipo = tipo;
        this.rol = rol;
        this.panel.setListener(this);
    }

    private void cargarDatos() {
        System.out.println(">> Cargando datos para: " + tipo);
        switch (tipo) {
            case CURSO -> {
                List<Curso> cursos = servicioAdmin.obtenerTodosLosCursos();
                panel.actualizarModelo(new CursoTableModel(cursos));
            }
            case USUARIO -> {
//                List<Usuario> usuarios = servicio.obtenerTodosLosUsuarios();
//                panel.actualizarModelo(new UsuarioTableModel(usuarios));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LEER -> cargarDatos();
            case CREAR -> {
                if (tipo == Gestionar.CURSO) {
//                    servicioAdmin.crearCurso(new Curso("Nuevo", 0, 0.0, 0.0, null));
                } else if (tipo == Gestionar.USUARIO) {
//                    servicio.crearUsuario(new Usuario("nuevo", "user", "pass", null));
                }

            }
            case ELIMINAR -> {
                int fila = panel.getFilaSeleccionada();
                if (fila >= 0) {
                    if (tipo == Gestionar.CURSO) {
                        Curso c = ((CursoTableModel) panel.getTabla().getModel()).getCursoAt(fila);
//                        servicio.eliminarCurso(c.getId());
                    } else {
                        Usuario u = ((UsuarioTableModel) panel.getTabla().getModel()).getUsuarioAt(fila);
//                        servicio.eliminarUsuario(u.getId());
                    }

                }
            }
            case REGRESAR -> {
                try {
                    panel.getPanelManager().mostrarPanelPorRol(rol);

                } catch (Exception ex) {
                    panel.mostrarError("Error al regresar: " + ex.getMessage());
                }
            }
            case "MOSTRARREPORTE" -> {
                cargarDatos();
            }
            case CANCELAR -> System.exit(0);
        }
    }
}
