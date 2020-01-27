package proyecto_programacion;

import java.util.*;

class metodos {
	
	//Menus
	public void MenuPrincipal () {
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
	public static void MenuAdmin (ArrayList<String> admin, Scanner teclado) {
		String datos="";
		
		System.out.println("");
		System.out.println("MENU ADMIN");
		System.out.println("");
		datos= teclado.next();
		System.out.println(datos);
		admin.remove(1);
		admin.add(1, "verdadero");
		
	}
	public static void MenuAsesor () {
		System.out.println("");
		System.out.println("MENU ASESOR");
		System.out.println("");
		
	}
	public static void MenuMecanico () {
		System.out.println("");
		System.out.println("MENU MECANICO");
		System.out.println("");
		
	}

	//Cuentas
	public void CuentaAdmin (ArrayList<String> admin, Scanner teclado) {
		String usuario, password;
		int contador= 2;
		admin.remove(1);
		admin.add(1, "verdadero");

		if (admin.get(0).equals("verdadero")) {
			System.out.println("\nBuenas administrador, esta es la primera vez que");
			System.out.println("inicia sesi\u00f3n, por favor rellene los siguientes datos");
			System.out.println("");
			do {
				System.out.print("Nombre de usuario: ");
				usuario= teclado.nextLine();
				System.out.print("Contrase\u00f1a: ");
				password= teclado.nextLine();
				
				if (usuario.equals("") || password.equals("")) {
					System.out.println("\nError: Nombre de usuario y/o contrase\u00f1a vacios, por favor rellene con datos");
					System.out.println("");
				} else {
					admin.remove(1);
					admin.add(1, "false");
				}
				
			} while (admin.get(1).equals("verdadero"));
			admin.remove(0);

			admin.add(0, "false");
			admin.add(usuario);
			admin.add(password);

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
				metodos.MenuAdmin(admin, teclado);
			}//Fin del while
		}
	}
	public void CuentaEmpleado(ArrayList<String> admin, ArrayList<personas> person, Scanner teclado) {
		String usuario;
		//int contador= 2;
		admin.remove(1);
		admin.add(1, "verdadero");
		
		if (admin.get(0).equals("verdadero")) {
			System.out.println("\nError: La cuenta administrador no ha sido establecida");
		} else {
			System.out.println(" == INICIO DE SESI\u00f3n EMPLEADO");
			System.out.println("");
			System.out.println("Introduce tu DNI: ");
			usuario= teclado.nextLine();

			for (int i=0; i<person.size(); i++) {
				if (person.get(i).equals(null)) {
					System.out.println("\nError: No existe empleado registrado con ese DNI");
					i= person.size();
				} else if (person.get(i).getDni().equals(usuario)) {
					System.out.println(" == BIENVENIDO "+person.get(i).getNombre()+" "+person.get(i).getApellidos()+" == ");
					
				}
				
			}
		}//Fin del if
	}

	//Altas y Bajas
	public void AltaVehiculo () {
		
	}
	public void BajaVehiculo () {
		
	}
	public void AltaEmpleado () {
		
	}
	public void BajaEmpleado () {
		
	}
	public void AltaCliente () {

	}
	public void AltaVC () {
		
	}

	//Comprobacion de valides
    public boolean isDNI (String dni) {  	
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
	public boolean isMatricula (String matricula){
		boolean matriculaValida= false;
		
	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        matriculaValida= true;

	    }else{
	        matriculaValida= false;
	    }
	    
	    return matriculaValida;
	}
	public boolean isNumeroMovil (String opcion) {
		boolean verdadero= false;
		
		if (opcion.matches("[0-9]+")) {
			verdadero= true;
		} else {
			System.out.println("\nError: La opcion debe ser un numero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	public boolean isFechaNacimiento (String fechan) {
		boolean fechan_valida= false;
		
		return fechan_valida;
	}
}
