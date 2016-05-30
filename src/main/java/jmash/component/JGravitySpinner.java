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
import jmash.Utils;
/**
 *
 * @author AChiari
 */
public class JGravitySpinner  extends JMultiUnitSpinner{
    
    public JGravitySpinner() {
	super(new String[] { "SG", "°P" , "°Bè" });
	setModel(1.040,0.8,1.3,0.001,"0.000",null);
    }
    
    public static final int SG = 0;
    public static final int PL = 1;
    public static final int BA = 2;
    
    private int precision = 0;
    public void setPrecision(int p){
	precision=p;
	getSpinner().setFormat(precision == 0?"0.000":"0.00000");
    }

    @Override
    protected double getRealValue(){return getGravity();}
    @Override
    protected void changeUnit(int from, int to){
	super.changeUnit(from,to);
	double T=getSpinner().getDoubleValue();
	if(to==SG){
	    ((SpinnerNumberModel)getSpinner().getModel()).setMinimum(0.8);
	    ((SpinnerNumberModel)getSpinner().getModel()).setMaximum(1.3);
	    getSpinner().setFormat(precision == 0?"0.000":"0.00000");
	    ((SpinnerNumberModel)getSpinner().getModel()).setStepSize(0.001);
	    if(from==PL){
		
		getSpinner().setDoubleValue(
			precision == 0?
			    Math.round(Utils.Plato2SG(T)*1000)/1000.0:
			    Math.round(Utils.Plato2SG(T)*100000)/100000.0
			);
	    }
	    if(from == BA) {
		getSpinner().setDoubleValue( Utils.Baume2SG(T) );
	    }
	} else if(to == PL){
	    ((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.SG2Plato(0.8));
	    ((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.SG2Plato(1.3));
	    getSpinner().setFormat(precision == 0?"0.0":"0.00");
	    ((SpinnerNumberModel)getSpinner().getModel()).setStepSize(0.1);
	    if(from==SG){
		getSpinner().setDoubleValue(Utils.SG2Plato(T));
	    }
	    if(from==BA){
		getSpinner().setDoubleValue(Utils.SG2Plato(Utils.Baume2SG(T)));
	    }
	} else if(to == BA){
	    ((SpinnerNumberModel)getSpinner().getModel()).setMinimum(Utils.SG2Baume(0.8));
	    ((SpinnerNumberModel)getSpinner().getModel()).setMaximum(Utils.SG2Baume(1.3));
	    getSpinner().setFormat(precision == 0?"0.00":"0.000");
	    ((SpinnerNumberModel)getSpinner().getModel()).setStepSize(0.01);
	    if(from==SG){
		getSpinner().setDoubleValue(Utils.SG2Baume(T));
	    }
	    if(from==PL){
		getSpinner().setDoubleValue(Utils.SG2Baume(Utils.Plato2SG(T)));
	    }
	}
    }
    public void setGravity(double V){
	int i=getComboBox().getSelectedIndex();
	if(i==SG)getSpinner().setDoubleValue(V);
	else if(i==PL)getSpinner().setDoubleValue(Utils.SG2Plato(V));
	else if(i==BA)getSpinner().setDoubleValue(Utils.SG2Baume(V));
    }
    public double getGravity(){
	double T=getSpinner().getDoubleValue();
	int i=getComboBox().getSelectedIndex();
	double res=-1;
	if(i==SG)res=T;
	else if(i==PL)res=Utils.Plato2SG(T);
	else if(i==BA)res=Utils.Baume2SG(T);
	return res;
    }
}