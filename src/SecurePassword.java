/**
 * Created with IntelliJ IDEA.
 * User: bull
 * Date: 12/1/12
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurePassword implements BackendReqs {
    private String pass;
    private boolean isAlpha, isNumeric, isPunc;
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

    public String toString() {
        return pass;
    }
}
