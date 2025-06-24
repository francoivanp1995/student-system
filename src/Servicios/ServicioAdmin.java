package Servicios;

import datos.DAO.CursoDAOH2Impl;
import datos.DAO.UsuarioDAOH2Impl;
import presentacion.PanelManager;
import presentacion.admin.PanelPrincipalAdmin;
import datos.Curso;
import java.util.List;

public class ServicioAdmin {

    private final PanelPrincipalAdmin panelPrincipalAdmin;
    private final PanelManager panelManager;
    private final CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
    private final UsuarioDAOH2Impl usuarioDAO = new UsuarioDAOH2Impl();


    public ServicioAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
    }

    public List<Curso> obtenerTodosLosCursos() {
        return cursoDAO.listaTodosLosCursos();
    }

    public void crearCurso(Curso curso) {
        cursoDAO.crearCurso(curso);
    }

    public void eliminarCurso(Curso curso) {
        cursoDAO.eliminarCurso(curso);
    }

    public void actualizarCurso(Curso curso) {
        cursoDAO.actualizarCurso(curso);
    }

    public void usuario(){
        //todo
    }
}
