package jmash;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.Dimension;

import jmash.tableModel.HopTableModel;

import jmash.component.JVolumeSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import sun.security.util.Debug;
import javax.swing.SwingConstants;


public class frmBiabUtils extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980894983404722200L;

	private JInternalFrame parent;
	int Boiltime=0;
	double GrainBill=0;
	String Nomericetta;
	double Volferm=0.0;
	double VolTrub=0.0;
	double VolPostBoil=0.0;
	double VolEvap=0.0;
	double VolPreboil=0.0;
	double VolSparge=0.0;
	double VolPostMash=0.0;
	double VolMashRetain=0.0;
	double VI=0.0;
	
	
	JSpinner spinEvapPerc = new JSpinner();
	JVolumeSpinner spinVolumeFermentatore = new JVolumeSpinner();
	JVolumeSpinner spinSparge = new JVolumeSpinner();
	JVolumeSpinner spinPerditeMash = new JVolumeSpinner();
	JVolumeSpinner spinVI = new JVolumeSpinner();
	JVolumeSpinner spinVolumeFineMash = new JVolumeSpinner();
	JVolumeSpinner spinFineBoil = new JVolumeSpinner();
	JVolumeSpinner spinEvapVolume = new JVolumeSpinner();
	JSpinner spinLtKg = new JSpinner();
	JVolumeSpinner spinPerditeTrub = new JVolumeSpinner();
	JVolumeSpinner spinPreboil = new JVolumeSpinner();
	JLabel lblMashLtkg = new JLabel("Mash L/kg:");
	
	public frmBiabUtils(int grainbill, double volferm,  int boiltime, String nome) {
		Boiltime=boiltime;
		Nomericetta=nome;
		GrainBill=grainbill;
		Volferm=volferm;
		this.setTitle("Volumi per "+Nomericetta);
		setResizable(false);
		setMaximizable(false);
		setBounds(100, 100, 373, 314);
		parent=this;
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 233, 361, 50);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);
		
		JButton btnChiudi = new JButton("");
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		
		btnChiudi.setToolTipText("OK");
		btnChiudi.setIcon(new ImageIcon(frmBiabUtils.class.getResource("/jmash/images/button_ok.png")));
		toolBar.add(btnChiudi);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 361, 215);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblInFermentatore = new JLabel("In fermentatore:");
		lblInFermentatore.setBounds(16, 197, 89, 14);
		panel.add(lblInFermentatore);
		spinEvapPerc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CalcVols();
			}
		});
		
		
		spinEvapPerc.setModel(new SpinnerNumberModel(15.0, 0.0, 50.0, 0.1));
		spinEvapPerc.setToolTipText("Percentuale oraria di evaporazione");
		spinEvapPerc.setBounds(67, 122, 56, 26);
		panel.add(spinEvapPerc);
		
		JLabel lblPerditeInTrub = new JLabel("Perdite in Trub:");
		lblPerditeInTrub.setBounds(16, 174, 89, 14);
		panel.add(lblPerditeInTrub);
		
		
		spinVolumeFermentatore.getSpinner().setEnabled(false);
		spinVolumeFermentatore.setBounds(125, 193, 142, 22);
		spinVolumeFermentatore.setVolume(Volferm);
		spinVolumeFermentatore.setModel(0, 0, 0, 0.1, "0.00", "");
		panel.add(spinVolumeFermentatore);
		
		JLabel lblFineBoil = new JLabel("Post Boil:");
		lblFineBoil.setBounds(16, 151, 89, 14);
		panel.add(lblFineBoil);
		
		JLabel lblEvaporazioneOraria = new JLabel("Evap/h %");
		lblEvaporazioneOraria.setBounds(16, 128, 107, 14);
		panel.add(lblEvaporazioneOraria);
		
		
		spinFineBoil.getSpinner().setEnabled(false);
		spinFineBoil.setModel(0, 0, 0, 0.1, "0.00", "");
		spinFineBoil.setBounds(125, 147, 142, 22);
		panel.add(spinFineBoil);
		
		JLabel lblMiniSparge = new JLabel("Mini Sparge:");
		lblMiniSparge.setBounds(16, 81, 89, 14);
		panel.add(lblMiniSparge);
		
		spinEvapVolume.getSpinner().setEnabled(false);
		spinEvapVolume.setModel(0, 0, 0, 0.1, "0.00", "");
		spinEvapVolume.setBounds(125, 124, 142, 22);
		panel.add(spinEvapVolume);
		
		JLabel lblFineMash = new JLabel("Fine Mash:");
		lblFineMash.setBounds(16, 58, 89, 14);
		panel.add(lblFineMash);
		
		JLabel lblPerditeMash = new JLabel("Assorb. Mash:");
		lblPerditeMash.setBounds(16, 35, 89, 14);
		panel.add(lblPerditeMash);
		
		JLabel lblVolumeIniziale = new JLabel("Volume Iniziale:");
		lblVolumeIniziale.setBounds(16, 12, 89, 14);
		panel.add(lblVolumeIniziale);
		
		
		spinVolumeFineMash.getSpinner().setEnabled(false);
		spinVolumeFineMash.setModel(0, 0, 0, 0.1, "0.00", "");
		spinVolumeFineMash.setBounds(125, 54, 142, 22);
		panel.add(spinVolumeFineMash);
		
		
		spinVI.getSpinner().setEnabled(false);
		spinVI.setModel(0, 0, 0, 0.1, "0.00", "");
		spinVI.setBounds(125, 8, 142, 22);
		panel.add(spinVI);
		
		
		spinPerditeMash.getSpinner().setEnabled(false);
		spinPerditeMash.setModel(0, 0, 0, 0.1, "0.00", "");
		spinPerditeMash.setBounds(125, 31, 142, 22);
		panel.add(spinPerditeMash);
		
		//spinSparge.getSpinner().setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0)));
		spinSparge.setModelFormat(0.0, 0.0, volferm, 0.1, "0.0", "");
		spinSparge.setVolume(0);
		spinSparge.setBounds(125, 77, 142, 22);
		panel.add(spinSparge);
		spinSparge.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CalcVols();
			}
		});
		
		
		JLabel lblLtkg = new JLabel("L/kg:");
		lblLtkg.setBounds(268, 35, 37, 14);
		panel.add(lblLtkg);
		
		
		spinLtKg.setModel(new SpinnerNumberModel(0.3, 0.0, 5.0,0.1));
		spinLtKg.setToolTipText("Assorbimento grani");
		spinLtKg.setBounds(299, 29, 56, 26);
		panel.add(spinLtKg);
		spinLtKg.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CalcVols();
			}
		});
		spinPerditeTrub.getSpinner().setIntegerValue(0);
		spinPerditeTrub.setBounds(125, 170, 144, 23);
		spinPerditeTrub.setModelFormat(0.5, 0.0, volferm, 0.1, "0.0", "");
		spinPerditeTrub.setVolume(0.5);
		panel.add(spinPerditeTrub);
		
		
		spinPreboil.getSpinner().setEnabled(false);
		spinPreboil.setModel(0, 0, 0, 0.1, "0.00", "");
		spinPreboil.setVolume(-0.4117647058823529);
		spinPreboil.setBounds(125, 100, 142, 22);
		panel.add(spinPreboil);
		
		JLabel lblPreBoil = new JLabel("Pre Boil:");
		lblPreBoil.setBounds(16, 104, 89, 14);
		panel.add(lblPreBoil);
		
		
		lblMashLtkg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMashLtkg.setBounds(103, 220, 155, 14);
		getContentPane().add(lblMashLtkg);
		spinPerditeTrub.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CalcVols();
			}
		});

		InitForm();
		CalcVols();
	}
	private void CalcVols()
	{
		VolTrub=spinPerditeTrub.getVolume();
		VolPostBoil=VolTrub+Volferm;
		VolPreboil=(VolPostBoil*100)/(100-new Double(spinEvapPerc.getValue().toString()));
		VolEvap=VolPreboil/100*new Double(spinEvapPerc.getValue().toString());
		VolEvap=VolEvap/60*Boiltime;
		VolSparge=spinSparge.getVolume();
		VolPostMash=VolPreboil-VolSparge;
		VolMashRetain=new Double(spinLtKg.getValue().toString()) * (GrainBill/1000);
		VI=VolPostMash+VolMashRetain;
		
		spinVolumeFermentatore.setVolume(Volferm);
		spinPreboil.setVolume(VolPreboil);
		spinEvapVolume.setVolume(VolEvap);
		spinFineBoil.setVolume(VolPostBoil);
		spinPerditeMash.setVolume(VolMashRetain);
		spinVI.setVolume(VI);
		spinVolumeFineMash.setVolume(VolPostMash);
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		lblMashLtkg.setText("Mash L/kg: "+ decimalFormat.format(VI/(GrainBill/1000)));
		
	}
	private void InitForm()
	{
		setClosable(true);
        setIconifiable(true);
        setAutoscrolls(true);
	}
}