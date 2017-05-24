package math.operation;

import math.operation.entities.Constant;
import math.operation.entities.Function;
import math.Operand;
import math.operation.entities.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Operators, Constants, and Functions
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class OperationDefaultFactory {

    public List<Operator> getDefaultOperator() {
        List<Operator> operators = new ArrayList<>();
        operators.add(ADD);
        operators.add(SUBTRACT);
        operators.add(MULTIPLY);
        operators.add(DIVIDE);
        operators.add(MOD);
        operators.add(POWER);
        operators.add(FACTORIAL);
        return operators;
    }

    public List<Constant> getDefaultConstant() {
        List<Constant> constants = new ArrayList<>();
        constants.add(PI);
        constants.add(E);
        constants.add(GOLDEN_RATIO);
        return constants;
    }

    public List<Function> getDefaultFunction() {
        List<Function> functions = new ArrayList<>();
        functions.add(SIN);
        functions.add(COS);
        functions.add(TAN);
        functions.add(CSC);
        functions.add(SEC);
        functions.add(COT);
        return functions;
    }

    /* Operators */
    public final static Operator ADD = new Operator("+", "add", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION, true) {
        @Override
        public Operand operate(Operand... operands) {
            return new Operand(operands[0].getNumber() + operands[1].getNumber());
        }
    };

    public final static Operator SUBTRACT = new Operator("-", "subtract", Operator.ARITY_BINARY, Operator.PRECEDENCE_ADDITION, true) {
        @Override
        public Operand operate(Operand... operands) {
            return new Operand(operands[0].getNumber() - operands[1].getNumber());
        }
    };

    public final static Operator MULTIPLY = new Operator("*", "multiply", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
        @Override
        public Operand operate(Operand... operands) {
            return new Operand(operands[0].getNumber() * operands[1].getNumber());
        }
    };

    public final static Operator DIVIDE = new Operator("/", "divide", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
        @Override
        public Operand operate(Operand... operands) {
            if(operands[1].getNumber() == 0)
                throw new ArithmeticException("Division by 0.");
            return new Operand(operands[0].getNumber() / operands[1].getNumber());
        }
    };

    public final static Operator MOD = new Operator("%", "mod", Operator.ARITY_BINARY, Operator.PRECEDENCE_MULTIPLICATION, true){
        @Override
        public Operand operate(Operand... operands) {
            if(operands[1].getNumber() == 0)
                throw new ArithmeticException("Division (mod) by 0.");

            double number = 1;
            number  = operands[0].getNumber() % operands[1].getNumber();
            return new Operand(number);
        }
    };

    public final static Operator POWER = new Operator("^", "power", Operator.ARITY_BINARY, Operator.PRECEDENCE_POWER, false) {
        @Override
        public Operand operate(Operand... operands) {
            return new Operand(Math.pow(operands[0].getNumber(), operands[1].getNumber()));
        }
    };

    public final static Operator FACTORIAL = new Operator("!", "factorial", Operator.ARITY_UNARY, Operator.PRECEDENCE_FORCE, true) {
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
    };

    /* Constants */
    public final static Constant PI = new Constant(Math.PI, "pi", "π");
    public final static Constant E = new Constant(Math.E, "e");
    public final static Constant GOLDEN_RATIO = new Constant(1.6180339887498948482, "φ");

    /* Functions */
    public final static Function SIN = new Function("sin", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(Math.sin(Math.toRadians(operands[0].getNumber())));
        }
    };

    public final static Function COS = new Function("cos", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(Math.cos(Math.toRadians(operands[0].getNumber())));
        }
    };

    public final static Function TAN = new Function("tan", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(Math.tan(Math.toRadians(operands[0].getNumber())));
        }
    };

    public final static Function CSC = new Function("csc", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(1/Math.sin(Math.toRadians(operands[0].getNumber())));
        }
    };

    public final static Function SEC = new Function("sec", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(1/Math.cos(Math.toRadians(operands[0].getNumber())));
        }
    };

    public final static Function COT = new Function("cot", 1) {
        @Override
        public Operand function(Operand... operands) {
            return new Operand(1/Math.tan(Math.toRadians(operands[0].getNumber())));
        }
    };

}
