/*
 *  
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

import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import jmash.tableModel.HopBuyTableModel;
import jmash.tableModel.MaltBuyTableModel;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author  AChiari
 */
public class Acquisto extends javax.swing.JInternalFrame {
    
    /** Creates new form Acquisto */
    //boolean showInventario=false;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    
    public static Acquisto buildFRMInventario(){
	Acquisto frm = new Acquisto();
	frm.readInventarioFromXML();
	return frm;
    }

    public static Acquisto newAcquisto(){
	Acquisto frm = new Acquisto();
	
	return frm;	
    }

    
    private Acquisto() {
	initComponents();
	setBorder(Utils.getDefaultBorder());
	
	tblHops.setDefaultEditor(Date.class, new JDateChooserCellEditor());
	tblMalts.setDefaultEditor(Date.class, new JDateChooserCellEditor());

        this.tblMalts.getColumnModel().getColumn(8).setCellEditor(new JDateChooserCellEditor());
        
	this.tblHops.getColumnModel().getColumn(7).setCellEditor(new JDateChooserCellEditor());
	this.tblHops.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer(){
	    JLabel label=new JLabel("");
            @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		if(markedRow>=0 && hopTableModel.getRow(markedRow).getDataAcquisto()!=null)
		    label.setText( dateFormat.format(hopTableModel.getRow(markedRow).getDataAcquisto()));
		else label.setText("");
		return label;
	    }
	});
	this.tblMalts.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer(){
	    JLabel label=new JLabel("");
            @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		if(markedRow>= 0 && maltTableModel.getRow(markedRow).getDataAcquisto()!=null)
		    label.setText( dateFormat.format(maltTableModel.getRow(markedRow).getDataAcquisto()));
		else label.setText("");
		return label;
	    }
	});
	
	this.tblHops.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
	this.tblHops.getColumnModel().getColumn(4).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.HOP_FORMS));
	
	tblMalts.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
	tblMalts.getColumnModel().getColumn(5).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.MALT_FORMS));
	
	tblMalts.getColumnModel().getColumn(0).setPreferredWidth(32);
	tblHops.getColumnModel().getColumn(0).setPreferredWidth(32);
	this.tblHops.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		return (JLabel)tblHops.getValueAt(markedRow,0);
	    }
	});
	this.tblMalts.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
            @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		return (JLabel)tblMalts.getValueAt(markedRow,0);
	    }
	});
	
	btnNewActionPerformed(null);

    }
    
    public void readInventarioFromXML(){
	btnNew.setVisible(false);
	btnOpen.setVisible(false);
	btnInventario.setVisible(false);
	jPanel3.setVisible(false);
	file=new File(Main.inventarioXML);
	try {
	    read(file);
	} catch (Exception ex) {
	    Utils.showException(ex);
	}	
    }
    
    private HopBuyTableModel hopTableModel=new HopBuyTableModel();
    private Picker hopPicker=new Picker(Gui.hopPickerTableModel);;
    private MaltBuyTableModel maltTableModel=new MaltBuyTableModel();
    //private Picker maltPicker=new Picker(Gui.maltPickerTableModel);
    private MaltTypePicker maltPicker=new MaltTypePicker();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fldDate = new com.toedter.calendar.JDateChooser();
        fldDes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMalts = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        addMalt = new javax.swing.JButton();
        remMalt1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHops = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        addHop = new javax.swing.JButton();
        remMalt = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Dati acquisto");
        setFont(getFont());

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filenew.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang"); // NOI18N
        btnNew.setToolTipText(bundle.getString("Nuovo_acquisto")); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/fileopen.png"))); // NOI18N
        btnOpen.setToolTipText("Apri acquisto");
        btnOpen.setIconTextGap(0);
        btnOpen.setMaximumSize(new java.awt.Dimension(37, 35));
        btnOpen.setMinimumSize(new java.awt.Dimension(37, 35));
        btnOpen.setPreferredSize(new java.awt.Dimension(37, 35));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOpen);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filesave.png"))); // NOI18N
        btnSave.setToolTipText("Salva acquisto");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/editpaste.png"))); // NOI18N
        btnInventario.setToolTipText("Copia nell'inventario");
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        jToolBar1.add(btnInventario);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acquisto"));
        jPanel3.setFont(jPanel3.getFont());

        jLabel1.setFont(jLabel1.getFont());
        jLabel1.setText("Data ");

        fldDate.setFont(fldDate.getFont());

        fldDes.setFont(fldDes.getFont());

        jLabel2.setFont(jLabel2.getFont());
        jLabel2.setText("Nome");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(fldDes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(fldDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jLabel1)
                        .add(fldDes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(fldDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel3, java.awt.BorderLayout.NORTH);

        jTabbedPane1.setFont(jTabbedPane1.getFont());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setFont(jScrollPane2.getFont());
        jScrollPane2.setMinimumSize(new java.awt.Dimension(100, 100));

        tblMalts.setFont(tblMalts.getFont());
        tblMalts.setModel(maltTableModel);
        jScrollPane2.setViewportView(tblMalts);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(40, 100));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        addMalt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
        addMalt.setIconTextGap(0);
        addMalt.setMaximumSize(new java.awt.Dimension(36, 36));
        addMalt.setMinimumSize(new java.awt.Dimension(36, 36));
        addMalt.setPreferredSize(new java.awt.Dimension(36, 36));
        addMalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaltActionPerformed(evt);
            }
        });
        jPanel5.add(addMalt, new java.awt.GridBagConstraints());

        remMalt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edittrash.png"))); // NOI18N
        remMalt1.setIconTextGap(0);
        remMalt1.setMaximumSize(new java.awt.Dimension(36, 36));
        remMalt1.setMinimumSize(new java.awt.Dimension(36, 36));
        remMalt1.setPreferredSize(new java.awt.Dimension(36, 36));
        remMalt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remMalt1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(28, 0, 0, 0);
        jPanel5.add(remMalt1, gridBagConstraints);

        jPanel2.add(jPanel5, java.awt.BorderLayout.WEST);

        jTabbedPane1.addTab("Malti", jPanel2);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 100));

        tblHops.setFont(tblHops.getFont());
        tblHops.setModel(hopTableModel);
        jScrollPane1.setViewportView(tblHops);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(40, 100));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        addHop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
        addHop.setIconTextGap(0);
        addHop.setMaximumSize(new java.awt.Dimension(36, 36));
        addHop.setMinimumSize(new java.awt.Dimension(36, 36));
        addHop.setPreferredSize(new java.awt.Dimension(36, 36));
        addHop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHopActionPerformed(evt);
            }
        });
        jPanel4.add(addHop, new java.awt.GridBagConstraints());

        remMalt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edittrash.png"))); // NOI18N
        remMalt.setIconTextGap(0);
        remMalt.setMaximumSize(new java.awt.Dimension(36, 36));
        remMalt.setMinimumSize(new java.awt.Dimension(36, 36));
        remMalt.setPreferredSize(new java.awt.Dimension(36, 36));
        remMalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remMaltActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(28, 0, 0, 0);
        jPanel4.add(remMalt, gridBagConstraints);

        jPanel1.add(jPanel4, java.awt.BorderLayout.WEST);

        jTabbedPane1.addTab("Luppoli e altro", jPanel1);

        jPanel6.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
	inventario();
    }//GEN-LAST:event_btnInventarioActionPerformed
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
	save();
	
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private File file=null;
    public void save(){

	if(this.file==null){
	    this.file=Utils.pickFileToSave(this, Main.recipeDir);
	}
	if(this.file==null)return;
	
	AcquistoIngredienti obj= new AcquistoIngredienti();
	obj.setLuppoli(hopTableModel.getRows());
	obj.setMalti(maltTableModel.getRows());
	obj.setDes(fldDes.getText());
	obj.setData(fldDate.getDate());
	Document doc=obj.toXml();
	Utils.saveXmlAsFile(doc, file, this);
	
	setTitle(this.file.getName());
    }
    
    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
	File f=Utils.pickFileToLoad(new JInternalFrame(), Main.shoppingDir);
	
	try {
	    read(f);
	} catch (Exception ex) {
	    Utils.showException(ex);
	}
    }//GEN-LAST:event_btnOpenActionPerformed
    
    public void read(File file) throws Exception{
	if(file!=null) {
	    btnNewActionPerformed(null);
	    Document doc=Utils.readFileAsXml(file.toString());
	    Element root=doc.getRootElement();
	    if(root.getName().compareToIgnoreCase(new AcquistoIngredienti().getClass().getName())==0){
		this.file=file;
		setTitle(this.file.getName());
		AcquistoIngredienti acquistoIngredienti=AcquistoIngredienti.fromXml(root);
		fldDate.setDate(acquistoIngredienti.getData());
		fldDes.setText(acquistoIngredienti.getDes());
		for(Object obj: root.getChildren()){
		    Element elem=(Element)obj;
		    if(elem.getName().compareToIgnoreCase(new Hop().getClass().getName())==0){
			hopTableModel.addRow(Hop.fromXml(elem));
		    }
		    if(elem.getName().compareToIgnoreCase(new Malt().getClass().getName())==0){
			maltTableModel.addRow(Malt.fromXml(elem));
		    }
		}
	    } else{
		Msg.showMsg("Il file non è una lista della spesa Hobbybrew!",this);
	    }
	}
    }
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
	hopTableModel.clear();
	maltTableModel.clear();
	fldDes.setText("");
	fldDate.setDate(null);
	this.file=null;
	setTitle("Nuovo acquisto");
    }//GEN-LAST:event_btnNewActionPerformed
    
    private void remMalt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remMalt1ActionPerformed
	int i=this.tblMalts.getSelectedRow();
	this.maltTableModel.remRow(i);
    }//GEN-LAST:event_remMalt1ActionPerformed
    
    private void remMaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remMaltActionPerformed
	int i=this.tblHops.getSelectedRow();
	this.hopTableModel.remRow(i);
    }//GEN-LAST:event_remMaltActionPerformed
    
    private void addMaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaltActionPerformed
	this.maltPicker.startModal(this);
	MaltType type=(MaltType)this.maltPicker.getSelection();
	if(type!=null){
	    this.maltTableModel.addRow(new Malt(type));
	}
    }//GEN-LAST:event_addMaltActionPerformed
    
    private void addHopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHopActionPerformed
	this.hopPicker.startModal(this);
	HopType type=(HopType)this.hopPicker.getSelection();
	if(type!=null){
	    this.hopTableModel.addRow(new Hop(new Ricetta(),type));
	}
    }//GEN-LAST:event_addHopActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addHop;
    private javax.swing.JButton addMalt;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private com.toedter.calendar.JDateChooser fldDate;
    private javax.swing.JTextField fldDes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton remMalt;
    private javax.swing.JButton remMalt1;
    private javax.swing.JTable tblHops;
    private javax.swing.JTable tblMalts;
    // End of variables declaration//GEN-END:variables
    
    public void inventario(){
	Acquisto acquisto=Acquisto.buildFRMInventario();
	acquisto.addRows(maltTableModel.getRows(), hopTableModel.getRows(), fldDate.getDate());
	Main.gui.addFrame(acquisto);
    }
    
    public void addRows(List<Malt> malts, List<Hop> hops, Date dataRif){
	for(Malt m:malts) {
	    if(m.getDataAcquisto()==null)
		m.setDataAcquisto(dataRif);
	    maltTableModel.addRow(Malt.fromXml(m.toXml()));
	}
	for(Hop h:hops) {
	    if(h.getDataAcquisto()==null)
		h.setDataAcquisto(dataRif);
	    hopTableModel.addRow(Hop.fromXml(h.toXml()));
	}
    }
    
    public List<Malt> getMalts(){return maltTableModel.getRows();}
    public List<Hop>  getHops(){return hopTableModel.getRows();}
    
    public List<Malt> getMalts(String des){
	List<Malt> list=new ArrayList<Malt>();
	for(Malt m:maltTableModel.getRows()){
	    if(m.getNome().equalsIgnoreCase(des))
		list.add(Malt.fromXml(m.toXml()));
	}
	return list;
    }
    public List<Hop>  getHops(String des) {
	List<Hop> list=new ArrayList<Hop>();
	for(Hop m:hopTableModel.getRows()){
	    if(m.getNome().equalsIgnoreCase(des))
		list.add(Hop.fromXml(m.toXml()));
	}
	return list;
    }
}
