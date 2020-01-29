package proyecto_programacion;

import java.util.*;

class metodos {
	
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
				System.out.println(" [3] Buscar empleado");
				System.out.println(" [4] Modificar empleado");
				System.out.println(" [5] Alta vehiculo");
				System.out.println(" [6] Baja vehiculo");
				System.out.println(" [7] Buscar vehiculo");
				System.out.println(" [8] Mostrar vehiculos");
				System.out.println(" [9] Modificar vehiculo");
				System.out.println(" [10] Cerrar sesi\uf003n");
				datos= teclado.nextLine();
				
				if (isNumeroMovil(datos)) {
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
				BuscarEmpleado(person, teclado);
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
				BuscarVehiculo(automovil, teclado);
				break;
			case "8":
				MostrarVehiculo(automovil, teclado);
				break;
			case "9":
				ModificarVehiculo(automovil, teclado);
				break;
			case "10":
				ciclo= false;
				admin.remove(1);
				admin.add(1, "verdadero");
				break;
			}
		} while (ciclo);
	}
	private void MenuAsesor () {
		System.out.println("");
		System.out.println("MENU ASESOR");
		System.out.println("");
		
	}
	private void MenuMecanico () {
		System.out.println("");
		System.out.println("MENU MECANICO");
		System.out.println("");
		
	}

	//Cuentas
	public void CuentaAdmin (ArrayList<String> admin, ArrayList<personas> person, ArrayList<vehiculos> automovil, Scanner teclado) {
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
				
				if (usuario.equals("") || password.equals("") || dinerobase.equals("") ) {
					System.out.println("\nError: Nombre de administrador, contrase\u00f1a y/o dinero base no establecidos");
					System.out.println("");
					System.out.println("Por favor vuelva a repetir todo de nuevo");
				} else {
					if (dinerobase.matches("[a-zA-Z]+") || dinerobase.contains(" ")) {
						System.out.println("\nError: El dinero base tiene que ser un n\u00famero");
						System.out.println("");
						System.out.println("Por favor vuelva a repetir todo de nuevo");
					} else {
						int dinero= Integer.parseInt(dinerobase);
						if (dinero >= 500000 ) {
							admin.remove(1);
							admin.add(1, "false");
						} else {
							System.out.println("\nError: El dinero base tiene que ser mayor o igual a 500,000\u20ac");
							System.out.println("");
							System.out.println("Por favor vuelva a repetir todo de nuevo");
						}//Fin del if
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
	public void CuentaEmpleado(ArrayList<String> admin, ArrayList<personas> person, ArrayList<String> dnis, Scanner teclado) {
		String usuario;
		boolean existe= false;
		//int contador= 2;
		admin.remove(1);
		admin.add(1, "verdadero");
		
		if (admin.get(0).equals("verdadero")) {
			System.out.println("\nError: La cuenta administrador no ha sido establecida");
		} else {
			System.out.println("\n == INICIO DE SESI\u00f3N EMPLEADO");
			System.out.println("");
			System.out.print("Introduzca su DNI: ");
			usuario= teclado.nextLine();
			
			System.out.println(dnis.get(0).equals(usuario));
			
			//Comprobamos que el DNI de la persona exista
			for (int i=0; i<dnis.size(); i++) {
				if (dnis.get(i).equals(usuario)) {
					existe= true;
				} else {
					existe= false;
				}//Fin del if
				
			}
			
			//Segun la verificacion anterior nos dira si existe o no
			for (int j=0; j<person.size(); j++) {
				if (!existe) {
					System.out.println("\nError: No existe empleado registrado con ese DNI");
					j= person.size();
				} else if (person.get(j).getDni().equals(usuario)) {
					System.out.println(" == BIENVENIDO "+person.get(j).getNombre()+" "+person.get(j).getApellidos()+" == ");
						if (((asesor) person.get(j)).getTrabajo_asesor().equals("Asesor")) {
							MenuAsesor();
						} else if (((asesor) person.get(j)).getTrabajo_asesor().equals("Mecanico")) {
							MenuMecanico();
						}
				}//Fin del if
			}
		}//Fin del if
	}

	//Altas y Bajas
	private void AltaEmpleado (ArrayList<personas> person, Scanner teclado) {
//		personas a= new asesor();
//		personas m= new mecanico();
		
		
		
	}
	private void BajaEmpleado (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void BuscarEmpleado (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void ModificarEmpleado (ArrayList<personas> person, Scanner teclado) {
		
	}
	private void AltaVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void BajaVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void BuscarVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void MostrarVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void ModificarVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void CompraVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void VentaVehiculo (ArrayList<vehiculos> automovil, Scanner teclado) {
		
	}
	private void GuardarCliente (ArrayList<vehiculos> person, Scanner teclado) {
		
	}
	private void ModificarCliente (ArrayList<vehiculos> person, Scanner teclado) {
		
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
	protected boolean isMatricula (String matricula){
		boolean matriculaValida= false;
		
	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        matriculaValida= true;

	    }else{
	        matriculaValida= false;
	    }
	    
	    return matriculaValida;
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
	protected boolean isNumeroMovil (String movil) {
		boolean verdadero= false;
		
		if (movil.matches("[0-9]+")) {
			verdadero= true;
		} else {
			System.out.println("\nError: La opcion debe ser un numero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isFechaNacimiento (String fechan) {
		boolean fechan_valida= false;
		
		return fechan_valida;
	}
}
