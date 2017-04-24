/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.exponencial;

import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.*;
import jfree.*;
import objects.*;
import front.*;

/**
 *
 * @author nicolashefty
 */
public class ExponencialTestTable extends javax.swing.JFrame {

    Controller controller;
    int contador;
    float[] valoresGenerados;
    float rango;
    Calculator calculator = new Calculator();
    float minimo, maximo;
    int N;

    private final float media;

    private final float lambda;

    public ExponencialTestTable(JFrame parent, Controller cont, float[] values, String[] datos, int intervalos) {
        float[] ordenadosValues = new float[values.length];
        System.arraycopy(values, 0, ordenadosValues, 0, values.length);
        rango = calcularRango(ordenadosValues, intervalos);
        controller = cont;
        float[][] matriz = calculator.matrizFrecuenciaExponencial(ordenadosValues, rango, intervalos, minimo);
        N = Integer.parseInt(datos[0]);
        media = Float.parseFloat(datos[1]);
        lambda = (float) 1/media;
        

        initComponents();
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
//        int contador = 0;
//        int frecEsp = (int) values.length / intervalos;

        //double rangoM;
        DefaultTableModel tm = (DefaultTableModel) _tabla.getModel();

        for (int i = 0; i < matriz.length; i++)
        {
//            tm.addRow(new Object[]{in.format(matriz[i][0]) + " - " + in.format(matriz[i][1]), i + 1, matriz[i][2], frecEsp, c.format(estadisticoPrueba(matriz[i][2], frecEsp, i))});
//        }
            float probabilidad = calcularProbabilidad(matriz[i][0],matriz[i][1]);
            float frecEsp = calcularFrecuenciaEsperada(probabilidad);
            double estadistico = estadisticoPrueba(matriz[i][2], frecEsp, i);
            tm.addRow(new Object[]
            {
                in.format(matriz[i][0]), in.format(matriz[i][1]), matriz[i][2], c.format(probabilidad), c.format(frecEsp) , c.format(estadistico)
            });
        }
        
        agregarTablaAcumulada();
        

//        if (frecEsp < 5) {
//            int frecuenciaAcumulada=0;
//            int frecObsAcumulada=0;
//            int a= (int) tm.getValueAt(0, 3)+(int) tm.getValueAt(1, 3);
//            int inicio = 0;
//            for (int i = 0; i <= tm.getRowCount(); i++) {
//                frecuenciaAcumulada += (int) tm.getValueAt(i, 3);
//                frecObsAcumulada += matriz[i][2];
//                if (frecuenciaAcumulada >= 5) {
//                 tm2.addRow(new Object[]{matriz[inicio][0] + " - " + matriz[i][1], frecObsAcumulada, frecuenciaAcumulada, estadisticoPrueba(frecObsAcumulada, (int)frecuenciaAcumulada,i)});
//                    inicio = i+1;
//                    frecuenciaAcumulada =0;
//                    frecObsAcumulada =0;
//                    break;
//                }
//                
//                if (frecSumaEsp < 5) {
//                    if (i == tm.getRowCount())//significa que termino de buscar en las filas y aun la fe es menor a 5
//                    {
//                        int valorE = (int) tm2.getValueAt((tm2.getRowCount() - 1), 2);
//                        int valorO = (int) tm2.getValueAt((tm2.getRowCount() - 1), 1);
//
//                        valorE += frecSumaEsp;
//                        valorO += frecSumaObservadas;
//
//                        tm2.setValueAt(valorE, (tm2.getRowCount() - 1), 2);
//                        tm2.setValueAt(valorO, (tm2.getRowCount() - 1), 1);
//
//                        double desde = 0;
//                        desde = r[tm2.getRowCount() - 1][0];
//                        tm2.setValueAt(in.format(desde) + " - 1,00", (tm2.getRowCount() - 1), 0);
//
//                        double estadis = (double) Math.pow((valorE - valorO), 2) / valorE;
//                        tm2.setValueAt(estadis, (tm2.getRowCount() - 1), 3);
//
//                        break;
//                    }
//                    frecSumaEsp += (int) tm.getValueAt(i, 3);
//                    frecSumaObservadas += (int) tm.getValueAt(i, 2);
//                    vueltas++;
//            }
            //else {
//                    rangoM = 0;
//                    for (int j = 0; j < vueltas-1; j++) {
//                        rangoM += rango;
//                    }
//                   

            // do{
//                        if(contAux == 0){
//                            
//                            contAux++;
//                            ultimoValor = vueltas;
//                            
//                            r[0][0] = 0.0;
//                           // r[0][1] = rangoM;
//                            double estadisticoParcial=(double)(Math.pow((frecSumaObservadas-frecSumaEsp),2))/frecSumaEsp;
//                            tm2.addRow(new Object[]{"0.00 - "+ in.format(rangoM), frecSumaObservadas, frecSumaEsp, c.format(estadisticoParcial)});
//                            estadisticoTotal += estadisticoParcial;
//                            frecSumaEsp = 0;
//                            frecSumaObservadas = 0;
//                            i = i-1;
//                            end = true;
//                        }
//                        else if(contAux != 0){
//                            double rangoM2 = r[contAux-1][1];
//                            double estadisticoParcial=(double)(Math.pow((frecSumaObservadas-frecSumaEsp),2))/frecSumaEsp;
//                            estadisticoTotal += estadisticoParcial;
//                            tm2.addRow(new Object[]{in.format(rangoM2)+" - "+ in.format(rangoM), frecSumaObservadas, frecSumaEsp, c.format(estadisticoParcial)});
//                            
//                            r[contAux][0]=rangoM2;
//                           // r[contAux][1]=rangoM;
//                            
//                            ultimoValor = vueltas-1;
//                            frecSumaEsp = 0;
//                            frecSumaObservadas = 0;
//                            contAux++;
//                            if(i==tm.getRowCount()){
//                                end = true;
//                            }
//                            else{
//                                i = i-1;
//                                end = true;
//                            }
//                        }
//                    }while(end != true);
            // }
            // }
//        }

        String valoresGenerados = valoresGenerados(values);
        txt_valoresGenerados.setText(valoresGenerados);

        DefaultTableModel tm2 = (DefaultTableModel) _tablaAcumulada.getModel();
        _gradosLib_agrupado.setText("" + gradosLibertad(tm2.getRowCount()));
        txt_nuevo_estadistico.setText("" + c.format(estadisticoTotalAcumulado()));

        //para el calculo de mi estadistico de prueba total
        txt_estadistico.setText("" + c.format(estadisticoPruebaTotal()));
        txt_grados.setText("" + gradosLibertad(intervalos));

        //     valoresGenerados = vec;
//      //  agregarHistograma();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public double estadisticoPrueba(float frecObs, float frecEsp, int loop) {
       return (double) (Math.pow(frecObs - frecEsp, 2)) / frecEsp;
    }

