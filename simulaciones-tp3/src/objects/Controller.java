/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Random;
import front.*;
/**
 *
 * @author federico
 */
public class Controller {
    private static Controller controller;
    static Main main;
    static Normal normal;
    static Exponencial expo;
    static Poisson poisson;
    static GeneradorUniforme uniforme;
    
    protected Controller(Main menu) {
        main = menu;
        uniforme = new GeneradorUniforme(this);
    }
    
    public static Controller getInstance(Main menu) {
        if (controller == null)
        {
            controller = new Controller(menu);
        }
        else
        {
            controller.setMain(menu);
        }

        return controller;
    }
    
    public void setMain(Main menu)
    {
        main = menu;
    }
    
    public void showMenu() {
        main.setVisible(true);
    }
    
    public void selectedUniforme() {
        main.setVisible(false);
        this.uniforme.setVisible(true);
    }
    
    
    public void randomFloatUniforme(int size, int desde, int hasta){
        Random random = new Random();
        float[] vecValores = new float[size];
        float randomValue=0, operacion = 0;
        
        
        for (int i = 0; i < vecValores.length; i++) {
            randomValue = random.nextFloat();
            operacion = desde + (randomValue*(hasta - desde));
            vecValores[i]=operacion;
        }
        TablaUniforme table = new TablaUniforme(this,vecValores,desde,hasta);
        table.setVisible(true);
    }
    
    
    
}
