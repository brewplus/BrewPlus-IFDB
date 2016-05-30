/*
 * Gyle.java
 *
 * Created on 19 febbraio 2008, 19.32
 */

package jmash;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import jmash.tableModel.HopTableModel;
import jmash.tableModel.MaltTableModel;
import jmash.tableModel.NumberFormatter;
import jmash.tableModel.SummaryTableModel;
import jmash.tableModel.YeastTableModel;

/**
 *
 * @author  Alessandro
 */
public class Gyle extends javax.swing.JPanel {
    
    /** Creates new form Gyle */
    public Gyle(Ricetta ricetta) {
        this.ricetta=ricetta;
	this.hopTableModel=new HopTableModel(ricetta);
	this.maltTableModel=new MaltTableModel(ricetta);
	this.summaryTableModel=new SummaryTableModel(ricetta);
	this.yeastTableModel=new YeastTableModel(ricetta);
	this.hopPicker=new Picker(Gui.hopPickerTableModel);
	this.hopPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/hops.gif")));
	this.maltPicker=new MaltTypePicker();
	
	
/*
	this.maltPicker=new Picker(Gui.maltPickerTableModel);
	this.maltPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/malts.png")));
	maltPicker.setFilters(new String[]{"Tutti","Grani","Estratto secco","Estratto liquido"}, null);
 */
	this.yeastPicker=new Picker(Gui.yeastPickerTableModel);
	this.yeastPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/malts.png")));
	this.brewStylePicker=new Picker(Gui.brewStylePickerTableModel);
	this.brewStylePicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/birre.png")));
	
	this.maltSorter = new TableSorter(this.maltTableModel);
	this.hopSorter = new TableSorter(this.hopTableModel);
	this.summarySorter = new TableSorter(this.summaryTableModel);
	this.yeastSorter = new TableSorter(this.yeastTableModel);
	
	this.maltTableModel.addTableModelListener(new TableModelListener(){
            @Override
	    public void tableChanged(TableModelEvent ev){
		ricettaModificata();
	    }
	});
	
	this.hopTableModel.addTableModelListener(new TableModelListener(){
            @Override
	    public void tableChanged(TableModelEvent ev){
		ricettaModificata();
	    }
	});        
	initComponents();
        
	this.maltSorter.setTableHeader(this.tblMalts.getTableHeader());
	this.hopSorter.setTableHeader(this.tblHops.getTableHeader());
	this.summarySorter.setTableHeader(this.tblSummary.getTableHeader());
	
	this.tblHops.setSelectionMode(0);
	this.tblMalts.setSelectionMode(0);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblMalts);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblMalts.getTableHeader());
	ToolTipManager.sharedInstance().unregisterComponent(this.tblHops);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblHops.getTableHeader());
	ToolTipManager.sharedInstance().unregisterComponent(this.tblSummary);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblSummary.getTableHeader());
	//        tblHops.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor());
	tblHops.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
	tblHops.getColumnModel().getColumn(4).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.HOP_FORMS));
	tblHops.getColumnModel().getColumn(7).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.HOP_USAGE));
	tblHops.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblHops.getColumnModel().getColumn(0).setPreferredWidth(32);
	tblHops.getColumnModel().getColumn(11).setPreferredWidth(32);
	
	tblHops.setDefaultRenderer(JButton.class,
		new jmash.component.JTableButtonRenderer(tblHops.getDefaultRenderer(JButton.class)));
	tblHops.addMouseListener(new jmash.component.JTableButtonMouseListener(tblHops, ricetta));
	
	tblMalts.getColumnModel().getColumn(6).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.MALT_FORMS));
	tblMalts.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
