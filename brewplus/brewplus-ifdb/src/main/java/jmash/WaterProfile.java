/*

 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package jmash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jdom.Element;
import jmash.interfaces.XmlAble;

public class WaterProfile implements XmlAble, Comparable<WaterProfile> {

	private static Logger LOGGER = Logger.getLogger(WaterProfile.class);

	private String nome;
	private Integer type;
	private Double calcio;
	private Double sodio;
	private Double magnesio;
	private Double solfato;
	private Double cloruro;
	private Double carbonato;
	private double diff;
	private Double gypsum = 0.0;
	private Double calciumChloride = 0.0;
	private Double sale = 0.0;
	private Double epsom = 0.0;
	private Double chalk = 0.0;
	private Double soda = 0.0;
	private Double slakedLime = 0.0;
	private Boolean useGypsum = true;
	private Boolean useCaCl2 = true;
	private Boolean useNaCl = true;
	private Boolean useEpsom = true;
	private Boolean useChalk = true;
	private Boolean useSoda = true;
	private Boolean useSlakedLime = true;
	private Boolean useGypsumSparge = true;
	private Boolean useCaCl2Sparge = true;
	private Boolean useNaClSparge = true;
	private Boolean useEpsomSparge = true;
	private Boolean useChalkSparge = true;
	private Boolean useSodaSparge = true;
	private Boolean useSlakedLimeSparge = true;
	private static int pCalcio = 50, pSolfato = 50, pCloruro = 50, pSodio = 50, pMagnesio = 50, pCarbonato = 50;

	private Double acidulatedMaltContent = 2.0;
	private Double lacticAcid = 0.0;
	private Double lacticAcidContent = 88.0;
	private Double citrusAcid = 0.0;
	private Double citrusAcidContent = 88.0;

	public static Random R = new Random();

	public WaterProfile() {
		this.calcio = 0.0;
		this.magnesio = 0.0;
		this.solfato = 0.0;
		this.cloruro = 0.0;
		this.sodio = 0.0;
		this.carbonato = 0.0;
	}

	public WaterProfile(double Ca, double Mg, double So, double Cl, double Na, double carbonato) {
		this.calcio = Ca;
		this.magnesio = Mg;
		this.solfato = So;
		this.cloruro = Cl;
		this.sodio = Na;
		this.carbonato = carbonato;
	}

	public double diff(WaterProfile dest) {
		return Math.pow(((double) pCalcio), 5) * Math.pow(getCalcioTotale() - dest.getCalcioTotale(), 2)
				+ Math.pow(((double) pSolfato), 5) * Math.pow(getSolfatoTotale() - dest.getSolfatoTotale(), 2)
				+ Math.pow(((double) pSodio), 5) * Math.pow(getSodioTotale() - dest.getSodioTotale(), 2)
				+ Math.pow(((double) pMagnesio), 5) * Math.pow(getMagnesioTotale() - dest.getMagnesioTotale(), 2)
				+ Math.pow(((double) pCloruro), 5) * Math.pow(getCloruroTotale() - dest.getCloruroTotale(), 2)
				+ Math.pow(((double) pCarbonato), 5) * Math.pow(getCarbonatoTotale() - dest.getCarbonatoTotale(), 2);

	}

	public double getCalcioTotale() {
		return this.calcio + (this.chalk * 105.8 + this.gypsum * 61.5 + this.calciumChloride * 72) / 1000;
	}

	public double getMagnesioTotale() {
		return this.magnesio + (this.epsom * 26.1) / 1000;
	}

	public double getSolfatoTotale() {
		return this.solfato + (this.gypsum * 147.4 + this.epsom * 103) / 1000;
	}

	public double getCloruroTotale() {
		return this.cloruro + (this.calciumChloride * 127.4 + this.sale * 160.3) / 1000;
	}

	public double getSodioTotale() {
		return this.sodio + (this.sale * 103.9 + this.soda * 72) / 1000;
	}

	public double getCarbonatoTotale() {
		return this.carbonato + (this.chalk * 158.4 + this.soda * 189) / 1000;
	}

	@Override
	public WaterProfile clone() {
		WaterProfile p = new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio,
				this.carbonato);
		p.gypsum = this.gypsum;
		p.sale = this.sale;
		p.epsom = this.epsom;
		p.calciumChloride = this.calciumChloride;
		p.chalk = this.chalk;
		p.soda = this.soda;
		p.slakedLime = this.slakedLime;

		p.acidulatedMaltContent = this.acidulatedMaltContent;
		p.lacticAcid = this.lacticAcid;
		p.lacticAcidContent = this.lacticAcidContent;
		p.citrusAcid = this.citrusAcid;
		p.citrusAcidContent = this.citrusAcidContent;
		p.diff = this.diff;
		return p;
	}

	public WaterProfile target(WaterProfile dest, double LITRI, String name, int recusions, List<WaterProfile> L,
			int POPULATION) {
		if (L == null)
			L = new ArrayList<WaterProfile>();
		if (L.size() == 0) {
			for (int i = 0; i < POPULATION; i++) {
				WaterProfile p = new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio,
						this.carbonato);
				/*
				 * p.gypsum=R.nextInt(1000); p.sale=R.nextInt(1000);
				 * p.epsom=R.nextInt(1000); p.calciumCloride=R.nextInt(1000);
				 */
				L.add(p);
			}
		}
		for (int k = 0; k < recusions; k++) {

			WaterProfile best = null;
			for (int i = 0; i < L.size(); i++) {
				WaterProfile p = L.get(i);
				p.diff = p.diff(dest);
			}

			Collections.sort(L, new Compare());
			best = L.get(
					0);/*
						 * System.out.println(k+") first.diff="+best.diff +
						 * ", \tgypsum="+best.gypsum+ ", calciumChloride="
						 * +best.calciumCloride+ ", sale="+best.sale+ ", epsom="
						 * +best.epsom );
						 * System.out.println("\tcalcio="+best.getCalcio()+"("+
						 * dest.calcio+")"+ ", magnesio="
						 * +best.getMagnesio()+"("+dest.magnesio+")"+
						 * ", cloruri="+best.getCloruri()+"("+dest.cloruri+")"+
						 * ", solfato="+best.getSolfato()+"("+dest.solfato+")"+
						 * ", sodio="+best.getSodio()+"("+dest.sodio+")" );
						 */
			if (best.diff == 0) {
				break;
			}
			int S = 15;
			List<WaterProfile> LL = new ArrayList<WaterProfile>();
			for (int i = 0; i < S; i++) {
				WaterProfile p = (L.get(i)).clone();
				LL.add(p);
			}
			L.clear();
			// for (int j = 0; j < 3; j++)

			for (int i = 0; i < S; i++) {
				L.add(LL.get(i));
			}
			int GRAMS_VAR = 5000;
			while (L.size() < POPULATION) {
				for (int i = 0; i < S; i++) {
					WaterProfile p = (LL.get(i)).clone();
					// if(R.nextInt()%2==0)
					p.gypsum = p.gypsum + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					// if(R.nextInt()%2==0)
					p.sale = p.sale + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					// if(R.nextInt()%2==0)
					p.calciumChloride = p.calciumChloride + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					// if(R.nextInt()%2==0)
					p.epsom = p.epsom + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					p.chalk = p.chalk + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					p.soda = p.soda + R.nextInt(GRAMS_VAR) - GRAMS_VAR / 2;
					if (p.gypsum < 0) {
						p.gypsum = 0.0;
					}
					if (p.sale < 0) {
						p.sale = 0.0;
					}
					if (p.epsom < 0) {
						p.epsom = 0.0;
					}
					if (p.chalk < 0) {
						p.chalk = 0.0;
					}
					if (p.soda < 0) {
						p.soda = 0.0;
					}
					if (p.calciumChloride < 0) {
						p.calciumChloride = 0.0;
					}
					if (p.slakedLime < 0) {
						p.slakedLime = 0.0;
					}
					
					if (!useSoda)
						p.soda = 0.0;
					if (!useChalk)
						p.chalk = 0.0;
					if (!useGypsum)
						p.gypsum = 0.0;
					if (!useCaCl2)
						p.calciumChloride = 0.0;
					if (!useNaCl)
						p.sale = 0.0;
					if (!useEpsom)
						p.epsom = 0.0;
					if (!useSlakedLime)
						p.slakedLime = 0.0;

					L.add(p);
				}
			}
			;
			int SEED = 100;
			for (int i = 0; i < SEED; i++) {
				WaterProfile p = new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio,
						this.carbonato);
				p.gypsum = (double) R.nextInt(10000);
				p.sale = (double) R.nextInt(10000);
				p.epsom = (double) R.nextInt(10000);
				p.calciumChloride = (double) R.nextInt(10000);
				p.soda = (double) R.nextInt(10000);
				p.chalk = (double) R.nextInt(10000);
				if (!useSoda)
					p.soda = 0.0;
				if (!useChalk)
					p.chalk = 0.0;
				if (!useGypsum)
					p.gypsum = 0.0;
				if (!useCaCl2)
					p.calciumChloride = 0.0;
				if (!useNaCl)
					p.sale = 0.0;
				if (!useEpsom)
					p.epsom = 0.0;

				L.add(p);
			}
		}
		WaterProfile res = L.get(0);
		/*
		 * System.out.println( "Per "+LITRI+" litri di '"+name+"',"+
		 * "\n\t"+((res.gypsum*Utils.litToGal(LITRI))/1000)+" gr di gypsum "+
		 * "\n\t"+((res.calciumCloride*Utils.litToGal(LITRI))/1000)+
		 * " gr di cloruro di calcio"+
		 * "\n\t"+((res.sale*Utils.litToGal(LITRI))/1000)+
		 * " gr di cloruro di sodio (sale)"+
		 * "\n\t"+((res.epsom*Utils.litToGal(LITRI))/1000)+" gr di epsom"+
		 * "\n\t"+((res.chalk*Utils.litToGal(LITRI))/1000)+" gr di chalk"+
		 * "\n\t"+((res.soda*Utils.litToGal(LITRI))/1000)+" gr di soda"+
		 * "\ncalcio="+res.getCalcioTotale()+"("+dest.calcio+")"+ ", magnesio="
		 * +res.getMagnesioTotale()+"("+dest.magnesio+")"+ ", cloruri="
		 * +res.getCloruroTotale()+"("+dest.cloruro+")"+ ", solfato="
		 * +res.getSolfatoTotale()+"("+dest.solfato+")"+ ", sodio="
		 * +res.getSodioTotale()+"("+dest.sodio+")\n"+ ", carbonato="
		 * +res.getCarbonatoTotale()+"("+dest.sodio+")\n" );
		 */
		return res;
	}

	private class Compare implements Comparator<WaterProfile> {
		public int compare(WaterProfile a, WaterProfile b) {
			WaterProfile p = a;
			WaterProfile q = b;
			return new Double(p.diff).compareTo(new Double(q.diff));
		}
	};

	public static void main(String[] args) {

		WaterProfile burton = new WaterProfile(295, 25, 725, 25, 55, 150);
		WaterProfile bitter = new WaterProfile(170, 15, 400, 200, 55, 150);
		WaterProfile porter = new WaterProfile(120, 10, 100, 300, 15, 150);
		WaterProfile mild = new WaterProfile(120, 10, 150, 200, 15, 150);

		WaterProfile dublin = new WaterProfile(115, 4, 55, 19, 12, 50);
		WaterProfile munich = new WaterProfile(70, 18, 7, 2, 10, 50);
		WaterProfile vienna = new WaterProfile(200, 60, 125, 12, 8, 50);
		WaterProfile pilsen = new WaterProfile(7, 5, 5, 5, 25, 50);
		WaterProfile paleAle = new WaterProfile(100, 10, 200, 20, 10, 50);
		WaterProfile norda = new WaterProfile(10, 2, 5, 0, 2, 50);
		try {
			int L = 40;
			int LITRI = L;
			// norda.target(munich, L, "munich");
			// norda.target(burton, L, "burton");
			WaterProfile res = norda.target(bitter, L, "bitter", 1000, null, 100);

			System.out.println("Per " + LITRI + " litri di '" + res.getNome() + "'," + "\n\t"
					+ ((res.getGypsum() * Utils.litToGal(LITRI)) / 1000) + " gr di gypsum " + "\n\t"
					+ ((res.getCalciumChloride() * Utils.litToGal(LITRI)) / 1000) + " gr di cloruro di calcio" + "\n\t"
					+ ((res.getSale() * Utils.litToGal(LITRI)) / 1000) + " gr di cloruro di sodio (sale)" + "\n\t"
					+ ((res.getEpsom() * Utils.litToGal(LITRI)) / 1000) + " gr di epsom" + "\n\t"
					+ ((res.getChalk() * Utils.litToGal(LITRI)) / 1000) + " gr di chalk" + "\n\t"
					+ ((res.getSoda() * Utils.litToGal(LITRI)) / 1000) + " gr di soda" + "\ncalcio="
					+ res.getCalcioTotale() + "(" + norda.getCalcio() + ")" + ", magnesio=" + res.getMagnesioTotale()
					+ "(" + norda.getMagnesio() + ")" + ", cloruri=" + res.getCloruroTotale() + "(" + norda.getCloruro()
					+ ")" + ", solfato=" + res.getSolfatoTotale() + "(" + norda.getSolfato() + ")" + ", sodio="
					+ res.getSodioTotale() + "(" + norda.getSodio() + ")" + ", carbonato=" + res.getCarbonatoTotale()
					+ "(" + norda.getCarbonato() + ")\n");
			norda.target(porter, L, "porter", 1000, null, 100);
			norda.target(mild, L, "mild", 1000, null, 100);
			// norda.target(paleAle, L, "paleAle");
			// norda.target(dublin, L, "dublin");
			// norda.target(vienna, L, "vienna");
			// norda.target(pilsen, L, "pilsen");

			System.out.println("-------------------");

			WaterProfile res2 = norda.target(bitter, L, "bitter", 1000, null, 100);
			System.out.println(res2.toXmlPlus().getAttributes());

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	public static Random getR() {
		return R;
	}

	public static void setR(Random r) {
		R = r;
	}

	public Double getCalcio() {
		return this.calcio;
	}

	public void setCalcio(Double calcio) {
		this.calcio = calcio;
	}

	public Double getCalciumChloride() {
		return this.calciumChloride;
	}

	public void setCalciumChloride(Double calciumChloride) {
		this.calciumChloride = calciumChloride;
	}

	public Double getCloruro() {
		return this.cloruro;
	}

	public void setCloruro(Double cloruro) {
		this.cloruro = cloruro;
	}

	public Double getCarbonato() {
		return this.carbonato;
	}

	public void setCarbonato(Double carbonato) {
		this.carbonato = carbonato;
	}

	public double getDiff() {
		return this.diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public Double getEpsom() {
		return this.epsom;
	}

	public void setEpsom(Double epsom) {
		this.epsom = epsom;
	}

	public Double getGypsum() {
		return this.gypsum;
	}

	public void setGypsum(Double gypsum) {
		this.gypsum = gypsum;
	}

	public Double getMagnesio() {
		return this.magnesio;
	}

	public void setMagnesio(Double magnesio) {
		this.magnesio = magnesio;
	}

	public Double getSale() {
		return this.sale;
	}

	public void setSale(Double sale) {
		this.sale = sale;
	}

	public Double getChalk() {
		return this.chalk;
	}

	public void setChalk(Double chalk) {
		this.chalk = chalk;
	}

	public Double getSoda() {
		return this.soda;
	}

	public void setSoda(Double soda) {
		this.soda = soda;
	}

	public Double getSodio() {
		return this.sodio;
	}

	public void setSodio(Double sodio) {
		this.sodio = sodio;
	}

	public Double getSolfato() {
		return this.solfato;
	}

	public void setSolfato(Double solfato) {
		this.solfato = solfato;
	}

	public int getPCalcio() {
		return pCalcio;
	}

	public void setPCalcio(int pCalcio) {
		WaterProfile.pCalcio = pCalcio;
	}

	public int getPSolfato() {
		return pSolfato;
	}

	public void setPSolfato(int pSolfato) {
		WaterProfile.pSolfato = pSolfato;
	}

	public int getPCloruro() {
		return pCloruro;
	}

	public void setPCloruro(int pCloruro) {
		WaterProfile.pCloruro = pCloruro;
	}

	public int getPSodio() {
		return pSodio;
	}

	public void setPSodio(int pSodio) {
		WaterProfile.pSodio = pSodio;
	}

	public int getPMagnesio() {
		return pMagnesio;
	}

	public void setPMagnesio(int pMagnesio) {
		WaterProfile.pMagnesio = pMagnesio;
	}

	public void setPCarbonato(int pCarbonato) {
		WaterProfile.pCarbonato = pCarbonato;
	}

	public static WaterProfile fromXml(Element elem) {
		WaterProfile waterProfile = new WaterProfile(0, 0, 0, 0, 0, 0);
		try {
			waterProfile = (WaterProfile) Utils.fromXml(waterProfile, campiXmlPlus, elem);
		} catch (Exception ex) {
			Utils.showException(ex);
		}
		return waterProfile;
	}

	public static WaterProfile fromXmlPlus(Element elem) {
		WaterProfile waterProfile = new WaterProfile(0, 0, 0, 0, 0, 0);
		try {
			waterProfile = (WaterProfile) Utils.fromXml(waterProfile, campiXmlPlus, elem);
		} catch (Exception ex) {
			Utils.showException(ex);
		}
		return waterProfile;
	}

	// public static String campiXml[]=new
	// String[]{"nome","type","calcio","magnesio","solfato","cloruro","sodio",
	// "carbonato"};
	// ixtlanas NO TYPE
	public static String campiXml[] = new String[] { "nome", "calcio", "magnesio", "solfato", "cloruro", "sodio",
			"carbonato" };

	public static String campiXmlPlus[] = new String[] { "nome", "type", "calcio", "magnesio", "solfato", "cloruro",
			"sodio", "carbonato", "gypsum", "sale", "epsom", "calciumChloride", "chalk", "soda", "slakedLime",
			"acidulatedMaltContent", "lacticAcid", "lacticAcidContent", "citrusAcid", "citrusAcidContent", "useGypsum",

			"useCaCl2", "useNaCl", "useEpsom", "useChalk", "useSoda", "useSlakedLime", "useGypsumSparge",
			"useCaCl2Sparge", "useNaClSparge", "useEpsomSparge", "useChalkSparge", "useSodaSparge",
			"useSlakedLimeSparge" };

	@Override
	public Element toXml() {
		Element malt = new Element("waterProfile");
		try {
			return Utils.toXml(this, campiXml);
		} catch (Exception ex) {
			Utils.showException(ex);
		}
		return malt;
	}

	public Element toXmlPlus() {
		Element malt = new Element("waterProfile");
		try {
			return Utils.toXml(this, campiXmlPlus);
		} catch (Exception ex) {
			Utils.showException(ex);
		}
		return malt;
	}

	public void setUseGypsum(Boolean useGypsum) {
		this.useGypsum = useGypsum;
	}

	public void setUseCaCl2(Boolean useCaCl2) {
		this.useCaCl2 = useCaCl2;
	}

	public void setUseNaCl(Boolean useNaCl) {
		this.useNaCl = useNaCl;
	}

	public void setUseEpsom(Boolean useEpsom) {
		this.useEpsom = useEpsom;
	}

	public void setUseChalk(Boolean useChalk) {
		this.useChalk = useChalk;
	}

	public void setUseSoda(Boolean useSoda) {
		this.useSoda = useSoda;
	}

	public void setUseSlakedLime(Boolean useSlakedLime) {
		this.useSlakedLime = useSlakedLime;
	}

	public void setUseCaCl2Sparge(Boolean useCaCl2Sparge) {
		this.useCaCl2Sparge = useCaCl2Sparge;
	}

	public void setUseChalkSparge(Boolean useChalkSparge) {
		this.useChalkSparge = useChalkSparge;
	}

	public void setUseEpsomSparge(Boolean useEpsomSparge) {
		this.useEpsomSparge = useEpsomSparge;
	}

	public void setUseGypsumSparge(Boolean useGypsumSparge) {
		this.useGypsumSparge = useGypsumSparge;
	}

	public void setUseNaClSparge(Boolean useNaClSparge) {
		this.useNaClSparge = useNaClSparge;
	}

	public void setUseSlakedLimeSparge(Boolean useSlakedLimeSparge) {
		this.useSlakedLimeSparge = useSlakedLimeSparge;
	}

	public void setUseSodaSparge(Boolean useSodaSparge) {
		this.useSodaSparge = useSodaSparge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getAcidulatedMaltContent() {
		return acidulatedMaltContent;
	}

	public void setAcidulatedMaltContent(Double acidulatedMaltContent) {
		this.acidulatedMaltContent = acidulatedMaltContent;
	}

	public Double getLacticAcid() {
		return lacticAcid;
	}

	public void setLacticAcid(Double lacticAcid) {
		this.lacticAcid = lacticAcid;
	}

	public Double getLacticAcidContent() {
		return lacticAcidContent;
	}

	public void setLacticAcidContent(Double lacticAcidContent) {
		this.lacticAcidContent = lacticAcidContent;
	}

	public Double getCitrusAcid() {
		return citrusAcid;
	}

	public void setCitrusAcid(Double citrusAcid) {
		this.citrusAcid = citrusAcid;
	}

	public Double getCitrusAcidContent() {
		return citrusAcidContent;
	}

	public void setCitrusAcidContent(Double citrusAcidContent) {
		this.citrusAcidContent = citrusAcidContent;
	}

	public Double getSlakedLime() {
		return slakedLime;
	}

	public void setSlakedLime(Double slakedLime) {
		this.slakedLime = slakedLime;
	}

	@Override
	public String getTag() {
		return "waters";
	}

	@Override
	public String[] getXmlFields() {
		return campiXml;
	}

	@Override
	public int compareTo(WaterProfile o) {
		return nome.compareToIgnoreCase(o.getNome());
	}

	public Boolean getUseCaCl2() {
		return useCaCl2;
	}

	public Boolean getUseNaCl() {
		return useNaCl;
	}

	public Boolean getUseEpsom() {
		return useEpsom;
	}

	public Boolean getUseChalk() {
		return useChalk;
	}

	public Boolean getUseSoda() {
		return useSoda;
	}

	public Boolean getUseSlakedLime() {
		return useSlakedLime;
	}

	public Boolean getUseGypsum() {
		return useGypsum;
	}

	public Boolean getUseGypsumSparge() {
		return useGypsumSparge;
	}

	public Boolean getUseCaCl2Sparge() {
		return useCaCl2Sparge;
	}

	public Boolean getUseNaClSparge() {
		return useNaClSparge;
	}

	public Boolean getUseEpsomSparge() {
		return useEpsomSparge;
	}

	public Boolean getUseChalkSparge() {
		return useChalkSparge;
	}

	public Boolean getUseSodaSparge() {
		return useSodaSparge;
	}

	public Boolean getUseSlakedLimeSparge() {
		return useSlakedLimeSparge;
	}

}