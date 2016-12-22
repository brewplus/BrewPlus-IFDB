package jmash;

/**
 * Creata per issue #47 per poter ordinare le quantita su tabella
 */
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
