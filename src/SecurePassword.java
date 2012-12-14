/**
 * todo insert GPLv3 here
 */

import java.util.Random;

public class SecurePassword implements BackendReqs {
    private String pass;
    private boolean isAlpha, isNumeric, isPunc;
    private int passLen;
    private Random generator;

    public SecurePassword() throws InvalidPasswordException, InvalidPasswordLengthException {
        this(true, true, true, 16);
    }

    public SecurePassword(boolean isA, boolean isN, boolean isP, int len) throws InvalidPasswordException, InvalidPasswordLengthException {
        if(isA == false && isN == false && isP == false) {
            throw new InvalidPasswordException("Password must contain one or more types");
        } else if(len == 0) {
            throw new InvalidPasswordLengthException("Password cannot be zero in length");
        }
        pass = "";
        isAlpha = isA;
        isNumeric = isN;
        isPunc = isP;
        passLen = len;
        generator = new Random();
    }

    /**************************************************************************
     * Getters
     */
    @Override
    public boolean getAlpha() {
        return isAlpha;
    }

    @Override
    public boolean getNumeric() {
        return isNumeric;
    }

    @Override
    public boolean getPunctuation() {
        return isPunc;
    }

    @Override
    public int getLength() {
        return passLen;
    }

    /**************************************************************************
     * Setters
     */
    @Override
    public void setAlpha(boolean isAlpha) {
        this.isAlpha = isAlpha;
    }

    @Override
    public void setNumeric(boolean isNumeric) {
        this.isNumeric = isNumeric;
    }

    @Override
    public void setPunctuation(boolean isPunc) {
        this.isPunc = isPunc;
    }

    @Override
    public void setLength(int len) {
        passLen = len;
    }

    /**************************************************************************
     * Miscellaneous
     */
    public String generateNew() throws InvalidPasswordException, InvalidPasswordLengthException {
        if(isAlpha == false && isNumeric == false && isPunc == false) {
            throw new InvalidPasswordException("Password must contain one or more types");
        } else if(passLen == 0) {
            throw new InvalidPasswordLengthException("Password cannot be zero in length");
        }

        String ch; int ascii;
        for(int x=0; x<passLen; x++) {
            ascii = generator.nextInt(128);
            ch = String.valueOf(Character.toChars(ascii));
            /*
             * Randomize whether alpha, numeric, or punctuation comes next
             */
            switch(generator.nextInt(3)) {
                case 0:
                    if(isAlpha) {
                        if(ch.matches("\\p{Alpha}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                case 1:
                    if(isNumeric) {
                        if(ch.matches("\\p{Digit}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                case 2:
                    if(isPunc) {
                        if(ch.matches("\\p{Punct}{1}")) {
                            pass += ch;
                            break;
                        }
                    }
                x--; // A new character wasn't added
            }
        }
        return pass;
    }

    @Override
    public String toString() {
        return pass;
    }
}
