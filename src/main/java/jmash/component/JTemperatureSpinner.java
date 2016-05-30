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

import javax.swing.SpinnerNumberModel;
import jmash.Main;
import jmash.Utils;

/**
 *
 * @author AChiari
 */
public class JTemperatureSpinner extends JMultiUnitSpinner{
    
    /** Creates a new instance of JVolumeSpinner */
    public JTemperatureSpinner() {
        super(new String[] { "°C", "°F" });
	setModel(40,0,100,1,"0.00",null);
    }
    private static final int C = 0;
    private static final int F = 1;

    @Override
    protected double getRealValue(){return getTemperature();}

    @Override
    protected void changeUnit(int from, int to){
        if(to==C){
            if(from==F){ 
            ((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.F2C((Double)((SpinnerNumberModel)getSpinner().getModel()).getMaximum()));
            ((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.F2C((Double)((SpinnerNumberModel)getSpinner().getModel()).getMinimum()));
            double T=getSpinner().getDoubleValue();
            getSpinner().setDoubleValue(Utils.F2C(T));
            }
        } else if(to == F){
            if(from==C){ 
            ((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.C2F((Double)((SpinnerNumberModel)getSpinner().getModel()).getMaximum()));
            ((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.C2F((Double)((SpinnerNumberModel)getSpinner().getModel()).getMinimum()));
            double T=getSpinner().getDoubleValue();
            getSpinner().setDoubleValue(Utils.C2F(T));
            }
        }
	Main.putIntoCache(nome+"_U",to);
    }
    public void setTemperature(double V){
        int i=getComboBox().getSelectedIndex();
        
        if(i==C)setDoubleValue(V);
        else
            if(i==F)setDoubleValue(Utils.C2F(V));
    }
    public double getTemperature(){
        double T=getSpinner().getDoubleValue();
        int i=getComboBox().getSelectedIndex();
        double res=-1;
        if(i==C)res=T;
        if(i==F)res=Utils.F2C(T);
        return res;
    }
}