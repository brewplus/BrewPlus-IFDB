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
import java.util.Iterator;
import java.util.List;
import jmash.tableModel.HopTableModel;
import jmash.tableModel.MaltTableModel;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

public class RecipeData {
    private String nome, note, unitaMisura,fotografia;
    private Double volumeBoll, volumeFin, volumeDiluito;
    private Integer efficienza, bollitura;
    private Boolean bollituraConcentrata;
    
    //private BrewStyle style;
    private String codiceStile;
    
    private List<Hop> hops;
    private List<Malt> malts;
    private List<Yeast> yeasts;
    private List<MashStep> infusionSteps;
    private List<MashStep> decoctionSteps;
    private WaterProfile sourceWater;
    private WaterProfile destWater;
    private WaterProfile treatment;
    private Element waterNeeded;
    public void setRicetta(Ricetta ricetta){
	for(Hop h: hops){
	    h.setRicetta(ricetta);
	}
	for(Malt m: malts){
	    m.setRicetta(ricetta);
	}
	for(Yeast y: yeasts){
	    y.setRicetta(ricetta);
	}
	for(MashStep y: infusionSteps){
	    y.setRicetta(ricetta);
	}
	for(MashStep y: decoctionSteps){
	    y.setRicetta(ricetta);
	}
    }
    
    public String getNome() {
	return nome;
    }
    public void setNome(String nome) {
	this.nome = nome;
    }
    public String getFotografia()
    {
    	return fotografia;
    }
    public void setFotografia(String fotografia)
    {
    	this.fotografia=fotografia;
    }
    public String getNote() {
	return note;
    }
    public void setNote(String note) {
	this.note = note;
    }
    public String getUnitaMisura() {
	return unitaMisura;
    }
    public void setUnitaMisura(String unitaMisura) {
	this.unitaMisura = unitaMisura;
    }
    public Double getVolumeBoll() {
	return volumeBoll;
    }
    public void setVolumeBoll(Double volumeBoll) {
	this.volumeBoll = volumeBoll;
    }
    public Double getVolumeDiluito() {
	return volumeDiluito;
    }
    public void setVolumeDiluito(Double volumeDiluito) {
	this.volumeDiluito = volumeDiluito;
    }
    public Double getVolumeFin() {
	return volumeFin;
    }
    public void setVolumeFin(Double volumeFin) {
	this.volumeFin = volumeFin;
    }
    public Integer getEfficienza() {
	return efficienza;
    }
    public void setEfficienza(Integer efficienza) {
	this.efficienza = efficienza;
    }
    public String getCodiceStile() {
	return codiceStile;
    }
    public void setCodiceStile(String codiceStile) {
	this.codiceStile=codiceStile;
    }
    public List<Hop> getHops() {
	return hops;
    }
    public void setHops(List<Hop> hops) {
	this.hops = hops;
    }
    public List<Malt> getMalts() {
	return malts;
    }
    public void setMalts(List<Malt> malts) {
	this.malts = malts;
    }
    public List<Yeast> getYeasts() {
	return yeasts;
    }
    public void setYeasts(List<Yeast> yeasts) {
	this.yeasts = yeasts;
    }
    public List<MashStep> getInfusionSteps() {
	return infusionSteps;
    }
    public void setInfusionSteps(List<MashStep> infusionSteps) {
	this.infusionSteps = infusionSteps;
    }
    public List<MashStep> getDecoctionSteps() {
	return decoctionSteps;
    }
    public void setDecoctionSteps(List<MashStep> decoctionSteps) {
	this.decoctionSteps = decoctionSteps;
    }
    
