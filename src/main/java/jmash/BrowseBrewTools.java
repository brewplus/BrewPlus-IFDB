/*
 *  
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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author  Alessandro
 */
public class BrowseBrewTools extends javax.swing.JInternalFrame {

    private List<StyleTreeNode> styleNodes = new ArrayList<StyleTreeNode>();
    private JLabel lblTitle = new JLabel("Ricette su BeerTools");
    private JLabel icon = new JLabel();
    GridLayout gl = new GridLayout();
    private static final int COLUMNS = 3;
    /** Creates new form BrowseBrewTools */
    public BrowseBrewTools() {
        initComponents();
        setBorder(Utils.getDefaultBorder());
        try {
            icon = new JLabel("", new ImageIcon(new URL("http://www.beertools.com/images/new_design/logo.gif")), 0);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        pnl.setBackground(Color.white);
        styleNodes.add(new StyleTreeNode(37, "1A Lite American Lager"));
        styleNodes.add(new StyleTreeNode(38, "1B Standard American Lager"));
        styleNodes.add(new StyleTreeNode(39, "1C Premium American Lager"));
        styleNodes.add(new StyleTreeNode(34, "1D Munich Helles"));
        styleNodes.add(new StyleTreeNode(33, "1E Dortmunder Export"));
        styleNodes.add(new StyleTreeNode(35, "2A German Pilsner (Pils)"));
        styleNodes.add(new StyleTreeNode(36, "2B Bohemian Pilsener"));
        styleNodes.add(new StyleTreeNode(65, "2C Classic American Pilsner"));
        styleNodes.add(new StyleTreeNode(41, "3A Vienna Lager"));
        styleNodes.add(new StyleTreeNode(42, "3B Oktoberfest/Marzen"));
        styleNodes.add(new StyleTreeNode(32, "4A Dark American Lager"));
        styleNodes.add(new StyleTreeNode(30, "4B Munich Dunkel"));
        styleNodes.add(new StyleTreeNode(31, "4C Schwarzbier (Black Beer)"));
        styleNodes.add(new StyleTreeNode(28, "5A Maibock/Helles Bock"));
        styleNodes.add(new StyleTreeNode(27, "5B Traditional Bock"));
        styleNodes.add(new StyleTreeNode(29, "5C Doppelbock"));
        styleNodes.add(new StyleTreeNode(64, "5D Eisbock"));
        styleNodes.add(new StyleTreeNode(45, "6A Cream Ale"));
        styleNodes.add(new StyleTreeNode(68, "6B Blonde Ale"));
        styleNodes.add(new StyleTreeNode(44, "6C Kolsch"));
        styleNodes.add(new StyleTreeNode(46, "6D American Wheat or Rye Bee..."));
        styleNodes.add(new StyleTreeNode(69, "7A Northern German Altbier"));
        styleNodes.add(new StyleTreeNode(47, "7B California Common Beer"));
        styleNodes.add(new StyleTreeNode(43, "7C Dusseldorf Altbier"));
        styleNodes.add(new StyleTreeNode(8, "8A Standard/Ordinary Bitter"));
        styleNodes.add(new StyleTreeNode(9, "8B Special/Best/Premium Bitter"));
        styleNodes.add(new StyleTreeNode(10, "8C Extra Special/Strong Bitter"));
        styleNodes.add(new StyleTreeNode(11, "9A Scottish Light 60/-"));
        styleNodes.add(new StyleTreeNode(12, "9B Scottish Heavy 70/-"));
        styleNodes.add(new StyleTreeNode(13, "9C Scottish Export 80/-"));
        styleNodes.add(new StyleTreeNode(73, "9D Irish Red Ale"));
        styleNodes.add(new StyleTreeNode(18, "9E Strong Scotch Ale"));
        styleNodes.add(new StyleTreeNode(16, "10A American Pale Ale"));
        styleNodes.add(new StyleTreeNode(72, "10B American Amber Ale"));
        styleNodes.add(new StyleTreeNode(21, "10C American Brown Ale"));
        styleNodes.add(new StyleTreeNode(19, "11A Mild"));
        styleNodes.add(new StyleTreeNode(63, "11B Southern English Brown"));
        styleNodes.add(new StyleTreeNode(20, "11C Northern English Brown Ale"));
        styleNodes.add(new StyleTreeNode(23, "12A Brown Porter"));
        styleNodes.add(new StyleTreeNode(22, "12B Robust Porter"));
        styleNodes.add(new StyleTreeNode(74, "12C Baltic Porter"));
        styleNodes.add(new StyleTreeNode(24, "13A Dry Stout"));
        styleNodes.add(new StyleTreeNode(25, "13B Sweet Stout"));
        styleNodes.add(new StyleTreeNode(62, "13C Oatmeal Stout"));
        styleNodes.add(new StyleTreeNode(61, "13D Foreign Extra Stout"));
        styleNodes.add(new StyleTreeNode(75, "13E American Stout"));
        styleNodes.add(new StyleTreeNode(26, "13F Russian Imperial Stout"));
        styleNodes.add(new StyleTreeNode(15, "14A English IPA"));
        styleNodes.add(new StyleTreeNode(76, "14B American IPA"));
        styleNodes.add(new StyleTreeNode(77, "14C Imperial IPA"));
        styleNodes.add(new StyleTreeNode(50, "15A Weizen/Weissbier"));
        styleNodes.add(new StyleTreeNode(51, "15B Dunkelweizen"));
        styleNodes.add(new StyleTreeNode(52, "15C Weizenbock"));
        styleNodes.add(new StyleTreeNode(78, "15D Roggenbier (German Rye)"));
        styleNodes.add(new StyleTreeNode(6, "16A Witbier"));
        styleNodes.add(new StyleTreeNode(4, "16B Belgian Pale Ale"));
        styleNodes.add(new StyleTreeNode(66, "16C Saison"));
        styleNodes.add(new StyleTreeNode(58, "16D Biére de Garde"));
        styleNodes.add(new StyleTreeNode(70, "16E Belgian Specialty Ale"));
        styleNodes.add(new StyleTreeNode(49, "17A Berliner Weisse"));
        styleNodes.add(new StyleTreeNode(40, "17B Flanders Red Ale"));
        styleNodes.add(new StyleTreeNode(1, "17C Flanders Brown Ale/Oud Bruin"));
        styleNodes.add(new StyleTreeNode(59, "17D Straight (Unblended) Lambic"));
        styleNodes.add(new StyleTreeNode(7, "17E Gueuze"));
        styleNodes.add(new StyleTreeNode(60, "17F Fruit Lambic"));
        styleNodes.add(new StyleTreeNode(79, "18A Belgian Blond Ale"));
        styleNodes.add(new StyleTreeNode(2, "18B Belgian Dubbel"));
        styleNodes.add(new StyleTreeNode(3, "18C Belgian Tripel"));
        styleNodes.add(new StyleTreeNode(5, "18D Belgian Golden Strong Ale"));
        styleNodes.add(new StyleTreeNode(71, "18E Belgian Dark Strong Ale"));
        styleNodes.add(new StyleTreeNode(17, "19A Old Ale"));
        styleNodes.add(new StyleTreeNode(57, "19B English Barleywine"));
        styleNodes.add(new StyleTreeNode(56, "19C American Barleywine"));
        styleNodes.add(new StyleTreeNode(53, "20A Fruit Beer"));
        styleNodes.add(new StyleTreeNode(54, "21A Spice, Herb, or Vegetable "));
        styleNodes.add(new StyleTreeNode(80, "21B Winter Specialty Spiced Ale"));
        styleNodes.add(new StyleTreeNode(48, "22A Classic Rauchbier"));
        styleNodes.add(new StyleTreeNode(67, "22B Other Smoked Beer"));
        styleNodes.add(new StyleTreeNode(81, "22C Wood-Aged Beer"));
        styleNodes.add(new StyleTreeNode(55, "23A Specialty Beer"));


        gl.setColumns(COLUMNS);
        gl.setRows(styleNodes.size() / COLUMNS + styleNodes.size() % COLUMNS);
        gl.setVgap(2);
        //this.pnl.setLayout(new GridBagLayout());
        this.pnl.setLayout(gl);
        for (StyleTreeNode node : styleNodes) {
            this.pnl.add(node);
        }
        
        GridLayout tgl = new GridLayout();
        tgl.setColumns(2);
        tgl.setRows(1);
        title.setLayout(tgl);
        title.add(icon);
        title.add(lblTitle);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnl = new javax.swing.JPanel();
        title = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        pnl.setFont(pnl.getFont());

        org.jdesktop.layout.GroupLayout pnlLayout = new org.jdesktop.layout.GroupLayout(pnl);
        pnl.setLayout(pnlLayout);
        pnlLayout.setHorizontalGroup(
            pnlLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 593, Short.MAX_VALUE)
        );
        pnlLayout.setVerticalGroup(
            pnlLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 375, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnl);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        title.setMinimumSize(new java.awt.Dimension(100, 32));
        title.setPreferredSize(new java.awt.Dimension(100, 48));
        getContentPane().add(title, java.awt.BorderLayout.NORTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-619)/2, (screenSize.height-414)/2, 619, 414);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
    private static ImageIcon folderIcon = new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/folder.png"));

