package jmash;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
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
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class frmTimer extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3434293058479482856L;
	/**
	 * 
	 */
	private final JPanel listaSteps = new JPanel();
	private JLabel labelSteps = new JLabel("<html>Step 1</html>");
	
	private Timer timerboil; 
	private ArrayList<BoilStep> steps;
	private String nomericetta;
	private int boiltime;
	private int minutiTrascorsi;
	private Boolean timercaricato=false;
	private Boolean running=false;
	private String xml="";
	
	
	JLabel lblRicetta = new JLabel("(nessuna ricetta)");
	JLabel lblTimer = new JLabel("0 / 0");
	JButton cmdStartStop = new JButton("Avvia");
	JButton cmdReset = new JButton("Reset");
	
	
	public frmTimer(String xmlstring) 
	{
		xml=xmlstring;
		this.setTitle("Boiling Timer");
		setResizable(false);
		setBounds(100, 100, 315, 421);
		getContentPane().setLayout(new BorderLayout());
		listaSteps.setBackground(Color.BLACK);
		listaSteps.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listaSteps, BorderLayout.CENTER);
		listaSteps.setLayout(null);
		
		
		lblRicetta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRicetta.setOpaque(true);
		lblRicetta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRicetta.setBackground(new Color(18, 148, 61));
		lblRicetta.setForeground(Color.WHITE);
		lblRicetta.setBounds(0, 0, 309, 25);
		listaSteps.add(lblRicetta);
		cmdStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!timercaricato)
				{
					diQuesto(true);
					return;
				}
				if(running)
				{
					setStarted(false);
					timerboil.stop();
				}
				else
				{
					setStarted(true);
					timerboil.start();
					actionForce(); //forza un tick in avvio timer
				}
				running=!running;
			}
		});
		
		cmdStartStop.setActionCommand("");
		cmdStartStop.setBounds(10, 36, 91, 23);
		listaSteps.add(cmdStartStop);
		cmdReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset();
			}
		});
		
		cmdReset.setActionCommand("");
		cmdReset.setBounds(208, 36, 91, 23);
		listaSteps.add(cmdReset);
		
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setForeground(new Color(18, 148, 61));
		lblTimer.setFont(new Font("SansSerif", Font.BOLD, 58));
		lblTimer.setBackground(Color.BLACK);
		lblTimer.setBounds(0, 70, 309, 67);
		listaSteps.add(lblTimer);
		
		labelSteps.setVerticalAlignment(SwingConstants.TOP);
		labelSteps.setForeground(Color.WHITE);
		labelSteps.setBounds(10, 149, 289, 205);
		listaSteps.add(labelSteps);
		JPanel buttonPane = new JPanel();
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
		LoadTimer();
	}
	
	
	///BL
	private void LoadTimer()
	{
		Document doc=Utils.XMLfromString(xml);
		  try{
	            steps = new ArrayList<BoilStep>();
	            Element bsteps = doc.getDocumentElement();
	            nomericetta=bsteps.getAttribute("birra");
	            boiltime=Integer.parseInt(bsteps.getAttribute("minuti"));
	            lblRicetta.setText(" "+nomericetta+" ");
	            lblTimer.setText("0 / "+boiltime);
	            setStarted(false);
	            NodeList nodes=doc.getElementsByTagName("step");
	            for (int i=0;i<nodes.getLength();i++)
	            {
	            	BoilStep step=new BoilStep();
	            	org.w3c.dom.Element e = (org.w3c.dom.Element)nodes.item(i);
	            	NodeList n = e.getElementsByTagName("t");
	            	step.setMinuto(Integer.parseInt(getElementValue(n.item(0))));
	            	n = e.getElementsByTagName("d");
	            	step.setDescrizione(getElementValue(n.item(0)));
	            	steps.add(step);
	            }
	            BindaLista();
	            diQuesto(false);
	            timercaricato=true;
	            //setto timer
	            timerboil=new Timer(60000,new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						actionTick();
					}
				});
	          } catch (Exception e) { 
	            diQuesto(true);
	          }
	}
	private void actionTick()
	{
		minutiTrascorsi++;
		lblTimer.setText(minutiTrascorsi+" / "+boiltime);
		checkSteps();
	}
	private void actionForce()
	{
		//forza il task in avvio timer
		lblTimer.setText(minutiTrascorsi+" / "+boiltime);
		checkSteps();
	}
	private void checkSteps()
	{
		for (int x=0;x<steps.size();x++){
			if(steps.get(x).getMinuto()==minutiTrascorsi){
				diQuesto(true);
			}
		}
		if(minutiTrascorsi==boiltime){
			diQuesto(true);
			Reset();
		}
        BindaLista();
	}
	private void Reset() {
		if(!timercaricato)
		{
			diQuesto(false);
			return;
		}
		lblTimer.setText("0 / "+boiltime);
		minutiTrascorsi=0;
        BindaLista();
		if(running){timerboil.stop();}
		setStarted(false);
		running=false;
	}
	
	//XML PARSER
	 private String getElementValue( Node elem ) {
	     Node kid;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
	                 if( kid.getNodeType() == Node.TEXT_NODE  ){
	                     return kid.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
	
	//UI
	 private void BindaLista()
	 {
		 String layoutsteps="<html>";
		 for(int i=0;i<steps.size();i++)
		 {
			 if(steps.get(i).getMinuto()<minutiTrascorsi)
			 {
				 layoutsteps+="<font color='#095422'>"+steps.get(i).getMinuto()+" "+steps.get(i).getDescrizione()+"</font><br>";
			 }
			 if(steps.get(i).getMinuto()==minutiTrascorsi)
			 {
				 layoutsteps+="<font color='red'>"+steps.get(i).getMinuto()+" "+steps.get(i).getDescrizione()+"</font><br>";
			 }
			 if(steps.get(i).getMinuto()>minutiTrascorsi)
			 {
				 layoutsteps+="<font color='#12943D'>"+steps.get(i).getMinuto()+" "+steps.get(i).getDescrizione()+"</font><br>";
			 }
		 }
		 layoutsteps+="</html>";
		 labelSteps.setText(layoutsteps);
	 }
	
	
	private void setStarted(boolean valore){
		//switch start-stop
		if(valore){
			cmdStartStop.setText("Ferma");
		}
		else{
			cmdStartStop.setText("Avvia");
		}
	}
	
	private void diQuesto(Boolean isStep)
	{
		File wav=null;
		if(isStep) wav=new File(Main.userDir+"/Templates/b1.wav"); else wav=new File(Main.userDir+"/Templates/b0.wav");
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream( wav);
	        clip.open(inputStream);
	        clip.loop(0);
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	}
	
}
