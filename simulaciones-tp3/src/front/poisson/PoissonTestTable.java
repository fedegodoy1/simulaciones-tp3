/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.poisson;

import java.math.BigInteger;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import objects.*;

/**
 *
 * @author federico
 */
public class PoissonTestTable extends javax.swing.JFrame {

    Controller controller;
    float[] valores;
    int N;
    float media;

    private final static int COL_DESDE = 0;
    private final static int COL_HASTA = 1;
    private final static int COL_FREC_OBS = 2;
    private final static int COL_PROB = 3;
    private final static int COL_FREC_ESP = 4;
    private final static int COL_ESTAD = 5;
    
    private final int cantIntervalos;
    
    public PoissonTestTable(JFrame parent, Controller cont, float[] values, String[] datos, int intervalos) {
        
        controller=cont;
        valores = values;
        cantIntervalos = intervalos;
        N = Integer.parseInt(datos[0]);
        media = Calculator.obtenerValorEnFloat(datos[1]);
        
        float [][] m = Calculator.matrizFrecuenciaPoisson(values);
        initComponents();
        
        
        
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
        
        DefaultTableModel tm = (DefaultTableModel) _tabla.getModel();
        float generados = Float.parseFloat(datos[0]),frecEsp = 0, estadistico = 0;
        
        double lambdaValor = 0,eValor = 0, prob = 0;
        BigInteger fac;
        
        for (int i = 0; i < m.length; i++) {
            lambdaValor = Math.pow(media,m[i][0]);
            eValor = Math.exp(media*-1);
            fac = fact((int)m[i][0]);
            
            prob = (lambdaValor * eValor)/fac.doubleValue();
            frecEsp = generados * (float) prob;
            estadistico = (float) (Math.pow(m[i][1] - frecEsp, 2))/frecEsp;
            
            tm.addRow(new Object[]
                {
                    in.format(m[i][0]),
                    in.format(m[i][1]),
                    c.format(prob),
                    c.format(frecEsp),
                    c.format(estadistico)
                });
        }
        
        
//        for (int i = 0; i < m.length; i++) {
//            tm.addRow(new Object[]
//            {
//                in.format(m[i][0]),
//                in.format(m[i][1]),
//                m[i][2], 
//                c.format(calcularProbabilidad(m,getRango(values,intervalos),datos)),
////                c.format(frecEsp),
////                c.format(estadistico)
//            });
//        }
//        completarFrecuencia();
//        
//        agregarAgrupado();
        
        String valoresGenerados = valoresGenerados(values);
        txt_valoresGenerados.setText(valoresGenerados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();
        _scpTabla = new javax.swing.JScrollPane();
        _tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        _tablaAcumulada = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelHistograma = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

        _tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Valor", "Frec Observada", "Probabilidad", "Frec Esperada", "Estadistico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        _scpTabla.setViewportView(_tabla);

        txt_valoresGenerados.setEditable(false);
        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        _tablaAcumulada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desde", "Hasta", "Frec Obs", "Prob", "Frec Esp", "Estadistico"
            }
        ));
        jScrollPane1.setViewportView(_tablaAcumulada);

        jLabel1.setText("Valores Generados");

        txt_estadistico.setEditable(false);
        txt_estadistico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estadisticoActionPerformed(evt);
            }
        });

        jLabel2.setText("Estadistico de prueba total:");

        jLabel3.setText("Grados de libertad:");

        txt_grados.setEditable(false);

        jButton1.setText("Volver");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelHistograma.setPreferredSize(new java.awt.Dimension(800, 800));
        panelHistograma.setLayout(new java.awt.BorderLayout());

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(panelHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nuevo_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nuevo_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nuevo_estadisticoActionPerformed

    private void txt_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadisticoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //  controller.volverDeTestRandomJava();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static BigInteger fact(int a)
    {
        BigInteger factorial = BigInteger.ONE;
        BigInteger factz = BigInteger.valueOf(a);

        if(a == 1)
        {
            return factorial;
        }

        else
        {
            return factz.multiply(fact(a-1));
        }
    }
    
    public long factorial (double numero) {
        if (numero==0)
            return 1;
        else
            return (long) numero * factorial(numero-1);
    }
    
    public int getRango(float[]values, int intervalo){
         float min = 0, max = 0;
        for (int i = 0; i < values.length; i++) {
            if(values[i] > max){
                max = values[i];
            }
        }
        for (int i = 0; i < values.length; i++) {
            if(i == 0){
                min = values[i];
            }
            else{
                if(values[i] < min){
                min = values[i];
                }
            }
        }
        
        int rango = (int) (max - min) / intervalo;
        rango ++;
        return rango;
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

    private void completarFrecuencia()
    {
        
        DecimalFormat c = new DecimalFormat("0.000");
        //Iterar la tabla... para cada valor:
        TableModel tmOriginal = _tabla.getModel();
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            float frecObs = Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_FREC_OBS));
            float probabilidad = Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_PROB));
            
            //Fe = ROUND -> P() * N;
            float frecEsp = Math.round(probabilidad*N);
       
            //Estadistico = estadistico( frecObs , frecEsperada)
            double estadistico = estadistico(frecObs, frecEsp);
            
            tmOriginal.setValueAt(frecEsp, i, COL_FREC_ESP);
            tmOriginal.setValueAt(c.format(estadistico), i, COL_ESTAD);
        }
        
        
        //Al final
        //Grados de libertad
        // Estadistico Total
        txt_grados.setText(gradosLibertadOriginal());
        
        txt_estadistico.setText(estadisticoTotalOriginal(tmOriginal));
    }

    private void agregarAgrupado()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private double estadistico(float frecuenciaObservada, float frecuenciaEsperada)
    {
        
        return (double) (Math.pow(frecuenciaObservada - frecuenciaEsperada, 2)) / frecuenciaEsperada;
    }

    private String gradosLibertadOriginal()
    {
        return "" + (cantIntervalos - 1 -1);
    }

    private String estadisticoTotalOriginal(TableModel tmOriginal)
    {
        float estadisticoAcumulado = 0;
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            estadisticoAcumulado += Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_ESTAD));
        }
        return "" + estadisticoAcumulado;
    }
}