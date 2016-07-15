package jmash;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import javax.swing.JLabel;
import java.awt.Insets;

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
	private JLabel lblNewLabel;

	public WaterNeededNew2() {
		initComponents();
		setBorder(Utils.getDefaultBorder());

		// spinnerBackSize.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.BS");
		// spinnerTrub.setModel(Main.config.getVolumeFin(), 0, 1000000, 0.5,
		// "0.0", "WaterNeeded.Trub");

		setBackground(getBackground().darker());
		panelSparge.setBackground(panelSparge.getBackground().darker());

		// tabbedPane.setEnabledAt(1, false);
		// tabbedPane.setEnabledAt(2, false);
	}

	private void initComponents() {

		tabbedPane = new JTabbedPane();

		panelSparge = new JPanel(new GridBagLayout());
		panelBatchSparge = new JPanel(new GridBagLayout());
		panelNoSparge = new JPanel(new GridBagLayout());

		setClosable(true);
		setIconifiable(true);
		setTitle("Acqua necessaria New 2");

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("Con Sparge", panelSparge);
		tabbedPane.addTab("Batch Sparge", panelBatchSparge);
		tabbedPane.addTab("No Sparge", panelNoSparge);

		getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

		initSpargePanel();
		initBatchSpargePanel();
		initNoSpargePanel();

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
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };;
		panelSpecificheCottaSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		
		panelSparge.add(panelSpecificheCottaSparge, gridBagConstraints);

	}

	private void initPanelDatiImpiantoSparge() {
		
		GridBagConstraints gridBagConstraints;

		panelDatiImpiantoSparge = new JPanel();
		panelDatiImpiantoSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati impianto"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };;
		panelDatiImpiantoSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		
		panelSparge.add(panelDatiImpiantoSparge, gridBagConstraints);

	}

	private void initPanelCalcoloPerditeSparge() {

		
		GridBagConstraints gridBagConstraints;

		panelCalcoloPerditeSparge = new JPanel();
		panelCalcoloPerditeSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo delle perdite"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };;
		panelCalcoloPerditeSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		
		panelSparge.add(panelCalcoloPerditeSparge, gridBagConstraints);

	}
	
	private void initPanelCalcoloVolumiSparge() {

		GridBagConstraints gridBagConstraints;

		panelCalcoloVolumiSparge = new JPanel();
		panelCalcoloVolumiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Calcolo dei volumi"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };;
		panelCalcoloVolumiSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		
		panelSparge.add(panelCalcoloVolumiSparge, gridBagConstraints);

	}
	
	private void initPanelRisultatiSparge() {

		GridBagConstraints gridBagConstraints;

		panelRisultatiSparge = new JPanel();
		panelRisultatiSparge.setBorder(javax.swing.BorderFactory.createTitledBorder("Totale acqua richiesta"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gbl_panelSpecificheCottaSparge.columnWeights = new double[]{0.0, 1.0,
		// 0.0};
		gridBagLayout.columnWidths = new int[] { 150, 78, 0 };;
		panelRisultatiSparge.setLayout(gridBagLayout);
		gridBagConstraints = new GridBagConstraints();

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		
		panelSparge.add(panelRisultatiSparge, gridBagConstraints);

	}

	private void initBatchSpargePanel() {

	}

	private void initNoSpargePanel() {

	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}

	private void calc() {

	}

}
