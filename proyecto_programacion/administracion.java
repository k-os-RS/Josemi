package proyecto_programacion;

import java.util.*;

class administracion {
	ArrayList<vehiculos> automovil;
	ArrayList<personas> person;
	ArrayList<String> admin;
	E_S teclado= new E_S();
	
	//Altas y Bajas
	protected void AltaEmpleado () {
		String tipoEmpleado= "", datos= "";
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA EMPLEADO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de empleado");
		System.out.println("Asesor - Mecanico | Si desea cancelar el alta escriba Cancelar\n");

		do {
			System.out.print("Tipo de empleado: ");
			teclado.CadenaTexto(tipoEmpleado);

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
					teclado.CadenaTexto(datos);

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
					teclado.CadenaTexto(datos);

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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);

					if (datos.equals("")) {
						System.out.println("\nError: Por favor rellene el campo fecha de nacimiento");
					} else if (isFechaNacimiento(datos)) {
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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);

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
					person.add(a);
					cancelar= true;
				} else {
					System.out.println("\n == EL ALTA DE MECANICO HA SIDO COMPLETADA == ");
					m.setTipo_persona("mecanico");
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
//	protected void BajaEmpleado () {
//		String dni;
//		boolean existe= false;
//		
//		System.out.println("\n == BAJA EMPLEADO == ");
//		System.out.println("");
//		System.out.print("DNI del empleado: ");
//		dni= teclado.nextLine();
//
//		//Comprobamos que el DNI de la persona exista
//		for (int i=0; i<person.size(); i++) {
//			if (person.get(i).getDni().equals(dni) && person.get(i).getTipo_persona().equals("empleado")) {
//				existe= true;
//				i= person.size();
//			} else {
//				existe= false;
//			}//Fin del if
//			
//		}//Fin del for i
//		
//		//Segun la verificacion anterior nos dira si existe o no
//		for (int j=0; j<person.size(); j++) {
//			if (!existe) {
//				System.out.println("\nError: No existe empleado registrado con ese DNI");
//				j= person.size();
//			} else if (person.get(j).getDni().equals(dni)) {
//				System.out.println("\nEst\u00e1 por eliminar del registro al empleado "+person.get(j).getNombre());
//				System.out.println("con DNI "+person.get(j).getDni());
//				System.out.println("");
//				System.out.println("Pulse INTRO si est\u00e1 seguro o escriba Cancelar, para cancelar la operaci\u00f3n\n");
//				dni= teclado.nextLine();
//				
//				if (dni.equals("")) {
//					System.out.println(" == SE HA DADO DE BAJA AL EMPLEADO CON \u00e0XITO == ");
//					person.remove(j);
//				} else if (dni.equalsIgnoreCase("cancelar")) {
//					System.out.println(" == OPERACI\u00f3N CANCELADA == ");
//					j= person.size();
//				} else {
//					System.out.println(" == OPERACI\u00f3N CANCELADA == ");
//					j= person.size();
//				}
//			}//Fin del if
//		}//Fin del for j
//		
//	}
//	protected void MostrarEmpleado () {
//		String dni;
//		boolean existe= false;
//		
//		System.out.println("\n == MOSTRAR DATOS EMPLEADO == ");
//		System.out.println("");
//		System.out.print("DNI del empleado: ");
//		dni= teclado.nextLine();
//
//		//Comprobamos que el DNI de la persona exista
//		for (int i=0; i<person.size(); i++) {
//			if (person.get(i).getDni().equals(dni) && person.get(i).getTipo_persona().equals("empleado")) {
//				existe= true;
//				i= person.size();
//			} else {
//				existe= false;
//			}//Fin del if
//			
//		}//Fin del for i
//		
////		if (!existe) {
////			System.out.println("\nError: No existe empleado registrado con ese DNI");
////		} else {
////			for (personas t: person) {
////				System.out.println(t);
////			}
////		}
//
//		//Segun la verificacion anterior nos dira si existe o no
//		for (int j=0; j<person.size(); j++) {
//			if (!existe) {
//				System.out.println("\nError: No existe empleado registrado con ese DNI");
//				j= person.size();
//			} else if (person.get(j).getDni().equals(dni)) {
//				person.get(j);
//			}//Fin del if
//		}//Fin del for j
//	}
	protected void AltaVehiculo () {
		
		String tipoVehiculo= "", datos= "";
		Double precio, km;
		boolean cancelar= false, correcto= false;

		System.out.println("\n == ALTA VEHICULO == ");
		System.out.println("\nPor favor, a continuaci\u00f3n indique el tipo de vehiculo");
		System.out.println("Coche - Moto | Si desea cancelar el alta escriba Cancelar\n");
		System.out.print("Tipo de vehiculo: ");
		teclado.CadenaTexto(tipoVehiculo);

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
					teclado.CadenaTexto(datos);

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
					teclado.CadenaTexto(datos);

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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);
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
					teclado.CadenaTexto(datos);
					
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
					teclado.CadenaTexto(datos);
					
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
		
		if (combustible.equalsIgnoreCase("gasolina") || combustible.equalsIgnoreCase("diesel")) {
			valido= true;
		} else {
			System.out.println("\nError: El tipo de combustible no es v\u00e1lido");
		}
		
		return valido;
	}
	protected boolean isFechaNacimiento (String fecha) {
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
}
