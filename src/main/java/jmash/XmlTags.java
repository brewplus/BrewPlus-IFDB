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


/**
 *
 * @author Alessandro
 */
public class XmlTags {
    
    public XmlTags() {
    }
    
    public static final String RECIPE="recipe";
    public static final String BJCPCOD="codicestile";
    public static final String MASH_DESIGN="mash";
    public static final String VOLUME="volume";
    public static final String VOLUME_BOLL="volume_boll";
    public static final String VOLUME_DIL="volume_dil";
    public static final String EFFICIENZA="efficienza";
    public static final String VOLUME_UM="unita_misura_mostrata";
    public static final String BOLLITURA="bollitura";
    public static final String BOLLITURA_CONC="bollitura_conc";
    public static final String NOME="nome";
    public static final String NOTE="note";
    //foto
    public static final String FOTOGRAFIA="fotografia";
    
    public static final String MALT="malt";
    
    public static final String HOP_FORMS[]={"Fiore","Plug","Pellet","Semi","Pezzi","Estratto", "Altro","Spice","Fruit","Coffee","Other","Fining","Herb"};
    public static final String HOP_USAGE[]={"Kettle","First wort","Mash","Dry","DHEA"};
    public static final String MALT_FORMS[]={"Grani","Estratto secco","Estratto liquido","Cristalli","Fiocchi","Chicchi", "Altro"};
    
    public static final String MASH_STEP_TYPE[]={"Infusione","Decozione","Aggiunta"};
    
    public static final String UNITA_PESO[]={"grammi","kg","ounces","pounds"};
    
    public static final String UNITA_VOLUME[]={"litri","gal"};
    
}
