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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdom.Document;
import org.jdom.Element;

import jmash.Main.BitterBUGU;
import jmash.component.JMashSpinner;
import jmash.component.JUnitSpinner;
import jmash.interfaces.Constants;
import jmash.interfaces.XmlAble;

/**
 *
 * @author Alessandro
 */
public class ConfigurationTool extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JInternalFrame parent;

	private boolean actionListenerOn = true;
	private Gui gui;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
		private jmash.component.JMashSpinner fldBoil;
		private jmash.component.JMashSpinner fldTempGrani;
		private jmash.component.JUnitSpinner fldEff;
		private javax.swing.JTextField fldNick;
		private javax.swing.JTextField fldProxy;
		private javax.swing.JTextField fldProxyPort;
		private javax.swing.JTextField fldPwd;
		private jmash.component.JMashSpinner fldSLM;
		private jmash.component.JMashSpinner fldDHEA;
		private javax.swing.JTextField fldServer;
		private jmash.component.JUnitSpinner fldVolumeFin;
		private javax.swing.JButton saveButton;
		private javax.swing.JLabel jLabel1;
		private javax.swing.JLabel jLabel10;
		private javax.swing.JLabel jLabel11;
		private javax.swing.JLabel jLabel13;
		private javax.swing.JLabel jLabel14;
		private javax.swing.JLabel jLabel2;
		private javax.swing.JLabel jLabel4;
		private javax.swing.JLabel jLabelBUGU;
		private javax.swing.JLabel jLabelDHEA;
		private javax.swing.JLabel jLabelTempGrani;
		// private javax.swing.JLabel jLabel6;
		// private javax.swing.JLabel jLabel7;
		// private javax.swing.JLabel jLabel8;
		// private javax.swing.JLabel jLabel9;
		private javax.swing.JPanel jPanelDefaults;
		// private javax.swing.JPanel jPanel2;
		private javax.swing.JPanel jPanelDatiImpianto;
		private javax.swing.JPanel jPanelProfiliAcque;
		private javax.swing.JToolBar jToolBar1;
		private jmash.component.JUnitSpinner spnRapportoAcquaGrani;
		private jmash.component.JUnitSpinner spnAssorbimentoGraniEsausti;
		private jmash.component.JUnitSpinner spnLostToTrub;
		private GridBagConstraints gridBagConstraints_1;
		private GridBagConstraints gridBagConstraints_2;
		private GridBagConstraints gridBagConstraints_3;
		private GridBagConstraints gridBagConstraints_4;
		private GridBagConstraints gridBagConstraints_7;
		private GridBagConstraints gridBagConstraints_8;
		private GridBagConstraints gridBagConstraints_8_1;
		private GridBagConstraints gridBagConstraints_9;
		private GridBagConstraints gridBagConstraints_10;
		private GridBagConstraints gridBagConstraints_11;
		private GridBagConstraints gridBagConstraints_12;
		private GridBagConstraints gbc_jPanelDefaults;
		private GridBagConstraints gbcLabelTempGrani;
		private GridBagConstraints gbcTempGrani;
		private JCheckBox chckbxNewCheckBox;
		private JButton btnNewButton;
		private JComboBox<String> cmbBUGURatio;
		private JComboBox<String> cmbLanguage;
		private JComboBox<String> cmbBreweryProfile;
		private JLabel lblBreweryProfile;
		private GridBagConstraints gbc_cmbBUGU;
		private JLabel lblLanguage;
		private GridBagConstraints gridBagConstraints_14;
		private GridBagConstraints gridBagConstraints_15;
		private GridBagConstraints gbc_spnAssorbimentoGraniEsausti;
		private GridBagConstraints gbc_spnRapportoAcquaGrani;
		private GridBagConstraints gridBagConstraints_20;
		private GridBagConstraints gridBagConstraints_21;
		private JLabel lblPercentualeEvaporazione;
		private JLabel lblContrazionePerRaffreddamento;
		private JCheckBox chckbxBiab;
		private JUnitSpinner spnPercentualeEvaporazione;
		private JUnitSpinner spnContrazionePerRaffreddamento;
		private GridBagConstraints gbc_jPanelDatiImpianto;
		private GridBagConstraints gridBagConstraints_ProfiliAcque;
		private JLabel lblBiab;
		private JLabel lblAcquaOrigine;
		private JComboBox<String> cmbWaterProfileOrigine;
		private JLabel lblCalcioOrigine;
		private JLabel lblMagnesioOrigine;
		private JLabel lblSolfatoOrigine;
		private JLabel lblCloruro;
		private JLabel lblSodioOrigine;
		private JLabel lblCarbonatoOrigine;
		private JMashSpinner spinnerCalcioOrigine;
		private JMashSpinner spinnerMagnesioOrigine;
		private JMashSpinner spinnerSolfatoOrigine;
		private JMashSpinner spinnerCloruroOrigine;
		private JMashSpinner spinnerSodioOrigine;
		private JMashSpinner spinnerCarbonatoOrigine;
		private JPanel panelSaliOrigine;
		// End of variables declaration//GEN-END:variables

	/** Creates new form ConfigurationTool */
	public ConfigurationTool(Gui gui) {
		this.gui = gui;
		setResizable(true);

		initComponents();
		
		parent = this;
		setBorder(Utils.getDefaultBorder());
//		fldEff.setModel(75.0, 1, 100, 1, "0", "CT.eff");
//		fldVolumeFin.setModel(23.0, 1, 9999999, 1, "0.0", "CT.vf");
		fldSLM.setModel(300.0, 1, 8844, 1, "0", "CT.slm");
		fldDHEA.setModel(45.0, 1, 60, 1, "0", "CT.dhea");
		fldBoil.setModel(90.0, 1, 1000, 1);
		fldEff.setDoubleValue(Main.config.getEfficienza());
		fldVolumeFin.setDoubleValue(Main.config.getVolumeFin());
		fldSLM.setValue(Main.config.getMetriSLM());
		fldDHEA.setValue(Main.config.getAmaroDHEA());
		fldBoil.setValue(Main.config.getBoilTime());
		fldTempGrani.setValue(Main.config.getTempGrani());
		fldServer.setText(Main.config.getRemoteServer());
		fldNick.setText(Main.config.getNickIHB());
		fldPwd.setText(Main.config.getPasswordIHB());
		fldProxy.setText(Main.config.getProxyHost());
		fldProxyPort.setText(Main.config.getProxyPort());
		chckbxNewCheckBox.setSelected((Main.config.getPotLibGal() == 1));

		cmbBUGURatio = new JComboBox<String>();
		cmbBUGURatio.setModel(new DefaultComboBoxModel<String>(new String[] { "Tinseth", "Rager", "Daniels" }));
		gbc_cmbBUGU = new GridBagConstraints();

		gbc_cmbBUGU.insets = new Insets(0, 0, 5, 5);
		gbc_cmbBUGU.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBUGU.gridx = 1;
		gbc_cmbBUGU.gridy = 2;
		jPanelDefaults.add(cmbBUGURatio, gbc_cmbBUGU);
		if (Main.config.getBUGURatio() == BitterBUGU.TIN)
			cmbBUGURatio.setSelectedIndex(0);
		if (Main.config.getBUGURatio() == BitterBUGU.RAG)
			cmbBUGURatio.setSelectedIndex(1);
		if (Main.config.getBUGURatio() == BitterBUGU.DAN)
			cmbBUGURatio.setSelectedIndex(2);

		lblLanguage = new JLabel(Main.bundle.getString("label.language"));
		GridBagConstraints gbc_lblLanguage = new GridBagConstraints();
		gbc_lblLanguage.insets = new Insets(0, 0, 0, 5);
		gbc_lblLanguage.anchor = GridBagConstraints.WEST;
		gbc_lblLanguage.gridx = 0;
		gbc_lblLanguage.gridy = 3;
		jPanelDefaults.add(lblLanguage, gbc_lblLanguage);

		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setModel(new DefaultComboBoxModel<String>(new String[] { Constants.ITALIAN, Constants.ENGLISH }));
		GridBagConstraints gbc_cmbLanguage = new GridBagConstraints();
		gbc_cmbLanguage.insets = new Insets(0, 0, 0, 5);
		gbc_cmbLanguage.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLanguage.gridx = 1;
		gbc_cmbLanguage.gridy = 3;
		jPanelDefaults.add(cmbLanguage, gbc_cmbLanguage);
		spnAssorbimentoGraniEsausti.setDoubleValue(Main.config.getLitriPerKg());
		spnRapportoAcquaGrani.setDoubleValue(Main.config.getRapportoAcquaGrani());

		spnPercentualeEvaporazione.setDoubleValue(Main.config.getPercentualeEvaporazione());
		chckbxBiab.setSelected(Main.config.getBiab());

		spnContrazionePerRaffreddamento.setDoubleValue(Main.config.getContrazionePerRaffreddamento());

		jLabel11 = new javax.swing.JLabel();

		jLabel11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel11.setText(Main.bundle.getString("label.lostTrub"));
		gridBagConstraints_14 = new java.awt.GridBagConstraints();
		gridBagConstraints_14.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_14.gridx = 0;
		gridBagConstraints_14.gridy = 8;
		gridBagConstraints_14.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDatiImpianto.add(jLabel11, gridBagConstraints_14);
		spnLostToTrub = new jmash.component.JUnitSpinner("L", 57);
		spnLostToTrub.setModel(Main.config.getLostToTrub(), 0.0, 1000000, 0.1, "0.00", "CT.ltt");
		spnLostToTrub.setDoubleValue(Main.config.getLostToTrub());

		spnLostToTrub.setFont(spnLostToTrub.getFont());
		gridBagConstraints_15 = new java.awt.GridBagConstraints();
		gridBagConstraints_15.gridx = 1;
		gridBagConstraints_15.gridy = 8;
		gridBagConstraints_15.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanelDatiImpianto.add(spnLostToTrub, gridBagConstraints_15);

		cmbLanguage.setSelectedItem(
				"it_IT".equalsIgnoreCase(Main.config.getLocale()) ? Constants.ITALIAN : Constants.ENGLISH);
		// ButtonGroup group = new ButtonGroup();

		// BreweryProfile toFind = new BreweryProfile(null, null,
		// Main.config.getVolumeFin(), Main.config.getEfficienza(),
		// Main.config.getLitriPerKg(), Main.config.getRapportoAcquaGrani(),
		// Main.config.getPercentualeEvaporazione(),
		// Main.config.getContrazionePerRaffreddamento(),
		// Main.config.getLostToTrub(), Main.config.getBiab().toString());
		//
		// Integer indexBreweryProfile =
		// Gui.breweryProfilePickerTableModel.findFirstIndexBreweryProfile(toFind);
		// if (indexBreweryProfile != null)
		// {
		// cmbBreweryProfile.setSelectedIndex(indexBreweryProfile + 1);
		// }

		selectBreweryProfile();

		addBreweryProfileListeners();
		
		double calcioSource = Main.config.getCalcioSource() != null ? Main.config.getCalcioSource() : 0.0;
		spinnerCalcioOrigine.setModel(calcioSource, 0, 100000, 1, "0.0", null);
		spinnerCalcioOrigine.setDoubleValue(calcioSource);
		double cloruroSource = Main.config.getCloruroSource() != null ? Main.config.getCloruroSource() : 0.0;
		spinnerCloruroOrigine.setModel(cloruroSource, 0, 100000, 1, "0.0", null);
		spinnerCloruroOrigine.setDoubleValue(cloruroSource);
		double magnesioSource = Main.config.getMagnesioSource() != null ? Main.config.getMagnesioSource() : 0.0;
		spinnerMagnesioOrigine.setModel(magnesioSource, 0, 100000, 1, "0.0", null);
		spinnerMagnesioOrigine.setDoubleValue(magnesioSource);
		double sodioSource = Main.config.getSodioSource() != null ? Main.config.getSodioSource() : 0.0;
		spinnerSodioOrigine.setModel(sodioSource, 0, 100000, 1, "0.0", null);
		spinnerSodioOrigine.setDoubleValue(sodioSource);
		double solfatoSource = Main.config.getSolfatoSource() != null ? Main.config.getSolfatoSource() : 0.0;
		spinnerSolfatoOrigine.setModel(solfatoSource, 0, 100000, 1, "0.0", null);
		spinnerSolfatoOrigine.setDoubleValue(solfatoSource);
		double carbonatoSource = Main.config.getCarbonatoSource() != null ? Main.config.getCarbonatoSource() : 0.0;
		spinnerCarbonatoOrigine.setModel(carbonatoSource, 0, 100000, 1, "0.0", null);
		spinnerCarbonatoOrigine.setDoubleValue(carbonatoSource);
		selectWaterProfileOrigine();
		
		addWaterProfileOrigineListeners();

	}

	private void addBreweryProfileListeners() {
		cmbBreweryProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedIndex = cmbBreweryProfile.getSelectedIndex();
				if (selectedIndex > 0) {
					actionListenerOn = false;
					String selectedBreweryProfileName = cmbBreweryProfile.getItemAt(selectedIndex);

					BreweryProfile selectedBreweryProfile = Gui.breweryProfilePickerTableModel
							.findBreweryProfile(selectedBreweryProfileName);

					fldVolumeFin.setDoubleValue(selectedBreweryProfile.getVolumeFinale());
					fldEff.setDoubleValue(selectedBreweryProfile.getEfficienza());

					spnAssorbimentoGraniEsausti.setDoubleValue(selectedBreweryProfile.getAssorbimentoGraniEsausti());
					spnRapportoAcquaGrani.setDoubleValue(selectedBreweryProfile.getRapportoAcquaGrani());
					spnPercentualeEvaporazione.setDoubleValue(selectedBreweryProfile.getPercentualeEvaporazione());
					spnContrazionePerRaffreddamento
							.setDoubleValue(selectedBreweryProfile.getContrazionePerRaffreddamento());
					spnLostToTrub.setDoubleValue(selectedBreweryProfile.getPerditeNelTrub());
					chckbxBiab.setSelected(selectedBreweryProfile.isBiab());
					actionListenerOn = true;
				}

			}

		});

		fldVolumeFin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		fldEff.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		spnAssorbimentoGraniEsausti.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		spnRapportoAcquaGrani.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		spnPercentualeEvaporazione.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		spnContrazionePerRaffreddamento.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		spnLostToTrub.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectBreweryProfile();
			}
		});
		chckbxBiab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectBreweryProfile();
			}
		});

	}
	
	private void addWaterProfileOrigineListeners() {
		cmbWaterProfileOrigine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedIndex = cmbWaterProfileOrigine.getSelectedIndex();
				if (selectedIndex > 0) {
					actionListenerOn = false;
					
					String selectedWaterProfileName = cmbWaterProfileOrigine.getItemAt(selectedIndex);

					WaterProfile selectedWaterProfile = Gui.waterPickerTableModel.findWaterProfile(selectedWaterProfileName);
					
					spinnerCalcioOrigine.setDoubleValue(selectedWaterProfile.getCalcio());
					spinnerMagnesioOrigine.setDoubleValue(selectedWaterProfile.getMagnesio());
					spinnerSolfatoOrigine.setDoubleValue(selectedWaterProfile.getSolfato());
					spinnerCloruroOrigine.setDoubleValue(selectedWaterProfile.getCloruro());
					spinnerSodioOrigine.setDoubleValue(selectedWaterProfile.getSodio());
					spinnerCarbonatoOrigine.setDoubleValue(selectedWaterProfile.getCarbonato());
					
					actionListenerOn = true;
				}

			}

		});

		spinnerCalcioOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		spinnerMagnesioOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		spinnerSolfatoOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		spinnerCloruroOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		spinnerSodioOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		spinnerCarbonatoOrigine.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectWaterProfileOrigine();
			}
		});
		

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		java.awt.GridBagConstraints gridBagConstraints;

		jToolBar1 = new javax.swing.JToolBar();
		saveButton = new javax.swing.JButton();
		jPanelDefaults = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		
		jLabel2 = new javax.swing.JLabel();
