package jmash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import jmash.component.JMashSpinner;
import org.jdom.Element;

public class WaterNeededNew2 extends JInternalFrame {
	private static final long serialVersionUID = -5301195065823912614L;
	private static Logger LOGGER = Logger.getLogger(WaterNeededNew2.class);

	private JTabbedPane tabbedPane;
	private JPanel panelSparge;
	private JPanel panelBatchSparge;
	private JPanel panelNoSparge;
	private JPanel panelSpecificheCottaSparge;
	private JPanel panelDatiImpiantoSparge;
	private JPanel panelCalcoloPerditeSparge;
	private JPanel panelCalcoloVolumiSparge;
	// private JPanel panelRabbocchiSparge;
	private JPanel panelRisultatiSparge;
	private JLabel lblBatchSize;
	private JLabel lblGraniTotali;
	private JLabel lblOriginalGravity;
	private JLabel lblAssorbimentoGraniEsausti;
	private JLabel lblRapportoAcquaGrani;
	private JLabel lblPercentualeEvaporazione;
	private JLabel lblContrazionePerRaffreddamento;
	private JCheckBox chckbxPerditeNelTrub;
	private JMashSpinner spinnerBatchSize;
	private JMashSpinner spinnerGraniTotali;
	private JMashSpinner spinnerOriginalGravity;
	private JMashSpinner spinnerAssorbimentoGraniEsausti;
	private JMashSpinner spinnerRapportoAcquaGrani;
	private JMashSpinner spinnerPercentualeEvaporazione;
	private JMashSpinner spinnerContrazionePerRaffreddamento;
	private JMashSpinner spinnerPerditeNelTrub;
	private JLabel lbl_1;
	private JLabel lbl_2;
	private JLabel lbl_3;
	private JLabel lblNewLabel_1;
	private JLabel lblLitrikg;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblLitri;
	private JLabel lblPerditaPerAssorbimento;
	private JLabel lblPerditaPerEvaporazione;
	private JLabel lblPerditaPerContrazione;
	private JLabel lblVolumeMostoPreboil;
	private JLabel lblOgPreboil;
	private JLabel lblVolumePostboil;
	private JLabel lblVolumePostraffreddamento;
	private JLabel lblAcquaDiMash;
	private JLabel lblAcquaDiSparge;
	private JMashSpinner spinnerPerditaPerAssorbimento;
	private JMashSpinner spinnerPerditaPerEvaporazione;
	private JMashSpinner spinnerPerditaPerContrazione;
	private JMashSpinner spinnerVolumeMostoPreBoil;
	private JMashSpinner spinnerOGPreBoil;
	private JMashSpinner spinnerVolumePostBoil;
	private JMashSpinner spinnerVolumePostRaffreddamento;
	private JMashSpinner spinnerAcquaMash;
	private JMashSpinner spinnerAcquaSparge;
	private JLabel lblLitri_1;
	private JLabel lblLitri_2;
	private JLabel lblLitri_3;
	private JLabel lblLitri_4;
	private JLabel lblLitri_5;
	private JLabel lblLitri_6;
	private JLabel lblLitri_7;
	private JLabel lblLitri_8;
	private JLabel lblLitri_9;
	private JMashSpinner spinnerTotaleAcqua;
	private JLabel lblTotaleAcquaRichiesta;
	private JLabel lblLitri_10;

	public WaterNeededNew2() {
		initComponents();
		setBorder(Utils.getDefaultBorder());

		// spinnerBackSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.BS");
		// spinnerTrub.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.Trub");
		
		spinnerBatchSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5, "0.00", "WaterNeeded.BS");
		spinnerGraniTotali.setModel(0.0, 0.0, 1000000.0, 0.5, "0.000", "WaterNeeded.TotGrani");
		spinnerOriginalGravity.setModel(0.0, 0.0, 1000000.0, 1, "0", "WaterNeeded.OG");
		spinnerAssorbimentoGraniEsausti.setModel(Main.getFromCache("WaterNeeded.coefficienteAssorbimento", 1.01), 0, 1000000, 0.001, "0.000", "WaterNeeded.ABS");
		
		spinnerPerditeNelTrub.setModel(Main.config.getLostToTrub(), 0.0, 1000000, 0.1, "0.000", "WaterNeeded.trub");
		spinnerRapportoAcquaGrani.setModel(Main.config.getLitriPerKg(), 0.0, 1000000, 0.25, "0.000", "WaterNeeded.kg");
		
		spinnerPercentualeEvaporazione.setModel(Main.getFromCache("WaterNeeded.pEV", 4.0), 0.0, 1000000, 0.25, "0.000", "WaterNeeded.pEV");
		spinnerContrazionePerRaffreddamento.setModel(Main.getFromCache("WaterNeeded.shrink", 4.0), 0, 99, 1, "0.000", "WaterNeeded.shrink");

