package jmash;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Peruzzi Alessandro
 *
 */
public class AskSaveNewBrewingProfile extends Ask {

	private static final long serialVersionUID = 1L;
	private BreweryProfile breweryProfile;

	private JLabel lblNomeProfilo;
	private JLabel lblDescrizioneProfilo;

	private JTextField txtNomeProfilo;
	private JTextField txtDescrizioneProfilo;

	public AskSaveNewBrewingProfile(BreweryProfile breweryProfile, String msg) {
		super(msg);
		this.breweryProfile = breweryProfile;
	}

	@Override
	protected void initComponents() {
		super.initComponents();

		lblNomeProfilo = new JLabel("Nome profilo");
		lblDescrizioneProfilo = new JLabel("Descrizione profilo");

		GridBagConstraints gbc_lblNomeProfilo = new GridBagConstraints();
		gbc_lblNomeProfilo.anchor = GridBagConstraints.EAST;
		gbc_lblNomeProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeProfilo.gridx = 2;
		gbc_lblNomeProfilo.gridy = 1;
		getCentralPanel().add(lblNomeProfilo, gbc_lblNomeProfilo);

		txtNomeProfilo = new JTextField();
		txtNomeProfilo.setPreferredSize(new Dimension(150, 22));
		GridBagConstraints gbc_fieldNomeProfilo = new GridBagConstraints();
		gbc_fieldNomeProfilo.anchor = GridBagConstraints.WEST;
		gbc_fieldNomeProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNomeProfilo.gridx = 3;
		gbc_fieldNomeProfilo.gridy = 1;
		getCentralPanel().add(txtNomeProfilo, gbc_fieldNomeProfilo);

		GridBagConstraints gbc_lblDescrizioneProfilo = new GridBagConstraints();
		gbc_lblDescrizioneProfilo.anchor = GridBagConstraints.EAST;
		gbc_lblDescrizioneProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrizioneProfilo.gridx = 2;
		gbc_lblDescrizioneProfilo.gridy = 2;
		getCentralPanel().add(lblDescrizioneProfilo, gbc_lblDescrizioneProfilo);

		txtDescrizioneProfilo = new JTextField();
		txtDescrizioneProfilo.setPreferredSize(new Dimension(300, 22));
		GridBagConstraints gbc_fieldDescrizioneProfilo = new GridBagConstraints();
		gbc_fieldDescrizioneProfilo.anchor = GridBagConstraints.WEST;
		gbc_fieldDescrizioneProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDescrizioneProfilo.gridx = 3;
		gbc_fieldDescrizioneProfilo.gridy = 2;
		getCentralPanel().add(txtDescrizioneProfilo, gbc_fieldDescrizioneProfilo);

		txtNomeProfilo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkFields();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkFields();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkFields();
				
			}
		});

			

		txtDescrizioneProfilo.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e)  {
				checkFields();
			}
		});
		
		txtDescrizioneProfilo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkFields();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkFields();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkFields();
				
			}
		});
		
		checkFields();

	}

	private void checkFields() {
		if (StringUtils.isBlank(txtNomeProfilo.getText()) || StringUtils.isBlank(txtDescrizioneProfilo.getText())) {
			getOkButton().setEnabled(false);
		} else {
			getOkButton().setEnabled(true);
		}
	}

	public BreweryProfile getBreweryProfile() {
		return breweryProfile;
	}

	public boolean doAsk(JInternalFrame frame) {
		
		startModal(frame);
		return get();
	}
	
	public String getNomeProfilo() {
		return txtNomeProfilo.getText().trim();
	}
	
	public String getDescrizioneProfilo() {
		return txtDescrizioneProfilo.getText().trim();
	}

}
