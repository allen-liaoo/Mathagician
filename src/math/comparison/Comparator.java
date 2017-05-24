package math.comparison;

import math.Operand;
import math.Section;

/**
 * Created by liaoyilin on 5/23/17.
 */
public abstract class Comparator extends Section {

    public Comparator(String section) {
        super(section);
    }

    public abstract boolean compare(Operand o1, Operand o2);

}
