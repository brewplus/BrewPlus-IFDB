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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.jdom.Element;
import jmash.interfaces.XmlAble;


public class WaterProfile implements XmlAble, Comparable<WaterProfile>{
    private String nome;
    private Integer type;
    private Double calcio;
    private Double sodio;
    private Double magnesio;
    private Double solfato;
    private Double cloruro;
    private Double carbonato;
    private double diff;
    private Double gypsum=0.0, calciumChloride=0.0, sale=0.0, epsom=0.0, chalk = 0.0 , soda = 0.0;
    private boolean useGypsum=true, useCalciumChloride=true, useSale=true, useEpsom=true, useChalk =true , useSoda = true;
    private static int pCalcio=50, pSolfato=50, pCloruro=50, pSodio=50, pMagnesio=50, pCarbonato=50;
    public static Random R=new Random();
    public WaterProfile(){
        this.calcio=0.0;
        this.magnesio=0.0;
        this.solfato=0.0;
        this.cloruro=0.0;
        this.sodio=0.0;
        this.carbonato=0.0;
    }
    public WaterProfile(double Ca, double Mg, double So, double Cl, double Na, double carbonato){
        this.calcio=Ca;
        this.magnesio=Mg;
        this.solfato=So;
        this.cloruro=Cl;
        this.sodio=Na;
        this.carbonato=carbonato;
    }
    
