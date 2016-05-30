package jmash;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.net.URI;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import jmash.component.JVolumeSpinner;
import jmash.component.JGravitySpinner;
import jmash.tableModel.NumberFormatter;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class frmEfficienza extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218837417290578L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private Ricetta ricetta;
	private JVolumeSpinner volumeSpinnerPREBOIL ;
	private JVolumeSpinner volumeSpinnerBREWHOUSE;
	private JGravitySpinner gravitySpinnerBREWHOUSE;
	private JGravitySpinner gravitySpinnerPREBOIL;
	JLabel lblEFFPreboil ;
	JLabel lblEFFBrewhouse;
	

	public frmEfficienza(Ricetta ric, String nome, Double volPREBOIL, Double volFINALE) 
	{
		ricetta=ric;
		this.setTitle("Calcolo efficienza "+nome);
		setResizable(false);
		setBounds(100, 100, 523, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmEfficienza.class.getResource("/jmash/images/efficiency.png")));
		lblNewLabel.setBounds(10, 0, 507, 277);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPreboil = new JLabel("Pre-boil");
		lblPreboil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreboil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreboil.setBounds(10, 274, 224, 14);
		contentPanel.add(lblPreboil);
		
		JLabel lblBrewhouse = new JLabel("Brewhouse");
		lblBrewhouse.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrewhouse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrewhouse.setBounds(272, 274, 228, 14);
		contentPanel.add(lblBrewhouse);
		
		JLabel lblLitriInFermentatore = new JLabel("Litri in ferment.:");
		lblLitriInFermentatore.setBounds(272, 296, 78, 14);
		contentPanel.add(lblLitriInFermentatore);
		
		JLabel lblOgInFermentatore = new JLabel("OG in ferment.:");
		lblOgInFermentatore.setBounds(272, 321, 87, 14);
		contentPanel.add(lblOgInFermentatore);
		
		JLabel lblOgInPentola = new JLabel("OG in pentola:");
		lblOgInPentola.setBounds(10, 321, 78, 14);
		contentPanel.add(lblOgInPentola);
		
		JLabel lblLitriInPentola = new JLabel("Litri in pentola:");
		lblLitriInPentola.setBounds(10, 295, 78, 14);
		contentPanel.add(lblLitriInPentola);
		
		lblEFFPreboil = new JLabel("--%");
		lblEFFPreboil.setHorizontalAlignment(SwingConstants.CENTER);
		lblEFFPreboil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEFFPreboil.setBounds(10, 349, 224, 14);
		contentPanel.add(lblEFFPreboil);
		
		lblEFFBrewhouse = new JLabel("--%");
		lblEFFBrewhouse.setHorizontalAlignment(SwingConstants.CENTER);
		lblEFFBrewhouse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEFFBrewhouse.setBounds(272, 349, 228, 14);
		contentPanel.add(lblEFFBrewhouse);
		
		volumeSpinnerPREBOIL = new JVolumeSpinner();
		volumeSpinnerPREBOIL.setDoubleValue(volPREBOIL);
		volumeSpinnerPREBOIL.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcPREBOIL();
			}
		});
		volumeSpinnerPREBOIL.setBounds(86, 291, 148, 22);
		contentPanel.add(volumeSpinnerPREBOIL);
		
		gravitySpinnerPREBOIL = new JGravitySpinner();
		gravitySpinnerPREBOIL.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcPREBOIL();
			}
		});
		gravitySpinnerPREBOIL.setBounds(86, 317, 148, 22);
		contentPanel.add(gravitySpinnerPREBOIL);
		
		volumeSpinnerBREWHOUSE = new JVolumeSpinner();
		volumeSpinnerBREWHOUSE.setDoubleValue(volFINALE);
		volumeSpinnerBREWHOUSE.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcBREWHOUSE();
			}
		});
		volumeSpinnerBREWHOUSE.setBounds(352, 291, 148, 22);
		contentPanel.add(volumeSpinnerBREWHOUSE);
		
		gravitySpinnerBREWHOUSE = new JGravitySpinner();
		gravitySpinnerBREWHOUSE.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcBREWHOUSE();
			}
		});
		gravitySpinnerBREWHOUSE.setBounds(352, 316, 148, 22);
		contentPanel.add(gravitySpinnerBREWHOUSE);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	
	
	 private void calcPREBOIL(){
	    	double X=0;
	    	double xpts=0;
	    	for(Malt m:ricetta.maltTableModel.getRows()){
	    	    double pts=m.getPotentialSG()*1000-1000;
	    	    double grs=m.getGrammi();
	    	    if(Main.config.getPotLibGal()==1)
	    	    {
	    	    	//anglosaxon
	    	    	if(m.isMashed())
	    	    		X+=pts*Utils.kgToPound(grs/1000)/Utils.litToGal(volumeSpinnerPREBOIL.getVolume());
	    	    	    else
	    	    		xpts+=pts*Utils.kgToPound(grs/1000)/Utils.litToGal(volumeSpinnerPREBOIL.getVolume());
	    	    }
	    	    else
	    	    {
	    	    	//metric
	    	    	if(m.isMashed())
	    	    		X+=(pts*grs/100)/volumeSpinnerPREBOIL.getVolume();
	    	    	else
	    	    		xpts+=(pts*grs/100)/volumeSpinnerPREBOIL.getVolume();
	    	    }
	    	    
	    	}
	    	double actPts=(gravitySpinnerPREBOIL.getGravity()*1000-1000)-xpts;
	    	double eff=100*actPts/X;
	    	if(X==0)eff=100;
	    	lblEFFPreboil.setText("Eff. mash: "+NumberFormatter.format02(eff)+"%");
	        }
	 private void calcBREWHOUSE(){
	    	double X=0;
	    	double xpts=0;
	    	for(Malt m:ricetta.maltTableModel.getRows()){
	    	    double pts=m.getPotentialSG()*1000-1000;
	    	    double grs=m.getGrammi();
	    	    if(Main.config.getPotLibGal()==1)
	    	    {
	    	    	//anglosaxon
	    	    	if(m.isMashed())
	    	    		X+=pts*Utils.kgToPound(grs/1000)/Utils.litToGal(volumeSpinnerBREWHOUSE.getVolume());
	    	    	    else
	    	    		xpts+=pts*Utils.kgToPound(grs/1000)/Utils.litToGal(volumeSpinnerBREWHOUSE.getVolume());
	    	    }
	    	    else
	    	    {
	    	    	//metric
	    	    	if(m.isMashed())
	    	    		X+=(pts*grs/100)/volumeSpinnerBREWHOUSE.getVolume();
	    	    	else
	    	    		xpts+=(pts*grs/100)/volumeSpinnerBREWHOUSE.getVolume();
	    	    }
	    	    
	    	}
	    	double actPts=(gravitySpinnerBREWHOUSE.getGravity()*1000-1000)-xpts;
	    	double eff=100*actPts/X;
	    	if(X==0)eff=100;
	    	lblEFFBrewhouse.setText("Eff. finale: "+NumberFormatter.format02(eff)+"%");
	        }
}
