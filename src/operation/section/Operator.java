package operation.section;

import operation.Section;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public abstract class Operator extends Section {

    private final String name;
    private final int operandLimit;
    private final int precedence;

    public Operator(String element, String name, int operandLimit, int precedence) {
        super(element);
        this.name = name;
        this.operandLimit = operandLimit;
        this.precedence = precedence;
    }

    public String getName() {
        return name;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getOperandLimit() {
        return operandLimit;
    }

    public Operand apply(Operand... operands) {
        if(operands.length != operandLimit) {
            throw new IllegalArgumentException(name + " operator only accept " + operandLimit + " operands as parameter. "
                    + "(Parameter passed: " + operands.length + ")");
        }
        return function(operands);
    }

    public abstract Operand function(Operand... operands);

}
