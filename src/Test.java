
public class Test
{
    public static void main(String[] args)
    {
        String expression = "1*1";
        System.out.println("Expression: "+expression);
        OperationBuilder operation = new OperationBuilder(expression).parse();
        System.out.println(operation);
    }
}