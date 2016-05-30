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

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import jmash.Main;

/**
 *
 * @author Alessandro
 */
public class JMashSpinner extends JSpinner{
    
    /**
     *
     */
    private static final long serialVersionUID = 1411790229110221532L;
    
    /** Creates a new instance of JMashSpinner */
    public JMashSpinner() {
	addChangeListener(new ChangeListener(){
            @Override
	    public void stateChanged(ChangeEvent ev){
		Main.putIntoCache(nome+"_V",getDoubleValue());
	    }
	});
    }
    private SpinnerNumberModel model = new SpinnerNumberModel(23.0, 0.0, 10000.0, 0.25);
    private String nome;
    public void setModel(double def, double min, double max, double step){
	if(Double.isNaN(min))min=0;
	if(Double.isNaN(max))max=0;
	if(Double.isNaN(def))def=0;
	if(def<min)def=min;
	if(def>max)def=max;
	if(max<min)max=min;
	this.model=new SpinnerNumberModel(def, min, max, step);
	setModel(this.model);
    }
    public void setModel(double def, double min, double max, double step, String format, String name){
	if(name!=null)def=Main.getFromCache(name+"_V",def);
	if(Double.isNaN(min))min=0;
	if(Double.isNaN(max))max=0;
	if(Double.isNaN(def))def=0;	
	if(def<min)def=min;
	if(def>max)def=max;
	if(max<min)max=min;
	setModel(def,min,max,step);
	setFormat(format);
	nome=name;
    }
    public void setModelFormat(double def, double min, double max, double step, String format, String name){
	if(name!=null)def=Main.getFromCache(name+"_V",def);
	if(Double.isNaN(min))min=0;
	if(Double.isNaN(max))max=0;
	if(Double.isNaN(def))def=0;	
	if(def<min)def=min;
	if(def>max)def=max;
	if(max<min)max=min;
	setModel(def,min,max,step);
	setFormat(format);
	nome=name;
    }
    public void setFormat(String format){
	setEditor(new JSpinner.NumberEditor(this, format));
    }
    public double getDoubleValue(){
	return this.model.getNumber().doubleValue();
    }
    public int getIntegerValue(){
	return this.model.getNumber().intValue();
    }
    public void setDoubleValue(double val){
	this.model.setValue(val);
	Main.putIntoCache(nome+"_V",val);
    }
    public void setIntegerValue(int val){
	this.model.setValue(val);
	Main.putIntoCache(nome+"_V",val);
    }
    
    @Override
    protected void fireStateChanged() {
	if(!blockStateChangedEvents)
	    super.fireStateChanged();
    }
    
    private boolean blockStateChangedEvents=false;
    
    public boolean getBlockStateChangedEvents() {
	return blockStateChangedEvents;
    }
    
    public void setBlockStateChangedEvents(boolean blockStateChangedEvents) {
	this.blockStateChangedEvents = blockStateChangedEvents;
    }

    @Override
    public void setEnabled(boolean enable) {
        super.setEnabled(enable);
        if (!enable) {
            super.setFont(super.getFont().deriveFont(Font.BOLD));
        } else {
            super.setFont(super.getFont().deriveFont(Font.PLAIN));
        }
    }
}
