package jmash.report;

import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import jmash.report.model.RecipeModel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.repo.DefaultRepositoryService;
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
			
			// select available fonts
			String fontname = "";
			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
			boolean f = true;
	        for(String font:e.getAvailableFontFamilyNames()) {
	            if(f){
    	            if(font.equalsIgnoreCase("DejaVu Sans")){
    	                fontname = "DejaVu Sans";
    	                f = false;
    	            } else if (font.equalsIgnoreCase("Times New Roman")){
    	                fontname = "Times New Roman";
    	                f = false;
    	            } else {
    	                fontname = "Arial";
    	            }
	            }
	        }
	        LOGGER.info("Using font: " + fontname);
	        
	        DefaultJasperReportsContext ctx = DefaultJasperReportsContext.getInstance();
            DefaultRepositoryService service = new DefaultRepositoryService(ctx);
	        
	        JRPropertiesUtil props = JRPropertiesUtil.getInstance(ctx);
	        props.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
	        props.setProperty("net.sf.jasperreports.default.font.name", fontname);
						
			report.setJasperReportsContext(ctx);
			report.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			report.setProperty("net.sf.jasperreports.default.font.name", fontname);
			
			// end - select available fonts
				
			
			LOGGER.debug("Set report parameters");
			lParameters.put("pRecipeName", recipeName);
		    lParameters.put("pBJCPStyle", styleName);
		    lParameters.put("pBrewplusVersion", brewPlusVersion);   
		    
		    LOGGER.debug("Retrive report images");
		    lParameters.put("pLogoDir", ClassLoader.getSystemResourceAsStream("jmash/reports/logo.png"));
		    lParameters.put("pBackgroundImage", ClassLoader.getSystemResourceAsStream("jmash/reports/sfondo.jpg"));
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
