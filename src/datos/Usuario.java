package datos;

public abstract class Usuario {

	protected String id;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected RolUsuario rol;
	protected String nombreUsuario;
	protected String contrasenia;
	
	public Usuario() {
		
	}
	
	public Usuario(String id, String nombre, String apellido, String email, RolUsuario rol, String nombreUsuario, String contrasenia) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.rol = rol;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
	}
	
	public String getId() {
		return id;
	};

	public String getNombre() {
		return nombre;
	};

	public String getApellido() {
		return nombre;
	};

	public String getEmail() {
		return email;
	};

	public String getNombreUsuario() {
		return nombreUsuario;
	};

	public String getContrasenia() {
		return contrasenia;
	};
	
	public void setId(String id) {
		this.id = id;
	};
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	};

	public void setEmail(String email) {
		this.email = email;
	};

	public void setApellido(String apellido) {
		this.apellido = apellido;
	};

	public RolUsuario getRol(){
		return rol;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	};

	public void setContrasenia() {
		this.contrasenia = contrasenia;
	};
}
