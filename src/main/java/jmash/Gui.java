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

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;
import jmash.config.XmlAbleEditor;
import jmash.config.XmlAbleTableModel;
import jmash.tableModel.BrewStylePickerTableModel;
import jmash.tableModel.GenericTableModel;
import jmash.tableModel.HopPickerTableModel;
import jmash.tableModel.MaltPickerTableModel;
import jmash.tableModel.WaterPickerTableModel;
import jmash.tableModel.YeastPickerTableModel;
import jmash.test.BeerXMLReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.border.EmptyBorder;

public class Gui extends javax.swing.JFrame {
	
    private static final long serialVersionUID = 348370096080739755L;
    JPopupMenu recipesPopup = new JPopupMenu();

    private Map<JMenuItem, JInternalFrame> Finestre = new HashMap<JMenuItem, JInternalFrame>();
    private Set<JInternalFrame> listaFinestre = new HashSet<JInternalFrame>();

    /** Creates new form Gui */
    public Gui() {
    try{
    	initComponents();
    }
    catch(Exception ex){}
	
	desktopPane=this.desktop;
	this.setIconImage(Main.mainIcon.getImage());
	setExtendedState(Frame.MAXIMIZED_BOTH);
	desktopPane.setTransferHandler(new TransferHandler() {
            @Override
	    public boolean canImport(JComponent dest, DataFlavor[] flavors) {
		return true;
	    }
	    
            @Override
	    public boolean importData(JComponent src,  Transferable t) {
		
		try {
		    DataFlavor[] flavors = t.getTransferDataFlavors();
		    for (int i = 0; i < flavors.length; i++) {
			DataFlavor flavor = flavors[i];
			if (flavor.equals(DataFlavor.javaFileListFlavor)) {
			    List l = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
			    Iterator iter = l.iterator();
			    while (iter.hasNext()) {
				File file = (File) iter.next();
				if(file.getCanonicalPath().endsWith(".rec")){
				    RecipeData data=new RecipeData();
				    Ricetta R=new Ricetta();
				    R.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
				    addFrame(R);
				    data.readRec(file.getCanonicalPath());
				    R.fromRecipeData(data);
				} else{
				    nuovaRicetta(new Ricetta(file));
				}
			    }
			}
		    }
		} catch (IOException ex) {
		    ex.printStackTrace();
		} catch (UnsupportedFlavorException ex) {
		    ex.printStackTrace();
		} catch (Exception ex) {
		    Utils.showException(ex);
		}
		
		return true;
	    }
	});
	updatePopupMenu();
        
    }
           
