package jmash;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.List;
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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.UIManager;

import javax.swing.JSplitPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.Dimension;

import jmash.tableModel.HopTableModel;
import java.awt.Rectangle;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;


public class frmTimerBoil extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 78003750980266540L;
	/**
	 * 
	 */
	private JInternalFrame parent;
	private JPopupMenu popup;
	private JMenuItem menuItem;
	JSpinner spinboiltime = new JSpinner();
	private JList list;
	private DefaultListModel listModel;
	private JTextField txtdesc;
	private HopTableModel listasteps;
	private class STEP implements Comparable<STEP>
	{
	    private int minuto;
	    private String descrizione;
	    public STEP(int minuto, String descrizione) {
	        this.minuto = minuto;
	        this.descrizione = descrizione;
	    }
	    public int compareTo(STEP compareSTEP) {
	    	 
			int compareQuantity = ((STEP) compareSTEP).minuto; 
	 
			//ascending order
			return this.minuto - compareQuantity;
	 
		}	
	}
	ArrayList<STEP> steps=new ArrayList<frmTimerBoil.STEP>();
	
	String xmlText="";
	int boiltime=0;
	String nomericetta;
	int maxtime=120;
	
	public frmTimerBoil(HopTableModel listasteps, int boiltime, String nome) {
		this.listasteps=listasteps;
		this.boiltime=boiltime;
		maxtime=boiltime;
		nomericetta=nome;
		this.setTitle("Boiling Timer per "+nomericetta);
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 502, 348);
		parent=this;

		listModel = new DefaultListModel();
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);
		
		JButton btnQRCode = new JButton("");
		btnQRCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmQRCode qr=new frmQRCode(xmlText);
				qr.setLocationRelativeTo(parent);
				qr.setVisible(true);
			}
		});
		toolBar.add(btnQRCode);
		btnQRCode.setIcon(new ImageIcon(frmTimerBoil.class.getResource("/jmash/images/qrcode.png")));
		btnQRCode.setToolTipText("Trasferisci il timer su dispositivo Android");
		
		JButton btnChiudi = new JButton("");
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		
		JButton cmdGooglePlay = new JButton("");
		cmdGooglePlay.setToolTipText("Dove trovo il timer per Android?");
		cmdGooglePlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	try
		    	{
		    		new Utils.BareBonesBrowserLaunch().openURL("https://play.google.com/store/apps/details?id=ixtlanas.brewplus.brewplustimer");
		    	}
		    	catch (Exception ex)
		    	{
		    	}
			}
		});
		cmdGooglePlay.setIcon(new ImageIcon(frmTimerBoil.class.getResource("/jmash/images/help.png")));
		toolBar.add(cmdGooglePlay);
		
		JButton cmdTimer = new JButton("");
		cmdTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	
				frmTimer tm=new frmTimer(xmlText);
//				Gui.addFrame(tm);
//				Dimension desktopSize = desktopPane.getSize();
//		    	Dimension jInternalFrameSize = fc.getSize();
//		    	fc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
//		    	    (desktopSize.height- jInternalFrameSize.height)/2);
				//tm.setLocationRelativeTo(parent);
				tm.setVisible(true);
			}
		});
		cmdTimer.setIcon(new ImageIcon(frmTimerBoil.class.getResource("/jmash/images/timer.png")));
		cmdTimer.setToolTipText("Visualizza il timer");
		toolBar.add(cmdTimer);
		
		btnChiudi.setToolTipText("OK");
		btnChiudi.setIcon(new ImageIcon(frmTimerBoil.class.getResource("/jmash/images/button_ok.png")));
		toolBar.add(btnChiudi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("Button.background"));
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		splitPane.setLeftComponent(scrollPane);
		
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//loadContent();
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 9));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblMinuto = new JLabel("Minuto:");
		lblMinuto.setBounds(10, 11, 46, 14);
		panel.add(lblMinuto);
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setBounds(10, 36, 72, 14);
		panel.add(lblDescrizione);
		
		spinboiltime.setModel(new SpinnerNumberModel(0, null, maxtime, 1));
		spinboiltime.setBounds(74, 11, 46, 20);
		panel.add(spinboiltime);
		
		txtdesc = new JTextField();
		txtdesc.setPreferredSize(new Dimension(200, 24));
		txtdesc.setMinimumSize(new Dimension(100, 24));
		txtdesc.setBounds(72, 35, 212, 28);
		panel.add(txtdesc);
		txtdesc.setColumns(10);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStep();
			}
		});
		btnAggiungi.setBounds(74, 69, 91, 23);
		panel.add(btnAggiungi);
		
		popup = new JPopupMenu();
		menuItem= new JMenuItem("Elimina");
	    popup.add(menuItem);
	    list.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent me) {
	          // if right mouse button clicked (or me.isPopupTrigger())
	          if (SwingUtilities.isRightMouseButton(me)
	              && !list.isSelectionEmpty()
	              && list.locationToIndex(me.getPoint())
	                 == list.getSelectedIndex()) {
	        	  popup.show(list, me.getX(), me.getY());
	                  }
	              }
	           }
	        );
	    ActionListener menuListener = new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          removeStep();
	        }
	      };
	    menuItem.addActionListener(menuListener);

		InitForm();
		caricaSteps();
		mostraSteps();
	}
	private void InitForm()
	{
		setClosable(true);
        setIconifiable(true);
        setAutoscrolls(true);
	}
	private void caricaSteps()
	{
		
		for(int x=0;x<listasteps.getRowCount();x++)
		{
			if(!listasteps.getRow(x).getUso().equalsIgnoreCase("kettle")){continue;}
			int btime=listasteps.getRow(x).getBoilTime();
			btime=boiltime-btime;
			String nome=listasteps.getRow(x).getNome();
			String um=listasteps.getRow(x).getUnitaMisura();
			String desc;
			double qta=listasteps.getRow(x).getGrammi();
			int qtaint;
			if((qta == Math.floor(qta)) && !Double.isInfinite(qta))
			{
				qtaint=(int)qta;
				desc="Inserire "+ qtaint + " " + um + " di "+nome;
			}
			else
			{
				desc="Inserire "+ qta + " " + um + " di "+nome;
			}
			
			STEP s=new STEP(btime,desc);
			steps.add(s);
		}
	}
	private void mostraSteps()
	{
		Collections.sort(steps);
		listModel.removeAllElements();
		for(STEP s:steps)
		{
			String itemlayout="<html><strong>Minuto "+ s.minuto +"</strong><br>"+s.descrizione+"</html>";
			listModel.addElement(itemlayout);
		}
		creaXML();
	}
	private void addStep()
	{
		if(txtdesc.getText().trim().equalsIgnoreCase(""))return;
		STEP s=new STEP((Integer)spinboiltime.getValue(),txtdesc.getText());
		steps.add(s);
		txtdesc.setText("");
		mostraSteps();
	}
	private void removeStep()
	{
		steps.remove(list.getSelectedIndex());
		mostraSteps();
	}
	private void creaXML()
	{
		xmlText="<boilsteps birra='"+nomericetta+"' minuti='"+boiltime+"'>";
		for (STEP s:steps)
		{
			xmlText=xmlText+"<step><t>"+s.minuto+"</t><d>"+s.descrizione+"</d></step>";
		}
		xmlText+="</boilsteps>";
	}
}