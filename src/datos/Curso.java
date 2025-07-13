package datos;

import java.util.List;

public class Curso {

    private String id;
    private String profesorDni;
    private String nombre;
    private int precio;
    private int cupoMaximo;
    private int notaAprobacion;
    private List<Inscripcion> inscripciones;
    private int cantidadInscritos;

    public Curso(String nombre, int cupoMaximo, int precio, int notaAprobacion, String profesorDni) {
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        this.precio = precio;
        this.notaAprobacion = notaAprobacion;
        this.profesorDni = profesorDni;
    }

    public Curso(String id, String nombre, int cupoMaximo, int precio, int notaAprobacion, String profesorDni  ){
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        this.precio = precio;
        this.notaAprobacion = notaAprobacion;
        this.id = id;
        this.profesorDni = profesorDni;
    }

    public Curso(String id, String nombre, int notaAprobacion){
        this.id = id;
        this.nombre = nombre;
        this.notaAprobacion = notaAprobacion;
    }

    public Curso(){

    }

    public int getCantidadInscritos() {
        return cantidadInscritos;
    }

    public void setCantidadInscritos(int cantidadInscritos) {
        this.cantidadInscritos = cantidadInscritos;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getNotaAprobacion() { return notaAprobacion; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }


    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public void setNotaAprobacion(int notaAprobacion) {
        this.notaAprobacion = notaAprobacion;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public String getProfesorDni() {
        return profesorDni;
    }

    public void setProfesorDni(String profesorDni) {
        this.profesorDni = profesorDni;
    }
}
