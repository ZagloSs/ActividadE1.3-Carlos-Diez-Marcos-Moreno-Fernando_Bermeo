package obj;

public class Cita {
	private String Centro;
	private String Especialidad;
	private String Doctor;
	private String Fecha;
	private String Hora;
	
	
	//Constructor
	public Cita(String centro, String especialidad, String doctor, String fecha, String hora) {
		Centro = centro;
		Especialidad = especialidad;
		Doctor = doctor;
		Fecha = fecha;
		Hora = hora;
	}


	//Getter y Setters
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
