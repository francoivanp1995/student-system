package Servicios;

import datos.DAO.CursoDAOH2Impl;
import datos.DAO.UsuarioDAOH2Impl;
import datos.Excepcion.CursoException;
import datos.Excepcion.DAOException;
import datos.Excepcion.DatabaseException;
import datos.Excepcion.ServicioException;
import datos.Profesor;
import datos.Usuario;
import datos.Validacion.ValidarCurso;
import presentacion.PanelManager;
import presentacion.admin.PanelPrincipalAdmin;
import datos.Curso;
import java.util.List;

public class ServicioAdmin {

    private final PanelPrincipalAdmin panelPrincipalAdmin;
    private final PanelManager panelManager;
    private final CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
    private final UsuarioDAOH2Impl usuarioDAO = new UsuarioDAOH2Impl();
    private ValidarCurso validadorCurso;


    public ServicioAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
    }

    public List<Curso> obtenerTodosLosCursos() throws ServicioException{
        try {
           return cursoDAO.listaTodosLosCursos();
        } catch (DatabaseException e){
            throw new ServicioException(e);
        }
    }

    public void crearCurso(Curso curso) throws ServicioException {
        try {
            cursoDAO.crearCurso(curso);
        } catch (DatabaseException e) {
            throw new ServicioException("Error al guardar el curso en la base de datos." + e.getMessage(), e);
        }
    }

    public void eliminarCurso(String curso) throws ServicioException {
        try {
            cursoDAO.eliminarCurso(curso);
        } catch (DatabaseException e) {
            throw new ServicioException(e);
        }
    }

    public void actualizarCurso(Curso curso) throws ServicioException {
        try {
            cursoDAO.actualizarCurso(curso);
        } catch (DatabaseException e) {
            throw new ServicioException("Error al actualizar curso: " + e.getMessage(), e);
        }
    }

    public void usuario(){
        //todo
    }

    public void validarCurso(Curso nuevoCurso) throws CursoException {
        validadorCurso.validar(nuevoCurso);
    }

//    public List<Usuario> obtenerTodosLosUsuarios() {
//        return cursoDAO.listaTodosLosUsuarios();
//    }

    public Profesor buscarProfesorPorDni(String dni) throws ServicioException {
        try {
            Usuario usuario = null;
            try {
                usuario = usuarioDAO.buscarUsuarioPorDni(dni);
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
            if (usuario == null) {
                throw new ServicioException("No existe un usuario con DNI: " + dni);
            }
            if (!(usuario instanceof Profesor)) {
                throw new ServicioException("El usuario con DNI " + dni + " no es un profesor.");
            }
            return (Profesor) usuario;
        } catch (DAOException e) {
            throw new ServicioException("Error al buscar profesor: " + e.getMessage(), e);
        }
    }
}
