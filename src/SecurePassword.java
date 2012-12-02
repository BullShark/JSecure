/**
 * Created with IntelliJ IDEA.
 * User: bull
 * Date: 12/1/12
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurePassword implements BackendReqs {
    private String pass;
    @Override
    public void setAlpha(boolean isAlpha) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setNumeric(boolean isNumeric) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPunctuation(boolean isPunc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String toString() {
        return pass;
    }
}
