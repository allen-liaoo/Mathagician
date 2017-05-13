import operation.DefaultOperator;
import operation.Operation;
import operation.Section;
import operation.section.Operand;
import operation.section.Operator;

import java.util.*;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class OperationBuilder {

    private String operation;

    private List<Section> sections;
    private Stack<Section> operatorStack;

    private HashMap<String, Operator> operators;

    public OperationBuilder(String operation) {
        this.operation = operation.toLowerCase();
        sections = new ArrayList<>();
        operatorStack = new Stack<>();
        operators = new HashMap<>();
        addDefaultOperator();
    }

    /**
     * Change the operation into reverse polish
     * @return
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Section sec : sections) output.append(sec.toString()+" ");
        return output.toString();
    }

    public OperationBuilder addOperator(Operator... operators)
    {
        for(Operator op : operators) {
            this.operators.put(op.getSection(), op);
        }
        return this;
    }

    private void addDefaultOperator()
    {
        DefaultOperator defaultOp = new DefaultOperator();
        addOperator(defaultOp.getDefaultOperator());
    }
}

