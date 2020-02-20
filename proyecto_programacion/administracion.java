package proyecto_programacion;

import java.util.*;

class administracion {
	protected ArrayList<vehiculos> automovil= new ArrayList<vehiculos>();
	protected ArrayList<personas> person= new ArrayList<personas>();
	protected ArrayList<String> admin= new ArrayList<String>();
	protected E_S teclado= new E_S();

	//Cuentas de Administrador y Cuenta de Empleado
	private void CuentaAdmin () {
		String usuario, password, dinerobase;
		boolean ciclo= true;
		int contador= 2;

		if (admin.size() == 0) {
			System.out.println("\nBuenas administrador, esta es la primera vez que");
			System.out.println("inicia sesi\u00f3n, por favor rellene los siguientes datos");
			System.out.println("");
			do {
				System.out.print("Nombre de administrador: ");
				usuario= teclado.CadenaTexto();
				System.out.print("Contrase\u00f1a: ");
				password= teclado.CadenaTexto();
				System.out.print("Establezca el dinero base: ");
				dinerobase= teclado.CadenaTexto();
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
			admin.add(usuario); //0
			admin.add(password); //1
			admin.add(dinerobase); //2
				
			System.out.println("\n == Cuenta Administrador creada con \u00e9xito == ");
			System.out.println("");
			System.out.println("Por favor vuelva a seleccionar el tipo de cuenta administrador");
			System.out.println("e inicie sesi\u00f3n con los datos que ha indicado.");
				
			} else {
			System.out.println("\nPor favor indique los siguientes datos: ");

			do {
				System.out.print("\nNombre de usuario: ");
				usuario= teclado.CadenaTexto();
				System.out.print("Contrase\u00f1a: ");
				password= teclado.CadenaTexto();
				
				//Comprueba si nombre de usuario y contrasenia coinciden con el registro.
				if (admin.get(0).equals(usuario) && admin.get(1).equals(password)) {
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
				ciclo= true;
			}//Fin del while
		}
	}
	private void CuentaEmpleado() {
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
			usuario= teclado.CadenaTexto();

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

	//Menus
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
				cuenta= teclado.CadenaTexto();
				//Llamamos metodo que compruebar si la opcion 
				//introducida es un numero.
				if (isNumero(cuenta)) {
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
				break;
			default:
				System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
				break;
				
			}//Fin del swith
		} while (ciclo);
	}
	protected void MenuAdmin () {
		String datos;
		boolean ciclo2= true, confirmacion= false;

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
				datos= teclado.CadenaTexto();

				if (isNumero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
				
			} while (!confirmacion);
				
			switch (datos) {
			case "1":
				AltaEmpleado();
				break;
			case "2":
				BajaEmpleado();
				break;
			case "3":
				//MostrarEmpleado(person);
				break;
			case "4":
				//ModificarEmpleado(automovil);
				break;
			case "5":
				AltaVehiculo();
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
				ciclo2= false;
				break;
			default: 
				System.out.println("\nError: La opci\u00f3n elegida no es v\u00e1lida");
				break;
			}
		}while (ciclo2);
	}
	protected void MenuAsesor () {
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
				datos= teclado.CadenaTexto();
					
				if (isNumero(datos)) {
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
		String datos;
		boolean ciclo= true, confirmacion= false;

		do {
			do {
				System.out.println("");
				System.out.println(" [1] Mostrar Vehiculo");
				System.out.println(" [2] Marcar vehiculo reparado");
				System.out.println(" [3] Vehiculos en reparaci\u00f3n");
				System.out.println(" [4] Cerrar sesi\uf003n");
				datos= teclado.CadenaTexto();
				
				if (isNumero(datos)) {
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

	//Altas y Bajas
	protected void AltaEmpleado () {
		String tipoEmpleado= "", datos= "";
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA EMPLEADO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de empleado");
		System.out.println("Asesor - Mecanico | Si desea cancelar el alta escriba Cancelar\n");

		do {
			System.out.print("Tipo de empleado: ");
			tipoEmpleado= teclado.CadenaTexto();

			if (tipoEmpleado.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoEmpleado.equalsIgnoreCase("asesor") || tipoEmpleado.equalsIgnoreCase("mecanico")) {
				personas a= new asesor();
				personas m= new asesor();
				System.out.println("\nPor favor rellene los siguientes datos: ");
				System.out.println("");
				//DNI
				do {
					System.out.print("DNI: ");
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo DNI");
					} else if (isDNI(datos)) {
						a.setDni(datos);
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
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else {
						a.setNombre(datos);
						m.setNombre(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Apellidos
				do {
					System.out.print("Apellidos: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						a.setApellidos(datos);
						m.setApellidos(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Telefono movil
				do {
					System.out.print("Telefono m\u00f3vil: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo telefono m\u00f3vil");
					} else if (isNumeroMovil(datos)) {
						a.setTelefono_movil(datos);
						m.setTelefono_movil(datos);
						correcto= true;
					} else {
						correcto= false;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Fecha nacimiento
				do {
					System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo fecha de nacimiento");
					} else if (isFecha(datos)) {
						a.setFecha_nacimiento(datos);
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
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo cuenta bancaria");
					} else {
						a.setCuenta_bancaria(datos);
						m.setCuenta_bancaria(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Direccion
				do {
					System.out.print("Direcci\u00f3n: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo direccion");
					} else {
						a.setDireccion(datos);
						m.setDireccion(datos);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Sueldo
				do {
					System.out.print("Sueldo: ");
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo nombre");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
						datos= datos.replace(',', '.');
						double sueldo= Double.parseDouble(datos);
						a.setSueldo(sueldo);
						m.setSueldo(sueldo);
						correcto= true;
					} else {
						System.out.println("\nError: El sueldo solo puede contener numeros");
					}//Fin del if

				} while (!correcto);

				if (tipoEmpleado.equalsIgnoreCase("asesor")) {
					System.out.println("\n == EL ALTA DE ASESOR HA SIDO COMPLETADA == ");
					a.setTipo_persona("empleado");
					a.setDespedida("no");
					person.add(a);
					cancelar= true;
				} else {
					System.out.println("\n == EL ALTA DE MECANICO HA SIDO COMPLETADA == ");
					m.setTipo_persona("mecanico");
					m.setDespedida("no");
					person.add(m);
					cancelar= true;
				}
			} else if (tipoEmpleado.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA EMPLEADO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de empleado no existe");
			}
		} while (!cancelar);
	}
	protected void BajaEmpleado () {
		String dni;
		boolean existe= false;
		
		System.out.println("\n == BAJA EMPLEADO == ");
		System.out.println("");
		System.out.print("DNI del empleado: ");
		dni= teclado.CadenaTexto();
		
		//Comprobamos que el DNI de la persona exista
		for (int i=0; i<person.size(); i++) {
			if (person.get(i).getDni().equals(dni) && person.get(i).getTipo_persona().equals("empleado")) {
				if (person.get(i).getDespedida().equals("no")) {
					existe= true;
					i= person.size();
				} else {
					System.out.println("\nError: El empleado ya ha sido despedido");
				}
				
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
				System.out.println("\nEst\u00e1 por despedir al empleado "+person.get(j).getNombre());
				System.out.println("con DNI "+person.get(j).getDni());
				System.out.println("");
				System.out.println("Pulse INTRO si est\u00e1 seguro o escriba Cancelar, para cancelar la operaci\u00f3n\n");
				dni= teclado.CadenaTexto();
				
				if (dni.equals("")) {
					System.out.println(" == SE HA DADO DE BAJA AL EMPLEADO CON \u00e0XITO == ");
					person.remove(j);
				} else if (dni.equalsIgnoreCase("cancelar")) {
					System.out.println(" == OPERACI\u00f3N CANCELADA == ");
					j= person.size();
				} else {
					System.out.println(" == OPERACI\u00f3N CANCELADA == ");
					j= person.size();
				}
			}//Fin del if
		}//Fin del for j
		
	}
	protected void MostrarEmpleado () {
		String dni;
		boolean existe= false;
		
		System.out.println("\n == MOSTRAR DATOS EMPLEADO == ");
		System.out.println("");
		System.out.print("DNI del empleado: ");
		dni= teclado.CadenaTexto();

		//Comprobamos que el DNI de la persona exista
		for (int i=0; i<person.size(); i++) {
			if (person.get(i).getDni().equals(dni) && person.get(i).getTipo_persona().equals("empleado")) {
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
				person.get(j);
			}//Fin del if
		}//Fin del for j
	}
	protected void AltaVehiculo () {
		
		String tipoVehiculo= "", datos= "";
		Double precio, km;
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA VEHICULO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de vehiculo");
		System.out.println("Coche - Moto | Si desea cancelar el alta escriba Cancelar\n");
		System.out.print("Tipo de vehiculo: ");
		tipoVehiculo= teclado.CadenaTexto();

		do {
			if (tipoVehiculo.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoVehiculo.equalsIgnoreCase("coche") || tipoVehiculo.equalsIgnoreCase("moto")) {
				vehiculos m = new motos();
				vehiculos c = new coches();
				System.out.println("\nPor favor rellene los siguientes datos: \n");
				//Matricula
				do {
					System.out.print("Matr\u00edcula: ");
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo matr\u00edcula");
					} else if (isMatricula(datos)) {
						c.setMatricula(datos);
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
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo modelo");
					} else {
						c.setModelo(datos);
						m.setModelo(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Color
				do {
					System.out.print("Color: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo apellidos");
					} else {
						c.setColor(datos);
						m.setColor(datos);
						correcto= true;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Combustible
				do {
					System.out.print("Combustible: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo combustible");
						correcto= false;
					} else if (isCombustible(datos)) {
						c.setCombustible(datos);
						m.setCombustible(datos);
						correcto= true;
					} else {
						correcto= false;
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//plazas
				do {
					System.out.print("Plazas: ");
					datos= teclado.CadenaTexto();
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo plazas");
					} else if (isNumero(datos)) {
						int comprobante= Integer.parseInt(datos);
						if (comprobante >= 1) {
							c.setPlazas(datos);
							m.setPlazas(datos);
							correcto= true;
						} else {
							System.out.println("\nError: La cantidad de plazas debe ser mayor a 1");
						}
					} else {
						System.out.println("\nError: El n\u00famero no es v\u00a1lido");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Kilometros
				do {
					System.out.print("Kilometros: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo kilometros");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
						datos.replace('.',',');
						km= Double.parseDouble(datos);
						c.setKilometros(km);
						m.setKilometros(km);
						correcto= true;
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Precio
				do {
					System.out.print("Precio: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo precio");
					} else if (datos.matches("[0-9]+") || datos.contains(".") || datos.contains(",")){
							datos.replace('.',',');
							precio= Double.parseDouble(datos);
							c.setPrecio(precio);
							m.setPrecio(precio);
							correcto= true;
					}//Fin del if

				} while (!correcto);

				if (tipoVehiculo.equalsIgnoreCase("coche")) {
					System.out.println("\n == EL ALTA DE COCHE HA SIDO COMPLETADA == ");
					automovil.add(c);
					cancelar= true;	
				} else {
					System.out.println("\n == EL ALTA DE MOTO HA SIDO COMPLETADA == ");
					automovil.add(m);
					cancelar= true;	
				}

			} else if (tipoVehiculo.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA VEHICULO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de empleado no existe");
			}

		} while (!cancelar);		
	}
	protected void ModificarEmpleado () {
		
	}
	protected void BajaVehiculo () {
		
	}
	protected void MostrarVehiculo () {
		
	}
	protected void ModificarVehiculo () {
		
	}
	protected void CompraVehiculo () {
		
	}
	protected void VentaVehiculo () {
		
	}
	protected void GuardarCliente () {
		
	}
	protected void MostrarCliente () {
		
	}
	protected void MostrarEnReparacion () {
		
	}
	protected void RepararVehiculo () {
		
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
        	if (person.size() > 0 ) {
        		for (int j= 0; j < person.size(); j++) {
            		if (person.get(j).getDni().equalsIgnoreCase(dni)) {
            			System.out.println("\nError: Ya existe una persona registrada con ese DNI");
            			j= person.size();
            			valido= false;
            		} else {
            			//Validamos que la letra corresponda al DNI
                        letra = Character.toUpperCase(dni.charAt(8));
                        miDNI = Integer.parseInt(dni.substring(0,8));
                        resto = miDNI % 23;
                        valido = (letra == asigLetra[resto]);
            		}
        		}
        	} else {
        		//Validamos que la letra corresponda al DNI
                letra = Character.toUpperCase(dni.charAt(8));
                miDNI = Integer.parseInt(dni.substring(0,8));
                resto = miDNI % 23;
                valido = (letra == asigLetra[resto]);
        	}
        }//Fin del if
        
        return valido;
    }
    protected boolean isFecha (String fecha) {
		boolean valido= false;
		int[] diasMes= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if (fecha.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
			int dia= Integer.parseInt(fecha.substring(0, 2)); 
			int mes= Integer.parseInt(fecha.substring(3, 5)); 
			int anio= Integer.parseInt(fecha.substring(6, 10)); 

			if (dia > 28 && mes == 2) {
				if (((anio%400 == 0 ) || (anio%100 != 0)) && (anio%4 == 0)) {
					valido= true;
				} else {
					System.out.println("\nError: Este a\u00f1o no es bisiesto");
					valido= false;
				}
			} else {
				valido= dia > 0 && dia <= diasMes[mes - 1];
			}
		} else {
			valido= false;
		}

		return valido;
	}
	protected boolean isNumero (String numero) {
		boolean verdadero= false;
		
		if (numero.matches("[0-9]+")) {
			verdadero= true;
		} else {
			System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isMatricula (String matricula){
		boolean matriculaValida= false;
		
	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        for (int i= 0; i < automovil.size(); i++) {
	        	if (automovil.get(i).getMatricula().equalsIgnoreCase(matricula)) {
	        		System.out.println("\nError: La matricula ya existe en algun vehiculo");
	        		i= automovil.size();
	        		matriculaValida= false;
	        	} else {
	        		matriculaValida= true;
	        	}
	        }

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
	protected boolean isCombustible (String combustible) {
		boolean valido= false;
		
		if (combustible.equalsIgnoreCase("gasolina") || combustible.equalsIgnoreCase("diesel") || combustible.equals("electricidad")) {
			valido= true;
		} else {
			System.out.println("\nError: El tipo de combustible no es v\u00e1lido");
		}
		
		return valido;
	}

}
