package operation;

import operation.entities.Function;
import operation.entities.Operand;
import operation.entities.Operator;

import java.util.*;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Operation {

    private String operation;
    private Queue<Section> sections;
    private Stack<Operand> operands;

    public Operation(String operation, Section... sections) {
        this.operation = operation;
        this.sections = new LinkedList<>(Arrays.asList(sections));
        this.operands = new Stack<>();
    }

    /**
     * @return result of the math sections
     * @throws UnsupportedOperationException if there is no result for this sections
     */
    public double eval() {
        while(!sections.isEmpty()) {
            Section sec = sections.peek();
            if(sec instanceof Operand) {
                operands.push((Operand) sections.poll());
            } else if(sec instanceof Operator) {
                Operator op = (Operator) sec;

                List<Operand> opargs = new ArrayList<>();
                for (int i = 0; i < op.getArity(); i++) {

                    // Adding operands at index 0 to reverse the order to normal
                    // 5 % 3 would came out as 3 -> 5, so reversing it gives normal order
                    opargs.add(0, this.operands.pop());
                }

                operands.push(op.apply(opargs.toArray(new Operand[opargs.size()])));
                sections.remove();

            /* Function */
            } else if(sec instanceof Function) {
                Function func = (Function) sec;

                List<Operand> funcargs = new ArrayList<>();
                for (int i = 0; i < func.getParameters(); i++) {
                    funcargs.add(0, this.operands.pop());
                }

                operands.push(func.apply(funcargs.toArray(new Operand[funcargs.size()])));
                sections.remove();
            }

            /* Return if there is only one operand left in the stack */
            if(operands.size() == 1 && sections.isEmpty()) {
                return returnMachine(operands.pop()).getNumber();
            }
        }

        /* No Result */
        if (operands.isEmpty()) {
            throw new UnsupportedOperationException("No result.");

        /* Multiply all operands left in the stack */
        } else {
            double result = 1;
            for (Operand op : operands) {
                result *= op.getNumber();
            }
            return result;
        }
    }

    private Operand returnMachine(Operand operand) {
        return operand;
    }

    public String getOperation() {
        return operation;
    }

    /* Trace:
        System.out.println(opargs.get(1).getNumber()+" " +op.getSection()+" "+opargs.get(0).getNumber()+" = "+operands.peek().getNumber());
        System.out.print("\nQueue: ");
        for(Section s : sections) {
            System.out.print(s.getSection()+" ");
        }
        System.out.print("\nStack: ");
        for(Operand d : operands) {
            System.out.print(d.getNumber()+" ");
        }
        System.out.println();
     */

}