    public void updatePopupMenu(){
		recipesPopup.removeAll();
		mnuFile1.removeAll();
		int i=0;
		for(String s: lastOpenedRecipes){
		    Main.putIntoCache("Main.recipe"+i, s );
		    i++;
		}
		javax.swing.JMenuItem mnuItem1=new javax.swing.JMenuItem();
		mnuItem1.setText("Crea una nuova ricetta...");
		mnuItem1.addActionListener(new java.awt.event.ActionListener() {
	            @Override
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			nuovaRicetta(new Ricetta());
		    }
		});
		recipesPopup.add(mnuItem1);
		recipesPopup.addSeparator();
		menuItemDelete = new JMenuItem("Elimina elenco..");
        menuItemDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteLastRecipesEntries();
        	}
        });
        menuItemDelete.setSelected(true);
        mnuFile1.add(menuItemDelete);
        separator_5 = new JSeparator();
        mnuFile1.add(separator_5);
		for(i=0;i<20;i++){
		    String s=(String)Main.getFromCache("Main.recipe"+i,"");
		    lastOpenedRecipes.add(s);
		    if(!s.equals(""))
		    {
				mnuItem1=new javax.swing.JMenuItem();
				mnuItem1.setText(s);
				mnuItem1.setActionCommand(s);
				mnuItem1.addActionListener(new java.awt.event.ActionListener() 
				{
		                    @Override
				    public void actionPerformed(java.awt.event.ActionEvent evt) 
		                    {
		                    	Gui.this.addFrame(new Ricetta(new File(evt.getActionCommand())));
		                    }
				});
			mnuFile1.add(mnuItem1);
			mnuItem1=new javax.swing.JMenuItem();
			mnuItem1.setText(s);
			mnuItem1.setActionCommand(s);
			mnuItem1.addActionListener(new java.awt.event.ActionListener() {
	                    @Override
			    public void actionPerformed(java.awt.event.ActionEvent evt) {
				Gui.this.addFrame(new Ricetta(new File(evt.getActionCommand())));
			    }
			});
			recipesPopup.add(mnuItem1);
		    }
		}
    }
    
    public JToolBar getTaskBar(){return taskBar;}
    public JToolBar getSideBar(){return sideBar;}
    
    public javax.swing.JDesktopPane getDesktop(){return desktopPane;}
    public static HopPickerTableModel hopPickerTableModel= new HopPickerTableModel();
    public static MaltPickerTableModel maltPickerTableModel= new MaltPickerTableModel();
    public static WaterPickerTableModel waterPickerTableModel= new WaterPickerTableModel();
    public static YeastPickerTableModel yeastPickerTableModel= new YeastPickerTableModel();
    public static BrewStylePickerTableModel brewStylePickerTableModel=new BrewStylePickerTableModel();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * @throws IOException 
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws IOException {

        desktop = new javax.swing.JDesktopPane();
        toolbar = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnSaveAs = new javax.swing.JButton();
        btnSaveAll1 = new javax.swing.JButton();
        btnSaveAll2 = new javax.swing.JButton();
        btnMashDesign = new javax.swing.JButton();
        btnSaveAll4 = new javax.swing.JButton();
        btnSaveAll5 = new javax.swing.JButton();
        btnSaveAll7 = new javax.swing.JButton();
        btnSaveAll8 = new javax.swing.JButton();
        btnSaveAll9 = new javax.swing.JButton();
        btnSaveAll11 = new javax.swing.JButton();
        btnSaveAll14 = new javax.swing.JButton();
        btnSaveAll17 = new javax.swing.JButton();
        btnSaveAll15 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnUpdate = new javax.swing.JButton();
        sideBar = new javax.swing.JToolBar();
        btnNew1 = new javax.swing.JButton();
        btnSaveAll21 = new javax.swing.JButton();
        btnSaveAll12 = new javax.swing.JButton();
        btnSaveAll13 = new javax.swing.JButton();
        btnSaveAll13.setVisible(false);
        taskBar = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuNuova = new javax.swing.JMenuItem();
        mnuFile1 = new javax.swing.JMenu();
        mnuFromPromash = new javax.swing.JMenuItem();
        mnuFromPromash.setVisible(false);
        mnuBeerXML = new javax.swing.JMenuItem();
        mnuBeerXML.setVisible(false);
        mnuEsci = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuLuppoliXML = new javax.swing.JMenuItem();
        mnuMaltiXML = new javax.swing.JMenuItem();
        mnuLievitiXML = new javax.swing.JMenuItem();
        mnuAcquaXML = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ChiudiTutteMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(jmash.Main.Nome + " " + jmash.Main.getVersione());
        setFont(new java.awt.Font("Andale Mono IPA", 0, 10));
        setForeground(java.awt.Color.white);
        setLocationByPlatform(true);
        setName("guiFrame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        //desktop.setBackground(Color.BLUE);
        
        
        BufferedImage bf=null;
        bf=ImageIO.read(getClass().getResourceAsStream("images/bkgrnd.jpg"));
        desktop = new JDesktopBackground(bf);
        
        desktop.setDoubleBuffered(true);
        desktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desktopMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                desktopMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                desktopMouseReleased(evt);
            }
        });
        desktop.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                desktopComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                desktopComponentRemoved(evt);
            }
        });
        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        toolbar.setFloatable(false);

        btnNew.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/new.png"))); // NOI18N
        btnNew.setToolTipText("Nuova ricetta");
        btnNew.setMaximumSize(new java.awt.Dimension(37, 35));
        btnNew.setMinimumSize(new java.awt.Dimension(37, 35));
        btnNew.setPreferredSize(new java.awt.Dimension(37, 35));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        toolbar.add(btnNew);

        btnOpen.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/open.png"))); // NOI18N
        btnOpen.setToolTipText("Apri ricetta");
        btnOpen.setIconTextGap(0);
        btnOpen.setMaximumSize(new java.awt.Dimension(37, 35));
        btnOpen.setMinimumSize(new java.awt.Dimension(37, 35));
        btnOpen.setPreferredSize(new java.awt.Dimension(37, 35));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        btnOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOpenMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnOpenMouseReleased(evt);
            }
        });
        toolbar.add(btnOpen);

        btnSave.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/save.png"))); // NOI18N
        btnSave.setToolTipText("Salva ricetta");
        btnSave.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSave.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSave.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        toolbar.add(btnSave);

        btnSaveAs.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/saveas.png"))); // NOI18N
        btnSaveAs.setToolTipText("Salva ricetta come...");
        btnSaveAs.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAs.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAs.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAsActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAs);

        btnSaveAll1.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/diluizioni.png"))); // NOI18N
        btnSaveAll1.setToolTipText("Diluizioni e concentrazioni");
        btnSaveAll1.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll1.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll1.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll1ActionPerformed(evt);
            }
        });
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        toolbar.add(separator);
        toolbar.add(btnSaveAll1);

        btnSaveAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/steam.png"))); // NOI18N
        btnSaveAll2.setToolTipText("Evaporazione");
        btnSaveAll2.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll2.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll2.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll2ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll2);

        btnMashDesign.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/mashdesign.png"))); // NOI18N
        btnMashDesign.setToolTipText("Mash Designer");
        btnMashDesign.setMaximumSize(new java.awt.Dimension(37, 35));
        btnMashDesign.setMinimumSize(new java.awt.Dimension(37, 35));
        btnMashDesign.setPreferredSize(new java.awt.Dimension(37, 35));
        btnMashDesign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMashDesignActionPerformed(evt);
            }
        });
        toolbar.add(btnMashDesign);

        btnSaveAll4.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/hydrometer.png"))); // NOI18N
        btnSaveAll4.setToolTipText("Lettura Densità");
        btnSaveAll4.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll4.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll4.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll4ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll4);

        btnSaveAll5.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/alcoholometer.png"))); // NOI18N
        btnSaveAll5.setToolTipText("Calcolo alcool");
        btnSaveAll5.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll5.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll5.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll5ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll5);

        btnSaveAll7.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/manometer.png"))); // NOI18N
        btnSaveAll7.setToolTipText("Calcolo carbonazione");
        btnSaveAll7.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll7.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll7.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll7ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll7);

        btnSaveAll8.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/temp.png"))); // NOI18N
        btnSaveAll8.setToolTipText("Temperatura mash in");
        btnSaveAll8.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll8.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll8.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll8ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll8);

        btnSaveAll9.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/controflusso.png"))); // NOI18N
        btnSaveAll9.setToolTipText("Dimensionamento controflusso");
        btnSaveAll9.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll9.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll9.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll9ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll9);

        btnSaveAll11.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/bubbles.png"))); // NOI18N
        btnSaveAll11.setToolTipText("Yeast pitcher - calcolo inoculo");
        btnSaveAll11.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll11.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll11.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll11ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll11);

        btnSaveAll14.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/water.png"))); // NOI18N
        btnSaveAll14.setToolTipText("Trattamento Acqua");
        btnSaveAll14.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll14.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll14.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll14ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll14);

        btnSaveAll17.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/pipe.png"))); // NOI18N
        btnSaveAll17.setToolTipText("Acqua necessaria");
        btnSaveAll17.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll17.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll17.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll17ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll17);

        btnSaveAll15.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/drill.png"))); // NOI18N
        btnSaveAll15.setToolTipText("Calcolo fori filtro");
        btnSaveAll15.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll15.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll15.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll15ActionPerformed(evt);
            }
        });
        toolbar.add(btnSaveAll15);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setOrientation(SwingConstants.VERTICAL);
        toolbar.add(separator_2);
        btnSaveAll20 = new javax.swing.JButton();
        
                btnSaveAll20.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/hops.png"))); // NOI18N
                btnSaveAll20.setToolTipText("Scheda luppoli");
                 
                 btnSaveAll20.setMaximumSize(new java.awt.Dimension(37, 35));
                 btnSaveAll20.setMinimumSize(new java.awt.Dimension(37, 35));
                 btnSaveAll20.setPreferredSize(new java.awt.Dimension(37, 35));
                 btnSaveAll20.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                         btnSaveAll20ActionPerformed(evt);
                     }
                 });
                 btnSaveAll18 = new javax.swing.JButton();
                 
                         btnSaveAll18.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/bjcp.png"))); // NOI18N
                         btnSaveAll18.setToolTipText("Stili e categorie");
                         
                         btnSaveAll18.setMaximumSize(new java.awt.Dimension(37, 35));
                         btnSaveAll18.setMinimumSize(new java.awt.Dimension(37, 35));
                         btnSaveAll18.setPreferredSize(new java.awt.Dimension(37, 35));
                         btnSaveAll18.addActionListener(new java.awt.event.ActionListener() {
                             public void actionPerformed(java.awt.event.ActionEvent evt) {
                                 btnSaveAll18ActionPerformed(evt);
                             }
                         });
                         toolbar.add(btnSaveAll18);
                 toolbar.add(btnSaveAll20);
        
        button = new JButton();
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gotoForum();
        	}
        });
        
        button.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/forum1.png")));
        button.setToolTipText("Birra Birra feed");
        button.setPreferredSize(new Dimension(37, 35));
        button.setMinimumSize(new Dimension(37, 35));
        button.setMaximumSize(new Dimension(37, 35));
        toolbar.add(button);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        toolbar.add(jSeparator2);

        btnUpdate.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/update.png"))); // NOI18N
        btnUpdate.setToolTipText("Aggiornamento disponibile");
        btnUpdate.setMaximumSize(new java.awt.Dimension(37, 35));
        btnUpdate.setMinimumSize(new java.awt.Dimension(37, 35));
        btnUpdate.setPreferredSize(new java.awt.Dimension(37, 35));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        btnSaveAll16 = new javax.swing.JButton();
        
                btnSaveAll16.setFont(btnSaveAll16.getFont());
                btnSaveAll16.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/configure.png"))); // NOI18N
                btnSaveAll16.setToolTipText("Impostazioni");
                btnSaveAll16.setMaximumSize(new java.awt.Dimension(37, 35));
                btnSaveAll16.setMinimumSize(new java.awt.Dimension(37, 35));
                btnSaveAll16.setPreferredSize(new java.awt.Dimension(37, 35));
                btnSaveAll16.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnSaveAll16ActionPerformed(evt);
                    }
                });
                toolbar.add(btnSaveAll16);
        
        btnGuida = new JButton();
        btnGuida.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ShowHelp();
        	}
        });
        btnGuida.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/help.png")));
        btnGuida.setToolTipText("Help");
        btnGuida.setPreferredSize(new Dimension(37, 35));
        btnGuida.setMinimumSize(new Dimension(37, 35));
        btnGuida.setMaximumSize(new Dimension(37, 35));
        btnGuida.setFont(new Font("SansSerif", Font.PLAIN, 12));
        toolbar.add(btnGuida);
        toolbar.add(btnUpdate);

        getContentPane().add(toolbar, java.awt.BorderLayout.NORTH);

        sideBar.setFloatable(false);
        sideBar.setOrientation(1);

        btnNew1.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/kettle.png"))); // NOI18N
        btnNew1.setToolTipText("Registrazione cotte");
        btnNew1.setMaximumSize(new java.awt.Dimension(37, 35));
        btnNew1.setMinimumSize(new java.awt.Dimension(37, 35));
        btnNew1.setPreferredSize(new java.awt.Dimension(37, 35));
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });
        sideBar.add(btnNew1);

        btnSaveAll21.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/inventario.png"))); // NOI18N
        btnSaveAll21.setToolTipText("Inventario");
        btnSaveAll21.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll21.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll21.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll21ActionPerformed(evt);
            }
        });
        sideBar.add(btnSaveAll21);

        btnSaveAll12.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/carrello.png"))); // NOI18N
        btnSaveAll12.setToolTipText("Acquisti");
        btnSaveAll12.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll12.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll12.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll12ActionPerformed(evt);
            }
        });
        sideBar.add(btnSaveAll12);

        btnSaveAll13.setIcon(new ImageIcon(Gui.class.getResource("/jmash/images/calendario.png"))); // NOI18N
        btnSaveAll13.setToolTipText("Calendario e pianificazione");
        btnSaveAll13.setMaximumSize(new java.awt.Dimension(37, 35));
        btnSaveAll13.setMinimumSize(new java.awt.Dimension(37, 35));
        btnSaveAll13.setPreferredSize(new java.awt.Dimension(37, 35));
        btnSaveAll13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAll13ActionPerformed(evt);
            }
        });
        sideBar.add(btnSaveAll13);

        getContentPane().add(sideBar, java.awt.BorderLayout.WEST);

        taskBar.setFloatable(false);

        getContentPane().add(taskBar, java.awt.BorderLayout.SOUTH);
        
        lblStatus = new JLabel("");
        lblStatus.setBorder(new EmptyBorder(0, 0, 0, 0));
        lblStatus.setBackground(Color.WHITE);
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblStatus.setForeground(Color.BLACK);
        taskBar.add(lblStatus);

        mnuFile.setText("File");
        mnuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileActionPerformed(evt);
            }
        });

        mnuNuova.setText("Nuova");
        mnuNuova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuovaActionPerformed(evt);
            }
        });
        mnuFile.add(mnuNuova);

        mnuFile1.setText("Ricette recenti");
        mnuFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFile1ActionPerformed(evt);
            }
        });
        
        mntmApriRicetta = new JMenuItem("Apri ricetta..");
        mntmApriRicetta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		File file=Utils.pickFileToLoad(new JInternalFrame(), (String)Main.getFromCache("recipe.dir",Main.recipeDir));
        		if(file!=null) {
        		    addLastOpenedFile(file);
        		    nuovaRicetta(new Ricetta(file));
        		}
        	}
        });
        mnuFile.add(mntmApriRicetta);
        mnuFile.add(mnuFile1);
        
        menuItemDelete = new JMenuItem("Elimina elenco..");
        menuItemDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteLastRecipesEntries();
        	}
        });
        menuItemDelete.setSelected(true);
        mnuFile1.add(menuItemDelete);
        
        separator_5 = new JSeparator();
        mnuFile1.add(separator_5);

        mnuFromPromash.setText("Incolla dalla clipboard");
        mnuFromPromash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFromPromashActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFromPromash);

        mnuBeerXML.setText("Importa BeerXML");
        mnuBeerXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBeerXMLActionPerformed(evt);
            }
        });
        mnuFile.add(mnuBeerXML);
        
        JMenuItem mntmIncollaDaPromash = new JMenuItem("Incolla da ProMash");
        mntmIncollaDaPromash.setVisible(false);
        mntmIncollaDaPromash.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fromProMash();
        	}
        });
        mnuFile.add(mntmIncollaDaPromash);

        mnuEsci.setText("Esci");
        mnuEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEsciActionPerformed(evt);
            }
        });
        mnuFile.add(mnuEsci);

        jMenuBar1.add(mnuFile);

        jMenu2.setText("Configurazione");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        mnuLuppoliXML.setText("Luppoli ed erbe");
        mnuLuppoliXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLuppoliXMLActionPerformed(evt);
            }
        });
        jMenu2.add(mnuLuppoliXML);

        mnuMaltiXML.setText("Malti, estratti, zuccheri");
        mnuMaltiXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMaltiXMLActionPerformed(evt);
            }
        });
        jMenu2.add(mnuMaltiXML);

        mnuLievitiXML.setLabel("Lieviti e fermenti");
        mnuLievitiXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLievitiXMLActionPerformed(evt);
            }
        });
        jMenu2.add(mnuLievitiXML);

        mnuAcquaXML.setText("Acque del mondo");
        mnuAcquaXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAcquaXMLActionPerformed(evt);
            }
        });
        
        JMenu mnRisorse = new JMenu("Risorse");
        jMenuBar1.add(mnRisorse);
        mnuStiliXML = new javax.swing.JMenuItem();
        mnRisorse.add(mnuStiliXML);
        
                mnuStiliXML.setText("Stili e categorie");
                
                JMenuItem mntmSchedaLuppoli = new JMenuItem("Scheda luppoli");
                mntmSchedaLuppoli.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		mostraSchedaluppoli();
                	}
                });
                mnRisorse.add(mntmSchedaLuppoli);
                
                JMenuItem mntmForumBirraBirra = new JMenuItem("Birra Birra feed");
                mntmForumBirraBirra.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		gotoForum();
                	}
                });
                mnRisorse.add(mntmForumBirraBirra);
                mnuStiliXML.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuStiliXMLActionPerformed(evt);
                    }
                });
                
                
        jMenu2.add(mnuAcquaXML);

        jMenuBar1.add(jMenu2);
        
        JSeparator separator_1 = new JSeparator();
        jMenu2.add(separator_1);
        
        JMenuItem mntmImpostazioni = new JMenuItem("Impostazioni");
        mntmImpostazioni.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addFrame(new ConfigurationTool());
        	}
        });
        jMenu2.add(mntmImpostazioni);

        jMenu1.setText("Finestre");

        ChiudiTutteMenuItem.setText("Chiudi Tutte");
        ChiudiTutteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChiudiTutteMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(ChiudiTutteMenuItem);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("?");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        mnuAbout = new javax.swing.JMenuItem();
        
                mnuAbout.setText("Info...");
                mnuAbout.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuAboutActionPerformed(evt);
                    }
                });
                
                mntmNewMenuItem = new JMenuItem("Help");
                mntmNewMenuItem.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		ShowHelp();
                	}
                });
                jMenu3.add(mntmNewMenuItem);
                
                JSeparator separator_3 = new JSeparator();
                jMenu3.add(separator_3);
                mnuUpdate = new javax.swing.JMenuItem();
                jMenu3.add(mnuUpdate);
                
                        mnuUpdate.setText("Controllo aggiornamenti");
                        mnuUpdate.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mnuUpdateActionPerformed(evt);
                            }
                        });
                
                JSeparator separator_4 = new JSeparator();
                jMenu3.add(separator_4);
                jMenu3.add(mnuAbout);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1121)/2, (screenSize.height-600)/2, 1121, 600);
    }// </editor-fold>//GEN-END:initComponents
    
    private void mnuBeerXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBeerXMLActionPerformed
	BeerXMLReader.main(null);
    }//GEN-LAST:event_mnuBeerXMLActionPerformed
    
    private void btnOpenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenMouseReleased
	if(evt.isPopupTrigger()){
	    recipesPopup.show(evt.getComponent(),evt.getX(),evt.getY());
	}
    }//GEN-LAST:event_btnOpenMouseReleased
    
    private void btnOpenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenMousePressed
	if(evt.isPopupTrigger()){
	    recipesPopup.show(evt.getComponent(),evt.getX(),evt.getY());
	}
    }//GEN-LAST:event_btnOpenMousePressed
    
    private void desktopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseReleased
	if(evt.isPopupTrigger()|| evt.getClickCount()>1){
	    recipesPopup.show(evt.getComponent(),evt.getX(),evt.getY());
	}
    }//GEN-LAST:event_desktopMouseReleased
    
    private void desktopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMousePressed
	if(evt.isPopupTrigger() || evt.getClickCount()>1){
	    recipesPopup.show(evt.getComponent(),evt.getX(),evt.getY());
	}
    }//GEN-LAST:event_desktopMousePressed
    
    private void desktopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseClicked
	
    }//GEN-LAST:event_desktopMouseClicked
    
    private void mnuFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFile1ActionPerformed

    }//GEN-LAST:event_mnuFile1ActionPerformed
    
    private void btnOpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenMouseClicked
	
    }//GEN-LAST:event_btnOpenMouseClicked
    
    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
    	frmCredits fc= new frmCredits();
    	addFrame(fc);
    	Dimension desktopSize = desktopPane.getSize();
    	Dimension jInternalFrameSize = fc.getSize();
    	fc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
    	    (desktopSize.height- jInternalFrameSize.height)/2);
    }//GEN-LAST:event_mnuAboutActionPerformed
    
    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed
    
    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
	JInternalFrame cotta = new NewCotta();
        addFrame(cotta);
        try {
            cotta.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNew1ActionPerformed
    
    private void btnSaveAll21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll21ActionPerformed
	addFrame(Acquisto.buildFRMInventario());
    }//GEN-LAST:event_btnSaveAll21ActionPerformed
    
    private void btnSaveAll20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll20ActionPerformed
	//addFrame(new BrowseTastyBrew());
    	mostraSchedaluppoli();
    }//GEN-LAST:event_btnSaveAll20ActionPerformed
    
    private void mnuAcquaXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAcquaXMLActionPerformed
	GenericTableModel tableModel = new XmlAbleTableModel(new WaterProfile());
	tableModel.setRows(Gui.waterPickerTableModel.getRows());
	try {
	    addFrame(new XmlAbleEditor(tableModel, WaterProfile.class, Main.waterXML, Main.class.getMethod("readWater")));
	} catch (SecurityException ex) {
	    ex.printStackTrace();
	} catch (NoSuchMethodException ex) {
	    ex.printStackTrace();
	}
    }//GEN-LAST:event_mnuAcquaXMLActionPerformed
    private void mostraSchedaluppoli()
    {
    	frmBrowser brow = new frmBrowser("http://brewplus.t15.org/brewplus/docs/Hops/Hops.html","Scheda luppoli",false);
    	addFrame(brow,true);
    }
    
    private void btnSaveAll18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll18ActionPerformed
    	//frmBrowser brow = new frmBrowser("http://www.bjcp.org/2008styles/catdex.php","BJCP",false);
    	frmBrowseBJCP brow=new frmBrowseBJCP();
    	addFrame(brow,true);
    }//GEN-LAST:event_btnSaveAll18ActionPerformed
    
    private void fromProMash()
    {
    	try {
    	    newRecipeFromPromashClipboard();
    	} catch (Exception ex) {
    	    //Utils.showException(ex,"Impossibile riconoscere una ricetta di promash nella clipboard.");
    	    RecipeData data=new RecipeData();
    	    Ricetta R=new Ricetta();
    	    try {
    		File file=Utils.pickFileToLoad(R,(String)Main.getFromCache("promash.dir",Main.recipeDir),"rec");
    		if(file!=null){
    		    Main.putIntoCache("promash.dir",file.getAbsolutePath());
    		    data.readRec(file.toString());
    		    R.fromRecipeData(data);
    		    addFrame(R, true);
    		} else{
    		    R.setClosed(true);
    		}
    	    } catch (Exception ex2) {
    		Utils.showException(ex2,"Impossibile riconoscere una ricetta di promash.");
    	    }
    	}
    }
    
    private void mnuFromPromashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFromPromashActionPerformed
	
	String str=Utils.getClipboard();
	try{
	    nuovaRicetta(new Ricetta(XMLReader.XMLfromString(str), null));
	} catch (Exception ex2) {
	    Utils.showException(ex2,"Impossibile riconoscere una ricetta di HobbyBrew.");
	}
    }//GEN-LAST:event_mnuFromPromashActionPerformed
    public void newRecipeFromPromashClipboard() throws Exception{
	String str=Utils.getClipboard();
	int i,e;
	i=str.indexOf("Grain/Extract/Sugar");
	i=1+str.indexOf("-----------------------------------------------------------------------------",i)+"-----------------------------------------------------------------------------".length();
	e=str.indexOf("Potential represented as SG per pound per gallon.");
	String malts=str.substring(i,e);
	
	String s[]=malts.split("\n");
	List<Malt> ms=new ArrayList<Malt>();
	List<Hop> hs=new ArrayList<Hop>();
	for (int j = 0; j < s.length; j++) {
	    Malt m1=new Malt();
	    m1.setNome(s[j].substring(20,50).trim());
	    
	    if(str.indexOf("   %     Amount     Name                          Origin        Potential SRM")>=0)
		m1.setEbc(Utils.srmToEbc( new Double(s[j].substring(70).trim())));
	    else if(str.indexOf("   %     Amount     Name                          Origin        Potential EBC")>=0)
		m1.setEbc(new Double(s[j].substring(70).trim()));
	    m1.setPotentialSG(new Double(s[j].substring(65,70).trim()));
	    if(m1.getNome().indexOf("LME")>=0)m1.setForma("Estratto liquido");
	    else if(m1.getNome().indexOf("DME")>=0)m1.setForma("Estratto secco");
	    else if(m1.getNome().toLowerCase().indexOf("flaked")>=0)m1.setForma("Fiocchi");
	    if(s[j].substring(5,20).indexOf("kg.")>=0)
		m1.setGrammi(new Double(s[j].substring(5,20).replaceFirst("kg.","").trim()).doubleValue()*1000);
	    if(s[j].substring(5,20).indexOf("lbs.")>=0)
		m1.setGrammi(Utils.poundToKg(new Double(s[j].substring(5,20).replaceFirst("lbs.","").trim()).doubleValue()*1000));
	    ms.add(m1);
	}
	
	i=str.indexOf("   Amount     Name                              Form    Alpha  IBU  Boil Time");
	i=1+str.indexOf("-----------------------------------------------------------------------------",i)+"-----------------------------------------------------------------------------".length();
	e=str.indexOf("Extras");
	if(e<0)e=str.indexOf("Yeast");
	String hops=str.substring(i,e);
	s=hops.split("\n");
	for (int j = 0; j < s.length; j++) {
	    Hop h1=new Hop();
	    h1.setNome(s[j].substring(14,48).trim());
	    
	    if(s[j].substring(0,14).indexOf("g.")>=0)
		h1.setGrammi(new Double(s[j].substring(0,7).trim()));
	    if(s[j].substring(0,14).indexOf("oz.")>=0)
		h1.setGrammi(Utils.ouncesToGrams(new Double(s[j].substring(0,7).trim())));
	    
	    h1.setAlfaAcidi(new Double(s[j].substring(54,63).trim()));
	    h1.setAlfaAcidiPrec(new Double(s[j].substring(54,63).trim()));
	    
	    if(s[j].substring(69).indexOf("Dry Hop")>=0){
		h1.setBoilTime(0);
		h1.setUso("Dry");
	    } else{
		try {
		    h1.setBoilTime(new Double(s[j].substring(69).replace("min.","").trim()).intValue());
		} catch (NumberFormatException ex) {
		    h1.setBoilTime(0);
		    ex.printStackTrace();
		}
	    }
	    hs.add(h1);
	}
	RecipeData rd=new RecipeData();
	
	String bV=str.substring(str.indexOf("Pre-Boil Wort Size:")+"Pre-Boil Wort Size:".length(),str.indexOf("Pre-Boil Gravity:")).trim();
	String wbt=str.substring(str.indexOf("Wort Boil Time:")+"Wort Boil Time:".length(),str.indexOf("Minutes")).trim();
	
	
	String eff=str.substring(str.indexOf("Brewhouse Efficiency:")+"Brewhouse Efficiency:".length(),str.indexOf("Wort Boil Time:")).replace("%","").trim();
	rd.setEfficienza(new Integer(eff));
	if(str.indexOf("Batch Size (L):")>0){
	    String bs=str.substring(str.indexOf("Batch Size (L):")+"Batch Size (L):".length(),str.indexOf("Wort Size (L):")).trim();
	    rd.setVolumeFin(new Double(bs));
	}
	if(str.indexOf("Batch Size (Gal):")>0){
	    String bs=str.substring(str.indexOf("Batch Size (Gal):")+"Batch Size (Gal):".length(),str.indexOf("Wort Size (Gal):")).trim();
	    rd.setVolumeFin(Utils.galToLit(new Double(bs)));
	}
	
	rd.setBollitura(new Integer(wbt));
	if(bV.indexOf("L")>=0){
	    rd.setVolumeBoll(new Double(bV.replaceFirst("L","").trim()));
	}
	if(bV.indexOf("Gal")>=0){
	    rd.setVolumeBoll(Utils.galToLit(new Double(bV.replaceFirst("Gal","").trim())));
	}
	rd.setNome(str.substring(0,str.indexOf("A ProMash Recipe Report")).trim());
	rd.setMalts(ms);
	rd.setHops(hs);
	rd.setYeasts(new ArrayList<Yeast>());
	Ricetta r=new Ricetta();
	r.fromRecipeData(rd);
	addFrame(r, true);
    }
    private void mnuLievitiXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLievitiXMLActionPerformed
	GenericTableModel tableModel = new XmlAbleTableModel(new YeastType());
	tableModel.setRows(Gui.yeastPickerTableModel.getRows());
	try {
	    addFrame(new XmlAbleEditor(tableModel, YeastType.class, Main.yeastXML, Main.class.getMethod("readLieviti")));
	} catch (SecurityException ex) {
	    ex.printStackTrace();
	} catch (NoSuchMethodException ex) {
	    ex.printStackTrace();
	}
    }//GEN-LAST:event_mnuLievitiXMLActionPerformed
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
	updateProgram();
    }//GEN-LAST:event_btnUpdateActionPerformed
    
    private void btnSaveAll17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll17ActionPerformed
	addFrame(new WaterNeeded());
    }//GEN-LAST:event_btnSaveAll17ActionPerformed
    
    private void btnSaveAll16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll16ActionPerformed
	addFrame(new ConfigurationTool());
    }//GEN-LAST:event_btnSaveAll16ActionPerformed
    
    private void btnSaveAll15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll15ActionPerformed
	addFrame(new CalcoloForiZapap());
    }//GEN-LAST:event_btnSaveAll15ActionPerformed
    
    private void btnSaveAll14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll14ActionPerformed
	addFrame(new WaterDesign(), true);
    }//GEN-LAST:event_btnSaveAll14ActionPerformed
    
    private void btnSaveAll13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll13ActionPerformed
	//addFrame(new Planner());
    }//GEN-LAST:event_btnSaveAll13ActionPerformed
    
    private void btnSaveAll12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll12ActionPerformed
	addFrame(Acquisto.newAcquisto());
    }//GEN-LAST:event_btnSaveAll12ActionPerformed
    
    private void btnSaveAll11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll11ActionPerformed
	addFrame(new YeastPitching());
    }//GEN-LAST:event_btnSaveAll11ActionPerformed
    
    private void btnSaveAll9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll9ActionPerformed
	addFrame(new DimensionamentoControflusso(), true);
    }//GEN-LAST:event_btnSaveAll9ActionPerformed
    
    private void btnSaveAll8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll8ActionPerformed
	addFrame(new StrikeTemp());
    }//GEN-LAST:event_btnSaveAll8ActionPerformed
    
    private void btnSaveAll7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll7ActionPerformed
	addFrame(new CarbonationTool(), true);
    }//GEN-LAST:event_btnSaveAll7ActionPerformed
    
    private void btnSaveAll5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll5ActionPerformed
	addFrame(new AlcoolContent());
    }//GEN-LAST:event_btnSaveAll5ActionPerformed
    
    private void btnSaveAll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll4ActionPerformed
	addFrame(new CorrezioneDensimetro());
    }//GEN-LAST:event_btnSaveAll4ActionPerformed
    
    private void btnMashDesignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMashDesignActionPerformed
	addFrame(new MashDesign());
    }//GEN-LAST:event_btnMashDesignActionPerformed
    
    private void btnSaveAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll2ActionPerformed
	addFrame(new EvaporationForm());
    }//GEN-LAST:event_btnSaveAll2ActionPerformed
    
    private void btnSaveAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAll1ActionPerformed
	addFrame(new DiluitionForm());
    }//GEN-LAST:event_btnSaveAll1ActionPerformed
    private void updateProgram(){
	try {
	    Utils.copyFile("runner.jar","_runner.jar");
	} catch (IOException ex) {
	    Utils.showException(ex,"Impossibile eseguire l'update: non è stato possibile creare il file _runner.jar");
	    return;
	}
	try {
	    String remoteRoot=Main.config.getRemoteRoot();
	    if(remoteRoot==null)remoteRoot="http://brewplus.t15.org/brewplus";
	    if(!remoteRoot.startsWith("http://"))remoteRoot="http://"+remoteRoot;
	    if(!remoteRoot.endsWith("/"))remoteRoot+="/";
	    
	    //test con classe locale per debug IXTLANAS
	    //String[] address= {remoteRoot};
		//runner.Main.main(address);
	    Process p=Runtime.getRuntime().exec("java -jar _runner.jar "+remoteRoot);
	    chiudiGui();
	} catch (Exception ex) {
	    Utils.showException(ex,"Impossibile lanciare l'update.");
	}
    }
    
    private void mnuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateActionPerformed
    	updateProgram();
	
    }//GEN-LAST:event_mnuUpdateActionPerformed
    public void setStatusBar(String T){
	lblStatus.setText(T);
    }
    private void mnuStiliXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuStiliXMLActionPerformed
    	frmBrowseBJCP brow = new frmBrowseBJCP();
    	addFrame(brow,true);
	    }
    	
    private void btnSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAsActionPerformed
	if(this.desktop.getSelectedFrame() instanceof Ricetta ){
	    Ricetta ed=(Ricetta)this.desktop.getSelectedFrame();
	    ed.nullFile();
	    File file=ed.saveRicetta();
	    Main.putIntoCache("recipe.dir",file.getAbsolutePath());
	    addLastOpenedFile(file);
	}
    }//GEN-LAST:event_btnSaveAsActionPerformed
    
    
    public static List<String> lastOpenedRecipes=new ArrayList<String>();
    public void addLastOpenedFile(File file){
		if(file!=null) {
		    Main.putIntoCache("recipe.dir",file.getAbsolutePath());
		    boolean flag=true;
		    for(String s: lastOpenedRecipes){
		    	if(s.equals(file.getAbsolutePath())){flag=false;break;}
		    }
		    if(flag) lastOpenedRecipes.add(0,file.getAbsolutePath());
		    if(lastOpenedRecipes.size()>20){
		    	lastOpenedRecipes.remove(lastOpenedRecipes.size()-1);
		    }
		    updatePopupMenu();
		}
    }
    
    
    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
	File file=Utils.pickFileToLoad(new JInternalFrame(), (String)Main.getFromCache("recipe.dir",Main.recipeDir));
	if(file!=null) {
	    addLastOpenedFile(file);
	    nuovaRicetta(new Ricetta(file));
	}
    }//GEN-LAST:event_btnOpenActionPerformed
    
    private void mnuMaltiXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMaltiXMLActionPerformed
	GenericTableModel tableModel = new XmlAbleTableModel(new MaltType());
	Gui.maltPickerTableModel.setFilterOff();
	tableModel.setRows(Gui.maltPickerTableModel.getRows());
	try {
	    addFrame(new XmlAbleEditor(tableModel, MaltType.class, Main.maltiXML, Main.class.getMethod("readMalti")));
	} catch (SecurityException ex) {
	    ex.printStackTrace();
	} catch (NoSuchMethodException ex) {
	    ex.printStackTrace();
	}
    }//GEN-LAST:event_mnuMaltiXMLActionPerformed
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
		if(this.desktop.getSelectedFrame() instanceof XmlEditor ){
		    XmlEditor ed=(XmlEditor)this.desktop.getSelectedFrame();
		    ed.saveFile();
		}
		if(this.desktop.getSelectedFrame() instanceof Ricetta ){
		    Ricetta ed=(Ricetta)this.desktop.getSelectedFrame();
		    ed.saveRicetta();
		}
		if(this.desktop.getSelectedFrame() instanceof NewCotta ){
		    NewCotta ed=(NewCotta)this.desktop.getSelectedFrame();
		    ed.save();
		}
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
	nuovaRicetta(new Ricetta());
    }//GEN-LAST:event_btnNewActionPerformed
    
    private void mnuLuppoliXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLuppoliXMLActionPerformed
	GenericTableModel tableModel = new XmlAbleTableModel(new HopType());
	tableModel.setRows(Gui.hopPickerTableModel.getRows());
	try {
	    addFrame(new XmlAbleEditor(tableModel, HopType.class,Main.luppoliXML, Main.class.getMethod("readLuppoli")));
	} catch (SecurityException ex) {
	    ex.printStackTrace();
	} catch (NoSuchMethodException ex) {
	    ex.printStackTrace();
	}
    }//GEN-LAST:event_mnuLuppoliXMLActionPerformed
    
    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
	
    }//GEN-LAST:event_jMenu2ActionPerformed
    
    //private List<Ricetta> ricette=new ArrayList<Ricetta>();
    private void mnuNuovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuovaActionPerformed
	nuovaRicetta(new Ricetta());
    }//GEN-LAST:event_mnuNuovaActionPerformed
    
    private void mnuEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEsciActionPerformed
	chiudiGui();
    }//GEN-LAST:event_mnuEsciActionPerformed
    
    private void mnuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileActionPerformed
								                        	            }//GEN-LAST:event_mnuFileActionPerformed
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
	chiudiGui();
    }//GEN-LAST:event_formWindowClosing

    private void desktopComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_desktopComponentAdded

        if (!listaFinestre.contains((JInternalFrame)evt.getChild())) {
            listaFinestre.add((JInternalFrame)evt.getChild());

            if (evt.getChild() != null) {
                JMenuItem item = new JMenuItem();
                JInternalFrame child = (JInternalFrame)evt.getChild();
                if (child.getTitle().length() > 0) { //inserito questo controllo perchè viene generato l'evento a caso quando aggiungo una ricetta
                    item.setText(child.getTitle());
                    item.setActionCommand(child.getTitle());
                    item.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            JMenuItem item = (JMenuItem)evt.getSource();
                            JPopupMenu popmenu = (JPopupMenu)item.getParent();
                            javax.swing.JMenu menu = (javax.swing.JMenu)popmenu.getInvoker();

                            Gui mdi = (Gui)menu.getTopLevelAncestor();

                            if (mdi.Finestre.containsKey(item)) {
                                try {
                                    if (mdi.Finestre.get(item).isIcon()) {
                                        mdi.Finestre.get(item).setIcon(false);
                                    }
                                    mdi.Finestre.get(item).setSelected(true);
                                } catch (PropertyVetoException ex) {
                                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });

                    jMenu1.add(item);
                    Finestre.put(item, child);
                }
            }
            
        }

        this.desktop.repaint();
    }//GEN-LAST:event_desktopComponentAdded

    private void deleteLastRecipesEntries()
    {
    	lastOpenedRecipes.clear();
		for(int x=0;x<500;x++)
		{
			Main.removeFromCache("Main.recipe"+x);
		}
		updatePopupMenu();
    }
    
    private void gotoForum()
    {
    	frmFeed2 ffedd=new frmFeed2();
		addFrame(ffedd);
    }
    private void ShowHelp()
    {
    	try
    	{
    		new Utils.BareBonesBrowserLaunch().openURL("file:///" + Main.userDir+"/help/index.html");
    	}
    	catch (Exception ex)
    	{
    		//Utils.showException(ex,"",this);
    	}
    }
    
    private void desktopComponentRemoved(java.awt.event.ContainerEvent evt)
    {
        for (int i=0; i<=jMenu1.getItemCount() -1; i++) {
            if (jMenu1.getItem(i) != null) {
                if (jMenu1.getItem(i).getText().equalsIgnoreCase(((JInternalFrame)evt.getChild()).getTitle())) {
                    jMenu1.remove(i);
                    break;
                }
            }
        }
        if (evt.getChild() != null) {
            if (evt.getChild() instanceof JInternalFrame) {
                if (((JInternalFrame)evt.getChild()).isClosed()) {

                    JMenuItem item = new JMenuItem();
                    for(Map.Entry<JMenuItem, JInternalFrame> e: Finestre.entrySet()) {
                        if (e.getValue().equals((JInternalFrame)evt.getChild())) {
                            item = e.getKey();
                            for (int i=0; i<=jMenu1.getItemCount() -1; i++) {
                                if (jMenu1.getItem(i) != null) {
                                    if (jMenu1.getItem(i).equals(e.getKey())) {
                                        jMenu1.remove(i);
                                        listaFinestre.remove(e.getValue());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    Finestre.remove(item);
                }
            }
            this.desktop.repaint();
        }
        
    }//GEN-LAST:event_desktopComponentRemoved

    private void ChiudiTutteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChiudiTutteMenuItemActionPerformed
        JInternalFrame[] frames = this.desktop.getAllFrames();
        int l = frames.length;
        try {
            for (int i=0; i < l; i++) {
                if (frames[i].isIcon()) {
                    frames[i].setIcon(false);
                }
                frames[i].setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_ChiudiTutteMenuItemActionPerformed
    
    private void chiudiGui(){
	// if(ricette.size()==0)
	
	Main.putIntoCache("Main.utilizzo", Utils.utilizzo() );
	
	
	
	Main.saveCache();
	
	System.exit(0);
    }
    private int n=1,m=0;
    public void nuovaRicetta(Ricetta r){
	
	if(r!=null){
	    Utils.utilizzo(r);
	    r.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
	    this.desktop.add(r);
	    r.setLocation(this.n*10, this.n*10);
	    this.n++;
	    if(this.n>20) {
		this.n=++this.m;
	    }
	    try {
		r.setSelected(true);
                r.setMaximum(true);
	    } catch (java.beans.PropertyVetoException e) {e.printStackTrace();}
	}
    }
    public void addFrame(JInternalFrame r){
	if(r!=null){
	    Utils.utilizzo(r);
	    this.desktop.add(r);
	    r.setLocation(this.n*10, this.n*10);
	    this.n++;
	    if(this.n>20) {
		this.n=++this.m;
	    }
	    r.setVisible(true);
	    try {
                r.setSelected(true);
	    } catch (java.beans.PropertyVetoException e) {e.printStackTrace();}
	}
    }
    
    public void addFrame(JInternalFrame r, boolean maximum){
	if(r!=null){
	    Utils.utilizzo(r);
	    this.desktop.add(r);
	    r.setLocation(this.n*10, this.n*10);
	    this.n++;
	    if(this.n>20) {
		this.n=++this.m;
	    }
	    r.setVisible(true);

	    //r.updateUI();
	    try {
                r.setSelected(true);
                r.setMaximum(maximum);
	    } catch (java.beans.PropertyVetoException e) {e.printStackTrace();}
	}
    }
//    private void chiudiRicetta(){
//        Ricetta r=null;
//        if(desktop.getSelectedFrame() instanceof Ricetta ){
//            r=(Ricetta)desktop.getSelectedFrame();
//            if(r!=null){
//                chiudiRicetta(r);
//                r.dispose();
//
//                if(ricette.size()>0){
//                    r=ricette.get(ricette.size()-1);
//                    try {
//                        r.setSelected(true);
//                    } catch (java.beans.PropertyVetoException e) {e.printStackTrace();}
//                }
//            }
//        }
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ChiudiTutteMenuItem;
    private javax.swing.JButton btnMashDesign;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAll1;
    private javax.swing.JButton btnSaveAll11;
    private javax.swing.JButton btnSaveAll12;
    private javax.swing.JButton btnSaveAll13;
    private javax.swing.JButton btnSaveAll14;
    private javax.swing.JButton btnSaveAll15;
    private javax.swing.JButton btnSaveAll16;
    private javax.swing.JButton btnSaveAll17;
    private javax.swing.JButton btnSaveAll18;
    private javax.swing.JButton btnSaveAll2;
    private javax.swing.JButton btnSaveAll20;
    private javax.swing.JButton btnSaveAll21;
    private javax.swing.JButton btnSaveAll4;
    private javax.swing.JButton btnSaveAll5;
    private javax.swing.JButton btnSaveAll7;
    private javax.swing.JButton btnSaveAll8;
    private javax.swing.JButton btnSaveAll9;
    private javax.swing.JButton btnSaveAs;
    public javax.swing.JButton btnUpdate;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenuItem mnuAcquaXML;
    private javax.swing.JMenuItem mnuBeerXML;
    private javax.swing.JMenuItem mnuEsci;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuFile1;
    private javax.swing.JMenuItem mnuFromPromash;
    private javax.swing.JMenuItem mnuLievitiXML;
    private javax.swing.JMenuItem mnuLuppoliXML;
    private javax.swing.JMenuItem mnuMaltiXML;
    private javax.swing.JMenuItem mnuNuova;
    private javax.swing.JMenuItem mnuStiliXML;
    private javax.swing.JMenuItem mnuUpdate;
    private javax.swing.JToolBar sideBar;
    private javax.swing.JToolBar taskBar;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
    public  static javax.swing.JDesktopPane desktopPane;
    private JButton button;
    private JMenuItem mntmApriRicetta;
    private JButton btnGuida;
    private JMenuItem mntmNewMenuItem;
    private JMenuItem menuItemDelete;
    private JSeparator separator_5;
    private JLabel lblStatus;
}