    public double diff(WaterProfile dest){
        return
                Math.pow(((double)pCalcio),5)*Math.pow(getCalcioTotale()-dest.getCalcioTotale(),2)+
                Math.pow(((double)pSolfato),5)*Math.pow(getSolfatoTotale()-dest.getSolfatoTotale(),2)+
                Math.pow(((double)pSodio),5)*Math.pow(getSodioTotale()-dest.getSodioTotale(),2)+
                Math.pow(((double)pMagnesio),5)*Math.pow(getMagnesioTotale()-dest.getMagnesioTotale(),2)+
                Math.pow(((double)pCloruro),5)*Math.pow(getCloruroTotale()-dest.getCloruroTotale(),2)+
                Math.pow(((double)pCarbonato),5)*Math.pow(getCarbonatoTotale()-dest.getCarbonatoTotale(),2);
        
        
    }
    public double getCalcioTotale(){
        return this.calcio+(this.chalk*105.8+this.gypsum*61.5 +this.calciumChloride*72)/1000;
    }
    public double getMagnesioTotale(){
        return this.magnesio+(this.epsom*26.1)/1000;
    }
    public double getSolfatoTotale(){
        return this.solfato+(this.gypsum*147.4+this.epsom*103)/1000;
    }
    public double getCloruroTotale(){
        return this.cloruro+(this.calciumChloride*127.4+this.sale*160.3)/1000;
    }
    public double getSodioTotale(){
        return this.sodio+(this.sale*103.9+this.soda*72)/1000;
    }
    public double getCarbonatoTotale(){
        return this.carbonato+(this.chalk*158.4+this.soda*189)/1000;
    }
    @Override
    public WaterProfile clone(){
        WaterProfile p=new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio, this.carbonato);
        p.gypsum=this.gypsum;
        p.sale=this.sale;
        p.epsom=this.epsom;
        p.calciumChloride=this.calciumChloride;
        p.chalk=this.chalk;
        p.soda=this.soda;
        p.diff=this.diff;
        return p;
    }
    public WaterProfile target(WaterProfile dest, double LITRI, String name, int recusions, List<WaterProfile> L, int POPULATION) {
        if(L==null)L=new ArrayList<WaterProfile>();
        if(L.size()==0){
            for (int i = 0; i < POPULATION; i++) {
                WaterProfile p=new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio, this.carbonato);
                            /*
                    p.gypsum=R.nextInt(1000);
                    p.sale=R.nextInt(1000);
                    p.epsom=R.nextInt(1000);
                    p.calciumCloride=R.nextInt(1000);*/
                L.add(p);
            }
        }
        for (int k = 0; k < recusions; k++) {
            
            WaterProfile best=null;
            for (int i = 0; i <L.size(); i++) {
                WaterProfile p=L.get(i);
                p.diff=p.diff(dest);
            }
            
            Collections.sort(L, new Compare());
            best=L.get(0);/*
            System.out.println(k+") first.diff="+best.diff  +
                    ", \tgypsum="+best.gypsum+
                    ", calciumChloride="+best.calciumCloride+
                    ", sale="+best.sale+
                    ", epsom="+best.epsom
                    );
            System.out.println("\tcalcio="+best.getCalcio()+"("+dest.calcio+")"+
                    ", magnesio="+best.getMagnesio()+"("+dest.magnesio+")"+
                    ", cloruri="+best.getCloruri()+"("+dest.cloruri+")"+
                    ", solfato="+best.getSolfato()+"("+dest.solfato+")"+
                    ", sodio="+best.getSodio()+"("+dest.sodio+")"
                    );    */
            if(best.diff==0) {
                break;
            }
            int S=15;
            List<WaterProfile> LL=new ArrayList<WaterProfile>();
            for(int i=0;i<S;i++){
                WaterProfile p=(L.get(i)).clone();
                LL.add(p);
            }
            L.clear();
            //for (int j = 0; j < 3; j++)
            
            
            for(int i=0;i<S;i++){
                L.add(LL.get(i));
            }
            int GRAMS_VAR = 5000;
            while(L.size()<POPULATION){
                for(int i=0;i<S;i++){
                    WaterProfile p=(LL.get(i)).clone();
                    //if(R.nextInt()%2==0)
                    p.gypsum=p.gypsum+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    //if(R.nextInt()%2==0)
                    p.sale=p.sale+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    //if(R.nextInt()%2==0)
                    p.calciumChloride=p.calciumChloride+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    //if(R.nextInt()%2==0)
                    p.epsom=p.epsom+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    p.chalk=p.chalk+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    p.soda=p.soda+R.nextInt(GRAMS_VAR)-GRAMS_VAR/2;
                    if(p.gypsum<0) {
                        p.gypsum=0.0;
                    }
                    if(p.sale<0) {
                        p.sale=0.0;
                    }
                    if(p.epsom<0) {
                        p.epsom=0.0;
                    }
                    if(p.chalk<0) {
                        p.chalk=0.0;
                    }
                    if(p.soda<0) {
                        p.soda=0.0;
                    }
                    if(p.calciumChloride<0) {
                        p.calciumChloride=0.0;
                    }
                    if(!useSoda)p.soda=0.0;
                    if(!useChalk)p.chalk=0.0;
                    if(!useGypsum)p.gypsum=0.0;
                    if(!useCalciumChloride)p.calciumChloride=0.0;
                    if(!useSale)p.sale=0.0;
                    if(!useEpsom)p.epsom=0.0;
                    
                    L.add(p);
                }
            };
            int SEED = 100;
            for (int i = 0; i < SEED; i++) {
                WaterProfile p=new WaterProfile(this.calcio, this.magnesio, this.solfato, this.cloruro, this.sodio, this.carbonato);
                p.gypsum=(double)R.nextInt(10000);
                p.sale=(double)R.nextInt(10000);
                p.epsom=(double)R.nextInt(10000);
                p.calciumChloride=(double)R.nextInt(10000);
                p.soda=(double)R.nextInt(10000);
                p.chalk=(double)R.nextInt(10000);
                if(!useSoda)p.soda=0.0;
                if(!useChalk)p.chalk=0.0;
                if(!useGypsum)p.gypsum=0.0;
                if(!useCalciumChloride)p.calciumChloride=0.0;
                if(!useSale)p.sale=0.0;
                if(!useEpsom)p.epsom=0.0;
                
                L.add(p);
            }
        }
        WaterProfile res=L.get(0);
        /*
        System.out.println(
                "Per "+LITRI+" litri di '"+name+"',"+
                "\n\t"+((res.gypsum*Utils.litToGal(LITRI))/1000)+" gr di gypsum "+
                "\n\t"+((res.calciumCloride*Utils.litToGal(LITRI))/1000)+" gr di cloruro di calcio"+
                "\n\t"+((res.sale*Utils.litToGal(LITRI))/1000)+" gr di cloruro di sodio (sale)"+
                "\n\t"+((res.epsom*Utils.litToGal(LITRI))/1000)+" gr di epsom"+
                "\n\t"+((res.chalk*Utils.litToGal(LITRI))/1000)+" gr di chalk"+
                "\n\t"+((res.soda*Utils.litToGal(LITRI))/1000)+" gr di soda"+
                "\ncalcio="+res.getCalcioTotale()+"("+dest.calcio+")"+
                ", magnesio="+res.getMagnesioTotale()+"("+dest.magnesio+")"+
                ", cloruri="+res.getCloruroTotale()+"("+dest.cloruro+")"+
                ", solfato="+res.getSolfatoTotale()+"("+dest.solfato+")"+
                ", sodio="+res.getSodioTotale()+"("+dest.sodio+")\n"+
                ", carbonato="+res.getCarbonatoTotale()+"("+dest.sodio+")\n"
                );*/
        return res;
    }
    
    private class Compare implements Comparator<WaterProfile>{
        public int compare(WaterProfile a, WaterProfile b){
            WaterProfile p=a;
            WaterProfile q=b;
            return new Double(p.diff).compareTo(new Double(q.diff));
        }
    };
    
    
    public static void main(String[] args) {
        
        
        WaterProfile burton  =new WaterProfile(295,25,725,25,55, 150);
        WaterProfile bitter  =new WaterProfile(170,15,400,200,55, 150);
        WaterProfile porter  =new WaterProfile(120,10,100,300,15, 150);
        WaterProfile mild  =new WaterProfile(120,10,150,200,15, 150);
        
        WaterProfile dublin  =new WaterProfile(115,4,55,19,12, 50);
        WaterProfile munich  =new WaterProfile(70,18,7,2,10, 50);
        WaterProfile vienna  =new WaterProfile(200,60,125,12,8, 50);
        WaterProfile pilsen  =new WaterProfile(7,5,5,5,25, 50);
        WaterProfile paleAle =new WaterProfile(100,10,200,20,10, 50);
        WaterProfile norda   =new WaterProfile(10,2,5,0,2, 50);
        try {
            int L = 40;
            int LITRI=L;
//			norda.target(munich,  L, "munich");
//			norda.target(burton,  L, "burton");
            WaterProfile res=norda.target(bitter,  L, "bitter",1000, null, 100);
            
            System.out.println(
                    "Per "+LITRI+" litri di '"+res.getNome()+"',"+
                    "\n\t"+((res.getGypsum()*Utils.litToGal(LITRI))/1000)+" gr di gypsum "+
                    "\n\t"+((res.getCalciumChloride()*Utils.litToGal(LITRI))/1000)+" gr di cloruro di calcio"+
                    "\n\t"+((res.getSale()*Utils.litToGal(LITRI))/1000)+" gr di cloruro di sodio (sale)"+
                    "\n\t"+((res.getEpsom()*Utils.litToGal(LITRI))/1000)+" gr di epsom"+
                    "\n\t"+((res.getChalk()*Utils.litToGal(LITRI))/1000)+" gr di chalk"+
                    "\n\t"+((res.getSoda()*Utils.litToGal(LITRI))/1000)+" gr di soda"+
                    "\ncalcio="+res.getCalcioTotale()+"("+norda.getCalcio()+")"+
                    ", magnesio="+res.getMagnesioTotale()+"("+norda.getMagnesio()+")"+
                    ", cloruri="+res.getCloruroTotale()+"("+norda.getCloruro()+")"+
                    ", solfato="+res.getSolfatoTotale()+"("+norda.getSolfato()+")"+
                    ", sodio="+res.getSodioTotale()+"("+norda.getSodio()+")"+
                    ", carbonato="+res.getCarbonatoTotale()+"("+norda.getCarbonato()+")\n"
                    );
            norda.target(porter,  L, "porter",1000, null, 100);
            norda.target(mild,  L, "mild",1000, null, 100);
//			norda.target(paleAle, L, "paleAle");
//			norda.target(dublin,  L, "dublin");
//			norda.target(vienna,  L, "vienna");
//			norda.target(pilsen,  L, "pilsen");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Random getR() {
        return R;
    }
    public static void setR(Random r) {
        R = r;
    }
    public Double getCalcio() {
        return this.calcio;
    }
    public void setCalcio(Double calcio) {
        this.calcio = calcio;
    }
    public Double getCalciumChloride() {
        return this.calciumChloride;
    }
    public void setCalciumChloride(Double calciumChloride) {
        this.calciumChloride = calciumChloride;
    }
    public Double getCloruro() {
        return this.cloruro;
    }
    public void setCloruro(Double cloruro) {
        this.cloruro = cloruro;
    }
    public Double getCarbonato() {
        return this.carbonato;
    }
    public void setCarbonato(Double carbonato) {
        this.carbonato = carbonato;
    }
    public double getDiff() {
        return this.diff;
    }
    public void setDiff(double diff) {
        this.diff = diff;
    }
    public Double getEpsom() {
        return this.epsom;
    }
    public void setEpsom(Double epsom) {
        this.epsom = epsom;
    }
    public Double getGypsum() {
        return this.gypsum;
    }
    public void setGypsum(Double gypsum) {
        this.gypsum = gypsum;
    }
    public Double getMagnesio() {
        return this.magnesio;
    }
    public void setMagnesio(Double magnesio) {
        this.magnesio = magnesio;
    }
    public Double getSale() {
        return this.sale;
    }
    public void setSale(Double sale) {
        this.sale = sale;
    }
    public Double getChalk() {
        return this.chalk;
    }
    public void setChalk(Double chalk) {
        this.chalk = chalk;
    }
    public Double getSoda() {
        return this.soda;
    }
    public void setSoda(Double soda) {
        this.soda = soda;
    }
    public Double getSodio() {
        return this.sodio;
    }
    public void setSodio(Double sodio) {
        this.sodio = sodio;
    }
    public Double getSolfato() {
        return this.solfato;
    }
    public void setSolfato(Double solfato) {
        this.solfato = solfato;
    }
    
    public int getPCalcio() {
        return pCalcio;
    }
    
    public void setPCalcio(int pCalcio) {
        WaterProfile.pCalcio = pCalcio;
    }
    
    public int getPSolfato() {
        return pSolfato;
    }
    
    public void setPSolfato(int pSolfato) {
        WaterProfile.pSolfato = pSolfato;
    }
    
    public int getPCloruro() {
        return pCloruro;
    }
    
    public void setPCloruro(int pCloruro) {
        WaterProfile.pCloruro = pCloruro;
    }
    
    public int getPSodio() {
        return pSodio;
    }
    
    public void setPSodio(int pSodio) {
        WaterProfile.pSodio = pSodio;
    }
    
    public int getPMagnesio() {
        return pMagnesio;
    }
    
    public void setPMagnesio(int pMagnesio) {
        WaterProfile.pMagnesio = pMagnesio;
    }
    
    public void setPCarbonato(int pCarbonato) {
        WaterProfile.pCarbonato = pCarbonato;
    }
    
    public static WaterProfile fromXml(Element elem){
        WaterProfile malt=new WaterProfile(0,0,0,0,0,0);
        try {
            malt=(WaterProfile)Utils.fromXml(malt,campiXmlPlus,elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }
    public static WaterProfile fromXmlPlus(Element elem){
        WaterProfile malt=new WaterProfile(0,0,0,0,0,0);
        try {
            malt=(WaterProfile)Utils.fromXml(malt,campiXmlPlus,elem);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }
    
//    public static String campiXml[]=new String[]{"nome","type","calcio","magnesio","solfato","cloruro","sodio", "carbonato"};
    //ixtlanas NO TYPE
    public static String campiXml[]=new String[]{"nome","calcio","magnesio","solfato","cloruro","sodio", "carbonato"};
    public static String campiXmlPlus[]=new String[]{"nome","type","calcio","magnesio","solfato","cloruro","sodio", "carbonato",
    "gypsum",
    "sale",
    "epsom",
    "calciumChloride",
    "chalk",
    "soda",
    };
    @Override
    public Element toXml(){
        Element malt=new Element("waterProfile");
        try {
            return Utils.toXml(this,campiXml);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }
    public Element toXmlPlus(){
        Element malt=new Element("waterProfile");
        try {
            return Utils.toXml(this,campiXmlPlus);
        } catch (Exception ex) {
            Utils.showException(ex);
        }
        return malt;
    }
    public void setUseGypsum(boolean useGypsum) {
        this.useGypsum = useGypsum;
    }
    
    public void setUseCalciumChloride(boolean useCalciumChloride) {
        this.useCalciumChloride = useCalciumChloride;
    }
    
    public void setUseSale(boolean useSale) {
        this.useSale = useSale;
    }
    
    public void setUseEpsom(boolean useEpsom) {
        this.useEpsom = useEpsom;
    }
    
    public void setUseChalk(boolean useChalk) {
        this.useChalk = useChalk;
    }
    
    public void setUseSoda(boolean useSoda) {
        this.useSoda = useSoda;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    @Override
    public String getTag(){
        return "waters";
    }
    @Override
    public String[] getXmlFields(){return campiXml;}
    
    @Override
    public int compareTo(WaterProfile o) {
        return nome.compareToIgnoreCase(o.getNome());
    }
    
}