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
package jmash.tableModel;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import jmash.*;

/**
 *
 * @author Alessandro Peruzzi
 */
public class MaltCategoryPickerTableModel extends PickerTableModel
{
  private static final long serialVersionUID = 2573174133360604792L;
  private JLabel ret = new JLabel("");
  LinkedList<MaltCategory> dataValues = new LinkedList<MaltCategory>();

  public MaltCategoryPickerTableModel()
  {
  }

  String columnNames[] =
  {
    "Codice", "Nome", "ph"
  };

  public void addRow(MaltCategory maltCategory)
  {
    this.dataValues.add(maltCategory);
    fireTableDataChanged();
  }

  @Override
  public void emptyRows()
  {
    this.dataValues.clear();
    fireTableDataChanged();
  }

  @Override
  public List<MaltCategory> getRows()
  {
    return this.dataValues;
  }

  @Override
  public String getColumnName(int col)
  {
    return this.columnNames[col].toString();
  }

  @Override
  public int getRowCount()
  {
    return this.dataValues == null ? 0 : this.dataValues.size();
  }

  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int row, int col)
  {
    MaltCategory maltCategory = this.dataValues.get(row);
    if (maltCategory != null)
    {
      switch (col)
      {
        case 1:
          return maltCategory.getCodice();
        case 2:
          return maltCategory.getNome();
        case 3:
          return maltCategory.getPh();
        case 0:
        default:
          return maltCategory.getCodice();
      }
    }
    return null;
  }

}
