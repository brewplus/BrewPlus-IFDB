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
import java.util.Date;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class AcquistoIngredienti /*extends CalendarEntry*/ {
    
    /** Creates a new instance of AcquistoIngredienti */
    public AcquistoIngredienti() {
    }
    
    private List<Hop> luppoli=new ArrayList<Hop>();
    private List<Malt> malti=new ArrayList<Malt>();
    private List<Yeast> lieviti=new ArrayList<Yeast>();
    private Date data;
    private String des;
    private static String campiXml[]={
	"data",
	"des",
    };
    @Override
    public String toString(){
	return "Acquisto "+des+" del "+data;
    }
    
    public List<Hop> getLuppoli() {
	return luppoli;
    }
    
    public void setLuppoli(List<Hop> luppoli) {
	this.luppoli = luppoli;
    }
    
    public List<Malt> getMalti() {
	return malti;
    }
    
    public void setMalti(List<Malt> malti) {
	this.malti = malti;
    }
    
    public List<Yeast> getLieviti() {
	return lieviti;
    }
    
    public void setLieviti(List<Yeast> lieviti) {
	this.lieviti = lieviti;
    }
    public static String[] getCampiXml() {
	return campiXml;
    }
    
    public Document toXml(){
	Document doc=new Document();
	try{
	    Element root=Utils.toXml(this, getCampiXml());
	    for(Hop h: this.luppoli){
		root.addContent(h.toXml());
	    }
	    for(Malt m: this.malti){
		root.addContent(m.toXml());
	    }
	    for(Yeast y: this.lieviti){
		root.addContent(y.toXml());
	    }
	    doc.setRootElement(root);
	} catch(Exception ex){
	    Utils.showException(ex);
	    return null;
	}
	return doc;
    }
    public static AcquistoIngredienti fromXml(Element elem){
        AcquistoIngredienti y=new AcquistoIngredienti();
        try{
            y=(AcquistoIngredienti)Utils.fromXml(y,getCampiXml(), elem);
        } catch(Exception ex){
            Utils.showException(ex);
            return null;
        }
        return y;
    }
    
    public Date getData() {
	return data;
    }
    
    public void setData(Date data) {
	this.data = data;
    }
    
    public String getDes() {
	return des;
    }
    
    public void setDes(String des) {
	this.des = des;
    }
}
