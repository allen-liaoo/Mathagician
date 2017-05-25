package math.comparison;

import math.Operand;
import math.operation.Operation;

/**
 * @author AlienIdeology
 */
public class Comparison {

    private String comparison;

    private Comparator comparator;
    private Operation operation_right;
    private Operation operation_left;

    /**
     * Constructor
     * @param right the right side of the comparison expression
     * @param comparator the comparison operator
     * @param left the left side of the comparison expression
     */
    public Comparison(Operation right, Comparator comparator, Operation left) {
        this.operation_right = right;
        this.comparator = comparator;
        this.operation_left = left;
        this.comparison = operation_right.getOperation() + comparator.getSection() + operation_left.getOperation();
    }

    /**
     * Evaluate the expression
     * @return the boolean value compared by the comparator
     */
    public boolean eval() {
        if (comparator == null) throw new IllegalArgumentException("Unknown Comparison Operator.");
        return comparator.compare(new Operand(operation_right.eval()), new Operand(operation_left.eval()));
    }

    public String getComparison() {
        return this.comparison;
    }
}