    public int gradosLibertad(int intervalo) {
        return intervalo - 0 - 1;
    }

    public double estadisticoPruebaTotal() {
        double res = 0;
        DefaultTableModel tm = (DefaultTableModel) _tabla.getModel();
        for (int i = 0; i < tm.getRowCount(); i++)
        {
            Double estadistico = 0.0;
            if (tm.getValueAt(i, 5) instanceof String)
            {
                String estadStr = (String) tm.getValueAt(i, 5);
                if (estadStr.indexOf(',') > 0)
                {
                    estadistico = Double.parseDouble(estadStr.replace(',', '.'));
                }
            }
            res += estadistico;
        }
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelHistograma = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();
        _scpTabla = new javax.swing.JScrollPane();
        _tabla = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        _tablaAcumulada = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prueba de chi cuadrado");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
        });

        txt_valoresGenerados.setEditable(false);
        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        jLabel1.setText("Valores Generados");

        txt_estadistico.setEditable(false);
        txt_estadistico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_estadisticoActionPerformed(evt);
            }
        });

        jLabel2.setText("Estadistico de prueba total:");

        jLabel3.setText("Grados de libertad:");

        txt_grados.setEditable(false);

        jButton1.setText("Volver");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        panelHistograma.setPreferredSize(new java.awt.Dimension(800, 800));
        panelHistograma.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

        _tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Desde", "Hasta", "Frec Observada", "Probabilidad", "Frec Esperada", "Estadistico"
            }
        ));
        _scpTabla.setViewportView(_tabla);

        _tablaAcumulada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null}
            },
            new String []
            {
                "Desde", "Hasta", "Frec Obs", "Prob", "Frec Esp", "Estadistico"
            }
        ));
        jScrollPane1.setViewportView(_tablaAcumulada);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(_scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(panelHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(_scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void txt_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadisticoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //  controller.volverDeTestRandomJava();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_nuevo_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nuevo_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nuevo_estadisticoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField _gradosLib_agrupado;
    private javax.swing.JScrollPane _scpTabla;
    private javax.swing.JTable _tabla;
    private javax.swing.JTable _tablaAcumulada;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelHistograma;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_nuevo_estadistico;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void agregarHistograma() {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
        //Histograma histograma = new Histograma("Frecuencia de numeros random Java",
//                "Histograma del Random de Java", valoresGeneradosEnDouble, cantIntervalos);
//        JPanel histoPanel = histograma.obtenerPanel();
//        histoPanel.setVisible(true);
//        panelHistograma.add(histoPanel);
//        panelHistograma.validate();
    }

    private double[] obtenerValoresEnDouble() {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++) {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }

    private float calcularRango(float[] values, int cantIntervalos) {
        Calculator.quicksort(values, 0, values.length-1);
        minimo = (float) Math.floor(values[0]);
//        minimo = values[0];
        maximo = (float) Math.ceil(values[values.length-1]);
        return (maximo - minimo) / cantIntervalos;
    }

    private String valoresGenerados(float[] valores) {
        //para mostrar los valores generados
        String acum = "";
        DecimalFormat aleat = new DecimalFormat("0.0000");
        for (int i = 0; i < valores.length; i++) {
            acum += "Valor " + (i + 1) + ": " + aleat.format(valores[i]) + ".\n";
        }
        return acum;
    }

    private float calcularProbabilidad(float limiteInferior, float limiteSuperior)
    {
        float res = 0.0f;
        float expInferior = (float) (1 - (Math.exp((-lambda * limiteInferior))));
        float expSuperior = (float) (1 - (Math.exp((-lambda * limiteSuperior))));
        res = expSuperior - expInferior;
        return res;
    }

    private float calcularFrecuenciaEsperada(float probabilidad)
    {
        return probabilidad*N;
    }

    private void agregarTablaAcumulada()
    {
        if (algunaFrecuenciaEsperadaMenorA5())
        {
            DefaultTableModel tm2 = (DefaultTableModel) _tablaAcumulada.getModel();
            
        }
        
        
        
    }

    private boolean algunaFrecuenciaEsperadaMenorA5()
    {
        boolean rv = false;
        
        TableModel tmOriginal = (DefaultTableModel) _tabla.getModel();
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            Double frecEsperada = 0.0;
            if (tmOriginal.getValueAt(i, 5) instanceof String)
            {
                String estadStr = (String) tmOriginal.getValueAt(i, 5);
                if (estadStr.indexOf(',') > 0)
                {
                    frecEsperada = Double.parseDouble(estadStr.replace(',', '.'));
                }
            }
            else
            {
                throw new NullPointerException("NO ERA STRING!");
            }
            if (frecEsperada < 5)
            {
                return true;
            }
        }
        return rv;
    }

    private Object estadisticoTotalAcumulado()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}