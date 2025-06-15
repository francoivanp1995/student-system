package datos;

public abstract class Usuario {

	protected String id;
	protected String nombre;
	protected String email;
	
	public Usuario() {
		
	}
	
	public Usuario(String id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
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
	public void setEmail() {
		this.email = email;
	};
}
