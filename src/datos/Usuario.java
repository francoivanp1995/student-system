package datos;

public abstract class Usuario {

	protected String id;
	protected String nombre;
	protected String email;
	protected RolUsuario rol;
	
	public Usuario() {
		
	}
	
	public Usuario(String id, String nombre, String email, RolUsuario rol) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
	}
	
	public String getId() {
		return id;
	};
	
	public String getNombre() {
		return nombre;
	};
	
	public String getEmail() {
		return email;
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

	public RolUsuario getRol(){
		return rol;
	}
}
