package jmash;

import java.io.Serializable;

public class PHResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double alk = Double.NaN;
	private Double RA = Double.NaN;
	private Double pH = Double.NaN;

	private Double totalAcidGrainWeightGr = 0.0;

	public PHResult() {

	}

	public Double getAlk() {
		return alk;
	}

	public void setAlk(Double alk) {
		this.alk = alk;
	}

	public Double getRA() {
		return RA;
	}

	public void setRA(Double rA) {
		RA = rA;
	}

	public Double getpH() {
		return pH;
	}

	public void setpH(Double pH) {
		if (pH < 0.0) {
			this.pH = 0.0;
		} else if (pH >= 14.0) {
			this.pH = 14.0;
		}
		this.pH = pH;
	}

	public void setTotalAcidGrainWeightGr(Double totalAcidGrainWeightGr) {
		this.totalAcidGrainWeightGr = totalAcidGrainWeightGr;
	}

	public Double getTotalAcidGrainWeightGr() {
		return totalAcidGrainWeightGr;
	}

}