    public class StyleTreeNode extends JButton {

        /**
         *
         */
        private static final long serialVersionUID = 9142284801436841375L;
        private String idStile;
        private int stile;
        private String des;
        private JButton jb = new JButton("Torna a Tutti gli stili", new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/up.png")));

        public StyleTreeNode(int stile, String des) {

            super(des, folderIcon);
            this.stile = stile;
            idStile = "" + stile;
            this.des = des;
            setBorderPainted(false);
            setAlignmentX(Component.LEFT_ALIGNMENT);
            this.setBackground(pnl.getBackground());
            setMaximumSize(BTN_DIM);
            setSize(BTN_DIM);
            setPreferredSize(BTN_DIM);
            setHorizontalAlignment(SwingConstants.LEFT);
            this.jb.setMaximumSize(BTN_DIM);
            this.jb.setSize(BTN_DIM);
            this.jb.setPreferredSize(BTN_DIM);
            this.jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    title.removeAll();
                    title.add(icon);
                    title.add(lblTitle);
                    jScrollPane1.getVerticalScrollBar().setValue(0);
                    title.updateUI();
                    pnl.removeAll();
                    pnl.updateUI();
                    for (StyleTreeNode node : styleNodes) {
                        pnl.add(node);
                    }
                    gl.setColumns(COLUMNS);
                    gl.setRows(styleNodes.size()/COLUMNS+styleNodes.size()%COLUMNS);

                    pnl.updateUI();
                }
            });


            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    Thread th = new Thread() {

                        @Override
                        public void run() {
                            setEnabled(false);
                            show();
                            setEnabled(true);
                        }
                    };
                    th.start();
                }
            });
        }

        public String getIdStile() {
            return this.idStile;
        }

        @Override
        public void show() {
            title.removeAll();
            title.add(new JLabel(this.getDes(), this.getIcon(), 0));
            title.add(jb);
            title.updateUI();
            pnl.removeAll();
            JLabel L = new JLabel("Loading...");
            pnl.add(L);
            jScrollPane1.getVerticalScrollBar().setValue(0);
            pnl.updateUI();
            readRecipesForStyle(stile, jb);
            pnl.remove(L);
            pnl.updateUI();
        }

        public String getDes() {
            return this.des;
        }

        public void setDes(String des) {
            this.des = des;
            setText(des);
        }
    }

    public void readRecipesForStyle(int a, JButton jb) {
        try {
            List<RecipeTreeNode> nodes = new ArrayList<RecipeTreeNode>();
            int index = 0;
            boolean flag = true;
            int progressive = 1;
            while (flag) {

                String str = Utils.download("http://www.beertools.com/html/recipe.php?index=" + index + "&sort=recent&order=a&style=" + a).toLowerCase();
                if (str.indexOf(">next</a>") < 0) {
                    flag = false;
                }
                int N = str.indexOf("<p class=\"body_sans\"><a href=\"/html/recipes.php\">recipe library</a>");
                int i = str.indexOf("<table", N);
                int e = str.indexOf("</table>", i);
                str = str.substring(i, e + "</table>".length());

                int f = 0;
                boolean b = true;
                while (b) {
                    int j = str.indexOf("bold\"><a href=\"/html/recipe.php?view=", f);
                    int L = "bold\"><a href=\"/html/recipe.php?view=".length();
                    if (j < 0) {
                        b = false;
                    } else {
                        int z = str.indexOf("</a></span>", j);
                        int y = str.indexOf("<span style=\"font-size: 10px; font-weight: bold\">", z) + "<span style=\"font-size: 10px; font-weight: bold\">".length();
                        String R = str.substring(j + L, z);
                        String A = str.substring(
                                y, str.indexOf("</span>", y)).trim();

                        String num = R.substring(0, R.indexOf(">") - 1);
                        R = R.substring(R.indexOf(">") + 1);

                        String K = "<span style=\"font-size: 12px\"> - ";
                        String K2 = "</span><br>";
                        String U = str.substring(
                                str.indexOf(K, z) + K.length(),
                                str.indexOf(K2, z));
                        if (U.compareToIgnoreCase("all grain") == 0) {
                            U = "AG";
                        } else if (U.compareToIgnoreCase("partial mash") == 0) {
                            U = "PM";
                        } else if (U.compareToIgnoreCase("extract") == 0) {
                            U = "EG";
                        }
                        //System.out.println(R);
                        RecipeTreeNode node = new RecipeTreeNode(new Integer(num), "" + Utils.capitalize(R) + " by " + Utils.capitalize(A), U);
                        nodes.add(node);
                        gl.setColumns(1);
                        gl.setRows(nodes.size());
                        pnl.add(node);
                        progressive++;
                        f = j + L;
                    }
                }
                index += 25;      
            }
            Collections.sort(nodes);
            gl.setColumns(1);
            gl.setRows(nodes.size());
            for (RecipeTreeNode node : nodes) {
                pnl.add(node);
            }

        } catch (Exception ex) {
            Utils.showException(ex, "", BrowseBrewTools.this);
        }
    }
    private Dimension BTN_DIM=new Dimension(240,32);
    private ImageIcon agIcon = new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/ingredients.jpg"));
    private ImageIcon egIcon = new ImageIcon(BrowseHobbyBirra2.class.getResource("/jmash/images/extract.png"));

    public class RecipeTreeNode extends JButton implements Comparable<RecipeTreeNode> {

        private int id;
        private String des;

        public RecipeTreeNode(int id, String des, String T) {

            super(des);
            if (T.equalsIgnoreCase("AG")) {
                setIcon(agIcon);
            } else {
                setIcon(egIcon);
            }
            this.id = id;
            this.des = des;
            this.setBorderPainted(false);
            this.setBackground(pnl.getBackground());
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setMaximumSize(BTN_DIM);
            setSize(BTN_DIM);
            setPreferredSize(BTN_DIM);
            setHorizontalAlignment(SwingConstants.LEFT);

            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread th = new Thread() {

                        @Override
                        public void run() {
                            setEnabled(false);
                            show();
                            setEnabled(true);
                        }
                    };
                    th.start();
                }
            });
        }

        @Override
        public int compareTo(RecipeTreeNode e) {
            return des.compareTo(e.getDes());
        }

        @Override
        public void show() {
            try {
                parserBeerTool(id, des);
            } catch (Exception ex) {
                Utils.showException(ex, "Errore nella lettura della ricetta", BrowseBrewTools.this);
            }
        }

        public String getDes() {
            return this.des;
        }

        public void setDes(String des) {
            this.des = des;
            setText(des);
        }
    }

    public static void parserBeerTool(int n, String des) throws Exception {
        String str = null;
        RecipeData rd = new RecipeData();
        try {
            str = Utils.download("http://www.beertools.com/html/recipe.php?view=" + n + "&fvu=liters&u=metric&fv=23").toLowerCase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        {
            // find efficiency
            String K = "<span class=\"label\">mash efficiency";
            int i = str.indexOf(K);
            String k2 = "<span class=\"value\">";
            int e = str.indexOf(k2, i) + k2.length();
            String eff = str.substring(e, str.indexOf("%", e)).trim();
            rd.setEfficienza(new Double(eff).intValue());
        }

        {
            // find efficiency
            String K = "<span class=\"label\">subcategory<a href=\"javascript:help(26)\">";
            int i = str.indexOf(K);
            String k2 = "<span class=\"value\">";
            int e = str.indexOf(k2, i) + k2.length();
            String bsdes = str.substring(e, str.indexOf("</span></td>", e)).trim();
            BrewStyle bs = Main.getBrewStyleByDes(bsdes);
            //rd.setStyle(bs);
        }
        int N = str.indexOf("src=\"/images/ingrdnts.gif\"");
        int i = str.substring(0, N).lastIndexOf("<table");
        int e = str.indexOf("</table>", i);
        str = str.substring(i, e + "</table>".length());
        str = str.replace("<td align=\"right\">", "");
        str = str.replace("<td align=\"left\">", "");
        str = str.replace("<span class=\"value\">", "");
        str = str.replace("</span></td>", "");
        str = str.replace("<td align=\"right\" valign=\"top\">", "");
        str = str.replace("<td align=\"left\" valign=\"top\">", "");
        str = str.replace("<span class=\"caption\">", "");
        str = str.replace("</span> <a href=\"ingredients.php?view=", " ");
        str = str.replace("\">info</a>", "");
        str = str.replace("<span class=\"label\">", "");
        str = str.replace("</tr>", "");
        str = str.replace("<tr>", "");


        str = str.replace("\t", " ");
        str = str.replace("\r", "\n");
        while (str.indexOf("  ") >= 0) {
            str = str.replace("  ", " ");
        }
        str = str.replace(" \n", "\n");
        while (str.indexOf("\n\n") >= 0) {
            str = str.replace("\n\n", "\n");
        }

//        str=str.replace("","");
//        str=str.replace("","");
//        str=str.replace("","");

//        System.out.println(str);
        String s[] = str.split("\n");
        String w = "";

        List<Malt> malts = new ArrayList<Malt>();
        List<Hop> hops = new ArrayList<Hop>();
        List<Yeast> yeasts = new ArrayList<Yeast>();
        for (int j = 0; j < s.length; j++) {
            String S = s[j];
//	    System.out.println(S);
            if (S.indexOf("table") < 0 && S.indexOf("src=\"/images/ingrdnts.gif\"") < 0) {
                if (S.indexOf("grains#") >= 0) {
                    String nome = S.substring(1, S.indexOf("grains#")).trim();
                    MaltType T = Main.getMaltTypeByDes(nome);
                    Malt m;
                    if (T != null) {
                        m = new Malt(T);
                    } else {
                        m = new Malt();
                    }
                    m.setNome(nome);
                    m.setGrammi(new Double(w.replace("kg.", "").trim()) * 1000.0);
                    malts.add(m);
                } else if (S.indexOf("adjuncts#") >= 0) {
                    String nome = S.substring(1, S.indexOf("adjuncts#")).trim();
                    MaltType T = Main.getMaltTypeByWords(nome);
                    Malt m;
                    if (T != null) {
                        m = new Malt(T);
                    } else {
                        m = new Malt();
                    }
                    m.setNome(nome);
                    m.setGrammi(new Double(w.replace("kg.", "").trim()) * 1000.0);
                    malts.add(m);
                } else if (S.indexOf("extracts#") >= 0) {
                    String nome = S.substring(1, S.indexOf("extracts#")).trim();
                    MaltType T = Main.getMaltTypeByWords(nome);
                    Malt m;
                    if (T != null) {
                        m = new Malt(T);
                    } else {
                        m = new Malt();
                    }
                    m.setNome(nome);
                    m.setGrammi(new Double(w.replace("kg.", "").trim()) * 1000.0);
                    m.setForma("estratto");
                    malts.add(m);
                } else if (S.indexOf("yeast#") >= 0) {
                    Yeast m = new Yeast();
                    m.setNome(S.substring(1, S.indexOf("yeast#")).trim());
                    yeasts.add(m);

                } else if (S.indexOf("(not included in calculations)") >= 0) {
                    String nome = S.substring(1, S.indexOf("</span><span class=")).trim();
                    HopType T = Main.getHopTypeByDes(nome);
                    Hop m;
                    if (T != null) {
                        m = new Hop(T);
                    } else {
                        m = new Hop();
                    }
                    m.setNome(nome);
                    m.setGrammi(0.0);
                    m.setAlfaAcidi(0.0);
                    m.setBoilTime(0);
                    m.setForma("Altro");
                    hops.add(m);
                } else if (S.indexOf("hops#") >= 0) {
                    Hop m;
                    String nome = S.substring(1, S.indexOf("(")).trim();
                    HopType T = Main.getHopTypeByDes(nome);
                    if (T != null) {
                        m = new Hop(T);
                    } else {
                        m = new Hop();
                    }
                    m.setNome(nome);
                    m.setGrammi(new Double(w.replace("g.", "").trim()));
                    if (S.indexOf("used as dry hop") < 0) {
                        String time = S.substring(S.indexOf("boiled") + "boiled".length(), S.indexOf("min")).trim();
                        if (time.startsWith(".")) {
                            time = "0";
                        }
			try{
                        m.setBoilTime(new Integer(time));
			}
			catch(NumberFormatException ex){
			    m.setBoilTime(new Double(time).intValue());
			}
                    } else {
                        m.setBoilTime(0);
                        m.setUso("dry");
                    }
                    m.setForma(
                            S.substring(S.indexOf("(") + "(".length(), S.indexOf(",")).trim());
                    m.setAlfaAcidi(new Double(
                            S.substring(S.indexOf(",", S.indexOf("(")) + ",".length(), S.indexOf("%aa")).trim()));
                    hops.add(m);
                } else {
                    w = S;
                }
            }
        }
        rd.setMalts(malts);
        rd.setHops(hops);
        rd.setYeasts(yeasts);
        rd.setNome(des);
        Ricetta R = new Ricetta();
        R.fromRecipeData(rd);
        Main.gui.addFrame(R);
    }

    public GridBagConstraints newConstraint(int i) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new java.awt.Insets(2, 2, 2, 2);
        c.gridx = i % 1;
        c.gridy = i / 1;
        return c;
    }
}
