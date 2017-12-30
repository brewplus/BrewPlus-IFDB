package jmash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.log4j.Logger;

public class frmCredits extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088568590286636987L;
	private static Logger LOGGER = Logger.getLogger(frmCredits.class);
	private final JPanel contentPanel = new JPanel();
	private final JEditorPane txtthanks = new JEditorPane();

	public frmCredits() {
		this.setTitle("Info");
		setResizable(false);
		setBounds(100, 100, 450, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtthanks.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtthanks.setEditable(false);
		txtthanks.setContentType("text/html");
		txtthanks.setText("<html><head></head><body><p style=\"margin-top: 0\">"
		        // title version
		        + "<span style='font-family:Tahoma; font-size: 18pt; font-weight: bold'>"
		        + "<center><a href='https://github.com/rekhyt75/BrewPlus-IFDB/releases'>" + Main.Nome + " " + Utils.getVersion() + "</a></center>"
		        + "<br><br></span>"
		        // logo
		        + "<span style='font-family:Tahoma; font-size: 14pt'>"
		        + "<center>"
                + "<img src=\"" + frmCredits.class.getResource("/jmash/images/logo-min.png") + "\">"
                + "<br>"
                + "<a href=\"http://www.ilforumdellabirra.net\">Il Forum Della Birra</a>"
                + "<br>Based on Hobbybrew 2.0.3 and BrewPlus 1.5.0 ( by Ixtlanas )"
                + "</center>"
                + "<br><br></span>"
                // thanks
		        +"<span style='font-family:Tahoma; font-size: 12pt'>"
		        + "<strong>Contributors:</strong>"
		        + "<br>Michele \"Rekhyt\" Antonecchia  (Telegram: " + generateTelegramUrl("rekhyt") + " )"
		        + "<br>Alessandro \"pera76\" Peruzzi (Email: " + generateEMailAddress("peruzzi.alessandro","gmail.com") + " )"
		        + "<br>Angelo \"Girella\" Cerella (Email: " + generateEMailAddress("angelo.cerella","gmail.com") + " )"
				+ "<br>Diego \"DellaBotte\" Varriale  (Telegram: " + generateTelegramUrl("DiegoDellaBotte") + " )"
				+ "<br>Giovanni \"Sgabuzen\" Iovane ( Email: " + generateEMailAddress("info", "sgabuzen.com") + " )"
				+ "<br><br><strong>For Italian translation of BJCP, Malt, Hop and Yeast lists:</strong>"
                + "<br>Luciano \"BATIGOLLE\" Picchioni ( Email: " + generateEMailAddress("batigolle", "gmail.com") + " )"
                + "<br>Massimo \"superpiggy\" Scalvini (Email: " + generateEMailAddress("m.scalvini.80","gmail.com") + " )"
                + "<br>Vito Fasano (Telegram: " + generateTelegramUrl("VitoFasano") +" - Email: " + generateEMailAddress("vito.fasano","gmail.com") + " )"
                + "<br><br><strong>Export to Brewing controller in collaboration with Andrea Fantin of IMELAB</strong>"
                + "<br><br><strong>Special thanks to:</strong>"
                + "<br>Antonio De Feo (Telegram: " + generateTelegramUrl("JigenDaisuke") +" )"
                + "<br>Stefano Longo (Telegram: " + generateTelegramUrl("Essetielle") + " - Email: " + generateEMailAddress("stl.lecce","gmail.com") + " )"
                + "<br><br></span>"
                // License and disclaimer
                + "<span style='font-family:Tahoma; font-size: 10pt'>"
                + "<strong>License: </strong><a href=\"https://www.gnu.org/licenses/gpl-3.0.html\">GNU GPL v3.0</a><br>"
                + "<strong>Disclaimer: </strong>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details."
                + "<br><br></span>"
                + "<span style='font-family:Tahoma; font-size: 12pt'>"
                + "<br><br><strong>Software hosted on GitHub: </strong><a href=\"https://github.com/rekhyt75/BrewPlus-IFDB\">BrewPlus-IFDB</a><br>"
		        + "</span></p></body></html>");
		txtthanks.setBounds(10, 20, 400, 800);
		contentPanel.add(txtthanks);

		// catch hyperlink
		try {
			//
			txtthanks.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) {
					try {
						if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
						    Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (Exception ex) {
					}

				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
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
	
	// anti spam method to prevents email address discovered from source code 
	private String generateEMailAddress(String name, String domain){
	    
	    StringBuffer mail = new StringBuffer();
	    mail.append(name).append("@").append(domain);
	    StringBuffer sb = new StringBuffer();
	    sb.append("<a href=\"mailto:")
	    .append(mail)
	    .append("\">")
	    .append(mail)
	    .append("</a>");
        return sb.toString();
	    
	}
	
	// method to create telegram url
	private String generateTelegramUrl(String nickname){
	    StringBuffer telegram = new StringBuffer();
	    telegram.append("https://telegram.me/").append(nickname);
	    
	    StringBuffer sb = new StringBuffer();
        sb.append("<a href=\"")
        .append(telegram)
        .append("\">@")
        .append(nickname)
        .append("</a>");
        return sb.toString();
	}
}
