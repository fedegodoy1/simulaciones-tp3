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
    private String tipoG;
    static Main main;
    static Normal normal;
    static Exponencial expo;
    static Poisson poisson;
    static UniformeGenerator uniforme;
    static Generator generator;
    
    protected Controller(Main menu) {
        main = menu;
        uniforme = new UniformeGenerator(this);
        generator = new Generator(this);
        tipoG = "";
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
    
    public void selectedGenerator(String tipo) {
        tipoG = tipo;
        main.setVisible(false);
        this.generator.setVisible(true);
        this.generator.setGeneratorType(tipo);
    }
    public String getTipo(){
        return tipoG;
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
        UniformeTable table = new UniformeTable(this,vecValores,desde,hasta);
        table.setVisible(true);
    }
    
    public void randomFloatExponencial(int size, float media){
        Exponencial e = new Exponencial(media);
        float[] vec = Calculator.calculatorExp(size, e);
        String[] datosUsados = new String[3];
        datosUsados[0] = ""+size;
        datosUsados[1] = ""+media;
        //datosUsados[2] = ""+desviacion
        GeneratorTable exp = new GeneratorTable(this,vec,"Exponencial",datosUsados);
        exp.setVisible(true);
        generator.setVisible(false);
    }
    
    public void randomFloatNormal(int size, float media, float desviacion)
    {
        Normal n = new Normal(media,desviacion);
        
        float[] vec = Calculator.calculatorNormal(size,n);
        String[] datosUsados = new String[3];
        datosUsados[0] = ""+size;
        datosUsados[1] = ""+media;
        datosUsados[2] = ""+desviacion;
        
        GeneratorTable exp = new GeneratorTable(this,vec,"Normal",datosUsados);
        exp.setVisible(true);
        generator.setVisible(false);
    }
}
