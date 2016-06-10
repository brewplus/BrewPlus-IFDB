package jmash;

import jmash.interfaces.XmlAble;
import org.apache.log4j.Logger;
import org.jdom.Element;

/**
 *
 * @author Peruzzi Alessandro
 */
public class MaltCategory implements XmlAble, Comparable<MaltCategory>
{

  private static Logger LOGGER = Logger.getLogger(MaltCategory.class);

  private String codice;
  private String nome;
  private Double pH;

  private static String campiXml[] =
  {
    "Codice", "Nome", "pH"
  };
  
  /**
   * Creates a new instance of MaltCategory
   */
  public MaltCategory()
  {
  }

  public MaltCategory(String codice, String nome, Double pH)
  {
    setCodice(codice);
    setNome(nome);
    setPH(pH);
  }

  public String getCodice()
  {
    return codice;
  }

  public void setCodice(String codice)
  {
    this.codice = codice;
  }
  
  public String getNome()
  {
    return nome;
  }

  public void setNome(String nome)
  {
    this.nome = nome;
  }

  public Double getPH()
  {
    return pH;
  }

  public void setPH(Double pH)
  {
    this.pH = pH;
  }

  public static String[] getCampiXml()
  {
    return campiXml;
  }

  public static void setCampiXml(String[] aCampiXml)
  {
    campiXml = aCampiXml;
  }
  
  public static MaltCategory fromXml(Element malt) {
		MaltCategory maltCategory = new MaltCategory();
		try {
			maltCategory = (MaltCategory) Utils.fromXml(maltCategory, getCampiXml(), malt);
		} catch (Exception ex) {
			Utils.showException(ex);
		  maltCategory = new MaltCategory();
		}
		return maltCategory;
	}

  @Override
  public Element toXml()
  {
    try
    {
      return Utils.toXml(this, getCampiXml());
    }
    catch (Exception ex)
    {
      LOGGER.error(ex.getMessage(), ex);
    }
    return null;
  }

  @Override
  public String getTag()
  {
    return "maltsCategories";
  }

  @Override
	public String[] getXmlFields() {
		return getCampiXml();
	}

  @Override
  public int compareTo(MaltCategory o)
  {
    return codice.compareToIgnoreCase(o.getCodice());
  }

}
