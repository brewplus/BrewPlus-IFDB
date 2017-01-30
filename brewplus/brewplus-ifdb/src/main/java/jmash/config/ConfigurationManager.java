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

package jmash.config;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.beanutils.BeanDeclaration;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.beanutils.XMLBeanDeclaration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.log4j.Logger;

import jmash.config.bean.GeneralConfig;
import jmash.utils.BrewplusEnvironment;
import jmash.utils.Constants;

/**
 *
 * @author rekhyts
 */
public class ConfigurationManager {
	
	private static ConfigurationManager istance = null;
	private static final Logger logger = Logger.getLogger(ConfigurationManager.class);
	private static GeneralConfig generalConfig;
	
//	private String userDir = System.getProperty("user.dir")+"/skeleton/";
	
	protected ConfigurationManager(){
		
		
		try
		{

			logger.info("Reading and configuring Environment");
			BrewplusEnvironment bpenv = BrewplusEnvironment.getIstance();
			Parameters params = new Parameters();
			FileBasedConfigurationBuilder<XMLConfiguration> builder =
				    new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class);
			builder.configure(params.xml().setFileName(bpenv.getConfigfileName(Constants.XML_CONFIG)));
			XMLConfiguration config = builder.getConfiguration();
				
			BeanDeclaration decl = new XMLBeanDeclaration(config, "brewplus2.generalConfig");
			
			generalConfig = (GeneralConfig) BeanHelper.INSTANCE.createBean(decl);
			

		}
		catch (Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		
		
		
	};
	
	public static ConfigurationManager getIstance() {
		if (istance == null){
			istance = new ConfigurationManager();
		}
		return istance;
	}

//	private void init() {
//		if (istance == null){
//			istance = new ConfigurationManager();
//		}
//	}

	public GeneralConfig getGeneralConfig() {
		return generalConfig;
	}


}
