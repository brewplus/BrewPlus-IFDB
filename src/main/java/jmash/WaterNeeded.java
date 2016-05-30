/*

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

import org.jdom.Element;

/**
 *
 * @author  AChiari
 */
public class WaterNeeded extends javax.swing.JInternalFrame {
    
    /** Creates new form WaterNeeded */
    public WaterNeeded() {
	initComponents();
	setBorder(Utils.getDefaultBorder());
	spnBatchSize.setModel(Main.config.getVolumeFin(),0,1000000,0.5,"0.0","WaterNeeded.BS");
	spnBoiltime.setModel(Main.config.getBoilTime(),0,1000,1,"0","WaterNeeded.BT");
	spnEvaporation.setModel(Main.config.getEvaporazionePerOra(),0.0,1000000,0.1,"0.0","WaterNeeded.EV");
	spnKg.setModel(4,0.0,1000000,0.25,"0.00","WaterNeeded.kg");
	spnNeiGrani.setModel(4,0.0,1000000,0.25,"0.00","WaterNeeded.NeiGrani");
	spnLitriKg.setModel(Main.config.getLitriPerKg(),0.0,1000.0,0.1,"0.0","WaterNeeded.ltkg");
	spnLosses.setModel(Main.config.getLostToSparge(),0,1000000,0.5,"0.0","WaterNeeded.losses");
	spnTrub.setModel(Main.config.getLostToTrub(),0.0,1000000,0.1,"0.0","WaterNeeded.trub");
	spnResult.setModel(0,0,1000000,1,"0.0","WaterNeeded.res");
	spnSparge.setModel(0,0,1000000,1,"0.0","WaterNeeded.sparge");
	spnMash.setModel(0,0,1000000,1,"0.0","WaterNeeded.mash");
	spnShrink.setModel(Main.getFromCache("WaterNeeded.shrink", 4.0),0,99,1);
	spnPreBoil.setModel(Main.getFromCache("WaterNeeded.rabboccoPreBoil", 0.0),0,1000000,0.5,"0.0","WaterNeeded.PB");
	spnPostBoil.setModel(Main.getFromCache("WaterNeeded.rabboccoPostBoil", 0.0),0,1000000,0.5,"0.0","WaterNeeded.PTB");
	spnAssorbimento.setModel(Main.getFromCache("WaterNeeded.coefficienteAssorbimento", 1.01),0,1000000,0.001,"0.000","WaterNeeded.ABS");

	spnBatchSize2.setModel(Main.config.getVolumeFin(),0,1000000,0.5,"0.0","WaterNeeded.BS2");
	spnBoiltime2.setModel(Main.config.getBoilTime(),0,1000,1,"0","WaterNeeded.BT2");
	spnEvaporation2.setModel(Main.config.getEvaporazionePerOra(),0.0,1000000,0.1,"0.0","WaterNeeded.EV2");
	spnKg2.setModel(4000,0.0,999999999,0.25,"0.00","WaterNeeded.KG2");
	spnNeiGrani2.setModel(4,0.0,1000000,0.25,"0.00","WaterNeeded.NG2");
	spnLosses2.setModel(Main.config.getLostToSparge(),0,1000000,0.5,"0.0","WaterNeeded.LS2");
	spnTrub2.setModel(Main.config.getLostToTrub(),0.0,1000000,0.1,"0.0","WaterNeeded.TR2");
	spnResult2.setModel(0,0,1000000,1,"0.0","WaterNeeded.RS2");
	spnShrink2.setModel(4,0,99,1);
	spnPreBoil2.setModel(0,0,1000000,0.5,"0.0","WaterNeeded.PB2");
	spnPostBoil2.setModel(0,0,1000000,0.5,"0.0","WaterNeeded.PTB2");
	
	setBackground(getBackground().darker());
	jPanel4.setBackground(jPanel4.getBackground().darker());
	jTabbedPane1.setEnabledAt(1,true);
	jTabbedPane1.setEnabledAt(2,true);
	calc();
	calc2();
    }
    
