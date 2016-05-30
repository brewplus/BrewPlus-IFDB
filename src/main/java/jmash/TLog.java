/*
 *  Copyright  2008 Alessandro Chiari.
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

import java.util.Date;
import jmash.interfaces.XmlAble;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class TLog implements XmlAble {
    
    public TLog() {
	setMinuto(0);
        setTemperatura(0);
        setPH(7.0);
    }
    private Integer minuto;
    private Integer temperatura;
    private Double pH;
    private Date data;
    
    public Integer getMinuto() {
	return minuto;
    }
    
    public void setMinuto(Integer minuto) {
	this.minuto = minuto;
    }
    
    public Integer getTemperatura() {
	return temperatura;
    }
    
    public void setTemperatura(Integer temperatura) {
	this.temperatura = temperatura;
    }
    private static String campiXml[]={"temperatura","minuto","data","pH"};
    @Override
    public String[] getXmlFields(){return getCampiXml();}
    @Override
    public String getTag(){
        return "tLog";
    }
    public static TLog fromXml(Element malt){
        TLog type=new TLog();
        try {
            type=(TLog) Utils.fromXml(type,new TLog().getXmlFields(), malt);
        }         
        catch(Exception ex){
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

    public Double getPH() {
        return pH;
    }

    public void setPH(Double pH) {
        this.pH = pH;
    }

    public static String[] getCampiXml() {
        return campiXml;
    }

    public static void setCampiXml(String[] aCampiXml) {
        campiXml = aCampiXml;
    }
}
