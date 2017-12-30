package jmash.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import jmash.config.ConfigurationManager;

/*
 * @author rekhyt
 */

public class BrewplusEnvironment {

	private static BrewplusEnvironment istance = null;
	private static final Logger logger = Logger.getLogger(ConfigurationManager.class);

	private static HashMap<String, File> folderMap;
	private static HashMap<String, File> configFileMap;

	private static String userDir = System.getProperty("user.dir") + "/.brewplus2/";

	protected BrewplusEnvironment() {

		folderMap = new HashMap<String, File>();
		folderMap.put(Constants.DIR_RECIPE, new File(userDir + "/recipes/"));
		folderMap.put(Constants.DIR_BATCH, new File(userDir + "/batches/"));
		folderMap.put(Constants.DIR_MASH, new File(userDir + "/mashes/"));
		folderMap.put(Constants.DIR_SHOPPING, new File(userDir + "/shopping/"));
		folderMap.put(Constants.DIR_WATER, new File(userDir + "/water/"));
		folderMap.put(Constants.DIR_CONFIG, new File(userDir + "/config/"));
		folderMap.put(Constants.DIR_EXPORTPID, new File(userDir + "/recipes/"));

		configFileMap = new HashMap<String, File>();
		configFileMap.put(Constants.XML_HOPS, new File(userDir + "/config/" + "luppoli_ita.xml"));
		configFileMap.put(Constants.XML_MALT, new File(userDir + "/config/" + "malti_ita.xml"));
		configFileMap.put(Constants.XML_CATEGORIES, new File(userDir + "/config/" + "categorie_malti.xml"));
		configFileMap.put(Constants.XML_WATER, new File(userDir + "/config/" + "water.xml"));
		configFileMap.put(Constants.XML_YEAST, new File(userDir + "/config/" + "lieviti_ita.xml"));
		configFileMap.put(Constants.XML_STYLES, new File(userDir + "/config/" + "stili.xml"));
		configFileMap.put(Constants.XML_BJCP, new File(userDir + "/config/" + "styleguide-2015.xml"));
		configFileMap.put(Constants.XML_COLORS, new File(userDir + "/config/" + "colors.xml"));
		configFileMap.put(Constants.XML_CONFIG, new File(userDir + "/config/" + "generalConfig.xml"));
		configFileMap.put(Constants.XML_INVENTORY, new File(userDir + "/config/" + "inventario.xml"));
		configFileMap.put(Constants.XML_BREPROFILE, new File(userDir + "/config/" + "profili_impianto.xml"));

	}

	public static BrewplusEnvironment getIstance() {

		if (istance == null) {
			istance = new BrewplusEnvironment();

			// check for first run configuration
			File ud = new File(userDir);
			boolean firstRun = !ud.exists();
			if (firstRun) {
				firstRunConfig(ud);
			}
		}

		return istance;
	}

	private static void firstRunConfig(File ud) {
		try {

			// create folders
			ud.mkdir();
			for (String key : folderMap.keySet()) {
				File f = (File) folderMap.get(key);
				if (!f.exists()) {
					f.mkdir();
					logger.debug("first run creating folder: \"" + f.getCanonicalPath() + "\"");
				}
			}

			// copy config files
			logger.info("Copying config files into user config folder");
			File cfgSource = new File(System.getProperty("user.dir") + "/skeleton/config/");
			File cfgDest = (File) folderMap.get(Constants.DIR_CONFIG);
			FileUtils.copyDirectory(cfgSource, cfgDest);

			File recipeSource = new File(System.getProperty("user.dir") + "/skeleton/recipes/");
			File recipeDest = (File) folderMap.get(Constants.DIR_RECIPE);
			FileUtils.copyDirectory(recipeSource, recipeDest);

		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}
	
	public String getFolderName(String key){
		File f = (File)folderMap.get(key);
		String name = "";
		try {
			name = f.getCanonicalPath();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return name;
	}
	
	public String getConfigfileName(String key){
		File f = (File)configFileMap.get(key);
		String name = "";
		try {
			name = f.getCanonicalPath();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return name;
	}

}
