import java.math.BigInteger;

/**
 * Number Utilities
 * @author Alien Ideology <alien.ideology at alien.org>
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

    public static int getGCD(int num, int num2) {
        return BigInteger.valueOf(num).gcd(BigInteger.valueOf(num2)).intValue();
    }

}
