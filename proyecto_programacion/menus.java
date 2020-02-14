package proyecto_programacion;

import java.util.ArrayList;

class menus {

	ArrayList<vehiculos> automovil= new ArrayList<vehiculos>();
	ArrayList<personas> person= new ArrayList<personas>();
	ArrayList<String> admin= new ArrayList<String>();

	E_S teclado= new E_S();
	administracion Llamar= new administracion();

	//Cuentas de Administrador y Cuenta de Empleado
	private void CuentaAdmin () {
		String usuario= "", password= "", dinerobase= "";
		boolean ciclo= true;
		int contador= 2;

		if (admin.size() == 0) {
			System.out.println("\nBuenas administrador, esta es la primera vez que");
			System.out.println("inicia sesi\u00f3n, por favor rellene los siguientes datos");
			System.out.println("");
			do {
				System.out.print("Nombre de administrador: ");
				teclado.CadenaTexto(usuario);
				System.out.print("Contrase\u00f1a: ");
				teclado.CadenaTexto(password);
				System.out.print("Establezca el dinero base: ");
				teclado.CadenaTexto(dinerobase);
				//Comproba que los datos no esten vacios
				if (usuario.equals("") || password.equals("") || dinerobase.equals("") ) {
					System.out.println("\nError: Nombre de administrador, contrase\u00f1a y/o dinero base no establecidos");
					System.out.println("Por favor vuelva a repetir todo de nuevo\n");
				} else {
					//Comprueba que el dinerobase no sean letras y que no contenga espacios
					if (dinerobase.matches("[0-9]+") || dinerobase.contains(".") || dinerobase.contains(",")) {
						//Reemplaza la coma por un .
						dinerobase= dinerobase.replace(',', '.');
						double dinero= Double.parseDouble(dinerobase);
						if (dinero >= 500000 ) {
							ciclo= false;
						} else {
							System.out.println("\nError: El dinero base tiene que ser mayor o igual a 500mil euros");
							System.out.println("Por favor vuelva a repetir todo de nuevo\n");
						}//Fin del if
						
					} else {
						System.out.println("\nError: El dinero base tiene que ser un n\u00famero");
						System.out.println("Por favor vuelva a repetir todo de nuevo\n");
					}//Fin del if
				}//Fin del if

			} while (ciclo);
			admin.add(usuario); //2
			admin.add(password); //3
			admin.add(dinerobase); //4
			
			System.out.println("\n == Cuenta Administrador creada con \u00e9xito == ");
			System.out.println("");
			System.out.println("Por favor vuelva a seleccionar el tipo de cuenta administrador");
			System.out.println("e inicie sesi\u00f3n con los datos que ha indicado.");
			
		} else {
			System.out.println("\nPor favor indique los siguientes datos: ");
			do {
				System.out.print("\nNombre de usuario: ");
				teclado.CadenaTexto(usuario);
				System.out.print("Contrase\u00f1a: ");
				teclado.CadenaTexto(password);
				
				//Comprueba si nombre de usuario y contrasenia coinciden con el registro.
				if (admin.get(2).equals(usuario) && admin.get(3).equals(password)) {
					ciclo= false;
				} else {
					//Contador de fallos
					if (contador > 0) {
						System.out.println("\nError: El usuario o la contrase\u00f1a son incorrectos");
						contador--;
					} else {
						System.out.println("\n == Has fallado 3 veces, volviendo al menu principal == ");
						ciclo= false;
					}//Fin del if
				}//Fin del if
			} while (ciclo); //Fin del do

			//Se ejecuta si los datos son correctos
			while (!ciclo && contador > 0) {
				System.out.println("\n == Bienvenido Administrador "+usuario+" == ");
				MenuAdmin();
			}//Fin del while
		}
	}
	private void CuentaEmpleado() {
		String usuario= "";
		boolean existe= false;
		//int contador= 2;
		admin.remove(1);
		admin.add(1, "verdadero");
		
		if (admin.get(0).equals("verdadero")) {
			System.out.println("\nError: La cuenta administrador no ha sido establecida");
		} else {
			System.out.println("\n == INICIO DE SESI\u00f3N EMPLEADO == ");
			System.out.println("");
			System.out.print("Introduzca su DNI: ");
			teclado.CadenaTexto(usuario);

			//Comprobamos que el DNI de la persona exista
			for (int i=0; i<person.size(); i++) {
				if (person.get(i).getDni().equals(usuario) && person.get(i).getTipo_persona().equals("empleado")) {
					existe= true;
				} else {
					existe= false;
				}//Fin del if
				
			}//Fin del for i
			
			//Segun la verificacion anterior nos dira si existe o no
			for (int j=0; j<person.size(); j++) {
				if (!existe) {
					System.out.println("\nError: No existe empleado registrado con ese DNI");
					j= person.size();
				} else if (person.get(j).getDni().equals(usuario)) {
					System.out.println(" == BIENVENIDO "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
						if (((asesor) person.get(j)).getTrabajo_asesor().equals("Asesor")) {
							System.out.println("\n == Asesor: "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
							MenuAsesor();
							j= person.size();
						} else if (((asesor) person.get(j)).getTrabajo_asesor().equals("Mecanico")) {
							System.out.println("\n == Mecanico: "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
							MenuMecanico();
							j= person.size();
						}
				}//Fin del if
			}//Fin del for j
		}//Fin del if
	}

	protected void MenuPrincipal () {
		String cuenta= "";
		boolean ciclo= true, confirmacion= false;
		
		do {
			do {
				System.out.println("");
				System.out.println(" == CONCENSIONARIO == ");
				System.out.println("");
				System.out.println("Por favor, a continuaci\u00f3n elija el tipo de cuenta");
				System.out.println("");
				System.out.println(" [1] Administrador");
				System.out.println(" [2] Empleado");
				System.out.println(" [3] Salir");
				System.out.println("");
				System.out.print("Elija la opci\u00f3n: ");
				teclado.CadenaTexto(cuenta);

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
				CuentaAdmin();

				break;
			case "2":
				//Llamamos al metodo que nos pedira el DNI del empleado y
				//mostrara un menu segun la categoria del empleado (asesor o mecanico)
				CuentaEmpleado();

				break;
			case "3":
				//Salimos del programa
				System.out.println(" == Hasta la pr\u00f3xima == ");
				ciclo= false;

			default:
				System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
			
			}//Fin del swith
		} while (ciclo);
	}
	protected void MenuAdmin () {
		String datos= "";
		boolean ciclo= true, confirmacion= false;

		do {
			do {
				System.out.println("");
				System.out.println(" [1] Alta empleado");
				System.out.println(" [2] Baja empleado");
				System.out.println(" [3] Mostrar empleado");
				System.out.println(" [4] Modificar empleado");
				System.out.println(" [5] Alta vehiculo");
				System.out.println(" [6] Baja vehiculo");
				System.out.println(" [7] Mostrar vehiculos");
				System.out.println(" [8] Modificar vehiculo");
				System.out.println(" [9] Cerrar sesi\u00f3n");
				System.out.println("");
				System.out.print("Elija una opci\u00f3n: ");
				teclado.CadenaTexto(datos);

				if (Llamar.isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
			
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				//AltaEmpleado(person);
				break;
			case "2":
				//BajaEmpleado(person);
				break;
			case "3":
				//MostrarEmpleado(person);
				break;
			case "4":
				//ModificarEmpleado(automovil);
				break;
			case "5":
				//AltaVehiculo(automovil);
				break;
			case "6":
				//BajaVehiculo(automovil);
				break;
			case "7":
				//MostrarVehiculo(automovil);
				break;
			case "8":
				//ModificarVehiculo(automovil);
				break;
			case "9":
				ciclo= false;
				admin.remove(1);
				admin.add(1, "verdadero");
				break;
			}
		} while (ciclo);
	}
	protected void MenuAsesor () {
		String datos= "";
		boolean ciclo= true, confirmacion= false;

		do {
			do {
				System.out.println("");
				System.out.println(" [1] Mostrar Cliente");
				System.out.println(" [2] Vender vehiculo");
				System.out.println(" [3] Comprar vehiculo");
				System.out.println(" [4] Mostrar Vehiculo");
				System.out.println(" [5] Devolver Vehiculo");
				System.out.println(" [6] Enviar a mecanico");
				System.out.println(" [7] Cerrar sesi\uf003n");
				System.out.println("");
				System.out.print("Elija una opci\u00fan: ");
				teclado.CadenaTexto(datos);
				
				if (Llamar.isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
				
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				//VentaVehiculo(person, automovil);

				break;
			case "2":
				//CompraVehiculo(person, automovil);
				
				break;
			case "3":
				//MostrarCliente(person);

				break;
			case "4":
				//MostrarCliente(person);

				break;
			case "5":
				ciclo= false;
				
				break;
			default:
				System.out.println("\nError: La opci\u00fan elegida es incorrecta");
				
				break;
			}
			
		}while (ciclo);
	}
	protected void MenuMecanico () {
		String datos= "";
		boolean ciclo= true, confirmacion= false;

		do {
			do {
				System.out.println("");
				System.out.println(" [1] Mostrar Vehiculo");
				System.out.println(" [2] Marcar vehiculo reparado");
				System.out.println(" [3] Vehiculos en reparaci\u00f3n");
				System.out.println(" [4] Cerrar sesi\uf003n");
				teclado.CadenaTexto(datos);
				
				if (Llamar.isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
				
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				//MostrarVehiculo(automovil);
				
				break;
			case "2":
				//RepararVehiculo(automovil);
				
				break;
			case "3":
				//MostrarEnReparacion(automovil);
				
				break;
			case "4":
				ciclo= false;
				
				break;
			default:
				System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
				
				break;
			}
			
		}while (ciclo);
	}

}