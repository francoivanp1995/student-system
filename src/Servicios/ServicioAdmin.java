package Servicios;

import datos.DAO.CursoDAOH2Impl;
import datos.DAO.UsuarioDAOH2Impl;
import datos.Excepcion.*;
import datos.Profesor;
import datos.Usuario;
import datos.Validacion.ValidarCurso;
import datos.Validacion.ValidarUsuario;
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
    private ValidarUsuario validarUsuario;

    public ServicioAdmin(PanelPrincipalAdmin panelPrincipalAdmin, PanelManager panelManager) {
        this.panelPrincipalAdmin = panelPrincipalAdmin;
        this.panelManager = panelManager;
    }

    public List<Curso> obtenerTodosLosCursos() throws ServicioException{
        try {
           return cursoDAO.listaTodosLosCursos();
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServicioException(e);
        }
    }

    public void crearCurso(Curso curso) throws ServicioException {
        try {
            cursoDAO.crearCurso(curso);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServicioException(e);
        }
    }

    public void eliminarCurso(String curso) throws ServicioException {
        try {
            cursoDAO.eliminarCurso(curso);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServicioException(e);
        }
    }

    public void actualizarCurso(Curso curso) throws ServicioException {
        try {
            cursoDAO.actualizarCurso(curso);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServicioException("Error al actualizar curso: ", e);
        }
    }

    public void validarCurso(Curso nuevoCurso) throws ServicioException {
        try {
            validadorCurso.validar(nuevoCurso);
        } catch (CursoException e) {
            e.printStackTrace();
            throw new ServicioException("Error al validar el curso: ", e);
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws ServicioException {
        try {
            return usuarioDAO.listaTodosLosUsuarios();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServicioException("Error al obtener todos los usuarios: ", e);
        }
    }

    public void crearUsuario(Usuario usuario) throws ServicioException{
        try {
            usuarioDAO.crearUsuario(usuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServicioException(e);
        }
    };

    public void validarUsuario(Usuario usuario){
        try {
            validarUsuario.validar(usuario);
        } catch (ValidacionException e) {
            e.printStackTrace();
            throw new ServicioException(e);
        }
    };

    public void eliminarUsuario(Usuario usuario) throws ServicioException {
        try {
            usuarioDAO.eliminarUsuario(usuario);
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServicioException(e);
        }
    }

    public void actualizarUsuario(Usuario usuario) throws ServicioException {
        try {
            usuarioDAO.actualizarUsuario(usuario);
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServicioException(e);
        }
    }
}
