package proyecto_programacion;

class mecanico extends personas {
	//Atributos
	protected String trabajo_mecanico= "Mecanico";

	//Constructores
	public mecanico() {
		
	}
	
	//Getters & Setters
	protected String getTrabajo_mecanico() {
		return trabajo_mecanico;
	}
	
	protected void setTrabajo_mecanico(String trabajo_mecanico) {
		this.trabajo_mecanico = trabajo_mecanico;
	}

	@Override
	public String toString() {
		String texto= "INFORMACI\u00f3N PARA EL EMPLEADO CON DNI: "+dni+"\n"+
				"Nombre: "+nombre+
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
