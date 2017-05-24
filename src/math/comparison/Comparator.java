package math.comparison;

import math.Operand;
import math.Section;

/**
 * Comparison Operators like <=, !=
 * @author AlienIdeology
 */
public abstract class Comparator extends Section {

    public final static int PRECEDENCE_TWO = 2;
    public final static int PRECEDENCE_ONE = 1;

    private int precedence;

    /**
     * Default Constructor
     * @param section The string representation of this comparison operator.
     * @param precedence The precedence of this operator. The precedence should be the length of the section.
     */
    public Comparator(String section, int precedence) {
        super(section);
        this.precedence = precedence;
    }

    public abstract boolean compare(Operand o1, Operand o2);

    public int getPrecedence() {
        return precedence;
    }

}