    public String getDes4Forum(){
	
	Element root=new Element(XmlTags.RECIPE);
	String S="Ricetta per "+getNome()+", ";
	
	double volume=getBollituraConcentrata()?getVolumeDiluito():getVolumeFin();
	
	S+=String.format("litri finali %.1f (in bollitura %.1f)%n",volume,getVolumeBoll());
	S+="efficienza  "+getEfficienza()+"%, bollitura "+getBollitura()+" min.\n";

	double OG=MaltTableModel.calcolaSG(getMalts(), volume, getEfficienza());
	double EBC= Utils.srmToEbc(MaltTableModel.calcolaSRMMosher(getMalts(), volume));
	double IBU=HopTableModel.getIBUTinseth(getHops(),getVolumeFin(),getVolumeDiluito(),OG);
	
	S+=String.format("OG %.03f; IBU: %.1f; EBC: %.0f;\n",
		OG,IBU, EBC );
//	if(this.getCodiceStile()!=null) {
//	    root.addContent(this.getCodiceStile().toXml());
//	}
	if(getMalts()!=null && malts.size()>0){
	    S+="Malti:\n";
	    for(Malt m: malts){
		S+=String.format("  %.0f gr %s, %.03f;\n",m.getGrammi(),m.getNome(),m.getPotentialSG());
	    }
	}
	if(getHops()!=null && hops.size()>0){
	    S+="Luppoli e altro:\n";
	    for(Hop h: hops){
		//S+=String.format("  %.0f gr %s, %.01f %%a.a., %d min;\n",h.getGrammi(),h.getNome(),h.getAlfaAcidi(),h.getBoilTime());
	    	//modifica IXTLANAS inclusione di metodo luppolatura
	    	S+=String.format("  %.0f gr %s, %.01f %%a.a., %d min, %s;\n",h.getGrammi(),h.getNome(),h.getAlfaAcidi(),h.getBoilTime(),h.getUso());
	    }
	}
	
	if(getYeasts()!=null && yeasts.size()>0){
	    S+="Lieviti:\n";
	    for(Yeast y: yeasts){
	    	//ixtlanas aggiunta note
		S+="  "+y.getNome()+" "+y.getNote()+"\n";
	    }
	    
	}
	return S;
    }
    
    public Element getXmlRoot(){
	
	Element root=new Element(XmlTags.RECIPE);
	
	root.setAttribute(new Attribute(XmlTags.VOLUME,""+getVolumeFin()));
	root.setAttribute(new Attribute(XmlTags.VOLUME_BOLL,""+getVolumeBoll()));
	root.setAttribute(new Attribute(XmlTags.VOLUME_DIL,""+getVolumeDiluito()));
	root.setAttribute(new Attribute(XmlTags.VOLUME_UM,""+getUnitaMisura()));
	root.setAttribute(new Attribute(XmlTags.EFFICIENZA,""+getEfficienza()));
	root.setAttribute(new Attribute(XmlTags.BOLLITURA,""+getBollitura()));
	root.setAttribute(new Attribute(XmlTags.BOLLITURA_CONC,""+getBollituraConcentrata()));
	root.setAttribute(new Attribute(XmlTags.NOME,getNome()));
	root.setAttribute(new Attribute(XmlTags.NOTE,getNote()));
	//foto
	root.setAttribute(new Attribute(XmlTags.FOTOGRAFIA,""+getFotografia()));
	
//	if(this.getStyle()!=null) {
//	    root.addContent(this.getStyle().toXml());
//	}
	if(this.getCodiceStile()!=null)
	{
		root.setAttribute(new Attribute(XmlTags.BJCPCOD,""+getCodiceStile()));
	}
	if(getHops()!=null){
	    for(Hop h: hops){
		root.addContent(h.toXml());
	    }
	}
	if(getMalts()!=null){
	    for(Malt m: malts){
		root.addContent(m.toXml());
	    }
	}
	if(getYeasts()!=null){
	    for(Yeast y: yeasts){
		root.addContent(y.toXml());
	    }
	}
	if(getInfusionSteps()!=null){
	    for(MashStep y: infusionSteps){
		root.addContent(y.toXml());
	    }
	}
	if(getDecoctionSteps()!=null){
	    for(MashStep y: decoctionSteps){
		root.addContent(y.toXml());
	    }
	}
	if(getSourceWater()!=null){
	    getSourceWater().setType(0);
	    root.addContent(getSourceWater().toXml());
	}
	if(getDestWater()!=null){
	    getDestWater().setType(1);
	    root.addContent(getDestWater().toXml());
	}
	if(getTreatment()!=null){
	    getTreatment().setType(2);
	    root.addContent(getTreatment().toXmlPlus());
	}
	if(waterNeeded!=null){
	    root.addContent(waterNeeded);
	}
	return root;
    }
    public Document toXml(){
	Document doc=new Document();
	
	Element root=getXmlRoot();
	doc.setRootElement(root);
	return doc;
    }
    public Integer getBollitura() {
	return bollitura;
    }
    
