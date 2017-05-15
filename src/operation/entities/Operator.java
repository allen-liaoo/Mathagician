package operation.entities;

import operation.Section;

/**
 * Operator of a math operation
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public abstract class Operator extends Section {

    /* Setting */
    private final String name;
    private final int precedence;
    private final int arity;
    private final boolean leftAssociative;

    /* Operator Precedence */
    public static int PRECEDENCE_ADDITION = 1;          // Addition, Subtraction
    public static int PRECEDENCE_MULTIPLICATION = 2;    // Multiplication, Division
    public static int PRECEDENCE_POWER = 3;             // Power
    public static int PRECEDENCE_FORCE = 15;            // First Priority

    public static int ARITY_UNARY = 1;
    public static int ARITY_BINARY = 2;

    /**
     * Constructor of Operator
     * @param element the operator
     * @param name the short name of this operator
     * @param arity unary or binary
     * @param precedence precedence of this operator
     */
    public Operator(String element, String name, int arity, int precedence, boolean leftAssociative) {
        super(element);
        this.name = name;
        this.arity = arity;
        this.precedence = precedence;
        this.leftAssociative = leftAssociative;
    }

    public String getName() {
        return name;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getArity() {
        return arity;
    }

    public boolean getAssociative() {
        return leftAssociative;
    }

    /**
     * Checker for the varargs operands
     * @param operands
     * @return
     */
    public Operand apply(Operand... operands) {
        if(operands.length != arity) {
            throw new IllegalArgumentException(name + " operator only accept " + arity + " operands as parameter. "
                    + "(Parameter passed: " + operands.length + ")");
        }
        return operate(operands);
    }

    /**
     * If passing 2 operands, should be operand[1] / operand [0]
     * @param operands 1~2 operands, depend on the arity
     * @return the Operand result object
     */
    public abstract Operand operate(Operand... operands);

}
