package operation;

import operation.section.Operand;
import operation.section.Operator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Operation {

    private Queue<Section> operation;
    private Stack<Operand> operands;

    public Operation(Section... sections) {
        operation = new LinkedList<>(Arrays.asList(sections));
        operands = new Stack<>();
    }

    public double eval() {
        while(!operation.isEmpty()) {
            Section sec = operation.peek();
            if(sec instanceof Operand) {
                operands.push((Operand) operation.poll());
            } else if(sec instanceof Operator) {
                System.out.println("size: "+operands.size());

                Operator op = (Operator) sec;
                Operand pop1 = operands.pop();
                Operand pop2 = operands.pop();
                operands.push(op.function(pop2, pop1));
            }
        }
        return 0;
    }

    public String stack() {
        eval();
        /*StringBuilder outStack = new StringBuilder();
        for(Operand d : operands) {
            outStack.append(d.getOperand());
        }*/
        return "";
    }

}
