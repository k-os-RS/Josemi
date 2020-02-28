package proyecto_programacion;

import java.util.*;
class E_S {
	
	Scanner teclado= new Scanner(System.in);

	public String CadenaTexto () {
		String dato= teclado.nextLine();
		
		return dato;
	}
	
	public int EnteroTexto () {
		int dato= teclado.nextInt();
		
		return dato;
	}
	
	public double DoubleTexto () {
		double dato= teclado.nextDouble();
		
		return dato;
	}
	
	public char CaracterTexto () {
		char dato= teclado.next().charAt(0);
		
		return dato;
	}

}
