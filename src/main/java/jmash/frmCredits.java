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

public class frmCredits extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088568590286636987L;
	private final JPanel contentPanel = new JPanel();
	private final JEditorPane txtthanks = new JEditorPane();
	private final JEditorPane txtApp = new JEditorPane(); 
	private final JEditorPane txtCredits = new JEditorPane();


	public frmCredits() 
	{
		this.setTitle("Info");
		setResizable(false);
		setBounds(100, 100, 427, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("<html>Ixtlanas<br>(project creator - programmer)</html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setIcon(new ImageIcon(frmCredits.class.getResource("/jmash/images/ixtlanas.jpg")));
		lblNewLabel_1.setBounds(10, 21, 259, 52);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblBasedOnHobbybrew = new JLabel();
		lblBasedOnHobbybrew.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblBasedOnHobbybrew.setText("Based on Hobbybrew 2.0.3");
		lblBasedOnHobbybrew.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasedOnHobbybrew.setBackground(Color.WHITE);
		lblBasedOnHobbybrew.setBounds(10, 80, 399, 14);
		contentPanel.add(lblBasedOnHobbybrew);

		txtthanks.setFont(new Font("Tahoma", Font.PLAIN, 6));
		txtthanks.setEditable(false);
		txtthanks.setContentType("text/html");
		txtthanks.setText("<html><span style='font-family:Tahoma; font-size: 9pt'><strong>Thanks to</strong><br>Jigen Daisuke (<a href=\"http://www.birrandosiimpara.it/birrabirra/index.php\">Birra Birra</a>)</span></html>");
		txtthanks.setBounds(10, 106, 299, 41);
		contentPanel.add(txtthanks);
		
		txtApp.setEditable(false);
		txtApp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtApp.setContentType("text/html");
		txtApp.setBounds(10, 6, 399, 24);
		txtApp.setText("<html><center><a href='http://brewplus.blogspot.it'>"+Main.Nome+" "+Main.versioneHobbyBrew+"</a></center></html>");
		contentPanel.add(txtApp);
		
		txtCredits.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCredits.setEditable(false);
		txtCredits.setContentType("text/html");
		txtCredits.setText("<html><span style='font-family:Tahoma; font-size: 9pt'><strong>Credits</strong><br>Inline help: Jigen Daisuke<br>\r\nSuggested ingredients: ab62, conco, John Priming, source, velleitario</span>\r\n</html>");
		txtCredits.setBounds(10, 145, 399, 84);
		contentPanel.add(txtCredits);
		
		
		//catch hyperlink
		try {
			//
			txtthanks.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) {
					try
	                {
			             if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
			            	 Desktop.getDesktop().browse( new URI("http://www.birrabirra.altervista.org"));
	                }
					catch(Exception ex)
	                {}
					
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			txtApp.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) {
					try
	                {
			             if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
			            	 Desktop.getDesktop().browse( new URI("http://brewplus.blogspot.it"));
	                }
					catch(Exception ex)
	                {}
					
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//
			txtCredits.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) {
					try
	                {
			             if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
			            	 Desktop.getDesktop().browse( new URI("http://www.areabirra.it"));
	                }
					catch(Exception ex)
	                {}
					
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		{
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
		}
	}
}
