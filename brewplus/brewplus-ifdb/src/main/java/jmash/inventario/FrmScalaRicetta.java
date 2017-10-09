/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.inventario;

import jmash.AcquistoIngredienti;
import java.awt.Cursor;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jmash.Hop;
import jmash.Malt;
import jmash.Quantita;
import jmash.Ricetta;
import jmash.TableSorter;
import jmash.Utils;
import jmash.Yeast;
import jmash.component.CheckBoxModelListener;
import jmash.utils.BrewplusEnvironment;
import jmash.utils.Constants;
import org.jdom.Document;

/**
 *
 * @author a.cerella
 */
public class FrmScalaRicetta extends javax.swing.JDialog {
    
    private AcquistoIngredienti inventario;
    private static final String FERMENTABILE = "FERMENTABILE";
    private static final String LUPPOLI = "LUPPOLI";
    private static final String LIEVITI = "LIEVITI";
    
    private static BrewplusEnvironment bpenv = BrewplusEnvironment.getIstance();
    /**
     * Creates new form FrmScalaRicetta
     * @param parent
     * @param modal
     */
    public FrmScalaRicetta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public FrmScalaRicetta(AcquistoIngredienti inventario, java.awt.Frame parent, boolean modal) {
        this(parent,modal);
        this.inventario = inventario;
        btnScalaInventario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tblScalaIngredienti.getModel().addTableModelListener(new CheckBoxModelListener());
    }
    