    public Element toXml(){
	Element E=new Element("water");
	
	E.setAttribute("spnBatchSize", ""+spnBatchSize.getVolume());
	E.setAttribute("spnBoiltime",""+spnBoiltime.getDoubleValue());
	E.setAttribute("spnEvaporation",""+spnEvaporation.getVolume());
	E.setAttribute("spnKg",""+spnKg.getDoubleValue());
	E.setAttribute("spnNeiGrani",""+spnNeiGrani.getVolume());
	E.setAttribute("spnLitriKg",""+spnLitriKg.getDoubleValue());
	E.setAttribute("spnLosses",""+spnLosses.getVolume());
	E.setAttribute("spnTrub",""+spnTrub.getVolume());
	E.setAttribute("spnSparge",""+spnSparge.getVolume());
	E.setAttribute("spnMash",""+spnMash.getVolume());
	E.setAttribute("spnShrink",""+spnShrink.getDoubleValue());
	E.setAttribute("spnPreBoil",""+spnPreBoil.getVolume());
	E.setAttribute("spnPostBoil",""+spnPostBoil.getVolume());
	E.setAttribute("spnAssorbimento",""+spnAssorbimento.getVolume());
	return E;
    }
    public void fromXml(Element E){
	
	if(E.getAttribute("spnBatchSize")!=null)spnBatchSize.setVolume(	new Double(E.getAttribute("spnBatchSize").getValue()));
	if(E.getAttribute("spnBoiltime")!=null)spnBoiltime.setDoubleValue(	new Double(E.getAttribute("spnBoiltime").getValue()));
	if(E.getAttribute("spnEvaporation")!=null)spnEvaporation.setVolume(	new Double(E.getAttribute("spnEvaporation").getValue()));
	if(E.getAttribute("spnKg")!=null)spnKg.setDoubleValue(	new Double(E.getAttribute("spnKg").getValue()));
	if(E.getAttribute("spnNeiGrani")!=null)spnNeiGrani.setVolume(	new Double(E.getAttribute("spnNeiGrani").getValue()));
	if(E.getAttribute("spnLitriKg")!=null)spnLitriKg.setDoubleValue(	new Double(E.getAttribute("spnLitriKg").getValue()));
	if(E.getAttribute("spnLosses")!=null)spnLosses.setVolume(	new Double(E.getAttribute("spnLosses").getValue()));
	if(E.getAttribute("spnTrub")!=null)spnTrub.setVolume(	new Double(E.getAttribute("spnTrub").getValue()));
	if(E.getAttribute("spnSparge")!=null)spnSparge.setVolume(	new Double(E.getAttribute("spnSparge").getValue()));
	if(E.getAttribute("spnMash")!=null)spnMash.setVolume(	new Double(E.getAttribute("spnMash").getValue()));
	if(E.getAttribute("spnShrink")!=null)spnShrink.setDoubleValue(	new Double(E.getAttribute("spnShrink").getValue()));
	if(E.getAttribute("spnPreBoil")!=null)spnPreBoil.setVolume(	new Double(E.getAttribute("spnPreBoil").getValue()));
	if(E.getAttribute("spnPostBoil")!=null)spnPostBoil.setVolume(	new Double(E.getAttribute("spnPostBoil").getValue()));
	if(E.getAttribute("spnAssorbimento")!=null)spnAssorbimento.setVolume(	new Double(E.getAttribute("spnAssorbimento").getValue()));

    }
    public WaterNeeded(double batchSize, double kg, double boilTime, double evap) {
	this();
	spnBatchSize.setVolume(batchSize);
	spnKg.setDoubleValue(kg);
	spnBoiltime.setDoubleValue(boilTime);
	spnEvaporation.setVolume(evap);
	calc();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spnBatchSize = new jmash.component.JVolumeSpinner();
        jLabel2 = new javax.swing.JLabel();
        spnTrub = new jmash.component.JVolumeSpinner();
        jLabel3 = new javax.swing.JLabel();
        spnShrink = new jmash.component.JMashSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spnBoiltime = new jmash.component.JMashSpinner();
        jLabel6 = new javax.swing.JLabel();
        spnLosses = new jmash.component.JVolumeSpinner();
        jLabel7 = new javax.swing.JLabel();
        spnKg = new jmash.component.JMashSpinner();
        jLabel8 = new javax.swing.JLabel();
        spnLitriKg = new jmash.component.JMashSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        spnEvaporation = new jmash.component.JVolumeSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        spnMash = new jmash.component.JVolumeSpinner();
        jLabel10 = new javax.swing.JLabel();
        spnSparge = new jmash.component.JVolumeSpinner();
        jLabel9 = new javax.swing.JLabel();
        spnResult = new jmash.component.JVolumeSpinner();
        spnNeiGrani = new jmash.component.JVolumeSpinner();
        jLabel22 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        spnAssorbimento = new jmash.component.JVolumeSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        spnPreBoil = new jmash.component.JVolumeSpinner();
        jLabel21 = new javax.swing.JLabel();
        spnPostBoil = new jmash.component.JVolumeSpinner();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        spnBatchSize1 = new jmash.component.JVolumeSpinner();
        jLabel24 = new javax.swing.JLabel();
        spnTrub1 = new jmash.component.JVolumeSpinner();
        jLabel25 = new javax.swing.JLabel();
        spnShrink1 = new jmash.component.JMashSpinner();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        spnBoiltime1 = new jmash.component.JMashSpinner();
        jLabel28 = new javax.swing.JLabel();
        spnLosses1 = new jmash.component.JVolumeSpinner();
        jLabel29 = new javax.swing.JLabel();
        spnKg1 = new jmash.component.JMashSpinner();
        jLabel30 = new javax.swing.JLabel();
        spnLitriKg1 = new jmash.component.JMashSpinner();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        spnEvaporation1 = new jmash.component.JVolumeSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        spnPreBoil1 = new jmash.component.JVolumeSpinner();
        jLabel40 = new javax.swing.JLabel();
        spnPostBoil1 = new jmash.component.JVolumeSpinner();
        jPanel9 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        spnMash1 = new jmash.component.JVolumeSpinner();
        jLabel42 = new javax.swing.JLabel();
        spnSparge1 = new jmash.component.JVolumeSpinner();
        jLabel43 = new javax.swing.JLabel();
        spnResult1 = new jmash.component.JVolumeSpinner();
        spnNeiGrani1 = new jmash.component.JVolumeSpinner();
        jLabel44 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        spnBatchSize2 = new jmash.component.JVolumeSpinner();
        jLabel46 = new javax.swing.JLabel();
        spnTrub2 = new jmash.component.JVolumeSpinner();
        jLabel47 = new javax.swing.JLabel();
        spnShrink2 = new jmash.component.JMashSpinner();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        spnBoiltime2 = new jmash.component.JMashSpinner();
        jLabel50 = new javax.swing.JLabel();
        spnLosses2 = new jmash.component.JVolumeSpinner();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        spnEvaporation2 = new jmash.component.JVolumeSpinner();
        spnKg2 = new jmash.component.JWeightSpinner();
        spnSG = new jmash.component.JGravitySpinner();
        jLabel64 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        spnPreBoil2 = new jmash.component.JVolumeSpinner();
        jLabel62 = new javax.swing.JLabel();
        spnPostBoil2 = new jmash.component.JVolumeSpinner();
        jPanel12 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        spnResult2 = new jmash.component.JVolumeSpinner();
        spnNeiGrani2 = new jmash.component.JVolumeSpinner();
        jLabel66 = new javax.swing.JLabel();
        spnSGF = new jmash.component.JGravitySpinner();

        setClosable(true);
        setIconifiable(true);
        setTitle("Acqua necessaria");

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametri"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Batch size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        spnBatchSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBatchSizeStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnBatchSize, gridBagConstraints);

        jLabel2.setText("Perdite nel Trub");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        spnTrub.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTrubStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnTrub, gridBagConstraints);

