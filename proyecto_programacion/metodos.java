package proyecto_programacion;

import java.util.*;
import java.text.*;

class metodos {
	//NOTA: ArrayList admin posicion 0 y 1 reservadas, person, automovil y dym posicion 0 reservadas.
	
	//Menus
	protected void MenuPrincipal () {
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
	}
	private void MenuAdmin (ArrayList<String> admin, ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		String datos;
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
				datos= teclado.nextLine();

				if (isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
			
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				AltaEmpleado(person, teclado);
				break;
			case "2":
				BajaEmpleado(person, teclado);
				break;
			case "3":
				MostrarEmpleado(person, teclado);
				break;
			case "4":
				ModificarEmpleado(person, teclado);
				break;
			case "5":
				AltaVehiculo(automovil, teclado);
				break;
			case "6":
				BajaVehiculo(automovil, teclado);
				break;
			case "7":
				MostrarVehiculo(automovil, teclado);
				break;
			case "8":
				ModificarVehiculo(automovil, teclado);
				break;
			case "9":
				ciclo= false;
				admin.remove(1);
				admin.add(1, "verdadero");
				break;
			}
		} while (ciclo);
	}
	private void MenuAsesor (ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		String datos;
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
				datos= teclado.nextLine();
				
				if (isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
				
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				VentaVehiculo(person, automovil, teclado);

				break;
			case "2":
				CompraVehiculo(person, automovil, teclado);
				
				break;
			case "3":
				MostrarCliente(person, teclado);

				break;
			case "4":
				MostrarCliente(person, teclado);

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
	private void MenuMecanico (ArrayList<vehiculos> automovil, Scanner teclado) {
		String datos;
		boolean ciclo= true, confirmacion= false;

		do {
			do {
				System.out.println("");
				System.out.println(" [1] Mostrar Vehiculo");
				System.out.println(" [2] Marcar vehiculo reparado");
				System.out.println(" [3] Vehiculos en reparaci\u00f3n");
				System.out.println(" [4] Cerrar sesi\uf003n");
				datos= teclado.nextLine();
				
				if (isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
				
			} while (!confirmacion);
			
			switch (datos) {
			case "1":
				MostrarVehiculo(automovil, teclado);
				
				break;
			case "2":
				RepararVehiculo(automovil, teclado);
				
				break;
			case "3":
				MostrarEnReparacion(automovil, teclado);
				
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

	//Cuentas
	protected void CuentaAdmin (ArrayList<String> admin, ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		String usuario, password, dinerobase;
		int contador= 2;
		admin.remove(1);
		admin.add(1, "verdadero");

		if (admin.get(0).equals("verdadero")) {
			System.out.println("\nBuenas administrador, esta es la primera vez que");
			System.out.println("inicia sesi\u00f3n, por favor rellene los siguientes datos");
			System.out.println("");
			do {
				System.out.print("Nombre de administrador: ");
				usuario= teclado.nextLine();
				System.out.print("Contrase\u00f1a: ");
				password= teclado.nextLine();
				System.out.print("Establezca el dinero base: ");
				dinerobase= teclado.nextLine();
				
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
							admin.remove(1);
							admin.add(1, "false");
						} else {
							System.out.println("\nError: El dinero base tiene que ser mayor o igual a 500mil euros");
							System.out.println("Por favor vuelva a repetir todo de nuevo\n");
						}//Fin del if
						
					} else {
						System.out.println("\nError: El dinero base tiene que ser un n\u00famero");
						System.out.println("Por favor vuelva a repetir todo de nuevo\n");
					}//Fin del if
				}//Fin del if

			} while (admin.get(1).equals("verdadero"));
			admin.remove(0);

			admin.add(0, "false");
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
				usuario= teclado.nextLine();
				System.out.print("Contrase\u00f1a: ");
				password= teclado.nextLine();
				
				//Comprueba si nombre de usuario y contrasenia coinciden con el registro.
				if (admin.get(2).equals(usuario) && admin.get(3).equals(password)) {
					admin.remove(1);
					admin.add(1, "false");
				} else {
					//Contador de fallos
					if (contador > 0) {
						System.out.println("\nError: El usuario o la contrase\u00f1a son incorrectos");
						contador--;
					} else {
						System.out.println("\n == Has fallado 3 veces, volviendo al menu principal == ");
						admin.remove(1);
						admin.add(1, "false");
					}//Fin del if
				}//Fin del if
			} while (admin.get(1).equals("verdadero")); //Fin del do

			//Se ejecuta si los datos son correctos
			while (admin.get(1).equals("false") && contador > 0) {
				System.out.println("\n == Bienvenido Administrador "+usuario+" == ");
				MenuAdmin(admin, person, automovil, teclado);
			}//Fin del while
		}
	}
	protected void CuentaEmpleado(ArrayList<String> admin, ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		String usuario;
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
			usuario= teclado.nextLine();

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
							MenuAsesor(person, automovil, teclado);
							j= person.size();
						} else if (((asesor) person.get(j)).getTrabajo_asesor().equals("Mecanico")) {
							System.out.println("\n == Mecanico: "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
							MenuMecanico(automovil, teclado);
							j= person.size();
						}
				}//Fin del if
			}//Fin del for j
		}//Fin del if
	}

	//Altas y Bajas
	private void AltaEmpleado (ArrayList<personas> person, Scanner teclado) {
		String tipoEmpleado, datos;
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA EMPLEADO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de empleado");
		System.out.println("Asesor - Mecanico | Si desea cancelar el alta escriba Cancelar\n");

		do {
			System.out.print("Tipo de empleado: ");
			tipoEmpleado= teclado.nextLine();

			if (tipoEmpleado.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoEmpleado.equalsIgnoreCase("Asesor")) {
				personas a= new asesor();
				a.setTipo_persona("empleado");
				System.out.println("\nRellene los siguientes datos para el/la asesor(a): ");
				System.out.println("");
				//DNI
				do {
					System.out.print("DNI: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo DNI");
					} else if (isDNI(datos)) {
						a.setDni(datos);
						correcto= true;
					} else {
						System.out.println("\nError: El DNI no es v\u00e1lido");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Nombre
				do {
					System.out.print("Nombre: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else {
						a.setNombre(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Apellidos
				do {
					System.out.print("Apellidos: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						a.setApellidos(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Telefono movil
				do {
					System.out.print("Telefono m\u00f3vil: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo telefono m\u00f3vil");
					} else if (isNumeroMovil(datos)) {
						a.setTelefono_movil(datos);
						correcto= true;
					} else {
						correcto= false;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Fecha nacimiento
				do {
					System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo fecha de nacimiento");
					} else if (isFechaNacimiento(datos)) {
						a.setFecha_nacimiento(datos);
						correcto= true;
					} else {
						System.out.println("\nError: La fecha de nacimiento no es v\u00e1lida");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Cuenta bancaria
				do {
					System.out.print("Cuenta bancaria: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo cuenta bancaria");
					} else {
						a.setCuenta_bancaria(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Direccion
				do {
					System.out.print("Direcci\u00f3n: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo direccion");
					} else {
						a.setDireccion(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Sueldo
				do {
					System.out.print("Sueldo: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
						datos= datos.replace(',', '.');
						double sueldo= Double.parseDouble(datos);
						a.setSueldo(sueldo);
						correcto= true;
					} else {
						System.out.println("\nError: El sueldo solo puede contener numeros");
					}//Fin del if

				} while (!correcto);

				System.out.println("\n == EL ALTA DE EMPLEADO HA SIDO COMPLETADA == ");
				person.add(a);
				cancelar= true;

			} else if (tipoEmpleado.equalsIgnoreCase("Mecanico")) {
				personas m= new mecanico();
				m.setTipo_persona("empleado");
				System.out.println("\nRellene los siguientes datos para el/la mecanico(a): ");
				System.out.println("");
				//DNI
				do {
					System.out.print("DNI: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo DNI");
					} else if (isDNI(datos)) {
						m.setDni(datos);
						correcto= true;
					} else {
						System.out.println("\nError: El DNI no es v\u00e1lido");
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Nombre
				do {
					System.out.print("Nombre: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else {
						m.setNombre(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Apellidos
				do {
					System.out.print("Apellidos: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						m.setApellidos(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Telefono movil
				do {
					System.out.print("Telefono m\u00f3vil: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo telefono m\u00f3vil");
					} else if (isNumeroMovil(datos)) {
						m.setTelefono_movil(datos);
						correcto= true;
					} else {
						correcto= false;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Fecha nacimiento
				do {
					System.out.print("Fecha de nacimiento: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo fecha de nacimiento");
					} else if (isFechaNacimiento(datos)) {
						m.setFecha_nacimiento(datos);
						correcto= true;
					} else {
						System.out.println("\nError: La fecha de nacimiento no es v\u00e1lida");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Cuenta bancaria
				do {
					System.out.print("Cuenta bancaria: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo cuenta bancaria");
					} else {
						m.setCuenta_bancaria(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Direccion
				do {
					System.out.print("Direcci\u00f3n: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo direccion");
					} else {
						m.setDireccion(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Sueldo
				do {
					System.out.print("Sueldo: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else if (datos.matches("^[0-9]*$") || datos.matches("[a-zA-Z]+") || datos.contains(" ")){
						System.out.println("\nError: El sueldo solo puede contener numeros");
					} else {
						datos= datos.replace(',', '.');
						double sueldo= Double.parseDouble(datos);
						m.setSueldo(sueldo);
						correcto= true;
					}//Fin del if

				} while (!correcto);

				System.out.println("\n == EL ALTA DE EMPLEADO HA SIDO COMPLETADA == ");
				person.add(m);
				cancelar= true;

			} else if (tipoEmpleado.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA EMPLEADO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de empleado no existe");
			}
			
		} while (!cancelar);
		
	}
	private void BajaEmpleado (ArrayList<personas> person, Scanner teclado) {
		//SIN TERMINAR - HACER COMPROBACIONES
		//Comprobar si la variable i da como resultado el valor de la posicion fuera del for
		//Comprobar que el ArrayList dym no es necesario
		//Si variable i es igual a posicion, borrar for j
		//Pensar de que manera se puede obtener el dni unicamente de un empleado, no cliente
		String dni;
		boolean existe= false;
		
		System.out.println("\n == BAJA EMPLEADO == ");
		System.out.println("");
		System.out.print("DNI del empleado: ");
		dni= teclado.nextLine();

		//Comprobamos que el DNI de la persona exista
		for (int i=0; i<person.size(); i++) {
			if (person.get(i).getDni().equals(dni)) {
				existe= true;
				i= person.size();
			} else {
				existe= false;
			}//Fin del if
			
		}//Fin del for i
		
		//Segun la verificacion anterior nos dira si existe o no
		for (int j=0; j<person.size(); j++) {
			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
				j= person.size();
			} else if (person.get(j).getDni().equals(dni)) {
				System.out.println(" == BIENVENIDO "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
					if (((asesor) person.get(j)).getTrabajo_asesor().equals("Asesor")) {
						System.out.println("\n == Asesor: "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
						
					} else if (((asesor) person.get(j)).getTrabajo_asesor().equals("Mecanico")) {
						System.out.println("\n == Mecanico: "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
						
						j= person.size();
					}
			}//Fin del if
		}//Fin del for j
		
	}
	private void MostrarEmpleado (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void ModificarEmpleado (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void AltaVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
		String tipoVehiculo, datos;
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA VEHICULO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de vehiculo");
		System.out.println("Coche - Moto | Si desea cancelar el alta escriba Cancelar\n");
		System.out.print("Tipo de vehiculo: ");
		tipoVehiculo= teclado.nextLine();
		
		do {
			if (tipoVehiculo.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoVehiculo.equalsIgnoreCase("Coche")) {

				vehiculos c = new coches();
				System.out.println("\nRellene los siguientes datos para el coche: \n");
				//Matricula
				do {
					System.out.print("Matr\u00edcula: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo matr\u00edcula");
					} else if (isMatricula(datos)) {
						c.setMatricula(datos);
						correcto= true;
					} else {
						System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Modelo
				do {
					System.out.print("Modelo: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo modelo");
					} else {
						c.setModelo(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Color
				do {
					System.out.print("Color: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						c.setColor(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Combustible
				do {
					System.out.print("Combustible: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo combustible");
						correcto= false;
					} else {
						c.setCombustible(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//plazas
				do {
					System.out.print("Plazas: ");
					datos= teclado.nextLine();
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo plazas");
					} else if (datos.matches("[0-9]+")) {
						int comprobante=Integer.parseInt(datos);
						if(comprobante>=1) {
							c.setPlazas(datos);
							correcto= true;
						}else {
							System.out.println("La cantidad de plazas debe ser mayor a 1");
						}
					} else {
						System.out.println("\nError: El n\u00famero no es v\u00a1lido");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Kilometros
				do {
					System.out.print("Kilometros: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo kilometros");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
						datos.replace('.',',');
						Double km=Double.parseDouble(datos);
						c.setKilometros(km);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Precio
				do {
					System.out.print("Precio: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo precio");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
							datos.replace('.',',');
							Double precio=Double.parseDouble(datos);
							c.setKilometros(precio);
							correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				
				System.out.println("\n == EL ALTA DE VEHICULO HA SIDO COMPLETADA == ");
				automovil.add(c);
				cancelar= true;

			
			}else if (tipoVehiculo.equalsIgnoreCase("Moto")) {


				vehiculos m = new motos();
				System.out.println("\nRellene los siguientes datos para el moto: \n");
				//Matricula
				do {
					System.out.print("Matr\u00edcula: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo matricula");
					} else if (isMatricula(datos)) {
						m.setMatricula(datos);
						correcto= true;
					} else {
						System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Modelo
				do {
					System.out.print("Modelo: ");
					datos= teclado.nextLine();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo modelo");
					} else {
						m.setModelo(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Color
				do {
					System.out.print("Color: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						m.setColor(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Combustible
				do {
					System.out.print("Combustible: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo combustible");
						correcto= false;
					} else {
						m.setCombustible(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//plazas
				do {
					System.out.print("Plazas: ");
					datos= teclado.nextLine();
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo plazas");
					} else if (datos.matches("[0-9]+")) {
						int comprobante=Integer.parseInt(datos);
						if(comprobante>=1) {
							m.setPlazas(datos);
							correcto= true;
						}else {
							System.out.println("La cantidad de plazas debe ser mayor a 1");
						}
					} else {
						System.out.println("\nError: El n\u00famero no es v\u00e1lido");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Kilometros
				do {
					System.out.print("Kilometros: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo kilometros");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
						datos.replace('.',',');
						Double km=Double.parseDouble(datos);
						m.setKilometros(km);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Precio
				do {
					System.out.print("Precio: ");
					datos= teclado.nextLine();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo direccion");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
							datos.replace('.',',');
							Double precio=Double.parseDouble(datos);
							m.setKilometros(precio);
							correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				
				System.out.println("\n == EL ALTA DE VEHICULO HA SIDO COMPLETADA == ");
				automovil.add(m);
				cancelar= true;

			
			
			}else if (tipoVehiculo.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA EMPLEADO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de empleado no existe");
			}
		}while(!cancelar);
	}
	private void BajaVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void MostrarVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void ModificarVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void CompraVehiculo (ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		//Comprobar que el arraylist person es utilizado en este metodo
	}
	private void VentaVehiculo (ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
		GuardarCliente(person, teclado);
	}
	private void GuardarCliente (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void MostrarCliente (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void MostrarEnReparacion (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void RepararVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}

	//Comprobacion de valides
    protected boolean isDNI (String dni) {  	
        boolean valido = false;
        int caracter= 0, miDNI = 0, resto = 0, i= 0;
        char letra = ' ';
        char[] asigLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        
        //Comprobamos que el DNI introducido tiene una longitud de 9 caracterez y que el ultimo caracter es un letra
        if(dni.length() == 9 && Character.isLetter(dni.charAt(8))) {
        	
        	do {
                caracter = dni.codePointAt(i);
                valido = (caracter > 47 && caracter < 58);
                i++;
            } while(i < dni.length() - 1 && valido);

        }//Fin del if
        
        if(valido) {
        	//Validamos que la letra corresponda al DNI
            letra = Character.toUpperCase(dni.charAt(8));
            miDNI = Integer.parseInt(dni.substring(0,8));
            resto = miDNI % 23;
            valido = (letra == asigLetra[resto]);
        }//Fin del if
        
        return valido;
    }
	protected boolean isNumero (String opcion) {
		boolean verdadero= false;
		
		if (opcion.matches("[0-9]+")) {
			verdadero= true;
		} else {
			System.out.println("\nError: La opcion debe ser un numero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isMatricula (String matricula){
		boolean matriculaValida= false;
		
	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        matriculaValida= true;

	    }else{
	        matriculaValida= false;
	    }
	    
	    return matriculaValida;
	}
	protected boolean isNumeroMovil (String movil) {
		boolean verdadero= false;
		
		if (movil.substring(0, 1).equals("6") && movil.length() == 9){
			verdadero= true;
		} else {
			System.out.println("\nError: La opcion debe ser un numero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isFechaNacimiento (String fecha) {
		boolean valido= true;

		try {
			SimpleDateFormat isFecha= new SimpleDateFormat("dd/mm/yyyy");
			isFecha.setLenient(false);
			isFecha.parse(fecha);
		} catch (ParseException e) {
			valido= false;

		}

		return valido;
	}
}