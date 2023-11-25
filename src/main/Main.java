package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import hilos.Consumidor;
import hilos.Productor;
import obj.Persona;

public class Main {

	public static void main(String[] args) {
		File pacientes = new File("pacientes.txt");
		Scanner sc;
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		ArrayList<Consumidor> hilosConsumidores = new ArrayList<Consumidor>();
		
		//Crear el archivo TXT si no existiese
		try {
			if (!pacientes.exists()) {
				pacientes.createNewFile();
			}
			
			

			
			// Crear la carpeta "Pacientes" si no existe
			File carpeta = new File("Pacientes");

			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

			//Lanzar el hilo productor para que genere los objetos
			Productor hiloProductor = new Productor(listaPersonas);
			hiloProductor.start();
			hiloProductor.join();
			
			//Lanzar el hilo consumidor para que cree las carpetas
			for(int i = 0; i<listaPersonas.size(); i++) {
				hilosConsumidores.add(new Consumidor(listaPersonas.get(i)));
				hilosConsumidores.get(i).start();
				hilosConsumidores.get(i).join();
			}
			
			
			
			
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
                scCitas.nextLine();
                scCitas.nextLine();
                
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
