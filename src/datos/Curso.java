package datos;

import java.util.List;

public class Curso {

    private String id;
    private String nombre;
    private int precio;
    private int cupoMaximo;
    private int notaAprobacion;
    private List<Inscripcion> inscripciones;

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getNotaAprobacion() { return notaAprobacion; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }

    // Setters
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
}
