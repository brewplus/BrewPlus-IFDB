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
public class Srm2Rgb {
    private static String campiXml[]={"Srm","R","G","B"};
    /** Creates a new instance of Srm2Rgb */
    public Srm2Rgb() {
    }
    private Double srm,ebc;
    private Integer r,g,b;

    @Override
	public String toString(){
        return getEbc()+" ("+getSrm()+") ["+getR()+","+getG()+","+getB()+"]";
    }

    public static String[] getCampiXml() {
        return campiXml;
    }

    public static void setCampiXml(String[] aCampiXml) {
        campiXml = aCampiXml;
    }


    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Double getSrm() {
        return this.srm;
    }
    public Integer getR() {
        return this.r;
    }

    public Integer getG() {
        return this.g;
    }

    public Integer getB() {
        return this.b;
    }

    public Double getEbc() {
        return this.ebc;
    }

    public void setEbc(Double ebc) {
        this.ebc = ebc;
    }
}
