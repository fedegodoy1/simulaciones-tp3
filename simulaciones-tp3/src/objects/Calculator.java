/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import static java.lang.Math.random;
import java.util.Random;

public class Calculator {
    public static float[] calculatorExp(int size, Exponencial distribucion){
        Random random = new Random();
        float[] vec = new float[size];
        float op = 0;
        float randomValue=0; 
        float mediaNegativa = (float) distribucion.getMedia()*-1;
        
        for (int i = 0; i < vec.length; i++) {
            randomValue = random.nextFloat();
            op = mediaNegativa * (float)Math.log(1-randomValue);
            vec[i]=op;
        }
        
        return vec;
    }
    public static float[] calculatorNormal(int size, Normal distribucion){
        Random random = new Random();
        float[] vec = new float[size];
        float z = 0, randomValue=0, sumaRandom=0, acum=0;
        
        for (int i = 0; i < size; i++) {
            acum = 0;
            for (int j = 0; j < vec.length; j++) {
                acum += random.nextFloat();
            }
            z = ((acum-6)*(float)distribucion.getDesviacionEstandar()) + (float)distribucion.getMedia();
            vec[i]=z;
        }
        return vec;
    }
    
     public float[][][] matrizFrecuenciaUniforme(Uniforme uniforme, float rango, int intervalos){
        float [][][] m = armadoRangos(uniforme.getDesde(), rango, intervalos);
        float [] randomVec = uniforme.getVecValores();
        
        for (int i = 0; i < randomVec.length; i++) {
            for (int j = 0; j < intervalos; j++) {
                
                //m[j][j][0] tiene el desde y el m[j][j][1] el hasta, y m[j][j][2] la frecuencia obtenida
                if (randomVec[i]<m[j][j][1]) {
                    m[j][j][2]++;
                    break;
                }
            }
        }        
        return m;
    }
     
     public float[][][] armadoRangos(int desde,float rango,int intervalos){
     float [][][] m = new float [intervalos][intervalos][3];
     float inicio = desde;
     float hasta = desde+rango;
     
         for (int i = 0; i < m.length; i++) {
             m[i][i][0]= inicio;
             m[i][i][1]=hasta;
             inicio = hasta;
             hasta+=rango;
         }
             return m;
     }
     
    public static void quicksort(float valores[], int izq, int der)
    {

        float pivote = valores[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        float aux;

        while (i < j)
        {            // mientras no se crucen las búsquedas
            while (valores[i] <= pivote && i < j)
            {
                i++; // busca elemento mayor que pivote
            }
            while (valores[j] > pivote)
            {
                j--;         // busca elemento menor que pivote
            }
            if (i < j)
            {                      // si no se han cruzado                      
                aux = valores[i];                  // los intercambia
                valores[i] = valores[j];
                valores[j] = aux;
            }
        }
        valores[izq] = valores[j]; // se coloca el pivote en su lugar de forma que tendremos
        valores[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1)
        {
            quicksort(valores, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der)
        {
            quicksort(valores, j + 1, der); // ordenamos subarray derecho
        }
        
    }
        
     public float[][] matrizFrecuenciaExponencial(float valores[], float rango, int intervalos, float minimo)
    {
        //Se arranca con el extremo minimo y vamos agregando el rango definido a cada intervalo
        float[][] vectorFrecuencias = armadoRangosExponencial(minimo, rango, intervalos);

        for (int i = 0; i < valores.length; i++)
        {
            for (int j = 0; j < intervalos; j++)
            {

                //vectorFrecuencias[j][0] tiene el desde y el vectorFrecuencias[j][1] el hasta,
                //y vectorFrecuencias[j][2] la frecuencia obtenida
                // Si el valor actual es menor al extremo superior del intervalo
                if (valores[i] < vectorFrecuencias[j][1])
                {
                    // Incremento Frecuencia
                    vectorFrecuencias[j][2]++;
                    break;
                }
            }
        }
        return vectorFrecuencias;
    }

    private float[][] armadoRangosExponencial(float minimo, float rango, int intervalos)
    {
        float[][] vectorFrecuencias = new float[intervalos][3];
        //  [0] es el limite inferior del intervalo
        //  [1] es el limite superior del intervalo
        //  [2] es la frecuencia del intervalo
        
        float inicio = minimo;
        float hasta = minimo + rango;

        for (int i = 0; i < vectorFrecuencias.length; i++)
        {
            vectorFrecuencias[i][0] = inicio;
            vectorFrecuencias[i][1] = hasta;
            inicio = hasta;
            hasta += rango;
        }
        return vectorFrecuencias;
    }
}
