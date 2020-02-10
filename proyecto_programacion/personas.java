package proyecto_programacion;

abstract class personas {
	//Atributos
	protected String dni, nombre, apellidos, telefono_movil, fecha_nacimiento, cuenta_bancaria, direccion, tipo_persona;
	protected double sueldo;

	//Getters & Setters
	protected String getDni() {
		return dni;
	}

	protected void setDni(String dni) {
		this.dni = dni;
	}

	protected String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getApellidos() {
		return apellidos;
	}

	protected void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	protected String getTelefono_movil() {
		return telefono_movil;
	}

	protected void setTelefono_movil(String telefono_movil) {
		this.telefono_movil = telefono_movil;
	}

	protected String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	protected void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	protected String getCuenta_bancaria() {
		return cuenta_bancaria;
	}

	protected void setCuenta_bancaria(String cuenta_bancaria) {
		this.cuenta_bancaria = cuenta_bancaria;
	}

	protected String getDireccion() {
		return direccion;
	}

	protected void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	protected double getSueldo() {
		return sueldo;
	}

	protected void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String getTipo_persona() {
		return tipo_persona;
	}

	public void setTipo_persona(String tipo_persona) {
		this.tipo_persona = tipo_persona;
	}

}