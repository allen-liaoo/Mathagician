package math.operation.entities;

import math.Operand;
import math.Section;

/**
 * Functions like sin(), cos()
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public abstract class Function extends Section {

    private final int parameters;

    public Function(String element, int parameters) {
        super(element);
        this.parameters = parameters;
    }

    public int getParameters() {
        return parameters;
    }

    /**
     * Checker for the varargs operands
     * @param operands
     * @return
     */
    public Operand apply(Operand... operands) {
        if(operands.length != parameters) {
            throw new IllegalArgumentException(getSection() + " function only accept " + parameters + " operands as parameter. "
                    + "(Parameter passed: " + operands.length + ")");
        }
        return function(operands);
    }

    public abstract Operand function(Operand... operands);

    @Override
    public boolean equals(Object o) {
        return o instanceof Function && this.getSection().equals(((Function) o).getSection()) && this.getParameters() == ((Function) o).getParameters();
    }
}