//		fldVolumeFin = new jmash.component.JMashSpinner();
		jLabel4 = new javax.swing.JLabel();
		fldSLM = new jmash.component.JMashSpinner();
		jLabel10 = new javax.swing.JLabel();
		fldBoil = new jmash.component.JMashSpinner();
		fldTempGrani = new jmash.component.JMashSpinner();
		// jPanel2 = new javax.swing.JPanel();
		jLabelBUGU = new javax.swing.JLabel();
		fldServer = new javax.swing.JTextField();
		// jLabel6 = new javax.swing.JLabel();
		// jLabel7 = new javax.swing.JLabel();
		fldNick = new javax.swing.JTextField();
		fldPwd = new javax.swing.JTextField();
		// jLabel8 = new javax.swing.JLabel();
		// jLabel9 = new javax.swing.JLabel();
		fldProxy = new javax.swing.JTextField();
		fldProxyPort = new javax.swing.JTextField();
		jPanelDatiImpianto = new javax.swing.JPanel();
		jPanelProfiliAcque = new javax.swing.JPanel();

		setBorder(null);
		setClosable(true);
		setIconifiable(true);
		setTitle("Configurazione");
		setFont(getFont());
		getContentPane().setLayout(new java.awt.GridBagLayout());

		jToolBar1.setFloatable(false);

		saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filesave.png"))); // NOI18N
		saveButton.setToolTipText("Salva configurazione");
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveConfiguration(evt);
			}
		});
		jToolBar1.add(saveButton);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		getContentPane().add(jToolBar1, gridBagConstraints);

		jPanelDefaults.setBorder(javax.swing.BorderFactory.createTitledBorder("Defaults"));
		jPanelDefaults.setMinimumSize(new Dimension(550, 150));
		GridBagLayout gbl_jPanelDefaults = new GridBagLayout();
		gbl_jPanelDefaults.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		jPanelDefaults.setLayout(gbl_jPanelDefaults);

		chckbxNewCheckBox = new JCheckBox("Potenziale SG libbre/gallone");
		chckbxNewCheckBox.setToolTipText("");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 0;
		jPanelDefaults.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.showMsg(
						"Selezionare l'opzione se il potenziale degli ingredienti è espresso in libbre/Gallone e non in grammi/Litro.\nNota: il software BrewPlus considera il potenziale ingredienti espresso come libbre/Gallone.",
						parent);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		jPanelDefaults.add(btnNewButton, gbc_btnNewButton);

		// jLabel1.setFont(jLabel1.getFont());
