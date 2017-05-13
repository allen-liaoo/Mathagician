package operation;

import operation.section.Operand;
import operation.section.Operator;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class DefaultOperator {

    public Operator[] getDefaultOperator()
    {
        Operator[] operators = new Operator[4];
        operators[0] = new Operator("+", "add", 2, 0) {
            @Override
            public Operand function(Operand... operands) {
                if(operands.length != 2)
                    throw new IllegalArgumentException("Addition need to be have two numbers as parameter!");

                double number = 0;
                for (Operand operand : operands) {
                    number += operand.getOperand();
                }
                return new Operand(number);
            }
        };
        operators[1] = (new Operator("-", "subtract", 2, 0) {
            @Override
            public Operand function(Operand... operands) {
                if(operands.length != 2)
                    throw new IllegalArgumentException("Subtraction need to be have two numbers as parameter!");

                double number = 0;
                for (Operand operand : operands) {
                    number -= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators[2] = new Operator("*", "multiply", 2, 1){
            @Override
            public Operand function(Operand... operands) {
                if(operands.length != 2)
                    throw new IllegalArgumentException("Multiplication need to be have two numbers as parameter!");

                double number = 0;
                for (Operand operand : operands) {
                    number *= operand.getOperand();
                }
                return new Operand(number);
            }
        };
        operators[3] = new Operator("/", "divide", 2, 1){
            @Override
            public Operand function(Operand... operands) {
                if(operands.length != 2)
                    throw new IllegalArgumentException("Division need to be have two numbers as parameter!");

                double number = 0;
                for (Operand operand : operands) {
                    if(operand.getOperand() == 0)
                        throw new ArithmeticException("Division by 0.");
                    number /= operand.getOperand();
                }
                return new Operand(number);
            }
        };
        return operators;
    }

}
