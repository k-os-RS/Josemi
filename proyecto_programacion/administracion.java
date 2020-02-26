package proyecto_programacion;

import java.util.*;

class administracion {
	protected ArrayList<vehiculos> automovil= new ArrayList<vehiculos>();
	protected ArrayList<personas> person= new ArrayList<personas>();
	protected ArrayList<String> admin= new ArrayList<String>();
	protected E_S teclado= new E_S();
	protected comprobaciones comprob= new comprobaciones();

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
						if (dinero >= 50000 ) {
							ciclo= false;
						} else {
							System.out.println("\nError: El dinero base tiene que ser mayor o igual a 50mil euros");
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
		}//Fin del if
	}
	private void CuentaEmpleado() {
		String usuario;
		boolean existe= false;
		//int contador= 2;
			
		if (admin.size() == 0) {
			System.out.println("\nError: La cuenta administrador no ha sido establecida");
		} else {
			System.out.println("\n == INICIO DE SESI\u00f3N EMPLEADO == ");
			System.out.println("");
			System.out.print("Introduzca su DNI: ");
			usuario= teclado.CadenaTexto();

			//Comprobamos que el DNI de la persona existe
			Iterator <personas> itrP = person.iterator();
			while (itrP.hasNext() && !existe) {
				personas p= itrP.next();
				if (p.getDni().equalsIgnoreCase(usuario) && p.getTipo_persona().equals("empleado")) {
					if (p.getDespedida().equals("si")) {
						System.out.println("\nError: Este empleado ha sido despedido");
						existe= true;
					} else {
						if (((asesor) p).getTrabajo_asesor().equals("Asesor")) {
							System.out.println("\n == Asesor: "+p.getNombre()+" "+p.getApellidos()+" == ");
							MenuAsesor();
							existe= true;
						} else if (((mecanico) p).getTrabajo_mecanico().equals("Mecanico")) {
							System.out.println("\n == Mecanico: "+p.getNombre()+" "+p.getApellidos()+" == ");
							MenuMecanico();
							existe= true;
						}//Fin del if
					}//Fin del if
				} else {
					existe= false;
				}//Fin del if
			}//Fin del while
			
			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
			}//Fin del if
			//Segun la verificacion anterior nos dira si existe o no
		}
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
				if (comprob.isNumeroEntero(cuenta)) {
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
				System.out.println(" [9] Mostrar dinero y ganancias");
				System.out.println(" [10] Cerrar sesi\u00f3n");
				System.out.println("");
				System.out.print("Elija una opci\u00f3n: ");
				datos= teclado.CadenaTexto();

				if (comprob.isNumeroEntero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}//Fin del if
				
			} while (!confirmacion);

			switch (datos) {
			case "1":
				AltaEmpleado();
				break;
			case "2":
				BajaEmpleado();
				break;
			case "3":
				MostrarEmpleado();
				break;
			case "4":
				ModificarEmpleado();
				break;
			case "5":
				AltaVehiculo();
				break;
			case "6":
				BajaVehiculo();
				break;
			case "7":
				MostrarVehiculo();
				break;
			case "8":
				ModificarVehiculo();
				break;
			case "9":
				MostrarGanancias();
				break;
			case "10":
				ciclo = false;
				break;
			default:
				System.out.println("\nError: La opci\u00f3n elegida es incorrecta");

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
				System.out.println(" [1] Vender vehiculo");
				System.out.println(" [2] Comprar vehiculo");
				System.out.println(" [3] Mostrar Vehiculo");
				System.out.println(" [4] Devolver Vehiculo");
				System.out.println(" [5] Enviar a mecanico");
				System.out.println(" [6] Mostrar Cliente");
				System.out.println(" [7] Cerrar sesi\u00f3n");
				System.out.println("");
				System.out.print("Elija una opci\u00f3n: ");
				datos= teclado.CadenaTexto();
					
				if (comprob.isNumeroEntero(datos)) {
					confirmacion= true;
				} else {
					confirmacion= false;
				}// Fin del if
					
			} while (!confirmacion);
				
			switch (datos) {
			case "1":
				//VentaVehiculo();
				break;
			case "2":
				//CompraVehiculo();
				break;
			case "3":
				//MostrarVehiculo();
				break;
			case "4":
				//DevolverVehiculo();
				break;
			case "5":
				//EnviarMecanico();
				break;
			case "6":
				//MostrarCliente();
				break;
			case "7":
				ciclo= false;		
				break;
			default:
				System.out.println("\nError: La opci\u00fan elegida es incorrecta");	
				break;
			}//Fin del switch
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
				System.out.println("");
				System.out.print("Elija una opci\u00f3n: ");
				datos= teclado.CadenaTexto();
				
				if (comprob.isNumeroEntero(datos)) {
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
		String tipoEmpleado, datos;
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
				personas m= new mecanico();
				System.out.println("\nPor favor rellene los siguientes datos: ");
				System.out.println("");
				//DNI
				do {
					boolean repetir= true;
					System.out.print("DNI: ");
					datos= teclado.CadenaTexto();
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo DNI");
						repetir= false;
					} else if (comprob.isDNI(datos)) {
						if (person.size() > 0) {
							for (int i= 0; i < person.size(); i++) {
								if (person.get(i).getDni().equalsIgnoreCase(datos) && person.get(i).getTipo_persona().equals("empleado")) {
									System.out.println("\nError: El DNI ya est\u00e1 registrado en un empleado");
									repetir= false;
									i= person.size();
								}//Fin del if
							}//Fin del for i
						} else {
							a.setDni(datos);
							m.setDni(datos);
							correcto= true;
							repetir= false;
						}//Fin del if
					} else {
						System.out.println("\nError: El DNI no es v\u00e1lido");
						repetir= false;
					}//Fin del if
					if (repetir) {
						a.setDni(datos);
						m.setDni(datos);
						correcto= true;
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
					} else if (comprob.isNumeroMovil(datos)) {
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
					} else if (comprob.isFecha(datos)) {
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
					} else if (comprob.isNumeroDecimal(datos)){
						double sueldo= Double.parseDouble(datos);
						if (sueldo < 950) {
							System.out.println("\nError: El sueldo minimo es de \u20ac950");
						} else {
							a.setSueldo(sueldo);
							m.setSueldo(sueldo);
							correcto= true;
						}//Fin del if
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
					m.setTipo_persona("empleado");
					m.setDespedida("no");
					person.add(m);
					cancelar= true;
				}//Fin del if
			} else if (tipoEmpleado.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA EMPLEADO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de empleado no existe");
			}//Fin del if
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
					existe= false;
					i= person.size();
				}//Fin del if
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
				System.out.println("con DNI: "+person.get(j).getDni());
				System.out.println("");
				System.out.println("Pulse INTRO si est\u00e1 seguro o escriba Cancelar, para cancelar la operaci\u00f3n\n");
				dni= teclado.CadenaTexto();
				
				if (dni.equals("")) {
					System.out.println(" == SE HA DADO DE BAJA AL EMPLEADO CON \u00e9XITO == ");
					person.get(j).setDespedida("si");
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
		if (person.size() > 0) {
			System.out.println("\n == MOSTRAR DATOS EMPLEADO == ");
			System.out.println("");
			System.out.print("DNI del empleado: ");
			dni= teclado.CadenaTexto();
			//Comprobar que exista el DNI de la persona
			Iterator <personas> itrP = person.iterator();
			while (itrP.hasNext() && !existe) {
				personas p= itrP.next();
				//Segun la verificacion nos lo mostrara o no
				if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("empleado")) {
					if (p.getDespedida().equals("si")) {
						System.out.println("\n == Empleado despedido == ");
						System.out.println(p);
						System.out.println("\n == Empleado despedido == ");
						existe= true;
					} else {
						System.out.println(p);
						existe= true;
					}
				}else {
					existe= false;
				}//Fin del if
			}//Fin del while

			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
				existe= true;
			}//Fin del if
		} else {
			System.out.println("\nError: No hay ningun empleado contratado");
		}//Fin del if
	}
	protected void ModificarEmpleado () {
		String dni;
		boolean ciclo= true, confirmacion= false, existe= false;
		if (person.size() > 0) {
			System.out.println("\n == MOSTRAR DATOS EMPLEADO == ");
			System.out.println("");
			System.out.print("DNI del empleado: ");
			dni= teclado.CadenaTexto();
			//Comprobar que exista el DNI de la persona
			Iterator <personas> itrP = person.iterator();
			while (itrP.hasNext() && !existe) {
				personas p= itrP.next();
				//Segun la verificacion nos lo mostrara o no
				if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("empleado")) {
					if (p.getDespedida().equals("si")) {
						System.out.println("\nError: Este empleado ha sido despedido");
						do {
							System.out.println("\nDesea recontratar a este empleado");
							System.out.println("Escriba SI en respuesta afirmativa o NO en respuesta negativa");
							dni= teclado.CadenaTexto();
							
							if (dni.equals("")) {
								System.out.println("\nError: Por favor rellene el campo");
							} else if (dni.equalsIgnoreCase("si")) {
								System.out.println("\n == RECONTRATACI\u00f3N ACEPTADA");
								p.setDespedida("no");
							} else if (dni.equalsIgnoreCase("no")) {
								System.out.println("\n == RECONTRATACI\u00f3N CANCELADA == ");
								existe= true;
							} else {
								System.out.println("\nError: Solo se aceptan respuestas SI o NO");
							}
							
						} while (!confirmacion);
					} else {
						do {
							do {
								System.out.println("\nPor favor indique el dato que desea cambiar");
								System.out.println("");
								System.out.println(" [1] DNI");
								System.out.println(" [2] Mombre");
								System.out.println(" [3] Apellidos");
								System.out.println(" [4] Telefono m\u00f3vil");
								System.out.println(" [5] Fecha de nacimiento");
								System.out.println(" [6] Cuenta bancaria");
								System.out.println(" [7] Direccion");
								System.out.println(" [8] Tipo de trabajo");
								System.out.println(" [9] Cancelar");
								System.out.println("");
								System.out.println("Elige una opci\u00f3n: ");
								dni= teclado.CadenaTexto();
								
								if (comprob.isNumeroEntero(dni)) {
									confirmacion= true;
								} else {
									System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
								}
							} while (!confirmacion);
							
							switch (dni) {
							case "1":
								break;
							case "2":
								break;
							case "3":
								break;
							case "4":
								break;
							case "5":
								break;
							case "6":
								break;
							case "7":
								break;
							case "8":
								break;
							case "9":
								break;
							default:
								System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
								break;
							}
							
						} while (ciclo);
						
						existe= true;
					}//Fin del if
				}else {
					existe= false;
				}//Fin del if
			}//Fin del while

			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
				existe= true;
			}//Fin del if
		} else {
			System.out.println("\nError: No hay ningun empleado contratado");
		}//Fin del if
	}
	protected void AltaVehiculo () {
		String tipoVehiculo, datos;
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
					} else if (comprob.isMatricula(datos)) {
						if(automovil.size()==0) {
							c.setMatricula(datos);
							m.setMatricula(datos);
							correcto= true;
						}else {
						for (int i= 0; i < automovil.size(); i++) {
				        	if (automovil.get(i).getMatricula().equalsIgnoreCase(datos)) {
				        		System.out.println("\nError: La matricula ya existe en algun vehiculo");
				        		correcto= false;
				        		i= automovil.size();
				        	} else {
				        		c.setMatricula(datos);
								m.setMatricula(datos);
								correcto= true;
								i= automovil.size();
				        	}//Fin del if
				        }//Fin del for i
						}
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
					} else if (comprob.isCombustible(datos)) {
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
					} else if (comprob.isNumeroEntero(datos)) {
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
	protected void BajaVehiculo () {
		String respuesta, datos;
		boolean cancelar= false, correcto= false;

		System.out.println("\n == BAJA VEHICULO == ");
		System.out.println("Si desea cancelar la baja escriba Cancelar\n");
		System.out.println("Introduzca la matr\u00edcula ");
		
		do {
			do {
				System.out.print("Matr\u00edcula: ");
				datos= teclado.CadenaTexto();
				
				if (datos.equalsIgnoreCase("Cancelar")) {
					System.out.println("\n == BAJA VEHICULO CANCELADA == ");
					cancelar= true;
				} else if (datos.equals("")) {
					System.out.println("\nError: Por favor rellene el campo matr\u00edcula");
				} else if (comprob.isMatricula(datos)) {		
					for (int i= 0; i < automovil.size(); i++) {
			        	if (automovil.get(i).getMatricula().equalsIgnoreCase(datos)) {
			        		System.out.println("\nLa matr\u00edcula es de este veh\u00edculo");
			        		System.out.println(automovil.get(i));
				        	correcto= true;
							cancelar= true;
				        	i= automovil.size();
				        } else {
				        	System.out.println("La matr\u00edcula no existe");
							correcto= false;
							i= automovil.size();
				        }//Fin del if
				       }//Fin del for i
				} else {
					System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
				}//Fin del if

			} while (!correcto);
			correcto= false;
			System.out.println("\nSeguro que desea dar de baja este vehiculo?");
			respuesta=teclado.CadenaTexto();	
			
			if (respuesta.equalsIgnoreCase("si")) {
				System.out.println("\n == LA BAJA DE VEH\u00dedCULO HA SIDO COMPLETADA == ");
				for (int i= 0; i < automovil.size(); i++) {
			       	if (automovil.get(i).getMatricula().equalsIgnoreCase(datos)) {
			       		i= automovil.size();
			       		automovil.remove(i);
				cancelar= true;	
			       	}//Fin del if
			       }//Fin del for i
			} else if (respuesta.equalsIgnoreCase("no")) {					
				cancelar= true;	
			}//Fin del if
		} while (!cancelar);		
	}
	protected void MostrarVehiculo () {
		String respuesta, datos;
		boolean cancelar= false, correcto= false;
		
		do {
			do {
				System.out.println("\n == MOSTRAR VEHICULO == ");
				System.out.println("");
				System.out.println(" [1] Mostrar todos los coches");
				System.out.println(" [2] Mostrar todos las motos");
				System.out.println(" [3] Mostrar un vehiculo espec\u00edfico");
				System.out.println(" [4] Salir");
				System.out.println("");
				System.out.print("Elige una opci\u00f3n: ");
				respuesta= teclado.CadenaTexto();
				
				if(comprob.isNumeroEntero(respuesta)) {
					correcto=true;
				}else {
					correcto=false;
				}//Fin del if
				
			}while(!correcto);

			switch(respuesta) {
			case "1":
				for (vehiculos i : automovil) {
					if(i instanceof coches) {
					System.out.println(i);	
					}
				}
			break;
			case "2":
				for (vehiculos i : automovil) {
					if(i instanceof motos) {
					System.out.println(i);	
					}
				}
			break;
			case "3":
				boolean existe=false;
				System.out.println("Introduzca la matr\u00edcula ");
				System.out.println("Si quiere salir escriba cancelar");
				//Matricula
				do {
					System.out.print("Matr\u00edcula: ");
					datos= teclado.CadenaTexto();
					if (datos.equalsIgnoreCase("Cancelar")) {
						System.out.println("\n == MOSTRAR VEHICULO CANCELADO == ");
						cancelar= true;
					} else if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo matr\u00edcula");		
					} else if (comprob.isMatricula(datos)) {
							
						for (int i= 0; i < automovil.size(); i++) {
					      	if (automovil.get(i).getMatricula().equalsIgnoreCase(datos)) {
					       		System.out.println("\nLa matr\u00edcula es de este veh\u00edculo");
					       		System.out.println(automovil.get(i));
					       		correcto= true;
					       		existe=true;
					       		i= automovil.size();
					       	} else {
					       		if(!existe) {
					       		existe=false;}
								correcto= false;
					       	}//Fin del if
					     }//Fin del for i
						if(!existe) {
						       		System.out.println("La matr\u00edcula no existe");
									correcto= false;
						}
					} else {
						System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
					}//Fin del if
	
					} while (!correcto);	
			break;
			case "4":
				cancelar=true;
				break;
			default:
				System.out.println("Esa opci\u00f3n no es v\u00e1lida");
			break;
			}
		
		}while(!cancelar);
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
	protected void MostrarGanancias () {

		
	}

}
