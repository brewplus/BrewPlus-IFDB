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
public class JWeightSpinner  extends JMultiUnitSpinner{
    
    /** Creates a new instance of JVolumeSpinner */
    public JWeightSpinner() {
        super(new String[] { "gr", "kg", "oz", "lb" });
	setModel(0,0,9999999999.99,0.5,"0.0", null);
    }

    private static final int GR = 0;
    private static final int KG = 1;
    private static final int OZ = 2;
    private static final int LB = 3;

    @Override
    protected double getRealValue(){return getWeight();}

    @Override
    protected void changeUnit(int from, int to){
        if(to==GR){
            if(from==KG) setValues(1000);
            if(from==OZ)  setValues(Utils.ouncesToGrams(1.0));
            if(from==LB)  setValues(Utils.poundToKg(1.0)*1000);
        } else if(to == KG){
            if(from==GR) setValues(0.001);
            if(from==OZ)  setValues(Utils.ouncesToGrams(0.001));
            if(from==LB)  setValues(Utils.poundToKg(1.0));
        } else if(to == OZ){
            if(from==GR) setValues(Utils.gramsToOunces(1));
            if(from==KG)  setValues(Utils.gramsToOunces(1000.0));
            if(from==LB)  setValues(Utils.poundsToOunces(1.0));
        } else if(to == LB){
            if(from==GR) setValues( Utils.ouncesToPounds(Utils.gramsToOunces(1)));
            if(from==KG) setValues( Utils.ouncesToPounds(Utils.gramsToOunces(1000)));
            if(from==OZ)  setValues(Utils.ouncesToPounds(1.0));
        }
	Main.putIntoCache(nome+"_U",to);
    }
    public void setWeight(double V){
        int i=getComboBox().getSelectedIndex();
            if(i==GR)setDoubleValue(V);
        else 
            if(i==OZ)setDoubleValue(Utils.gramsToOunces(V));
        else 
            if(i==LB)setDoubleValue(Utils.kgToPound(V/1000));
        else 
            if(i==KG)setDoubleValue(V/1000);
    }
    public double getWeight(){
        double T=getSpinner().getDoubleValue();
        int i=getComboBox().getSelectedIndex();
        double res=-1;
        if(i==GR)res= T;
        if(i==KG)res= T*1000;
        if(i==OZ)res= Utils.ouncesToGrams(T);
        if(i==LB)res= Utils.poundToKg(T*1000);
        return res;
    }        
}