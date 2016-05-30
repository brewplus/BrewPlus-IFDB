/*
 * NumberFormatter.java
 *
 * Created on 26 dicembre 2008, 11.34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.tableModel;

import java.text.DecimalFormat;
import java.text.Format;

/**
 *
 * @author Alessandro
 */
public class NumberFormatter extends DecimalFormat{
    
    public NumberFormatter(String s){
	super(s);
    }
    
    public String nullFormat(Object obj){
	if(obj==null)return "";
	return format(obj);
    }

    private static Format DF00 = new DecimalFormat("0");
    private static Format DF01 = new DecimalFormat("0.0");
    private static Format DF02 = new DecimalFormat("0.00");    
    private static Format DF03 = new DecimalFormat("0.000");
    
    public static String format00(Object obj){
	if(obj==null)return "";
	try{
	    return DF00.format(obj);
	}catch(Exception ex){
	    return "";
	}
    }
    public static String format01(Object obj){
	if(obj==null)return "";
	try{
	    return DF01.format(obj);
	}catch(Exception ex){
	    return "";
	}
    }    
    public static String format02(Object obj){
	if(obj==null)return "";
	try{
	    return DF02.format(obj);
	}catch(Exception ex){
	    return "";
	}
    }    
    public static String format03(Object obj){
	if(obj==null)return "";
	try{
	    return DF03.format(obj);
	}catch(Exception ex){
	    return "";
	}
    }
}
