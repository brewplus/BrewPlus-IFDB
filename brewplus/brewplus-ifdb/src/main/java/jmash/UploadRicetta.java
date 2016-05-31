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

import java.io.IOException;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author  Alessandro
 */
public class UploadRicetta extends javax.swing.JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7237714392778714707L;
	private String xml;
    /** Creates new form UploadRicetta */
    public UploadRicetta(String xml, String nomeRicetta) {
        initComponents();
        setBorder(Utils.getDefaultBorder());        
        this.xml=xml;
    /*        String zxml = Base64.encodeObject( xml, Base64.GZIP| Base64.DONT_BREAK_LINES );
            System.out.println(">>>>");
            System.out.println(xml.length()+ " vs "+ zxml.length());
            System.out.println("<<<<<");
            xml    = (String)Base64.decodeToObject( zxml );
            System.out.println(xml);
      */      
        
        this.txtNome1.setText(nomeRicetta);
        Document doc=new Document();
        try{
//            doc=XMLReader.readXML("http://127.0.0.1/ricette_stile_xml.asp");
            doc=XMLReader.readXML("http://"+Main.config.getRemoteServer()+"/ricette_stile_xml.asp");
        } catch (JDOMException e) {
            Msg.showMsg(
                    "Il file non corrisponde al formato:\n",this
                    //+
                    //e.getCause().toString().replace("org.xml.sax.SAXParseException: ", "")
                    );
            return;
        } catch (IOException e) {
            Utils.showException(e,"Il file non esiste o non è leggibile",this);
            doDefaultCloseAction();
            return;
        }
        
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        try {
            
            outputter.output(doc,System.out );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ricette su hobbybirra.com");
        
        
        Element root=doc.getRootElement();
        Iterator it=root.getChildren().iterator();
        while(it.hasNext()){
            Element el=(Element)it.next();
            
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(el.getAttribute("nome").getValue());
            
            Iterator it2=el.getChildren().iterator();
            while(it2.hasNext()){
                Element el2=(Element)it2.next();
                StyleTreeNode node2 = new StyleTreeNode(
                        el2.getAttribute("nome").getValue()+" ("+el2.getAttribute("count").getValue()+")",
                        el2.getAttribute("id_stile").getValue()
                        );
                node.add(node2);
            }
            
            top.add(node);
        }
        
        this.treeModel = new DefaultTreeModel(top);
//treeModel.addTreeModelListener(new MyTreeModelListener());
        
//tree = new JTree(treeModel);
        
        this.jTree1 =  new JTree(this.treeModel);
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
        //}
        this.jScrollPane1.setViewportView(this.jTree1);
        /*
        jTree1.addTreeExpansionListener(new TreeExpansionListener(){
            public void treeExpanded(TreeExpansionEvent ev) {
                if(ev.getPath().getLastPathComponent() instanceof StyleTreeNode){
                    StyleTreeNode n=(StyleTreeNode)ev.getPath().getLastPathComponent();
                    expandNode(n);
                    System.out.println("ev.getSource() ="+n.getIdStile());
                }
            }
            
            // Required by TreeExpansionListener interface.
            public void treeCollapsed(TreeExpansionEvent e) {
                //System.out.println("Tree-collapsed event detected"+ e);
            }
            
        });*/
        
    }
    
    DefaultTreeModel treeModel;
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        this.jScrollPane2 = new javax.swing.JScrollPane();
        this.jTree2 = new javax.swing.JTree();
        this.jPanel3 = new javax.swing.JPanel();
        this.jPanel4 = new javax.swing.JPanel();
        this.jLabel3 = new javax.swing.JLabel();
        this.jTextField2 = new javax.swing.JTextField();
        this.jLabel4 = new javax.swing.JLabel();
        this.jPasswordField2 = new javax.swing.JPasswordField();
        this.jButton2 = new javax.swing.JButton();
        this.txtNome1 = new javax.swing.JTextField();

        this.jScrollPane2.setViewportView(this.jTree2);

        getContentPane().add(this.jScrollPane2, java.awt.BorderLayout.CENTER);

        this.jPanel3.setLayout(new java.awt.BorderLayout());

        this.jLabel3.setText("Utente");
        this.jPanel4.add(this.jLabel3);

        this.jTextField2.setPreferredSize(new java.awt.Dimension(64, 20));
        this.jPanel4.add(this.jTextField2);

        this.jLabel4.setText("Password");
        this.jPanel4.add(this.jLabel4);

        this.jPasswordField2.setText("jPasswordField1");
        this.jPanel4.add(this.jPasswordField2);

        this.jButton2.setText("Upload!");
        this.jPanel4.add(this.jButton2);

        this.jPanel3.add(this.jPanel4, java.awt.BorderLayout.SOUTH);

        this.txtNome1.setEditable(false);
        this.jPanel3.add(this.txtNome1, java.awt.BorderLayout.CENTER);

        getContentPane().add(this.jPanel3, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-556)/2, (screenSize.height-451)/2, 556, 451);
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNome1;
    // End of variables declaration//GEN-END:variables
    public class StyleTreeNode extends DefaultMutableTreeNode{
        /**
		 * 
		 */
		private static final long serialVersionUID = 6759538780901926009L;
		private  String idStile;
        
        
        public StyleTreeNode(String des, String idStile){
            super(des);
            this.idStile=idStile;
            
        }
        public String getIdStile(){return this.idStile;}
        @Override
		public boolean isLeaf(){return true;}
    }
    
}
