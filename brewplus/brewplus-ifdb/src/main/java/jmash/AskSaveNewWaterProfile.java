package jmash;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
public class AskSaveNewWaterProfile extends Ask {

	private static final long serialVersionUID = 1L;
	private WaterProfile waterProfile;

	private JLabel lblNomeProfilo;

	private JTextField txtNomeProfilo;

	public AskSaveNewWaterProfile(WaterProfile waterProfile, String msg) {
		super(msg);
		this.waterProfile = waterProfile;
	}

	@Override
	protected void initComponents() {
		super.initComponents();

		lblNomeProfilo = new JLabel("Nome acqua");

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

		checkFields();

	}

	private void checkFields() {
		String nome = txtNomeProfilo.getText();
		if (StringUtils.isBlank(nome)) {
			getOkButton().setEnabled(false);
		} else {
			if (Gui.waterPickerTableModel.findWaterProfile(nome.trim()) == null) {
				getOkButton().setEnabled(true);
			} else {
				getOkButton().setEnabled(false);
			}
		}
	}

	public WaterProfile getWaterProfile() {
		return waterProfile;
	}

	public boolean doAsk(JInternalFrame frame) {

		startModal(frame);
		return get();
	}

	public String getNomeProfilo() {
		return txtNomeProfilo.getText().trim();
	}

}
