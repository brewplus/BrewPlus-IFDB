package jmash;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Font;

public class frmQRCode extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4325011818507308835L;

	private final JPanel contentPanel = new JPanel();
	
	JLabel lblQRCode = new JLabel("");
	JSlider slider = new JSlider();
	String xmlText;

	/**
	 * Create the dialog.
	 */
	public frmQRCode(String xml) {
		setFont(new Font("Tahoma", Font.PLAIN, 11));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Scansiona");
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		xmlText=xml;
		setBounds(100, 100, 298, 311);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				
				panel.add(lblQRCode);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				slider.setVisible(false);
				slider.setBorder(null);
				slider.setPaintTicks(true);
				slider.setValue(200);
				slider.setMinorTickSpacing(50);
				slider.setMinimum(250);
				slider.setMaximum(400);
				buttonPane.add(slider);
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider)e.getSource();
					    if (!source.getValueIsAdjusting()) {creaQR(slider.getValue());}
					}
				});
			}
		}
		creaQR(slider.getValue());
	}
	
	private void creaQR(int pixel)
	{
		try {
			byte[] comp=Utils.compress(xmlText);
			String s = new sun.misc.BASE64Encoder().encode(comp);
			lblQRCode.setIcon(new ImageIcon(Utils.getQRCodeFromBase64(s,pixel)));
		} catch (IOException e2) {

		}
	}

}
