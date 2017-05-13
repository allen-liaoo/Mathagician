/**
 * Created by liaoyilin on 5/13/17.
 */
public class Util {

    public static boolean isNumber(String test) {
        try {
            Double.parseDouble(test);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
