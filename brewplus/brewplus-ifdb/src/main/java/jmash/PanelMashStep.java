/*
 *  Copyright 2006, 2007, 2008 Alessandro Chiari.
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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import jmash.tableModel.MashDecoctionStepTableModel;
import jmash.tableModel.MashInfusionStepTableModel;
import jmash.tableModel.MashStepTableModel;
import jmash.tableModel.NumberFormatter;

import org.jdom.Document;
import org.jdom.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Alessandro
 */
public class PanelMashStep extends javax.swing.JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 2970112440652235772L;
	/** Creates new form PanelMashStep */
	private Ricetta ricetta;
	private JInternalFrame parentFrame;

	public static ImageIcon newIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/filenew.png")));
	public static ImageIcon openIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/fileopen.png")));
	public static ImageIcon saveIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/filesave.png")));
	public static ImageIcon saveAsIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/filesaveas.png")));
	public static ImageIcon detailsIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/edu_languages.png")));

	public PanelMashStep(Ricetta r, JInternalFrame parentFrame) {
		this.ricetta = r;
		this.parentFrame = parentFrame;
		initComponents();
		// mashStepTableModel=new MashStepTableModel(ricetta);
		this.mashStepTableModel = new MashStepTableModel(this);
		this.mashDecoctionStepTableModel = new MashDecoctionStepTableModel(this);
		this.mashInfusionStepTableModel = new MashInfusionStepTableModel(this);
		this.tblMaltSteps.setModel(this.mashStepTableModel);
		this.tblMaltSteps1.setModel(this.mashDecoctionStepTableModel);
		this.tblMaltSteps2.setModel(this.mashInfusionStepTableModel);
		this.jPanel6.add(createDemoPanel(), BorderLayout.CENTER);
		coeffX.setModel(10, 0, 100, 1, "0", "PanelMashStep.CX");
		jMashSpinner1.setModel(1, -100, 100, 1);
		tBoil.setTemperature(100);
	}

	public MashStepTableModel mashStepTableModel;
	public MashDecoctionStepTableModel mashDecoctionStepTableModel;
	public MashInfusionStepTableModel mashInfusionStepTableModel;
	private JFreeChart chart = null;

	public JPanel createDemoPanel() {
		JFreeChart lChart = createChart();
		return new ChartPanel(lChart);
	}

	public BufferedImage getImage() {
		return this.chart.createBufferedImage(440, 220);
	}

	private JFreeChart createChart() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		this.chart = createChart(dataset);
		return this.chart;
	}

	int R1 = 2;
	int R2 = 0;
	int R3 = 1;

	@SuppressWarnings("deprecation")
	private JFreeChart createChart(final XYDataset dataset) {
		// final JFreeChart chart = ChartFactory.createXYLineChart(
		final JFreeChart chart1 = ChartFactory.createXYAreaChart("", "Minuti", "Temperatura", null,
				PlotOrientation.VERTICAL, false, // legend
				true, // tool tips
				false // URLs
		);

		chart1.setBackgroundPaint(Color.white);

		this.ds2.addSeries(publicData);
		this.ds3.addSeries(publicPH);

		chart1.setBackgroundPaint(new Color(230, 230, 245));

		chart1.getXYPlot().setDataset(R1, this.ds);
		chart1.getXYPlot().setDataset(R2, this.ds2);
		chart1.getXYPlot().setDataset(R3, this.ds3);

		chart1.getXYPlot().setRenderer(R1, new XYAreaRenderer());
		chart1.getXYPlot().setRenderer(R2, new DefaultXYItemRenderer());
		chart1.getXYPlot().getRenderer(R2).setStroke(new BasicStroke(5f));

		chart1.getXYPlot().setRenderer(R3, new DefaultXYItemRenderer());
		chart1.getXYPlot().getRenderer(R3).setStroke(new BasicStroke(5f));

		chart1.getXYPlot().getRenderer(R2).setSeriesPaint(0, Color.BLUE);
		chart1.getXYPlot().getRenderer(R3).setSeriesPaint(0, Color.ORANGE);

		chart1.getXYPlot().getRenderer(R1).setItemLabelsVisible(true);
		chart1.getXYPlot().getRenderer(R1).setItemLabelPaint(Color.black);

		/*
		 * NumberAxis axis = new NumberAxis("°C");
		 * axis.setLabelPaint(Color.BLUE); axis.setTickLabelPaint(Color.BLUE);
		 * axis.setNumberFormatOverride(new DecimalFormat("0.0"));
		 * axis.setAutoRange(true); chart.getXYPlot().setRangeAxis(R1, axis);
		 * chart.getXYPlot().setRangeAxisLocation(R1,
		 * AxisLocation.BOTTOM_OR_LEFT);
		 */

		chart1.getXYPlot().getRenderer(R1).setToolTipGenerator(new XYToolTipGenerator() {
			@Override
			public String generateToolTip(XYDataset ds, int s, int i) {
				if (ds.getItemCount(s) < 2)
					return "";
				double d0 = ds.getXValue(s, 0), d1 = ds.getXValue(s, 1);
				double y0 = ds.getYValue(s, 0), y1 = ds.getYValue(s, 1);
				String str = "";
				int N = ds.getItemCount(s);
				if (N == 3) {
					double y2 = ds.getYValue(s, 2);
					double d2 = ds.getXValue(s, 2);
					// infusione
					str = "Rest di " + ((int) (d2 - d1)) + " minuti a " + (int) y1 + "°C,\npartendo da " + (int) y0
							+ "°C, in " + ((int) (d1 - d0)) + " minuti";
				} else if (N == 6) {
					// decozione
					double y2 = ds.getYValue(s, 2);
					double d2 = ds.getXValue(s, 2);
					double d3 = ds.getXValue(s, 3), d4 = ds.getXValue(s, 4), d5 = ds.getXValue(s, 5);
					double y3 = ds.getYValue(s, 3), y4 = ds.getYValue(s, 4), y5 = ds.getYValue(s, 5);
					// F = (T1 - T0) / (TB - T0 - X)
					double F = 100.0 * (y5 - y0) / (tBoil.getTemperature() - y0 - coeffX.getDoubleValue());

					str += "Decozione del ";
					str += " " + (int) F + "% dell'impasto, prima per " + (int) (d2 - d1) + " minuti a " + (int) y2
							+ " °C";
					str += ", poi per " + (int) (d4 - d3) + " minuti a " + (int) y4 + " °C";
				}
				return str;
			}
		});

		final XYPlot plot = chart1.getXYPlot();

		XYAreaRenderer xyAreaRenderer = (XYAreaRenderer) chart1.getXYPlot().getRenderer(R1);
		for (int i = 0; i < 100; i++) {
			xyAreaRenderer.setSeriesOutlinePaint(i, ((Color) xyAreaRenderer.getSeriesPaint(i)));
			Color C = (Color) xyAreaRenderer.getSeriesPaint(i);
			if (C != null) {
				float r[] = new float[4];
				r = C.getRGBColorComponents(r);
				xyAreaRenderer.setSeriesPaint(i, new Color(r[0], r[1], r[2], 0.3f));
			}
		}
		xyAreaRenderer.setOutline(true);
		xyAreaRenderer.setItemLabelsVisible(true);
		xyAreaRenderer.setItemLabelPaint(Color.black);
		xyAreaRenderer.setOutlineStroke(new BasicStroke(2f));

		xyAreaRenderer.setOutlineStroke(new BasicStroke(2f));
		plot.setForegroundAlpha(1.0f);
		plot.getRenderer(R1).setStroke(new BasicStroke(2f));
		plot.setDomainGridlinePaint(Color.gray);
		plot.setRangeGridlinePaint(Color.gray);
		plot.setBackgroundPaint(Color.white);
		plot.setBackgroundPaint(new Color(240, 240, 255));
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);

		final ValueAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.0);
		return chart1;
	}

	public void addPHAxis() {
		NumberAxis axis2 = new NumberAxis("pH");
		axis2.setLabelPaint(Color.ORANGE);
		axis2.setTickLabelPaint(Color.ORANGE);
		axis2.setNumberFormatOverride(new NumberFormatter("0.0"));
		axis2.setAutoRange(true);
		chart.getXYPlot().setRangeAxis(R3, axis2);
		chart.getXYPlot().setRangeAxisLocation(R3, AxisLocation.BOTTOM_OR_LEFT);
		chart.getXYPlot().mapDatasetToRangeAxis(R3, R3);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jToolBar2 = new javax.swing.JToolBar();
		jButton8 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButtonImg = new javax.swing.JButton();
		jButtonImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmShowImage ed = new frmShowImage();
				Gui.desktopPane.add(ed);
				// Utils.center(ed,this);
				ed.setVisible(true);
			}
		});
		jButton6.setVisible(false);
		jLabel1 = new javax.swing.JLabel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane5 = new javax.swing.JScrollPane();
		tblMaltSteps = new javax.swing.JTable();
		jPanel1 = new javax.swing.JPanel();
		btnAdd3 = new javax.swing.JButton();
		btnRem3 = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jScrollPane6 = new javax.swing.JScrollPane();
		tblMaltSteps1 = new javax.swing.JTable();
		jPanel4 = new javax.swing.JPanel();
		btnAdd5 = new javax.swing.JButton();
		btnRem4 = new javax.swing.JButton();
		jPanel11 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		tBoil = new jmash.component.JTemperatureSpinner();
		jLabel11 = new javax.swing.JLabel();
		coeffX = new jmash.component.JMashSpinner();
		jButton1 = new javax.swing.JButton();
		jPanel8 = new javax.swing.JPanel();
		jScrollPane7 = new javax.swing.JScrollPane();
		tblMaltSteps2 = new javax.swing.JTable();
		jPanel9 = new javax.swing.JPanel();
		btnAdd6 = new javax.swing.JButton();
		btnRem5 = new javax.swing.JButton();
		jPanel10 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		spnGrani = new jmash.component.JWeightSpinner();
		jLabel3 = new javax.swing.JLabel();
		spnAcqua = new jmash.component.JVolumeSpinner();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		spnGrani1 = new jmash.component.JWeightSpinner();
		jLabel6 = new javax.swing.JLabel();
		jMashSpinner1 = new jmash.component.JMashSpinner();
		jComboBox1 = new javax.swing.JComboBox();

		setLayout(new java.awt.BorderLayout(0, 5));

		jPanel2.setLayout(new java.awt.BorderLayout());

		jPanel3.setLayout(new java.awt.BorderLayout());

		jToolBar2.setFloatable(false);

		jButton8.setIcon(new ImageIcon(PanelMashStep.class.getResource("/jmash/images/new.png")));
		jButton8.setToolTipText("Nuovo...");
		jButton8.setMaximumSize(new java.awt.Dimension(37, 35));
		jButton8.setMinimumSize(new java.awt.Dimension(37, 35));
		jButton8.setPreferredSize(new java.awt.Dimension(37, 35));
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});
		jToolBar2.add(jButton8);

		jButton4.setIcon(new ImageIcon(PanelMashStep.class.getResource("/jmash/images/folder.png")));
		jButton4.setToolTipText("Apri...");
		jButton4.setMaximumSize(new java.awt.Dimension(37, 35));
		jButton4.setMinimumSize(new java.awt.Dimension(37, 35));
		jButton4.setPreferredSize(new java.awt.Dimension(37, 35));
		jButton4.setRequestFocusEnabled(false);
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jToolBar2.add(jButton4);

		jButton5.setIcon(new ImageIcon(PanelMashStep.class.getResource("/jmash/images/save.png")));
		jButton5.setToolTipText("Salva...");
		jButton5.setMaximumSize(new java.awt.Dimension(37, 35));
		jButton5.setMinimumSize(new java.awt.Dimension(37, 35));
		jButton5.setPreferredSize(new java.awt.Dimension(37, 35));
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});
		jToolBar2.add(jButton5);

		jButton7.setIcon(new ImageIcon(PanelMashStep.class.getResource("/jmash/images/saveas.png")));
		jButton7.setToolTipText("Salva come...");
		jButton7.setMaximumSize(new java.awt.Dimension(37, 35));
		jButton7.setMinimumSize(new java.awt.Dimension(37, 35));
		jButton7.setPreferredSize(new java.awt.Dimension(37, 35));
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});
		jToolBar2.add(jButton7);

		jButton6.setIcon(detailsIcon);
		jButton6.setToolTipText("Visualizza in formato umano");
		jButton6.setMaximumSize(new java.awt.Dimension(37, 35));
		jButton6.setMinimumSize(new java.awt.Dimension(37, 35));
		jButton6.setPreferredSize(new java.awt.Dimension(37, 35));
		jButton6.setRequestFocusEnabled(false);
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});
		jToolBar2.add(jButton6);

		jPanel3.add(jToolBar2, java.awt.BorderLayout.SOUTH);

		jButtonImg.setIcon(new ImageIcon(PanelMashStep.class.getResource("/jmash/images/mashdesign.png")));
		jButtonImg.setToolTipText("Visualizza prospetto temperature");
		jButtonImg.setRequestFocusEnabled(false);
		jButtonImg.setPreferredSize(new Dimension(37, 35));
		jButtonImg.setMinimumSize(new Dimension(37, 35));
		jButtonImg.setMaximumSize(new Dimension(37, 35));
		jToolBar2.add(jButtonImg);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

		jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

		jPanel5.setLayout(new java.awt.BorderLayout(5, 0));

		jScrollPane5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
		jScrollPane5.setMinimumSize(null);
		jScrollPane5.setPreferredSize(new java.awt.Dimension(450, 10));
		jScrollPane5.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				jScrollPane5MouseWheelMoved(evt);
			}
		});

		tblMaltSteps.setPreferredSize(null);
		tblMaltSteps.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				tblMaltStepsMouseWheelMoved(evt);
			}
		});
		jScrollPane5.setViewportView(tblMaltSteps);

		jPanel5.add(jScrollPane5, java.awt.BorderLayout.CENTER);

		jPanel1.setPreferredSize(new java.awt.Dimension(36, 46));
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

		btnAdd3.setIcon(Main.addIcon);
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jmash/lang"); // NOI18N
		btnAdd3.setToolTipText(bundle.getString("Aggiungi")); // NOI18N
		btnAdd3.setAlignmentX(0.5F);
		btnAdd3.setMaximumSize(new java.awt.Dimension(30, 30));
		btnAdd3.setMinimumSize(new java.awt.Dimension(30, 30));
		btnAdd3.setPreferredSize(new java.awt.Dimension(36, 36));
		btnAdd3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdd3ActionPerformed(evt);
			}
		});
		jPanel1.add(btnAdd3);

		btnRem3.setIcon(Main.remIcon);
		btnRem3.setToolTipText(bundle.getString("Rimuovi")); // NOI18N
		btnRem3.setAlignmentX(0.5F);
		btnRem3.setMaximumSize(new java.awt.Dimension(30, 30));
		btnRem3.setMinimumSize(new java.awt.Dimension(30, 30));
		btnRem3.setPreferredSize(new java.awt.Dimension(36, 36));
		btnRem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRem3ActionPerformed(evt);
			}
		});
		jPanel1.add(btnRem3);

		jPanel5.add(jPanel1, java.awt.BorderLayout.WEST);

		jPanel6.setPreferredSize(new java.awt.Dimension(600, 250));
		jPanel6.setLayout(new java.awt.BorderLayout());
		jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

		jTabbedPane1.addTab("Riscaldamento diretto", jPanel5);

		jPanel7.setLayout(new java.awt.BorderLayout(5, 0));

		jScrollPane6.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
		jScrollPane6.setMinimumSize(null);
		jScrollPane6.setPreferredSize(new java.awt.Dimension(450, 120));
		jScrollPane6.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				jScrollPane6MouseWheelMoved(evt);
			}
		});

		tblMaltSteps1.setPreferredSize(null);
		tblMaltSteps1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				tblMaltSteps1MouseWheelMoved(evt);
			}
		});
		jScrollPane6.setViewportView(tblMaltSteps1);

		jPanel7.add(jScrollPane6, java.awt.BorderLayout.CENTER);

		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

		btnAdd5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
		btnAdd5.setToolTipText(bundle.getString("Aggiungi")); // NOI18N
		btnAdd5.setAlignmentX(0.5F);
		btnAdd5.setMaximumSize(new java.awt.Dimension(30, 30));
		btnAdd5.setMinimumSize(new java.awt.Dimension(30, 30));
		btnAdd5.setPreferredSize(new java.awt.Dimension(36, 36));
		btnAdd5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdd5ActionPerformed(evt);
			}
		});
		jPanel4.add(btnAdd5);

		btnRem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_remove.png"))); // NOI18N
		btnRem4.setToolTipText(bundle.getString("Rimuovi")); // NOI18N
		btnRem4.setAlignmentX(0.5F);
		btnRem4.setMaximumSize(new java.awt.Dimension(30, 30));
		btnRem4.setMinimumSize(new java.awt.Dimension(30, 30));
		btnRem4.setPreferredSize(new java.awt.Dimension(36, 36));
		btnRem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRem4ActionPerformed(evt);
			}
		});
		jPanel4.add(btnRem4);

		jPanel7.add(jPanel4, java.awt.BorderLayout.WEST);

		jPanel11.setMinimumSize(new java.awt.Dimension(378, 38));

		jLabel7.setText("T. bollitura");
		jPanel11.add(jLabel7);
		jPanel11.add(jLabel9);
		jPanel11.add(tBoil);

		jLabel11.setText("Coeff.");
		jPanel11.add(jLabel11);
		jPanel11.add(coeffX);

		jButton1.setText("?");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel11.add(jButton1);

		jPanel7.add(jPanel11, java.awt.BorderLayout.SOUTH);

		jTabbedPane1.addTab("Decozione", jPanel7);

		jPanel8.setLayout(new java.awt.BorderLayout(5, 0));

		jScrollPane7.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.shadow"));
		jScrollPane7.setMinimumSize(null);
		jScrollPane7.setPreferredSize(new java.awt.Dimension(450, 40));
		jScrollPane7.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				jScrollPane7MouseWheelMoved(evt);
			}
		});

		tblMaltSteps2.setPreferredSize(null);
		tblMaltSteps2.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				tblMaltSteps2MouseWheelMoved(evt);
			}
		});
		jScrollPane7.setViewportView(tblMaltSteps2);

		jPanel8.add(jScrollPane7, java.awt.BorderLayout.CENTER);

		jPanel9.setPreferredSize(new java.awt.Dimension(36, 46));
		jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

		btnAdd6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_add.png"))); // NOI18N
		btnAdd6.setToolTipText(bundle.getString("Aggiungi")); // NOI18N
		btnAdd6.setAlignmentX(0.5F);
		btnAdd6.setMaximumSize(new java.awt.Dimension(30, 30));
		btnAdd6.setMinimumSize(new java.awt.Dimension(30, 30));
		btnAdd6.setPreferredSize(new java.awt.Dimension(36, 36));
		btnAdd6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdd6ActionPerformed(evt);
			}
		});
		jPanel9.add(btnAdd6);

		btnRem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/edit_remove.png"))); // NOI18N
		btnRem5.setToolTipText(bundle.getString("Rimuovi")); // NOI18N
		btnRem5.setAlignmentX(0.5F);
		btnRem5.setMaximumSize(new java.awt.Dimension(30, 30));
		btnRem5.setMinimumSize(new java.awt.Dimension(30, 30));
		btnRem5.setPreferredSize(new java.awt.Dimension(36, 36));
		btnRem5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRem5ActionPerformed(evt);
			}
		});
		jPanel9.add(btnRem5);

		jPanel8.add(jPanel9, java.awt.BorderLayout.WEST);

		jPanel10.setMinimumSize(new java.awt.Dimension(378, 38));

		jLabel2.setText("Grani");
		jPanel10.add(jLabel2);
		jPanel10.add(spnGrani);

		jLabel3.setText("Acqua iniziale");
		jPanel10.add(jLabel3);
		jPanel10.add(spnAcqua);
		jPanel10.add(jLabel4);

		jLabel5.setText("Contenitore");
		jPanel10.add(jLabel5);
		jPanel10.add(spnGrani1);

		jLabel6.setText("Coeff.");
		jPanel10.add(jLabel6);
		jPanel10.add(jMashSpinner1);

		jComboBox1.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "", "Alluminio", "Acciaio", "Plastica" }));
		jPanel10.add(jComboBox1);

		jPanel8.add(jPanel10, java.awt.BorderLayout.SOUTH);

		jTabbedPane1.addTab("Infusione di acqua calda", jPanel8);

		jPanel2.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		add(jPanel2, java.awt.BorderLayout.CENTER);
	}// </editor-fold>                        

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		new ShowFormula("", "F = \\frac{T_fin - T_ini} {T_boll - T_ini - T_X^coeff}").startModal(this.parentFrame);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void btnRem5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRem5ActionPerformed
		if (!tblMaltSteps2.isEnabled())
			return;
		this.mashInfusionStepTableModel.remRow(this.tblMaltSteps2.getSelectedRow());
		mashModificato();
	}// GEN-LAST:event_btnRem5ActionPerformed

	private void btnAdd6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAdd6ActionPerformed
		if (!tblMaltSteps2.isEnabled())
			return;
		MashStep step = new MashStep(this.ricetta);
		step.setLength(new Integer(15));
		step.setRamp(new Integer(5));
		step.setType("Aggiunta");
		step.setStart(new Integer(15));
		step.setStartTemp(new Integer(15));
		step.setStartTemp(new Integer(15));
		step.setEndTemp(50);
		step.setInfusionTemp(100);
		this.mashInfusionStepTableModel.addRow(step, true);
		mashModificato();
	}// GEN-LAST:event_btnAdd6ActionPerformed

	private void jScrollPane7MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_jScrollPane7MouseWheelMoved

	}// GEN-LAST:event_jScrollPane7MouseWheelMoved

	private void tblMaltSteps2MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_tblMaltSteps2MouseWheelMoved

	}// GEN-LAST:event_tblMaltSteps2MouseWheelMoved

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton8ActionPerformed
		clear();
	}// GEN-LAST:event_jButton8ActionPerformed

	private void clear() {
		mashDecoctionStepTableModel.remAllRows();
		mashInfusionStepTableModel.remAllRows();
		mashStepTableModel.remAllRows();
		tblMaltSteps.setEnabled(true);
		tblMaltSteps1.setEnabled(true);
		tblMaltSteps2.setEnabled(true);
		mashModificato();
		this.file = null;
		jLabel1.setText("");
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
		save(null);
	}// GEN-LAST:event_jButton7ActionPerformed

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6ActionPerformed
		log();
	}// GEN-LAST:event_jButton6ActionPerformed

	public void ricalcolaInfusioni() {

		@SuppressWarnings("unchecked")
		List<MashStep> ls = (List<MashStep>) new LinkedList<MashStep>(this.mashDecoctionStepTableModel.getRows())
				.clone();
		Collections.sort(ls);

		List<MashStep> list = new ArrayList<MashStep>();
		MashStep first = new MashStep();
		first.setStartTemp(20);
		first.setType(XmlTags.MASH_STEP_TYPE[0]);
		first.setRamp(2);
		first.setNome("Generato da decozione");
		boolean flag = true;
		MashStep prec = null;
		MashStep yPrec = null;
		for (MashStep y : ls) {
			if (flag) {
				first.setEndTemp(y.getStartTemp());
				first.setLength(y.getStart() + y.getDecoctionRamp1() + y.getDecoctionRamp2() + y.getDecoctionRestL1()
						+ y.getDecoctionRestL2() +

						-first.getRamp());
				flag = false;
				prec = first;
			}

			MashStep next = new MashStep();
			next.setNome("Generato da decozione");
			next.setType(XmlTags.MASH_STEP_TYPE[0]);
			next.setStartTemp(prec.getStartTemp());
			next.setRamp(y.getDecoctionRampDown());
			next.setEndTemp(y.getEndTemp());
			next.setLength(10);

			if (prec != first) {
				int start = 0;
				if (yPrec != null) {
					start = y.getStart() - (yPrec.getStart() + yPrec.getDecoctionRamp1() + yPrec.getDecoctionRamp2()
							+ yPrec.getDecoctionRestL1() + yPrec.getDecoctionRestL2() + yPrec.getDecoctionRampDown());
				}
				prec.setLength(start + y.getDecoctionRamp1() + y.getDecoctionRamp2() + y.getDecoctionRestL1()
						+ y.getDecoctionRestL2()

				);
			}
			list.add(next);
			prec = next;
			yPrec = y;
		}
		this.mashStepTableModel.removeAll();
		this.mashStepTableModel.addRow(first, false);
		for (int i = 0; i < list.size(); i++) {
			this.mashStepTableModel.addRow(list.get(i), false);
		}
		tblMaltSteps.setEnabled(false);
		tblMaltSteps2.setEnabled(false);
		mashModificato();
	}

	public void ricalcolaInfusioniDaAggiunte() {

		@SuppressWarnings("unchecked")
		List<MashStep> ls = (List<MashStep>) new LinkedList<MashStep>(this.mashInfusionStepTableModel.getRows())
				.clone();
		Collections.sort(ls);

		List<MashStep> list = new ArrayList<MashStep>();
		MashStep first = new MashStep();
		first.setStartTemp(15);
		first.setType(XmlTags.MASH_STEP_TYPE[0]);
		first.setRamp(2);
		first.setNome("Generato da aggiunte");
		boolean flag = true;
		MashStep prec = null;
		MashStep yPrec = null;
		for (MashStep y : ls) {
			if (flag) {
				first.setEndTemp(y.getStartTemp());
				first.setLength(y.getStart() - first.getRamp());
				flag = false;
				prec = first;
			}

			MashStep next = new MashStep();
			next.setNome("Generato da aggiunte");
			next.setType(XmlTags.MASH_STEP_TYPE[0]);
			next.setStartTemp(prec.getEndTemp());
			next.setRamp(y.getRamp());
			next.setEndTemp(y.getEndTemp());
			next.setLength(30);

			if (prec != first) {
				int start = 0;
				start = y.getStart();
				if (yPrec != null) {
					start -= yPrec.getStart();
					prec.setLength(start - prec.getRamp());
				}
			}
			list.add(next);
			prec = next;
			yPrec = y;
		}
		this.mashStepTableModel.removeAll();
		this.mashStepTableModel.addRow(first, false);
		for (int i = 0; i < list.size(); i++) {
			this.mashStepTableModel.addRow(list.get(i), false);
		}
		tblMaltSteps.setEnabled(false);
		tblMaltSteps1.setEnabled(false);
		mashModificato();
	}

	private void btnRem4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRem4ActionPerformed
		if (!tblMaltSteps1.isEnabled())
			return;
		this.mashDecoctionStepTableModel.remRow(this.tblMaltSteps1.getSelectedRow());
		mashModificato();
	}// GEN-LAST:event_btnRem4ActionPerformed

	private void btnAdd5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAdd5ActionPerformed
		if (!tblMaltSteps1.isEnabled())
			return;
		MashStep step = new MashStep(this.ricetta);
		step.setLength(new Integer(15));
		step.setRamp(new Integer(5));
		step.setType("Decozione");

		step.setStart(new Integer(15));
		step.setStartTemp(new Integer(50));
		step.setStartTemp(new Integer(15));

		step.setDecoctionRamp1(5);
		step.setDecoctionRamp2(5);
		step.setDecoctionRestL1(15);
		step.setDecoctionRestL2(15);
		step.setDecoctionRestT1(72);
		step.setDecoctionRestT2(100);
		step.setDecoctionRampDown(5);
		step.setEndTemp(step.getStartTemp());

		this.mashDecoctionStepTableModel.addRow(step, true);
		mashModificato();
	}// GEN-LAST:event_btnAdd5ActionPerformed

	private void jScrollPane6MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_jScrollPane6MouseWheelMoved

	}// GEN-LAST:event_jScrollPane6MouseWheelMoved

	private void tblMaltSteps1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_tblMaltSteps1MouseWheelMoved

	}// GEN-LAST:event_tblMaltSteps1MouseWheelMoved

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed

		save(file);
	}// GEN-LAST:event_jButton5ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		File file1 = Utils.pickFileToLoad(new JInternalFrame(), Main.mashDir);
		if (file1 != null) {

			Document doc = Utils.readFileAsXml(file1.toString());
			if (doc == null) {
				return;
			}
			jLabel1.setText(file1.getName());
			Element root = doc.getRootElement();

			if (root.getName().compareToIgnoreCase(XmlTags.RECIPE) == 0) {

				clear();
				@SuppressWarnings("unchecked")
				Iterator iterator = root.getChildren().iterator();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();

					if (elem.getName().compareToIgnoreCase(new MashStep().getClass().getName()) == 0) {
						MashStep step = MashStep.fromXml(elem, null);
						if (step != null) {
							if (step.isDecoctionStep()) {
								this.mashDecoctionStepTableModel.addRow(step, false);
							} else if (step.isInfusionStep()) {
								this.mashInfusionStepTableModel.addRow(step, false);
							} else
								this.mashStepTableModel.addRow(step, false);
						}
					}
				}
				mashModificato();
			} else if (root.getName().compareToIgnoreCase(XmlTags.MASH_DESIGN) == 0) {
				this.file = file1;
				clear();
				@SuppressWarnings("unchecked")
				Iterator iterator = root.getChildren().iterator();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();

					if (elem.getName().compareToIgnoreCase(new MashStep().getClass().getName()) == 0) {
						MashStep step = MashStep.fromXml(elem, null);
						if (step != null) {
							if (step.isDecoctionStep()) {
								this.mashDecoctionStepTableModel.addRow(step, false);
							} else if (step.isInfusionStep()) {
								this.mashInfusionStepTableModel.addRow(step, false);
							} else
								this.mashStepTableModel.addRow(step, false);
						}
					}
				}
				mashModificato();
			}
			jLabel1.setText(file1.getName());
			this.file = file1;
		}
	}// GEN-LAST:event_jButton4ActionPerformed

	public String getDesc() {
		// ixtlanas per desc forum (append a getDes4Forum di recipedata) SOLO
		// MASH STEPS NO DECOZIONE
		String str = "";
		if (this.mashStepTableModel.getRows().size() < 1)
			return str;
		str += "Mash Steps:\n";
		for (MashStep y : this.mashStepTableModel.getRows()) {
			str += " " + y.getNome() + " ";
			str += +y.getEndTemp() + " °C ";
			str += y.getLength() + " minuti\n";
		}
		// for (MashStep y :this.mashDecoctionStepTableModel.getRows()) {
		// str+="Decozione al minuto "+y.getStart()+": ";
		// double F=100.0 * (y.getEndTemp() - y.getStartTemp()) / (100 -
		// y.getStartTemp() - 10);
		// str+="\n\tprelevo il "+(int)F+"% dell'impasto e lo porto da
		// "+y.getStartTemp()+" a "+y.getDecoctionRestT1()+" °C;";
		// str+="\n\tdopo "+y.getDecoctionRestL1()+" minuti porto la decozione a
		// "+y.getDecoctionRestT2()+" °C per "+y.getDecoctionRestL2()+"
		// minuti;";
		// str+="\n\tnel frattempo, mantengo il resto del mash a
		// "+y.getStartTemp()+"°C;";
		// str+="\n\triunisco, mescolo per "+y.getDecoctionRampDown()+"
		// minuti;";
		// str+="\n\tin modo da portare l'intero mash da "+y.getStartTemp()+" a
		// "+y.getEndTemp()+" °C.\n";
		// }
		return str;
	}

	public void log() {
		String str = "";
		for (MashStep y : this.mashStepTableModel.getRows()) {
			str += "Aumento di temperatura al minuto " + y.getStart() + ": ";
			str += "\n\triscaldo da " + y.getStartTemp() + " a " + y.getEndTemp() + " °C in " + y.getRamp()
					+ " minuti;";
			if (this.mashDecoctionStepTableModel.getRows().size() > 0) {
				str += "\n";
				break;
			}
			str += "\n\tmantengo la temperatura di " + y.getEndTemp() + " °C per " + y.getLength() + " minuti;\n";
		}
		for (MashStep y : this.mashDecoctionStepTableModel.getRows()) {
			str += "Decozione al minuto " + y.getStart() + ": ";
			double F = 100.0 * (y.getEndTemp() - y.getStartTemp()) / (100 - y.getStartTemp() - 10);
			str += "\n\tprelevo il " + (int) F + "% dell'impasto e lo porto da " + y.getStartTemp() + " a "
					+ y.getDecoctionRestT1() + " °C;";
			str += "\n\tdopo " + y.getDecoctionRestL1() + " minuti porto la decozione a " + y.getDecoctionRestT2()
					+ " °C per " + y.getDecoctionRestL2() + " minuti;";
			str += "\n\tnel frattempo, mantengo il resto del mash a " + y.getStartTemp() + "°C;";
			str += "\n\triunisco, mescolo per " + y.getDecoctionRampDown() + " minuti;";
			str += "\n\tin modo da portare l'intero mash da " + y.getStartTemp() + " a " + y.getEndTemp() + " °C.\n";
		}

		Msg.showMsg(str, parentFrame);
	}

	private File file = null;

	public void save(File savefile) {

		if (savefile == null) {
			savefile = Utils.pickFileToSave(new JInternalFrame(), Main.mashDir);
		}
		if (savefile == null)
			return;
		Document doc = new Document();
		Element root = new Element(XmlTags.MASH_DESIGN);

		for (MashStep y : this.mashStepTableModel.getRows()) {
			root.addContent(y.toXml());
		}

		for (MashStep y : this.mashDecoctionStepTableModel.getRows()) {
			root.addContent(y.toXml());
		}
		for (MashStep y : this.mashInfusionStepTableModel.getRows()) {
			root.addContent(y.toXml());
		}

		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, savefile, parentFrame);
		jLabel1.setText(savefile.getName());
		this.file = savefile;
	}

	private void jScrollPane5MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_jScrollPane5MouseWheelMoved

	}// GEN-LAST:event_jScrollPane5MouseWheelMoved

	private void tblMaltStepsMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_tblMaltStepsMouseWheelMoved

	}// GEN-LAST:event_tblMaltStepsMouseWheelMoved

	private void btnRem3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRem3ActionPerformed
		if (!tblMaltSteps.isEnabled())
			return;
		this.mashStepTableModel.remRow(this.tblMaltSteps.getSelectedRow());
		mashModificato();
	}// GEN-LAST:event_btnRem3ActionPerformed

	private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAdd3ActionPerformed
		if (!tblMaltSteps.isEnabled())
			return;
		MashStep step = new MashStep(this.ricetta);
		MashStep last = this.mashStepTableModel.getLast();
		step.setLength(new Integer(15));
		step.setRamp(new Integer(5));
		step.setType("Infusione");
		if (last != null) {
			step.setStartTemp(last.getEndTemp());
			step.setEndTemp(last.getEndTemp());
		} else {
			step.setStartTemp(new Integer(50));
			step.setEndTemp(step.getStartTemp());
		}
		this.mashStepTableModel.addRow(step, true);
		mashModificato();
	}// GEN-LAST:event_btnAdd3ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAdd3;
	private javax.swing.JButton btnAdd5;
	private javax.swing.JButton btnAdd6;
	private javax.swing.JButton btnRem3;
	private javax.swing.JButton btnRem4;
	private javax.swing.JButton btnRem5;
	private jmash.component.JMashSpinner coeffX;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButtonImg;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel9;
	private jmash.component.JMashSpinner jMashSpinner1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JToolBar jToolBar2;
	private jmash.component.JVolumeSpinner spnAcqua;
	private jmash.component.JWeightSpinner spnGrani;
	private jmash.component.JWeightSpinner spnGrani1;
	private jmash.component.JTemperatureSpinner tBoil;
	private javax.swing.JTable tblMaltSteps;
	private javax.swing.JTable tblMaltSteps1;
	private javax.swing.JTable tblMaltSteps2;
	// End of variables declaration//GEN-END:variables
	private XYSeriesCollection ds = new XYSeriesCollection();
	private XYSeriesCollection ds2 = new XYSeriesCollection();
	private XYSeriesCollection ds3 = new XYSeriesCollection();
	public XYSeries publicData = new XYSeries("");
	public XYSeries publicPH = new XYSeries("");
	private PanelMashStep roped = null;

	public void mashModificato() {
		this.ds.removeAllSeries();
		this.ds = this.mashStepTableModel.getDataSet(this.ds);
		this.ds = this.mashDecoctionStepTableModel.getDataSet(this.ds);
		this.ds = this.mashInfusionStepTableModel.getDataSet(this.ds);

		this.chart.getXYPlot().getRangeAxis().setUpperBound(105);
		this.chart.getXYPlot().getRangeAxis().setLowerBound(0);
		if (roped != null) {
			XYSeriesCollection rds = roped.getDataSource();
			rds.removeAllSeries();
			rds = this.mashStepTableModel.getDataSet(rds);
			rds = this.mashDecoctionStepTableModel.getDataSet(rds);
			rds = this.mashInfusionStepTableModel.getDataSet(rds);
		}
	}

	public void setReadOnly() {
		jToolBar2.setVisible(false);
		jTabbedPane1.setVisible(false);
		jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);
	}

	public void setDatasource(XYSeriesCollection ds) {
		this.ds = ds;
	}

	public XYSeriesCollection getDatasource() {
		return this.ds;
	}

	public void setLinkedPanel(PanelMashStep roped) {
		this.roped = roped;
		roped.setBrightColors();
	}

	public XYSeriesCollection getDataSource() {
		return this.ds;
	}

	public void setBrightColors() {
		XYAreaRenderer xyAreaRenderer = (XYAreaRenderer) chart.getXYPlot().getRenderer(R1);
		for (int i = 0; i < 100; i++) {
			xyAreaRenderer.setSeriesOutlinePaint(i, ((Color) xyAreaRenderer.getSeriesPaint(i)));
			Color C = (Color) xyAreaRenderer.getSeriesPaint(i);
			if (C != null) {
				float r[] = new float[4];
				r = C.getRGBColorComponents(r);
				xyAreaRenderer.setSeriesPaint(i, new Color(r[0], r[1], r[2], 0.05f));
			}
		}
	}
}
