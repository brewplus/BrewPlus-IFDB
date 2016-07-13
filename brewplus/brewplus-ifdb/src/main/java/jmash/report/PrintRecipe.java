package jmash.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import jmash.report.model.RecipeModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PrintRecipe {
	
	private static final Logger LOGGER = Logger.getLogger(PrintRecipe.class);
	
	public static final void recipe(String recipeName, String styleName, String brewPlusVersion, List<RecipeModel> recipeModel) {
		Map<String, Object> lParameters = new HashMap<String, Object>();
		JasperPrint jasperPrint;
		try {
			LOGGER.debug("Retrive recipe jasper file");
			InputStream jasperStream = ClassLoader.getSystemResourceAsStream("jmash/reports/recipe.jasper"); 
			JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
			
			LOGGER.debug("Set report parameters");
			lParameters.put("pRecipeName", recipeName);
		    lParameters.put("pBJCPStyle", styleName);
		    lParameters.put("pBrewplusVersion", brewPlusVersion);   
		    
		    LOGGER.debug("Retrive report images");
		    lParameters.put("pLogoDir", ClassLoader.getSystemResourceAsStream("jmash/reports/logo.png"));
		    lParameters.put("pBackgroundImage", ClassLoader.getSystemResourceAsStream("jmash/reports/sfondo.png"));
		    JRDataSource dataSource = new JRBeanCollectionDataSource(recipeModel);	        
	    	
		    LOGGER.debug("Fill jasper report with datasource");
	    	jasperPrint = JasperFillManager.fillReport(report, lParameters, dataSource);
	    	
	    	LOGGER.debug("Open report in JasperViewer");
	    	JasperViewer viewer = new JasperViewer(jasperPrint, false);
	    	viewer.setTitle(recipeName);	   
	    	viewer.setVisible(true);	    
		} catch (JRException e) {
			LOGGER.debug("Unable to print recipe " + e.getMessage());
		}
	       
	}
}
