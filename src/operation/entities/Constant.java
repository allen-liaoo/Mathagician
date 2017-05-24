package operation.entities;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Constant {

    private final List<String> keys;
    private final double value;

    public Constant(double value, String... keys) {
        this.value = value;
        this.keys = Arrays.asList(keys);
    }

    public String getInitialKey() {
        return keys.get(0);
    }

    public List<String> getKeys() {
        return keys;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Constant && this.getValue() == ((Constant) obj).getValue();
    }
}
