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



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;



/**
 *
 *
 *
 * @author  Alessandro
 *
 */

public class BrowseHobbyBirra extends javax.swing.JInternalFrame {
    
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 909356925171057928L;

	/** Creates new form BrowseHobbyBirra */
    
    public BrowseHobbyBirra() {
        
        initComponents();
        
        setBorder(Utils.getDefaultBorder());
        
        Document doc=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/ricette_stile_xml.asp");
        if(doc==null) {
			return;
		}
        Document doc2=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/ricette_unconfirmed_stile_xml.asp");
        if(doc2==null) {
			return;
		}        
        
        
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        
        try {
            outputter.output(doc,System.out );
            
        } catch (IOException ex) {
            
            ex.printStackTrace();
            
        }
        
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ricette su hobbybirra.com");
        DefaultMutableTreeNode uTop = new DefaultMutableTreeNode("Ricette non confermate su hobbybirra.com");
        
        
        
        
        Element root=doc.getRootElement();
        Iterator it=root.getChildren().iterator();
        while(it.hasNext()){
            Element el=(Element)it.next();
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(el.getAttribute("nome").getValue());
            
            Iterator it2=el.getChildren().iterator();
            
            int count =0;
            while(it2.hasNext()){
                Element el2=(Element)it2.next();
                try {
                    count+=el2.getAttribute("count").getIntValue();
                } catch (DataConversionException ex) {
                    ex.printStackTrace();
                }
                StyleTreeNode node2 = new StyleTreeNode(
                        el2.getAttribute("nome").getValue()+" ("+el2.getAttribute("count").getValue()+")",
                        el2.getAttribute("id_stile").getValue()
                        );
                node.add(node2);
            }
            String str=el.getAttribute("nome").getValue()+" ("+count+")";
            node.setUserObject(str);
            top.add(node);
        }

        root=doc2.getRootElement();
        it=root.getChildren().iterator();
        while(it.hasNext()){
            Element el=(Element)it.next();
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(el.getAttribute("nome").getValue());
            int count=0;
            Iterator it2=el.getChildren().iterator();
            while(it2.hasNext()){
                Element el2=(Element)it2.next();
                try {
                    count+=el2.getAttribute("count").getIntValue();
                } catch (DataConversionException ex) {
                    ex.printStackTrace();
                }                
                StyleTreeNode node2 = new StyleTreeNode(
                        el2.getAttribute("nome").getValue()+" ("+el2.getAttribute("count").getValue()+")",
                        el2.getAttribute("id_stile").getValue()
                        );
                String str=el.getAttribute("nome").getValue()+" ("+count+")";
                node.setUserObject(str);                
                node.add(node2);
            }
            uTop.add(node);
        }
        
        
        
        this.treeModel = new DefaultTreeModel(top);
        this.treeModel2 = new DefaultTreeModel(uTop);
        
//treeModel.addTreeModelListener(new MyTreeModelListener());
        
        
        
//tree = new JTree(treeModel);
        
        
        
        this.jTree1 =  new JTree(this.treeModel);
        this.uTree =  new JTree(this.treeModel2);
        
        ImageIcon closeIcon = new ImageIcon(BrowseHobbyBirra.class.getResource("/jmash/images/folder.png"));
        ImageIcon openIcon = new ImageIcon(BrowseHobbyBirra.class.getResource("/jmash/images/folder_open.png"));
        ImageIcon leafIcon = new ImageIcon(BrowseHobbyBirra.class.getResource("/jmash/images/ingredients.jpg"));
        
        //if (leafIcon != null) {
        
        DefaultTreeCellRenderer renderer =
                
                new DefaultTreeCellRenderer();
        
        renderer.setOpenIcon(openIcon);
        
        renderer.setClosedIcon(closeIcon);
        
        renderer.setLeafIcon(leafIcon);
        
        renderer.setSize(24,24);
        
        this.jTree1.setRowHeight(34);
        
        this.jTree1.setCellRenderer(renderer);
        this.uTree.setRowHeight(34);
        this.uTree.setCellRenderer(renderer);        
       
        this.jScrollPane1.setViewportView(this.jTree1);
        this.jScrollPane2.setViewportView(this.uTree);
        
        this.jTree1.addTreeExpansionListener(new TreeExpansionListener(){
            @Override
            public void treeExpanded(TreeExpansionEvent ev) {
                if(ev.getPath().getLastPathComponent() instanceof StyleTreeNode){
                    StyleTreeNode n=(StyleTreeNode)ev.getPath().getLastPathComponent();
                    expandNode(n,"", BrowseHobbyBirra.this.treeModel);
                }
            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
            }
        });

        this.uTree.addTreeExpansionListener(new TreeExpansionListener(){
            @Override
            public void treeExpanded(TreeExpansionEvent ev) {
                if(ev.getPath().getLastPathComponent() instanceof StyleTreeNode){
                    StyleTreeNode n=(StyleTreeNode)ev.getPath().getLastPathComponent();
                    expandNode(n,"_unconfirmed", BrowseHobbyBirra.this.treeModel2);
                }
            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
            }
        });
        
        
        
        
        this.jTree1.addMouseListener(new MouseAdapter() {
            @Override
			public void mousePressed(MouseEvent event) {
                if((event.getClickCount() == 2) && (BrowseHobbyBirra.this.jTree1.getSelectionPath()!=null)){
                    if(BrowseHobbyBirra.this.jTree1.getSelectionPath().getLastPathComponent() instanceof RecipeTreeNode){
                        RecipeTreeNode node=(RecipeTreeNode)BrowseHobbyBirra.this.jTree1.getSelectionPath().getLastPathComponent();
                        System.out.println("ricetta selezionata ID ="+node.getId());
                        Document doc=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/view_ricetta_xml.asp?id_ricetta_HB="+node.getId());
                        if(doc==null) {
							return;
						}
                        Main.gui.nuovaRicetta(new Ricetta(doc,node.getId()));
                    }
                }
            }});

        this.uTree.addMouseListener(new MouseAdapter() {
            @Override
			public void mousePressed(MouseEvent event) {
                if((event.getClickCount() == 2) && (BrowseHobbyBirra.this.jTree1.getSelectionPath()!=null)){
                    if(BrowseHobbyBirra.this.jTree1.getSelectionPath().getLastPathComponent() instanceof RecipeTreeNode){
                        RecipeTreeNode node=(RecipeTreeNode)BrowseHobbyBirra.this.jTree1.getSelectionPath().getLastPathComponent();
                        System.out.println("ricetta selezionata ID ="+node.getId());
                        Document doc=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/view_ricetta_xml.asp?id_ricetta_HB="+node.getId());
                        if(doc==null) {
							return;
						}
                        Main.gui.nuovaRicetta(new Ricetta(doc,node.getId()));
                    }
                }
            }});            
    }
    
