import operation.DefaultFactory;
import operation.Operation;
import operation.Section;
import operation.entities.Constant;
import operation.entities.Function;
import operation.entities.Operand;
import operation.entities.Operator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Build an operation base on a String. Includes parser.
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
    private HashMap<String, Function> functions;

    private String parenthesis_open = "(";
    private String parenthesis_close = ")";
    private String argument_separator = ",";

    /* Default */
    private DefaultFactory defaultFactory = new DefaultFactory();

    public OperationBuilder(String operation) {
        this.operation = operation.toLowerCase();
        sections = new ArrayList<>();
        operatorStack = new Stack<>();
        operators = new HashMap<>();
        constants = new HashMap<>();
        functions = new HashMap<>();
        addDefaultPack();
    }

    /**
     * Change the operation into reverse polish
     * @return OperationBuilder, easier for chaining
     * @throws IllegalArgumentException for unknown characters in this operation
     */
    public OperationBuilder parse() {

        Matcher matcher = buildRegex();
        int matchCount = 0;

        while (matcher.find()) {
            matchCount++;
            String section = matcher.group();
            System.out.println("Group "+matchCount+" "+section);

            /** Number */
            if (Util.isNumber(section)) {

                sections.add(new Operand(Double.parseDouble(section)));

            /** Operator */
            } else if (operators.containsKey(section)) {
                handleOperator(section);

            /** Constant */
            } else if (constants.containsKey(section)) {

                sections.add(new Operand(constants.get(section).getValue()));

            /** Function */
            } else if (functions.containsKey(section)) {

                operatorStack.push(functions.get(section));

            /** Open Parenthesis */
            } else if (parenthesis_open.equals(section)) {     // Open Parenthesis

                operatorStack.push(new Section(parenthesis_open));

            /** Close Parenthesis */
            } else if (parenthesis_close.equals(section)) {     // Close Parenthesis

                while (!operatorStack.peek().getSection().equals(parenthesis_open)) {
                    // Add every operator before "(" to list
                    sections.add(operatorStack.pop());
                }

                if (operatorStack.peek().getSection().equals(parenthesis_open))      // Pop "(", ignore return value
                    operatorStack.pop();
                if (!operatorStack.isEmpty() && operatorStack.peek() instanceof Function)     // If first token is function token, pop
                    sections.add(operatorStack.pop());

            /** Arguments Separator (For functions, i.e. comma) */
            /*} else if (argument_separator.equals(section)) {

                while (operatorStack.isEmpty() && !operatorStack.peek().getSection().equals(parenthesis_open)) {
                    sections.add(operatorStack.pop());
                    //throw new IllegalArgumentException("Unclosed function parenthesis.");
                }*/

            /** Unknown */
            } else {    // Unknown operator, constant, or function
                throw new IllegalArgumentException("Unknown section \""+section+"\" at index: "+operation.indexOf(section));
            }
        }

        /** Add all operators left in the stack */
        while(!operatorStack.empty()) {
            sections.add(operatorStack.pop());
        }

        return this;
    }

    /**
     * Handle a operator
     * @param section the operator's string representation
     */
    private void handleOperator(String section) {
        Operator op = operators.get(section);

        /* Operator that is not forced to be pushed */
        if (op.getPrecedence() < Operator.PRECEDENCE_FORCE) {
            while (!operatorStack.empty()) {

                /* Only considering Operator object in the stack */
                if (operatorStack.peek() instanceof Operator) {
                    Operator op2 = (Operator) operatorStack.peek();

                    /* Determine Precedence and pop if needed */
                    if (op.getAssociative() && op.getPrecedence() <= op2.getPrecedence() ||
                            !op.getAssociative() && op.getPrecedence() < op2.getPrecedence()) {
                        sections.add(operatorStack.pop());
                    } else {
                        break;
                    }
                /* Avoid Parenthesis */
                } else break;
            }
            operatorStack.push(op);

        /* Force push operator like "!" */
        } else {
            sections.add(op);
        }
    }

    /**
     * @return Operation in reverse polish order
     */
    public Operation build() {
        return new Operation(operation, sections.toArray(new Section[sections.size()]));
    }

    /**
     * @return a Matcher that matches all operators and constants
     */
    private Matcher buildRegex() {
        String regexOperator = "", regexConstant = "", regexFunction = "", regexNotation;

        for(String s : operators.keySet()) regexOperator += "\\"+s;

        for(String s : constants.keySet()) regexConstant += s+"|";
        regexConstant = regexConstant.substring(0,regexConstant.length()-1);

        for(String s : functions.keySet()) regexFunction += s+"|";
        regexFunction = regexFunction.substring(0,regexFunction.length()-1);

        regexNotation = "\\" + parenthesis_open + "\\" + parenthesis_close + "\\" + argument_separator;

        Pattern regex = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|"
                        +"(["+regexNotation+"])|"
                        +"(["+regexOperator+"])|"+"("+regexConstant+")|("+regexFunction+"))");

        return regex.matcher(operation);
    }

    /**
     * @param operators varargs of operators to be added
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder addOperator(Operator... operators) {
        for(Operator op : operators) {
            this.operators.put(op.getSection(), op);
        }
        return this;
    }

    /**
     * @param constants varargs of constants to be added
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder addConstant(Constant... constants) {
        for(Constant con : constants) {
            for(String key : con.getKeys()) this.constants.put(key, con);
        }
        return this;
    }

    /**
     * @param functions varargs of constants to be added
     * @return OperationBuilder, easier for chaining
     */
    public OperationBuilder addFunction(Function... functions) {
        for(Function func : functions) {
            this.functions.put(func.getSection(), func);
        }
        return this;
    }

    public void setParenthesis(String open, String close) {
        this.parenthesis_open = open;
        this.parenthesis_close = close;
    }

    public void setArgumentSeparator(String argument_separator) {
        this.argument_separator = argument_separator;
    }

    /**
     * @return a reverse polish of the given operation
     */
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
        addFunction(defaultFactory.getDefaultFunction().toArray(new Function[defaultFactory.getDefaultFunction().size()]));
    }
}

