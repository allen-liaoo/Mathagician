package operation.section;

/**
 * Created by liaoyilin on 5/13/17.
 */
public enum OperatorPrecedence {
    ADDITION,
    MULTIPLICATION,
    POWER,
    BRACKET;

    @Override
    public String toString() {
        return name().substring(0,1) + name().substring(1).toLowerCase() + " Precedence";
    }

}