        jLabel3.setText("Espansione termica");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        spnShrink.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnShrinkStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnShrink, gridBagConstraints);

        jLabel4.setText("Evaporazione");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Durata bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        spnBoiltime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBoiltimeStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnBoiltime, gridBagConstraints);

        jLabel6.setText("Perdite sparge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        spnLosses.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLossesStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnLosses, gridBagConstraints);

        jLabel7.setText("Mash: grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        spnKg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnKgStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnKg, gridBagConstraints);

        jLabel8.setText("Mash:  L/kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        spnLitriKg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLitriKgStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnLitriKg, gridBagConstraints);

        jLabel12.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel13.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        jLabel14.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel14, gridBagConstraints);

        jLabel15.setText("ogni ora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel15, gridBagConstraints);

        jLabel16.setText("minuti");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel16, gridBagConstraints);

        jLabel17.setText("es. spazio morto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel17, gridBagConstraints);

        jLabel18.setText("kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel18, gridBagConstraints);

        jLabel19.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jLabel19, gridBagConstraints);

        spnEvaporation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnEvaporationStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel1.add(spnEvaporation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati"));
        jPanel2.setPreferredSize(new java.awt.Dimension(265, 148));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("Usata nel mash");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel11, gridBagConstraints);

        spnMash.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel2.add(spnMash, gridBagConstraints);

        jLabel10.setText("Per lo sparge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel10, gridBagConstraints);

        spnSparge.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel2.add(spnSparge, gridBagConstraints);

        jLabel9.setText("Totale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        spnResult.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel2.add(spnResult, gridBagConstraints);

        spnNeiGrani.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel2.add(spnNeiGrani, gridBagConstraints);

        jLabel22.setText("Rimane nei grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel22, gridBagConstraints);

        jLabel52.setFont(jLabel52.getFont());
        jLabel52.setText("Assorbimento / kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel52, gridBagConstraints);

        spnAssorbimento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnAssorbimentoStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel2.add(spnAssorbimento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Rabbocchi"));
        jPanel3.setPreferredSize(new java.awt.Dimension(310, 97));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel20.setText("Pre-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel3.add(jLabel20, gridBagConstraints);

        spnPreBoil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPreBoilStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel3.add(spnPreBoil, gridBagConstraints);

        jLabel21.setText("Post-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel3.add(jLabel21, gridBagConstraints);

        spnPostBoil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPostBoilStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel3.add(spnPostBoil, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel3, gridBagConstraints);

        jTabbedPane1.addTab("Con Sparge", jPanel4);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametri"));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel23.setText("Batch size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel23, gridBagConstraints);

        spnBatchSize1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBatchSize1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnBatchSize1, gridBagConstraints);

        jLabel24.setText("Perdite nel Trub");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel24, gridBagConstraints);

        spnTrub1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTrub1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnTrub1, gridBagConstraints);

        jLabel25.setText("Espansione termica");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel25, gridBagConstraints);

        spnShrink1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnShrink1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnShrink1, gridBagConstraints);

        jLabel26.setText("Evaporazione");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel26, gridBagConstraints);

        jLabel27.setText("Durata bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel27, gridBagConstraints);

        spnBoiltime1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBoiltime1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnBoiltime1, gridBagConstraints);

        jLabel28.setText("Perdite sparge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel28, gridBagConstraints);

        spnLosses1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLosses1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnLosses1, gridBagConstraints);

        jLabel29.setText("Mash: grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel29, gridBagConstraints);

        spnKg1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnKg1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnKg1, gridBagConstraints);

        jLabel30.setText("Mash:  L/kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel7.add(jLabel30, gridBagConstraints);

        spnLitriKg1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLitriKg1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnLitriKg1, gridBagConstraints);

        jLabel31.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel31, gridBagConstraints);

        jLabel32.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel32, gridBagConstraints);

        jLabel33.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel33, gridBagConstraints);

        jLabel34.setText("ogni ora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel34, gridBagConstraints);

        jLabel35.setText("minuti");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel35, gridBagConstraints);

        jLabel36.setText("es. spazio morto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel36, gridBagConstraints);

        jLabel37.setText("kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel37, gridBagConstraints);

        jLabel38.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jLabel38, gridBagConstraints);

        spnEvaporation1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnEvaporation1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel7.add(spnEvaporation1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel7, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Rabbocchi"));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel39.setText("Pre-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel8.add(jLabel39, gridBagConstraints);

        spnPreBoil1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPreBoil1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel8.add(spnPreBoil1, gridBagConstraints);

        jLabel40.setText("Post-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel8.add(jLabel40, gridBagConstraints);

        spnPostBoil1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPostBoil1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel8.add(spnPostBoil1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel8, gridBagConstraints);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati"));
        jPanel9.setPreferredSize(new java.awt.Dimension(250, 128));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel41.setText("Usata nel mash");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel9.add(jLabel41, gridBagConstraints);

        spnMash1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel9.add(spnMash1, gridBagConstraints);

        jLabel42.setText("Per lo sparge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel9.add(jLabel42, gridBagConstraints);

        spnSparge1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel9.add(spnSparge1, gridBagConstraints);

        jLabel43.setText("Totale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel9.add(jLabel43, gridBagConstraints);

        spnResult1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel9.add(spnResult1, gridBagConstraints);

        spnNeiGrani1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel9.add(spnNeiGrani1, gridBagConstraints);

        jLabel44.setText("Rimane nei grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel9.add(jLabel44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel9, gridBagConstraints);

        jTabbedPane1.addTab("Batch sparge", jPanel5);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametri"));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel45.setText("Batch size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel45, gridBagConstraints);

        spnBatchSize2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnBatchSize2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBatchSize2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnBatchSize2, gridBagConstraints);

        jLabel46.setText("Perdite nel Trub");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel46, gridBagConstraints);

        spnTrub2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnTrub2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTrub2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnTrub2, gridBagConstraints);

        jLabel47.setText("Espansione termica");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel47, gridBagConstraints);

        spnShrink2.setPreferredSize(new java.awt.Dimension(96, 20));
        spnShrink2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnShrink2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnShrink2, gridBagConstraints);

        jLabel48.setText("Evaporazione");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel48, gridBagConstraints);

        jLabel49.setText("Durata bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel49, gridBagConstraints);

        spnBoiltime2.setPreferredSize(new java.awt.Dimension(96, 20));
        spnBoiltime2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBoiltime2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnBoiltime2, gridBagConstraints);

        jLabel50.setText("Perdite");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel50, gridBagConstraints);

        spnLosses2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnLosses2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLosses2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnLosses2, gridBagConstraints);

        jLabel51.setText("Mash: grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel51, gridBagConstraints);

        jLabel53.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel53, gridBagConstraints);

        jLabel54.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel54, gridBagConstraints);

        jLabel55.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel55, gridBagConstraints);

        jLabel56.setText("ogni ora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel56, gridBagConstraints);

        jLabel57.setText("minuti");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel57, gridBagConstraints);

        jLabel58.setText("es. spazio morto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel58, gridBagConstraints);

        jLabel60.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel10.add(jLabel60, gridBagConstraints);

        spnEvaporation2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnEvaporation2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnEvaporation2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnEvaporation2, gridBagConstraints);

        spnKg2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnKg2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnKg2, gridBagConstraints);

        spnSG.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSGStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel10.add(spnSG, gridBagConstraints);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang"); // NOI18N
        jLabel64.setText(bundle.getString("Densita_finale")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jLabel64, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel6.add(jPanel10, gridBagConstraints);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Rabbocchi"));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel61.setText("Pre-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel11.add(jLabel61, gridBagConstraints);

        spnPreBoil2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnPreBoil2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPreBoil2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel11.add(spnPreBoil2, gridBagConstraints);

        jLabel62.setText("Post-bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel11.add(jLabel62, gridBagConstraints);

        spnPostBoil2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnPostBoil2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPostBoil2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel11.add(spnPostBoil2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel6.add(jPanel11, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati"));
        jPanel12.setPreferredSize(new java.awt.Dimension(250, 128));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel63.setText("Densità pre-boll.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel12.add(jLabel63, gridBagConstraints);

        jLabel65.setText("Totale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel12.add(jLabel65, gridBagConstraints);

        spnResult2.setEnabled(false);
        spnResult2.setPreferredSize(new java.awt.Dimension(128, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel12.add(spnResult2, gridBagConstraints);

        spnNeiGrani2.setEnabled(false);
        spnNeiGrani2.setPreferredSize(new java.awt.Dimension(128, 20));
        spnNeiGrani2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnNeiGrani2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel12.add(spnNeiGrani2, gridBagConstraints);

        jLabel66.setText("Rimane nei grani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel12.add(jLabel66, gridBagConstraints);

        spnSGF.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        jPanel12.add(spnSGF, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel6.add(jPanel12, gridBagConstraints);
        
        jTabbedPane1.addTab("No sparge", jPanel6);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-758)/2, (screenSize.height-365)/2, 758, 365);
    }// </editor-fold>//GEN-END:initComponents

    private void spnAssorbimentoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnAssorbimentoStateChanged
	calc();
    }//GEN-LAST:event_spnAssorbimentoStateChanged
    
    private void spnSGStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSGStateChanged
	calc2();
    }//GEN-LAST:event_spnSGStateChanged
    
    private void spnNeiGrani2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnNeiGrani2StateChanged

    }//GEN-LAST:event_spnNeiGrani2StateChanged
    
    private void spnKg2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnKg2StateChanged
	calc2();
    }//GEN-LAST:event_spnKg2StateChanged
    
    private void spnPostBoil2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPostBoil2StateChanged
	calc2();
    }//GEN-LAST:event_spnPostBoil2StateChanged
    
    private void spnPreBoil2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPreBoil2StateChanged
	calc2();
    }//GEN-LAST:event_spnPreBoil2StateChanged
    
    private void spnEvaporation2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnEvaporation2StateChanged
	calc2();
    }//GEN-LAST:event_spnEvaporation2StateChanged
    
    private void spnLosses2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLosses2StateChanged
	calc2();
    }//GEN-LAST:event_spnLosses2StateChanged
    
    private void spnBoiltime2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBoiltime2StateChanged
	calc2();
    }//GEN-LAST:event_spnBoiltime2StateChanged
    
    private void spnShrink2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnShrink2StateChanged
	calc2();
    }//GEN-LAST:event_spnShrink2StateChanged
    
    private void spnTrub2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTrub2StateChanged
	calc2();
    }//GEN-LAST:event_spnTrub2StateChanged
    
    private void spnBatchSize2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBatchSize2StateChanged
	calc2();
    }//GEN-LAST:event_spnBatchSize2StateChanged
    
    private void spnPostBoil1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPostBoil1StateChanged

    }//GEN-LAST:event_spnPostBoil1StateChanged
    
    private void spnPreBoil1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPreBoil1StateChanged

    }//GEN-LAST:event_spnPreBoil1StateChanged
    
    private void spnEvaporation1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnEvaporation1StateChanged

    }//GEN-LAST:event_spnEvaporation1StateChanged
    
    private void spnLitriKg1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLitriKg1StateChanged

    }//GEN-LAST:event_spnLitriKg1StateChanged
    
    private void spnKg1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnKg1StateChanged

    }//GEN-LAST:event_spnKg1StateChanged
    
    private void spnLosses1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLosses1StateChanged

    }//GEN-LAST:event_spnLosses1StateChanged
    
    private void spnBoiltime1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBoiltime1StateChanged

    }//GEN-LAST:event_spnBoiltime1StateChanged
    
    private void spnShrink1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnShrink1StateChanged

    }//GEN-LAST:event_spnShrink1StateChanged
    
    private void spnTrub1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTrub1StateChanged

    }//GEN-LAST:event_spnTrub1StateChanged
    
    private void spnBatchSize1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBatchSize1StateChanged

    }//GEN-LAST:event_spnBatchSize1StateChanged
    
    private void spnEvaporationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnEvaporationStateChanged
	calc();
    }//GEN-LAST:event_spnEvaporationStateChanged
    
    private void spnPostBoilStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPostBoilStateChanged
	calc();
    }//GEN-LAST:event_spnPostBoilStateChanged
    
    private void spnPreBoilStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPreBoilStateChanged
	calc();
    }//GEN-LAST:event_spnPreBoilStateChanged
    
    private void spnLitriKgStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLitriKgStateChanged
	calc();
    }//GEN-LAST:event_spnLitriKgStateChanged
    
    private void spnKgStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnKgStateChanged
	calc();
    }//GEN-LAST:event_spnKgStateChanged
    
    private void spnLossesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLossesStateChanged
	calc();
    }//GEN-LAST:event_spnLossesStateChanged
    
    private void spnBoiltimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBoiltimeStateChanged
	calc();
    }//GEN-LAST:event_spnBoiltimeStateChanged
    
    private void spnShrinkStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnShrinkStateChanged
	calc();
    }//GEN-LAST:event_spnShrinkStateChanged
    
    private void spnTrubStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTrubStateChanged
	calc();
    }//GEN-LAST:event_spnTrubStateChanged
    
    private void spnBatchSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBatchSizeStateChanged
	calc();
    }//GEN-LAST:event_spnBatchSizeStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jmash.component.JVolumeSpinner spnAssorbimento;
    private jmash.component.JVolumeSpinner spnBatchSize;
    private jmash.component.JVolumeSpinner spnBatchSize1;
    private jmash.component.JVolumeSpinner spnBatchSize2;
    private jmash.component.JMashSpinner spnBoiltime;
    private jmash.component.JMashSpinner spnBoiltime1;
    private jmash.component.JMashSpinner spnBoiltime2;
    private jmash.component.JVolumeSpinner spnEvaporation;
    private jmash.component.JVolumeSpinner spnEvaporation1;
    private jmash.component.JVolumeSpinner spnEvaporation2;
    private jmash.component.JMashSpinner spnKg;
    private jmash.component.JMashSpinner spnKg1;
    private jmash.component.JWeightSpinner spnKg2;
    private jmash.component.JMashSpinner spnLitriKg;
    private jmash.component.JMashSpinner spnLitriKg1;
    private jmash.component.JVolumeSpinner spnLosses;
    private jmash.component.JVolumeSpinner spnLosses1;
    private jmash.component.JVolumeSpinner spnLosses2;
    private jmash.component.JVolumeSpinner spnMash;
    private jmash.component.JVolumeSpinner spnMash1;
    private jmash.component.JVolumeSpinner spnNeiGrani;
    private jmash.component.JVolumeSpinner spnNeiGrani1;
    private jmash.component.JVolumeSpinner spnNeiGrani2;
    private jmash.component.JVolumeSpinner spnPostBoil;
    private jmash.component.JVolumeSpinner spnPostBoil1;
    private jmash.component.JVolumeSpinner spnPostBoil2;
    private jmash.component.JVolumeSpinner spnPreBoil;
    private jmash.component.JVolumeSpinner spnPreBoil1;
    private jmash.component.JVolumeSpinner spnPreBoil2;
    private jmash.component.JVolumeSpinner spnResult;
    private jmash.component.JVolumeSpinner spnResult1;
    private jmash.component.JVolumeSpinner spnResult2;
    private jmash.component.JGravitySpinner spnSG;
    private jmash.component.JGravitySpinner spnSGF;
    private jmash.component.JMashSpinner spnShrink;
    private jmash.component.JMashSpinner spnShrink1;
    private jmash.component.JMashSpinner spnShrink2;
    private jmash.component.JVolumeSpinner spnSparge;
    private jmash.component.JVolumeSpinner spnSparge1;
    private jmash.component.JVolumeSpinner spnTrub;
    private jmash.component.JVolumeSpinner spnTrub1;
    private jmash.component.JVolumeSpinner spnTrub2;
    // End of variables declaration//GEN-END:variables
    
    public void calc(){
	
	
	Main.putIntoCache("WaterNeeded.shrink", spnShrink.getDoubleValue());
	Main.putIntoCache("WaterNeeded.rabboccoPreBoil", spnPreBoil.getVolume());
	Main.putIntoCache("WaterNeeded.rabboccoPostBoil", spnPostBoil.getVolume());
	Main.putIntoCache("WaterNeeded.coefficienteAssorbimento", spnAssorbimento.getVolume());
	
	double res =
		(spnBatchSize.getVolume())/((100-spnShrink.getDoubleValue())/100)+
		spnTrub.getVolume()+
		spnEvaporation.getVolume()*spnBoiltime.getDoubleValue()/60+
		spnLosses.getVolume()+
		spnKg.getDoubleValue()*spnAssorbimento.getVolume()
		;
	spnNeiGrani.setVolume(spnKg.getDoubleValue()*spnAssorbimento.getVolume());
	spnMash.setVolume(spnKg.getDoubleValue()*spnLitriKg.getDoubleValue());
	spnSparge.setVolume(res-spnKg.getDoubleValue()*spnLitriKg.getDoubleValue());
	res+=spnPreBoil.getVolume()+spnPostBoil.getVolume();
	spnResult.setVolume(res);
    }
    
    public void calc2(){
	// http://home.elp.rr.com/brewbeer/files/nbsparge.html
	// http://www.promash.com/TipsNTricks/CommonTasks/MechOfNoSparge.html
	double preBoil=(spnBatchSize2.getVolume())/((100-spnShrink2.getDoubleValue())/100)+
		spnTrub2.getVolume()+
		spnEvaporation2.getVolume()*spnBoiltime2.getDoubleValue()/60;
	double K=1/(Utils.poundToKg(1)/Utils.galToLit(0.125));
	
	double preBoilSG=(((spnSG.getGravity()-1)*1000)*spnBatchSize2.getVolume()/preBoil)/1000+1;
	
	
//	System.out.println("preBoil SG = "+preBoilSG);
//	System.out.println("preBoil Vol = "+preBoil);
	double TL =(preBoil+spnLosses2.getVolume());
//	System.out.println("devo produrre pero' "+TL);
	
	double F = 4.0;
	
//	System.out.println("voglio fare un mash con "+F+" L/kg");
	
	double KG =spnKg2.getWeight()/1000;
	double ML=((F-K)*KG)-spnLosses2.getVolume() ;
//	System.out.println("per cui posso recuperare dal mash "+ML+" litri");
	double MSG=(((preBoilSG-1)*1000)*preBoil/ML)/1000+1;
//	System.out.println("che dovranno avere una SG di "+MSG);
	
	double A = ML*((MSG-1)*1000)/((preBoilSG-1)*1000)-ML;
//	System.out.println("per cui devo aggiungere "+A+" litri di acqua");
//	System.out.println("per avere "+(A+ML));
	
	
//	System.out.println("");
	
	
//	double KG =spnKg2.getWeight()/1000;
//	double S= (preBoil+spnLosses2.getVolume())/(preBoil+spnLosses2.getVolume() - KG*K);
//	System.out.println("Kettle:"+preBoil);
//	System.out.println("Grain Scale-Up Factor S:"+S);
//	double Wg=S*KG;
//	System.out.println("Total Grain Required Wg: "+Wg);
//	double R=K*S/(S-1);
//	System.out.println("Mash Thickness R: "+R);
//	double W= (K+R)* Wg;
//	System.out.println("Mash Water  W: "+W);
//	spnResult2.setVolume(W);
//	spnNeiGrani2.setVolume(W-preBoil);
//	spnSGF.setGravity(1+(spnBatchSize2.getVolume()*(spnSG.getGravity()-1)*1000/(preBoil+spnLosses2.getVolume()))/1000);
//	System.out.println("\n\n"+K+"\n\n");
    }
    
    public void setBatchSize(double size){
	spnBatchSize.setVolume(size);
	spnBatchSize.setEnabled(false);
    }
    public void setMashKg(double size){
	spnKg.setDoubleValue(size);
	spnKg.setEnabled(false);
    }
    public void setBoilTime(double size){
	spnBoiltime.setDoubleValue(size);
	spnBoiltime.setEnabled(false);
    }    
    public double getTotWater(){
	return spnResult.getVolume();
    }
}
