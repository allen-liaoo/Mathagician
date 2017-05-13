package operation.section;

import operation.Section;

/**
 * Created by liaoyilin on 5/12/17.
 */
public abstract class Operator extends Section {

    private String name;

    public Operator(String element, String name) {
        super(element);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Number function(Number num);

}
