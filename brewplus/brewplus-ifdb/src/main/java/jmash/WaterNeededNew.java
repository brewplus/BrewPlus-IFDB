package jmash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import jmash.component.JVolumeSpinner;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaterNeededNew extends JInternalFrame {

	private static final long serialVersionUID = -5301195065823912614L;
	private static Logger LOGGER = Logger.getLogger(WaterNeededNew.class);

	private JTabbedPane tabbedPane;
	private JPanel panelSparge;
	private JPanel panelBatchSparge;
	private JPanel panelNoSparge;
	private JPanel panelSpecificheCottaSparge;
	private JPanel panelDatiImpiantoSparge;
	private JPanel panelCalcoloPerditeSparge;
	private JPanel panelCalcoloVolumiSparge;
//	private JPanel panelRabbocchiSparge;
	private JPanel panelRisultatiSparge;

	private JTextField textFieldGraniTotali;
	private JTextField textFieldOriginalGravity;
	private JTextField textFieldAssorbimentoGraniEsausti;
	private JTextField textFieldRapportoAcquaGrani;
	private JTextField textFieldPercentualeEvaporazione;
	private JTextField textFieldContrazioneRaffreddamento;
	private JTextField textFieldPerditaAssorbimento;
	private JTextField textFieldPerditaEvaporazione;
	private JTextField textFieldPerditaContrazione;
	
	private JTextField textFieldVolumeMostoPreBoil;
	private JTextField textFieldOGPreBoil;
	private JTextField textFieldVolumePostBoil;
	private JTextField textField_12;

	private JTextField textFieldAcquaMash;
	private JTextField textFieldAcquaSparge;
	private JCheckBox chckbxPerditeNelTrub;
	private JVolumeSpinner spinnerBackSize;
	private JSpinner spinnerTrub;
	private GridBagConstraints gridBagConstraints_1;
	private JLabel lblBatchSize;
	private GridBagConstraints gridBagConstraints_2;
	private GridBagConstraints gridBagConstraints_3;
	private JLabel lblKg;
	private GridBagConstraints gridBagConstraints_4;
	private JLabel lblNewLabel;



	/** Creates new form WaterNeeded */
	public WaterNeededNew() {
		initComponents();
		setBorder(Utils.getDefaultBorder());
		
		
		spinnerBackSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5, "0.0", "WaterNeeded.BS");
		

		setBackground(getBackground().darker());
		panelSparge.setBackground(panelSparge.getBackground().darker());

		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
	}

	private void initComponents() {

		tabbedPane = new JTabbedPane();

		panelSparge = new JPanel();
		GridBagLayout gbl_panelSparge = new GridBagLayout();
		gbl_panelSparge.columnWidths = new int[]{305, 0};
		panelSparge.setLayout(gbl_panelSparge);

		panelBatchSparge = new JPanel();
		panelBatchSparge.setLayout(new GridBagLayout());

		panelNoSparge = new JPanel();
		panelNoSparge.setLayout(new GridBagLayout());

		setBounds(0, 0, 758, 365);
		setClosable(true);
		setIconifiable(true);
		setTitle("Acqua necessaria New");

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Con Sparge", panelSparge);
		tabbedPane.addTab("Batch Sparge", panelBatchSparge);
		tabbedPane.addTab("No Sparge", panelNoSparge);

		getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

		initSpargePanel();
		initBatchSpargePanel();
		initNoSpargePanel();

		setBounds(0, 0, 758, 365);

	}

	private void initSpargePanel() {
		GridBagConstraints gridBagConstraints;

		panelSpecificheCottaSparge = new JPanel();
		panelSpecificheCottaSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Specifiche della cotta"));
		GridBagLayout gbl_panelSpecificheCottaSparge = new GridBagLayout();
		gbl_panelSpecificheCottaSparge.columnWidths = new int[]{185, 0, 0};
		gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0, 0.0};
		panelSpecificheCottaSparge.setLayout(gbl_panelSpecificheCottaSparge);
		gridBagConstraints = new GridBagConstraints();
		
		lblBatchSize = new JLabel("Batch size");
		GridBagConstraints gbc_lblBatchSize = new GridBagConstraints();
		gbc_lblBatchSize.anchor = GridBagConstraints.EAST;
		gbc_lblBatchSize.insets = new Insets(0, 20, 5, 9);
		gbc_lblBatchSize.gridx = 0;
		gbc_lblBatchSize.gridy = 0;
		panelSpecificheCottaSparge.add(lblBatchSize, gbc_lblBatchSize);
		
		spinnerBackSize = new JVolumeSpinner();
		spinnerBackSize.getSpinner().setEnabled(false);
		spinnerBackSize.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_spinnerBackSize = new GridBagConstraints();
		gbc_spinnerBackSize.anchor = GridBagConstraints.WEST;
		gbc_spinnerBackSize.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerBackSize.gridx = 1;
		gbc_spinnerBackSize.gridwidth = 4;
		gbc_spinnerBackSize.gridy = 0;
		panelSpecificheCottaSparge.add(spinnerBackSize, gbc_spinnerBackSize);
		

		
		JLabel labelGraniTotali = new JLabel();
		labelGraniTotali.setText("Grani totali");
		gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.anchor = GridBagConstraints.EAST;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.insets = new Insets(0, 20, 5, 9);
		panelSpecificheCottaSparge.add(labelGraniTotali, gridBagConstraints_1);
		
		textFieldGraniTotali = new JTextField();
		textFieldGraniTotali.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		textFieldGraniTotali.setPreferredSize(new Dimension(76, 22));
		panelSpecificheCottaSparge.add(textFieldGraniTotali, gridBagConstraints);
		
		lblKg = new JLabel("kg");
		GridBagConstraints gbc_lblKg = new GridBagConstraints();
		gbc_lblKg.insets = new Insets(0, 0, 5, 0);
		gbc_lblKg.gridx = 2;
		gbc_lblKg.gridy = 1;
		panelSpecificheCottaSparge.add(lblKg, gbc_lblKg);
		
		JLabel labelOriginalGravity = new JLabel();
		labelOriginalGravity.setText("Original Gravity");
		gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.anchor = GridBagConstraints.EAST;
		gridBagConstraints_2.gridx = 0;
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.insets = new Insets(0, 20, 0, 9);
		panelSpecificheCottaSparge.add(labelOriginalGravity, gridBagConstraints_2);
		
		
		panelDatiImpiantoSparge = new JPanel();
		panelDatiImpiantoSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		GridBagLayout gbl_panelDatiImpiantoSparge = new GridBagLayout();
		gbl_panelDatiImpiantoSparge.columnWeights = new double[]{0.0, 1.0, 0.0};
		panelDatiImpiantoSparge.setLayout(gbl_panelDatiImpiantoSparge);

		JLabel labelAssorbimentoGraniEsausti = new JLabel();
		labelAssorbimentoGraniEsausti.setText("Assorbimento grani esausti");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelDatiImpiantoSparge.add(labelAssorbimentoGraniEsausti, gridBagConstraints);
		
		textFieldAssorbimentoGraniEsausti = new JTextField();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		textFieldAssorbimentoGraniEsausti.setPreferredSize(new Dimension(76, 22));
		panelDatiImpiantoSparge.add(textFieldAssorbimentoGraniEsausti, gridBagConstraints);
		
		lblNewLabel = new JLabel("litri/kg");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panelDatiImpiantoSparge.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel labelRapportoAcquaGrani = new JLabel();
		labelRapportoAcquaGrani.setText("Rapporto acqua/grani");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelDatiImpiantoSparge.add(labelRapportoAcquaGrani, gridBagConstraints);
		
		textFieldRapportoAcquaGrani = new JTextField();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		textFieldRapportoAcquaGrani.setPreferredSize(new Dimension(76, 22));
		panelDatiImpiantoSparge.add(textFieldRapportoAcquaGrani, gridBagConstraints);
		
		JLabel labelPercentualeEvaporazione = new JLabel();
		labelPercentualeEvaporazione.setText("Percentuale evaporazione");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelDatiImpiantoSparge.add(labelPercentualeEvaporazione, gridBagConstraints);
		
		textFieldPercentualeEvaporazione = new JTextField();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		textFieldPercentualeEvaporazione.setPreferredSize(new Dimension(76, 22));
		panelDatiImpiantoSparge.add(textFieldPercentualeEvaporazione, gridBagConstraints);
		
		JLabel labelContrazioneRaffreddamento = new JLabel();
		labelContrazioneRaffreddamento.setText("Contrazione per raffreddamento");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelDatiImpiantoSparge.add(labelContrazioneRaffreddamento, gridBagConstraints);
		
		
		
		textFieldContrazioneRaffreddamento = new JTextField();
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		textFieldContrazioneRaffreddamento.setPreferredSize(new Dimension(76, 22));
		panelDatiImpiantoSparge.add(textFieldContrazioneRaffreddamento, gridBagConstraints);

			    

	    panelCalcoloPerditeSparge = new JPanel();
	    panelCalcoloPerditeSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo delle perdite"));
	    GridBagLayout gbl_panelCalcoloPerditeSparge = new GridBagLayout();
	    gbl_panelCalcoloPerditeSparge.columnWeights = new double[]{1.0};
	    panelCalcoloPerditeSparge.setLayout(gbl_panelCalcoloPerditeSparge);
		
	    
	    JLabel labelPerditaAssorbimento = new JLabel();
	    labelPerditaAssorbimento.setText("Perdita per assorbimento");
	    gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloPerditeSparge.add(labelPerditaAssorbimento, gridBagConstraints);
	    
		JLabel labelPerditaEvaporazione = new JLabel();
		labelPerditaEvaporazione.setText("Perdita per evaporazione");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloPerditeSparge.add(labelPerditaEvaporazione, gridBagConstraints);
	    
		JLabel labelPerditaContrazione = new JLabel();
		labelPerditaContrazione.setText("Perdita per contrazione");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloPerditeSparge.add(labelPerditaContrazione, gridBagConstraints);
	    
		panelCalcoloVolumiSparge = new JPanel();
		panelCalcoloVolumiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo dei volumi"));
		GridBagLayout gbl_panelCalcoloVolumiSparge = new GridBagLayout();
		gbl_panelCalcoloVolumiSparge.columnWeights = new double[]{1.0, 1.0};
		panelCalcoloVolumiSparge.setLayout(gbl_panelCalcoloVolumiSparge);
		
		JLabel labelVolumeMostoPreBoil = new JLabel();
		labelVolumeMostoPreBoil.setText("Volume mosto pre-boil");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloVolumiSparge.add(labelVolumeMostoPreBoil, gridBagConstraints);
		
		textFieldVolumeMostoPreBoil = new JTextField();
		textFieldVolumeMostoPreBoil.setEditable(false);
		GridBagConstraints gbc_textFieldVolumeMostoPreBoil = new GridBagConstraints();
		gbc_textFieldVolumeMostoPreBoil.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVolumeMostoPreBoil.gridx = 1;
		gbc_textFieldVolumeMostoPreBoil.gridy = 0;
		textFieldVolumeMostoPreBoil.setPreferredSize(new Dimension(76, 22));
		panelCalcoloVolumiSparge.add(textFieldVolumeMostoPreBoil, gbc_textFieldVolumeMostoPreBoil);
	    
		JLabel labelOGPreBoil = new JLabel();
		labelOGPreBoil.setText("OG pre-boil");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloVolumiSparge.add(labelOGPreBoil, gridBagConstraints);
		
		textFieldOGPreBoil = new JTextField();
		textFieldOGPreBoil.setEditable(false);
		GridBagConstraints gbc_textFieldOGPreBoil = new GridBagConstraints();
		gbc_textFieldOGPreBoil.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldOGPreBoil.gridx = 1;
		gbc_textFieldOGPreBoil.gridy = 1;
		textFieldOGPreBoil.setPreferredSize(new Dimension(76, 22));
		panelCalcoloVolumiSparge.add(textFieldOGPreBoil, gbc_textFieldOGPreBoil);
	    
		JLabel labelVolumePostBoil = new JLabel();
		labelVolumePostBoil.setText("Volume post-boil");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelCalcoloVolumiSparge.add(labelVolumePostBoil, gridBagConstraints);
		
		textFieldVolumePostBoil = new JTextField();
		textFieldVolumePostBoil.setEditable(false);
		GridBagConstraints gbc_textFieldVolumePostBoil = new GridBagConstraints();
		gbc_textFieldVolumePostBoil.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVolumePostBoil.gridx = 1;
		gbc_textFieldVolumePostBoil.gridy = 2;
		textFieldVolumePostBoil.setPreferredSize(new Dimension(76, 22));
		panelCalcoloVolumiSparge.add(textFieldVolumePostBoil, gbc_textFieldVolumePostBoil);
		
		JLabel labelVolumePostRaffreddamento = new JLabel();
		labelVolumePostRaffreddamento.setText("Volume post-raffreddamento");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 0, 9);
		panelCalcoloVolumiSparge.add(labelVolumePostRaffreddamento, gridBagConstraints);
		
		panelRisultatiSparge = new JPanel();
		panelRisultatiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Totale acqua richiesta"));
		GridBagLayout gbl_panelRisultatiSparge = new GridBagLayout();
		gbl_panelRisultatiSparge.columnWidths = new int[]{168, 0};
		gbl_panelRisultatiSparge.columnWeights = new double[]{0.0, 1.0};
		panelRisultatiSparge.setLayout(gbl_panelRisultatiSparge);
	    

		JLabel labelAcquaMash = new JLabel();
		labelAcquaMash.setText("Acqua di mash");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 5, 9);
		panelRisultatiSparge.add(labelAcquaMash, gridBagConstraints);
		
		textFieldAcquaMash = new JTextField();
		textFieldAcquaMash.setEditable(false);
		GridBagConstraints gbc_textFieldAcquaMash = new GridBagConstraints();
		gbc_textFieldAcquaMash.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAcquaMash.gridx = 1;
		gbc_textFieldAcquaMash.gridy = 0;
		textFieldAcquaMash.setPreferredSize(new Dimension(76, 22));
		panelRisultatiSparge.add(textFieldAcquaMash, gbc_textFieldAcquaMash);
	    
		JLabel labelAcquaSparge = new JLabel();
		labelAcquaSparge.setText("Acqua si sparge");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(0, 20, 0, 9);
		panelRisultatiSparge.add(labelAcquaSparge, gridBagConstraints);
		
		
		gridBagConstraints_3 = new java.awt.GridBagConstraints();
		gridBagConstraints_3.gridx = 0;
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.fill = GridBagConstraints.BOTH;
		gridBagConstraints_3.insets = new java.awt.Insets(2, 2, 2, 2);
		panelSparge.add(panelSpecificheCottaSparge, gridBagConstraints_3);
		
		textFieldOriginalGravity = new JTextField();
		textFieldOriginalGravity.setEditable(false);
		gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints_4.anchor = GridBagConstraints.WEST;
		gridBagConstraints_4.gridx = 1;
		gridBagConstraints_4.gridy = 2;
		textFieldOriginalGravity.setPreferredSize(new Dimension(76, 22));
		panelSpecificheCottaSparge.add(textFieldOriginalGravity, gridBagConstraints_4);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelSparge.add(panelDatiImpiantoSparge, gridBagConstraints);
		
		chckbxPerditeNelTrub = new JCheckBox("Perdite nel Trub");
		chckbxPerditeNelTrub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinnerTrub.setEnabled(chckbxPerditeNelTrub.isSelected());
			}
		});
		chckbxPerditeNelTrub.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_chckbxPerditeNelTrub = new GridBagConstraints();
		gbc_chckbxPerditeNelTrub.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxPerditeNelTrub.anchor = GridBagConstraints.EAST;
		gbc_chckbxPerditeNelTrub.gridx = 0;
		gbc_chckbxPerditeNelTrub.gridy = 4;
		panelDatiImpiantoSparge.add(chckbxPerditeNelTrub, gbc_chckbxPerditeNelTrub);
		
		spinnerTrub = new JSpinner();
		spinnerTrub.setPreferredSize(new Dimension(76, 20));
		spinnerTrub.setEnabled(false);
		GridBagConstraints gbc_spinnerTrub = new GridBagConstraints();
		gbc_spinnerTrub.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerTrub.anchor = GridBagConstraints.WEST;
		gbc_spinnerTrub.gridx = 1;
		gbc_spinnerTrub.gridy = 4;
		panelDatiImpiantoSparge.add(spinnerTrub, gbc_spinnerTrub);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelSparge.add(panelCalcoloPerditeSparge, gridBagConstraints);
		
		textFieldPerditaAssorbimento = new JTextField();
		textFieldPerditaAssorbimento.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		textFieldPerditaAssorbimento.setPreferredSize(new Dimension(76, 22));
		panelCalcoloPerditeSparge.add(textFieldPerditaAssorbimento, gridBagConstraints);
		
		textFieldPerditaEvaporazione = new JTextField();
		textFieldPerditaEvaporazione.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		textFieldPerditaEvaporazione.setPreferredSize(new Dimension(76, 22));
		panelCalcoloPerditeSparge.add(textFieldPerditaEvaporazione, gridBagConstraints);
		
		textFieldPerditaContrazione = new JTextField();
		textFieldPerditaContrazione.setEditable(false);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		textFieldPerditaContrazione.setPreferredSize(new Dimension(76, 22));
		panelCalcoloPerditeSparge.add(textFieldPerditaContrazione, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelSparge.add(panelCalcoloVolumiSparge, gridBagConstraints);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.gridx = 1;
		gbc_textField_12.gridy = 3;
		textField_12.setPreferredSize(new Dimension(76, 22));
		panelCalcoloVolumiSparge.add(textField_12, gbc_textField_12);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelSparge.add(panelRisultatiSparge, gridBagConstraints);
		
		textFieldAcquaSparge = new JTextField();
		textFieldAcquaSparge.setEditable(false);
		GridBagConstraints gbc_textFieldAcquaSparge = new GridBagConstraints();
		gbc_textFieldAcquaSparge.gridx = 1;
		gbc_textFieldAcquaSparge.gridy = 1;
		textFieldAcquaSparge.setPreferredSize(new Dimension(76, 22));
		panelRisultatiSparge.add(textFieldAcquaSparge, gbc_textFieldAcquaSparge);
		

//		panelRabbocchiSparge = new JPanel();
//		panelRabbocchiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Rabbocchi"));
//		gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.gridx = 1;
//		gridBagConstraints.gridy = 0;
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
//		panelSparge.add(panelRabbocchiSparge, gridBagConstraints);
//
//		panelSpargeRisultati = new JPanel();
//		panelSpargeRisultati.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati"));
//		gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.gridx = 1;
//		gridBagConstraints.gridy = 1;
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
//		panelSparge.add(panelSpargeRisultati, gridBagConstraints);
	}

	private void initBatchSpargePanel() {

	}

	private void initNoSpargePanel() {

	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}
	
	private void spnTrubStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnBatchSizeStateChanged
		calc();
	}

	private void spnBatchSizeStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnBatchSizeStateChanged
		calc();
	}

	private void calc() {
		// TODO Auto-generated method stub

	}
}
