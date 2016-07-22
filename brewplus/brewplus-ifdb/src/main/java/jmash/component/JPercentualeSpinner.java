package jmash.component;

public class JPercentualeSpinner extends JMultiUnitSpinner {

	private static final long serialVersionUID = 1L;

	public JPercentualeSpinner() {
		this(0.0, 1.0, "0.00", null);
	}
	
	public JPercentualeSpinner(double def, double step, String format, String name) {
		super(new String[] { "%" });
		getComboBox().setEditable(false);
		setModel(def, 0.0, 100.0, step, format, name);
	}

	@Override
	protected double getRealValue() {
		return getPercentuale();
	}

	public void setPercentuale(double V) {
		getSpinner().setDoubleValue(V);
	}

	public double getPercentuale() {
		return getSpinner().getDoubleValue();
	}
	
	

}
