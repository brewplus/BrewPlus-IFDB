/*
 * PolarChartDemo.java
 *
 * Created on 28 gennaio 2008, 23.23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;
/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * PolarChartDemo.java
 * -------------------
 * (C) Copyright 2004, by Solution Engineering, Inc. and Contributors.
 *
 * Original Author:  Daniel Bridenbecker, Solution Engineering, Inc.;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: PolarChartDemo.java,v 1.1 2008/02/03 17:36:41 alexchiari Exp $
 *
 * Changes
 * -------
 * 19-Jan-2004 : Version 1, contributed by DB with minor changes by DG (DG);
 *
 */


import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PolarChartPanel;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * <code>PolarChartDemo</code> demonstrates the capabilities of the {@link PolarPlot}.
 * 
 * @author  Daniel Bridenbecker, Solution Engineering, Inc.
 */
public class PolarChartDemo extends ApplicationFrame {
    
    /**
     * Creates a new instance of the demo.
     * 
     * @param title  the frame title.
     */
    public PolarChartDemo(final String title) {
        super(title);
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new PolarChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private XYDataset createDataset() {    
        final XYSeriesCollection data = new XYSeriesCollection();
        final XYSeries series1 = createRandomData("Series 1", 5.0, 360.0/8);
        final XYSeries series2 = createRandomData("Series 2", 5.0, 360.0/8);
        final XYSeries series3 = createRandomData("Series 3", 5.0, 360.0/8);
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);

	
        return data;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createPolarChart(
            "Polar Chart Demo", dataset, true, true, false
        ); 
        final PolarPlot plot = (PolarPlot) chart.getPlot();

        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
plot.setBackgroundAlpha(0.3f);
        renderer.setSeriesFilled(0, true);
	renderer.setSeriesFilled(2, true);
	renderer.setSeriesFilled(1, true);
        return chart;
    }
    
    /**
     * Creates a series containing random data.
     * 
     * @param name  the series name.
     * @param baseRadius  the base radius.
     * @param thetaInc  the angle increment.
     * 
     * @return The series.
     */
    private static XYSeries createRandomData(final String name, 
                                             final double baseRadius, 
                                             final double thetaInc) {
        final XYSeries series = new XYSeries(name);
        for (double theta = 0.0; theta < 360.0; theta += thetaInc) {
            final double radius = 1+baseRadius * (Math.random());
            series.add(theta, radius);
        }
        return series;
    }
    
    public static void main(String[] args) {
	 PolarChartDemo demo = new PolarChartDemo("Polar Chart Demo");
        demo.pack();
        demo.setVisible(true);
    }
}