//	tblMalts.getColumnModel().getColumn(4).setCellRenderer(new BarCellRenderer());
	tblMalts.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblHops.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblMalts.getColumnModel().getColumn(0).setPreferredWidth(32);
	

	setVolume(Main.config.getVolumeFin());
	setVolumeBoll(Main.config.getVolumeBoil());
	setUnitaMisura("litri");
	
	spinVolumeBollWO.setModelFormat(23.0,0.25,9999999.0,0.25,"0.00","Gyle.VB");
	spinVolumeFinWO.setModelFormat(23.0,0.25,9999999.0,0.25,"0.00","Gyle.VF");
	
	spinVolumeBollWO.setDoubleValue(Main.config.getVolumeBoil());
	spinVolumeFinWO.setDoubleValue(Main.config.getVolumeFin());
	spinEfficienza.setValue(Main.config.getEfficienza());
	spinBollitura.setValue(Main.config.getBoilTime());
	
	((javax.swing.SpinnerNumberModel)spinEfficienza.getModel()).setMaximum(new Integer(100));
	((javax.swing.SpinnerNumberModel)spinBollitura.getModel()).setMinimum(new Integer(0));
	((javax.swing.SpinnerNumberModel)spinEfficienza.getModel()).setMinimum(new Integer(1));
	
	jPanel2.setBackground(maltSorter.getTableHeader().getBackground());
	
	tblSummary.setCellSelectionEnabled(false);
	tblSummary.setRowSelectionAllowed(false);
	tblSummary.setColumnSelectionAllowed(false);
	
	this.tblHops.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
	    /**
	     *
	     */
	    private static final long serialVersionUID = 1287616464596441930L;
	    
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		JLabel ret=(JLabel)super.getTableCellRendererComponent(tblDataTable,value,isSelected,hasFocus,markedRow,col);
		ret.setIcon(Main.hopIcon);ret.setText("");
		return ret;
	    }
	});
	this.tblHops.getColumnModel().getColumn(11).setCellRenderer(new DefaultTableCellRenderer(){
	    /**
	     *
	     */
	    private static final long serialVersionUID = -1649193412422579171L;
	    
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		return (Component)Gyle.this.tblHops.getValueAt(markedRow,11);
	    }
	});
	this.tblMalts.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer(){
	    
	    private static final long serialVersionUID = -4314493966636560005L;
	    
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
		return (JLabel)Gyle.this.tblMalts.getValueAt(markedRow,0);
	    }
	});

	this.glassPanel.setMinimumSize(new Dimension(this.dimx,this.dimy));
	this.glassPanel.setPreferredSize(new Dimension(this.dimx,this.dimy));
	this.glassPanel.setMaximumSize(new Dimension(this.dimx,this.dimy));
	this.glassPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	this.jPanel10.add(this.glassPanel,2);        
    }
    HopTableModel hopTableModel;
    YeastTableModel yeastTableModel;
    MaltTableModel maltTableModel;
    SummaryTableModel summaryTableModel;
    Ricetta ricetta;
    Picker hopPicker;
    MaltTypePicker maltPicker;
    Picker yeastPicker;
    Picker brewStylePicker;
    TableSorter maltSorter, hopSorter, summarySorter, yeastSorter;
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnAdd5 = new javax.swing.JButton();
        btnAdd6 = new javax.swing.JButton();
        btnAdd7 = new javax.swing.JButton();
        btnAdd8 = new javax.swing.JButton();
        btnAdd9 = new javax.swing.JButton();
        btnAdd10 = new javax.swing.JButton();
        btnAdd11 = new javax.swing.JButton();
        btnAdd12 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtStile = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOG1 = new javax.swing.JTextField();
        txtOG2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFG1 = new javax.swing.JTextField();
        txtFG2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIBU1 = new javax.swing.JTextField();
        txtIBU2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spinEfficienza = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        spinBollitura = new javax.swing.JSpinner();
        lock = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        fldNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEBC1 = new javax.swing.JTextField();
        txtEBC2 = new javax.swing.JTextField();
        comboVolFin = new javax.swing.JComboBox();
        comboVolBoll = new javax.swing.JComboBox();
        spinVolumeBollWO = new jmash.component.JMashSpinner();
        spinVolumeFinWO = new jmash.component.JMashSpinner();
        jPanel18 = new javax.swing.JPanel();
        btnStyle = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMalts = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        btnAdd1 = new javax.swing.JButton();
        btnRem1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHops = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRem = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(96, 10));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setMinimumSize(new java.awt.Dimension(96, 10));
        jPanel6.setPreferredSize(new java.awt.Dimension(96, 180));

        btnAdd5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/steam.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang"); // NOI18N
        btnAdd5.setToolTipText(bundle.getString("evaporazione")); // NOI18N
        btnAdd5.setAlignmentX(0.5F);
        btnAdd5.setMaximumSize(new java.awt.Dimension(30, 30));
        btnAdd5.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAdd5.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd5ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd5);

        btnAdd6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/extract.png"))); // NOI18N
        btnAdd6.setToolTipText(bundle.getString("diluizioni")); // NOI18N
        btnAdd6.setAlignmentX(0.5F);
        btnAdd6.setMaximumSize(new java.awt.Dimension(30, 30));
        btnAdd6.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAdd6.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd6ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd6);

        btnAdd7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/upload.png"))); // NOI18N
        btnAdd7.setToolTipText(bundle.getString("upload")); // NOI18N
        btnAdd7.setAlignmentX(0.5F);
        btnAdd7.setEnabled(false);
        btnAdd7.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd7.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd7ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd7);

        btnAdd8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/bowling.gif"))); // NOI18N
        btnAdd8.setToolTipText(bundle.getString("StrikeTemp")); // NOI18N
        btnAdd8.setAlignmentX(0.5F);
        btnAdd8.setMaximumSize(new java.awt.Dimension(30, 30));
        btnAdd8.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAdd8.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd8ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd8);

        btnAdd9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/fileprint.png"))); // NOI18N
        btnAdd9.setToolTipText(bundle.getString("Print")); // NOI18N
        btnAdd9.setAlignmentX(0.5F);
        btnAdd9.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd9.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd9ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd9);

        btnAdd10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/h2o.png"))); // NOI18N
        btnAdd10.setToolTipText("Acqua necessaria");
        btnAdd10.setAlignmentX(0.5F);
        btnAdd10.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd10.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd10ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd10);

        btnAdd11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/checkInventario.png"))); // NOI18N
        btnAdd11.setToolTipText("Controlla in inventario");
        btnAdd11.setAlignmentX(0.5F);
        btnAdd11.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd11.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd11ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd11);

        btnAdd12.setToolTipText("");
        btnAdd12.setAlignmentX(0.5F);
        btnAdd12.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd12.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd12ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd12);

        jPanel10.add(jPanel6);

        jSeparator1.setPreferredSize(new java.awt.Dimension(96, 10));
        jPanel10.add(jSeparator1);

        jSeparator2.setPreferredSize(new java.awt.Dimension(96, 10));
        jPanel10.add(jSeparator2);

        jPanel5.setMinimumSize(new java.awt.Dimension(84, 2));
        jPanel5.setPreferredSize(new java.awt.Dimension(96, 48));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel9.setOpaque(true);
        jLabel9.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel10.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jLabel11, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel16.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel17.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel18.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jLabel18, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Mos.");
        jLabel20.setToolTipText("Mosher");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel20.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel20.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel20, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Dan.");
        jLabel21.setToolTipText("Daniels");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel21.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel21.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel21, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Mor.");
        jLabel22.setToolTipText("Morey");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel22.setMaximumSize(new java.awt.Dimension(28, 14));
        jLabel22.setMinimumSize(new java.awt.Dimension(28, 14));
        jLabel22.setOpaque(true);
        jLabel22.setPreferredSize(new java.awt.Dimension(26, 14));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jLabel22, gridBagConstraints);

        jPanel10.add(jPanel5);

        add(jPanel10, java.awt.BorderLayout.EAST);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel12.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 200));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("PropSheet.setBackground"));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 14));
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 150));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Stile");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtStile.setBackground(new java.awt.Color(204, 204, 255));
        txtStile.setEditable(false);
        txtStile.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtStile, gridBagConstraints);

        jLabel3.setText("OG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtOG1.setBackground(new java.awt.Color(204, 204, 255));
        txtOG1.setEditable(false);
        txtOG1.setMinimumSize(new java.awt.Dimension(40, 20));
        txtOG1.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtOG1, gridBagConstraints);

        txtOG2.setBackground(new java.awt.Color(204, 204, 255));
        txtOG2.setEditable(false);
        txtOG2.setMinimumSize(new java.awt.Dimension(40, 20));
        txtOG2.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtOG2, gridBagConstraints);

        jLabel4.setText("FG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        txtFG1.setBackground(new java.awt.Color(204, 204, 255));
        txtFG1.setEditable(false);
        txtFG1.setMinimumSize(new java.awt.Dimension(40, 20));
        txtFG1.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtFG1, gridBagConstraints);

        txtFG2.setBackground(new java.awt.Color(204, 204, 255));
        txtFG2.setEditable(false);
        txtFG2.setMinimumSize(new java.awt.Dimension(40, 20));
        txtFG2.setPreferredSize(new java.awt.Dimension(40, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtFG2, gridBagConstraints);

        jLabel6.setText("IBU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        txtIBU1.setBackground(new java.awt.Color(204, 204, 255));
        txtIBU1.setEditable(false);
        txtIBU1.setMinimumSize(new java.awt.Dimension(36, 20));
        txtIBU1.setPreferredSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtIBU1, gridBagConstraints);

        txtIBU2.setBackground(new java.awt.Color(204, 204, 255));
        txtIBU2.setEditable(false);
        txtIBU2.setMinimumSize(new java.awt.Dimension(36, 20));
        txtIBU2.setPreferredSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtIBU2, gridBagConstraints);

        jLabel7.setText("Vol. boll.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel13.setText("Eff.%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel13, gridBagConstraints);

        spinEfficienza.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinEfficienzaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(spinEfficienza, gridBagConstraints);

        jLabel1.setText("Boil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        spinBollitura.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinBollituraStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(spinBollitura, gridBagConstraints);

        lock.setBackground(javax.swing.UIManager.getDefaults().getColor("PropSheet.setBackground"));
        lock.setText("Adjust");
        lock.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lock.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lock.setMaximumSize(new java.awt.Dimension(55557, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(lock, gridBagConstraints);

        jLabel5.setText("Ricetta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        fldNome.setMinimumSize(new java.awt.Dimension(200, 20));
        fldNome.setPreferredSize(null);
        fldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fldNomeKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel2.add(fldNome, gridBagConstraints);

        jLabel8.setText("Vol. fin.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel12.setText("EBC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jLabel12, gridBagConstraints);

        txtEBC1.setBackground(new java.awt.Color(204, 204, 255));
        txtEBC1.setEditable(false);
        txtEBC1.setMinimumSize(new java.awt.Dimension(36, 20));
        txtEBC1.setPreferredSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtEBC1, gridBagConstraints);

        txtEBC2.setBackground(new java.awt.Color(204, 204, 255));
        txtEBC2.setEditable(false);
        txtEBC2.setMinimumSize(new java.awt.Dimension(36, 20));
        txtEBC2.setPreferredSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtEBC2, gridBagConstraints);

        comboVolFin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "litri", "gal" }));
        comboVolFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVolFinActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(comboVolFin, gridBagConstraints);

        comboVolBoll.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "litri", "gal" }));
        comboVolBoll.setMinimumSize(new java.awt.Dimension(56, 20));
        comboVolBoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVolBollActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(comboVolBoll, gridBagConstraints);

        spinVolumeBollWO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVolumeBollWOStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(spinVolumeBollWO, gridBagConstraints);

        spinVolumeFinWO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVolumeFinWOStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(spinVolumeFinWO, gridBagConstraints);

        jPanel12.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(40, 10));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        btnStyle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/bookcase.png"))); // NOI18N
        btnStyle.setToolTipText(bundle.getString("PickStyle")); // NOI18N
        btnStyle.setAlignmentX(0.5F);
        btnStyle.setMaximumSize(new java.awt.Dimension(35, 35));
        btnStyle.setMinimumSize(new java.awt.Dimension(35, 35));
        btnStyle.setPreferredSize(new java.awt.Dimension(35, 35));
        btnStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStyleActionPerformed(evt);
            }
        });
        jPanel18.add(btnStyle);

        jPanel12.add(jPanel18, java.awt.BorderLayout.WEST);

        jPanel1.add(jPanel12, java.awt.BorderLayout.NORTH);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setMinimumSize(new java.awt.Dimension(48, 10));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(24, 32));

        tblSummary.setModel(summarySorter);
        jScrollPane3.setViewportView(tblSummary);

        jPanel11.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel17.setPreferredSize(new java.awt.Dimension(40, 10));
        jPanel11.add(jPanel17, java.awt.BorderLayout.WEST);

        jPanel1.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);

        jPanel13.setMinimumSize(new java.awt.Dimension(500, 5));
        jPanel13.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(500, 10));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(640, 10));
        jScrollPane2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane2MouseWheelMoved(evt);
            }
        });

        tblMalts.setModel(maltSorter);
        tblMalts.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                tblMaltsMouseWheelMoved(evt);
            }
        });
        jScrollPane2.setViewportView(tblMalts);

        jPanel13.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel15.setPreferredSize(new java.awt.Dimension(40, 100));

        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
        btnAdd1.setToolTipText(bundle.getString("AddMalt")); // NOI18N
        btnAdd1.setAlignmentX(0.5F);
        btnAdd1.setMaximumSize(new java.awt.Dimension(35, 35));
        btnAdd1.setMinimumSize(new java.awt.Dimension(35, 35));
        btnAdd1.setPreferredSize(new java.awt.Dimension(35, 35));
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });
        jPanel15.add(btnAdd1);

        btnRem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_remove.png"))); // NOI18N
        btnRem1.setToolTipText(bundle.getString("RemoveMalto")); // NOI18N
        btnRem1.setAlignmentX(0.5F);
        btnRem1.setMaximumSize(new java.awt.Dimension(35, 35));
        btnRem1.setMinimumSize(new java.awt.Dimension(35, 35));
        btnRem1.setPreferredSize(new java.awt.Dimension(35, 35));
        btnRem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRem1ActionPerformed(evt);
            }
        });
        jPanel15.add(btnRem1);

        jPanel13.add(jPanel15, java.awt.BorderLayout.WEST);

        jSplitPane1.setTopComponent(jPanel13);

        jPanel14.setMinimumSize(new java.awt.Dimension(660, 5));
        jPanel14.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(660, 10));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(660, 10));

        tblHops.setModel(hopSorter);
        jScrollPane1.setViewportView(tblHops);

        jPanel14.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel16.setPreferredSize(new java.awt.Dimension(40, 10));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
        btnAdd.setToolTipText(bundle.getString("AddHop")); // NOI18N
        btnAdd.setAlignmentX(0.5F);
        btnAdd.setMaximumSize(new java.awt.Dimension(35, 35));
        btnAdd.setMinimumSize(new java.awt.Dimension(35, 35));
        btnAdd.setPreferredSize(new java.awt.Dimension(35, 35));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel16.add(btnAdd);

        btnRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_remove.png"))); // NOI18N
        btnRem.setToolTipText(bundle.getString("RemHop")); // NOI18N
        btnRem.setAlignmentX(0.5F);
        btnRem.setMaximumSize(new java.awt.Dimension(35, 35));
        btnRem.setMinimumSize(new java.awt.Dimension(35, 35));
        btnRem.setPreferredSize(new java.awt.Dimension(35, 35));
        btnRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemActionPerformed(evt);
            }
        });
        jPanel16.add(btnRem);

        jPanel14.add(jPanel16, java.awt.BorderLayout.WEST);

        jSplitPane1.setBottomComponent(jPanel14);

        jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemActionPerformed
	this.hopTableModel.remRow(this.hopSorter.modelIndex(this.tblHops.getSelectedRow()));
	ricettaModificata();
    }//GEN-LAST:event_btnRemActionPerformed
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
	this.hopPicker.startModal(ricetta);
	HopType type=(HopType)this.hopPicker.getSelection();
	if(type!=null){
	    this.hopTableModel.addRow(new Hop(this.ricetta,type));
	    ricettaModificata();
	}
    }//GEN-LAST:event_btnAddActionPerformed
    
    private void btnRem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRem1ActionPerformed
	if(this.tblMalts.getSelectedRow()>=0){
	    this.maltTableModel.remRow(this.maltSorter.modelIndex(this.tblMalts.getSelectedRow()));
	    ricettaModificata();
	}
    }//GEN-LAST:event_btnRem1ActionPerformed
    
    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
	this.maltPicker.startModal(ricetta);
	MaltType type=(MaltType)this.maltPicker.getSelection();
	if(type!=null){
	    this.maltTableModel.addRow(new Malt(this.ricetta,type));
	    ricettaModificata();
	}
    }//GEN-LAST:event_btnAdd1ActionPerformed
    
    private void jScrollPane2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane2MouseWheelMoved

    }//GEN-LAST:event_jScrollPane2MouseWheelMoved
    
    private void tblMaltsMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tblMaltsMouseWheelMoved

    }//GEN-LAST:event_tblMaltsMouseWheelMoved

    private BrewStyle brewStyle;
    
    private void btnStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStyleActionPerformed
	this.brewStylePicker.startModal(ricetta);
	BrewStyle type=(BrewStyle)this.brewStylePicker.getSelection();
	setBrewStyle(type);
    }//GEN-LAST:event_btnStyleActionPerformed
    private boolean flagUm=false;
    private void spinVolumeFinWOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVolumeFinWOStateChanged
	if(flagUm)return;
	double v=spinVolumeFinWO.getDoubleValue();
	v=Utils.convertVolume(v,(String)comboVolFin.getSelectedItem(),"litri");
	
	if(lock.isSelected()){
	    hopTableModel.adjustTo( getEfficienza(), v,
		    getEfficienza(), getVolume());
	    maltTableModel.adjustTo( getEfficienza(), v,
		    getEfficienza(), getVolume());
	}
	setVolume(v);
	ricettaModificata();
    }//GEN-LAST:event_spinVolumeFinWOStateChanged
    
    private void spinVolumeBollWOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVolumeBollWOStateChanged
	if(flagUm)return;
	double v=spinVolumeBollWO.getDoubleValue();
	v=Utils.convertVolume(v,(String)comboVolBoll.getSelectedItem(),"litri");
	setVolumeBoll(v);
    }//GEN-LAST:event_spinVolumeBollWOStateChanged
    
    private void comboVolBollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVolBollActionPerformed
	if(flagUm)return;
	flagUm=true;
	String um = (String)comboVolBoll.getSelectedItem();
	double u=getVolumeBoll();
	double v=getVolume();
	u=Utils.convertVolume(u,"litri", um);
	v=Utils.convertVolume(v,"litri", um);
	
	spinVolumeBollWO.setDoubleValue(u);
	spinVolumeFinWO.setDoubleValue(v);
	comboVolFin.setSelectedItem(um);
	setUnitaMisura(um);
	flagUm=false;
    }//GEN-LAST:event_comboVolBollActionPerformed
    
    private void comboVolFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVolFinActionPerformed
	if(flagUm)return;
	flagUm=true;
	String um = (String)comboVolFin.getSelectedItem();
	double u=getVolumeBoll();
	double v=getVolume();
	u=Utils.convertVolume(u,"litri", um);
	v=Utils.convertVolume(v,"litri", um);
	
	spinVolumeBollWO.setDoubleValue(u);
	spinVolumeFinWO.setDoubleValue(v);
	comboVolBoll.setSelectedItem(um);
	setUnitaMisura(um);
	flagUm=false;
    }//GEN-LAST:event_comboVolFinActionPerformed
    private boolean dirty=false;
    private void fldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fldNomeKeyTyped
	this.dirty=true;
    }//GEN-LAST:event_fldNomeKeyTyped
    
    private void spinBollituraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinBollituraStateChanged
	int v=((Integer)this.spinBollitura.getValue()).intValue();
	setBollitura(v);
	ricettaModificata();
    }//GEN-LAST:event_spinBollituraStateChanged
    
    private void spinEfficienzaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinEfficienzaStateChanged
	int v=((Integer)this.spinEfficienza.getValue()).intValue();
	if(this.lock.isSelected()){
	    this.maltTableModel.adjustTo( this.getEfficienza(), this.getVolume(),
		    v,  this.getVolume());
	    
	}
	this.setEfficienza(v);
	ricettaModificata();
    }//GEN-LAST:event_spinEfficienzaStateChanged
    
    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked

    }//GEN-LAST:event_jLabel22MouseClicked
    
    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked

    }//GEN-LAST:event_jLabel21MouseClicked
    
    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked

    }//GEN-LAST:event_jLabel20MouseClicked
    private GlassPanel glassPanel=new GlassPanel();
    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMorey());
    }//GEN-LAST:event_jLabel18MouseClicked
    
    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
	glassPanel.setColor(maltTableModel.getSRMDaniels());
    }//GEN-LAST:event_jLabel17MouseClicked
    
    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMosher());
    }//GEN-LAST:event_jLabel16MouseClicked
    
    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMorey());
    }//GEN-LAST:event_jLabel11MouseClicked
    
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
	glassPanel.setColor(maltTableModel.getSRMDaniels());
    }//GEN-LAST:event_jLabel10MouseClicked
    
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMosher());
    }//GEN-LAST:event_jLabel9MouseClicked
    
    private void btnAdd12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd12ActionPerformed

    }//GEN-LAST:event_btnAdd12ActionPerformed
    
    private void btnAdd11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd11ActionPerformed
