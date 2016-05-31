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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alessandro
 */
public class RicettaNoGui /*extends CalendarEntry*/ {

    /** Creates a new instance of RicettaNoGui */
    public RicettaNoGui() {
    }
    List<Hop> luppoli = new ArrayList<Hop>();
    List<Malt> malti = new ArrayList<Malt>();

    @Override
    public String toString() {
        String str = "Ricetta\nLuppoli:\n";
        for (Hop L : this.luppoli) {
            str += "\t" + L + "\n";
        }
        str += "Malti:\n";
        for (Malt M : this.malti) {
            str += "\t" + M;
        }
        return str;
    }
    private java.util.Date data = new Date();

    public java.util.Date getData() {
        return this.data;
    }

    public void setData(java.util.Date data) {
        this.data = data;
    }
}
