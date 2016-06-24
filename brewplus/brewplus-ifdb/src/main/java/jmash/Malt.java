/*
 *  Copyright 2005, 2006, 2007, 2008 Alessandro Chiari.
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
import jmash.interfaces.Constants;
import jmash.interfaces.InventoryObject;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class Malt implements InventoryObject {

    /** Creates a new instance of Malt */
    Ricetta ricetta;
    private Double grammi;
    private Double potentialSG;
    private Integer percentuale;
    private Double ebc;
    private String nome;
    private String origine;
    private String forma;
    private Double yield;
    private String unitaMisura;
    public static String campiXml[] = new String[] { "Grammi", "UnitaMisura", "Nome", "Forma", "Ebc", "PotentialSG", "srm", "Origine", "DataAcquisto" };
     
    public Malt() {
        this.grammi = 000.0;
        this.ebc = 10.0;
        this.potentialSG = 1.034;
        this.nome = "Innominato";
        this.forma = "Grani";
        this.unitaMisura = "grammi";
    }

    public Malt(Ricetta ricetta) {
        this();
        this.ricetta = ricetta;
    }

    public Malt(Ricetta ricetta, MaltType type) {
        this(ricetta);
        this.ricetta = ricetta;
        this.origine = type.getOrigine();
        this.potentialSG = type.getSg();
        this.nome = type.getNome();
        this.ebc = type.getEbc();
        this.forma = type.getForma();
        this.yield = type.getYield();
    }

    public Malt(MaltType type) {
        this();
        this.origine = type.getOrigine();
        this.potentialSG = type.getSg();
        this.nome = type.getNome();
        this.ebc = type.getEbc();
        this.forma = type.getForma();
        this.yield = type.getYield();
    }
    
     public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }

    public Integer getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(Integer percentuale) {
        this.percentuale = percentuale;
    }

    public String getUnitaMisura() {
        return this.unitaMisura;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public double getConvertedGrammi() {
        return Utils.convertWeight(this.grammi, "grammi", this.unitaMisura);
    }

    @Override
    public Double getGrammi() {
        return this.grammi;
    }

    public void setGrammi(Double grammi) {
        this.grammi = grammi;
    }

    public Double getPotentialSG() {
        return this.potentialSG;
    }

    public void setSrm(Double d) {
        setEbc(Utils.srmToEbc(d));
    }

    public Double getSrm() {
        return (Utils.ebcToSrm(getEbc()));
    }

    public void setPotentialSG(Double potentialSG) {
        this.potentialSG = potentialSG;
    }

    public Double getEbc() {
        return this.ebc;
    }

    public void setEbc(Double ebc) {
        this.ebc = ebc;
    }

    @Override
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

    public String getForma() {
        return this.forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public static Malt fromXml(Element elem, Ricetta ricetta) {
        Malt malt = new Malt(ricetta);
        try {
            malt = (Malt) Utils.fromXml(malt, campiXml, elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }

    public static Malt fromXml(Element elem) {
        return fromXml(elem, null);
    }

    public Element toXml() {
        Element malt = new Element("malt");
        try {
            return Utils.toXml(this, campiXml);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }

    public Double getYield() {
        return this.yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    private Date dataAcquisto;

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    private Boolean selected;

    @Override
    public Boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public boolean isMashed() {
        return (getForma() != null)
                && ((Constants.GRANI.compareToIgnoreCase(getForma()) == 0) || (Constants.FIOCCHI.compareToIgnoreCase(getForma()) == 0)
                        || (Constants.CHICCHI.compareToIgnoreCase(getForma()) == 0));
    }

    public double getMcu(double volume) {
        return this.getSrm() * Utils.gramsToPound(this.getGrammi()) / Utils.litToGal(volume);
    }
}
