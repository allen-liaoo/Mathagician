package math.comparison;

import math.Operand;
import math.operation.Operation;
import math.operation.OperationBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AlienIdeology
 */
public class ComparisonBuilder {

    private String comparison;
    private Comparator comparator;
    private Operation operation1;
    private Operation operation2;
    private Operand operand1;
    private Operand operand2;

    private HashMap<String, Comparator> operators;
    private ComparisonDefaultFactory defaultFactory = new ComparisonDefaultFactory();

    /**
     * Default Constructor
     * @param comparison The comparison expression
     */
    public ComparisonBuilder(String comparison) {
        this.comparison = comparison;
        this.operators = new HashMap<>();
        addDefaultPack();
    }

    /**
     * Parametric Constructor
     * @param operation1
     * @param comparison
     * @param operation2
     */
    public ComparisonBuilder(Operation operation1, String comparison, Operation operation2) {
        this.comparison = comparison;
        this.operation1 = operation1;
        this.operation2 = operation2;
        this.operators = new HashMap<>();
        addDefaultPack();
    }

    public ComparisonBuilder parse() {

        /* One of the operation variable was not initialized */
        if ((operation1 == null && operation2 != null) || (operation1 != null && operation2 == null)) {
            throw new NullPointerException("One of the operation must be initialized.");

        /* Default Constructor */
        } else if (operation1 == null) {
            Matcher matcher = Pattern.compile(buildRegex()).matcher(comparison);

            if (matcher.find()) {

                comparator = operators.get(matcher.group());
                System.out.println(buildRegex());
                System.out.println(matcher.start());
                System.out.println(matcher.group());
                String[] operations = comparison.split(buildRegex(), 2);

                Arrays.stream(operations).forEach(op -> System.out.println("Split: "+op));

                /* Parsing String Expression (Default Constructor) */
                if (operations.length != 2) {
                    throw new IllegalArgumentException("Comparisons need to have two operations. Found: " + operations.length);
                } else {
                    operation1 = new OperationBuilder(operations[0]).parse().build();
                    operation2 = new OperationBuilder(operations[1]).parse().build();
                }
            } else {
                throw new IllegalArgumentException("Cannot find a comparison operator in the expression.");
            }
        }

        return this;
    }

    public ComparisonBuilder build() {
        if (operation1 != null) {
            operand1 = new Operand(operation1.eval());
        }
        if (operation2 != null) {
            operand2 = new Operand(operation2.eval());
        }
        return this;
    }

    public boolean eval() {
        return comparator.compare(operand1, operand2);
    }

    public ComparisonBuilder addComparisonOperator(Comparator... comparators) {
        for (Comparator com : comparators) {
            operators.put(com.getSection(), com);
        }
        return this;
    }

    private String buildRegex() {
        StringBuilder comparisons = new StringBuilder("(");
        int counter = 0;
        for (Comparator com : operators.values()) {
            comparisons.append(com.getSection()).append(counter == operators.size() ? "" : "|");
            counter++;
        }
        return comparisons.append(")").toString();
    }

    private void addDefaultPack() {
        addComparisonOperator(defaultFactory.getDefaultComparisonOperator().toArray(new Comparator[defaultFactory.getDefaultComparisonOperator().size()]));
    }

}
