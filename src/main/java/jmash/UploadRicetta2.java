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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author  Alessandro
 */
public class UploadRicetta2 extends javax.swing.JInternalFrame {
    /**
     *
     */
    private static final long serialVersionUID = 3695389393696260527L;
    private String xml;
    private String nomeRicetta;
//    private String idRicettaHB;
    /** Creates new form UploadRicetta2 */
    public UploadRicetta2(String xml, String nomeRicetta, String idRicettaHB) {
        initComponents();
        setBorder(Utils.getDefaultBorder());
        this.xml=xml;
        this.nomeRicetta=nomeRicetta;
//        this.idRicettaHB=idRicettaHB;
        
        
        
    /*        String zxml = Base64.encodeObject( xml, Base64.GZIP| Base64.DONT_BREAK_LINES );
            System.out.println(">>>>");
            System.out.println(xml.length()+ " vs "+ zxml.length());
            System.out.println("<<<<<");
            xml    = (String)Base64.decodeToObject( zxml );
            System.out.println(xml);
     */
        
        this.txtNome.setText(nomeRicetta);
        
        this.txtUser.setText(Main.config.getNickIHB());
        this.txtPwd.setText(Main.config.getPasswordIHB());
        this.jTree2.removeAll();
        reloadTree();
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
        this.jTree2.setRowHeight(34);
        this.jTree2.setCellRenderer(renderer);
        
        this.jScrollPane2.setViewportView(this.jTree2);
        
    }
    
    private void reloadTree(){
        Document doc=Utils.readFileAsXml("http://"+Main.config.getRemoteServer()+"/ricette_stile_xml.asp");
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ricette su Birra Birra");
        if(doc==null) {
            this.treeModel = new DefaultTreeModel(top);
            this.jTree2 =  new JTree(this.treeModel);
            return;
        }
        
        Element root=doc.getRootElement();
        @SuppressWarnings("unchecked")
        Iterator it=root.getChildren().iterator();
        while(it.hasNext()){
            Element el=(Element)it.next();
            
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(el.getAttribute("nome").getValue());
            @SuppressWarnings("unchecked")
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
        this.jTree2 =  new JTree(this.treeModel);
        
    }
    
    
    DefaultTreeModel treeModel;
    public class StyleTreeNode extends DefaultMutableTreeNode{
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private  String idStile;
        
        
        public StyleTreeNode(String des, String idStile){
            super(des);
            this.idStile=idStile;
            
        }
        public String getIdStile(){return this.idStile;}
        @Override
        public boolean isLeaf(){return true;}
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jPanel3 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPwd = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        jScrollPane2.setViewportView(jTree2);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome ricetta");

        jLabel3.setText("Utente");

        txtUser.setPreferredSize(new java.awt.Dimension(64, 20));

        jLabel4.setText("Password");

        txtPwd.setPreferredSize(new java.awt.Dimension(96, 24));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/agt_internet.png")));
        jButton2.setText("Upload!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/kgpg.png")));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(txtUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtPwd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2))
                    .add(txtNome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtNome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jButton2)
                            .add(jLabel4)
                            .add(txtUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(txtPwd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );
        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-556)/2, (screenSize.height-422)/2, 556, 422);
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if((this.jTree2.getSelectionPath()==null) || !(this.jTree2.getSelectionPath().getLastPathComponent() instanceof StyleTreeNode)){
            Msg.showMsg("Devi selezionare lo stile sotto il quale caricare la ricetta!",this);
            return;
        }
        if((this.txtUser.getText()==null) || (this.txtUser.getText().length()==0)){
            Msg.showMsg("Il nome utente è obbligatorio!",this);
            return;
        }
        if((this.txtPwd.getPassword()==null) || (this.txtPwd.getPassword().length==0)){
            Msg.showMsg("La password è obbligatoria!",this);
            return;
        }
        this.nomeRicetta=this.txtNome.getText();
        if((this.nomeRicetta==null) || (this.nomeRicetta.length()==0)){
            Msg.showMsg("Il nome della ricetta è obbligatorio!",this);
            return;
        }
        StyleTreeNode node=null;
        if(this.jTree2.getSelectionPath().getLastPathComponent() instanceof StyleTreeNode){
            node= (StyleTreeNode)this.jTree2.getSelectionPath().getLastPathComponent();
        }
        
        try {
            // Construct data
            
            String data = URLEncoder.encode("nick", "UTF-8") + "=" + URLEncoder.encode(this.txtUser.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(new String(this.txtPwd.getPassword()), "UTF-8");
            data += "&" + URLEncoder.encode("id_stile", "UTF-8") + "=" + URLEncoder.encode(node.getIdStile(), "UTF-8");
            data += "&" + URLEncoder.encode("nome_ricetta", "UTF-8") + "=" + URLEncoder.encode(this.nomeRicetta, "UTF-8");
            data += "&" + URLEncoder.encode("xml_ricetta", "UTF-8") + "=" + URLEncoder.encode(this.xml, "UTF-8");
            
            // Send data
            URL url = new URL("http://"+Main.config.getRemoteServer()+"/upload_ricetta.asp?"+data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String str="";
            while ((line = rd.readLine()) != null) {
                str+=line;
            }
            rd.close();
            Msg.showMsg(str,this);
            doDefaultCloseAction();
        } catch (Exception e) {            
	    Utils.showException(e,"Errore in upload", this);
        }
        reloadTree();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree2;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPwd;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
    
}
