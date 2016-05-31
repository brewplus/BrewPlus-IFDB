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

import java.util.Date;
import jmash.interfaces.XmlAble;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class SGLog  implements XmlAble {
    
    public SGLog() {
        setData(new Date());
	setSG(1.040);
        setPH(7.0);
        setT(20.0);
    }
    private Double SG;
    private Double plato;
    private Double pH;
    private Date data;
    private Double T;
    

    
    private static String campiXml[]={"SG","data","pH", "T", "plato"};
    @Override
    public String[] getXmlFields(){return getCampiXml();}
    @Override
    public String getTag(){
	return "SGLog";
    }
    public static SGLog fromXml(Element el){
	SGLog type=new SGLog();
	try {
	    type=(SGLog) Utils.fromXml(type,new SGLog().getXmlFields(), el);
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
    
    public Double getSG() {
	return SG;
    }
    
    public void setSG(Double SG) {
	this.SG = Math.round(1000.0*SG)/1000.0;;
	this.plato=Math.round(100.0*Utils.SG2Plato(this.SG))/100.0;
    }

    public Double getPlato() {
	return plato;
    }
    
    public void setPlato(Double plato) {
	this.plato = plato;
	this.SG=Math.round(1000.0*Utils.Plato2SG(plato))/1000.0;
    }
    
    public static String[] getCampiXml() {
	return campiXml;
    }
    
    public static void setCampiXml(String[] aCampiXml) {
	campiXml = aCampiXml;
    }

    public Double getPH() {
        return pH;
    }

    public void setPH(Double pH) {
        this.pH = pH;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getT() {
        return T;
    }

    public void setT(Double T) {
        this.T = T;
    }
}
