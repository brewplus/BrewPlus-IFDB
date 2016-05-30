/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.test;

public class SVGTest {}
/*
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import jmash.component.JMashSpinner;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.JSVGScrollPane;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;


public class SVGTest {
    
    public static void main(String[] args) {
	// Create a new JFrame.
	JFrame f = new JFrame("Batik");
	SVGTest app = new SVGTest(f);
	// Add components to the frame.
	f.getContentPane().add(app.createComponents());
	// Display the frame.
	f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});
	f.setSize(400, 400);
	f.setVisible(true);
    }
    // The frame.
    protected JFrame frame;
    // The "Load" button, which displays up a file chooser upon clicking.
    protected JButton button = new JButton("Load...");
    protected JButton btn = new JButton("Modify");
    // The status label.
    protected JLabel label = new JLabel();
    // The SVG canvas.
    protected JSVGCanvas svgCanvas = new JSVGCanvas();
    GraphicsEnvironment gEnv = GraphicsEnvironment
	    .getLocalGraphicsEnvironment();
    String fonts[]= gEnv.getAvailableFontFamilyNames();    
    JSVGScrollPane svgPanel=new JSVGScrollPane(svgCanvas);
    Vector vector = new Vector();
    public SVGTest(JFrame f) {
	frame = f;
	
	for (int i = 1; i < fonts.length; i++) {
	    vector.addElement(fonts[i]);
	}
    }
    JPanel pnl = new JPanel();
    GridLayout GL=new GridLayout();
    public JComponent createComponents() {
	// Create a panel and add the button, status label and the SVG canvas.
	final JPanel panel = new JPanel(new BorderLayout());
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	p.add(button);
	p.add(btn);
	p.add(label);
	svgCanvas.setDocumentState(svgCanvas.ALWAYS_STATIC);
	svgCanvas.setDisableInteractions(true);
	svgPanel.setScrollbarsAlwaysVisible(false);
	
	pnl.setLayout(GL);
	//JScrollPane SC2=new JScrollPane();
	//SC2.getViewport().add(svgCanvas);
	panel.add("North", p);
	panel.add("Center", svgPanel);
	JScrollPane SC=new JScrollPane();
	SC.getViewport().add(pnl);
	panel.add("South", SC);
	
	// Set the button action.
	button.addActionListener(new ActionListener() {
	    
	    public void actionPerformed(ActionEvent ae) {
		svgCanvas.setURI("file:/D:/lbl.svg");
		if(true)return;
		JFileChooser fc = new JFileChooser(".");
		int choice = fc.showOpenDialog(panel);
		if (choice == JFileChooser.APPROVE_OPTION) {
		    File f = fc.getSelectedFile();
		    try {
			System.out.println(f.toURL().toString());
			svgCanvas.setURI(f.toURL().toString());
		    } catch (IOException ex) {
			ex.printStackTrace();
		    }
		    
		}
	    }
	});
	
	btn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		SVGDocument doc = svgPanel.getCanvas().getSVGDocument();
		NodeList ls=doc.getElementsByTagName("text");
		for(int i=0;i<ls.getLength();i++){
		    System.out.println(
			    ls.item(i).getTextContent().trim());
		    ls.item(i).setTextContent("."+ls.item(i).getTextContent().trim()+".");
		}
	    }
	});
	
	// Set the JSVGCanvas listeners.
	svgCanvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {
	    public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
		label.setText("Document Loading...");
	    }
	    public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
		label.setText("Document Loaded.");
	    }
	});
	
	svgCanvas.addGVTTreeBuilderListener(new GVTTreeBuilderAdapter() {
	    public void gvtBuildStarted(GVTTreeBuilderEvent e) {
		label.setText("Build Started...");
	    }
	    public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
		label.setText("Build Done.");
		build();
	    }
	});
	
	svgCanvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {
	    public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
		label.setText("Rendering Started...");
	    }
	    public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
		label.setText("");
	    }
	});
	
	return panel;
    }
    boolean flag=false;
    public void build(){
	if(flag)return;
	flag=true;
	doc = svgCanvas.getSVGDocument();
	final NodeList ls=doc.getElementsByTagName("text");
	pnl.removeAll();
	GL.setColumns(1);
	GL.setRows(ls.getLength());
	for(int i=0;i<ls.getLength();i++){
	    pnl.add(new MyTextField(ls.item(i).getTextContent().trim(),ls.item(i)));
	}
    }

    SVGDocument doc;
    class MyTextField extends JPanel{
	private Node N;
	JTextField txt=new JTextField();
	JMashSpinner spin=new JMashSpinner();
	JComboBox combo=new JComboBox(vector);
	public MyTextField(String stxt, Node N){
	    txt.setText(stxt);
	    this.N=N;
	    txt.setPreferredSize(new Dimension(300,22));
	    spin.setPreferredSize(new Dimension(100,22));
	    spin.setModel(20,1,100,1);
	    add(txt);
	    add(spin);
	    add(combo);
	    txt.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev){
		    action(null);
		}
	    });
	    combo.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev){
		    action3(null);
		}
	    });
	    spin.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent ev){
		    action2(null);
		}
	    });
	}
	public void action(ActionEvent ev){
	    N.setTextContent(txt.getText());
	    svgCanvas.setSVGDocument(doc);
	}
	
	int i;
	public void action2(ActionEvent ev){
	    ((Element)N).setAttributeNS(null,"font-size",spin.getIntegerValue()+"");
	    svgCanvas.setSVGDocument(doc);
	}
	public void action3(ActionEvent ev){
	    ((Element)N).setAttributeNS(null,"font-family",(String)combo.getSelectedItem());
	    svgCanvas.setSVGDocument(doc);
	}
    }
}
 **/