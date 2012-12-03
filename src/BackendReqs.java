/**
 * todo insert description here
 * todo insert GPLv3 here
 */
public interface BackendReqs {
    void setAlpha(boolean isAlpha);
    void setNumeric(boolean isNumeric);
    void setPunctuation(boolean isPunc);
    void setLength(int len);
    boolean getNumeric();
    boolean getAlpha();
    boolean getPunctuation();
    int getLength();
    String toString();
}
