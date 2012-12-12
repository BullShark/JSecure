/**
 * Created with IntelliJ IDEA.
 * User: bull
 * Date: 12/11/12
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */

package Bull
import
public class SecurePassTest {
    public static void main(String[] args) {
        try {
            SecurePassword spass = new SecurePassword(true, true, true, 10);
            System.out.println(spass.generateNew());
        } catch(InvalidPasswordException e) {
            e.printStackTrace();
        }
    }
}
