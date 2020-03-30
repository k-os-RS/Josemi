package proyecto_programacion;
import java.util.*;

class administracion {
	protected ArrayList<vehiculos> automovil= new ArrayList<vehiculos>();
	protected ArrayList<personas> person= new ArrayList<personas>();
	protected ArrayList<venta> vendidos= new ArrayList<venta>();
	protected ArrayList<String> admin= new ArrayList<String>();
	//protected ArrayList<personas> newperson= new ArrayList<personas>();
	protected comprobaciones comprob= new comprobaciones();
	protected E_S teclado= new E_S();
	protected menus menu= new menus();

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
					if (comprob.isNumeroDecimal(dinerobase) || comprob.isNumeroEntero(dinerobase)) {
						//Reemplaza la coma por un .
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
			admin.add("0"); //3 
			admin.add("0"); //4
			admin.add("0"); //5
			admin.add("0"); //6
			admin.add(dinerobase); //7
			admin.add("DNIC"); //8
			admin.add("DNIE"); //9
			
			System.out.println("\n == Cuenta Administrador creada con \u00e9xito == ");
			System.out.println("\nPor favor vuelva a seleccionar el tipo de cuenta administrador");
			System.out.println("e inicie sesi\u00f3n con los datos que ha indicado.");
				
		} else {
			System.out.println("\nPor favor indique los siguientes datos: ");
			do {
				System.out.print("\nNombre de administrador: ");
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
					}//Fin del if.
				}//Fin del if.
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
		String usuario, password;
		boolean existe= false, comprobacion= false;		
		if (admin.size() == 0) {
			System.out.println("\nError: La cuenta administrador no ha sido establecida");
		} else {
			System.out.println("\n == INICIO DE SESI\u00f3N EMPLEADO == ");
			System.out.print("\nIntroduzca su DNI: ");
			usuario= teclado.CadenaTexto();
			for (int i= 0; i < person.size(); i++) {
				if (person.get(i).getDni().equalsIgnoreCase(usuario) && person.get(i).getTipo_persona().equals("empleado")) {
					existe= true;
					if (person.get(i).getDespedida().equals("si")) {
						System.out.println("\nError: Este empleado ha sido despedido");
					} else {
						if (person.get(i) instanceof asesor) {
							if (((asesor) person.get(i)).getPrimerinicio().equals("si")) {
								System.out.println("\nEste es su primer inicio, por favor establezca una contrase\u00f1a");
								System.out.print("Contrase\u00f1a: ");
								password= teclado.CadenaTexto();
								((asesor) person.get(i)).setPrimerinicio("no");
								((asesor) person.get(i)).setPassword(password);
								System.out.println("\n == La contrase\u00f1a se ha establecida con \u00e9xito == ");
								System.out.println("\nPor favor vuelva a seleccionar la opci\u00f3n empleado e ingrese");
								System.out.println("con su DNI y contrase\u00f1a establecida recientemente");
							} else {
								do {
									System.out.print("Contrase\u00f1a: ");
									password= teclado.CadenaTexto();
									if (admin.get(1).equals(password)) {
										admin.remove(9);
										admin.add(9, person.get(i).getDni());
										CambiarPassword();
										comprobacion= true;
									} else if (((asesor) person.get(i)).getPassword().equals(password)) {
										System.out.println("\n == Asesor: "+person.get(i).getNombre()+" "+person.get(i).getApellidos()+" == ");
										MenuAsesor();
										comprobacion= true;
									} else {
										System.out.println("\nError: La contrase\u00f1a establecida es incorrecta");
										System.out.println("Si ha olvidado su contrase\u00f1a por favor contacte con el administrador");
										comprobacion= true;
									}//Fin del if
								} while (!comprobacion);
							}//Fin del if
						} else if (person.get(i) instanceof mecanico) {
							if (((mecanico) person.get(i)).getPrimerinicio().equals("si")) {
								System.out.println("\nEste es su primer inicio, por favor establezca una contrase\u00f1a");
								System.out.print("Contrase\u00f1a: ");
								password= teclado.CadenaTexto();
								((mecanico) person.get(i)).setPrimerinicio("no");
								((mecanico) person.get(i)).setPassword(password);
								System.out.println("\n == La contrase\u00f1a se ha establecida con \u00e9xito == ");
								System.out.println("\nPor favor vuelva a seleccionar la opci\u00f3n empleado e ingrese");
								System.out.println("con su DNI y contrase\u00f1a establecida recientemente");
							} else {
								do {
									System.out.print("Contrase\u00f1a: ");
									password= teclado.CadenaTexto();
									if (((mecanico) person.get(i)).getPassword().equals(password) || admin.get(1).equals(password)) {
										System.out.println("\n == Mec\u00e1nico: "+person.get(i).getNombre()+" "+person.get(i).getApellidos()+" == ");
										MenuMecanico();
										comprobacion= true;
									} else {
										System.out.println("\nError: La contrase\u00f1a establecida es incorrecta");
										System.out.println("Si ha olvidado su contrase\u00f1a por favor contacte con el administrador");
										comprobacion= true;
									}//Fin del if
								} while (!comprobacion);
							}
						}//Fin del if
					}//Fin del if
				}//Fin del if
			}//Fin del for i
			
			//Segun la verificacion anterior nos dira si existe o no
			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
			}//Fin del if
		}//Fin del if
	}
	//Menus
	protected void MenuPrincipal () {
		String cuenta;
		boolean ciclo= true;
		do {
			menu.MenuPrincipal();
			cuenta= teclado.CadenaTexto();
			//Llamamos metodo que compruebar si la opcion 
			//introducida es un numero.
			if (comprob.isNumeroEntero(cuenta)) {
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
			} else {
				System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
			}// Fin del if
		} while (ciclo);
	}
	protected void MenuAdmin () {
		String datos;
		boolean ciclo= true;

		do {
			menu.MenuAdmin();
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\n\n\nError: Por favor elige una opci\u00f3n");
			} else if (comprob.isNumeroEntero(datos)) {
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
				}//Fin del switch
			} else {
				System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
			}//Fin del if
		}while (ciclo);
	}
	protected void MenuAsesor () {
		String datos;
		boolean ciclo= true;

		do {
			menu.MenuAsesor();
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\nError: Por favor elige una opci\u00f3n");
			} else if (comprob.isNumeroEntero(datos)) {
				switch (datos) {
				case "1":
					VenderVehiculo();
					break;
				case "2":
					ComprarVehiculo();
					break;
				case "3":
					MostrarVehiculo();
					break;
				case "4":
					MostrarCliente();
					break;
				case "5":
					ModificarCliente();
					break;
				case "6":
					EnviarMecanico();
					break;
				case "7":
					VentasRealizadas();
					break;
				case "8":
					CambiarPassword();
					break;
				case "9":
					ciclo= false;		
					break;
				default:
					System.out.println("\nError: La opci\u00f3n elegida es incorrecta");	
					break;
				}//Fin del switch
			} else {
				System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
			}// Fin del if			
		}while (ciclo);
	}
	protected void MenuMecanico () {
		String datos;
		boolean ciclo= true;

		do {
			menu.MenuMecanico();
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\nErro: Por favor elige una opci\u00f3n");
			} else if (comprob.isNumeroEntero(datos)) {
				switch (datos) {
				case "1":
					MostrarVehiculo();	
					break;
				case "2":
					RepararVehiculo();
					break;
				case "3":
					MostrarEnReparacion();	
					break;
				case "4":
					CambiarPassword();
					break;
				case "5":
					ciclo= false;		
					break;
				default:
					System.out.println("\nError: La opci\u00f3n elegida es incorrecta");	
					break;
				}//Fin del switch
			} else {
				System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
			}// Fin del if
		} while (ciclo);
	}
	//Metodos generales
	protected void AltaEmpleado () {
		//variables
		String tipoEmpleado, datos;
		boolean cancelar= false, correcto= false;
		personas p = null;
		
		System.out.println("\n == ALTA EMPLEADO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de empleado");
		System.out.println("Asesor - Mecanico | Si desea cancelar el alta escriba Cancelar\n");

		do {
			System.out.print("Tipo de empleado: ");
			tipoEmpleado= teclado.CadenaTexto();

			if (tipoEmpleado.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoEmpleado.equalsIgnoreCase("asesor") || tipoEmpleado.equalsIgnoreCase("mecanico")) {
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
							p.setDni(datos);
							correcto= true;
							repetir= false;
						}//Fin del if
					} else {
						System.out.println("\nError: El DNI no es v\u00e1lido");
						repetir= false;
					}//Fin del if
					if (repetir) {
						p.setDni(datos);
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
						p.setNombre(datos);
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
						p.setApellidos(datos);
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
						p.setTelefono_movil(datos);
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
					} else if (comprob.isFechaNacimiento(datos)) {
						p.setFecha_nacimiento(datos);
						correcto= true;
					} else {
						correcto= false;
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
						p.setCuenta_bancaria(datos);
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
						p.setDireccion(datos);
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
					} else if (comprob.isNumeroDecimal(datos) || comprob.isNumeroEntero(datos)){
						datos= datos.replace(',', '.');
						double sueldo= Double.parseDouble(datos);
						if (sueldo < 950) {
							System.out.println("\nError: El sueldo minimo es de 950\u20ac");
						} else {
							p.setSueldo(sueldo);
							correcto= true;
						}//Fin del if
					} else {
						System.out.println("\nError: El sueldo solo puede contener numeros");
					}//Fin del if
				} while (!correcto);

				if (tipoEmpleado.equalsIgnoreCase("asesor")) {
					System.out.println("\n == EL ALTA DE ASESOR HA SIDO COMPLETADA == ");
					((asesor) p).setPrimerinicio("si");
					((asesor) p).setTrabajo("Asesor");
					p.setTipo_persona("empleado");
					p.setDespedida("no");
					person.add(p);
					cancelar= true;
				} else {
					System.out.println("\n == EL ALTA DE MECANICO HA SIDO COMPLETADA == ");
					((mecanico) p).setPrimerinicio("si");
					((mecanico) p).setTrabajo("Mec\u00e1nico");
					p.setTipo_persona("empleado");
					p.setDespedida("no");
					person.add(p);
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
		//variables
		String dni;
		boolean existe= false, confirmacion= false;
		
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
						System.out.println("\nError: El empleado ya ha sido despedido");
						existe= true;
					} else {
						System.out.println("\nEsta por despedir al empleado");
						System.out.println("DNI: "+p.getDni());
						System.out.println("Nombre: "+p.getNombre());
						System.out.println("Apellidos: "+p.getApellidos());
						do {
							System.out.println("\nSi esta seguro pulse INTRO en caso contrario escriba CANCELAR");
							dni= teclado.CadenaTexto();
							if (dni.equals("")) {
								System.out.println("\n == El empleado ha sido despedido == ");
								p.setDespedida("si");
								confirmacion= true;
							} else if (dni.equalsIgnoreCase("cancelar")) {
								System.out.println("\n == DESPIDO CANCELADO == ");
							} else {
								System.out.println("\nError: Por favor indique la acci\u00f3n correcta.");
							}//Fin del if
							existe= true;
						} while (!confirmacion); //Fin del while
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
	protected void MostrarEmpleado () {
		//variables
		String dni, datos;
		boolean ciclo= true;

		if (person.size() > 0) {
			do {
				menu.MenuMostrarEmpleado();
				datos= teclado.CadenaTexto();
				if (comprob.isNumeroEntero(datos)) {
					switch (datos) {
					case "1":
						for (personas a : person) {
							if (a instanceof asesor) {
								if (a.getDespedida().equals("no")) {
									System.out.println("\n-------------------------");
									System.out.println(a);
								}//Fin del if
							}//Fin del if
						}//Fin del foreach
						break;
					case "2":
						for (personas m : person) {
							if (m instanceof mecanico) {
								if (m.getDespedida().equals("no")) {
									System.out.println("\n-------------------------");
									System.out.println(m);
								}//Fin del if
							}//Fin del if
						}//Fin del foreach
						break;
					case "3":
						boolean existe= false;
						System.out.println("\n == MOSTRAR DATOS DE UN EMPLEADO == ");
						System.out.print("\nDNI del empleado: ");
						dni= teclado.CadenaTexto();
						//Comprobar que exista el DNI de la persona
						if (comprob.isDNI(dni)) {
							for (personas p : person) {
								if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("empleado")) {
									if (p.getDespedida().equals("si")) {
										System.out.println("\n == Empleado despedido == ");
										System.out.println("\n"+p);
										System.out.println("\n == Empleado despedido == ");
										existe= true;
									} else {
										System.out.println("\n"+p);
										existe= true;
									}//Fin del if
								}//Fin del if
							}//Fin del foreach
							if (!existe) {
								System.out.println("\nError: No existe empleado registrado con ese DNI");
							}//Fin del if
						} else {
							System.out.println("\nError: El DNI introducido no es v\u00e1lido");
						}//Fin del if
						break;
					case "4":
						ciclo= false;
						break;
					default:
						System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
						break;
					}//Fin del switch
				} else {
					System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
				}//Fin del if
			} while (ciclo);
		} else {
			System.out.println("\nError: No hay ningun empleado contratado");
		}//Fin del if
	}
	protected void ModificarEmpleado () {
		String dni, datos;
		boolean ciclo= true, repetir= true, confirmacion= false, existe= false;
		if (person.size() > 0) {
			System.out.println("\n == MODIFICAR DATOS DE UN EMPLEADO == ");
			System.out.print("\nDNI del empleado: ");
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
							datos= teclado.CadenaTexto();
							if (datos.equals("")) {
								System.out.println("\nError: Por favor rellene el campo");
							} else if (datos.equalsIgnoreCase("si")) {
								System.out.println("\n == RECONTRATACI\u00f3N ACEPTADA");
								p.setDespedida("no");
							} else if (datos.equalsIgnoreCase("no")) {
								System.out.println("\n == RECONTRATACI\u00f3N CANCELADA == ");
								existe= true;
							} else {
								System.out.println("\nError: Solo se aceptan respuestas SI o NO");
							}//Fin del if
						} while (!confirmacion);
					} else {
						do {
							System.out.println("\n"+p);
							menu.MenuModificarEmpleado();
							datos= teclado.CadenaTexto();
							if (comprob.isNumeroEntero(datos)) {
								for (int i= 0; i < person.size(); i++) {
									if (person.get(i).getDni().equals(dni)) {
										switch (datos) {
										case "1":
											do {
												System.out.print("\nIndique el nuevo DNI: ");
												datos= teclado.CadenaTexto();
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte dato.");
												} else if (comprob.isDNI(datos)) {
													if (person.size() > 0) {
														for (int j= 0; j < person.size(); j++) {
															if (person.get(i).getDni().equalsIgnoreCase(datos) && person.get(i).getTipo_persona().equals("empleado")) {
																System.out.println("\nError: El DNI ya est\u00e1 registrado en un empleado");
																repetir= false;
																i= person.size();
															}//Fin del if
														}//Fin del for i
													} else {
														person.get(i).setDni(datos);
														System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
														confirmacion= true;
													}//Fin del if
												} else {
													System.out.println("\nError: El DNI no es v\u00e1lido.");
												}//Fin del if
												if (repetir) {
													person.get(i).setDni(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "2":
											do {
												System.out.print("\nIndique el nuevo nombre: ");
												datos= teclado.CadenaTexto();
												
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else if (comprob.isNumeroMovil(datos)) {
													person.get(i).setNombre(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												} else {
													confirmacion= false;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "3":
											do {
												System.out.print("\nIndique los nuevos apellidos: ");
												datos= teclado.CadenaTexto();
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else if (comprob.isNumeroMovil(datos)) {
													person.get(i).setApellidos(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												} else {
													confirmacion= false;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "4":
											do {
												System.out.print("\nIndique el nuevo n\u00famero de telefono m\u00f3vil: ");
												datos= teclado.CadenaTexto();
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else if (comprob.isNumeroMovil(datos)) {
													person.get(i).setTelefono_movil(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												} else {
													confirmacion= false;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "5":
											do {
												System.out.print("\nIndique la nueva fecha de nacimiento (dd/mm/yyyy): ");
												datos= teclado.CadenaTexto();	
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else if (comprob.isFechaNacimiento(datos)) {
													person.get(i).setFecha_nacimiento(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												} else {
													confirmacion= false;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "6":
											System.out.print("\nIndique la nueva cuenta bancaria: ");
											datos= teclado.CadenaTexto();
											person.get(i).setCuenta_bancaria(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											i= person.size();
											ciclo= false;
											break;
										case "7":
											do {
												System.out.print("\nIndique la nueva direcci\u00f3n: ");
												datos= teclado.CadenaTexto();
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else {
													person.get(i).setDireccion(datos);
													System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
													confirmacion= true;
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "8":
											do {
												if (person.get(i) instanceof asesor) {
													System.out.println("\nEste empleado es un asesor desea cambiarlo a mec\u00e1nico");
													System.out.println("Pulsa INTRO para aceptar de lo contrario escriba Cancelar para cancelar");
													datos= teclado.CadenaTexto();
													if (datos.equals("")) {
														personas m= new mecanico();
														m.setDni(person.get(i).getDni());
														m.setNombre(person.get(i).getNombre());
														m.setApellidos(person.get(i).getApellidos());
														m.setTelefono_movil(person.get(i).getTelefono_movil());
														m.setFecha_nacimiento(person.get(i).getFecha_nacimiento());
														m.setCuenta_bancaria(person.get(i).getCuenta_bancaria());
														m.setDireccion(person.get(i).getDireccion());
														m.setSueldo(person.get(i).getSueldo());
														m.setTipo_persona("empleado");
														m.setDespedida("no");
														((mecanico) m).setTrabajo("Mec\u00e1nico");
														person.remove(i);
														person.add(i, m);
														System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
														confirmacion= true;
													} else if (datos.equals("cancelar")) {
														System.out.println("\n == MODIFCACI\\u00f3N CANCELADA == ");
														confirmacion= true;
													} else {
														System.out.println("\nError: Por favor indique la acci\u00f3n correcta");
													}//Fin del if
												} else if (person.get(i) instanceof mecanico) {
													System.out.println("\nEste empleado es un asesor desea cambiarlo a mec\u00e1nico");
													System.out.println("Pulsa INTRO para aceptar de lo contrario escriba Cancelar para cancelar");
													datos= teclado.CadenaTexto();
													if (datos.equals("")) {
														personas a= new asesor();
														a.setDni(person.get(i).getDni());
														a.setNombre(person.get(i).getNombre());
														a.setApellidos(person.get(i).getApellidos());
														a.setTelefono_movil(person.get(i).getTelefono_movil());
														a.setFecha_nacimiento(person.get(i).getFecha_nacimiento());
														a.setCuenta_bancaria(person.get(i).getCuenta_bancaria());
														a.setDireccion(person.get(i).getDireccion());
														a.setSueldo(person.get(i).getSueldo());
														a.setTipo_persona("empleado");
														a.setDespedida("no");
														((asesor) a).setTrabajo("Asesor");
														person.remove(i);
														person.add(i, a);
														System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
														confirmacion= true;
													} else if (datos.equals("cancelar")) {
														System.out.println("\n == MODIFCACI\\u00f3N CANCELADA == ");
														confirmacion= true;
													} else {
														System.out.println("\nError: Por favor indique la acci\u00f3n correcta");
													}//Fin del if
												}//Fin del if
											} while (!confirmacion);
											i= person.size();
											ciclo= false;
											break;
										case "9":
											do {
												System.out.print("\nIndique el nuevo sueldo: ");
												datos= teclado.CadenaTexto();
												if (datos.equals("")) {
													System.out.println("\nError: Por favor inserte datos.");
												} else if (comprob.isNumeroDecimal(datos) || comprob.isNumeroEntero(datos)) {
													datos= datos.replace(',', '.');
													double sueldo= Double.parseDouble(datos);
													if (sueldo < 950) {
														System.out.println("\nError: El sueldo minimo es de 950\u20ac");
													} else {
														person.get(i).setSueldo(sueldo);
														System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
														confirmacion= true;	
													}//Fin del if
												} else {
													System.out.println("\nError: Solo se admiten n\u00fameros");
												}//Fin del if
											} while (!confirmacion);
											ciclo= false;
											i= person.size();
											break;
										case "10":
											System.out.println("\n == MODIFICACI\u00f3N CANCELADA == ");
											i= person.size();
											ciclo= false;
											break;
										default:
											System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
											break;
										}//Fin del switch
									}//Fin del if
								}//Fin del for i
							} else {
								System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
							}//Fin del if
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
		//variables
		String tipoVehiculo, datos;
		Double decimal;
		boolean cancelar= false, correcto= false;
		vehiculos v = null;

		System.out.println("\n == ALTA VEHICULO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de vehiculo");
		System.out.println("Coche - Moto | Si desea cancelar el alta escriba Cancelar\n");
		do {
			System.out.print("Tipo de vehiculo: ");
			tipoVehiculo= teclado.CadenaTexto();
			if (tipoVehiculo.equals("")) {
				System.out.println("\nError: Por favor rellene el campo para poder continuar");
			} else if (tipoVehiculo.equalsIgnoreCase("coche") || tipoVehiculo.equalsIgnoreCase("moto")) {
				System.out.println("\nPor favor rellene los siguientes datos: \n");
				//Matricula
				do {
					System.out.print("Matr\u00edcula: ");
					datos= teclado.CadenaTexto();

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo matr\u00edcula");
					} else if (comprob.isMatricula(datos)) {
						if(automovil.size()==0) {
							v.setMatricula(datos);
							correcto= true;
						}else {
							for (int i= 0; i < automovil.size(); i++) {
								if (automovil.get(i).getMatricula().equalsIgnoreCase(datos)) {
									System.out.println("\nError: La matricula ya existe en algun vehiculo");
									correcto= false;
									i= automovil.size();
								} else {
									v.setMatricula(datos);
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
						v.setModelo(datos);
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
						v.setColor(datos);
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
						v.setCombustible(datos);
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
							v.setPlazas(datos);
							correcto= true;
						} else {
							System.out.println("\nError: La cantidad de plazas debe ser mayor a 1");
						}
					} else {
						System.out.println("\nError: El campo solo admite n\u00fameros");
					}//Fin del if

				} while (!correcto);
				correcto= false;
				//Kilometros
				do {
					System.out.print("Kil\u00f3metros: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo kil\u00f3metros");
					} else if (comprob.isNumeroDecimal(datos) || comprob.isNumeroEntero(datos)){
						datos= datos.replace(',', '.');
						decimal= Double.parseDouble(datos);
						v.setKilometros(decimal);
						correcto= true;
					} else {
						System.out.println("\nError: El campo solo admite n\u00fameros");
					}//Fin del if
					
				} while (!correcto);
				correcto= false;
				//Precio
				do {
					System.out.print("Precio: ");
					datos= teclado.CadenaTexto();
					
					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo precio");
					} else if (comprob.isNumeroDecimal(datos) || comprob.isNumeroEntero(datos)){
						datos= datos.replace(',', '.');
						decimal= Double.parseDouble(datos);
						v.setPrecio(decimal);
						correcto= true;
					} else {
						System.out.println("\nError: El campo solo admite n\u00fameros");
					}//Fin del if

				} while (!correcto);
				if (tipoVehiculo.equalsIgnoreCase("coche")) {
					System.out.println("\n == EL ALTA DE COCHE HA SIDO COMPLETADA == ");
					((coches) v).setVendidoCoche("no");
					((coches) v).setEnReparacionCoche("no");
					automovil.add(v);
					cancelar= true;	
				} else {
					System.out.println("\n == EL ALTA DE MOTO HA SIDO COMPLETADA == ");
					((motos) v).setVendidoMoto("no");
					((motos) v).setEnReparacionMoto("no");
					automovil.add(v);
					cancelar= true;	
				}//Fin del if
			} else if (tipoVehiculo.equalsIgnoreCase("Cancelar")) {
				System.out.println("\n == ALTA VEHICULO CANCELADA == ");
				cancelar= true;
			} else {
				System.out.println("\nError: El tipo de veh\u00edculo no existe");
			}//Fin del if
		} while (!cancelar);
	}
	protected void BajaVehiculo () {
		//variables
		String matricula;
		boolean cancelar= false, correcto= false, comprobacion= false;

		System.out.println("\n == BAJA VEHICULO == ");
		System.out.println("Si desea cancelar la baja escriba Cancelar");
		
		do {
			do {
				System.out.print("\nMatr\u00edcula: ");
				matricula= teclado.CadenaTexto();
				
				if (matricula.equalsIgnoreCase("Cancelar")) {
					System.out.println("\n == BAJA VEHICULO CANCELADA == ");
					cancelar= true;
				} else if (matricula.equals("")) {
					System.out.println("\nError: Por favor rellene el campo matr\u00edcula");
				} else if (comprob.isMatricula(matricula)) {		
					for (int i= 0; i < automovil.size(); i++) {
			        	if (automovil.get(i).getMatricula().equalsIgnoreCase(matricula)) {
			        		if (automovil.get(i) instanceof coches) {
			        			if (((coches) automovil.get(i)).getVendidoCoche().equals("no")) {
			        				System.out.println("\nLa matr\u00edcula es de este veh\u00edculo");
					        		System.out.println(automovil.get(i));
					        		comprobacion= true;
						        	correcto= true;
									cancelar= true;
						        	i= automovil.size();
			        			} else {
			        				System.out.println("\nError: El coche ha sido vendido");
			        			}//Fin del if
			        		} else if (automovil.get(i) instanceof motos) {
			        			if (((motos) automovil.get(i)).getVendidoMoto().equals("no")) {
			        				System.out.println("\nLa matr\u00edcula es de este veh\u00edculo");
					        		System.out.println(automovil.get(i));
					        		comprobacion= true;
						        	correcto= true;
									cancelar= true;
						        	i= automovil.size();
			        			} else {
			        				System.out.println("\nError: La moto ha sido vendida");
			        			}//Fin del if
			        		}//Fin del if
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

			if (comprobacion) {
				System.out.println("\nSeguro que desea dar de baja este vehiculo?");
				matricula=teclado.CadenaTexto();	
				
				if (matricula.equalsIgnoreCase("si")) {
					System.out.println("\n == LA BAJA DE VEH\u00dedCULO HA SIDO COMPLETADA == ");
					for (int i= 0; i < automovil.size(); i++) {
				       	if (automovil.get(i).getMatricula().equalsIgnoreCase(matricula)) {
				       		i= automovil.size();
				       		automovil.remove(i);
				       		cancelar= true;	
				       	}//Fin del if
				       }//Fin del for i
				} else if (matricula.equalsIgnoreCase("no")) {
					System.out.println("\n== BAJA VEH\u00edCULO CANCELADA == ");
					cancelar= true;	
				}//Fin del if
			}
		} while (!cancelar);		
	}
	protected void MostrarVehiculo () {
		//variables
		String respuesta, datos;
		boolean cancelar= false, correcto= false;
		
		do {
			do {
				menu.MenuMostrarVehiculo();
				respuesta= teclado.CadenaTexto();
				
				if(comprob.isNumeroEntero(respuesta)) {
					correcto=true;
				}else {
					System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
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
		//variables
		String matricula, datos;
		double decimal;
		boolean ciclo= true, existe= false, repetir= true, confirmacion= false;
		
		System.out.println("\n ==MODIFICACION== ");
		System.out.print("\nIntroduce la matr\u00edcula: ");
		matricula=teclado.CadenaTexto();
		for (int i= 0; i <automovil.size(); i++) {
			if(automovil.get(i).getMatricula().equals(matricula)) {
				do {
					menu.MenuModificarVehiculo();
					datos= teclado.CadenaTexto();				
					if (comprob.isNumeroEntero(datos)) {
						for (int j =0; j <automovil.size(); j++) {
							if(automovil.get(j).getMatricula().equals(matricula)) {
								switch (datos){
								case "1":
									do {
										System.out.print("\nIndique la nueva matr\u00edcula: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte dato.");
										} else if (comprob.isMatricula(datos)) {
											if (automovil.size() > 0) {
												for (int x= 0; x < automovil.size(); x++) {
													if (person.get(x).getDni().equalsIgnoreCase(datos)) {
														System.out.println("\nError: La matr\u00edcula ya est\u00e1 registrada en un veh\u00edculo");
														repetir= false;
														x= automovil.size();
													}//Fin del if
												}//Fin del for i
											} else {
												automovil.get(j).setMatricula(datos);
												System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
												confirmacion= true;
											}//Fin del if
										} else {
											System.out.println("\nError: El DNI no es v\u00e1lido.");
										}//Fin del if
										if (repetir) {
											automovil.get(j).setMatricula(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										}//Fin del if
									} while (!confirmacion);
									j= automovil.size();
									ciclo= false;
									break;
								case "2":
									System.out.print("\nIndique el nuevo modelo: ");
									automovil.get(j).setModelo(teclado.CadenaTexto());
									j= automovil.size();
									ciclo= false;
									break;
								case "3":
									System.out.print("\nIndique el nuevo color: ");
									automovil.get(j).setColor(teclado.CadenaTexto());
									j= automovil.size();
									ciclo= false;
									break;
								case "4":
									System.out.print("\nIndique el combustible: ");
									datos= teclado.CadenaTexto();
									if(comprob.isCombustible(datos))
										automovil.get(j).setCombustible(datos);
									j= automovil.size();
									ciclo= false;
									break;
								case "5":
									do {
										System.out.print("\nIndique las plazas: ");
										datos=teclado.CadenaTexto();
										if(comprob.isNumeroEntero(datos)) {
											automovil.get(j).setPlazas(datos);
											confirmacion= true;
										} else {
											System.out.println("\nError: La opci\u00f3n solo admite n\u00faumero");
										}//Fin del if
									} while (!confirmacion);
									j= automovil.size();
									ciclo= false;
									break;
								case "6":
									do {
										System.out.print("\nIndique el precio: ");
										datos=teclado.CadenaTexto();
										if(comprob.isNumeroEntero(datos) || comprob.isNumeroDecimal(datos)) {
											datos= datos.replace(',', '.');
											decimal= Double.parseDouble(datos);
											automovil.get(j).setPrecio(decimal);
										} else {
											System.out.println("\nError: La opci\u00f3n solo admite n\u00faumero");
										}//Fin del if
									} while (!confirmacion);
									j= automovil.size();
									ciclo= false;
									break;
								case "7":
									do {
										System.out.println("\nIndique los kil\u00f3metros");
										datos=teclado.CadenaTexto();
										if(comprob.isNumeroEntero(datos) || comprob.isNumeroDecimal(datos)) {
											datos= datos.replace(',', '.');
											decimal= Double.parseDouble(datos);
											automovil.get(j).setKilometros(decimal);
											confirmacion= true;
										} else {
											System.out.println("\nError: debe ser un numero");
										}//Fin del if
									} while (!confirmacion);
									j= automovil.size();
									ciclo= false;
									break;
								case "8":
									j= automovil.size();
									ciclo= false;
									break;
								default:
									System.out.println("Error: esa opci\u00f3n no es valida");
									break;
								}//Fin del switch
							}//Fin del if
						}//Fin del for j
					}else {
						System.out.println("\nError: La opci\u00f3 solo admite n\u00fameros");
					}//Fin del if
				} while(ciclo);
				existe= true;
			}//Fin del if
		}//Fin del for i
		
		if (!existe) {
			System.out.println("\nError: La matr\u00edcula no est\u00e1 registrada en ningun veh\u00edculo");
		}//Fin del if
	}
	protected void VenderVehiculo () {
		//variables
		ArrayList<vehiculos> auto= new ArrayList<vehiculos>();
		String matricula, dni, datos;
		int contadorC= 0, contadorM= 0, cantidad, cantidad2;
		double dinerototal, precioC, precioM;
		boolean ciclo= true, confirmacion= false, compra= true, comprobacion= false;
		
		if (automovil.size() > 0) {
			System.out.println("\n == VENTA DE UN VEH\u00edCULO == ");
			System.out.println("Si desea cancelar la venta escriba Cancelar");
			do {
				System.out.print("\nMatr\u00edcula: ");
				matricula= teclado.CadenaTexto();
				if (matricula.equals("")) {
					System.out.println("\nError: Por favor rellene datos");
				} else if (matricula.equalsIgnoreCase("cancelar")) {
					System.out.println("\n == VENTA CANCELADA == ");
				} else if (comprob.isMatricula(matricula)) {
					for (vehiculos v : automovil) {
						if (v.getMatricula().equalsIgnoreCase(matricula)) {
							if (v instanceof coches) {
								if (((coches) v).getVendidoCoche().equals("no")) {
									do {
										System.out.println("\n"+v);
										System.out.println("\nQuien realizar\u00e1 la compra?");
										System.out.println("");
										System.out.println(" [1] Cliente Nuevo");
										System.out.println(" [2] Cliente habitual");
										System.out.println(" [3] Cancelar");
										System.out.println("");
										System.out.print("Opci\u00f3n: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor seleccione un opci\u00f3n");
										} else if (comprob.isNumeroEntero(datos)) {
											switch (datos) {
											case "1":
												AltaCliente();
												for (personas p : person) {
													if (p.getDni().equalsIgnoreCase(admin.get(8)) && p.getTipo_persona().equals("cliente")) {
														System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matricula "+v.getMatricula());
														System.out.println("Para continar pulse INTRO si desea elegir otro cliente escriba cualquier cosa");
														datos= teclado.CadenaTexto();
														if (datos.equals("")) {
															venta vender= new venta();
															contadorC++;
															precioC= Double.parseDouble(admin.get(3));
															precioM= Double.parseDouble(admin.get(4));
															dinerototal= Double.parseDouble(admin.get(7));
															precioC= precioC + v.getPrecio();
															vender.setCl(((cliente) p));
															((coches) v).setVendidoCoche("si");
															auto.add(((coches) v));
															do {
																System.out.println("Desea comprar otro veh\u00edculo\n");
																menu.MenuConfirmacion();
																datos= teclado.CadenaTexto();
																if (datos.equals("")) {
																	System.out.println("\nError: Por favor rellena el campo");
																} else if (comprob.isNumeroEntero(datos)) {
																	switch (datos) {
																	case "1":
																		comprobacion= false;
																		do {
																			System.out.println("\nMatr\u00edcula:");
																			matricula= teclado.CadenaTexto();
																			if (matricula.equals("")) {
																				System.out.println("\nError: Por favor rellene datos");
																			} else if (comprob.isMatricula(matricula)) {
																				for (vehiculos v2 : automovil) {
																					if (v2.getMatricula().equalsIgnoreCase(matricula)) {
																						if (v2 instanceof coches) {
																							if (((coches) v2).getVendidoCoche().equals("no")) {
																								System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matr\u00edcula "+v2.getMatricula());
																								System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																								if (datos.equals("")) {
																									((coches) v2).setVendidoCoche("si");
																									precioC= precioC + v2.getPrecio();
																									auto.add(((coches) v2));
																									contadorC++;
																								} else {
																									System.out.println("\n == VENTA CANCELADA ==");
																									comprobacion= true;
																								}//Fin del if
																							} else {
																								System.out.println("\nError: Este coche ya ha sido vendido");
																							}//Fin del if
																						} else if (v2 instanceof motos) {
																							if (((motos) v2).getVendidoMoto().equals("no")) {
																								System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 la moto con matr\u00edcula "+v2.getMatricula());
																								System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																								if (datos.equals("")) {
																									((coches) v2).setVendidoCoche("si");
																									precioM= precioM + v2.getPrecio();
																									auto.add(((coches) v2));
																									contadorM++;
																								} else {
																									System.out.println("\n == VENTA CANCELADA ==");
																									comprobacion= true;
																								}//Fin del if
																							} else {
																								System.out.println("\nError: Esta mota ya ha sido vendida");
																							}//Fin del if
																						}//Fin del if
																					}//Fin del if
																				}//Fin del foreach
																			} else {
																				System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
																			}//Fin de if
																		} while (!comprobacion);
																		break;
																	case "2":
																		cantidad= ((cliente) p).getCompraCoche();
																		cantidad2= ((cliente) p).getCompraMoto();
																		((cliente) p).setCompraCoche(cantidad+contadorC);
																		((cliente) p).setCompraMotos(cantidad2+contadorM);
																		dinerototal= precioC + precioM;
																		admin.remove(3);
																		admin.remove(4);
																		admin.remove(7);
																		admin.add(3, String.valueOf(precioC));
																		admin.add(4, String.valueOf(precioM));
																		admin.add(7, String.valueOf(dinerototal));
																		vender.setAuto(auto);
																		vender.setFechaventa(comprob.isFechaActual());
																		vender.setHoraventa(comprob.isHoraActual());
																		vendidos.add(vender);
																		System.out.println("\n ===== RESUMEN DE LA VENTA =====");
																		System.out.println("\nCliente: ");
																		System.out.println(" DNI: "+p.getDni());
																		System.out.println(" Nombre y apellidos: "+p.getNombre()+" "+p.getApellidos());
																		if (((cliente) p).getCompraCoche() > 0) {
																			System.out.println("\nCoches comprados: "+((cliente) p).getCompraCoche());
																			System.out.println("\n-------------------------");
																			for (int i= 0; i < auto.size(); i++) {
																				if (auto.get(i) instanceof coches) {
																					System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																					System.out.println("   Modelo: "+auto.get(i).getModelo());
																					System.out.println("-------------------------");
																				}//Fin del if
																			}//Fin del for i
																		} else {
																			System.out.println("\nCoches comprados: 0");
																		}//Fin del if
																		if (((cliente) p).getCompraMoto() > 0) {
																			System.out.println("\nMotos compradas: "+((cliente) p).getCompraMoto());
																			System.out.println("\n-------------------------");
																			for (int i= 0; i < auto.size(); i++) {
																				if (auto.get(i) instanceof motos) {
																					System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																					System.out.println("   Modelo: "+auto.get(i).getModelo());
																					System.out.println("-------------------------");
																				}//Fin del if
																			}//Fin del for i
																		} else {
																			System.out.println("\nMotos compradas: 0");
																		}//Fin del if
																		System.out.println("\nVenta realizada el d\u00eda "+comprob.isFechaActual()+" a las "+comprob.isHoraActual());
																		System.out.println("\n == VENTA REALIZADA ==");
																		compra= false;
																		confirmacion= true;
																		ciclo= false;
																		break;
																	default:
																		System.out.println("\nError La opci\u00f3n elegida es incorrecta");
																		break;
																	}//Fin del switch
																} else {
																	System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
																}//Fin del if
															} while (compra);
														} else {
															System.out.println("\nRegresando...");
														}//Fin del if
													}//Fin de if
												}//Fin del foreach
												break;
											case "2":
												if (person.size() > 0) {
													do {
														System.out.println("\nDNI del cliente");
														dni= teclado.CadenaTexto();
														if (dni.equals("")) {
															System.out.println("\nError: Por favor rellene el campo");
														} else if (comprob.isDNI(dni)) {
															for (personas p : person) {
																if (p.getDni().equals(dni) && p.getTipo_persona().equals("cliente")) {
																	System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matricula "+v.getMatricula());
																	System.out.println("Para continar pulse INTRO si desea elegir otro cliente escriba cualquier cosa");
																	datos= teclado.CadenaTexto();
																	if (datos.equals("")) {
																		venta vender= new venta();
																		contadorC++;
																		vender.setCl(((cliente) p));
																		((coches) v).setVendidoCoche("si");
																		auto.add(((coches) v));
																		do {
																			System.out.println("\nDesea comprar otro veh\u00edculo\n");
																			menu.MenuConfirmacion();
																			datos= teclado.CadenaTexto();
																			if (datos.equals("")) {
																				System.out.println("\nError: Por favor rellena el campo");
																			} else if (comprob.isNumeroEntero(datos)) {
																				switch (datos) {
																				case "1":
																					comprobacion= false;
																					do {
																						System.out.println("\nMatr\u00edcula:");
																						matricula= teclado.CadenaTexto();
																						if (matricula.equals("")) {
																							System.out.println("\nError: Por favor rellene datos");
																						} else if (comprob.isMatricula(matricula)) {
																							for (vehiculos v2 : automovil) {
																								if (v.getMatricula().equalsIgnoreCase(matricula)) {
																									if (v2 instanceof coches) {
																										System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matricula "+v2.getMatricula());
																										System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																										if (datos.equals("")) {
																											((coches) v2).setVendidoCoche("si");
																											auto.add(((coches) v2));
																											contadorC++;
																										} else {
																											System.out.println("\n == VENTA CANCELADA ==");
																											comprobacion= true;
																										}//Fin del if
																									} else if (v2 instanceof motos) {
																										System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar la moto con matricula "+v.getMatricula());
																									}//Fin del if
																								}//Fin del if
																							}//Fin del foreach
																						} else {
																							System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
																						}//Fin de if
																					} while (!comprobacion);
																					break;
																				case "2":
																					cantidad= ((cliente) p).getCompraCoche();
																					((cliente) p).setCompraCoche(cantidad+contadorC);
																					vender.setAuto(auto);
																					vender.setFechaventa(comprob.isFechaActual());
																					vender.setHoraventa(comprob.isHoraActual());
																					vendidos.add(vender);
																					System.out.println("\n ===== RESUMEN DE LA VENTA =====");
																					System.out.println("\nCliente: ");
																					System.out.println(" DNI: "+p.getDni());
																					System.out.println(" Nombre y apellidos: "+p.getNombre()+" "+p.getApellidos());
																					if (((cliente) p).getCompraCoche() > 0) {
																						System.out.println("\nCoches comprados: "+((cliente) p).getCompraCoche());
																						System.out.println("\n-------------------------");
																						for (int i= 0; i < auto.size(); i++) {
																							if (auto.get(i) instanceof coches) {
																								System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																								System.out.println("   Modelo: "+auto.get(i).getMatricula());
																								System.out.println("-------------------------");
																							}//Fin del if
																						}//Fin del foreach
																					} else {
																						System.out.println("\nCoches comprados: 0");
																					}//Fin del if
																					if (((cliente) p).getCompraMoto() > 0) {
																						System.out.println("\nMotos compradas: "+((cliente) p).getCompraCoche());
																						System.out.println("\n-------------------------");
																						for (int i= 0; i < auto.size(); i++) {
																							if (auto.get(i) instanceof motos) {
																								System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																								System.out.println("   Modelo: "+auto.get(i).getMatricula());
																								System.out.println("-------------------------");
																							}//Fin del if
																						}//Fin del foreach
																					} else {
																						System.out.println("\nMotos compradas: 0");
																					}//Fin del if
																					System.out.println("\nVenta realizada el d\u00eda "+comprob.isFechaActual()+" a las "+comprob.isHoraActual());
																					System.out.println("\n == VENTA REALIZADA ==");
																					compra= false;
																					confirmacion= true;
																					comprobacion= true;
																					ciclo= false;
																					break;
																				default:
																					System.out.println("\nError La opci\u00f3n elegida es incorrecta");
																					break;
																				}//Fin del switch
																			} else {
																				System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
																			}//Fin del if
																		} while (compra);
																	} else {
																		System.out.println("\nRegresando...");
																	}//Fin del if
																}//Fin de if
															}//Fin del foreach
														} else {
															System.out.println("\nError: Ese DNI no es v\u00e1lido");
														}//Fin del if
													} while (!comprobacion);
												} else {
													System.out.println("\nError: No hay clientes registrados");
												}//Fin del if
												break;
											case "3":
												System.out.println("\n== VENTA CANCELADA == ");
												confirmacion= true;
												ciclo= false;
												break;
											default:
												System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
												break;
											}//Fin del switch
										} else {
											System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
										}//Fin del if
									} while (!confirmacion);
								} else {
									System.out.println("\nError: Este coche ya ha sido vendido");
								}//Fin del if
							} else if (v instanceof motos) {
								if (((motos) v).getVendidoMoto().equals("no")) {
									do {
										System.out.println("\n"+v);
										System.out.println("\nQuien realizar\u00e1 la compra?");
										System.out.println("");
										System.out.println(" [1] Cliente Nuevo");
										System.out.println(" [2] Cliente habitual");
										System.out.println(" [3] Cancelar");
										System.out.println("");
										System.out.print("Opci\u00f3n: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor seleccione un opci\u00f3n");
										} else if (comprob.isNumeroEntero(datos)) {
											switch (datos) {
											case "1":
												AltaCliente();
												for (personas p : person) {
													if (p.getDni().equalsIgnoreCase(admin.get(8)) && p.getTipo_persona().equals("cliente")) {
														System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el moto con matricula "+v.getMatricula());
														System.out.println("Para continar pulse INTRO si desea elegir otro cliente escriba cualquier cosa");
														datos= teclado.CadenaTexto();
														if (datos.equals("")) {
															venta vender= new venta();
															contadorC++;
															precioC= Double.parseDouble(admin.get(3));
															precioM= Double.parseDouble(admin.get(4));
															dinerototal= Double.parseDouble(admin.get(7));
															precioM= precioM + v.getPrecio();
															vender.setCl(((cliente) p));
															((motos) v).setVendidoMoto("si");
															auto.add(((motos) v));
															do {
																System.out.println("Desea comprar otro veh\u00edculo\n");
																menu.MenuConfirmacion();
																datos= teclado.CadenaTexto();
																if (datos.equals("")) {
																	System.out.println("\nError: Por favor rellena el campo");
																} else if (comprob.isNumeroEntero(datos)) {
																	switch (datos) {
																	case "1":
																		comprobacion= false;
																		do {
																			System.out.println("\nMatr\u00edcula:");
																			matricula= teclado.CadenaTexto();
																			if (matricula.equals("")) {
																				System.out.println("\nError: Por favor rellene datos");
																			} else if (comprob.isMatricula(matricula)) {
																				for (vehiculos v2 : automovil) {
																					if (v2.getMatricula().equalsIgnoreCase(matricula)) {
																						if (v2 instanceof coches) {
																							if (((coches) v2).getVendidoCoche().equals("no")) {
																								System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matr\u00edcula "+v2.getMatricula());
																								System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																								if (datos.equals("")) {
																									((coches) v2).setVendidoCoche("si");
																									precioC= precioC + v2.getPrecio();
																									auto.add(((coches) v2));
																									contadorC++;
																								} else {
																									System.out.println("\n == VENTA CANCELADA ==");
																									comprobacion= true;
																								}//Fin del if
																							} else {
																								System.out.println("\nError: Este coche ya ha sido vendido");
																							}//Fin del if
																						} else if (v2 instanceof motos) {
																							if (((motos) v2).getVendidoMoto().equals("no")) {
																								System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 la moto con matr\u00edcula "+v2.getMatricula());
																								System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																								if (datos.equals("")) {
																									((coches) v2).setVendidoCoche("si");
																									precioM= precioM + v2.getPrecio();
																									auto.add(((coches) v2));
																									contadorM++;
																								} else {
																									System.out.println("\n == VENTA CANCELADA ==");
																									comprobacion= true;
																								}//Fin del if
																							} else {
																								System.out.println("\nError: Esta mota ya ha sido vendida");
																							}//Fin del if
																						}//Fin del if
																					}//Fin del if
																				}//Fin del foreach
																			} else {
																				System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
																			}//Fin de if
																		} while (!comprobacion);
																		break;
																	case "2":
																		cantidad= ((cliente) p).getCompraCoche();
																		cantidad2= ((cliente) p).getCompraMoto();
																		((cliente) p).setCompraCoche(cantidad+contadorC);
																		((cliente) p).setCompraMotos(cantidad2+contadorM);
																		dinerototal= precioC + precioM;
																		admin.remove(3);
																		admin.remove(4);
																		admin.remove(7);
																		admin.add(3, String.valueOf(precioC));
																		admin.add(4, String.valueOf(precioM));
																		admin.add(7, String.valueOf(dinerototal));
																		vender.setAuto(auto);
																		vender.setFechaventa(comprob.isFechaActual());
																		vender.setHoraventa(comprob.isHoraActual());
																		vendidos.add(vender);
																		System.out.println("\n ===== RESUMEN DE LA VENTA =====");
																		System.out.println("\nCliente: ");
																		System.out.println(" DNI: "+p.getDni());
																		System.out.println(" Nombre y apellidos: "+p.getNombre()+" "+p.getApellidos());
																		if (((cliente) p).getCompraCoche() > 0) {
																			System.out.println("\nCoches comprados: "+((cliente) p).getCompraCoche());
																			System.out.println("\n-------------------------");
																			for (int i= 0; i < auto.size(); i++) {
																				if (auto.get(i) instanceof coches) {
																					System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																					System.out.println("   Modelo: "+auto.get(i).getModelo());
																					System.out.println("-------------------------");
																				}//Fin del if
																			}//Fin del foreach
																		} else {
																			System.out.println("\nCoches comprados: 0");
																		}//Fin del if
																		if (((cliente) p).getCompraMoto() > 0) {
																			System.out.println("\nMotos compradas: "+((cliente) p).getCompraMoto());
																			System.out.println("\n-------------------------");
																			for (int i= 0; i < auto.size(); i++) {
																				if (auto.get(i) instanceof motos) {
																					System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																					System.out.println("   Modelo: "+auto.get(i).getModelo());
																					System.out.println("-------------------------");
																				}//Fin del if
																			}//Fin del foreach
																		} else {
																			System.out.println("\nMotos compradas: 0");
																		}//Fin del if
																		System.out.println("Venta realizada el d\u00eda "+comprob.isFechaActual()+" a las "+comprob.isHoraActual());
																		System.out.println("\n == VENTA REALIZADA ==");
																		compra= false;
																		confirmacion= true;
																		ciclo= false;
																		break;
																	default:
																		System.out.println("\nError La opci\u00f3n elegida es incorrecta");
																		break;
																	}//Fin del switch
																} else {
																	System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
																}//Fin del if
															} while (compra);
														} else {
															System.out.println("\nRegresando...");
														}//Fin del if
													}//Fin de if
												}//Fin del foreach
												break;
											case "2":
												do {
													System.out.println("\nDNI del cliente");
													dni= teclado.CadenaTexto();
													if (dni.equals("")) {
														System.out.println("\nError: Por favor rellene el campo");
													} else if (comprob.isDNI(dni)) {
														for (personas p : person) {
															if (p.getDni().equals(dni) && p.getTipo_persona().equals("cliente")) {
																System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el moto con matricula "+v.getMatricula());
																System.out.println("Para continar pulse INTRO si desea elegir otro cliente escriba cualquier cosa");
																datos= teclado.CadenaTexto();
																if (datos.equals("")) {
																	venta vender= new venta();
																	contadorC++;
																	vender.setCl(((cliente) p));
																	((motos) v).setVendidoMoto("si");
																	auto.add(((motos) v));
																	do {
																		System.out.println("\nDesea comprar otro veh\u00edculo\n");
																		menu.MenuConfirmacion();
																		datos= teclado.CadenaTexto();
																		if (datos.equals("")) {
																			System.out.println("\nError: Por favor rellena el campo");
																		} else if (comprob.isNumeroEntero(datos)) {
																			switch (datos) {
																			case "1":
																				comprobacion= false;
																				do {
																					System.out.println("\nMatr\u00edcula:");
																					matricula= teclado.CadenaTexto();
																					if (matricula.equals("")) {
																						System.out.println("\nError: Por favor rellene datos");
																					} else if (comprob.isMatricula(matricula)) {
																						for (vehiculos v2 : automovil) {
																							if (v.getMatricula().equalsIgnoreCase(matricula)) {
																								if (v2 instanceof coches) {
																									System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar\u00e1 el coche con matricula "+v2.getMatricula());
																									System.out.println("Pulse INTRO para continuar o escriba cualquier cosa para cancelar");
																									if (datos.equals("")) {
																										((coches) v2).setVendidoCoche("si");
																										auto.add(((coches) v2));
																										contadorC++;
																									} else {
																										System.out.println("\n == VENTA CANCELADA ==");
																										comprobacion= true;
																									}//Fin del if
																								} else if (v2 instanceof motos) {
																									System.out.println("\nEl cliente con DNI "+p.getDni()+" comprar la moto con matricula "+v.getMatricula());
																								}//Fin del if
																							}//Fin del if
																						}//Fin del foreach
																					} else {
																						System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
																					}//Fin de if
																				} while (!comprobacion);
																				break;
																			case "2":
																				cantidad= ((cliente) p).getCompraCoche();
																				((cliente) p).setCompraCoche(cantidad+contadorC);
																				vender.setAuto(auto);
																				vender.setFechaventa(comprob.isFechaActual());
																				vender.setHoraventa(comprob.isHoraActual());
																				vendidos.add(vender);
																				System.out.println("\n ===== RESUMEN DE LA VENTA =====");
																				System.out.println("\nCliente: ");
																				System.out.println(" DNI: "+p.getDni());
																				System.out.println(" Nombre y apellidos: "+p.getNombre()+" "+p.getApellidos());
																				if (((cliente) p).getCompraCoche() > 0) {
																					System.out.println("\nCoches comprados: "+((cliente) p).getCompraCoche());
																					System.out.println("\n-------------------------");
																					for (int i= 0; i < auto.size(); i++) {
																						if (auto.get(i) instanceof coches) {
																							System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																							System.out.println("   Modelo: "+auto.get(i).getMatricula());
																							System.out.println("-------------------------");
																						}//Fin del if
																					}//Fin del foreach
																				} else {
																					System.out.println("\nCoches comprados: 0");
																				}//Fin del if
																				if (((cliente) p).getCompraMoto() > 0) {
																					System.out.println("\nMotos compradas: "+((cliente) p).getCompraCoche());
																					System.out.println("\n-------------------------");
																					for (int i= 0; i < auto.size(); i++) {
																						if (auto.get(i) instanceof motos) {
																							System.out.println(" * Matricula: "+auto.get(i).getMatricula());
																							System.out.println("   Modelo: "+auto.get(i).getMatricula());
																							System.out.println("-------------------------");
																						}//Fin del if
																					}//Fin del foreach
																				} else {
																					System.out.println("\nMotos compradas: 0");
																				}//Fin del if
																				System.out.println("\n == VENTA REALIZADA ==");
																				compra= false;
																				confirmacion= true;
																				comprobacion= true;
																				ciclo= false;
																				break;
																			default:
																				System.out.println("\nError La opci\u00f3n elegida es incorrecta");
																				break;
																			}//Fin del switch
																		} else {
																			System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
																		}//Fin del if
																	} while (compra);
																} else {
																	System.out.println("\nRegresando...");
																}//Fin del if
															}//Fin de if
														}//Fin del foreach
													} else {
														System.out.println("\nError: Ese DNI no es v\u00e1lido");
													}//Fin del if
												} while (!comprobacion);
												break;
											case "3":
												System.out.println("\n== VENTA CANCELADA == ");
												confirmacion= true;
												ciclo= false;
												break;
											default:
												System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
												break;
											}//Fin del switch
										} else {
											System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
										}//Fin del if
									} while (!confirmacion);
								} else {
									System.out.println("\nError: Este coche ya ha sido vendido");
								}//Fin del if
							}//Fin de if
						}//Fin del if
					}//Fin del foreach
				} else {
					System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
				}//Fin del if
			} while (ciclo);
		} else {
			System.out.println("\nError: No hay vehiculos disponibles");
		}//Fin del if

	}
	protected void ComprarVehiculo () {
		//variables
		String dni, matricula, datos;
		double precioC, precioM, dinerototal;
		boolean ciclo= true, ciclo2= true, ciclo3= true, ciclo4= true, comprobacion= false, existe= false;
		
		if (person.size() > 0) {
			System.out.println("\n == COMPRA DE UN VEH\u00edCULO == ");
			System.out.println("Si desea cancelar la compra escriba Cancelar");
			do {
				System.out.print("\nDNI del cliente: ");
				dni= teclado.CadenaTexto();
				if (dni.equals("")) {
					System.out.println("\nError: Por favor rellene el campo");
				} else if (dni.equalsIgnoreCase(dni)) {
					System.out.println("\n== COMPRA CANCELADA ==");
					ciclo= false;
				} else if (comprob.isDNI(dni)) {
					for (personas p : person) {
						if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("cliente")) {
							if (p instanceof cliente) {
								do {
									System.out.print("\nMatricula del vehiculo a comprar: ");
									matricula= teclado.CadenaTexto();
									if (matricula.equals("")) {
										System.out.println("\nError: Por favor rellena datos");
									} else if (matricula.equalsIgnoreCase("cancelar")) {
										System.out.println("\n== COMPRA CANCELADA ==");
										ciclo= false;
										ciclo2= false;
									} else if (comprob.isMatricula(matricula)) {
										for (vehiculos v : automovil) {
											if (v.getMatricula().equalsIgnoreCase(matricula)) {
												comprobacion= true;
												System.out.println("\nError: La matr\u00edcula ya existe en un veh\u00edculo");
											}//Fin del if
										}//Fin del foreach
										if (!comprobacion) {
											do {
												System.out.print("Precio del veh\u00edculo: ");
												datos= teclado.CadenaTexto();
												
												if (datos.equals("")) {
													System.out.println("\nError: Por favor rellena datos");
												} else if (comprob.isNumeroDecimal(datos) || comprob.isNumeroEntero(datos)) {
													ciclo= false;
													ciclo2= false;
													ciclo3= false;
													datos= datos.replace(',', '.');
													double precio= Double.parseDouble(datos);
													do {
														System.out.println("\nCoche - Moto");
														System.out.print("Indique el tipo de veh\u00edculo: ");
														datos= teclado.CadenaTexto();
														
														if (datos.equals("")) {
															System.out.println("\nError: Por favor rellene datos");
														} else if (datos.equalsIgnoreCase("coche")) {
															vehiculos c= new coches();
															precioC= Double.parseDouble(admin.get(5));
															precioC= precioC + precio;
															dinerototal= Double.parseDouble(admin.get(7));
															dinerototal= dinerototal - precio;
															admin.remove(5);
															admin.remove(7);
															admin.add(5, String.valueOf(precioC));
															admin.add(7, String.valueOf(dinerototal));
															c.setMatricula(matricula);
															c.setModelo("");
															c.setColor("");
															c.setCombustible("");
															c.setKilometros(0);
															c.setPlazas("");
															precio = precio * 1.3;
															c.setPrecio(precio);
															((coches) c).setEnReparacionCoche("no");
															((coches) c).setVendidoCoche("no");
															automovil.add(c);
															System.out.println("\n == COMPRA REALIZADA CON \u00e9xito == ");
															System.out.println("El administrador podr\u00e1 visualizar los datos del vehiculo");
															System.out.println("en su respectivo apartado, resta por completar los datos");
															ciclo= false;
															ciclo2= false;
															ciclo3= false;
															ciclo4= false;
														} else if (datos.equalsIgnoreCase("moto")) {
															vehiculos m= new motos();
															precioM= Double.parseDouble(admin.get(5));
															precioM= precioM + precio;
															dinerototal= Double.parseDouble(admin.get(7));
															dinerototal= dinerototal - precio;
															admin.remove(5);
															admin.remove(7);
															admin.add(5, String.valueOf(precioM));
															admin.add(7, String.valueOf(dinerototal));
															m.setMatricula(matricula);
															m.setModelo("");
															m.setColor("");
															m.setCombustible("");
															m.setKilometros(0);
															m.setPlazas("");
															m.setPrecio(precio);
															((motos) m).setEnReparacionMoto("no");
															((motos) m).setVendidoMoto("no");
															automovil.add(m);
															System.out.println("\n == COMPRA REALIZADA CON \u00e9xito == ");
															System.out.println("El administrador podr\u00e1 visualizar los datos del vehiculo");
															System.out.println("en su respectivo apartado, resta por completar los datos");
															ciclo= false;
															ciclo2= false;
															ciclo3= false;
															ciclo4= false;
;														} else {
															System.out.println("\nError: El tipo de veh\u00edculo no existe");
														}//Fin del if
													} while (ciclo4);
												} else {
													System.out.println("\nError: El campo solo admite n\u00fameros");
												}//Fin del if
											} while (ciclo3);
										}//Fin del if
									} else {
										System.out.println("\nError: La matr\u00edcula no es v\u00e1lida");
									}//Fin del if
								} while (ciclo2);
							}//Fin del if
						}//Fin del if
					}//Fin del foreach
					if (!existe) {
						System.out.println("No existe cliente registrado con ese DNI");
					}//Fin del if
				} else {
					System.out.println("\nError: El DNI no es v\u00e1lido");
				}
			} while (ciclo);
		} else {
			System.out.println("\nError: No hay clientes registrados");
		}//Fin del if
	}
	protected void AltaCliente () {
		//Un trabajador puede ser cliente
		String datos;
		boolean correcto= false;
		
		System.out.println("\n == ALTA CLIENTE == ");
		personas c= new cliente();
		System.out.println("\nPor favor rellene los siguientes datos: ");
		//DNI
		do {
			boolean repetir= true;
			System.out.print("\nDNI: ");
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\nError: Por favor rellene el campo DNI");
				repetir= false;
			} else if (comprob.isDNI(datos)) {
				for (int i= 0; i < person.size(); i++) {
					if (person.get(i).getDni().equalsIgnoreCase(datos) && person.get(i).getTipo_persona().equals("cliente")) {
						System.out.println("\nError: El DNI ya est\u00e1 registrado en un cliente");
						repetir= false;
						i= person.size();
					}//Fin del if
				}//Fin del for i
			} else {
				System.out.println("\nError: El DNI no es v\u00e1lido");
				repetir= false;
			}//Fin del if
			if (repetir) {
				c.setDni(datos);
				admin.remove(8);
				admin.add(8, c.getDni());
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
				c.setNombre(datos);
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
				c.setApellidos(datos);
				correcto= true;
			}//Fin del if
		} while (!correcto);
		correcto= false;
		//Direccion
		do {
			System.out.print("Direcci\u00f3n: ");
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\nError: Por favor rellene el campo direcci\u00f3n");
			} else {
				c.setDireccion(datos);
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
				c.setTelefono_movil(datos);
				correcto= true;
			} else {
				correcto= false;
			}//Fin del if
		} while (!correcto);
		c.setTipo_persona("cliente");
		person.add(c);
		System.out.println("\n == EL ALTA DE CLIENTE HA SIDO COMPLETADA == ");
	}
	protected void MostrarCliente () {
		//variables
		String dni, datos;
		boolean ciclo= true;
		
		do {
			menu.MenuMostrarCliente();
			datos= teclado.CadenaTexto();
			if (datos.equals("")) {
				System.out.println("\nError: Por favor rellene el campo");
			} else if (comprob.isNumeroEntero(datos)) {
				switch (datos) {
				case "1":
					for (personas c : person) {
						if (c instanceof cliente) {
							System.out.println("\n-------------------------");
							System.out.println("\n"+c);
						}//Fin del if
					}//Fin del foreach
					break;
				case "2":
					boolean existe= false;
					System.out.println("\n == MOSTRAR DATOS DE UN CLIENTE == ");
					System.out.print("\nDNI del CLIENTE: ");
					dni= teclado.CadenaTexto();
					//Comprobar que exista el DNI de la persona
					if (comprob.isDNI(dni)) {
							for (personas c : person) {
								if (c.getDni().equalsIgnoreCase(dni) && c.getTipo_persona().equals("cliente")) {
									System.out.println("\n"+c);
									existe= true;
								}//Fin del if
							}//Fin del foreach
							if (!existe) {
								System.out.println("\nError: No existe cliente registrado con ese DNI");
							}//Fin del if
						} else {
							System.out.println("\nError: El DNI introducido no es v\u00e1lido");
						}//Fin del if
						break;
					case "3":
						ciclo= false;
						break;
					default:
						System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
						break;
					}//Fin del switch
			} else {
				System.out.println("\nError: La opci\u00f3n solo admite n\u00fameros");
			}//Fin del if
		} while (ciclo);
	}
	protected void VentasRealizadas () {
		String dni;
		boolean ciclo= true, existe= false;
		
		if (vendidos.size() > 0) {
			System.out.println("\n== COMPRAS REALIZAS POR UN CLIENTE == ");
			System.out.println("Para cancelar escriba Cancelar");
			do {
				System.out.print("\nDNI del cliente: ");
				dni= teclado.CadenaTexto();
				
				if (dni.equals("")) {
					System.out.println("\nError: Por favor rellena los datos");
				} else if (comprob.isDNI(dni)) {
					for (venta ve : vendidos) {
						if (ve.getCl().getDni().equals(dni) && ve.getCl().getTipo_persona().equals("cliente")) {
							System.out.println("\nCliente: ");
							System.out.println(" DNI: "+ve.getCl().getDni());
							System.out.println(" Nombre y apellidos: "+ve.getCl().getNombre()+" "+ve.getCl().getApellidos());
							if (((cliente) ve.getCl()).getCompraCoche() > 0) {
								System.out.println("\nCoches comprados: "+((cliente) ve.getCl()).getCompraCoche());
								System.out.println("\n-------------------------");
								for (int i= 0; i < ve.getAuto().size(); i++) {
									if (ve.getAuto().get(i) instanceof coches) {
										System.out.println(" * Matricula: "+ve.getAuto().get(i).getMatricula());
										System.out.println("   Modelo: "+ve.getAuto().get(i).getModelo());
										System.out.println("-------------------------");
									}//Fin del if
								}//Fin del for i
							} else {
								System.out.println("\nCoches comprados: 0");
							}//Fin del if
							if (((cliente) ve.getCl()).getCompraMoto() > 0) {
								System.out.println("\nMotos compradas: "+((cliente) ve.getCl()).getCompraMoto());
								System.out.println("\n-------------------------");
								for (int i= 0; i < ve.getAuto().size(); i++) {
									if (ve.getAuto().get(i) instanceof motos) {
										System.out.println(" * Matricula: "+ve.getAuto().get(i).getMatricula());
										System.out.println("   Modelo: "+ve.getAuto().get(i).getModelo());
										System.out.println("-------------------------");
									}//Fin del if
								}//Fin del for i
							} else {
								System.out.println("\nMotos compradas: 0");
							}//Fin del if
							System.out.println("\nVenta realizada el d\u00eda "+ve.getFechaventa()+" a las "+ve.getHoraventa());
						}//Fin del if
					}//Fin del for
					if (!existe) {
						System.out.println("\nError: No existe cliente con ese DNI");
					}//Fin del if
					ciclo= false;
				} else if (dni.equalsIgnoreCase("cancelar")) {
					System.out.println("\n == ACCI\u00f3N CANCELADA == ");
					ciclo= false;
				} else {
					System.out.println("\nError: El DNI no es v\u00e1lido");
				}//Fin del if
			} while (ciclo);
		} else {
			System.out.println("\nError: No se ha realizado ninguna venta");
		}//Fin del if
	}
	protected void ModificarCliente () {
		//variables
		String dni, datos;
		boolean ciclo= true, repetir= true, confirmacion= false, existe= false;
		
		System.out.println("\n == MODIFICAR DATOS DE UN CLIENTE == ");
		System.out.print("\nDNI del cliente: ");
		dni= teclado.CadenaTexto();
		//Comprobar que exista el DNI de la persona
		Iterator <personas> itrP = person.iterator();
		while (itrP.hasNext() && !existe) {
			personas p= itrP.next();
			//Segun la verificacion nos lo mostrara o no
			if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("cliente")) {
				do {
					System.out.println("\n"+p);
					menu.MenuModificarEmpleado();
					datos= teclado.CadenaTexto();
					if (comprob.isNumeroEntero(datos)) {
						for (int i= 0; i < person.size(); i++) {
							if (person.get(i).getDni().equals(dni)) {
								switch (datos) {
								case "1":
									do {
										System.out.print("\nIndique el nuevo DNI: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte dato.");
										} else if (comprob.isDNI(datos)) {
											if (person.size() > 0) {
												for (int j= 0; j < person.size(); j++) {
													if (person.get(i).getDni().equalsIgnoreCase(datos) && person.get(i).getTipo_persona().equals("cliente")) {
														System.out.println("\nError: El DNI ya est\u00e1 registrado en un cliente");
														repetir= false;
														i= person.size();
													}//Fin del if
												}//Fin del for i
											} else {
												person.get(i).setDni(datos);
												System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
											}//Fin del if
										} else {
											System.out.println("\nError: El DNI no es v\u00e1lido.");
										}//Fin del if
										if (repetir) {
											person.get(i).setDni(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										}//Fin del if
									} while (!confirmacion);
									i= person.size();
									ciclo= false;
									break;
								case "2":
									do {
										System.out.print("\nIndique el nuevo nombre: ");
										datos= teclado.CadenaTexto();
										
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte datos.");
										} else if (comprob.isNumeroMovil(datos)) {
											person.get(i).setNombre(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										} else {
											confirmacion= false;
										}//Fin del if
									} while (!confirmacion);
									i= person.size();
									ciclo= false;
									break;
								case "3":
									do {
										System.out.print("\nIndique los nuevos apellidos: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte datos.");
										} else if (comprob.isNumeroMovil(datos)) {
											person.get(i).setApellidos(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										} else {
											confirmacion= false;
										}//Fin del if
									} while (!confirmacion);
									i= person.size();
									ciclo= false;
									break;
								case "4":
									do {
										System.out.print("\nIndique la nueva direcci\u00f3n: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte datos.");
										} else {
											person.get(i).setDireccion(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										}//Fin del if
									} while (!confirmacion);
									i= person.size();
									ciclo= false;
									break;
								case "5":
									do {
										System.out.print("\nIndique el nuevo n\u00famero de telefono m\u00f3vil: ");
										datos= teclado.CadenaTexto();
										if (datos.equals("")) {
											System.out.println("\nError: Por favor inserte datos.");
										} else if (comprob.isNumeroMovil(datos)) {
											person.get(i).setTelefono_movil(datos);
											System.out.println("\n == MODIFCACI\u00f3N REALIZADA == ");
											confirmacion= true;
										} else {
											confirmacion= false;
										}//Fin del if
									} while (!confirmacion);
									i= person.size();
									ciclo= false;
									break;
								case "6":
									System.out.println("\n == MODIFICACI\u00f3N CANCELADA == ");
									i= person.size();
									ciclo= false;
									break;
								default:
									System.out.println("\nError: La opci\u00f3n elegida es incorrecta");
									break;
								}//Fin del switch
							}//Fin del if
						}//Fin del for i
					} else {
						System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero");
					}//Fin del if
					existe= true;
				} while (ciclo);
			}//Fin del while
			if (!existe) {
				System.out.println("\nError: No existe cliente registrado con ese DNI");
			}//Fin del if
		}//Fin del if

	}
	protected void MostrarEnReparacion () {
		if (automovil.size() > 0) {
			System.out.println("\n== MOSTRAR VEHI\u00f3CULOS EN REPARACI\u00f3N ==");
			System.out.println("\nCoches: \n");
			for (vehiculos v : automovil) {
				if (v instanceof coches) {
					if (((coches) v).getVendidoCoche().equals("si") && ((coches) v).getEnReparacionCoche().equals("si")) {
						System.out.println(" * Matricula: "+v.getMatricula());
						System.out.println("  Modelo: "+v.getModelo());
						System.out.println("-------------------------");
					}//Fin del if
				}//Fin del if
			}//Fin del foreach
			System.out.println("\nMotos: ");
			for (vehiculos v2 : automovil) {
				if (v2 instanceof motos) {
					if (((motos) v2).getVendidoMoto().equals("si") && ((motos) v2).getEnReparacionMoto().equals("si")) {
						System.out.println(" * Matricula: "+v2.getMatricula());
						System.out.println("  Modelo: "+v2.getModelo());
						System.out.println("-------------------------");
					}//Fin del if
				}//Fin del if
			}//Fin del foreach
		} else {
			System.out.println("\nError: No hay veh\u00edculos registrados");
		}//Fin del if
	}
	protected void RepararVehiculo () {
		//variables
		String matricula;
		boolean ciclo= true, existe= false;
		
		if (automovil.size() > 0) {
			System.out.println("\n== REPARAR VEH\u00edCULO ==");
			System.out.println("Si quieres cancelar escribe Cancelar");
			do {
				System.out.print("\nMatricula del veh\u00edculo: ");
				matricula= teclado.CadenaTexto();
				
				if (matricula.equals("")) {
					System.out.println("\nError: Por favor rellena el campo");
				} else if (matricula.equalsIgnoreCase("cancelar")) {
					System.out.println("\n == REPARACI\u00f3N CANCELADA ==");
					ciclo= false;
				} else if (comprob.isMatricula(matricula)) {
					ciclo= false;
					for (vehiculos v : automovil) {
						if (v instanceof coches) {
							if (v.getMatricula().equals(matricula) && ((coches) v).getVendidoCoche().equals("si") && ((coches) v).getEnReparacionCoche().equals("si")) {
								((coches) v).setEnReparacionCoche("si");
								System.out.println("\n== COCHE REPARADO ==");
								existe= true;
							}//Fin del if
						} else if (v instanceof motos) {
							if (v.getMatricula().equals(matricula) && ((motos) v).getVendidoMoto().equals("si") && ((motos) v).getEnReparacionMoto().equals("si")) {
								((motos) v).setEnReparacionMoto("no");
								System.out.println("\n== MOTO REPARADA ==");
								existe= true;
							}//Fin del if
						}//Fin del if
					}//Fin del foreach
					if (!existe) {
						System.out.println("\nError: No existe veh\u00edculo con esa matr\u00edcula o no ha sido vendido");
					}//Fin del if
				} else {
					System.out.println("\nError: El DNI no es v\u00e1lido");
				}//Fin del if
			} while (ciclo);
		} else {
			System.out.println("\nError: No hay veh\u00edculos registrados");
		}//Fin del if
	}
	protected void EnviarMecanico () {
		String matricula;
		boolean ciclo= true, existe= false;
		
		if (automovil.size() > 0) {
			System.out.println("\n== ENVIAR VEH\u00edCULO A MEC\u00e1NICO ==");
			System.out.println("Si quieres cancelar escribe Cancelar");
			do {
				System.out.print("\nMatricula del veh\u00edculo: ");
				matricula= teclado.CadenaTexto();
				
				if (matricula.equals("")) {
					System.out.println("\nError: Por favor rellena el campo");
				} else if (matricula.equalsIgnoreCase("cancelar")) {
					System.out.println("\n == ENVIO CANCELADO ==");
					ciclo= false;
				} else if (comprob.isMatricula(matricula)) {
					ciclo= false;
					for (vehiculos v : automovil) {
						if (v instanceof coches) {
							if (v.getMatricula().equals(matricula) && ((coches) v).getVendidoCoche().equals("si")) {
								((coches) v).setEnReparacionCoche("si");
								System.out.println("\n== COCHE ENVIADO A REPARACI\u00f3N ==");
								existe= true;
							}//Fin del if
						} else if (v instanceof motos) {
							if (v.getMatricula().equals(matricula) && ((motos) v).getVendidoMoto().equals("si")) {
								((motos) v).setEnReparacionMoto("si");
								System.out.println("\n== MOTO ENVIADA A REPARACI\u00f3N ==");
								existe= true;
							}//Fin del if
						}//Fin del if
					}//Fin del foreach
					if (!existe) {
						System.out.println("\nError: No existe veh\u00edculo con esa matr\u00edcula o no ha sido vendido");
					}//Fin del if
				} else {
					System.out.println("\nError: El DNI no es v\u00e1lido");
				}//Fin del if
			} while (ciclo);
		} else {
			System.out.println("\nError: No hay veh\u00edculos registrados");
		}//Fin del if
	}
	protected void MostrarGanancias () {
		System.out.println("\n== MOSTRAR GANANCIAS == ");
		System.out.println("\nDinero Base: "+admin.get(2)+"\u20ac");
		System.out.println("\nVenta de coches "+admin.get(3)+"\u20ac");
		System.out.println("Venta de motos "+admin.get(4)+"\u20ac");
		System.out.println("\nCompra de coches -"+admin.get(5)+"\u20ac");
		System.out.println("Compra de motos -"+admin.get(6)+"\u20ac");
		System.out.println("\nDinero Total: "+admin.get(7)+"\u20ac");
	}
	protected void CambiarPassword () {
		//variables
		String dni, passold, passnew, passrepeat;
		boolean existe= false, correcto= false, comprobacion= false;
		
		for (personas p : person) {
			if (p.getDni().equalsIgnoreCase(admin.get(9)) && p.getTipo_persona().equals("empleado")) {
				System.out.println("\n== CAMBIAR CONTRASE\u00f1A EMPLEADO == ");
				comprobacion= true;
				if (p instanceof asesor) {
					do {
						System.out.print("\nNueva contrase\u00f1a: ");
						passnew= teclado.CadenaTexto();
						System.out.print("Repite contrase\u00f1a: ");
						passrepeat= teclado.CadenaTexto();
						if (passrepeat.equals(passnew)) {
							((asesor) p).setPassword(passnew);
							System.out.println("\n == Contrase\u00f1a cambiada con \u00e9xito == ");
							correcto= true;
						} else {
							System.out.println("\nError: Las contrase\u00f1as no coinciden");
						}//Fin del if
					} while (!correcto);
				} else if (p instanceof mecanico) {
					do {
						System.out.print("\nNueva contrase\u00f1a: ");
						passnew= teclado.CadenaTexto();
						System.out.println("Repite contrase\u00f1a");
						passrepeat= teclado.CadenaTexto();
						if (passrepeat.equals(passnew)) {
							((mecanico) p).setPassword(passnew);
							System.out.println("\n == Contrase\u00f1a cambiada con \u00e9xito == ");
						} else {
							System.out.println("\nError: Las contrase\u00f1as no coinciden");
						}//Fin del if
					} while (!correcto);
				}//Fin del if
			}//Fin del if
		}//Fin del foreach
		if (!comprobacion) {
			System.out.println("\n== CAMBIAR CONTRASE\u00f1A == ");
			System.out.print("\nDNI: ");
			dni= teclado.CadenaTexto();
			
			for (personas p : person) {
				if (p.getDni().equalsIgnoreCase(dni) && p.getTipo_persona().equals("empleado")) {
					existe= true;
					if (p instanceof asesor) {
						do {
							System.out.print("\nContrase\u00f1a antigua: ");
							passold= teclado.CadenaTexto();
							if (((asesor) p).getPassword().equals(passold) || admin.get(1).equals(passold)) {
								correcto= true;
								System.out.print("\nNueva contrase\u00f1a: ");
								passnew= teclado.CadenaTexto();
								System.out.print("Repite contrase\u00f1a: ");
								passrepeat= teclado.CadenaTexto();
								if (passrepeat.equals(passnew)) {
									((asesor) p).setPassword(passnew);
									System.out.println("\n == Contrase\u00f1a cambiada con \u00e9xito == ");
								} else {
									System.out.println("\nError: Las contrase\u00f1as no coinciden");
									correcto= false;
								}//Fin del if
							} else {
								System.out.println("\nError: La contrase\u00f1a no es correcta");
							}//Fin del if
						} while (!correcto);
					} else if (p instanceof mecanico) {
						do {
							System.out.print("\nContrase\u00f1a antigua: ");
							passold= teclado.CadenaTexto();
							if (((mecanico) p).getPassword().equals(passold) || admin.get(1).equals(passold)) {
								correcto= true;
								System.out.print("\nNueva contrase\u00f1a: ");
								passnew= teclado.CadenaTexto();
								System.out.println("Repite contrase\u00f1a");
								passrepeat= teclado.CadenaTexto();
								if (passrepeat.equals(passnew)) {
									((mecanico) p).setPassword(passnew);
									System.out.println("\n == Contrase\u00f1a cambiada con \u00e9xito == ");
								} else {
									System.out.println("\nError: Las contrase\u00f1as no coinciden");
									correcto= false;
								}//Fin del if
							} else {
								System.out.println("\nError: La contrase\u00f1a no es correcta");
							}//Fin del if
						} while (!correcto);
					}//Fin del if
				}//Fin del if
			}//Fin del foreach
			if (!existe) {
				System.out.println("\nError: No existe empleado registrado con ese DNI");
			}//Fin del if
		}//Fin del if
	}
}
