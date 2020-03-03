package proyecto_programacion;
import java.util.*;
class venta {
	//Atributos
	protected cliente cl;
	protected ArrayList<vehiculos> auto;
	protected String fechaventa, horaventa;
	
	//Constructores
	public venta () {
		
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

	public String getFechaventa() {
		return fechaventa;
	}

	public void setFechaventa(String fechaventa) {
		this.fechaventa = fechaventa;
	}

	public String getHoraventa() {
		return horaventa;
	}

	public void setHoraventa(String horaventa) {
		this.horaventa = horaventa;
	}
	
}
