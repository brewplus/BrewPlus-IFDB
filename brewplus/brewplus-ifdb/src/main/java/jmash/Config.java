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

import jmash.Main.BitterBUGU;

import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class Config {

    /** Creates a new instance of Config */
    public Config() {
    }

    private String locale;
    private String remoteServer;
    private String remoteRoot;
    private String nickIHB;
    private String passwordIHB;
    private String proxyPort;
    private String proxyHost;
    private String RSSFeed;
    private String BUGUratiostring;

    private Integer boilTime = 90;
    private Double efficienza = 75.0;
    private Double volumeFin = 23.0;
    private Double volumeBoil = 28.0;
    private Integer metriSLM = 160;
    private Integer amaroDHEA = 45;
   
    private Double lostToTrub = 0.5;
    private Double litriPerKg = 1.01;
    private Double rapportoAcquaGrani = 5.0;

    private Boolean ebcNewMethod = true;
    private Integer potLibGal = 0;
    
    private Double percentualeEvaporazione = 15.0;
    private Double contrazionePerRaffreddamento = 4.0;
    private Boolean biab = Boolean.FALSE;
    
    private Double calcioSource;
	private Double sodioSource;
	private Double magnesioSource;
	private Double solfatoSource;
	private Double cloruroSource;
	private Double carbonatoSource;

    public String getRemoteServer() {
        return this.remoteServer;
    }

    private static String campiXml[] = new String[] {"locale", "remoteRoot", "RemoteServer", "RSSFeed", "boilTime", "volumeFin",
            "volumeBoil", "efficienza", "metriSLM", "amaroDHEA", "nickIHB", "passwordIHB", "proxyPort", "proxyHost",
            "lostToTrub", "litriPerKg", "rapportoAcquaGrani", "ebcNewMethod", "potLibGal",
            "BUGUratiostring", "percentualeEvaporazione", "contrazionePerRaffreddamento", "biab",
            "calcioSource", "magnesioSource", "solfatoSource", "cloruroSource", "sodioSource", "carbonatoSource"
    };

    public void setRemoteServer(String remoteServer) {
        this.remoteServer = remoteServer;
    }

    public static Config fromXml(Element elem) {
        Config conf = new Config();
        try {
            conf = (Config) Utils.fromXml(conf, getCampiXml(), elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return conf;
    }

    public Element toXml() {
        try {
            return Utils.toXml(this, getCampiXml());
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return null;
    }

    public Integer getAmaroDHEA() {
        return this.amaroDHEA;
    }

    public void setAmaroDHEA(Integer amarodhea) {
        this.amaroDHEA = amarodhea;
    }

    public Integer getBoilTime() {
        return this.boilTime;
    }

    public void setBoilTime(Integer boilTime) {
        this.boilTime = boilTime;
    }

    public Double getEfficienza() {
        return this.efficienza;
    }

    public void setEfficienza(Double efficienza) {
        this.efficienza = efficienza;
    }

    public Double getVolumeFin() {
        return this.volumeFin;
    }

    public void setVolumeFin(Double volumeFin) {
        this.volumeFin = volumeFin;
    }

    public Double getVolumeBoil() {
        return this.volumeBoil;
    }

    public void setVolumeBoil(Double volumeBoil) {
        this.volumeBoil = volumeBoil;
    }

    public Integer getMetriSLM() {
        return this.metriSLM;
    }

    public void setMetriSLM(Integer metriSLM) {
        this.metriSLM = metriSLM;
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
    
    public Boolean getBiab() {
		return biab;
	}
    
    public void setBiab(Boolean biab) {
		this.biab = biab;
	}

    public Double getCalcioSource() {
		return calcioSource;
	}

	public void setCalcioSource(Double calcioSource) {
		this.calcioSource = calcioSource;
	}

	public Double getSodioSource() {
		return sodioSource;
	}

	public void setSodioSource(Double sodioSource) {
		this.sodioSource = sodioSource;
	}

	public Double getMagnesioSource() {
		return magnesioSource;
	}

	public void setMagnesioSource(Double magnesioSource) {
		this.magnesioSource = magnesioSource;
	}

	public Double getSolfatoSource() {
		return solfatoSource;
	}

	public void setSolfatoSource(Double solfatoSource) {
		this.solfatoSource = solfatoSource;
	}

	public Double getCloruroSource() {
		return cloruroSource;
	}

	public void setCloruroSource(Double cloruroSource) {
		this.cloruroSource = cloruroSource;
	}

	public Double getCarbonatoSource() {
		return carbonatoSource;
	}

	public void setCarbonatoSource(Double carbonatoSource) {
		this.carbonatoSource = carbonatoSource;
	}

	public static String[] getCampiXml() {
        return campiXml;
    }

    public static void setCampiXml(String[] aCampiXml) {
        campiXml = aCampiXml;
    }

    public String getNickIHB() {
        return this.nickIHB;
    }

    public void setNickIHB(String nickIHB) {
        this.nickIHB = nickIHB;
    }

    public String getPasswordIHB() {
        return this.passwordIHB;
    }

    public void setPasswordIHB(String passwordIHB) {
        this.passwordIHB = passwordIHB;
    }

    public String getProxyHost() {
        return this.proxyHost == null ? "" : proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return this.proxyPort == null ? "" : proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Double getLostToTrub() {
        return lostToTrub;
    }

    public void setLostToTrub(Double lostToTrub) {
        this.lostToTrub = lostToTrub;
    }

    public Double getLitriPerKg() {
        return litriPerKg;
    }

    public void setLitriPerKg(Double litriPerKg) {
        this.litriPerKg = litriPerKg;
    }

    public Double getRapportoAcquaGrani() {
        return rapportoAcquaGrani;
    }

    public void setRapportoAcquaGrani(Double rapportoAcquaGrani) {
        this.rapportoAcquaGrani = rapportoAcquaGrani;
    }

    public String getRemoteRoot() {
        return remoteRoot == null ? "http://www.ilforumdellabirra.net/" : remoteRoot;
    }

    public void setRemoteRoot(String remoteRoot) {
        this.remoteRoot = remoteRoot;
    }

    public String getRSSFeed() {
        return RSSFeed == null ? "http://www.ilforumdellabirra.net/feed.php" : RSSFeed;
    }

    public void setRSSFeed(String RSSFeed) {
        this.RSSFeed = RSSFeed;
    }

    public Boolean getEbcNewMethod() {
        return ebcNewMethod;
    }

    public void setEbcNewMethod(Boolean ebcNewMethod) {
        this.ebcNewMethod = ebcNewMethod;
    }

    public Integer getPotLibGal() {
        return this.potLibGal;
    }

    public void setPotLibGal(Integer potLibGal) {
        this.potLibGal = potLibGal;
    }

    public String getBUGUratiostring() {
        return this.BUGUratiostring == null ? "TIN" : BUGUratiostring;
    }

    public void setBUGUratiostring(String valore) {
        this.BUGUratiostring = valore;
    }

    public BitterBUGU getBUGURatio() {
        if (this.BUGUratiostring == null)
            return BitterBUGU.TIN;
        if (this.BUGUratiostring.equals("RAG"))
            return BitterBUGU.RAG;
        if (this.BUGUratiostring.equals("DAN"))
            return BitterBUGU.DAN;
        return BitterBUGU.TIN; // default
    }

    public void setBUGURatio(BitterBUGU valore) {
        if (valore == BitterBUGU.DAN)
            this.BUGUratiostring = "DAN";
        if (valore == BitterBUGU.RAG)
            this.BUGUratiostring = "RAG";
        if (valore == BitterBUGU.TIN)
            this.BUGUratiostring = "TIN";
    }

    public String getLocale() {
        return (locale == null ? (java.util.Locale.getDefault().getLanguage()+ "_" + java.util.Locale.getDefault().getCountry()) : locale);
    }

    public void setLocale(String locale) {
        this.locale = (locale == null ? (java.util.Locale.getDefault().getLanguage() + "_" + java.util.Locale.getDefault().getCountry()) : locale);
    }
}
