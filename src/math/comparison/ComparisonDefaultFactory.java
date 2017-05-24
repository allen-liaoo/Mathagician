package math.comparison;

import math.Operand;
import java.util.ArrayList;
import java.util.List;

/**
 * Default Comparison Operators
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class ComparisonDefaultFactory {

    public List<Comparator> getDefaultComparisonOperator() {
        List<Comparator> comOperators = new ArrayList<>();
        comOperators.add(EQUAL);
        comOperators.add(GREATER);
        comOperators.add(GREATER_EQ);
        comOperators.add(LESS);
        comOperators.add(LESS_EQ);
        comOperators.add(UNEQUAL);
        return comOperators;
    }

    public final static Comparator EQUAL = new Comparator("=", Comparator.PRECEDENCE_ONE) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.equals(o2);
        }
    };

    public final static Comparator GREATER = new Comparator(">", Comparator.PRECEDENCE_ONE) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() > o2.getNumber();
        }
    };

    public final static Comparator GREATER_EQ = new Comparator(">=", Comparator.PRECEDENCE_TWO) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() >= o2.getNumber();
        }
    };

    public final static Comparator LESS = new Comparator("<", Comparator.PRECEDENCE_ONE) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() < o2.getNumber();
        }
    };

    public final static Comparator LESS_EQ = new Comparator("<=", Comparator.PRECEDENCE_TWO) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() <= o2.getNumber();
        }
    };

    public final static Comparator UNEQUAL = new Comparator("!=", Comparator.PRECEDENCE_TWO) {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() != o2.getNumber();
        }
    };

}
