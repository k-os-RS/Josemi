package proyecto_programacion;

class motos extends vehiculos {
	//Atributos
	protected String enReparacionMoto, vendidoMoto;

	//Constructores	
	public motos() {
		
	}

	//Getters & Setters
	public String getEnReparacionMoto() {
		return enReparacionMoto;
	}

	public void setEnReparacionMoto(String enReparacionMoto) {
		this.enReparacionMoto = enReparacionMoto;
	}

	public String getVendidoMoto() {
		return vendidoMoto;
	}

	public void setVendidoMoto(String vendidoMoto) {
		this.vendidoMoto = vendidoMoto;
	}

	@Override
	public String toString() {
		String texto= "\nDatos del coche: "+matricula+
				"\nModelo: "+modelo+
				"\nColor: "+color+
				"\nPlazas: "+plazas+
				"\nCombustible: "+combustible+
				"\nKilometros: "+kilometros+
				"\nPrecio: "+precio+"\u20ac"+
				"\nVendido: "+vendidoMoto+
				"\nEn reparaci\u00fan: "+enReparacionMoto;
		
		return texto;
	}
}
