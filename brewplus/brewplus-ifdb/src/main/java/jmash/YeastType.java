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

import jmash.interfaces.XmlAble;

import org.apache.log4j.Logger;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class YeastType implements XmlAble {

	private static Logger LOGGER = Logger.getLogger(YeastType.class);

	/** Creates a new instance of YeastType */
	public YeastType() {
	}

	private String nome;
	private String codice;
	private String produttore;
	private String forma;
	private String categoria;
	private String descrizione;
	private String attenuazioneMed;
	private String attenuazioneMin;
	private String attenuazioneMax;
	private String temperaturaMin;
	private String temperaturaMax;
	private String temperaturaMaxFerm;

	private static String campiXml[] = { "nome", "codice", "produttore", "forma", "categoria", 
			"attenuazioneMed", "attenuazioneMin", "attenuazioneMax", "temperaturaMin", "temperaturaMax", "descrizione"};

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

	public String getAttenuazioneMin() {
		return this.attenuazioneMin;
	}

	public void setAttenuazioneMin(String attenuazioneMin) {
		this.attenuazioneMin = attenuazioneMin;
	}

	public String getAttenuazioneMax() {
		return this.attenuazioneMax;
	}

	public void setAttenuazioneMax(String attenuazioneMax) {
		this.attenuazioneMax = attenuazioneMax;
	}

	public String getTemperaturaMin() {
		return this.temperaturaMin;
	}

	public void setTemperaturaMin(String temperaturaMin) {
		this.temperaturaMin = temperaturaMin;
	}

	public String getTemperaturaMax() {
		return this.temperaturaMax;
	}

	public void setTemperaturaMax(String temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

	public String getAttenuazioneMed() {
		if (attenuazioneMed != null && !"".equals(attenuazioneMed)) {			
			return this.attenuazioneMed;
		} else if (getAttenuazioneMin() != null && !"".equals(getAttenuazioneMin()) &&
				getAttenuazioneMax() != null && !"".equals(getAttenuazioneMax())) {
			return (String.valueOf((Integer.valueOf(getAttenuazioneMin())+Integer.valueOf(getAttenuazioneMax()))/2));
		}
		return this.attenuazioneMed;
	}

	public void setAttenuazioneMed(String attenuazioneMed) {
		this.attenuazioneMed = attenuazioneMed;
	}

	public String getTemperaturaMaxFerm() {
		return temperaturaMaxFerm;
	}

	public void setTemperaturaMaxFerm(String temperaturaMaxFerm) {
		this.temperaturaMaxFerm = temperaturaMaxFerm;
	}

	public static String[] getCampiXml() {
		return campiXml;
	}

	public static void setCampiXml(String[] aCampiXml) {
		campiXml = aCampiXml;
	}

	@Override
	public Element toXml() {
		try {
			return Utils.toXml(this, campiXml);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public String getTag() {
		return "yeasts";
	}

	@Override
	public String[] getXmlFields() {
		return campiXml;
	}
}
