/*
 *  Copyright 2006, 2007 Alessandro Chiari.
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
public class Yeast {
    
    /** Creates a new instance of Yeast */
    public Yeast() {
    }
    public Yeast(Ricetta ricetta) {
        this.setRicetta(ricetta);
    }
    private Ricetta ricetta;
    private String nome;
    private String codice;
    private String produttore;
    private String forma;
    private String categoria;
    private String descrizione;
    private String note;
    private Integer attenuazioneMed;
    private Integer attenuazioneMin;
    private Integer attenuazioneMax;
    private Integer temperaturaMin;
    private Integer temperaturaCons;
    private Integer temperaturaMax;
    
    private static String campiXml[]={
        "nome",
        "codice",
        "produttore",
        "forma",
        "categoria",
        "descrizione",
        "attenuazioneMin",
        "attenuazioneMax",
        "attenuazioneMed",
        "temperaturaCons",
        "temperaturaMin",
        "temperaturaMax",
        "note",
    };
    
    public Element toXml(){
        try {
            return Utils.toXml(this, getCampiXml());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static Yeast fromXml(Element elem){
        Yeast y=new Yeast();
        try{
            y=(Yeast)Utils.fromXml(y,getCampiXml(), elem);
        } catch(Exception ex){
	    Utils.showException(ex);
            return null;
        }
        return y;
    }
    
    public static Yeast fromXml(Element elem,Ricetta ricetta){
        Yeast y=new Yeast(ricetta);
        try{
            y=(Yeast)Utils.fromXml(y,getCampiXml(), elem);
        } catch(Exception ex){
	    Utils.showException(ex);
            return null;
        }
        return y;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCodice() {
        return this.codice;
    }
    
    public void setCodice(String codice) {
        this.codice = codice;
    }
    
    public String getProduttore() {
        return this.produttore;
    }
    
    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }
    
    public String getForma() {
        return this.forma;
    }
    
    public void setForma(String forma) {
        this.forma = forma;
    }
    
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getDescrizione() {
        return this.descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public Integer getAttenuazioneMin() {
        return this.attenuazioneMin;
    }
    
    public void setAttenuazioneMin(Integer attenuazioneMin) {
        this.attenuazioneMin = attenuazioneMin;
    }
    
    public Integer getAttenuazioneMax() {
        return this.attenuazioneMax;
    }
    
    public void setAttenuazioneMax(Integer attenuazioneMax) {
        this.attenuazioneMax = attenuazioneMax;
    }
    
    public Integer getTemperaturaMin() {
        return this.temperaturaMin;
    }
    
    public void setTemperaturaMin(Integer temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }
    
    public Integer getTemperaturaMax() {
        return this.temperaturaMax;
    }
    
    public void setTemperaturaMax(Integer temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }
    
    public Ricetta getRicetta() {
        return this.ricetta;
    }
    
    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public static String[] getCampiXml() {
        return campiXml;
    }
    
    public static void setCampiXml(String[] aCampiXml) {
        campiXml = aCampiXml;
    }
    public Yeast(Ricetta ricetta,YeastType type) {
        this(ricetta);
        this.setRicetta(ricetta);
        setNome(type.getNome());
        setCodice(type.getCodice());
        setProduttore(type.getProduttore());
        setForma(type.getForma());
        setCategoria(type.getCategoria());
        setDescrizione(type.getDescrizione());
        setAttenuazioneMed(type.getAttenuazioneMed());
        setAttenuazioneMin(type.getAttenuazioneMin());
        setAttenuazioneMax(type.getAttenuazioneMax());
        setTemperaturaMin(type.getTemperaturaMin());
        setTemperaturaMax(type.getTemperaturaMax());
        setNote("");
    }
    
    public Integer getTemperaturaCons() {
        return this.temperaturaCons;
    }
    
    public void setTemperaturaCons(Integer temperaturaCons) {
        this.temperaturaCons = temperaturaCons;
    }
    
    public Integer getAttenuazioneMed() {
        return this.attenuazioneMed;
    }
    
    public void setAttenuazioneMed(Integer attenuazioneMed) {
        this.attenuazioneMed = attenuazioneMed;
    }
    
}
