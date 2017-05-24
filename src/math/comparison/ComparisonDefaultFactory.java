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
        comOperators.add(EQUAL2);
        comOperators.add(GREATER);
        comOperators.add(GREATER_EQ);
        comOperators.add(LESS);
        comOperators.add(LESS);
        comOperators.add(UNEQUAL);
        return comOperators;
    }

    public final static Comparator EQUAL = new Comparator("=") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.equals(o2);
        }
    };

    public final static Comparator EQUAL2 = new Comparator("==") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.equals(o2);
        }
    };

    public final static Comparator GREATER = new Comparator(">") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() > o2.getNumber();
        }
    };

    public final static Comparator GREATER_EQ = new Comparator(">=") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() >= o2.getNumber();
        }
    };

    public final static Comparator LESS = new Comparator("<") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() < o2.getNumber();
        }
    };

    public final static Comparator LESS_EQ = new Comparator("<=") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() <= o2.getNumber();
        }
    };

    public final static Comparator UNEQUAL = new Comparator("!=") {
        @Override
        public boolean compare(Operand o1, Operand o2) {
            return o1.getNumber() != o2.getNumber();
        }
    };

}
