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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class frmModelliNote extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218837417290578L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private JEditorPane txtNoteA; 
	private JEditorPane txtNoteB; 

	public frmModelliNote() 
	{
		this.setTitle("Gestione modelli delle note");
		setResizable(false);
		setBounds(100, 100, 515, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblOgInPentola = new JLabel("Testo predefinito Note cotta:");
		lblOgInPentola.setBounds(10, 11, 497, 14);
		contentPanel.add(lblOgInPentola);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 487, 126);
		contentPanel.add(scrollPane);
		
		txtNoteA= new JEditorPane();
		txtNoteA.setToolTipText("Il testo qui digitato verr\u00E0 automaticamente applicato ad ogni nuova cotta");
		scrollPane.setViewportView(txtNoteA);
		
		JLabel lblTestoPredefinitoNote = new JLabel("Testo predefinito Note fermentazione:");
		lblTestoPredefinitoNote.setBounds(10, 175, 497, 14);
		contentPanel.add(lblTestoPredefinitoNote);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 190, 487, 126);
		contentPanel.add(scrollPane_1);
		
		txtNoteB = new JEditorPane();
		txtNoteB.setToolTipText("Il testo qui digitato verr\u00E0 automaticamente applicato ad ogni nuova cotta");
		scrollPane_1.setViewportView(txtNoteB);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.control);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JButton cmdSave = new JButton("Salva");
				cmdSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SaveNote();
					}
				});
				buttonPane.add(cmdSave);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		LoadNote();
		
	}
	
	private void SaveNote()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter( new FileWriter( "config/NoteA.note"));
			writer.write(txtNoteA.getText().trim());
			writer.close();
		}
		catch(Exception ex){}
		try
		{
			BufferedWriter writer = new BufferedWriter( new FileWriter( "config/NoteB.note"));
			writer.write(txtNoteB.getText().trim());
			writer.close();
		}
		catch(Exception ex){}
	}
	
	private void LoadNote()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("config/NoteA.note"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            	}
           txtNoteA.setText(sb.toString());
           br.close();
            }
            catch(Exception ex){}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("config/NoteB.note"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            	}
           txtNoteB.setText(sb.toString());
           br.close();
            }
            catch(Exception ex){}
            

	}
	
}
