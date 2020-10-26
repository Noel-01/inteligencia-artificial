package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Aplicacion {
	
	public static void main(String [] args) {
		Aplicacion app = new Aplicacion();
		System.out.println("Arrancado");
	}
	
	protected void lanzar() {
		String[] contenido = leerArchivo("abalone_norm.txt", false);
		//Añadir sistema
	}
	
	protected String[] leerArchivo(String nombreArchivo, boolean eliminarEncabezado) {
		try {
			ArrayList<String> lineas = new ArrayList<>();
			BufferedReader buffer = new BufferedReader(new FileReader(nombreArchivo));
			String linea;
			
			while((linea = buffer.readLine()) != null) {
				lineas.add(linea);
			}
			
			buffer.close();
			if(eliminarEncabezado) {
				lineas.remove(0);
			}
			String[] contenido = lineas.toArray(new String[lineas.size()]);
			return contenido;
			
		} catch(IOException e) {
			System.err.println(e.toString());
			return null;
		}
	}
	
//    @Override
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

}
