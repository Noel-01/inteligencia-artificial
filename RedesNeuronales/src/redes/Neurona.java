package redes;

import java.util.Random;

// Neruna formal de nuestra red
public class Neurona {
	
	protected double[] pesos;
	protected double salida;
	
	
	public Neurona(int numEntradas) {
		salida = Double.NaN;
		
		Random generador = new Random();
		pesos = new double[numEntradas + 1];
		for(int i = 0; i < numEntradas + 1; i++) {
			pesos[i] = generador.nextDouble() * 2.0 - 1.0;
		}
	}
	
    protected double evaluar(double[] entradas) {
        if (Double.isNaN(salida)) {
            double x = 0.0;
            int numEntradas = entradas.length;
            for (int i = 0; i < numEntradas; i++) {
                x += entradas[i] * pesos[i];
            }
            x += pesos[numEntradas];
            salida = 1.0 / (1.0 + Math.exp(-1.0 * x));
        }
        
        return salida;
    }
	
    
	public double getSalida() {
		return salida;
	}
	
	public double getPeso(int index) {
		return pesos[index];
	}
	
	public void setPeso(int index, double valor) {
		pesos[index] = valor;
	}
	
    protected double evaluar(PuntoND punto) {
        return evaluar(punto.entradas);
    }
    
    protected void eliminar() {
        salida = Double.NaN;
    }

}
