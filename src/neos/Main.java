package neos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		//declaramos el fichero a leer
		String fichero = "NEOs.txt";
		
		//creamos lista donde guardar los neos
		ArrayList<String> neos = new ArrayList<>();
		
		//declaramos variable para almacenar los datos de cada linea de la lista para ser tratados 
		//por el metodo calculaprobabilidad
		String procesaNeo;
		
		//creamos la lista donde almacenaremos los neos procesados.
		ArrayList<String> neosProcesados = new ArrayList<>();
		
		//iniciamos proceso de lectura, que guardara los datos en una lista, para despues convertir cada elemento en string 
		//y pasarselo al metodo calculaprobabilidad,
		//que devuelve un string con el nombre del neo y la probabilidad,
		//que escribiremos en un fichero que llevara el nombre del neo y dentro su nombre mas probabilidad
		//almacenaremos los datos del metodo calculaprobabilidad en una lista para poder mostrar por pantalla el msg de advertencia
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			ProcessBuilder builder;
			Process process;
			String[] neo;
			
			while(br.readLine() != null) {	//SOLO LEE 6 LINEAS, UNA SI Y UNA NO ????
				String lineaNeo = br.readLine();
				neos.add(lineaNeo);
				//System.out.println(lineaNeo);
				
				/*String clase = "neos.CalculaProbabilidad";
				String javaHome = System.getProperty("java.home");
				String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
				String classpath = System.getProperty("java.class.path");
				String className = clase;
				ArrayList <String> command = new ArrayList<>();
				command.add(javaBin);
				command.add("-cp");
				command.add(classpath);
				command.add(className);
				neo = br.readLine().split(",");
				command.add(neo[0]);
				command.add(neo[1]);
				command.add(neo[2]);
				System.out.println(command);
				
				builder = new ProcessBuilder(command);
				process = builder.inheritIO().start();*/
				
			}
			br.close();
			
			for(int i=0; i<neos.size(); i++) {
				procesaNeo = neos.get(i);
				//almacena el string procedente del calculaProbabilidad con el nombre y la probabilidad
				String neoProcesado = probabilidad(procesaNeo);
				neosProcesados.add(neoProcesado);
				//System.out.println(neosProcesados.get(i));
			}
			
			for(int i = 0; i < neosProcesados.size(); i++) {
				//asignamos a string y se la pasamos al metodo
				String neoAnalizar = neosProcesados.get(i);
				analisisDatos(neoAnalizar);
				//System.out.println(neoAnalizar);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		escribeArchivo(neosProcesados);
		System.out.println("Finalizada escritura!");

	}
	
	public static String probabilidad(String neo) {
		String[] neos = neo.split(",");
		double res = 0;
		double resultado = 0;
		
		

        //CALCULAR PROBABILIDADES NEOS

         //metodo para calcular
		
         double posicionNEO = Double.parseDouble(neos[1]);
         double velocidadNEO = Double.parseDouble(neos[2]);
         double posicionTierra = 1;
         double velocidadTierra = 100;
         for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
            posicionNEO = posicionNEO + velocidadNEO * i;
            posicionTierra = posicionTierra + velocidadTierra * i;
          }
         res = 100 * Math.random() *
         Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
         resultado = Math.round(res *100.0)/100.0;
         //System.out.println(resultado);	
        
         
         //creamos un string donde almacenamos el nombre del neo y la probabilidad(resultado)
         String probabilidadNeo = neos[0] + "," + String.valueOf(resultado);
         
		return probabilidadNeo;

	}
	
	//Reescribir mensaje
	public static void analisisDatos(String neo) {
		//recibe un string convertido de la lista de neos procesados, analiza la probabilidad y muestra un mensaje
		
		//convierte en array
		String[] neoAnalizar = neo.split(",");
		//asignamos a variables
		String nombre = neoAnalizar[0];
		Double probabilidad = Double.parseDouble(neoAnalizar[1]);
		
		if(probabilidad >= 10) {
			System.err.println("VAMOS A MORIR -- " + nombre + " - " + probabilidad);
		}else {
			System.out.println("CALMA, " + nombre + " - " + probabilidad);
		}
		//return null;
		
	}
	
	public static void escribeArchivo(ArrayList neos) {
		//Recibe un arrayList, del cual pasaremos a arrayString cada linea
		//Declaramos el nombre del fichero, que sera la posicion 0 del string recibido
		
		String[] neo;
		String nombreFichero = null;
		File fichero;
		FileWriter fw;
		BufferedWriter bw;
		System.out.println("iniciando escritura en fichero...");
		
		try {
			for(int i = 0; i<neos.size(); i++) {
				neo = ((String) neos.get(i)).split(",");
				nombreFichero = neo[0];
				fichero = new File(nombreFichero);
				fw = new FileWriter(fichero);
				bw = new BufferedWriter(fw);
				bw.write(neo[1]);
				bw.close();
				fw.close();			
			}
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Metodo para escribir en ficheros con el procesBuilder
	/*public static void escribeFicheroBuilder(ArrayList neos) {
		File directorio = new File("D:\\javaprojects\\Neos");
		File ficheroNeo;
		String[] neo;
		String nombreFichero;
		String clase = "neos.CalculaProbabilidad";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clase;
		ArrayList<String> command = new ArrayList<>();
		ProcessBuilder builder;
		
		for(int i = 0; i<neos.size(); i++) {
			neo = ((String) neos.get(i)).split(",");
			nombreFichero = neo[0];
			ficheroNeo = new File(nombreFichero);
			String valor = neo[1];
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(valor);
			builder = new ProcessBuilder();
			builder.directory(directorio);
			builder.redirectOutput(ficheroNeo);
			try {
				Process p = builder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}*/

}
