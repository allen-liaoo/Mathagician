package operation.section;

import operation.Section;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public abstract class Operator extends Section {

    private final String name;
    private final int precedence;

    public Operator(String element, String name, int precedence) {
        super(element);
        this.name = name;
        this.precedence = precedence;
    }

    public String getName() {
        return name;
    }

    public int getPrecedence() {
        return precedence;
    }

    public abstract Operand function(Operand num, Operand num2);

}
