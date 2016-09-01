package jmash;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Color;

public class Form1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtAaaaa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1 frame = new Form1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dati pH",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11)));
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCiccio = new JLabel("ciccio");
		GridBagConstraints gbc_lblCiccio = new GridBagConstraints();
		gbc_lblCiccio.insets = new Insets(0, 0, 0, 5);
		gbc_lblCiccio.gridx = 0;
		gbc_lblCiccio.gridy = 0;
		gbc_lblCiccio.gridheight = lblCiccio.getHeight();
		panel.add(lblCiccio, gbc_lblCiccio);
		
		JLabel lblCiccio2 = new JLabel("ciccio2");
		GridBagConstraints gbc_lblCiccio2 = new GridBagConstraints();
		gbc_lblCiccio2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiccio2.gridx = 1;
		gbc_lblCiccio2.gridy = 0;
		
				gbc_lblCiccio2.gridheight = lblCiccio2.getHeight();
				
				panel.add(lblCiccio2, gbc_lblCiccio2);
		
		txtAaaaa = new JTextField();
		txtAaaaa.setBackground(Color.YELLOW);
		txtAaaaa.setForeground(Color.BLACK);
		txtAaaaa.setToolTipText("zaaa");
		txtAaaaa.setText("aaaaa");
		GridBagConstraints gbc_txtAaaaa = new GridBagConstraints();
		gbc_txtAaaaa.anchor = GridBagConstraints.EAST;
		gbc_txtAaaaa.insets = new Insets(2, 2, 2, 2);
		gbc_txtAaaaa.fill = GridBagConstraints.BOTH;
		gbc_txtAaaaa.gridx = 4;
		gbc_txtAaaaa.gridy = 0;
		panel.add(txtAaaaa, gbc_txtAaaaa);
		txtAaaaa.setColumns(1);
	}

}
