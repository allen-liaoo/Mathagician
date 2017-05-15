package operation;

import operation.entities.Constant;
import operation.entities.Function;
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

        operators.add(new Operator("+", "add", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION, true) {
            @Override
            public Operand operate(Operand... operands) {
                return new Operand(operands[0].getNumber() + operands[1].getNumber());
            }
        });

        operators.add(new Operator("-", "subtract", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION, true) {
            @Override
            public Operand operate(Operand... operands) {
                return new Operand(operands[0].getNumber() - operands[1].getNumber());
            }
        });

        operators.add(new Operator("*", "multiply", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
            @Override
            public Operand operate(Operand... operands) {
                return new Operand(operands[0].getNumber() * operands[1].getNumber());
            }
        });

        operators.add(new Operator("/", "divide", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
            @Override
            public Operand operate(Operand... operands) {
                if(operands[1].getNumber() == 0)
                    throw new ArithmeticException("Division by 0.");
                return new Operand(operands[0].getNumber() / operands[1].getNumber());
            }
        });

        operators.add(new Operator("%", "mod", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
            @Override
            public Operand operate(Operand... operands) {
                if(operands[1].getNumber() == 0)
                    throw new ArithmeticException("Division (mod) by 0.");

                double number = 1;
                number  = operands[0].getNumber() % operands[1].getNumber();
                return new Operand(number);
            }
        });

        operators.add(new Operator("^", "power", Operator.ARITY_BINARY, Operator.PRECEDENCE_POWER, false) {
            @Override
            public Operand operate(Operand... operands) {
                return new Operand(Math.pow(operands[0].getNumber(), operands[1].getNumber()));
            }
        });

        operators.add(new Operator("!", "factorial", Operator.ARITY_UNARY, Operator.PRECEDENCE_FORCE, true) {
            @Override
            public Operand operate(Operand... operands) {
                Operand op = operands[0];
                if(op.getNumber() < 0)
                    throw new IllegalArgumentException("Operand of factorial must be greater than 0.");

                double number = 1;
                for(int i = 1; i <= op.getNumber(); i++) {
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

    public List<Function> getDefaultFunction() {
        List<Function> functions = new ArrayList<>();

        functions.add(new Function("sin", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(Math.sin(Math.toRadians(operands[0].getNumber())));
            }
        });

        functions.add(new Function("cos", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(Math.cos(Math.toRadians(operands[0].getNumber())));
            }
        });

        functions.add(new Function("tan", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(Math.tan(Math.toRadians(operands[0].getNumber())));
            }
        });

        functions.add(new Function("csc", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(1/Math.sin(Math.toRadians(operands[0].getNumber())));
            }
        });

        functions.add(new Function("sec", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(1/Math.cos(Math.toRadians(operands[0].getNumber())));
            }
        });

        functions.add(new Function("cot", 1) {
            @Override
            public Operand function(Operand... operands) {
                return new Operand(1/Math.tan(Math.toRadians(operands[0].getNumber())));
            }
        });

        return functions;
    }

}
