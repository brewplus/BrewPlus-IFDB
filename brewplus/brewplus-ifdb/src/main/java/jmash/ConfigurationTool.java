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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdom.Document;
import org.jdom.Element;

import jmash.Main.BitterBUGU;
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

	/** Creates new form ConfigurationTool */
	public ConfigurationTool() {
		setResizable(true);

		initComponents();
		parent = this;
		setBorder(Utils.getDefaultBorder());
		fldEff.setModel(75.0, 1, 100, 1, "0", "CT.eff");
		fldVolumeFin.setModel(23.0, 1, 9999999, 1, "0.0", "CT.vf");
		fldSLM.setModel(300.0, 1, 8844, 1, "0", "CT.slm");
		fldDHEA.setModel(45.0, 1, 60, 1, "0", "CT.dhea");
		fldBoil.setModel(90.0, 1, 1000, 1);
		fldEff.setValue(Main.config.getEfficienza());
		fldVolumeFin.setValue(Main.config.getVolumeFin());
		fldSLM.setValue(Main.config.getMetriSLM());
		fldDHEA.setValue(Main.config.getAmaroDHEA());
		fldBoil.setValue(Main.config.getBoilTime());
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
		gbc_cmbBUGU.gridy = 4;
		jPanel1.add(cmbBUGURatio, gbc_cmbBUGU);
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
		gbc_lblLanguage.gridy = 5;
		jPanel1.add(lblLanguage, gbc_lblLanguage);

		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setModel(new DefaultComboBoxModel<String>(new String[] { Constants.ITALIAN, Constants.ENGLISH }));
		GridBagConstraints gbc_cmbLanguage = new GridBagConstraints();
		gbc_cmbLanguage.insets = new Insets(0, 0, 0, 5);
		gbc_cmbLanguage.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLanguage.gridx = 1;
		gbc_cmbLanguage.gridy = 5;
		jPanel1.add(cmbLanguage, gbc_cmbLanguage);
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
		gridBagConstraints_14.gridy = 6;
		gridBagConstraints_14.anchor = java.awt.GridBagConstraints.EAST;
		jPanel4.add(jLabel11, gridBagConstraints_14);
		spnLostToTrub = new jmash.component.JUnitSpinner("L", 57);
		spnLostToTrub.setModel(Main.config.getLostToTrub(), 0.0, 1000000, 0.1, "0.00", "CT.ltt");
		spnLostToTrub.setDoubleValue(Main.config.getLostToTrub());

		spnLostToTrub.setFont(spnLostToTrub.getFont());
		gridBagConstraints_15 = new java.awt.GridBagConstraints();
		gridBagConstraints_15.gridx = 1;
		gridBagConstraints_15.gridy = 6;
		gridBagConstraints_15.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel4.add(spnLostToTrub, gridBagConstraints_15);

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

					fldVolumeFin.setIntegerValue(selectedBreweryProfile.getVolumeFinale());
					fldEff.setIntegerValue(selectedBreweryProfile.getEfficienza());

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
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		fldEff = new jmash.component.JMashSpinner();
		jLabel2 = new javax.swing.JLabel();
		fldVolumeFin = new jmash.component.JMashSpinner();
		jLabel4 = new javax.swing.JLabel();
		fldSLM = new jmash.component.JMashSpinner();
		jLabel10 = new javax.swing.JLabel();
		fldBoil = new jmash.component.JMashSpinner();
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
		jPanel4 = new javax.swing.JPanel();

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

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Defaults"));
		jPanel1.setMinimumSize(new Dimension(550, 210));
		jPanel1.setPreferredSize(new Dimension(550, 210));
		GridBagLayout gbl_jPanel1 = new GridBagLayout();
		gbl_jPanel1.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		jPanel1.setLayout(gbl_jPanel1);

		chckbxNewCheckBox = new JCheckBox("Potenziale SG libbre/gallone");
		chckbxNewCheckBox.setToolTipText("");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 0;
		jPanel1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.showMsg(
						"Selezionare l'opzione se il potenziale degli ingredienti è espresso in libbre/Gallone e non in grammi/Litro.\nNota: il software Hobbybrew considera il potenziale ingredienti espresso come libbre/Gallone.",
						parent);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		jPanel1.add(btnNewButton, gbc_btnNewButton);

		// jLabel1.setFont(jLabel1.getFont());
		jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel1.setText("Efficienza %");
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_1.anchor = java.awt.GridBagConstraints.EAST;
		jPanel1.add(jLabel1, gridBagConstraints_1);

		fldEff.setFont(fldEff.getFont());
		gridBagConstraints_2 = new java.awt.GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_2.gridx = 1;
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.fill = java.awt.GridBagConstraints.BOTH;
		jPanel1.add(fldEff, gridBagConstraints_2);

		// jLabel2.setFont(jLabel2.getFont());
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel2.setText("Volume finale (litri)");
		gridBagConstraints_3 = new java.awt.GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_3.gridx = 0;
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_3.anchor = java.awt.GridBagConstraints.EAST;
		jPanel1.add(jLabel2, gridBagConstraints_3);
		fldVolumeFin.setFont(fldVolumeFin.getFont());
		gridBagConstraints_4 = new java.awt.GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_4.gridx = 1;
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.fill = java.awt.GridBagConstraints.BOTH;
		jPanel1.add(fldVolumeFin, gridBagConstraints_4);
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
		jPanel1.add(jLabelDHEA, gridBagConstraints_7);
		fldDHEA = new jmash.component.JMashSpinner();

		fldDHEA.setFont(fldDHEA.getFont());
		gridBagConstraints_10 = new java.awt.GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_10.gridx = 3;
		gridBagConstraints_10.gridy = 2;
		gridBagConstraints_10.fill = java.awt.GridBagConstraints.BOTH;
		jPanel1.add(fldDHEA, gridBagConstraints_10);

		jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel4.setText("Metri SLM");
		jLabel4.setToolTipText("Metri sul livello del mare");
		gridBagConstraints_8 = new java.awt.GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_8.gridx = 0;
		gridBagConstraints_8.gridy = 3;
		gridBagConstraints_8.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8.anchor = java.awt.GridBagConstraints.EAST;
		jPanel1.add(jLabel4, gridBagConstraints_8);

		jLabelBUGU.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabelBUGU.setText("Ratio BU/GU");
		gridBagConstraints_8_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_8_1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8_1.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_8_1.gridx = 0;
		gridBagConstraints_8_1.gridy = 4;
		gridBagConstraints_8_1.anchor = java.awt.GridBagConstraints.EAST;
		jPanel1.add(jLabelBUGU, gridBagConstraints_8_1);

		fldSLM.setFont(fldSLM.getFont());
		gridBagConstraints_9 = new java.awt.GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_9.gridx = 1;
		gridBagConstraints_9.gridy = 3;
		gridBagConstraints_9.fill = java.awt.GridBagConstraints.BOTH;
		jPanel1.add(fldSLM, gridBagConstraints_9);

		// jLabel10.setFont(jLabel10.getFont());
		jLabel10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel10.setText("Bollitura (minuti)");
		gridBagConstraints_11 = new java.awt.GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_11.gridx = 2;
		gridBagConstraints_11.gridy = 1;
		gridBagConstraints_11.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints_11.anchor = java.awt.GridBagConstraints.EAST;
		jPanel1.add(jLabel10, gridBagConstraints_11);

		fldBoil.setFont(fldBoil.getFont());
		gridBagConstraints_12 = new java.awt.GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_12.gridx = 3;
		gridBagConstraints_12.gridy = 1;
		gridBagConstraints_12.fill = java.awt.GridBagConstraints.BOTH;
		jPanel1.add(fldBoil, gridBagConstraints_12);

		gridBagConstraints_13 = new java.awt.GridBagConstraints();
		gridBagConstraints_13.gridheight = 2;
		gridBagConstraints_13.gridx = 0;
		gridBagConstraints_13.gridy = 1;
		gridBagConstraints_13.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(jPanel1, gridBagConstraints_13);

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		jPanel4.setFont(jPanel4.getFont());
		jPanel4.setLayout(new java.awt.GridBagLayout());
		jPanel4.setMinimumSize(new Dimension(550, 210));
		jPanel4.setPreferredSize(new Dimension(550, 210));
		
		
		gridBagConstraints_22 = new java.awt.GridBagConstraints();
		gridBagConstraints_22.anchor = GridBagConstraints.EAST;
		gridBagConstraints_22.gridx = 0;
		gridBagConstraints_22.gridy = 3;
		gridBagConstraints_22.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(jPanel4, gridBagConstraints_22);

		lblBreweryProfile = new JLabel("Profilo impianto");
		GridBagConstraints gbc_lblProfiliImpianto = new GridBagConstraints();
		gbc_lblProfiliImpianto.anchor = GridBagConstraints.EAST;
		gbc_lblProfiliImpianto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfiliImpianto.gridx = 0;
		gbc_lblProfiliImpianto.gridy = 0;
		jPanel4.add(lblBreweryProfile, gbc_lblProfiliImpianto);

		cmbBreweryProfile = new JComboBox<>();
		cmbBreweryProfile.setModel(new DefaultComboBoxModel<String>(
				Gui.breweryProfilePickerTableModel.getBreweryProfileNames("- Seleziona -")));

		GridBagConstraints gbc_cmbProfiliImpianto = new GridBagConstraints();
		gbc_cmbProfiliImpianto.anchor = GridBagConstraints.WEST;
		gbc_cmbProfiliImpianto.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProfiliImpianto.gridx = 1;
		gbc_cmbProfiliImpianto.gridy = 0;
		jPanel4.add(cmbBreweryProfile, gbc_cmbProfiliImpianto);

		lblBiab = new JLabel("BIAB");
		GridBagConstraints gbc_lblBiab = new GridBagConstraints();
		gbc_lblBiab.anchor = GridBagConstraints.EAST;
		gbc_lblBiab.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiab.gridx = 0;
		gbc_lblBiab.gridy = 1;
		jPanel4.add(lblBiab, gbc_lblBiab);

		chckbxBiab = new JCheckBox("");
		GridBagConstraints gbc_chckbxBiab = new GridBagConstraints();
		gbc_chckbxBiab.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxBiab.anchor = GridBagConstraints.WEST;
		gbc_chckbxBiab.gridx = 1;
		gbc_chckbxBiab.gridy = 1;
		jPanel4.add(chckbxBiab, gbc_chckbxBiab);
		jLabel13 = new javax.swing.JLabel();

		jLabel13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel13.setText("Assorbimento grani esausti");
		gridBagConstraints_20 = new java.awt.GridBagConstraints();
		gridBagConstraints_20.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_20.gridx = 0;
		gridBagConstraints_20.gridy = 2;
		gridBagConstraints_20.anchor = java.awt.GridBagConstraints.EAST;
		jPanel4.add(jLabel13, gridBagConstraints_20);
		spnAssorbimentoGraniEsausti = new JUnitSpinner("L/kg", 57);
		spnAssorbimentoGraniEsausti.setModel(Main.config.getLitriPerKg(), 0.0, 1000000, 0.01, "0.00", "CT.lkg");
		spnAssorbimentoGraniEsausti.setFont(spnAssorbimentoGraniEsausti.getFont());
		gbc_spnAssorbimentoGraniEsausti = new java.awt.GridBagConstraints();
		gbc_spnAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 0);
		gbc_spnAssorbimentoGraniEsausti.gridx = 1;
		gbc_spnAssorbimentoGraniEsausti.gridy = 2;
		gbc_spnAssorbimentoGraniEsausti.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel4.add(spnAssorbimentoGraniEsausti, gbc_spnAssorbimentoGraniEsausti);
		jLabel14 = new javax.swing.JLabel();

		jLabel14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jLabel14.setText("Rapporto acqua/grani");
		gridBagConstraints_21 = new java.awt.GridBagConstraints();
		gridBagConstraints_21.anchor = GridBagConstraints.EAST;
		gridBagConstraints_21.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_21.gridx = 0;
		gridBagConstraints_21.gridy = 3;
		jPanel4.add(jLabel14, gridBagConstraints_21);
		
		spnRapportoAcquaGrani = new JUnitSpinner("L/Kg", 57);
		spnRapportoAcquaGrani.setModel(Main.config.getRapportoAcquaGrani(), 0.0, 1000000, 0.1, "0.00", "CT.ev");
		spnRapportoAcquaGrani.setFont(spnRapportoAcquaGrani.getFont());
		gbc_spnRapportoAcquaGrani = new java.awt.GridBagConstraints();
		gbc_spnRapportoAcquaGrani.insets = new Insets(0, 0, 5, 0);
		gbc_spnRapportoAcquaGrani.gridx = 1;
		gbc_spnRapportoAcquaGrani.gridy = 3;
		gbc_spnRapportoAcquaGrani.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel4.add(spnRapportoAcquaGrani, gbc_spnRapportoAcquaGrani);

		lblPercentualeEvaporazione = new JLabel("Percentuale evaporazione");
		GridBagConstraints gbc_lblPercentualeEvaporazione = new GridBagConstraints();
		gbc_lblPercentualeEvaporazione.anchor = GridBagConstraints.EAST;
		gbc_lblPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercentualeEvaporazione.gridx = 0;
		gbc_lblPercentualeEvaporazione.gridy = 4;
		jPanel4.add(lblPercentualeEvaporazione, gbc_lblPercentualeEvaporazione);

		spnPercentualeEvaporazione = new JUnitSpinner("%", 57);
		spnPercentualeEvaporazione.setModel(Main.config.getPercentualeEvaporazione(), 0.0, 100, 0.25, "0.00", null);
		GridBagConstraints gbc_spnPercentualeEvaporazione = new GridBagConstraints();
		gbc_spnPercentualeEvaporazione.insets = new Insets(0, 0, 5, 0);
		gbc_spnPercentualeEvaporazione.gridx = 1;
		gbc_spnPercentualeEvaporazione.gridy = 4;
		jPanel4.add(spnPercentualeEvaporazione, gbc_spnPercentualeEvaporazione);

		lblContrazionePerRaffreddamento = new JLabel("Contrazione per raffreddamento");
		GridBagConstraints gbc_lblContrazionePerRaffreddamento = new GridBagConstraints();
		gbc_lblContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrazionePerRaffreddamento.anchor = GridBagConstraints.EAST;
		gbc_lblContrazionePerRaffreddamento.gridx = 0;
		gbc_lblContrazionePerRaffreddamento.gridy = 5;
		jPanel4.add(lblContrazionePerRaffreddamento, gbc_lblContrazionePerRaffreddamento);
		spnContrazionePerRaffreddamento = new JUnitSpinner("%", 57);
		spnContrazionePerRaffreddamento.setModel(Main.config.getContrazionePerRaffreddamento(), 0.0, 100, 0.25, "0.00", null);
		GridBagConstraints gbc_spnContrazionePerRaffreddamento = new GridBagConstraints();
		gbc_spnContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 0);
		gbc_spnContrazionePerRaffreddamento.gridx = 1;
		gbc_spnContrazionePerRaffreddamento.gridy = 5;
		jPanel4.add(spnContrazionePerRaffreddamento, gbc_spnContrazionePerRaffreddamento);
		
		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void saveConfiguration(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed

		Config config = new Config();

		config.setEfficienza(fldEff.getIntegerValue());
		config.setVolumeFin(fldVolumeFin.getIntegerValue());
		// config.setVolumeBoil(fldVolumeBoll.getIntegerValue());
		config.setMetriSLM(fldSLM.getIntegerValue());
		config.setAmaroDHEA(fldDHEA.getIntegerValue());
		config.setBoilTime(fldBoil.getIntegerValue());
		config.setProxyHost(fldProxy.getText());
		config.setProxyPort(fldProxyPort.getText());
		config.setRemoteServer(fldServer.getText());
		config.setNickIHB(fldNick.getText());
		config.setPasswordIHB(fldPwd.getText());
		config.setPotLibGal((chckbxNewCheckBox.isSelected()) ? 1 : 0);
		config.setLostToTrub(spnLostToTrub.getDoubleValue());
		config.setLitriPerKg(spnAssorbimentoGraniEsausti.getDoubleValue());
		config.setRapportoAcquaGrani(spnRapportoAcquaGrani.getDoubleValue());
		config.setLocale(
				Constants.ITALIAN.equalsIgnoreCase((String) cmbLanguage.getSelectedItem()) ? "it_IT" : "en_US");
		config.setPercentualeEvaporazione(spnPercentualeEvaporazione.getDoubleValue());
		config.setContrazionePerRaffreddamento(spnContrazionePerRaffreddamento.getDoubleValue());
		config.setBiab(chckbxBiab.isSelected());

		if (cmbBUGURatio.getSelectedIndex() == 0)
			config.setBUGURatio(BitterBUGU.TIN);
		if (cmbBUGURatio.getSelectedIndex() == 1)
			config.setBUGURatio(BitterBUGU.RAG);
		if (cmbBUGURatio.getSelectedIndex() == 2)
			config.setBUGURatio(BitterBUGU.DAN);

		Main.config = config;
		File file = new File(Main.configXML);
		Document doc = new Document();
		Element root = config.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, this);
		if (config.getProxyHost() != null)
			System.setProperty("http.proxyHost", config.getProxyHost());
		if (config.getProxyPort() != null)
			System.setProperty("http.proxyPort", config.getProxyPort());

		saveCurrentBreweryProfile();

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
				Utils.saveXmlAsFile(doc, new File(Main.breweryProfileXML), this);

			}
		}

		selectBreweryProfile();
		// try {
		// reloadMethod.invoke(Main.class);
		// } catch (Exception ex) {
		// Utils.showException(ex, "", this);
		// }

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private jmash.component.JMashSpinner fldBoil;
	private jmash.component.JMashSpinner fldEff;
	private javax.swing.JTextField fldNick;
	private javax.swing.JTextField fldProxy;
	private javax.swing.JTextField fldProxyPort;
	private javax.swing.JTextField fldPwd;
	private jmash.component.JMashSpinner fldSLM;
	private jmash.component.JMashSpinner fldDHEA;
	private javax.swing.JTextField fldServer;
	private jmash.component.JMashSpinner fldVolumeFin;
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
	// private javax.swing.JLabel jLabel6;
	// private javax.swing.JLabel jLabel7;
	// private javax.swing.JLabel jLabel8;
	// private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	// private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
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
	private GridBagConstraints gridBagConstraints_13;
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
	private GridBagConstraints gridBagConstraints_22;
	private JLabel lblBiab;
	// End of variables declaration//GEN-END:variables

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
		Integer volumeFinale = fldVolumeFin.getIntegerValue();
		Integer efficienza = fldEff.getIntegerValue();
		Double assorbimentoGraniEsausti = spnAssorbimentoGraniEsausti.getDoubleValue();
		Double rapportoAcquaGrani = spnRapportoAcquaGrani.getDoubleValue();
		Double percentualeEvaporazione = spnPercentualeEvaporazione.getDoubleValue();
		Double contrazionePerRaffreddamento = spnContrazionePerRaffreddamento.getDoubleValue();
		Double perditeNelTrub = spnLostToTrub.getDoubleValue();
		String biab = chckbxBiab.isSelected() ? "1" : "0";

		return new BreweryProfile(volumeFinale, efficienza, assorbimentoGraniEsausti, rapportoAcquaGrani,
				percentualeEvaporazione, contrazionePerRaffreddamento, perditeNelTrub, biab);
	}

}
