package jmash.utils;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author michele "Rekhyt" antonecchia
 *
 */
public class Cli {
	private static final Logger log = Logger.getLogger(Cli.class.getName());
	private String[] args = null;
	private Options options = new Options();

	public Cli(String[] args) {

		this.args = args;

		options.addOption("h", "help", false, "show help.");
		options.addOption("d", "debug", false, "Set log level to debug");

	}

	public void parse() {
		CommandLineParser parser = new PosixParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();

			if (cmd.hasOption("d")) {
				log.log(Level.INFO, "Current log level = " + LogManager.getRootLogger().getLevel());
				LogManager.getRootLogger().setLevel(Level.DEBUG);
				log.log(Level.DEBUG, "new log level = " + Level.DEBUG);
			} 

		} catch (ParseException e) {
			log.log(Level.ERROR, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);
		System.exit(0);
	}

}
