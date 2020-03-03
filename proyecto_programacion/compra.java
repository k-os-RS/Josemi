package proyecto_programacion;
import java.util.*;
class compra {
	//Atributos
	protected cliente cl;
	protected ArrayList<vehiculos> auto;
	protected String fechacompra, horacompra;
	
	//Constructores
	public compra () {
		
	}

	//Getters & Setters
	public cliente getCl() {
		return cl;
	}

	public void setCl(cliente cl) {
		this.cl = cl;
	}

	public ArrayList<vehiculos> getAuto() {
		return auto;
	}

	public void setAuto(ArrayList<vehiculos> auto) {
		this.auto = auto;
	}

	public String getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(String fechacompra) {
		this.fechacompra = fechacompra;
	}

	public String getHoracompra() {
		return horacompra;
	}

	public void setHoracompra(String horacompra) {
		this.horacompra = horacompra;
	}
}