//	finalizeInInventory();
    }//GEN-LAST:event_btnAdd11ActionPerformed
    
    private void btnAdd10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd10ActionPerformed
	WaterNeeded ed=new WaterNeeded(
		this.volume, summaryTableModel.getTotG()/1000.0, this.getBollitura(), 60*(volumeBoll-volume)/this.getBollitura());
	Gui.desktopPane.add(ed);
	Utils.center(ed,ricetta);
	ed.setVisible(true);
    }//GEN-LAST:event_btnAdd10ActionPerformed
    
    private void btnAdd9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd9ActionPerformed

    }//GEN-LAST:event_btnAdd9ActionPerformed
    
    private void btnAdd8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd8ActionPerformed
	StrikeTemp ed=new StrikeTemp();
	ed.setKgMalto( this.summaryTableModel.getTotG() /1000 );
	Gui.desktopPane.add(ed);
	Utils.center(ed,ricetta);
	ed.setVisible(true);
    }//GEN-LAST:event_btnAdd8ActionPerformed
    
    private void btnAdd7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd7ActionPerformed
	Thread th=new Thread(){
            @Override
	    public void run(){
		btnAdd7.setEnabled(false);
		UploadRicetta2 ed=new UploadRicetta2(ricetta.getXmlRicetta(),ricetta.toRecipeData().getNome(), ricetta.getIdRicettaHB());
		Main.gui.addFrame(ed);
		Utils.center(ed,ricetta);
		ed.setVisible(true);
		btnAdd7.setEnabled(true);
	    }
	};
	th.start();
    }//GEN-LAST:event_btnAdd7ActionPerformed
    
    private void btnAdd6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd6ActionPerformed
	DiluitionForm ed=new DiluitionForm(this.volume, this.getGravity(), this.hopTableModel.getIBUTinseth());
	Gui.desktopPane.add(ed);
	Utils.center(ed,ricetta);
	ed.setVisible(true);
    }//GEN-LAST:event_btnAdd6ActionPerformed
    
    private void btnAdd5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd5ActionPerformed
	EvaporationForm ed=new EvaporationForm(ricetta, this.volumeBoll, this.volume, this.getGravity(), this.getBollitura());
	Gui.desktopPane.add(ed);
	Utils.center(ed,ricetta);
	ed.setVisible(true);
    }//GEN-LAST:event_btnAdd5ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd10;
    private javax.swing.JButton btnAdd11;
    private javax.swing.JButton btnAdd12;
    private javax.swing.JButton btnAdd5;
    private javax.swing.JButton btnAdd6;
    private javax.swing.JButton btnAdd7;
    private javax.swing.JButton btnAdd8;
    private javax.swing.JButton btnAdd9;
    private javax.swing.JButton btnRem;
    private javax.swing.JButton btnRem1;
    private javax.swing.JButton btnStyle;
    private javax.swing.JComboBox comboVolBoll;
    private javax.swing.JComboBox comboVolFin;
    private javax.swing.JTextField fldNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JCheckBox lock;
    private javax.swing.JSpinner spinBollitura;
    private javax.swing.JSpinner spinEfficienza;
    private jmash.component.JMashSpinner spinVolumeBollWO;
    private jmash.component.JMashSpinner spinVolumeFinWO;
    private javax.swing.JTable tblHops;
    private javax.swing.JTable tblMalts;
    private javax.swing.JTable tblSummary;
    private javax.swing.JTextField txtEBC1;
    private javax.swing.JTextField txtEBC2;
    private javax.swing.JTextField txtFG1;
    private javax.swing.JTextField txtFG2;
    private javax.swing.JTextField txtIBU1;
    private javax.swing.JTextField txtIBU2;
    private javax.swing.JTextField txtOG1;
    private javax.swing.JTextField txtOG2;
    private javax.swing.JTextField txtStile;
    // End of variables declaration//GEN-END:variables
    
    private static NumberFormatter nf = new NumberFormatter("0.000");    
    public void setBrewStyle(BrewStyle brewStyle){
	this.brewStyle=brewStyle;
	if(brewStyle!=null){
	    this.txtStile.setText(brewStyle.getDesCategoria());
	    nf.setMinimumFractionDigits(3);
	    this.txtIBU1.setText(brewStyle.getIbuMin()+"");
	    this.txtIBU2.setText(brewStyle.getIbuMax()+"");
	    if(brewStyle.getOgMin()!=null) {
		this.txtOG1.setText(nf.format(brewStyle.getOgMin()>1000?brewStyle.getOgMin()/1000:brewStyle.getOgMin()));
	    }
	    if(brewStyle.getOgMax()!=null) {
		this.txtOG2.setText(nf.format(brewStyle.getOgMax()>1000?brewStyle.getOgMax()/1000:brewStyle.getOgMax()));
	    }
	    if(brewStyle.getFgMin()!=null) {
		this.txtFG1.setText(nf.format(brewStyle.getFgMin()>1000?brewStyle.getFgMin()/1000:brewStyle.getFgMin()));
	    }
	    if(brewStyle.getFgMax()!=null) {
		this.txtFG2.setText(nf.format(brewStyle.getFgMax()>1000?brewStyle.getFgMax()/1000:brewStyle.getFgMax()));
	    }
	    
	    if(brewStyle.getColorMin()!=null) {
		this.txtEBC1.setText((int)Utils.srmToEbc(brewStyle.getColorMin())+"");
	    }
	    if(brewStyle.getColorMax()!=null) {
		this.txtEBC2.setText((int)Utils.srmToEbc(brewStyle.getColorMax())+"");
	    }
	    
	}
    }
    public BrewStyle getBrewStyle(){
	return this.brewStyle;
    }
    
    private int counter=0;
    private boolean flgDirty=false;
    Thread colorThread=null;
    public void ricettaModificata(){
	flgDirty=true;
	summaryTableModel.setIBU(hopTableModel.getIBUTinseth());
	summaryTableModel.setIBU2(hopTableModel.getIBURager());
	summaryTableModel.setIBUGaretz(hopTableModel.getIBUGaretz());
	summaryTableModel.setIBUDaniels(hopTableModel.getIBUDaniels());
	summaryTableModel.setTotL(hopTableModel.getGrammi());
	summaryTableModel.setTotG(maltTableModel.getGrammi());
	summaryTableModel.setSG(maltTableModel.getSG(false));
	
	tblSummary.setCellSelectionEnabled(false);
	tblSummary.setRowSelectionAllowed(false);
	tblSummary.setColumnSelectionAllowed(false);
	
	double srmMosher=maltTableModel.getSRMMosher();
	double srmDaniels=maltTableModel.getSRMDaniels();
	double srmMorey=maltTableModel.getSRMMorey();
	jLabel9.setBackground(Main.treeColor.srmToRgb(srmMosher));
	jLabel10.setBackground(Main.treeColor.srmToRgb(srmDaniels));
	jLabel11.setBackground(Main.treeColor.srmToRgb(srmMorey));
	jLabel16.setBackground(Main.treeColor.srmToRgb(srmMosher));
	jLabel17.setBackground(Main.treeColor.srmToRgb(srmDaniels));
	jLabel18.setBackground(Main.treeColor.srmToRgb(srmMorey));
	
	
	if(srmMosher>10){
	    jLabel9.setForeground(Color.white);
	    jLabel10.setForeground(Color.white);
	    jLabel11.setForeground(Color.white);
	    jLabel16.setForeground(Color.white);
	    jLabel17.setForeground(Color.white);
	    jLabel18.setForeground(Color.white);
	} else{
	    jLabel9.setForeground(Color.BLACK);
	    jLabel10.setForeground(Color.BLACK);
	    jLabel11.setForeground(Color.BLACK);
	    jLabel16.setForeground(Color.BLACK);
	    jLabel17.setForeground(Color.BLACK);
	    jLabel18.setForeground(Color.BLACK);
	}
	jLabel9.setText( ""+((int)Utils.srmToEbc(srmMosher)));
	jLabel10.setText(""+((int)Utils.srmToEbc(srmDaniels)));
	jLabel11.setText(""+((int)Utils.srmToEbc(srmMorey)));
	jLabel16.setText(""+((int)srmMosher));
	jLabel17.setText(""+((int)srmDaniels));
	jLabel18.setText(""+((int)srmMorey));
	counter=10;
	if(colorThread==null){
	    colorThread=new Thread(){
                @Override
		public void run(){
		    while(true){
			while(Gyle.this.counter>0){
			    Gyle.this.counter--;
			    try {
				java.lang.Thread.sleep(100);
			    } catch (InterruptedException ex) {
				ex.printStackTrace();
			    }
			    if(Gyle.this.counter==0){
				Gyle.this.glassPanel.setColor(
					Gyle.this.maltTableModel.getSRMMosher()
					);
			    }
			}
			
			while(Gyle.this.counter==0){
			    try {
				java.lang.Thread.sleep(100);
			    } catch (InterruptedException ex) {
				ex.printStackTrace();
			    }
			}
		    }
		}
	    };
	    this.colorThread.start();
	}
	this.jLabel9.updateUI();
	this.jLabel10.updateUI();
	this.jLabel11.updateUI();
	this.tblSummary.updateUI();
	this.dirty=true;
    }
    private double volume=Main.config.getVolumeFin();
    public double getVolumeConverted(){
	return this.volume;
    }
    public double getVolume(){
	return this.volume;
    }
    public void setVolume(double v){
	this.volume=v;
    }
    private double volumeBoll=Main.config.getVolumeBoil();
    public double getVolumeBollConverted(){
	return this.volumeBoll;
    }
    public double getVolumeBoll(){
	return this.volumeBoll;
    }
    public void setVolumeBoll(double v){
	this.volumeBoll=v;
    }
    private String unitaMisura;
    public String getUnitaMisura(){ return unitaMisura; }
    public void setUnitaMisura(String unitaMisura){ this.unitaMisura= unitaMisura; }
    
    private int efficienza=Main.config.getEfficienza();
    public int getEfficienza(){
	return this.efficienza;
    }
    public void setEfficienza(int v){
	this.efficienza=v;
    }
    private int bollitura=Main.config.getBoilTime();
    public int getBollitura() {
	return this.bollitura;
    }
    public void setBollitura(int bollitura) {
	this.bollitura = bollitura;
    }
    
    private String note;
    public String getNote() {
	return this.note;
    }
    
    public void setNote(String note) {
	this.note = note;
    }
    
    private String nome;
    public String getNome() {
	return this.nome;
    }
    
    public void setNome(String nome) {
	this.nome = nome;
    }
    public void nullFile(){
	this.file=null;
    }
    private File file=null;
    private double srm=-1;
    private double ebc=-1;
    private final int dimx = 81;
    private final int dimy = 120;    
    public class GlassPanel extends JPanel{
	private static final long serialVersionUID = -7721900896919471804L;
	Image glassColor = Main.glassColorIcon.getImage();
	BufferedImage doubleBuffer;
	BufferedImage  dest3;
	BufferedImage colorSource;
	BufferedImage bwSource;
	double maxH=0;
	public GlassPanel(){
	    this.doubleBuffer = new BufferedImage(Gyle.this.dimx, Gyle.this.dimy, BufferedImage.TYPE_INT_ARGB);
	    this.dest3 = new BufferedImage(Gyle.this.dimx, Gyle.this.dimy, BufferedImage.TYPE_INT_ARGB);
	    this.colorSource= new BufferedImage(Gyle.this.dimx, Gyle.this.dimy, BufferedImage.TYPE_INT_ARGB);
	    this.colorSource.createGraphics().drawImage(this.glassColor, 0, 0, this);
	    this.setDoubleBuffered(true);
	    for(int y=0;y<Gyle.this.dimy;y++) {
		for(int x=0;x<Gyle.this.dimx;x++){
		    int red= (colorSource.getRGB(x, y)>>16)&0xff;
		    int green= (colorSource.getRGB(x, y)>>8)&0xff;
		    int blue= (colorSource.getRGB(x, y)>>0)&0xff;
		    if(red > blue*1.9){
			float f[]=new float[3];
			Color.RGBtoHSB((int)red,(int)green,(int)blue,f);
			if(f[2]>maxH)maxH=f[2];
		    }
		}
	    }
	}
        @Override
	public void paint(Graphics g) {
	    Graphics2D g2d = (Graphics2D)g;
	    Graphics2D destG = this.dest3.createGraphics();
	    if(this.backColor!=null){
		destG.setColor(this.backColor);
		destG.drawRect(0,0,Gyle.this.dimx,Gyle.this.dimy);
	    }
	    destG.drawImage(this.doubleBuffer, 0, 0, this);
	    g2d.drawImage(this.dest3, 0, 0, this);
	}
	Color backColor=null;
	public  void setColor(double srm){
	    Gyle.this.srm=srm;
	    Gyle.this.ebc=Utils.srmToEbc(srm);
	    Color color=Main.treeColor.srmToRgb(srm);
	    this.backColor=Gyle.this.jPanel1.getBackground();
	    int bc=backColor.getRGB();
	    float f[]=new float[3];
	    float f2[]=new float[3];
	    double factor=1.5f;
	    for(int y=0;y<Gyle.this.dimy;y++) {
		for(int x=0;x<Gyle.this.dimx;x++){
		    int c=this.colorSource.getRGB(x, y);
		    double red= (c>>16)&0xff;
		    double green= (c>>8)&0xff;
		    double blue= (c>>0)&0xff;
		    if(green/2>red && green/2>blue)
			doubleBuffer.setRGB(x,y,bc);
		    else if(red > blue*1.9){
			Color.RGBtoHSB((int)red,(int)green,(int)blue,f);
			Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),f2);
			int fin=Color.HSBtoRGB(f2[0],f2[1],(float)(f[2]*f2[2]/maxH));
			doubleBuffer.setRGB(x,y,fin);
		    }else doubleBuffer.setRGB(x,y,c);
		}
	    }
	    this.updateUI();
	}
    }
    
    public Double getEbc(){
	return ebc;
    }
    public Double getIBUTinseth(){
	return hopTableModel.getIBUTinseth();
    }
    public int getGrammiTotali(){
	return summaryTableModel.getTotG();
    }
    public Double getPlato(){
	return Utils.SG2Plato(getGravity());
    }
    public String getHexColor(){
	String rgb = Integer.toHexString(Main.treeColor.srmToRgb(srm).getRGB());
	rgb = rgb.substring(2, rgb.length());
	return rgb;
    }
    public Double getGravity(){
	return this.maltTableModel.getSG(false);
    }        
}