//		jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		jLabel1.setText("Efficienza %");
//		gridBagConstraints_1 = new java.awt.GridBagConstraints();
//		gridBagConstraints_1.insets = new Insets(0, 0, 5, 5);
//		gridBagConstraints_1.gridx = 0;
//		gridBagConstraints_1.gridy = 1;
//		gridBagConstraints_1.fill = java.awt.GridBagConstraints.HORIZONTAL;
//		gridBagConstraints_1.anchor = java.awt.GridBagConstraints.EAST;
//		jPanelDefaults.add(jLabel1, gridBagConstraints_1);
//
//		fldEff.setFont(fldEff.getFont());
//		gridBagConstraints_2 = new java.awt.GridBagConstraints();
//		gridBagConstraints_2.insets = new Insets(0, 0, 5, 5);
//		gridBagConstraints_2.gridx = 1;
//		gridBagConstraints_2.gridy = 1;
//		gridBagConstraints_2.fill = java.awt.GridBagConstraints.BOTH;
//		jPanelDefaults.add(fldEff, gridBagConstraints_2);

		// jLabel2.setFont(jLabel2.getFont());
//		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		jLabel2.setText("Volume finale (litri)");
//		gridBagConstraints_3 = new java.awt.GridBagConstraints();
//		gridBagConstraints_3.insets = new Insets(0, 0, 5, 5);
//		gridBagConstraints_3.gridx = 0;
//		gridBagConstraints_3.gridy = 2;
//		gridBagConstraints_3.fill = java.awt.GridBagConstraints.HORIZONTAL;
//		gridBagConstraints_3.anchor = java.awt.GridBagConstraints.EAST;
//		jPanelDefaults.add(jLabel2, gridBagConstraints_3);
//		fldVolumeFin.setFont(fldVolumeFin.getFont());
//		gridBagConstraints_4 = new java.awt.GridBagConstraints();
//		gridBagConstraints_4.insets = new Insets(0, 0, 5, 5);
//		gridBagConstraints_4.gridx = 1;
//		gridBagConstraints_4.gridy = 2;
//		gridBagConstraints_4.fill = java.awt.GridBagConstraints.BOTH;
//		jPanelDefaults.add(fldVolumeFin, gridBagConstraints_4);
		
		jLabelDHEA = new javax.swing.JLabel();
		// jLabelDHEA.setFont(jLabelDHEA.getFont());
		jLabelDHEA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelDHEA.setText("Amaro DHEA");
		gridBagConstraints_7 = new java.awt.GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_7.gridx = 2;
		gridBagConstraints_7.gridy = 2;
		gridBagConstraints_7.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_7.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDefaults.add(jLabelDHEA, gridBagConstraints_7);
		fldDHEA = new jmash.component.JMashSpinner();

		fldDHEA.setFont(fldDHEA.getFont());
		gridBagConstraints_10 = new java.awt.GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_10.gridx = 3;
		gridBagConstraints_10.gridy = 2;
		gridBagConstraints_10.fill = java.awt.GridBagConstraints.BOTH;
		jPanelDefaults.add(fldDHEA, gridBagConstraints_10);
				
		jLabelTempGrani = new javax.swing.JLabel();
		jLabelTempGrani.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelTempGrani.setText("Temp. grani");
		gbcLabelTempGrani = new java.awt.GridBagConstraints();
		gbcLabelTempGrani.insets = new Insets(0, 0, 5, 5);
		gbcLabelTempGrani.gridx = 2;
		gbcLabelTempGrani.gridy = 3;
		gbcLabelTempGrani.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbcLabelTempGrani.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDefaults.add(jLabelTempGrani, gbcLabelTempGrani);
		fldTempGrani = new jmash.component.JMashSpinner();

		fldTempGrani.setFont(fldTempGrani.getFont());
		gbcTempGrani = new java.awt.GridBagConstraints();
		gbcTempGrani.insets = new Insets(0, 0, 5, 0);
		gbcTempGrani.gridx = 3;
		gbcTempGrani.gridy = 3;
		gbcTempGrani.fill = java.awt.GridBagConstraints.BOTH;
		jPanelDefaults.add(fldTempGrani, gbcTempGrani);

		jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel4.setText("Metri SLM");
		jLabel4.setToolTipText("Metri sul livello del mare");
		gridBagConstraints_8 = new java.awt.GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_8.gridx = 0;
		gridBagConstraints_8.gridy = 1;
		gridBagConstraints_8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDefaults.add(jLabel4, gridBagConstraints_8);

		jLabelBUGU.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelBUGU.setText("IBU");
		gridBagConstraints_8_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_8_1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8_1.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_8_1.gridx = 0;
		gridBagConstraints_8_1.gridy = 2;
		gridBagConstraints_8_1.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDefaults.add(jLabelBUGU, gridBagConstraints_8_1);

		fldSLM.setFont(fldSLM.getFont());
		gridBagConstraints_9 = new java.awt.GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_9.gridx = 1;
		gridBagConstraints_9.gridy = 1;
		gridBagConstraints_9.fill = java.awt.GridBagConstraints.BOTH;
		jPanelDefaults.add(fldSLM, gridBagConstraints_9);

		// jLabel10.setFont(jLabel10.getFont());
		jLabel10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel10.setText("Bollitura (minuti)");
		gridBagConstraints_11 = new java.awt.GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_11.gridx = 2;
		gridBagConstraints_11.gridy = 1;
		gridBagConstraints_11.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_11.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDefaults.add(jLabel10, gridBagConstraints_11);

		fldBoil.setFont(fldBoil.getFont());
		gridBagConstraints_12 = new java.awt.GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_12.gridx = 3;
		gridBagConstraints_12.gridy = 1;
		gridBagConstraints_12.fill = java.awt.GridBagConstraints.BOTH;
		jPanelDefaults.add(fldBoil, gridBagConstraints_12);

		gbc_jPanelDefaults = new java.awt.GridBagConstraints();
		gbc_jPanelDefaults.gridheight = 2;
		gbc_jPanelDefaults.gridx = 0;
		gbc_jPanelDefaults.gridy = 1;
		gbc_jPanelDefaults.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(jPanelDefaults, gbc_jPanelDefaults);
		
		jPanelProfiliAcque.setBorder(javax.swing.BorderFactory.createTitledBorder("Profili acque"));
		jPanelProfiliAcque.setFont(jPanelDatiImpianto.getFont());
		GridBagLayout gbl_jPanelProfiliAcque = new GridBagLayout();
		gbl_jPanelProfiliAcque.rowWeights = new double[]{0.0, 0.0};
		gbl_jPanelProfiliAcque.columnWeights = new double[]{0.0, 1.0};
		jPanelProfiliAcque.setLayout(gbl_jPanelProfiliAcque);
		jPanelProfiliAcque.setMinimumSize(new Dimension(650, 110));
		jPanelProfiliAcque.setPreferredSize(new Dimension(650, 110));
		
		gridBagConstraints_ProfiliAcque = new java.awt.GridBagConstraints();
		gridBagConstraints_ProfiliAcque.anchor = GridBagConstraints.EAST;
		gridBagConstraints_ProfiliAcque.gridx = 0;
		gridBagConstraints_ProfiliAcque.gridy = 4;
		gridBagConstraints_ProfiliAcque.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(jPanelProfiliAcque, gridBagConstraints_ProfiliAcque);
		
		lblAcquaOrigine = new JLabel("Acqua origine");
		GridBagConstraints gbc_lblAcquaOrigine = new GridBagConstraints();
		gbc_lblAcquaOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcquaOrigine.anchor = GridBagConstraints.WEST;
		gbc_lblAcquaOrigine.gridx = 0;
		gbc_lblAcquaOrigine.gridy = 0;
		jPanelProfiliAcque.add(lblAcquaOrigine, gbc_lblAcquaOrigine);
		
		cmbWaterProfileOrigine = new JComboBox<>();
		cmbWaterProfileOrigine.setModel(new DefaultComboBoxModel<String>(Gui.waterPickerTableModel.getWaterNames("- Seleziona -")));
		GridBagConstraints gbc_cmbAcquaOrigine = new GridBagConstraints();
		gbc_cmbAcquaOrigine.insets = new Insets(0, 0, 5, 5);
