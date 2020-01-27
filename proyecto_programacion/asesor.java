package proyecto_programacion;

class asesor extends personas {
	//Atributos
	protected final String trabajo_asesor= "Asesor";
	
	//Constructores
	public asesor(String dni, String nombre, String apellidos, String telefono_movil, String fecha_nacimiento,
			String cuenta_bancaria, String direccion, double sueldo) {
		super(dni, nombre, apellidos, telefono_movil, fecha_nacimiento, cuenta_bancaria, direccion, sueldo);

	}

	public asesor() {
		
	}

	//Getters & Setters
	protected String getTrabajo_asesor() {
		return trabajo_asesor;
	}

	@Override
	public String toString() {
		String texto= "\nINFORMACI\u00f3N PARA EL EMPLEADO CON DNI: "+dni+"\n"+
				"\nNombre: "+nombre+
				"\nApellidos: "+apellidos+
				"\nFecha Nacimiento: "+fecha_nacimiento+
				"\nTrabaja de: "+trabajo_asesor+
				"\nSueldo: "+sueldo+ "\u20ac"+
				"\nCuenta bancaria: "+cuenta_bancaria+
				"\nTelefono m\u00f3vil: "+telefono_movil+
				"\nDirecci\u00f3n: "+direccion;

		return texto;
	}
}
