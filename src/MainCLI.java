/**
 * Created with IntelliJ IDEA.
 * User: JKillNazi
 * Date: 12/3/12
 * Time: 6:36 PM
 */

import jargs.gnu.CmdLineParser;

public class MainCLI extends CmdLineParser {

    private static class Parser extends CmdLineParser {
        /**
         * Initialize all the possible arguments(short and long way)
         * -v | --verbose = tells JSecure to speak loud (DEFAULT: <???>)
         * -n | --numeric = allows JSecure to use numbers while generating the password (DEFAULT: <???>)
         * -a | --alpha = allows JSecure to use letters while generating the password (DEFAULT: <???>)
         * -p | --punct = allows JSecure to use punctuation characters while generating the password (DEFAULT: <???>)
         * -l | --length = tells JSecure the wished length for the password (DEFAULT: <???>)
         * -h | --help = asks for JSecure to show you this help menu
         */

        CmdLineParser.Option optVvv;
        CmdLineParser.Option optLen;
        CmdLineParser.Option optAlpha;
        CmdLineParser.Option optNum;
        CmdLineParser.Option optPunct;
        CmdLineParser.Option optHelp;
        //TODO: DICTIONARY and SCRUBBLED OPTIONS

        public Parser() {
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
         * Main method,here everything will take place(parsing of the options,generation of the secure password,etc...)
         */
        //TODO: fix the "NO ARGS==ULLPOINT EXCEPTION"

        int EXIT_STATUS=0;
        /**EXIT STATUES:
         *  0 = no errors
         *  1 = wrong or illegal option
         *  2 = unknown option
         *  3 = unknown error
         * */
        boolean verbose=false,help;

        Parser parser = new Parser(); //The Parser class will take care of all the options
        SecurePassword pass = new SecurePassword();

        try { //Try to parse the args
            parser.parse(args);

            //verbose = (Boolean)parser.getOptionValue(parser.optVvv);
            //help = parser.getOptionValue(parser.optHelp).equals(null)?false:true; //TODO:Fix NullPointException

            if ((Boolean)parser.getOptionValue(parser.optHelp)) { //IF the -h | --help option has been used IGNORE the others and show the message,then quit
                System.out.println("JSecure is a OPEN SOURCE software written in java that helps you generating strong passwords based on your needs.\n= AVAIABLE OPTIONS =\n" +
                        " -"+parser.optVvv.shortForm()  +" | --"+parser.optVvv.longForm()    +" = tells JSecure to speak loud (DEFAULT false).\n" +
                        " -"+parser.optNum.shortForm()  +" | --"+parser.optNum.longForm()    +" = allows JSecure to use numbers while generating the password (DEFAULT true).\n" +
                        " -"+parser.optAlpha.shortForm()+" | --"+parser.optAlpha.longForm()  +" = allows JSecure to use letters while generating the password (DEFAULT true).\n" +
                        " -"+parser.optPunct.shortForm()  +" | --"+parser.optPunct.longForm()+" = allows JSecure to use punctuation characters while generating the password (DEFAULT false).\n" +
                        " -"+parser.optLen.shortForm()+" | --"+parser.optLen.longForm()+" <value>"+" = tells JSecure the wished length for the password (DEFAULT 16).\n" +
                        " -"+parser.optHelp.shortForm()+" | --"+parser.optHelp.longForm()    +" = asks JSecure to show you this help menu.\n" +
                        " -? | --credits "                                                   +" = asks to JSecure to show the credits/about informations.\n" +    //TODO:credits/about option
                        "\nUSAGE: java Main <options> \n" +
                        "       java -jar JSecure.jar <options>");
            } else { //ELSE get the values from the other options

                /*
                *========================NOTE========================
                *If an option has not been used it returns NULL. So let's say i run:
                *  "java -jar JSecure.jar -a -l 10 -p"
                *-n will be null,so:
                * "pass.setNumeric((Boolean) parser.getOptionValue(parser.optNum));"
                *will set the isNumeric to NULL!
                *Be sure to check if we pass NULL to a Setter to make it "ignore" and leave the default option!
                **/

                /**SET THE NEEDED PARAMETERS TO GENERATE THE PASSWORD*/
                //IF <cond> is null ? set default : get value
                pass.setAlpha(parser.getOptionValue(parser.optAlpha).equals(null) ? pass.getAlpha() : (Boolean)parser.getOptionValue(parser.optAlpha)); //Set isAlpha
                pass.setNumeric(parser.getOptionValue(parser.optNum).equals(null) ? pass.getNumeric() : (Boolean)parser.getOptionValue(parser.optNum)); //Set isNumeric
                pass.setPunctuation(parser.getOptionValue(parser.optPunct).equals(null) ? pass.getPunctuation() : (Boolean)parser.getOptionValue(parser.optPunct)); //Set isPunc
                pass.setLength(parser.getOptionValue(parser.optLen).equals(null) ? pass.getLength() : (Integer)parser.getOptionValue(parser.optLen)); //Set passLength

                if(verbose) System.out.println("NUM:"+pass.getNumeric()+"\nALPHA:"+pass.getAlpha()+"\nPUNC:"+pass.getPunctuation()+"\nLEN:"+pass.getLength());
            }

        } catch (IllegalOptionValueException e) {
            if(verbose) System.err.println(e);
            EXIT_STATUS=1;
        } catch (UnknownOptionException e) {
            if(verbose) System.err.println(e);
            EXIT_STATUS=2;
        } catch (NullPointerException e) {
            if(verbose) System.err.println(e);
            EXIT_STATUS=1;
        }

        if(EXIT_STATUS != 0) System.out.println("USAGE: java MainCLI -h \n" +
                                                "       java -jar JSecure.jar -h\n\n"+
                                                "EXIT STATUS:"+EXIT_STATUS);
        System.exit(EXIT_STATUS);
    }


}
