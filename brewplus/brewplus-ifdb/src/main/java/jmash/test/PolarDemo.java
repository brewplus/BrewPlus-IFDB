/*
 * PolarDemo.java
 *
 * Created on 29 gennaio 2008, 21.52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PolarChartPanel;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author Alessandro
 */
public class PolarDemo  extends ApplicationFrame {
    
    /**
     * Creates a new instance of the demo.
     *
     * @param title  the frame title.
     */
    public PolarDemo(final String title) {
        super(title);
        final JFreeChart chart = createChart();
        final ChartPanel chartPanel = new PolarChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }
    
    
    
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart() {
        XYSeriesCollection data = new XYSeriesCollection();
        
        XYSeries series1 = createRandomData("Series 1", 5.0, 360.0/8);
        data.addSeries(series1);
        
        
        PolarPlot plot = new MyPolarPlot();
        
        
        plot.setDataset(data);
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        rangeAxis.setRange(0,5);
        plot.setAxis(rangeAxis);
        plot.setRenderer(new DefaultPolarItemRenderer());
        JFreeChart chart = new JFreeChart(
                "Giudizio", JFreeChart.DEFAULT_TITLE_FONT, plot, false
                );
        DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
        plot.setBackgroundAlpha(0.3f);
        ((NumberAxis)plot.getAxis()).setTickLabelsVisible(true);
//        plot.setAngleLabelsVisible(false);
        renderer.setSeriesFilled(0, true);
renderer.setSeriesStroke(0,new BasicStroke(2f));
        renderer.setSeriesOutlineStroke(0,new BasicStroke(2f));
        renderer.setSeriesOutlinePaint(0,Color.BLACK);
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
            final double radius = (int)(1+(baseRadius) * (Math.random()));
            series.add(theta, radius);
        }
        return series;
    }
    
    public static void main(String[] args) {
        PolarDemo demo = new PolarDemo("Polar Chart Demo");
        demo.pack();
        demo.setVisible(true);
    }
    class MyPolarPlot extends PolarPlot{
        List angleTicks = new java.util.ArrayList();
        public MyPolarPlot(){
            //la schiuma, l'aspetto, l'intensità olfattiva, la finezza olfattiva, la frizzantezza, il corpo, l'amaro e la persistenza retrolfattiva.
            this.angleTicks.add(new NumberTick(new Double(0.0), "schiuma",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(45.0), "aspetto",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(90.0), "intensità olfattiva",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(135.0), "finezza olfattiva",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(180.0), "frizzantezza",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(225.0), "corpo",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(270.0), "amaro",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
            this.angleTicks.add(new NumberTick(new Double(315.0), "persistenza",
                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
        }
        protected void drawGridlines(Graphics2D g2, Rectangle2D dataArea, List angularTicks, List radialTicks) {
            super.drawGridlines(g2,dataArea,angleTicks, radialTicks);
        }
    }
}