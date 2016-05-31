/*
 *  Copyright 2005, 2006 Alessandro Chiari.
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
public class BrewStyle implements XmlAble {

    /** Creates a new instance of BrewStyle */
    public BrewStyle() {
    }
    private String nome;
    private String categoria;
    private String codicecategoria;
    private String numero;
    private String sottoCategoria;
    private String lettera;
    private String guida;
    private String tipo;
    private Double ogMin;
    private Double ogMax;
    private Double fgMin;
    private Double fgMax;
    private Double ibuMin;
    private Double ibuMax;
    private Double colorMin;
    private Double colorMax;
    private Double carbMin;
    private Double carbMax;
    private Double abvMin;
    private Double abvMax;
    private String note;
    private String descrizione;
    private String ingredienti;
    private String esempi;
    private String water;
    private String yeast;
    private String malt;
    private String hops;
    private String spices;
    private String aroma;
    private String appearance;
    private String flavor;
    private String mouthfeel;
    private String impression;
    private String comments;
    private String ingredients;
    private String examples;
    
    
    public String getAroma() {
        return this.aroma;
    }
    public void setAroma(String valore) {
        this.aroma = valore;
    }
    public String getAppearance() {
        return this.appearance;
    }
    public void setAppearance(String valore) {
        this.appearance = valore;
    }
    public void setFlavor(String valore) {
        this.flavor = valore;
    }
    public String getFlavor() {
        return this.flavor;
    }
    public void setMouthfeel(String valore) {
        this.mouthfeel = valore;
    }
    public String getMouthfeel() {
        return this.mouthfeel;
    }
    public void setImpression(String valore) {
        this.impression = valore;
    }
    public String getImpression() {
        return this.impression;
    }
    public void setComments(String valore) {
        this.comments = valore;
    }
    public String getComments() {
        return this.comments;
    }
    public void setIngredients(String valore) {
        this.ingredients = valore;
    }
    public String getIngredients() {
        return this.ingredients;
    }
    public void setExamples(String valore) {
        this.examples = valore;
    }
    public String getExamples(){
    	return this.examples;
    }
    
    
    
    @Override
    public String toString(){
         return this.numero+" "+this.nome;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getcodiceCategoria() {
        return this.codicecategoria;
    }

    public void setcodiceCategoria(String categoria) {
        this.codicecategoria = categoria;
    }

    public String getSottoCategoria() {
        return this.sottoCategoria;
    }

    public void setSottoCategoria(String sottoCategoria) {
        this.sottoCategoria = sottoCategoria;
    }

    public String getLettera() {
        return this.lettera;
    }

    public void setLettera(String lettera) {
        this.lettera = lettera;
    }

    public String getGuida() {
        return this.guida;
    }

    public void setGuida(String guida) {
        this.guida = guida;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getOgMin() {
        return this.ogMin;
    }

    public void setOgMin(Double ogMin) {
        this.ogMin = ogMin;
    }

    public Double getOgMax() {
        return this.ogMax;
    }

    public void setOgMax(Double ogMax) {
        this.ogMax = ogMax;
    }

    public Double getFgMin() {
        return this.fgMin;
    }

    public void setFgMin(Double fgMin) {
        this.fgMin = fgMin;
    }

    public Double getFgMax() {
        return this.fgMax;
    }

    public void setFgMax(Double fgMax) {
        this.fgMax = fgMax;
    }

    public Double getIbuMin() {
        return this.ibuMin;
    }

    public void setIbuMin(Double ibuMin) {
        this.ibuMin = ibuMin;
    }

    public Double getIbuMax() {
        return this.ibuMax;
    }

    public void setIbuMax(Double ibuMax) {
        this.ibuMax = ibuMax;
    }

    public Double getColorMin() {
        return this.colorMin;
    }

    public void setColorMin(Double colorMin) {
        this.colorMin = colorMin;
    }

    public Double getColorMax() {
        return this.colorMax;
    }

    public void setColorMax(Double colorMax) {
        this.colorMax = colorMax;
    }

    public Double getCarbMin() {
        return this.carbMin;
    }

    public void setCarbMin(Double carbMin) {
        this.carbMin = carbMin;
    }

    public Double getCarbMax() {
        return this.carbMax;
    }

    public void setCarbMax(Double carbMax) {
        this.carbMax = carbMax;
    }

    public Double getAbvMin() {
        return this.abvMin;
    }

    public void setAbvMin(Double abvMin) {
        this.abvMin = abvMin;
    }

    public Double getAbvMax() {
        return this.abvMax;
    }

    public void setAbvMax(Double abvMax) {
        this.abvMax = abvMax;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIngredienti() {
        return this.ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getEsempi() {
        return this.esempi;
    }

    public void setEsempi(String esempi) {
        this.esempi = esempi;
    }
    
    public String getWater()
    { return this.water;}
    public void setWater(String value)
    {this.water=value;}
    
    public String getYeast()
    {return this.yeast;}
    public void setYeast(String value)
    {this.yeast=value;}
    
    public String getMalt()
    {return this.malt;}
    public void setMalt(String value)
    {this.malt=value;}
    
    public String getHops()
    {return this.hops;}
    public void setHops(String value)
    {this.hops=value;}
    
    public String getSpices()
    {return this.spices;}
    public void setSpices(String value)
    {this.spices=value;}
    
    public static final String campiXml[] = new String[]{
        "nome",
        "categoria",
        "numero",
        "sottoCategoria",
        "lettera",
        "guida",
        "tipo",
        "ogMin",
        "ogMax",
        "fgMin",
        "fgMax",
        "ibuMin",
        "ibuMax",
        "colorMin",
        "colorMax",
        "carbMin",
        "carbMax",
        "abvMin",
        "abvMax",
        "note",
        "descrizione",
        "ingredienti",
        "esempi",
        "water",
        "yeast",
        "malt",
        "hops",
        "spices"
    ,

                 
        }   ; 
            
        
         
    

    public static  BrewStyle fromXml( 
         Element elem
    

       ) {
        BrewStyle type = 
    

       
           new BrewStyle();
        try {
            type=(BrewStyle)Utils.fromXml(type,campiXml,elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return type;
    }
    
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
//    @Override
//    public String toString(){
//        return  (this.numero!=null ? this.numero : "") + (this.sottoCategoria != null ? "-" + this.sottoCategoria + " " : "") + this.nome +
//                " IBU: [" + this.ibuMin + ";" + this.ibuMax + "]" +
//                " OG: [" + this.ogMin + ";" + this.ogMax + "]" +
//                " FG: [" + this.fgMin + ";" + this.fgMax + "]" +
//                "";
//    }

    @Override
    public Element toXml() {
        try {
            return Utils.toXml(this, campiXml);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return null;
    }

    public String getDesCategoria() {
        return (this.numero != null ? (this.numero + " - ") : "") + (this.categoria != null ? this.categoria + " - " : "") + this.nome;
    }

    public String getDesIBU() {
        return " IBU: " + this.ibuMin + "- " + this.ibuMax + "";
    }

    public String getDesOG() {
        return " OG: " + this.ogMin + " - " + this.ogMax + "";
    }

    public String getDesFG() {
        return " FG: " + this.fgMin + " - " + this.fgMax + "";
    }

    @Override
    public String getTag() {
        return "styles";
    }

    @Override
    public String[] getXmlFields() {
        return campiXml;
    }
}
