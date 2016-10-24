package jmash;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdom.Element;

import jmash.component.JUnitSpinner;

public class WaterNeeded extends JInternalFrame {
	private static final long serialVersionUID = -5301195065823912614L;

	private JPanel panelWaterNeeded;
	private JPanel panelSpecificheCotta;
	private JPanel panelDatiImpianto;
	private JPanel panelCalcoloPerdite;
	private JPanel panelCalcoloVolumi;
	private JPanel panelRisultati;
	private JLabel lblBatchSize;
	private JLabel lblGraniTotali;
	private JLabel lblOriginalGravity;
	private JLabel lblAssorbimentoGraniEsausti;
	private JLabel lblRapportoAcquaGrani;
	private JLabel lblPercentualeEvaporazione;
	private JLabel lblContrazionePerRaffreddamento;
	private JCheckBox chckbxPerditeNelTrub;
	private JUnitSpinner spinnerBatchSize;
	private JUnitSpinner spinnerGraniTotali;
	private JUnitSpinner spinnerOriginalGravity;
	private JUnitSpinner spinnerAssorbimentoGraniEsausti;
	private JUnitSpinner spinnerRapportoAcquaGrani;
	private JUnitSpinner spinnerPercentualeEvaporazione;
	private JUnitSpinner spinnerContrazionePerRaffreddamento;
	private JUnitSpinner spinnerPerditeNelTrub;
	private JLabel lblPerditaPerAssorbimento;
	private JLabel lblPerditaPerEvaporazione;
	private JLabel lblPerditaPerContrazione;
	private JLabel lblVolumeMostoPreboil;
	private JLabel lblOgPreboil;
	private JLabel lblVolumePostboil;
	private JLabel lblVolumePostraffreddamento;
	private JLabel lblAcquaDiMash;
	private JLabel lblAcquaDiSparge;
	private JUnitSpinner spinnerPerditaPerAssorbimento;
	private JUnitSpinner spinnerPerditaPerEvaporazione;
	private JUnitSpinner spinnerPerditaPerContrazione;
	private JUnitSpinner spinnerVolumeMostoPreBoil;
	private JUnitSpinner spinnerOGPreBoil;
	private JUnitSpinner spinnerVolumePostBoil;
	private JUnitSpinner spinnerVolumePostRaffreddamento;
	private JUnitSpinner spinnerAcquaMash;
	private JUnitSpinner spinnerAcquaSparge;
	private JUnitSpinner spinnerTotaleAcqua;
	private JLabel lblTotaleAcquaRichiesta;
	private JLabel lblVolumeRealeInFermentatore;
	private JUnitSpinner spinnerVolumeRealeInFermentatore;

	private Boolean biab = Boolean.FALSE;
	private GridBagConstraints gridBagConstraints_1;

	public WaterNeeded() {
		initComponents();
		setBorder(Utils.getDefaultBorder());

		// spinnerBackSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.BS");
		// spinnerTrub.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.Trub");

		spinnerBatchSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5, "0.00", "WaterNeeded.BS");
		spinnerGraniTotali.setModel(0.0, 0.0, 1000000.0, 0.5, "0.000", "WaterNeeded.TotGrani");
		spinnerOriginalGravity.setModel(0.0, 0.0, 1000000.0, 1, "0", "WaterNeeded.OG");
		
		spinnerAssorbimentoGraniEsausti.setModel(Main.config.getLitriPerKg(), 0, 1000000, 0.1, "0.00", null);
		spinnerRapportoAcquaGrani.setModel(Main.config.getRapportoAcquaGrani(), 0.0, 1000000, 0.1, "0.00", null);
		spinnerPercentualeEvaporazione.setModel(Main.config.getPercentualeEvaporazione(), 0.0, 100, 0.25, "0.00", null);
		spinnerContrazionePerRaffreddamento.setModel(Main.config.getContrazionePerRaffreddamento(), 0, 100, 0.25, "0.00", null);
		spinnerPerditeNelTrub.setModel(Main.config.getLostToTrub(), 0.0, 1000000, 0.1, "0.00", null);

