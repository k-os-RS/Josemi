
public class Vehiculos {
<<<<<<< Updated upstream
	String Motor,Modelo,Color,Matricula,Combustible,Accesorio;
	Double Precio,Km;
	int Plazas;
	
=======
	//Atributos
		protected String matricula, modelo, color, combustible, plazas;
		protected double precio, kilometros;
		//Constructores
		public Vehiculos(String matricula, String modelo, String color, String combustible, String plazas, double precio,
				double kilometros) {
			this.matricula = matricula;
			this.modelo = modelo;
			this.color = color;
			this.combustible = combustible;
			this.plazas = plazas;
			this.precio = precio;
			this.kilometros = kilometros;
		}

		public Vehiculos () {
			
		}

		
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
>>>>>>> Stashed changes

}
