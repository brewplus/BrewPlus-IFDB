/*
 * Unicode.java
 *
 * Created on 15 settembre 2008, 19.35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import jmash.BrowseBrewMonkey;
import jmash.RecipeData;
import jmash.Utils;

/**
 * Unicode - show a page of Unicode characters. BUG: Times throws a bunch of
 * exceptions on page 2 and 3, that can not be caught as they occur in the AWT
 * thread. On some platforms.
 * 
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: Unicode.java,v 1.2.2.1 2010/02/06 16:54:44 dariolipari Exp $
 */
public class Unicode extends JFrame {

  /** "main program" method - construct and show */
  public static void main(String[] av) {
      new BrowseBrewMonkey();
	RecipeData R=new RecipeData();
	R.setNome(getAll());
	R.setNote(getAll());	
	Utils.saveXmlAsFile(R.toXml(),new File("C:/test.xml"),new JInternalFrame());
	R.read(Utils.readFileAsXml("C:/test.xml"));
  }
    private static String getAll(){
	String s="";
	for(int i=100;i<3000;i++)
	s+=(char) i;
	return s;
    }
}