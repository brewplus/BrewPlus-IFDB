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
import jmash.interfaces.InventoryObject;
import org.jdom.Element;

public class Hop implements InventoryObject {
    
    /** Creates a new instance of Hop */
    Ricetta ricetta;
    
    public Hop() {
	setGrammi(0.0);
	setBoilTime(60);
	setAlfaAcidi(4.5);
	setNome("Nuovo luppolo");
	setForma("Fiore");
	setUso("Kettle");
	setUnitaMisura("grammi");
    }
    
    public Hop(Ricetta ricetta) {
	this();
	this.ricetta = ricetta;
    }
    
    public void setRicetta(Ricetta ricetta) {
	this.ricetta = ricetta;
    }
    
    public Hop(HopType type) {
	this();
	setAlfaAcidi(type.getAlfaAcidi());
	setHSI(type.getHSI());
	setNome(type.getNome());
	setOrigine(type.getProvenienza());
    }
    
    public Hop(Ricetta ricetta, HopType type) {
	this(ricetta);
	this.ricetta = ricetta;
	setAlfaAcidi(type.getAlfaAcidi());
	setHSI(type.getHSI());
	setNome(type.getNome());
	setOrigine(type.getProvenienza());
    }
    private double HSI;
    // serve il wrapper per poter fare il controllo != null nella CalcoloHopLoss
    private Double alfaAcidiPrec;
    private double grammi;
    private int boilTime;
    private double alfaAcidi;
    private String nome;
    private String forma;
    private String origine;
    private String uso;
    private String unitaMisura;
    
    public String getUnitaMisura() {
	return this.unitaMisura;
    }
    
    public void setUnitaMisura(String unitaMisura) {
	this.unitaMisura = unitaMisura;
    }
    
    public String getUso() {
	return this.uso;
    }
    
    public void setUso(String uso) {
	this.uso = uso;
	if (uso.compareToIgnoreCase("dry") == 0) {
	    setBoilTime(0);
	}
	//modifica IXTLANAS DHEA
	if (uso.compareToIgnoreCase("DHEA") == 0) {
	    setBoilTime(Main.config.getAmaroDHEA());
	}
	if ((uso.compareToIgnoreCase("first wort") == 0) && (this.ricetta != null)) {
	    setBoilTime(this.ricetta.getBollitura());
	}
	if ((uso.compareToIgnoreCase("mash") == 0) && (this.ricetta != null)) {
	    setBoilTime(this.ricetta.getBollitura());
	}
    }
    
    public Double getHSI() {
	return this.HSI;
    }
    
    public void setHSI(Double HSI) {
	this.HSI = HSI;
    }
    