//		gbc_cmbAcquaOrigine.fill = GridBagConstraints.HORIZONTAL;
		Dimension cmbAcquaOrigineDimension = new Dimension(205,20);
		cmbWaterProfileOrigine.setPreferredSize(cmbAcquaOrigineDimension);
		cmbWaterProfileOrigine.setMinimumSize(cmbAcquaOrigineDimension);
		cmbWaterProfileOrigine.setMaximumSize(cmbAcquaOrigineDimension);
		gbc_cmbAcquaOrigine.anchor = GridBagConstraints.WEST;
		gbc_cmbAcquaOrigine.gridx = 1;
		gbc_cmbAcquaOrigine.gridy = 0;
		jPanelProfiliAcque.add(cmbWaterProfileOrigine, gbc_cmbAcquaOrigine);
		
		panelSaliOrigine = new JPanel();
		GridBagLayout gbl_jPanelSaliOrigine = new GridBagLayout();
		panelSaliOrigine.setLayout(gbl_jPanelSaliOrigine);
		
		
		GridBagConstraints gbc_panelSaliOrigine = new GridBagConstraints();
		gbc_panelSaliOrigine.insets = new Insets(0, 0, 0, 5);
		gbc_panelSaliOrigine.anchor = GridBagConstraints.WEST;
		gbc_panelSaliOrigine.gridx = 1;
		gbc_panelSaliOrigine.gridy = 1;
		
		jPanelProfiliAcque.add(panelSaliOrigine, gbc_panelSaliOrigine);
		
		lblCalcioOrigine = new JLabel("Calcio");
		GridBagConstraints gbc_lblCalcioOrigine = new GridBagConstraints();
		gbc_lblCalcioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_lblCalcioOrigine.gridx = 0;
		gbc_lblCalcioOrigine.gridy = 0;
		panelSaliOrigine.add(lblCalcioOrigine, gbc_lblCalcioOrigine);
		
		lblMagnesioOrigine = new JLabel("Magnesio");
		GridBagConstraints gbc_lblMagnesioOrigine = new GridBagConstraints();
		gbc_lblMagnesioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagnesioOrigine.gridx = 1;
		gbc_lblMagnesioOrigine.gridy = 0;
		panelSaliOrigine.add(lblMagnesioOrigine, gbc_lblMagnesioOrigine);
		
		lblSolfatoOrigine = new JLabel("Solfato");
		GridBagConstraints gbc_lblSolfatoOrigine = new GridBagConstraints();
		gbc_lblSolfatoOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_lblSolfatoOrigine.gridx = 2;
		gbc_lblSolfatoOrigine.gridy = 0;
		panelSaliOrigine.add(lblSolfatoOrigine, gbc_lblSolfatoOrigine);
		
		lblCloruro = new JLabel("Cloruro");
		GridBagConstraints gbc_lblCloruro = new GridBagConstraints();
		gbc_lblCloruro.insets = new Insets(0, 0, 5, 5);
		gbc_lblCloruro.gridx = 3;
		gbc_lblCloruro.gridy = 0;
		panelSaliOrigine.add(lblCloruro, gbc_lblCloruro);
		
		lblSodioOrigine = new JLabel("Sodio");
		GridBagConstraints gbc_lblSodioOrigine = new GridBagConstraints();
		gbc_lblSodioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_lblSodioOrigine.gridx = 4;
		gbc_lblSodioOrigine.gridy = 0;
		panelSaliOrigine.add(lblSodioOrigine, gbc_lblSodioOrigine);
		
		lblCarbonatoOrigine = new JLabel("Carbonato");
		GridBagConstraints gbc_lblCarbonatoOrigine = new GridBagConstraints();
		gbc_lblCarbonatoOrigine.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarbonatoOrigine.gridx = 5;
		gbc_lblCarbonatoOrigine.gridy = 0;
		panelSaliOrigine.add(lblCarbonatoOrigine, gbc_lblCarbonatoOrigine);
		
		Dimension spinnerSaltOriginDimension = new Dimension(65,  20);
		
		spinnerCalcioOrigine = new JMashSpinner();
		spinnerCalcioOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerCalcioOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerCalcioOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerCalcioOrigine = new GridBagConstraints();
		gbc_spinnerCalcioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerCalcioOrigine.gridx = 0;
		gbc_spinnerCalcioOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerCalcioOrigine, gbc_spinnerCalcioOrigine);
		
		spinnerMagnesioOrigine = new JMashSpinner();
		spinnerMagnesioOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerMagnesioOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerMagnesioOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerMagnesioOrigine = new GridBagConstraints();
		gbc_spinnerMagnesioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerMagnesioOrigine.gridx = 1;
		gbc_spinnerMagnesioOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerMagnesioOrigine, gbc_spinnerMagnesioOrigine);
		
		spinnerSolfatoOrigine = new JMashSpinner();
		spinnerSolfatoOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerSolfatoOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerSolfatoOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerSolfatoOrigine = new GridBagConstraints();
		gbc_spinnerSolfatoOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSolfatoOrigine.gridx = 2;
		gbc_spinnerSolfatoOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerSolfatoOrigine, gbc_spinnerSolfatoOrigine);
		
		spinnerCloruroOrigine = new JMashSpinner();
		spinnerCloruroOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerCloruroOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerCloruroOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerCloruroOrigine = new GridBagConstraints();
		gbc_spinnerCloruroOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerCloruroOrigine.gridx = 3;
		gbc_spinnerCloruroOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerCloruroOrigine, gbc_spinnerCloruroOrigine);
		
		spinnerSodioOrigine = new JMashSpinner();
		spinnerSodioOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerSodioOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerSodioOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerSodioOrigine = new GridBagConstraints();
		gbc_spinnerSodioOrigine.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerSodioOrigine.gridx = 4;
		gbc_spinnerSodioOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerSodioOrigine, gbc_spinnerSodioOrigine);
		
		spinnerCarbonatoOrigine = new JMashSpinner();
		spinnerCarbonatoOrigine.setPreferredSize(spinnerSaltOriginDimension);
		spinnerCarbonatoOrigine.setMinimumSize(spinnerSaltOriginDimension);
		spinnerCarbonatoOrigine.setMaximumSize(spinnerSaltOriginDimension);
		GridBagConstraints gbc_spinnerCarbonatoOrigine = new GridBagConstraints();
		gbc_spinnerCarbonatoOrigine.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerCarbonatoOrigine.gridx = 5;
		gbc_spinnerCarbonatoOrigine.gridy = 1;
		panelSaliOrigine.add(spinnerCarbonatoOrigine, gbc_spinnerCarbonatoOrigine);
		
		

		jPanelDatiImpianto.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		jPanelDatiImpianto.setFont(jPanelDatiImpianto.getFont());
		jPanelDatiImpianto.setLayout(new java.awt.GridBagLayout());
		jPanelDatiImpianto.setMinimumSize(new Dimension(550, 275));
		jPanelDatiImpianto.setPreferredSize(new Dimension(550, 275));
		
		gbc_jPanelDatiImpianto = new java.awt.GridBagConstraints();
		gbc_jPanelDatiImpianto.anchor = GridBagConstraints.EAST;
		gbc_jPanelDatiImpianto.gridx = 0;
		gbc_jPanelDatiImpianto.gridy = 3;
		gbc_jPanelDatiImpianto.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(jPanelDatiImpianto, gbc_jPanelDatiImpianto);

		lblBreweryProfile = new JLabel("Profilo impianto");
		GridBagConstraints gbc_lblProfiliImpianto = new GridBagConstraints();
		gbc_lblProfiliImpianto.anchor = GridBagConstraints.EAST;
		gbc_lblProfiliImpianto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfiliImpianto.gridx = 0;
		gbc_lblProfiliImpianto.gridy = 0;
		jPanelDatiImpianto.add(lblBreweryProfile, gbc_lblProfiliImpianto);

		cmbBreweryProfile = new JComboBox<>();
		cmbBreweryProfile.setModel(new DefaultComboBoxModel<String>(Gui.breweryProfilePickerTableModel.getBreweryProfileNames("- Seleziona -")));

		GridBagConstraints gbc_cmbProfiliImpianto = new GridBagConstraints();
		gbc_cmbProfiliImpianto.anchor = GridBagConstraints.WEST;
		gbc_cmbProfiliImpianto.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProfiliImpianto.gridx = 1;
		gbc_cmbProfiliImpianto.gridy = 0;
		jPanelDatiImpianto.add(cmbBreweryProfile, gbc_cmbProfiliImpianto);

		lblBiab = new JLabel("BIAB");
		GridBagConstraints gbc_lblBiab = new GridBagConstraints();
		gbc_lblBiab.anchor = GridBagConstraints.EAST;
		gbc_lblBiab.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiab.gridx = 0;
		gbc_lblBiab.gridy = 1;
		jPanelDatiImpianto.add(lblBiab, gbc_lblBiab);

		chckbxBiab = new JCheckBox("");
		GridBagConstraints gbc_chckbxBiab = new GridBagConstraints();
		gbc_chckbxBiab.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxBiab.anchor = GridBagConstraints.WEST;
		gbc_chckbxBiab.gridx = 1;
		gbc_chckbxBiab.gridy = 1;
		jPanelDatiImpianto.add(chckbxBiab, gbc_chckbxBiab);
		
		
		JLabel lblPercentualeEfficienza = new JLabel("Efficienza");
		GridBagConstraints gbc_lblPercentualeEfficienza  = new GridBagConstraints();
		gbc_lblPercentualeEfficienza.anchor = GridBagConstraints.EAST;
		gbc_lblPercentualeEfficienza.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercentualeEfficienza.gridx = 0;
		gbc_lblPercentualeEfficienza.gridy = 2;
		jPanelDatiImpianto.add(lblPercentualeEfficienza, gbc_lblPercentualeEfficienza);

		fldEff = new JUnitSpinner("%", 57);
		fldEff.setModel(Main.config.getEfficienza(), 0.0, 100, 0.25, "0.00", "CT.eff");
		
