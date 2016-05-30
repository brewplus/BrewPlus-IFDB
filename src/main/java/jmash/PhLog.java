/*
 * PhLog.java
 *
 * Created on 28 gennaio 2008, 18.57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash;

import jmash.interfaces.XmlAble;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class PhLog  implements XmlAble {
    
    public PhLog() {
	setMinuto(0);
	setPH(0);
    }
    private Integer minuto;
    private Integer pH;
    
    public Integer getMinuto() {
	return minuto;
    }
    
    public void setMinuto(Integer minuto) {
	this.minuto = minuto;
    }
    
    private static String campiXml[]={"pH","minuto",};
    @Override
    public String[] getXmlFields(){return getCampiXml();}
    @Override
    public String getTag(){
	return "pHLog";
    }
    public static PhLog fromXml(Element el){
	PhLog type=new PhLog();
	try {
	    type=(PhLog) Utils.fromXml(type,new PhLog().getXmlFields(), el);
	} catch(Exception ex){
	    Utils.showException(ex);
	}
	return type;
    }
    @Override
    public Element toXml(){
	try {
	    return Utils.toXml(this, getXmlFields());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }
    
    public Integer getPH() {
	return pH;
    }
    
    public void setPH(Integer pH) {
	this.pH = pH;
    }
    
    public static String[] getCampiXml() {
	return campiXml;
    }
    
    public static void setCampiXml(String[] aCampiXml) {
	campiXml = aCampiXml;
    }
}