    public Integer getBB() {
	return this.boilTime;
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
    
    public Integer getBoilTime() {
	return this.boilTime;
    }
    
    public void setBoilTime(Integer boilTime) {
	this.boilTime = boilTime;
    }
    
    public Double getAlfaAcidi() {
	return this.alfaAcidi;
    }
    
    public void setAlfaAcidi(Double alfaAcidi) {
	this.alfaAcidi = alfaAcidi;
    }
    
    public String getForma() {
	return this.forma;
    }
    
    public void setForma(String forma) {
	this.forma = forma;
    }
    
    @Override
    public String getNome() {
	return this.nome;
    }
    
    public void setNome(String nome) {
	this.nome = nome;
    }
    
    public static double hypTan(double a) {
	return ((Math.exp(a) - Math.exp(-a)) / (Math.exp(a) + Math.exp(-a)));
    }
    //private Double dIbu=new Double(0);
    public double considerUse(double d) {
	if ((getUso() != null) && (getUso().compareToIgnoreCase("mash") == 0)) {
	    d *= 0.2;
	}
	if ((getUso() != null) && (getUso().compareToIgnoreCase("first wort") == 0)) {
	    d *= 0.9;
	}
	if ((getUso() != null) && (getUso().compareToIgnoreCase("dry") == 0)) {
	    d *= 0;
	}
	return d;
    }
    
    public double considerUseAndForm(double d) {
	d = considerUse(d);
	if ((getForma() != null) && (getForma().compareToIgnoreCase("pellet") == 0)) {
	    d *= 1.1;
	}
	if ((getForma() != null) && (getForma().compareToIgnoreCase("plug") == 0)) {
	    d *= 1.02;
	}
	return d;
    }
    
    
    public double adjustUtilizationToUseAndForm(double d) {
	return adjustUtilizationToUseAndForm(this, d);
    }
    
    public static double adjustUtilizationToUseAndForm(Hop hop,double d) {
	
	if ((hop.getUso() != null) && (hop.getUso().compareToIgnoreCase("mash") == 0)) {
	    //d *= 0.7;
		d *= 0.2;
	}
	if ((hop.getUso() != null) && (hop.getUso().compareToIgnoreCase("first wort") == 0)) {
	    //d *= 0.9;
		d *= 1.1;
	}
	if ((hop.getUso() != null) && (hop.getUso().compareToIgnoreCase("dry") == 0)) {
	    d *= 0;
	}
	if ((hop.getForma() != null) && (hop.getForma().compareToIgnoreCase("pellet") == 0)) {
	    d *= 1.1;
	}
	if ((hop.getForma() != null) && (hop.getForma().compareToIgnoreCase("plug") == 0)) {
	    d *= 1.02;
	}
	return d;
    }
    private double IBUTinseth;
    private double IBURager;
    private double IBUDaniels;
    
    
//    public static double calcIBUTinseth(Hop hop, double volume, double volumeDiluito, double OG) {
//
//	// Tinseth
////        double ibu = (1.65 * Math.pow(0.00013, (this.ricetta.getGravity() - 1)) *
////                (1 - Math.exp(-0.04 * this.boilTime)) *
////                this.alfaAcidi * this.grammi * 10 / (this.ricetta.getVolume() * 4.15));
//
//// mg/l of added alpha acids = decimal AA rating * grams hops * 1000
////                            -------------------------------------
////                                        liters of wort
//	double v2=volume;
//
//	double mg = hop.getAlfaAcidi() * hop.getGrammi() * 1000 / v2;
//
////        Bigness factor = 1.65 * 0.000125^(wort gravity - 1)
//	double BF = 1.65 * Math.pow(0.000125, (OG - 1));
//// Boil Time factor = 1 - e^(-0.04 * time in mins)        / 4.15
//	double BT = (1 - Math.exp(-0.04 * hop.getBoilTime())) / 4.15;
//// Decimal Alpha Acid Utilization = Bigness Factor * Boil Time Factor
//	double ut = BF * BT;
//	ut = adjustUtilizationToUseAndForm(hop, ut);
//
//	double ibu = ut * mg / 100;
//
//
//	double v3=volumeDiluito;
//	double ibu0=ibu;
//	ibu=ibu*v2/v3;
//
//
//	return ibu;
//    }
    
    
    public double calcIBUTinseth() {
	if (this.ricetta == null) {
	    return -1;
	}
	
	// Tinseth
//        double ibu = (1.65 * Math.pow(0.00013, (this.ricetta.getGravity() - 1)) *
//                (1 - Math.exp(-0.04 * this.boilTime)) *
//                this.alfaAcidi * this.grammi * 10 / (this.ricetta.getVolume() * 4.15));
	
// mg/l of added alpha acids = decimal AA rating * grams hops * 1000
//                            -------------------------------------
//                                        liters of wort
	double v2=this.ricetta.getVolume();
	
	double mg = this.alfaAcidi * this.grammi * 1000 / v2;
	
//        Bigness factor = 1.65 * 0.000125^(wort gravity - 1)
	double BF = 1.65 * Math.pow(0.000125, (this.ricetta.getGravity() - 1));
// Boil Time factor = 1 - e^(-0.04 * time in mins)        / 4.15
	double BT = (1 - Math.exp(-0.04 * this.boilTime)) / 4.15;
// Decimal Alpha Acid Utilization = Bigness Factor * Boil Time Factor
	double ut = BF * BT;
	ut = adjustUtilizationToUseAndForm(ut);
	
	double ibu = ut * mg / 100;
	
	if(ricetta.isConcetratedBoil()){
	    double v3=this.ricetta.getVolumeDiluito();
	    double ibu0=ibu;
	    ibu=ibu*v2/v3;
	}
	
	return ibu;
    }
    
    public double calcIBURager() {
	if (this.ricetta == null) {
	    return -1;
	}
	
	//%UTILIZATION = 18.11 + 13.86 * hyptan[(MINUTES - 31.32) / 18.27]
	double ut = 18.11 + 13.86 * hypTan((this.boilTime - 31.32) / 18.27);
	double ga = 0;
	if (this.ricetta.getGravity() > 1.050) {
	    ga = (this.ricetta.getGravity() - 1.050) / 0.2;
	}
	
	//IBU = (GRAMS OF HOPS) * %UTILIZATION * %ALPHA * 1000
	//      ------------------------------------------------
	//                 VOLUME(litres) * (1 + GA)
	
	ut = adjustUtilizationToUseAndForm(ut);
	
	double ibu = (Utils.gramsToOunces(this.grammi) * this.alfaAcidi * ut * 0.7462) / (Utils.litToGal(this.ricetta.getVolume()) * (1 + ga));
	
	if(ricetta.isConcetratedBoil()){
	    double v3=this.ricetta.getVolumeDiluito();
	    double v2=this.ricetta.getVolume();
	    ibu=ibu*v2/v3;
	}
	
	return ibu;
    }
    
    public double calcIBUDaniels() {
	if (this.ricetta == null) {
	    return -1;
	}
	
	double og = this.ricetta.getGravity();
	double v1 = this.ricetta.getVolumeBoll();
	double v2 = this.ricetta.getVolume();
	
	if (v1 < v2) {
	    og = 1 + (og - 1) * (v2 / v1);
	}
	
	double c;
	if (og > 1.050) {
	    c = 1 + ((og - 1.050) / .2);
	} else {
	    c = 1;
	}
	
	double ut = 1;
	if (this.forma.compareToIgnoreCase("pellet") == 0) {
	    ut = -(.0051 * this.boilTime * this.boilTime) + (0.7835 * this.boilTime) + 1.9348;
	} else {
	    ut = -(.0041 * this.boilTime * this.boilTime) + (0.6261 * this.boilTime) + 1.5779;
	}
	ut = adjustUtilizationToUseAndForm(ut);
	double ibu = (((this.grammi / 28.3495231) * ut * this.alfaAcidi * .7489) / ((v2 / 3.7854118) * c));
	
	
	if(ricetta.isConcetratedBoil()){
	    double v3=this.ricetta.getVolumeDiluito();
	    ibu=ibu*v2/v3;
	}
	
	return ibu;
    }
    private static String campiXml[] = new String[]{
	"Grammi",
	"UnitaMisura",
	"BoilTime",
	"AlfaAcidi",
	"Nome",
	"Forma",
	"Origine",
	"Uso",
	"HSI",
	"dataAcquisto"
    };
    
    public static Hop fromXml(Element elem, Ricetta ricetta) {
	Hop hop = new Hop(ricetta);
	try {
	    hop = (Hop) Utils.fromXml(hop, campiXml, elem);
	} catch (Exception ex) {
	    Utils.showException(ex);
	}
	return hop;
    }
    
    public static Hop fromXml(Element elem) {
	return fromXml(elem, null);
    }
    
    public Element toXml() {
	try {
	    return Utils.toXml(this, campiXml);
	} catch (Exception ex) {
	    Utils.showException(ex);
	}
	return null;
    }
    
    public String getOrigine() {
	return this.origine == null ? "" : this.origine;
    }
    
    public void setOrigine(String origine) {
	this.origine = origine;
    }
    
    public Double getAlfaAcidiPrec() {
	return this.alfaAcidiPrec;
    }
    
    public void setAlfaAcidiPrec(Double alfaAcidiPrec) {
	this.alfaAcidiPrec = alfaAcidiPrec;
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
    
    public double getIBUTinseth() {
	return IBUTinseth;
    }
    
    public void setIBUTinseth(double IBUTinseth) {
	this.IBUTinseth = IBUTinseth;
    }
    
    public double getIBURager() {
	return IBURager;
    }
    
    public void setIBURager(double IBURager) {
	this.IBURager = IBURager;
    }
    
    public double getIBUDaniels() {
	return IBUDaniels;
    }
    
    public void setIBUDaniels(double IBUDaniels) {
	this.IBUDaniels = IBUDaniels;
    }
}