    public void setBollitura(Integer bollitura) {
	this.bollitura = bollitura;
    }
    public void read(Document doc){
	Element root=doc.getRootElement();
	if(root.getName().compareToIgnoreCase(XmlTags.RECIPE)==0)
	    read(root);
    }
    public void readRec(String filename) throws Exception{
	byte [] b= Utils.buffer(filename);
	setHops(new ArrayList<Hop>());
	setMalts(new ArrayList<Malt>());
	setYeasts(new ArrayList<Yeast>());
	setInfusionSteps(new ArrayList<MashStep>());
	setDecoctionSteps(new ArrayList<MashStep>());
	
	setNome(Utils.arr2String(b,0));
	
	setVolumeFin(Utils.galToLit(Utils.arr2Double((byte[]) b, 97)));
	setVolumeBoll(Utils.galToLit(Utils.arr2Double((byte[]) b, 101)));
	setEfficienza((int)(100*Utils.arr2Double((byte[]) b, 113)));
	setBollitura((int)b[117]);
	
	BrewStyle style=new BrewStyle();
	style.setOgMin(Utils.arr2Double( b, 237));
	style.setOgMax(Utils.arr2Double( b, 241));
	style.setFgMin(Utils.arr2Double( b, 245));
	style.setFgMax(Utils.arr2Double( b, 249));
	style.setIbuMin(Utils.arr2Double( b, 261));
	style.setIbuMax(Utils.arr2Double( b, 265));
	style.setColorMin(Utils.arr2Double( b, 269));
	style.setColorMax(Utils.arr2Double( b, 273));
	style.setCategoria(Utils.arr2String(b,126));
	style.setNome(Utils.arr2String(b,181));
	setCodiceStile(style.getNumero());
	
	String []maltType=new String[]{"?","Grani","Estratto Liquido", "Cristalli","Fiocchi","Altro2","Altro3"};
	String []hopType=new String[]{"Fiore","Plug","Pellet", "Sugar"};
	String []extraType=new String[]{"Spice","Fruit","Coffee","Other","Fining","Herb"};
	int nH=(int)b[85];
	int nM=(int)b[89];
	int nE=(int)b[93];
	
	
	setNote(
		"Notes: "+Utils.arr2String(b,1157+635*nH+529*nM+589*nE+757)+
		"\nAward: "+Utils.arr2String(b,1157+635*nH+529*nM+589*nE+757+4028));
	
	for(int i=0;i<nH;i++){
	    
	    Hop H=new Hop();
	    H.setNome(Utils.arr2String(b,1157+635*i));
	    H.setAlfaAcidi(Utils.arr2Double(b,1157+635*i+(1777-1157)));
	    H.setGrammi((double)(int)Utils.ouncesToGrams(Utils.arr2Double(b,1157+635*i+(1782-1157))));
	    H.setBoilTime((int)Utils.arr2Byte(b,1157+635*i+(1786-1157)));
	    
	    if(H.getBoilTime()<0){
		H.setBoilTime(0);
		H.setUso("dry");
	    }
	    
	    H.setForma(hopType[((b[1157+635*i+81])&0xF0)/16]);
	    getHops().add(H);
	    
	}
	
	for(int i=0;i<nM;i++){
	    Malt M=new Malt();
	    
	    M.setNome(Utils.arr2String(b,1157+635*nH+529*i));
	    M.setPotentialSG(Utils.arr2Double(b,1157+635*nH+529*i+(3229-3062)));
	    M.setSrm(Utils.arr2Double(b,1157+635*nH+529*i+(3762-3591)));
	    double d=Utils.poundsToGrams(Utils.arr2Double(b,1157+635*nH+529*i+(4112-3591)));
	    d=Math.round(d);
	    M.setGrammi(d);
	    M.setForma(maltType[(int)Utils.arr2Byte(b,1157+635*nH+529*i+(165))]);
	    
	    getMalts().add(M);
	}
	
	for(int i=0;i<nE;i++){
	    
	    Hop H=new Hop();
	    H.setNome(Utils.arr2String(b,1157+635*nH+529*nM+589*i));
	    H.setAlfaAcidi(0.0);
	    H.setGrammi((double)(int)Utils.ouncesToGrams(Utils.arr2Double(b,1157+635*nH+529*nM+589*i+577)));
	    H.setBoilTime((int)Utils.arr2Byte(b,1157+635*nH+529*nM+589*i+56));
	    if((int)Utils.arr2Byte(b,1157+635*nH+529*nM+589*i+56+6)==1)
		H.setGrammi((double)(int)Utils.arr2Double(b,1157+635*nH+529*nM+589*i+577));
	    
	    H.setForma(extraType[Utils.arr2Byte(b,1157+635*nH+529*nM+589*i+55)]);
	    getHops().add(H);
	    
	}
	int M=1157+635*nH+529*nM+589*nE+757+4028+4028;
	
	boolean mashComplexMode=b[b.length-1]==1;
//	System.out.println("Mash is Complex: "+mashComplexMode);
	int nS=(int)b[M+255];
	
//	System.out.println("Grain Temp:"+b[M+259]+" °F");
	int Y=1157+635*nH+529*nM+589*nE;
	Yeast Ys=new Yeast();
	Ys.setNome(Utils.arr2String(b,Y));
	Ys.setProduttore(Utils.arr2String(b,Y+55));
	Ys.setCodice(Utils.arr2String(b,Y+110));
	Ys.setAttenuazioneMin((int)Utils.arr2Double(b,Y+455));
	Ys.setAttenuazioneMax((int)Utils.arr2Double(b,Y+459));
	Ys.setAttenuazioneMed((Ys.getAttenuazioneMin()+Ys.getAttenuazioneMax())/2);
	getYeasts().add(Ys);
	
	if(!mashComplexMode){
	    System.out.println("multistep="+Utils.arr2Byte(b,Y+695));
	    boolean multistep=(Utils.arr2Byte(b,Y+695)==3);
	    int i=704;
	    int minute=0, T=(int)Utils.F2C((int)b[M+259]);
	    String D[]=new String[]{"Acid rest","Protein rest","Intermediate rest","Saccharification rest","Mash out","Sparge"};
	    for (int j = 0; j < 6; j++) {
		if(multistep || j>=3){
		    MashStep step=new MashStep();
		    step.setStart(minute);
		    step.setStartTemp(T);
		    step.setEndTemp((int)
		    Math.round(Utils.F2C((int)0xFF&Utils.arr2Byte(b,Y+i)))
		    );
		    int L=(int)0xFF&Utils.arr2Byte(b,Y+i+4);
		    if(L>5) {
			step.setLength(L-5);
			step.setRamp(5);
		    } else{
			step.setLength(L);
			step.setRamp(0);
		    }
		    if(step.getStartTemp()<=0 || L<=5) step.setStartTemp(step.getEndTemp());
		    step.setNome(D[j]);
		    if(L>0) getInfusionSteps().add(step);
		    minute+=5+step.getLength();
		    T=step.getEndTemp();
		}
		i+=8;
	    }
	}
	if(mashComplexMode){
	    System.out.println(nS+" step:");
	    int SD=292;
	    for(int i=0;i<nS;i++){
		int P=M+267+i*SD;
		int type=Utils.arr2Byte(b,P+255);
		System.out.println("\t"+Utils.arr2String(b,P)+
			" infusionQ="+Utils.arr2float(b,P+280)+
			" startT="+Utils.arr2Byte(b,P+256)+
			" stopT="+Utils.arr2Byte(b,P+260)+
			" infuseT="+(Utils.arr2Byte(b,P+264)&0xFF)+
			" restTime="+Utils.arr2Byte(b,P+268)+
			" stepTime="+Utils.arr2Byte(b,P+272)+
			" type="+Utils.arr2Byte(b,P+255)+
			""
			);
	    }
	}
    }
    public void read(Element root){
	setHops(new ArrayList<Hop>());
	setMalts(new ArrayList<Malt>());
	setYeasts(new ArrayList<Yeast>());
	setInfusionSteps(new ArrayList<MashStep>());
	setDecoctionSteps(new ArrayList<MashStep>());
	
	@SuppressWarnings("unchecked")
	Iterator itR = root.getAttributes().iterator();
	while (itR.hasNext()) {
	    Attribute att = (Attribute)itR.next();
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.BOLLITURA)==0){
		    setBollitura(att.getIntValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setBollitura(0);
	    }
	    
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.EFFICIENZA)==0){
		    setEfficienza(att.getIntValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setEfficienza(0);
	    }
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.VOLUME)==0){
		    setVolumeFin(att.getDoubleValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setVolumeFin(1.0);
	    }
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.VOLUME_DIL)==0){
		    setVolumeDiluito(att.getDoubleValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setVolumeDiluito(1.0);
	    }
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.BOLLITURA_CONC)==0){
		    setBollituraConcentrata(att.getBooleanValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setBollituraConcentrata(false);
	    }
	    try{
		if(att.getName().compareToIgnoreCase(XmlTags.VOLUME_BOLL)==0){
		    setVolumeBoll(att.getDoubleValue());
		}
	    } catch(org.jdom.DataConversionException ex){
		setVolumeBoll(1.0);
	    }
	    if(att.getName().compareToIgnoreCase(XmlTags.NOME)==0){
		setNome(att.getValue());
	    }
	    if(att.getName().compareToIgnoreCase(XmlTags.FOTOGRAFIA)==0){
	    	setFotografia(att.getValue());
	    }
	    if(att.getName().compareToIgnoreCase(XmlTags.BJCPCOD)==0)
	    {
	    	setCodiceStile(att.getValue());
	    }
	    if(att.getName().compareToIgnoreCase("finalista")==0){
		setFinalista(att.getValue().equals("true"));
	    }
	    if(att.getName().compareToIgnoreCase("titolo_concorso")==0){
		setConcorso(att.getValue());
	    }
	    if(att.getName().compareToIgnoreCase(XmlTags.VOLUME_UM)==0){
		setUnitaMisura(att.getValue());
	    }
	    if(att.getName().compareToIgnoreCase(XmlTags.NOTE)==0){
		setNote(att.getValue());
	    }
	}
	@SuppressWarnings("unchecked")
	Iterator iterator = root.getChildren().iterator();
	while (iterator.hasNext()) {
	    Element elem = (Element)iterator.next();
	    if(elem.getName().compareToIgnoreCase("water")==0){
		waterNeeded=elem;
	    }
	    if(elem.getName().compareToIgnoreCase(new BrewStyle().getClass().getName())==0){
		BrewStyle style=BrewStyle.fromXml(elem);
		if(style!=null) {
		    setCodiceStile(style.getNumero());
		}
	    }
	    if(elem.getName().compareToIgnoreCase(new Hop().getClass().getName())==0){
		Hop hop=Hop.fromXml(elem);
		if(hop!=null) {
		    getHops().add(hop);
		}
	    }
	    
	    if(elem.getName().compareToIgnoreCase(new Malt().getClass().getName())==0){
		Malt malt=Malt.fromXml(elem);
		if(malt!=null) {
		    getMalts().add(malt);
		}
	    }
	    if(elem.getName().compareToIgnoreCase(new Yeast().getClass().getName())==0){
		Yeast yeast=Yeast.fromXml(elem);
		if(yeast!=null) {
		    getYeasts().add(yeast);
		}
	    }
	    if(elem.getName().compareToIgnoreCase(new WaterProfile().getClass().getName())==0){
		WaterProfile profile=WaterProfile.fromXml(elem);
		if(profile!=null && profile.getType()!=null) {
		    if(profile.getType()==0)setSourceWater(profile);
		    if(profile.getType()==1)setDestWater(profile);
		    if(profile.getType()==2)setTreatment(profile);
		}
	    }
	    if(elem.getName().compareToIgnoreCase(new MashStep().getClass().getName())==0){
		MashStep step=MashStep.fromXml(elem);
		if(step!=null){
		    if(step.isDecoctionStep()) {
			getDecoctionSteps().add(step);
		    } else {
			getInfusionSteps().add(step);
		    }
		}
	    }
	}
    }
    
    public WaterProfile getSourceWater() {
	return sourceWater;
    }
    
    public void setSourceWater(WaterProfile sourceWater) {
	this.sourceWater = sourceWater;
    }
    
    public WaterProfile getDestWater() {
	return destWater;
    }
    
    public void setDestWater(WaterProfile destWater) {
	this.destWater = destWater;
    }
    
    public WaterProfile getTreatment() {
	return treatment;
    }
    
    public void setTreatment(WaterProfile treatment) {
	this.treatment = treatment;
    }
    
    private boolean finalista;
    private String concorso;
    
    public boolean getFinalista() {
	return finalista;
    }
    
    public void setFinalista(boolean finalista) {
	this.finalista = finalista;
    }
    
    public String getConcorso() {
	return concorso;
    }
    
    public void setConcorso(String concorso) {
	this.concorso = concorso;
    }
    
    public Element getWaterNeeded() {
	return waterNeeded;
    }
    
    public void setWaterNeeded(Element waterNeeded) {
	this.waterNeeded = waterNeeded;
    }
    
    public Boolean getBollituraConcentrata() {
	return bollituraConcentrata;
    }
    
    public void setBollituraConcentrata(Boolean bollituraConcentrata) {
	this.bollituraConcentrata = bollituraConcentrata;
    }
    
    public Double getVolPerStampa(){
	if(bollituraConcentrata!=null && bollituraConcentrata.booleanValue())
	    return volumeDiluito;
	return volumeFin;
    }
}
