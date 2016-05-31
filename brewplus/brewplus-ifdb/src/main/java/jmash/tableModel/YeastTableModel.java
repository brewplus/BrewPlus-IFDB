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

package jmash.tableModel;

import java.lang.reflect.Method;
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class YeastTableModel  extends GenericTableModel<Yeast>{
    
    
    /**
     *
     */
    private static final long serialVersionUID = -401917354676314454L;
    Ricetta ricetta;
    private static String[] cN=new String[]{"Codice","Produttore","Nome","Note", "Attenuazione"};
    public YeastTableModel(Ricetta ricetta) {
	this.ricetta=ricetta;
	this.columnNames = cN;
    }
    
    String fieldNames[] = {"Codice","Produttore","Nome","Note", "AttenuazioneMed"};
    
    
    @Override
    public Object getValueAt(int row, int col) {
	Yeast h=this.dataValues.get(row);
	try{
	    Method m=h.getClass().getMethod("get"+Utils.capitalize(this.fieldNames[col]));
	    return m.invoke(h);
	} catch(Exception e){
	    Utils.showException(e,"Il file "+Main.stiliXML+" non corrisponde al formato.",ricetta);
	}
	return null;
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
	if(this.dataValues.get(row)!=null){
	    
	    Yeast h=(this.dataValues.get(row));
	    Class<? extends Object> cl=h.getClass();
	    try{
		Method g=cl.getMethod("get"+Utils.capitalize(this.fieldNames[col]));
		Method m=cl.getMethod("set"+Utils.capitalize(this.fieldNames[col]),g.getReturnType());
		Class<? extends Object> ret=g.getReturnType();
		m.invoke(h, ret.cast(value));
	    } catch(Exception e){
		Utils.showException(e,ricetta);
	    }
	    fireTableCellUpdated(row, col);
	}
    }
    
    
    @Override
    public boolean isCellEditable(int row, int col){
	return true;
    }
    
}
