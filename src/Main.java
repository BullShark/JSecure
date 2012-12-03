/**
 * Created with IntelliJ IDEA.
 * User: JKillNazi
 * Date: 12/3/12
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */

import jargs.gnu.CmdLineParser;

public class Main extends CmdLineParser {

    private static class Parser extends CmdLineParser {
        /**
         * Initialize all the possible arguments(short and long way)
         * -g | --gui = asks JSecure to show its nice GUI (DEFAULT: <???>)
         * -v | --verbose = tells JSecure to speak loud (DEFAULT: <???>)
         * -n | --numeric = allows JSecure to use numbers while generating the password (DEFAULT: <???>)
         * -a | --alpha = allows JSecure to use letters while generating the password (DEFAULT: <???>)
         * -p | --punct = allows JSecure to use punctuation characters while generating the password (DEFAULT: <???>)
         * -l | --length = tells JSecure the wished length for the password (DEFAULT: <???>)
         * -h | --help = asks for JSecure to show you this help menu
         */

        CmdLineParser.Option optGUI;
        CmdLineParser.Option optVvv;
        CmdLineParser.Option optLen;
        CmdLineParser.Option optAlpha;
        CmdLineParser.Option optNum;
        CmdLineParser.Option optPunct;
        CmdLineParser.Option optHelp;
        //TODO: DICTIONARY and SCRUBBLED OPTIONS

        public Parser() {
            optGUI = this.addBooleanOption('g',"gui");
            optVvv = this.addBooleanOption('v',"verbose");
            optAlpha = this.addBooleanOption('a',"alpha");
            optNum = this.addBooleanOption('n',"numeric");
            optPunct = this.addBooleanOption('p',"punct");
            optHelp = this.addBooleanOption('h',"help");
            optLen = this.addIntegerOption('l',"length");
        }
    }

    public static void main(String[] args) {
        /**
        * Main method,here everithing will take place(parsing of the options,generation of the secure password,etc...)
        * USAGE: java Main <options></options>
        */

        Main main = new Main();
        Parser parser = new Parser();
        SecurePassword pass = new SecurePassword();

        try {
            parser.parse(args);   //Try to parse the args
        } catch (IllegalOptionValueException e) {
            e.printStackTrace();
        } catch (UnknownOptionException e) {
            e.printStackTrace();
        }

        if (((Boolean) parser.getOptionValue(parser.optHelp))) {
            System.out.println("JSecure is a OPEN SOURCE software written in java that helps you generating strong passwords based on your needs.\n= AVAIABLE OPTIONS =\n" +
                    " -"+parser.optGUI.shortForm()  +" | --"+parser.optGUI.longForm()    +" = asks JSecure to show its nice GUI (DEFAULT <???>).\n" +
                    " -"+parser.optVvv.shortForm()  +" | --"+parser.optVvv.longForm()    +" = tells JSecure to speak loud (DEFAULT <???>).\n" +
                    " -"+parser.optNum.shortForm()  +" | --"+parser.optNum.longForm()    +" = allows JSecure to use numbers while generating the password (DEFAULT <???>).\n" +
                    " -"+parser.optAlpha.shortForm()+" | --"+parser.optAlpha.longForm()  +" = allows JSecure to use letters while generating the password (DEFAULT <???>).\n" +
                    " -"+parser.optPunct.shortForm()  +" | --"+parser.optPunct.longForm()+" = allows JSecure to use punctuation characters while generating the password (DEFAULT <???>).\n" +
                    " -"+parser.optLen.shortForm()+" | --"+parser.optLen.longForm()+" <value>"+" = tells JSecure the wished length for the password (DEFAULT: <???>).\n" +
                    " -"+parser.optHelp.shortForm()+" | --"+parser.optHelp.longForm()    +" = asks JSecure to show you this help menu.\n" +
                    " -? | --credits "                                                   +" = asks to JSecure to show the credits/about informations.\n" +    //TODO:credits/about option
                    "\nUSAGE: java Main <options> \n" +
                    "       java -jar Main <options>");
        } else {
            pass.setAlpha((Boolean) parser.getOptionValue(parser.optAlpha));
            pass.setNumeric((Boolean) parser.getOptionValue(parser.optNum));
            pass.setPunctuation((Boolean) parser.getOptionValue(parser.optPunct));
            pass.setLength((Integer) parser.getOptionValue(parser.optLen));
        }

        System.exit(0);
    }


}