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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Alessandro
 */
public class MashStepTableModel extends GenericTableModel<MashStep>{
    
    /**
     *
     */
    private static final long serialVersionUID = -5250295672007084331L;
    /** Creates a new instance of MashStepTableModel */
    public MashStepTableModel() {
        this.columnNames=new String[]  {    "T. iniziale", "Rampa",
        "T. finale",
        "Durata",
        "Descrizione",};
    }
    
    
    Ricetta ricetta;
    PanelMashStep panel;
    public MashStepTableModel(Ricetta ricetta) {
        this();
        this.ricetta=ricetta;
    }
    public MashStepTableModel(PanelMashStep panel) {
        this();
        this.panel=panel;
    }
    
    String fieldNames[] = {   "startTemp", "Ramp",
    "endTemp",
    "length",
    "nome",};
    
    
    private MashStep last=null;
    public MashStep getLast(){return this.last;}
    public void addRow(MashStep h, boolean heritage){
        if((this.last!=null) && heritage) {
            h.setStartTemp(this.last.getEndTemp());
        }
        this.dataValues.add(h);
        this.last=h;
        fireTableDataChanged();
    }
    
    public void removeAll(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
    public void remRow(int i){
        if(i>=0){
            if((i>1) && (i<this.dataValues.size()-1)) {
                (this.dataValues.get(i+1)).setStartTemp(
                        (this.dataValues.get(i-1)).getEndTemp(
                        ))
                        ;
            }
            this.dataValues.remove(i);
            fireTableDataChanged();
        }
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        MashStep h=this.dataValues.get(row);
        try{
            Method m=h.getClass().getMethod("get"+Utils.capitalize(this.fieldNames[col]));
            return m.invoke(h);
        } catch(Exception e){
            Utils.showException(e);
        }
        return null;
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        if(this.dataValues.get(row)!=null){
            
            MashStep h=(this.dataValues.get(row));
            Class<? extends Object> cl=h.getClass();
            try{
                Method g=cl.getMethod("get"+Utils.capitalize(this.fieldNames[col]));
                Method m=cl.getMethod("set"+Utils.capitalize(this.fieldNames[col]),g.getReturnType());
                Class<? extends Object> ret=g.getReturnType();
                m.invoke(h, ret.cast(value));
            } catch(Exception e){
                Utils.showException(e);
            }
            fireTableCellUpdated(row, col);
            if((col==1) && (this.dataValues.size()>row+1)){
                MashStep h2=(this.dataValues.get(row+1));
                if(h2!=null){
                    h2.setStartTemp(h.getEndTemp());
                    fireTableCellUpdated(row+1, 0);
                }
            }
            this.panel.mashModificato();
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        //if(row>0)return col==0||col>1;
        return true;
    }
    
    public XYSeriesCollection getDataSet(XYSeriesCollection ds){
        XYSeries series1 = new XYSeries("");
        int T=0;
	if(dataValues==null)return ds;
        for(MashStep h: this.dataValues){
            T+=h.getLength().intValue();
        }
        int start=0;
        MashStep prec=null;
        for(MashStep h: this.dataValues){
            h.setStart(start);
            if(prec!=null){
                h.setStartTemp(prec.getEndTemp());
            }
            series1 = new XYSeries(h.getNome());
            series1.add(start, h.getStartTemp());
            series1.add(start+h.getRamp(), h.getEndTemp());
            series1.add(start+h.getLength()+h.getRamp(), h.getEndTemp());
            
            start+=h.getLength()+h.getRamp();
            prec=h;
            ds.addSeries(series1);
        }
        //ds.addSeries(series1);
        return ds;
    }
}
