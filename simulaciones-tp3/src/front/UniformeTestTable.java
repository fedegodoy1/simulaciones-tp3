/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.*;
import jfree.*;
import objects.Controller;

/**
 *
 * @author federico
 */
public class UniformeTestTable extends javax.swing.JFrame {

    Controller controller;
    int contador;
    float[] valoresGenerados;
    int cantIntervalos;
    
    public UniformeTestTable(Controller cont, int[][] response, float[]vec, float rango, int cantIntervalos) {
        
        controller = cont;
        contador = response.length;
        initComponents();
        
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat aleat = new DecimalFormat("0.0000");
        DecimalFormat c = new DecimalFormat("0.000");
        int contador = 0;
        int frecEsp = (int) vec.length/cantIntervalos;
        double rangoM;
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        
//        if(frecEsp < 5)
//        {
//            int frecA = frecEsp;
//            int loops = 0;
//            int frecB = frecA;
//            
//            do{
//                frecB =+ frecA;
//                loops ++;
//            }while(frecB >= 5);
//            
//            frecEsp = frecB;
//            
//        }
        
        
        for (int i = 0; i < response.length; i++) {
            if (i == 0) {
                rangoM = rango;
                tm.addRow(new Object[]{"0.00 - "+in.format(rangoM),i, response[i][1], frecEsp, c.format(estadisticoPrueba(response,frecEsp,i))});
                contador++;
            }
            else{
                if (contador == 1) {
                    tm.addRow(new Object[]{in.format(rango)+" - "+in.format(rango+rango),i, response[i][1], frecEsp, c.format(estadisticoPrueba(response,frecEsp,i))});
                    contador ++;
                }
                else{
                   tm.addRow(new Object[]{in.format(rango*contador)+" - "+in.format((rango*contador)+rango),i, response[i][1], frecEsp, c.format(estadisticoPrueba(response,frecEsp,i))});
                   contador++; 
                }
                
            }
        }
        int frecSumaObservadas = 0, contAux = 0;
        int frecSumaEsp = 0;
        int vueltas = 1, ultimoValor = 0;
        boolean end = false;
        rangoM = 0;
        Double [][] r = new Double[tm.getRowCount()][3];
        double estadisticoTotal = 0;
        DefaultTableModel tm2 = (DefaultTableModel) tablaFE.getModel();
        if(frecEsp < 5){
            
            for (int i = 0; i <= tm.getRowCount(); i++) {
                if(frecSumaEsp < 5){   
                    if(i==tm.getRowCount())//significa que termino de buscar en las filas y aun la fe es menor a 5
                    {
                        int valorE = (int)tm2.getValueAt((tm2.getRowCount()-1), 2);
                        int valorO = (int)tm2.getValueAt((tm2.getRowCount()-1), 1);
                        
                        valorE += frecSumaEsp;
                        valorO += frecSumaObservadas;
                        
                        tm2.setValueAt(valorE, (tm2.getRowCount()-1), 2);
                        tm2.setValueAt(valorO, (tm2.getRowCount()-1), 1);
                        
                        double desde = 0;
                        desde = r[tm2.getRowCount()-1][0];
                        tm2.setValueAt(in.format(desde)+" - 1,00", (tm2.getRowCount()-1), 0);
                        
                        double estadis= (double) Math.pow((valorE - valorO),2)/valorE;
                        tm2.setValueAt(estadis, (tm2.getRowCount()-1), 3);
                        
                        break;
                    }
                    frecSumaEsp += (int)tm.getValueAt(i, 3);
                    frecSumaObservadas += (int) tm.getValueAt(i, 2);
                    vueltas++;
                }
                else{
                    rangoM = 0;
                    for (int j = 0; j < vueltas-1; j++) {
                        rangoM += rango;
                    }
                    
                    do{
                        if(contAux == 0){
                            
                            contAux++;
                            ultimoValor = vueltas;
                            
                            r[0][0] = 0.0;
                            r[0][1] = rangoM;
                            double estadisticoParcial=(double)(Math.pow((frecSumaObservadas-frecSumaEsp),2))/frecSumaEsp;
                            tm2.addRow(new Object[]{"0.00 - "+ in.format(rangoM), frecSumaObservadas, frecSumaEsp, c.format(estadisticoParcial)});
                            estadisticoTotal += estadisticoParcial;
                            frecSumaEsp = 0;
                            frecSumaObservadas = 0;
                            i = i-1;
                            end = true;
                        }
                        else if(contAux != 0){
                            double rangoM2 = r[contAux-1][1];
                            double estadisticoParcial=(double)(Math.pow((frecSumaObservadas-frecSumaEsp),2))/frecSumaEsp;
                            estadisticoTotal += estadisticoParcial;
                            tm2.addRow(new Object[]{in.format(rangoM2)+" - "+ in.format(rangoM), frecSumaObservadas, frecSumaEsp, c.format(estadisticoParcial)});
                            
                            r[contAux][0]=rangoM2;
                            r[contAux][1]=rangoM;
                            
                            ultimoValor = vueltas-1;
                            frecSumaEsp = 0;
                            frecSumaObservadas = 0;
                            contAux++;
                            if(i==tm.getRowCount()){
                                end = true;
                            }
                            else{
                                i = i-1;
                                end = true;
                            }
                        }
                    }while(end != true);
                }
            }
        }
        
        //para mostrar los valores generados
        String acum = "";
        for (int i = 0; i < vec.length; i++) {
            acum += "Valor "+(i+1)+": "+aleat.format(vec[i])+".\n";
        }
        txt_valoresGenerados.setText(acum);
        
        _gradosLib_agrupado.setText(""+gradosLibertad(tm2.getRowCount()));
        txt_nuevo_estadistico.setText(""+c.format(estadisticoTotal));
        
        //para el calculo de mi estadistico de prueba total
        txt_estadistico.setText(""+c.format(estadisticoPruebaTotal(response,frecEsp)));
        txt_grados.setText(""+gradosLibertad(cantIntervalos));
        
        valoresGenerados = vec;
        this.cantIntervalos = cantIntervalos;
      //  agregarHistograma();
    }
    
