package hilos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import obj.Cita;
import obj.Persona;

public class Productor extends Thread{
	ArrayList<Persona> listaPersonas;

	public Productor(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}


	public void run() {
		try {
		File pacientes = new File("pacientes.txt");
		// Leer el archivo TXT con los datos de los pacientes
		Scanner sc = new Scanner(pacientes);
		
		File carpeta = new File("Pacientes");
		ArrayList<Cita> citas = new ArrayList<Cita>();
		
		// Recorrer el archivo línea por línea
		while (sc.hasNextLine()) {
			
			// Obtener los datos personales del paciente (primera línea)
			String[] datos = sc.nextLine().split(";");
			String id = datos[0];
			String nombre = datos[1];
			String apellido1 = datos[2];
			String apellido2 = datos[3];
			String nacimiento = datos[4];
			String localidad = datos[5];
			String idFormato = String.format("%09d", Integer.parseInt(id));
			
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				if (linea.isEmpty()) {
					break;
				}
				if (linea.startsWith("#")) {
					// Obtener los datos de la cita médica
					String[] datosCita = linea.substring(1).split(";");
					String centro = datosCita[0];
					String especialidad = datosCita[1];
					String doctor = datosCita[2];
					String fecha = datosCita[3];
					String hora = datosCita[4];
					citas.add(new Cita(centro, especialidad, doctor, fecha, hora));
				}
				
				
			}
			
			listaPersonas.add(new Persona(Integer.parseInt(idFormato), nombre, apellido1, apellido2, nacimiento, localidad, citas));
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
