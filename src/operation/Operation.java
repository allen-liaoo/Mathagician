package operation;

import operation.section.Operand;
import operation.section.Operator;

import java.util.*;

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
                Operator op = (Operator) sec;

                List<Operand> opargs = new ArrayList<>();
                for (int i = 0; i < op.getOperandLimit(); i++) {
                    opargs.add(this.operands.pop());
                }
                //Operand pop2 = operands.pop();
                operands.push(op.apply(opargs.toArray(new Operand[opargs.size()])));
                operation.remove();
            }
            if(operands.size() == 1 && operation.isEmpty())
                return operands.pop().getOperand();
        }
        return 0;
    }

    /* Trace:
        System.out.println(pop2.getOperand()+" " +op.getSection()+" "+pop2.getOperand());

        System.out.print("\nQueue: ");
        for(Section s : operation) {
            System.out.print(s.getSection()+" ");
        }
        System.out.print("\nStack: ");
        for(Operand d : operands) {
            System.out.print(d.getOperand()+" ");
        }
        System.out.println();
     */

}
