/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.tableModel;

import java.util.Date;
import javax.swing.JLabel;
import jmash.Main;
import jmash.Yeast;

/**
 *
 * @author a.cerella
 */
public class YeastBuyTableModel extends GenericTableModel<Yeast> {
    
    private JLabel ret = new JLabel("");
    
    public YeastBuyTableModel() {
        this.ret.setIcon(Main.clockIcon);
        this.columnNames = new String[] { "Codice", "Nome", "Quantità (gr)", "Data rif."};
    }
    
    @Override
    public Object getValueAt(int row, int col) {
            Yeast yeast = this.dataValues.get(row);
            this.ret.setIcon(Main.hopIcon);
            if (yeast != null) {
                    switch (col) {
                    case 0:
                            return yeast.getCodice();
                    case 1:
                            return yeast.getNome();
                    case 2:
                            return yeast.getQuantita();
                    case 3:
                            return yeast.getDataAcquisto();
                    }
            }
            return null;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        if (this.dataValues.get(row) != null) {
                Yeast yeast = (this.dataValues.get(row));
                boolean flag = false;
                switch (col) {
                    case 0: 
                        yeast.setCodice((String)value);
                        break;
                    case 1:
                            yeast.setNome((String) value);
                            break;
                    case 2:
                            if (value instanceof Double)
                                yeast.setQuantita(value.toString());
                            else
                                yeast.setQuantita((String) value);
                            break;
                    case 3:
                            yeast.setDataAcquisto((Date) value);
                            break;
                    default:
                            break;
                }
                fireTableCellUpdated(row, col);
                if (flag) {
                        fireTableRowsUpdated(row, row);
                }
        }
    }
        
    @Override
    public boolean isCellEditable(int row, int col) {
            return true;
    }
    
     public void appendRow(Yeast row) {
            boolean esiste = false;
            //Controllo se il luppolo è già presente
            for (int ii = 0; ii < this.getRowCount(); ii++) {
                if (((String)this.getValueAt(ii, 0)).equalsIgnoreCase(row.getCodice())) {
                    this.setValueAt(Double.parseDouble((String)this.getValueAt(ii, 2))+(row.getQuantita()!=null?Double.parseDouble(row.getQuantita()):0.0), ii, 2);
                    esiste = true;
                    break;
                }
                    
            }
            if (!esiste) this.dataValues.add(row);
            fireTableDataChanged();
        }
}
