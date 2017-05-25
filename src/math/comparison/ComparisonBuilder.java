package math.comparison;

import math.Operand;
import math.operation.Operation;
import math.operation.OperationBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Comparison Expression Builder
 * @author AlienIdeology
 */
public class ComparisonBuilder {

    /* Original Expression */
    private String comparison;

    /* Comparator */
    private Comparator comparator;

    /* Operations */
    private Operation operation_left;
    private Operation operation_right;

    /* Resources */
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
     * @param operation_left The left side of this expression
     * @param comparison The comparison operator
     * @param operation_right The right side of this expression
     */
    public ComparisonBuilder(Operation operation_left, String comparison, Operation operation_right) {
        this.comparison = comparison;
        this.operation_left = operation_left;
        this.operation_right = operation_right;
        this.operators = new HashMap<>();
        addDefaultPack();
        this.comparator = operators.get(comparison);
    }

    /**
     * Parse the string comparison, assign left and right operations.
     * This method may not be used if the ComparisonBuilder is constructed using Parametric Constructor
     * @return ComparisonBuilder for chaining
     */
    public ComparisonBuilder parse() {

        /* One of the operation variable was not initialized */
        if ((operation_left == null && operation_right != null) || (operation_left != null && operation_right == null)) {
            throw new NullPointerException("One of the operation must be initialized.");

        /* Default Constructor */
        } else if (operation_left == null) {
            Matcher matcher = Pattern.compile(buildRegex()).matcher(comparison);

            if (matcher.find()) {

                comparator = operators.get(matcher.group());
                String[] operations = comparison.split(buildRegex(), 2);

                /* Parsing String Expression (Default Constructor) */
                if (operations.length != 2) {
                    throw new IllegalArgumentException("Comparisons need to have two operations. Found: " + operations.length);
                } else {
                    operation_left = new OperationBuilder(operations[0]).parse().build();
                    operation_right = new OperationBuilder(operations[1]).parse().build();
                }
            } else {
                throw new IllegalArgumentException("Cannot find a comparison operator in the expression.");
            }
        }

        return this;
    }

    /**
     * Build the Comparison object
     * @return Comparison for evaluating the comparison expression
     */
    public Comparison build() {
        return new Comparison(operation_right, comparator, operation_left);
    }

    /**
     * @param comparators varargs of comparators to be added
     * @return ComparisonBuilder for chaining
     */
    public ComparisonBuilder addComparisonOperator(Comparator... comparators) {
        for (Comparator com : comparators) {
            operators.put(com.getSection(), com);
        }
        return this;
    }

    private String buildRegex() {
        StringBuilder comparisons = new StringBuilder("(");
        int counter = 0;

        List<Comparator> comparators = new ArrayList<>(operators.values());
        Collections.sort(comparators, (o1, o2) -> o1.getPrecedence() > o2.getPrecedence() ? -1 : o1.getPrecedence() == o2.getPrecedence() ? 0 : 1);


        for (Comparator com : comparators) {
            comparisons.append(com.getSection()).append(counter == operators.size()-1 ? "" : "|");
            counter++;
        }
        return comparisons.append(")").toString();
    }

    private void addDefaultPack() {
        addComparisonOperator(defaultFactory.getDefaultComparisonOperator().toArray(new Comparator[defaultFactory.getDefaultComparisonOperator().size()]));
    }

}
