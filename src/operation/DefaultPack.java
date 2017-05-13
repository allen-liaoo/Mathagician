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
public class DefaultPack {

    public List<Operator> getDefaultOperator() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator("+", "add", 2, 0) {
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    number += operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("-", "subtract", 2, 0) {
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    number -= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("*", "multiply", 2, 1){
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    number *= operand.getOperand();
                }
                return new Operand(number);
            }
        });
        operators.add(new Operator("/", "divide", 2, 1){
            @Override
            public Operand action(Operand... operands) {
                double number = 0;
                for (Operand operand : operands) {
                    if(operand.getOperand() == 0)
                        throw new ArithmeticException("Division by 0.");
                    number /= operand.getOperand();
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