		spinnerPerditaPerAssorbimento.setModel(Main.getFromCache("WaterNeeded.perditaPerAssorbimento", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.PerdAss");
		spinnerPerditaPerEvaporazione.setModel(Main.getFromCache("WaterNeeded.perditaPerEvaporazione", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.PerdEvap");
		spinnerPerditaPerContrazione.setModel(Main.getFromCache("WaterNeeded.perditaPerContrazione", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.PerdContraz");
		
		
		spinnerVolumeMostoPreBoil.setModel(Main.getFromCache("WaterNeeded.PB", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.PB");
		spinnerOGPreBoil.setModel(Main.getFromCache("WaterNeeded.pOG", 0.0), 0, 1000000, 0.5, "0", "WaterNeeded.pOG");
		spinnerVolumePostBoil.setModel(Main.getFromCache("WaterNeeded.PostB", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.PostB");
		spinnerVolumePostRaffreddamento.setModel(Main.getFromCache("WaterNeeded.volumePostRaffreddamento", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.volumePostRaffreddamento");
		
		spinnerAcquaMash.setModel(Main.getFromCache("WaterNeeded.volumeMash", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.volumeMash");
		spinnerAcquaSparge.setModel(Main.getFromCache("WaterNeeded.volumeSparge", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.volumeSparge");	
		spinnerTotaleAcqua.setModel(Main.getFromCache("WaterNeeded.volumeTotale", 0.0), 0, 1000000, 0.5, "0.000", "WaterNeeded.volumeTotale");
		
		
		
		
		setBackground(getBackground().darker());
		panelSparge.setBackground(panelSparge.getBackground().darker());

		// tabbedPane.setEnabledAt(1, false);
		// tabbedPane.setEnabledAt(2, false);
	}
	
	public WaterNeededNew2(double batchSize, double kg, double boilTime, double evap) {
        this();
        spinnerBatchSize.setDoubleValue(batchSize);
        spinnerGraniTotali.setDoubleValue(kg);
        
//        spnBoiltime.setDoubleValue(boilTime);
//        spnEvaporation.setVolume(evap);
        
        calcolaQuantitaAcqua();
    }

	private void initComponents() {

		tabbedPane = new JTabbedPane();

		panelSparge = new JPanel(new GridBagLayout());
		panelBatchSparge = new JPanel(new GridBagLayout());
		panelNoSparge = new JPanel(new GridBagLayout());

		setClosable(true);
		setIconifiable(true);
		setTitle("Acqua necessaria");

		//getContentPane().add(tabbedPane, BorderLayout.CENTER);

		//tabbedPane.addTab("Con Sparge", panelSparge);
		//tabbedPane.addTab("Batch Sparge", panelBatchSparge);
		//tabbedPane.addTab("No Sparge", panelNoSparge);

		//getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

		initSpargePanel();
		//initBatchSpargePanel();
		//initNoSpargePanel();
		
		getContentPane().add(panelSparge, java.awt.BorderLayout.CENTER);
		

	}

	private void initSpargePanel() {

		initPanelSpecificheCottaSparge();
		initPanelDatiImpiantoSparge();

		initPanelCalcoloPerditeSparge();
		initPanelCalcoloVolumiSparge();
		initPanelRisultatiSparge();

	}

	private void initPanelSpecificheCottaSparge() {

		GridBagConstraints gridBagConstraints;

		panelSpecificheCottaSparge = new JPanel();
		panelSpecificheCottaSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Specifiche della cotta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0, 0 };
		;
		panelSpecificheCottaSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelSparge.add(panelSpecificheCottaSparge, gridBagConstraints);
		{
			lblBatchSize = new JLabel("Batch size");
			GridBagConstraints gbc_lblBatchSize = new GridBagConstraints();
			gbc_lblBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblBatchSize.gridx = 0;
			gbc_lblBatchSize.gridy = 0;
			panelSpecificheCottaSparge.add(lblBatchSize, gbc_lblBatchSize);
		}
		{
			spinnerBatchSize = new JMashSpinner();
			spinnerBatchSize.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerBatchSize.setEnabled(false);
			spinnerBatchSize.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerBatchSize = new GridBagConstraints();
			gbc_spinnerBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerBatchSize.gridx = 1;
			gbc_spinnerBatchSize.gridy = 0;
			panelSpecificheCottaSparge.add(spinnerBatchSize, gbc_spinnerBatchSize);
		}
		{
			lbl_1 = new JLabel("litri");
			GridBagConstraints gbc_lbl_1 = new GridBagConstraints();
			gbc_lbl_1.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_1.gridx = 2;
			gbc_lbl_1.gridy = 0;
			panelSpecificheCottaSparge.add(lbl_1, gbc_lbl_1);
		}
		{
			lblGraniTotali = new JLabel("Grani totali");
			GridBagConstraints gbc_lblGraniTotali = new GridBagConstraints();
			gbc_lblGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_lblGraniTotali.gridx = 0;
			gbc_lblGraniTotali.gridy = 1;
			panelSpecificheCottaSparge.add(lblGraniTotali, gbc_lblGraniTotali);
		}
		{
			spinnerGraniTotali = new JMashSpinner();
			spinnerGraniTotali.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerGraniTotali.setEnabled(false);
			spinnerGraniTotali.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerGraniTotali = new GridBagConstraints();
			gbc_spinnerGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerGraniTotali.gridx = 1;
			gbc_spinnerGraniTotali.gridy = 1;
			panelSpecificheCottaSparge.add(spinnerGraniTotali, gbc_spinnerGraniTotali);
		}
		{
			lbl_2 = new JLabel("kg");
			GridBagConstraints gbc_lbl_2 = new GridBagConstraints();
			gbc_lbl_2.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_2.gridx = 2;
			gbc_lbl_2.gridy = 1;
			panelSpecificheCottaSparge.add(lbl_2, gbc_lbl_2);
		}
		{
			lblOriginalGravity = new JLabel("Original Gravity");
			GridBagConstraints gbc_lblOriginalGravity = new GridBagConstraints();
			gbc_lblOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_lblOriginalGravity.gridx = 0;
			gbc_lblOriginalGravity.gridy = 2;
			panelSpecificheCottaSparge.add(lblOriginalGravity, gbc_lblOriginalGravity);
		}
		{
			spinnerOriginalGravity = new JMashSpinner();
			spinnerOriginalGravity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerOriginalGravity.setEnabled(false);
			spinnerOriginalGravity.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerOriginalGravity = new GridBagConstraints();
			gbc_spinnerOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerOriginalGravity.gridx = 1;
			gbc_spinnerOriginalGravity.gridy = 2;
			panelSpecificheCottaSparge.add(spinnerOriginalGravity, gbc_spinnerOriginalGravity);
		}
		{
			lbl_3 = new JLabel("SG");
			GridBagConstraints gbc_lbl_3 = new GridBagConstraints();
			gbc_lbl_3.insets = new Insets(0, 0, 0, 5);
			gbc_lbl_3.gridx = 2;
			gbc_lbl_3.gridy = 2;
			panelSpecificheCottaSparge.add(lbl_3, gbc_lbl_3);
		}

	}

	private void initPanelDatiImpiantoSparge() {

		GridBagConstraints gridBagConstraints;

		panelDatiImpiantoSparge = new JPanel();
		panelDatiImpiantoSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0, 0 };
		;
		panelDatiImpiantoSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelSparge.add(panelDatiImpiantoSparge, gridBagConstraints);
		{
			lblAssorbimentoGraniEsausti = new JLabel("Assorbimento grani esausti");
			GridBagConstraints gbc_lblAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_lblAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_lblAssorbimentoGraniEsausti.gridx = 0;
			gbc_lblAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpiantoSparge.add(lblAssorbimentoGraniEsausti, gbc_lblAssorbimentoGraniEsausti);
		}
		{
			spinnerAssorbimentoGraniEsausti = new JMashSpinner();
			spinnerAssorbimentoGraniEsausti.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerAssorbimentoGraniEsausti.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_spinnerAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAssorbimentoGraniEsausti.gridx = 1;
			gbc_spinnerAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpiantoSparge.add(spinnerAssorbimentoGraniEsausti, gbc_spinnerAssorbimentoGraniEsausti);
		}
		{
			lblNewLabel_1 = new JLabel("litri/kg");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 0;
			panelDatiImpiantoSparge.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			lblRapportoAcquaGrani = new JLabel("Rapporto acqua/grani");
			GridBagConstraints gbc_lblRapportoAcquaGrani = new GridBagConstraints();
			gbc_lblRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_lblRapportoAcquaGrani.gridx = 0;
			gbc_lblRapportoAcquaGrani.gridy = 1;
			panelDatiImpiantoSparge.add(lblRapportoAcquaGrani, gbc_lblRapportoAcquaGrani);
		}
		{
			spinnerRapportoAcquaGrani = new JMashSpinner();
			spinnerRapportoAcquaGrani.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerRapportoAcquaGrani.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerRapportoAcquaGrani = new GridBagConstraints();
			gbc_spinnerRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerRapportoAcquaGrani.gridx = 1;
			gbc_spinnerRapportoAcquaGrani.gridy = 1;
			panelDatiImpiantoSparge.add(spinnerRapportoAcquaGrani, gbc_spinnerRapportoAcquaGrani);
		}
		{
			lblLitrikg = new JLabel("litri/kg");
			GridBagConstraints gbc_lblLitrikg = new GridBagConstraints();
			gbc_lblLitrikg.insets = new Insets(0, 0, 5, 5);
			gbc_lblLitrikg.gridx = 2;
			gbc_lblLitrikg.gridy = 1;
			panelDatiImpiantoSparge.add(lblLitrikg, gbc_lblLitrikg);
		}
		{
			lblPercentualeEvaporazione = new JLabel("Percentuale evaporazione");
			GridBagConstraints gbc_lblPercentualeEvaporazione = new GridBagConstraints();
			gbc_lblPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPercentualeEvaporazione.gridx = 0;
			gbc_lblPercentualeEvaporazione.gridy = 2;
			panelDatiImpiantoSparge.add(lblPercentualeEvaporazione, gbc_lblPercentualeEvaporazione);
		}
		{
			spinnerPercentualeEvaporazione = new JMashSpinner();
			spinnerPercentualeEvaporazione.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPercentualeEvaporazione.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerPercentualeEvaporazione = new GridBagConstraints();
			gbc_spinnerPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPercentualeEvaporazione.gridx = 1;
			gbc_spinnerPercentualeEvaporazione.gridy = 2;
			panelDatiImpiantoSparge.add(spinnerPercentualeEvaporazione, gbc_spinnerPercentualeEvaporazione);
		}
		{
			label = new JLabel("%");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 2;
			panelDatiImpiantoSparge.add(label, gbc_label);
		}
		{
			lblContrazionePerRaffreddamento = new JLabel("Contrazione per raffreddamento");
			GridBagConstraints gbc_lblContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_lblContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblContrazionePerRaffreddamento.gridx = 0;
			gbc_lblContrazionePerRaffreddamento.gridy = 3;
			panelDatiImpiantoSparge.add(lblContrazionePerRaffreddamento, gbc_lblContrazionePerRaffreddamento);
		}
		{
			spinnerContrazionePerRaffreddamento = new JMashSpinner();
			spinnerContrazionePerRaffreddamento.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerContrazionePerRaffreddamento.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_spinnerContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerContrazionePerRaffreddamento.gridx = 1;
			gbc_spinnerContrazionePerRaffreddamento.gridy = 3;
			panelDatiImpiantoSparge.add(spinnerContrazionePerRaffreddamento, gbc_spinnerContrazionePerRaffreddamento);
		}
		{
			label_1 = new JLabel("%");
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.insets = new Insets(0, 0, 5, 5);
			gbc_label_1.gridx = 2;
			gbc_label_1.gridy = 3;
			panelDatiImpiantoSparge.add(label_1, gbc_label_1);
		}
		{
			chckbxPerditeNelTrub = new JCheckBox("Perdite nel Trub");
			chckbxPerditeNelTrub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spinnerPerditeNelTrub.setEnabled(chckbxPerditeNelTrub.isSelected());
					calcolaQuantitaAcqua();
				}
			});
			GridBagConstraints gbc_chckbxPerditeNelTrub = new GridBagConstraints();
			gbc_chckbxPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxPerditeNelTrub.gridx = 0;
			gbc_chckbxPerditeNelTrub.gridy = 4;
			panelDatiImpiantoSparge.add(chckbxPerditeNelTrub, gbc_chckbxPerditeNelTrub);
		}
		{
			spinnerPerditeNelTrub = new JMashSpinner();
			spinnerPerditeNelTrub.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPerditeNelTrub.setEnabled(false);
			spinnerPerditeNelTrub.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerPerditeNelTrub = new GridBagConstraints();
			gbc_spinnerPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditeNelTrub.gridx = 1;
			gbc_spinnerPerditeNelTrub.gridy = 4;
			panelDatiImpiantoSparge.add(spinnerPerditeNelTrub, gbc_spinnerPerditeNelTrub);
		}
		{
			lblLitri = new JLabel("litri");
			GridBagConstraints gbc_lblLitri = new GridBagConstraints();
			gbc_lblLitri.insets = new Insets(0, 0, 5, 5);
			gbc_lblLitri.gridx = 2;
			gbc_lblLitri.gridy = 4;
			panelDatiImpiantoSparge.add(lblLitri, gbc_lblLitri);
		}

	}

	private void initPanelCalcoloPerditeSparge() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloPerditeSparge = new JPanel();
		panelCalcoloPerditeSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo delle perdite"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloPerditeSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelSparge.add(panelCalcoloPerditeSparge, gridBagConstraints);
		{
			lblPerditaPerAssorbimento = new JLabel("Perdita per assorbimento");
			GridBagConstraints gbc_lblPerditaPerAssorbimento = new GridBagConstraints();
			gbc_lblPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerAssorbimento.gridx = 0;
			gbc_lblPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerditeSparge.add(lblPerditaPerAssorbimento, gbc_lblPerditaPerAssorbimento);
		}
		{
			spinnerPerditaPerAssorbimento = new JMashSpinner();
			spinnerPerditaPerAssorbimento.setEnabled(false);
			spinnerPerditaPerAssorbimento.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerPerditaPerAssorbimento = new GridBagConstraints();
			gbc_spinnerPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerAssorbimento.gridx = 1;
			gbc_spinnerPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerditeSparge.add(spinnerPerditaPerAssorbimento, gbc_spinnerPerditaPerAssorbimento);
		}
		{
			lblLitri_1 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_1 = new GridBagConstraints();
			gbc_lblLitri_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_1.gridx = 2;
			gbc_lblLitri_1.gridy = 0;
			panelCalcoloPerditeSparge.add(lblLitri_1, gbc_lblLitri_1);
		}
		{
			lblPerditaPerEvaporazione = new JLabel("Perdita per evaporazione");
			GridBagConstraints gbc_lblPerditaPerEvaporazione = new GridBagConstraints();
			gbc_lblPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerEvaporazione.gridx = 0;
			gbc_lblPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerditeSparge.add(lblPerditaPerEvaporazione, gbc_lblPerditaPerEvaporazione);
		}
		{
			spinnerPerditaPerEvaporazione = new JMashSpinner();
			spinnerPerditaPerEvaporazione.setEnabled(false);
			spinnerPerditaPerEvaporazione.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerPerditaPerEvaporazione = new GridBagConstraints();
			gbc_spinnerPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerEvaporazione.gridx = 1;
			gbc_spinnerPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerditeSparge.add(spinnerPerditaPerEvaporazione, gbc_spinnerPerditaPerEvaporazione);
		}
		{
			lblLitri_2 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_2 = new GridBagConstraints();
			gbc_lblLitri_2.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_2.gridx = 2;
			gbc_lblLitri_2.gridy = 1;
			panelCalcoloPerditeSparge.add(lblLitri_2, gbc_lblLitri_2);
		}
		{
			lblPerditaPerContrazione = new JLabel("Perdita per contrazione");
			GridBagConstraints gbc_lblPerditaPerContrazione = new GridBagConstraints();
			gbc_lblPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_lblPerditaPerContrazione.gridx = 0;
			gbc_lblPerditaPerContrazione.gridy = 2;
			panelCalcoloPerditeSparge.add(lblPerditaPerContrazione, gbc_lblPerditaPerContrazione);
		}
		{
			spinnerPerditaPerContrazione = new JMashSpinner();
			spinnerPerditaPerContrazione.setEnabled(false);
			spinnerPerditaPerContrazione.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerPerditaPerContrazione = new GridBagConstraints();
			gbc_spinnerPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerPerditaPerContrazione.gridx = 1;
			gbc_spinnerPerditaPerContrazione.gridy = 2;
			panelCalcoloPerditeSparge.add(spinnerPerditaPerContrazione, gbc_spinnerPerditaPerContrazione);
		}
		{
			lblLitri_3 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_3 = new GridBagConstraints();
			gbc_lblLitri_3.gridx = 2;
			gbc_lblLitri_3.gridy = 2;
			panelCalcoloPerditeSparge.add(lblLitri_3, gbc_lblLitri_3);
		}

	}

	private void initPanelCalcoloVolumiSparge() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloVolumiSparge = new JPanel();
		panelCalcoloVolumiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo dei volumi"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloVolumiSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelSparge.add(panelCalcoloVolumiSparge, gridBagConstraints);
		{
			lblVolumeMostoPreboil = new JLabel("Volume mosto pre-boil");
			GridBagConstraints gbc_lblVolumeMostoPreboil = new GridBagConstraints();
			gbc_lblVolumeMostoPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumeMostoPreboil.gridx = 0;
			gbc_lblVolumeMostoPreboil.gridy = 0;
			panelCalcoloVolumiSparge.add(lblVolumeMostoPreboil, gbc_lblVolumeMostoPreboil);
		}
		{
			spinnerVolumeMostoPreBoil = new JMashSpinner();
			spinnerVolumeMostoPreBoil.setEnabled(false);
			spinnerVolumeMostoPreBoil.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerVolumeMostoPreBoil = new GridBagConstraints();
			gbc_spinnerVolumeMostoPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumeMostoPreBoil.gridx = 1;
			gbc_spinnerVolumeMostoPreBoil.gridy = 0;
			panelCalcoloVolumiSparge.add(spinnerVolumeMostoPreBoil, gbc_spinnerVolumeMostoPreBoil);
		}
		{
			lblLitri_4 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_4 = new GridBagConstraints();
			gbc_lblLitri_4.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_4.gridx = 2;
			gbc_lblLitri_4.gridy = 0;
			panelCalcoloVolumiSparge.add(lblLitri_4, gbc_lblLitri_4);
		}
		{
			lblOgPreboil = new JLabel("OG pre-boil");
			GridBagConstraints gbc_lblOgPreboil = new GridBagConstraints();
			gbc_lblOgPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblOgPreboil.gridx = 0;
			gbc_lblOgPreboil.gridy = 1;
			panelCalcoloVolumiSparge.add(lblOgPreboil, gbc_lblOgPreboil);
		}
		{
			spinnerOGPreBoil = new JMashSpinner();
			spinnerOGPreBoil.setEnabled(false);
			spinnerOGPreBoil.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerOGPreBoil = new GridBagConstraints();
			gbc_spinnerOGPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerOGPreBoil.gridx = 1;
			gbc_spinnerOGPreBoil.gridy = 1;
			panelCalcoloVolumiSparge.add(spinnerOGPreBoil, gbc_spinnerOGPreBoil);
		}
		{
			lblLitri_5 = new JLabel("SG");
			GridBagConstraints gbc_lblLitri_5 = new GridBagConstraints();
			gbc_lblLitri_5.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_5.gridx = 2;
			gbc_lblLitri_5.gridy = 1;
			panelCalcoloVolumiSparge.add(lblLitri_5, gbc_lblLitri_5);
		}
		{
			lblVolumePostboil = new JLabel("Volume post-boil");
			GridBagConstraints gbc_lblVolumePostboil = new GridBagConstraints();
			gbc_lblVolumePostboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumePostboil.gridx = 0;
			gbc_lblVolumePostboil.gridy = 2;
			panelCalcoloVolumiSparge.add(lblVolumePostboil, gbc_lblVolumePostboil);
		}
		{
			spinnerVolumePostBoil = new JMashSpinner();
			spinnerVolumePostBoil.setEnabled(false);
			spinnerVolumePostBoil.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerVolumePostBoil = new GridBagConstraints();
			gbc_spinnerVolumePostBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumePostBoil.gridx = 1;
			gbc_spinnerVolumePostBoil.gridy = 2;
			panelCalcoloVolumiSparge.add(spinnerVolumePostBoil, gbc_spinnerVolumePostBoil);
		}
		{
			lblLitri_6 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_6 = new GridBagConstraints();
			gbc_lblLitri_6.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_6.gridx = 2;
			gbc_lblLitri_6.gridy = 2;
			panelCalcoloVolumiSparge.add(lblLitri_6, gbc_lblLitri_6);
		}
		{
			lblVolumePostraffreddamento = new JLabel("Volume post-raffreddamento");
			GridBagConstraints gbc_lblVolumePostraffreddamento = new GridBagConstraints();
			gbc_lblVolumePostraffreddamento.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumePostraffreddamento.gridx = 0;
			gbc_lblVolumePostraffreddamento.gridy = 3;
			panelCalcoloVolumiSparge.add(lblVolumePostraffreddamento, gbc_lblVolumePostraffreddamento);
		}
		{
			spinnerVolumePostRaffreddamento = new JMashSpinner();
			spinnerVolumePostRaffreddamento.setEnabled(false);
			spinnerVolumePostRaffreddamento.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerVolumePostRaffreddamento = new GridBagConstraints();
			gbc_spinnerVolumePostRaffreddamento.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerVolumePostRaffreddamento.gridx = 1;
			gbc_spinnerVolumePostRaffreddamento.gridy = 3;
			panelCalcoloVolumiSparge.add(spinnerVolumePostRaffreddamento, gbc_spinnerVolumePostRaffreddamento);
		}
		{
			lblLitri_7 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_7 = new GridBagConstraints();
			gbc_lblLitri_7.gridx = 2;
			gbc_lblLitri_7.gridy = 3;
			panelCalcoloVolumiSparge.add(lblLitri_7, gbc_lblLitri_7);
		}

	}

	private void initPanelRisultatiSparge() {

		GridBagConstraints gridBagConstraints;

		panelRisultatiSparge = new JPanel();
		panelRisultatiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Totale acqua richiesta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelRisultatiSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelSparge.add(panelRisultatiSparge, gridBagConstraints);
		{
			lblAcquaDiMash = new JLabel("Acqua di mash");
			GridBagConstraints gbc_lblAcquaDiMash = new GridBagConstraints();
			gbc_lblAcquaDiMash.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiMash.gridx = 0;
			gbc_lblAcquaDiMash.gridy = 0;
			panelRisultatiSparge.add(lblAcquaDiMash, gbc_lblAcquaDiMash);
		}
		{
			spinnerAcquaMash = new JMashSpinner();
			spinnerAcquaMash.setEnabled(false);
			spinnerAcquaMash.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerAcquaMash = new GridBagConstraints();
			gbc_spinnerAcquaMash.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaMash.gridx = 1;
			gbc_spinnerAcquaMash.gridy = 0;
			panelRisultatiSparge.add(spinnerAcquaMash, gbc_spinnerAcquaMash);
		}
		{
			lblLitri_8 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_8 = new GridBagConstraints();
			gbc_lblLitri_8.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_8.gridx = 2;
			gbc_lblLitri_8.gridy = 0;
			panelRisultatiSparge.add(lblLitri_8, gbc_lblLitri_8);
		}
		{
			lblAcquaDiSparge = new JLabel("Acqua di sparge");
			GridBagConstraints gbc_lblAcquaDiSparge_1 = new GridBagConstraints();
			gbc_lblAcquaDiSparge_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiSparge_1.gridx = 0;
			gbc_lblAcquaDiSparge_1.gridy = 1;
			panelRisultatiSparge.add(lblAcquaDiSparge, gbc_lblAcquaDiSparge_1);
		}
		{
			spinnerAcquaSparge = new JMashSpinner();
			spinnerAcquaSparge.setEnabled(false);
			spinnerAcquaSparge.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerAcquaDiSparge = new GridBagConstraints();
			gbc_spinnerAcquaDiSparge.anchor = GridBagConstraints.ABOVE_BASELINE;
			gbc_spinnerAcquaDiSparge.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaDiSparge.gridx = 1;
			gbc_spinnerAcquaDiSparge.gridy = 1;
			panelRisultatiSparge.add(spinnerAcquaSparge, gbc_spinnerAcquaDiSparge);
		}
		{
			lblLitri_9 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_9 = new GridBagConstraints();
			gbc_lblLitri_9.insets = new Insets(0, 0, 5, 0);
			gbc_lblLitri_9.gridx = 2;
			gbc_lblLitri_9.gridy = 1;
			panelRisultatiSparge.add(lblLitri_9, gbc_lblLitri_9);
		}
		{
			lblTotaleAcquaRichiesta = new JLabel("Totale acqua richiesta");
			GridBagConstraints gbc_lblTotaleAcquaRichiesta = new GridBagConstraints();
			gbc_lblTotaleAcquaRichiesta.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotaleAcquaRichiesta.gridx = 0;
			gbc_lblTotaleAcquaRichiesta.gridy = 2;
			panelRisultatiSparge.add(lblTotaleAcquaRichiesta, gbc_lblTotaleAcquaRichiesta);
		}
		{
			spinnerTotaleAcqua = new JMashSpinner();
			spinnerTotaleAcqua.setEnabled(false);
			spinnerTotaleAcqua.setPreferredSize(new Dimension(80, 20));
			GridBagConstraints gbc_spinnerTotaleAcqua = new GridBagConstraints();
			gbc_spinnerTotaleAcqua.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerTotaleAcqua.gridx = 1;
			gbc_spinnerTotaleAcqua.gridy = 2;
			panelRisultatiSparge.add(spinnerTotaleAcqua, gbc_spinnerTotaleAcqua);
		}
		{
			lblLitri_10 = new JLabel("litri");
			GridBagConstraints gbc_lblLitri_10 = new GridBagConstraints();
			gbc_lblLitri_10.gridx = 2;
			gbc_lblLitri_10.gridy = 2;
			panelRisultatiSparge.add(lblLitri_10, gbc_lblLitri_10);
		}

	}

	private void initBatchSpargePanel() {

	}

	private void initNoSpargePanel() {

	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}

	

	public void setBatchSize(double batchSize) {
		this.spinnerBatchSize.setDoubleValue(batchSize);
	}
	
	public void setTotGrani(double totGrani) {
		this.spinnerGraniTotali.setDoubleValue(totGrani);
	}
	
	public void setOriginalGravity(double originalGravity) {
		double sg = (originalGravity * 1000) - 1000;
		this.spinnerOriginalGravity.setDoubleValue(sg);
	}
	
	public void calcolaQuantitaAcqua() {
		
		// dati in input
		double batchSize = this.spinnerBatchSize.getDoubleValue();
		double totGrani = this.spinnerGraniTotali.getDoubleValue();
		double originalGravity = this.spinnerOriginalGravity.getDoubleValue();
		double assorbimentoGraniEsausti = this.spinnerAssorbimentoGraniEsausti.getDoubleValue();
		double rapportoAcquaGrani = this.spinnerRapportoAcquaGrani.getDoubleValue();
		double percentualeEvaporazione = this.spinnerPercentualeEvaporazione.getDoubleValue();
		double contrazioneRaffreddamento = this.spinnerContrazionePerRaffreddamento.getDoubleValue();
		double perditeNelTrub = this.chckbxPerditeNelTrub.isSelected() ? this.spinnerPerditeNelTrub.getDoubleValue() : 0.0;
		
		// inizio calcoli
		double perditeAssorbimento = totGrani * assorbimentoGraniEsausti;
		double volumePostRaffreddamento = batchSize + perditeNelTrub;
		double volumePostBoil = volumePostRaffreddamento * (1.0 + (contrazioneRaffreddamento / 100.0));
		double volumeMostoPreBoil = volumePostBoil * (1.0 + (percentualeEvaporazione / 100.0));
		double ogPreBoil = (batchSize * originalGravity) / volumeMostoPreBoil;
		double perditaContrazione = volumePostBoil * (contrazioneRaffreddamento / 100.0);
		double perditaEvaporazione = volumeMostoPreBoil * (percentualeEvaporazione / 100.0);
		
		double acquaMash = totGrani * rapportoAcquaGrani;
		double acquaTotale = volumeMostoPreBoil + perditeAssorbimento;
		double acquaSparge = acquaTotale - acquaMash;
		
		
		spinnerPerditaPerAssorbimento.setDoubleValue(perditeAssorbimento);
		spinnerPerditaPerEvaporazione.setDoubleValue(perditaEvaporazione);
		spinnerPerditaPerContrazione.setDoubleValue(perditaContrazione);
		
		spinnerVolumeMostoPreBoil.setDoubleValue(volumeMostoPreBoil);
		spinnerOGPreBoil.setDoubleValue(ogPreBoil);
		spinnerVolumePostBoil.setDoubleValue(volumePostBoil);
		spinnerVolumePostRaffreddamento.setDoubleValue(volumePostRaffreddamento);
		
		spinnerAcquaMash.setDoubleValue(acquaMash);
		spinnerAcquaSparge.setDoubleValue(acquaSparge);
		spinnerTotaleAcqua.setDoubleValue(acquaTotale);
		
		
		
	}

	public double getTotWater() {
		return spinnerTotaleAcqua.getDoubleValue();
	}

	public double getMashVolume() {
		return spinnerAcquaMash.getDoubleValue();
	}

	public double getSpargeVolume() {
		return spinnerAcquaSparge.getDoubleValue();
	}
	
	
	public void fromXml(Element E) {	
		
        if (E.getAttribute("spinnerBatchSize") != null)
            spinnerBatchSize.setDoubleValue(new Double(E.getAttribute("spinnerBatchSize").getValue()));
        if (E.getAttribute("spinnerGraniTotali") != null)
        	spinnerGraniTotali.setDoubleValue(new Double(E.getAttribute("spinnerGraniTotali").getValue()));
        if (E.getAttribute("spinnerOriginalGravity") != null)
        	spinnerOriginalGravity.setDoubleValue(new Double(E.getAttribute("spinnerOriginalGravity").getValue()));
        if (E.getAttribute("spinnerAssorbimentoGraniEsausti") != null)
        	spinnerAssorbimentoGraniEsausti.setDoubleValue(new Double(E.getAttribute("spinnerAssorbimentoGraniEsausti").getValue()));
        
        if (E.getAttribute("chckbxPerditeNelTrub") != null)
        	chckbxPerditeNelTrub.setSelected(new Boolean(E.getAttribute("chckbxPerditeNelTrub").getValue()));
        if (E.getAttribute("spinnerPerditeNelTrub") != null)
        	spinnerPerditeNelTrub.setDoubleValue(new Double(E.getAttribute("spinnerPerditeNelTrub").getValue()));
        if (E.getAttribute("spinnerRapportoAcquaGrani") != null)
        	spinnerRapportoAcquaGrani.setDoubleValue(new Double(E.getAttribute("spinnerRapportoAcquaGrani").getValue()));
        
        
        if (E.getAttribute("spinnerPercentualeEvaporazione") != null)
        	spinnerPercentualeEvaporazione.setDoubleValue(new Double(E.getAttribute("spinnerPercentualeEvaporazione").getValue()));
        if (E.getAttribute("spinnerContrazionePerRaffreddamento") != null)
        	spinnerContrazionePerRaffreddamento.setDoubleValue(new Double(E.getAttribute("spinnerContrazionePerRaffreddamento").getValue()));
        
        
        if (E.getAttribute("spinnerPerditaPerAssorbimento") != null)
        	spinnerPerditaPerAssorbimento.setDoubleValue(new Double(E.getAttribute("spinnerPerditaPerAssorbimento").getValue()));
        if (E.getAttribute("spinnerPerditaPerEvaporazione") != null)
        	spinnerPerditaPerEvaporazione.setDoubleValue(new Double(E.getAttribute("spinnerPerditaPerEvaporazione").getValue()));
        if (E.getAttribute("spinnerPerditaPerContrazione") != null)
        	spinnerPerditaPerContrazione.setDoubleValue(new Double(E.getAttribute("spinnerPerditaPerContrazione").getValue()));
        
        
        if (E.getAttribute("spinnerVolumeMostoPreBoil") != null)
        	spinnerVolumeMostoPreBoil.setDoubleValue(new Double(E.getAttribute("spinnerVolumeMostoPreBoil").getValue()));
        if (E.getAttribute("spinnerOGPreBoil") != null)
        	spinnerOGPreBoil.setDoubleValue(new Double(E.getAttribute("spinnerOGPreBoil").getValue()));
        if (E.getAttribute("spinnerVolumePostBoil") != null)
        	spinnerVolumePostBoil.setDoubleValue(new Double(E.getAttribute("spinnerVolumePostBoil").getValue()));
        if (E.getAttribute("spinnerVolumePostRaffreddamento") != null)
        	spinnerVolumePostRaffreddamento.setDoubleValue(new Double(E.getAttribute("spinnerVolumePostRaffreddamento").getValue()));
        
        
        
        if (E.getAttribute("spinnerAcquaMash") != null)
        	spinnerAcquaMash.setDoubleValue(new Double(E.getAttribute("spinnerAcquaMash").getValue()));
        if (E.getAttribute("spinnerAcquaSparge") != null)
        	spinnerAcquaSparge.setDoubleValue(new Double(E.getAttribute("spinnerAcquaSparge").getValue()));
        if (E.getAttribute("spinnerTotaleAcqua") != null)
        	spinnerTotaleAcqua.setDoubleValue(new Double(E.getAttribute("spinnerTotaleAcqua").getValue()));
        
      
      

    }

	public Element toXml() {
        Element E = new Element("water");

        
        E.setAttribute("spinnerBatchSize", "" + spinnerBatchSize.getDoubleValue());
        E.setAttribute("spinnerGraniTotali", "" + spinnerGraniTotali.getDoubleValue());
        E.setAttribute("spinnerOriginalGravity", "" + spinnerOriginalGravity.getDoubleValue());
        E.setAttribute("spinnerAssorbimentoGraniEsausti", "" + spinnerAssorbimentoGraniEsausti.getDoubleValue());
        
        E.setAttribute("chckbxPerditeNelTrub", "" + chckbxPerditeNelTrub.isSelected());
        E.setAttribute("spinnerPerditeNelTrub", "" + spinnerPerditeNelTrub.getDoubleValue());
        E.setAttribute("spinnerRapportoAcquaGrani", "" + spinnerRapportoAcquaGrani.getDoubleValue());
        
        E.setAttribute("spinnerPercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());
        E.setAttribute("spinnerContrazionePerRaffreddamento", "" + spinnerContrazionePerRaffreddamento.getDoubleValue());
        E.setAttribute("spinnerPercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());
        
        E.setAttribute("spinnerPerditaPerAssorbimento", "" + spinnerPerditaPerAssorbimento.getDoubleValue());
        E.setAttribute("spinnerPerditaPerEvaporazione", "" + spinnerPerditaPerEvaporazione.getDoubleValue());
        E.setAttribute("spinnerPerditaPerContrazione", "" + spinnerPerditaPerContrazione.getDoubleValue());
        
        
        E.setAttribute("spinnerVolumeMostoPreBoil", "" + spinnerVolumeMostoPreBoil.getDoubleValue());
        E.setAttribute("spinnerOGPreBoil", "" + spinnerOGPreBoil.getDoubleValue());
        E.setAttribute("spinnerVolumePostBoil", "" + spinnerVolumePostBoil.getDoubleValue());
        E.setAttribute("spinnerVolumePostRaffreddamento", "" + spinnerVolumePostRaffreddamento.getDoubleValue());
        
        E.setAttribute("spinnerAcquaMash", "" + spinnerAcquaMash.getDoubleValue());
        E.setAttribute("spinnerAcquaSparge", "" + spinnerAcquaSparge.getDoubleValue());
        E.setAttribute("spinnerTotaleAcqua", "" + spinnerTotaleAcqua.getDoubleValue());

        
        
        
        return E;
    }
	
	
	

}
