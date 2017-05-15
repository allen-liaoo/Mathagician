
/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Test
{
    public static void main(String[] args)
    {
        String expression = "csc(30)";
        System.out.println("Expression: " + expression);

        OperationBuilder operation = new OperationBuilder(expression).parse();

        System.out.println("Reverse Polish: " + operation);
        System.out.println("Answer: " + operation.build().eval());
    }
}