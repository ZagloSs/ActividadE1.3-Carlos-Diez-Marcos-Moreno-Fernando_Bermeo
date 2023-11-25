package hilos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import obj.Cita;
import obj.Persona;

public class Consumidor extends Thread {
	private Persona persona;
	
	
	
	public Consumidor(Persona persona) {
		super();
		this.persona = persona;
	}



	public void run() {
		
		try {
		
		// Crear la subcarpeta con el ID del paciente (formato de 9 dígitos)
		File subcarpeta = new File("Pacientes", String.valueOf(persona.getiD()));
		subcarpeta.mkdir();
		// Crear el archivo "Datos Personales.xml" con la información personal del
		// paciente
		File datosPersonales = new File(subcarpeta, "Datos Personales.xml");
		
		PrintWriter pw = new PrintWriter(datosPersonales);
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<paciente>");
		pw.println("\t<id>" + persona.getiD() + "</id>");
		pw.println("\t<nombre>" + persona.getNombre() + "</nombre>");
		pw.println("\t<apellido1>" + persona.getApellido1() + "</apellido1>");
		pw.println("\t<apellido2>" + persona.getApellido2() + "</apellido2>");
		pw.println("\t<nacimiento>" + persona.getNacimiento() + "</nacimiento>");
		pw.println("\t<localidad>" + persona.getLocalidadResidencia() + "</localidad>");
		pw.println("</paciente>");
		pw.close();

		// Crear el archivo "Citas.xml" con la información de su historial de citas
		// médicas
		File citasFile = new File(subcarpeta, "Citas.xml");
		
		

		pw = new PrintWriter(citasFile);
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<citas>");
		
		for(Cita citas: persona.getCitas()) {
				
		// Recorrer el array de citas que tenga la persona en cuestion
				// Escribir los datos de la cita en formato XML
				pw.println("\t<cita>");
				pw.println("\t\t<centro>" + citas.getCentro() + "</centro>");
				pw.println("\t\t<especialidad>" + citas.getEspecialidad() + "</especialidad>");
				pw.println("\t\t<doctor>" + citas.getDoctor() + "</doctor>");
				pw.println("\t\t<fecha>" + citas.getFecha() + "</fecha>");
				pw.println("\t\t<hora>" + citas.getHora()+ "</hora>");
				pw.println("\t</cita>");
			}
		
		pw.println("</citas>");
		pw.close();
		
		
		}catch(IOException e) {
		
		}
		
		
	}

}