    DefaultTreeModel treeModel;
    DefaultTreeModel treeModel2;
    
    private void expandNode(StyleTreeNode node, String U, DefaultTreeModel tm){
        Document doc=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/ricette"+U+"_xml.asp?id_stile="+node.getIdStile());
        if(doc==null) {
			return;
		}

        node.removeAllChildren();
        this.treeModel.nodeStructureChanged(node);
        Element root=doc.getRootElement();
        @SuppressWarnings("unchecked")
        Iterator it=root.getChildren().iterator();
        int i=0;
        while(it.hasNext()){
            Element el=(Element)it.next();
            //System.out.println("el="+el.getAttribute("nome").getValue());
            RecipeTreeNode newNode =new RecipeTreeNode(
                    "("+el.getAttribute("id_ricetta_HB").getValue()+") - "+el.getAttribute("nome").getValue(),
                    el.getAttribute("id_ricetta_HB").getValue()
                    );
            tm.insertNodeInto(newNode, node, i++);
        }
    }
    
    
    
    /** This method is called from within the constructor to
     *
     * initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is
     *
     * always regenerated by the Form Editor.
     *
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        uTree = new javax.swing.JTree();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFont(getFont());

        jTabbedPane1.setFont(jTabbedPane1.getFont());
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(100, 100));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(100, 100));

        jTree1.setFont(jTree1.getFont());
        jScrollPane1.setViewportView(jTree1);

        jTabbedPane1.addTab("Ricette approvate", jScrollPane1);

        uTree.setFont(uTree.getFont());
        jScrollPane2.setViewportView(uTree);

        jTabbedPane1.addTab("In attesa di conferma", jScrollPane2);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-464)/2, (screenSize.height-412)/2, 464, 412);
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree uTree;
    // End of variables declaration//GEN-END:variables
    
    
    
    
    
    public class StyleTreeNode extends DefaultMutableTreeNode{
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 7101807915794876933L;
		private  String idStile;
        
        
        
        
        
        public StyleTreeNode(String des, String idStile){
            
            super(des);
            
            this.idStile=idStile;
            
            
            
        }
        
        public String getIdStile(){return this.idStile;}
        
        @Override
		public boolean isLeaf(){return false;}
        
    }
    
    public class RecipeTreeNode extends DefaultMutableTreeNode{
        
        /**
		 * 
		 */
		private static final long serialVersionUID = -810882682976398946L;
		private  String id;
        
        public RecipeTreeNode(String des, String id){
            
            super(des);
            
            this.id=id;
            
            
            
        }
        
        public String getId(){return this.id;}
        
        @Override
		public boolean isLeaf(){return true;}
        
    }
    
}

