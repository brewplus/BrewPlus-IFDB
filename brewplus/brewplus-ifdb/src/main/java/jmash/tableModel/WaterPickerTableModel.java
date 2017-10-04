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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jmash.WaterProfile;



public class WaterPickerTableModel extends PickerTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2273174133360604792L;
	/** Creates a new instance of HopTableModel */
	LinkedList<WaterProfile> dataValues = new LinkedList<WaterProfile>();

	public WaterPickerTableModel() {
	}

	String columnNames[] = { "Nome", "Calcio", "Magnesio", "Solfato", "Cloruro", "Sodio", "Carbonato" };

	public void addRow(WaterProfile m) {
		this.dataValues.add(m);
		fireTableDataChanged();
	}

	@Override
	public void emptyRows() {
		this.dataValues.clear();
		fireTableDataChanged();
	}

	@Override
	public List<WaterProfile> getRows() {
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
		WaterProfile m = this.dataValues.get(row);
		if (m != null) {
			switch (col) {
			case 1:
				return m.getCalcio();
			case 2:
				return m.getMagnesio();
			case 3:
				return m.getSolfato();
			case 4:
				return m.getCloruro();
			case 5:
				return m.getSodio();
			case 6:
				return m.getCarbonato();
			case 0:
			default:
				return m.getNome();
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
	
	public String[] getWaterNames() {
		return getWaterNames(null);
	}

	public String[] getWaterNames(String header) {

		int n = StringUtils.isBlank(header) ? getRowCount() : getRowCount() + 1;
		int posInit = StringUtils.isBlank(header) ? 0 : 1;
		String[] waterNames = new String[n];

		if (!StringUtils.isBlank(header)) {
			waterNames[0] = header;
		}

		WaterProfile waterProfile;
		for (int i = posInit; i < n; i++) {
			waterProfile = getRows().get(i - posInit);
			waterNames[i] = waterProfile.getNome();
		}

		return waterNames;
	}
	
	public Map<String, WaterProfile> getWaterProfiles() {

		Map<String, WaterProfile> waterProfiles = new LinkedHashMap<>();

		for (WaterProfile waterProfile : getRows()) {

			waterProfiles.put(waterProfile.getNome(), waterProfile);
		}

		return waterProfiles;
	}

	public WaterProfile findFirstWaterProfile(WaterProfile waterProfileToFind) {
		WaterProfile found = waterProfileToFind;

		for (WaterProfile waterProfile : getRows()) {

			if (waterProfile.isLiteEquals(waterProfileToFind)) {
				found.setNome(waterProfile.getNome());
				break;
			}

		}

		return found;
	}

	public Integer findFirstIndexWaterProfile(WaterProfile waterProfileToFind) {
		Integer index = null;

		for (int i = 0; i < getRows().size(); i++) {
			WaterProfile waterProfile = getRows().get(i);

			if (waterProfile.isLiteEquals(waterProfileToFind)) {
				index = i;
				break;
			}

		}

		return index;
	}
	
	public boolean isPresentWaterProfile(WaterProfile waterProfileToFind)
	{
		return findFirstIndexWaterProfile(waterProfileToFind) != null;
	}

	public WaterProfile findWaterProfile(String name) {
		WaterProfile found = null;

		if (StringUtils.isNotBlank(name)) {

			for (WaterProfile waterProfile : getRows()) {

				if (name.equals(waterProfile.getNome())) {
					found = waterProfile;
					break;
				}

			}
		}
		return found;
	}
}
