package obj;

import java.util.ArrayList;

public class Persona {
	private long ID;
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private String Nacimiento;
	private String LocalidadResidencia;
	private ArrayList<Cita> citas;
	
	
	
	public Persona(long iD, String nombre, String apellido1, String apellido2, String nacimiento,
			String localidadResidencia, ArrayList<Cita> citas) {


		this.ID = iD;
		this.Nombre = nombre;
		this.Apellido1 = apellido1;
		this.Apellido2 = apellido2;
		this.Nacimiento = nacimiento;
		this.LocalidadResidencia = localidadResidencia;
		this.citas = citas;
	}



	public long getID() {
		return ID;
	}



	public void setID(long iD) {
		ID = iD;
	}



	public String getNombre() {
		return Nombre;
	}



	public void setNombre(String nombre) {
		Nombre = nombre;
	}



	public String getApellido1() {
		return Apellido1;
	}



	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}



	public String getApellido2() {
		return Apellido2;
	}



	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}



	public String getNacimiento() {
		return Nacimiento;
	}



	public void setNacimiento(String nacimiento) {
		Nacimiento = nacimiento;
	}



	public String getLocalidadResidencia() {
		return LocalidadResidencia;
	}



	public void setLocalidadResidencia(String localidadResidencia) {
		LocalidadResidencia = localidadResidencia;
	}



	public ArrayList<Cita> getCitas() {
		return citas;
	}



	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}
	
	
	
	
	
	
	
	
}
