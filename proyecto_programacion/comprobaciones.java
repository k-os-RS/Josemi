package proyecto_programacion;

class comprobaciones {
	
	//Comprobacion de validez
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
	protected boolean isMatricula (String matricula){
		boolean matriculaValida= false;
		
	    if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
	        matriculaValida= true;
	    }else{
	        matriculaValida= false;
	    }
	    
	    return matriculaValida;
	}
	protected boolean isCombustible (String combustible) {
		boolean valido= false;
		
		if (combustible.equalsIgnoreCase("gasolina") || combustible.equalsIgnoreCase("diesel") ||combustible.equalsIgnoreCase("electricidad") || combustible.equalsIgnoreCase("gasoil")) {
			valido= true;
		} else {
			System.out.println("\nError: El tipo de combustible no es v\u00e1lido");
		}
		
		return valido;
	}
	protected boolean isNumeroMovil (String movil) {
		boolean verdadero= false;
		
		if (movil.substring(0, 1).equals("6") && movil.length() == 9){
			verdadero= true;
		} else {
			System.out.println("\nError: El n\u00famero tendrá que conteneder 9 digitos y empezar en 6.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isNumeroEntero (String numeroE) {
		boolean verdadero= false;
		
		if (numeroE.matches("[0-9]+")) {
			verdadero= true;
		} else {
			System.out.println("\nError: La opci\u00f3n debe ser un n\u00famero.");
			verdadero= false;
		}//Fin del if
		
		return verdadero;
	}
	protected boolean isNumeroDecimal (String numeroD) {
		int pospunto= 0, cont= 0;
		boolean verdadero= false;
		numeroD.replace(',', '.');
		
		for (int i= 0; i < numeroD.length(); i++) {
			if (numeroD.charAt(i) == '.') {
				cont++;
			}
		}
		
		if (cont == 1) {
			pospunto= numeroD.indexOf('.');
		} else {
			verdadero= true;
		}
		
		if (!verdadero) {
			if (pospunto == numeroD.length() - 1 || pospunto == 0) {
				verdadero= false;
			}
		}
		
		return verdadero;
	}
	
}
