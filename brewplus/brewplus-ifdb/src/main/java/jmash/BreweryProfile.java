package jmash;

import org.apache.log4j.Logger;
import org.jdom.Element;

import jmash.interfaces.XmlAble;

/**
 *
 * @author Peruzzi Alessandro
 */
public class BreweryProfile implements XmlAble, Comparable<BreweryProfile> {

	private static Logger LOGGER = Logger.getLogger(BreweryProfile.class);

//	
	
	private static String campiXml[] = { "Nome", "Descrizione", "AssorbimentoGraniEsausti", "RapportoAcquaGrani", "PercentualeEvaporazione",
			"ContrazionePerRaffreddamento", "PerditeNelTrub", "Biab" };

	private String nome;
	private String descrizione;
	private Double assorbimentoGraniEsausti;
	private Double rapportoAcquaGrani;
	private Double percentualeEvaporazione;
	private Double contrazionePerRaffreddamento;
	private Double perditeNelTrub;
	private String biab;

	public BreweryProfile() {
		this(null, null, 1.4, 3.0, 15.0, 4.0, 0.0, "No");
	}

	public BreweryProfile(String nome, String descrizione, double assorbimentoGraniEsausti, double rapportoAcquaGrani,
			double percentualeEvaporazione, double contrazionePerRaffreddamento, double perditeNelTrub, String biab) {
		setNome(nome);
		setDescrizione(descrizione);
		setAssorbimentoGraniEsausti(assorbimentoGraniEsausti);
		setRapportoAcquaGrani(rapportoAcquaGrani);
		setPercentualeEvaporazione(percentualeEvaporazione);
		setContrazionePerRaffreddamento(contrazionePerRaffreddamento);
		setPerditeNelTrub(perditeNelTrub);
		setBiab(biab);
	}

	@Override
	public int compareTo(BreweryProfile o) {
		return this.getNome().compareTo(o.getNome());
	}

	@Override
	public Element toXml() {
		try {
			return Utils.toXml(this, getCampiXml());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public String getTag() {
		return "breweryProfiles";
	}

	@Override
	public String[] getXmlFields() {
		return getCampiXml();
	}
	
	public static BreweryProfile fromXml(Element profile) {
		BreweryProfile breweryProfile = new BreweryProfile();
		try {
			breweryProfile = (BreweryProfile) Utils.fromXml(breweryProfile, getCampiXml(), profile);
		} catch (Exception ex) {
			Utils.showException(ex);
		  breweryProfile = new BreweryProfile();
		}
		return breweryProfile;
	}


	public static String[] getCampiXml() {
		return campiXml;
	}

	public static void setCampiXml(String[] campiXml) {
		BreweryProfile.campiXml = campiXml;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getAssorbimentoGraniEsausti() {
		return assorbimentoGraniEsausti;
	}

	public void setAssorbimentoGraniEsausti(Double assorbimentoGraniEsausti) {
		this.assorbimentoGraniEsausti = assorbimentoGraniEsausti;
	}

	public Double getRapportoAcquaGrani() {
		return rapportoAcquaGrani;
	}

	public void setRapportoAcquaGrani(Double rapportoAcquaGrani) {
		this.rapportoAcquaGrani = rapportoAcquaGrani;
	}

	public Double getPercentualeEvaporazione() {
		return percentualeEvaporazione;
	}

	public void setPercentualeEvaporazione(Double percentualeEvaporazione) {
		this.percentualeEvaporazione = percentualeEvaporazione;
	}

	public Double getContrazionePerRaffreddamento() {
		return contrazionePerRaffreddamento;
	}

	public void setContrazionePerRaffreddamento(Double contrazionePerRaffreddamento) {
		this.contrazionePerRaffreddamento = contrazionePerRaffreddamento;
	}

	public Double getPerditeNelTrub() {
		return perditeNelTrub;
	}

	public void setPerditeNelTrub(Double perditeNelTrub) {
		this.perditeNelTrub = perditeNelTrub;
	}

	public String getBiab() {
		return biab;
	}

	public void setBiab(String biab) {
		this.biab = biab;
	}

	public boolean isBiab() {

		boolean isBiab = false;

		if (biab != null) {
			String tmpBiab = biab.trim().toLowerCase();
			isBiab = "1".equals(tmpBiab) || "true".equals(tmpBiab) || "yes".equals(tmpBiab) || "si".equals(tmpBiab);
		}
		
		return isBiab;
	}

}
