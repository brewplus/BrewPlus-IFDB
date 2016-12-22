package jmash.utils;

public class Periods {
	private final Period basic = Period.NORMAL;
	private Period period;
	private Period alternative;

	public Periods() {
	}

	public Periods(Period period, Period alternative) {
		this.period = period;
		this.alternative = alternative;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Period getAlternative() {
		return alternative;
	}

	public void setAlternative(Period alternative) {
		this.alternative = alternative;
	}

	public Period getBasic() {
		return basic;
	}

	@Override
	public String toString() {
		return "Periods [basic=" + basic + ", period=" + period + ", alternative=" + alternative + "]";
	}

}