    public double estadisticoPrueba(int[][] response, int frecEsp, int loop){
        double res = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        res = (double)(Math.pow(response[loop][1] - frecEsp,2))/frecEsp;
        return res;
    }
    public int gradosLibertad(int intervalo){
        return intervalo - 0 -1;
    }
    
    public double estadisticoPruebaTotal(int[][] response, int frecEsp){
        double res = 0, a = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        for (int i = 0; i < response.length; i++) {
            a += (double)(Math.pow(response[i][1] - frecEsp,2))/frecEsp;
        }
        res = a;
        return res;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelHistograma = new javax.swing.JPanel();
        scfe = new javax.swing.JScrollPane();
        tablaFE = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prueba de chi cuadrado");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numeros del intervalo", "Intervalo", "Frecuencia", "Frecuencia esperada", "Estadistico de prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        txt_valoresGenerados.setEditable(false);
        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

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

        tablaFE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Intervalo", "Frecuencia observada", "Frecuencia esperada", "Estadistico de prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scfe.setViewportView(tablaFE);

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scfe)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(panelHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scfe, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(panelHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
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
    private javax.swing.JScrollPane scfe;
    private javax.swing.JTable tablaFE;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_nuevo_estadistico;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

//    private void agregarHistograma()
//    {
//        // Tenemos que convertir los numeros generados a un vector de double.
//        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
//        Histograma histograma = new Histograma("Frecuencia de numeros random Java",
//                "Histograma del Random de Java", valoresGeneradosEnDouble, cantIntervalos);
//        JPanel histoPanel = histograma.obtenerPanel();
//        histoPanel.setVisible(true);
//        panelHistograma.add(histoPanel);
//        panelHistograma.validate();
//    }

    private double[] obtenerValoresEnDouble()
    {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++)
        {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }
}
