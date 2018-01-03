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

    private static String campiXml[] = { "Nome", "Descrizione", "VolumeFinale", "Efficienza", "AssorbimentoGraniEsausti", "DeadSpace" , "RapportoAcquaGrani", "PercentualeEvaporazione", "ContrazionePerRaffreddamento", "PerditeNelTrub", "Biab" };

    private String nome;
    private String descrizione;
    private Double volumeFinale;
    private Double efficienza;
    private Double assorbimentoGraniEsausti;
    private Double deadSpace;
    private Double rapportoAcquaGrani;
    private Double percentualeEvaporazione;
    private Double contrazionePerRaffreddamento;
    private Double perditeNelTrub;
    private String biab;

    public BreweryProfile() {
	this(null, null, 23, 70, 1.4, 6.5, 3.0, 15.0, 4.0, 0.0, "No");
    }

    public BreweryProfile(double volumeFinale, double efficienza, double assorbimentoGraniEsausti, double deadSpace, double rapportoAcquaGrani, double percentualeEvaporazione, double contrazionePerRaffreddamento, double perditeNelTrub, String biab) {
	this(null, null, volumeFinale, efficienza, assorbimentoGraniEsausti, deadSpace, rapportoAcquaGrani, percentualeEvaporazione, contrazionePerRaffreddamento, perditeNelTrub, biab);
    }

    public BreweryProfile(String nome, String descrizione, double volumeFinale, double efficienza, double assorbimentoGraniEsausti, double deadSpace, double rapportoAcquaGrani, double percentualeEvaporazione, double contrazionePerRaffreddamento, double perditeNelTrub, String biab) {
	setNome(nome);
	setDescrizione(descrizione);
	setVolumeFinale(volumeFinale);
	setEfficienza(efficienza);
	setAssorbimentoGraniEsausti(assorbimentoGraniEsausti);
	setDeadSpace(deadSpace);
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

    public Double getVolumeFinale() {
	return volumeFinale;
    }

    public void setVolumeFinale(Double volumeFinale) {
	this.volumeFinale = volumeFinale;
    }

    public Double getEfficienza() {
	return efficienza;
    }

    public void setEfficienza(Double efficienza) {
	this.efficienza = efficienza;
    }

    public Double getAssorbimentoGraniEsausti() {
	return assorbimentoGraniEsausti;
    }

    public void setAssorbimentoGraniEsausti(Double assorbimentoGraniEsausti) {
	this.assorbimentoGraniEsausti = assorbimentoGraniEsausti;
    }
    
    public Double getDeadSpace() {
	return deadSpace;
    }
    
    public void setDeadSpace(Double deadSpace) {
	this.deadSpace = deadSpace;
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
	    isBiab = "1".equals(tmpBiab) || "true".equalsIgnoreCase(tmpBiab) || "yes".equalsIgnoreCase(tmpBiab) || "si".equalsIgnoreCase(tmpBiab);
	}

	return isBiab;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((assorbimentoGraniEsausti == null) ? 0 : assorbimentoGraniEsausti.hashCode());
	result = prime * result + ((biab == null) ? 0 : biab.hashCode());
	result = prime * result + ((contrazionePerRaffreddamento == null) ? 0 : contrazionePerRaffreddamento.hashCode());
	result = prime * result + ((deadSpace == null) ? 0 : deadSpace.hashCode());
	result = prime * result + ((efficienza == null) ? 0 : efficienza.hashCode());
	result = prime * result + ((percentualeEvaporazione == null) ? 0 : percentualeEvaporazione.hashCode());
	result = prime * result + ((perditeNelTrub == null) ? 0 : perditeNelTrub.hashCode());
	result = prime * result + ((rapportoAcquaGrani == null) ? 0 : rapportoAcquaGrani.hashCode());
	result = prime * result + ((volumeFinale == null) ? 0 : volumeFinale.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {

	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	BreweryProfile other = (BreweryProfile) obj;
	
	if (assorbimentoGraniEsausti == null) {
	    if (other.assorbimentoGraniEsausti != null)
		return false;
	} else if (!assorbimentoGraniEsausti.equals(other.assorbimentoGraniEsausti))
	    return false;
	
	if (isBiab() != other.isBiab())
	    return false;
	
	if (contrazionePerRaffreddamento == null) {
	    if (other.contrazionePerRaffreddamento != null)
		return false;
	} else if (!contrazionePerRaffreddamento.equals(other.contrazionePerRaffreddamento))
	    return false;
	
	if (deadSpace == null) {
	    if (other.deadSpace != null)
		return false;
	} else if (!deadSpace.equals(other.deadSpace))
	    return false;
	
	if (efficienza == null) {
	    if (other.efficienza != null)
		return false;
	} else if (!efficienza.equals(other.efficienza))
	    return false;
	
	if (percentualeEvaporazione == null) {
	    if (other.percentualeEvaporazione != null)
		return false;
	} else if (!percentualeEvaporazione.equals(other.percentualeEvaporazione))
	    return false;
	
	if (perditeNelTrub == null) {
	    if (other.perditeNelTrub != null)
		return false;
	} else if (!perditeNelTrub.equals(other.perditeNelTrub))
	    return false;
	
	if (rapportoAcquaGrani == null) {
	    if (other.rapportoAcquaGrani != null)
		return false;
	} else if (!rapportoAcquaGrani.equals(other.rapportoAcquaGrani))
	    return false;
	
	if (volumeFinale == null) {
	    if (other.volumeFinale != null)
		return false;
	} else if (!volumeFinale.equals(other.volumeFinale))
	    return false;
	
	return true;
    }

    @Override
    public String toString() {
	return "BreweryProfile [volumeFinale=" + volumeFinale + ", efficienza=" + efficienza + ", assorbimentoGraniEsausti=" + assorbimentoGraniEsausti + ", deadSpace=" + deadSpace + ", rapportoAcquaGrani=" + rapportoAcquaGrani + ", percentualeEvaporazione=" + percentualeEvaporazione + ", contrazionePerRaffreddamento=" + contrazionePerRaffreddamento + ", perditeNelTrub=" + perditeNelTrub
		+ ", biab=" + isBiab() + "]";
    }
    
    

}
