/*
 * BeerXMLReader.java
 *
 * Created on 13 agosto 2008, 15.53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import jmash.Hop;
import jmash.Main;
import jmash.Malt;
import jmash.MashStep;
import jmash.RecipeData;
import jmash.Ricetta;
import jmash.Utils;
import jmash.Yeast;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Alessandro
 */
public class BeerXMLReader {
    
    /** Creates a new instance of BeerXMLReader */
    public BeerXMLReader() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	File file=Utils.pickFileToLoad(new JInternalFrame(), (String)Main.getFromCache("recipe.dir",Main.recipeDir));
	if(file!=null) {
	    //Main.putIntoCache("recipe.dir",file.getAbsolutePath());
	    
	    Document doc=Utils.readFileAsXml(file.getAbsolutePath());
	    //Document doc=Utils.readFileAsXml("C:/mash.xml");
	    Element root=doc.getRootElement();
	    //System.out.println(root.getName());
	    for(int i=0;i<root.getChildren().size();i++){
		Element E = (Element) root.getChildren().get(i);
		if(E.getName().equalsIgnoreCase("HOPS")){
		    parseBeerXMLHops(E);
		} else if(E.getName().equalsIgnoreCase("FERMENTABLES")){
		    parseBeerXMLFermentables(E);
		} else if(E.getName().equalsIgnoreCase("YEASTS")){
		    parseBeerXMLYeasts(E);
		} else if(E.getName().equalsIgnoreCase("MISCS")){
		    parseBeerXMLMiscs(E);
		} else if(E.getName().equalsIgnoreCase("WATERS")){
		    parseBeerXMLWaters(E);
		} else if(E.getName().equalsIgnoreCase("STYLES")){
		    parseBeerXMLStyles(E);
		} else if(E.getName().equalsIgnoreCase("MASH_STEPS")){
		    parseBeerXMLMashSteps(E);
		} else if(E.getName().equalsIgnoreCase("MASHS")){
		    parseBeerXMLMashes(E);
		} else if(E.getName().equalsIgnoreCase("RECIPE")){
		    parseBeerXMLRecipe(E);
		} else if(E.getName().equalsIgnoreCase("EQUIPMENTS")){
		    parseBeerXMLEquips(E);
		} else if(E.getName().equalsIgnoreCase("STYLE")){
		    parseBeerXMLStyle(E);
		} else if(E.getName().equalsIgnoreCase("EQUIPMENT")){
		    parseBeerXMLEquip(E);
		} else if(E.getName().equalsIgnoreCase("STYLE")){
		    parseBeerXMLStyle(E);
		} else if(E.getName().equalsIgnoreCase("MASH")){
		    parseBeerXMLMash(E);
		}
	    }
	}
	
    }
    public static void parseBeerXMLRecipes(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    
	    Element E = (Element) root.getChildren().get(i);
	    if(E.getName().equalsIgnoreCase("RECIPE")){
		parseBeerXMLRecipe(E);
	    }	    
	}
    }
    public static void parseBeerXMLRecipe(Element root){
	RecipeData R=new RecipeData();
	for(int i=0;i<root.getChildren().size();i++){
	    
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println(E.getName());
	    if(E.getName().equalsIgnoreCase("HOPS")){
		R.setHops(parseBeerXMLHops(E));
	    } else if(E.getName().equalsIgnoreCase("FERMENTABLES")){
		R.setMalts(parseBeerXMLFermentables(E));
	    } else if(E.getName().equalsIgnoreCase("YEASTS")){
		R.setYeasts(parseBeerXMLYeasts(E));
	    } else if(E.getName().equalsIgnoreCase("MISCS")){
		parseBeerXMLMiscs(E);
	    } else if(E.getName().equalsIgnoreCase("WATERS")){
		parseBeerXMLWaters(E);
	    } else if(E.getName().equalsIgnoreCase("STYLES")){
		parseBeerXMLStyles(E);
	    } else if(E.getName().equalsIgnoreCase("STYLE")){
		parseBeerXMLStyle(E);
	    } else if(E.getName().equalsIgnoreCase("MASH_STEPS")){
		R.setInfusionSteps(parseBeerXMLMashSteps(E));
	    } else if(E.getName().equalsIgnoreCase("MASHS")){
		parseBeerXMLMashes(E);
	    } else if(E.getName().equalsIgnoreCase("MASH")){
		parseBeerXMLMash(E);
		R.setInfusionSteps(parseBeerXMLMash(E));
	    } else if(E.getName().equalsIgnoreCase("EQUIPMENTS")){
		parseBeerXMLEquips(E);
	    } else if(E.getName().equalsIgnoreCase("EQUIPMENT")){
		parseBeerXMLEquip(E);
	    } else if(E.getName().equalsIgnoreCase("BATCH_SIZE")){
		R.setVolumeFin(new Double(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("EFFICIENCY")){
		String V=E.getValue();
		if(V.equals("-"))V="100";
		R.setEfficienza(new Double(V).intValue());
	    } else if(E.getName().equalsIgnoreCase("BOIL_TIME")){
		R.setBollitura(new Double(E.getValue()).intValue());
	    } else if(E.getName().equalsIgnoreCase("BOIL_SIZE")){
		R.setVolumeBoll(new Double(E.getValue()));
	    }
	}
	Ricetta r=new Ricetta();
	r.fromRecipeData(R);
	Main.gui.addFrame(r);
    }
    
    public static List<Hop> parseBeerXMLHops(Element root){
	List<Hop> hops=new ArrayList<Hop>();
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    hops.add(parseBeerXMLHop(E));
	}
	return hops;
    }
    public static Hop parseBeerXMLHop(Element root){
	Hop H=new Hop();
	for(int i=0;i<root.getChildren().size();i++){
/*
HOP
VERSION
NOTES
TYPE
BETA
SUBSTITUTES
HUMULENE
CARYOPHYLLENE
COHUMULONE
MYRCENE
 */
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("HOP: "+E.getName()+"="+E.getValue());
	    
	    if(E.getName().equalsIgnoreCase("NAME")){
		H.setNome(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("ALPHA")){
		H.setAlfaAcidi(Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("AMOUNT")){
		H.setGrammi(1000*Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("TIME")){
		H.setBoilTime((int)Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("HSI")){
		H.setHSI(Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("ORIGIN")){
		H.setOrigine(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("FORM")){
		String T=E.getValue();
		if(T.equalsIgnoreCase("PELLET"))H.setForma("Pellet");
		if(T.equalsIgnoreCase("Plug"))H.setForma("Plug");
		if(T.equalsIgnoreCase("Leaf"))H.setForma("Fiore");
	    } else if(E.getName().equalsIgnoreCase("USE")){
		String T=E.getValue();
		if(T.equalsIgnoreCase("Boil"))H.setUso("Kettle");
		if(T.equalsIgnoreCase("Mash"))H.setForma("Mash");
		if(T.equalsIgnoreCase("Dry Hop"))H.setForma("Dry");
		if(T.equalsIgnoreCase("Aroma"))H.setForma("Dry");
		if(T.equalsIgnoreCase("First Wort"))H.setForma("First Wort");
	    }
	    
	}
	//System.out.println(H.toXml().toString());
	return H;
    }
    
    public static List<Malt> parseBeerXMLFermentables(Element root){
	List<Malt> malts=new ArrayList<Malt>();
	for(int i=0;i<root.getChildren().size();i++){
	    
	    Element E = (Element) root.getChildren().get(i);
	    malts.add(parseBeerXMLFermentable(E));
	}
	return malts;
    }
    public static Malt parseBeerXMLFermentable(Element root){
	Malt H=new Malt();
/*
FERMENTABLE
VERSION
TYPE
ADD_AFTER_BOIL
SUPPLIER
NOTES
COARSE_FINE_DIFF
MOISTURE
DIASTATIC_POWER
PROTEIN
MAX_IN_BATCH
RECOMMEND_MASH
IBU_GAL_PER_LB
 */
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    if(E.getName().equalsIgnoreCase("NAME")){
		H.setNome(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("AMOUNT")){
		H.setGrammi(1000*Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("YIELD")){
		H.setPotentialSG(1+(46*Utils.parseDouble(E.getValue())/100)/1000);
	    } else if(E.getName().equalsIgnoreCase("COLOR")){
		H.setSrm(Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("ORIGIN")){
		H.setOrigine(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("TYPE")){
		String T=E.getValue();
		if(T.equalsIgnoreCase("Grain"))H.setForma("Grani");
		if(T.equalsIgnoreCase("Sugar"))H.setForma("Zuccheri");
		if(T.equalsIgnoreCase("Extract"))H.setForma("Estratto liquido");
		if(T.equalsIgnoreCase("Dry Extract"))H.setForma("Estratto secco");
		if(T.equalsIgnoreCase("Adjunct"))H.setForma("Altro");
	    }
	}
	//System.out.println(H.toXml().toString());
	return H;
    }
    
    public static List<Yeast> parseBeerXMLYeasts(Element root){
	List<Yeast> yeasts=new ArrayList<Yeast>();
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    yeasts.add(parseBeerXMLYeast(E));
	}
	return yeasts;
    }
    public static Yeast parseBeerXMLYeast(Element root){
/*
YEAST
VERSION
TYPE
AMOUNT
AMOUNT_IS_WEIGHT
MIN_TEMPERATURE
MAX_TEMPERATURE
FLOCCULATION
BEST_FOR
TIMES_CULTURED
MAX_REUSE
ADD_TO_SECONDARY
 */
	Yeast H=new Yeast();
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    if(E.getName().equalsIgnoreCase("NAME")){
		H.setNome(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("LABORATORY")){
		H.setProduttore(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("FORM")){
		H.setForma(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("NOTES")){
		H.setNote(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("ATTENUATION")){
		H.setAttenuazioneMed((int)Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("PRODUCT_ID")){
		H.setCodice(E.getValue());
	    }
	}
	//System.out.println(H.toXml().toString());
	return H;
    }
    public static void parseBeerXMLMiscs(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    parseBeerXMLMisc(E);
	}
    }
    public static void parseBeerXMLMisc(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("MISC: "+E.getName()+"="+E.getValue());
	}
    }
    public static void parseBeerXMLWaters(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    parseBeerXMLWater(E);
	}
    }
    public static void parseBeerXMLWater(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("WATER: "+E.getName()+"="+E.getValue());
	}
    }
    public static void parseBeerXMLEquips(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    parseBeerXMLEquip(E);
	}
    }
    public static void parseBeerXMLEquip(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("EQ: "+E.getName()+"="+E.getValue());
	}
    }
    public static void parseBeerXMLStyles(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    parseBeerXMLStyle(E);
	}
    }
    public static void parseBeerXMLStyle(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("STYLE: "+E.getName()+"="+E.getValue());
	}
    }
    public static List<MashStep> parseBeerXMLMashSteps(Element root){
	List<MashStep> steps=new ArrayList<MashStep>();
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    MashStep ms=parseBeerXMLMashStep(E);
	    steps.add(ms);
	    //System.out.println(ms);
	}
	return steps;
    }
    public static MashStep parseBeerXMLMashStep(Element root){
/*
MASH_STEP
NAME
VERSION
TYPE
INFUSE_AMOUNT
 */
	MashStep H=new MashStep();
	H.setStart(10);
	H.setType("infusion");
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    if(E.getName().equalsIgnoreCase("NAME")){
		H.setNome(E.getValue());
	    } else if(E.getName().equalsIgnoreCase("STEP_TEMP")){
		H.setStartTemp((int)Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("STEP_TIME")){
		H.setLength((int)Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("RAMP_TIME")){
		H.setRamp((int)Utils.parseDouble(E.getValue()));
	    } else if(E.getName().equalsIgnoreCase("END_TEMP")){
		H.setEndTemp((int)Utils.parseDouble(E.getValue()));
	    }
	}
	//System.out.println(H.toXml().toString());
	return H;
    }
    public static void parseBeerXMLMashes(Element root){
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    
	    parseBeerXMLMash(E);
	}
    }
    public static List<MashStep> parseBeerXMLMash(Element root){
	List<MashStep> steps=new ArrayList<MashStep>();
	for(int i=0;i<root.getChildren().size();i++){
	    Element E = (Element) root.getChildren().get(i);
	    //System.out.println("MASH: "+E.getName()+"="+E.getValue());
	    
	    steps.addAll(parseBeerXMLMashSteps(E));
	}
	return steps;
    }
}
