package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import obj.Cita;
import obj.Persona;

public class Main {

	public static void main(String[] args) {
        ArrayList<Persona> listaPacientes = new ArrayList<>(); //<-------Tocado por fernando
		File pacientes = new File("pacientes.txt");
		
		//Crear el archivo TXT si no existiese
		try {
			if (!pacientes.exists()) {
				pacientes.createNewFile();
			}			
            
			// Crear la carpeta "Pacientes" si no existe
			File carpeta = new File("Pacientes");
			//Se crea el hilo productor para leer datos y meterlos al Array
			Thread hiloProductor = new Thread(() -> { //<-------Tocado por fernando
			try (Scanner sc = new Scanner(pacientes)) { //<-------Tocado por fernando
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
				int ID=Integer.parseInt(id);
				
				
				// Crear la subcarpeta con el ID del paciente (formato de 9 dígitos)
				String idFormato = String.format("%09d", Integer.parseInt(id));
				File subcarpeta = new File(carpeta, idFormato);
				subcarpeta.mkdir();
				
				// Crear el archivo "Datos Personales.xml" con la información personal del
				// paciente
				File datosPersonales = new File(subcarpeta, "Datos Personales.xml");
				PrintWriter pw;
				try { //<-------Tocado por fernando
					pw = new PrintWriter(datosPersonales);
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
				//Creamos la lista de citas
				ArrayList<Cita> pcitas = new ArrayList<>();  //<-------Tocado por fernando
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
						pcitas.add(new Cita(centro, especialidad, doctor, fecha, hora)); //<-------Tocado por fernando
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
				synchronized(listaPacientes) {
					listaPacientes.add(new Persona(ID, nombre, apellido1, apellido2, nacimiento, localidad, pcitas));
				}
				pw.println("</citas>");
				pw.close();
				} catch (FileNotFoundException e) { //<-------Tocado por fernando
					e.printStackTrace();//<-------Tocado por fernando
				}
                }
			} catch (FileNotFoundException e1) {//<-------Tocado por fernando
				e1.printStackTrace();//<-------Tocado por fernando
			}
            });
		

			Scanner sc = new Scanner(pacientes); //<-------Tocado por fernando
			// Leer la carpeta de un paciente y mostrar un resumen por consola
            System.out.println("Introduce el ID del paciente del que quieres ver el resumen:");
            sc = new Scanner(System.in);
            String idPaciente = sc.nextLine();
            
            // Buscar la subcarpeta con el ID del paciente
            File subcarpeta = new File(carpeta, idPaciente);
            if (subcarpeta.exists()) {
            	
                // Leer el archivo "Datos Personales.xml" y mostrar los datos personales
                File datosPersonales = new File(subcarpeta, "Datos Personales.xml");
                Scanner scDatos = new Scanner(datosPersonales);
                
                // Saltar las dos primeras líneas
                scDatos.nextLine();
                scDatos.nextLine();
                
                // Obtener el ID
                String id = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Obtener el nombre
                String nombre = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Obtener el apellido1
                String apellido1 = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Obtener el apellido2
                String apellido2 = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Obtener el nacimiento
                String nacimiento = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Obtener la localidad
                String localidad = scDatos.nextLine().replaceAll("<[^>]+>", "").trim();
                
                // Mostrar los datos personales
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre + " " + apellido1 + " " + apellido2);
                System.out.println("Nacimiento: " + nacimiento);
                System.out.println("Residencia: " + localidad);
                scDatos.close();
                
                // Leer el archivo "Citas.xml" y mostrar el historial de citas médicas
                File citas = new File(subcarpeta, "Citas.xml");
                Scanner scCitas = new Scanner(citas);
                
                // Saltar las dos primeras líneas
                //scCitas.nextLine();
                //scCitas.nextLine();
                
                // Mostrar el título de las citas
                System.out.println("Citas:");
                
                // Recorrer las líneas que empiezan por "\t<cita>" hasta encontrar una línea con "</citas>"
                while (scCitas.hasNextLine()) {
                    String linea = scCitas.nextLine();
                    if (linea.equals("</citas>")) {
                        break;
                    }
                    if (linea.equals("\t<cita>")) {
                    	
                        // Obtener el centro
                        String centro = scCitas.nextLine().replaceAll("<[^>]+>", "").trim();
                        
                        // Obtener la especialidad
                        String especialidad = scCitas.nextLine().replaceAll("<[^>]+>", "").trim();
                        
                        // Obtener el doctor
                        String doctor = scCitas.nextLine().replaceAll("<[^>]+>", "").trim();
                        
                        // Obtener la fecha
                        String fecha = scCitas.nextLine().replaceAll("<[^>]+>", "").trim();
                        
                        // Obtener la hora
                        String hora = scCitas.nextLine().replaceAll("<[^>]+>", "").trim();
                        
                        // Mostrar la cita
                        System.out.println("> " + centro + ". " + especialidad + ". " + doctor + ". El " + fecha + " a las " + hora);
                        
                        // Saltar la línea con "\t</cita>"
                        scCitas.nextLine();
                    }
                }
                scCitas.close();
            } else {
                System.out.println("No se ha encontrado la carpeta del paciente con el ID " + idPaciente);
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
