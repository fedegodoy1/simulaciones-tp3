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
}
