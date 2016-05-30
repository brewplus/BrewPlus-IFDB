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

import jmash.interfaces.XmlAble;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class MaltType implements XmlAble, Comparable<MaltType>{
    
    /** Creates a new instance of MaltType */
    public MaltType() {
    }
    public MaltType(String nome, String origine, double sg, double ebc, String forma, String caratteristiche) {
	setNome(nome);
	setOrigine(origine);
	setSg(sg);
	setForma(forma);
	setEbc(ebc);
	setCaratteristiche(caratteristiche);
    }
    
    private String nome;
    private String origine;
    private Double sg;
    private Double ebc;
    private Double srm;
    private String caratteristiche;
    private String forma;
    private Double yield;
    
    private String ADD_AFTER_BOIL;
    private String needMash;
    private String notes;
    private String COARSE_FINE_DIFF;
    private String MOISTURE;
    private String DIASTATIC_POWER;
    private String PROTEIN;
    private String MAX_IN_BATCH;
    private String RECOMMEND_MASH;
    private String EXTRACT_SUBSTITUTE;
    
    public Double getEbc() {
	return ebc==null?0:ebc;
    }
    
    public void setEbc(Double ebc) {
	this.ebc = ebc;
	this.srm=(Utils.ebcToSrm(ebc));
    }
    
    public Double getSrm() {
	return this.srm;
    }
    public void setSrm(Double srm) {
	this.srm = srm;
	this.ebc=(Utils.srmToEbc(srm));
    }
    
    public String getNome() {
	return this.nome;
    }
    
    public void setNome(String nome) {
	this.nome = nome;
    }
    
    public String getOrigine() {
	return this.origine;
    }
    
    public void setOrigine(String origine) {
	this.origine = origine;
    }
    
    public Double getSg() {
	return this.sg;
    }
    
    public void setSg(Double sg) {
	this.sg = sg;
    }
    
    public String getCaratteristiche() {
	return this.caratteristiche;
    }
    
    public void setCaratteristiche(String caratteristiche) {
	this.caratteristiche = caratteristiche;
    }
    
    public String getForma() {
	return this.forma;
    }
    
    public void setForma(String forma) {
	this.forma = forma;
    }
    
    private static String campiXml[]={"Nome","Forma","Sg","Ebc","Srm","Origine","Yield","Caratteristiche",
    "ADD_AFTER_BOIL",
    "needMash",
    "notes",
    "COARSE_FINE_DIFF",
    "MOISTURE",
    "DIASTATIC_POWER",
    "PROTEIN",
    "MAX_IN_BATCH",
    "RECOMMEND_MASH",
    "EXTRACT_SUBSTITUTE",
    };
    
    public static MaltType fromXml(Element malt){
	MaltType type=new MaltType();
	try {
	    type=(MaltType) Utils.fromXml(type,getCampiXml(), malt);
	} catch(Exception ex){
	    Utils.showException(ex);
	    return new MaltType();
	}
	return type;
    }
    @Override
    public Element toXml(){
	try {
	    return Utils.toXml(this, getCampiXml());
	} catch (Exception ex) {
	    Utils.showException(ex);
	}
	return null;
    }
    
    public Double getYield() {
	return this.yield;
    }
    
    public void setYield(Double yield) {
	this.yield = yield;
    }
    @Override
    public String getTag(){
	return "malts";
    }
    @Override
    public String[] getXmlFields(){return getCampiXml();}
    
    public String getADD_AFTER_BOIL() {
	return ADD_AFTER_BOIL;
    }
    
    public void setADD_AFTER_BOIL(String ADD_AFTER_BOIL) {
	this.ADD_AFTER_BOIL = ADD_AFTER_BOIL;
    }
    
    public String getNeedMash() {
	return needMash;
    }
    
    public void setNeedMash(String needMash) {
	this.needMash = needMash;
    }
    
    public String getNotes() {
	return notes;
    }
    
    public void setNotes(String notes) {
	this.notes = notes;
    }
    
    public String getCOARSE_FINE_DIFF() {
	return COARSE_FINE_DIFF;
    }
    
    public void setCOARSE_FINE_DIFF(String COARSE_FINE_DIFF) {
	this.COARSE_FINE_DIFF = COARSE_FINE_DIFF;
    }
    
    public String getMOISTURE() {
	return MOISTURE;
    }
    
    public void setMOISTURE(String MOISTURE) {
	this.MOISTURE = MOISTURE;
    }
    
    public String getDIASTATIC_POWER() {
	return DIASTATIC_POWER;
    }
    
    public void setDIASTATIC_POWER(String DIASTATIC_POWER) {
	this.DIASTATIC_POWER = DIASTATIC_POWER;
    }
    
    public String getPROTEIN() {
	return PROTEIN;
    }
    
    public void setPROTEIN(String PROTEIN) {
	this.PROTEIN = PROTEIN;
    }
    
    public String getMAX_IN_BATCH() {
	return MAX_IN_BATCH;
    }
    
    public void setMAX_IN_BATCH(String MAX_IN_BATCH) {
	this.MAX_IN_BATCH = MAX_IN_BATCH;
    }
    
    public String getRECOMMEND_MASH() {
	return RECOMMEND_MASH;
    }
    
    public void setRECOMMEND_MASH(String RECOMMEND_MASH) {
	this.RECOMMEND_MASH = RECOMMEND_MASH;
    }
    
    public String getEXTRACT_SUBSTITUTE() {
	return EXTRACT_SUBSTITUTE;
    }
    
    public void setEXTRACT_SUBSTITUTE(String EXTRACT_SUBSTITUTE) {
	this.EXTRACT_SUBSTITUTE = EXTRACT_SUBSTITUTE;
    }
    
    public static String[] getCampiXml() {
	return campiXml;
    }
    
    public static void setCampiXml(String[] aCampiXml) {
	campiXml = aCampiXml;
    }
    
    @Override
    public int compareTo(MaltType o) {
	return nome.compareToIgnoreCase(o.getNome());
    }
}
