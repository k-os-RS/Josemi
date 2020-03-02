package proyecto_programacion;
class asesor extends personas {
	//Atributos
	protected String trabajo;
	
	//Constructores
	public asesor() {
		
	}

	//Getters & Setters
	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	
	@Override
	public String toString() {
		String texto= "INFORMACI\u00f3N PARA EL EMPLEADO CON DNI: "+dni+"\n"+
				"Nombre: "+nombre+
				"\nApellidos: "+apellidos+
				"\nFecha nacimiento: "+fecha_nacimiento+
				"\nTrabaja de: "+trabajo+
				"\nSueldo: "+sueldo+ "\u20ac"+
				"\nCuenta bancaria: "+cuenta_bancaria+
				"\nTelefono m\u00f3vil: "+telefono_movil+
				"\nDirecci\u00f3n: "+direccion;

		return texto;
	}
}
