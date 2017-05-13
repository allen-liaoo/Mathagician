package operation.section;

import operation.Section;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class Operand extends Section {

    private double operand;

    public Operand(String element, double operand) {
        super(element);
        this.operand = operand;
    }

    public double getOperand() {
        return operand;
    }

    public double setOperand(double number) {
        return this.operand = number;
    }

    public boolean equals(Operand num) {
        return num.getOperand() == operand;
    }
}
