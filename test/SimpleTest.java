import math.comparison.ComparisonBuilder;
import math.operation.OperationBuilder;

import java.util.Scanner;

/**
 * Simple tester for Mathagician Math Evaluation Library
 * @author AlienIdeology
 */
public class SimpleTest {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();

            if (input.startsWith("compare ")) {

                String compare = input.replaceFirst("compare ", "");

                ComparisonBuilder comparison = new ComparisonBuilder(compare).parse().build();
                boolean result = comparison.eval();

                System.out.println("Input: " + compare + "\nComparison Result: " + result);

            } else {

                OperationBuilder builder = new OperationBuilder(input).parse();
                double result = builder.build().eval();

                System.out.println("Input: " + "\nCalculation Result: " + result);

            }
        }
    }

}
