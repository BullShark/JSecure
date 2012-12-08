import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: bull
 * Date: 12/1/12
 * Time: 10:58 PM
 * todo insert GPLv3 here
 */

public class SecurePassword implements BackendReqs {
    private String pass;
    private boolean isAlpha, isNumeric, isPunc;
    private int passLength;
    private Random generator;

    public SecurePassword(boolean isA, boolean isN, boolean isP) {
        pass = "";
        isAlpha = isA;
        isNumeric = isN;
        isPunc = isP;
        generator = new Random(); //todo Range for alpha, numeric, punctuation
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
        return passLength;
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
        passLength = len;
    }

    /**************************************************************************
     * Miscellaneous
     */
    public String generateNew() throws InvalidPasswordException {
        if(isAlpha == false && isNumeric == false && isPunc == false) {
            throw new InvalidPasswordException("Password must contain one or more types");
        } else if(length = 0) {
            throw new InvalidPasswordException("Password cannot be zero in length");
        }

        for(int x=0; x<passLength; x++) {
            ;//todo use continue if regex doesn't match to skip the incrementing
        }

        return pass;
    }

    @Override
    public String toString() {
        return pass;
    }
}
