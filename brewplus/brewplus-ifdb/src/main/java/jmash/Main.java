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

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import jmash.component.MultiLineCellRenderer;
import jmash.config.ConfigurationManager;
import jmash.config.bean.GeneralConfig;
import jmash.schema.bjcp.Styleguide;
import jmash.utils.BrewplusEnvironment;
import jmash.utils.BundleMessage;
import jmash.utils.Cli;
import jmash.utils.Constants;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);
    public static BundleMessage bundle;
        
    public static Locale locale;
        
	//public static String versioneBrewPlus = "2.1.0";
	public static Integer webVersion;

	
	public static String Nome = "BrewPlus";
	public static String resource_distr = "/brewplus-ifdb-distr/src/main/resources/distr/";
	
	private static BrewplusEnvironment bpenv;

	public static Gui gui;
	public static javax.swing.JDesktopPane desktopPane;
	public static MultiLineCellRenderer multiLineCellRenderer = new MultiLineCellRenderer();
	public static Image chartImage = java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/kettle.jpg"));
	public static Image hopImage = java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/hops.gif"));

	public static ImageIcon hopIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/hops.jpg")));
	public static ImageIcon spiceIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/icon_spices.png")));
	public static ImageIcon clockIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/clock.png")));
	public static ImageIcon maltIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/ingredients.jpg")));
	public static ImageIcon extractIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/extract.png")));
	public static ImageIcon glassColorIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/glass_color.png")));

	public static ImageIcon addIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/edit_add.png")));
	public static ImageIcon remIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/edit_remove.png")));
	public static ImageIcon PieIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/pieicon.png")));

	public static ImageIcon mainIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/BPlusIcon-32.png")));

	public static ImageIcon boilOffIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/steam.png")));
	public static ImageIcon diluiteIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/extract.png")));
	public static ImageIcon uploadIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/upload.png")));
	public static ImageIcon strikeIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/temp.png")));
	public static ImageIcon printIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/fileprint.png")));
	public static ImageIcon checkInventoryIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/chkinventario.png")));
	public static ImageIcon editIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/edit.png")));
	public static ImageIcon xmlIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/xml.png")));
	public static ImageIcon androidIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/android.png")));

	public static ImageIcon allGrainIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/ag.png")));
	public static ImageIcon biabIcon = new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/biab.png")));
	
	
	private static GeneralConfig generalConfig;
	
	
	public static enum BitterBUGU { // metodo di calcolo BU/GU
		TIN, RAG, DAN
	}
        
        
         
	public static MaltType getMaltTypeByWords(String des) {
		des = des.toLowerCase();
		MaltType res = null;
		double best = -1;
		String rxp = "[\\W]|[\\s]";
		String R[] = des.toLowerCase().split(rxp);
		for (MaltType m : getMalti()) {
			String str = m.getNome().toLowerCase();
			String S[] = str.split(rxp);
			double t = -1;
                    //int matches = 0;
                    for (String S1 : S) {
                        for (String R1 : R) {
                            if (S1.equals(R1)) {
                                t++;
                                //matches++;
                            }
                        }
                    }
			if (des.contains("lme") && !m.getNome().toLowerCase().contains("lme"))
				t = -1;
			if (!des.contains("lme") && m.getNome().toLowerCase().contains("lme"))
				t = -1;
			if (des.contains("dme") && !m.getNome().toLowerCase().contains("dme"))
				t = -1;
			if (!des.contains("dme") && m.getNome().toLowerCase().contains("dme"))
				t = -1;
			if (des.contains("dry") && !m.getNome().toLowerCase().contains("dry"))
				t = -1;
			if (!des.contains("dry") && m.getNome().toLowerCase().contains("dry"))
				t = -1;
			if (des.contains("extract") && !m.getNome().toLowerCase().contains("extract"))
				t = -1;
			if (!des.contains("extract") && m.getNome().toLowerCase().contains("extract"))
				t = -1;
			if (t > best) {
				best = t;
				res = m;
			}
		}
		return res;
	}

	public static String getVersione() {
		return Utils.getVersion();
	}


	/** Creates a new instance of Main */
	public Main() {
		try {
			
			bpenv = BrewplusEnvironment.getIstance();
			
		    printInfo();
			readConfig();
			readProfiliImpianto();
			readLuppoli();
			readCategorieMalti();
			readMalti();
			readLieviti();
			readWater();
			readColors();

			Styleguide bjcp = Utils.readBjcpXml(bpenv.getConfigfileName(Constants.XML_BJCP));
			Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_BJCP));
			Element root = doc.getRootElement();
			Gui.brewStylePickerTableModel.setRows(getBJCPStyles(root));
			logger.info("BJCP styles detected");

		} catch (Exception ex) {
			logger.error("Errore critico:" + ex.getMessage(), ex);
		}
		try {
			loadCache();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		gui = new Gui();
		desktopPane = Gui.desktopPane;
		gui.btnUpdate.setVisible(false);
		new File("_runner.jar").delete();

		Utils.parseUtilizzo((String) getFromCache("Main.utilizzo", ""));

		gui.setVisible(true);

		gui.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

		if (generalConfig.getProxyHost() != null)
			System.setProperty("http.proxyHost", generalConfig.getProxyHost());
		if (generalConfig.getProxyPort() != null)
			System.setProperty("http.proxyPort", generalConfig.getProxyPort());

		try {
			update();
			logger.info("Update check");
		} catch (FileNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
	
	private class Option {
	     String flag, opt;
	     public Option(String flag, String opt) { this.flag = flag; this.opt = opt; }
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		
		new Cli(args).parse();		
		
		try {
			AzatothLookAndFeel myLAF = new AzatothLookAndFeel();
			UIManager.setLookAndFeel(myLAF);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return;
		}
		
		

		new Main();
		try {
			if (args.length > 0 && args[0].compareToIgnoreCase("showNews") == 0) {
				String str = generalConfig.getRemoteRoot();
				if (!str.startsWith("http://"))
					str = "http://" + str;
				if (!str.endsWith("/"))
					str = str + "/";
				gui.addFrame(new ViewHtml(Utils.download(str + "news.html")));
			}
		} catch (Exception ex) {
		}
	}



	public static void readLuppoli() throws Exception {
		Gui.hopPickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_HOPS));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();

		if (root.getName().compareToIgnoreCase("hops") == 0) {
			logger.info("hops detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				HopType type = HopType.fromXml((Element) iterator.next());
				if (type != null) {
					Gui.hopPickerTableModel.addRow(type);
				}

			}
		}
		Collections.sort(Gui.hopPickerTableModel.getRows());
	}

	public static List<MaltType> getMalti() {
		return Gui.maltPickerTableModel.getRows();
	}

	public static MaltType getMaltTypeByDes(String des) {
		for (MaltType m : getMalti()) {
			if (m.getNome().toLowerCase().startsWith(des.toLowerCase())
					|| des.toLowerCase().startsWith(m.getNome().toLowerCase()))
				return m;
		}
		return null;
	}

	public static BrewStyle getBrewStyleByDes(String des) {
		for (BrewStyle m : Gui.brewStylePickerTableModel.getRows()) {
			if (m.getNome() != null && m.getNome().compareToIgnoreCase(des) == 0)
				return m;
		}
		return null;
	}

	public static HopType getHopTypeByDes(String des) {
		for (HopType m : Gui.hopPickerTableModel.getRows()) {
			if (m.getNome().compareToIgnoreCase(des) == 0)
				return m;
		}
		return null;
	}
  
  public static void readCategorieMalti() throws Exception {
		Gui.maltCategoryPickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_CATEGORIES));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("maltsCategories") == 0) {
			logger.info("malts categories detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				Element maltCategory = (Element) iterator.next();
				MaltCategory category = MaltCategory.fromXml(maltCategory);
				if (category != null) {
					Gui.maltCategoryPickerTableModel.addRow(category);
				}
			}
		}
		Collections.sort(Gui.maltCategoryPickerTableModel.getRows());
	}
  
  public static void readProfiliImpianto() throws Exception {
		Gui.breweryProfilePickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_BREPROFILE));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("breweryProfiles") == 0) {
			logger.info("brewery profiles detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				Element breweryProfile = (Element) iterator.next();
				BreweryProfile profile = BreweryProfile.fromXml(breweryProfile);
				if (profile != null) {
					Gui.breweryProfilePickerTableModel.addRow(profile);
				}
			}
		}
		Collections.sort(Gui.breweryProfilePickerTableModel.getRows());
	}

	public static void readMalti() throws Exception {
		Gui.maltPickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_MALT));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("malts") == 0) {
			logger.info("malts detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				Element malt = (Element) iterator.next();
				MaltType type = MaltType.fromXml(malt);
				if (type != null) {
					Gui.maltPickerTableModel.addRow(type);
				}
			}
		}
		Collections.sort(Gui.maltPickerTableModel.getRows());
	}

	public static void readLieviti() throws Exception {
		Gui.yeastPickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_YEAST));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("yeasts") == 0) {
			logger.info("yeasts detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				Element malt = (Element) iterator.next();
				try {
					YeastType type = (YeastType) Utils.fromXml(new YeastType(), YeastType.getCampiXml(), malt);
					if (type != null) {
						Gui.yeastPickerTableModel.addRow(type);
					}
				} catch (Exception ex) {
					Utils.showException(ex);
					return;
				}
			}
		}
	}

	public static void readWater() throws Exception {
		Gui.waterPickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_WATER));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("waters") == 0) {
			logger.info("waters detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				Element malt = (Element) iterator.next();
				try {
					WaterProfile type = (WaterProfile) Utils.fromXml(new WaterProfile(), WaterProfile.campiXml, malt);
					if (type != null) {
						Gui.waterPickerTableModel.addRow(type);
					}
				} catch (Exception ex) {
					Utils.showException(ex);
					return;
				}
			}
		}
		Collections.sort(Gui.waterPickerTableModel.getRows());
	}

	public static void readConfig()  {

		ConfigurationManager cm = new ConfigurationManager();
		logger.info("config detected");
		
		generalConfig = cm.getIstance().getGeneralConfig();
		
		
		locale = new Locale(generalConfig.getLocale().split("_")[0],generalConfig.getLocale().split("_")[1]);
		Locale.setDefault(locale);
        ResourceBundle.clearCache();
        logger.info("Setting localization: " + generalConfig.getLocale());
		bundle = new BundleMessage(java.util.PropertyResourceBundle.getBundle("jmash/lang"));
		
	}
	
	public static void printInfo()  {
	    logger.info("java.version: " + System.getProperty("java.version"));
	    logger.info("java.specification.version: " + System.getProperty("java.specification.version"));
	    logger.info("java.vm.name: " + System.getProperty("java.vm.name"));
	    logger.info("java.vm.version: " + System.getProperty("java.vm.version"));
	    logger.info("java.runtime.version: " + System.getProperty("java.runtime.version"));
	    logger.info("os.name: " + System.getProperty("os.name"));
	    logger.info("os.version: " + System.getProperty("os.version"));
	}

	public static void readStili() throws Exception {
		Gui.brewStylePickerTableModel.emptyRows();
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_STYLES));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("styles") == 0) {
			logger.info("styles detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			while (iterator.hasNext()) {
				BrewStyle type = BrewStyle.fromXml((Element) iterator.next());
				Gui.brewStylePickerTableModel.addRow(type);
			}
		}
	}

	public static BinaryTreeNode treeColor;

	public static void readColors() throws Exception {
		Document doc = Utils.readFileAsXml(bpenv.getConfigfileName(Constants.XML_COLORS));
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		if (root.getName().compareToIgnoreCase("lovibondToRGB") == 0) {
			logger.info("colors detected");
			@SuppressWarnings("unchecked")
			Iterator iterator = root.getChildren().iterator();
			Srm2Rgb dao = new Srm2Rgb();
			while (iterator.hasNext()) {
				try {
					Srm2Rgb type = (Srm2Rgb) Utils.fromXml(dao, Srm2Rgb.getCampiXml(), (Element) iterator.next());
					type.setEbc(Utils.srmToEbc(type.getSrm()));
					if (treeColor == null) {
						treeColor = new BinaryTreeNode(type.getEbc(), new Color(type.getR(), type.getG(), type.getB()));
					} else {
						treeColor.add(type.getEbc(), new Color(type.getR(), type.getG(), type.getB()));
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
		}
	}

	public static Map<Double, Color> hmRGB = new HashMap<>();

	public static class BinaryTreeNode {
		public Double data;
		public Color color;
		public BinaryTreeNode L, R;

		public BinaryTreeNode() {
		}

		public BinaryTreeNode(Double d, Color c) {
			this.data = d;
			this.color = c;
		}

		public void add(Double d, Color c) {
			if (d.compareTo(data) > 0) {
				if (this.R == null) {
					this.R = new BinaryTreeNode(d, c);
				} else {
					this.R.add(d, c);
				}
			}
			if (d.compareTo(data) < 0) {
				if (this.L == null) {
					this.L = new BinaryTreeNode(d, c);
				} else {
					this.L.add(d, c);
				}
			}
		}

		public static Color blend(Color a, Color b) {
			int r = a.getRed() + b.getRed();
			int g = a.getGreen() + b.getGreen();
			int blue = a.getBlue() + b.getBlue();

			return new Color(r / 2, g / 2, blue / 2);
		}

		public Color srmToRgb(Double d) {
			return ebcToRgb(Utils.srmToEbc(d));
		}

		public Color ebcToRgb(Double d) {
			if (hmRGB.containsKey(d))
				return (Color) hmRGB.get(d);

			if (d.equals(this.data)) {
				hmRGB.put(d, this.color);
				return this.color;
			}
			if (d.compareTo(this.data) > 0) {
				if (this.R == null) {
					hmRGB.put(d, this.color);
					return this.color;
				} else if (this.R.data.compareTo(d) > 0) {
					Color res = blend(this.color, this.R.color);
					if (res != null)
						hmRGB.put(d, res);
					return res;
				} else {
					Color res = (Color) this.R.ebcToRgb(d);
					if (res != null)
						hmRGB.put(d, res);
					return res;
				}
			}
			if (d.compareTo(this.data) < 0) {
				if (this.L == null) {
					hmRGB.put(d, this.color);
					return this.color;
				} else if (this.L.data.compareTo(d) < 0) {
					Color res = blend(this.color, this.L.color);
					if (res != null)
						hmRGB.put(d, res);
					return res;
				} else {
					Color res = (Color) this.L.ebcToRgb(d);
					if (res != null)
						hmRGB.put(d, res);
					return res;
				}
			}
			hmRGB.put(d, this.color);
			return this.color;
		}
	}

	public static void readversionfromweb(String address) {
		try {
			String content = "";
			URL url = new URL(address);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				content += inputLine;
			}
			in.close();
			webVersion = Integer.parseInt(content);
		} catch (Exception ex) {
                    
		}
	}

	private void downloadweb(String from, String to) {
		// alternativa a download ma non funzia in runtime lo stesso
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(from).openStream());
			FileOutputStream fos = new FileOutputStream(to);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte data[] = new byte[1024];
			while (in.read(data, 0, 1024) >= 0) {
				bout.write(data);
			}
			bout.close();
			in.close();
		} catch (Exception ex) {
		}
	}

	private void download(String from, String _to, int size) {
		try {
			OutputStream out = null;
			URLConnection conn = null;
			InputStream in = null;
			URL url = new URL(from);

			out = new BufferedOutputStream(new FileOutputStream(_to + "_"));
			conn = url.openConnection();
			in = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;
			int d = 0;

			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}
			out.close();
			in.close();
			copyFile(_to + "_", _to);
			File f = new File(_to + "_");
			f.delete();
		} catch (Exception ex) {

		}
	}

	@SuppressWarnings("resource")
	public static void copyFile(String source, String dest) throws IOException {
		FileChannel in = null, out = null;
		try {
			in = new FileInputStream(new File(source)).getChannel();
			out = new FileOutputStream(new File(dest)).getChannel();
			in.transferTo(0, in.size(), out);

		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
	}

	@SuppressWarnings("unused")
	public void update() throws FileNotFoundException, IOException {
		boolean ret = false;
		System.setProperty("http.proxyHost", generalConfig.getProxyHost());
		System.setProperty("http.proxyPort", generalConfig.getProxyPort());

		String remoteRoot = generalConfig.getRemoteRoot();
		if (remoteRoot == null)
			remoteRoot = "http://www.ilforumdellabirra.net/";

		if (!remoteRoot.startsWith("http://"))
			remoteRoot = "http://" + remoteRoot;
		if (!remoteRoot.endsWith("/"))
			remoteRoot += "/";

		if (!remoteRoot.endsWith("/"))
			remoteRoot += "/";

		try {
			readversionfromweb(remoteRoot + "version.v");
		} catch (Exception ex) {
		}

		File fVersione = new File("config/version.v");
		int versione = 0;
		if (fVersione.exists()) {
			FileReader in = new FileReader(fVersione);

			char[] c = new char[2048];
			while (in.read(c) > 0) {
				try {
					versione = Integer.parseInt(String.valueOf(c).trim());
				} catch (NumberFormatException nfex) {
					versione = 0;
				}
			}
			in.close();
		}

		if (false) {
			gui.btnUpdate.setVisible(true);
			gui.btnUpdate.setToolTipText("Trovata una nuova versione");
			Thread thread = new Thread() {
				javax.swing.border.LineBorder B = new javax.swing.border.LineBorder(new java.awt.Color(250, 0, 0), 2,
						true);

				@Override
				public void run() {
					while (true) {
						try {
							gui.btnUpdate.setBorder(null);
							sleep(1000);
							gui.btnUpdate.setBorder(B);
							sleep(300);
						} catch (InterruptedException ex) {
							logger.error(ex.getMessage(), ex);
						}
					}
				}
			};
			thread.start();
		}
	}

	private static Double toDouble(Element el) {
		if (el == null)
			return null;
		if (el.getValue() == null)
			return null;
		if (el.getValue().length() == 0)
			return null;
		return new Double(el.getValue());
	}

	private static Element getChild(Element E, String[] d) {
		for (int i = 0; i < d.length; i++) {
			if (E == null)
				return null;
			E = E.getChild(d[i]);
		}
		return E;
	}

	public static List<BrewStyle> bjcpStyles = new ArrayList<BrewStyle>();

	private static List<BrewStyle> getBJCPStyles(Element el) {
		if (el.getName().compareTo("subcategory") == 0) {
			BrewStyle S = new BrewStyle();
			S.setNome(el.getChild("name").getValue().replaceAll("\n", ""));

			S.setImpression(readBjcpElement(el, "impression"));
			S.setWater(readBjcpElement(el, "water"));
			S.setMalt(readBjcpElement(el, "malts"));
			S.setHops(readBjcpElement(el, "hops"));
			S.setSpices(readBjcpElement(el, "spices"));
			S.setYeast(readBjcpElement(el, "yeasts"));
			S.setAroma(readBjcpElement(el, "aroma"));
			S.setComments(readBjcpElement(el, "comments"));
			S.setIngredients(readBjcpElement(el, "ingredients"));
			S.setMouthfeel(readBjcpElement(el, "mouthfeel"));
			S.setAppearance(readBjcpElement(el, "appearance"));
			S.setFlavor(readBjcpElement(el, "flavor"));
			S.setImpression(readBjcpElement(el, "impression"));
			S.setExamples(readBjcpElement(el, "examples"));

			S.setNumero(el.getAttribute("id").getValue());
			S.setCategoria(el.getParentElement().getChild("name").getValue().replaceAll("\n", ""));
			S.setcodiceCategoria(el.getParentElement().getAttributeValue("id"));

			S.setCatNotes(el.getParentElement().getChild("notes").getValue().replaceAll("\n", ""));

			S.setIbuMin(toDouble(getChild(el, new String[] { "stats", "ibu", "low" })));
			S.setIbuMax(toDouble(getChild(el, new String[] { "stats", "ibu", "high" })));

			S.setFgMin(toDouble(getChild(el, new String[] { "stats", "fg", "low" })));
			S.setFgMax(toDouble(getChild(el, new String[] { "stats", "fg", "high" })));

			S.setOgMin(toDouble(getChild(el, new String[] { "stats", "og", "low" })));
			S.setOgMax(toDouble(getChild(el, new String[] { "stats", "og", "high" })));

			S.setColorMin(toDouble(getChild(el, new String[] { "stats", "srm", "low" })));
			S.setColorMax(toDouble(getChild(el, new String[] { "stats", "srm", "high" })));

			bjcpStyles.add(S);
		}

		List<Element> children = el.getChildren();
		for (Element E : children) {
			getBJCPStyles(E);
		}
		return bjcpStyles;
	}

	private static String readBjcpElement(Element el, String elName) {

		String eleStrVal = "";

		if (el.getChild(elName) != null) {
			eleStrVal = el.getChild(elName).getValue().replaceAll("\n", "");
		}

		return eleStrVal;
	}

	private static Map<String, Object> cache = new HashMap<>();

	public static void removeFromCache(String k) {
		// utilizzato per eliminare elenco ricette aperte
		cache.remove(k);
	}

	public static void putIntoCache(String k, Object o) {
		cache.put(k, o);
	}

	public static Object getFromCache(String k, Object defaultValue) {
		Object o = cache.get(k);
		if (o == null)
			return defaultValue;
		return o;
	}

	public static Integer getFromCache(String k, Integer defaultValue) {
		Object o = cache.get(k);
		if (o == null)
			return defaultValue;
		if (o instanceof Double)
			return ((Double) o).intValue();
		Integer i = (Integer) o;
		return i;
	}

	public static Double getFromCache(String k, Double defaultValue) {
		Object o = cache.get(k);
		if (o == null)
			return defaultValue;
		if (o instanceof Integer)
			return (double) ((Integer) o).intValue();
		Double d = (Double) o;
		return d;
	}

	@SuppressWarnings("element-type-mismatch")
	public static void saveCache() {
		Document doc = new Document();
		Iterator it = cache.keySet().iterator();
		Element root = new Element("cache");
		while (it.hasNext()) {
			Object k = it.next();
			Element el = new Element(k.toString());
			el.setText(cache.get(k).toString());
			el.setAttribute("class", cache.get(k).getClass().getCanonicalName());
			root.addContent(el);
		}
		doc.setRootElement(root);
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			FileWriter writer = new FileWriter("cache.xml");
			outputter.output(doc, writer);
			writer.close();
		} catch (IOException e) {
		}
	}

	public static void loadCache() {
		Document doc = Utils.readFileAsXml("cache.xml");
		if (!doc.hasRootElement())
			return;
		Element root = doc.getRootElement();
		Iterator iterator = root.getChildren().iterator();
		while (iterator.hasNext()) {
			Element elem = (Element) iterator.next();
			Attribute A = elem.getAttribute("class");
			String value = elem.getValue();
			String name = elem.getName();
			try {
				Class c = Class.forName(A.getValue());
				Constructor k = c.getConstructor(new Class[] { String.class });
				Object o = k.newInstance(new Object[] { value });
				putIntoCache(name, o);
			} catch (IllegalArgumentException | SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
	}
}
