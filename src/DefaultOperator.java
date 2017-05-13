import operation.section.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class DefaultOperator {

    private List<Operator> operators;

    public DefaultOperator(){
        operators = new ArrayList<>();
        operators.add(new Operator("+", "add"));
        operators.add(new Operator("-", "subtract"));
        operators.add(new Operator("*", "multiply"));
        operators.add(new Operator("/", "divide"));
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public List<String> getOperatorKeys() {
        List<String> list = new ArrayList<>();
        for(Operator op : operators){
            list.add(op.getElement());
        }
        return list;
    }

}
