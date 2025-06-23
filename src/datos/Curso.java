
package datos;

import java.util.List;

public class Curso {
	
    private String id;
    private String nombre;
    private int precio;
    private int cupoMaximo;
    private int notaAprobacion;
    private List<Inscripcion> inscripciones;

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPrecio(){
        return precio;
    }

    public int getCupoMaximo(){
        return cupoMaximo;
    }

    public int getNotaAprobacion(){
        return notaAprobacion;
    }

    public List<Inscripcion> getInscripciones(){
        return inscripciones;
    }

    public void setId(){
        
    }

    public void setNombre(){

    }

    public void setPrecio(){

    }

    public void setCupoMaximo(){

    }

    public void setNotaAprobacion(){

    }

    public void setInscripciones(){

    }
}
