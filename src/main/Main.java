package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File pacientes = new File("pacientes.txt");
		
		//Crear el archivo TXT si no existiese
		try {
			if (!pacientes.exists()) {
				pacientes.createNewFile();
			}
			
			// Leer el archivo TXT con los datos de los pacientes
			Scanner sc = new Scanner(pacientes);
			
			// Crear la carpeta "Pacientes" si no existe
			File carpeta = new File("Pacientes");

			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

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
				
				// Crear la subcarpeta con el ID del paciente (formato de 9 dígitos)
				String idFormato = String.format("%09d", Integer.parseInt(id));
				File subcarpeta = new File(carpeta, idFormato);
				subcarpeta.mkdir();
				
				// Crear el archivo "Datos Personales.xml" con la información personal del
				// paciente
				File datosPersonales = new File(subcarpeta, "Datos Personales.xml");
				PrintWriter pw = new PrintWriter(datosPersonales);
				pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.println("<paciente>");
				pw.println("\t<id>" + id + "</id>");
				pw.println("\t<nombre>" + nombre + "</nombre>");
				pw.println("\t<apellido1>" + apellido1 + "</apellido1>");
				pw.println("\t<apellido2>" + apellido2 + "</apellido2>");
				pw.println("\t<nacimiento>" + nacimiento + "</nacimiento>");
				pw.println("\t<localidad>" + localidad + "</localidad>");
				pw.println("</paciente>");
				pw.close();
				
				// Crear el archivo "Citas.xml" con la información de su historial de citas
				// médicas
				File citas = new File(subcarpeta, "Citas.xml");
				pw = new PrintWriter(citas);
				pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.println("<citas>");
				
				// Recorrer las líneas que empiezan por "#" hasta encontrar una línea vacía o el
				// final del archivo
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
						// Escribir los datos de la cita en formato XML
						pw.println("\t<cita>");
						pw.println("\t\t<centro>" + centro + "</centro>");
						pw.println("\t\t<especialidad>" + especialidad + "</especialidad>");
						pw.println("\t\t<doctor>" + doctor + "</doctor>");
						pw.println("\t\t<fecha>" + fecha + "</fecha>");
						pw.println("\t\t<hora>" + hora + "</hora>");
						pw.println("\t</cita>");
					}
				}
				pw.println("</citas>");
				pw.close();
			}
			sc.close();
			System.out.println("Aplicacion terminada con exito.");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo pacientes.txt");
		} catch (IOException e) {
			System.out.println("Archivo pacientes.txt no encontrado, creando...");
		}
	}

}
