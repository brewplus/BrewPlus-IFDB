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

package jmash;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import javax.swing.JInternalFrame;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 *
 * @author Alessandro
 */
public class XMLReader {
    
    /** Creates a new instance of ReadXML */
    public static Document readXML(String file) throws JDOMException, IOException {
	try{
	    SAXBuilder builder = new SAXBuilder();
	    String fileOri=new String(file);
	    InputSource is = null;
	    if(!file.startsWith("http://")) {
		file=new File(file).toURI().toString();
		is=new InputSource(file);
		is.setEncoding("UTF-8");
	    } else{
		is=new InputSource(file);
		is.setEncoding("UTF-8");
	    }
	    try{
		Document doc = builder.build(is);
		return doc;
	    } catch(JDOMException spe ){
		is = new InputSource(new FileReader(new File(fileOri)));
		is.setEncoding("ISO-8859-1");
		Document doc = builder.build(is);
		return doc;
	    }
	} catch(Exception ex){
	    Utils.showMsg(
		    "Buongiorno gentile utente!\n\nIl file che ha tentato di aprire (o uno dei file interni di BrewPlus) non corrisponde a quanto il programma e' in grado di interpretare.  Sarebbe particolarmente utile poter ricevere il file che avete appena tentato di aprire:\n\n"+file+"\n\nSaremmo molto lieti di riceverlo al seguente indirizzo email:\n\n brewplus@brewplus.t15.org\n\n in modo da correggere questo e futuri errori.\n\nGrazie per la pazienza e per la collaborazione!",
		    new JInternalFrame()
		    );
	    Utils.showException(ex);
	}
	return null;
    }
    public static Document XMLfromString(String str) throws JDOMException, IOException {
	SAXBuilder builder = new SAXBuilder();
	InputSource is =new InputSource(new StringReader(str));
	is.setEncoding("UTF-8");
	try{
	    Document doc = builder.build(is);
	    return doc;
	}catch(JDOMException spe ){
	    is = new InputSource(new StringReader(str));
	    is.setEncoding("ISO-8859-1");
	    Document doc = builder.build(is);
	    return doc;
	}
    }
    public static void listChildren(Object o, int depth) {
	
	printSpaces(depth);
	
	if (o instanceof Element) {
	    Element element = (Element) o;
	    System.out.println("Element: " + element.getName());
	    @SuppressWarnings("unchecked")
	    Iterator iterator = element.getContent().iterator();
	    while (iterator.hasNext()) {
		Object child = iterator.next();
		listChildren(child, depth+1);
	    }
	    @SuppressWarnings("unchecked")
	    Iterator iterator2 = element.getAttributes().iterator();
	    while (iterator2.hasNext()) {
		Attribute att = (Attribute)iterator2.next();
		printSpaces(depth);
		System.out.println("\tatt: " + att.getQualifiedName()+"="+att.getValue());
	    }
	} else if (o instanceof Document) {
	    System.out.println("Document");
	    Document doc = (Document) o;
	    @SuppressWarnings("unchecked")
	    Iterator iterator = doc.getContent().iterator();
	    while (iterator.hasNext()) {
		Object child = iterator.next();
		listChildren(child, depth+1);
	    }
	}
    }
    
    private static void printSpaces(int n) {
	for (int i = 0; i < n; i++) {
	    System.out.print(' ');
	}
    }
}
