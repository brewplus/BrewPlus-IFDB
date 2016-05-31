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

/**
 *
 * @author Alessandro
 */
public class NullableElement extends org.jdom.Element {

    /**
     * 
     */
    private static final long serialVersionUID = -6684894306024308916L;

    /** Creates a new instance of NullableElement */
    public NullableElement(String name) {
        super(name);
    }

    @Override
    public org.jdom.Element setAttribute(String a, String b) {
        if (b == null) {
            b = "";
        }
        return super.setAttribute(a, b);
    }
}
