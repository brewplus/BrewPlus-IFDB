package jmash.report.model;

import java.util.List;

import jmash.Hop;
import jmash.Malt;
import jmash.Yeast;

public class RecipeModel {
	
	private String totalLiters;	
	private String boilLiters;
	private String totalGrain;	
	private String og;	
	private String ogPreBoil;
	private String fg;	
	private String alcoolVolume;
	private String plato;
	private String ebc;	 
	private String ibu;	 
	private String efficency; 
	private String boilingTime;
	private String ratioLitreKg;
	private List<Hop> hops;
	private List<Malt> malts;
	private List<Mash> steps;
	private List<Yeast> yeasts;
	
	public List<Hop> getHops() {
		return hops;
	}
	public void setHops(List<Hop> hops) {
		this.hops = hops;
	}
	public List<Malt> getMalts() {
		return malts;
	}
	public void setMalts(List<Malt> malts) {
		this.malts = malts;
	}
	public String getTotalLiters() {
		return totalLiters;
	}
	public void setTotalLiters(String totalLiters) {
		this.totalLiters = totalLiters;
	}
	public String getBoilLiters() {
		return boilLiters;
	}
	public void setBoilLiters(String boilLiters) {
		this.boilLiters = boilLiters;
	}
	public String getTotalGrain() {
		return totalGrain;
	}
	public void setTotalGrain(String totalGrain) {
		this.totalGrain = totalGrain;
	}
	public String getOg() {
		return og;
	}
	public void setOg(String og) {
		this.og = og;
	}
	public String getOgPreBoil() {
		return ogPreBoil;
	}
	public void setOgPreBoil(String ogPreBoil) {
		this.ogPreBoil = ogPreBoil;
	}
	public String getFg() {
		return fg;
	}
	public void setFg(String fg) {
		this.fg = fg;
	}
	public String getAlcoolVolume() {
		return alcoolVolume;
	}
	public void setAlcoolVolume(String alcoolVolume) {
		this.alcoolVolume = alcoolVolume;
	}
	public String getPlato() {
		return plato;
	}
	public void setPlato(String plato) {
		this.plato = plato;
	}
	public String getEbc() {
		return ebc;
	}
	public void setEbc(String ebc) {
		this.ebc = ebc;
	}
	public String getIbu() {
		return ibu;
	}
	public void setIbu(String ibu) {
		this.ibu = ibu;
	}
	public String getEfficency() {
		return efficency;
	}
	public void setEfficency(String efficency) {
		this.efficency = efficency;
	}
	public String getBoilingTime() {
		return boilingTime;
	}
	public void setBoilingTime(String boilingTime) {
		this.boilingTime = boilingTime;
	}
	public List<Mash> getSteps() {
		return steps;
	}
	public void setSteps(List<Mash> steps) {
		this.steps = steps;
	}
	public List<Yeast> getYeasts() {
		return yeasts;
	}
	public void setYeasts(List<Yeast> yeasts) {
		this.yeasts = yeasts;
	}
	public String getRatioLitreKg() {
		return ratioLitreKg;
	}
	public void setRatioLitreKg(String ratioLitreKg) {
		this.ratioLitreKg = ratioLitreKg;
	}
	
}
