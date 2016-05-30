/*
 *  Copyright 2007,2008 Alessandro Chiari.
 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package jmash;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import jmash.tableModel.NumberFormatter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author  Alessandro
 */
public class CalcoloHopLoss extends ModalInternalFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2794087157762513236L;
	/** Creates new form CalcoloHopLoss */
    public CalcoloHopLoss() {
        initComponents();

        this.fldT.setModel(this.mdlT);
        this.fldCost.setModel(this.mdlCost);
        this.fldAA.setModel(this.mdlAA);
        this.fldMesi.setModel(this.mdlMesi);
        this.fldT.setEditor(new JSpinner.NumberEditor(this.fldT, "0.0"));
        this.fldCost.setEditor(new JSpinner.NumberEditor(this.fldCost, "0.0"));
        this.fldAA.setEditor(new JSpinner.NumberEditor(this.fldAA, "0.0"));
        this.fldMesi.setEditor(new JSpinner.NumberEditor(this.fldMesi, "0.00"));
        this.pnl.add(createDemoPanel(), BorderLayout.CENTER);   
        setBorder(Utils.getDefaultBorder());
        this.jLabel4.setText("Modalità conservazione");
        calc();
    }

    private Hop hop;
    public void set(Hop hop){
        this.hop=hop;
        if(hop.getAlfaAcidiPrec()==null) {
			hop.setAlfaAcidiPrec(hop.getAlfaAcidi());
		}
        this.mdlAA.setValue(hop.getAlfaAcidiPrec());
        this.mdlCost.setValue(hop.getHSI());
    }
    public Hop get(){
        return this.hop;
    }
    
    private double _agedAa;
    private void calc(){
        double aa=this.mdlAA.getNumber().doubleValue();
        double lossRate=this.mdlCost.getNumber().doubleValue();
        double agingTime = this.mdlMesi.getNumber().doubleValue()*30.41667;  // in giorni
        double agingTemp = this.mdlT.getNumber().doubleValue(); // in Â°C
        
        double storageFactor = 1;
        if(this.fldStorage.getSelectedIndex()==1) {
			storageFactor = 0.75;
		}
        if(this.fldStorage.getSelectedIndex()==2) {
			storageFactor = 0.5;
		}
        
        double lossRateConstant = Math.log((1.0/(1.0 - lossRate/100.0)))/180.0;
        double factorTemperature = Math.exp(-(4.620981e-2*(20.0 - agingTemp)));  
        
        double agedAa=aa*Math.exp(-(lossRateConstant*agingTime*factorTemperature*storageFactor));
        this._agedAa=agedAa;
        this.result.setText("Alfa acidi del luppolo invecchiato = "+NumberFormatter.format02(agedAa));        
        
        if(this.pointer==null){
            //chart.getXYPlot().removeAnnotation(pointer);
            this.pointer = new XYPointerAnnotation("",0,0,6 * Math.PI / 4.0);
            this.chart.getXYPlot().addAnnotation(this.pointer);
            this.pointer.setTipRadius(5.0);
            this.pointer.setBaseRadius(20.0);
            this.pointer.setFont(new Font("SansSerif", Font.BOLD, 12));
            this.pointer.setPaint(Color.blue);
            this.pointer.setTextAnchor(TextAnchor.BOTTOM_CENTER);
        }
        this.pointer.setX(this.mdlMesi.getNumber().doubleValue());
        this.pointer.setY(agedAa);
        this.pointer.setText(NumberFormatter.format02(agedAa));
        this.chart.getXYPlot().getRangeAxis().setUpperBound(aa+1);        
        this.series1.clear();
        for(int m=0;m<36;m+=1){
            agingTime = m*30.41667;  // in giorni
            agedAa=aa*Math.exp(-(lossRateConstant*agingTime*factorTemperature*storageFactor));
            this.series1.add(m, agedAa);
        }
    }
    
    private SpinnerNumberModel mdlT = new SpinnerNumberModel(-15.0, -273.0, 100.0, 1.0);
    private SpinnerNumberModel mdlMesi = new SpinnerNumberModel(6.0, 0.0, 100.0, 0.25);
    private SpinnerNumberModel mdlAA = new SpinnerNumberModel(7.0, 0.0, 50.0, 0.1);   
    private SpinnerNumberModel mdlCost = new SpinnerNumberModel(40.0, 0.0, 99.0, 1.0);   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        result = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        fldAA = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        fldT = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        fldMesi = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        fldStorage = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        fldCost = new javax.swing.JSpinner();
        pnl = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Calcolo perdita %aa");

        result.setFont(result.getFont());
        result.setText("jTextField1");

        jLabel1.setFont(jLabel1.getFont());
        jLabel1.setText("Alfa acidi iniziali");

        fldAA.setFont(fldAA.getFont());
        fldAA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fldAAStateChanged(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont());
        jLabel2.setText("Temperatura stoccaggio °C");

        fldT.setFont(fldT.getFont());
        fldT.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fldTStateChanged(evt);
            }
        });

        jLabel3.setFont(jLabel3.getFont());
        jLabel3.setText("Tempo stoccaggio (mesi)");

        fldMesi.setFont(fldMesi.getFont());
        fldMesi.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fldMesiStateChanged(evt);
            }
        });

        jLabel4.setFont(jLabel4.getFont());
        jLabel4.setText("testo a runtime");

        fldStorage.setFont(fldStorage.getFont());
        fldStorage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non sigillato o sigillato in sacchetti di plastica", "Sigillato in sacchetti protettivi o contenitori stagni, ma con presenza di ossigeno", "Sigillato in sacchetti protettivi o contenitori stagni sotto vuoto o con gas inerti (azoto o CO2)" }));
        fldStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldStorageActionPerformed(evt);
            }
        });

        jLabel5.setFont(jLabel5.getFont());
        jLabel5.setText("Costante di perdita");

        fldCost.setFont(fldCost.getFont());
        fldCost.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fldCostStateChanged(evt);
            }
        });

        pnl.setFont(pnl.getFont());
        pnl.setMaximumSize(new java.awt.Dimension(200, 200));
        pnl.setMinimumSize(new java.awt.Dimension(200, 200));
        pnl.setPreferredSize(new java.awt.Dimension(200, 200));
        pnl.setLayout(new java.awt.BorderLayout());

        jButton1.setFont(jButton1.getFont());
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/button_ok.png"))); // NOI18N
        jButton1.setText("Applica");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jLabel4)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(fldAA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabel5))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(fldT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel3)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(fldCost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(fldMesi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(66, 66, 66))
                            .add(fldStorage, 0, 461, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(result, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1))
                    .add(pnl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(fldAA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(fldCost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fldT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(fldMesi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(fldStorage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(result, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-642)/2, (screenSize.height-473)/2, 642, 473);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.hop.setAlfaAcidi(this._agedAa);
        doDefaultCloseAction();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fldCostStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fldCostStateChanged
calc();
    }//GEN-LAST:event_fldCostStateChanged

    private void fldStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldStorageActionPerformed
calc();
    }//GEN-LAST:event_fldStorageActionPerformed

    private void fldMesiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fldMesiStateChanged
calc();
    }//GEN-LAST:event_fldMesiStateChanged

    private void fldTStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fldTStateChanged
calc();
    }//GEN-LAST:event_fldTStateChanged

    private void fldAAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fldAAStateChanged
calc();
    }//GEN-LAST:event_fldAAStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner fldAA;
    private javax.swing.JSpinner fldCost;
    private javax.swing.JSpinner fldMesi;
    private javax.swing.JComboBox fldStorage;
    private javax.swing.JSpinner fldT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel pnl;
    private javax.swing.JTextField result;
    // End of variables declaration//GEN-END:variables
    XYPointerAnnotation pointer=null;
    XYSeriesCollection dataset = new XYSeriesCollection();    
    XYSeries series1 = new XYSeries("");
    public  JPanel createDemoPanel() {
        JFreeChart lChart = createChart( );        
        this.dataset.addSeries(this.series1);    
        this.chart.getXYPlot().setDataset(this.dataset);
        return new ChartPanel( lChart );
    }    
        
    private JFreeChart chart=null;    
    private  JFreeChart createChart( ) {


        this.chart = createChart(this.dataset);

        return this.chart;
    }    
    private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart chart1 = ChartFactory.createXYLineChart(
            "",
            "Mesi", "Alfa acidi",
            null,
            PlotOrientation.VERTICAL,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        chart1.setBackgroundPaint(Color.white);
        chart1.getXYPlot().getRenderer().setItemLabelsVisible(true);
        chart1.getXYPlot().getRenderer().setItemLabelPaint(Color.black);
        chart1.setAntiAlias(true);
        final XYPlot plot = chart1.getXYPlot();
        plot.getRenderer().setSeriesStroke(0,new BasicStroke(5f));
        plot.getRenderer().setSeriesStroke(1,new BasicStroke(2f));
        
        plot.getRenderer().setSeriesVisibleInLegend(false);

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);

        final ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        
//        final ValueAxis rangeAxis = plot.getRangeAxis();
        chart1.getXYPlot().getDomainAxis().setUpperBound(24);
        chart1.getXYPlot().getDomainAxis().setLowerBound(0);
        chart1.getXYPlot().getRangeAxis().setUpperBound(25);
        chart1.getXYPlot().getRangeAxis().setLowerBound(0);
        
        chart1.getXYPlot().getRenderer().setToolTipGenerator(new XYToolTipGenerator(){
            @Override
                public String generateToolTip(XYDataset ds, int s, int i){
                    
                    double d0=ds.getXValue(s,i);
                    double y0=ds.getYValue(s,i);
                    
                    String str="";
                    str="Dopo "+d0+" mesi sarebbero "+NumberFormatter.format02( y0);
                    return str;
                }
        });        
        
        return chart1;
        
    }
}
