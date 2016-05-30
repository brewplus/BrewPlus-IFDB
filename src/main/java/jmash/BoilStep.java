package jmash;
public class BoilStep {
	private int minuto;
	private String descrizione;
	
	public BoilStep()
	{}
	public BoilStep(int min, String desc)
	{
		this.minuto=min;
		this.descrizione=desc;
	}
	
	public int getMinuto()
	{return this.minuto;}
	public void setMinuto(int min)
	{this.minuto=min;	}
	public String getDescrizione()
	{return this.descrizione;}
	public void setDescrizione(String des)
	{this.descrizione=des;}
}
