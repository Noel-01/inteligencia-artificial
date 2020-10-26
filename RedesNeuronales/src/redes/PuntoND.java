package redes;

// Punto usado en la red de neuronas, con varias entradas y salidas.
public class PuntoND {
	
	public final double[] entradas;
	public final double[] salidas;
	
	public PuntoND(String str, int numSalidas) {
		String[] contenido = str.split("/t");
		entradas = new double[contenido.length - numSalidas];
		for(int i = 0; i < entradas.length; i++) {
            entradas[i] = Double.parseDouble(contenido[i]);
        }
        salidas = new double[numSalidas];
        for (int i = 0; i < numSalidas; i++) {
            salidas[i] = Double.parseDouble(contenido[entradas.length + i]);
        }
	}

}
