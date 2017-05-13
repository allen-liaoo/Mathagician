import operation.Section;
import operation.section.Number;
import operation.section.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class OperationBuilder {

    private String operation;
    private DefaultOperator defualtOps = new DefaultOperator();
    private List<Section> sections;
    private List<Operator> operators;

    public OperationBuilder(String operation) {
        sections = new ArrayList<>();
        operators = new ArrayList<>();
        this.operation = operation.toLowerCase();
    }

    public OperationBuilder parse() {
        StringBuilder op = new StringBuilder(operation);
        for(int i = 0; i < operation.length(); i++) {
            String character = String.valueOf(op.charAt(i));
            if(isInteger(character)) {
                sections.add(new Number(character, Integer.parseInt(character)));
            } else if(defualtOps.getOperatorKeys().contains(character)) {
                sections.add(defualtOps.getOperators().get(defualtOps.getOperatorKeys().indexOf(character)));
                System.out.println(character);
            }
        }

        return this;
    }

    public OperationBuilder evaluate() {
        for(int i = 0; i < sections.size(); i++) {
            Section sec = sections.get(i);
            if(sec instanceof Operator) {
                Operator op = (Operator) sec;
                
            }
        }
        return this;
    }

    private boolean isInteger(String test) {
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        for(Section sec : sections) {
            if(sec instanceof Number)
                return sec.toString();
        }
        return null;
        //return operation;
    }
}
