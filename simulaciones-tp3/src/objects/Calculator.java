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
            op = mediaNegativa* (float)Math.log(1-randomValue);
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
        float [][][] m = armadoVector(rango, intervalos);
        float comparador;
        float [] randomVec = uniforme.getVecValores();
        
        for (int i = 0; i < randomVec.length; i++) {
            comparador = rango;
            for (int j = 0; j < intervalos; j++) {
                if (randomVec[i]<comparador) {
                    m[j][j][2]++;
                    break;
                }
                else{
                    comparador = comparador + rango;
                }
            }
        }        
        return m;
    }
     
     public float[][][] armadoVector(float rango,int intervalos){
     float [][][] m = new float [intervalos][intervalos][3];
     float desde = rango;
     float hasta = desde+rango;
     
         for (int i = 0; i < m.length; i++) {
             m[i][i][0]= desde;
             m[i][i][1]=hasta;
             desde = hasta;
             hasta+=rango;
         }
             return m;
     }
}
