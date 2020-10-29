package redes;

// Gestion de la red de neuronas
public class RedNeuronal {

	 protected Neurona[] neuronasOcultas;
	    protected Neurona[] neuronasSalida;
	    protected int numEntradas;
	    protected int numOcultas;
	    protected int numSalidas;
	    
	    public RedNeuronal(int _numEntradas, int _numOcultas, int _numSalidas) {
	        numEntradas = _numEntradas;
	        numOcultas = _numOcultas;
	        numSalidas = _numSalidas;
	        
	        neuronasOcultas = new Neurona[numOcultas];
	        for (int i = 0; i < numOcultas; i++) {
	            neuronasOcultas[i] = new Neurona(_numEntradas);
	        }
	        
	        neuronasSalida = new Neurona[numSalidas];
	        for (int i = 0; i < numSalidas; i++) {
	            neuronasSalida[i] = new Neurona(numOcultas);
	        }
	    }
	    
	    protected double[] Evaluar(PuntoND punto) {
	        // Eliminamos la salida anterior
	        for (Neurona n : neuronasOcultas) {
	            n.eliminar();
	        }
	        for (Neurona n : neuronasSalida) {
	            n.eliminar();
	        }
	        
	        // Cálculo de las salidas de las neuronas ocultas
	        double[] salidasOcultas = new double[numOcultas];
	        for (int i = 0; i < numOcultas; i++) {
	            salidasOcultas[i] = neuronasOcultas[i].evaluar(punto);
	        }
	        
	       // Cálculo de las salidas de las neuronas de salida
	        double[] salidas = new double[numSalidas];
	        for (int i = 0; i < numSalidas; i++) {
	            salidas[i] = neuronasSalida[i].evaluar(salidasOcultas);
	        }
	        
	        return salidas;
	    }
	    
	    protected void AjustarPeso(PuntoND punto, double tasaAprendizaje) {
	        double[] deltasSalida = calcularDeltasSalida(punto);
	        double[] deltasOcultos = calcularDeltasOcultos(deltasSalida);
	        ajustarPesoSalida(deltasSalida, tasaAprendizaje);
	        ajustarPesoOcultos(deltasOcultos, tasaAprendizaje, punto);
	    }
	    
	    private double[] calcularDeltasSalida(PuntoND punto) {
	        double[] deltasSalida = new double[numSalidas];
	        for (int i = 0; i < numSalidas; i++) {
	            double salidaObtenida = neuronasSalida[i].salida;
	            double salidaEsperada = punto.salidas[i];
	            deltasSalida[i] = salidaObtenida * (1 - salidaObtenida) * (salidaEsperada - salidaObtenida);
	        }
	        return deltasSalida;
	    }
	    
	    private double[] calcularDeltasOcultos(double[] deltasSalida) {
	        double[] deltasOcultos = new double[numOcultas];
	        for (int i = 0; i < numOcultas; i++) {
	            double salidaObtenida = neuronasOcultas[i].salida;
	            double suma = 0.0;
	            for (int j = 0; j < numSalidas; j++) {
	                suma += deltasSalida[j] * neuronasSalida[j].getPeso(i);
	            }
	            deltasOcultos[i] = salidaObtenida * (1 - salidaObtenida) * suma;
	        }
	        return deltasOcultos;
	    }
	    
	    private void ajustarPesoSalida(double[] deltasSalida, double tasaAprendizaje) {
	        double valor;
	        for (int i = 0; i < numSalidas; i++) {
	            Neurona neuronaSalida = neuronasSalida[i];
	            for (int j = 0; j < numOcultas; j++) {
	                valor = neuronaSalida.getPeso(j) + tasaAprendizaje * deltasSalida[i] * neuronasOcultas[j].getSalida();
	                neuronaSalida.setPeso(j, valor);
	            }
	            valor = neuronaSalida.getPeso(numOcultas) + tasaAprendizaje * deltasSalida[i] * 1.0;
	            neuronaSalida.setPeso(numOcultas, valor);
	        }
	    }
	    
	    private void ajustarPesoOcultos(double[] deltasOcultos, double tasaAprendizaje, PuntoND punto) {
	        double valor;
	        for (int i = 0; i < numOcultas; i++) {
	            Neurona neuronaOculto = neuronasOcultas[i];
	            for (int j = 0; j < numEntradas; j++) {
	                valor = neuronaOculto.getPeso(j) + tasaAprendizaje * deltasOcultos[i] * punto.entradas[j];
	                neuronaOculto.setPeso(j, valor);
	            }
	            valor = neuronaOculto.getPeso(numEntradas) + tasaAprendizaje * deltasOcultos[i] * 1.0;
	            neuronaOculto.setPeso(numEntradas, valor);
	        }
	    }
}
