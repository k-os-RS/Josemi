package proyecto_programacion;

class mecanico extends personas {
	//Atributos
	protected final String trabajo_mecanico= "Mecanico";
	
	//Constructores
	public mecanico() {
		
	}
	
	//Getters & Setters
	protected String getTrabajo_mecanico() {
		return trabajo_mecanico;
	}
	
	@Override
	public String toString() {
		String texto= "\nINFORMACI\u00f3N PARA EL EMPLEADO CON DNI: "+dni+"\n"+
				"\nNombre: "+nombre+
				"\nApellidos: "+apellidos+
				"\nFecha Nacimiento: "+fecha_nacimiento+
				"\nTrabaja de: "+trabajo_mecanico+
				"\nSueldo: "+sueldo+ "\u20ac"+
				"\nCuenta bancaria: "+cuenta_bancaria+
				"\nTelefono m\u00f3vil: "+telefono_movil+
				"\nDirecci\u00f3n: "+direccion;
		
		return texto;
	}
}
