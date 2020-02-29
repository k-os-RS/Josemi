package proyecto_programacion;

class asesor extends personas {
	//Atributos
	protected String trabajo_asesor= "Asesor";
	
	//Constructores
	public asesor() {
		
	}
	
	//Getters & Setters
	protected String getTrabajo_asesor() {
		return trabajo_asesor;
	}
	
	public void setTrabajo_asesor(String trabajo_asesor) {
		this.trabajo_asesor = trabajo_asesor;
	}

	@Override
	public String toString() {
		String texto= "INFORMACI\u00f3N PARA EL EMPLEADO CON DNI: "+dni+"\n"+
				"Nombre: "+nombre+
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