		spinnerPerditaPerAssorbimento.setModel(Main.getFromCache("WaterNeeded.perditaPerAssorbimento", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdAss");
		spinnerPerditaPerEvaporazione.setModel(Main.getFromCache("WaterNeeded.perditaPerEvaporazione", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdEvap");
		spinnerPerditaPerContrazione.setModel(Main.getFromCache("WaterNeeded.perditaPerContrazione", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PerdContraz");

		spinnerVolumeMostoPreBoil.setModel(Main.getFromCache("WaterNeeded.PB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PB");
		spinnerOGPreBoil.setModel(Main.getFromCache("WaterNeeded.pOG", 0.0), 0, 1000000, 0.5, "0", "WaterNeeded.pOG");
		spinnerVolumePostBoil.setModel(Main.getFromCache("WaterNeeded.PostB", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.PostB");
		spinnerVolumePostRaffreddamento.setModel(Main.getFromCache("WaterNeeded.volumePostRaffreddamento", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumePostRaffreddamento");
		spinnerVolumeRealeInFermentatore.setModel(Main.getFromCache("WaterNeeded.volumeRealeInFermentatore", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeRealeInFermentatore");

		spinnerAcquaMash.setModel(Main.getFromCache("WaterNeeded.volumeMash", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeMash");
		spinnerAcquaSparge.setModel(Main.getFromCache("WaterNeeded.volumeSparge", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeSparge");
		spinnerTotaleAcqua.setModel(Main.getFromCache("WaterNeeded.volumeTotale", 0.0), 0, 1000000, 0.5, "0.00", "WaterNeeded.volumeTotale");

		setBiab(Main.config.getBiab(), false);
		setBackground(getBackground().darker());
		panelWaterNeeded.setBackground(panelWaterNeeded.getBackground().darker());

		calcolaQuantitaAcqua();

	}

	public WaterNeeded(double batchSize, double kg, double boilTime, double evap) {
		this();
		spinnerBatchSize.setDoubleValue(batchSize);
		spinnerGraniTotali.setDoubleValue(kg);

		// spnBoiltime.setDoubleValue(boilTime);
		// spnEvaporation.setVolume(evap);

		calcolaQuantitaAcqua();
	}

	private void initComponents() {

		panelWaterNeeded = new JPanel(new GridBagLayout());

		setClosable(true);
		setIconifiable(true);
		setTitle("Acqua necessaria");

		initWaterNeededPanel();

		getContentPane().add(panelWaterNeeded, java.awt.BorderLayout.CENTER);

	}

	private void initWaterNeededPanel() {

		initPanelSpecificheCotta();
		initPanelDatiImpianto();

		initPanelCalcoloPerdite();
		initPanelCalcoloVolumi();
		initPanelRisultati();

	}

	private void initPanelSpecificheCotta() {

		GridBagConstraints gridBagConstraints;

		panelSpecificheCotta = new JPanel();
		panelSpecificheCotta.setBorder(javax.swing.BorderFactory.createTitledBorder("Specifiche della cotta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 165, 78, 0, 0 };
		;
		panelSpecificheCotta.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelSpecificheCotta, gridBagConstraints);
		{
			lblBatchSize = new JLabel("Batch size");
			GridBagConstraints gbc_lblBatchSize = new GridBagConstraints();
			gbc_lblBatchSize.anchor = GridBagConstraints.EAST;
			gbc_lblBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblBatchSize.gridx = 0;
			gbc_lblBatchSize.gridy = 0;
			panelSpecificheCotta.add(lblBatchSize, gbc_lblBatchSize);
		}
		{
			spinnerBatchSize = new JUnitSpinner("L", 57);
			spinnerBatchSize.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerBatchSize.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerBatchSize.setEnabled(false);
			GridBagConstraints gbc_spinnerBatchSize = new GridBagConstraints();
			gbc_spinnerBatchSize.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerBatchSize.gridx = 1;
			gbc_spinnerBatchSize.gridy = 0;
			panelSpecificheCotta.add(spinnerBatchSize, gbc_spinnerBatchSize);
		}
		{
			lblGraniTotali = new JLabel("Grani totali");
			GridBagConstraints gbc_lblGraniTotali = new GridBagConstraints();
			gbc_lblGraniTotali.anchor = GridBagConstraints.EAST;
			gbc_lblGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_lblGraniTotali.gridx = 0;
			gbc_lblGraniTotali.gridy = 1;
			panelSpecificheCotta.add(lblGraniTotali, gbc_lblGraniTotali);
		}
		{
			spinnerGraniTotali = new JUnitSpinner("Kg", 57);
			spinnerGraniTotali.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerGraniTotali.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerGraniTotali.setEnabled(false);
			GridBagConstraints gbc_spinnerGraniTotali = new GridBagConstraints();
			gbc_spinnerGraniTotali.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerGraniTotali.gridx = 1;
			gbc_spinnerGraniTotali.gridy = 1;
			panelSpecificheCotta.add(spinnerGraniTotali, gbc_spinnerGraniTotali);
		}
		{
			lblOriginalGravity = new JLabel("Original Gravity");
			GridBagConstraints gbc_lblOriginalGravity = new GridBagConstraints();
			gbc_lblOriginalGravity.anchor = GridBagConstraints.EAST;
			gbc_lblOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_lblOriginalGravity.gridx = 0;
			gbc_lblOriginalGravity.gridy = 2;
			panelSpecificheCotta.add(lblOriginalGravity, gbc_lblOriginalGravity);
		}
		{
			spinnerOriginalGravity = new JUnitSpinner("SG", 57);
			spinnerOriginalGravity.getSpinner().setPreferredSize(new Dimension(148, 22));
			spinnerOriginalGravity.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerOriginalGravity.setEnabled(false);
			GridBagConstraints gbc_spinnerOriginalGravity = new GridBagConstraints();
			gbc_spinnerOriginalGravity.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerOriginalGravity.gridx = 1;
			gbc_spinnerOriginalGravity.gridy = 2;
			panelSpecificheCotta.add(spinnerOriginalGravity, gbc_spinnerOriginalGravity);
		}

	}

	private void initPanelDatiImpianto() {

		panelDatiImpianto = new JPanel();
		panelDatiImpianto.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] {165, 78, 0, 0};
		panelDatiImpianto.setLayout(gridBagLayout);
		
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridheight = 2;
		gridBagConstraints_1.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelDatiImpianto, gridBagConstraints_1);
		{
			lblAssorbimentoGraniEsausti = new JLabel("Assorbimento grani esausti");
			GridBagConstraints gbc_lblAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_lblAssorbimentoGraniEsausti.anchor = GridBagConstraints.EAST;
			gbc_lblAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_lblAssorbimentoGraniEsausti.gridx = 0;
			gbc_lblAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpianto.add(lblAssorbimentoGraniEsausti, gbc_lblAssorbimentoGraniEsausti);
		}
		{
			spinnerAssorbimentoGraniEsausti = new JUnitSpinner("L/Kg", 57);
			spinnerAssorbimentoGraniEsausti.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerAssorbimentoGraniEsausti.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAssorbimentoGraniEsausti = new GridBagConstraints();
			gbc_spinnerAssorbimentoGraniEsausti.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAssorbimentoGraniEsausti.gridx = 1;
			gbc_spinnerAssorbimentoGraniEsausti.gridy = 0;
			panelDatiImpianto.add(spinnerAssorbimentoGraniEsausti, gbc_spinnerAssorbimentoGraniEsausti);
		}
		{
			lblRapportoAcquaGrani = new JLabel("Rapporto acqua/grani");
			GridBagConstraints gbc_lblRapportoAcquaGrani = new GridBagConstraints();
			gbc_lblRapportoAcquaGrani.anchor = GridBagConstraints.EAST;
			gbc_lblRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_lblRapportoAcquaGrani.gridx = 0;
			gbc_lblRapportoAcquaGrani.gridy = 1;
			panelDatiImpianto.add(lblRapportoAcquaGrani, gbc_lblRapportoAcquaGrani);
		}
		{
			spinnerRapportoAcquaGrani = new JUnitSpinner("L/Kg", 57);
			spinnerRapportoAcquaGrani.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerRapportoAcquaGrani.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerRapportoAcquaGrani = new GridBagConstraints();
			gbc_spinnerRapportoAcquaGrani.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerRapportoAcquaGrani.gridx = 1;
			gbc_spinnerRapportoAcquaGrani.gridy = 1;
			panelDatiImpianto.add(spinnerRapportoAcquaGrani, gbc_spinnerRapportoAcquaGrani);
		}
		{
			lblPercentualeEvaporazione = new JLabel("Percentuale evaporazione");
			GridBagConstraints gbc_lblPercentualeEvaporazione = new GridBagConstraints();
			gbc_lblPercentualeEvaporazione.anchor = GridBagConstraints.EAST;
			gbc_lblPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPercentualeEvaporazione.gridx = 0;
			gbc_lblPercentualeEvaporazione.gridy = 2;
			panelDatiImpianto.add(lblPercentualeEvaporazione, gbc_lblPercentualeEvaporazione);
		}
		{
			spinnerPercentualeEvaporazione = new JUnitSpinner("%", 57);
			spinnerPercentualeEvaporazione.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPercentualeEvaporazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPercentualeEvaporazione = new GridBagConstraints();
			gbc_spinnerPercentualeEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPercentualeEvaporazione.gridx = 1;
			gbc_spinnerPercentualeEvaporazione.gridy = 2;
			panelDatiImpianto.add(spinnerPercentualeEvaporazione, gbc_spinnerPercentualeEvaporazione);
		}
		{
			lblContrazionePerRaffreddamento = new JLabel("Contrazione per raffreddamento");
			GridBagConstraints gbc_lblContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_lblContrazionePerRaffreddamento.anchor = GridBagConstraints.EAST;
			gbc_lblContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblContrazionePerRaffreddamento.gridx = 0;
			gbc_lblContrazionePerRaffreddamento.gridy = 3;
			panelDatiImpianto.add(lblContrazionePerRaffreddamento, gbc_lblContrazionePerRaffreddamento);
		}
		{
			spinnerContrazionePerRaffreddamento = new JUnitSpinner("%", 57);
			spinnerContrazionePerRaffreddamento.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerContrazionePerRaffreddamento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerContrazionePerRaffreddamento = new GridBagConstraints();
			gbc_spinnerContrazionePerRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerContrazionePerRaffreddamento.gridx = 1;
			gbc_spinnerContrazionePerRaffreddamento.gridy = 3;
			panelDatiImpianto.add(spinnerContrazionePerRaffreddamento, gbc_spinnerContrazionePerRaffreddamento);
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
			gbc_chckbxPerditeNelTrub.anchor = GridBagConstraints.EAST;
			gbc_chckbxPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxPerditeNelTrub.gridx = 0;
			gbc_chckbxPerditeNelTrub.gridy = 4;
			panelDatiImpianto.add(chckbxPerditeNelTrub, gbc_chckbxPerditeNelTrub);
		}
		{
			spinnerPerditeNelTrub = new JUnitSpinner("L", 57);
			spinnerPerditeNelTrub.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					calcolaQuantitaAcqua();
				}
			});
			spinnerPerditeNelTrub.setEnabled(false);
			spinnerPerditeNelTrub.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditeNelTrub = new GridBagConstraints();
			gbc_spinnerPerditeNelTrub.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditeNelTrub.gridx = 1;
			gbc_spinnerPerditeNelTrub.gridy = 4;
			panelDatiImpianto.add(spinnerPerditeNelTrub, gbc_spinnerPerditeNelTrub);
		}

	}

	private void initPanelCalcoloPerdite() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloPerdite = new JPanel();
		panelCalcoloPerdite.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo delle perdite"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloPerdite.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelCalcoloPerdite, gridBagConstraints);
		{
			lblPerditaPerAssorbimento = new JLabel("Perdita per assorbimento");
			GridBagConstraints gbc_lblPerditaPerAssorbimento = new GridBagConstraints();
			gbc_lblPerditaPerAssorbimento.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerAssorbimento.gridx = 0;
			gbc_lblPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerdite.add(lblPerditaPerAssorbimento, gbc_lblPerditaPerAssorbimento);
		}
		{
			spinnerPerditaPerAssorbimento = new JUnitSpinner("L", 57);
			spinnerPerditaPerAssorbimento.setEnabled(false);
			spinnerPerditaPerAssorbimento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerAssorbimento = new GridBagConstraints();
			gbc_spinnerPerditaPerAssorbimento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerAssorbimento.gridx = 1;
			gbc_spinnerPerditaPerAssorbimento.gridy = 0;
			panelCalcoloPerdite.add(spinnerPerditaPerAssorbimento, gbc_spinnerPerditaPerAssorbimento);
		}
		{
			lblPerditaPerEvaporazione = new JLabel("Perdita per evaporazione");
			GridBagConstraints gbc_lblPerditaPerEvaporazione = new GridBagConstraints();
			gbc_lblPerditaPerEvaporazione.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_lblPerditaPerEvaporazione.gridx = 0;
			gbc_lblPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerdite.add(lblPerditaPerEvaporazione, gbc_lblPerditaPerEvaporazione);
		}
		{
			spinnerPerditaPerEvaporazione = new JUnitSpinner("L", 57);;
			spinnerPerditaPerEvaporazione.setEnabled(false);
			spinnerPerditaPerEvaporazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerEvaporazione = new GridBagConstraints();
			gbc_spinnerPerditaPerEvaporazione.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerPerditaPerEvaporazione.gridx = 1;
			gbc_spinnerPerditaPerEvaporazione.gridy = 1;
			panelCalcoloPerdite.add(spinnerPerditaPerEvaporazione, gbc_spinnerPerditaPerEvaporazione);
		}
		{
			lblPerditaPerContrazione = new JLabel("Perdita per contrazione");
			GridBagConstraints gbc_lblPerditaPerContrazione = new GridBagConstraints();
			gbc_lblPerditaPerContrazione.anchor = GridBagConstraints.EAST;
			gbc_lblPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_lblPerditaPerContrazione.gridx = 0;
			gbc_lblPerditaPerContrazione.gridy = 2;
			panelCalcoloPerdite.add(lblPerditaPerContrazione, gbc_lblPerditaPerContrazione);
		}
		{
			spinnerPerditaPerContrazione = new JUnitSpinner("L", 57);
			spinnerPerditaPerContrazione.setEnabled(false);
			spinnerPerditaPerContrazione.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerPerditaPerContrazione = new GridBagConstraints();
			gbc_spinnerPerditaPerContrazione.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerPerditaPerContrazione.gridx = 1;
			gbc_spinnerPerditaPerContrazione.gridy = 2;
			panelCalcoloPerdite.add(spinnerPerditaPerContrazione, gbc_spinnerPerditaPerContrazione);
		}

	}

	private void initPanelCalcoloVolumi() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloVolumi = new JPanel();
		panelCalcoloVolumi.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo dei volumi"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelCalcoloVolumi.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelCalcoloVolumi, gridBagConstraints);
		{
			lblVolumeMostoPreboil = new JLabel("Volume mosto pre-boil");
			GridBagConstraints gbc_lblVolumeMostoPreboil = new GridBagConstraints();
			gbc_lblVolumeMostoPreboil.anchor = GridBagConstraints.EAST;
			gbc_lblVolumeMostoPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumeMostoPreboil.gridx = 0;
			gbc_lblVolumeMostoPreboil.gridy = 0;
			panelCalcoloVolumi.add(lblVolumeMostoPreboil, gbc_lblVolumeMostoPreboil);
		}
		{
			spinnerVolumeMostoPreBoil = new JUnitSpinner("L", 57);
			spinnerVolumeMostoPreBoil.setEnabled(false);
			spinnerVolumeMostoPreBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumeMostoPreBoil = new GridBagConstraints();
			gbc_spinnerVolumeMostoPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumeMostoPreBoil.gridx = 1;
			gbc_spinnerVolumeMostoPreBoil.gridy = 0;
			panelCalcoloVolumi.add(spinnerVolumeMostoPreBoil, gbc_spinnerVolumeMostoPreBoil);
		}
		{
			lblOgPreboil = new JLabel("OG pre-boil");
			GridBagConstraints gbc_lblOgPreboil = new GridBagConstraints();
			gbc_lblOgPreboil.anchor = GridBagConstraints.EAST;
			gbc_lblOgPreboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblOgPreboil.gridx = 0;
			gbc_lblOgPreboil.gridy = 1;
			panelCalcoloVolumi.add(lblOgPreboil, gbc_lblOgPreboil);
		}
		{
			spinnerOGPreBoil = new JUnitSpinner("SG", 57);
			spinnerOGPreBoil.setEnabled(false);
			spinnerOGPreBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerOGPreBoil = new GridBagConstraints();
			gbc_spinnerOGPreBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerOGPreBoil.gridx = 1;
			gbc_spinnerOGPreBoil.gridy = 1;
			panelCalcoloVolumi.add(spinnerOGPreBoil, gbc_spinnerOGPreBoil);
		}
		{
			lblVolumePostboil = new JLabel("Volume post-boil");
			GridBagConstraints gbc_lblVolumePostboil = new GridBagConstraints();
			gbc_lblVolumePostboil.anchor = GridBagConstraints.EAST;
			gbc_lblVolumePostboil.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumePostboil.gridx = 0;
			gbc_lblVolumePostboil.gridy = 2;
			panelCalcoloVolumi.add(lblVolumePostboil, gbc_lblVolumePostboil);
		}
		{
			spinnerVolumePostBoil = new JUnitSpinner("L", 57);
			spinnerVolumePostBoil.setEnabled(false);
			spinnerVolumePostBoil.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumePostBoil = new GridBagConstraints();
			gbc_spinnerVolumePostBoil.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumePostBoil.gridx = 1;
			gbc_spinnerVolumePostBoil.gridy = 2;
			panelCalcoloVolumi.add(spinnerVolumePostBoil, gbc_spinnerVolumePostBoil);
		}
		{
			lblVolumePostraffreddamento = new JLabel("Volume post-raffreddamento");
			GridBagConstraints gbc_lblVolumePostraffreddamento = new GridBagConstraints();
			gbc_lblVolumePostraffreddamento.anchor = GridBagConstraints.EAST;
			gbc_lblVolumePostraffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumePostraffreddamento.gridx = 0;
			gbc_lblVolumePostraffreddamento.gridy = 3;
			panelCalcoloVolumi.add(lblVolumePostraffreddamento, gbc_lblVolumePostraffreddamento);
		}
		{
			spinnerVolumePostRaffreddamento = new JUnitSpinner("L", 57);
			spinnerVolumePostRaffreddamento.setEnabled(false);
			spinnerVolumePostRaffreddamento.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerVolumePostRaffreddamento = new GridBagConstraints();
			gbc_spinnerVolumePostRaffreddamento.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerVolumePostRaffreddamento.gridx = 1;
			gbc_spinnerVolumePostRaffreddamento.gridy = 3;
			panelCalcoloVolumi.add(spinnerVolumePostRaffreddamento, gbc_spinnerVolumePostRaffreddamento);
		}
		{
			lblVolumeRealeInFermentatore = new JLabel("Volume reale in fermentatore");
			GridBagConstraints gbc_lblVolumeRealeInFermentatore = new GridBagConstraints();
			gbc_lblVolumeRealeInFermentatore.anchor = GridBagConstraints.EAST;
			gbc_lblVolumeRealeInFermentatore.insets = new Insets(0, 0, 0, 5);
			gbc_lblVolumeRealeInFermentatore.gridx = 0;
			gbc_lblVolumeRealeInFermentatore.gridy = 4;
			panelCalcoloVolumi.add(lblVolumeRealeInFermentatore, gbc_lblVolumeRealeInFermentatore);
		}
		{
			spinnerVolumeRealeInFermentatore = new JUnitSpinner("L", 57);
			spinnerVolumeRealeInFermentatore.setPreferredSize(new Dimension(148, 22));
			spinnerVolumeRealeInFermentatore.setEnabled(false);
			GridBagConstraints gbc_spinnerVolumeRealeInFermentatore = new GridBagConstraints();
			gbc_spinnerVolumeRealeInFermentatore.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerVolumeRealeInFermentatore.gridx = 1;
			gbc_spinnerVolumeRealeInFermentatore.gridy = 4;
			panelCalcoloVolumi.add(spinnerVolumeRealeInFermentatore, gbc_spinnerVolumeRealeInFermentatore);
		}

	}

	private void initPanelRisultati() {

		GridBagConstraints gridBagConstraints;

		panelRisultati = new JPanel();
		panelRisultati.setBorder(javax.swing.BorderFactory.createTitledBorder("Totale acqua richiesta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };
		;
		panelRisultati.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);

		panelWaterNeeded.add(panelRisultati, gridBagConstraints);
		{
			lblAcquaDiMash = new JLabel("Acqua di mash");
			GridBagConstraints gbc_lblAcquaDiMash = new GridBagConstraints();
			gbc_lblAcquaDiMash.anchor = GridBagConstraints.EAST;
			gbc_lblAcquaDiMash.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiMash.gridx = 0;
			gbc_lblAcquaDiMash.gridy = 0;
			panelRisultati.add(lblAcquaDiMash, gbc_lblAcquaDiMash);
		}
		{
			spinnerAcquaMash = new JUnitSpinner("L", 57);
			spinnerAcquaMash.setEnabled(false);
			spinnerAcquaMash.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAcquaMash = new GridBagConstraints();
			gbc_spinnerAcquaMash.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaMash.gridx = 1;
			gbc_spinnerAcquaMash.gridy = 0;
			panelRisultati.add(spinnerAcquaMash, gbc_spinnerAcquaMash);
		}
		{
			lblAcquaDiSparge = new JLabel("Acqua di sparge");
			GridBagConstraints gbc_lblAcquaDiSparge_1 = new GridBagConstraints();
			gbc_lblAcquaDiSparge_1.anchor = GridBagConstraints.EAST;
			gbc_lblAcquaDiSparge_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblAcquaDiSparge_1.gridx = 0;
			gbc_lblAcquaDiSparge_1.gridy = 1;
			panelRisultati.add(lblAcquaDiSparge, gbc_lblAcquaDiSparge_1);
		}
		{
			spinnerAcquaSparge = new JUnitSpinner("L", 57);
			spinnerAcquaSparge.setEnabled(false);
			spinnerAcquaSparge.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerAcquaDiSparge = new GridBagConstraints();
			gbc_spinnerAcquaDiSparge.anchor = GridBagConstraints.ABOVE_BASELINE;
			gbc_spinnerAcquaDiSparge.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerAcquaDiSparge.gridx = 1;
			gbc_spinnerAcquaDiSparge.gridy = 1;
			panelRisultati.add(spinnerAcquaSparge, gbc_spinnerAcquaDiSparge);
		}
		{
			lblTotaleAcquaRichiesta = new JLabel("Totale acqua richiesta");
			GridBagConstraints gbc_lblTotaleAcquaRichiesta = new GridBagConstraints();
			gbc_lblTotaleAcquaRichiesta.anchor = GridBagConstraints.EAST;
			gbc_lblTotaleAcquaRichiesta.insets = new Insets(0, 0, 0, 5);
			gbc_lblTotaleAcquaRichiesta.gridx = 0;
			gbc_lblTotaleAcquaRichiesta.gridy = 2;
			panelRisultati.add(lblTotaleAcquaRichiesta, gbc_lblTotaleAcquaRichiesta);
		}
		{
			spinnerTotaleAcqua = new JUnitSpinner("L", 57);
			spinnerTotaleAcqua.setEnabled(false);
			spinnerTotaleAcqua.setPreferredSize(new Dimension(148, 22));
			GridBagConstraints gbc_spinnerTotaleAcqua = new GridBagConstraints();
			gbc_spinnerTotaleAcqua.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerTotaleAcqua.gridx = 1;
			gbc_spinnerTotaleAcqua.gridy = 2;
			panelRisultati.add(spinnerTotaleAcqua, gbc_spinnerTotaleAcqua);
		}

	}

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
		calcolaQuantitaAcqua(false);
	}
	
	public void calcolaQuantitaAcqua(boolean fireEvent) {

		// dati in input
		double batchSize = this.spinnerBatchSize.getDoubleValue();
		double totGrani = this.spinnerGraniTotali.getDoubleValue();
		double originalGravity = this.spinnerOriginalGravity.getDoubleValue();
		double assorbimentoGraniEsausti = this.spinnerAssorbimentoGraniEsausti.getDoubleValue();
		double rapportoAcquaGrani = this.spinnerRapportoAcquaGrani.getDoubleValue();
		double percentualeEvaporazione = this.spinnerPercentualeEvaporazione.getDoubleValue();
		double contrazioneRaffreddamento = this.spinnerContrazionePerRaffreddamento.getDoubleValue();
		double perditeNelTrub = this.chckbxPerditeNelTrub.isSelected() ? this.spinnerPerditeNelTrub.getDoubleValue() : 0.0;
		this.spinnerPerditeNelTrub.setEnabled(this.chckbxPerditeNelTrub.isSelected());

		// inizio calcoli
		double perditeAssorbimento = totGrani * assorbimentoGraniEsausti;
		double volumePostRaffreddamento = batchSize;// batchSize +
													// perditeNelTrub;
		double volumeRealeInFermentaore = volumePostRaffreddamento - perditeNelTrub;
		double volumePostBoil = volumePostRaffreddamento * (1.0 + (contrazioneRaffreddamento / 100.0));
		double volumeMostoPreBoil = volumePostBoil * (1.0 + (percentualeEvaporazione / 100.0));
		double ogPreBoil = (batchSize * originalGravity) / volumeMostoPreBoil;
		double perditaContrazione = volumePostBoil * (contrazioneRaffreddamento / 100.0);
		double perditaEvaporazione = volumeMostoPreBoil * (percentualeEvaporazione / 100.0);

		double acquaTotale = volumeMostoPreBoil + perditeAssorbimento;
		double acquaMash = !biab ? totGrani * rapportoAcquaGrani : acquaTotale;
		double acquaSparge = acquaTotale - acquaMash;

		spinnerPerditaPerAssorbimento.setDoubleValue(perditeAssorbimento);
		spinnerPerditaPerEvaporazione.setDoubleValue(perditaEvaporazione);
		spinnerPerditaPerContrazione.setDoubleValue(perditaContrazione);

		spinnerVolumeMostoPreBoil.setDoubleValue(volumeMostoPreBoil);
		spinnerOGPreBoil.setDoubleValue(ogPreBoil);
		spinnerVolumePostBoil.setDoubleValue(volumePostBoil);
		spinnerVolumePostRaffreddamento.setDoubleValue(volumePostRaffreddamento);
		spinnerVolumeRealeInFermentatore.setDoubleValue(volumeRealeInFermentaore);

		spinnerAcquaMash.setDoubleValue(acquaMash);
		spinnerAcquaSparge.setDoubleValue(acquaSparge);
		spinnerTotaleAcqua.setDoubleValue(acquaTotale);
		
		if (fireEvent)
		{
			fireStateChanged(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
		}
	}

	public void fromXml(Element E) {

		if (E.getAttribute("BatchSize") != null)
			spinnerBatchSize.setDoubleValue(new Double(E.getAttribute("BatchSize").getValue()));
		if (E.getAttribute("GraniTotali") != null)
			spinnerGraniTotali.setDoubleValue(new Double(E.getAttribute("GraniTotali").getValue()));
		if (E.getAttribute("OriginalGravity") != null)
			spinnerOriginalGravity.setDoubleValue(new Double(E.getAttribute("OriginalGravity").getValue()));
		if (E.getAttribute("AssorbimentoGraniEsausti") != null)
			spinnerAssorbimentoGraniEsausti
					.setDoubleValue(new Double(E.getAttribute("AssorbimentoGraniEsausti").getValue()));

		if (E.getAttribute("HasPerditeNelTrub") != null)
			chckbxPerditeNelTrub.setSelected(new Boolean(E.getAttribute("HasPerditeNelTrub").getValue()));
		if (E.getAttribute("PerditeNelTrub") != null)
			spinnerPerditeNelTrub.setDoubleValue(new Double(E.getAttribute("PerditeNelTrub").getValue()));
		if (E.getAttribute("RapportoAcquaGrani") != null)
			spinnerRapportoAcquaGrani
					.setDoubleValue(new Double(E.getAttribute("RapportoAcquaGrani").getValue()));

		if (E.getAttribute("PercentualeEvaporazione") != null)
			spinnerPercentualeEvaporazione
					.setDoubleValue(new Double(E.getAttribute("PercentualeEvaporazione").getValue()));
		if (E.getAttribute("ContrazionePerRaffreddamento") != null)
			spinnerContrazionePerRaffreddamento
					.setDoubleValue(new Double(E.getAttribute("ContrazionePerRaffreddamento").getValue()));

		if (E.getAttribute("PerditaPerAssorbimento") != null)
			spinnerPerditaPerAssorbimento
					.setDoubleValue(new Double(E.getAttribute("PerditaPerAssorbimento").getValue()));
		if (E.getAttribute("PerditaPerEvaporazione") != null)
			spinnerPerditaPerEvaporazione
					.setDoubleValue(new Double(E.getAttribute("PerditaPerEvaporazione").getValue()));
		if (E.getAttribute("PerditaPerContrazione") != null)
			spinnerPerditaPerContrazione
					.setDoubleValue(new Double(E.getAttribute("PerditaPerContrazione").getValue()));

		if (E.getAttribute("VolumeMostoPreBoil") != null)
			spinnerVolumeMostoPreBoil
					.setDoubleValue(new Double(E.getAttribute("VolumeMostoPreBoil").getValue()));
		if (E.getAttribute("OGPreBoil") != null)
			spinnerOGPreBoil.setDoubleValue(new Double(E.getAttribute("OGPreBoil").getValue()));
		if (E.getAttribute("VolumePostBoil") != null)
			spinnerVolumePostBoil.setDoubleValue(new Double(E.getAttribute("VolumePostBoil").getValue()));
		if (E.getAttribute("VolumePostRaffreddamento") != null)
			spinnerVolumePostRaffreddamento
					.setDoubleValue(new Double(E.getAttribute("VolumePostRaffreddamento").getValue()));
		if (E.getAttribute("VolumeRealeInFermentatore") != null)
			spinnerVolumeRealeInFermentatore
					.setDoubleValue(new Double(E.getAttribute("VolumeRealeInFermentatore").getValue()));

		if (E.getAttribute("AcquaMash") != null)
			spinnerAcquaMash.setDoubleValue(new Double(E.getAttribute("AcquaMash").getValue()));
		if (E.getAttribute("AcquaSparge") != null)
			spinnerAcquaSparge.setDoubleValue(new Double(E.getAttribute("AcquaSparge").getValue()));
		if (E.getAttribute("TotaleAcqua") != null)
			spinnerTotaleAcqua.setDoubleValue(new Double(E.getAttribute("TotaleAcqua").getValue()));

	}

	public Element toXml() {
		Element E = new Element("water");

		E.setAttribute("BatchSize", "" + spinnerBatchSize.getDoubleValue());
		E.setAttribute("GraniTotali", "" + spinnerGraniTotali.getDoubleValue());
		E.setAttribute("OriginalGravity", "" + spinnerOriginalGravity.getDoubleValue());
		E.setAttribute("AssorbimentoGraniEsausti", "" + spinnerAssorbimentoGraniEsausti.getDoubleValue());

		E.setAttribute("HasPerditeNelTrub", "" + chckbxPerditeNelTrub.isSelected());
		E.setAttribute("PerditeNelTrub", "" + spinnerPerditeNelTrub.getDoubleValue());
		E.setAttribute("RapportoAcquaGrani", "" + spinnerRapportoAcquaGrani.getDoubleValue());

		E.setAttribute("PercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());
		E.setAttribute("ContrazionePerRaffreddamento",
				"" + spinnerContrazionePerRaffreddamento.getDoubleValue());
		E.setAttribute("PercentualeEvaporazione", "" + spinnerPercentualeEvaporazione.getDoubleValue());

		E.setAttribute("PerditaPerAssorbimento", "" + spinnerPerditaPerAssorbimento.getDoubleValue());
		E.setAttribute("PerditaPerEvaporazione", "" + spinnerPerditaPerEvaporazione.getDoubleValue());
		E.setAttribute("PerditaPerContrazione", "" + spinnerPerditaPerContrazione.getDoubleValue());

		E.setAttribute("VolumeMostoPreBoil", "" + spinnerVolumeMostoPreBoil.getDoubleValue());
		E.setAttribute("OGPreBoil", "" + spinnerOGPreBoil.getDoubleValue());
		E.setAttribute("VolumePostBoil", "" + spinnerVolumePostBoil.getDoubleValue());
		E.setAttribute("VolumePostRaffreddamento", "" + spinnerVolumePostRaffreddamento.getDoubleValue());
		E.setAttribute("VolumeRealeInFermentatore", "" + spinnerVolumeRealeInFermentatore.getDoubleValue());

		E.setAttribute("AcquaMash", "" + spinnerAcquaMash.getDoubleValue());
		E.setAttribute("AcquaSparge", "" + spinnerAcquaSparge.getDoubleValue());
		E.setAttribute("TotaleAcqua", "" + spinnerTotaleAcqua.getDoubleValue());

		return E;
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

	public double getOGPreBoil() {
		return spinnerOGPreBoil.getDoubleValue();
	}

	public double getOriginalGravity() {
		return spinnerOriginalGravity.getDoubleValue();
	}

	public double getVolumeMostoPreBoil() {
		return spinnerVolumeMostoPreBoil.getDoubleValue();
	}

	public double getVolumePostBoil() {
		return spinnerVolumePostBoil.getDoubleValue();
	}

	public double getVolumePostRaffreddamento() {
		return spinnerVolumePostRaffreddamento.getDoubleValue();
	}

	public double getVolumeRealeInFermentatore() {
		return spinnerVolumeRealeInFermentatore.getDoubleValue();
	}

	public Boolean getBiab() {
		return biab;
	}

	public void setBiab(Boolean biab) {
		setBiab(biab, true);
	}
	
	public void setBiab(Boolean biab, boolean fireEvent) {
		this.biab = biab;
		
		lblRapportoAcquaGrani.setVisible(!biab);
		spinnerRapportoAcquaGrani.setVisible(!biab);
		
		lblAcquaDiMash.setVisible(!biab);
		spinnerAcquaMash.setVisible(!biab);

		lblAcquaDiSparge.setVisible(!biab);
		spinnerAcquaSparge.setVisible(!biab);
		
		calcolaQuantitaAcqua(fireEvent);
		
	}

}