    public void loadFermentabili(TableSorter tblMalti) {
        for (int ii = 0; ii < tblMalti.getRowCount(); ii++) {
            Boolean esiste = false;
            for (Malt malto : inventario.getMalti()) {
                esiste = false;
                if (malto.getNome().equalsIgnoreCase((String)tblMalti.getValueAt(ii, 1)) && malto.getForma().equalsIgnoreCase((String)tblMalti.getValueAt(ii, 6))) {
                    ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{FERMENTABILE,tblMalti.getValueAt(ii, 1), tblMalti.getValueAt(ii, 6),Double.parseDouble(((Quantita)tblMalti.getValueAt(ii, 2)).getValue()),malto.getGrammi(),malto.getGrammi()-Double.parseDouble(((Quantita)tblMalti.getValueAt(ii, 2)).getValue()),false});
                    esiste = true;
                    break;
                }
            }
        if (!esiste) ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{FERMENTABILE,tblMalti.getValueAt(ii, 1), tblMalti.getValueAt(ii, 6), Double.parseDouble(((Quantita)tblMalti.getValueAt(ii, 2)).getValue()),0.0,0.0-Double.parseDouble(((Quantita)tblMalti.getValueAt(ii, 2)).getValue()),false});    
        } 
    }       
    
     public void loadLuppoli(TableSorter tblLuppoli) {
        DefaultTableModel modelScalaIngredienti = (DefaultTableModel)(tblScalaIngredienti.getModel());
        for (int ii = 0; ii < tblLuppoli.getRowCount(); ii++) {
            //Verifico se è gia presente nella tabella per scalare dall'inventario
            Boolean presente = false;
            for (int jj = 0; jj < tblScalaIngredienti.getRowCount(); jj++) {
                if (LUPPOLI.equalsIgnoreCase((String)modelScalaIngredienti.getValueAt(jj, 0)) && ((String)modelScalaIngredienti.getValueAt(jj, 1)).equalsIgnoreCase((String)tblLuppoli.getValueAt(ii, 1)) && ((String)modelScalaIngredienti.getValueAt(jj,2)).equalsIgnoreCase((String)tblLuppoli.getValueAt(ii, 4))) {
                    modelScalaIngredienti.setValueAt((Double)modelScalaIngredienti.getValueAt(jj, 3) + Double.parseDouble(((Quantita)tblLuppoli.getValueAt(ii, 2)).getValue()), jj, 3);
                    modelScalaIngredienti.setValueAt((Double)modelScalaIngredienti.getValueAt(jj, 4) - (Double)modelScalaIngredienti.getValueAt(jj,3), jj, 5);
                    presente = true;
                }
            }
            if (!presente) {
                    Boolean esiste = false;
                    for (Hop hop : inventario.getLuppoli()) {
                        esiste = false;
                        if (hop.getNome().equalsIgnoreCase((String)tblLuppoli.getValueAt(ii, 1)) && hop.getForma().equalsIgnoreCase((String)tblLuppoli.getValueAt(ii, 4))) {
                            ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{LUPPOLI,(String)tblLuppoli.getValueAt(ii, 1), (String)tblLuppoli.getValueAt(ii, 4),Double.parseDouble(((Quantita)tblLuppoli.getValueAt(ii, 2)).getValue()),hop.getGrammi(),hop.getGrammi()-Double.parseDouble(((Quantita)tblLuppoli.getValueAt(ii, 2)).getValue()),false});
                            esiste = true;
                            break;
                        }
                    }
                if (!esiste) ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{LUPPOLI,(String)tblLuppoli.getValueAt(ii, 1),(String)tblLuppoli.getValueAt(ii, 4), Double.parseDouble(((Quantita)tblLuppoli.getValueAt(ii, 2)).getValue()),0.0,0.0-Double.parseDouble(((Quantita)tblLuppoli.getValueAt(ii, 2)).getValue()),false});    
            }
        } 
    } 
     
      public void loadLieviti(TableSorter tblLieviti) {
        for (int ii = 0; ii < tblLieviti.getRowCount(); ii++) {
            Boolean esiste = false;
            for (Yeast yeast : inventario.getLieviti()) {
                esiste = false;
                if (yeast.getCodice().equalsIgnoreCase((String)tblLieviti.getValueAt(ii, 0))) {
                    ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{LIEVITI,tblLieviti.getValueAt(ii, 2), "",Double.parseDouble((String)tblLieviti.getValueAt(ii, 4)!=null?(String)tblLieviti.getValueAt(ii, 4):"0.0"),Double.parseDouble(yeast.getQuantita()),Double.parseDouble(yeast.getQuantita())-Double.parseDouble((String)tblLieviti.getValueAt(ii, 4)!=null?(String)tblLieviti.getValueAt(ii, 4):"0.0"),false});
                    esiste = true;
                    break;
                }
            }
        if (!esiste) ((DefaultTableModel)(tblScalaIngredienti.getModel())).addRow(new Object[]{LIEVITI,tblLieviti.getValueAt(ii, 2), "", Double.parseDouble((String)tblLieviti.getValueAt(ii, 4)!=null?(String)tblLieviti.getValueAt(ii, 4):"0.0"),0.0,0.0-Double.parseDouble((String)tblLieviti.getValueAt(ii, 4)!=null?(String)tblLieviti.getValueAt(ii, 4):"0.0"),false});    
        } 
    }     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblScalaIngredienti = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnScalaInventario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Scala Ingredienti");
        setMinimumSize(new java.awt.Dimension(681, 399));
        setModal(true);
        setResizable(false);

        tblScalaIngredienti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Nome", "Forma", "Necessario (gr)", "Disponibile (gr)", "Rimanenti (gr)", "Forza"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblScalaIngredienti);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnScalaInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/sacco.png"))); // NOI18N
        btnScalaInventario.setToolTipText("Scala da inventario");
        btnScalaInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScalaInventarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnScalaInventario)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnScalaInventario)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnScalaInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScalaInventarioActionPerformed
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Sei sicuro di voler scalare gli Ingredienti della ricetta dall'Inventario?", "Scala Ingredienti",JOptionPane.YES_NO_OPTION)) {
            if ((!Ricetta.isAlreadyDrop) || (Ricetta.isAlreadyDrop && ( JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Gli ingredienti della Ricetta risultano già scalati. Sei sicuro di continuare?", "Scala Ingredienti",JOptionPane.YES_NO_OPTION)))) {
                DefaultTableModel tableModel = (DefaultTableModel)(tblScalaIngredienti.getModel());
                for (int ii = 0; ii < tblScalaIngredienti.getRowCount(); ii++) {
                    if (((Double)tableModel.getValueAt(ii, 5)) < 0 && !(Boolean)tableModel.getValueAt(ii, 6)) {
                        JOptionPane.showMessageDialog(this, "Il " + tableModel.getValueAt(ii, 0) + " : [" + tableModel.getValueAt(ii, 1) + "] non è sufficiente. Per poter procedere devi \"Forzare\" l'operazione selezionando l'ingrediente.", "Scala Ingredienti", JOptionPane.WARNING_MESSAGE); 
                        return;
                    }
                }
                for (int ii = 0; ii < tblScalaIngredienti.getRowCount(); ii++) {
                    if (FERMENTABILE.equalsIgnoreCase((String)tableModel.getValueAt(ii, 0)))
                        for (Malt malto : inventario.getMalti()) 
                            if (malto.getNome().equalsIgnoreCase((String)tblScalaIngredienti.getValueAt(ii, 1)) && malto.getForma().equalsIgnoreCase((String)tblScalaIngredienti.getValueAt(ii, 2)))
                                  inventario.getMalti().get(inventario.getMalti().indexOf(malto)).setGrammi(((Double)tableModel.getValueAt(ii, 5)) > 0 ? (Double)tableModel.getValueAt(ii, 5) : 0);
                    if (LUPPOLI.equalsIgnoreCase((String)tableModel.getValueAt(ii, 0)))
                        for (Hop hop : inventario.getLuppoli()) 
                            if (hop.getNome().equalsIgnoreCase((String)tblScalaIngredienti.getValueAt(ii, 1)) && hop.getForma().equalsIgnoreCase((String)tblScalaIngredienti.getValueAt(ii, 2)))
                                  inventario.getLuppoli().get(inventario.getLuppoli().indexOf(hop)).setGrammi(((Double)tableModel.getValueAt(ii, 5)) > 0 ? (Double)tableModel.getValueAt(ii, 5) : 0);
                    if (LIEVITI.equalsIgnoreCase((String)tableModel.getValueAt(ii, 0)))
                        for (Yeast yeast : inventario.getLieviti()) 
                            if (yeast.getNome().equalsIgnoreCase((String)tblScalaIngredienti.getValueAt(ii, 1)))
                                  inventario.getLieviti().get(inventario.getLieviti().indexOf(yeast)).setQuantita((((Double)tableModel.getValueAt(ii, 5)) > 0 ? (Double)tableModel.getValueAt(ii, 5) : 0) + "");

                }
                Document doc = inventario.toXml();
                File file = new File(bpenv.getConfigfileName(Constants.XML_INVENTORY));
                Utils.saveXmlAsFile(doc, file, null);
                JOptionPane.showMessageDialog(this, "Gli Ingredienti sono stati scalati dall'inventario.", "Scala Ingredienti", JOptionPane.INFORMATION_MESSAGE); 
                Ricetta.isAlreadyDrop = true;
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnScalaInventarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnScalaInventario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblScalaIngredienti;
    // End of variables declaration//GEN-END:variables
}
