package com.uwi.cli;

import com.uwi.config.Loader;
import com.uwi.engine.Spat;
import net.sf.jsqlparser.JSQLParserException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * <code>
 * The **Engine** class is CLI for accessing SPAT. The Engine is the interface between SPAT and the user. All queries
 * are parsed, validated and then sent to SPAT to be processed. After processing as completed the result is returned
 * to the user on the screen or in a specified output file.
 * </code>
 */
public final class Engine {
    /**
     * <code>
     * The main method. Facade for SPAT. See [Loader.md](Loader.md) for more info on the flags that can be passed to
     * the
     * engine.
     *</code>
     * @param args
     *         the arguments
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

            System.exit(-1);
        }

        try {
            Spat.parse(
                    loader.getXmlFile(), loader.getSql(), loader.getType(), loader.getOutputFile());
        } catch (JSQLParserException e) {
            System.err.println(e.getMessage());
            System.err.println("Invalid SQL Statement ..");
            System.exit(-2);
        }
    }
}
