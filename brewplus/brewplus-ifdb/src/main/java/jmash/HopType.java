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
public class HopType implements XmlAble, Comparable<HopType> {

    /** Creates a new instance of HopType */
    public HopType() {
    }

    public HopType(String nome, String provenienza, double alfaAcidi, String caratteristiche) {
        setNome(nome);
        setProvenienza(provenienza);
        setAlfaAcidi(alfaAcidi);
        setCaratteristiche(caratteristiche);
    }
    private String nome;
    private String provenienza;
    private Double alfaAcidi;
    private Double HSI;
    private String caratteristiche;
    private String use;
    private Double beta;
    private String utilizzo;

    public Double getHSI() {
        return this.HSI;
    }

    public void setHSI(Double HSI) {
        this.HSI = HSI;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProvenienza() {
        return this.provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public String getOrigine() {
        return this.getProvenienza();
    }

    public void setOrigine(String provenienza) {
        this.setProvenienza(provenienza);
    }

    public Double getAlfaAcidi() {
        return this.alfaAcidi;
    }

    public void setAlfaAcidi(Double alfaAcidi) {
        this.alfaAcidi = alfaAcidi;
    }

    public Double getAA() {
        return this.getAlfaAcidi();
    }

    public void setAA(Double alfaAcidi) {
        this.setAlfaAcidi(alfaAcidi);
    }

    public String getCaratteristiche() {
        return this.caratteristiche;
    }

    public void setCaratteristiche(String caratteristiche) {
        this.caratteristiche = caratteristiche;
    }

    public String getDes() {
        return this.getCaratteristiche();
    }

    public void setDes(String caratteristiche) {
        this.setCaratteristiche(caratteristiche);
    }
    private static String campiXml[] = {"Nome", "AA", "Origine", "Des", "HSI", "use", "utilizzo", "beta"};

    public static HopType fromXml(Element malt) {
        HopType type = new HopType();
        try {
            type = (HopType) Utils.fromXml(type, getCampiXml(), malt);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return type;
    }

    @Override
    public Element toXml() {
        try {
            return Utils.toXml(this, getCampiXml());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTag() {
        return "hops";
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getUtilizzo() {
        return utilizzo;
    }

    public void setUtilizzo(String utilizzo) {
        this.utilizzo = utilizzo;
    }

    public static String[] getCampiXml() {
        return campiXml;
    }

    @Override
    public String[] getXmlFields() {
        return campiXml;
    }

    public static void setCampiXml(String[] aCampiXml) {
        campiXml = aCampiXml;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    @Override
    public int compareTo(HopType o) {
        return nome.compareToIgnoreCase(o.getNome());
    }
}
