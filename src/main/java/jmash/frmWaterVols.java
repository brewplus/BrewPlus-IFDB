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

public class frmWaterVols extends javax.swing.JInternalFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 9035810375597285423L;

	/** Creates new form WaterNeeded */
    public frmWaterVols() {
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
	
	setBackground(getBackground().darker());
	//jPanel4.setBackground(jPanel4.getBackground().darker());
	jTabbedPane1.setEnabledAt(1,true);
	calc();
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
    public frmWaterVols(double batchSize, double kg, double boilTime, double evap) {
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

        jTabbedPane1.addTab("BIAB", jPanel5);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang");

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-758)/2, (screenSize.height-365)/2, 758, 423);
    }// </editor-fold>//GEN-END:initComponents

    private void spnAssorbimentoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnAssorbimentoStateChanged
	calc();
    }//GEN-LAST:event_spnAssorbimentoStateChanged
    
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jmash.component.JVolumeSpinner spnAssorbimento;
    private jmash.component.JVolumeSpinner spnBatchSize;
    private jmash.component.JVolumeSpinner spnBatchSize1;
    private jmash.component.JMashSpinner spnBoiltime;
    private jmash.component.JMashSpinner spnBoiltime1;
    private jmash.component.JVolumeSpinner spnEvaporation;
    private jmash.component.JVolumeSpinner spnEvaporation1;
    private jmash.component.JMashSpinner spnKg;
    private jmash.component.JMashSpinner spnKg1;
    private jmash.component.JMashSpinner spnLitriKg;
    private jmash.component.JMashSpinner spnLitriKg1;
    private jmash.component.JVolumeSpinner spnLosses;
    private jmash.component.JVolumeSpinner spnLosses1;
    private jmash.component.JVolumeSpinner spnMash;
    private jmash.component.JVolumeSpinner spnMash1;
    private jmash.component.JVolumeSpinner spnNeiGrani;
    private jmash.component.JVolumeSpinner spnNeiGrani1;
    private jmash.component.JVolumeSpinner spnPostBoil;
    private jmash.component.JVolumeSpinner spnPostBoil1;
    private jmash.component.JVolumeSpinner spnPreBoil;
    private jmash.component.JVolumeSpinner spnPreBoil1;
    private jmash.component.JVolumeSpinner spnResult;
    private jmash.component.JVolumeSpinner spnResult1;
    private jmash.component.JMashSpinner spnShrink;
    private jmash.component.JMashSpinner spnShrink1;
    private jmash.component.JVolumeSpinner spnSparge;
    private jmash.component.JVolumeSpinner spnSparge1;
    private jmash.component.JVolumeSpinner spnTrub;
    private jmash.component.JVolumeSpinner spnTrub1;
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
