package datos;

public class Inscripcion {

    private Alumno alumno;
    private Curso curso;
    private Integer notaFinal;

    public Inscripcion(Alumno alumno, Curso curso) {
        this.alumno = alumno;
        this.curso = curso;
        this.notaFinal = null;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public boolean estaAprobado() {
        return notaFinal != null && notaFinal >= curso.getNotaAprobacion();
    }
}
