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
package jmash.component;

import jmash.Main;
import jmash.Utils;

/**
 *
 * @author AChiari
 */
public class JVolumeSpinner extends JMultiUnitSpinner{
    
    /** Creates a new instance of JVolumeSpinner */
    public JVolumeSpinner() {
        super(new String[] { "L", "gal", "hL" });
        setModel(23,0,9999999999.99,0.5,"0.0", null);
    }
    private static final int L = 0;
    private static final int GAL = 1;
    private static final int HL = 2;
    @Override
    protected double getRealValue(){return getVolume();}
    @Override
    public void setFormat(String f){
	super.setFormat(f);
    }
    @Override
    protected void changeUnit(int from, int to){
        if(to==L){
            if(from==GAL) setValues(Utils.galToLit(1.0));
            if(from==HL)  setValues(100.0);
        } else if(to == GAL){
            if(from==L)   setValues(Utils.litToGal(1));
            if(from==HL)  setValues(Utils.litToGal(100));
        } else if(to == HL){
            if(from==GAL) setValues(Utils.galToLit(0.01));
            if(from==L)   setValues(0.01);
        }
    }
    public void setVolume(double V){
        int i=getComboBox().getSelectedIndex();

        if(i==L)setDoubleValue(V);
        else 
            if(i==GAL)setDoubleValue(Utils.litToGal(V));
        else 
            if(i==HL)setDoubleValue(V/100);
    }
    public double getVolume(){
        double T=getSpinner().getDoubleValue();
        int i=getComboBox().getSelectedIndex();
	double res=-1;
        if(i==L)res= T;
        if(i==GAL)res= Utils.galToLit(T);
        if(i==HL)res= T*100;
	return res;
    }        
}
