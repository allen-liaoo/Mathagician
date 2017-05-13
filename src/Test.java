import operation.entities.Operand;
import operation.entities.Operator;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Test
{
    public static void main(String[] args)
    {
        String expression = "φ";
        System.out.println("Expression: " + expression);
        OperationBuilder operation = new OperationBuilder(expression)
            .addOperator(new Operator("!", "factorial", 1, 2) {
                @Override
                public Operand action(Operand... operands) {
                    Operand op = operands[0];
                    if(op.getOperand() < 0)
                        throw new IllegalArgumentException("Operand of factorial must be greater than 0.");

                    double number = 1;
                    for(int i = 1; i < operands[0].getOperand(); i++) {
                        number *= i;
                    }
                    return new Operand(number);
                }
            })
            .parse();
        System.out.println("Reverse Polish: " + operation);
        System.out.println("Answer: " + operation.build().eval());
    }
}