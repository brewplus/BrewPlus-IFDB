/*
 * WaterAdjustPanel.java
 *
 * Created on 28 settembre 2007, 18.37
 */

package jmash;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;

//import com.lowagie.text.Font;

import jmash.PalmerRecommendedRange.PalmerRecommendedRangeType;
import jmash.component.JMashSpinner;

/**
 *
 * @author Alessandro
 */
public class WaterAdjustPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(WaterAdjustPanel.class);
	private JInternalFrame parent;
	Picker waterPicker;

	private boolean skipRecalc = false;
	private JLabel lblMashWP;
	private JLabel lblMashAndSpargeWP;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblcaPpm;
	private JLabel lblMagnesium;
	private JLabel lblNewLabel_4;
	private JLabel lblChloride;
	private JLabel lblNewLabel_5;
	private JLabel lblChlorideSulfate;
	private JTextField textFieldMashCalcium;
	private JTextField textFieldMashMagnesium;
	private JTextField textFieldMashSodium;
	private JTextField textFieldMashChloride;
	private JTextField textFieldMashSulfate;
	private JTextField textFieldMashChlorideSulfateRatio;
	private JTextField textFieldMashSpargeCalcium;
	private JTextField textFieldMashSpargeMagnesium;
	private JTextField textFieldMashSpargeSodium;
	private JTextField textFieldMashSpargeChloride;
	private JTextField textFieldMashSpargeSulfate;
	private JTextField textFieldMashSpargeChlorideSulfateRatio;
	private JTextField textFieldPalmerCalcium;
	private JTextField textFieldPalmerMagnesium;
	private JTextField textFieldPalmerSodium;
	private JTextField textFieldPalmerChloride;
	private JTextField textFieldPalmerSulfate;
	private JTextField textFieldPalmerChlorideSulfateRatio;
	private JLabel lblCalcium;
	private GridBagConstraints gridBagConstraints_1;
	private JLabel lblNewLabel_1;
	private GridBagConstraints gridBagConstraints_2;
	private GridBagConstraints gridBagConstraints_3;
	private JLabel lblSparge;
	private JCheckBox useGypsumSparge;
	private JCheckBox useEpsomSparge;
	private JCheckBox useCaCl2Sparge;
	private JCheckBox useNaClSparge;
	private JTextField txtGyspumSparge;
	private JTextField txtEpsomSparge;
	private JTextField txtCaCl2Sparge;
	private JTextField txtNaClSparge;
	private GridBagConstraints gbc_jPanelSali;
	private JLabel lblMash;
	private JCheckBox useChalkSparge;
	private JCheckBox useSodaSparge;
	private JCheckBox useSlakedLime;
	private JTextField txtChalkSparge;
	private JTextField txtSodaSparge;
	private JTextField txtSlakedLimeSparge;
	private JCheckBox useSlakedLimeSparge;
	private GridBagConstraints gbc_jPanelApprossimazioni;
	private JTextField txtGypsumMash;
	private JTextField txtEpsomMash;
	private JTextField txtCaCl2Mash;
	private JTextField txtNaClMash;
	private JTextField txtChalkMash;
	private JTextField txtSodaMash;
	private JTextField txtSlakedLimeMash;
	private JLabel lblTotale;
	private GridBagConstraints gbc_lblMashWP;
	private GridBagConstraints gbc_lblMashAndSpargeWP;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnA;
	private javax.swing.JButton btnB;
	private javax.swing.JPanel destPanel;
	private javax.swing.JPanel fromPanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
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
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel38;
	private javax.swing.JLabel jLabel39;
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
	private javax.swing.JLabel jLabel50;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanelSali;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanelApprossimazioni;
	private javax.swing.JPanel jPanelPh;
	private javax.swing.JPanel jPanelResultWaterProfile;
	private javax.swing.JToggleButton jToggleButton1;
	private javax.swing.JSlider pCalcio;
	private javax.swing.JSlider pCarbonato;
	private javax.swing.JSlider pCloruro;
	private javax.swing.JSlider pMagnesio;
	private javax.swing.JSlider pSodio;
	private javax.swing.JSlider pSolfato;
	private JMashSpinner spinCalcio;
	private JMashSpinner spinCalcio1;
	private JMashSpinner spinCalcio2;
	private JMashSpinner spinCarb;
	private JMashSpinner spinCarb1;
	private JMashSpinner spinCarb2;
	private JMashSpinner spinCloruro;
	private JMashSpinner spinCloruro1;
	private JMashSpinner spinCloruro2;
	private JMashSpinner spinMagnesio;
	private JMashSpinner spinMagnesio1;
	private JMashSpinner spinMagnesio2;
	private JMashSpinner spinSodio;
	private JMashSpinner spinSodio1;
	private JMashSpinner spinSodio2;
	private JMashSpinner spinSolfato;
	private JMashSpinner spinSolfato1;
	private JMashSpinner spinSolfato2;
	
	// salts
	private JMashSpinner spnCaCl2;
	private JMashSpinner spnChalk;
	private JMashSpinner spnEpsom;
	private JMashSpinner spnGypsum;
	private JMashSpinner spnNaCl;
	private JMashSpinner spnSoda;
	private JMashSpinner spnSlakedLime;
	private JTextField unusedCaCl2;
	private JTextField unusedChalk;
	private JTextField unusedEpsom;
	private JTextField unusedGypsum;
	private JTextField unusedNaCl;
	private JTextField unusedSoda;
	private JTextField unusedSlakedLime;
	
	
	private jmash.component.JVolumeSpinner spnVolume;

	private javax.swing.JTextField txtRA;
	private javax.swing.JTextField txtAlk;
	private javax.swing.JTextField txtPH;
	private javax.swing.JTextField txtAcidMalt;
	private JMashSpinner spnAcidulatedMaltContent;
	private JMashSpinner spnLacticAcid;
	private JMashSpinner spnCitrusAcid;
	private JMashSpinner spnLacticAcidContent;
	private JMashSpinner spnCitrusAcidContent;

	private javax.swing.JCheckBox useCaCl2;
	private javax.swing.JCheckBox useChalk;
	private javax.swing.JCheckBox useEpsom;
	private javax.swing.JCheckBox useGypsum;
	private javax.swing.JCheckBox useNaCl;
	private javax.swing.JCheckBox useSoda;

	/** Creates new form WaterAdjustPanel */
	public WaterAdjustPanel(JInternalFrame parent) {
		this.parent = parent;

		this.waterPicker = new Picker(Gui.waterPickerTableModel);
		initComponents();

		spinCalcio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio");
		spinCloruro.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro");
		spinMagnesio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio");
		spinSodio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio");
		spinSolfato.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato");
		spinCarb.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb");

		spinCalcio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio1");
		spinCloruro1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro1");
		spinMagnesio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio1");
		spinSodio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio1");
		spinSolfato1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato1");
		spinCarb1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb1");

		spinCalcio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio2");
		spinCloruro2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro2");
		spinMagnesio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio2");
		spinSodio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio2");
		spinSolfato2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato2");
		spinCarb2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb2");

		spnVolume.setModel(40, 1, 999999, 1, "0.0", "WaterAdjustPanel.spnVolume");

		spnCaCl2.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnCaCl2");
		spnChalk.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnChalk");
		spnEpsom.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnEpsom");
		spnGypsum.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnGypsum");
		spnNaCl.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnNaCl");
		spnSoda.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnSoda");
		spnSlakedLime.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnSlakedLime");

		spnLacticAcid.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnLacticAcid");
		spnLacticAcidContent.setModel(88.0, 0, 100, 1, "0.0", null);
		spnCitrusAcid.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnCitrusAcid");
		spnCitrusAcidContent.setModel(88.0, 0, 100, 1, "0.0", null);
		spnAcidulatedMaltContent.setModel(2.0, 0, 100, 1, "0.0", null);

		setBackground(getBackground().darker());
		thread = new Thread() {
			@Override
			public void run() {
				List<WaterProfile> L = new ArrayList<WaterProfile>();
				while (flag) {
					if (work) {
						WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(),
								spinMagnesio.getIntegerValue(), spinSolfato.getIntegerValue(),
								spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(), spinCarb.getIntegerValue());
						if (flagRes) {
							flagRes = false;
							res = null;
							L.clear();
						}
						if (res != null)
							source = res;
						WaterProfile dest = new WaterProfile(spinCalcio1.getIntegerValue(),
								spinMagnesio1.getIntegerValue(), spinSolfato1.getIntegerValue(),
								spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
								spinCarb1.getIntegerValue());
						double LITRI = spnVolume.getVolume();

						source.setPCalcio(pCalcio.getValue());
						source.setPSolfato(pSolfato.getValue());
						source.setPSodio(pSodio.getValue());
						source.setPCloruro(pCloruro.getValue());
						source.setPMagnesio(pMagnesio.getValue());
						source.setPCarbonato(pCarbonato.getValue());

						source.setUseGypsum(useGypsum.isSelected());
						source.setUseEpsom(useEpsom.isSelected());
						source.setUseNaCl(useNaCl.isSelected());
						source.setUseCaCl2(useCaCl2.isSelected());
						source.setUseChalk(useChalk.isSelected());
						source.setUseSoda(useSoda.isSelected());
						source.setUseSlakedLime(useSlakedLime.isSelected());
						
						source.setUseGypsumSparge(useGypsumSparge.isSelected());
						source.setUseEpsomSparge(useEpsomSparge.isSelected());
						source.setUseNaClSparge(useNaClSparge.isSelected());
						source.setUseCaCl2Sparge(useCaCl2Sparge.isSelected());
						source.setUseChalkSparge(useChalkSparge.isSelected());
						source.setUseSodaSparge(useSodaSparge.isSelected());
						source.setUseSlakedLimeSparge(useSlakedLimeSparge.isSelected());

						res = source.target(dest, LITRI, "", 100, L, 100);
						setTreatment(res);
					}
					try {
						sleep(500);
					} catch (InterruptedException ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			}
		};
		thread.start();
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

		fromPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		spinCalcio = new JMashSpinner();
		jLabel2 = new javax.swing.JLabel();
		spinMagnesio = new JMashSpinner();
		jLabel3 = new javax.swing.JLabel();
		spinSolfato = new JMashSpinner();
		jLabel4 = new javax.swing.JLabel();
		spinCloruro = new JMashSpinner();
		jLabel5 = new javax.swing.JLabel();
		spinSodio = new JMashSpinner();
		jLabel28 = new javax.swing.JLabel();
		spinCarb = new JMashSpinner();
		btnA = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		destPanel = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		spinCalcio1 = new JMashSpinner();
		jLabel7 = new javax.swing.JLabel();
		spinMagnesio1 = new JMashSpinner();
		jLabel8 = new javax.swing.JLabel();
		spinSolfato1 = new JMashSpinner();
		jLabel9 = new javax.swing.JLabel();
		spinCloruro1 = new JMashSpinner();
		jLabel10 = new javax.swing.JLabel();
		spinSodio1 = new JMashSpinner();
		jLabel29 = new javax.swing.JLabel();
		spinCarb1 = new JMashSpinner();
		btnB = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jLabel15 = new javax.swing.JLabel();
		spinCalcio2 = new JMashSpinner();
		jLabel16 = new javax.swing.JLabel();
		spinMagnesio2 = new JMashSpinner();
		jLabel17 = new javax.swing.JLabel();
		spinSolfato2 = new JMashSpinner();
		jLabel18 = new javax.swing.JLabel();
		spinCloruro2 = new JMashSpinner();
		jLabel19 = new javax.swing.JLabel();
		spinSodio2 = new JMashSpinner();
		jLabel30 = new javax.swing.JLabel();
		spinCarb2 = new JMashSpinner();
		jToggleButton1 = new javax.swing.JToggleButton();
		jButton3 = new javax.swing.JButton();
		jPanelApprossimazioni = new javax.swing.JPanel();
		jPanelPh = new javax.swing.JPanel();
		jPanelResultWaterProfile = new javax.swing.JPanel();
		jLabel20 = new javax.swing.JLabel();
		pCalcio = new javax.swing.JSlider();
		pMagnesio = new javax.swing.JSlider();
		pSolfato = new javax.swing.JSlider();
		pCloruro = new javax.swing.JSlider();
		pSodio = new javax.swing.JSlider();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		pCarbonato = new javax.swing.JSlider();
		jLabel31 = new javax.swing.JLabel();
		jPanelSali = new javax.swing.JPanel();
		jLabel38 = new javax.swing.JLabel();
		jLabel39 = new javax.swing.JLabel();
		jLabel40 = new javax.swing.JLabel();
		jLabel41 = new javax.swing.JLabel();
		jLabel42 = new javax.swing.JLabel();
		jLabel43 = new javax.swing.JLabel();
		jLabel44 = new javax.swing.JLabel();
		jLabel45 = new javax.swing.JLabel();
		jLabel46 = new javax.swing.JLabel();
		jLabel47 = new javax.swing.JLabel();
		jLabel48 = new javax.swing.JLabel();
		jLabel49 = new javax.swing.JLabel();
		jLabel50 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		spnVolume = new jmash.component.JVolumeSpinner();
		spnVolume.setEnabled(false);

		spnAcidulatedMaltContent = new JMashSpinner();
		spnLacticAcid = new JMashSpinner();
		spnLacticAcidContent = new JMashSpinner();
		spnCitrusAcid = new JMashSpinner();
		spnCitrusAcidContent = new JMashSpinner();

		txtAcidMalt = new JTextField();
		txtAcidMalt.setHorizontalAlignment(JTextField.RIGHT);
		txtRA = new JTextField();
		txtRA.setHorizontalAlignment(JTextField.CENTER);
		txtAlk = new JTextField();
		txtAlk.setHorizontalAlignment(JTextField.CENTER);
		txtPH = new JTextField();
		txtPH.setHorizontalAlignment(JTextField.CENTER);

		// setMaximumSize(new java.awt.Dimension(646, 409));
		// setMinimumSize(new java.awt.Dimension(646, 409));
		// setPreferredSize(new java.awt.Dimension(660, 65));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 163, 0 };
		gridBagLayout.columnWidths = new int[] { 415, 350, 0 };
		setLayout(gridBagLayout);

		fromPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partenza - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		fromPanel.setLayout(new java.awt.GridBagLayout());

		jLabel1.setFont(jLabel1.getFont());
		jLabel1.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel1, gridBagConstraints);

		spinCalcio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCalcio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCalcioStateChanged(evt);
			}
		});
		fromPanel.add(spinCalcio, new java.awt.GridBagConstraints());

		jLabel2.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel2, gridBagConstraints);

		spinMagnesio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinMagnesio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinMagnesioStateChanged(evt);
			}
		});
		fromPanel.add(spinMagnesio, new java.awt.GridBagConstraints());

		jLabel3.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel3, gridBagConstraints);

		spinSolfato.setFont(spinSolfato.getFont());
		spinSolfato.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSolfato.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSolfatoStateChanged(evt);
			}
		});
		fromPanel.add(spinSolfato, new java.awt.GridBagConstraints());

		jLabel4.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel4, gridBagConstraints);

		spinCloruro.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCloruro.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCloruroStateChanged(evt);
			}
		});
		fromPanel.add(spinCloruro, new java.awt.GridBagConstraints());

		jLabel5.setFont(jLabel5.getFont());
		jLabel5.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel5, gridBagConstraints);

		spinSodio.setFont(spinSodio.getFont());
		spinSodio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSodio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSodioStateChanged(evt);
			}
		});
		fromPanel.add(spinSodio, new java.awt.GridBagConstraints());

		jLabel28.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel28, gridBagConstraints);

		spinCarb.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarbStateChanged(evt);
			}
		});
		fromPanel.add(spinCarb, new java.awt.GridBagConstraints());

		btnA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filefind.png"))); // NOI18N
		btnA.setToolTipText("Apri...");
		btnA.setBorderPainted(false);
		btnA.setContentAreaFilled(false);
		btnA.setMaximumSize(new java.awt.Dimension(37, 35));
		btnA.setMinimumSize(new java.awt.Dimension(37, 35));
		btnA.setPreferredSize(new java.awt.Dimension(37, 35));
		btnA.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		fromPanel.add(btnA, gridBagConstraints);

		jButton1.setText("?");
		jButton1.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		fromPanel.add(jButton1, new java.awt.GridBagConstraints());

		gridBagConstraints_2 = new java.awt.GridBagConstraints();
		gridBagConstraints_2.gridx = 0;
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridwidth = 3;
		gridBagConstraints_2.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints_2.insets = new Insets(2, 2, 5, 2);
		add(fromPanel, gridBagConstraints_2);

		destPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Target - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		destPanel.setLayout(new java.awt.GridBagLayout());

		jLabel6.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel6, gridBagConstraints);

		spinCalcio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCalcio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCalcio1StateChanged(evt);
			}
		});
		destPanel.add(spinCalcio1, new java.awt.GridBagConstraints());

		jLabel7.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel7, gridBagConstraints);

		spinMagnesio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinMagnesio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinMagnesio1StateChanged(evt);
			}
		});
		destPanel.add(spinMagnesio1, new java.awt.GridBagConstraints());

		jLabel8.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel8, gridBagConstraints);

		spinSolfato1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSolfato1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSolfato1StateChanged(evt);
			}
		});
		destPanel.add(spinSolfato1, new java.awt.GridBagConstraints());

		jLabel9.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel9, gridBagConstraints);

		spinCloruro1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCloruro1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCloruro1StateChanged(evt);
			}
		});
		destPanel.add(spinCloruro1, new java.awt.GridBagConstraints());

		jLabel10.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel10, gridBagConstraints);

		spinSodio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSodio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSodio1StateChanged(evt);
			}
		});
		destPanel.add(spinSodio1, new java.awt.GridBagConstraints());

		jLabel29.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel29, gridBagConstraints);

		spinCarb1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarb1StateChanged(evt);
			}
		});
		destPanel.add(spinCarb1, new java.awt.GridBagConstraints());

		btnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filefind.png"))); // NOI18N
		btnB.setToolTipText("Apri...");
		btnB.setBorder(null);
		btnB.setBorderPainted(false);
		btnB.setContentAreaFilled(false);
		btnB.setMaximumSize(new java.awt.Dimension(37, 35));
		btnB.setMinimumSize(new java.awt.Dimension(37, 35));
		btnB.setPreferredSize(new java.awt.Dimension(37, 35));
		btnB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		destPanel.add(btnB, gridBagConstraints);

		jButton2.setText("?");
		jButton2.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		destPanel.add(jButton2, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(2, 2, 5, 2);
		add(destPanel, gridBagConstraints);

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Miglior approssimazione trovata - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		jPanel4.setLayout(new java.awt.GridBagLayout());

		jLabel15.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel15, gridBagConstraints);

		spinCalcio2.setEnabled(false);
		spinCalcio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinCalcio2, new java.awt.GridBagConstraints());

		jLabel16.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel16, gridBagConstraints);

		spinMagnesio2.setEnabled(false);
		spinMagnesio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinMagnesio2, new java.awt.GridBagConstraints());

		jLabel17.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel17, gridBagConstraints);

		spinSolfato2.setEnabled(false);
		spinSolfato2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinSolfato2, new java.awt.GridBagConstraints());

		jLabel18.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel18, gridBagConstraints);

		spinCloruro2.setEnabled(false);
		spinCloruro2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinCloruro2, new java.awt.GridBagConstraints());

		jLabel19.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel19, gridBagConstraints);

		spinSodio2.setEnabled(false);
		spinSodio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinSodio2, new java.awt.GridBagConstraints());

		jLabel30.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel30, gridBagConstraints);

		spinCarb2.setEnabled(false);
		spinCarb2.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarb2StateChanged(evt);
			}
		});
		jPanel4.add(spinCarb2, new java.awt.GridBagConstraints());

		jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/gear.png"))); // NOI18N
		jToggleButton1.setToolTipText("KEY work! : RB jmash/lang");
		jToggleButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
		jToggleButton1.setMaximumSize(new java.awt.Dimension(37, 35));
		jToggleButton1.setMinimumSize(new java.awt.Dimension(37, 35));
		jToggleButton1.setPreferredSize(new java.awt.Dimension(37, 35));
		jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton1ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel4.add(jToggleButton1, gridBagConstraints);

		jButton3.setText("?");
		jButton3.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jPanel4.add(jButton3, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(2, 2, 5, 2);
		add(jPanel4, gridBagConstraints);

		jPanelApprossimazioni.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Priorità di approssimazione", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		GridBagLayout gbl_jPanelApprossimazioni = new GridBagLayout();
		gbl_jPanelApprossimazioni.columnWeights = new double[] { 0.0, 1.0 };
		jPanelApprossimazioni.setLayout(gbl_jPanelApprossimazioni);

		jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel20.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel20, gridBagConstraints);

		pCalcio.setMajorTickSpacing(10);
		pCalcio.setMinorTickSpacing(5);
		pCalcio.setSnapToTicks(true);
		pCalcio.setPreferredSize(new Dimension(200, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelApprossimazioni.add(pCalcio, gridBagConstraints);

		pMagnesio.setMajorTickSpacing(10);
		pMagnesio.setMinorTickSpacing(5);
		pMagnesio.setSnapToTicks(true);
		pMagnesio.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelApprossimazioni.add(pMagnesio, gridBagConstraints);

		pSolfato.setMajorTickSpacing(10);
		pSolfato.setMinorTickSpacing(5);
		pSolfato.setSnapToTicks(true);
		pSolfato.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelApprossimazioni.add(pSolfato, gridBagConstraints);

		pCloruro.setMajorTickSpacing(10);
		pCloruro.setMinorTickSpacing(5);
		pCloruro.setSnapToTicks(true);
		pCloruro.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelApprossimazioni.add(pCloruro, gridBagConstraints);

		pSodio.setMajorTickSpacing(10);
		pSodio.setMinorTickSpacing(5);
		pSodio.setSnapToTicks(true);
		pSodio.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanelApprossimazioni.add(pSodio, gridBagConstraints);

		jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel21.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel21, gridBagConstraints);

		jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel22.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel22, gridBagConstraints);

		jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel23.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel23, gridBagConstraints);

		jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel24.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel24, gridBagConstraints);

		pCarbonato.setMajorTickSpacing(10);
		pCarbonato.setMinorTickSpacing(5);
		pCarbonato.setSnapToTicks(true);
		pCarbonato.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(pCarbonato, gridBagConstraints);

		jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel31.setText("Carbonato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanelApprossimazioni.add(jLabel31, gridBagConstraints);

		gbc_jPanelApprossimazioni = new java.awt.GridBagConstraints();
		gbc_jPanelApprossimazioni.gridx = 1;
		gbc_jPanelApprossimazioni.gridy = 3;
		gbc_jPanelApprossimazioni.fill = java.awt.GridBagConstraints.BOTH;
		gbc_jPanelApprossimazioni.insets = new Insets(2, 2, 5, 5);
		add(jPanelApprossimazioni, gbc_jPanelApprossimazioni);

		jPanelSali.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Ottenuta tramite queste aggiunte - grammi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		GridBagLayout gbl_jPanelSali = new GridBagLayout();
		gbl_jPanelSali.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
		jPanelSali.setLayout(gbl_jPanelSali);

		jLabel38.setText("Quantità");
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints_1.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel38, gridBagConstraints_1);

		lblTotale = new JLabel("Totale");
		GridBagConstraints gbc_lblTotale = new GridBagConstraints();
		gbc_lblTotale.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotale.gridx = 2;
		gbc_lblTotale.gridy = 1;
		jPanelSali.add(lblTotale, gbc_lblTotale);

		lblMash = new JLabel("Mash");
		GridBagConstraints gbc_lblMash = new GridBagConstraints();
		gbc_lblMash.insets = new Insets(0, 0, 5, 5);
		gbc_lblMash.gridx = 3;
		gbc_lblMash.gridy = 1;
		jPanelSali.add(lblMash, gbc_lblMash);

		lblSparge = new JLabel("Sparge");
		GridBagConstraints gbc_lblSparge = new GridBagConstraints();
		gbc_lblSparge.insets = new Insets(0, 0, 5, 5);
		gbc_lblSparge.gridx = 4;
		gbc_lblSparge.gridy = 1;
		jPanelSali.add(lblSparge, gbc_lblSparge);

		jLabel11.setText("Gypsum");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel11, gridBagConstraints);
		spnGypsum = new JMashSpinner();

		spnGypsum.setPreferredSize(new Dimension(77, 22));
		spnGypsum.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnGypsumStateChanged(evt);
			}
		});
		
		
		
		useGypsum = new javax.swing.JCheckBox();

		useGypsum.setSelected(true);
		useGypsum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useGypsum.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useGypsumActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useGypsum, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnGypsum, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedGypsum = new JTextField("0,0");
		unusedGypsum.setHorizontalAlignment(SwingConstants.CENTER);
		unusedGypsum.setEnabled(false);
		unusedGypsum.setVisible(false);
		unusedGypsum.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedGypsum, gridBagConstraints);

		useChalk = new javax.swing.JCheckBox();

		useChalk.setSelected(true);
		useChalk.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useChalk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useChalkActionPerformed(evt);
			}
		});

		txtGypsumMash = new JTextField();
		txtGypsumMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtGypsumMash.setPreferredSize(new Dimension(60, 22));
		txtGypsumMash.setEditable(false);
		GridBagConstraints gbc_txtGyspumMash = new GridBagConstraints();
		gbc_txtGyspumMash.insets = new Insets(0, 0, 5, 5);
		gbc_txtGyspumMash.gridx = 3;
		gbc_txtGyspumMash.gridy = 2;
		jPanelSali.add(txtGypsumMash, gbc_txtGyspumMash);

		txtGyspumSparge = new JTextField();
		txtGyspumSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtGyspumSparge.setEditable(false);

		GridBagConstraints gbc_txtGyspumSparge = new GridBagConstraints();
		gbc_txtGyspumSparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtGyspumSparge.gridx = 4;
		gbc_txtGyspumSparge.gridy = 2;
		jPanelSali.add(txtGyspumSparge, gbc_txtGyspumSparge);
		txtGyspumSparge.setPreferredSize(new Dimension(60, 22));

		useGypsumSparge = new JCheckBox();
		useGypsumSparge.setSelected(true);
		useGypsumSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useGypsumSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useGypsumSpargeActionPerformed(evt);
			}
		});

		GridBagConstraints gbc_useGypsumSparge = new GridBagConstraints();
		gbc_useGypsumSparge.insets = new Insets(0, 0, 5, 0);
		gbc_useGypsumSparge.gridx = 5;
		gbc_useGypsumSparge.gridy = 2;
		jPanelSali.add(useGypsumSparge, gbc_useGypsumSparge);

		txtEpsomMash = new JTextField();
		txtEpsomMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtEpsomMash.setEditable(false);
		txtEpsomMash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtEpsomMash = new GridBagConstraints();
		gbc_txtEpsomMash.insets = new Insets(0, 0, 5, 5);
		gbc_txtEpsomMash.gridx = 3;
		gbc_txtEpsomMash.gridy = 3;
		jPanelSali.add(txtEpsomMash, gbc_txtEpsomMash);

		txtEpsomSparge = new JTextField();
		txtEpsomSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtEpsomSparge.setEditable(false);
		txtEpsomSparge.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtEpsomSparge = new GridBagConstraints();
		gbc_txtEpsomSparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtEpsomSparge.gridx = 4;
		gbc_txtEpsomSparge.gridy = 3;
		jPanelSali.add(txtEpsomSparge, gbc_txtEpsomSparge);

		useEpsomSparge = new JCheckBox();
		useEpsomSparge.setSelected(true);
		useEpsomSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useEpsomSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useEpsomSpargeActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_useEpsomSparge = new GridBagConstraints();
		gbc_useEpsomSparge.insets = new Insets(0, 0, 5, 0);
		gbc_useEpsomSparge.gridx = 5;
		gbc_useEpsomSparge.gridy = 3;
		jPanelSali.add(useEpsomSparge, gbc_useEpsomSparge);

		txtCaCl2Mash = new JTextField();
		txtCaCl2Mash.setHorizontalAlignment(SwingConstants.CENTER);
		txtCaCl2Mash.setEditable(false);
		txtCaCl2Mash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtCaCl2Mash = new GridBagConstraints();
		gbc_txtCaCl2Mash.insets = new Insets(0, 0, 5, 5);
		gbc_txtCaCl2Mash.gridx = 3;
		gbc_txtCaCl2Mash.gridy = 4;
		jPanelSali.add(txtCaCl2Mash, gbc_txtCaCl2Mash);

		txtCaCl2Sparge = new JTextField();
		txtCaCl2Sparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtCaCl2Sparge.setEditable(false);
		txtCaCl2Sparge.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtCaCl2Sparge = new GridBagConstraints();
		gbc_txtCaCl2Sparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtCaCl2Sparge.gridx = 4;
		gbc_txtCaCl2Sparge.gridy = 4;
		jPanelSali.add(txtCaCl2Sparge, gbc_txtCaCl2Sparge);

		txtNaClMash = new JTextField();
		txtNaClMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtNaClMash.setEditable(false);
		txtNaClMash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtNaClMash = new GridBagConstraints();
		gbc_txtNaClMash.insets = new Insets(0, 0, 5, 5);
		gbc_txtNaClMash.gridx = 3;
		gbc_txtNaClMash.gridy = 5;
		jPanelSali.add(txtNaClMash, gbc_txtNaClMash);

		txtNaClSparge = new JTextField();
		txtNaClSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtNaClSparge.setEditable(false);
		GridBagConstraints gbc_txtNaClSparge = new GridBagConstraints();
		gbc_txtNaClSparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtNaClSparge.gridx = 4;
		gbc_txtNaClSparge.gridy = 5;
		txtNaClSparge.setPreferredSize(new Dimension(60, 22));
		jPanelSali.add(txtNaClSparge, gbc_txtNaClSparge);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useChalk, gridBagConstraints);
		spnChalk = new JMashSpinner();

		spnChalk.setPreferredSize(new java.awt.Dimension(64, 22));
		spnChalk.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnChalkStateChanged(evt);
			}
		});
		spnChalk.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}

			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				spnChalkAncestorAdded(evt);
			}

			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnChalk, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedChalk = new JTextField("0,0");
		unusedChalk.setHorizontalAlignment(SwingConstants.CENTER);
		unusedChalk.setEnabled(false);
		unusedChalk.setVisible(false);
		unusedChalk.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedChalk, gridBagConstraints);


		jLabel12.setText("Epsom");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel12, gridBagConstraints);
		spnEpsom = new JMashSpinner();

		spnEpsom.setPreferredSize(new java.awt.Dimension(64, 22));
		spnEpsom.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnEpsomStateChanged(evt);
			}
		});
		useEpsom = new javax.swing.JCheckBox();

		useEpsom.setSelected(true);
		useEpsom.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useEpsom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useEpsomActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useEpsom, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnEpsom, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedEpsom = new JTextField("0,0");
		unusedEpsom.setHorizontalAlignment(SwingConstants.CENTER);
		unusedEpsom.setEnabled(false);
		unusedEpsom.setVisible(false);
		unusedEpsom.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedEpsom, gridBagConstraints);

		useSoda = new javax.swing.JCheckBox();

		useSoda.setSelected(true);
		useSoda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useSoda.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useSodaActionPerformed(evt);
			}
		});

		useChalkSparge = new JCheckBox();
		useChalkSparge.setSelected(true);
		useChalkSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useChalkSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useChalkSpargeActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_useChalkSparge = new GridBagConstraints();
		gbc_useChalkSparge.insets = new Insets(0, 0, 5, 0);
		gbc_useChalkSparge.gridx = 5;
		gbc_useChalkSparge.gridy = 6;
		jPanelSali.add(useChalkSparge, gbc_useChalkSparge);

		txtChalkMash = new JTextField();
		txtChalkMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtChalkMash.setEditable(false);
		txtChalkMash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtChalkMash = new GridBagConstraints();
		gbc_txtChalkMash.insets = new Insets(0, 0, 5, 5);
		gbc_txtChalkMash.gridx = 3;
		gbc_txtChalkMash.gridy = 6;
		jPanelSali.add(txtChalkMash, gbc_txtChalkMash);

		txtChalkSparge = new JTextField();
		txtChalkSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtChalkSparge.setEditable(false);
		GridBagConstraints gbc_txtChalkSparge = new GridBagConstraints();
		gbc_txtChalkSparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtChalkSparge.gridx = 4;
		gbc_txtChalkSparge.gridy = 6;
		jPanelSali.add(txtChalkSparge, gbc_txtChalkSparge);
		txtChalkSparge.setPreferredSize(new Dimension(60, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useSoda, gridBagConstraints);
		spnSoda = new JMashSpinner();

		spnSoda.setPreferredSize(new java.awt.Dimension(64, 22));
		spnSoda.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnSodaStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnSoda, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedSoda = new JTextField("0,0");
		unusedSoda.setHorizontalAlignment(SwingConstants.CENTER);
		unusedSoda.setEnabled(false);
		unusedSoda.setVisible(false);
		unusedSoda.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedSoda, gridBagConstraints);


		jLabel13.setText("Cloruro di calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel13, gridBagConstraints);
		spnCaCl2 = new JMashSpinner();

		spnCaCl2.setPreferredSize(new java.awt.Dimension(64, 22));
		spnCaCl2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnCaCl2StateChanged(evt);
			}
		});
		useCaCl2 = new javax.swing.JCheckBox();

		useCaCl2.setSelected(true);
		useCaCl2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useCaCl2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useCaCl2ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useCaCl2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnCaCl2, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedCaCl2 = new JTextField("0,0");
		unusedCaCl2.setHorizontalAlignment(SwingConstants.CENTER);
		unusedCaCl2.setEnabled(false);
		unusedCaCl2.setVisible(false);
		unusedCaCl2.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedCaCl2, gridBagConstraints);

		useCaCl2Sparge = new JCheckBox();
		useCaCl2Sparge.setSelected(true);
		useCaCl2Sparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useCaCl2Sparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useCaCl2SpargeActionPerformed(evt);
			}
		});

		GridBagConstraints gbc_useCaCl2Sparge = new GridBagConstraints();
		gbc_useCaCl2Sparge.insets = new Insets(0, 0, 5, 0);
		gbc_useCaCl2Sparge.gridx = 5;
		gbc_useCaCl2Sparge.gridy = 4;
		jPanelSali.add(useCaCl2Sparge, gbc_useCaCl2Sparge);
		spnSlakedLime = new JMashSpinner();

		spnSlakedLime.setPreferredSize(new java.awt.Dimension(64, 22));
		spnSlakedLime.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnSlakedLimeStateChanged(evt);
			}
		});

		useSodaSparge = new JCheckBox();
		useSodaSparge.setSelected(true);
		useSodaSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useSodaSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useSodaSpargeActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_useSodaSparge = new GridBagConstraints();
		gbc_useSodaSparge.insets = new Insets(0, 0, 5, 0);
		gbc_useSodaSparge.gridx = 5;
		gbc_useSodaSparge.gridy = 7;
		jPanelSali.add(useSodaSparge, gbc_useSodaSparge);

		txtSodaMash = new JTextField();
		txtSodaMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtSodaMash.setEditable(false);
		txtSodaMash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtSodaMash = new GridBagConstraints();
		gbc_txtSodaMash.insets = new Insets(0, 0, 5, 5);
		gbc_txtSodaMash.gridx = 3;
		gbc_txtSodaMash.gridy = 7;
		jPanelSali.add(txtSodaMash, gbc_txtSodaMash);

		txtSodaSparge = new JTextField();
		txtSodaSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtSodaSparge.setEditable(false);
		GridBagConstraints gbc_txtSodaSparge = new GridBagConstraints();
		gbc_txtSodaSparge.insets = new Insets(0, 0, 5, 5);
		gbc_txtSodaSparge.gridx = 4;
		gbc_txtSodaSparge.gridy = 7;
		jPanelSali.add(txtSodaSparge, gbc_txtSodaSparge);
		txtSodaSparge.setPreferredSize(new Dimension(60, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 0, 5);
		jPanelSali.add(spnSlakedLime, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 0, 5);
		unusedSlakedLime = new JTextField("0,0");
		unusedSlakedLime.setHorizontalAlignment(SwingConstants.CENTER);
		unusedSlakedLime.setEnabled(false);
		unusedSlakedLime.setVisible(false);
		unusedSlakedLime.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedSlakedLime, gridBagConstraints);

		jLabel14.setText("Cloruro di sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel14, gridBagConstraints);
		spnNaCl = new JMashSpinner();

		spnNaCl.setPreferredSize(new java.awt.Dimension(64, 22));
		spnNaCl.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnNaClStateChanged(evt);
			}
		});
		useNaCl = new javax.swing.JCheckBox();

		useNaCl.setSelected(true);
		useNaCl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useNaCl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useNaClActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(useNaCl, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(spnNaCl, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		unusedNaCl = new JTextField("0,0");
		unusedNaCl.setHorizontalAlignment(SwingConstants.CENTER);
		unusedNaCl.setEnabled(false);
		unusedNaCl.setVisible(false);
		unusedNaCl.setPreferredSize(new Dimension(77, 22));
		jPanelSali.add(unusedNaCl, gridBagConstraints);

		spnVolume.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnVolumeStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(2, 2, 5, 0);
		jPanelSali.add(spnVolume, gridBagConstraints);

		gbc_jPanelSali = new java.awt.GridBagConstraints();
		gbc_jPanelSali.gridheight = 2;
		gbc_jPanelSali.gridx = 0;
		gbc_jPanelSali.gridy = 3;
		gbc_jPanelSali.fill = java.awt.GridBagConstraints.BOTH;
		gbc_jPanelSali.insets = new Insets(2, 2, 5, 5);
		add(jPanelSali, gbc_jPanelSali);

		useNaClSparge = new JCheckBox();
		useNaClSparge.setSelected(true);
		useNaClSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useNaClSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useNaClSpargeActionPerformed(evt);
			}
		});

		GridBagConstraints gbc_useNaClSparge = new GridBagConstraints();
		gbc_useNaClSparge.insets = new Insets(0, 0, 5, 0);
		gbc_useNaClSparge.gridx = 5;
		gbc_useNaClSparge.gridy = 5;
		jPanelSali.add(useNaClSparge, gbc_useNaClSparge);
		jLabel26 = new javax.swing.JLabel();

		jLabel26.setText("Carbonato di calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel26, gridBagConstraints);
		jLabel27 = new javax.swing.JLabel();

		jLabel27.setText("Bicarbonato di sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		jPanelSali.add(jLabel27, gridBagConstraints);

		lblNewLabel_1 = new JLabel("Idrossido di calcio");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 8;
		jPanelSali.add(lblNewLabel_1, gbc_lblNewLabel_1);

		useSlakedLime = new JCheckBox();
		useSlakedLime.setSelected(true);
		useSlakedLime.setEnabled(true);
		useSlakedLime.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useSlakedLime.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useSlakedLimeActionPerformed(evt);
			}
		});

		GridBagConstraints gbc_useSlakedLime = new GridBagConstraints();
		gbc_useSlakedLime.insets = new Insets(0, 0, 0, 5);
		gbc_useSlakedLime.gridx = 1;
		gbc_useSlakedLime.gridy = 8;
		jPanelSali.add(useSlakedLime, gbc_useSlakedLime);

		useSlakedLimeSparge = new JCheckBox();
		useSlakedLimeSparge.setSelected(true);
		useSlakedLimeSparge.setEnabled(true);
		useSlakedLimeSparge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useSlakedLimeSparge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useSlakedLimeSpargeActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_useSlakedLimeSparge = new GridBagConstraints();
		gbc_useSlakedLimeSparge.gridx = 5;
		gbc_useSlakedLimeSparge.gridy = 8;
		jPanelSali.add(useSlakedLimeSparge, gbc_useSlakedLimeSparge);

		txtSlakedLimeMash = new JTextField();
		txtSlakedLimeMash.setHorizontalAlignment(SwingConstants.CENTER);
		txtSlakedLimeMash.setEditable(false);
		txtSlakedLimeMash.setPreferredSize(new Dimension(60, 22));
		GridBagConstraints gbc_txtSlakedLimeMash = new GridBagConstraints();
		gbc_txtSlakedLimeMash.insets = new Insets(0, 0, 0, 5);
		gbc_txtSlakedLimeMash.gridx = 3;
		gbc_txtSlakedLimeMash.gridy = 8;
		jPanelSali.add(txtSlakedLimeMash, gbc_txtSlakedLimeMash);

		txtSlakedLimeSparge = new JTextField();
		txtSlakedLimeSparge.setHorizontalAlignment(SwingConstants.CENTER);
		txtSlakedLimeSparge.setEditable(false);
		GridBagConstraints gbc_txtSlakedLimeSparge = new GridBagConstraints();
		gbc_txtSlakedLimeSparge.insets = new Insets(0, 0, 0, 5);
		// gbc_txtSlackedLimeSparge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSlakedLimeSparge.gridx = 4;
		gbc_txtSlakedLimeSparge.gridy = 8;
		jPanelSali.add(txtSlakedLimeSparge, gbc_txtSlakedLimeSparge);
		txtSlakedLimeSparge.setPreferredSize(new Dimension(60, 22));

		jPanelPh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dati pH",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		jPanelPh.setLayout(new GridBagLayout());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(2, 2, 5, 2);
		add(jPanelPh, gridBagConstraints);

		jLabel42.setText("Malti acidi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel42, gridBagConstraints);
		txtAcidMalt.setPreferredSize(new Dimension(77, 22));
		txtAcidMalt.setEditable(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtAcidMalt, gridBagConstraints);
		jLabel43.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel43, gridBagConstraints);
		spnAcidulatedMaltContent.setPreferredSize(new Dimension(77, 22));
		spnAcidulatedMaltContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinAcidulatedMaltContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnAcidulatedMaltContent, gridBagConstraints);
		jLabel44.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel44, gridBagConstraints);

		jLabel45.setText("Acido lattico");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel45, gridBagConstraints);
		spnLacticAcid.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnLacticAcid, gridBagConstraints);
		spnLacticAcid.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinLacticAcidStateChanged(evt);
			}
		});
		jLabel46.setText("ml");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel46, gridBagConstraints);
		spnLacticAcidContent.setPreferredSize(new java.awt.Dimension(64, 22));
		spnLacticAcidContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinLacticAcidContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnLacticAcidContent, gridBagConstraints);
		jLabel47.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel47, gridBagConstraints);

		jLabel48.setText("Acido citrico");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel48, gridBagConstraints);
		spnCitrusAcid.setPreferredSize(new java.awt.Dimension(64, 22));
		spnCitrusAcid.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCitrusAcidStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnCitrusAcid, gridBagConstraints);
		jLabel49.setText("gr");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel49, gridBagConstraints);
		spnCitrusAcidContent.setPreferredSize(new java.awt.Dimension(64, 22));
		spnCitrusAcidContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCitrusAcidContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnCitrusAcidContent, gridBagConstraints);
		jLabel50.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel50, gridBagConstraints);

		jLabel39.setText("Alcalinità residua");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel39, gridBagConstraints);
		txtRA.setEditable(false);
		txtRA.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtRA, gridBagConstraints);

		jLabel40.setText("Alcalinità effettiva");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel40, gridBagConstraints);
		txtAlk.setEditable(false);
		txtAlk.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtAlk, gridBagConstraints);

		jLabel41.setText("pH");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel41, gridBagConstraints);
		txtPH.setEditable(false);
		txtPH.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtPH, gridBagConstraints);

		jPanelResultWaterProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"View Resulting Water Profile - ppm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		GridBagLayout gbl_jPanelResultWaterProfile = new GridBagLayout();
		gbl_jPanelResultWaterProfile.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0 };
		gbl_jPanelResultWaterProfile.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		jPanelResultWaterProfile.setLayout(gbl_jPanelResultWaterProfile);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(2, 2, 5, 2);
		add(jPanelResultWaterProfile, gridBagConstraints);

		lblCalcium = new JLabel("Calcium");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblCalcium, gridBagConstraints);

		lblMagnesium = new JLabel("Magnesium");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblMagnesium, gridBagConstraints);
		lblNewLabel_4 = new JLabel("Sodium");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblNewLabel_4, gridBagConstraints);
		lblChloride = new JLabel("Chloride");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblChloride, gridBagConstraints);
		lblNewLabel_5 = new JLabel("Sulfate");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblNewLabel_5, gridBagConstraints);
		lblChlorideSulfate = new JLabel("Chloride / Sulfate ratio");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 2);
		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		jPanelResultWaterProfile.add(lblChlorideSulfate, gridBagConstraints);

		lblMashWP = new JLabel("Mash Water Profile ");
		gbc_lblMashWP = new GridBagConstraints();
		gbc_lblMashWP.insets = new Insets(2, 2, 5, 5);
		gbc_lblMashWP.gridx = 0;
		gbc_lblMashWP.gridy = 1;
		gbc_lblMashWP.anchor = java.awt.GridBagConstraints.EAST;
		jPanelResultWaterProfile.add(lblMashWP, gbc_lblMashWP);

		textFieldMashCalcium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashCalcium, jPanelResultWaterProfile, 1, 1, 10);

		textFieldMashMagnesium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashMagnesium, jPanelResultWaterProfile, 2, 1, 10);

		textFieldMashSodium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSodium, jPanelResultWaterProfile, 3, 1, 10);

		textFieldMashChloride = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashChloride, jPanelResultWaterProfile, 4, 1, 10);

		textFieldMashSulfate = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSulfate, jPanelResultWaterProfile, 5, 1, 10);

		textFieldMashChlorideSulfateRatio = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashChlorideSulfateRatio, jPanelResultWaterProfile, 6, 1, 10);

		lblMashAndSpargeWP = new JLabel("Mash + Sparge Water Profile");
		gbc_lblMashAndSpargeWP = new GridBagConstraints();
		gbc_lblMashAndSpargeWP.insets = new Insets(2, 2, 5, 5);
		gbc_lblMashAndSpargeWP.gridx = 0;
		gbc_lblMashAndSpargeWP.gridy = 2;
		gbc_lblMashAndSpargeWP.anchor = java.awt.GridBagConstraints.EAST;
		jPanelResultWaterProfile.add(lblMashAndSpargeWP, gbc_lblMashAndSpargeWP);

		textFieldMashSpargeCalcium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeCalcium, jPanelResultWaterProfile, 1, 2, 10);

		textFieldMashSpargeMagnesium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeMagnesium, jPanelResultWaterProfile, 2, 2, 10);

		textFieldMashSpargeSodium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeSodium, jPanelResultWaterProfile, 3, 2, 10);

		textFieldMashSpargeChloride = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeChloride, jPanelResultWaterProfile, 4, 2, 10);

		textFieldMashSpargeSulfate = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeSulfate, jPanelResultWaterProfile, 5, 2, 10);

		textFieldMashSpargeChlorideSulfateRatio = new JTextField();
		prepareTextFielsWaterProfileView(textFieldMashSpargeChlorideSulfateRatio, jPanelResultWaterProfile, 6, 2, 10);

		lblNewLabel_2 = new JLabel("Palmer's Recommended Ranges");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(2, 2, 5, 5);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		jPanelResultWaterProfile.add(lblNewLabel_2, gridBagConstraints);

		textFieldPalmerCalcium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldPalmerCalcium, jPanelResultWaterProfile, 1, 3, 10);
		setPalmerRecommendedRange(PalmerRecommendedRangeType.CALCIUM, textFieldPalmerCalcium);

		textFieldPalmerMagnesium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldPalmerMagnesium, jPanelResultWaterProfile, 2, 3, 10);
		setPalmerRecommendedRange(PalmerRecommendedRangeType.MAGNESIUM, textFieldPalmerMagnesium);

		textFieldPalmerSodium = new JTextField();
		prepareTextFielsWaterProfileView(textFieldPalmerSodium, jPanelResultWaterProfile, 3, 3, 10);
		setPalmerRecommendedRange(PalmerRecommendedRangeType.SODIUM, textFieldPalmerSodium);

		textFieldPalmerChloride = new JTextField();
		prepareTextFielsWaterProfileView(textFieldPalmerChloride, jPanelResultWaterProfile, 4, 3, 10);
		setPalmerRecommendedRange(PalmerRecommendedRangeType.CHLORIDE, textFieldPalmerChloride);

		textFieldPalmerSulfate = new JTextField();
		prepareTextFielsWaterProfileView(textFieldPalmerSulfate, jPanelResultWaterProfile, 5, 3, 10);
		setPalmerRecommendedRange(PalmerRecommendedRangeType.SULFATE, textFieldPalmerSulfate);

		textFieldPalmerChlorideSulfateRatio = new JTextField();
		textFieldPalmerChlorideSulfateRatio.setFont(new Font("Tahoma", Font.BOLD, 9));
		prepareTextFielsWaterProfileView(textFieldPalmerChlorideSulfateRatio, jPanelResultWaterProfile, 6, 3, 10);

	}// </editor-fold>//GEN-END:initComponents

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio2.getIntegerValue(), spinMagnesio2.getIntegerValue(),
				spinSolfato2.getIntegerValue(), spinCloruro2.getIntegerValue(), spinSodio2.getIntegerValue(),
				spinCarb2.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void spnVolumeStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnVolumeStateChanged
		updateTreatment();
	}// GEN-LAST:event_spnVolumeStateChanged

	private void spinSodio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSodio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinSodio1StateChanged

	private void spinCloruro1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCloruro1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCloruro1StateChanged

	private void spinSolfato1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSolfato1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinSolfato1StateChanged

	private void spinMagnesio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinMagnesio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinMagnesio1StateChanged

	private void spinCalcio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCalcio1StateChanged

	private void spnSodaStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnSodaStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnSodaStateChanged

	private void spnSlakedLimeStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnSodaStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnSodaStateChanged

	private void spnChalkStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnChalkStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnChalkStateChanged

	private void spnChalkAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_spnChalkAncestorAdded

	}// GEN-LAST:event_spnChalkAncestorAdded

	private void spnNaClStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnNaClStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnNaClStateChanged

	private void spnCaCl2StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnCaCl2StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnCaCl2StateChanged

	private void spnEpsomStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnEpsomStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnEpsomStateChanged

	private void spnGypsumStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnGypsumStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnGypsumStateChanged

	private boolean work = false;

	private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton1ActionPerformed
		work = jToggleButton1.isSelected();
	}// GEN-LAST:event_jToggleButton1ActionPerformed

	private void useSodaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useSodaActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useSodaActionPerformed

	private void useChalkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useChalkActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useChalkActionPerformed

	private void useNaClActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useNaClActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useNaClActionPerformed

	private void useCaCl2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useCaCl2ActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useCaCl2ActionPerformed

	private void useEpsomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useEpsomActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useEpsomActionPerformed

	private void useGypsumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useGypsumActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useGypsumActionPerformed

	private void useSlakedLimeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useGypsumActionPerformed
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_useGypsumActionPerformed

	private void useSodaSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useSodaActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useSodaActionPerformed

	private void useChalkSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useChalkActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useChalkActionPerformed

	private void useNaClSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useNaClActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useNaClActionPerformed

	private void useCaCl2SpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useCaCl2ActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useCaCl2ActionPerformed

	private void useEpsomSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useEpsomActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useEpsomActionPerformed

	private void useGypsumSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useGypsumActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useGypsumActionPerformed

	private void useSlakedLimeSpargeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useGypsumActionPerformed
		recalcTreatment();
	}// GEN-LAST:event_useGypsumActionPerformed

	private void spinCarb2StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarb2StateChanged

	}// GEN-LAST:event_spinCarb2StateChanged

	private void spinCarb1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarb1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCarb1StateChanged

	private void btnBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBActionPerformed
		waterPicker.startModal(parent);
		WaterProfile profile = (WaterProfile) this.waterPicker.getSelection();

		setDest(profile);
		recalcTreatment();
	}// GEN-LAST:event_btnBActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void btnAActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAActionPerformed
		// loadFrom();
		waterPicker.startModal(parent);
		WaterProfile profile = (WaterProfile) this.waterPicker.getSelection();
		setSource(profile);
		res = null;
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_btnAActionPerformed

	private void spinCarbStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarbStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinCarbStateChanged

	private void spinSodioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSodioStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinSodioStateChanged

	private void spinCloruroStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCloruroStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinCloruroStateChanged

	private void spinSolfatoStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSolfatoStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinSolfatoStateChanged

	private void spinMagnesioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinMagnesioStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinMagnesioStateChanged

	private void spinCalcioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		flagRes = true;
		recalcTreatment();
	}

	private void spinLacticAcidStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		recalcTreatment();
	}

	private void spinLacticAcidContentStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		recalcTreatment();
	}

	private void spinCitrusAcidStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		recalcTreatment();
	}

	private void spinCitrusAcidContentStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		recalcTreatment();
	}

	private void spinAcidulatedMaltContentStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		recalcTreatment();
	}

	// GEN-LAST:event_spinCalcioStateChanged

	// End of variables declaration//GEN-END:variables
	private void loadFrom() {
		File file = Utils.pickFileToLoad(parent, Main.waterDir);
		if (file == null)
			return;
		Document doc = Utils.readFileAsXml(file.toString());
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		WaterProfile profile = WaterProfile.fromXml(root);
		sourceName = profile.getNome();
		if (sourceName == null)
			sourceName = file.getName();
		profile.setNome(sourceName);
		setSource(profile);
		res = null;
		flagRes = true;
	}

	private boolean flagRes = false;

	public void saveFrom() {

		File file = Utils.pickFileToSave(parent, Main.waterDir);
		if (file == null) {
			return;
		}
		WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		source.setNome(sourceName);
		if (!file.exists())
			source.setNome(file.getName());
		Document doc = new Document();
		Element root = source.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, parent);
	}

	private File destFile = null;
	private String sourceName = null;
	private String destName = null;

	private void loadDest() {
		File file = Utils.pickFileToLoad(parent, Main.waterDir);
		if (file == null)
			return;
		Document doc = Utils.readFileAsXml(file.toString());
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		WaterProfile profile = WaterProfile.fromXml(root);
		destName = profile.getNome();
		if (destName == null)
			destName = file.getName();
		profile.setNome(destName);
		setDest(profile);
	}

	public void saveDest() {

		File file = Utils.pickFileToSave(parent, Main.waterDir);
		if (file == null) {
			return;
		}

		WaterProfile source = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		source.setNome(destName);
		if (!file.exists())
			source.setNome(file.getName());
		Document doc = new Document();
		Element root = source.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, parent);
	}

	private boolean flag = true;

	public void end() {
		flag = false;
	}

	private WaterProfile res = null;
	private Thread thread;

	public WaterProfile getSource() {
		WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		source.setNome(sourceName);
		return source;
	}

	public WaterProfile getDest() {
		WaterProfile dest = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		dest.setNome(destName);
		return dest;
	}

	public WaterProfile getTreatment() {
		return res;
	}

	public void setSource(WaterProfile profile) {
		if (profile == null)
			return;
		sourceName = profile.getNome();
		((TitledBorder) fromPanel.getBorder()).setTitle("Origine - " + sourceName);
		spinCalcio.setIntegerValue((int) profile.getCalcio().doubleValue());
		spinSodio.setIntegerValue((int) profile.getSodio().doubleValue());
		spinSolfato.setIntegerValue((int) profile.getSolfato().doubleValue());
		spinMagnesio.setIntegerValue((int) profile.getMagnesio().doubleValue());
		spinCloruro.setIntegerValue((int) profile.getCloruro().doubleValue());
		spinCarb.setIntegerValue((int) profile.getCarbonato().doubleValue());
	}

	public void setDest(WaterProfile profile) {
		if (profile == null)
			return;
		destName = profile.getNome();
		((TitledBorder) destPanel.getBorder()).setTitle("Target - " + destName);
		spinCalcio1.setIntegerValue((int) profile.getCalcio().doubleValue());
		spinSodio1.setIntegerValue((int) profile.getSodio().doubleValue());
		spinSolfato1.setIntegerValue((int) profile.getSolfato().doubleValue());
		spinMagnesio1.setIntegerValue((int) profile.getMagnesio().doubleValue());
		spinCloruro1.setIntegerValue((int) profile.getCloruro().doubleValue());
		spinCarb1.setIntegerValue((int) profile.getCarbonato().doubleValue());
	}

	public void setTreatment(WaterProfile profile) {
		res = profile;
		updateTreatment();
	}

	private void updateTreatment() {
		double LITRI = spnVolume.getVolume();
		if (res == null)
			return;
		skipRecalc = true;
		spnCaCl2.setDoubleValue(res.getCalciumChloride() * Utils.litToGal(LITRI) / 1000);
		spnChalk.setDoubleValue(res.getChalk() * Utils.litToGal(LITRI) / 1000);
		spnEpsom.setDoubleValue(res.getEpsom() * Utils.litToGal(LITRI) / 1000);
		spnGypsum.setDoubleValue(res.getGypsum() * Utils.litToGal(LITRI) / 1000);
		spnNaCl.setDoubleValue(res.getSale() * Utils.litToGal(LITRI) / 1000);
		spnSoda.setDoubleValue(res.getSoda() * Utils.litToGal(LITRI) / 1000);
		spnSlakedLime.setDoubleValue(res.getSlakedLime() * Utils.litToGal(LITRI) / 1000);
		skipRecalc = false;
		spinCalcio2.setIntegerValue((int) res.getCalcioTotale());
		spinMagnesio2.setIntegerValue((int) res.getMagnesioTotale());
		spinSolfato2.setIntegerValue((int) res.getSolfatoTotale());
		spinCloruro2.setIntegerValue((int) res.getCloruroTotale());
		spinSodio2.setIntegerValue((int) res.getSodioTotale());
		spinCarb2.setIntegerValue((int) res.getCarbonatoTotale());

		spnAcidulatedMaltContent.setDoubleValue(res.getAcidulatedMaltContent());
		spnLacticAcid.setDoubleValue(res.getLacticAcid());
		spnLacticAcidContent.setDoubleValue(res.getLacticAcidContent());
		spnCitrusAcid.setDoubleValue(res.getCitrusAcid());
		spnCitrusAcidContent.setDoubleValue(res.getCitrusAcidContent());
		
		useGypsum.setSelected(res.getUseGypsum());
		useGypsumSparge.setSelected(res.getUseGypsumSparge());
		useEpsom.setSelected(res.getUseEpsom());
		useEpsomSparge.setSelected(res.getUseEpsomSparge());
		useCaCl2.setSelected(res.getUseCaCl2());
		useCaCl2Sparge.setSelected(res.getUseCaCl2Sparge());
		useNaCl.setSelected(res.getUseNaCl());
		useNaClSparge.setSelected(res.getUseNaClSparge());
		useChalk.setSelected(res.getUseChalk());
		useChalkSparge.setSelected(res.getUseChalkSparge());
		useSoda.setSelected(res.getUseSoda());
		useSodaSparge.setSelected(res.getUseSodaSparge());
		useSlakedLime.setSelected(res.getUseSlakedLime());
		useSlakedLimeSparge.setSelected(res.getUseSlakedLimeSparge());
		
		
		
		for (SaltType saltType: SaltType.values())
		{
			toggleSalt(saltType);
		}
		

		fireStateChanged(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
	}

	private void recalcTreatment() {
		if (skipRecalc)
			return;
		double LITRI = spnVolume.getVolume();
		WaterProfile treat = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		res = treat;
		treat.setGypsum((spnGypsum.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setEpsom((spnEpsom.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setSale((spnNaCl.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setCalciumChloride((spnCaCl2.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setSoda((spnSoda.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setChalk((spnChalk.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		
		treat.setUseGypsum(useGypsum());
		treat.setUseEpsom(useEpsom());
		treat.setUseNaCl(useNaCl());
		treat.setUseCaCl2(useCaCl2());
		treat.setUseSoda(useSoda());
		treat.setUseChalk(useChalk());
		treat.setUseSlakedLime(useSlakedLime());
		
		treat.setUseGypsumSparge(useGypsumSparge());
		treat.setUseEpsomSparge(useEpsomSparge());
		treat.setUseNaClSparge(useNaClSparge());
		treat.setUseCaCl2Sparge(useCaCl2Sparge());
		treat.setUseSodaSparge(useSodaSparge());
		treat.setUseChalkSparge(useChalkSparge());
		treat.setUseSlakedLimeSparge(useSlakedLimeSparge());
		
		

		treat.setSlakedLime((spnSlakedLime.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setAcidulatedMaltContent((spnAcidulatedMaltContent.getDoubleValue()));
		treat.setLacticAcid((spnLacticAcid.getDoubleValue()));
		treat.setLacticAcidContent((spnLacticAcidContent.getDoubleValue()));
		treat.setCitrusAcid((spnCitrusAcid.getDoubleValue()));
		treat.setCitrusAcidContent((spnCitrusAcidContent.getDoubleValue()));

		res = treat;
		updateTreatment();

		for (SaltType saltType : SaltType.values()) {
			toggleSalt(saltType);
		}
	}

	@Override
	public void setEnabled(boolean F) {
		jToggleButton1.setEnabled(F);
		pCalcio.setEnabled(F);
		pCarbonato.setEnabled(F);
		pCloruro.setEnabled(F);
		pMagnesio.setEnabled(F);
		pSodio.setEnabled(F);
		pSolfato.setEnabled(F);
		spinCalcio.setEnabled(F);
		spinCalcio1.setEnabled(F);
		spinCalcio2.setEnabled(F);
		spinCarb.setEnabled(F);
		spinCarb1.setEnabled(F);
		spinCarb2.setEnabled(F);
		spinCloruro.setEnabled(F);
		spinCloruro1.setEnabled(F);
		spinCloruro2.setEnabled(F);
		spinMagnesio.setEnabled(F);
		spinMagnesio1.setEnabled(F);
		spinMagnesio2.setEnabled(F);
		spinSodio.setEnabled(F);
		spinSodio1.setEnabled(F);
		spinSodio2.setEnabled(F);
		spinSolfato.setEnabled(F);
		spinSolfato1.setEnabled(F);
		spinSolfato2.setEnabled(F);
		spnCaCl2.setEnabled(F);
		spnChalk.setEnabled(F);
		spnEpsom.setEnabled(F);
		spnGypsum.setEnabled(F);
		spnNaCl.setEnabled(F);
		spnSoda.setEnabled(F);
		spnVolume.setEnabled(F);
		useCaCl2.setEnabled(F);
		useChalk.setEnabled(F);
		useEpsom.setEnabled(F);
		useGypsum.setEnabled(F);
		useNaCl.setEnabled(F);
		useSoda.setEnabled(F);
		useSlakedLime.setEnabled(F);
		useCaCl2Sparge.setEnabled(F);
		useChalkSparge.setEnabled(F);
		useEpsomSparge.setEnabled(F);
		useGypsumSparge.setEnabled(F);
		useNaClSparge.setEnabled(F);
		useSodaSparge.setEnabled(F);
		useSlakedLimeSparge.setEnabled(F);
		btnA.setEnabled(F);
		btnB.setEnabled(F);

	}

	public void setTotWater(double size) {
		spnVolume.setVolume(size);
	}

	public void fer() {
		WaterProfile wp = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		String txt = "HD = " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk = " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA = " + RA;
		Utils.showException(null, txt, parent);
	}

	public double getCalcio() {
		return spinCalcio.getDoubleValue();
	}

	public double getMagnesio() {
		return spinMagnesio.getDoubleValue();
	}

	public double getSolfato() {
		return spinSolfato.getDoubleValue();
	}

	public double getCloruro() {
		return spinCloruro.getDoubleValue();
	}

	public double getSodio() {
		return spinSodio.getDoubleValue();
	}

	public double getCarb() {
		return spinCarb.getDoubleValue();
	}

	// public double getAdjustGypsum() {
	// return useGypsum.isSelected() ? spnGypsum.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustEpsom() {
	// return useEpsom.isSelected() ? spnEpsom.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustBicarbonatoDiSodio() {
	// return useSoda.isSelected() ? spnSoda.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustCloruroDiCalcio() {
	// return useCaCl2.isSelected() ? spnCaCl2.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustCloruroDiSodio() {
	// return useNaCl.isSelected() ? spnNaCl.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustCarbonatoDiCalcio() {
	// return useChalk.isSelected() ? spnChalk.getDoubleValue() : 0.0;
	// }
	//
	// public double getAdjustIdrossidoDiCalcio() {
	// return useSlakedLime.isSelected() ? spnSlakedLime.getDoubleValue() : 0.0;
	// }

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		listenerList.remove(ChangeListener.class, listener);
	}

	protected void fireStateChanged(ActionEvent actionEvent) {
		ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
		if (listeners != null && listeners.length > 0) {
			ChangeEvent evt = new ChangeEvent(actionEvent);
			for (ChangeListener listener : listeners) {
				listener.stateChanged(evt);
			}
		}
	}

	public double getLacticAcid() {
		return spnLacticAcid.getDoubleValue();
	}

	public double getLacticAcidContent() {
		return spnLacticAcidContent.getDoubleValue();
	}

	public double getCitrusAcid() {
		return spnCitrusAcid.getDoubleValue();
	}

	public double getCitrusAcidContent() {
		return spnCitrusAcidContent.getDoubleValue();
	}

	public double getAcidulatedMaltContent() {
		return spnAcidulatedMaltContent.getDoubleValue();
	}

	public void actionPerformed(ActionEvent evt) {
		fireStateChanged(evt);
	}

	public void setRA(Double RA) {
		this.txtRA.setText(Utils.format(RA, "0.000"));
	}

	public void setAlk(Double alk) {
		this.txtAlk.setText(Utils.format(alk, "0.000"));
	}

	public void setPH(Double pH) {
		this.txtPH.setText(Utils.format(pH, "0.000"));
	}

	public void setTotalAcidGrainWeightGr(Double grammi) {
		this.txtAcidMalt.setText(Utils.format(grammi, "0.0"));
	}

	private void setPalmerRecommendedRange(PalmerRecommendedRangeType type, JTextField palmerTextfield) {
		palmerTextfield.setFont(new Font("Tahoma", Font.BOLD, 9)); // NOI18N
		palmerTextfield.setForeground(Color.RED);
		palmerTextfield.setText(readPalmerRecommendedRange(type));
	}

	private String readPalmerRecommendedRange(PalmerRecommendedRangeType type) {
		Double[] range = RicettaUtils.getPalmerRecommendedRange(type);
		return range[0] + " - " + range[1];
	}

	private void prepareTextFielsWaterProfileView(JTextField textField, JPanel jpanel, int gridx, int gridy,
			int columns) {
		prepareTextFielsWaterProfileView(textField, jpanel, gridx, gridy, columns, false);
	}

	private void prepareTextFielsWaterProfileView(JTextField textField, JPanel jpanel, int gridx, int gridy,
			int columns, boolean editable) {

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		textField.setPreferredSize(new Dimension(102, 22));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		jPanelResultWaterProfile.add(textField, gridBagConstraints);
		textField.setColumns(columns);
		textField.setEditable(editable);
		jpanel.add(textField, gridBagConstraints);
	}

	public void setWaterProfile(ResultingWaterProfileType type, Double resultingWaterProfile) {
		switch (type) {
		case MASH_CALCIUM:
			this.textFieldMashCalcium.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_MAGNESIUM:
			this.textFieldMashMagnesium.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_SODIUM:
			this.textFieldMashSodium.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_CHLORIDE:
			this.textFieldMashChloride.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_SULFATE:
			this.textFieldMashSulfate.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_CHLORIDE_SULFATE_RATIO:
			this.textFieldMashChlorideSulfateRatio.setText(Utils.format(resultingWaterProfile, "0.00"));
			break;
		case MASH_SPARGE_CALCIUM:
			this.textFieldMashSpargeCalcium.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerColor(textFieldPalmerCalcium, resultingWaterProfile, PalmerRecommendedRangeType.CALCIUM);
			break;
		case MASH_SPARGE_MAGNESIUM:
			this.textFieldMashSpargeMagnesium.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerColor(textFieldPalmerMagnesium, resultingWaterProfile, PalmerRecommendedRangeType.MAGNESIUM);
			break;
		case MASH_SPARGE_SODIUM:
			this.textFieldMashSpargeSodium.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerColor(textFieldPalmerSodium, resultingWaterProfile, PalmerRecommendedRangeType.SODIUM);
			break;
		case MASH_SPARGE_CHLORIDE:
			this.textFieldMashSpargeChloride.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerColor(textFieldPalmerChloride, resultingWaterProfile, PalmerRecommendedRangeType.CHLORIDE);
			break;
		case MASH_SPARGE_SULFATE:
			this.textFieldMashSpargeSulfate.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerColor(textFieldPalmerSulfate, resultingWaterProfile, PalmerRecommendedRangeType.SULFATE);
			break;
		case MASH_SPARGE_CHLORIDE_SULFATE_RATIO:
			this.textFieldMashSpargeChlorideSulfateRatio.setText(Utils.format(resultingWaterProfile, "0.00"));
			setPalmerResult(resultingWaterProfile);
			break;
		default:
			break;

		}
	}

	private void setPalmerColor(JTextField texfield, Double value, PalmerRecommendedRangeType type) {
		boolean isPalmerOk = RicettaUtils.isPalmerValueOk(value, type);
		Color okColor = new Color(31, 112, 68);
		Color koColor = Color.RED;
		texfield.setForeground(isPalmerOk ? okColor : koColor);
	}

	private void setPalmerResult(Double value) {
		String amara = "< 0.77 - Amara "; // "Sotto 0,77, può esaltare
											// l'amarezza";
		String bilanciata = "Bilanciata";
		String maltata = "> 1,3 - Maltata"; // Sopra 1,3 può esaltare il
											// maltato";
		Color okColor = new Color(31, 112, 68);
		Color koColor = Color.RED;
		Color color = 0.77 <= value && value <= 1.3 ? okColor : koColor;

		String text = value < 0.77 ? amara : 0.77 <= value && value <= 1.3 ? bilanciata : maltata;
		this.textFieldPalmerChlorideSulfateRatio.setText(text);
		this.textFieldPalmerChlorideSulfateRatio.setForeground(color);
	}

	public void setSaltValues(SaltType saltType, double volumeMashLitri, double volumeSpargeLitri) {
		double volumeTotale = volumeMashLitri + volumeSpargeLitri;
		switch (saltType) {
		case GYPSUM:
			double gypsumMash = getGypsum() * (useGypsumSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double gypsumSparge = useGypsumSparge.isSelected() ? getGypsum() - gypsumMash : 0.0;
			this.txtGypsumMash.setText(Utils.format(gypsumMash, "0.0"));
			this.txtGyspumSparge.setText(Utils.format(gypsumSparge, "0.0"));
			break;
		case EPSOM:
			double epsomMash = getEpsom() * (useEpsomSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double epsomSparge = useEpsomSparge.isSelected() ? getEpsom() - epsomMash : 0.0;
			this.txtEpsomMash.setText(Utils.format(epsomMash, "0.0"));
			this.txtEpsomSparge.setText(Utils.format(epsomSparge, "0.0"));
			break;
		case CACL2:
			double caCl2Mash = getCaCl2() * (useCaCl2Sparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double caCl2Sparge = useCaCl2Sparge.isSelected() ? getCaCl2() - caCl2Mash : 0.0;
			this.txtCaCl2Mash.setText(Utils.format(caCl2Mash, "0.0"));
			this.txtCaCl2Sparge.setText(Utils.format(caCl2Sparge, "0.0"));
			break;
		case NACL:
			double naClMash = getNaCl() * (useNaClSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double naClSparge = useNaClSparge.isSelected() ? getNaCl() - naClMash : 0.0;
			this.txtNaClMash.setText(Utils.format(naClMash, "0.0"));
			this.txtNaClSparge.setText(Utils.format(naClSparge, "0.0"));
			break;
		case CHALK:
			double chalkMash = getChalk() * (useChalkSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double chalkSparge = useChalkSparge.isSelected() ? getChalk() - chalkMash : 0.0;
			this.txtChalkMash.setText(Utils.format(chalkMash, "0.0"));
			this.txtChalkSparge.setText(Utils.format(chalkSparge, "0.0"));
			break;
		case SODA:
			double sodaMash = getSoda() * (useSodaSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double sodaSparge = useSodaSparge.isSelected() ? getSoda() - sodaMash : 0.0;
			this.txtSodaMash.setText(Utils.format(sodaMash, "0.0"));
			this.txtSodaSparge.setText(Utils.format(sodaSparge, "0.0"));
			break;
		case SLAKED_LIME:
			double slakedLimeMash = getSlakedLime()
					* (useSlakedLimeSparge.isSelected() ? volumeMashLitri / volumeTotale : 1);
			double slakedLimeSparge = useSlakedLimeSparge.isSelected() ? getSlakedLime() - slakedLimeMash : 0.0;
			this.txtSlakedLimeMash.setText(Utils.format(slakedLimeMash, "0.0"));
			this.txtSlakedLimeSparge.setText(Utils.format(slakedLimeSparge, "0.0"));
			break;
		default:
			break;
		}

	}

	public boolean useCaCl2() {
		return useCaCl2.isSelected();
	}

	public void setUseCaCl2(boolean useCaCl2) {
		this.useCaCl2.setSelected(useCaCl2);
	}

	public boolean useChalk() {
		return useChalk.isSelected();
	}

	public void setUseChalk(boolean useChalk) {
		this.useChalk.setSelected(useChalk);
	}

	public boolean useEpsom() {
		return useEpsom.isSelected();
	}

	public void setUseEpsom(boolean useEpsom) {
		this.useEpsom.setSelected(useEpsom);
	}

	public boolean useGypsum() {
		return useGypsum.isSelected();
	}

	public void setUseGypsum(boolean useGypsum) {
		this.useGypsum.setSelected(useGypsum);
	}

	public boolean useNaCl() {
		return useNaCl.isSelected();
	}

	public void setUseNaCl(boolean useNaCl) {
		this.useNaCl.setSelected(useNaCl);
	}

	public boolean useSoda() {
		return useSoda.isSelected();
	}

	public void setUseSoda(boolean useSoda) {
		this.useSoda.setSelected(useSoda);
	}

	public boolean useGypsumSparge() {
		return useGypsumSparge.isSelected();
	}

	public void setUseGypsumSparge(boolean useGypsumSparge) {
		this.useGypsumSparge.setSelected(useGypsumSparge);
	}

	public boolean isGypsumSpargeActive() {
		return useGypsumSparge.isVisible() && useGypsumSparge.isSelected();
	}

	public boolean useEpsomSparge() {
		return useEpsomSparge.isSelected();
	}

	public boolean isEpsomSpargeActive() {
		return useEpsomSparge.isVisible() && useEpsomSparge.isSelected();
	}

	public void setUseEpsomSparge(boolean useEpsomSparge) {
		this.useEpsomSparge.setSelected(useEpsomSparge);
	}

	public boolean useCaCl2Sparge() {
		return useCaCl2Sparge.isSelected();
	}

	public boolean isCaCl2SpargeActive() {
		return useCaCl2Sparge.isVisible() && useCaCl2Sparge.isSelected();
	}

	public void setUseCaCl2Sparge(boolean useCaCl2Sparge) {
		this.useCaCl2Sparge.setSelected(useCaCl2Sparge);
	}

	public boolean useNaClSparge() {
		return useNaClSparge.isSelected();
	}

	public boolean isNaClSpargeActive() {
		return useNaClSparge.isVisible() && useNaClSparge.isSelected();
	}

	public void setUseNaClSparge(boolean useNaClSparge) {
		this.useNaClSparge.setSelected(useNaClSparge);
	}

	public boolean useChalkSparge() {
		return useChalkSparge.isSelected();
	}

	public boolean isChalkSpargeActive() {
		return useChalkSparge.isVisible() && useChalkSparge.isSelected();
	}

	public void setUseChalkSparge(boolean useChalkSparge) {
		this.useChalkSparge.setSelected(useChalkSparge);
	}

	public boolean useSodaSparge() {
		return useSodaSparge.isSelected();
	}

	public boolean isSodaSpargeActive() {
		return useSodaSparge.isVisible() && useSodaSparge.isSelected();
	}

	public void setUseSodaSparge(boolean useSodaSparge) {
		this.useSodaSparge.setSelected(useSodaSparge);
	}

	public boolean useSlakedLime() {
		return useSlakedLime.isSelected();
	}

	public void setUseSlakedLime(boolean useSlakedLime) {
		this.useSlakedLime.setSelected(useSlakedLime);
	}

	public boolean useSlakedLimeSparge() {
		return useSlakedLimeSparge.isSelected();
	}

	public boolean isSlakedLimeSpargeActive() {
		return useSlakedLimeSparge.isVisible() && useSlakedLimeSparge.isSelected();
	}

	public void setUseSlakedLimeSparge(boolean useSlakedLimeSparge) {
		this.useSlakedLimeSparge.setSelected(useSlakedLimeSparge);
	}

	public void setBIAB(boolean biab) {

		lblMash.setVisible(!biab);
		lblSparge.setVisible(!biab);

		txtGypsumMash.setVisible(!biab);
		txtGyspumSparge.setVisible(!biab);
		useGypsumSparge.setVisible(!biab);

		txtEpsomMash.setVisible(!biab);
		txtEpsomSparge.setVisible(!biab);
		useEpsomSparge.setVisible(!biab);

		txtCaCl2Mash.setVisible(!biab);
		txtCaCl2Sparge.setVisible(!biab);
		useCaCl2Sparge.setVisible(!biab);

		txtNaClMash.setVisible(!biab);
		txtNaClSparge.setVisible(!biab);
		useNaClSparge.setVisible(!biab);

		txtChalkMash.setVisible(!biab);
		txtChalkSparge.setVisible(!biab);
		useChalkSparge.setVisible(!biab);

		txtSodaMash.setVisible(!biab);
		txtSodaSparge.setVisible(!biab);
		useSodaSparge.setVisible(!biab);

		txtSlakedLimeMash.setVisible(!biab);
		txtSlakedLimeSparge.setVisible(!biab);
		useSlakedLimeSparge.setVisible(!biab);

		lblMashAndSpargeWP.setVisible(!biab);
		textFieldMashSpargeCalcium.setVisible(!biab);
		textFieldMashSpargeMagnesium.setVisible(!biab);
		textFieldMashSpargeSodium.setVisible(!biab);
		textFieldMashSpargeChloride.setVisible(!biab);
		textFieldMashSpargeSulfate.setVisible(!biab);
		textFieldMashSpargeChlorideSulfateRatio.setVisible(!biab);

		lblMashWP.setText(!biab ? "Mash Water Profile" : "BIAB Water Profile");

	}

	public double getGypsum() {
		return useGypsum.isSelected() ? spnGypsum.getDoubleValue() : 0.0;
	}

	public double getGypsumMash() {
		try {
			return useGypsum.isSelected() ? Double.parseDouble(txtGypsumMash.getText().replaceAll(",", ".")) : 0.0;
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}

	public double getGypsumSparge() {
		try {
			return useGypsum.isSelected() ? Double.parseDouble(txtGyspumSparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getEpsom() {
		return useEpsom.isSelected() ? spnEpsom.getDoubleValue() : 0.0;
	}

	public double getEpsomMash() {
		try {
			return useEpsom.isSelected() ? Double.parseDouble(txtEpsomMash.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getEpsomSparge() {
		try {
			return useEpsom.isSelected() ? Double.parseDouble(txtEpsomSparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getCaCl2() {
		return useCaCl2.isSelected() ? spnCaCl2.getDoubleValue() : 0.0;
	}

	public double getCaCl2Mash() {
		try {
			return useCaCl2.isSelected() ? Double.parseDouble(txtCaCl2Mash.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getCaCl2Sparge() {
		try {
			return useCaCl2.isSelected() ? Double.parseDouble(txtCaCl2Sparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getNaCl() {
		return useNaCl.isSelected() ? spnNaCl.getDoubleValue() : 0.0;
	}

	public double getNaClMash() {
		try {
			return useNaCl.isSelected() ? Double.parseDouble(txtNaClMash.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getNaClSparge() {
		try {
			return useNaCl.isSelected() ? Double.parseDouble(txtNaClSparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getChalk() {
		return useChalk.isSelected() ? spnChalk.getDoubleValue() : 0.0;
	}

	public double getChalkMash() {
		try {
			return useChalk.isSelected() ? Double.parseDouble(txtChalkMash.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getChalkSparge() {
		try {
			return useChalk.isSelected() ? Double.parseDouble(txtChalkSparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getSoda() {
		return useSoda.isSelected() ? spnSoda.getDoubleValue() : 0.0;
	}

	public double getSodaMash() {
		try {
			return useSoda.isSelected() ? Double.parseDouble(txtSodaMash.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getSodaSparge() {
		try {
			return useSoda.isSelected() ? Double.parseDouble(txtSodaSparge.getText().replaceAll(",", ".")) : 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getSlakedLime() {
		return useSlakedLime.isSelected() ? spnSlakedLime.getDoubleValue() : 0.0;
	}

	public double getSlakedLimeMash() {
		try {
			return useSlakedLime.isSelected() ? Double.parseDouble(txtSlakedLimeSparge.getText().replaceAll(",", "."))
					: 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public double getSlakedLimeSparge() {
		try {
			return useSlakedLime.isSelected() ? Double.parseDouble(txtSlakedLimeMash.getText().replaceAll(",", "."))
					: 0.0;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	private void toggleSalt(JCheckBox useSalt, JMashSpinner totSalt, JTextField unusedTotSalt, JTextField mashSalt,
			JTextField spargeSalt, JCheckBox useSpargeSalt) {

		totSalt.setVisible(useSalt.isSelected());
		unusedTotSalt.setVisible(!useSalt.isSelected());

		mashSalt.setEnabled(useSalt.isSelected());
		spargeSalt.setEnabled(useSalt.isSelected());
		useSpargeSalt.setEnabled(useSalt.isSelected());

	}

	private void toggleSalt(SaltType saltType) {
		JCheckBox useSalt = null;
		JMashSpinner totSalt = null;
		JTextField unusedTotSalt = null;
		JTextField mashSalt = null;
		JTextField spargeSalt = null;
		JCheckBox useSpargeSalt = null;

		switch (saltType) {
		case CACL2:
			useSalt = useCaCl2;
			unusedTotSalt = unusedCaCl2;
			totSalt = spnCaCl2;
			mashSalt = txtCaCl2Mash;
			spargeSalt = txtCaCl2Sparge;
			useSpargeSalt = useCaCl2Sparge;
			break;
		case CHALK:
			useSalt = useChalk;
			unusedTotSalt = unusedChalk;
			totSalt = spnChalk;
			mashSalt = txtChalkMash;
			spargeSalt = txtChalkSparge;
			useSpargeSalt = useChalkSparge;
			break;
		case EPSOM:
			useSalt = useEpsom;
			unusedTotSalt = unusedEpsom;
			totSalt = spnEpsom;
			mashSalt = txtEpsomMash;
			spargeSalt = txtEpsomSparge;
			useSpargeSalt = useEpsomSparge;
			break;
		case GYPSUM:
			useSalt = useGypsum;
			unusedTotSalt = unusedGypsum;
			totSalt = spnGypsum;
			mashSalt = txtGypsumMash;
			spargeSalt = txtGyspumSparge;
			useSpargeSalt = useGypsumSparge;
			break;
		case NACL:
			useSalt = useNaCl;
			unusedTotSalt = unusedNaCl;
			totSalt = spnNaCl;
			mashSalt = txtNaClMash;
			spargeSalt = txtNaClSparge;
			useSpargeSalt = useNaClSparge;
			break;
		case SLAKED_LIME:
			useSalt = useSlakedLime;
			unusedTotSalt = unusedSlakedLime;
			totSalt = spnSlakedLime;
			mashSalt = txtSlakedLimeMash;
			spargeSalt = txtSlakedLimeSparge;
			useSpargeSalt = useSlakedLimeSparge;
			break;
		case SODA:
			useSalt = useSoda;
			unusedTotSalt = unusedSoda;
			totSalt = spnSoda;
			mashSalt = txtSodaMash;
			spargeSalt = txtSodaSparge;
			useSpargeSalt = useSodaSparge;
			break;
		}

		toggleSalt(useSalt, totSalt, unusedTotSalt, mashSalt, spargeSalt, useSpargeSalt);
	}

}
