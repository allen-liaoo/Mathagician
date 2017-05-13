package operation.section;

import operation.Section;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class Number extends Section {

    private double number;

    public Number(String element, double number) {
        super(element);
        this.number = number;
    }

    public Number add(Number num) {
        number += num.getNumber();
        return this;
    }

    public Number subtract(Number num) {
        number -= num.getNumber();
        return this;
    }

    public Number multiply(Number num) {
        number *= num.getNumber();
        return this;
    }

    public Number divide(Number num) {
        number /= num.getNumber();
        return this;
    }

    public double getNumber() {
        return number;
    }

    public boolean equals(Number num) {
        return num.getNumber() == number;
    }
}
