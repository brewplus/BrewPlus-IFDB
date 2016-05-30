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
    private String remoteServer;
    private String remoteRoot;
    private String nickIHB;
    private String passwordIHB;
    private String proxyPort;
    private String proxyHost;
    private String RSSFeed;
    private String BUGUratiostring;
    
    private Integer boilTime = new Integer(90);
    private Integer efficienza = new Integer(75);
    private Integer volumeFin = new Integer(23);
    private Integer volumeBoil = new Integer(28);
    private Integer metriSLM = new Integer(160);
    private Integer amaroDHEA = new Integer(45);
    
    
    private Double lostToTrub = new Double(0.5);
    private Double lostToSparge = new Double(3.0);
    private Double litriPerKg = new Double(3.0);
    private Double evaporazionePerOra = new Double(2.0);
    
    private Boolean ebcNewMethod=true;
    private Integer potLibGal=0;
    
    
    public String getRemoteServer() {
        return this.remoteServer;
    }
    
    private static String campiXml[]=new String[]{
        "remoteRoot", "RemoteServer","RSSFeed","boilTime","volumeFin", "volumeBoil",
        "efficienza","metriSLM", "amaroDHEA","nickIHB","passwordIHB",
        "proxyPort", "proxyHost",
        "lostToTrub","lostToSparge","litriPerKg","evaporazionePerOra", "ebcNewMethod",
        "potLibGal","BUGUratiostring"
    };
    
    public void setRemoteServer(String remoteServer) {
        this.remoteServer = remoteServer;
    }
    
    public static Config fromXml(Element elem){
        Config conf=new Config();
        try {
            conf=(Config)Utils.fromXml(conf,getCampiXml(), elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return conf;
    }
    
    public Element toXml(){
        try {
            return Utils.toXml(this, getCampiXml());
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return null;
    }
    
    public Integer getAmaroDHEA()
    {
    	return this.amaroDHEA;
    }
    public void setAmaroDHEA(Integer amarodhea)
    {
    	this.amaroDHEA=amarodhea;
    }
    
    public Integer getBoilTime() {
        return this.boilTime;
    }
    
    
    public void setBoilTime(Integer boilTime) {
        this.boilTime = boilTime;
    }
    
    public Integer getEfficienza() {
        return this.efficienza;
    }
    
    public void setEfficienza(Integer efficienza) {
        this.efficienza = efficienza;
    }
    
    public Integer getVolumeFin() {
        return this.volumeFin;
    }
    
    public void setVolumeFin(Integer volumeFin) {
        this.volumeFin = volumeFin;
    }
    
    public Integer getVolumeBoil() {
        return this.volumeBoil;
    }
    
    public void setVolumeBoil(Integer volumeBoil) {
        this.volumeBoil = volumeBoil;
    }
    
    public Integer getMetriSLM() {
        return this.metriSLM;
    }
    
    public void setMetriSLM(Integer metriSLM) {
        this.metriSLM = metriSLM;
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
        return this.proxyHost==null?"":proxyHost;
    }
    
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }
    
    public String getProxyPort() {
        return this.proxyPort==null?"":proxyPort;
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
    
    public Double getLostToSparge() {
        return lostToSparge;
    }
    
    public void setLostToSparge(Double lostToSparge) {
        this.lostToSparge = lostToSparge;
    }
    
    public Double getLitriPerKg() {
        return litriPerKg;
    }
    
    public void setLitriPerKg(Double litriPerKg) {
        this.litriPerKg = litriPerKg;
    }
    
    public Double getEvaporazionePerOra() {
        return evaporazionePerOra;
    }
    
    public void setEvaporazionePerOra(Double evaporazionePerOra) {
        this.evaporazionePerOra = evaporazionePerOra;
    }

	public String getRemoteRoot() {
		return remoteRoot==null?"http://brewplus.t15.org/brewplus":remoteRoot;
	}

	public void setRemoteRoot(String remoteRoot) {
		this.remoteRoot = remoteRoot;
	}

	public String getRSSFeed()
	{
		return RSSFeed==null?"http://birrabirra.altervista.org/forum/feed.php":RSSFeed;
	}
	public void setRSSFeed(String RSSFeed)
	{
		this.RSSFeed=RSSFeed;
	}
	
	public Boolean getEbcNewMethod() {
		return ebcNewMethod;
	}
	public void setEbcNewMethod(Boolean ebcNewMethod) {
		this.ebcNewMethod = ebcNewMethod;
	}
	
	public Integer getPotLibGal()
	{
		return this.potLibGal;
	}
	public void setPotLibGal(Integer potLibGal)
	{
		this.potLibGal=potLibGal;
	}
	
	public String getBUGUratiostring(){
		return this.BUGUratiostring==null?"TIN" :BUGUratiostring;
	}
	public void setBUGUratiostring(String valore){
		this.BUGUratiostring=valore;
	}
	public BitterBUGU getBUGURatio(){
		if(this.BUGUratiostring==null)return BitterBUGU.TIN;
		if(this.BUGUratiostring.equals("RAG"))return BitterBUGU.RAG;
		if(this.BUGUratiostring.equals("DAN"))return BitterBUGU.DAN;
		return BitterBUGU.TIN; //default
	}
    public void setBUGURatio(BitterBUGU valore){
    	if(valore==BitterBUGU.DAN)this.BUGUratiostring="DAN";
    	if(valore==BitterBUGU.RAG)this.BUGUratiostring="RAG";
    	if(valore==BitterBUGU.TIN)this.BUGUratiostring="TIN";
    }
}
