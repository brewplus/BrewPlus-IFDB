package jmash;

public class Quantita {
	
	private String number;
	private String unitaMisura;
	
	public Quantita(String number) {
		this.number = number;
	}

	public String getValue() {
		return number;
	}
	
	@Override
	public String toString() {
		return number;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

}
