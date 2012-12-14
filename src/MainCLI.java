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
        //TODO: DICTIONARY and SCRUBBLED OPTIONS and ABOUT
        //TODO: debug instead of verbose

        public Parser() {
            optVvv = this.addBooleanOption('v', "verbose");
            optAlpha = this.addBooleanOption('a', "alpha");
            optNum = this.addBooleanOption('n', "numeric");
            optPunct = this.addBooleanOption('p', "punct");
            optHelp = this.addBooleanOption('h', "help");
            optLen = this.addIntegerOption('l', "length");
        }
    }

    public static void main(String[] args) {
        /**
         * Main method,here everything will take place(parsing of the options,generation of the secure password,etc...)
         */
        boolean isAlpha = false, isNum = false, isPunc = false;
        int l = 16;

        int EXIT_STATUS = 0;
        /**EXIT STATUES:
         *  0 = no errors
         *  1 = wrong or illegal option
         *  2 = unknown option
         *  3 = unknown error
         * */
        boolean verbose = false, help;

        Parser parser = new Parser(); //The Parser class will take care of all the options
        SecurePassword pass;

        try { //Try to parse the args
            parser.parse(args);

            try {   //Try to get verbose,if null it's false
                verbose = (Boolean) parser.getOptionValue(parser.optVvv);
            } catch (NullPointerException e) {
                verbose = false;
            }

            try {   //Try to get help,if null it's false
                help = (Boolean) parser.getOptionValue(parser.optHelp);
            } catch (NullPointerException e) {
                help = false;
            }

            if (help) { //IF the -h | --help option has been used IGNORE the others and show the message,then quit

                System.out.println("JSecure is a OPEN SOURCE software written in java that helps you generating strong passwords based on your needs.\n= AVAIABLE OPTIONS =\n" +
                        " -" + parser.optVvv.shortForm() + " | --" + parser.optVvv.longForm() + " = tells JSecure to speak loud (DEFAULT false).\n" +
                        " -" + parser.optNum.shortForm() + " | --" + parser.optNum.longForm() + " = allows JSecure to use numbers while generating the password (DEFAULT " + isNum + ").\n" +
                        " -" + parser.optAlpha.shortForm() + " | --" + parser.optAlpha.longForm() + " = allows JSecure to use letters while generating the password (DEFAULT " + isAlpha + ").\n" +
                        " -" + parser.optPunct.shortForm() + " | --" + parser.optPunct.longForm() + " = allows JSecure to use punctuation characters while generating the password (DEFAULT " + isPunc + ").\n" +
                        " -" + parser.optLen.shortForm() + " | --" + parser.optLen.longForm() + " <value>" + " = tells JSecure the wished length for the password (DEFAULT " + l + ").\n" +
                        " -" + parser.optHelp.shortForm() + " | --" + parser.optHelp.longForm() + " = asks JSecure to show you this help menu.\n" +
                        " -? | --credits " + " = asks to JSecure to show the credits/about informations.\n" +    //TODO:credits/about option
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
                //SET ALPHA
                try {
                    isAlpha = (Boolean) parser.getOptionValue(parser.optAlpha);
                } catch (NullPointerException e) {
                    if (verbose) System.err.println(e);  //Leave default option's value
                }
                //SET NUMERIC
                try {
                    isNum = (Boolean) parser.getOptionValue(parser.optNum);
                } catch (NullPointerException e) {
                    if (verbose) System.err.println(e);  //Leave default option's value
                }
                //SET PUNCTUATION
                try {
                    isPunc = (Boolean) parser.getOptionValue(parser.optPunct);
                } catch (NullPointerException e) {
                    if (verbose) System.err.println(e);  //Leave default option's value
                }
                //SET LENGTH
                try {
                    l = (Integer) parser.getOptionValue(parser.optLen);
                } catch (NullPointerException e) {
                    if (verbose) System.err.println(e);  //Leave default option's value
                }


                if (verbose)
                    System.out.println("NUM:" + isNum + "\nALPHA:" + isAlpha + "\nPUNC:" + isPunc + "\nLEN:" + l);

                /**Display generated password*/
                pass = new SecurePassword(isAlpha, isNum, isPunc, l);
                if(isAlpha || isNum || isPunc) {
                if (verbose) {
                    System.out.println("[*]PASSWORD STRENGTH:" + pass.getPassStrength()); //TODO PasswordStrength.java
                    System.out.println("[*]GENERATED NEW PASSWORD:");
                }
                System.out.print(pass.generateNew() + "\n");
                } else {
                    System.err.println("ERROR: you must use at least one pattern(-a,-n or -p)!");
                    EXIT_STATUS=3;
                }
            }

        } catch (IllegalOptionValueException e) {
            if (verbose) System.err.println(e);
            EXIT_STATUS = 1;
        } catch (UnknownOptionException e) {
            if (verbose) System.err.println(e);
            EXIT_STATUS = 2;
        } catch (NullPointerException e) {
            if (verbose) System.err.println(e);
            EXIT_STATUS = 1;
        } catch (InvalidPasswordException e) {
            if (verbose) System.err.println(e);
            EXIT_STATUS = 3;
//        } catch (ZeroPasswordLengthException e) { //TODO implement me
//
//        }

        if (EXIT_STATUS != 0) System.out.println("USAGE: java MainCLI <options> \n" +
                "       java -jar JSecure.jar <options>\n\nUse -h or --help to get a list of all available options.\n" +
                "EXIT STATUS:" + EXIT_STATUS);
        System.exit(EXIT_STATUS);
    }


}
