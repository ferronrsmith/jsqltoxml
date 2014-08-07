package com.uwi.config;

import net.sf.jsqlparser.JSQLParserException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.uwi.cmd.Loader;

// TODO: Auto-generated Javadoc
/**
 * The Class Engine.
 */
public class Engine {
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// "select c.name, c.drink from country as c"
		// countries.xml
		Loader loader = new Loader();
		CmdLineParser parser = new CmdLineParser(loader);

		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			// if there's a problem in the command line,
			// you'll get this exception. this will report
			// an error message.
			System.err.println(e.getMessage());
			System.err.println("java Engine [options...] arguments...");
			// print the list of available options
			parser.printUsage(System.err);
			System.err.println();

			return;
		}

		try {
			JSql.parse(loader.getXmlFile(), loader.getSql(), loader.getType(),
					loader.getOutputFile());
		} catch (JSQLParserException e) {
			System.err.println(e.getMessage());
			System.err.println("Invalid SQL Statement ..");
		}
	}
}
