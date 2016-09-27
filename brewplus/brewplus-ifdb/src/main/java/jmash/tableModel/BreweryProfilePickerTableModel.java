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
 * @author Peruzzi Alessandro
 */
public class BreweryProfilePickerTableModel extends PickerTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2273172913360604792L;
	private JLabel ret = new JLabel("");
	/** Creates a new instance of BreweryProfileTableModel */
	LinkedList<BreweryProfile> dataValues = new LinkedList<BreweryProfile>();

	public BreweryProfilePickerTableModel() {
	}

	String columnNames[] = { "Nome", "Descrizione", "AssorbimentoGraniEsausti", "RapportoAcquaGrani", "PercentualeEvaporazione",
			"ContrazionePerRaffreddamento", "PerditeNelTrub", "Biab" };
	
	
	public void addRow(BreweryProfile breweryProfile) {
		this.dataValues.add(breweryProfile);
		fireTableDataChanged();
	}

	@Override
	public void emptyRows() {
		this.dataValues.clear();
		fireTableDataChanged();
	}

	@Override
	public List<BreweryProfile> getRows() {
		return this.dataValues;
	}

	@Override
	public String getColumnName(int col) {
		return this.columnNames[col].toString();
	}

	@Override
	public int getRowCount() {
		return this.dataValues == null ? 0 : this.dataValues.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		BreweryProfile breweryProfile  = this.dataValues.get(row);
		this.ret.setIcon(Main.maltIcon);

		if (breweryProfile != null) {
			if (breweryProfile.isBiab()) {
				this.ret.setIcon(Main.extractIcon);
			}
			switch (col) {
			case 8:
				return breweryProfile.getBiab();
			case 7:
				return breweryProfile.getPerditeNelTrub();
			case 6:
				return breweryProfile.getContrazionePerRaffreddamento();
			case 5:
				return breweryProfile.getPercentualeEvaporazione();
			case 4:
				return breweryProfile.getRapportoAcquaGrani();
			case 3:
				return breweryProfile.getAssorbimentoGraniEsausti();
			case 2:
				return breweryProfile.getDescrizione();
			case 1:
				return breweryProfile.getNome();
			default:
				return this.ret;
			}
			
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
	}

	LinkedList<BreweryProfile> dataValuesCopy = null;

	public void setFilterOn(int column, String VAL) {
		if (dataValuesCopy == null)
			dataValuesCopy = dataValues;
		dataValues = dataValuesCopy;
		LinkedList<BreweryProfile> dataValuesNew = new LinkedList<BreweryProfile>();
		for (int i = 0; i < dataValuesCopy.size(); i++) {
			if (getValueAt(i, column) != null && getValueAt(i, column).toString().compareToIgnoreCase(VAL) == 0)
				dataValuesNew.add(dataValuesCopy.get(i));
		}
		dataValues = dataValuesNew;
		fireTableDataChanged();
	}

	public void setFilterOff() {
		if (dataValuesCopy != null)
			dataValues = dataValuesCopy;
		fireTableDataChanged();
	}
}
