package proyecto_programacion;

abstract class vehiculos {
	//Atributos
	protected String matricula, modelo, color, combustible, plazas;
	protected double precio, kilometros;
	
	//Getters & Setters
	protected String getMatricula() {
		return matricula;
	}
	
	protected void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	protected String getModelo() {
		return modelo;
	}

	protected void setModelo(String modelo) {
		this.modelo = modelo;
	}

	protected String getColor() {
		return color;
	}

	protected void setColor(String color) {
		this.color = color;
	}

	protected String getCombustible() {
		return combustible;
	}

	protected void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	protected String getPlazas() {
		return plazas;
	}

	protected void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	protected double getPrecio() {
		return precio;
	}

	protected void setPrecio(double precio) {
		this.precio = precio;
	}

	protected double getKilometros() {
		return kilometros;
	}

	protected void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}	

}