//		fldEff.setModel(75.0, 1, 100, 1, "0", "CT.eff");
		GridBagConstraints gbc_spnPercentualeEfficienza = new GridBagConstraints();
		gbc_spnPercentualeEfficienza.insets = new Insets(0, 0, 5, 0);
		gbc_spnPercentualeEfficienza.gridx = 1;
		gbc_spnPercentualeEfficienza.gridy = 2;
		jPanelDatiImpianto.add(fldEff, gbc_spnPercentualeEfficienza);
		
//		fldEff.setModel(75.0, 1, 100, 1, "0", "CT.eff");
//		fldVolumeFin.setModel(23.0, 1, 9999999, 1, "0.0", "CT.vf");
		
		JLabel jLabelVolumeFinale = new javax.swing.JLabel();

		jLabelVolumeFinale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelVolumeFinale.setText("Volume finale");
		GridBagConstraints gridBagConstraints_30 = new java.awt.GridBagConstraints();
		gridBagConstraints_30.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_30.gridx = 0;
		gridBagConstraints_30.gridy = 3;
		gridBagConstraints_30.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDatiImpianto.add(jLabelVolumeFinale, gridBagConstraints_30);
		fldVolumeFin = new JUnitSpinner("L", 57);
		fldVolumeFin.setModel(Main.config.getVolumeFin(), 0.0, 1000000, 0.1, "0.00", "CT.ltt");
		fldVolumeFin.setDoubleValue(Main.config.getVolumeFin());

		fldVolumeFin.setFont(fldVolumeFin.getFont());
		GridBagConstraints gridBagConstraints_31 = new java.awt.GridBagConstraints();
		gridBagConstraints_31.gridx = 1;
		gridBagConstraints_31.gridy = 3;
		gridBagConstraints_31.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanelDatiImpianto.add(fldVolumeFin, gridBagConstraints_31);
		
		
		jLabel13 = new javax.swing.JLabel();
		jLabel13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel13.setText("Assorbimento grani esausti");
		gridBagConstraints_20 = new java.awt.GridBagConstraints();
		gridBagConstraints_20.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_20.gridx = 0;
		gridBagConstraints_20.gridy = 4;
		gridBagConstraints_20.anchor = java.awt.GridBagConstraints.EAST;
		jPanelDatiImpianto.add(jLabel13, gridBagConstraints_20);
		spnAssorbimentoGraniEsausti = new JUnitSpinner("L/kg", 57);
		spnAssorbimentoGraniEsausti.setModel(Main.config.getLitriPerKg(), 0.0, 1000000, 0.01, "0.00", "CT.lkg");
		spnAssorbimentoGraniEsausti.setFont(spnAssorbimentoGraniEsausti.getFont());
		gbc_spnAssorbimentoGraniEsausti = new java.awt.GridBagConstraints();
		gbc_spnAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 0);
		gbc_spnAssorbimentoGraniEsausti.gridx = 1;
		gbc_spnAssorbimentoGraniEsausti.gridy = 4;
		gbc_spnAssorbimentoGraniEsausti.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelDatiImpianto.add(spnAssorbimentoGraniEsausti, gbc_spnAssorbimentoGraniEsausti);
		jLabel14 = new javax.swing.JLabel();

		jLabel14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel14.setText("Rapporto acqua/grani");
		gridBagConstraints_21 = new java.awt.GridBagConstraints();
		gridBagConstraints_21.anchor = GridBagConstraints.EAST;
		gridBagConstraints_21.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_21.gridx = 0;
		gridBagConstraints_21.gridy = 5;
		jPanelDatiImpianto.add(jLabel14, gridBagConstraints_21);
		
		spnRapportoAcquaGrani = new JUnitSpinner("L/Kg", 57);
		spnRapportoAcquaGrani.setModel(Main.config.getRapportoAcquaGrani(), 0.0, 1000000, 0.1, "0.00", "CT.ev");
		spnRapportoAcquaGrani.setFont(spnRapportoAcquaGrani.getFont());
		gbc_spnRapportoAcquaGrani = new java.awt.GridBagConstraints();
		gbc_spnRapportoAcquaGrani.insets = new Insets(0, 0, 5, 0);
		gbc_spnRapportoAcquaGrani.gridx = 1;
		gbc_spnRapportoAcquaGrani.gridy = 5;
		gbc_spnRapportoAcquaGrani.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelDatiImpianto.add(spnRapportoAcquaGrani, gbc_spnRapportoAcquaGrani);

		lblPercentualeEvaporazione = new JLabel("Percentuale evaporazione");
		GridBagConstraints gbc_lblPercentualeEvaporazione = new GridBagConstraints();
		gbc_lblPercentualeEvaporazione.anchor = GridBagConstraints.EAST;
		gbc_lblPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercentualeEvaporazione.gridx = 0;
		gbc_lblPercentualeEvaporazione.gridy = 6;
		jPanelDatiImpianto.add(lblPercentualeEvaporazione, gbc_lblPercentualeEvaporazione);

		spnPercentualeEvaporazione = new JUnitSpinner("%", 57);
		spnPercentualeEvaporazione.setModel(Main.config.getPercentualeEvaporazione(), 0.0, 100, 0.25, "0.00", null);
		GridBagConstraints gbc_spnPercentualeEvaporazione = new GridBagConstraints();
		gbc_spnPercentualeEvaporazione.insets = new Insets(0, 0, 5, 0);
		gbc_spnPercentualeEvaporazione.gridx = 1;
		gbc_spnPercentualeEvaporazione.gridy = 6;
		jPanelDatiImpianto.add(spnPercentualeEvaporazione, gbc_spnPercentualeEvaporazione);

		lblContrazionePerRaffreddamento = new JLabel("Contrazione per raffreddamento");
		GridBagConstraints gbc_lblContrazionePerRaffreddamento = new GridBagConstraints();
		gbc_lblContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrazionePerRaffreddamento.anchor = GridBagConstraints.EAST;
		gbc_lblContrazionePerRaffreddamento.gridx = 0;
		gbc_lblContrazionePerRaffreddamento.gridy = 7;
		jPanelDatiImpianto.add(lblContrazionePerRaffreddamento, gbc_lblContrazionePerRaffreddamento);
		spnContrazionePerRaffreddamento = new JUnitSpinner("%", 57);
		spnContrazionePerRaffreddamento.setModel(Main.config.getContrazionePerRaffreddamento(), 0.0, 100, 0.25, "0.00", null);
		GridBagConstraints gbc_spnContrazionePerRaffreddamento = new GridBagConstraints();
		gbc_spnContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 0);
		gbc_spnContrazionePerRaffreddamento.gridx = 1;
		gbc_spnContrazionePerRaffreddamento.gridy = 7;
		jPanelDatiImpianto.add(spnContrazionePerRaffreddamento, gbc_spnContrazionePerRaffreddamento);
		
		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void saveConfiguration(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed

		Config config = new Config();

		config.setEfficienza(fldEff.getDoubleValue());
		config.setVolumeFin(fldVolumeFin.getDoubleValue());
		// config.setVolumeBoil(fldVolumeBoll.getIntegerValue());
		config.setMetriSLM(fldSLM.getIntegerValue());
		config.setAmaroDHEA(fldDHEA.getIntegerValue());
		config.setBoilTime(fldBoil.getIntegerValue());
		config.setTempGrani(fldTempGrani.getDoubleValue());
		config.setProxyHost(fldProxy.getText());
		config.setProxyPort(fldProxyPort.getText());
		config.setRemoteServer(fldServer.getText());
		config.setNickIHB(fldNick.getText());
		config.setPasswordIHB(fldPwd.getText());
		config.setPotLibGal((chckbxNewCheckBox.isSelected()) ? 1 : 0);
		config.setLostToTrub(spnLostToTrub.getDoubleValue());
		config.setLitriPerKg(spnAssorbimentoGraniEsausti.getDoubleValue());
		config.setRapportoAcquaGrani(spnRapportoAcquaGrani.getDoubleValue());
		config.setLocale(Constants.ITALIAN.equalsIgnoreCase((String) cmbLanguage.getSelectedItem()) ? "it_IT" : "en_US");
		config.setPercentualeEvaporazione(spnPercentualeEvaporazione.getDoubleValue());
		config.setContrazionePerRaffreddamento(spnContrazionePerRaffreddamento.getDoubleValue());
		config.setBiab(chckbxBiab.isSelected());
		
		config.setCalcioSource(spinnerCalcioOrigine.getDoubleValue());
		config.setMagnesioSource(spinnerMagnesioOrigine.getDoubleValue());
		config.setSolfatoSource(spinnerSolfatoOrigine.getDoubleValue());
		config.setCloruroSource(spinnerCloruroOrigine.getDoubleValue());
		config.setSodioSource(spinnerSodioOrigine.getDoubleValue());
		config.setCarbonatoSource(spinnerCarbonatoOrigine.getDoubleValue());

		if (cmbBUGURatio.getSelectedIndex() == 0)
			config.setBUGURatio(BitterBUGU.TIN);
		if (cmbBUGURatio.getSelectedIndex() == 1)
			config.setBUGURatio(BitterBUGU.RAG);
		if (cmbBUGURatio.getSelectedIndex() == 2)
			config.setBUGURatio(BitterBUGU.DAN);

		Main.config = config;
		File file = new File(Main.configXML);
        if ("1".equals(System.getProperty("ide"))) {
            String currentDir = System.getProperty("user.dir");
            String currentParentDir = new File(currentDir).getParent();
            file = new File(currentParentDir + Main.resource_distr + Main.configXML);
        }
		Document doc = new Document();
		Element root = config.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, this);
		if (config.getProxyHost() != null)
			System.setProperty("http.proxyHost", config.getProxyHost());
		if (config.getProxyPort() != null)
			System.setProperty("http.proxyPort", config.getProxyPort());
		
		saveCurrentBreweryProfile();
		
		gui.updateRicette();

	}// GEN-LAST:event_jButton3ActionPerformed

	private void saveCurrentBreweryProfile() {
		BreweryProfile breweryProfile = getCurrentBreweryProfile();

		if (!Gui.breweryProfilePickerTableModel.isPresentBreweryProfile(breweryProfile)) {

			AskSaveNewBrewingProfile ask = new AskSaveNewBrewingProfile(breweryProfile,
					"Salvare i dati configurati anche su un nuovo profilo impianto?");

			if (ask.doAsk(this)) {

				breweryProfile.setNome(ask.getNomeProfilo());
				breweryProfile.setDescrizione(ask.getDescrizioneProfilo());

				Gui.breweryProfilePickerTableModel.addRow(breweryProfile);
				cmbBreweryProfile.insertItemAt(breweryProfile.getNome(), cmbBreweryProfile.getItemCount());

				List<BreweryProfile> list = Gui.breweryProfilePickerTableModel.getRows();
				Document doc = new Document();

				Element root = !list.isEmpty() ? new Element(((XmlAble) list.get(0)).getTag())
						: new Element(breweryProfile.getTag());
				doc.setRootElement(root);
				for (Object o : list) {
					if (o instanceof XmlAble) {
						root.addContent(((XmlAble) o).toXml());
					}
				}
				if ("1".equals(System.getProperty("ide"))) {
		            String currentDir = System.getProperty("user.dir");
		            String currentParentDir = new File(currentDir).getParent();
		            Utils.saveXmlAsFile(doc, new File(currentParentDir + Main.resource_distr + Main.breweryProfileXML), this);
		        } else {
		        	Utils.saveXmlAsFile(doc, new File(Main.breweryProfileXML), this);
		        }

			}
		}
		
		WaterProfile waterProfileOrigine = getCurrentWaterProfileOrigine();
		
		if (!waterProfileOrigine.isLiteEmpty() && !Gui.waterPickerTableModel.isPresentWaterProfile(waterProfileOrigine)) {

			AskSaveNewWaterProfile ask = new AskSaveNewWaterProfile(waterProfileOrigine,
					"Salvare i dati configurati anche su un nuovo profilo acqua?");

			if (ask.doAsk(this)) {

				waterProfileOrigine.setNome(ask.getNomeProfilo());

				Gui.waterPickerTableModel.addRow(waterProfileOrigine);
				cmbWaterProfileOrigine.insertItemAt(waterProfileOrigine.getNome(), cmbWaterProfileOrigine.getItemCount());

				List<WaterProfile> list = Gui.waterPickerTableModel.getRows();
				Document doc = new Document();

				Element root = !list.isEmpty() ? new Element(((XmlAble) list.get(0)).getTag())
						: new Element(waterProfileOrigine.getTag());
				doc.setRootElement(root);
				for (Object o : list) {
					if (o instanceof XmlAble) {
						root.addContent(((XmlAble) o).toXml());
					}
				}
				if ("1".equals(System.getProperty("ide"))) {
		            String currentDir = System.getProperty("user.dir");
		            String currentParentDir = new File(currentDir).getParent();
		            Utils.saveXmlAsFile(doc, new File(currentParentDir + Main.resource_distr + Main.waterXML), this);
		        } else {
		        	Utils.saveXmlAsFile(doc, new File(Main.waterXML), this);
		        }

			}
		}

		selectBreweryProfile();
		selectWaterProfileOrigine();
		// try {
		// reloadMethod.invoke(Main.class);
		// } catch (Exception ex) {
		// Utils.showException(ex, "", this);
		// }

	}

	

	private void selectBreweryProfile() {

		if (actionListenerOn) {
			BreweryProfile toFind = getCurrentBreweryProfile();

			Integer indexBreweryProfile = Gui.breweryProfilePickerTableModel.findFirstIndexBreweryProfile(toFind);
			if (indexBreweryProfile != null) {
				cmbBreweryProfile.setSelectedIndex(indexBreweryProfile + 1);
			} else {
				cmbBreweryProfile.setSelectedIndex(0);
			}
		}
	}

	public BreweryProfile getCurrentBreweryProfile() {
		Double volumeFinale = fldVolumeFin.getDoubleValue();
		Double efficienza = fldEff.getDoubleValue();
		Double assorbimentoGraniEsausti = spnAssorbimentoGraniEsausti.getDoubleValue();
		Double rapportoAcquaGrani = spnRapportoAcquaGrani.getDoubleValue();
		Double percentualeEvaporazione = spnPercentualeEvaporazione.getDoubleValue();
		Double contrazionePerRaffreddamento = spnContrazionePerRaffreddamento.getDoubleValue();
		Double perditeNelTrub = spnLostToTrub.getDoubleValue();
		String biab = chckbxBiab.isSelected() ? "1" : "0";

		return new BreweryProfile(volumeFinale, efficienza, assorbimentoGraniEsausti, rapportoAcquaGrani,
				percentualeEvaporazione, contrazionePerRaffreddamento, perditeNelTrub, biab);
	}
	
	private void selectWaterProfileOrigine() {

		if (actionListenerOn) {
			WaterProfile toFind = getCurrentWaterProfileOrigine();

			Integer indexWaterProfileOrigine = Gui.waterPickerTableModel.findFirstIndexWaterProfile(toFind);
			if (indexWaterProfileOrigine != null) {
				cmbWaterProfileOrigine.setSelectedIndex(indexWaterProfileOrigine + 1);
			} else {
				cmbWaterProfileOrigine.setSelectedIndex(0);
			}
		}
	}
	
	public WaterProfile getCurrentWaterProfileOrigine() {
		
		Double calcio = spinnerCalcioOrigine.getDoubleValue();
		Double magnesio = spinnerMagnesioOrigine.getDoubleValue();
		Double solfato = spinnerSolfatoOrigine.getDoubleValue();
		Double cloruro = spinnerCloruroOrigine.getDoubleValue();
		Double sodio = spinnerSodioOrigine.getDoubleValue();
		Double carbonato = spinnerCarbonatoOrigine.getDoubleValue();
		return new WaterProfile(calcio, magnesio, solfato, cloruro, sodio, carbonato);
		
	}

}
