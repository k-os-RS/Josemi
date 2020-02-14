package proyecto_programacion;

import java.util.*;
class E_S {
	
	Scanner teclado= new Scanner(System.in);
	
	public String CadenaTexto (String dato) {
		dato= teclado.nextLine();
		
		return dato;
	}
	
	public int EnteroTexto (int dato) {
		dato= teclado.nextInt();
		
		return dato;
	}
	
	public double DoubleTexto (double dato) {
		dato= teclado.nextDouble();
		
		return dato;
	}
	
	public char CaracterTexto (char dato) {
		dato= teclado.next().charAt(0);
		
		return dato;
	}

}
