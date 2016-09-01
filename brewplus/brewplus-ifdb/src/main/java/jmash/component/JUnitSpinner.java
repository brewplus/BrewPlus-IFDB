package jmash.component;

public class JUnitSpinner extends JMultiUnitSpinner {

	private static final long serialVersionUID = 6146495040385760354L;

	/**
	 * @wbp.parser.constructor
	 */
	public JUnitSpinner(String unit)
	{
		this(unit, 0.0, 0.0, 9999999999.99, 0.5, "0.00", null);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public JUnitSpinner(String unit, int widthUnit)
	{
		this(unit, 0.0, 0.0, 9999999999.99, 0.5, "0.00", null, widthUnit);
	}
	
	
	public JUnitSpinner(String unit, double def, double min, double max, double step, String format, String name) {
		super(new String[] { unit });
		getComboBox().setEditable(false);
		setModel(def, min, max, step, format, name);
	}
	
	public JUnitSpinner(String unit, double def, double min, double max, double step, String format, String name, int widthUnit) {
		super(new String[] { unit }, widthUnit);
		getComboBox().setEditable(false);
		setModel(def, min, max, step, format, name);
	}

	@Override
	protected double getRealValue() {
		return getSpinner().getDoubleValue();
	}
	
	public double getDoubleValue() {
		return getRealValue();
	}
	
	
}
