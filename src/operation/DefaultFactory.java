package operation;

import operation.entities.Constant;
import operation.entities.Operand;
import operation.entities.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class DefaultFactory {

    public List<Operator> getDefaultOperator() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator("+", "add", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION) {
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    number += operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("-", "subtract", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION) {
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    number -= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("*", "multiply", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION){
            @Override
            public Operand action(Operand... operands) {
                double number = 1;
                for (Operand operand : operands) {
                    number *= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("/", "divide", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION){
            @Override
            public Operand action(Operand... operands) {
                double number = 1;
                for (Operand operand : operands) {
                    if(operand.getOperand() == 0)
                        throw new ArithmeticException("Division by 0.");
                    number /= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("%", "mod", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION){
            @Override
            public Operand action(Operand... operands) {
                if(operands[0].getOperand() == 0)
                    throw new ArithmeticException("Division (mod) by 0.");

                double number = 1;
                number  = operands[1].getOperand() % operands[0].getOperand();
                return new Operand(number);
            }
        });
        operators.add(new Operator("!", "factorial", Operator.ARITY_UNARY, Operator.PRECEDENCE_FORCE) {
            @Override
            public Operand action(Operand... operands) {
                Operand op = operands[0];
                if(op.getOperand() < 0)
                    throw new IllegalArgumentException("Operand of factorial must be greater than 0.");

                double number = 1;
                for(int i = 1; i <= operands[0].getOperand(); i++) {
                    number *= i;
                }
                return new Operand(number);
            }
        });
        return operators;
    }

    public List<Constant> getDefaultConstant() {
        List<Constant> constants = new ArrayList<>();

        constants.add(new Constant(Math.PI, "pi", "π"));
        constants.add(new Constant(Math.E, "e"));
        constants.add(new Constant(1.6180339887498948482, "φ"));
        return constants;
    }

}
