package proyecto_programacion;

import java.util.*;

//euro -> \u20ac � -> \u00e1 � -> \u00e9 � -> \u00ed � -> \u00f3 � -> \u00fa � -> \u00f1
public class concesionario {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		String cuenta;
		boolean ciclo= true, confirmacion= false;
		
		ArrayList<vehiculos> automovil= new ArrayList<vehiculos>();
		automovil.add(0, new coches());
		ArrayList<personas> person= new ArrayList<personas>();
		person.add(0, new asesor());
		ArrayList<String> dym= new ArrayList<String>();
		dym.add(0, "DNI&Matricula");
		ArrayList<String> admin= new ArrayList<String>();
		admin.add(0, "verdadero");
		admin.add(1, "verdadero");

		metodos Llamar= new metodos();

		//Llamamos al metodo que mostrara la 
		do {
			do {
				Llamar.MenuPrincipal();
				cuenta= teclado.nextLine();

				//Llamamos metodo que compruebar si la opcion 
				//introducida es un numero.
				if (Llamar.isNumero(cuenta)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
			} while (!confirmacion); //Fin del do

			switch (cuenta) {
			case "1":
				//Llamamos al metodo que mostrara unas opciones si es la
				//primera vez que inicia sesion y otra si ya ha iniciado antes.
				Llamar.CuentaAdmin(admin, person, dym, automovil, teclado);
				
				break;
			case "2":
				//Llamamos al metodo que nos pedira el DNI del empleado y
				//mostrara un menu segun la categoria del empleado (asesor o mecanico)
				Llamar.CuentaEmpleado(admin, person, dym, automovil, teclado);
				
				break;
			case "3":
				//Salimos del programa
				System.out.println(" == Hasta la pr\u00f3xima == ");
				ciclo= false;
				
			default:
				System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
				
			}//Fin del swith

		} while (ciclo); //Fin del do
	}//Fin del main
}//Fin del la clase
