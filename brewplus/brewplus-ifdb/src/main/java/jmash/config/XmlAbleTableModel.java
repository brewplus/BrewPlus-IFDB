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

package jmash.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jmash.tableModel.GenericTableModel;
import jmash.Utils;
import jmash.interfaces.XmlAble;

/**
 *
 * @author AChiari
 */
public class XmlAbleTableModel extends GenericTableModel<XmlAble>{
    private XmlAble xmlAble;
    public XmlAbleTableModel(XmlAble xmlAble) {
        this.xmlAble= xmlAble;
        this.columnNames = xmlAble.getXmlFields();
    }
    @Override
    public Object getValueAt(int row, int col) {
        XmlAble h=this.dataValues.get(row);
        try {
            
            Method m=h.getClass().getMethod("get"+Utils.capitalize(xmlAble.getXmlFields()[col]));
            return m.invoke(h);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    @Override
    public void setValueAt(Object value,int row, int col) {
        XmlAble h=this.dataValues.get(row);
        Class<? extends Object> cl=h.getClass();
        try {
            Method g=cl.getMethod("get"+Utils.capitalize(xmlAble.getXmlFields()[col]));
            Method m=cl.getMethod("set"+Utils.capitalize(xmlAble.getXmlFields()[col]),g.getReturnType());
            Class<? extends Object> ret=g.getReturnType();
            if(ret.equals(String.class)){
                m.invoke(h, (String)value);
            }
            if(ret.equals(java.util.Date.class)){
                m.invoke(h, (java.util.Date)value);
            }
            if(ret.equals(Integer.class) ){
                m.invoke(h, (Integer)value);
            }
            if(ret.equals(Double.class) ){
                m.invoke(h, (Double)value);
            }
            if(ret.equals(Long.class) ){
                m.invoke(h, (Long)value);
            }
            if(ret.equals(Float.class) ){
                m.invoke(h, (Float)value);
            }
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        
        fireTableDataChanged();
    }
}
