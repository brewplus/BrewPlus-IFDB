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
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;

import jmash.tableModel.MaltTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author  ixtlanas
 */
public class frmIngPie extends javax.swing.JInternalFrame {
    
    private static final long serialVersionUID = 1852074935415528044L;
    
    public frmIngPie(String titolo) {
	initComponents();
	setBorder(Utils.getDefaultBorder());
	PieDataset ds =createDataset();
	JFreeChart ch=createChart(ds,titolo);
	ChartPanel chpanel=new ChartPanel(ch);
	chpanel.setPreferredSize(new java.awt.Dimension(400, 250));
	JPanel panelC = new JPanel();
	panelC.add(chpanel);
	getContentPane().add(panelC, BorderLayout.CENTER);
	
    }
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-513)/2, (screenSize.height-368)/2, 400, 300);
    }
    
    private  PieDataset createDataset()
    {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", new Integer(29));
        result.setValue("Mac", new Integer(20));
        result.setValue("Windows", new Integer(51));
        return result;
        
    }
    private JFreeChart createChart(PieDataset dataset, String title)
    {
    	JFreeChart chart= ChartFactory.createPieChart(title, dataset,false,true,false);
    	PiePlot plot = (PiePlot) chart.getPlot();
    	plot.setNoDataMessage("No data available");
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
    	return chart;		
    }

}
