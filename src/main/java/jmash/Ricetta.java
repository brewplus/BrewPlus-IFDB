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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmash.component.GlassPanel;
import jmash.component.UpDownPopupMenu;
import jmash.imagecomponents.ImageFileView;
import jmash.imagecomponents.ImageFilter;
import jmash.imagecomponents.ImagePreview;
import jmash.robot.hbRobot;
import jmash.tableModel.HopTableModel;
import jmash.tableModel.InventoryObjectTableModel;
import jmash.tableModel.MaltTableModel;
import jmash.tableModel.NumberFormatter;
import jmash.tableModel.SummaryTableModel;
import jmash.tableModel.YeastTableModel;
import org.jdom.Document;
import org.jdom.Parent;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author  Alessandro - Ixtlanas
 */
public class Ricetta extends javax.swing.JInternalFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = -3021970158888588464L;
    /** Creates new form Ricetta */
    private Boolean isCotta=false;
    protected Component entered = null;
    String fotografia= "";
    HopTableModel hopTableModel;
    YeastTableModel yeastTableModel;
    MaltTableModel maltTableModel;
    SummaryTableModel summaryTableModel;
    Ricetta thisRicetta;
    Picker hopPicker;
    MaltTypePicker maltPicker;
    Picker yeastPicker;
    Picker brewStylePicker;
    TableSorter maltSorter, hopSorter, summarySorter, yeastSorter;
    private GlassPanel glassPanel ;
    private boolean dirty = false;
    public static final int dimx = 81;
    public static final int dimy = 120;
    private WaterAdjustPanel waterPanel = null;
    private Gyle gyle = null;
    private static javax.swing.ImageIcon hopsIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/hops.gif"));
    private static javax.swing.ImageIcon maltsIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/malts.png"));
    private static javax.swing.ImageIcon brewsIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/birre.png"));
    private static javax.swing.ImageIcon dupIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/editdup.png"));
    private static javax.swing.ImageIcon bookIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/bookcase.png"));
    private static javax.swing.ImageIcon calIcon = new javax.swing.ImageIcon(Ricetta.class.getResource("/jmash/images/descforumpaste.png"));
    
    public boolean isDirty() {
	return this.dirty;
    }
    
    public void setDilVisible(boolean val){
	lblDil.setVisible(val);
	spinVolumeDiluito.setVisible(val);
	jPanel2.setPreferredSize(new java.awt.Dimension(650, 90+(val?30:0)));
	jPanel2.setSize(new java.awt.Dimension(650, 90+(val?30:0)));
	jPanel12.setMinimumSize(new java.awt.Dimension(100, 100+(val?30:0)));
	jPanel12.setPreferredSize(new java.awt.Dimension(100, 104+(val?26:0)));
    }
    
    public Ricetta() {
	this.thisRicetta = this;
	this.hopTableModel = new HopTableModel(this);
	this.waterPanel = new WaterAdjustPanel(this);
	this.maltTableModel = new MaltTableModel(this);
	this.summaryTableModel = new SummaryTableModel(this);
	this.yeastTableModel = new YeastTableModel(this);
	this.glassPanel = new GlassPanel(this);
	this.hopPicker = new Picker(Gui.hopPickerTableModel);
	this.hopPicker.setIcon(hopsIcon);
	this.maltPicker = new MaltTypePicker();
	
	this.yeastPicker = new Picker(Gui.yeastPickerTableModel);
	this.yeastPicker.setIcon(maltsIcon);
	this.brewStylePicker = new Picker(Gui.brewStylePickerTableModel);
	this.brewStylePicker.setIcon(brewsIcon);
	
	this.maltSorter = new TableSorter(this.maltTableModel);
	this.hopSorter = new TableSorter(this.hopTableModel);
	this.summarySorter = new TableSorter(this.summaryTableModel);
	this.yeastSorter = new TableSorter(this.yeastTableModel);
	
	this.maltTableModel.addTableModelListener(new TableModelListener() {
            @Override
	    public void tableChanged(TableModelEvent ev) {
		ricettaModificata();
	    }
	});
	
	
	this.hopTableModel.addTableModelListener(new TableModelListener() {
            @Override
	    public void tableChanged(TableModelEvent ev) {
		ricettaModificata();
	    }
	});
	this.mashDesign = new PanelMashStep(this, this);
	
	initComponents();
	
	splitPanel.setDividerLocation(0.5);
	splitPanel.setResizeWeight(0.5);
	
	java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	gridBagConstraints.gridx = 5;
	gridBagConstraints.gridy = 2;
	gridBagConstraints.gridheight = 4;
	gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
	gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
	this.glassPanel.setMinimumSize(new Dimension(Ricetta.dimx, Ricetta.dimy));
	this.glassPanel.setPreferredSize(new Dimension(Ricetta.dimx, Ricetta.dimy));
	this.glassPanel.setMaximumSize(new Dimension(Ricetta.dimx, Ricetta.dimy));
	this.glassPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	this.jPanel10.add(this.glassPanel, 2);
	
	this.jTabbedPane1.add(this.mashDesign, "Mash design");
	
	
	JScrollPane scrollPanel = new JScrollPane();
	//scrollPanel.setViewportView(waterPanel);
    scrollPanel.getViewport().setPreferredSize(new Dimension(0,0));
	//this.jTabbedPane1.add(waterNeeded.getComponent(0), "Quantità  Acqua");
    this.jTabbedPane1.add(waterNeeded.getComponent(0), "Quantità  Acqua");
    
	this.jTabbedPane1.add("Qualità  Acqua", waterPanel);
   
        
	this.maltSorter.setTableHeader(this.tblMalts.getTableHeader());
	this.hopSorter.setTableHeader(this.tblHops.getTableHeader());
	this.yeastSorter.setTableHeader(this.tblYeast.getTableHeader());
	this.summarySorter.setTableHeader(this.tblSummary.getTableHeader());
	
	this.tblHops.setSelectionMode(0);
	this.tblMalts.setSelectionMode(0);
	this.tblYeast.setSelectionMode(0);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblMalts);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblMalts.getTableHeader());
	ToolTipManager.sharedInstance().unregisterComponent(this.tblHops);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblHops.getTableHeader());
	ToolTipManager.sharedInstance().unregisterComponent(this.tblSummary);
	ToolTipManager.sharedInstance().unregisterComponent(this.tblSummary.getTableHeader());
	tblHops.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
	tblHops.getColumnModel().getColumn(4).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.HOP_FORMS));
	tblHops.getColumnModel().getColumn(7).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.HOP_USAGE));
	tblHops.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblHops.getColumnModel().getColumn(0).setPreferredWidth(32);
	tblHops.getColumnModel().getColumn(11).setPreferredWidth(32);
	
	tblHops.getColumnModel().getColumn(1).setCellRenderer(Main.multiLineCellRenderer);
	tblMalts.getColumnModel().getColumn(1).setCellRenderer(Main.multiLineCellRenderer);
	
	tblHops.setDefaultRenderer(JButton.class,
		new jmash.component.JTableButtonRenderer(tblHops.getDefaultRenderer(JButton.class)));
	
	javax.swing.JMenuItem mnuItem=new javax.swing.JMenuItem("Calcolo invecchiamento");
	hopPopup.add(mnuItem );
	mnuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		Hop hop=hopTableModel.getRow(tblHops.getSelectedRow());
		if(hop!=null){
		    CalcoloHopLoss ed= new CalcoloHopLoss();
		    ed.set(hop);
		    Utils.center(ed,Ricetta.this);
		    ed.startModal(Ricetta.this);
		    hop=ed.get();
		    Ricetta.this.hopTableModel.getRow(Ricetta.this.tblHops. getSelectedRow()).setAlfaAcidi(hop.getAlfaAcidi());
		    ricettaModificata();
		}
	    }
	}
	);
	tblHops.addMouseListener(new jmash.component.JTableButtonMouseListener(tblHops, this));
	
	tblMalts.getColumnModel().getColumn(6).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.MALT_FORMS));
	tblMalts.getColumnModel().getColumn(3).setCellEditor(new jmash.component.JSpinnerEditor(XmlTags.UNITA_PESO));
	tblMalts.getColumnModel().getColumn(4).setCellRenderer(new BarCellRenderer());
	tblMalts.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblHops.getColumnModel().getColumn(1).setPreferredWidth(128);
	tblMalts.getColumnModel().getColumn(0).setPreferredWidth(32);
	
	thisRicetta.setClosable(true);
	thisRicetta.setIconifiable(true);
	thisRicetta.setTitle(java.util.ResourceBundle.getBundle("jmash/lang").getString("Ricetta"));
	thisRicetta.setVisible(true);
	setVolume(Main.config.getVolumeFin());
	setVolumeBoll(Main.config.getVolumeBoil());
	setUnitaMisura("litri");
	
	spinBollitura.setEditor(new JSpinner.NumberEditor(spinBollitura,"# min"));
	
	spinVolumeBoll.setModelFormat(23.0, 0.25, 9999999.0, 0.25, "0.00","Ricetta.VB");
	spinVolumeFin.setModelFormat(23.0, 0.25, 9999999.0, 0.25, "0.00","Ricetta.VF");
	spinVolumeDiluito.setModelFormat(23.0, 0.25, 9999999.0, 0.25, "0.00","Ricetta.DL");
	
	spinVolumeBoll.setVolume(Main.config.getVolumeBoil());
	spinVolumeFin.setVolume(Main.config.getVolumeFin());
	spinEfficienza.setValue(Main.config.getEfficienza());
	spinBollitura.setValue(Main.config.getBoilTime());
	
	((javax.swing.SpinnerNumberModel) spinEfficienza.getModel()).setMaximum(new Integer(100));
	((javax.swing.SpinnerNumberModel) spinBollitura.getModel()).setMinimum(new Integer(0));
	((javax.swing.SpinnerNumberModel) spinEfficienza.getModel()).setMinimum(new Integer(1));
	
	jPanel2.setBackground(maltSorter.getTableHeader().getBackground());
	
	tblSummary.setCellSelectionEnabled(false);
	tblSummary.setRowSelectionAllowed(false);
	tblSummary.setColumnSelectionAllowed(false);
	
	this.tblHops.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
	    private static final long serialVersionUID = -4314493966636560005L;
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col) {
		return (JLabel) Ricetta.this.tblHops.getValueAt(markedRow, 0);
	    }
	});
	
	this.tblHops.getColumnModel().getColumn(11).setCellRenderer(new DefaultTableCellRenderer() {
	    private static final long serialVersionUID = -1649193412422579171L;
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col) {
		return (Component) Ricetta.this.tblHops.getValueAt(markedRow, 11);
	    }
	});
	
	this.tblMalts.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
	    private static final long serialVersionUID = -4314493966636560005L;
	    @Override
	    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col) {
		return (JLabel) Ricetta.this.tblMalts.getValueAt(markedRow, 0);
	    }
	});
	

	setBorder(Utils.getDefaultBorder());
	this.mashDesign.mashModificato();
	setDilVisible(false);
    dirty=false;
    }
    public PanelMashStep mashDesign;
    
    public class BarCellRenderer extends JProgressBar implements TableCellRenderer {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -2598339473111480205L;
	// This method is called each time a cell in a column
	// using this renderer needs to be rendered.
	public BarCellRenderer() {
	    setMaximum(100);
	    setMinimum(0);
	    setStringPainted(true);
	    this.setForeground(this.getForeground().darker());
	    this.setBorder(null);
	}
	
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
	    Double d = (Double) value;
	    setValue((int) Math.rint(d.doubleValue()));
	    return this;
	}
	
	// The following methods override the defaults for performance reasons
	@Override
	public void validate() {
	}
	
	@Override
	public void revalidate() {
	}
	
	@Override
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	}
	
	@Override
	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
	}
    }
    
    public Ricetta(File file) {
    	this();
    	this.file = file;
    	read(file);
    }
    
    private String idRicettaHB;
    
    public String getIdRicettaHB() {
	return idRicettaHB;
    }
    
    public Ricetta(Document doc, String idRicettaHB) {
	this();
	read(doc);
	this.idRicettaHB = idRicettaHB;
	
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        hopPopup = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnAdd5 = new javax.swing.JButton();
        btnAdd6 = new javax.swing.JButton();
        btnAdd8 = new javax.swing.JButton();
        btnAdd9 = new javax.swing.JButton();
        btnAdd10 = new javax.swing.JButton();
        btnAdd11 = new javax.swing.JButton();
        btnAdd12 = new javax.swing.JButton();
        btnBiab = new javax.swing.JButton();
        
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
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
        jPanel19 = new javax.swing.JPanel();
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
        btnIngredienti=new JButton();
        btnIngredienti.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showIngredienti();
        	}
        });
        chkConcentratedBoil = new javax.swing.JCheckBox();
        lblDil = new javax.swing.JLabel();
        spinVolumeDiluito = new jmash.component.JVolumeSpinner();
        spinVolumeBoll = new jmash.component.JVolumeSpinner();
        spinVolumeFin = new jmash.component.JVolumeSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        btnStyle = new javax.swing.JButton();
        btnStyle.setContentAreaFilled(false);
        btnStyle.setBorderPainted(false);
        splitPanel = new javax.swing.JSplitPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMalts = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        btnAdd1 = new javax.swing.JButton();
        btnRem1 = new javax.swing.JButton();
        btnIngPie = new javax.swing.JButton();
        btnIngPie.setVisible(false);
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHops = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRem = new javax.swing.JButton();
        btnDupHop = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnAdd2 = new javax.swing.JButton();
        btnAdd2.setContentAreaFilled(false);
        btnAdd2.setBorderPainted(false);
        btnRem2 = new javax.swing.JButton();
        btnRem2.setBorderPainted(false);
        btnRem2.setContentAreaFilled(false);
        jScrollPane4 = new javax.swing.JScrollPane();
        tblYeast = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        fldNote = new javax.swing.JEditorPane();
        lblFermentazione = new javax.swing.JLabel();

        hopPopup.setLabel("Seleziona");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang"); // NOI18N
        setTitle(bundle.getString("Ricetta")); // NOI18N
        setAutoscrolls(true);
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setPreferredSize(new java.awt.Dimension(1024, 600));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(96, 10));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setMinimumSize(new java.awt.Dimension(96, 10));
        jPanel6.setPreferredSize(new java.awt.Dimension(96, 180));

        btnAdd5.setIcon(Main.boilOffIcon);
        btnAdd5.setToolTipText("Evaporazione");
        btnAdd5.setContentAreaFilled(false);
        btnAdd5.setBorderPainted(false);
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

        btnAdd6.setIcon(Main.diluiteIcon);
        btnAdd6.setToolTipText("Diluizioni e concentrazioni");
        btnAdd6.setContentAreaFilled(false);
        btnAdd6.setBorderPainted(false);
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

        btnAdd8.setIcon(Main.strikeIcon);
        btnAdd8.setToolTipText("Temperatura mash in"); 
        btnAdd8.setContentAreaFilled(false);
        btnAdd8.setBorderPainted(false);
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

        btnAdd9.setIcon(Main.printIcon);
        btnAdd9.setToolTipText("Stampa");
        btnAdd9.setContentAreaFilled(false);
        btnAdd9.setBorderPainted(false);
        btnAdd9.setAlignmentX(0.5F);
        btnAdd9.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd9.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd9ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd9);

        btnAdd10.setIcon(calIcon);
        btnAdd10.setToolTipText("Descrizione per forum");
        btnAdd10.setContentAreaFilled(false);
        btnAdd10.setBorderPainted(false);
        btnAdd10.setAlignmentX(0.5F);
        btnAdd10.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd10.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd10ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd10);

        btnAdd11.setIcon(Main.checkInventoryIcon);
        btnAdd11.setToolTipText("Controlla in inventario");
        btnAdd11.setContentAreaFilled(false);
        btnAdd11.setBorderPainted(false);
        btnAdd11.setAlignmentX(0.5F);
        btnAdd11.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd11.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd11ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd11);

        btnAdd12.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/timer2.png")));
        btnAdd12.setToolTipText("Genera timer di bollitura");
        btnAdd12.setContentAreaFilled(false);
        btnAdd12.setBorderPainted(false);
        btnAdd12.setAlignmentX(0.5F);
        btnAdd12.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAdd12.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd12ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAdd12);
        
        btnBiab.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/biab1.png")));
        btnBiab.setToolTipText("Utility di calcolo volumi per BIAB");
        btnBiab.setContentAreaFilled(false);
        btnBiab.setBorderPainted(false);
        btnBiab.setAlignmentX(0.5F);
        btnBiab.setMinimumSize(new java.awt.Dimension(32, 32));
        btnBiab.setPreferredSize(new java.awt.Dimension(36, 36));
        btnBiab.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		btnBiabActionPerformed(evt);
        	}
        });
        jPanel6.add(btnAdd12);
        jPanel6.add(btnBiab);
        jPanel10.add(jPanel6);

        jPanel10.add(jPanel1);

        jSeparator1.setPreferredSize(new java.awt.Dimension(96, 10));
        jPanel10.add(jSeparator1);

        jPanel5.setMinimumSize(new java.awt.Dimension(84, 2));
        jPanel5.setPreferredSize(new java.awt.Dimension(96, 48));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setToolTipText("EBC secondo Mosher");
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
        jLabel10.setToolTipText("EBC secondo Daniels");
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
        jLabel11.setToolTipText("EBC secondo Morey");
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
        jLabel16.setToolTipText("SRM secondo Mosher");
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
        jLabel17.setToolTipText("SRM secondo Daniels");
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
        jLabel18.setToolTipText("SRM secondo Morey");
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

        jPanel3.add(jPanel10, java.awt.BorderLayout.EAST);
        lblPicBeer= new JLabel();
        lblPicBeer.setToolTipText("<html>Clic sinistro: inserisci<br>Clic destro: elimina");
        lblPicBeer.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getButton()==MouseEvent.BUTTON1)addPhotoFromFile();
        		if(e.getButton()==MouseEvent.BUTTON3)deletePhoto();
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if (!(e.getSource() instanceof Component)) return;
        	     exit();
        	     enter((Component)e.getSource());
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		exit();
        	}
        });
    	lblPicBeer.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/addphoto.png"))); 
        
        jPanel10.add(lblPicBeer);
        
        
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel12.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 120));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("PropSheet.setBackground"));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 14));
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 120));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText(bundle.getString("style")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtStile.setBackground(new java.awt.Color(204, 204, 255));
        txtStile.setEditable(false);
        txtStile.setFont(new java.awt.Font("Arial", 0, 10));
        txtStile.setPreferredSize(new java.awt.Dimension(350, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(txtStile, gridBagConstraints);

        jLabel3.setText("OG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtOG1.setBackground(new java.awt.Color(204, 204, 255));
        txtOG1.setEditable(false);
        txtOG1.setFont(new java.awt.Font("Arial", 0, 10));
        txtOG1.setMinimumSize(new java.awt.Dimension(46, 22));
        txtOG1.setPreferredSize(new java.awt.Dimension(40, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtOG1, gridBagConstraints);

        txtOG2.setBackground(new java.awt.Color(204, 204, 255));
        txtOG2.setEditable(false);
        txtOG2.setFont(new java.awt.Font("Arial", 0, 10));
        txtOG2.setMinimumSize(new java.awt.Dimension(46, 22));
        txtOG2.setPreferredSize(new java.awt.Dimension(38, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtOG2, gridBagConstraints);

        jLabel4.setText("FG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        txtFG1.setBackground(new java.awt.Color(204, 204, 255));
        txtFG1.setEditable(false);
        txtFG1.setFont(new java.awt.Font("Arial", 0, 10));
        txtFG1.setMinimumSize(new java.awt.Dimension(46, 22));
        txtFG1.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtFG1, gridBagConstraints);

        txtFG2.setBackground(new java.awt.Color(204, 204, 255));
        txtFG2.setEditable(false);
        txtFG2.setFont(new java.awt.Font("Arial", 0, 10));
        txtFG2.setMinimumSize(new java.awt.Dimension(46, 22));
        txtFG2.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtFG2, gridBagConstraints);

        jLabel6.setText("IBU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        txtIBU1.setBackground(new java.awt.Color(204, 204, 255));
        txtIBU1.setEditable(false);
        txtIBU1.setFont(new java.awt.Font("Arial", 0, 10));
        txtIBU1.setMinimumSize(new java.awt.Dimension(38, 22));
        txtIBU1.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtIBU1, gridBagConstraints);

        txtIBU2.setBackground(new java.awt.Color(204, 204, 255));
        txtIBU2.setEditable(false);
        txtIBU2.setFont(new java.awt.Font("Arial", 0, 10));
        txtIBU2.setMinimumSize(new java.awt.Dimension(38, 22));
        txtIBU2.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtIBU2, gridBagConstraints);

        jLabel7.setText("In pentola");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        spinEfficienza.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinEfficienzaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinEfficienza, gridBagConstraints);

        jLabel1.setText("Bollitura");
        jLabel1.setToolTipText("Minuti di bollitura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        spinBollitura.setToolTipText("Minuti di bollitura");
        spinBollitura.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinBollituraStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinBollitura, gridBagConstraints);

        lock.setBackground(javax.swing.UIManager.getDefaults().getColor("PropSheet.setBackground"));
        lock.setText("Adjust");
        lock.setToolTipText("Ricalcola proporzionalmente gli ingredienti in base alle variazioni di volume o efficienza");
        lock.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lock.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lock.setMaximumSize(new java.awt.Dimension(55557, 23));
        lock.setPreferredSize(new java.awt.Dimension(61, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(lock, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText(bundle.getString("NomeRicetta")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        fldNome.setMinimumSize(new java.awt.Dimension(200, 24));
        fldNome.setPreferredSize(new java.awt.Dimension(350, 24));
        fldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fldNomeKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(fldNome, gridBagConstraints);

        jLabel8.setText("In ferment.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel12.setText("EBC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jLabel12, gridBagConstraints);

        txtEBC1.setBackground(new java.awt.Color(204, 204, 255));
        txtEBC1.setEditable(false);
        txtEBC1.setFont(new java.awt.Font("Arial", 0, 10));
        txtEBC1.setMinimumSize(new java.awt.Dimension(38, 22));
        txtEBC1.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtEBC1, gridBagConstraints);

        txtEBC2.setBackground(new java.awt.Color(204, 204, 255));
        txtEBC2.setEditable(false);
        txtEBC2.setFont(new java.awt.Font("Arial", 0, 10));
        txtEBC2.setMinimumSize(new java.awt.Dimension(38, 22));
        txtEBC2.setPreferredSize(new java.awt.Dimension(40, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtEBC2, gridBagConstraints);

        btnIngredienti.setText("Ingredienti consigliati..");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(btnIngredienti, gridBagConstraints);
        
        chkConcentratedBoil.setBackground(javax.swing.UIManager.getDefaults().getColor("PropSheet.setBackground"));
        chkConcentratedBoil.setText("Bollitura concent.");
        chkConcentratedBoil.setToolTipText("Bollitura concentrata");
        chkConcentratedBoil.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chkConcentratedBoil.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        chkConcentratedBoil.setPreferredSize(new java.awt.Dimension(132, 20));
        chkConcentratedBoil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConcentratedBoilActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(chkConcentratedBoil, gridBagConstraints);

        lblDil.setText("Dopo diluiz.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(lblDil, gridBagConstraints);
        spinVolumeDiluito.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVolumeDiluitoStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinVolumeDiluito, gridBagConstraints);

        spinVolumeBoll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVolumeBollStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinVolumeBoll, gridBagConstraints);

        spinVolumeFin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVolumeFinStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(spinVolumeFin, gridBagConstraints);

        jButton1.setFont(jButton1.getFont());
        jButton1.setText("Porta a...");
        jButton1.setToolTipText("Ridimensiona ricetta");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setFont(jButton2.getFont());
        jButton2.setText(" Efficienza % ");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel2.add(jButton2, gridBagConstraints);

        jPanel12.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(40, 10));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        btnStyle.setIcon(bookIcon);
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

        jPanel19.add(jPanel12, java.awt.BorderLayout.NORTH);

        splitPanel.setDividerLocation(100);
        splitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPanel.setMinimumSize(new java.awt.Dimension(502, 15));
        splitPanel.setPreferredSize(new java.awt.Dimension(102, 60));

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
        tblMalts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaltsMouseClicked(evt);
            }
        });
        tblMalts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblMaltsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblMalts);

        jPanel13.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel15.setPreferredSize(new java.awt.Dimension(40, 100));

        btnAdd1.setIcon(Main.addIcon);
        btnAdd1.setToolTipText(bundle.getString("AddMalt")); // NOI18N
        btnAdd1.setContentAreaFilled(false);
        btnAdd1.setBorderPainted(false);
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

        btnRem1.setIcon(Main.remIcon);
        btnRem1.setToolTipText(bundle.getString("RemoveMalto")); // NOI18N
        btnRem1.setContentAreaFilled(false);
        btnRem1.setBorderPainted(false);
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
        
        btnIngPie.setIcon(Main.PieIcon);
        btnIngPie.setToolTipText("Percentuali");
        btnIngPie.setContentAreaFilled(false);
        btnIngPie.setBorderPainted(false);
        btnIngPie.setAlignmentX(0.5F);
        btnIngPie.setMaximumSize(new java.awt.Dimension(35, 35));
        btnIngPie.setMinimumSize(new java.awt.Dimension(35, 35));
        btnIngPie.setPreferredSize(new java.awt.Dimension(35, 35));
        btnIngPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngPieActionPerformed(evt);
            }
        });
        jPanel15.add(btnIngPie);
        
        jPanel13.add(jPanel15, java.awt.BorderLayout.WEST);

        splitPanel.setTopComponent(jPanel13);

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

        btnAdd.setIcon(Main.addIcon);
        btnAdd.setToolTipText(bundle.getString("AddHop")); // NOI18N
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBorderPainted(false);
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

        btnRem.setIcon(Main.remIcon);
        btnRem.setToolTipText(bundle.getString("RemHop")); // NOI18N
        btnRem.setContentAreaFilled(false);
        btnRem.setBorderPainted(false);
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

        btnDupHop.setIcon(dupIcon);
        btnDupHop.setToolTipText("Duplica");
        btnDupHop.setContentAreaFilled(false);
        btnDupHop.setBorderPainted(false);
        btnDupHop.setAlignmentX(0.5F);
        btnDupHop.setMaximumSize(new java.awt.Dimension(35, 35));
        btnDupHop.setMinimumSize(new java.awt.Dimension(35, 35));
        btnDupHop.setPreferredSize(new java.awt.Dimension(35, 35));
        btnDupHop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDupHopActionPerformed(evt);
            }
        });
        jPanel16.add(btnDupHop);

        jPanel14.add(jPanel16, java.awt.BorderLayout.WEST);

        splitPanel.setBottomComponent(jPanel14);

        jPanel19.add(splitPanel, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setMinimumSize(new java.awt.Dimension(48, 10));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(24, 45));

        tblSummary.setModel(summarySorter);
        jScrollPane3.setViewportView(tblSummary);

        jPanel11.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel17.setPreferredSize(new java.awt.Dimension(40, 10));
        jPanel11.add(jPanel17, java.awt.BorderLayout.WEST);

        jPanel19.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel19, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Dati principali", jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        btnAdd2.setIcon(Main.addIcon);
        btnAdd2.setToolTipText("Aggiungi Lievito");
        btnAdd2.setAlignmentX(0.5F);
        btnAdd2.setMaximumSize(new java.awt.Dimension(30, 30));
        btnAdd2.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAdd2.setPreferredSize(new java.awt.Dimension(36, 36));
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });
        jPanel8.add(btnAdd2, java.awt.BorderLayout.NORTH);

        btnRem2.setIcon(Main.remIcon);
        btnRem2.setToolTipText("Rimuovi Lievito");
        btnRem2.setAlignmentX(0.5F);
        btnRem2.setMaximumSize(new java.awt.Dimension(30, 30));
        btnRem2.setMinimumSize(new java.awt.Dimension(30, 30));
        btnRem2.setPreferredSize(new java.awt.Dimension(36, 36));
        btnRem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRem2ActionPerformed(evt);
            }
        });
        jPanel8.add(btnRem2, java.awt.BorderLayout.SOUTH);

        jPanel7.add(jPanel8, java.awt.BorderLayout.WEST);

        jScrollPane4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(500, 120));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(500, 120));
        jScrollPane4.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane4MouseWheelMoved(evt);
            }
        });

        tblYeast.setModel(yeastSorter);
        tblYeast.setPreferredSize(null);
        tblYeast.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                tblYeastMouseWheelMoved(evt);
            }
        });
        jScrollPane4.setViewportView(tblYeast);

        jPanel7.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout(4, 4));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setAutoscrolls(true);

        fldNote.setPreferredSize(new java.awt.Dimension(380, 180));
        jScrollPane6.setViewportView(fldNote);

        jPanel9.add(jScrollPane6, BorderLayout.SOUTH);

        lblFermentazione.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFermentazione.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFermentazione.setIcon(Main.editIcon);
        lblFermentazione.setText("Note fermentazione");
        jPanel9.add(lblFermentazione, java.awt.BorderLayout.NORTH);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Lievito e fermentazione", jPanel4);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1141, 516));
    }// </editor-fold>//GEN-END:initComponents
    
    
    private UpDownPopupMenu upDownPopupMenu= new UpDownPopupMenu();
    
    private void tblMaltsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaltsMouseClicked
	if(evt.isPopupTrigger()){
	    //upDownPopupMenu.show(evt.getComponent(),evt.getX(),evt.getY());
	    upDownPopupMenu.setVisible(true);
	    UpDownPopupMenu.Action action= upDownPopupMenu.getAction();
//	    if(action==UpDownPopupMenu.Action.UP)
//		System.out.println("UP!");
//	    else if(action==UpDownPopupMenu.Action.DOWN)
//		System.out.println("DOWN!");
	}
    }//GEN-LAST:event_tblMaltsMouseClicked
    
    private void tblMaltsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMaltsKeyTyped
			    }//GEN-LAST:event_tblMaltsKeyTyped
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
	//CalcoloEfficienza frm=new CalcoloEfficienza(this);
	//frm.startModal(this);
    	Double volbrewhouse;
    	if(chkConcentratedBoil.isSelected()){volbrewhouse=spinVolumeDiluito.getVolume();} else {volbrewhouse=spinVolumeFin.getVolume();}
    	frmEfficienza f=new frmEfficienza(this,fldNome.getText().trim(),spinVolumeBoll.getVolume(),volbrewhouse);
    	Main.gui.addFrame(f);
    	Dimension desktopSize = Main.gui.desktopPane.getSize();
    	Dimension jInternalFrameSize = f.getSize();
    	f.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
    	    (desktopSize.height- jInternalFrameSize.height)/2);
	
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	ScaleRecipe frm=new ScaleRecipe(spinVolumeFin.getVolume());
	frm.startModal(this);
	double v=frm.newVolume;
	frm.dispose();
	lock.setSelected(true);
	spinVolumeFin.setVolume(v);
	lock.setSelected(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void spinVolumeFinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVolumeFinStateChanged
	if(this.lock.isSelected()){
	    this.maltTableModel.adjustTo( this.thisRicetta.getEfficienza(),  spinVolumeFin.getVolume(),
		    this.thisRicetta.getEfficienza(), volumePrec);
	    this.hopTableModel.adjustTo( this.thisRicetta.getEfficienza(),  spinVolumeFin.getVolume(),
		    this.thisRicetta.getEfficienza(), volumePrec);
	}
	volumePrec=spinVolumeFin.getVolume();
	ricettaModificata();
    }//GEN-LAST:event_spinVolumeFinStateChanged
    
    private void spinVolumeBollStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVolumeBollStateChanged
	ricettaModificata();
    }//GEN-LAST:event_spinVolumeBollStateChanged
    
    private void spinVolumeDiluitoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVolumeDiluitoStateChanged
	ricettaModificata();
    }//GEN-LAST:event_spinVolumeDiluitoStateChanged
    
    private void enter(Component c) {
        //change cursor appearance to HAND_CURSOR when the mouse pointed on images
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
        setCursor(cursor);
        entered = c;
     }

     private void exit() {
        Cursor cursor = Cursor.getDefaultCursor();
        setCursor(cursor);
        if (entered != null) {
           entered = null;
        }}
    
    private void addPhotoFromFile()
    {
    	JFileChooser fc=new JFileChooser();
    	fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileView(new ImageFileView());
        fc.setAccessory(new ImagePreview(fc));
        int returnVal = fc.showDialog(this,"Apri");
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            ImageIcon tmpIcon = new ImageIcon(file.getPath());
            if(tmpIcon.getIconWidth()<1) return; //prende i files errati
            if (tmpIcon != null)
            {
            	ImageIcon thumbnail;
                if (tmpIcon.getIconWidth() > 	100)
                {
                    thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(100, -1,Image.SCALE_DEFAULT));
                }
                else
                { 
                    thumbnail = tmpIcon;
                }
                lblPicBeer.setIcon(thumbnail);
                Image img = thumbnail.getImage();
                BufferedImage bi= new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bi.createGraphics();
                g2.setComposite(AlphaComposite.Src);
                g2.drawImage(img, 0, 0, null);
                g2.dispose();
                try
                {
                	ImageIO.write(bi, "jpg", new File("pics/tmpthum.tmp")); 
                    String b64=Utils.getBase64FromImageFile("pics/tmpthum.tmp");
                    this.fotografia=b64;
                }
                catch(Exception ex)
                {}
                
                this.dirty=true;
            }
        }
        fc.setSelectedFile(null);
    }
    private void deletePhoto()
    {
    	this.fotografia="";
    	lblPicBeer.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/addphoto.png")));
    	this.dirty=true;
    }

    private void chkConcentratedBoilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConcentratedBoilActionPerformed
	setDilVisible(chkConcentratedBoil.isSelected());
	ricettaModificata();
    }//GEN-LAST:event_chkConcentratedBoilActionPerformed
				
    private void btnAdd12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd12ActionPerformed
	//Document doc = this.toRecipeData().toXml();
	//Main.gui.addFrame(new XmlEditor(doc));
    frmTimerBoil ftb=new frmTimerBoil(this.hopTableModel,((Integer)this.spinBollitura.getValue()).intValue(),fldNome.getText().trim());
    Gui.desktopPane.add(ftb);
    Utils.center(ftb,this);
    ftb.setVisible(true);
    	
    }//GEN-LAST:event_btnAdd12ActionPerformed
    
    
    private void btnBiabActionPerformed(java.awt.event.ActionEvent evt) {
    	frmBiabUtils fbu=new frmBiabUtils(maltTableModel.getGrammiMash(),this.spinVolumeFin.getVolume(),((Integer)this.spinBollitura.getValue()).intValue(),fldNome.getText().trim());
        Gui.desktopPane.add(fbu);
        Utils.center(fbu,this);
        fbu.setVisible(true);
        	
        }
    
    private void btnAdd11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd11ActionPerformed
	
	finalizeInInventory();
	
    }//GEN-LAST:event_btnAdd11ActionPerformed
    
    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked

    }//GEN-LAST:event_jLabel22MouseClicked
    
    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked

    }//GEN-LAST:event_jLabel21MouseClicked
    
    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked

    }//GEN-LAST:event_jLabel20MouseClicked
    
    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMorey());
    }//GEN-LAST:event_jLabel18MouseClicked
    
    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
	glassPanel.setColor(maltTableModel.getSRMDaniels());
    }//GEN-LAST:event_jLabel17MouseClicked
    
    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
	glassPanel.setColor(maltTableModel.getSRMMosher());
    }//GEN-LAST:event_jLabel16MouseClicked
    
    private boolean flagUm=false;
    public WaterNeeded waterNeeded=new WaterNeeded();
    //public frmWaterVols waterNeeded=new frmWaterVols();
    
	private void btnAdd10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd10ActionPerformed
	    java.awt.Font f;
            try {
                f = new java.awt.Font("Tahoma", 0, 12);
            } catch (Exception ex){
                f = new java.awt.Font("Arial", 0, 12);
            }
            String MainInfo=toRecipeData().getDes4Forum()+mashDesign.getDesc();
            new Info(MainInfo).startModal(this);
            
	}//GEN-LAST:event_btnAdd10ActionPerformed
	
	
	public static String BEGIN_MALTS = "{begin-malts}";
	public static String END_MALTS = "{end-malts}";
	public static String BEGIN_HOPS = "{begin-hops}";
	public static String END_HOPS = "{end-hops}";
	public static String BEGIN_YEASTS = "{begin-yeasts}";
	public static String END_YEASTS = "{end-yeasts}";
	public static String BEGIN_MASHSTEPS="{begin-mashsteps}";
	public static String END_MASHSTEPS="{end-mashsteps}";
	
	private void showIngredienti()
	{
		if (brewStyle==null)return;
		String water, malts, hops, spices, yeasts, stylename;
		stylename=brewStyle.getNumero()+" " + brewStyle.getNome();
		water=brewStyle.getWater();
		malts= brewStyle.getMalt();
		hops=brewStyle.getHops();
		spices=brewStyle.getSpices();
		yeasts=brewStyle.getYeast();
		
		frmIngredienti fi= new frmIngredienti(stylename,water,malts,hops,spices,yeasts);
    	Main.gui.addFrame(fi);
    	Dimension desktopSize = Main.gui.desktopPane.getSize();
    	Dimension jInternalFrameSize = fi.getSize();
    	fi.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
		
	}
	
	private void btnAdd9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd9ActionPerformed
	    try {
		RandomAccessFile f=new RandomAccessFile(Main.printTemplate,"r");
		byte b[]=new byte[(int)f.length()];
		f.readFully(b);
		f.close();
		String str=new String(b);
		String res="";
		int i=str.indexOf(BEGIN_MALTS);
		int e=str.indexOf(END_MALTS);
		
		RecipeData rec=toRecipeData();
		if(i>=0 && e>=0){
		    
		    String S=str.substring(0,i);
		    String R=str.substring(i+BEGIN_MALTS.length(),e);
		    Method[] methods=new Malt().getClass().getDeclaredMethods();
		    for(Malt m: rec.getMalts()){
			String  RR=new String(R);
			
			for(int j=0;j<methods.length;j++){
			    Method method=methods[j];
			    method.setAccessible(true);
			    if(RR.indexOf("malt."+method.getName())>-1){
				Object o=method.invoke(m);
				String r=o==null?"":o.toString();
				if(method.getReturnType().toString().equalsIgnoreCase("double")||method.getReturnType().toString().compareTo(Double.class.toString())==0){
				    r=NumberFormatter.format03((Double)method.invoke(m));
				}
				RR=RR.replaceAll("malt."+method.getName(),r);
			    }
			}
			
			S+=RR;
		    }
		    res+=S;
		}
		if(e>=0)
		    str=str.substring(e+END_MALTS.length());
		
		
		
		i=str.indexOf(BEGIN_HOPS);
		e=str.indexOf(END_HOPS);
		
		if(i>=0 && e>=0){
		    
		    String S=str.substring(0,i);
		    String R=str.substring(i+BEGIN_HOPS.length(),e);
		    Method[] methods=new Hop().getClass().getDeclaredMethods();
		    for(Hop m: rec.getHops()){
			String  RR=new String(R);
			
			for(int j=0;j<methods.length;j++){
			    Method method=methods[j];
			    method.setAccessible(true);
			    if(RR.indexOf("hop."+method.getName())>-1){
				Object o=method.invoke(m);
				String r=o==null?"":o.toString();
				if(	method.getReturnType().toString().equalsIgnoreCase("double")||
					method.getReturnType().toString().compareTo(Double.class.toString())==0){
				    r=NumberFormatter.format01((Double)method.invoke(m));
				}
				RR=RR.replaceAll("hop."+method.getName(),r);
			    }
			}
			S+=RR;
		    }
		    res+=S;
		}
		if(e>=0)
		    str=str.substring(e+END_HOPS.length());
		
		
		i=str.indexOf(BEGIN_YEASTS);
		e=str.indexOf(END_YEASTS);
		
		if(i>=0 && e>=0){
		    
		    String S=str.substring(0,i);
		    String R=str.substring(i+BEGIN_YEASTS.length(),e);
		    Method[] methods=new Yeast().getClass().getDeclaredMethods();
		    for(Yeast m: rec.getYeasts()){
			String  RR=new String(R);
			
			for(int j=0;j<methods.length;j++){
			    Method method=methods[j];
			    method.setAccessible(true);
			    if(RR.indexOf("yeast."+method.getName())>-1){
				Object o=method.invoke(m);
				String r=o==null?"":o.toString();
				if(method.getReturnType().toString().equalsIgnoreCase("double")||method.getReturnType().toString().compareTo(Double.class.toString())==0){
				    r=NumberFormatter.format01((Double)method.invoke(m));
				}
				RR=RR.replaceAll("yeast."+method.getName(),r);
			    }
			}
			
			
			S+=RR;
		    }
		    res+=S;
		}
		if(e>=0)
		    str=str.substring(e+END_YEASTS.length());
		
		
		//ixtlanas mash steps
		i=str.indexOf(BEGIN_MASHSTEPS);
		e=str.indexOf(END_MASHSTEPS);
		if(i>=0 && e>=0){
		    String S=str.substring(0,i);
		    String R=str.substring(i+BEGIN_MASHSTEPS.length(),e);
		    Method[] methods=new MashStep().getClass().getDeclaredMethods();
		    for(MashStep m: rec.getInfusionSteps()){
		    	String  RR=new String(R);
		    	for(int j=0;j<methods.length;j++){
				    Method method=methods[j];
				    method.setAccessible(true);
				    if(RR.indexOf("MashStep."+method.getName())>-1){
				    	Object o=method.invoke(m);
				    	String r=o==null?"":o.toString();
				    		if(method.getReturnType().toString().equalsIgnoreCase("double")||method.getReturnType().toString().compareTo(Double.class.toString())==0){
				    			r=NumberFormatter.format01((Double)method.invoke(m));
				    		}
				    		RR=RR.replaceAll("MashStep."+method.getName(),r);
				    	}
		    		}
		    		S+=RR;
		    	}
		    	res+=S;
			}
		if(e>=0)
		    str=str.substring(e+END_MASHSTEPS.length());
		res+=str;
		
		
		Method[] methods=rec.getClass().getDeclaredMethods();
		for(int j=0;j<methods.length;j++){
		    Method method=methods[j];
		    method.setAccessible(true);
		    if(res.indexOf("ricetta."+method.getName())>-1){
			String r=""+method.invoke(rec);
			if(method.getReturnType().toString().equalsIgnoreCase("double")||method.getReturnType().toString().compareTo(Double.class.toString())==0){
			    r=NumberFormatter.format01((Double)method.invoke(rec));
			}
			res=res.replaceAll("ricetta."+method.getName(),r);
		    }
		}
		
		methods=this.getClass().getDeclaredMethods();
		for(int j=0;j<methods.length;j++){
		    Method method=methods[j];
		    method.setAccessible(true);
		    if(res.indexOf("ricetta."+method.getName())>-1){
			String r=""+method.invoke(this);
			if(method.getReturnType().toString().equalsIgnoreCase("double")||method.getReturnType().toString().compareTo(Double.class.toString())==0){
			    r=NumberFormatter.format01((Double)method.invoke(this));
			}
			res=res.replaceAll("ricetta."+method.getName(),r);
		    }
		}
		File fileOut = new File(Main.userDir+"/Templates/output.html");
		FileWriter fstream = new FileWriter(fileOut);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(res);
		out.close();
		
		FileOutputStream fos = new FileOutputStream(Main.userDir+"/Templates/mash.jpg");
		ImageIO.write(mashDesign.getImage(),"JPEG",fos);
		fos.close();
		
		new Utils.BareBonesBrowserLaunch().openURL("file:///" + Main.userDir+"/Templates/output.html");
		
	    } catch (Exception ex) {
		Utils.showException(ex,"",this);
	    }
	    
	}//GEN-LAST:event_btnAdd9ActionPerformed
	private void btnIngPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRem1ActionPerformed
		frmIngPie fi=new frmIngPie("Percentuali degli ingredienti");
	    Gui.desktopPane.add(fi);
	    Utils.center(fi,this);
	    fi.setVisible(true);
	    }
	private void btnAdd8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd8ActionPerformed
	    StrikeTemp ed=new StrikeTemp();
	    ed.setKgMalto( this.summaryTableModel.getTotG() /1000 );
	    Gui.desktopPane.add(ed);
	    Utils.center(ed,this);
	    ed.setVisible(true);
	}//GEN-LAST:event_btnAdd8ActionPerformed
	
	private void btnAdd6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd6ActionPerformed
	    DiluitionForm ed=new DiluitionForm(this.getVolume(), this.getGravity(), this.hopTableModel.getIBUTinseth());
	    Gui.desktopPane.add(ed);
	    Utils.center(ed,this);
	    ed.setVisible(true);
	}//GEN-LAST:event_btnAdd6ActionPerformed
	
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
	    Hop hop=hopTableModel.getRow(tblHops.getSelectedRow());
	    if(hop!=null){
		CalcoloHopLoss ed= new CalcoloHopLoss();
		ed.set(hop);
		Utils.center(ed,this);
		ed.startModal(Ricetta.this);
		hop=ed.get();
		this.hopTableModel.getRow(this.tblHops. getSelectedRow()).setAlfaAcidi(hop.getAlfaAcidi());
		ricettaModificata();
	    }
	}//GEN-LAST:event_jMenuItem1ActionPerformed
	
	private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
	    glassPanel.setColor(maltTableModel.getSRMMorey());
	}//GEN-LAST:event_jLabel11MouseClicked
	
	private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
	    glassPanel.setColor(maltTableModel.getSRMDaniels());
	}//GEN-LAST:event_jLabel10MouseClicked
	
	private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
	    glassPanel.setColor(maltTableModel.getSRMMosher());
	}//GEN-LAST:event_jLabel9MouseClicked
	
	private void btnAdd5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd5ActionPerformed
	    EvaporationForm ed=new EvaporationForm(this, this.getVolumeBoll(), this.getVolume(), this.getGravity(), this.getBollitura());
	    Gui.desktopPane.add(ed);
	    Utils.center(ed,this);
	    ed.setVisible(true);
	}//GEN-LAST:event_btnAdd5ActionPerformed
	
	private void jScrollPane4MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane4MouseWheelMoved

	}//GEN-LAST:event_jScrollPane4MouseWheelMoved
	
	private void tblYeastMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tblYeastMouseWheelMoved

	}//GEN-LAST:event_tblYeastMouseWheelMoved
	
	private void btnRem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRem2ActionPerformed
	    this.yeastTableModel.remRow(this.yeastSorter.modelIndex(this.tblYeast.getSelectedRow()));
	}//GEN-LAST:event_btnRem2ActionPerformed
	
	private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
	    this.yeastPicker.startModal(this);
	    YeastType type=(YeastType)this.yeastPicker.getSelection();
	    if(type!=null){
		this.yeastTableModel.addRow(new Yeast(this.thisRicetta,type));
		ricettaModificata();
	    }
	}//GEN-LAST:event_btnAdd2ActionPerformed
	
	private void tblMaltsMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tblMaltsMouseWheelMoved

	}//GEN-LAST:event_tblMaltsMouseWheelMoved
	
	private void jScrollPane2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane2MouseWheelMoved

	}//GEN-LAST:event_jScrollPane2MouseWheelMoved
	
	private void btnStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStyleActionPerformed
	    this.brewStylePicker.startModal(this);
	    BrewStyle type=(BrewStyle)this.brewStylePicker.getSelection();
	    setBrewStyle(type.getNumero());
	}//GEN-LAST:event_btnStyleActionPerformed
	
	private void fldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fldNomeKeyTyped
	    this.dirty=true;
	}//GEN-LAST:event_fldNomeKeyTyped
	
	private void spinBollituraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinBollituraStateChanged
	    int v=((Integer)this.spinBollitura.getValue()).intValue();
	    this.thisRicetta.setBollitura(v);
	    ricettaModificata();
	}//GEN-LAST:event_spinBollituraStateChanged
	
	private void spinEfficienzaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinEfficienzaStateChanged
	    int v=((Integer)this.spinEfficienza.getValue()).intValue();
	    if(this.lock.isSelected()){
		this.maltTableModel.adjustTo( this.thisRicetta.getEfficienza(), this.thisRicetta.getVolume(),
			v,  this.thisRicetta.getVolume());
		
	    }
	    this.thisRicetta.setEfficienza(v);
	    ricettaModificata();
	}//GEN-LAST:event_spinEfficienzaStateChanged
	
	private void btnRem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRem1ActionPerformed
	    if(this.tblMalts.getSelectedRow()>=0){
		this.maltTableModel.remRow(this.maltSorter.modelIndex(this.tblMalts.getSelectedRow()));
		ricettaModificata();
	    }
	}//GEN-LAST:event_btnRem1ActionPerformed
	

	    
	private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
	    this.maltPicker.startModal(this);
	    MaltType type=(MaltType)this.maltPicker.getSelection();
	    if(type!=null) {
		tblMalts.editCellAt(
			maltSorter.modelIndex(
			this.maltTableModel.addRow(new Malt(this.thisRicetta,type))),
			2);
		ricettaModificata();
	    }
	}//GEN-LAST:event_btnAdd1ActionPerformed
	
	
	
	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
		if(!isDirty()){
			dispose();
			return;
			}
		int option = JOptionPane.showConfirmDialog(Main.gui, "Uscire senza salvare le modifiche?", "Ricetta", JOptionPane.YES_NO_OPTION);  
        if (option == JOptionPane.YES_OPTION){  
            dispose();  
            this.setVisible(true);
        }  
	}//GEN-LAST:event_formInternalFrameClosing
	
	private void btnRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemActionPerformed
	    if(this.tblHops.getSelectedRow()<0)return;
	    this.hopTableModel.remRow(this.hopSorter.modelIndex(this.tblHops.getSelectedRow()));
	    ricettaModificata();
	}//GEN-LAST:event_btnRemActionPerformed
	
	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
	    this.hopPicker.startModal(this);
		
	    HopType type=(HopType)this.hopPicker.getSelection();
	    if(type!=null){
		//this.hopTableModel.addRow(new Hop(this.thisRicetta,type));
		tblHops.editCellAt(
			hopSorter.modelIndex(
			this.hopTableModel.addRow(new Hop(this.thisRicetta,type))),
			2);
		ricettaModificata();
	    }
	}//GEN-LAST:event_btnAddActionPerformed
	
        private void btnDupHopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDupHopActionPerformed
	    if(this.tblHops.getSelectedRow()<0)return;
	    Hop h=Hop.fromXml( this.hopTableModel.getRow(this.hopSorter.modelIndex(this.tblHops.getSelectedRow())).toXml());
	    h.setRicetta(this);
	    this.hopTableModel.addRow(h);
	    ricettaModificata();
}//GEN-LAST:event_btnDupHopActionPerformed
	public boolean isConcetratedBoil(){
	    return chkConcentratedBoil.isSelected();
	}
	public double getVolumeDiluito(){
	    return spinVolumeDiluito.getVolume();
	}
	
	public double getVolumeDopoEventualeDiluizione(){
	    if(isConcetratedBoil()){
		return getVolumeDiluito();
	    } else{
		return getVolume();
	    }
	}
	
	private int counter=0;
	Thread colorThread=null;
	public void ricettaModificata(){
	    dirty=true;
	    if(hopTableModel.getRows()!=null){
		for(Hop h: hopTableModel.getRows()){
		    h.setIBUDaniels(h.calcIBUDaniels());
		    h.setIBURager(h.calcIBURager());
		    h.setIBUTinseth(h.calcIBUTinseth());
		}
	    }
	    double vd=spinVolumeDiluito.getVolume();
	    double vf=spinVolumeFin.getVolume();
	    
	    boolean concentrato=chkConcentratedBoil.isSelected();
	    double factor=concentrato?vf/vd:1;
	    summaryTableModel.setIBU(hopTableModel.getIBUTinseth());
	    summaryTableModel.setIBU2(hopTableModel.getIBURager());
	    summaryTableModel.setIBUGaretz(hopTableModel.getIBUGaretz());
	    summaryTableModel.setIBUDaniels(hopTableModel.getIBUDaniels());
	    
	    
	    summaryTableModel.setTotL(hopTableModel.getGrammi());
	    summaryTableModel.setTotG(maltTableModel.getGrammi());
	    
	    waterNeeded.setMashKg((double)maltTableModel.getGrammiMash()/1000);
	    waterNeeded.setBatchSize(spinVolumeFin.getVolume());
	    waterNeeded.setBoilTime(getBollitura());
	    waterPanel.setTotWater(waterNeeded.getTotWater());
	    double sg=maltTableModel.getSG(concentrato);
	    
	    summaryTableModel.setSG(sg);
	    sg=maltTableModel.getSG(false);
	    summaryTableModel.setSGPB(
		    Utils.Plato2SG(
		    Utils.SG2Plato(sg)*
		    spinVolumeFin.getVolume()/spinVolumeBoll.getVolume()
		    )
		    );
	    
	    tblSummary.setCellSelectionEnabled(false);
	    tblSummary.setRowSelectionAllowed(false);
	    tblSummary.setColumnSelectionAllowed(false);
	    
	    double srmMosher=maltTableModel.getSRMMosher();
	    double srmDaniels=maltTableModel.getSRMDaniels();
	    double srmMorey=maltTableModel.getSRMMorey();
	    
	    jLabel9.setBackground(Main.treeColor.srmToRgb(srmMosher));
	    jLabel10.setBackground(Main.treeColor.srmToRgb(srmDaniels));
	    jLabel11.setBackground(Main.treeColor.srmToRgb(srmMorey));
	    jLabel16.setBackground(jLabel9.getBackground());
	    jLabel17.setBackground(jLabel10.getBackground());
	    jLabel18.setBackground(jLabel11.getBackground());
	    
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
			    while(Ricetta.this.counter>0){
				Ricetta.this.counter--;
				try {
				    java.lang.Thread.sleep(200);
				} catch (InterruptedException ex) {
				    ex.printStackTrace();
				}
				if(Ricetta.this.counter==0){
				    Ricetta.this.glassPanel.setColor(
					    Ricetta.this.maltTableModel.getSRMMosher()
					    );
				}
			    }
			    
			    while(Ricetta.this.counter==0){
				try {
				    java.lang.Thread.sleep(200);
				} catch (InterruptedException ex) {
				    ex.printStackTrace();
				}
			    }
			}
		    }
		};
		this.colorThread.start();
	    }
	    this.tblSummary.updateUI();
	    this.dirty=true;
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd10;
    private javax.swing.JButton btnAdd11;
    private javax.swing.JButton btnAdd12;
    private javax.swing.JButton btnBiab;
    private JLabel lblPicBeer;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnAdd5;
    private javax.swing.JButton btnAdd6;
    private javax.swing.JButton btnAdd8;
    private javax.swing.JButton btnAdd9;
    private javax.swing.JButton btnDupHop;
    private javax.swing.JButton btnRem;
    private javax.swing.JButton btnRem1;
    private javax.swing.JButton btnIngPie;
    private javax.swing.JButton btnRem2;
    private javax.swing.JButton btnStyle;
    private javax.swing.JCheckBox chkConcentratedBoil;
    private javax.swing.JTextField fldNome;
    private javax.swing.JEditorPane fldNote;
    public javax.swing.JPopupMenu hopPopup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDil;
    private javax.swing.JLabel lblFermentazione;
    private javax.swing.JCheckBox lock;
    private javax.swing.JSpinner spinBollitura;
    private javax.swing.JSpinner spinEfficienza;
    private jmash.component.JVolumeSpinner spinVolumeBoll;
    private jmash.component.JVolumeSpinner spinVolumeDiluito;
    private jmash.component.JVolumeSpinner spinVolumeFin;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JTable tblHops;
    private javax.swing.JTable tblMalts;
    private javax.swing.JTable tblSummary;
    private javax.swing.JTable tblYeast;
    private javax.swing.JTextField txtEBC1;
    private javax.swing.JTextField txtEBC2;
    private javax.swing.JButton btnIngredienti;
    private javax.swing.JTextField txtFG1;
    private javax.swing.JTextField txtFG2;
    private javax.swing.JTextField txtIBU1;
    private javax.swing.JTextField txtIBU2;
    private javax.swing.JTextField txtOG1;
    private javax.swing.JTextField txtOG2;
    private javax.swing.JTextField txtStile;
    // End of variables declaration//GEN-END:variables
    
    
    public double getVolume(){
	return spinVolumeFin.getVolume();
    }
    private double volumePrec;
    public void setVolume(double v){
	spinVolumeFin.setVolume(v);
	volumePrec=v;
    }
    public double getVolumeBoll(){
	return spinVolumeBoll.getVolume();
    }
    public void setVolumeBoll(double v){
	spinVolumeBoll.setVolume(v);
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
//    public void setFotografia(String foto64)
//    {
//    	this.fotografiaBase64=foto64;
//    }
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
    
    public void fromRecipeData(RecipeData src){
	if(src.getBollitura()!=null)
	    setBollitura(src.getBollitura());
	spinBollitura.setValue(getBollitura());
	fldNome.setText(src.getNome());
	fldNote.setText(src.getNote());
	try{
		if(src.getFotografia()==null || src.getFotografia().trim()=="")
		{
			fotografia="";  
			lblPicBeer.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/addphoto.png"))); 
		}
		else
		{
			fotografia=src.getFotografia();
			lblPicBeer.setIcon(new ImageIcon(Utils.getImageFromBase64(fotografia)));
		}
	}
	catch(Exception ex)
	{
		fotografia="";
		lblPicBeer.setIcon(new ImageIcon(Ricetta.class.getResource("/jmash/images/addphoto.png"))); 
	}
	if(src.getUnitaMisura()!=null)
	    setUnitaMisura(src.getUnitaMisura());
	if(src.getVolumeBoll()!=null)
	    setVolumeBoll(src.getVolumeBoll());
	spinVolumeBoll.setVolume(getVolumeBoll());
	if(src.getBollituraConcentrata()!=null)
	    chkConcentratedBoil.setSelected(src.getBollituraConcentrata());
	if(src.getVolumeDiluito()!=null)
	    spinVolumeDiluito.setVolume(src.getVolumeDiluito());
	chkConcentratedBoilActionPerformed(null);
	if(src.getVolumeFin()!=null)
	    setVolume(src.getVolumeFin());
	spinVolumeFin.setVolume(getVolume());
	if(src.getEfficienza()!=null)
	    setEfficienza(src.getEfficienza());
	spinEfficienza.setValue(getEfficienza());
	// fatto alla cazzo, modificato metodo setBrewStyle, viene scritto/recuperato da ricetta solo il codice (1A etc) e recuperato da Main.getBJCPStyles
	setBrewStyle(src.getCodiceStile());
	if(src.getHops()!=null){
	    for(Hop h : src.getHops()){
		h.setRicetta(this);
	    }
	}
	if(src.getMalts()!=null){
	    for(Malt h : src.getMalts()){
		h.setRicetta(this);
	    }
	}
	if(src.getYeasts()!=null){
	    for(Yeast h : src.getYeasts()){
		h.setRicetta(this);
	    }
	}
	hopTableModel.setRows(src.getHops());
	maltTableModel.setRows(src.getMalts());
	yeastTableModel.setRows(src.getYeasts());
	mashDesign.mashStepTableModel.setRows(src.getInfusionSteps());
	mashDesign.mashDecoctionStepTableModel.setRows(src.getDecoctionSteps());
	if(src.getSourceWater()!=null)
	    waterPanel.setSource(src.getSourceWater());
	if(src.getDestWater()!=null)
	    waterPanel.setDest(src.getDestWater());
	if(src.getTreatment()!=null)
	    waterPanel.setTreatment(src.getTreatment());
	if(src.getWaterNeeded()!=null)
	    waterNeeded.fromXml(src.getWaterNeeded());
	
	
	SwingUtilities.invokeLater(
		new Runnable(){
            @Override
	    public void run(){
		splitPanel.setDividerLocation((double)(double)maltTableModel.getRowCount()/(hopTableModel.getRowCount()+maltTableModel.getRowCount()));
	    }});
	    ricettaModificata();
	    mashDesign.mashModificato();
    }
    public RecipeData toRecipeData(){
	RecipeData src= new RecipeData();
	src.setBollitura(getBollitura());
	src.setNome(fldNome.getText());
	src.setFotografia(this.fotografia);
	src.setNote(fldNote.getText());
	src.setUnitaMisura(getUnitaMisura());
	src.setVolumeBoll(getVolumeBoll());
	src.setVolumeFin(getVolume());
	src.setVolumeDiluito(getVolumeDiluito());
	src.setBollituraConcentrata(chkConcentratedBoil.isSelected());
	src.setEfficienza(getEfficienza());
	if(brewStyle==null)
		{src.setCodiceStile("nd"); }
	else
		{src.setCodiceStile(brewStyle.getNumero());} 
	src.setHops(hopTableModel.getRows());
	src.setMalts(maltTableModel.getRows());
	src.setYeasts(yeastTableModel.getRows());
	src.setInfusionSteps(mashDesign.mashStepTableModel.getRows());
	src.setDecoctionSteps(mashDesign.mashDecoctionStepTableModel.getRows());
	
	src.setSourceWater(waterPanel.getSource());
	src.setDestWater(waterPanel.getDest());
	src.setTreatment(waterPanel.getTreatment());
	src.setWaterNeeded(waterNeeded.toXml());
	return src;
    }
    public String getXmlRicetta(){
	Document doc=toRecipeData().toXml();
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	String xml=outputter.outputString(doc);
	return xml;
    }
    
    public File saveRicetta(){
	if(this.file==null){
	    file=Utils.pickFileToSave(this, (String)Main.getFromCache("recipe.dir",Main.recipeDir));
	}
	if(this.file==null)return null;
	
	Document doc = toRecipeData().toXml();
	Utils.saveXmlAsFile(doc, this.file, this);
	
	setTitle(this.file.getName());
	this.dirty=false;
	
	return file;
    }
    public void read(File file){
	try {
	    Document doc=Utils.readFileAsXml(file.toString());
	    if(doc==null)return;
	    read(doc);
	} catch (Exception ex) {
	    Utils.showException(ex);
	}
    }
    public void read(Document doc){
		RecipeData rec=new RecipeData();
		rec.read(doc);
		rec.setRicetta(this);
		fromRecipeData(rec);
		ricettaModificata();
		this.mashDesign.mashModificato();
		if(this.file!=null) {
		    setTitle(this.file.getName());
	}
	splitPanel.setDividerLocation((double)maltTableModel.getRowCount()/(hopTableModel.getRowCount()+maltTableModel.getRowCount()));
	this.dirty=false;
    }
    private BrewStyle brewStyle;
    private static NumberFormatter nf = new NumberFormatter("0.000");
    public void setBrewStyle(String codiceStile){
    	for(BrewStyle bs: Main.bjcpStyles)
    	{
    		if(bs.getNumero().equalsIgnoreCase(codiceStile))
    		{
    			this.brewStyle=bs;
    			break;
    		}
    	}
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
    public Double getGravity(){
	return this.maltTableModel.getSG(false);
    }
    public String getSGPerStampa(){
	return String.format("%.03f", this.maltTableModel.getSG(chkConcentratedBoil.isSelected()));
    }
    private double srm=-1;
    private double ebc=-1;

    public void setEbc(double ebc){
	this.ebc=ebc;
    }    
    public double getEbc(){
	return ebc;
    }
    public Double getIBUTinseth(){
	return hopTableModel.getIBUTinseth();
    }
    public String getBUGUTinsethPerStampa()
    {
    	//return String.format("%.2f", hopTableModel.getIBUTinseth()/((this.maltTableModel.getSG(chkConcentratedBoil.isSelected())-1)*100));
    	return String.format("%.2f", (hopTableModel.getIBUTinseth()/(this.maltTableModel.getSG(chkConcentratedBoil.isSelected())-1)/1000));
    }
    public int getGrammiTotali(){
	return summaryTableModel.getTotG();
    }
    public Double getPlato(){
	return Utils.SG2Plato(getGravity());
    }
    public Double getPPerStampa(){
	return Utils.SG2Plato( this.maltTableModel.getSG(chkConcentratedBoil.isSelected()));
    }
    public String getHexColor(){
	String rgb = Integer.toHexString(Main.treeColor.srmToRgb(srm).getRGB());
	rgb = rgb.substring(2, rgb.length());
	return rgb;
    }
    
    public void setCotta(Boolean iscotta)
    {
    	isCotta=iscotta;
    	this.lblPicBeer.setVisible(!isCotta);
    	if(isCotta) this.fotografia=null;
    }

    
    @Override
    public void setEnabled(boolean F){
	btnStyle.setEnabled(F);
	spinVolumeBoll.setEnabled(F);
	spinVolumeFin.setEnabled(F);
	spinVolumeDiluito.setEnabled(F);
	spinEfficienza.setEnabled(F);
	fldNome.setEnabled(F);
	spinBollitura.setEnabled(F);
	lock.setEnabled(F);
	btnAdd.setEnabled(F);
	btnRem.setEnabled(F);
	btnAdd1.setEnabled(F);
	btnRem1.setEnabled(F);
	btnIngPie.setEnabled(F);
	tblMalts.setEnabled(F);
	tblHops.setEnabled(F);
	btnAdd2.setEnabled(F);
	btnRem2.setEnabled(F);
	tblYeast.setEnabled(F);
	//lblPicBeer.setVisible(F);
	//lblPicBeer.setEnabled(F);
	waterPanel.setEnabled(F);
	
	if(!F) mashDesign.setReadOnly();
    }
    
    @Override
    public void remove(int index) {
    }
    public void hideMashDesign(){
	this.jTabbedPane1.remove(2);
    }
    public void showMashDesign(){
	this.jTabbedPane1.add(this.mashDesign,"Mash design",2);
    }
    public void addPanel(Component c, String s, int i){
	this.jTabbedPane1.add(c,s,i);
    }
    
    
    public void finalizeInInventory(){
	Acquisto inv=Acquisto.buildFRMInventario();
	List<Malt> reqMalts=new ArrayList<Malt>();
	List<Hop> reqHops=new ArrayList<Hop>();
	
	for(Hop m1:hopTableModel.getRows()){
	    boolean flag=true;
	    for(Hop m2:reqHops){
		if(m2.getNome().equalsIgnoreCase(m1.getNome())){
		    m2.setGrammi(m2.getGrammi()+m1.getGrammi());
		    flag=false;
		}
	    }
	    if(flag){
		reqHops.add(Hop.fromXml(m1.toXml()));
	    }
	}
	for(Malt m1:maltTableModel.getRows()){
	    boolean flag=true;
	    for(Malt m2:reqMalts){
		if(m2.getNome().equalsIgnoreCase(m1.getNome())){
		    m2.setGrammi(m2.getGrammi()+m1.getGrammi());
		    flag=false;
		}
	    }
	    if(flag){
		reqMalts.add(Malt.fromXml(m1.toXml()));
	    }
	}
	
	List<CanDo<Malt>> cantDoMalt=new ArrayList<CanDo<Malt>>();
	List<CanDo<Malt>> canDoMalt=new ArrayList<CanDo<Malt>>();
	List<CanDo<Malt>> mayDoMalt=new ArrayList<CanDo<Malt>>();
	
	List<CanDo<Hop>> cantDoHop=new ArrayList<CanDo<Hop>>();
	List<CanDo<Hop>> canDoHop=new ArrayList<CanDo<Hop>>();
	List<CanDo<Hop>> mayDoHop=new ArrayList<CanDo<Hop>>();
	
	for(Malt m:reqMalts){
	    List<Malt> found=inv.getMalts(m.getNome());
	    if(found.size()==0)
		cantDoMalt.add(new CanDo<Malt>(m, null));
	    else if(found.size()==1){
		Malt M=found.get(0);
		if(M.getGrammi()>=m.getGrammi()){
		    canDoMalt.add(new CanDo<Malt>(m, M));
		} else{
		    cantDoMalt.add(new CanDo<Malt>(m, M));
		}
	    } else if(found.size()>=1){
		mayDoMalt.add(new CanDo<Malt>(m, null));
	    }
	}
	for(Hop m:reqHops){
	    List<Hop> found=inv.getHops(m.getNome());
	    if(found.size()==0)
		cantDoHop.add(new CanDo<Hop>(m, null));
	    else if(found.size()==1){
		Hop M=found.get(0);
		if(M.getGrammi()>=m.getGrammi()){
		    canDoHop.add(new CanDo<Hop>(m, M));
		} else{
		    cantDoHop.add(new CanDo<Hop>(m, M));
		}
	    } else if(found.size()>=1){
		mayDoHop.add(new CanDo<Hop>(m, null));
	    }
	}
	
	if(cantDoHop.size()>0 || cantDoMalt.size()>0){
	    String s="I seguenti ingredienti non sono sufficienti nell'inventario:";
	    InventoryObjectTableModel model=new InventoryObjectTableModel();
	    for(CanDo<Malt> m:cantDoMalt){
		s+="\n"+m.getReq().getNome();
		model.addRow(m.getReq());
		if(m.getHave()!=null)s+=" (presenti "+m.getHave().getGrammi()+" gr, richiesti "+m.getReq().getGrammi()+")";
	    }
	    for(CanDo<Hop> m:cantDoHop){
		s+="\n"+m.getReq().getNome();
		model.addRow(m.getReq());
		if(m.getHave()!=null)s+=" (presenti "+m.getHave().getGrammi()+" gr, richiesti "+m.getReq().getGrammi()+")";
	    }
	    
	    new ChooseFromList("Mancanti...","I seguenti ingredienti non sono sufficienti nell'inventario:",model).startModal(this);
	    return;
	}
	if(cantDoHop.size()==0 && cantDoMalt.size()==0 && mayDoHop.size()==0 && mayDoMalt.size()==0){
	    new Info("Gli ingredienti sono tutti disponibili in inventario.").startModal(this);
	    return;
	}
	
	if(mayDoHop.size()>0 || mayDoMalt.size()>0){
	    boolean canDo=true;
	    String cantDo="Nell'inventario non sono presenti abbastanza dei seguenti ingredienti:";
	    for(CanDo<Malt> m:mayDoMalt){
		List<Malt> found=inv.getMalts(m.getReq().getNome());
		double tot=0;
		for(Malt mm:found){
		    tot+=mm.getGrammi();
		}
		if(tot>=m.getReq().getGrammi()){
		} else{
		    canDo=false;
		    cantDo+="\n"+m.getReq().getNome()+" (presenti "+tot+" gr, richiesti "+m.getReq().getGrammi()+")";
		}
	    }
	    for(CanDo<Hop> m:mayDoHop){
		List<Hop> found=inv.getHops(m.getReq().getNome());
		double tot=0;
		for(Hop mm:found){
		    tot+=mm.getGrammi();
		}
		if(tot>=m.getReq().getGrammi()){
		} else{
		    canDo=false;
		    cantDo+="\n"+m.getReq().getNome()+" (presenti "+tot+" gr, richiesti "+m.getReq().getGrammi()+")";
		}
	    }
	    if(canDo)
		new Info("Gli ingredienti sono tutti disponibili in inventario, seppure divisi in diverse voci.").startModal(this);
	    else
		new Info(cantDo).startModal(this);
	    
	    return;
	}
	
	//inv.save();
    }
    class CanDo<T> {
	private T req;
	private T have;
	public CanDo(T req, T have){
	    this.req=req;
	    this.have=have;
	}
	public T getReq() {
	    return req;
	}
	
	public void setReq(T req) {
	    this.req = req;
	}
	
	public T getHave() {
	    return have;
	}
	
	public void setHave(T have) {
	    this.have = have;
	}
    }
    
    public void demo(){
	Thread T=new Thread(){
            @Override
	    public void run(){
		try {
		    hbRobot rob=new hbRobot();
		    // select style
		    Point p = btnAdd12.getLocationOnScreen();
		    Point p2 = btnStyle.getLocationOnScreen();
		    rob.moveMouse(p.x,p.y, p2.x+5,p2.y+5);
		    rob.click();
		    Thread.sleep(500);
		    rob.moveMouse(p2.x+5,p2.y+5, p2.x+5+350,p2.y+5+120);
		    rob.click();
		    
		    rob.type("ENGL");
		    rob.delay(1000);
		    
		    rob.moveMouseRel(0,128);
		    rob.doubleClick();
		    
// adding malt
		    p = btnAdd12.getLocationOnScreen();
		    p2 = btnAdd1.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+5,p2.y+5);
		    rob.click();
		    Thread.sleep(500);
		    rob.moveMouse(p2.x+5,p2.y+5, p2.x+5+220,p2.y+5+20);
		    rob.click();
		    
		    rob.type("PALE");
		    rob.delay(100);
		    rob.mouseWheel(5);
		    rob.moveMouseRel(0,80);
		    rob.delay(100);
		    rob.doubleClick();
		    
		    
		    p2 = tblMalts.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+200,p2.y+10);
		    rob.deleteAndType(3800);
		    
		    
		    rob.gotoComponent(btnAdd1);
		    rob.click();
		    Thread.sleep(500);
		    rob.moveMouse(p2.x+5,p2.y+5, p2.x+5+220,p2.y+5+20);
		    rob.click();
		    
		    rob.type("CRYST");
		    
		    rob.delay(100);
		    rob.keyPress(KeyEvent.VK_DOWN);
		    rob.delay(100);
		    rob.keyPress(KeyEvent.VK_DOWN);
		    p2 = maltPicker.btnOk.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+10,p2.y+10);
		    rob.click();
		    
		    p2 = tblMalts.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+200,p2.y+20);
		    rob.deleteAndType(200);
		    
		    rob.gotoComponent(spinEfficienza);
		    rob.deleteAndType(81);
		    
// adding hops
		    rob.gotoComponent(btnAdd);
		    rob.click();
		    Thread.sleep(500);
		    p2 = hopPicker.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+25,p2.y+100);
		    rob.click();
		    
		    rob.type("GOLDING");
		    rob.delay(200);
		    rob.keyPress(KeyEvent.VK_DOWN);
		    rob.delay(100);
		    rob.gotoComponent(hopPicker.btnOk);
		    rob.doubleClick();
		    
		    p2 = tblHops.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+160,p2.y+10);
		    rob.deleteAndType(40);
		    
		    rob.gotoComponent(btnAdd);
		    rob.click();
		    Thread.sleep(500);
		    p2 = hopPicker.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+25,p2.y+100);
		    rob.click();
		    
		    rob.type("GOLDING");
		    rob.delay(200);
		    rob.keyPress(KeyEvent.VK_DOWN);
		    rob.delay(100);
		    rob.gotoComponent(hopPicker.btnOk);
		    rob.doubleClick();
		    
		    p2 = tblHops.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+160,p2.y+20);
		    rob.deleteAndType(40);
		    
		    rob.moveMouseTo(p2.x+420,p2.y+20);
		    rob.deleteAndType(15);
		    
		    
		    rob.gotoComponent(btnAdd);
		    rob.click();
		    Thread.sleep(500);
		    p2 = hopPicker.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+25,p2.y+100);
		    rob.click();
		    rob.type("cascad");
		    rob.delay(200);
		    rob.gotoComponent(hopPicker.btnOk);
		    rob.doubleClick();
		    
		    p2 = tblHops.getLocationOnScreen();
		    rob.moveMouseTo(p2.x+160,p2.y+36);
		    rob.deleteAndType(40);
		    
		    
		    rob.moveMouseTo(p2.x+460,p2.y+36);
		    rob.doubleClick();
		    rob.delay(500);
		    rob.keyPress(KeyEvent.VK_DOWN);rob.delay(100);
		    rob.keyPress(KeyEvent.VK_DOWN);rob.delay(120);
		    rob.keyPress(KeyEvent.VK_DOWN);rob.delay(180);
		    rob.keyPress(KeyEvent.VK_ENTER);
		    rob.keyPress(KeyEvent.VK_TAB);
		    
		    
		    rob.gotoComponent(fldNome);
		    rob.doubleClick();
		    rob.type("GOLDEN BITTER ALE");
		    rob.keyPress(KeyEvent.VK_ENTER);
		    
		    
		    
		    rob.gotoComponent(spinBollitura);
		    rob.doubleClick();
		    rob.type("75 min");
		    
		    rob.delay(1500);
		    rob.gotoComponent(lock);
		    rob.click();
		    
		    rob.delay(500);
		    rob.gotoComponent(spinVolumeFin);
		    rob.deleteAndType(50);
		    rob.gotoComponent(spinVolumeBoll);
		    rob.deleteAndType(57);
		    
		} catch (AWTException ex) {
		    ex.printStackTrace();
		} catch (InterruptedException ex) {
		    ex.printStackTrace();
		}
	    }};
	    T.start();
    }
}
