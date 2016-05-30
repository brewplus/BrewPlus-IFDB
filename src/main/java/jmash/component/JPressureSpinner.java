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
 * @author Alessandro
 */
public class JPressureSpinner extends JMultiUnitSpinner{
    
    /** Creates a new instance of JPressureSpinner */
    public JPressureSpinner() {
	super(new String[] { "psi", "bar" });
	setModel(1,0,1000.0,0.100,"0.00",null);
    }
    private static final int PSI = 0;
    private static final int BAR = 1;
    @Override
    protected void changeUnit(int from, int to){
	if(to==PSI){
	    if(from==BAR){
		((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.BAR2PSI((Double)((SpinnerNumberModel)getSpinner().getModel()).getMaximum()));
		((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.BAR2PSI((Double)((SpinnerNumberModel)getSpinner().getModel()).getMinimum()));
		double T=getSpinner().getDoubleValue();
		getSpinner().setDoubleValue(
			Utils.BAR2PSI(T));
		((SpinnerNumberModel)getSpinner().getModel()).setStepSize(0.1);
	    }
	} else if(to == BAR){
	    if(from==PSI){
		((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.PSI2BAR((Double)((SpinnerNumberModel)getSpinner().getModel()).getMaximum()));
		((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.PSI2BAR((Double)((SpinnerNumberModel)getSpinner().getModel()).getMinimum()));
		double T=getSpinner().getDoubleValue();
		getSpinner().setDoubleValue(Utils.PSI2BAR(T));
	    }
	    ((SpinnerNumberModel)getSpinner().getModel()).setStepSize(0.01);
	}
	Main.putIntoCache(nome+"_U",to);
    }
    @Override
    protected double getRealValue(){return getPressure();}
    public void setPressure(double V){
	int i=getComboBox().getSelectedIndex();
	if(i==PSI)setDoubleValue(V);
	else
	    if(i==BAR)setDoubleValue(Utils.PSI2BAR(V));
    }
    public double getPressure(){
	double T=getSpinner().getDoubleValue();
	int i=getComboBox().getSelectedIndex();
	double res=-1;
	if(i==PSI)res=T;
	if(i==BAR)res=Utils.BAR2PSI(T);
	return res;
    }
}
