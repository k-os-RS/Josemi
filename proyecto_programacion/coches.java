package proyecto_programacion;

class coches extends vehiculos {
	//Atributos
	protected String enReparacionCoche, vendidoCoche;

	//Constructores	
	public coches() {
		
	}

	//Getters & Setters
	public String getEnReparacionCoche() {
		return enReparacionCoche;
	}

	public void setEnReparacionCoche(String enReparacionCoche) {
		this.enReparacionCoche = enReparacionCoche;
	}
	
	public String getVendidoCoche() {
		return vendidoCoche;
	}
	
	public void setVendidoCoche(String vendidoCoche) {
		this.vendidoCoche = vendidoCoche;
	}

	@Override
	public String toString() {
		String texto= "Datos del coche: "+matricula+"\n"+
				"Modelo: "+modelo+
				"\nColor: "+color+
				"\nPlazas: "+plazas+
				"\nCombustible: "+combustible+
				"\nKilometros: "+kilometros+
				"\nPrecio: "+precio+"\u20ac"+
				"\nVendido: "+vendidoCoche+
				"\nEn reparaci\u00f3n: "+enReparacionCoche;
		
		return texto;
	}

}
