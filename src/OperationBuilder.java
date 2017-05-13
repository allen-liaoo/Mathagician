import operation.DefaultPack;
import operation.Operation;
import operation.Section;
import operation.entities.Constant;
import operation.entities.Operand;
import operation.entities.Operator;

import java.util.*;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class OperationBuilder {

    /* Original Operation */
    private String operation;

    /* Operation Content */
    private List<Section> sections;
    private Stack<Section> operatorStack;

    /* Customizable */
    private HashMap<String, Operator> operators;
    private HashMap<String, Constant> constants;

    /* Default */
    private DefaultPack defaultPack = new DefaultPack();

    public OperationBuilder(String operation) {
        this.operation = operation.toLowerCase();
        sections = new ArrayList<>();
        operatorStack = new Stack<>();
        operators = new HashMap<>();
        constants = new HashMap<>();
        addDefaultPack();
    }

    /**
     * Change the operation into reverse polish
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder parse() {
        StringBuilder ops = new StringBuilder(operation);
        for(int i = 0; i < operation.length(); i++) {
            String character = String.valueOf(ops.charAt(i));
            if(Util.isNumber(character)) {
                sections.add(new Operand(Double.parseDouble(character)));
            } else if(operators.containsKey(character)) {
                Operator op = operators.get(character);
                while (!operatorStack.empty()) {
                    Operator op2 = (Operator) operatorStack.peek();
                    if(op2.getPrecedence() > op.getPrecedence()) {
                        sections.add(operatorStack.pop());
                    } else {
                        break;
                    }
                }
                operatorStack.push(op);
            } else if(constants.containsKey(character)) {
                sections.add(new Operand(constants.get(character).getValue()));
            } else {
                throw new IllegalArgumentException("Unknown operator "+character+" at index: "+i);
            }
        }

        while(!operatorStack.empty()) {
            sections.add(operatorStack.pop());
        }

        return this;
    }

    /**
     * @return Operation in reverse polish order
     */
    public Operation build() {
        return new Operation(sections.toArray(new Section[sections.size()]));
    }

    /**
     * @param operators varargs of operators to be added
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder addOperator(Operator... operators)
    {
        for(Operator op : operators) {
            this.operators.put(op.getSection(), op);
        }
        return this;
    }

    /**
     * @param constants varargs of constants to be added
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder addConstant(Constant... constants)
    {
        for(Constant con : constants) {
            for(String key : con.getKeys()) this.constants.put(key, con);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Section sec : sections) output.append(sec.toString()).append(" ");
        return output.toString();
    }

    private void addDefaultPack()
    {
        addOperator(defaultPack.getDefaultOperator().toArray(new Operator[defaultPack.getDefaultOperator().size()]));
        addConstant(defaultPack.getDefaultConstant().toArray(new Constant[defaultPack.getDefaultConstant().size()]));
    }
}

