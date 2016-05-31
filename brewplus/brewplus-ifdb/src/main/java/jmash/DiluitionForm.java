/*
 *  Copyright 2006, 2007 Alessandro Chiari.
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

import java.awt.Image;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author  Alessandro
 */
public class DiluitionForm extends javax.swing.JInternalFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 7235662704928426515L;

    /** Creates new form DiluitionForm2 */
    public DiluitionForm() {
        initComponents();

	
	spinL1.setModelFormat(23,0,999999999,0.5,"0.0","DiluitionForm.spinL1");
	spinL2.setModelFormat(23,0,999999999,0.5,"0.0","DiluitionForm.spinL2");
	spinL3.setModelFormat(23,0,999999999,0.5,"0.0","DiluitionForm.spinL3");
	spinD1.setModelFormat(1.04,0,2,0.01,"0.000","DiluitionForm.spinD1");
	spinD2.setModelFormat(1.04,0,2,0.01,"0.000","DiluitionForm.spinD2");
	spinD3.setModelFormat(1.04,0,2,0.01,"0.000","DiluitionForm.spinD3");
	
        this.spinI1.setModel(this.mi1);
        this.spinI2.setModel(this.mi2);
        this.spinI3.setModel(this.mi3);

        this.spinC1.setModel(this.mc1);
        this.spinC2.setModel(this.mc2);
        this.spinC3.setModel(this.mc3);


        this.spinC1.setEditor(new JSpinner.NumberEditor(this.spinC1, "0.0"));
        this.spinC2.setEditor(new JSpinner.NumberEditor(this.spinC2, "0.0"));
        this.spinC3.setEditor(new JSpinner.NumberEditor(this.spinC3, "0.0"));
        this.spinI1.setEditor(new JSpinner.NumberEditor(this.spinI1, "0.0"));
        this.spinI2.setEditor(new JSpinner.NumberEditor(this.spinI2, "0.0"));
        this.spinI3.setEditor(new JSpinner.NumberEditor(this.spinI3, "0.0"));
        setBorder(Utils.getDefaultBorder());

        setBackground(getBackground().darker());
        putDS();
    }

    public DiluitionForm(double V, double D, double I) {
        this();
        this.spinL1.setVolume(V);
        this.spinD1.setGravity(D);
        this.spinI1.setValue(I);
    }
    private double litri1 = 23,  litri2 = 1,  litri3 = 0;
    private double sg1 = 1.040,  sg2 = 1.000,  sg3 = 0;
    private double ebc1 = 1.040,  ebc2 = 1.000,  ebc3 = 0;
    private double ibu1 = 1.040,  ibu2 = 1.000,  ibu3 = 0;
    private SpinnerNumberModel mi1 = new SpinnerNumberModel(25.0, 0.0, 1000.0, 1.0);
    private SpinnerNumberModel mi2 = new SpinnerNumberModel(0.0, 0.0, 1000.0, 1.0);
    private SpinnerNumberModel mi3 = new SpinnerNumberModel(0.0, 0.0, 1000.0, 1.0);
    private SpinnerNumberModel mc1 = new SpinnerNumberModel(25.0, 0.0, 1000.0, 0.5);
    private SpinnerNumberModel mc2 = new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.5);
    private SpinnerNumberModel mc3 = new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.5);

    public void calculate() {
        getDS();
        this.litri3 = this.litri1 + this.litri2;
        this.sg3 = Utils.Plato2SG((this.litri1 * Utils.SG2Plato(this.sg1) +
                this.litri2 * Utils.SG2Plato(this.sg2)) / this.litri3);
        this.ibu3 = (this.litri1 * this.ibu1 + this.litri2 * this.ibu2) / this.litri3;
        this.ebc3 = (this.litri1 * this.ebc1 + this.litri2 * this.ebc2) / this.litri3;
        putDS();
    }

    private void getDS() {

        this.litri1 = spinL1.getVolume();
        this.litri2 = spinL2.getVolume();

        this.sg1 = spinD1.getGravity();
        this.sg2 = spinD2.getGravity();

        this.ibu1 = this.mi1.getNumber().doubleValue();
        this.ibu2 = this.mi2.getNumber().doubleValue();

        this.ebc1 = this.mc1.getNumber().doubleValue();
        this.ebc2 = this.mc2.getNumber().doubleValue();
    }

    private void putDS() {

        this.spinL3.setVolume(this.litri3);
        this.spinD3.setGravity(this.sg3);

        this.spinI1.setValue(this.ibu1);
        this.spinC1.setValue(this.ebc1);
        this.spinI2.setValue(this.ibu2);
        this.spinC2.setValue(this.ebc2);
        this.spinI3.setValue(this.ibu3);
        this.spinC3.setValue(this.ebc3);

    }

    public double getLitri1() {
        return this.litri1;
    }

    public void setLitri1(double litri1) {
        this.litri1 = litri1;
    }

    public double getLitri2() {
        return this.litri2;
    }

    public void setLitri2(double litri2) {
        this.litri2 = litri2;
    }

    public double getLitri3() {
        return this.litri3;
    }

    public void setLitri3(double litri3) {
        this.litri3 = litri3;
    }

    public double getSg1() {
        return this.sg1;
    }

    public void setSg1(double sg1) {
        this.sg1 = sg1;
    }

    public double getSg2() {
        return this.sg2;
    }

    public void setSg2(double sg2) {
        this.sg2 = sg2;
    }

    public double getSg3() {
        return this.sg3;
    }

    public void setSg3(double sg3) {
        this.sg3 = sg3;
    }
    public static Image bgImage = java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/kettle.jpg"));

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spinI1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        spinC1 = new javax.swing.JSpinner();
        spinL1 = new jmash.component.JVolumeSpinner();
        spinD1 = new jmash.component.JGravitySpinner();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spinC2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        spinI2 = new javax.swing.JSpinner();
        spinL2 = new jmash.component.JVolumeSpinner();
        spinD2 = new jmash.component.JGravitySpinner();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spinC3 = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        spinI3 = new javax.swing.JSpinner();
        spinL3 = new jmash.component.JVolumeSpinner();
        spinD3 = new jmash.component.JGravitySpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Diluzioni e concentrazioni");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Se a questo..."));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 160));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("N.Litri");
        jPanel2.add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setText("Densita'");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("IBU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        spinI1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinI1PropertyChange(evt);
            }
        });
        spinI1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinI1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinI1, gridBagConstraints);

        jLabel4.setText("EBC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        spinC1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinC1PropertyChange(evt);
            }
        });
        spinC1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinC1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinC1, gridBagConstraints);

        spinL1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinL1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinL1, gridBagConstraints);

        spinD1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinD1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinD1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("aggiungo questo..."));
        jPanel10.setPreferredSize(new java.awt.Dimension(200, 160));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel19.setText("N.Litri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel10.add(jLabel19, gridBagConstraints);

        jLabel20.setText("Densita'");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel10.add(jLabel20, gridBagConstraints);

        jLabel5.setText("EBC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel10.add(jLabel5, gridBagConstraints);

        spinC2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinC2PropertyChange(evt);
            }
        });
        spinC2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinC2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel10.add(spinC2, gridBagConstraints);

        jLabel6.setText(" IBU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel10.add(jLabel6, gridBagConstraints);

        spinI2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinI2PropertyChange(evt);
            }
        });
        spinI2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinI2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel10.add(spinI2, gridBagConstraints);

        spinL2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinL2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel10.add(spinL2, gridBagConstraints);

        spinD2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinD2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel10.add(spinD2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jPanel10, gridBagConstraints);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("...ottengo: "));
        jPanel11.setPreferredSize(new java.awt.Dimension(200, 160));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel21.setText("N.Litri");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel11.add(jLabel21, gridBagConstraints);

        jLabel22.setText("Densita'");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel11.add(jLabel22, gridBagConstraints);

        jLabel7.setText("EBC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel11.add(jLabel7, gridBagConstraints);

        spinC3.setEnabled(false);
        spinC3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinC3PropertyChange(evt);
            }
        });
        spinC3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinC3StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel11.add(spinC3, gridBagConstraints);

        jLabel8.setText("IBU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel11.add(jLabel8, gridBagConstraints);

        spinI3.setEnabled(false);
        spinI3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinI3PropertyChange(evt);
            }
        });
        spinI3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinI3StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel11.add(spinI3, gridBagConstraints);

        spinL3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel11.add(spinL3, gridBagConstraints);

        spinD3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel11.add(spinD3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jPanel11, gridBagConstraints);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel10, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/button_ok.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/finish.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jLabel11, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-325)/2, (screenSize.height-526)/2, 325, 526);
    }// </editor-fold>//GEN-END:initComponents
    private void spinD2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinD2StateChanged
        calculate();
    }//GEN-LAST:event_spinD2StateChanged

    private void spinL2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinL2StateChanged
        calculate();
    }//GEN-LAST:event_spinL2StateChanged

    private void spinD1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinD1StateChanged
        calculate();
    }//GEN-LAST:event_spinD1StateChanged

    private void spinL1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinL1StateChanged
        calculate();
    }//GEN-LAST:event_spinL1StateChanged

    private void spinI3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinI3StateChanged
        calculate();
    }//GEN-LAST:event_spinI3StateChanged

    private void spinI3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinI3PropertyChange
        calculate();
    }//GEN-LAST:event_spinI3PropertyChange

    private void spinC3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinC3StateChanged

    }//GEN-LAST:event_spinC3StateChanged

    private void spinC3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinC3PropertyChange

    }//GEN-LAST:event_spinC3PropertyChange

    private void spinI2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinI2StateChanged
        calculate();
    }//GEN-LAST:event_spinI2StateChanged

    private void spinI2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinI2PropertyChange

    }//GEN-LAST:event_spinI2PropertyChange

    private void spinC2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinC2StateChanged
        calculate();
    }//GEN-LAST:event_spinC2StateChanged

    private void spinC2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinC2PropertyChange

    }//GEN-LAST:event_spinC2PropertyChange

    private void spinC1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinC1StateChanged
        calculate();
    }//GEN-LAST:event_spinC1StateChanged

    private void spinC1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinC1PropertyChange

    }//GEN-LAST:event_spinC1PropertyChange

    private void spinI1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinI1StateChanged
        calculate();
    }//GEN-LAST:event_spinI1StateChanged

    private void spinI1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinI1PropertyChange

    }//GEN-LAST:event_spinI1PropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner spinC1;
    private javax.swing.JSpinner spinC2;
    private javax.swing.JSpinner spinC3;
    private jmash.component.JGravitySpinner spinD1;
    private jmash.component.JGravitySpinner spinD2;
    private jmash.component.JGravitySpinner spinD3;
    private javax.swing.JSpinner spinI1;
    private javax.swing.JSpinner spinI2;
    private javax.swing.JSpinner spinI3;
    private jmash.component.JVolumeSpinner spinL1;
    private jmash.component.JVolumeSpinner spinL2;
    private jmash.component.JVolumeSpinner spinL3;
    // End of variables declaration//GEN-END:variables
}
