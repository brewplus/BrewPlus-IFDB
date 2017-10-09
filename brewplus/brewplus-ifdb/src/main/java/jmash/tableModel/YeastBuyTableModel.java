/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.tableModel;

import java.text.ParseException;
import java.util.Date;
import javax.swing.JLabel;
import jmash.Hop;
import jmash.Main;
import jmash.Utils;
import jmash.Yeast;
import static jmash.tableModel.GenericTableModel.NF;

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
}
