package operation.entities;

import operation.Section;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public abstract class Operator extends Section {

    private final String name;
    private final int precedence;
    private final int arity;

    /* Operator Precedence */
    public static int PRECEDENCE_ADDITION = 1;          // Addition, Subtraction
    public static int PRECEDENCE_MULTIPLICATION = 2;    // Multiplication, Division
    public static int PRECEDENCE_POWER = 3;             // Power
    public static int PRECEDENCE_FORCE = 15;            // First Priority

    public static int ARITY_UNARY = 1;
    public static int ARITY_BINARY = 2;

    public Operator(String element, String name, int arity, int precedence) {
        super(element);
        this.name = name;
        this.arity = arity;
        this.precedence = precedence;
    }

    public String getName() {
        return name;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getOperandLimit() {
        return arity;
    }

    public Operand apply(Operand... operands) {
        if(operands.length != arity) {
            throw new IllegalArgumentException(name + " operator only accept " + arity + " operands as parameter. "
                    + "(Parameter passed: " + operands.length + ")");
        }
        return action(operands);
    }

    public abstract Operand action(Operand... operands);

}
