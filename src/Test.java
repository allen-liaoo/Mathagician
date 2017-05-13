/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Test
{
    public static void main(String[] args)
    {
        String expression = "2-2*3";
        System.out.println("Expression: " + expression);
        OperationBuilder operation = new OperationBuilder(expression).parse();
        System.out.println("Reverse Polish: " + operation);
        System.out.println("Eval stack: " + operation.build().stack());
    }
}