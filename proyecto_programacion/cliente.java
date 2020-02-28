package proyecto_programacion;

class cliente extends personas {
	//Atributos
	protected int compraCoche= 0, compraMoto= 0;

	//Constructores
	public cliente() {
		
	}

	//Getters & Setters
	public int getCompraCoche() {
		return compraCoche;
	}
	
	public void setCompraCoche(int compraCoche) {
		this.compraCoche = compraCoche;
	}

	public int getCompraMoto() {
		return compraMoto;
	}

	public void setCompraMotos(int compraMotos) {
		this.compraMoto = compraMotos;
	}

	@Override
	public String toString() {
		String texto= "\nINFORMACI\u00f3N PARA EL CLIENTE CON DNI: "+dni+"\n"+
				"Nombre: "+nombre+
				"\nApellidos: "+apellidos+
				"\nDirecci\u00f3n: "+direccion+
				"\nTelefono m\u00f3vil: "+telefono_movil+
				"\nCoches comprados: "+compraCoche+
				"\nMotos compradas: "+compraMoto;

		return texto;
	}
}
