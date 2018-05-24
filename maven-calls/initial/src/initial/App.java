package initial;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class App {

	public static void main(String[] args) {
		String[] aux = new String[4];
		
        aux[0] = args[0]; //folder com ontologias
        aux[1] = args[1]; //folder para rdf
		
		String operator = args[2].toLowerCase(); //top onto seleção
		
		aux[3] = args[3]; //seleção tecnica 
		System.out.println("\nOntologies at: " + aux[0] + "\n");
		switch(operator) {
			case "both":
				aux[2] = "dolce";
				sRUN(aux);
				
				aux[2] = "sumo";	
				sRUN(aux);
				break;
				
			case "dolce":

				sRUN(aux);
				break;
				
			case "sumo":

				sRUN(aux);
				break;
		}

	}
    
	public static void sRUN(String[] arguments) {
		String operator = arguments[3];

		switch(operator) {
			case "both":
				arguments[3] = "1";
				eRUN(arguments);
				
				arguments[3] = "2";
				eRUN(arguments);
				break;
			case "1":

				eRUN(arguments);
				break;
			case "2":

				eRUN(arguments);
				break;
		}
	//+----------------------------------------------+//	
	}
	
	public static void eRUN(String[] arguments) {
		/*
		args[0] //folder com ontologias
        args[1] //folder para rdf
		
		args[2] //top onto seleção
		
		args[3]; //seleção tecnica
		*/
		File file = new File(arguments[0]);
		File[] vec = file.listFiles();
		
		Process proc = null;
		
		String[] cmdarray = new String[5];
		cmdarray[0] = "java -jar ontoAli-pucrs-0.0.2.jar";
		cmdarray[2] = arguments[1];
		cmdarray[3] = arguments[2];
		cmdarray[4] = arguments[3];
		for(File f: vec) {
			if(f.isFile() && f.getName().endsWith(".owl") ) {
				cmdarray[1] = arguments[0] + "/" + f.getName();
				
				try {
						proc = Runtime.getRuntime().exec(cmdarray);
						if(proc != null) {
							InputStream in = proc.getInputStream();
							InputStream err = proc.getErrorStream();
							System.out.println(in);
							System.out.println(err);
						}

				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(f.getName());
			}
		}
	}
	
        
    }
