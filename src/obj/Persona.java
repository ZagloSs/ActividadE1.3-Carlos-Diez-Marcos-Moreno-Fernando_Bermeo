package obj;

import java.util.ArrayList;

public class Persona {
	private int iD;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nacimiento;
	private String localidadResidencia;
	private ArrayList<Cita> citas;
	
	
	public Persona(int iD, String nombre, String apellido1, String apellido2, String nacimiento,
			String localidadResidencia, ArrayList<Cita> citas) {
		this.iD = iD;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacimiento = nacimiento;
		this.localidadResidencia = localidadResidencia;
		this.citas = citas;
	}


	public int getiD() {
		return iD;
	}


	public void setiD(int iD) {
		this.iD = iD;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getNacimiento() {
		return nacimiento;
	}


	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}


	public String getLocalidadResidencia() {
		return localidadResidencia;
	}


	public void setLocalidadResidencia(String localidadResidencia) {
		this.localidadResidencia = localidadResidencia;
	}


	public ArrayList<Cita> getCitas() {
		return citas;
	}


	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}
}
