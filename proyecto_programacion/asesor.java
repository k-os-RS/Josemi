package proyecto_programacion;
class asesor extends personas {
	//Atributos
	protected String primerinicio, trabajo, password;
	
	//Constructores
	public asesor() {
		
	}
	
	//Getters & Setters	
	public String getPrimerinicio() {
		return primerinicio;
	}

	public void setPrimerinicio(String primerinicio) {
		this.primerinicio = primerinicio;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
