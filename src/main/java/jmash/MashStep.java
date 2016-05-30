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

import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class MashStep implements Comparable<Object> {
    
    /** Creates a new instance of MashStep */
    public MashStep() {
	setNome("Step non definito");
	this.decoctionRamp1=0;
	this.decoctionRamp2=0;
	this.decoctionRestL1=0;
	this.decoctionRestL2=0;
	this.decoctionRestT1=0;
	this.decoctionRestT2=0;
	this.decoctionRampDown=0;
	this.startTemp=0;
	this.start=0;
	this.endTemp=0;
	this.length=0;
	this.ramp=0;
	this.nome="";
	this.type="";
    }
    public MashStep(Ricetta ricetta) {
	this();
	this.setRicetta(ricetta);
    }
    private Ricetta ricetta;
    private Integer startTemp;
    private Integer start;
    private Integer endTemp;
    private Integer length;
    private Integer ramp;
    private String nome;
    private String type;
    
    private Integer decoctionRamp1;
    private Integer decoctionRamp2;
    private Integer decoctionRestL1;
    private Integer decoctionRestL2;
    private Integer decoctionRestT1;
    private Integer decoctionRestT2;
    private Integer decoctionRampDown;
    
    private Integer infusionTemp;
    
    private static String campiXml[]={
	"type",
	"start",
	"startTemp",
	"endTemp",
	"length",
	"nome",
	"ramp",
	"decoctionRamp1",
	"decoctionRamp2",
	"decoctionRestL1",
	"decoctionRestL2",
	"decoctionRestT1",
	"decoctionRestT2",
	"decoctionRampDown",
	"infusionTemp"
    };
    
    public Integer getStartTemp() {
	return this.startTemp;
    }
    
    public void setStartTemp(Integer startTemp) {
	this.startTemp = startTemp!=null?startTemp: new Integer(0);
    }
    
    public Integer getEndTemp() {
	return this.endTemp;
    }
    
    public void setEndTemp(Integer endTemp) {
	this.endTemp = endTemp!=null?endTemp: new Integer(0);
    }
    
    public Integer getLength() {
	return this.length;
    }
    
    public void setLength(Integer length) {
	this.length = length!=null?length: new Integer(1);
    }
    
    public String getNome() {
	return this.nome;
    }
    
    public void setNome(String nome) {
	this.nome = nome;
    }
    public Element toXml(){
	try {
	    return Utils.toXml(this, getCampiXml());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }
    public static MashStep fromXml(Element elem){
	MashStep y=new MashStep();
	try{
	    y=(MashStep)Utils.fromXml(y,getCampiXml(), elem);
	} catch(Exception ex){
	    Utils.showException(ex);
	    return null;
	}
	return y;
    }
    public static MashStep fromXml(Element elem,Ricetta ricetta){
	MashStep y=new MashStep(ricetta);
	try{
	    y=(MashStep)Utils.fromXml(y,getCampiXml(), elem);
	} catch(Exception ex){
	    Utils.showException(ex);
	    return null;
	}
	return y;
    }
    
    public Ricetta getRicetta() {
	return this.ricetta;
    }
    
    public void setRicetta(Ricetta ricetta) {
	this.ricetta = ricetta;
    }
    
    public String getType() {
	return this.type;
    }
    
    public void setType(String type) {
	this.type = type;
    }
    
    public static String[] getCampiXml() {
	return campiXml;
    }
    
    public static void setCampiXml(String[] aCampiXml) {
	campiXml = aCampiXml;
    }
    
    public Integer getRamp() {
	return this.ramp;
    }
    
    public void setRamp(Integer ramp) {
	this.ramp = ramp!=null?ramp: new Integer(5);
    }
    
    public Integer getStart() {
	return this.start;
    }
    
    public void setStart(Integer start) {
	this.start = start;
    }
    
    public Integer getDecoctionRamp1() {
	return this.decoctionRamp1;
    }
    
    public void setDecoctionRamp1(Integer decoctionRamp1) {
	this.decoctionRamp1 = decoctionRamp1;
    }
    
    public Integer getDecoctionRamp2() {
	return this.decoctionRamp2;
    }
    
    public void setDecoctionRamp2(Integer decoctionRamp2) {
	this.decoctionRamp2 = decoctionRamp2;
    }
    
    public Integer getDecoctionRestL1() {
	return this.decoctionRestL1;
    }
    
    public void setDecoctionRestL1(Integer decoctionRestL1) {
	this.decoctionRestL1 = decoctionRestL1;
    }
    
    public Integer getDecoctionRestL2() {
	return this.decoctionRestL2;
    }
    
    public void setDecoctionRestL2(Integer decoctionRestL2) {
	this.decoctionRestL2 = decoctionRestL2;
    }
    
    public Integer getDecoctionRestT1() {
	return this.decoctionRestT1;
    }
    
    public void setDecoctionRestT1(Integer decoctionRestT1) {
	this.decoctionRestT1 = decoctionRestT1;
    }
    
    public Integer getDecoctionRestT2() {
	return this.decoctionRestT2;
    }
    
    public void setDecoctionRestT2(Integer decoctionRestT2) {
	this.decoctionRestT2 = decoctionRestT2;
    }
    
    public Integer getDecoctionRampDown() {
	return this.decoctionRampDown;
    }
    
    public void setDecoctionRampDown(Integer decoctionRampDown) {
	this.decoctionRampDown = decoctionRampDown;
    }
    
    public boolean isDecoctionStep(){
	if(this.type==null) {
	    return false;
	} else {
	    return this.type.compareToIgnoreCase(XmlTags.MASH_STEP_TYPE[1])==0;
	}
    }
    public boolean isInfusionStep(){
	if(this.type==null) {
	    return false;
	} else {
	    return this.type.compareToIgnoreCase(XmlTags.MASH_STEP_TYPE[2])==0;
	}
    }    
    public int compare(Object o1, Object o2){
	return ((MashStep)o1).getStart().compareTo(((MashStep)o2).getStart());
    }
    @Override
    public int compareTo(Object ob){
	return getStart().compareTo(((MashStep)ob).getStart());
    }
    
    public Integer getInfusionTemp() {
	return infusionTemp;
    }
    
    public void setInfusionTemp(Integer infusionTemp) {
	this.infusionTemp = infusionTemp;
    }
}
