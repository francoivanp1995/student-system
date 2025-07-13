package datos;

public class Alumno extends Usuario{

	private int limiteCursos;

	public Alumno(String id, String nombre, String apellido, String email, RolUsuario rol,String nombreUsuario, String contrasenia) {
		super(id, nombre, apellido, email, rol, nombreUsuario, contrasenia);
		// TODO Auto-generated constructor stub
	}

	public Alumno(String dni, String nombre, String apellido){
		super(dni,nombre,apellido);
	}

	public int getLimiteCursos() {
		return limiteCursos;
	}

	public void setLimiteCursos(Integer limiteCursos) {
		this.limiteCursos = limiteCursos;
	}
}
