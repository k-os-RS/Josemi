package proyecto_programacion;
class menus {
	
	protected void MenuPrincipal () {
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
	protected void MenuAdmin () {
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
	}
	protected void MenuAsesor () {
		System.out.println("");
		System.out.println(" [1] Vender vehiculo");
		System.out.println(" [2] Comprar vehiculo");
		System.out.println(" [3] Mostrar vehiculos");
		System.out.println(" [4] Mostrar cliente");
		System.out.println(" [5] Modificar cliente");
		System.out.println(" [6] Enviar a mecanico");
		System.out.println(" [7] Devolver vehiculo reparado");
		System.out.println(" [8] Cerrar sesi\u00f3n");
		System.out.println("");
		System.out.print("Elija una opci\u00f3n: ");
	}
	protected void MenuMecanico () {
		System.out.println("");
		System.out.println(" [1] Mostrar Vehiculo");
		System.out.println(" [2] Marcar vehiculo reparado");
		System.out.println(" [3] Vehiculos en reparaci\u00f3n");
		System.out.println(" [4] Cerrar sesi\uf003n");
		System.out.println("");
		System.out.print("Elija una opci\u00f3n: ");
	}
	protected void MenuMostrarEmpleado() {
		System.out.println("\n == MOSTRAR EMPLEADO == ");
		System.out.println("");
		System.out.println(" [1] Mostrar a todos los asesores");
		System.out.println(" [2] Mostrar a todos las mecanicos");
		System.out.println(" [3] Mostrar un empleado espec\u00edfico");
		System.out.println(" [4] Salir");
		System.out.println("");
		System.out.print("Elige una opci\u00f3n: ");
	}
	protected void MenuModificarEmpleado () {
		System.out.println("\nPor favor indique el dato que desea cambiar");
		System.out.println("");
		System.out.println(" [1] DNI");
		System.out.println(" [2] Nombre");
		System.out.println(" [3] Apellidos");
		System.out.println(" [4] Telefono m\u00f3vil");
		System.out.println(" [5] Fecha de nacimiento");
		System.out.println(" [6] Cuenta bancaria");
		System.out.println(" [7] Direccion");
		System.out.println(" [8] Tipo de trabajo");
		System.out.println(" [9] Sueldo");
		System.out.println(" [10] Cancelar");
		System.out.println("");
		System.out.print("Elige una opci\u00f3n: ");
	}
	protected void MenuMostrarVehiculo () {
		System.out.println("\n == MOSTRAR VEHICULO == ");
		System.out.println("");
		System.out.println(" [1] Mostrar todos los coches");
		System.out.println(" [2] Mostrar todos las motos");
		System.out.println(" [3] Mostrar un veh\u00edculo espec\u00edfico");
		System.out.println(" [4] Salir");
		System.out.println("");
		System.out.print("Elige una opci\u00f3n: ");
	}
	protected void MenuModificarVehiculo () {
		System.out.println("\nPor favor indique el dato que desea cambiar");
		System.out.println("");
		System.out.println(" [1] Matr\u00edcula");
		System.out.println(" [2] Modelo");
		System.out.println(" [3] Color");
		System.out.println(" [4] Combustible");
		System.out.println(" [5] Plazas");
		System.out.println(" [6] Precio");
		System.out.println(" [7] Kil\u00f3metros");
		System.out.println(" [8] Cancelar");
		System.out.println("");
		System.out.print("Elige una opci\u00f3n: ");
	}
	protected void MenuConfirmacion () {
		System.out.println(" [1] Si");
		System.out.println(" [2] No");
		System.out.print("\nOpci\u00f3n: ");
	}

}
