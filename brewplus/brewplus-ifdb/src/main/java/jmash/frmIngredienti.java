package jmash;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;

public class frmIngredienti extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088568590286636987L;
	private final JPanel contentPanel = new JPanel();

	public frmIngredienti(BrewStyle bs) {
		setClosable(true);
		this.setTitle("BJCP: " + bs.getNome());
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 546, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		contentPanel.add(textPane);
		
		String html = Utils.getBJCPHtml(bs);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		textPane.setText(html);
		textPane.setCaretPosition(0);

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
