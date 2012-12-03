/**
 * Created with IntelliJ IDEA.
 * User: bull
 * Date: 12/1/12
 * Time: 10:23 PM
 */

public interface BackendReqs {
    void setAlpha(boolean isAlpha);
    void setNumeric(boolean isNumeric);
    void setPunctuation(boolean isPunc);
    void setLength(int len);
    boolean getAlpha();
    boolean getNumeric();
    boolean getPunctuation();
    int getLength();

    String toString();

}
