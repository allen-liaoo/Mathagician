package operation.entities;

import operation.Section;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class Operand extends Section {

    private double number;

    public Operand(double number) {
        super(number +"");
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public double setNumber(double number) {
        return this.number = number;
    }

    public boolean equals(Operand num) {
        return num.getNumber() == number;
    }
}
