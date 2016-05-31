/*
 *  Copyright 2005, 2006 Alessandro Chiari.
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

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthStyle;
import javax.swing.plaf.synth.SynthStyleFactory;
import sun.font.FontManager;
import sun.swing.plaf.synth.DefaultSynthStyle;

/**
 *
 * @author Azatoth
 */
public class AzatothLookAndFeel extends NimbusLookAndFeel
{
    private Font defaultFont;

    @Override
    public UIDefaults getDefaults()
    {
        //get the normal defaults
        UIDefaults theDefaults = super.getDefaults();
        //defaultFont = FontManager.getFontConfigFUIR("SansSerif", Font.PLAIN, 11);
        //theDefaults.put("defaultFont", new FontUIResource(defaultFont));
        theDefaults.put("defaultFont", new Font("Tahoma", 0, 11));
        return theDefaults;
    }

    @Override
    public void initialize()
    {
        //do the normal work
        super.initialize();

        // reset the synth style factory to get your font into the style also
        final SynthStyleFactory oldFactory = getStyleFactory();
        setStyleFactory(new SynthStyleFactory() {
            @Override
            public SynthStyle getStyle(JComponent c, Region r) {
                SynthStyle s = oldFactory.getStyle(c, r);
                if(s instanceof DefaultSynthStyle)
                {
                    ((DefaultSynthStyle)s).setFont(defaultFont);
                }
                return s;
            }
        });
    }
}
