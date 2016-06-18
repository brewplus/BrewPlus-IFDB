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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jmash.schema.bjcp.Styleguide;

/**
 *
 * @author Alessandro, Ixtlanas
 */
public class Utils {


    private static Logger LOGGER = Logger.getLogger(Utils.class);

    /** Creates a new instance of Utils */
    public Utils() {
    }

    public static String ConvertMinutesTo2Digits(int minutes) {
        if (minutes < 10)
            return "0" + minutes;
        else
            return "" + minutes;
    }

    public static String getBase64FromIcon(ImageIcon myImageIcon) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(myImageIcon);
            byte[] theBytes = baos.toByteArray();
            StringBuffer buf = new StringBuffer();
            for (byte b : theBytes) {
                buf.append((char) b);
            }
            return buf.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public static Image getImageFromBase64(String base64text) {
        // prende base64 xml e ritorna immagine
        BufferedImage img = null;
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.decode(base64text)));
        } catch (Exception ex) {
            return img;
        }
    }

    public static String getBase64FromImageFile(String ImagePathName) {
        // legge pic e converte in base64 per xml
        try {
            InputStream is = new Base64.InputStream(new FileInputStream(ImagePathName), Base64.ENCODE);
            java.util.Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";

        } catch (Exception ex) {
            return "";
        }
    }

    public static BufferedImage resizeImage(final Image image, int width, int height) {
        // non utilizzata
        // prende immagine con dimensioni e ritorna nuova immagine
        // ridimensionata
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    public static Image getQRCodeFromBase64(String qrcodeBase64, int px) {
        // ritorno un'immagine qrcdoe dall'encoding a b64
        BufferedImage img = null;
        try {
            BitMatrix matrix = null;
            int h = px;
            int w = px;
            com.google.zxing.Writer writer = new QRCodeWriter();
            matrix = writer.encode(qrcodeBase64, com.google.zxing.BarcodeFormat.QR_CODE, w, h);
            MatrixToImageWriter.writeToStream(matrix, "png", new FileOutputStream(new File("pics/qrcode.tmp")));
            img = ImageIO.read(new File("pics/qrcode.tmp"));
            return img;
        } catch (Exception ex) {
            return img;
        }
    }

    public static Border getDefaultBorder() {
        return new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true);
    }

    public static double BAR2PSI(double bar) {
        return bar * 14.503773800722;
    }

    public static double PSI2BAR(double psi) {
        return psi * 0.0689475728;
    }

    public static double C2F(double c) {
        return c * 9 / 5 + 32;
    }

    public static double F2C(double f) {
        return (f - 32) * 5 / 9;
    }

    public static double kgToPound(double kg) {
        return kg * 2.20462262;
    }

    public static double gramsToPound(double grams) {
        return grams * 2.20462262 / 1000.0;
    }

    public static double poundToKg(double pounds) {
        return pounds * 0.45359237;
    }

    public static double poundsToGrams(double pounds) {
        return pounds * 1000 * 0.45359237;
    }

    public static double gramsToOunces(double G) {
        return 0.0352739619 * G;
    }

    public static double ouncesToGrams(double O) {
        return 28.3495231 * O;
    }

    public static double poundsToOunces(double P) {
        return 16 * P;
    }

    public static double ouncesToPounds(double O) {
        return 0.0625 * O;
    }

    public static double litToGal(double lit) {
        return lit * 0.264172051;
    }

    public static double galToLit(double gal) {
        return gal * 3.7854118;
    }

    public static double srmToEbc(double srm) {
        /**
         * http://en.wikipedia.org/wiki/Standard_Reference_Method
         * http://www.beercolor.com/FEQs.htm
         */
        if (Main.config.getEbcNewMethod())
            return 1.97 * srm;
        return 2.65 * srm - 1.2;
        // return (srm-0.46)/0.375;
        // return 2.65 * srm - 1.2;
    }

    public static double ebcToSrm(double ebc) {
        double srm;
        /**
         * http://en.wikipedia.org/wiki/Standard_Reference_Method
         * http://www.beercolor.com/FEQs.htm
         */
        if (Main.config.getEbcNewMethod())
            return ebc * 0.508;
        return 0.377 * ebc + 0.45;
    }

    public static Element toXml(Object obj, String campi[]) throws Exception {
        return toXml(obj, campi, null);
    }

    private static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public static Element toXml(Object obj, String campi[], String elementName) throws Exception {
        if (elementName == null) {
            elementName = obj.getClass().getName();
        }
        Element elem = new Element(elementName);
        for (int i = 0; i < campi.length; i++) {
            Class<? extends Object> cl = obj.getClass();
            Method m = cl.getMethod("get" + ("" + campi[i].charAt(0)).toUpperCase() + campi[i].substring(1));
            Object res = m.invoke(obj);
            if (res != null) {
                if (res instanceof java.util.Date)
                    elem.setAttribute(campi[i], SDF.format((java.util.Date) res));
                else
                    elem.setAttribute(campi[i], "" + res);
            }
        }
        return elem;
    }

    public static Object fromXml(Object obj, String campi[], Element elem) throws Exception {
        return fromXml(obj, campi, elem, null);
    }

    public static Object fromXml(Object obj, String campi[], Element elem, String elementName) throws Exception {

        Class<? extends Object> cl = obj.getClass();
        @SuppressWarnings("unchecked")
        Iterator it = elem.getAttributes().iterator();
        if (elementName == null) {
            elementName = obj.getClass().getName();
        }
        if (elem.getName().compareToIgnoreCase(elementName) != 0) {
            return null;
        }
        while (it.hasNext()) {
            Attribute att = (Attribute) it.next();
            for (int i = 0; i < campi.length; i++) {
                String campo = campi[i];
                if (att.getName().compareToIgnoreCase(campo) == 0) {

                    Method g = cl.getMethod("get" + ("" + campi[i].charAt(0)).toUpperCase() + campi[i].substring(1));
                    Method m = cl.getMethod("set" + ("" + campi[i].charAt(0)).toUpperCase() + campi[i].substring(1),
                            g.getReturnType());
                    Class<? extends Object> ret = g.getReturnType();
                    if (ret.equals(String.class)) {
                        m.invoke(obj, att.getValue());
                    }
                    if (ret.equals(java.util.Date.class)) {
                        m.invoke(obj, SDF.parse(att.getValue()));
                    }
                    if (att.getValue().compareTo("null") == 0) {
                        att.setValue("");
                    }
                    try {
                        String S = att.getValue();
                        S = S.replace(",", ".");
                        if (S.equals("."))
                            S = "";
                        if (S.compareTo("") != 0) {
                            if (ret.equals(Integer.class)) {
                                m.invoke(obj, new Integer(S));
                            }
                            if (ret.equals(Double.class)) {
                                m.invoke(obj, new Double(S));
                            }
                            if (ret.equals(Long.class)) {
                                m.invoke(obj, new Long(S));
                            }
                            if (ret.equals(Float.class)) {
                                m.invoke(obj, new Float(S));
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error("" + m);
                        showException(e,
                                "Errore nel caricamento del file XML, attributo:" + att + ", " + att.getValue());
                        throw e;
                    }
                }
            }
        }
        return obj;
    }

    public static String capitalize(String s) {
        char chars[] = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static double Baume2SG(double B) {
        return (144.3 / (144.3 - B));
    }

    public static double SG2Baume(double SG) {
        return 144.3 * (1 - 1 / SG);
    }

    public static double Plato2SG(double p) {
        return 1.00001 + 3.8661E-3 * p + 1.3488E-5 * p * p + 4.3074E-8 * p * p * p;
    }

    public static double SG2Plato(double sg) {
        double P = -668.962 + 1262.45 * sg - 776.43 * sg * sg + 182.94 * sg * sg * sg;
        if (P < 0) {
            P = 0;
        }
        return P;
    }

    public static void showException(String msg) {
        showException(new Exception(msg), "");
    }

    public static void showException(Exception ex) {
        showException(ex, "");
    }

    public static void showException(Exception ex, String str) {
        LOGGER.error(ex.getMessage(), ex);
        sendException(ex);
        Msg msg = new Msg(str + "\n" + ex.toString());
        JInternalFrame i = new JInternalFrame();
        i.setLocation(Main.gui.getWidth() / 2 - msg.getWidth() / 2, Main.gui.getHeight() / 2 - msg.getHeight() / 2);
        Main.gui.getDesktop().add(i);
        i.setVisible(true);
        center(msg, i);
        msg.startModal(i);

        i.dispose();
    }

    public static void showMsg(String str, JInternalFrame i) {
        Msg.showMsg(str, i);
    }

    public static void showException(Exception ex, String str, JInternalFrame i) {
        LOGGER.error(ex.getMessage(), ex);
        sendException(ex);
        Msg msg = new Msg(str + "\n" + ex.toString());
        center(msg, i);
        msg.startModal(i);
    }

    public static void showException(Exception ex, JInternalFrame i) {
        showException(ex, "", i);
    }

    public static void sendException(Exception ex) {
        String ST = "";
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            ST += ex.getStackTrace()[i].toString() + "\n";
        }
        send(ex.toString(), ST);
    }

    public static void send(String T, String ST) {
        String data;

        // TO DO -- Apertura bug automatica

        /*
         * try { data = URLEncoder.encode("classe", "UTF-8") + "=" +
         * URLEncoder.encode(T, "UTF-8");
         * 
         * data += "&" + URLEncoder.encode("testo", "UTF-8") + "=" +
         * URLEncoder.encode(ST, "UTF-8"); URL url = new URL("http://" +
         * Main.config.getRemoteRoot() + "/bug.asp?" + data);
         * 
         * URLConnection conn = url.openConnection(); conn.setDoOutput(true);
         * conn.setDoInput(true);
         * 
         * // Get the response BufferedReader rd = new BufferedReader(new
         * InputStreamReader(conn.getInputStream())); String line; String str =
         * ""; while ((line = rd.readLine()) != null) { str += line; }
         * rd.close(); } catch (MalformedURLException e) {
         * LOGGER.error(ex.getMessage(), ex); } catch
         * (UnsupportedEncodingException e) { LOGGER.error(ex.getMessage(), ex);
         * } catch (IOException e) { LOGGER.error(ex.getMessage(), ex); } catch
         * (Exception e) { LOGGER.error(ex.getMessage(), ex); }
         */
    }

    public static Document readFileAsXml(String file) {

        Document doc = null;
        try {
            doc = XMLReader.readXML(file);
        } catch (JDOMException e) {
            LOGGER.error("JDOMException: " + file);
            Utils.showException(e, "Il file " + file + " non corrisponde al formato XML.");
            return null;
        } catch (IOException e) {
            LOGGER.error("IOException: " + file);
            Utils.showException(e, "Il file " + file + " non esiste o non e' leggibile.");
            return null;
        } catch (Exception e) {
            LOGGER.error("Generic Exception: " + file);
            Utils.showException(e, "Il file " + file + " non esiste o non e' leggibile.");
            return null;
        }
        return doc;
    }

    public static void saveXmlAsFile(Document doc, File file, JInternalFrame parent) {
       
        if ("1".equals(System.getProperty("ide"))) {
          if (!file.exists()) {
              // solo per esecuzioni per eclipse/netbeans
              String currentDir = System.getProperty("user.dir");
              String currentParentDir = new File(currentDir).getParent();
              file  = new File(currentParentDir + "/brewplus-ifdb-distr/src/main/resources/distr/" + file.toString());
          }
        }
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

        String xml = outputter.outputString(doc);
        try {

            OutputStream out = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            outputter.output(doc, writer);
            writer.close();
            LOGGER.debug(xml);
        } catch (IOException e) {
            Msg ask = new Msg("Errore: salvataggio non riuscito\n" + e);
            ask.startModal(parent);
            return;
        }
        new Info("Salvataggio eseguito con successo").startModal(parent);
    }

    private static float hsb[] = new float[3];

    public static Color darker(Color c) {
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);
        hsb[2] *= 0.9;
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public static void center(JInternalFrame child, JInternalFrame parent) {
        int x;
        int y;

        Point topLeft = parent.getLocation();
        Dimension parentSize = parent.getSize();

        Dimension mySize = child.getSize();

        if (parentSize.width > mySize.width) {
            x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
        } else {
            x = topLeft.x;
        }

        if (parentSize.height > mySize.height) {
            y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
        } else {
            y = topLeft.y;
        }
        child.setLocation(x, y);
    }

    public static double convertWeight(double w, String from, String to) {
        if (from.equalsIgnoreCase(XmlTags.UNITA_PESO[0])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[1])) {
                return w / 1000;
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[2])) {
                return gramsToOunces(w);
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[3])) {
                return kgToPound(w / 1000);
            }
        } else if (from.equalsIgnoreCase(XmlTags.UNITA_PESO[1])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[0])) {
                return w * 1000;
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[2])) {
                return gramsToOunces(w * 1000);
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[3])) {
                return kgToPound(w);
            }
        } else if (from.equalsIgnoreCase(XmlTags.UNITA_PESO[2])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[0])) {
                return ouncesToGrams(w);
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[1])) {
                return ouncesToGrams(w) / 1000;
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[3])) {
                return ouncesToPounds(w);
            }
        } else if (from.equalsIgnoreCase(XmlTags.UNITA_PESO[3])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[0])) {
                return ouncesToGrams(poundsToOunces(w));
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[1])) {
                return ouncesToGrams(poundsToOunces(w)) / 1000;
            }
            if (to.equalsIgnoreCase(XmlTags.UNITA_PESO[2])) {
                return poundsToOunces(w);
            }
        }
        return w;
    }

    public static double convertVolume(double w, String from, String to) {
        if (from.equalsIgnoreCase(XmlTags.UNITA_VOLUME[0])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_VOLUME[1])) {
                return litToGal(w);
            }
        } else if (from.equalsIgnoreCase(XmlTags.UNITA_VOLUME[1])) {
            if (to.equalsIgnoreCase(XmlTags.UNITA_VOLUME[0])) {
                return galToLit(w);
            }
        }
        return w;
    }

    public static File pickFileToSave(JInternalFrame parent, String dir) {
        return pickFileToSave(parent, dir, ".xml");
    }

    public static File pickFileToSave(JInternalFrame parent, String dir, String ext) {
        PickFile pick = new PickFile(true, dir);
        pick.startModal(parent);
        File file = pick.getSelectedFile();
        if (file == null) {
            return null;
        }
        if (!file.getName().endsWith(ext)) {
            file = new File(file.toString() + ext);
        }
        if (file.exists()) {
            if (Ask.doAsk(parent, "Il file esiste gia', si desidera sovrascriverlo?") == false) {
                return null;
            }
        }
        return file;
    }

    public static File pickFileToLoad(JInternalFrame parent, String dir, String ext) {
        PickFile pick = new PickFile(false, dir, ext);
        pick.setVisible(true);
        pick.startModal(parent);
        File ret = pick.getSelectedFile();
        try {
            pick.setClosed(true);
        } catch (PropertyVetoException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return ret;
    }

    public static File pickFileToLoad(JInternalFrame parent, String dir) {
        return pickFileToLoad(parent, dir, "xml");
    }

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

    public static String getClipboard() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                return text;
            }
        } catch (UnsupportedFlavorException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public static String download(String address) throws Exception {
        if (Main.gui != null)
            Main.gui.setStatusBar("downloading " + address);
        URLConnection conn = null;
        InputStream in = null;
        String R = "";
        try {
            URL url = new URL(address);
            conn = url.openConnection();
            // conn.setRequestProperty("Cookie",
            // "rid=H8phkT5OM&ruvol=2&ruweight=1&ruhops=1&rutemp=1&ruextract=0");
            in = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int numRead;
            long numWritten = 0;

            while ((numRead = in.read(buffer)) != -1) {
                R += new String(buffer, 0, numRead);
            }
        } catch (MalformedURLException ex) {
            Utils.showException(ex);
            throw ex;
        } catch (IOException ex) {
            Utils.showException(ex);
            throw ex;
        }
        return R;
    }

    private static HashMap hm = new HashMap();

    public static void utilizzo(JInternalFrame f) {
        String k = f.getClass().getSimpleName();
        String key = "";
        for (int i = 0; i < k.length(); i++) {
            if (Character.isUpperCase(k.charAt(i)))
                key += k.charAt(i);
        }
        k = key;
        if (hm.containsKey(k)) {
            hm.put(k, new Integer(((Integer) hm.get(k)).intValue() + 1));
        } else
            hm.put(k, new Integer(1));

    }

    public static String utilizzo() {
        String s = "";
        Iterator it = hm.keySet().iterator();
        while (it.hasNext()) {
            String k = (String) it.next();
            s += k + ":" + hm.get(k) + ":";
        }

        return s;
    }

    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static void parseUtilizzo(String util) {
        int i = 0;
        if (util == null || util.length() == 0)
            return;
        String s[] = util.split(":");
        String className = null;
        for (int j = 0; j < s.length; j++) {
            if (className == null)
                className = s[j];
            else {
                Integer N = null;
                try {
                    N = new Integer(s[j]);
                } catch (Exception ex) {
                    N = new Integer(0);
                }
                hm.put(className, N);
                className = null;
            }
        }
    }

    public static final byte[] compress(String str) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zout = new ZipOutputStream(out);
        zout.putNextEntry(new ZipEntry("0"));
        zout.write(str.getBytes());
        zout.closeEntry();
        byte[] compressed = out.toByteArray();
        zout.close();
        return compressed;
    }

    public static final String decompress(byte[] compressed) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry = zin.getNextEntry();
        byte[] buffer = new byte[1024];
        int offset = -1;
        while ((offset = zin.read(buffer)) != -1) {
            out.write(buffer, 0, offset);
        }
        String decompressed = out.toString();
        out.close();
        zin.close();
        return decompressed;
    }

    public static byte[] compress2(String s) {
        byte[] input = s.getBytes();

        // Compressor with highest level of compression
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);

        // Give the compressor the data to compress
        compressor.setInput(input);
        compressor.finish();

        // Create an expandable byte array to hold the compressed data.
        // It is not necessary that the compressed data will be smaller than
        // the uncompressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

        // Compress the data
        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
        }

        // Get the compressed data
        byte[] compressedData = bos.toByteArray();
        return compressedData;
    }

    public final static org.w3c.dom.Document XMLfromString(String xml) {

        org.w3c.dom.Document doc = null;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            LOGGER.error("XML parse error: " + e.getMessage(), e);
            return null;
        } catch (SAXException e) {
            LOGGER.error("Wrong XML file structure: " + e.getMessage(), e);
            return null;
        } catch (IOException e) {
            LOGGER.error("I/O exeption: " + e.getMessage(), e);
            return null;
        }

        return doc;

    }

    public static byte[] buffer(String filename) throws Exception {
        File file = new File(filename);
        InputStream is = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(is);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        dis.close();
        is.close();
        return bytes;
    }

    public static String arr2String(byte[] arr, int start) {
        int offset = 0;
        while (arr[start + offset] != 0)
            offset++;
        return new String(arr, start, offset);
    }

    public static byte arr2Byte(byte[] arr, int start) {

        return arr[start];
    }

    public static float arr2float(byte[] arr, int start) {
        int i = 0;
        int len = 4;
        int cnt = 0;
        byte[] tmp = new byte[len];
        for (i = start; i < (start + len); i++) {
            tmp[cnt] = arr[i];
            cnt++;
        }
        int accum = 0;
        i = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
            accum |= ((long) (tmp[i] & 0xff)) << shiftBy;
            i++;
        }
        return Float.intBitsToFloat(accum);
    }

    public static double arr2Double(byte[] arr, int start) {
        float f = arr2float(arr, start);
        return new Double(f);
    }

    public static class BareBonesBrowserLaunch {
        private static final String errMsg = "Error attempting to launch web browser";

        public void openURL(String url) throws Exception {
            String osName = System.getProperty("os.name");

            if (osName.startsWith("Mac OS")) {
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
                openURL.invoke(null, new Object[] { url });
            } else if (osName.startsWith("Windows"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            else { // assume Unix or Linux
                String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++)
                    if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
                        browser = browsers[count];
                if (browser == null)
                    throw new Exception("Could not find web browser");
                else
                    Runtime.getRuntime().exec(new String[] { browser, url });
            }
        }

    }

    public static Styleguide readBjcpXml(String bjcpStylesXML) {

        JAXBContext jaxbContext;
        Styleguide bjcp = null;
        try {
            jaxbContext = JAXBContext.newInstance(Styleguide.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            if ("1".equals(System.getProperty("ide"))) {
              if (!new File(bjcpStylesXML).exists()) {
              // solo per esecuzioni per eclipse/netbeans
                String currentDir = System.getProperty("user.dir");
                String currentParentDir = new File(currentDir).getParent();
                bjcpStylesXML = currentParentDir + "/brewplus-ifdb-distr/src/main/resources/distr/" + bjcpStylesXML;
              }
            }

            bjcp = (Styleguide) jaxbUnmarshaller.unmarshal(new File(bjcpStylesXML));
            LOGGER.info("Loaded BJCP revision: "
                    + bjcp.getClazz().get(0).getIntroduction().get(0).getRevision().getValue());
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage());
        }

        return bjcp;
    }

    public static String getBJCPHtml(BrewStyle bs) {
        
        ResourceBundle bundle = ResourceBundle.getBundle("jmash/lang", Main.locale);

        StringBuilder sb = new StringBuilder();
        sb.append(
                "<html><head><style>table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 5px;}</style></head><body>");
        sb.append("<h1>" + bs.getDesCategoria() + "</h1><br><br>");
        sb.append("<b>Aroma:</b><br>" + bs.getAroma() + "<br><br>");
        sb.append("<b>"+ bundle.getString("BJCP.Appearance") +":</b><br>" + bs.getAppearance() + "<br><br>");
        sb.append("<b>Gusto:</b><br>" + bs.getFlavor() + "<br><br>");
        sb.append("<b>Palato:</b><br>" + bs.getMouthfeel() + "<br><br>");
        sb.append("<b>Impressioni:</b><br>" + bs.getImpression() + "<br><br>");
        sb.append("<b>Commenti:</b><br>" + bs.getComments() + "<br><br>");
        sb.append("<b>Ingredienti:</b><br>" + bs.getIngredients() + "<br><br>");
        sb.append("<b>Esempi:</b><br>" + bs.getExamples() + "<br><br>");
        sb.append("<b>Note della Categoria:</b><br>" + bs.getCatNotes() + "<br><br>");

        sb.append("<table style=\"width:200\">");
        sb.append("<tr>");
        sb.append("<td><b>IBU</b></td><td>" + bs.getDesIBU().replace(".", ",") + "</td>");
        sb.append("</tr><tr>");
        sb.append("<td><b>OG</b></td><td>" + bs.getDesOG().replace(".", ",") + "</td>");
        sb.append("</tr><tr>");
        sb.append("<td><b>FG</b></td><td>" + bs.getDesFG().replace(".", ",") + "</td>");
        sb.append("</tr></table>");

        sb.append("</body></html>");

        return sb.toString();
    }
}
