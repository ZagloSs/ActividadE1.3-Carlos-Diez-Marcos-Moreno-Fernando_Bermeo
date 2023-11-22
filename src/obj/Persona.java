package obj;

public class Persona {
	private long ID;
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private String Nacimiento;
	private String LocalidadResidencia;
	private String Centro; 	
	private String Especialidad;
	private String Doctor;
	private String Fecha;
	private String Hora;
	
	
	//Constructor
	public Persona(long iD, String nombre, String apellido1, String apellido2, String nacimiento,
			String localidadResidencia, String centro, String especialidad, String doctor, String fecha, String hora) {
		ID = iD;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		Nacimiento = nacimiento;
		LocalidadResidencia = localidadResidencia;
		Centro = centro;
		Especialidad = especialidad;
		Doctor = doctor;
		Fecha = fecha;
		Hora = hora;
	}
	
	
	
	//Getters y Setters
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
	public String getCentro() {
		return Centro;
	}
	public void setCentro(String centro) {
		Centro = centro;
	}
	public String getEspecialidad() {
		return Especialidad;
	}
	public void setEspecialidad(String especialidad) {
		Especialidad = especialidad;
	}
	public String getDoctor() {
		return Doctor;
	}
	public void setDoctor(String doctor) {
		Doctor = doctor;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	
	
	
}
