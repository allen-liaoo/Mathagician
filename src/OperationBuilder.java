import operation.DefaultFactory;
import operation.Operation;
import operation.Section;
import operation.entities.Constant;
import operation.entities.Operand;
import operation.entities.Operator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private DefaultFactory defaultFactory = new DefaultFactory();

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
        Matcher matcher = buildRegex();
        while (matcher.find()) {
            String section = matcher.group();
        if (Util.isNumber(section)) {     // Number
                sections.add(new Operand(Double.parseDouble(section)));
            } else if (operators.containsKey(section)) {      // Operator
                Operator op = operators.get(section);

                if (op.getPrecedence() < Operator.PRECEDENCE_FORCE) {       // Operator that is not forced to be pushed
                    while (!operatorStack.empty()) {
                        Operator op2 = (Operator) operatorStack.peek();

                        // Determine Precedence and pop if needed
                        if (op2.getPrecedence() > op.getPrecedence()) {
                            sections.add(operatorStack.pop());
                        } else {
                            break;
                        }
                    }
                    operatorStack.push(op);

                } else if (op.getPrecedence() >= Operator.PRECEDENCE_FORCE) {   // Force push operator like "!"
                    sections.add(op);
                }

            } else if (constants.containsKey(section)) {          // Constant
                sections.add(new Operand(constants.get(section).getValue()));
            } else if ("(".equals(section)) {     // Open Parenthesis (Cause ClassCastException)
                operatorStack.push(new Operator("(", "open parenthesis", Operator.ARITY_BINARY+1, Operator.PRECEDENCE_FORCE) {
                    @Override
                    public Operand action(Operand... operands) {
                        return operands[0];
                    }
                });
            } else if(")".equals(section)) {     // Close Parenthesis
                while(!operatorStack.isEmpty()) {
                    // Add every operator before "(" to list
                    if(!operatorStack.peek().getSection().equals("("))
                        sections.add(operatorStack.pop());
                    // Pop "(", ignore return value
                    else {
                        operatorStack.pop();
                        break;
                    }
                }

            } else {    // Unknown operator, constant, or function
                throw new IllegalArgumentException("Unknown section \""+section+"\" at index: "+operation.indexOf(section));
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

    private Matcher buildRegex() {
        String regexOperator = "", regexConstant = "";
        for(String s : operators.keySet()) {
            regexOperator += "\\"+s;
        }
        for(String s : constants.keySet()) {
            regexConstant += s+"|";
        }
        regexConstant = regexConstant.substring(0,regexConstant.length()-1);
        Pattern regex = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\()"+regexOperator+"])|("+regexConstant+"))");
        return regex.matcher(operation);
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
        addOperator(defaultFactory.getDefaultOperator().toArray(new Operator[defaultFactory.getDefaultOperator().size()]));
        addConstant(defaultFactory.getDefaultConstant().toArray(new Constant[defaultFactory.getDefaultConstant().size()]));
    }
}

